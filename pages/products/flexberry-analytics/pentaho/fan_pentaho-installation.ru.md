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

Для запуска образа `Pentaho` требуется иметь установленное ПО [Docker](https://docs.docker.com). Для пользователей ОС Windows есть соответствующая версия [Docker for Windows](https://docs.docker.com/docker-for-windows/install/).

Образ `Pentaho` может быть запущен в двух режимах:
- сервис docker compose;
- сервис docker swarm

Режим запуска мультиконтейнерных приложений `docker-compose` обеспечивает удобный запуск образа на одном сервере, но для ряда дистрибутивов требует предварительной установки `docker-compose`,
так как команда  не входит в стандартный набор команд docker.
Установка `docker-compose` описана на странице [http://docker.crank.ru/docs/docker-compose/install-compose/](http://docker.crank.ru/docs/docker-compose/install-compose/).

Режим запуска в кластерном режиме обеспечивает удобный запуск образа на кластере серверов (в том числе на кластере из одного сервера), но требует предварительной инициализации кластера.
Инициализации кластера описана на странице [https://docs.docker.com/engine/reference/commandline/swarm_init/](https://docs.docker.com/engine/reference/commandline/swarm_init/).

Оба режима запуска используют один и тот же файл конфигурации `.env` и одни и те же именованные тома.
Использование именованных томов обеспечивает сохранение всех настроек и текущего состояния `pentaho` при перезапуске контейнера.

### PowerShell-скрипт установки и запуск Pentaho

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

Если в Вашей системе эти порты уже заняты Вы можете переопределить указанные переменные в файле `.env`, указав свободные порты.

### Получение образа

Если Вы запускаете образ в режиме `docker compose` Вам необходимо предварительно скачать образ с репозитория скриптом `pull.cmd`.

Если образ в репозитории обновился для его использования необходимо повторно запустить команду `pull.cmd`.

При запуске образа в режиме `docker swarm` данный шаг можно пропустить, так как образ скачивается автоматически при первоначальном запуске и
обновлении образа в репозитории.

### Запуск в режиме docker compose

 Запуск в режиме docker compose обеспечивается docker-скриптом `composeStart.cmd`.

 Инициализация сервиса происходит в течении 30-60 секунд.

 Останов сервиса обеспечивает скриптом `composeStop.cmd`.

### Запуск в режиме docker swarm

 Запуск в режиме docker swarm обеспечивается docker-скриптом `swarmStart.cmd`.

 Инициализация сервиса происходит в течении 30-60 секунд.

 Останов сервиса обеспечивает скриптом `swarmStop.cmd`.
 
 ### Проверка успешности запуска Pentaho

* В PowerShell консоли нужно ввести 
```powershell
`docker ps`
```
В результате будет выдан список запущенных `docker образов` (среди них должен быть `flexberry/pentaho:latest`).
* Административное приложение с конфигурацией по умолчанию доступно по адресу <http://localhost:8080>. Логин и пароль: `admin`, `password` (при переходе на промышленную эксплуатацию обязательно нужно сменить).

При запуске `Pentaho` в режиме `docker swarm` проверить работоспособность сервиса также можно командой
```powershell
docker service ls
ID    NAME            MODE         REPLICAS IMAGE                      PORTS
...   pentaho_pentaho replicated   1/1      flexberry/pentaho:latest   ...
...
```
В столбце `REPLICAS` должно быть значение `1/1`.

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
Одноименненные папки томов располагаются в каталоге `/var/lib/docker/volumes`.

### Дополнительно

- [git-репозиторий сборки образа Pentaho](https://github.com/Flexberry/dockerfiles/blob/master/pentaho/README_ru.md);
- [git-репозиторий скриптов запуска образа](https://github.com/Flexberry/NewPlatform.Flexberry.Analytics/tree/master/pentaho);
- [репозиторий docker-образа](https://hub.docker.com/r/flexberry/pentaho/).

