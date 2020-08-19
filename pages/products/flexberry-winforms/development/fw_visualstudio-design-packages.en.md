---
title: setting up a debug packet and the debug package
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, exception, Design-time
summary: sets the debug package in a Design time
toc: true
permalink: en/fw_visualstudio-design-packages.html
lang: en
autotranslated: true
hash: a19d102638a29031a6dfc32fa9d5770248c1f547bc5d2d7509914fee33881d56
---

There is a problem in `Design-time` when configuring `Flexberry Platform`-controls necessary to ensure the developer can specify the data classes, views, etc. through a standard window of the task environment properties `VisualStudio`. Therefore, it is necessary to specify what `.NET`-assemblies are data classes. This combination of assemblies is called a debug package. The programmer can set himself any number of such packages. One of the packages is called the current, i.e., those whose assemblies are used in the project.

To invoke the settings window of the debug packages, the programmer must select the control, open the properties window, where you click on the link `Configure design packages`:

![Link to packages](/images/pages/products/flexberry-winforms/development/primer13.jpg)

Then a window will appear:

![List of libraries](/images/pages/products/flexberry-winforms/development/primer14.jpg)

Here, moving through the package list, you can configure each library package.

When you press the button to save» «is the preservation of the batches and selected in the pack list becomes current.

Data is stored in `Windows Registry`.

The debug packages are designed exclusively for the ability to easily set properties in `DesignTime`.

## Troubleshooting Design-Time

If you do not open the window the debug packages or impossible to set properties of controls, such as classes, views, make sure that the Assembly `ICSSoft.STORMNET.Collections.dll and ICSSoft.STORMNET.DataObject.dll` placed in `Global Assembly Cache` and they are relevant. If necessary, reinstall the assemblies.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}