--- 
title: setting up the application structure 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_configuring-application-structure.html 
lang: en 
autotranslated: true 
hash: 4aa378247bc74e7c15e2203f78a6bbf1974b27b71f4c3f0094c05f98fea096c6 
--- 

1.Open a class diagram of the prototype application (for example, `АСУ_Склад`). 

2.Right-button click on the class with the stereotype `application`. Select the menu item `Редактировать свойства`. 

3.Tab `Приложение` opposite field `Containers` click `...`. A window will open `Контейнеры for запуска`: 

![](/images/pages/guides/flexberry-aspnet/containers-launching.png) 

By default, on the left there is a folder called the name of the application itself. It is embedded in all the available forms list. You want to group a list form, on any basis, and to create for each group/subgroup a folder/subfolder and place the appropriate list form. 

4.To allocate in separate group, for example, data about employees: 

* Add to application folder, for example, `Сотрудники`: stay on the root folder `-`, click `Добавить подпапку` ![](/images/pages/guides/flexberry-aspnet/subfolder.png). 
* Double-click on the name of the new folder and write `Сотрудники`. 

![](/images/pages/guides/flexberry-aspnet/employees.png) 

* In the right margin click `Добавить контейнер` ![](/images/pages/guides/flexberry-aspnet/add.png) . A window will appear `Добавить...`. It will be empty because by default, it displays only a list of forms that have not yet been added to any folder. 
* To display the existing list of forms you need to tick all `Показать элементы` and select lists, for example, `СотрудникL`. 

![](/images/pages/guides/flexberry-aspnet/employeer-l.png) 

* Click `ОК`. 
* Now in the window `Контейнеры for запуска` next to the folder `Сотрудники` assigned container `СотрудникL`. 

![](/images/pages/guides/flexberry-aspnet/employeer-l-container.jpg) 

* In the title field, change the default value to, for example, `Сотрудники`. 

5.In a similar way to create subfolders in the following folder. 

![](/images/pages/guides/flexberry-aspnet/other-folders.png) 

6.To remove all containers from the folder where they shouldn't be (for example, `АСУ_Склад`), using the button `Удалить контейнер` ![](/images/pages/guides/flexberry-aspnet/delete.png) in the right part of the window. 

7.Add the list of forms in the created folder: 

* For example, `Информация about заказах`: 

![](/images/pages/guides/flexberry-aspnet/orders.png) 

* In the folder `Товары on складе`: 

![](/images/pages/guides/flexberry-aspnet/goods.png) 

8.To change the header for a list of forms. 

9.The order of the folders and containers in them if you need to change with the arrows to the right of the window. 

10.In the window `Контейнеры for запуска` click `ОК`. 

11.In the window `Class (edit)` click `Сохранить and закрыть`. 

12.In the window with a chart and also click `Сохранить закрыть`. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [the prototype](gpg_prototype-creating.html) 
* [Configuration of code generation (CSharp)](gpg_configuring-generation.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}