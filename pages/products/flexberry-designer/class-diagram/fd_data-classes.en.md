--- 
title: the data Classes and their properties 
sidebar: flexberry-designer_sidebar 
keywords: DataObject Flexberry Designer, data class, stereotype, implementation, performance 
summary: Generation of the class data in a DBMS and .Net language 
toc: true 
permalink: en/fd_data-classes.html 
lang: en 
autotranslated: true 
hash: 143f1b01f4ccf6c590e7e26f8906d26d52d9add0998d1910b6b29832a96a5c14 
--- 

Data classes are classes with the stereotype implementation or without stereotype. 

[Stereotype](fd_key-concepts.html) `implementation` may be specified, and may not be indicated, the essence is the same: a UML class that corresponds to some substantive entity. 

![](/images/pages/products/flexberry-designer/class-diagram/implementation.png) 

{% include note.html content="the data Classes associated [inheritance hierarchy](fd_inheritance.html), [associations](fd_master-association.html) and [implements](fo_detail-associations-properties.html) with each other, form the so-called [object structure of the application system](fd_key-concepts.html) i.e. the structure of subject entities and their relationships." %} 


### That is generated from the class data 

Generated | Generate SQL DDL Generation .Net language 
:--------------|:---------------------------|:---------------------------------------- 
`UML` class | table Definition: `CREATE TABLE`. If in the database there is a view that corresponds to the name to store this class comment is generated with information about the presence of representations and the definition of the table. | `.Net` class that are inherited from .Net class corresponding to the ancestor model. If a model class has no ancestor, he inherited from class `[ICSSoft.STORMNET.DataObject](fo_data-object.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))` 
Attribute UML class | Field in the class table ([details class Attributes data](fo_attributes-class-data.html)) | Virtual property with the same name and the appropriate modifier (# - `protected`, - `public`, - - `private`), and private member of the class for this property. The type of the property and a private member - type attribute. ([details of the class Attributes data](fo_attributes-class-data.html)) 
Method UML class | | Virtual method with the appropriate parameters and modifier. The method body empty with a bracket of the programmer to make the code in the method. ([details of classes and Methods method parameters](fd_methods-parameters.html)) 


## Additional editable properties of the data class 

The properties of the data class as follows: 

1.Tab edit the properties of a UML class: 

![](/images/pages/products/flexberry-designer/class-diagram/implprop1.png) 

To edit additional properties of any chart element, click the right mouse button on the element to be edited: 

![](/images/pages/products/flexberry-designer/class-diagram/additionalprops.jpg) 

Then select `Редактировать свойства`. 

Generated | Generate SQL DDL Generation .Net language 
:-----------------------------|:-----------------------------------|:----------------------------------- 
`Name` - name `UML` class | | Name `.Net` class 
`Stored` - indicates whether the class is stored, i.e. whether instances of the class in the data source | If the class is not stored, the generation of script for this class is not happening | 
`Description` - a description of the class | | `DocComment` before the class definition 
`Caption` - a custom name, the title (displayed in the user interface instead of the class name) | | attribute set `CaptionAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))` immediately before the class Declaration. By default the user name is the same as the class name. __Note__: If you want to have different instances of the class data was different header is used `InstanceCaptionProperty` attribute that specifies the name of the property that returns the header. This attribute can only be specified programmatically, in Flexberry Designer it is not rendered. 
`PrimaryKeyStorage` - name storage field [primary key](fo_primary-keys-objects.html) | is Generated as the field name [primary key](fo_primary-keys-objects.html) in the table. If not specified, the field name of the primary key is `primaryKey`. | If specified, the generated attribute [PrimaryKeyStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) where you specify the name of the store. In the common ancestor of the classes of data [ICSSoft.STORMNET.DataObject](fo_data-object.html), this attribute is specified as `PrimaryKeyStorage("primaryKey")`. 
`Storage` - the name of the storage for instances of this class data is Generated as a table name. If not specified, the table name matches the class name. If specified, the generated attribute [ClassStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)), in which is written the name of the store. 
`AutoAltered` - automatic calculation of the status of the validation of the changed properties | | If specified, the generated attribute [AutoAlteredAttribute](fo_object-status.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)).
`LoadingOrderXML` - allows you to specify the loading order of properties of instances of this class with data services | | If specified, the generated attribute [LoadingOrderAttribute](fo_order-loading-property.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)). 
`Trim` - does removing spaces (Trim()) for string data in [service data](fo_data-service.html) ( see [additional information](fo_trimmed-string-storage.html)) | | 
`Packet, NamespacePostfix` - allow to set the Assembly and namespace | | see [the Location of assemblies after code generation](fo_location-assembly.html). 
`PBCustomAttributes` - allows you to specify whether to brace the programmer immediately before the class definition, for "manual" of any attributes | | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) to "manual" make `.Net` attributes to the class. 
`PBMembers` - allows you to specify whether to brace the programmer within the class to "manual" introduction of class members | | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html a) for "manual" introduction of the members of the class. 
`BSClass` - [business server](fd_business-servers.html) processing this class of data | | If specified, the generated attribute [BusinessServerAttribute](fo_user-operations-dataservice.html) (Namespace: ICSSoft.STORMNET.Business Assembly: ICSSoft.STORMNET.Business (in ICSSoft.STORMNET.Business.dll)) 
`PublishToEBSD` | | If the option is specified before the class is generated by specifying the attribute `PublishToEBSDAttribute`, which indicates the availability of this class to use the chart editor scripts. 

2.Bookmark editing [view](fd_view-definition.html grade) 

![](/images/pages/products/flexberry-designer/class-diagram/implprop2.png) 

Generated | Generation .Net language 
:------------------------|:------------------------------- 
`Name` - view name | view Name in the attribute [ViewAttribute](fd_view-definition.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) 
`Description` - some description for explanatory purposes | Commentary [static property for access to view](fo_static-view-accessors.html). 
`Properties` - view definition | List of attributes of the class .Net attribute [ViewAttribute](fd_view-definition.html), the associated representations of datalow attributes [AssociatedDetailViewAttribute](fd_view-definition.html), specifying additional settings masters in presentation attributes [MasterViewDefineAttribute](fd_view-definition.html). 

For easy editing of submission is [special form-editor](fd_view-edit-form.html).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}