---
title: IIS.Flexberry.Logging
sidebar: flexberry-orm_sidebar
keywords: Flexberry LogService
toc: true
permalink: ru/fo_flexberry-log-service-objects.html
folder: products/flexberry-orm/
lang: ru
---

# Логирование при помощи [Microsoft Enterprise Library](http://msdn.microsoft.com/en-us/library/ff632023.aspx) и Flexberry.

При использовании [Microsoft Enterprise Library Logging Application Block](http://msdn.microsoft.com/en-us/library/ff664569(v=pandp.50).aspx) можно использовать в качестве Trace Listener'а класс CaseberryDatabaseTraceListener из сборки IIS.Flexberry.Logging.MsEntLib.

Пример раздела listeners конфигурации приложения:
```
    <listeners>
      <add formatter="Text Formatter"
			  listenerDataType="IIS.Flexberry.Logging.MsEntLib.Configuration.CaseberryDatabaseTraceListenerData, IIS.Flexberry.Logging.MsEntLib, Version=1.0.0.0, Culture=neutral, PublicKeyToken=e89274d6fcfab3e9"
			  traceOutputOptions="None" type="IIS.Flexberry.Logging.MsEntLib.CaseberryDatabaseTraceListener, IIS.Flexberry.Logging.MsEntLib, Version=1.0.0.0, Culture=neutral, PublicKeyToken=e89274d6fcfab3e9"
			  name="Database Trace Listener" />
    </listeners>
```
Записи лога будут сохраняться в таблице ApplicationLog. Список для просмотра лога можно встроить в приложение, используя класс ApplicationLogL из сборки IIS.Flexberry.Logging(Forms):
```
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Flexberry.Logging.Forms.ApplicationLogL), "Логирование", "Лог приложения", ""));
```
 (код для DesktopCustomizer'а)
 

