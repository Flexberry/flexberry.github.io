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

Для запуска `Flexberry Service Bus` требуется иметь установленное ПО [Docker](https://docs.docker.com). Для пользоателей ОС Windows есть соответствующая версия [Docker for Windows](https://docs.docker.com/docker-for-windows/install/).

### PowerShell-скрипт установки и запуск шины

```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Flexberry Service Bus'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker'
$client.DownloadFile("$downloadPath/flexberry-service-bus-swarm-configuration.yml", "$pwd\flexberry-service-bus-swarm-configuration.yml")
$client.DownloadFile("$downloadPath/flexberry-service-bus-rabbitmq.yml", "$pwd\flexberry-service-bus-rabbitmq.yml")
$client.DownloadFile("$downloadPath/start-flexberry-service-bus.cmd", "$pwd\start-flexberry-service-bus.cmd")
$client.DownloadFile("$downloadPath/stop-flexberry-service-bus.cmd", "$pwd\stop-flexberry-service-bus.cmd")
```

Скрипт можно скачать с [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker/getServiceBus.ps1).  
Для запуска скрипта нужно открыть [PowerShell консоль](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Затем можно либо прописать в консоли путь к скрипту (например D:\userName\NewPlatform.Flexberry.ServiceBus\Docker\getServiceBus.ps1) и запустить его, либо просто скопировать текст скрипта в консоль и выполнить.
После выполнения скрипта будут скачаны все необходимые файлы для запуска шины, в командной строке выполните `start-flexberry-service-bus.cmd` для запуска и `stop-flexberry-service-bus.cmd` для остановки контейнеров шины.

{% include note.html content="С версии `1.2.0` шина запускается в конфигурации с `RabbitMQ`, используйте команду `start-flexberry-service-bus.cmd no-rabbit` для запуска в конфигурации по умолчанию." %}

### Проверка успешности запуска шины

* В PowerShell консоли нужно ввести "docker ps", в результате будет выдан список запущенных docker образов (среди них должны быть flexberry-service-bus-postgres-db, flexberry-service-bus-editor и flexberry-service-bus).
* Административное приложение с конфигурацией по умолчанию доступно по адресу <http://localhost:1818>. Логин и пароль: `admin`, `admin` (при переходе на промышленную эксплуатацию обязательно нужно сменить).
* WCF-интерфейс шины с конфигурацией по умолчанию: <http://localhost:7075/WcfService>.
* REST-интерфейс шины с конфигурацией по умолчанию: <http://localhost:7085/RestService>.

### Дальнейшие действия с шиной

* [Разработка адаптера для шины](fsb_adapters.html)

### Дополнительно

* Скачивание контейнеров: все необходимые контейнеры будут загружены при первом запуске.
* Настройка контейнеров:
[замена Web.config для административного приложения шины](fsb_editor.html), [замена App.Config для сервиса шины](fsb_service.html).
* Запуск контейнеров:
для старта требуется выполнить командный файл [start-flexberry-service-bus.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/start-flexberry-service-bus.cmd).
* Остановка контейнеров:
для остановки необходимо выполнить командный файл [stop-flexberry-service-bus.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/stop-flexberry-service-bus.cmd).
