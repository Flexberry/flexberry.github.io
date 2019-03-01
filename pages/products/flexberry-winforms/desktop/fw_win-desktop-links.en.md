--- 
title: desktop Shortcuts 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: in Detail the mechanism for applying labels designed to work with instances of data objects from the desktop, specified how to apply it, examples 
toc: true 
permalink: en/fw_win-desktop-links.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 7461a28e789cdf3f9ab53ead30c1e3b07f68ab7a4863cd8c01cc7bb7e3c7b35a 
--- 

The mechanism of labels allows you to work with instances of data objects from the desktop. 

Labels should be applied, if in any instance it is necessary to work very often. It will not load the list form and do not look for instance at her. 

Labels can be applied to an instance of any class that is the successor ICSSoft.STORMNET.DataObject. 

## Folders for shortcuts 
The folder in which you can create labels can be specified by overriding a method GetShortCutFolders() class DesktopCustomizer. This method returns an ArrayList whose elements are strings that specify the full path to the folder. By default, this method returns only the folder "favorites". 

Example 

```csharp
public class NewDesktopCustomizer : ICSSoft.STORMNET.UI.DesktopCustomizer
{
        …
        public override System.Collections.ArrayList GetShortCutFolders()
        {            
            System.Collections.ArrayList arr = base.GetShortCutFolders();
            arr.Add("NewFolder");
            return arr;
        }
        …
}
``` 

## the name of the shortcut 
When you create a shortcut by default takes the name of a list form. To assign a label adequate name, do the following: 
The class of the corresponding object, you need to mark an attribute ShortCutCaption. Setting this attribute is the name that will appear with the label. In the name of the shortcut you can specify @name@ where name is the name of a property or field of this class, then the name of the label instead of this design will be substituted the value of the specified property or field of the corresponding data object. 

Example: 

```csharp
[ShortCutCaption("A cat by nickname @Nickname@")]
public class Кошка : ICSSoft.STORMNET.DataObject
{
        …
        public virtual string Кличка
        {
            get
            {
                return this.fКличка;
            }
            set
            {
                this.fКличка = value;
            }
        }
        …
}
``` 

As a result, the name of the label for the data object "Cat" is, for example, "a Cat named Murka". 

## Runners 
Runner – a class that implements the methods for running a label. In the designer, a runner takes a shortcut. To add a new runner, you need to create a new class that are inherited from DesktopShortCutRunner. The methods of the runner, marked RunnerMethodAttribute can be started from the context menu of the label, with this type of runner. The attribute parameter is a string that will be displayed in the corresponding item in the context menu of the shortcut. 
In the base class DesktopShortCutRunner implemented two standard methods: 

RunEditForm – launch the edit form. A parameter is passed the name of the type editing form. 

RunObjectMethod – Start method of the object. A parameter is passed the name of the method. 

The second parameter of both methods can be passed the name of the view to load the object (this option is not mandatory, but in some cases its absence may lead to errors). 
In runner, you can specify default picture labels with the given type. 

## How to apply runner for labels 

Runner, who will run the shortcut that is automatically defined when you create the shortcut.
In order for a runner used to label objects of a particular type, do the following: 

1) the runner GetObjectTypes implement a static method that returns an array of types for which you want to apply data runner. 

2) Override the method GetShortCutRunners() class DesktopCustomizer. This method returns a dictionary whose key is the class name of the runner, and the value is an array of supported types of objects that the method returns GetObjectTypes(). 

By default, this method returns only runner for the report (ReportRunner). 

Example: 

```csharp
public class NewRunner : DesktopShortCutRunner
{
        …
        public static Type[] GetObjectsTypes()
        {
            return new Type[] { Type.GetType("ICSSoft.STORMNET.MyType, MyTypeAssemblyName") };
        }
        …
}

public class NewDesktopCustomizer : ICSSoft.STORMNET.UI.DesktopCustomizer
{
        …
        public override Dictionary<string, Type[]> GetShortCutRunners()
        {
            Dictionary<string, Type[]> shortCutRunners = base.GetShortCutRunners();
            shortCutRunners.Add("ICSSoft.STORMNET.NewShortCutRunner, NewShortCutRunnerAssemblyName", NewShortCutRunner.GetObjectsTypes());
            return shortCutRunners;
        }
        …
}
``` 

## create a label from code 
The label is created by calling the static method CreateShortCut. The method takes the name of the shortcut, the path to the folder in which to display the label, the type of runner, a data object corresponding to the label type edit form and picture to display the shortcut. If this parameter is null, the label will be displayed by the default picture. Another optional parameter specifies whether the created shortcut, common or not. If the label is common, it is in a database in the User field will be null in the future it will be seen by all users. If the label is not shared, in the database the label will remain with the current user.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/