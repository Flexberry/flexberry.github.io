---
title: Независимая установка Flexberry Designer
sidebar: product--sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/flexberry-designer-standalone-install.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Предназначение''': Существует [несколько способов установить и настроить](flexberry-designer-install.html) [Flexberry Designer](flexberry-designer.html). Один из них - установка приложения из архива.
</td>
</tr></tbody></table></a>
</div>
Существует [несколько способов установить и настроить](flexberry-designer-install.html) [Flexberry Designer](flexberry-designer.html). Один из них - установка приложения из архива.

1. Для запуска Flexberry Designer необходимо '''наличие [поддерживаемой СУБД](data-service.html)''' (установленной на Вашем компьютере, или где-то в сети) и [установленной лицензии](installation-licensing-files.html).

2. Необходимо проверить наличие '''доступа''' к серверу, который Вы будете использовать, на Вашем компьютере.

3. В файле конфигурации '''Flexberry.exe.config''' необходимо изменить [настройки для сервиса данных](data-service-provider-data-service.html).

Например, для [MSSQLDataService](m-s-s-q-l-data-service.html) можно изменить строку подключения, указав путь к серверу :
```xml 
<add key="CustomizationStrings" value="SERVER=<адрес сервера>;Trusted_connection=yes;DATABASE=CASE;"/>
```

вместо <адрес сервера> необходимо указать путь до сервера. В качестве наименования базы можно взять любое, система сама предложит создать базу данных. При использовании MS SQL Server версии Express адрес сервера будет выглядеть как '''.\SQLEXPRESS'''.

4. После запуска Flexberry необходимо создать Репозиторий для работы, выбрав пункт меню '''Репозиторий''' – '''Создать новый''':

![](/images/pages/img/page/FlexberryDesignerStandaloneInstall/создание.png)

5. Далее необходимо настроить новый Репозиторий, выбрав пункт меню '''Репозиторий''' – '''Редактировать свойства''', в открывшемся окне можно изменить наименование репозитория, а также необходимо подключить плагины:

![](/images/pages/img/page/FlexberryDesignerStandaloneInstall/RepProperties.png)

6. После чего необходимо '''сохранить изменения'''.

7. После внесения изменений в свойства репозитория, необходимо создать Проект и Конфигурацию: 

  7.1 '''Выделить репозиторий''' в дереве структуры. 

  7.2 Нажать кнопку '''Создать Project'''. 

  7.3 Ввести '''имя проекта'''. 

  7.4 ''Выделить''' созданный проект. 

  7.5 Нажать кнопку '''Создать Configuration'''. 

  7.6 Нажать кнопку '''Создать Стадия'''. 

  7.7 Ввести '''имя стадии'''. 

  7.8 Нажать кнопку '''Создать System'''. 

  7.9 Ввести '''наименование System'''. В созданной Вами System теперь можно создавать диаграммы. 


После выполнения этих действий Вы получите возможность создавать [диаграммы поддерживаемых видов](editing-diagram.html): 

![](/images/pages/img/page/FlexberryDesignerStandaloneInstall/CreateDiagram.png)

(((
<msg type=caution>При возникновении вопросов обратитесь в службу техподдержки по почте support@flexberry.ru.</msg>
)))
