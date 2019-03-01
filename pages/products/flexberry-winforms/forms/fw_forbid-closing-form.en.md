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
hash: 27b91c9cd7350ae03b1492a9e816e5375dd043c3675e1801e9174f2476fcd0bd 
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


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/