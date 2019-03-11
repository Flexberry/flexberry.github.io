--- 
title: Methods of classes and method parameters 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Flexberry ORM, methods, parameters, generation, example 
summary: a description of the methods classes and the example of their generation 
toc: true 
permalink: en/fd_methods-parameters.html 
lang: en 
autotranslated: true 
hash: d3489da3027026b31bae901926c604b59c7d0dffa825f6d3f8a5bfc936fac02a 
--- 

The method described in class, in UML notation in the form: 

```csharp
[AccessModifier]Name([ParamName:ParamType] [,...])[:Type]

``` 

For example: 

```csharp
+МожетПерейтиВСостояние(состояние:СостояниеОшибки, причина:string):bool
``` 

Generated | Generate SQL DDL Generation .Net language 
:--------------------------------|:--------------------|:-------------------------------------- 
Name | Does | Define a virtual method, this is the name of the method. 
AccessModifier modifier is generated .Net language method | Way | the Appropriate modifier in the method definition (# - protected, - public, - private) 
ParamName | No | Name of the parameter in the method definition 
ParamType | No | the Name of a type parameter in a method definition 
Type | Way | Type of the return value of the method, if not specified, the generated void. 

## Additional editable properties and how that is generated 

To edit additional properties of the methods, you must: 

* Open advanced properties класса; 
* Click on the tab "Methods". 

On the tab in the upper part is a list of all methods of the class at the bottom - the list of parameters selected in the top of the method: 

![](/images/pages/products/flexberry-designer/class-diagram/methods.jpg) 

Property Description | Generate SQL DDL Generation .Net language 
:--------------------|:-----------------|:------------------------------ 
AccessModifier - duplicate attribute definition | No | 
Name duplicates the attribute definition | No | 
Description - description | No | DocComment before the method definition 
Type - duplicate attribute definition | No | 
IsEvent - indicates that this is not a method, and the event | in any Way | [About the event see here](fd_eventarg.html). 
PBCustomAttributes | in any Way | If the option is on, - is generated parenthesis programmer 

Method parameters: 

Property Description | Generate SQL DDL Generation .Net language 
:-----------------|:--------------------|:------------------------------------------ 
Name | No | Name of the parameter. 
Modifier | No | Modifier parameter, according to the rules .Net in, out, byval, byref. 
Type | Way | type of the parameter. 
Description | in any Way | option in DocComment before the method definition. 

## Example 

If the description of the method looks like: 

```csharp
+МожетПерейтиВСостояние(состояние:СостояниеОшибки, причина:string):bool
``` 

The source code: 

```csharp
// *** Start programmer edit section *** (Error.Moreprecisely ICSSoft.BugLeR.Dostoeyevsky System.String CustomAttributes) 

// *** End programmer edit section *** (Error.Moreprecisely ICSSoft.BugLeR.Dostoeyevsky System.String CustomAttributes) 
public virtual bool МожетПерейтиВСостояние(ICSSoft.BugLeR.СостояниеОшибки состояние, string причина)
{
	// *** Start programmer edit section *** (Error.Moreprecisely ICSSoft.BugLeR.Dostoeyevsky System.String method implementation) 
	return true ;
	// *** End programmer edit section *** (Error.Moreprecisely ICSSoft.BugLeR.Dostoeyevsky System.String method implementation) 
}
``` 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}