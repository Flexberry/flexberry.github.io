---
title: Установка и запуск Pentaho в Docker
keywords: Pentaho, installation, Docker, инструкция
sidebar: flexberry-analytics_sidebar
toc: false
permalink: ru/fan_pentaho-installation.html
lang: ru
summary:
---

## Установка и запуск Pentaho в Docker

### Условия для запуска

Для запуска `Pentaho` требуется иметь установленное ПО [Docker](https://docs.docker.com). Для пользоателей ОС Windows есть соответствующая версия [Docker for Windows](https://docs.docker.com/docker-for-windows/install/).

### PowerShell-скрипт установки и запуск шины

```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Pentaho'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://github.com/Flexberry/NewPlatform.Flexberry.Analytics/tree/master/pentaho'
$client.DownloadFile("$downloadPath/.env", "$pwd\.env")
$client.DownloadFile("$downloadPath/pull.sh", "$pwd\pull.cmd")
$client.DownloadFile("$downloadPath/docker-compose.yml", "$pwd\docker-compose.yml")
$client.DownloadFile("$downloadPath/composeStart.sh", "$pwd\composeStart.cmd")
$client.DownloadFile("$downloadPath/composeStart.sh", "$pwd\composeStart.cmd")
$client.DownloadFile("$downloadPath/swarmStart.sh", "$pwd\swarmStart.cmd")
$client.DownloadFile("$downloadPath/swarmStart.sh", "$pwd\swarmStart.cmd")
```

Скрипт можно скачать с [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.Analytics/master/pentaho/getPentaho.ps1).  
Для запуска скрипта нужно открыть [PowerShell консоль](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Затем можно просто скопировать текст скрипта в консоль и выполнить.
