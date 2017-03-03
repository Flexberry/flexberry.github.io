---
title: Расположение сборок после генерации кода
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fo_location-assembly-after-code-generation.html
folder: products/flexberry-designer/
lang: ru
---

## Понятие пакета
Пакетом называется некоторое логическое объединение генерируемых исходных кодов сборок. После генерации исходники, объединённые в пакет, располагаются в отдельном подкаталоге.
## Структура каталогов после генерации кода выглядит следующим образом
* Каталог, указанный в SourceCodeCSPath настроек стадии 
#* Приложение 1 
#* Приложение 2 
#* ... 
#* Приложение N 
#* Пакет 1 
#* Пакет 2 
#* ... 
#* Пакет N 

Если имя пакета не указано внутри какого-либо класса, то в качестве имени пакета используется свойство Product из настроек стадии.


Внутри каждого пакета могут находиться следующие исходные коды сборок(в отдельных каталогах, прямо с такими именами):
* `BusinessServers` - [сборка классов со стереотипами businessserver](business-servers.html); 
* `BusinessServersBF` - сборка [бизнес-фасадов](business--servers--wrapper--business--facade.html) для классов со [стереотипами businessserver](business-servers.html); 
* `BusinessServersComPlus` - сборка [обёртки для обращения к бизнес-серверу](business--servers--wrapper--business--facade.html) через COM+ для бизнес-серверов, у которых установлена галочка `GenerateComPlusServer`; 
* `BusinessServersHttp` - сборка [обёртки для обращения к бизнес-серверу](business--servers--wrapper--business--facade.html) через веб-сервис для серверов, у которых установлена галочка `GenerateHTTPRemoteServer`; 
* `Catchers` - для [классов со стереотипом `eventarg`](classes-with-stereotype-eventarg.html), у которых установлена галочка `GenerateCatcher` в эту сборку генерируются классы-перехватчики событий; 
* `DesktopCustomizers` - сборка классов - настройщиков [рабочих столов приложений](app-desktop.html); 
* `Forms` - классы со стереотипами: [`editform, listform, printform, userform` (для генерации требуются дополнительные генераторы)](additional-stereotypes.html); 
* `Objects` - классы со стереотипами: `[implementation](data--classes.html), type, [enumeration](enumerations.html), [eventarg](classes-with-stereotype-eventarg.html)`. 
* `Scripts` - сценарии, определённые диаграммным методом `EBSD`. 

Если нет в стадии классов, попадающих в соответствующую категорию, то исходные коды не генерируются и каталог не создаётся.

## Пространства имён
Внутри сборок определяются пространства имен. Разработчик может управлять пространствами имен, для чего имеются следующие возможности:
* каждый класс имеет свойство `NamespacePostfix`; 
* стадия имеет свойство `Namespace`. 
Пространства имён формируются следующим образом (слитно приписывается, точка между ними не ставится):
```
<Namespace стадии> + <NamespacePostfix класса>
```
Если NamespacePostfix класса не указан, то остаётся только "<Namespace стадии>".


Если <Namespace стадии> не указан, то он формируется как "<Company>.<Product>" из настроек стадии.


