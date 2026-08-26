---
title: Установка и запуск «Flexberry Analytics» в Docker
keywords: Pentaho, installation, Docker, инструкция
sidebar: flexberry-analytics_sidebar
toc: false
permalink: ru/fan_pentaho-installation.html
lang: ru
summary:
---

Сервис «Flexberry Analytics» обеспечивает сервер `Pentaho`, офомленный в виде [Docker-образа](https://hub.docker.com/r/flexberry/pentaho/).

Для запуска образа `Pentaho` требуется иметь установленное ПО [Docker](https://docs.docker.com). Для пользователей ОС Windows есть соответствующая версия [Docker for Windows](https://docs.docker.com/docker-for-windows/install/).

### PowerShell-скрипт установки и запуск Pentaho

```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Pentaho'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.Analytics/master/pentaho'
$client.DownloadFile("$downloadPath/.env", "$pwd\.env")
$client.DownloadFile("$downloadPath/pull.sh", "$pwd\pull.cmd")
$client.DownloadFile("$downloadPath/docker-compose.yml", "$pwd\docker-compose.yml")
$client.DownloadFile("$downloadPath/composeStart.sh", "$pwd\composeStart.cmd")
$client.DownloadFile("$downloadPath/composeStop.sh", "$pwd\composeStop.cmd")
$client.DownloadFile("$downloadPath/swarmStart.sh", "$pwd\swarmStart.cmd")
$client.DownloadFile("$downloadPath/swarmStop.sh", "$pwd\swarmStop.cmd")
```

Скрипт можно скачать с [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.Analytics/master/pentaho/getPentaho.ps1).  
Для запуска скрипта нужно открыть [PowerShell консоль](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Затем можно просто скопировать текст скрипта в консоль и выполнить.

### Конфигурация TCP-портов сервера

Конфигурация сервера описана в файле `.env`.

Сервер использует следующие TCP-порты:
```
SERVER_HTTP_PORT=8080
SERVER_AJP_PORT=8009
SERVER_PROM_PORT=1234
```

Если в системе эти порты уже заняты можно переопределить указанные переменные в файле `.env`, указав свободные порты.

### Получение образа

Для работы необходимо предварительно скачать образ с репозитория скриптом `pull.cmd`.

Если образ в репозитории обновился для его использования необходимо повторно запустить команду `pull.cmd`.


### Запуск сервиса

 Для запуска сервиса необходимо запустить скрипт  `composeStart.cmd`.

 Инициализация сервиса происходит в течении 30-60 секунд.

### Проверка успешности запуска Pentaho

В PowerShell консоли нужно ввести
```powershell
docker ps
```

В результате будет выдан список запущенных `docker образов`.
Среди них должен быть `flexberry/pentaho-saiku:8.0`.

Административное приложение с конфигурацией по умолчанию доступно по адресу <`http://<IP_адрес_сервера>:<SERVER_HTTP_PORT>`>. 

Если доступ к серверу производится с компьютера на котором запущен сервис и значение переменной SERVER_HTTP_PORT в файле `.env` не изменялось, то URL будет выглядеть как:
`http://localhost:8080/`.

Логин и пароль: `admin`, `password` (при переходе на промышленную эксплуатацию обязательно нужно сменить).

При первоначальном запуске образа создаются именованые тома:
- pentaho_hidden - скрытые файлы сервера pentaho;
- pentaho_hsqldb - внутренняя база данных типа  hsql для хранения текущих настроек;
- pentaho_repository - файловая система пользователей;
- pentaho_logs - логи сервера;
- pentaho_tmp - временные файлы сервера.

При повторных запусках образ использует данные из указанным именованых томов.
Таким образов файлы пользователя и текущие настройки сохраняются при перезапуске контейнера.

Для просмотра списка созданных именованных томов используйте команду
```
docker volume ls
```

Если Вы планируете использовать данные настройки на другом сервере необходимо перенести указанные именованые тома.
Одноименненные папки томов располагаются в каталоге `/var/lib/docker/volumes`.```

### Остановка сервиса

 Для остановки сервиса необходимо запустить скрипт  `composeStop.cmd`.

### Дополнительно

- [git-репозиторий сборки образа Pentaho](https://github.com/Flexberry/dockerfiles/blob/master/pentaho/README_ru.md);
- [git-репозиторий скриптов запуска образа](https://github.com/Flexberry/NewPlatform.Flexberry.Analytics/tree/master/pentaho);
- [репозиторий docker-образа](https://hub.docker.com/r/flexberry/pentaho/).
