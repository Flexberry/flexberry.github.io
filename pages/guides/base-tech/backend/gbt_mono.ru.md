---
title: Mono
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_mono.html
lang: ru
---

**Mono** — проект по созданию полноценного воплощения системы .NET Framework на базе свободного программного обеспечения. Основной разработчик проекта Mono — корпорация Xamarin, ранее Novell. Проект возглавляет Мигель де Икаса, известный разработчик, основатель проекта GNOME. Реализации Mono существуют для следующих операционных систем: Windows, Linux, BSD (FreeBSD, OpenBSD, NetBSD), Solaris, Mac OS X, Apple iOS, Wii. Поддерживаются платформы: s390, SPARC, PowerPC, x86/x86-64, IA-64, ARM, Alpha, MIPS, HP PA.

##  Ссылки на материалы для изучения

* [Documentation](http://www.mono-project.com/docs/)
* [Microsoft .NET Framework](https://flexberry.github.io/ru/gbt_dotnet.html)
* [ASP.NET](https://flexberry.github.io/ru/gbt_aspnet.html)
* [Язык программирования С#](https://flexberry.github.io/ru/gbt_csharp.html)

* [Изучение языка C# для начинающих (Mono)](https://www.youtube.com/watch?v=3FWqP80fNJM&list=PL0lO_mIqDDFU66Cwwctcv1C6VNVpaqHfo)

### Презентация

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/5oLgQc_gnJg?list=PLaKXsWB2aJ1utjsUDJDmZBUW2KylhYsx0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>
</div>

## Отладка проекта

1.Выполнить команду

```
docker pull akosinsky/monodevelop-ember:latest
```

2.Установить [XServer](http://www.netsarang.com/download/down_xmg.html) под windows.

3.Запустить XServer.

4.Выполнить команду

```
docker run -dti --network host -e "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/root/projects/scripts" -e "DISPLAY=00.000.0.00:0.0" -e "http_proxy=http://00.000.000.0:8080" -v с:/catalog:/childrencatalog  developer/monodevelop-ember:latest /usr/bin/mate-terminal --disable-factory
```

5.В открывшимся окне терминал выполнить команду:

```
monodevelop&
```

6.Открыть нужный проект и в свойствах указать:

![](/images/pages/products/base-tech/mono/Monodevelop01.png)

7.В Visual Studio указать:

[MdbConverter.exe](https://download.freedownloadmanager.org/Windows-PC/MDB-Converter/FREE-3.0.html) . с:/catalog:/childrencatalog

![](/images/pages/products/base-tech/mono/Monodevelop02.png)

8.В итоге можно выполнять отладку:

![](/images/pages/products/base-tech/mono/Monodevelop03.png)

## Программное обеспечение

* [Mono](http://www.mono-project.com/download/#download-win)
* [Xamarin Studio](https://www.xamarin.com/studio)

## Перейти

* [ORM](gbt_orm.html)
* [Главная страница курса](gbt_landing-page.html)
