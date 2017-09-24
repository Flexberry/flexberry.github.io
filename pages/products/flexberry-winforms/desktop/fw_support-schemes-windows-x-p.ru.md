---
title: Поддержка схем Windows XP
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_support-schemes--windows--x-p.html
folder: products/flexberry-winforms/
lang: ru
---

Элементы управления `Flexberry Platform` поддерживают схемы пользовательских интерфейсов `Windows XP`: «мягкие» кнопки, градиентные заливки на закладках и т.д., и т.п. Такая поддержка включается явно.


Для того, чтобы настольное приложение поддержало схему пользовательского интерфейса `Windows XP`, необходимо:
# В методе `Main()` приложения вызвать метод `ICSSoft.STORMNET.Windows.Forms.WinApplication.EnableVisualStyles()`;
# Создать `Manifest`-файл в том же каталоге, где расположен exe-файл, например:

Файл приложения — `BugLeR.exe`, файл манифеста — `BugLeR.exe.manifest`, содержимое:
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