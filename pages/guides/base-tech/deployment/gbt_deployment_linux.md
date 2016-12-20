---
title: Разворачивание решений в Linux
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_deployment_linux.html
folder: base-tech/deployment
lang: ru
---

## Краткое описание

В группе компаний ИВС используются следующие базовые дистрибутивы ОС Linux:

* Сертифицированные
  * [Альт Линукс СПТ 6.0](http://www.altlinux.ru/products/altlinux-spt-fstec/) 
  * [Альт Линукс СПТ 7.0](https://www.basealt.ru/products/alt-spt/)
  * [Astra Linux Special Edition релиз "Смоленск"](http://astralinux.com/smolensk.html)
* Несертифицированные
  * [Альт Линукс 7-й платформы](http://www.altlinux.ru/products/7th-platform/centaurus/)
  * [Альт Линукс 8-й платформы](https://www.basealt.ru/products/alt-server/)
  * [Ubuntu](http://ubuntu.ru/)
  * [CentOS](https://www.centos.org/)
  
  Для снижения зависимости от конкретного дичтрибутива и пакетной базы при разворачивании решений максимально широко используются контейнеры [Docker](https://www.docker.com/) позволяющие избежать больших накладных расходов при разворачивании серверных и клиентских решений на широком классе дистрибутивов.
  В качестве образов контейнеров используются как [общедоступные контейнеры](https://hub.docker.com/),так и контейнеры, разработанные в ГК ИВС. Разрабатываемый контейнеры, как правило, базируются на дистрибутивах ALTLinux сертифицированной 7-й платформы и 8-й платформы общего пользования.      

## Ссылки на материалы для изучения
[Запуск ASP.NET-приложений на платформе Linux](https://www.ibm.com/developerworks/ru/library/l-Mono_10/)
[Серия статей: Работаем с Mono](https://www.ibm.com/developerworks/ru/library/l-Mono_13/)

### Лекции, курсы, презентации, видео
* [Обучающие курсы и семинары для IT-специалистов](https://www.basealt.ru/courses/training/)
* [Обзор ALT Linux 7](https://www.youtube.com/watch?v=uQrwD-OR-V4)
* [Linux - Установка Ubuntu рядом с Windows. (BIOS+MBR) ](https://www.youtube.com/watch?annotation_id=annotation_3417824783&feature=iv&src_vid=nGfkdBR2VVo&v=OmMkAGuZxCE)

### Рекомендованные книги

* [ALT Linux снаружи. ALT Linux изнутри](http://www.altlinux.ru/products/books/inside-outside/)
* [Linux: Полное руководство / Д.Н. Колисниченко, Питер В. Аллен (PDF)](http://www.softlabirint.ru/book/18371-linux-polnoe-rukovodstvo-dn-kolisnichenko-piter-v-allen-pdf.html)
* [Даниэл Дж. Баррет - Linux основные команды карманный справочник](http://www.proklondike.com/books/linux/barret-linux-osnovnye-komandy.html)
* [Лучшие книги о Linux](https://losst.ru/luchshie-knigi-o-linux)

## Программное обеспечение

## Лабораторные работы и практические задания

## Возможности по сертификации
* [Сертификация специалистов ALTLinux](http://www.altlinux.ru/partners/certified-specialist/)
* [Сертифицированный Центр компетенции по обучению Astra Linux](http://www.astralinux.com/uchebnyi/centr-obucheniya.html)




## Перейти
* [Разворачивание решений в контейнере Docker](gbt_deployment_docker.html)
* [Главная страница курса](gbt_landing-page.html)
