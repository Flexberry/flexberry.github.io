--- 
title: working with the menu in the main form of the application 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), the Windows UI (forms) 
summary: For example, consider how to change the menu items on the main form of the application 
toc: true 
permalink: en/fw_work-with-menu-in-main-form-app.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 9deaf623658d386e917a080cd8d85e9ca078d3e902b38ca3fec4fefeadcc130e 
--- 

Find in the application Main method. 
Next, look for the variable desktop 
which properties are 
all elements of an existing menu! 

Example: 
Remove the visibility of the menu item "Properties". 
Added menu item "switch user" with the handler. 
Add a handler to the menu item "Help". 

```csharp
[STAThread()]
static void Main()
{
//... 
      БорьбаDesktop desktop = new БорьбаDesktop();
      //... 
      desktop.menuItem1.MenuItems[0].Visible = false;                 //Свойства; 
      desktop.menuItem1.MenuItems.Add(0,new MenuItem("Change user...",new EventHandler(БорьбаDesktop_Click)));
      desktop.menuItem4.Click += new EventHandler(menuItem4_Click);   //Помощь; 
      //... 
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}