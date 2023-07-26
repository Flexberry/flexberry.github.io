---
title: Инструкция по отладке odatabackend в runtime
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, отладка, пример
summary: Пошаговая инструкция по удобному способу отладки
toc: true
permalink: ru/fo_debugging-odatabackend-runtime.html
lang: ru
---

В [ODataBackend]() существует удобный способ отлавливать ошибки в runtime. Для этого нужно:

* взять [CustomExceptionFilter.cs](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/develop/NewPlatform.Flexberry.ORM.ODataServiceCore.Common/Exceptions/CustomExceptionFilter.cs), 
* создать такой же в проекте (можно, например, изменить имя класса),
* в [строке](https://github.com/Flexberry/FlexberryEmberTestStand.ODataBackend/blob/7c0b0d8ca8e44c505a42661d531d534f245cca09/EmberFlexberry/ODataBackend/Startup.cs#L60) измененного файла указать уже свой класс
* и в нем установить [брекпойнт](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/c3ed1a3c181119606c87be6f1f89a2973d85b26a/NewPlatform.Flexberry.ORM.ODataServiceCore.Common/Exceptions/CustomExceptionFilter.cs#L50).