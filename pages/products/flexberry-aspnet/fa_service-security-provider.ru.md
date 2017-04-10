---
title: ServiceSecurityProvider
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_service-security-provider.html
lang: ru
---

Данный провайдер используется для того, чтобы сделать использование веб-сервиса в веб-приложении более безопасным. При помощи него можно указать какому методу из веб-сервиса какие типы объектов можно читать из базы. Это сделано потому, что есть довольно общие методы, при помощи которых можно прочитать все данные из базы.

## Конфигурационный файл

Конфигурационный файл находится в папке `/xml` под названием `ServiceSecurityProvider.xml`

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <method name="GetPropertyValues">
    <type name="IIS.КошкиСЛапами.Кошка, КошкиСЛапами(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
    <type name="IIS.КошкиСЛапами.Порода, КошкиСЛапами(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </method>

  <method name="LoadObject">
    <type name="IIS.КошкиСЛапами.Порода, КошкиСЛапами(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </method>
</root>
```

Для каждого метода указывается список разрешенных типов.

## Места использования

На текущий момент в WebTools есть класс `AjaxServiceHelper`, который инкапсулирует в себе работу с базой и настройками из конфигурационного файла `ServiceSecurityProvider.xml`. Данный класс (`AjaxServiceHelper`) вызывается из методов веб-сервиса в веб-приложении.
