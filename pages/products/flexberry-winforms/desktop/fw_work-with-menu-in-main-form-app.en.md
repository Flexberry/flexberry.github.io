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
hash: 0a6bd6e2e2e47c5cc1ff9790b8cd3d694921e0b670d316d99cdc0e9de77d0d1c 
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


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/