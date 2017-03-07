---
title: ExternalLangDef-ограничение на детейлы (ExistDetals)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_exist-detals.html
---
## Описание ExistDetails
Существуют такие {} и такие {}, что {}. Вернет True, если найдется хотя бы один объект из первого набора и хотя бы один объект из второго набора, удовлетворяющий условию, в противном случае - False. Условие - только одна функция (=, >, <, >=, <=).

## Ограничение на детейл

![](/images/pages/products/flexberry-orm/exist-detals/exist-detals-example-1.png)

В следующем коде подразумевается, что в представлении `"СерверПодразделенияE"` обязательно присутствуют свойства `"Подразделение"` (так как оно есть в условии) и `"Сервер"` (свойство, по которому идет соединение).

```cs
ExternalLangDef ldef = ICSSoft.STORMNET.Windows.Forms.ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(
    typeof (Репликации.Сервер), "СерверE");
lcsСервер.LoadingTypes = new Type[] {typeof (Репликации.Сервер)};
View view = Information.GetView("СерверПодразделенияE", typeof(Репликации.СерверПодразделения));
ICSSoft.STORMNET.Windows.Forms.DetailVariableDef dvd =
    new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef();
dvd.ConnectMasterPorp = "Сервер";
dvd.OwnerConnectProp = new string[] { SQLWhereLanguageDef.StormMainObjectKey };
dvd.View = view;
dvd.Type = ldef.GetObjectType("Details");
lcsСервер.LimitFunction = ldef.GetFunction("Exist",
                                            dvd,
                                            ldef.GetFunction(ldef.funcEQ,
                                                            new VariableDef(ldef.GuidType, "Подразделение"),
                                                            UpdatedObject.НаправленоИз.__PrimaryKey));
lcsСервер.ReturnTop = 1;
ICSSoft.STORMNET.DataObject[] dobjsСервер =
    ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObjects(lcsСервер);
```

Требуется вычитать только те сервера, которые принадлежат только одному конкретному Подразделению
(Т.е. сервера, которые принадлежат и указанному Подразделению и еще какому-то, не будут вычитаны).

```cs
ExternalLangDef ldef = ICSSoft.STORMNET.Windows.Forms.ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(typeof (Сервер), "СерверE");
lcsСервер.LoadingTypes = new[] {typeof (Сервер)};
View view = Information.GetView("СерверПодразделенияE", typeof(Репликации.СерверПодразделения));
var dvd = new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef
                {
                    ConnectMasterPorp = "Сервер",
                    OwnerConnectProp = new[] { SQLWhereLanguageDef.StormMainObjectKey },
                    View = view,
                    Type = ldef.GetObjectType("Details")
                };
lcsСервер.LimitFunction = ldef.GetFunction("ExistExact",
                                            dvd,
                                            ldef.GetFunction(ldef.funcEQ,
                                                            new VariableDef(ldef.GuidType, "Подразделение"),
                                                            new Guid("6D7DC426-F5E9-4F63-B7B5-20C9E237DF2D")));
DataObject[] dobjsСервер = DataServiceProvider.DataService.LoadObjects(lcsСервер);
```


## Сравнения свойств двух различных детейлов (не выше первого уровня) имеющих общий агрегатор

![](/images/pages/products/flexberry-orm/exist-detals/exist-detals-example-2.png)

Например, необходимо получить все компьютеры у которых хотя бы одна "железка" будет куплена раньше, чем любое программное обеспечение для него.
Порядок свойств в функции сравнения накладываемой на детейлы имеет значение (в данном случае имена свойств совпали).

```cs
 View view = Information.GetView("ComputerL", typeof(Computer));
 View view2 = Information.GetView("HardwareD", typeof(Hardware));
 View view3 = Information.GetView("SoftwareD", typeof(Software));
 view.AddDetailInView("Computer", view2, true);
 view.AddDetailInView("Computer", view3, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Computer), view);
 ExternalLangDef langDef = ExternalLangDef.LanguageDef;
 var detail1 = new DetailVariableDef(langDef.GetObjectType("Details"), "Hardware", view2, "Computer");
 var detail2 = new DetailVariableDef(langDef.GetObjectType("Details"), "Software", view3, "Computer");
 lcs.LimitFunction = langDef.GetFunction(langDef.funcExistDetails,
                                         detail1, detail2,
                                         langDef.GetFunction(langDef.funcG,
                                                             new VariableDef(langDef.DateTimeType, "DeliveryDate"),
                                                             new VariableDef(langDef.DateTimeType, "DeliveryDate")));
 var dos = DataServiceProvider.DataService.LoadObjects(lcs);
```

## Смотрите также

* [Exist, ExistExact, ExistAll, ExistAllExact](fo_exist--exist-exact--exist-all--exist-all-exact.html)
* [Фильтрация-по-детейлам-мастера-ConnectMasterProp-OwnerConnectProp.ashx|Фильтрация по детейлам мастера]
* [SQLWhereLanguageDef](fo_function-list.html)
