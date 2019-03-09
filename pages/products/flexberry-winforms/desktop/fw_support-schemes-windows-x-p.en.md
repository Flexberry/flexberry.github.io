--- 
title: Support schemes for Windows XP 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: describes how to enable support for schemas, user interfaces `Windows XP`, for example,» «soft buttons, gradients on the tabs, etc, sample 
toc: true 
permalink: en/fw_support-schemes-windows-x-p.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 611c575f5be6c9a82686d74496ae61571b221b9ca97ebb4c00b7a5ac49568e74 
--- 

Controls `Flexberry Platform` support the scheme of user interfaces `Windows XP`:» «soft buttons, gradients on the tabs, etc., etc., Such support is included explicitly. 


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