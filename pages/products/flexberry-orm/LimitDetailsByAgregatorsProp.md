---
title: Ограничение на детейлы с использованием свойств агрегатора
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/limit-details-by-agregators-prop.html
folder: product--folder
lang: ru
---

# Ограничение на детейлы с использованием свойств агрегатора
Если необходимо задать условие на существование [детейлов](key-concepts-flexberry-designer.html) по условию, в котором должно участвовать свойство [агрегатора](key-concepts-flexberry-designer.html), необходимо использовать функцию [Exist](exist--exist-exact--exist-all--exist-all-exact.html). При указании свойств в условии для функции [Exist](exist--exist-exact--exist-all--exist-all-exact.html) необходимо явно указывать, что это свойство агрегатора: 
<Имя агрегирующего свойства у детейла>.<Свойство агрегатора>.

## Пример наложения ограничения на детейлы с использованием свойства агрегатора
![](/images/pages/img/page/LimitDetailsByAgregatorsProp/ExistExample.png)

Выявить ошибочно занесенные данные в базе: найти все компании, у которых существует программный продукт созданный раньше создания самой компании.
```
 DataServiceLoader.InitializeDataSetvice();
 View view = Information.GetView("CompanyL", typeof(Company));
 View view2 = Information.GetView("SoftwareL", typeof(Software));
 view.AddDetailInView("Software", view2, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Company), view);
 ExternalLangDef langDef = ExternalLangDef.LanguageDef;
 var detail = new DetailVariableDef(langDef.GetObjectType("Details"), "Software", view2, "Company");
 lcs.LimitFunction = langDef.GetFunction(langDef.funcExist,
                                         detail,
                                         langDef.GetFunction(langDef.funcL,
                                                             new VariableDef(langDef.DateTimeType, "DateCreation"),
                                                             new VariableDef(langDef.DateTimeType, "Company.DateCreation")));
 var dos = DataServiceProvider.DataService.LoadObjects(lcs);
```
 

