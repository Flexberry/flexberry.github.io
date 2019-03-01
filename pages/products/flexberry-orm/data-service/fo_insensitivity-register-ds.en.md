--- 
title: case-Insensitivity in the service data 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, database, data service 
summary: setup plus an offset to the register 
toc: true 
permalink: en/fo_insensitivity-register-ds.html 
lang: en 
autotranslated: true 
hash: 26fc691d21571bfabea412ecc831b55c4658b829ea7a0538c67eafb80c8949f3 
--- 

[Flexberry ORM](fo_flexberry-orm.html) allows you to configure the application so that the generated [service data](fo_data-service.html) the query was applied to the feature [UPPER](https://docs.microsoft.com/ru-ru/sql/t-sql/functions/upper-transact-sql) to both sides of the expression string comparison (that is, the string comparison will not be considered in what case the recorded characters). 

In order to set this up, you need a configuration file to specify the following parameter: 

```xml
<add key="CaseInsensitive" value="true">
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/