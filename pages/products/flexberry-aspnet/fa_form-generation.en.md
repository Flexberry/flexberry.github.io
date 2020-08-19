--- 
title: Generation of forms in Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_form-generation.html 
lang: en 
autotranslated: true 
hash: 563b8c7e3a53cf8eab42ddb8dc835e28c335abadf474efe32edfee8d9d87c637 
--- 

## General principles generating forms 

For each shape generated 3 files: aspx markup code-behind and designer code. 
These files are generated on the basis of the present in WebAppTemplate text templates, which are files with the relevant extensions. For example, to edit form files are used `EditForm.aspx`, `EditForm.aspx.cs` and `EditForm.aspx.designer.cs`.<br> 
Templates these can be edited manually using the following notation to indicate where to insert certain values to generate: 

* `@Formname@` - name of form file (without the extension). 
* `@Codebehind@` is the name of the codebehind file (without the extension). Used in the. aspx file. Coincides with `@Formname@`. 
* `@Namespace@` - the namespace of the class generated form. 
* `@Classname@` - the class name of the generated form. 
* `@Classcaption@` - type header that is displayed on the form. 
* `@Formpath@` - relative (server) path to the generated form. 
* `@Typename@` - type name displayed on the form. 
* `@Viewname@` - view name specified for the generated form. 
* `@Editor@` - the path to the edit form (list of forms). 

## Generation of tailored packages 

To structure the folder which will generate the forms, you can use the mechanism of breakdown for the packages. For this form class in CASEBERRY need to specify a property Packet. This property is used to specify the folder which will generate the form files for this class. This folder will be located in the forms folder of the project. The package name cannot use characters that are invalid for file names and paths to files. To a property of Packet transliteration is not applied. 

Examples of property values `Packet`: 

* Folder1 
* Folder2\Folder 8 

If the Packet property is not specified, then the form files will be generated in a folder named data object for which this form is created. The class name will be transliterated. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}