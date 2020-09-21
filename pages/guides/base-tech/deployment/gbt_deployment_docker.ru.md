---
title: Разворачивание решений в контейнерах Docker
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_deployment_docker.html
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

Все это создает серьезные проблемы при переносе работающего приложения с одного типа Linux-дистрибутива на другой.

Docker решает эту проблему путем создания образа файловой системы, включающего кроме самого приложения все необходимые библиотеки и файлы конфигурации. Таким образом, установив образ docker-приложения, созданный для одного типа дистрибутива (например, Ubuntu), Вы можете на его основе запустить контейнер, выполняющий приложение на другом дистрибутиве (например, ALTLinux).

В отличие от других систем виртуализации (например Virtuozzo/OpenVZ) docker-приложения одного типа дистрибутива за счет использования каскадно-объединенного (послойного) монтирования используют одни и те же библиотеки, файлы конфигурации базового дистрибутива. Это позволяет оптимально использовать файловую систему, снизить объем ресурсов, потребляемых контнейнеров и сократить время его запуска.
Этот же механизм позволяет довольно просто конструировать собственные образы путем расширения (наследования) существующих образов, создавая таким образом дерево образов дистрибутивов.

Несомненным достоинством docker является наличие на настоящий момент сотен официальных и до полумиллиона неофициальных публичных образов на [hub-портале](https://hub.docker.com/). Найдя необходимый образ приложения, Вы можете его установить на свой компьютер и на его основе запускать контейнеры приложения, не заботясь о несовместимости Linux-дистрибутивов.

Кроме этого Вы можете, расширив полученный образ, создать собственный образ приложения, добавив в него свои файлы конфигурации и дополнительные пакеты.

Например, если Вам необходимо запустить Вашу MPI-программу, Вы можете скачать образ MPI-компилятора от CentOS:

```sh
# docker pull nersc/centos-mpi
```

Создать собственный подобраз, воспользовавшись инструкцией, приведенной на странице [образа](https://hub.docker.com/r/nersc/centos-mpi/) и запускать Ваше MPI-приложение с различными параметрами, на различных Linux-дистрибутивах.

Технологию создания собственных docker-образов на основе существующих Вы можете изучить на странице [Build your own images](https://docs.docker.com/engine/tutorials/dockerimages/).

Описанные далее образы созданы на основе базового docker-образа дистрибутива [ALTLinux/BaseALT P8](https://hub.docker.com/r/fotengauer/altlinux-p8/). Кроме этого созданы аналогичные образы на основе дистрибутива [ALTLinux C7](https://hub.docker.com/r/fotengauer/altlinux-p7/):

* [kafnevod/altlinux.p7-apache2](https://hub.docker.com/r/kafnevod/altlinux.p7-apache2/) - образ Apache2 сервера 
* [flexberry/altlinux.p7-apache2-mono4.6.2.7](https://hub.docker.com/r/flexberry/altlinux.p7-apache2-mono4.6.2.7/) - образ Apache2/Mono4  сервера.

Эти образы используют сертифицированный библиотеки дистрибутива ALTLinux C7 и при необходимости могут быть использованы вместо описываемых в данном докумене образов [kafnevod/altlinux.p8-apache2](https://hub.docker.com/r/kafnevod/altlinux.p8-apache2/), [flexberry/altlinux.p8-apache2-mono4.6.2.7](https://hub.docker.com/r/flexberry/altlinux.p8-apache2-mono4.6.2.7/) собранных на дистрибутива ALTLinux/BaseALT P8.

При запуске контейнера Вы можете передавать в него различные параметры, привязывать TCP-порты приложения контейнера к различных портам HOST-системы, монтировать каталоги HOST-системы в подкаталоги контейнера. 

Все эти механизмы позволяют запускать в рамках одной ОС:

* несколько  контейнеров с одним приложением одной версии;
* несколько контейнеров версий различных версий одного приложения;
* приложения, конфликтующих по TCP-портам или файловой системе;
* рой (swarm) контейнеров, поддерживающих определенный сервис;
* и т.п.

### Образ WEB-сервера Apache2: kafnevod/altlinux.p8-apache2 

#### Установка образа и запуск контейнера

Допустим, Вам необходимо запустить Ember-приложение под WEB-сервером Apache2. 
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
Столбец PORTS список привязанных портов контейнера с портам HOST-системы.
Столбец STATUS - статус контейнера (start, up, restart, ...).
Столбец CREATED - время с момента запуска контейнера. 
Столбец COMMAND - имя и параметры запуска корневого процесса контейнера.

Используя параметр exec команды docker, Вы можете запустить собственный процесс в рамках работающего контейнера -  например, команду pstree (дерево процессов):

```sh
# docker exec apache2 pstree
```
Команда выведет текущее дерево процессов контейнера:

```

httpd2---2*[httpd2---26*[{httpd2}]]

```

Вы также можете запустить bash-интепретатор

```sh
# docker exec -it apache2 bash
```

и в интерактивном режиме поработать в рамках контейнера.
Например, динамически просматривать лог-файлы apache2-сервера командой:

```sh
# tail -f /var/log/httpd2/*log
```

#### Проверка работы контейнера

Для проверки работы контейнера наберите в строке браузера URL: http://localhost/.
В окне браузера отобразится текст: **It works!**

> Здесь и далее рассматривается случай, когда контейнер запускается на компьютере пользователя. Если Вы запускаете контейнер на удаленном сервере замените имя сервера в URL с localhost на IP-адрес сервера или имя домена под которым рабоает сервер. Для тестирования Вы можете указать один или несколько доменов сервера в файле /etc/hosts. 

> Если Ваш браузер работает через proxy-сервер, опишите тестируемые адреса и домены сервера в поле _"не использовать proxy-сервер для"_ окна настройки proxy-сервера браузера.

Если Вы в контейнере запустили вышеуказанную команду просмотра логов **tail**, она выведет строки типа:

```
==> /var/log/httpd2/access_log <==
172.18.0.1 - - [26/Dec/2016:10:25:17 +0000] "GET / HTTP/1.1" 200 45
```

#### Запуск собственного виртуального сайта в рамках контейнера 

Файл описания виртуальных сайтов apache2-сервера контейнера находится в файле /conf/vhosts.conf образа.
Для запуска собственного виртуального сайта (например demo) Вы должны:
* Поместить дерево сайта в отдельный каталог (например /var/www/vhosts/demo/) HOST-системы.
Пусть это будет простая тестовая страница /var/www/vhosts/demo/index.html:

```html
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

* Описать конфигурацию этого виртуального хоста в файле конфигурации vhosts.conf отдельного каталога. Например, /etc/docker/apache2/conf/vhosts.conf:

```text
NameVirtualHost *:80
<VirtualHost *:80>
        DocumentRoot "/var/www/vhosts/demo/"
        ServerName demo.local
        ErrorLog "/var/log/httpd2/demo_log"
        CustomLog "/var/log/httpd2/demo_log" common
</VirtualHost>
```

* Удалить работающий контейнер, если он у Вас запущен:

```sh
# docker rm -f apache2
```

* Запустить новый контейнер командой:

```sh
# docker run -d \
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

>Если Вы запускаете контейнер на удаленном сервере, то вместо адреса 127.0.0.1 в файле /etc/hosts хоста, где Вы запускаете браузер, вы должны добавить строчку:

```
ip-адрес_сервера demo.local
```

>Если Вы запускаете виртуальный сайт для внешнего доступа, то вместо правки файла /etc/hosts зарегистритуйте домен (например demo.ics.perm.ru) у провайдера домена и укажите его в описании конфигурации файла /etc/docker/apache2/conf/vhosts.conf.

#### Проверка работы виртуального сайта в контейнере

Для проверки работы виртуального сайта наберите в строке браузера URL: http://demo.local/.
В окне браузера отобразится текст: **_Виртуальный хост demo.local контейнера kafnevod/altlinux.p8-apache2 работает!_**

### Образ сервера Mono/.NET-приложений flexberry/altlinux.p8-apache2-mono4.6.2.7 

#### Установка образа и запуск контейнера 

Для запуска mono-приложений загрузите образ flexberry/altlinux.p8-apache2-mono4.6.2.7:

```sh
# docker pull flexberry/altlinux.p8-apache2-mono4.6.2.7
```

Обратите внимание - так как образ flexberry/altlinux.p8-apache2-mono4.6.2.7 является расширением образа kafnevod/altlinux.p8-apache2, то те слои файловой системы, которые уже присутствуют в родительском образе в дочернем образе, не скачиваются.

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
# docker run -d -p 880:880 --name=apache2Mono flexberry/altlinux.p8-apache2-mono4.6.2.7
```

#### Проверка работы контейнера

Проверьте статус контейнера:

```sh
docker ps -a -f name=apache2Mono
CONTAINER_ID IMAGE                                    COMMAND CREATED     STATUS      PORTS                          NAMES
38c7e2a7a8aa flexberry/altlinux.p8-apache2-mono4.6.2.7 httpd2 2minutes ago Up 2minutes 80/tcp, 0.0.0.0:880->880/tcp   apache2Mono
```

Наберите в браузере URL виртуального сайта с тестовым Mono-приложением http://localhost:880/. В браузере отобразиться результат работы тестового mono-приложения - клавиша **Click me!**.

#### Запуск собственного виртуального Mono/.NET сайта в рамках контейнера 

Запуск собственного виртуального Mono/.NET сайта аналогичен запуску [виртуального сайта Apache2-сервера](#Запуск-собственного-виртуального-сайта-в-рамках-контейнера-apache):

* Поместите деревов сайта в отдельный каталог (например, /var/www/vhosts/myMonoApp/) HOST-системы.
* Скопируйте файл конфигурации виртуального хоста Mono-приложения из работающего контейнера:

```sh
docker cp apache2Mono:/conf/vhosts.conf /etc/docker/apache2/conf/vhosts.conf
```

* Допишите конфигурацию Вашего виртуального хоста в скопированный файл конфигурации /etc/docker/apache2/conf/vhosts.conf, воспользовавшись шаблоном:

```text
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

* Замените, если необходимо, порт 881 на номер порта, по которому будет доступен Ваш сайт, указав в дальнейшем этот порт во флаге -p при запуске контейнера. Домен myMonoApp.local на Ваш домен приложения, имя myMonoApp на имя Вашего mono-приложения.

* Перезапустите контейнер :

```sh
# docker rm -f apache2Mono
# docker run -d \
  -p 880:880 \
  -p 881:881 \
  --name=apache2MonoVirt \
  -v /etc/docker/apache2/conf/:/conf/ \
  -v /var/www/vhosts/:/var/www/vhosts/ \
  flexberry/altlinux.p8-apache2-mono4.6.2.7
```

#### Проверка работы виртуального сайта в контейнере Mono/.NET-приложений

Обратитесь в браузере к корневой странице виртуального сайта по URL: http://localhost:881/. 

### Замечания

После завершения работы контейнера он остается в списке и может быть найден в списке контейнеров командой ps с флагом -a:

```sh
# docker ps -a
```

Если контейнер именованный (при запуска используется флаг --name=имяконтейнера), то повторный запуск контейнера с тем же именем приведет к ошибке.

Для удаления завершенного контейнера испольуйте команду rm:

```sh
# docker rm ID_контейнера_или_его_имя
```

При интенсивном процессе запуска контейнеров (обычно во время тестирования) список остановленных контейнеров может быть достаточно большим и чистка этого списка может создать определенные временные проблемы. 
Если Вы не планируете перезапускать контейнер после его завершения или планируете неоднократно запускать именованный контейнер, используйте флаг --rm после аргумента run команды запуска. В это случае контейнер после завершения автоматически удалится из списка завершенных контейнеров. Например,

```sh
docker run -d -p 80:80 --rm --name=apache2 kafnevod/altlinux.p8-apache2
```


Если Вам необходим автоматический перезапуск контейнера после его (аварийного) останова и автоматический запуск контейнера после перезагрузки системы, используйте флаг --restart=unless-stopped после аргумента run команды запуска контейнера. Например:

```sh
docker run -d -p 80:80 --restart=unless-stopped --name=apache2 kafnevod/altlinux.p8-apache2
```

### docker для Windows

Запуск Docker для Windows 8 и Windows Server 2012 возможен только в виртуальной машине Virtualbox.

В Windows 10 Professional и Windows Server 2016 появилась натиная поддержка Docker.

### Запуск Linux-контейнеров в Windows Server 2016 и Windows 10 Professional

При запуске Linux-контейнеров в HyperV создается виртуальная машина Linux, загружается Linux Moby и в рамках нее запускаются все Linux-контейнеры.

### Запуск Windows-контейнеров в Windows Server 2016 и Windows 10 Professional

Поддерживается [два режима запуска Windows-контейнеров](https://dev-ops-notes.ru/docker/windows-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D1%8B-%D0%B8-docker/):
* контейнер Windows Server -  контейнер запускается в рамках HOST ОС и использует ядро и общие ресурсы HOST-системы.
* Hyper-V контейнер - контейнер запускается в рамках отдельной виртуальной машины Hyper-V.

## Ссылки на материалы для изучения

### Лекции, курсы, презентации, видео

* [DOCKER: ВВЕДЕНИЕ В ТЕХНОЛОГИИ ВИРТУАЛИЗАЦИИ](https://dev-ops-notes.ru/docker/docker-%D0%B2%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B2-%D1%82%D0%B5%D1%85%D0%BD%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D0%B8-%D0%B2%D0%B8%D1%80%D1%82%D1%83%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8/)
* [DOCKER: ПРОВЕРКА КОНТЕЙНЕРОВ НА БЕЗОПАСНОСТЬ](https://dev-ops-notes.ru/docker/docker-%D0%BF%D1%80%D0%BE%D0%B2%D0%B5%D1%80%D0%BA%D0%B0-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%BE%D0%B2-%D0%BD%D0%B0-%D0%B1%D0%B5%D0%B7%D0%BE%D0%BF%D0%B0%D1%81%D0%BD%D0%BE%D1%81/)


#### Docker для Linux

* [Docker Video Tutorials](https://www.docker.com/products/resources/video-tutorials)
* [Docker YouTube Channel](https://www.youtube.com/user/dockerrun)
* [5 must-watch Docker videos](https://opensource.com/business/15/5/must-watch-docker-videos)
* [Видео докладов с Docker митапа](https://habrahabr.ru/company/badoo/blog/304702/)
* [Docker в банке. Видео с лекции Александра Тарасова из Альфа-Банка](https://habrahabr.ru/company/jugru/blog/264669/)
* [ 350+ полезных ресурсов, книг и инструментов для работы с Docker](https://habrahabr.ru/company/1cloud/blog/275015/)
* [Get started with Docker](https://docs.docker.com/engine/getstarted/)
* [The Docker commands](https://docs.docker.com/engine/reference/commandline/)
* [Docker run reference](https://docs.docker.com/engine/reference/run/)
* Серия Play-With:
  - [ Play with Docker](http://play-with-docker.com/);
  - [Play with Docker Classroom](https://training.play-with-docker.com/);
  - [Play with Kubernetes](https://labs.play-with-k8s.com/);
  - [Play with Kubernetes Classroom](https://training.play-with-kubernetes.com/);

#### Docker для Windows
* [WINDOWS КОНТЕЙНЕРЫ И DOCKER](https://dev-ops-notes.ru/docker/windows-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D1%8B-%D0%B8-docker/)
* [РАБОТА С КОНТЕЙНЕРАМИ В WINDOWS И DOCKER](https://dev-ops-notes.ru/docker/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B0%D0%BC%D0%B8-%D0%B2-windows-%D0%B8-docker/)
* [Docker: Windows и Linux в контейнерах на одной машине](https://blog.amartynov.ru/docker-windows-%D0%B8-linux-%D0%B2-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B0%D1%85-%D0%BD%D0%B0-%D0%BE%D0%B4%D0%BD%D0%BE%D0%B9-%D0%BC%D0%B0%D1%88%D0%B8%D0%BD%D0%B5/)

### Рекомендованные книги

* [The Docker Book. "James Turnbull" November 26, 2016](https://www.dockerbook.com/TheDockerBook_sample.pdf)

## Программное обеспечение

* [Engine user guide](https://docs.docker.com/engine/installation/)
* [Install Docker from binaries](https://docs.docker.com/engine/installation/binaries/)

## Лабораторные работы и практические задания


## Перейти
* [Главная страница курса](gbt_landing-page.html)
