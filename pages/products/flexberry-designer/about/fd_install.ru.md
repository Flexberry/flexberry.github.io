---
title: Установка Flexberry Designer
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, алгоритм установки, лицензии
summary: Алгоритм установки Flexberry Designer, лицензии и возможные проблемы при установке
toc: true
permalink: ru/fd_install.html
lang: ru
---

Для того чтобы установить [Flexberry Designer](fd_landing_page.html) нужно скачать файл установки с сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try).  
При этом требуется регистрация на сайте.

Также получить файл установки можно в разделе [Лицензии](https://designer.flexberry.net/#/download-win-app) по кнопке `Установить`.

Для работы приложения на компьютере должно быть установлено следующее ПО (перед установкой это будет проверено и, если чего-то будет не хватать, установлено):

* SQL Server 2012 Express LocalDB (и выше)
* Windows Installer 4.5
* Microsoft .NET Framework 4 (x86 and x64)
* .NET Framework 3.5 SP1

## Алгоритм установки Flexberry Designer

*При установке система может запрашивать разрешения на установку, во всех случаях необходимо их дать.*

1.Зарегистрироваться на сайте [Flexberry.ru](https://flexberry.net), если это не было сделано ранее.

2.Нажать на кнопку `Попробовать` в разделе `Попробуйте сами` или `Установить` в разделе [Лицензии](https://designer.flexberry.net/#/download-win-app).

3.При скачивании приложения в `Google Chrome` браузер может заблокировать загрузку. Требуется дополнительно разрешить данную загрузку.

![](/images/pages/products/flexberry-designer/about/save-chrome.png)

5.Запустить скачанный установщик.

6.Если при установке возникнет окно, изображённое ниже, то необходимо в этом окошке нажать "Подробнее" и дать приложению необходимые привилегии.

![](/images/pages/products/flexberry-designer/about/let-setup0.png)

![](/images/pages/products/flexberry-designer/about/let-setup.png)

7.Разрешить установку приложения.

![](/images/pages/products/flexberry-designer/about/let-setup2.png)

8.Если всё необходимое ПО стоит, то система достаточно быстро пройдёт установку (либо потребует установить необходимое).

![](/images/pages/products/flexberry-designer/about/install-designer.png)

![](/images/pages/products/flexberry-designer/about/let-setup3.png)

9.Ввести логин/пароль от сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try) для получения лицензий.

![](/images/pages/products/flexberry-designer/about/login-flexberry2.png)

10.Определить путь, куда приложение будет генерироваться.

![](/images/pages/products/flexberry-designer/about/set-generation-path.png)

В редких случаях, когда не всё стороннее ПО успевает настроиться, при первом запуске может возникнуть ошибка. В этом случае рекомендуется запустить приложение повторно через созданный на рабочем столе ярлык.

## Установка лицензий

При данном способе получения продукта [Flexberry Designer](fd_landing_page.html) установка лицензий будет происходить следующим образом:

*   При установке приложения у пользователя будут запрошены логин и пароль от сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try), откуда лицензия будет выкачана и автоматически установлена.
*   По окончании срока лицензии при запуске приложения пользователю вновь будет предложено ввести учётные данные от сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try) для скачивания [приобретённой лицензии](https://designer.flexberry.net/#/fd-try).

## Установка новой версии Flexberry Desinger (обновление)

*При установке система может запрашивать разрешения на установку, во всех случаях необходимо их дать.*

Для того чтобы установить обновления для Flexberry Desinger, необходимо следующее:

1.Открыть раздел [Лицензии](https://designer.flexberry.net/#/download-win-app) и нажать кнопку `Установить`. 

*Может потребоваться залогиниться на сайт, если это не было сделано ранее. При скачивании приложения в Google Chrome браузер может заблокировать загрузку. Требуется дополнительно разрешить данную загрузку.*

2.Запустить скачанный установщик.

3.Ввести логин/пароль от сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try) для получения лицензий.

4.Определить путь, куда приложение будет генерироваться.

Подключить используемую ранее базу данных можно при помощи пункта меню `Настройки/Сменить БД` с последующим введением строки соединения c базой данных.

### Особенности обновления Flexberry Designer при использовании LocalDB

{% include note.html content="Перед обновлением необходимо запустить Flexberry Designer." %}

При обновлении `Flexberry Designer` с использованием `LocalDB` алгоритм обновления несколько иной:

1.Необходимо сделать backup FlexberryDesigner.mdf:

* Использовать панель задач:

    * Кликнуть на панели задач на иконку `Flexberry Designer` правой кнопкой мыши, затем кликнуть правой кнопкой по "CASEBERRY" и выбрать пункт "Свойства".

        ![](/images/pages/products/flexberry-designer/about/update-fd-menu.png)

    * Скопировать путь к файлу .exe, вставить в проводник и найти файл `FlexberryDesigner.mdf` и скопировать его в другую папку.

        ![](/images/pages/products/flexberry-designer/about/update-fd-settings.png)

* Использовать диспетчер задач:

    * Запустить диспетчер задач, найти в списке запущенных приложений `Flexberry Designer`. 
    * Кликнуть по нему правой кнопкой и выбрать пункт "Открыть расположение файла" - открыт каталог, в котором требуется найти и скопировать в другую папку файл `FlexberryDesigner.mdf`. 

2.Обновить `Flexberry Designer`.

3.Далее по указаному в п.1 данного раздела алгоритму подменить файл `FlexberryDesigner.mdf` на сохраненный ранее.

## Возможные проблемы при установке Flexberry Designer 

### Медленная инициализация MS SQL Server 2012 Express LocalDB

В некоторых случаях MS SQL Server 2012 Express LocalDB очень долго проводит инициализацию собственных компонент. В случае, если при запуске приложения возникла ошибка, рекомендуется подождать некоторое время и попробовать запустить приложение повторно с созданного на рабочем столе ярлыка. Если проблема была именно в медленной инициализации MS SQL Server 2012 Express LocalDB, то приложение запустится.

### Запуск под Windows 7

Для корректной работы MS SQL Server 2012 Express LocalDB под Windows 7 SP1 (если Service Pack не стоит, то требуется его установить, как и все рекомендуемые обновления) требуется заранее включить .Net Framework 3.5 и поставить на него Service Pack 1 (для [х86](https://www.microsoft.com/ru-ru/download/details.aspx?id=39237) или [х64](https://www.microsoft.com/ru-ru/download/details.aspx?id=7942)) в соответствии [с требованиями данного приложения](https://msdn.microsoft.com/library/ms143506%28v=SQL.110%29.aspx).

Если MS SQL Server 2012 Express LocalDB был установлен на Windows 7 до установки .Net Framework 3.5 Service Pack 1, то может потребоваться удалить MS SQL Server 2012 Express LocalDB и Flexberry Designer, после чего повторно их установить уже на систему с .Net Framework 3.5 Service Pack 1.

Под Windows 8 и 10 такой проблемы на настоящий момент зафиксировано не было.
