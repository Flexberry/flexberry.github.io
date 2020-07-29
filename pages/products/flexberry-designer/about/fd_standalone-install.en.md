---
title: Independent installation
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer to install, configure, license, launch, repository, DBMS, database
summary: Algorithm setup Flexberry Desinger from the archive, getting started, connecting to the DBMS server
toc: true
permalink: en/fd_standalone-install.html
lang: en
autotranslated: true
hash: 5c819cc3553a9ad13628a561cd2077fc78a50aca989a25ec50f496787d646467
---

There are [several ways to install and configure](fd_install.html) [Flexberry Designer](fd_flexberry-designer.html). One of them is the installation of the application from the archive.

1.To start Flexberry Designer you must have [supported DBMS](fo_data-service.html) (installed on the computer, or somewhere on the network) and [the installed license](fd_install.html).
2.It is necessary to check availability **access** to the server that You will use on Your computer.
3.In the configuration file **Flexberry.exe.config** required to edit settings for data service](fo_ds-provider.html).
For example, [MSSQLDataService](fo_mssql-data-service.html) you can change the connection string, specifying the path to the server :

```xml
<add key="CustomizationStrings" value="SERVER=<server address>;Trusted_connection=yes;DATABASE=CASE;"/>
```

instead of <server address> must specify the path to the server. As the name of the base you can take any, the system will offer to create a database. If you are using MS SQL Server version Express server address will look like **.\SQLEXPRESS**.

4.After starting Flexberry you need to create a Repository for work by choosing the menu item `Репозиторий` – `Создать новый`:

![Example](/images/pages/products/flexberry-designer/about/create-new-repository.png)

5.Next, you need to configure a new Repository by selecting the menu item `Репозиторий` – `Редактировать свойства`, in the opened window you can change the name of the repository, and you need to connect plug-ins:

![Example](/images/pages/products/flexberry-designer/about/rep-properties.png)

6.Then you must save the changes.
7.After changes in the properties of the repository, you must create the Project and Configuration:

Select the repository in the tree structure
* Click `Создать Project`
* Enter the project name
Select created project
* Click `Создать Configuration`
* Click `Создать Стадия`
* Enter a name for the stage
* Click `Создать System`
* Enter the name of the System.

After performing these steps, you will be able to create [chart of supported types](fd_editing-diagram.html):

![Example](/images/pages/products/flexberry-designer/about/create-diagram.png)

{% include note.html content="If you have any questions please contact our technical support by mail support@flexberry.ru." %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}