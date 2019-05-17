---
title: Characteristics of forms in Windows applications
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Forms
summary: uniform treatment, control of the screen position dependent and independent forms, the ban on closing the form, closing all forms
toc: true
permalink: en/fw_form-features.html
lang: en
autotranslated: true
hash: b855e56e97ec91c5e0a9b1e913f81cc30b6c59e0c1b1d1c030453e7c31702868
---

There are a number of features that are characteristic for the edit forms and list forms Windows-based applications.

## Uniform processing of all application forms

To uniformly process all of the forms application, you need to subscribe to global events.

In the Main method, you can subscribe to special event:

```csharp
static void Main()
...
// *** Start programmer edit section *** (Metalmania Main()) 
ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian();
ICSSoft.STORMNET.Windows.Forms.Desktop.GlobalWinformEvents.Load += new EventHandler(GlobalWinformEvents_Load);
// *** End programmer edit section *** (Metalmania Main()) 
ICSSoft.STORMNET.Business.LockService.ClearAllUserLocks();
...
}
```

In the handler for this event will be coming form in the variable sender. Further, for example, all the forms to sign at the pressing of certain keys:

```csharp
// *** Start programmer edit section *** (ДетейломанияDesktop CustomMembers) 
static void GlobalWinformEvents_Load(object sender, EventArgs e)
{
((Form)sender).KeyPreview = true;
((Form)sender).KeyPress += new KeyPressEventHandler(ДетейломанияDesktop_KeyPress);
}
static void ДетейломанияDesktop_KeyPress(object sender, KeyPressEventArgs e)
{
if (e.KeyChar == 13)
{
MessageBox.Show("Enter was pressed","Hello");
}
}
// *** End programmer edit section *** (ДетейломанияDesktop CustomMembers) 
```

## The implementation of interfaces independent and dependent forms and the relationships between them

The class diagram below shows how the relate to each other independent and dependent forms lists generic and implemented on the basis of specific forms of lists (can be implemented manually or through code generator for `Flexberry`):

![Example of when the dependent and independent forms of lists](/images/pages/products/flexberry-winforms/forms/primer11.jpg)

The diagram below shows how the relate to each other independent and dependent forms edit, universal, and basic and specific, based on the base form (can be implemented manually or through code generator for `Flexberry`):

![Example of when the dependent and independent forms editing](/images/pages/products/flexberry-winforms/forms/primer12.jpg)

## The form position on the screen

In some cases, vosstanovlenie the position of a form led to the fact that the form was not visible on the screen. This situation for example occurs when you change the screen resolution to a lower: in the new solution form can be outside the visible area.

To control the position of the form in Flexberry Platform class `BaseWin`, which is the basic of all forms, were added two properties `OutScreenControl` and `VisiblePercent`.

Property `OutScreenControl` allows you to enable the control of the exit window outside the screen in the process of moving Windows user. Property `VisiblePercent` sets the percentage of the form size (length/width), which must remain visible (the range of values from 0 to 100).

Comments:

1. When the form is loaded the controls outside of the screen is regardless of the value of the property `OutScreenControl` (for correct positioning of the forms when changing resolution). The initial display of the form takes into account the value `VisiblePercent`, the default value of the property `VisiblePercent` equal to __40__.

2. The current implementation does not consider multiple monitors.

## The prohibition of closure of the mold Flexberry Winforms

In order to prevent closing the form, you need to override the method `Finalize()` independent.

Example:

```csharp
[Scriptizer.RunTime.ScriptFinalizer]
public override bool Finalize()
{
return false;
}
```

## Closing all open forms

To close all open forms application, as happens when closing the main application window Flexberry, you can use the following code.

```csharp
var coll = desktopCtrl2.PathRunners;

foreach (ArrayList runList in coll.GetAllValues())
{
  foreach (Runner run in runList)
  {
    if (run.Alive)
    {
      run.Stop();
    }
  }
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}