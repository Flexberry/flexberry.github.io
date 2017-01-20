---
title: Настройка имени главной формы Flexberry Tool
sidebar: product--sidebar
keywords: Flexberry Designer, Platform Internals, Public
toc: true
permalink: ru/flexberry-designer-form-name.html
folder: product--folder
lang: ru
---
# Настройка имени главной формы Flexberry Tool
В версии после 29.04.2013 появилась возможность задавать в конфиг-файле Flexberry Tool настройку для задания имени главной формы Flexberry Tool (данная возможность полезна тем, кому необходимо одновременно работать с несколькими версиями Flexberry Tool, чтобы отличать их).

Для изменения имени главной формы в `Flexberry.exe.config` необходимо определить настройку `CaseToolFormName` (если данная настройка будет отсутствовать, либо ей будет определено пустое значение, то форма будет иметь по умолчанию имя "Flexberry")
```xml
<?xml version="1.0"?>
<configuration>
  <appSettings>
    ...
    <add key="CaseToolFormName" value="aprilCASEBERRY"/>
    ...
  </appSettings>
</configuration>
```

----