---
title: Разворачивание решений в контейнерах Docker
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_deployment_docker.html
folder: base-tech/deployment
lang: ru
---

## Описание

docker это легковесная система контейнеризации приложений с открытым исходным кодом.
На настоящий момент в существует несколько сотен типов Linux-дистрибутивов. Каждый тип Linux-дистрибутива имеет свои особенности в установке и настройке приложений:
* Различия в процедуре установки пакетов (apt-get, yum, dnf, ...).
* Различия пакетной базы (нужный пакет может отсутствовать).
* Название пакета, его версия и механизм установки могут отличаться.
* Пакеты собраны с различными опциями и расширениями.
* Файлы конфигурации располагаются в различных местах.
* ...

Все это создает серъезные проблемы при переносе работающего приложения с одного типа Linux-дистрибутива на другой.

Docker решает эту проблему путем создания образа файловой системы, включающего кроме самого приложения все необходимые библиотеки и файлы конфигурации. Таким образом установив образ docker-приложения, созданный для одного типа дистрибутива (например Ubuntu) Вы можете на его основе запустить контейнер выполняющий приложение на другом дистрибутиве (например ALTLinux).

В отличие от других систем виртуализации (например Virtuozzo/OpenVZ) docker-приложения одного типа дистрибутива за счет использования каскадно-объединенного (послойного) монтирования используют одни и те же библиотеки, файлы конфигурации базового дистрибутива. Это позволяет оптимально использовать файловую систему, снизить объем ресурсов, потребляемых контнейнеров и сократить время его запуска.
Этот же механизм позволяет довольно просто конструировать собственные образы путем расширения (наследования) существующих образов создавая таким образом дерево образов дистрибутивов.

Несомненным достоинством docker является наличие на настоящий момент сотен официальных и до полумиллиона неофициальных публичных образов на [hub-портале](https://hub.docker.com/). Найдя необходимый образ приложения Вы можете его установить на свой компьютер и на его основе запускать контейнеры приложения не заботясь о несовместимости Linux-дистрибутивов.

Кроме этого Вы можете расширив полученный образ создать собственный образ приложения добавив в него свои файлы конфигурации и дополнительные пакеты.

Например, если Вам необходимо запустить Вашу MPI-программу, Вы можете скачать образ MPI-компилятора от CentOS:

```sh
# docker pull nersc/centos-mpi
```

Создать собственный подобраз, воспользовавшись инструкцией, приведенной на странице [образа](https://hub.docker.com/r/nersc/centos-mpi/) и запускать Ваше MPI-приложение с различными параметрами, на различных Linux-дистрибутивах.

Кроме этого при запуске контейнера Вы можете передавать в него различные параметры, привязывать TCP-порты приложения контейнера к различных портам HOST-системы, монтировать каталоги HOST-системы в подкаталоги контейнера. 

Все эти механизмы позволяют запускать в рамках одной ОС:
* несколько  контейнеров с одним приложением одной версии;
* несколько контейнеров версий различных версий одного приложения;
* приложения, конфликтующих по TCP-портам или файловой системе;
* рой (swarm) контейнеров, поддерживающих определенный сервис;
* и т.п.

### Запуск контейнера Apache: kafnevod/altlinux.p8-apache2 

Допустим Вам необходимо запустить Ember-приложение под WEB-сервером Apache2. 
Вам достаточно скачать образ сервера для дистрибутива ALTLinux P8:

```sh
# docker pull kafnevod/altlinux.p8-apache2
```

Запуск контейнера производится командой:

```sh
# docker run -d -p 80:80 --name=apache2 kafnevod/altlinux.p8-apache2
```
Контейнер запускается в режиме демона (-d) с именем (--name) apache2 и с привязкой порта (-p) 80 контейнера к порту 80 HOST-системы.

Для проверки работоспособности контейнера запустите команду:

```sh
# docker ps -f name=apache2
```

Команда отобразит строку типа:

```
CONTAINER_ID IMAGE                          COMMAND CREATED     STATUS              PORTS                NAMES
688acc21b881 kafnevod/altlinux.p8-apache2   httpd2  5 seconds   Up 4 seconds        0.0.0.0:80->80/tcp   apache2
```

Столбец IMAGE содержит имя образа контейнера.
Столбец NAMES - имя контейнера.
Столбец CONTAINER ID - идентификатор (UID) контейнера.
Столбец PORTS список привязанны портов контейнера с портам HOST-системы.
Столбец STATUS - статус контейнера (start, up, restart, ...).
Столбец CREATED - время с момента запуска контейнера. 
Столбец COMMAND - имя и параметры запуска корневого процесса контейнера.

Используя параметр exec команды docker Вы можете запустить собственный процесс в рамках работающего контейнера -  например, команду pstree (дерево процессов):

```sh
# docker exec apache2 pstree
```
Команда выведет текущее дерево процессоа контейнера:

```

httpd2---2*[httpd2---26*[{httpd2}]]

```

Вы также можете запустить bash-интепретатор
```sh
docker exec -it apache2 bash
```

и в интерактивном режиме поработать в рамках контейнера.
Например динамически просматривать лог-файлы apache2-сервера командой:

```sh
# tail -f /var/log/httpd2/*log
```
### Проверка работы контейнера Apache: kafnevod/altlinux.p8-apache2 

Для проверки работы контейнера наберите в строке браузера URL: http://localhost/.
В окне браузера отобразится текст: **It works!**

> Здесь и далее рассматривается случай, когда контейнер запускается на компьютере пользователя. Если Вы запускаете контейнер на удаленном сервере замените имя сервера в URL с localhost на IP-адрес сервера или имя домена под которым рабоает сервер. Для тестирования Вы можете указать один или несколько доменов сервера в файле /etc/hosts. 

> Если Ваш браузер работает через proxy-сервер, опишите тестируемые адреса и домены сервера в поле _"не использовать proxy-сервер для"_ окна настройки proxy-сервера браузера.

Если Вы в контейнере запустили вышеуказанную команду просмотра логов **tail**, она выведет строки типа:

```
==> /var/log/httpd2/access_log <==
172.18.0.1 - - [26/Dec/2016:10:25:17 +0000] "GET / HTTP/1.1" 200 45
```

### Запуск собственного виртуального сайта в рамках контейнера Apache

Файл описания виртуальных сайтов apache2-сервера контейнера находится в файле /conf/vhosts.conf образа.
Для запуска собственного виртуального сайта (например demo) Вы должны 
* Поместить дерево сайта в отдельный каталог (например /var/www/vhosts/demo/) HOST-системы.
Пусть это будет простая тестовая страница /var/www/vhosts/demo/index.html:

```
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
  <head>
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
  <title>Тестовая demo-страница для проверки работоспособности виртуального хоста в контейнере kafnevod/altlinux.p8-apache2</title>
  </head>
  <body>
    <b><i>Виртуальный хост demo.local контейнера kafnevod/altlinux.p8-apache2 работает!</i></b>
  </body>
</html>
```

* Опишите конфигурацию этого виртуального хоста в файле конфигурации vhosts.conf отдельного каталога. Например /etc/docker/apache2/conf/vhosts.conf:

```
NameVirtualHost *:80
<VirtualHost *:80>
        DocumentRoot "/var/www/vhosts/demo/"
        ServerName demo.local
        ErrorLog "/var/log/httpd2/demo_log"
        CustomLog "/var/log/httpd2/demo_log" common
</VirtualHost>
```

* Удалите работающий контейнер, если он у Вас запущен:

```sh
# docker rm -f apache2
```

* Запустите новый контейнер командой:

```sh
docker run -d \
  -p 80:80 \
  --name=apache2Virt \
  -v /etc/docker/apache2/conf/:/conf/ \
  -v /var/www/vhosts/:/var/www/vhosts/ \
  kafnevod/altlinux.p8-apache2
```

Параметры -v монтируют каталог конфигурации /conf/ и каталог виртуальных сайтов /var/www/vhosts/ контейнера на каталоги /etc/docker/apache2/conf/, /var/www/vhosts/ HOST-системы.

* Опишите виртуальный домен в файле /etc/hosts:

```
127.0.0.1 demo.local ... localhost
```

>Если Вы запускаете контейнер на удаленном сервере, то вместо адреса 127.0.0.1 в файле /etc/hosts хоста где Вы запускаете браузер вы должны добавить строчку:

```
ip-адрес_сервера demo.local
```

>Если Вы запускаете виртуальный сайт для внешнего доступа, то вместо правки файла /etc/hosts зарегистритуйте домен (например demo.ics.perm.ru) у провайдера домена и укажите его в описании конфигурации файла /etc/docker/apache2/conf/vhosts.conf.

* Для проверки работы виртуального сайта наберите в строке браузера URL: http://demo.local/.
В окне браузера отобразится текст: **_Виртуальный хост demo.local контейнера kafnevod/altlinux.p8-apache2 работает!_**


### Установка и запуск контейнера Mono/.NET-приложений kafnevod/altlinux.p8-apache2-mono4.6.2.7

Для запуска mono-приложений загрузите образ kafnevod/altlinux.p8-apache2-mono4.6.2.7:
```sh
# docker pull kafnevod/altlinux.p8-apache2-mono4.6.2.7
```

Обратите внимание - так как образ kafnevod/altlinux.p8-apache2-mono4.6.2.7 является расширением образа kafnevod/altlinux.p8-apache2, то те слои файловой системы, которые уже присутствуют в родительском образе в дочернем образе не скачиваются.

```
6ed0c8c1348d: Already exists 
3083620832f3: Already exists 
03daa402b3d6: Already exists 
b867bce38065: Pulling fs layer                                                                                                                          
```

При запуске контейнера дочернего образа он использует слои файловой системы родительского образа.
Образ содержит тестовый виртуальный сайт, доступный по TCP-порту 880.
Для проверки тестового сайта запустите контейнер загруженного образа:

```sh
# docker run -d -p 880:880 --name=apache2Mono kafnevod/altlinux.p8-apache2-mono4.6.2.7
```

Проверьте его статус:

```sh
# docker ps -a -f name=apache2Mono
CONTAINER_ID IMAGE                                    COMMAND CREATED     STATUS      PORTS                          NAMES
38c7e2a7a8aa kafnevod/altlinux.p8-apache2-mono4.6.2.7 httpd2 2minutes ago Up 2minutes 80/tcp, 0.0.0.0:880->880/tcp   apache2Mono
```

Наберите в браузере URL виртуального сайта с тестовым Mono-приложением http://localhost:880/. В браузере отобразиться результат работы тестового mono-приложения - клавиша **Click me!**.

### Запуск собственного виртуального Mono/.NET сайта в рамках контейнера Apache-Mono

Запуск собственного виртуального Mono/.NET сайта аналогичен запуску [виртуального сайта Apache2-сервера](./#Запуск-собственного-виртуального-сайта-в-рамках-контейнера-apache):

* Поместите деревов сайта в отдельный каталог (например /var/www/vhosts/myMonoApp/) HOST-системы.
* Скопируйте файл конфигурации виртуального хоста Mono-приложения из работающего контейнера:

```
docker cp apache2Mono:/conf/vhosts.conf /etc/docker/apache2/conf/vhosts.conf

```

* Допишите конфигурацию Вашего виртуального хоста в скопированный файл конфигурации /etc/docker/apache2/conf/vhosts.conf воспользовавшись шаблоном:

```
Listen 881
NameVirtualHost *:881

<VirtualHost *:881>
  ServerName myMonoApp.local
  MonoServerPath test.local "/usr/bin/mod-mono-server4"
  MonoDebug myMonoApp.local true
  MonoSetEnv myMonoApp.local MONO_IOMAP=all
  MonoApplications myMonoApp.local "/:/var/www/vhosts/myMonoApp"
  AddDefaultCharset utf-8
  <Location "/">
    Allow from all
    Order allow,deny
    MonoSetServerAlias myMonoApp
    SetHandler mono
    #SetOutputFilter DEFLATE
  </Location>
  ErrorLog /var/log/httpd2/myMonoApp_error_log
  LogLevel debug
  CustomLog /var/log/httpd2/myMonoApp_access_log common
</VirtualHost>
```

* Замените, если необходимо, порт 881 на номер порта по которому будет доступен Ваш сайт, указав в дальнейшем этот порт во флаге -p при запуске контейнера. Домен myMonoApp.local на Ваш домен приложения, имя myMonoApp на имя Вашего mono-приложения.

* Перезапустите контейнер :

```sh
# docker rm -f apache2Mono
# docker run -d \
  -p 880:880 \
  -p 881:881 \
  --name=apache2MonoVirt \
  -v /etc/docker/apache2/conf/:/conf/ \
  -v /var/www/vhosts/:/var/www/vhosts/ \
  kafnevod/altlinux.p8-apache2-mono4.6.2.7
```

* Обратитесь в браузере к корневой странице виртуального сайта по URL: http://localhost:881/. 



## Ссылки на материалы для изучения

### Лекции, курсы, презентации, видео

* [Docker Video Tutorials](https://www.docker.com/products/resources/video-tutorials)
* [Docker YouTube Channel](https://www.youtube.com/user/dockerrun)
* [5 must-watch Docker videos](https://opensource.com/business/15/5/must-watch-docker-videos)
* [Видео докладов с Docker митапа](https://habrahabr.ru/company/badoo/blog/304702/)
* [Docker в банке. Видео с лекции Александра Тарасова из Альфа-Банка](https://habrahabr.ru/company/jugru/blog/264669/)
* [ 350+ полезных ресурсов, книг и инструментов для работы с Docker](https://habrahabr.ru/company/1cloud/blog/275015/)
* [Get started with Docker](https://docs.docker.com/engine/getstarted/)
* [The Docker commands](https://docs.docker.com/engine/reference/commandline/)
* [Docker run reference](https://docs.docker.com/engine/reference/run/)


### Рекомендованные книги

* [The Docker Book. "James Turnbull" November 26, 2016](https://www.dockerbook.com/TheDockerBook_sample.pdf)



## Программное обеспечение

* [Engine user guide](https://docs.docker.com/engine/installation/)
* [Install Docker from binaries](https://docs.docker.com/engine/installation/binaries/)

## Лабораторные работы и практические задания


## Перейти
* [Главная страница курса](gbt_landing-page.html)
