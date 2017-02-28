---
title: Запуск под SQL Server Express LocalDB
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public, БД
toc: true
permalink: ru/fd_sql-express-local-db.html
folder: products/flexberry-designer/about/
lang: ru
---

Об SQL Server 2016 Express LocalDB изложено в MSDN:
* [На русском языке](http://msdn.microsoft.com/ru-ru/library/hh510202.aspx)
* [На английском языке](http://msdn.microsoft.com/en-us/library/hh510202.aspx)

1. Скачать версию SQL Server 2016 Express LocalDB (алгоритм описан в статях выше).

2. Подключиться к установленной инстанции SQL Server 2016 Express LocalDB можно через Management Studio. Имя сервера на локальном компьютере вероятно будет следующее: `(localdb)\MSSQLLocalDB`.

3. В `Flexberry.exe.config` изменить строку соединения примерно следующим образом (имя сервера и базы данных может отличаться):

```xml
<add key="CustomizationStrings" value="SERVER=(localdb)\MSSQLLocalDB;Trusted_connection=yes;DATABASE=CaseLocalDB;"/>
```
Либо можно раскомментировать настройки для SQL Server 2016 Express LocalDB из конфиг-файла (соответствующие настройки по умолчанию нужно закомментировать). При этом mdf-файл будет создаваться в папку C:\Users\ИмяПользователя. 

4. Если вместо русских букв начнут отображаться знаки вопроса, то у соответствующих баз данных необходимо в качестве `Collation` поставить `Cyrillic_General_CI_AS`. Как это сделать, указано в следующих статьях: 

* [SQL Server 2014](http://technet.microsoft.com/en-us/library/ms175835(v=sql.120).aspx) 
* [SQL Server 2016](http://technet.microsoft.com/en-us/library/ms179254.aspx) 

## Работа Flexberry Desinger c SQL Server 2012/2014 Express LocalDB
Если по какой-то причине не удалось скачать SQL Server 2016 Express LocalDB, можно воспользоваться версией [2012](http://www.microsoft.com/ru-ru/download/details.aspx?id=35579) или [2014](https://www.microsoft.com/ru-ru/download/details.aspx%3Fid%3D42299).

Пример строки соединения для LоcalDB:

```xml
<add key="CustomizationStrings" value="Server=(LocalDB)\v11.0; Integrated Security=true;  AttachDbFileName=D:\Flexberry\a1.mdf;"/>
```

