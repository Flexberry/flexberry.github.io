--- 
title: case-Insensitivity in the service data 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, database, data service 
summary: setup plus an offset to the register 
toc: true 
permalink: en/fo_insensitivity-register-ds.html 
lang: en 
autotranslated: true 
hash: d6d4bc60711002bd29dcf3a1225aae155842f4aca9366efb3b60dd9556158df1 
--- 

[Flexberry ORM](fo_flexberry-orm.html) allows you to configure the application so that the generated [service data](fo_data-service.html) the query was applied to the feature [UPPER](https://docs.microsoft.com/ru-ru/sql/t-sql/functions/upper-transact-sql) to both sides of the expression string comparison (that is, the string comparison will not be considered in what case the recorded characters). 

In order to set this up, you need a configuration file to specify the following parameter: 

```xml
<add key="CaseInsensitive" value="true">
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}