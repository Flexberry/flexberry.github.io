---
title: MongoDbDataService
sidebar: flexberry-orm_sidebar
keywords: MongoDB, NoSQL
toc: true
lang: ru
permalink: ru/fo_mongodb-data-service.html
summary: Cервис данных для работы с NoSQL СУБД MongoDB.
---

## Описание

Для использования технологий обработки и хранения больших данных можно использовать [сервис данных](fo_data-service.html) для [NoSQL хранилища MongoDB](gbt_mongodb.html) - `MongoDbDataService`.  Этот сервис данных реализует стандартный интерфейс `IDataService`, что позволяет использовать его обычным образом. Также доступны сценарии, когда одно приложение работает с несколькими хранилищами, соответственно может быть вариант, когда одно хранилище представляет собой БД [Postgres](gbt_postgresql.html), а другое - это [MongoDB](gbt_mongodb.html).  

## Установка

Для того, чтобы начать использовать сервис данных `MongoDbDataService` нужно установить в проект соответствующий [NuGet-пакет NewPlatform.Flexberry.ORM.MongoDbDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.MongoDbDataService), который доступен через публичную галерею <https://nuget.org>.  

## Пример использования

Познакомиться с примером использования `MongoDbDataService` можно в [репозитории с бакендом демо-приложения - FlexberryOrmMongoDbDataServiceDemo.ODataBackend](https://github.com/Flexberry/FlexberryOrmMongoDbDataServiceDemo.ODataBackend). В коде этого приложения приводится пример одновременной работы с двумя типами хранилищ.  
[Фронтенд-часть приложения](https://github.com/Flexberry/flexberry-orm-mongodb-dataservice-demo-frontend) реализует механизмы переключения между несколькими бакендами (ещё один вариант организации доступа к хранилищам различных типов).  
Собранный и развёрнутый бакенд демо-приложения, работающий с хранилищем больших данных доступен по адресу <http://flexberry-orm-mongodb-dataservice-demo.azurewebsites.net/odata>.

## Доработка сервиса данных

Разработка `MongoDbDataService` ведётся в [открытом репозитории на GitHub - NewPlatform.Flexberry.ORM.MongoDbDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService). О проблемах можно сообщать при помощи создания соответствующего [Issue](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/issues), также можно выполнить доработку и предложить [Pull request](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/pulls) в [ветку develop](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/tree/develop).
