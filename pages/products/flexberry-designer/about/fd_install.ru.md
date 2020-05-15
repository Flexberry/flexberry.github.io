---
title: Установка Flexberry Designer
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, алгоритм установки, лицензии
summary: Алгоритм установки Flexberry Designer, лицензии и возможные проблемы при установке
toc: true
permalink: ru/fd_install.html
lang: ru
---

Для того чтобы установить [Flexberry Designer](fd_flexberry-designer.html) необходимо выполнить [регистрацию](https://designer.flexberry.net/#/login-or-register) на сайте [Flexberry.net](https://flexberry.net).  

Для работы приложения на компьютере должно быть установлено следующее программное обеспечение (в процессе установки Flexberry Designer наличие данного перечня ПО будет автоматически проверено, и при необходимости нехватающее ПО будет автоматически установлено):

* SQL Server 2012 Express LocalDB (и выше)
* Windows Installer 4.5
* Microsoft .NET Framework 4 (x86 and x64)
* .NET Framework 3.5 SP1

## Алгоритм установки Flexberry Designer

*При установке система может запрашивать разрешения на установку, во всех случаях необходимо их дать.*

1.Зарегистрироваться на сайте [Flexberry.net](https://flexberry.net), если это не было сделано ранее.

2.Нажать на кнопку [Попробовать](https://designer.flexberry.net/#/fd-try) в разделе `Разработчикам`, далее перейти по ссылке `Скачать Flexberry Designer`, после чего нажать на кнопку `Установить` в открывшемся разделе [Лицензии](https://designer.flexberry.net/#/download-win-app).

3.При скачивании приложения в `Google Chrome` браузер может заблокировать загрузку. Требуется дополнительно разрешить данную загрузку.

![Пример](/images/pages/products/flexberry-designer/about/save-chrome.png)

5.Запустить загруженный установщик.

6.Если при установке возникнет диалоговое окно, изображённое ниже, то необходимо в этом окне нажать "Подробнее" и дать приложению необходимые разрешения.

![Пример](/images/pages/products/flexberry-designer/about/let-setup0.png)

![Пример](/images/pages/products/flexberry-designer/about/let-setup.png)

7.Подтвердить установку приложения.

![Пример](/images/pages/products/flexberry-designer/about/let-setup2.png)

8.Если всё необходимое ПО установлено, то установка будет выполнена достаточно быстро (либо дополнительно будет выполнена установка необходимого ПО).

![Пример](/images/pages/products/flexberry-designer/about/install-designer.png)

![Пример](/images/pages/products/flexberry-designer/about/let-setup3.png)

9.Ввести логин (E-mail) и пароль, указанные при [регистрации на сайте Flexberry.net](https://designer.flexberry.net/#/fd-try) для получения лицензий.

![Пример](/images/pages/products/flexberry-designer/about/login-flexberry2.png)

10.Выбрать путь, куда будут генерироваться приложения, спроектированные при помощи Flexberry Designer.

![Пример](/images/pages/products/flexberry-designer/about/set-generation-path.png)

В редких случаях, когда не всё установленное стороннее ПО успевает настроиться, при первом запуске Flexberry Designer может возникнуть ошибка. В этом случае рекомендуется запустить приложение повторно через созданный на рабочем столе ярлык.

## Установка и продление лицензий

При загрузке [Flexberry Designer](fd_flexberry-designer.html) с сайта [Flexberry.net](https://flexberry.net) установка лицензий будет происходить следующим образом:

* В процессе установки приложения у пользователя будут запрошены логин (E-mail) и пароль, которые были уакзаны при [регистрации на сайте Flexberry.net](https://designer.flexberry.net/#/fd-try). В случае корректного ввода учетных данных лицензия будет автоматически загружена.
* Для продления лицензии необходимо перейти в раздел [Лицензции](https://designer.flexberry.net/#/download-win-app), предварительно [авторизовавшись на сайте Flexbery.net](https://designer.flexberry.net/#/login-or-register), и выполнить запрос академической или корпоративной лицензии, нажав на соответствующую кнопку. Уведомление об успешном продлении срока лицензии будет отправлено на E-mail, указанный при регистрации на сйте [Flexberry.net](https://flexberry.net).
* По окончании срока лицензии при запуске приложения пользователю вновь будет предложено ввести [учётные данные для сайта Flexberry.net](https://designer.flexberry.net/#/fd-try) для загрузки [приобретённой лицензии](https://designer.flexberry.net/#/fd-try).

## Установка новой версии Flexberry Desinger (обновление)

*При установке система может запрашивать разрешения на установку, во всех случаях необходимо их подтвердить.*

Для того чтобы установить обновления для Flexberry Desinger, необходимо выполнить следующие шаги:

1.Открыть раздел [Лицензии](https://designer.flexberry.net/#/download-win-app) и нажать кнопку `Установить`.

*Может потребоваться залогиниться на сайт, если это не было сделано ранее. При скачивании приложения в Google Chrome браузер может заблокировать загрузку. Требуется дополнительно разрешить данную загрузку.*

2.Запустить загруженный установщик.

3.Ввести логин/пароль от сайта [Flexberry.net](https://designer.flexberry.net/#/fd-try) для получения лицензий.

4.Определить путь, куда приложение будет генерироваться.

Подключить используемую ранее базу данных можно при помощи пункта меню `Настройки/Сменить БД` с последующим введением строки соединения c базой данных.

### Особенности обновления Flexberry Designer при использовании LocalDB

{% include note.html content="Перед обновлением необходимо запустить Flexberry Designer." %}

При обновлении `Flexberry Designer` с использованием `LocalDB` алгоритм обновления несколько иной:

1.Необходимо сделать backup FlexberryDesigner.mdf:

* Использовать панель задач:

  * Кликнуть на панели задач на иконку `Flexberry Designer` правой кнопкой мыши, затем кликнуть правой кнопкой по "CASEBERRY" и выбрать пункт "Свойства".

    ![Пример](/images/pages/products/flexberry-designer/about/update-fd-menu.png)

    * Скопировать путь к файлу .exe, вставить в проводник и найти файл `FlexberryDesigner.mdf` и скопировать его в другую папку.

    ![Пример](/images/pages/products/flexberry-designer/about/update-fd-settings.png)

* Использовать диспетчер задач:

  * Запустить диспетчер задач, найти в списке запущенных приложений `Flexberry Designer`.
  * Кликнуть по нему правой кнопкой и выбрать пункт "Открыть расположение файла" - открыт каталог, в котором требуется найти и скопировать в другую папку файл `FlexberryDesigner.mdf`.

2.Обновить `Flexberry Designer`.

3.Далее по указаному в п.1 данного раздела алгоритму подменить файл `FlexberryDesigner.mdf` на сохраненный ранее.

## Возможные проблемы при установке Flexberry Designer

### Ошибка «Setup has detected that the file ... has changed since it was initially published» при установке

На данный момент в ClickOnce-установщиках Microsoft есть проблемы с автоматической установкой некоторых компонентов, поэтому перед установкой Flexberry Designer требуется:

1.Вручную установить Microsoft SQL Server 2012 Express вместе с LocalDB:

<https://www.microsoft.com/ru-ru/download/details.aspx?id=29062>

2.Вручную установить Microsoft SQL Server® 2012 Native Client:
<https://www.microsoft.com/ru-ru/download/details.aspx?id=50402>

Всё скачивается бесплатно с сервера Microsoft.

### Медленная инициализация MS SQL Server 2012 Express LocalDB

В некоторых случаях MS SQL Server 2012 Express LocalDB очень долго проводит инициализацию собственных компонент. В случае, если при запуске приложения возникла ошибка, рекомендуется подождать некоторое время и попробовать запустить приложение повторно с созданного на рабочем столе ярлыка. Если проблема была именно в медленной инициализации MS SQL Server 2012 Express LocalDB, то приложение запустится.

### Запуск под Windows 7

Для корректной работы MS SQL Server 2012 Express LocalDB под Windows 7 SP1 (если Service Pack не стоит, то требуется его установить, как и все рекомендуемые обновления) требуется заранее включить .Net Framework 3.5 и поставить на него Service Pack 1 (для [х86](https://www.microsoft.com/ru-ru/download/details.aspx?id=39237) или [х64](https://www.microsoft.com/ru-ru/download/details.aspx?id=7942)) в соответствии [с требованиями данного приложения](https://msdn.microsoft.com/library/ms143506%28v=SQL.110%29.aspx).

Если MS SQL Server 2012 Express LocalDB был установлен на Windows 7 до установки .Net Framework 3.5 Service Pack 1, то может потребоваться удалить MS SQL Server 2012 Express LocalDB и Flexberry Designer, после чего повторно их установить уже на систему с .Net Framework 3.5 Service Pack 1.

Под Windows 8 и 10 такой проблемы на настоящий момент зафиксировано не было.
