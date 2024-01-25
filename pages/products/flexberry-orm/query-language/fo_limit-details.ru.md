---
title: Ограничение на детейлы с использованием свойств агрегатора
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Пример использования ограничения
toc: true
permalink: ru/fo_limit-details.html
lang: ru
---

Если необходимо задать условие на существование [детейлов](fd_key-concepts.html) по условию, в котором должно участвовать свойство [агрегатора](fd_key-concepts.html), необходимо использовать функцию [Exist](fo_exist-details.html). При указании свойств в условии для функции `Exist` необходимо явно указывать, что это свойство агрегатора:

```h
<Имя агрегирующего свойства у детейла>.<Свойство агрегатора>.
```

## Пример наложения ограничения на детейлы с использованием свойства агрегатора

![exist-example](/images/pages/products/flexberry-orm/query-language/exist-example.png)

Выявить ошибочно занесенные данные в базе: найти все компании, у которых существует программный продукт созданный раньше создания самой компании.

``` csharp
 DataServiceLoader.InitializeDataSetvice();
 View view = Information.GetView("CompanyL", typeof(Company));
 View view2 = Information.GetView("SoftwareL", typeof(Software));
 view.AddDetailInView("Software", view2, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Company), view);
 IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
 IDataService ds = mainUnityContainer.Resolve<IDataService>();
 ExternalLangDef languageDef = new ExternalLangDef(ds);
 var detail = new DetailVariableDef(languageDef.GetObjectType("Details"), "Software", view2, "Company");
 lcs.LimitFunction = languageDef.GetFunction(languageDef.funcExist, detail,
                                         languageDef.GetFunction(languageDef.funcL,
                                         new VariableDef(languageDef.DateTimeType, Information.ExtractPropertyPath<Software>(x => x.DateCreation)),
                                         new VariableDef(languageDef.DateTimeType, Information.ExtractPropertyPath<Software>(x => x.Company.DateCreation))));
 var dos = ds.LoadObjects(lcs);
```
