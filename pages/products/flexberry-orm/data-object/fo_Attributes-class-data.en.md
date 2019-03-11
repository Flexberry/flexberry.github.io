--- 
title: the attributes of the data classes 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM data classes 
summary: Description of class attributes data and the characteristics of their generation 
toc: true 
permalink: en/fo_attributes-class-data.html 
lang: en 
autotranslated: true 
hash: c038b2068222dea5e14fd831ef6843dce79e1980483334d4c761a2987fc89370 
--- 

## attribute Definition 

[Class diagram](fd_class-diagram.html) [attribute class](fd_class-diagram-constraction.html) is defined by a string of the form: 

```
[/)[AccessModifier)Name:Type[=DefaultValue)
``` 

that corresponds to the UML notation. 

```
+ДатаРегистрации:DateTime=Now
+Статус:Статус=Рег
+idx:int=0
``` 

| Generated | Generate SQL DDL Generation .Net language | 
|---|---|---| 
| "/" - if specified, [an attribute is nagraniem](fo_not-stored-attributes.html) | If the attribute is not stored](fo_not-stored-attributes.html) the field definition in the CREATE TABLE statement is generated Before the property definition is specified .Net-NotStoredAttribute attribute (Namespace: ICSSoft.STORMNET, Assembly:ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) | 
| "AccessModifier" - modifier is generated .Net language properties | No | applicable modifier properties (# - protected, - public, - private) | 
| "Name", "Type"- obviously | in a CREATE TABLE generates a field definition with the attribute name (if another name is not specified in the additional Storage property), with the type converted from the original according to [the map display types](fd_types-map.html) | Virtual property with the same name and a private member of the class for this property.The type of the property and a private member - type attribute are converted from the source according to [the map display types](fd_types-map.html). | 
| "DefaultValue" the default value (initializer). | No | Private member registers an initializer with [specified default](fo_features-dafault-value.html). <br / >If the specified value is of an enumerated type, is generated to initialize the value of this type. <br / >If the type is not enumerable, then it is appropriate .Net-type and checks whether there is a public static property with the name of "DefaultValue". the <br>Next, if the type is standard (of namespace System), generated a simple initialization of a constant (e.g.: int idx=0). <br / >otherwise generation halts with error.<br>**Note:** If you create your [own type in the diagram (a class with steriotip "type")](fd_data-types-properties.html), then, before asking the default, you must compile the objects. Only after compiling, you can set the default value to generate code. | 

There is the option to specify the data object as the attribute type](fo_data-object-as-attribute-type.html). 

## Additional editable properties 

To edit additional properties of the attribute should: 

* Open advanced properties класса; 
* Click on the tab "Attributes".

On the tab there is a list of all attributes of the class: 

![](/images/pages/products/flexberry-orm/data-object/attributeprops.jpg) 

| Property / Description | Generate SQL DDL Generation .Net language | 
| `AccessModifier` - qualifier attribute access (`public`, `private`, `protected`) | | Generates the appropriate qualifier when you declare a class (`public`, `private`, `protected`) | 
| `Stored` - stored or not, the attribute | attributes For the stored generated DDL description for neranenah - no | If the attribute phranky is generated NotSotedAttribute | 
| `Name` - the name of the attribute | | | 
| `Description` | No | DocComment before property definition | 
| `Type` - the type of the attribute is Displayed according to card types | Displayed in accordance with a map of types | 
| `DefaultValue` - duplicate attribute definition | | | 
| `NotNull` - indicates that this attribute cannot have empty (Null) values. __For the type must be specified `DefaultValue` (default)__ | If checked, the field definition in the CREATE TABLE statement is generated as NOT NULL Before the property definition is generated specifying attribute NotNullAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) | 
| `DataService expression` - a expression, providing a computable account attribute | No | in Front of the property definition is generated specifying attribute [DataServiceExpressionAttribute](fo_not-stored-attributes.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)). 
| `Storage` - a logical name by which the stored properties | If set, then this will be the name of the field | in Front of the property definition is generated specifying attribute [PropertyStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)). 
| `Order` - whether to use alignment in cases where objects are detaylari. | No | in Front of the property definition is generated specifying attribute [OrderAttribute](fo_functionality-work-detail-array.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) 
| `Trim` - for attributes of type string whether to apply the remove extra spaces in values | in any Way | in Front of the property definition is generated specifying attribute [TrimmedStringStorageAttribute](fo_trimmed-string-storage.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) <br>setting the property [Flexberry ORM](fo_flexberry-orm.html) applies the function string.Trim(). | 
| `PBCustomAttributes` - [the parenthesis of a programmer](fo_programmer-brackets.html) | No | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) for manual application .Net attributes before the code properties. | 
| `PBGetEnd` - [the parenthesis of a programmer](fo_programmer-brackets.html) | No | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) for manual entering of code before the end of the getter. | 
| `PBGetStart` - [the parenthesis of a programmer](fo_programmer-brackets.html) | No | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) to "manual" any code after the beginning of getter. | 
| `PBSetEnd` - [the parenthesis of a programmer](fo_programmer-brackets.html) | No | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) for manual entering of code before the end of the set accessor. | 
| `PBSetStart` - [the parenthesis of a programmer](fo_programmer-brackets.html) | No | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html) for manual entering the code after the set accessor. | 
| `Autoincrement` - box is the identity (note the checkboxes correctly only for attributes that are marked `NotNull` and have the type [mapperley](fd_types-map.html) type `int`) | If checked, then a new field in the table will be generated [IDENTITY(Seed,Increment)](http://msdn.microsoft.com/ru-ru/library/ms186775.aspx), where `Seed` - the core increment, `Increment` - step increment. Default `Seed` and `Increment` equal to one. If you specify `DefaultValue`, as `Seed` take the value `DefaultValue`. If the option is specified - corresponding element the attribute is added [`DisableInsertPropertyAttribute`](fo_disable-insert-property-attribute.html). | Property that is set to `autoincrement`, will update the value only when writing data to the database, therefore, to make a field to edit this property on the edit form is pointless and even harmful. | 
| `Hint` - tip for the field | in any Way. | Does (the property is not used in all plug-ins of generation, generates a tooltip that displays next to the control, where the value of the field). | 

{% include important.html content="Changing tick `Autoincrement` the attribute that is already generated in the database, impossible because the MS SQL Server allows you to assign the necessary properties only by deleting and re-creating the column. If you use `Autoincrement`, the update of this field happens not immediately but after the record in the database. This is due to the mechanism of IDENTITY(Seed,Increment). As a consequence, You __will see__ changes until perepechatala data from the database." %} 

## Example 

If a property definition looks like this: 

```csharp
+Название:Строка100
``` 

The code is the following: 

```csharp
//.... 

private string fНазвание;

//.... 

// *** Start programmer edit section *** (Error.Title CustomAttributes) 

// *** End programmer edit section *** (Error.Title CustomAttributes) 
public virtual string Название
{
	get
	{
		// *** Start programmer edit section *** (Error.Name Get start) 

		// *** End programmer edit section *** (Error.Name Get start) 
		string result = this.fНазвание;
		// *** Start programmer edit section *** (Error.Get name end) 

		// *** End programmer edit section *** (Error.Get name end) 
		return result;
	}
	set
	{
		// *** Start programmer edit section *** (Error.The name of the Set start) 

		// *** End programmer edit section *** (Error.The name of the Set start) 
		this.fНазвание = value;
		// *** Start programmer edit section *** (Error.Name Set end) 

		// *** End programmer edit section *** (Error.Name Set end) 
	}
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}