---
title: Настройка строки соединения c БД
sidebar: flexberry-designer_sidebar
keywords: Flexberry Desinger, строка соединения, connection string, установка, install, setup
summary: Взаимодействие с SQL Server, изменение строки соединения, настройка строки соединения
toc: true
permalink: ru/fd_sql-express-local-db.html
lang: ru
---

## Работа Flexberry Desinger c SQL Server 2014/2016

[Скачать версию SQL Server 2014 Express](https://www.microsoft.com/ru-ru/download/details.aspx%3Fid%3D42299)

Об SQL Server 2016 Express LocalDB изложено в MSDN:

* [На русском языке](http://msdn.microsoft.com/ru-ru/library/hh510202.aspx)
* [На английском языке](http://msdn.microsoft.com/en-us/library/hh510202.aspx)

1.Скачать версию SQL Server 2014/2016 Express LocalDB (алгоритм описан в статях выше).  

2.После запуска [Flexberry Designer](fd_flexberry-designer.html) можно изменить строку соединения, выбрав пункт меню `Настройка` – `Сменить БД...` или в файле конфигурации выбрав пункт меню `Настройка` – `Открыть файл конфигурации...`

3.В файле конфигурации, который называется `CASEBERRY.exe.config` изменяем строку соединения примерно следующим образом (имя сервера и базы данных может отличаться):

Изменения для строки подключения к SQL server Express или другой версии:

```xml
<add key="CustomizationStrings" value="SERVER=nameComp\SQLEXPRESS;Trusted_connection=yes;DATABASE=CaseLocalDB;"/>
```

* **nameComp** - это имя локального компьютера или сервера, а также возможно IP-адрес сервера, где установлен SQL Server.
* **SQLEXPRESS** - это имя установленого экземпляра SQL Server на компьютере.

Изменения для строки подключения к SQL server 2014/2016 Express LocalDB:

```xml
<add key="CustomizationStrings" value="SERVER=(localdb)\MSSQLLocalDB;Trusted_connection=yes;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```

4.Если вместо русских букв начнут отображаться знаки вопроса, то у соответствующих баз данных необходимо в качестве `Collation` поставить `Cyrillic_General_CI_AS`. Как это сделать, указано в следующих статьях:

* [SQL Server 2014](http://technet.microsoft.com/en-us/library/ms175835(v=sql.120).aspx)
* [SQL Server 2016](http://technet.microsoft.com/en-us/library/ms179254.aspx)

## Работа Flexberry Desinger c SQL Server 2012 Express LocalDB

Если по какой-то причине не удалось скачать SQL Server 2016 Express LocalDB, можно воспользоваться версией [2012](http://www.microsoft.com/ru-ru/download/details.aspx?id=35579).

Пример строки соединения для LоcalDB:

```xml
<add key="CustomizationStrings" value="Server=(LocalDB)\v11.0; Integrated Security=true;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```
