---
title: Application and desktop
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
summary: desktop apps, desktop customization, adding your own forms
toc: true
permalink: en/fw_app-desktop.html
lang: en
autotranslated: true
hash: 16a2500b8bcc1784c3066ec4c2987ef1eea1a92b1a7e182d911a07271273e283
---

The application is designed to run a desktop system. The application contains form code desktop. Form desktop inherited from `ICSSoft.STORMNET.Windows.Forms.Desktop`.

Form desktop is designed to Refine and zaprogramirovan an application developer in accordance with application requirements.

## Customize your desktop

Desk consists of the following (external view example):

![Desktop winforms application](/images/pages/products/flexberry-winforms/desktop/windesktop.png)

In the left part is a tree structure of menu items.

In the right part — the list of so-called starters» «(Runner) for the menu item selected in the left part of the menu.

The programmer can modify the structure, layout and appearance of the menu items, the composition and appearance of the starters.

Configuration is provided through a special class — Adjuster desktop, a descendant from ICSSoft.STORMNET.UI.DesktopCustomizer. The Adjuster desktop is installed in the property `DesktopCustomizer` form the desktop.

To indicate the composition of starters and placing the menu items, you should overload the Adjuster desktop method `GetRunners()` that returns a one-dimensional array of starters `ICSSoft.STORMNET.UI.Runner`.

### Starters

Basic starter `ICSSoft.STORMNET.UI.Runner` has:

* The menu path, the levels are separated with a backslash «\»;
* Заголовок;
* A description of the.

There are a few specialized starters:

* `ICSSoft.STORMNET.Windows.Forms.FormRunner` — starter form that is different from the standard that a property — the type of form that should open. Accordingly, the starter for opening forms. Method `Run()` overloaded so that designs in the form and calls the method `Show()`;
* `ICSSoft.STORMNET.UI.ContRunner` — starter independent list container, simultaneously launching in the container the script for execution. Has a property type of the container. Method `Run()` overloaded in such a way that builds a container, takes the script and runs it.
* `ICSSoft.STORMNET.UI.ScriptRunner` — starter script, located in the specified type implementing the interface `Scriptizer.DataObjects.IScripted`. Method `Run()` overloaded in such a way that launches this script.

It is possible to configure starters for the needs of a particular application to implement a given functionality or change the underlying. In particular, it is possible to overload a number of methods:

* `GetBigImage(), GetImage()` to change the icons стартера;
* `Run()` to implement the steps that occur when you select from the desktop.

### Form

In the form of desktop applications (<ApplicationName>Desktop.cs in the initial design) object of type desktopCtrl2 ICSSoft.STORMNET.Windows.Forms.DesktopCtrl has the following properties:

* `SortRunners` is responsible for sorting of items in tree and list:
* `true` - alphabetical order
* `false` - without sorting, the order matches the order of the tasks in Flexberry
* `ViewMode` responsible for the type of display list elements (icons, list, table)
* `Font` responsible for the fonts in the tree and in the list

Read more about control your desktop can be found in the article [desktop Control](fw_desktop-operations.html)

## Add forms to your desktop

To add your own form on the windows desktop application, you must:

1. To open a file `<Projectname>DesktopCustomizer.cs`.
2. In the file to find the method `GetRunner()`.
3. In [brackets programmer](fo_programmer-brackets.html) it is necessary to add the required `Стартеры`.

In the end you should get something like the following:

```csharp
public override ICSSoft.STORMNET.UI.Runner[] GetRunners()
{
    System.Collections.ArrayList arr = new System.Collections.ArrayList();
    arr.AddRange(base.GetRunners());
    // *** Start programmer edit section *** (Credits GetRunners()) 

    // *** End programmer edit section *** (Credits GetRunners()) 
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитныйИнспекторL), "The accounting system of credits", "Loan officer", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитL), "The accounting system of credits", "Credit", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "The accounting system of credits", "Client", ""));
    // *** Start programmer edit section *** (Credits GetRunners() End) 
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "The accounting system of credits\\Clients", Customers, ""));
    arr.Add(new ICSSoft.STORMNET.Windows.Forms.FormRunner(typeof(winformОтчеты), "The accounting system of credits", Reports, ""));
    // *** End programmer edit section *** (Credits GetRunners() End) 
    ICSSoft.STORMNET.UI.Runner[] retArray = new ICSSoft.STORMNET.UI.Runner[arr.Count];
    arr.CopyTo(retArray);
    return retArray;
}
```

Note1: you Can specify the path in the tree menu.

Note2: the Form is usually called with the prefix `winform`.

After launching the application, you can observe the following picture:

![Desktop customization](/images/pages/products/flexberry-winforms/desktop/win-desktop-plus.png)

## Cm. also

* [Customize desktop Starter](fd_application.html)
* [Pass parameters from ContRunner to edit form](fw_editform.html)
* [Desktop shortcuts](fw_desktop-operations.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}