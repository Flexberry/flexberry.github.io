--- 
title: Advanced constraint editor for Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls), Limitations 
toc: true 
permalink: en/fa_advanced-limit-editor.html 
lang: en 
autotranslated: true 
hash: 6f8d2f6c675b493a406127a07d7d0fc1bbc59aefe4cff1eb2cca8a04e0eebfed 
--- 

## a List of user-defined functions 

* Open the editor 
* Close the editor (*situations when it is closed itself) 
* Create the constraint 
* Create a constraint based on the current 
* Be the restriction 
* Add condition 
* Union (OR) 
* the intersection (And) 
* negation (NOT) 
* To change the operation in the condition 
* logical operations: 
* Union (OR) 
* the intersection (And) 
* string operations: 
* Pomaska (mask) 
* Empty 
* Is Non-Empty (Filled) 
* comparison operations: 
* more 
* greater than or equal to 
* less 
* less than or equal to 
* equal 
* unequally 
* Be the restriction detalam 
* Remove the condition 
* Clear restriction 
* Use 
* add option 
* to specify the type of option 
* remove the parameter 
* Specify a value for the condition 
* manually enter 
* select 
* select a value from the database 
* Apply the constraint 
* Enter the value of the parameter 
* To change the setting 
* To reset the limit 
* Save limit 
* Edit restriction 
* Remove the restriction 

## soft reset restrictions 

For software reset superimposed on the restrictions list on the server side it is sufficient to call the method 

```csharp
WebObjectListView1.DropLimitFromLimitEditor();
``` 

## select a value from the database 

*When choosing values from the database to optimize displays only the first 100 values that meet the condition.* 
There is the possibility to filter the values from the database. For this special field to enter a value, which must be a substring in the resulting found elements. 

## wildcard Search 

You can set a limit on the mask. The corresponding element is added to the ribbon editor. 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/limit-editor.png) 

At the moment, imposed some limitations on the pattern: 
* "*" - 0 or more any characters. 
* the " _ " is exactly 1 character. 
* To search for a substring, you must at the beginning and end of a string to specify the character "*". For example, to search for substrings that contain the sequence 123, you need to set a wildcard "*123*". 
* Escaping characters and some special characters are currently not supported (this is including [the limitations of using Regex in queries to LinqProvider](fo_linq-provider.html)).

## to Count the number of rows 

On the tab there is a button `Главная` `Подсчитать number строк`, by pressing on which is issued the number of objects satisfying the current constraint. 

{% include note.html content="Counting the number of lines does not take into account imposed on [WebObjectListView](fa_web-object-list-view.html) [filters](fa_wolv-filters.html)." %} 

## Settings the advanced editor limitations 

Parameters in the advanced editor of the limitations described in the relevant [article](fa_advanced-limit-editor-parameters.html) 

### Substitution of parameter values in the bounding function 

Substitution of parameter values in the bounding function described in the corresponding [article](fa_limit-function-parameters.html). 

### Limitation parameters to the user 

The constraint editor supports the possibility of forming conditions with parameters, in other words, the user has the option to use a constant instead of the parameter name), the specific value of the parameter will be requested from the user before executing the query. 

Read more about restriction settings for a user in [article Restriction settings for a user](fa_limit-parameters-user.html) 

### Constraints with parameters for the programmer 

Features of work with constraints with parameters for the programmer are described in [article](fa_limit-parameters-developer.html). 

## editor limitations 

### Global settings the advanced editor limitations 

Global settings the advanced editor restrictions is described in [article](fa_global-limit-editor-settings.html). 

### the editor without restrictions WOLV 

Edit restrictions without WOLV described in [article](fa_limit-editor-without-wolv.html). 

### specifies the title of the class in the advanced editor limitations 

Specifies the title of the class in the advanced editor of the limitations described in [article](fa_web-limit-editor-class-caption.html) 

### Work with detaylari in the advanced editor limitations 

Features of work with detaylari in the advanced editor of the limitations described in the relevant [article](fa_details-limit-editor.html). 

### Work with pseudocatalase in the advanced editor limitations 

Features of work with pseudocatalase in the advanced editor of the limitations described in the relevant [article](fa_pseudo-detail-extended-view.html). 

### Conversion functions restrictions in linq-restriction 

Conversion functions restrictions in linq-restriction described in [article](fo_lcs-to-linq.html). 

### set the display order of the properties in the advanced editor limitations 

Set the display order of the properties in the advanced editor of the limitations described in [article](fa_prop-order-limit-editor.html). 

### Expression is Empty/is non-empty in the advanced editor limitations 

Expression is Empty/is non-empty in the advanced editor of the limitations described in the relevant [article](fa_web-limit-editor-null.html).

## External classes in the advanced editor limitations 

External classes in the advanced editor of the limitations described in [article](fa_web-limit-editor-external-class.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}