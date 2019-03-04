--- 
title: Button `Свернуть/deploy папки` on the desktop 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Explanation of how to control the visibility of buttons __`Свернуть/deploy папки`__ desktop application example 
toc: true 
permalink: en/fw_desktop-operations.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 4b8e7bcb52ccfe6b2154c3a48d16dca6472bc7418745d7b333ea18eda7e2f75b 
--- 

In the toolbar of the desktop there is a button __`Свернуть/deploy папки`__. To display it you need to attach the property `Operations` control desktop value `DesktopOperations.TreeExpandCollapse`. The easiest way to do this in the Main method forms the desktop. 

```csharp
static void Main()
{
    try
    {
		...

        КошкиИЛапыDesktop desktop = new КошкиИЛапыDesktop();
		
		...  

        desktop.desktopCtrl2.Operations = DesktopOperations.TreeExpandCollapse;
        Application.Run(desktop);
    }
    catch (System.Exception e)
    {
		...
    }
}
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/