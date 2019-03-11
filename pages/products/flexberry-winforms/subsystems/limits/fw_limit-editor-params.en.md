--- 
title: Limitation settings (for the user) in Win 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms), Limitations 
summary: the Description part of the editor's functionality to limitations in Windows applications 
toc: true 
permalink: en/fw_limit-editor-params.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 38553cee5761a0b96535d4bb3fc499530e5df5067b565131c24474883288c0c9 
--- 

## Settings 
In the constraint editor you can create a parameter and including it in the query. Parameter requested from the user with application restrictions. Thus, it is possible to create many identical requests for 1 field with different values, and to create 1 query parameter to request it from the user. 

### Create parameters 
Parameters are created in the form of restrictions in `Стандартном` or `Расширенном`. 

Block creation options as follows: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit params.png) 

Each line is a separate parameter. 

The option is characterized by 4 properties: 
* Title 
* Type 
* Multiplicity 
* Optional 

First we need to ask `имя параметра`. The parameter name must meet the following requirements: 
* Parameter must only contain letters and numbers. 
* The parameter must begin with a letter. 

Then choose `тип параметра` from the drop-down list. In the list only types that are present in the tree of Available Properties. 

{% include important.html content="your Own custom app will be available only after the creation of [filters](fw_filter-example.html)." %} 

Setting `Множественность` we point out that the user is able to specify not one value but several. In this case, the form you select will appear [GroupEdit](fw_group-edit.html) to create an array of possible options. 

To specify a user friendly name of the parameter (which will appear on the form you select) you must fill in the column `Дополнительно`. 



### Form option 
The form you select is automatically generated based on the number and type of parameters. 

For example, if the form creation limitations as follows: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit01.png) 

the selection options are not displayed, because there are no parameters. 

If the restriction looks like this: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit02.png) 

the choice of parameters will look as follows: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit03.png) 

Accordingly, if the parameter indicated `Множественность`: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit04.png) 

the choice of parameters will look as follows: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit05.png) 

that is, it will be possible to select multiple elements to be combined OR condition. 


### creating the constraint 
To set the limit parameter: 
* Add the parameter to the limit: 
* __If the standard form:__ if you select to limit to specify the value from the database, and select the desired option.<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/24.png) 
* __If the extended form:__ specify as the value parameter.<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/25.png) 
The parameter type must match the type of operation in the constraint. 
* Click «Save and apply». Form editing restrictions closed. Form opens «Parameters restrictions», where you enter the parameter values.<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/26.png) 
* Click to Apply» qmo. The value of the parameters inserted in the restriction. The list is limited. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}