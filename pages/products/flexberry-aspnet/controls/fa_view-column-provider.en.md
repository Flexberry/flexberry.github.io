--- 
title: ViewColumnProvider 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_view-column-provider.html 
lang: en 
autotranslated: true 
hash: e995456d875ff17e1a1f5d6fb06b3a8c38e28d7b1b49903ed90f43e01097b551 
--- 

`ViewColumnProvider` is a provider that allows you to configure the display of columns in the controls ([WebObjectListView](fa_web-object-list-view.html), 
[AjaxGroupEdit](fa_ajax-group-edit.html)). All its settings are stored in the file `/xml/ViewColumnProvider.xml`. 
The provider allows you to configure the display of columns for the properties and the Toolbox user-defined types (using the tag `type`, `property` and `toolbar`), and types of displayed values (using the tag `basetype`). 
Priority setting properties. If they are not found in the configuration file, the settings are searched for a value type. 

Option `name` used in composition tags (`type`, `property` and `basetype`). The remaining parameters (`width`, `fixedwidth`, `cut` and others) used in the composition of the tags `property` and `basetype`. 

__After changing the values in the xml file, the project need to be recompiled.__ 

## Settings 

* `name` - object Attribute from the view, which operates the control. **Is a must!** 

```xml 
    <basetype name="System.Int32"/>
    ``` 

```xml
    <type name="Building">
        <property name="Name"/>
    </type>
    ``` 

* `width` - column Width. This parameter is applied to the column and its contents. Usually, all columns must have a certain width, set this option and the rest of the columns in css is set to 100% width. 

```xml
    <property name="The building.The name" width="150" />
    ``` 

* `fixedwidth` - Sets the width of the column. The setting will apply to the column. You can specify as just the number(100 - by default, pixels), and pixels(100px), and percent(100%) 

```xml
    <property name="The building.The name" fixedwidth="100" />
    ``` 

* `minwidth` - Sets the minimum width of the column. Ask option `только in пикселях` or it won't work. The minimum width used when the current column width becomes less than the minimum. 

```xml
    <property name="The building.The name" minwidth="100px" />
    ``` 

* `filter` - do you Need the possibility to filter on this column 

```xml
    <property name="The building.The name" filter="false" />
    ``` 

* `filteroperations` - do you Need the ability to use filter operations on this column (`>=`, `<=`) 

```xml
    <property name="The building.The name" filteroperations="false" />
    ``` 

* `noteditbyclick` - a Ban on the edit object by clicking on a cell in this column (edit and so is canceled if you specify `filter=false`). 

```xml
    <property name="The building.The name" noteditbyclick="true" />
    ``` 

* `sort` - Enable / disable sorting for a column 

```xml
    <property name="The building.The name" sort="true" />
    ``` 

* `cut` - Turn on\off cutting off content for the column. If the text exceeds 30 characters, the text is clipped at the end and adds an ellipsis 

```xml
    <property name="The building.The name" cut="true" />
    ``` 

* `cutwidth` is the Number of characters that you want to leave the column without clipping. If this parameter is specified, there is no set cut="true" 

```xml
    <property name="The building.The name" cutwidth="80" />
    ``` 

* `align` | Alignment of the column. Values: "Poliamory", "Pacentro", "Poprawka" 

```xml
    <property name="The building.The name" align="Pacentro" />
    ``` 

You can add your properties, and handle, for example, in `WolvSettApplyer`. To arbitrary display settings you can use the method `GetColumnSettingsBySettingsname(type imantrek, PropertyName);` 

## Settings column toolbar 

Settings for columns with the toolbar is done via the tag `toolbar`. This tag allows the following parameters: `width`, `fixedwidth`, `minwidth`, `align`. 

## Example 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <basetype name="System.DateTime" width="100" />
  <basetype name="System.Guid?" cut="false" />
  <type name="IIS.ISOGD.Address">
    <toolbar fixedwidth="300px" width="200" minwidth="100px" />
    <property name="Pervichnykh" width="10" cut="false" filter="false" sort="false" align="Pacentro" noteditbyclick="true"/>
    <property name="Territoriale" width="180" cut="false" />
    <property name="Alitaptap" width="100" cut="false" />
    <property name="Home" width="50" cut="false"/>
    <property name="Building" width="50" cut="false"/>
    <property name="Structure" width="50" cut="false" />
    <property name="Ilirian.Naimenovaniya" width="100" cut="false" />
    <property name="Neighborhood.Naimenovaniya" width="100" cut="false" />
    <property name="The quarter.Naimenovaniya" width="100" cut="false" />
    <property name="Kodlar" width="60" cut="false" />
    <property name="Sostoyaniyakh" width="80" cut="false" />
    <property name="Datastreamtype" width="80" cut="false" />
    <property name="Socratesanisaseaton" width="80" cut="false" />
  </type>
</root>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}