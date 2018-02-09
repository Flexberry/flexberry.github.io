---
title: Установка и запуск шины в Docker
sidebar: flexberry-servicebus_sidebar
keywords: ESB, docker, swarms
toc: true
permalink: ru/fsb_installation.html
lang: ru
summary: Запуск и остановка шины в docker swarms.
---

## Установка и запуск шины в Docker

### Условия для запуска
* Для запуска Flexberry Service Bus требуется [Docker](https://docs.docker.com/docker-for-windows/install/) версии не ниже 17.05. Рекомендуется Docker for Windows.

### PowerShell-скрипт установки и запуска шины
* Скрипт можно взять [здесь](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/getServiceBus.ps1).
```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Flexberry Service Bus'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker'
$client.DownloadFile("$downloadPath/cloud.yml", "$pwd\cloud.yml")
$client.DownloadFile("$downloadPath/startCloud.cmd", "$pwd\startCloud.cmd")
$client.DownloadFile("$downloadPath/stopCloud.cmd", "$pwd\stopCloud.cmd")
.\startCloud.cmd
```

### Проверка успешности запуска шины
* По умолчанию админка у запущенной шины - http://localhost:180/.
* WCF, по умолчанию, доступен [тут](http://localhost:7075/HighwaySBMonoPostgreSQLWcfService).
* REST, по умолчанию, доступен [тут](http://localhost:7085/HighwaySBMonoPostgreSQLWebApiService).

### Дальнейшие действия с шиной
* [Разработка адаптера для шины]()

### Дополнительно
* Скачивание контейнеров: все необходимые контейнеры будут загружены при первом запуске.
* Настройка контейнеров:
[замена Web.config для административного приложения шины](), [замена App.Config для шины]().
* Запуск контейнеров:
для старта требуется выполнить командный файл [startCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/startCloud.cmd).
* Остановка контейнеров:
для остановки необходимо выполнить командный файл [stopCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/stopCloud.cmd).
