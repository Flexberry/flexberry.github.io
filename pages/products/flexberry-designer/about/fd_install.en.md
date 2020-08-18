---
title: Installation Flexberry Designer
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, algorithm setup, license
summary: Algorithm setup Flexberry Designer, license, and possible problems when installing
toc: true
permalink: en/fd_install.html
lang: en
autotranslated: true
hash: 3328c967bbf96064b79eda572d085ed5988838233011ffd4d99951997619481f
---

To set [Flexberry Designer](fd_flexberry-designer.html) you need to perform [check](https://designer.flexberry.net/#/login-or-register) on the website [Flexberry.net](https://flexberry.net).

For the application on the computer must have the following software installed (in the installation process Flexberry Designer, this list will be automatically checked and, if necessary, lacking IN will be automatically installed):

* SQL Server 2012 Express LocalDB (and above)
* Windows Installer 4.5
* Microsoft .NET Framework 4 (x86 and x64)
* .NET Framework 3.5 SP1

## Algorithm setup Flexberry Designer

*When installing, the system may prompt for permission to install, in all cases, you need to give them.*

1.To register on the site [Flexberry.net](https://flexberry.net) if not already done.

2.Click on the button [Try](https://designer.flexberry.net/#/fd-try) under `Разработчикам`, then go to the link `Скачать Flexberry Designer`, then click on `Установить` the section [License](https://designer.flexberry.net/#/download-win-app).

3.When downloading apps in `Google Chrome` browser may block the download. You also need to allow the download.

![Example](/images/pages/products/flexberry-designer/about/save-chrome.png)

5.Run the downloaded installer.

6.If the installation dialog box will appear, pictured below, it is necessary in this window click "More" to give the app the necessary permissions.

![Example](/images/pages/products/flexberry-designer/about/let-setup0.png)

![Example](/images/pages/products/flexberry-designer/about/let-setup.png)

7.Confirm the installation of the application.

![Example](/images/pages/products/flexberry-designer/about/let-setup2.png)

8.If all prerequisites installed, installation will fail quite quickly (or additional installs required).

![Example](/images/pages/products/flexberry-designer/about/install-designer.png)

![Example](/images/pages/products/flexberry-designer/about/let-setup3.png)

9.Enter your login (E-mail) and password you specified during [the registration on the website Flexberry.net](https://designer.flexberry.net/#/fd-try) to obtain licenses.

![Example](/images/pages/products/flexberry-designer/about/login-flexberry2.png)

10.Choose the path where to generate the app, designed with the help of Flexberry Designer.

![Example](/images/pages/products/flexberry-designer/about/set-generation-path.png)

In rare cases where not all installed third-party software time to tune, when you first start Flexberry Designer, an error may occur. In this case, it is recommended to run the app again through the created desktop shortcut.

## Installation and renewal of licenses

When you download [Flexberry Designer](fd_flexberry-designer.html) from [Flexberry.net](https://flexberry.net) install licenses will occur as follows:

* In the process of installing the application the user will be prompted for your login (E-mail) and password that was Aksana when [registering on the website Flexberry.net](https://designer.flexberry.net/#/fd-try). In the case of correct credentials, the license will be automatically downloaded.
* To renew your license you must go to [Licensee](https://designer.flexberry.net/#/download-win-app) after [after logging in Flexbery.net](https://designer.flexberry.net/#/login-or-register), and complete the request academic or corporate license by clicking on the appropriate button. Notification of successful license renewal will be sent by E-mail, specified during registration on site [Flexberry.net](https://flexberry.net).
* At the end of the license term when the application starts, the user will again be prompted to enter the [account details for the website Flexberry.net](https://designer.flexberry.net/#/fd-try) to download the [purchased licenses](https://designer.flexberry.net/#/fd-try).

## Installing a new version Flexberry Desinger (update)

*When installing, the system may prompt for permission to install, in all cases, you should confirm them.*

To install the updates for Flexberry Desinger, you must perform the following steps:

1.To open the [License](https://designer.flexberry.net/#/download-win-app) and click `Установить`.

*May require login to the site if it was not done earlier. When downloading apps in the Google Chrome browser may block the download. You also need to allow the download.*

2.Run the downloaded installer.

3.Enter the username/password from website Flexberry.net](https://designer.flexberry.net/#/fd-try) to obtain licenses.

4.Define the path where the application will be generated.

To connect a previously used database by using the menu item `Настройки/Change БД` followed by the introduction of the connection string the database.

### Update features Flexberry Designer when using LocalDB

{% include note.html content="Before you upgrade, you must run Flexberry Designer." %}

When you upgrade `Flexberry Designer` using `LocalDB` update algorithm is somewhat different:

1.You must make a backup FlexberryDesigner.mdf:

* Use the task pane:

 * Click on the taskbar icon `Flexberry Designer`, right-click, then right click on the "CASEBERRY" and select "Properties".

 ![Example](/images/pages/products/flexberry-designer/about/update-fd-menu.png)

 * Copy the file path .exe, paste into Windows Explorer and find the file `FlexberryDesigner.mdf` and copy it to another folder.

 ![Example](/images/pages/products/flexberry-designer/about/update-fd-settings.png)

* Use the task Manager:

 * Run task Manager, find in the list of running applications `Flexberry Designer`.
 * Click on it, right click and select "Open file location" open the directory in which you want to find and copy to another folder the file `FlexberryDesigner.mdf`.

2.Update `Flexberry Designer`.

3.Further on specified in item 1 of this section, the algorithm to decrypt the file `FlexberryDesigner.mdf` on previously saved.

## Possible problems when installing Flexberry Designer

### Qmo error Setup has detected that the file ... has changed since it was initially published» with the installation

At this point in the ClickOnce installers Microsoft have problems with automatic installation of some components, so before installing Flexberry Designer required:

1.Manually install Microsoft SQL Server 2012 Express with LocalDB:

<https://www.microsoft.com/ru-ru/download/details.aspx?id=29062>

2.Manually install Microsoft SQL Server® 2012 Native Client:

<https://www.microsoft.com/ru-ru/download/details.aspx?id=50402>

3.Manually install Microsoft SQL Server® 2012 Command Line Tools:

<https://www.microsoft.com/ru-ru/download/details.aspx?id=35580>

Everything is downloaded for free from the Microsoft server.

### Slow initialization of MS SQL Server 2012 Express LocalDB

In some cases MS SQL Server 2012 Express LocalDB a long time carries out initialization of the private component. If when you run the application there is an error, it is recommended to wait some time and try to run the app again with the created desktop shortcut. If the problem was in the slow initialization of MS SQL Server 2012 Express LocalDB, the application will start.

### Run under Windows 7

For correct operation of MS SQL Server 2012 Express LocalDB on Windows 7 SP1 (if Service Pack is not necessary, it is required to install as all the recommended updates) must be enabled .Net Framework 3.5 and put it on a Service Pack 1 (for [x86](https://www.microsoft.com/ru-ru/download/details.aspx?id=39237) or [x64](https://www.microsoft.com/ru-ru/download/details.aspx?id=7942)) in accordance [with this application](https://msdn.microsoft.com/library/ms143506(v=SQL.110).aspx).

If MS SQL Server 2012 Express LocalDB was installed on Windows 7 prior to installation .Net Framework 3.5 Service Pack 1, you may need to remove MS SQL Server 2012 Express LocalDB and Flexberry Designer, and then re-install already on the system .Net Framework 3.5 Service Pack 1.

Under Windows 8 and 10 such problems at the moment were recorded.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}