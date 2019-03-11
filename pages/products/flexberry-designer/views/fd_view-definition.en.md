--- 
title: View (View) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, views, View, attributes, master, detail, inheritance, example 
summary: the purpose of the concepts and features of working with them 
toc: true 
permalink: en/fd_view-definition.html 
lang: en 
autotranslated: true 
hash: 9a0beccaf76cc359ec816c92b19615516a8d1bcf4cde415a14784229fc76f675 
--- 

## the view Definition 

`Представление (view)` describes some logical combination of: 
* self [attribute class](fo_attributes-class-data.html), 
* [masters](fd_key-concepts.html) of any depth and their attributes 
* views [datalow](fd_key-concepts.html). 

The presentation is a developer tool that allows you to capture a set of attributes associated classes associations. 

In order to properly understand what an idea, you can submit a written sheet of paper and another sheet of paper with rectangular slits. Overlaying the second sheet on the first allows you to see through the slits only a few words or even letters. In this example: a written sheet, a data object, the sheet with slits — representation of the superimposed sheets is the data object in the view. 

## Assignment submissions 

The practical value of ideas is obvious: almost always a manipulation of a fairly limited set of properties. Thus, in many operations (for example, when reading the object) it is convenient to restrict the set of properties, instead of handling all that will significantly increase productivity. 

This view is used as to generate user interface forms and to implement operations (e.g., reading data objects in the view). 

The presentation is a developer tool that allows you to reflect some set of attributes associated classes associations. 

`Обратите attention!` Not in one view twice to add the same property (attribute) as the [service data](fo_data-service.html) will not be able to handle the situation. If there is a need to reuse the properties, use [calculated field](fo_not-stored-attributes.html). 

## Types of representations 

There are different types of views. Read more you can learn about them from the article [Principal ideas](fd_View-types.html): 

* [E-view](fd_e-view.html) 
* [L-view](fd_l-view.html) 
* [T view](fd_t-view.html) 
* [D-view](fd_d-view.html) 

### view Inheritance 

Declared statically representation [inherited](fd_inheritance.html) (`наследуемые представления`), i.e. represent the ancestors, valid for posterity, moreover, work for any object of a descendant through the class (type) of the provided polymorphic ancestor for the descendants (for example, if there is a list for a class, all objects descendants of this class can be displayed in the same list in the same view). Statically declared in the ancestors of the submission can be fully `переопределены` in the heirs. 

In connection with the inheritance of ideas: 
* In a special way configures the [read belonging to different object classes in a single view](fo_reading-several-types-objects.html) 
* Implemented [adaptive view](fo_adaptive-views-details.html). 

### Job submissions 

The view can be set: 
* statically (assigned metadata attributes to classes of data) 
* dynamically (constructed in the source code). 

The submission attributed to the data classes and called. 

### Statically defined representations 

Statically, the performance is defined by the following attributes: 

* `ViewAttribute` — announces the presentation for the class. Specifies the name of the view and composition of private and artisans of properties of arbitrary levels. Format instructions: 

```
[(Имя св-ва мастера).(Имя св-ва мастера мастера).(Имя св-ва мастера мастера мастера ...).)(Имя атрибута) [as (пользовательское имя атрибута)) [on (путь расположения на форме))
``` 

* `AssociatedDetailViewAttribute` — specifies to represent [class-hats](fd_key-concepts.html) representation of detail as representation [hats](fd_key-concepts.html) includes a representation of detail. Here you can specify: 
Should detaily to be read by data services with the download [hats](fd_key-concepts.html); 
* The path to the location on форме; 
* Heading on форме; 
* Visibility on форме; 
* The names of the aggregate functions available to the user of this представления; 
* If the view should be adaptive (UseAdaptiveViewsLoading)](fo_adaptive-views-details.html). 

* `MasterViewDefineAttribute` — optional configures the master in performance [hats](fd_key-concepts.html), you can specify: 
* Selection type (lookup type) artisan объекта; 
* The string to initialize the object (control) selection of the workman объекта; 
* The display property for type of choice LookupTypeEnum.Combo. 

The path to the location on the form indicated by the line: 

```
[-|)[Имя)[\ [-|)[Имя))…
``` 

where: 
* `-` refers to grouping (`GroupBox`), 
* `|` - tab (Tab) on the edit forms, 
* `\` — path separator. 
* `Имя` — the name of the corresponding bookmark/groups. 

The names of the properties you can specify the "star" that will mean that you must use all properties. For example, if you just specify "star", then the view automatically includes all of its own properties, if you specify `Master.*`, it will mean that the view contains all the properties specified in the wizard. The programmer can disable automatic entering of any property to the view showing the front of the property attribute `DisableAutoViewing`. 

[Primer](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/Objects/CDDA.cs) statically defined views: 

```
[View("Generator", new string[) { "Company on \‘|Common\-Copyright\’", "Product on \’|Common\-Copyright\’", "Copyright on \’|Common\-Copyright\’ ", "Version" } ) )
[AssociatedDetailView( "Generator", "Classes", "Generator", true, "", "", true, new string[) { } )) 
[AssociatedDetailView( "Generator", "Inheritances", "Generator", true, "", "", true, new string[) { } )) 
[View( "Edit", new string[) { "Company", Product, Copyright, "Version", "DataObjectNameSpace" } ) )
public class Stage:STORMCASE.Repository.Stage
{
	…
}
``` 

In `RunTime` views are instances of a class `ICSSoft.STORMNET.View`. The developer can obtain an initialized instance of the class corresponding to the statically defined view, by calling [Information](fo_methods-class-information.html).GetView or through [special static properties](fo_static-view-accessors.html). 

### Dynamically specified performance 

In order to dynamically determine the picture, one must construct an instance of the class `View` ([primer](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs|)). This can be done in four ways: 
* Designing a blank view, and then use the methods of `DefineClassType, AddDetailInView, AddMasterInView, AddProperty` for further definition. 
* To design the presentation of the instance of the class `ViewAttribute` (aka attribute is used to specify static representations). 
* Designing view properties initialized (loaded other than [the internal copy of the data](fo_data-object-copy.html)) of the data object. 
* Designing performance `по-умолчанию` (fall only its own properties, as well as Decalogue properties that specify the relevant option) 

Also, using class methods `View` completely to the structure of the view and can change it (get/add/delete the items included in the submission). 

It is also worth noting that it is strongly recommended to determine the name of the dynamically created views. 

{% include note.html content="Caching View for the form for different visitok can lead to errors, as when reading with the overlay restrictions specific instance of the View can be changed [service data](fo_data-service.html)." %} 

## Verification of compliance of the object representation 

To check whether a certain [object](fo_data-object.html) view, you must call an instance of the class `View` method `TestObjectForViewing`. 

## for more information about views 

* In [Flexberry ORM](fo_flexberry-orm.html) there are [standard transactions](fo_view-operations.html), allowing to find for several classes of their common ancestor and the overall presentation. You can also manipulate views set to calculate, e.g., Union, or difference of views. 
* There are [recommendations for adding wizards to submission](fd_masters-view.html). 
* There is a [method to check whether a certain object to view](fo_test-object-for-viewing.html). 
* [ViewPropertyAppender](fo_view-property-appender.html) class, which allows to extend the display of properties that are in [the limitation](fo_limit-function.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}