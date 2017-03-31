---
title: Настройка строки соединения к БД
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public, БД
toc: true
permalink: ru/fd_sql-express-local-db.html
folder: products/flexberry-designer/about/
lang: ru
---

## Работа Flexberry Desinger c SQL Server 2014/2016

[Скачать версию SQL Server 2014 Express](https://www.microsoft.com/ru-ru/download/details.aspx%3Fid%3D42299)

Об SQL Server 2016 Express LocalDB изложено в MSDN:

* [На русском языке](http://msdn.microsoft.com/ru-ru/library/hh510202.aspx)
* [На английском языке](http://msdn.microsoft.com/en-us/library/hh510202.aspx)

1.Скачать версию SQL Server 2016 Express LocalDB (алгоритм описан в статях выше).  
2.Подключиться к установленной инстанции SQL Server 2016 Express LocalDB можно через Management Studio. Имя сервера на локальном компьютере вероятно будет следующее: `(localdb)\MSSQLLocalDB`.
3.Запустить приложение Flexberry Designer, на панели задач, кликнуть по иконке правой кнопкой и выбрать свойства приложения:

![](/images/pages/products/flexberry-designer/about/Settings_exe_FD.jpg)

5.Откроется окно свойств приложения, где необходимо скопировать путь расположения файлов приложения

![](/images/pages/products/flexberry-designer/about/FB_path.JPG)

6.Открываем проводним и в адресную строку проводника вставляем скопированный путь расположения файлов приложения

![](/images/pages/products/flexberry-designer/about/Catalog_FD.jpg)

7.В `CASEBERRY.exe.config` изменяем строку соединения примерно следующим образом (имя сервера и базы данных может отличаться):

Изменения для строки подключения к SQL server Express или другой версии: 

```xml
<add key="CustomizationStrings" value="SERVER=nameComp\SQLEXPRESS;Trusted_connection=yes;DATABASE=CaseLocalDB;"/>
```
Изменения для строки подключения к SQL server 2014/2016 Express LocalDB: 

```xml
<add key="CustomizationStrings" value="SERVER=(localdb)\MSSQLLocalDB;Trusted_connection=yes;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```

Либо можно раскомментировать настройки для SQL Server 2014/2016 Express LocalDB из конфиг-файла (соответствующие настройки по умолчанию нужно закомментировать). При этом mdf-файл будет создаваться в папку C:\Users\ИмяПользователя. 

8.Если вместо русских букв начнут отображаться знаки вопроса, то у соответствующих баз данных необходимо в качестве `Collation` поставить `Cyrillic_General_CI_AS`. Как это сделать, указано в следующих статьях: 

* [SQL Server 2014](http://technet.microsoft.com/en-us/library/ms175835(v=sql.120).aspx) 
* [SQL Server 2016](http://technet.microsoft.com/en-us/library/ms179254.aspx) 

## Работа Flexberry Desinger c SQL Server 2012 Express LocalDB
Если по какой-то причине не удалось скачать SQL Server 2016 Express LocalDB, можно воспользоваться версией [2012](http://www.microsoft.com/ru-ru/download/details.aspx?id=35579).

Пример строки соединения для LоcalDB:

```xml
<add key="CustomizationStrings" value="Server=(LocalDB)\v11.0; Integrated Security=true;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```
