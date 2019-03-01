--- 
title: "Click to Collapse/expand folders" on the desktop 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Explanation of how to control the visibility of the button __"to expand/Collapse folder"__ desktop application example 
toc: true 
permalink: en/fw_desktop-operations.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 6138a084dadfeceac6347a88ba6ab11f6aa52ce5a52d4e56e0aedb0c3c05859c 
--- 

In the toolbar of the desktop there is a button __"to expand/Collapse folder"__. To display it you need to attach the property `Operations` control desktop value `DesktopOperations.TreeExpandCollapse`. The easiest way to do this in the Main method forms the desktop. 

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