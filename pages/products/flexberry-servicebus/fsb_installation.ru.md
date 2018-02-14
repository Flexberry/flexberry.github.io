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

### PowerShell-скрипт установки и запуска шины

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

Скрипт можно скачать с [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker/getServiceBus.ps1).  
Для запуска скрипта нужно открыть [PowerShell консоль](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Затем можно либо прописать в консоли путь к скрипту (например D:\userName\NewPlatform.Flexberry.ServiceBus\Docker\getServiceBus.ps1) и запустить его, либо просто скопировать текст скрипта в консоль и выполнить.

### Проверка успешности запуска шины
* В PowerShell консоли нужно ввести "docker ps", в результате будет выдан список запущенных docker образов (среди них должны быть hwsb-postgres, servicebuseditor и hwsb).
* Административное приложение с конфигурацией по умолчанию доступно по адресу <http://localhost:180>. Логин и пароль: `admin`, `admin` (при переходе на промышленную эксплуатацию обязательно нужно сменить).
* WCF-интерфейс шины с конфигурацией по умолчанию: <http://localhost:7075/HighwaySBMonoPostgreSQLWcfService>.
* REST-интерфейс шины с конфигурацией по умолчанию: <http://localhost:7085/HighwaySBMonoPostgreSQLWebApiService>.

### Дальнейшие действия с шиной
* [Разработка адаптера для шины](fsb_adapters.html)

### Дополнительно
* Скачивание контейнеров: все необходимые контейнеры будут загружены при первом запуске.
* Настройка контейнеров:
[замена Web.config для административного приложения шины](fsb_editor.html), [замена App.Config для сервиса шины](fsb_service.html).
* Запуск контейнеров:
для старта требуется выполнить командный файл [startCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/startCloud.cmd).
* Остановка контейнеров:
для остановки необходимо выполнить командный файл [stopCloud.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/stopCloud.cmd).
