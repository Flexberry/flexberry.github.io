--- 
title: a Quick guide to Pentaho Report Designer 
sidebar: flexberry-analytics_sidebar 
keywords: Pentaho, problems, queries, reports, data types, aliases, display 
toc: true 
permalink: en/fan_pentaho-faq.html 
lang: en 
autotranslated: true 
hash: 86090313d999b5ce9e0c3215ac4c58addd41f722270e0a4d05fd190927f97bc3 
summary: Frequently asked questions on Pentaho 
--- 

This guide describes best practices for working with [Pentaho Report Designer](https://help.pentaho.com/Documentation/8.0/Products/Report_Designer) for faster and accurate reporting with the possibility of a more convenient format in the future if necessary. 

As an example, the user database will be used in video games. 

#### Aliases for the requested fields 

There are situations where when you build the query should display fields with the same names from different tables. For example, using the query you should print the name of the game, its price, genre and the company that developed it. The query will look like the following: 

``` sql
SELECT
     "dbo"."Genres"."Name",
     "dbo"."Games"."Name",
     "dbo"."Games"."Price",
     "dbo"."Companies"."Name"
FROM
     "dbo"."Companies" INNER JOIN "dbo"."Games" ON "dbo"."Companies"."Id" = "dbo"."Games"."Companies_Id"
     INNER JOIN "dbo"."Genres" ON "dbo"."Games"."Genres_Id" = "dbo"."Genres"."Id"
``` 

![](/images/pages/products/flexberry-analytics/pentaho-faq1.png) 

If the query has fields with the same names, the positioning in the report is just one of them. 

To have the opportunity to place all the fields in the query, you must assign a unique (within the request) __alias, (alias)__. For example, `Genre`, `Game`, `Price` and `Company`. In this case, the report will display all the fields of the request. 

#### the report must be selected execute the query 

A situation may arise in which for the report is selected executes the query (in this case, it will have an icon of a black dot). This can occur, for example, when you create a new query, or renames existing ones. If this report is selected, execute the query, no data will not be output. To set the query to be executed, you need click RMB on request and select `Select Query`. Next to the executed query should be a folder icon. 

#### Placement of queries that return multiple records, in the field Details 

The elements that represent the query results, it is recommended to place in the area `Details`. 
Region that is different from `Details` display only the first record of the query, while `Details` displays all records of the request, provided that the attribute `limit` report standard value has been set. If the attribute, for example, be set to 3, the report will display the first 3 records (as if the query was written `TOP (3)` or `LIMIT 3`).

#### Recommended for different types of query fields to select the appropriate display item 

When migrating fields in the executed query designer automatically selects the appropriate element of the display depending on the type of the field (`number-field` for numeric types, `date-field` for date type, etc.). It is recommended to output the query results to use it this way (instead of manually adding the item from the toolbar and give it the resulting field in the attribute `field`), because if the image element does not match the field type in the query, then when you export the query may be invalid values (so Excel will think that the numbers in the cells are strings). 

#### running multiple queries 

In one report can have only one query to be executed. To create multiple queries, there are two ways: 

1. The Union of two different queries in один; 
2. Create a subreport. 

Pervy method predpochtitelno due to the possibility of a more flexible report customization, quick arrange fields, convenient format. Although this method is more difficult, because forces to hand-write a complex query instead of using the visual designer. 

Poduce is the same report as the Master Report, with his the executed query. 
When you create a sub-report it is also important to choose execute the query, otherwise no data will be displayed. 

In the General case is recommend to use the first method in terms of ease of formatting and in terms of query optimization. 

The subreport should be created in that case, if the request it does not intersect with the source (another database, another set of tables, and so forth). A subreport can also be used for more convenient debugging. 

#### Cyclic query call 

When you create a sub-report may be a problem of cyclic query call. It occurs when the condition that the sub-report is located in region `Details` main report, and the fields of the executed query in the subreport also located in the area `Details` sub-report, and both reports attribute `limit` ustanovleno standard value (displaying all query results). 

For example, if the query result is a table with 7 records, the report will be displayed 49 records. 

![](/images/pages/products/flexberry-analytics/pentaho-faq2.png) 

This problem can be solved by setting the value attribute `limit` the main query in 1. 
If you set this value to the subreport, then the result will be the same 7 records. 

#### Creating elements with dynamic size 

Features create elements with dynamic size stated on the forum [Pentaho Community Forums](http://forums.pentaho.com/showthread.php?218712-Problem-with-variable-length-fields-in-detail-section&highlight=items height).

#### the Names of aliases do not fit in the box 

When specifying fields to be displayed in the element inside is written the alias field. 
If the alias name is very long, and the cell is small, it may slide that will cause incorrect display in the report preview. __Is recommended to give the alias short names are__, so they entered the cell. 

#### does Not draw the border 

Sometimes in some of the fields in the report output can disappear of the border. It may happens due to the fact that in the cell the query returned `значение NULL`. Border with empty values are not drawn. 

![](/images/pages/products/flexberry-analytics/pentaho-faq3.png) 

To fix this, it is enough in this element to register a space in the property `if-null`. 

#### the problem with the overlaying of fields on top of each other 

Let the report you must first display the list of games, followed immediately by a list of software development companies. 
For this task you can create two sub-report (in one list the games and the other list of companies) and place them in the region `Details` (by setting the attribute `limit` main report a value of 1). In this case, it may be a problem with the text overlaying one sub-report in the text of another sub-report. 

![](/images/pages/products/flexberry-analytics/pentaho-faq4.png) 

It would be possible to create a query in the main report and place both fields in it (the field of play over the field). However, in this case the report displays in one column the values of the» «Game, «Company»,» «Game, «Company»,» «Game, «Company», etc. 

To place in one column all the first game, and then all the company with no problem with the imposition of the fields you can set a property `layout region Details` in the value `block`. In this case, all the elements that are in the area `Details` will be stretched across the entire width of the report and are located one above the other. 

![](/images/pages/products/flexberry-analytics/pentaho-faq5.png) 

####» «Blank columns and rows when exporting to Excel 

Often when exporting to Excel may arise empty columns and rows in small sizes. 
Usually, this occurs due to careless arrangement of elements in the designer. 

Naprimer, let the report displays a list of games (text-field with a width of 200 and a coordinate (0, 0)) 
and next list of companies (text-field with a width of 268 and a position (201, 0)). 

Thus the total width occupied by these two elements is equal to 200 268 = 468 points plus 1 point between them = 469. 

Since the standard width is 468, then one extra point will go beyond the report which when exported to Excel no extra stoves is formed with a very small width. 

To position elements more accurately, it is recommended to put the check box in the property `Snap to 
Elements` menu `View`. Thus, when the location of items they will» «stick to each other and between them there will be extra spaces. 

However, there are bolee convenient sposob that can automate accurate location of items in the report. This is `элемент band`. It is necessary to add up all the items that are displayed in one line and set the property value `layout` `row` that the elements in the group went after each other from left to right. So they will dock to each other horizontally, and between them there will be gaps. 

To `band'ы` also attaches to each other vertically, you need to `Details` field to set a property value `layout` `block`. 

!![](/images/pages/products/flexberry-analytics/pentaho-faq6.png) 

#### Failed Query when trying to view the report on the server 

Sometimes a situation may arise when a query that works in `Pentaho Report Designer`, fails when trying to create it on the server. Perhaps the reason is that for the connection over which the query is not set the necessary settings. 

To fix this, you need to go into the connection settings (window `Database Connection`), select the left `options` and ask `параметр stringtype`, and the value `unspecified` (if not specified). 

#### Missing query results before viewing 

If the query returns too many records, they may not fit on the first sheet. In this case, they are leaves, but when you export to a file, all records will be displayed together («since the beginning of the»). 

#### reference to the parameters of the report 

To access the report parameter in the query, you must wrap it in braces and put in front of them with a dollar sign (for example, if the parameter name `Company`, then in the query you need to write `${Company}`). 

If you need to refer to the report parameter from the expression (for example, to make a title «Report Square Enix»), in this case, instead of curly brackets are put round (then the expression in the title field will be the «Report `$(Company)`»). 

#### Blank when pre viewing 

Sometimes it may happen the situation, when before the view appears empty area in between which are written the results of the queries. This can happen when the query, the results of which were to appear in this place, are not fulfilled (in this case, indeed, its results and the associated headers will not be logged, provided that they were together in `band'е` or sub-report). 

When set auto height of an element, the dimensions specified in parameters `width and height` may be incorrect, although the object on the field has always the same size regardless of those two settings. Blank area occurring when the parameter `height` greater than the actual height of the element. In this case, you want to decrease `height`, so that it is less than or equal to the true size of the item (or pull the edge of the element, or specify a height value manually). The size of the element will not change, and before the view disappears an empty region. 

#### report Parameters 

For the date parameter, it is recommended to choose `тип Date (SQL)`, because in SQL the date format is different from the rest. As of the date format you can specify, for example, `dd.MM.yyyy` (that's MM, because mm is responsible for minutes), then the date will be displayed in accordance with the Russian format. 
If the report parameters involved `GUID`, then it is necessary to choose the type `Object`. 

If in the UI you want to display the report with the specified parameter, it is necessary in the request URL in the settings area to add the required parameter value: 

```
<имя_парметра=UUENCODE_значение>
``` 

For example: 

```
http://localhost:4200/pentaho/api/repos/:home:msp:report1.prpt/generatedContent?orgId=f5078a44-2ef6-49d9-ad2e-0099ba1cf02d 
``` 

In this URL you can specify the type of report file: 

```
http://localhost:4200/pentaho/api/repos/:home:msp:report1.prpt/generatedContent?output-target=table/excel;page-mode=flow&orgId=f5078a44-2ef6-49d9-ad2e-0099ba1cf02d 
``` 

Output formats: 

```
table/html;page-mode=page for HTML (Paginated) 
table/html;page-mode=stream for HTML (Single Page) 
pageable/pdf for PDF 
table/excel;page-mode=flow for Excel 
application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;page-mode=flow for Excel 2007 
table/csv;page-mode=stream for Comma Separated Value 
table/rtf;page-mode=flow for Rich-Text-Format 
pageable/text for Text 
``` 

If there is no need to change the parameter value in the query, it is necessary to describe option to check the box `Hidden`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}