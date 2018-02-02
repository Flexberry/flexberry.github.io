---
title: Внутренняя архитектура Flexberry Service Bus
sidebar: flexberry-servicebus_sidebar
keywords: ESB
toc: true
permalink: ru/fsb_internal-architecture.html
lang: ru
summary: Описание компонентов сервисной шины предприятия.
---

## Вместо введения

`Flexberry Service Bus` построена на компонентной архитектуре.

## Компоненты Flexberry Service Bus

`Flexberry Service Bus` включает в себя следующие компоненты:

* **ReceivingManager** - компонент принимающий сообщения.
* **SendingManager** - компонент отправляющий сообщения.
* **SubscriptionsManager** - компонент управления подписками.
* **ObjectRepository** - абстракция над ORM для доступа к хранилищу.
* **WcfService** - компонент для взаимодействия клиентов с `Flexberry Service Bus` посредством технологии WCF.
* **WebApiService** - компонент для взаимодействия клиентов с `Flexberry Service Bus` в стиле REST.
* **MailScanningService** - компонент для взаимодействия клиентов с `Flexberry Service Bus` посредством электронной почты.
* **StatisticsService** - компонент собирающий статистику работы `Flexberry Service Bus`.
* **RerouterService**
* **CrossBusCommunicationService** - компонент для взаимодействия двух экземпляров `Flexberry Service Bus`.
* **Logger** - компонент для журналирования процессв работы `Flexberry Service Bus`.

## Взаимодействие компонентов Flexberry Service Bus

Описание взаимодействия.
