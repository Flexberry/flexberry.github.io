---
title: Как включить лог ошибок
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Windows UI (формы), Развёртывание
summary: Указано как включить лог ошибок в конфигурационном файле
toc: true
permalink: en/fw_include-error-log.html
folder: products/flexberry-winforms/
lang: en
---

Чтобы включить лог ошибок достаточно указать такой атрибут в файле конфигурации:

```xml   
<add key="ErrorLog" value="true" />
```

Все ErrorBox-ы будут записывать ошибки в csv-файл в папку с приложением.
