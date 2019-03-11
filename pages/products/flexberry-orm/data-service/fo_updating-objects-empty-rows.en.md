--- 
title: Update of objects with blank lines 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, mssql, an empty string 
summary: Using MSSQLDataService when updating objects with empty strings 
toc: true 
permalink: en/fo_updating-objects-empty-rows.html 
lang: en 
autotranslated: true 
hash: d51dc9161b199766ffc2dddff89b1770cfa6c84a31ecd6fe70dd7c9380c62d46 
--- 

[MSSQLDataService](fo_mssql-data-service.html) converts empty strings to `NULL` when updating objects. 

When building a [limitations](fo_limitation.html), if the argument in the equality check is passed `String.Empty`, the request will be generated as IS NULL and everything works as it should. This simplification eliminates the need to write complex Where clauses-expressions. 

If there is a need to distinguish between `NULL` and `String.Empty`, it is possible [to unasledovala from MSSQLDataService and override method](fo_implement-custom-ds.html) 

``` csharp
public virtual string ConvertSimpleValueToQueryValueString(object value)
``` 
so that was not replaced 

``` csharp
if (valType == typeof(string))
{
	if ((string)value == string.Empty)
		return "NULL";
	else
		return "'" + value.ToString().Replace("'", """) + "'";
}
``` 

Special attention should be paid to this feature, if you use the imported data or the SQL statements that run in a bypass [service data](fo_data-service.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}