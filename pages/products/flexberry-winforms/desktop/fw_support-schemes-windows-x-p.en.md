--- 
title: Support schemes for Windows XP 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: describes how to enable support for schemas, user interfaces `Windows XP`, for example, `мягкие` buttons, gradients on the tabs, etc, sample 
toc: true 
permalink: en/fw_support-schemes-windows-x-p.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: ce27bf8839da3c813200268dfba9f79cc04704d2cec32d40d75cc4b4d8ec45a0 
--- 

Controls `Flexberry Platform` support the scheme of user interfaces `Windows XP`: `мягкие` buttons, gradients on the tabs, etc., etc., Such support is included explicitly. 


To a desktop application supported scheme UI `Windows XP`, you must: 
* In the method `Main()` application to invoke a method `ICSSoft.STORMNET.Windows.Forms.WinApplication.EnableVisualStyles()`; 
* Create `Manifest` file in the same directory where is. exe file, for example: 

Application file — `BugLeR.exe`, the manifest file is `BugLeR.exe.manifest`, content: 

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<assembly xmlns="urn:schemas-microsoft-com:asm.v1" manifestVersion="1.0">
<assemblyIdentity
    version="1.0.0.0"
    processorArchitecture="X86"
    name="BugLeR"
    type="win32"
/>
<description>Your application description here.</description>
<dependency>
    <dependentAssembly>
        <assemblyIdentity
            type="win32"
            name="Microsoft.Windows.Common-Controls"
            version="6.0.0.0"
            processorArchitecture="X86"
            publicKeyToken="6595b64144ccf1df"
            language="*"
        />
    </dependentAssembly>
</dependency>
</assembly>
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/