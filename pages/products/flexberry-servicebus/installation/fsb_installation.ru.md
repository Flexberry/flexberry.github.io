---
title: Запуск и остановка шины Flexberry Service Bus в режиме docker swarms
sidebar: flexberry-servicebus_sidebar
keywords: ESB, docker, swarms
toc: true
permalink: ru/fsb_installation.html
lang: ru
summary: Запуск и остановка шины в docker swarms.
---

## Запуск и остановка шины Flexberry Service Bus в режиме docker swarms

### Требования
* Должен быть установлен [Docker](https://docs.docker.com/docker-for-windows/install/) версии не ниже 17.05. Рекомендуется Docker for Windows.
* Необходимо выполнить команды `git clone https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus` и `git checkout develop`, а затем перейти в папку `NewPlatform.Flexberry.ServiceBus/Docker/`.

### Старт сервисов
При первом запуске будут выкачены необходимые образы. Для старта требуется выполнить командный файл [startCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/startCloud.cmd).

### Остановка сервисов
Для остановки необходимо выполнить командный файл [stopCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/stopCloud.cmd).
