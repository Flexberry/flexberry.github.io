--- 
title: passing parameters from ContRunner to edit form 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: See what methods and how to override to take a parameter from DesktopCustomizer in list form and pass to the edit form 
toc: true 
permalink: en/fw_parameters-from-cont-runner-to-editform.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 8c9776d8d2f1002b2e30d2efd8b49a13b9a33341f373ca9b27c1872a2011a6fc 
--- 

## passing parameters in DesktopCustomizer 
In order to pass a variable to the container startup use the overloaded method of creating `ContRunner`. 

```csharp
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.LimitsTesting_Blog.LTB_БлогL), "MyTag1FromContRunner", "LimitsTesting_Blog", "Blog", ""));
``` 

## Reception parameter in a list form and the edit form 
Parameter in list form gets in the method `Activate(object tag)`. 
Pay attention to the number of parameters in overloaded method `OnNewEvent()`. You should overload a method with 2 parameters and call it with method 3. Default object creation occurs without the transfer of the tag. 

```csharp
// *** Start programmer edit section *** (LTB_БлогL CustomMembers) 
private object _tagFromRunner;

public override void Activate(object tag)
{
    _tagFromRunner = tag;
    // MessageBox.Show("Activate" (tag??"<empty>")); 
    base.Activate(tag);
}

protected override void OnNewEvent(Type dataobjecttype, string contpath)
{
    // MessageBox.Show("OnNewEvent"); 
    base.OnNewEvent(dataobjecttype, contpath, _tagFromRunner);
}

protected override void OnEditEvent(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
{
    // MessageBox.Show("OnEditEvent" (tag ?? "<empty>")); 
    base.OnEditEvent(propertyname, dataobject, contpath, _tagFromRunner);
}
// *** End programmer edit section *** (LTB_БлогL CustomMembers) 
``` 

## Receiving option on the edit form 
In the method `Edit` with the largest number of options comes the same tag. 

```csharp
// *** Start programmer edit section *** (WinformLTB_БлогE CustomMembers) 
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
    // MessageBox.Show("Blog tag:" (tag??"<empty>")); 
    base.Edit(dataobject, contpath, propertyname, tag);
}
// *** End programmer edit section *** (WinformLTB_БлогE CustomMembers) 
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/