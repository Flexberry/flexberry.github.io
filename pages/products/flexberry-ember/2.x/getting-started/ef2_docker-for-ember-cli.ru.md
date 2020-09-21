---
title: Ember-CLI via Docker
keywords: ember, ember-cli, docker, build
tags: [EmberJS]
sidebar: flexberry-ember-2_sidebar
toc: true
permalink: ru/ef2_docker-for-ember-cli.html
lang: ru
summary: Описание инструментария разработки без необходимости установки всего его многообразия на компьютер разработчика (обязательно иметь установленный Docker). 
---

## Требования к установленному на компьютере ПО

Для использования Docker-образов требуется установить [Docker](https://www.docker.com/). На сайте производителя имеются различные редакции, в том числе и для Windows (рекомендуется использовать `Windows 10`). Для того, чтобы продуктивно использовать `Docker` (не только для ember-разработки) рекомендуется [ознакомиться с данным инструментом контейнеризации подробнее](gbt_deployment_docker.html).

### Связывание файловой системы вашей операционной системы и Docker-контейнера

Docker-контейнер может использовать части файловой системы хост-машины. Делается это при помощи конструкции `-v /host/folder:/image/folder` при запуске контейнера. В Docker для Windows предварительно требуется настроить доступ к файловой системе службе Docker в настройках (`Settings...` -> `Shared Drives`).

### Связывание портов вашей операционной системы и Docker-контейнера

Docker-контейнер может "прокидывать" порты из контейнера в хост-машину. Делается это при помощи `-p 4200:4200` при запуске контейнера.

## Docker-образ для Ember-CLI

Для работы с `Ember-CLI` можно воспользоваться самым популярным Docker-образом - [danlynn/ember-cli](https://hub.docker.com/r/danlynn/ember-cli/), который опубликован на [hub.docker.com](https://hub.docker.com). Данный образ реализован для различных версий Ember-CLI, например для версии `2.4.3` образ будет называться: `danlynn/ember-cli:2.4.3`. **Следует учесть, что для разных версий образов есть небольшие различия в команде запуска Docker-контейнера (см. [документацию к образу](https://hub.docker.com/r/danlynn/ember-cli/)).**

## Запуск Docker-контейнера с Ember-CLI

Для запуска образа следует выполнить 2 шага в интерфейсе командной строки (для `Windows` рекомендуется `PowerShell`):  
1) Скачать нужный образ - `danlynn/ember-cli:2.4.3`

``` bash
docker pull danlynn/ember-cli:2.4.3
```

2) Перейти в каталог с ember-приложением
  
``` bash
cd <путь до каталога>`
```

3) Запустить Docker-контейнер

Вариант для `Windows PowerShell`
``` bash
docker run --rm -ti -v ${PWD}:/myapp --entrypoint=/bin/bash -p 4200:4200 -p 49153:49153 -p 7357:7357 danlynn/ember-cli:2.4.3
```

Вариант для `Linux bash`

``` bash
docker run --rm -ti -v $(pwd):/myapp --entrypoint=/bin/bash -p 4200:4200 -p 49153:49153 -p 7357:7357 danlynn/ember-cli:2.4.3
```

4) В запустившемся терминале выполняем все нужные команды Ember-CLI:  
`npm i`, `bower i --allow-root`, `ember b`, `ember s` и т.д..
