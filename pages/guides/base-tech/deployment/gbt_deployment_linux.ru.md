---
title: Разворачивание решений в Linux
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_deployment_linux.html
folder: guides/base-tech/deployment/
lang: ru
---

## Краткое описание

В настоящее время в России используются следующие базовые дистрибутивы ОС Linux:

* Сертифицированные
  * [Альт Линукс СПТ 6.0](http://www.altlinux.ru/products/altlinux-spt-fstec/) 
  * [Альт Линукс СПТ 7.0](https://www.basealt.ru/products/alt-spt/)
  * [Astra Linux Special Edition релиз "Смоленск"](http://astralinux.com/smolensk.html)
  * [РОСА ХРОМ](https://www.rosalinux.ru/pocaxpom/)
* Несертифицированные
  * [Альт Линукс 7-й платформы](http://www.altlinux.ru/products/7th-platform/centaurus/)
  * [Альт Линукс 8-й платформы](https://www.basealt.ru/products/alt-server/)
  * [ROSA Fresh](https://www.rosalinux.ru/rosa-fresh/)
  * [Astra Linux Common Edition](http://www.astra-linux.com/products/module-positions.html)
  * [Ubuntu](http://ubuntu.ru/)
  * [CentOS](https://www.centos.org/)
  * ...
  
  Для снижения зависимости от конкретного дистрибутива и пакетной базы при разворачивании решений максимально широко используются контейнеры [Docker](https://www.docker.com/) позволяющие избежать больших накладных расходов при разворачивании серверных и клиентских решений на широком классе дистрибутивов.
  В качестве образов контейнеров используются как [общедоступные контейнеры](https://hub.docker.com/),так и контейнеры, разработанные для применения в конкретных разрабатываемых проектах. Разрабатываемый контейнеры, как правило, базируются на дистрибутивах ALTLinux сертифицированной 7-й платформы и 8-й платформы общего пользования. Разворачивание решений в контейнерной системе docker описано в [следующем разделе](gbt_deployment_docker.html). 
  
## Установка и настройки ОС Linux

### Описание
Для установки и настройки Linux Вам необходимо выбрать дистрибутив, скачать его образ, записать его на физический носитель (flesh-карту или CD/DVD) и воспользовавшись интсрукцией установить его на физический раздел диска или в виртуальную машину HyperV, VirtualBox, VMWare, ... В таблице приведен список дистрибутивов, ссылки на страницы загрузки образа, инструкции по записи образа на носитель, установке дистрибутива и текст лицензии.

Дистрибутив | Скачать | Запись | Установка | Лицензия
------------|---------|--------|-----------|---------
ALTLinux P7 | [Скачать](http://www.altlinux.ru/products/7th-platform/kdesktop/)| [Запись](https://www.altlinux.org/Write) | [Установка](https://docs.altlinux.org/ru-RU/kdesktop/7.0.5/html-single/kdesktop/index.html) | [Лицензия](http://www.altlinux.ru/products/7th-platform/kdesktop/kdesktop-license)
BaseALT P8 | [Скачать](https://www.basealt.ru/products/alt-workstation/) | [Запись](https://www.altlinux.org/Write) | [Установка](http://basealt.ru/static/Basealt_Desktop_Установка.pdf) | [Лицензия](https://www.basealt.ru/products/alt-workstation/license/) 
ROSA Fresh | [Скачать](http://mirror.yandex.ru/rosa/rosa2014.1/iso/ROSA.Fresh.R8/) | [Запись](http://wiki.rosalab.ru/ru/index.php/Установка_ROSA_Desktop) | [Установка](http://smotrisoft.ru/ustanovka-rosa-desktop-fresh/) | [Лицензия GPL](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 
Astra Linux Common Edition | [Скачать](http://astralinux.com/download-ce.html) | [Запись](http://astralinux.com//images/doc/AstraLinuxCE_install.pdf) | [Установка](http://astralinux.com/doc.html) | [Лицензия](http://astralinux.com/litsenzionnoe-soglashenie.html) 
Ubuntu | [Скачать](http://ubuntu.ru/get) | [Запись](http://help.ubuntu.ru/wiki/руководство_по_ubuntu_desktop_14_04/получение_ubuntu) | [Установка](http://help.ubuntu.ru/wiki/руководство_по_ubuntu_desktop_14_04/введение) | [Лицензия GPL](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 
CentOS | [Скачать](https://wiki.centos.org/Download) | [Запись](http://serveradmin.ru/ustanovka-centos-7/) | [Установка](http://serveradmin.ru/ustanovka-centos-7/) | [Лицензия GPL и другие](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 

Применение всех перечисленных дистрибутивов физическими лицами не требует приобретения лицензионного соглашения у правообладателя.

Обратите внимание, что для дистрибутивов с лицензией [GNU GPL](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) (ROSA Frash, Ubuntu, CentOS, ...)  при их применении в коммерческих и государственных учреждениях и на предприятиях Российской Федерации необходима лицензия на поддержку дистрибутива и их применение в гос. учреждениях может быть ограничено, так как они не входят в  [Единый реестр российских программ и баз данных](https://reestr.minsvyaz.ru/reestr/?sort_by=date&sort=asc&class[]=54112&name=&owner_status=&owner_name=&set_filter=Y).


### Ссылки на материалы для изучения

#### Лекции, курсы, презентации, видео

* [Обучающие курсы и семинары для IT-специалистов](https://www.basealt.ru/courses/training/)
* [Обзор ALT Linux 7](https://www.youtube.com/watch?v=uQrwD-OR-V4)
* [Linux - Установка Ubuntu рядом с Windows. (BIOS+MBR) ](https://www.youtube.com/watch?annotation_id=annotation_3417824783&feature=iv&src_vid=nGfkdBR2VVo&v=OmMkAGuZxCE)

#### Рекомендованные книги

* [ALT Linux снаружи. ALT Linux изнутри](http://www.altlinux.ru/products/books/inside-outside/)
* [Linux: Полное руководство / Д.Н. Колисниченко, Питер В. Аллен (PDF)](http://www.softlabirint.ru/book/18371-linux-polnoe-rukovodstvo-dn-kolisnichenko-piter-v-allen-pdf.html)
* [Команды Linux «от A до Z», обзор с примерами](http://edu-cisco.org/docs/lp/Linux-handbook-comands-A-Z-SEDICOMM-University.pdf)
* [Даниэл Дж. Баррет - Linux основные команды карманный справочник](http://www.proklondike.com/books/linux/barret-linux-osnovnye-komandy.html)
* [Лучшие книги о Linux](https://losst.ru/luchshie-knigi-o-linux)

### Программное обеспечение

### Лабораторные работы и практические задания

### Возможности по сертификации
* [Сертификация специалистов ALTLinux](http://www.altlinux.ru/partners/certified-specialist/)
* [Сертифицированный Центр компетенции по обучению Astra Linux](http://www.astralinux.com/uchebnyi/centr-obucheniya.html)


## Установка и настройка WEB-сервера apache2

### Описание установки в дистрибутивах семейства ALTLinux

В пользователе в правами root наберите команду:

```sh
# apt-get update
# apt-get install apache2
```

Ответьте Y на запрос об установке зависимых пакетов.
После установки пакета apache2 и зависимых пакетов наберите для автоматического старта сервера Apache :

* для серверных дистрибутивов с системным менеджером SysV:

```sh
# chkconfig --add httpd2
# chkconfig --level 2345 httpd2 on
# service apache2 start
```

* для серверных и десктопных дистрибутивов с системным менеджером Systemd:

```sh
# systemctl enable apache2
# systemctl start apache2
```

По умолчанию сервер apache при ображение на порт 80 по адресу http://localhost/ отображает контент, расположенный в каталоге /var/www/html/. Если Вы планируете поддерживать несколько виртуальных хостов, то Вы можете их сконфигурировать в файле /etc/httpd2/conf/sites-available/vhosts.conf. 

Сервер apache2 поддерживает виртуалтзацию хостов по 

* [портам](https://httpd.apache.org/docs/2.4/vhosts/examples.html#port)
* [доменам](https://httpd.apache.org/docs/2.4/vhosts/examples.html#purename)
* [IP-адресам](https://httpd.apache.org/docs/2.4/vhosts/examples.html#twoips)
* [комбинацией вышеперечисленных вариантов](https://httpd.apache.org/docs/2.4/vhosts/examples.html#ipport).

Код виртуальных хостов, как правило, размещается в подкаталогах каталога /var/www/vhosts/.

После описание виртуальных хостов необходимо активировать их и перегрузить сервер:

```sh
# a2ensite vhosts
# service apache2 restart
```

### Проверка работосособности

После запуска сервера наберите команду:

```sh
# netstat -nlpt | grep httpd2
```

в результате должна отобразиться строка с описанием списка портов, обслуживаемых серверов apache (по умолчанию порт 80):

```
tcp    0      0 :::80   :::*   LISTEN   xxxxx/httpd2
```

xxx - номер процесса httpd2.

Если программа netstat отсутсвует установите ее:

```sh
# apt-get install net-tools
```

или воспользуйтесь командой

```sh
# fuser  -nv tcp 80
```

Данная команда отобразить список корневого и дочерних процессов httpd2, обслуживающих порт 80. Например:

```
80/tcp: 3756  3768  3769  3770  3771  3772
```

Если команды netstat и/или fuser выводят пустой список проверьте наличие ошибок в лог-файле /var/log/httpd2/error_log.

Проверить функционирование apache2-сервера можно обратившись в браузере к адресу http://localhost/ или http://&lt;ip-адрес-внешнего_интерфейса&gt;/ при серверной установке.

Набрав команду

```sh
# tail -f /var/log/httpd2/*log
```

вы можете динамически просматривать логи обращения к страницам сервера apache2, ошибок и предупреждений в работе сервера.  

### Ссылки на материалы для изучения

* Дистрибутив Ubuntu [Apache HTTP Server](http://help.ubuntu.ru/wiki/apache2)
* Дистрибутив РОСА: [Установка Apache, PHP, MySQL ](http://wiki.rosalab.ru/ru/index.php/Установка_Apache,_PHP,_MySQL)
* Дистрибутив CentOS [Установка и настройка Apache, PHP, MySQL на CentOS](https://unixhost.pro/clientarea/knowledgebase/12/Ustanovka-i-nastrojka-Apache-PHP-MySQL-na-CentOS.html)

#### Лекции, курсы, презентации, видео



#### Рекомендованные книги

### Программное обеспечение

### Лабораторные работы и практические задания



## Установка и настройка сервера приложений Mono/.NET

### Описание установки в дистрибутивах семейства ALTLinux

В пользователе в правами root наберите команду:

```sh
# apt-get update
# apt-get install apache2-mod_mono mono4-full
```

Ответьте Y на запрос об установке зависимых пакетов. 

После установки всех пакетов наберите следующие команды:

```sh
# cd /usr/bin
# ln -sf mod-mono-server4  mod-mono-server.sh
# cd /etc/httpd2/conf/mods-available/
# > mono.conf
# echo "LoadModule mono_module modules/mod_mono.so" > mono.load
# a2enmod  mono
```

### Проверка работосособности

Запуск собственного виртуального Mono/.NET сайта аналогичен запуску виртуального сайта Apache2-сервера:

* Поместите деревов сайта в отдельный каталог (например /var/www/vhosts/myMonoApp/) HOST-системы.
* Допишите конфигурацию Вашего виртуального хоста в файл конфигурации /etc/docker/apache2/conf/vhosts.conf воспользовавшись шаблоном:

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

* Перезапустите apache2-сервер:

    * для серверных дистрибутивов с системным менеджером SysV:

    ```
# service apache2 restart
    ```

    * для серверных и десктопных дистрибутивов с системным менеджером Systemd:

    ```
# systemctl restart apache2
    ```

* Обратитесь в браузере к корневой странице виртуального сайта по URL: http://localhost:881/.


### Ссылки на материалы для изучения

#### Лекции, курсы, презентации, видео

#### Рекомендованные книги

### Программное обеспечение

### Лабораторные работы и практические задания

### Ссылки на материалы для изучения
[Запуск ASP.NET-приложений на платформе Linux](https://www.ibm.com/developerworks/ru/library/l-Mono_10/)
[Серия статей: Работаем с Mono](https://www.ibm.com/developerworks/ru/library/l-Mono_13/)


## Перейти
* [Разворачивание решений в контейнере Docker](gbt_deployment_docker.html)
* [Главная страница курса](gbt_landing-page.html)
