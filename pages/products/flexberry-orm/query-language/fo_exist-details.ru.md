---
title: Ограничения на детейлы
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Функции funcExist, funcExistExact, funcExistAll, funcExistAllExact, funcExistDetails
toc: true
permalink: ru/fo_exist-details.html
lang: ru
---

## Описание и ключевые различия функций

При помощи этих функций [ExternalLangDef](fo_external-lang-def.html) возможно накладывать [ограничения](fo_limit-function.html) только на детейлы ([пример](fo_limit-details.html))

| Название | Отображаемое название | Описание |
|---|---|---|
| `funcExist` | Существуют такие {}, что {} | Вернет True, если найдется хотя бы один объект, удовлетворяющий условию, в противном случае - False. Условие - только одна функция.|
| `funcExistAll` | Существуют все такие {}, что {} И {} И {} ... | Вернет True, если найдется хотя бы один объект, удовлетворяющий условию, в противном случае - False. В качестве условия могут выступать множество функций, которые автоматически соединятся конъюнкцией. Внимание! Допустимых видов функций только две: "=" ([FuncEQ](fo_func-eq.html)) и "СРЕДИ ЗНАЧЕНИЙ()" ([FuncIN](fo_func-in.html)). |
| `funcExistExact` | Существуют только такие {}, что {} | Вернет True, если все объекты удовлетворяют условию, в противном случае - False. Условие - только одна функция. |
| `funcExistAllExact` | Существуют все только такие {}, что {} И {} И {} ... | Вернет True, если все объекты удовлетворяют условию, в противном случае - False. В качестве условия могут выступать множество функций, которые автоматически соединятся конъюнкцией. Внимание! Допустимых видов функций только две: "=" ([FuncEQ](fo_func-eq.html)) и "СРЕДИ ЗНАЧЕНИЙ()" ([FuncIN](fo_func-in.html)).|
| `funcExistDetails` | Существуют такие {} и такие {}, что {} | Функция вернет `True`, если найдется хотя бы один объект из первого набора и хотя бы один объект из второго набора, удовлетворяющий условию, в противном случае - `False`. Условие - только одна функция (=, >, <, >=, <=).|

## Пример использования

![exist-detals-example-2](/images/pages/products/flexberry-orm/query-language/exist-detals-example-2.jpg)

В следующем коде подразумевается, что в представлении `"СерверПодразделенияE"` обязательно присутствуют свойства `"Подразделение"` (так как оно есть в условии) и `"Сервер"` (свойство, по которому идет соединение).

Требуется вычитать те сервера, которые принадлежат определённому Подразделению (т.е. сервера, которые принадлежат и указанному Подразделению и еще какому-то, также будут вычитаны).

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(typeof (Репликации.Сервер), "СерверE");
lcsСервер.LoadingTypes = new Type[] {typeof (Репликации.Сервер)};
View view = Information.GetView("СерверПодразделенияE", typeof(Репликации.СерверПодразделения));
ICSSoft.STORMNET.Windows.Forms.DetailVariableDef dvd = new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef();
dvd.ConnectMasterPorp = "Сервер";
dvd.OwnerConnectProp = new string[] { SQLWhereLanguageDef.StormMainObjectKey };
dvd.View = view;
dvd.Type = languageDef.GetObjectType("Details");
lcsСервер.LimitFunction = languageDef.GetFunction(languageDef.funcExist,
                                            dvd,
                                            languageDef.GetFunction(languageDef.funcEQ,
                                                            new VariableDef(languageDef.GuidType, "Подразделение"),
                                                            UpdatedObject.НаправленоИз.__PrimaryKey));
lcsСервер.ReturnTop = 1;
ICSSoft.STORMNET.DataObject[] dobjsСервер = ICSSoft.STORMNET.Business.ds.LoadObjects(lcsСервер);
```

Требуется вычитать только те сервера, которые принадлежат только одному конкретному Подразделению (т.е. сервера, которые принадлежат и указанному Подразделению и еще какому-то, не будут вычитаны).

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(typeof (Сервер), "СерверE");
lcsСервер.LoadingTypes = new[] {typeof (Сервер)};
View view = Information.GetView("СерверПодразделенияE", typeof(Репликации.СерверПодразделения));
var dvd = new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef
                {
                    ConnectMasterPorp = "Сервер",
                    OwnerConnectProp = new[] { SQLWhereLanguageDef.StormMainObjectKey },
                    View = view,
                    Type = languageDef.GetObjectType("Details")
                };
lcsСервер.LimitFunction = languageDef.GetFunction(languageDef.funcExistExact,
                                            dvd,
                                            languageDef.GetFunction(languageDef.funcEQ,
                                                            new VariableDef(languageDef.GuidType, "Подразделение"),
                                                            new Guid("6D7DC426-F5E9-4F63-B7B5-20C9E237DF2D")));
DataObject[] dobjsСервер = ds.LoadObjects(lcsСервер);
```

## Сравнения свойств двух различных детейлов (не выше первого уровня) имеющих общий агрегатор

Например, необходимо получить все компьютеры у которых хотя бы одна "железка" будет куплена раньше, чем любое программное обеспечение для него.
Порядок свойств в функции сравнения накладываемой на детейлы имеет значение (в данном случае имена свойств совпали).

```csharp
 View view = Information.GetView("ComputerL", typeof(Computer));
 View view2 = Information.GetView("HardwareD", typeof(Hardware));
 View view3 = Information.GetView("SoftwareD", typeof(Software));
 view.AddDetailInView("Computer", view2, true);
 view.AddDetailInView("Computer", view3, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Computer), view);
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);
 var detail1 = new DetailVariableDef(languageDef.GetObjectType("Details"), "Hardware", view2, "Computer");
 var detail2 = new DetailVariableDef(languageDef.GetObjectType("Details"), "Software", view3, "Computer");
 lcs.LimitFunction = languageDef.GetFunction(languageDef.funcExistDetails,
                        detail1, detail2, languageDef.GetFunction(languageDef.funcG,
                        new VariableDef(languageDef.DateTimeType, "DeliveryDate"),
                        new VariableDef(languageDef.DateTimeType, "DeliveryDate")));
 var dos = ds.LoadObjects(lcs);
```
