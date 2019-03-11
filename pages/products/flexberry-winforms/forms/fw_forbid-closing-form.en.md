--- 
title: How to prevent closing of the Thomas Flexberry Winforms 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: an Example of how the closure of Thomas Flexberry Winforms 
toc: true 
permalink: en/fw_forbid-closing-form.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 556320847154fba317641e51990df1782272c91f72bfcbd56344fc855b2c2ed8 
--- 

In order to prevent closing the form, you need to override the method `Finalize()` independent. 

Example: 

```csharp
[Scriptizer.RunTime.ScriptFinalizer]
public override bool Finalize()
{
return false;
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}