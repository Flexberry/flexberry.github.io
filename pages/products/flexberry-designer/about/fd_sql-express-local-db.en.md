---
title: setting up connection strings with DB
sidebar: flexberry-designer_sidebar
keywords: Flexberry Desinger, connection string, connection string, install, install, setup
summary: Interaction with the SQL Server, change the connection string, configure connection string
toc: true
permalink: en/fd_sql-express-local-db.html
lang: en
autotranslated: true
hash: 47cf5bd5e795d6ffbfb583b09927e8fd9a6f3a3e9c3499315fac16089b416ae3
---

## Work Flexberry Desinger SQL Server 2014/2016

[Download the SQL Server 2014 Express](https://www.microsoft.com/ru-ru/download/details.aspx?id=42299)

About SQL Server Express LocalDB 2016 described in MSDN:

* [Russian](http://msdn.microsoft.com/ru-ru/library/hh510202.aspx)
* [English](http://msdn.microsoft.com/en-us/library/hh510202.aspx)

1.Download the version of SQL Server Express LocalDB 2014/2016 (the algorithm is described in the article above).

2.After starting [Flexberry Designer](fd_flexberry-designer.html) you can change the connection string by selecting the menu item `Настройка` – `Сменить DB...` or in the configuration file by choosing the menu item `Настройка` – `Открыть configuration file...`

3.In the configuration file, which is called `CASEBERRY.exe.config` change the connection string like the following (the name of the server and database may be different):

Change connection string to SQL server Express or another version:

```xml
<add key="CustomizationStrings" value="SERVER=nameComp\SQLEXPRESS;Trusted_connection=yes;DATABASE=CaseLocalDB;"/>
```

* **nameComp** is the name of the local computer or server and IP address of the server where SQL Server is installed.
* **SQLEXPRESS** is the name of the installed instance of SQL Server on the computer.

Change connection string to SQL server Express LocalDB 2014/2016:

```xml
<add key="CustomizationStrings" value="SERVER=(localdb)\MSSQLLocalDB;Trusted_connection=yes;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```

4.If instead of Russian letters are displayed as question marks, then the relevant databases is necessary as `Collation` to put `Cyrillic_General_CI_AS`. How to do this are outlined in the following articles:

* [SQL Server 2014](http://technet.microsoft.com/en-us/library/ms175835(v=sql.120).aspx)
* [SQL Server 2016](http://technet.microsoft.com/en-us/library/ms179254.aspx)

## Work Flexberry Desinger SQL Server 2012 Express LocalDB

If for some reason you are unable to download SQL Server Express LocalDB 2016, you can use the version of [2012](http://www.microsoft.com/ru-ru/download/details.aspx?id=35579).

Connection string example for LоcalDB:

```xml
<add key="CustomizationStrings" value="Server=(LocalDB)\v11.0; Integrated Security=true;AttachDbFilename=|DataDirectory|\FlexberryDesigner.mdf;"/>
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}