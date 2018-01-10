---
title: Запуск приложения в конкретной версии .NET Framework
sidebar: guide-base-tech_sidebar
keywords: Развёртывание
toc: true
permalink: ru/gbt_set-runtime-dotnet-version.html
lang: ru
---

Вопрос: что написать в App.exe.config для того что-бы указать 
приложению использовать ТОЛЬКО .NET Framework конкретной версии, например 2.0.40607 ?
```xml
<configuration>
  <startup>
    <!-- Тут - номер нужной версии -->
    <supportedRuntime version="v2.0.40607" />
  </startup>
</configuration>
```

Подробнее читайте тут: <http://msdn.microsoft.com/en-us/library/w4atty68.aspx>
