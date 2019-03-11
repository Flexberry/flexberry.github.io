--- 
title: Using data types in the structure of the application 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data types, data objects, data services 
summary: Rules for processing a typeusage and specify the names of fields storing links 
toc: true 
permalink: en/fo_type-usage.html 
lang: en 
autotranslated: true 
hash: 30bade3946b65b6e59777ab7ca779f6870aeeef05a04ee82e7df651a77278fd6 
--- 

If the [master Association](fd_master-association.html) has [the heirs](fd_inheritance.html) on the side of the master can be any object type from the inheritance hierarchy. However, there are conditions under which such a situation you want to exclude. To do this, in [Flexberry ORM] implemented `TypeUsage`-an attribute that limits the list of types in the inheritance hierarchy that is covered by this communication. 

## Processing A Typeusage 

Built-in `Flexberry Platform` [data services](fo_data-service.html) ([SQLDataService](fo_sql-data-service.html) and his heirs) should be treated `TypeUsage` as follows: 

If you specify `TypeUsage` [artisan properties](fd_master-association.html), the property [in the data structure match](fo_storing-data-objects.html) the foreign keys in the tables corresponding to the specified `TypeUsage` classes. 

Names the foreign keys are: `<Karolinenstr>_M<ПорядкНомерВTypeUsage>.«ПорядкНомерВTypeUsage»` — starts at 0. 

Thus, the above example is a table that has two foreign key names `M_m0` (corresponding to M1) and `M_m1` (corresponding to M2). 

![](/images/pages/products/flexberry-orm/data-types/primer2.jpg) 

## Job names for fields storing links 

If there is a need to ask for a name and `M_m0` `M_m1` some mnemonic names, you need to: 

1. Disable the checkbox [AutoGenerateTypeUsage](fd_master-association.html). 
2. To properly set the attributes [a typeusage](fo_type-usage-problem.html) and Storage. 

For example, suppose you have a diagram of the form: 

![](/images/pages/products/flexberry-orm/data-types/type-usage-test.png) 

If nothing changes in the settings of the chart elements, for example, in table `ДниПосещения` will be foreign keys with names `Пользователь_m0`, `Пользователь_m1`, `Пользователь_m2`. 

However, if linking classes `Пользователь` and `ДниПосещения` to put in `TypeUsage` and `Storage` the line `Пользователь,Doctor,Пациент`, the table `ДниПосещения` will be foreign keys with names `Пользователь`, `Врач`, `Пациент`. Similarly, you can configure [dyelovoy link](fo_detail-associations-properties.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}