--- 
title: Installation Flexberry Designer 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, algorithm setup, license 
summary: Algorithm setup Flexberry Designer, license, and possible problems when installing 
toc: true 
permalink: en/fd_install.html 
lang: en 
autotranslated: true 
hash: b31c46f5ce0cc11fb3ca18f60e2316f16161a3204e7624448b8f43393e113f83 
--- 

To set [Flexberry Designer](fd_landing_page.html) you need to download the installation file from site [Flexberry.net](https://designer.flexberry.net/#/fd-try). 
This requires registration on the website. 

Also to the install file in the section [License](https://designer.flexberry.net/#/download-win-app) button `Установить`. 

For the application to work, the computer must have the following installed (before you install it will be checked and if something will be missed, installed): 

* SQL Server 2012 Express LocalDB (and above) 
* Windows Installer 4.5 
* Microsoft .NET Framework 4 (x86 and x64) 
* .NET Framework 3.5 SP1 

## Algorithm setup Flexberry Designer 

*When installing, the system may prompt for permission to install, in all cases, you need to give them.* 

1.To register on the site [Flexberry.ru](https://flexberry.net) if not already done. 

2.Click the button `Попробовать` in section `Попробуйте сами` or `Установить` in [License](https://designer.flexberry.net/#/download-win-app). 

3.When downloading apps in `Google Chrome` browser may block the download. You also need to allow the download. 

![](/images/pages/products/flexberry-designer/about/save-chrome.png) 

5.Run the downloaded installer. 

6.If you are installing a new window will appear as shown below, it is necessary in this window click "More" to give the application the necessary privileges. 

![](/images/pages/products/flexberry-designer/about/let-setup0.png) 

![](/images/pages/products/flexberry-designer/about/let-setup.png) 

7.Allow installation of applications. 

![](/images/pages/products/flexberry-designer/about/let-setup2.png) 

8.If all the necessary costs, the system quickly enough will be to install (or requires you to install the necessary). 

![](/images/pages/products/flexberry-designer/about/install-designer.png) 

![](/images/pages/products/flexberry-designer/about/let-setup3.png) 

9.Enter the username/password from website Flexberry.net](https://designer.flexberry.net/#/fd-try) to obtain licenses. 

![](/images/pages/products/flexberry-designer/about/login-flexberry2.png) 

10.Define the path where the application will be generated. 

![](/images/pages/products/flexberry-designer/about/set-generation-path.png) 

In rare cases, when not all the parties have time to tune, when you first start may fail. In this case, it is recommended to run the app again through the created desktop shortcut.

## Install licenses 

With this method of obtaining the product [Flexberry Designer](fd_landing_page.html) install licenses will occur as follows: 

* When the application is installed the user will be prompted for the username and password of website [Flexberry.net](https://designer.flexberry.net/#/fd-try) where the license will be downloaded and automatically installed. 
* At the end of the license term when the application starts, the user will again be prompted to enter credentials from the website [Flexberry.net](https://designer.flexberry.net/#/fd-try) download [purchased licenses](https://designer.flexberry.net/#/fd-try). 

## installing a new version Flexberry Desinger (update) 

*When installing, the system may prompt for permission to install, in all cases, you need to give them.* 

To install the updates for Flexberry Desinger, you must have the following: 

1.To open the [License](https://designer.flexberry.net/#/download-win-app) and click `Установить`. 

*May require login to the site if it was not done earlier. When downloading apps in the Google Chrome browser may block the download. You also need to allow the download.* 

2.Run the downloaded installer. 

3.Enter the username/password from website Flexberry.net](https://designer.flexberry.net/#/fd-try) to obtain licenses. 

4.Define the path where the application will be generated. 

To connect a previously used database by using the menu item `Настройки/Change БД` followed by the introduction of the connection string the database. 

### update Features Flexberry Designer when using LocalDB 

{% include note.html content="Before you upgrade, you must run Flexberry Designer." %} 

When you upgrade `Flexberry Designer` using `LocalDB` update algorithm is somewhat different: 

1.You must make a backup FlexberryDesigner.mdf: 

* Use the task pane: 

* Click on the taskbar icon `Flexberry Designer`, right-click, then right click on the "CASEBERRY" and select "Properties". 

![](/images/pages/products/flexberry-designer/about/update-fd-menu.png) 

* Copy the file path .exe, paste into Windows Explorer and find the file `FlexberryDesigner.mdf` and copy it to another folder. 

![](/images/pages/products/flexberry-designer/about/update-fd-settings.png) 

* Use the task Manager: 

* Run task Manager, find in the list of running applications `Flexberry Designer`. 
* Click on it, right click and select "Open file location" open the directory in which you want to find and copy to another folder the file `FlexberryDesigner.mdf`. 

2.Update `Flexberry Designer`. 

3.Further on specified in item 1 of this section, the algorithm to decrypt the file `FlexberryDesigner.mdf` on previously saved.

## Possible problems when installing Flexberry Designer 

### Slow initialization MS SQL Server 2012 Express LocalDB 

In some cases MS SQL Server 2012 Express LocalDB a long time carries out initialization of the private component. If when you run the application there is an error, it is recommended to wait some time and try to run the app again with the created desktop shortcut. If the problem was in the slow initialization of MS SQL Server 2012 Express LocalDB, the application will start. 

### Run under Windows 7 

For correct operation of MS SQL Server 2012 Express LocalDB on Windows 7 SP1 (if Service Pack is not necessary, it is required to install as all the recommended updates) must be enabled .Net Framework 3.5 and put it on a Service Pack 1 (for [x86](https://www.microsoft.com/ru-ru/download/details.aspx?id=39237) or [x64](https://www.microsoft.com/ru-ru/download/details.aspx?id=7942)) in accordance [with this application](https://msdn.microsoft.com/library/ms143506(v=SQL.110).aspx). 

If MS SQL Server 2012 Express LocalDB was installed on Windows 7 prior to installation .Net Framework 3.5 Service Pack 1, you may need to remove MS SQL Server 2012 Express LocalDB and Flexberry Designer, and then re-install already on the system .Net Framework 3.5 Service Pack 1. 

Under Windows 8 and 10 such problems at the moment were recorded. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/