--- 
title: setting up a debug packet and the debug package 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Shows how to specify a debug package, i.e. to specify the data classes, performances, etc. to set the properties of controls in `DesignTime` 
toc: true 
permalink: en/fw_visualstudio-design-packages.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 32af0162303a3f4f65894257d825507dba3c4375db04d764710791004ab12723 
--- 

There is a problem in `Design-time` when configuring `Flexberry Platform`-controls necessary to ensure the developer can specify the data classes, views, etc. through a standard window of the task environment properties `VisualStudio`. Therefore, it is necessary to specify what `.NET`-assemblies are data classes. This combination of assemblies is called a debug package. The programmer can set himself any number of such packages. One of the packages is called the current, i.e., those whose assemblies are used in the project. 


To invoke the settings window of the debug packages, the programmer must select the control, open the properties window, where you click on the link “`Configure design packages`”: 
![](/images/pages/products/flexberry-winforms/development/primer13.jpg) 
Then a window will appear: 
![](/images/pages/products/flexberry-winforms/development/primer14.jpg) 
Here, moving through the package list, you can configure each library package. 

When you press the button to save» «is the preservation of the batches and selected in the pack list becomes current. 

Data is stored in `Windows Registry`. 

The debug packages are designed exclusively for the ability to easily set properties in `DesignTime`. 





 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/