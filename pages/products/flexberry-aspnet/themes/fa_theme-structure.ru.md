---
title: Структура тем Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_theme-structure.html
lang: ru
---

Внешний вид тем, а также дополнительная информация изложены в [статье Выбор темы Web-приложения](fa_choose-theme.html).

## Темы

Изначально темы оформления Flexberry ASP.NET представляли собой неупорядоченный набор .css-файлов, из которых было тяжело понять, что за что отвечает. Это приводило к тому, что процесс доработки тем на сторонних проектах усложнялся. Необходимо было как-то структурировать темы и упростить процесс их доработки.  
Для того чтобы добиться этой цели, было решено использовать динамический язык стилей LESS. Он позволяет группировать стили, а так же переносить какие-либо стилевые правила в переменные, а потом импортировать их в нужные .less-файлы.  
Такой подход позволяет создать отдельно группу файлов, в которых есть стили, использующиеся во всех темах. Подробней об этом рассказано в [статье Базовая тема Flexberry ASP.NET и ее связь с другими темами](fa_base-theme-structure.html). А также "собирать" общие стили для всех тем и переносить их в отдельный .less-файл, создавать  переменные и импортировать их по мере надобности.  
Таким образом уменьшается количество лишнего кода, а темы принимают общую структуру. Кроме того улучшается возможность доработки тем на прикладных проектах. Об этом рассказано в [статье Доработка тем оформления на прикладных проектах](fa_change-theme.html).

## Структура темы

По умолчанию структура тем выглядит следующим образом:

* `Controls`
  * `__DateTimePicker__`
      * Images
      * _Settings.less
      * _Variables.less
      * DateTimePicker.less
  * `__DefaultInputs__`
    * Images
    * _Settings.less
    * _Variables.less
    * DefaultInputs.less
  * `__Lookup__`
    * Images
    * _Settings.less
    * _Variables.less
    * Lookup.less
    * _Variables.less
  * `__ShowHideDiv__`
    * Images
    * _Settings.less
    * ShowHideDiv.less
  * `__TableControls__`
    * `AjaxGroupEdit`
      * _Settings.less
      * _Variables.less
      * AjaxGroupEdit.less
    * `WebObjectListView`
      * Images
      * WebObjectListView-Settings
        * Images
        * _Variables.less
        * WebObjectListView-HierarchySettings.less
        * WebObjectListView-LimitEditSettings.less
        * WebObjectListView-ViewColumnProvider.less
      * _Variables.less
      * _Settings.less
      * WebObjectListView-captionToolbar.less
      * WebObjectListView-contextMenu.less
      * WebObjectListView-pager.less
      * WebObjectListView-tableToolbar.less
      * WebObjectListView-toolbar.less
      * WebObjectListView.less
* `Forms`
  * `__Messages__`
    * `AlertMessage`
      * _Settings.less
      * _Variables.less
      * AlertMessage.less
  * `__ModalWindows__`
    * `ModalWindowContent`
      * Images
      * _Settings.less
      * _Variables.less
      * ModalWindowContent.less
    * `ModalWindowWrapper`
      * Images
      * _Settings.less
      * _Variables.less
      * ModalWindowContent.less
  * `__Pages__`
    * `EditPage`
      * Images
      * _Settings.less
      * _Variables.less
      * EditPage.less
    * `ExceptionPage`
      * _Settings.less
      * _Variables.less
      * ExceptionPage.less
    * `LoginPage`
      * _Settings.less
      * _Variables.less
      * LoginPage.less
    * `MainPage`
      * Images
      * _Settings.less
      * _Variables.less
      * MainPage.less
* `Images`
* `Libraries`
  * `__jQueryUI__`
    * Images
    * jQueryUI.Base.less
    * jQueryUI.DateTimePicker.less
    * jQueryUI.less
    * jQueryUI.Settings.less
* BaseStyles.css
* BaseStyles.less
* Theme.skin
* Web.config

### Папки и файлы в Controls

#### Стандартная структура

В папке `Controls` содержатся файлы и папки, связанные с контролами. По умолчанию это: стандартные контролы: `DateTimePicker`, `ShowHideDiv`, `Lookup`, а также `WebObjectListView` (WOLV) и `AjaxGroupEdit` (AGE). Каждый контрол содержится в отдельной папке. По умолчанию, в каждой папке есть три файла и папка с картинками Images (для WOLV структура немного другая). Пример представлен ниже.

* DateTimePicker
  * `Images `- папка, содержащая картинки, предназначенные только для данного контрола.
  * `_Settings.less` - файл, предназначенный для добавления стилевых правил прикладными разработчиками. Как он конкретно работает будет описано далее.
  * `_Varaibles.less` - файл, содержащий какие-либо переменные для данного контрола.
  * `DateTimePicker.less` - основной файл, содержащий стили для данного контрола.

{% include note.html content="В дальнейшей файлы _Variables.less и _Settings.less будут встречаться неоднократно. Их назначение везде одинаковое, поэтому отдельно для каждого элемента структура рассматриваться не будет." %}

### Структура [WOLV](fa_web-object-list-view.html)

Структура контрола WOLV немного иная. Данный контрол, как и AGE, содержится в папке `TableControls`, отвечающей за табличные контролы. При этом количество файлов WOLV намного больше, чем у других контролов.  
Структура логически поделена на два уровня:

* файлы, отвечающие за части WOLV
* файлы, отвечающие за настройки WOLV.

По примеру других контролов, для WOLV есть своя папка `Images`, файлы `_Variables.less` и `_Settings.less`. Так как стилей для WOLV очень много, они были разбиты на несколько файлов.
В папке `WebObjectListView-Settings` также содержаться папка `Images` и файл `_Variables.less`. Содержимое данной папки динамическое, поскольку количество настроек WOLV растет.  
На данный момент отдельно представлено их три:
* `WebObjectListView-HierarchySettings.less`
* `WebObjectListView-LimitEditSettings.less`
* `WebObjectListView-ViewColumnProvider.less`. 

В подробностях структура представлена ниже.

* TableControls
  * WebObjectListView
    * Images
    * WebObjectListView-Settings
      * Images
      * _Variables.less
      * `WebObjectListView-HierarchySettings.less` - файл, содержащий стили для иерархического отображения данных
      * `WebObjectListView-LimitEditSettings.less` - файл, содержащий стили блока ограничений.
      * `WebObjectListView-ViewColumnProvider.less` - файл, содержащий стили настроек для отображения колонок в таблице.
    * _Variables.less
    * _Settings.less
    * `WebObjectListView-captionToolbar.less` - файл, содержащий стили тулбара в шапке таблицы WOLV.
    * `WebObjectListView-contextMenu.less` - файл, содержащий стили контекстного меню.
    * `WebObjectListView-pager.less` - файл, содержащий стили нижнего и верхнего пейджеров.
    * `WebObjectListView-tableToolbar.less` - файл, содержащий стили тулбара в таблице WOLV.
    * `WebObjectListView-toolbar.less` - файл, содержащий стили основного тулбара.
    * `WebObjectListView.less` - основой файл, содержащий стили WOLV.

### Папки и файлы в Forms

В папке `Forms` содержаться файлы и папки, связанные с различными формами приложения.  
Содержимое этой папки подразделяется на три группы:

* сообщения (Messages)
* модальные окна (ModalWindows)
* страницы (Pages).  

Структура и описание каждой папки, что за что отвечает представлена ниже.
 
* Forms
  * Messages
    * `AlertMessage` - содержит стили для модальных окон с сообщениями.
      * _Settings.less
      * _Variables.less
      * AlertMessage.less
  * ModalWindows
    * `ModalWindowContent` - содержит стили для содержимого модальных окон.
      * Images
      * _Settings.less
      * _Variables.less
      * ModalWindowContent.less
    * `ModalWindowWrapper` - содержит стили для оболочки модальных окон.
      * Images
      * _Settings.less
      * _Variables.less
      * ModalWindowContent.less
  * Pages
    * `EditPage` - содержит стили для формы редактирования.
      * Images
      * _Settings.less
      * _Variables.less
      * EditPage.less
    * `ExceptionPage` - содержит стили для страницы с ошибками.
      * _Settings.less
      * _Variables.less
      * ExceptionPage.less
    * `LoginPage` - содержит стили для страницы авторизации.
      * _Settings.less
      * _Variables.less
      * LoginPage.less
    * `MainPage` - содержит стили для главной страницы.
      * Images
      * _Settings.less
      * _Variables.less
      * MainPage.less

### Файлы и папки в Libraries

Папка `Libraries` содержит файлы и папки различных библиотек. На данный момент только одну `jQueryUI`.

* Libraries
  * jQueryUI
    * `jQueryUI.Base.less` - файл, содержащий стили для библиотеки jQueryUI.
    * `jQueryUI.DateTimePicker.less` - файл, содержащий стили для DateTimePicker.
    * `jQueryUI.less` - основной файл, в котором содержаться импортированные файлы.
    * `jQueryUI.Settings.less` - файл, содержащий настройки для стилей jQueryUI.

### Остальные элементы темы

К остальным элементам темы относятся:

* `Images `- картинки, использующиеся в Theme.skin.
* `BaseStyles.css` - файл стилей полученный через компиляцию BaseStyles.less.
* `BaseStyles.less` - файл, содержащий импортированные .less-файлы темы.
* `Theme.skin` - "кожа" сайта.
* `Web.config` --//-.

### Внешние файлы тем

Внешние файлы тем - это файлы, относящиеся ко всем темам. Они находятся в папке `App_Themes`.

* `CommonSettings.less` - файл содержащий настройки для всех тем.
* `WolvClasses.less` - в этом файле содержаться переменные, соответствующие классам и идентификатором в WOLV.
