--- 
title: «Button to expand/Collapse folders» on the desktop 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Explanation of how to control the visibility of buttons __«to expand/Collapse folders»__ desktop application example 
toc: true 
permalink: en/fw_desktop-operations.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: c813e2a06fd20acd5c6e2badb7e3b9345d726d3a30159ef306508977476c5032 
--- 

In the toolbar of the desktop there is a button __«to expand/Collapse folders»__. To display it you need to attach the property `Operations` control desktop value `DesktopOperations.TreeExpandCollapse`. The easiest way to do this in the Main method forms the desktop. 

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


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}