---
title: Установка Legacy Security
sidebar: flexberry-security_sidebar
keywords: flexberry, legacysecurity, legacy, security, install
summary: Установка LegacySecurity
toc: true
permalink: ru/fs_legacy-security-install.html
lang: ru
---

##  Установка LegacySecurity
Последняя версия `ASP.NET`, совместима со старыми полномочиями, но имеет в зависимостях `Security`.
Для установки `LegacySecurity` надо:

1. Установить пакеты:
    * `NewPlatform.Flexberry.ORM` 4.1.0-beta07
    * `NewPlatform.Flexberry.AspNet` 2.3.1-beta07
    * `NewPlatform.Flexberry.Audit` 3.0.0-beta01
    * `NewPlatform.Flexberry.LegacySecurity` 1.0.0-beta02

2. Удалить `NewPlatform.Flexberry.Security` и его изменения в конфиге не обращая внимания на связь с `ASP.NET`.

Почти во всех пакетах сейчас зашиты трансформации конфига, поэтому в секции `Unity` установится что нужно само, но с параметрами по умолчанию.
