---
title: Расположение сборок после генерации кода
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fo_location-assembly-after-code-generation.html
folder: products/flexberry-orm/
lang: ru
---

## Понятие пакета

Пакетом называется некоторое логическое объединение генерируемых исходных кодов сборок. После генерации исходники, объединённые в пакет, располагаются в отдельном подкаталоге.

### Структура каталогов после генерации кода выглядит следующим образом

* Каталог, указанный в "Каталога для исходного кода" настроек стадии 
* Приложение 1 
* Приложение 2 
* ... 
* Приложение N 
* Пакет 1 
* Пакет 2 
* ... 
* Пакет N 

Если имя пакета не указано внутри какого-либо класса, то в качестве имени пакета используется [свойство "Название продукта" из настроек стадии](fd_project-customization.html).

Внутри каждого пакета могут находиться следующие исходные коды сборок(в отдельных каталогах, прямо с такими именами):

* `BusinessServers` - [сборка классов со стереотипами businessserver](business-servers.html); 
* `BusinessServersBF` - сборка [бизнес-фасадов](fo_business-servers-wrapper-business-facade.html) для классов со [стереотипами businessserver](business-servers.html); 
* `BusinessServersComPlus` - сборка [обёртки для обращения к бизнес-серверу](fo_business-servers-wrapper-business-facade.html) через COM+ для бизнес-серверов, у которых установлена галочка `GenerateComPlusServer`; 
* `BusinessServersHttp` - сборка [обёртки для обращения к бизнес-серверу](fo_business-servers-wrapper-business-facade.html) через веб-сервис для серверов, у которых установлена галочка `GenerateHTTPRemoteServer`; 
* `Catchers` - для [классов со стереотипом `eventarg`](classes-with-stereotype-eventarg.html), у которых установлена галочка `GenerateCatcher` в эту сборку генерируются классы-перехватчики событий; 
* `Objects` - классы со стереотипами: [`implementation`](fo_data-classes.html)`, type, `[`enumeration`](enumerations.html), [`eventarg`](classes-with-stereotype-eventarg.html). 
* `Scripts` - сценарии, определённые диаграммным методом `EBSD`. 

Дополнительно (__при наличии специальных генераторов__) могут генерироваться:
* `DesktopCustomizers` - сборка классов - настройщиков классов со стереотипом [`application`](fd_additional-stereotypes.html); 
* `Forms` - классы со стереотипами: [`editform, listform, printform, userform`](fd_additional-stereotypes.html); 

Если нет в стадии классов, попадающих в соответствующую категорию, то исходные коды не генерируются и каталог не создаётся.

### Пространства имён

Внутри сборок определяются пространства имен. Разработчик может управлять пространствами имен, для чего имеются следующие возможности:
* каждый класс имеет свойство `NamespacePostfix`; 
* [стадия имеет свойство `Namespace`](fd_project-customization.html). 

Пространства имён формируются следующим образом (слитно приписывается, точка между ними не ставится):

``` csharp
<Namespace стадии> + <NamespacePostfix класса>
```
Если NamespacePostfix класса не указан, то остаётся только "<Namespace стадии>".

Если <Namespace стадии> не указан, то он формируется как ["<Название компании>.<Название продукта>" из настроек стадии](fd_project-customization.html).




