---
title: Базовая тема web-шаблона и ее связь с другими темами
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_base-theme-structure.html
folder: products/flexberry-aspnet/
lang: ru
---

В этой статье будет описана базовая тема web-шаблона, ее назначение, а также ее связь с другими темами. Структура тем описана [здесь](theme-structure.html).

# Структура BaseTheme

`BaseTheme` - это базовая тема web-шаблона. По структуре она точно такая же как обычные темы, за исключением того, что в ней есть не все файлы. Эта тема является динамической и может обновляться. Но только на стороне web-шаблона. Ее структура представлена ниже.

* Controls
** DateTimePicker
*** _Variables.less
*** DateTimePicker.less
** Lookup
*** _Variables.less
*** Lookup
** ShowHideDiv
*** _Variables.less
*** ShowHideDiv.less
** TableControls
*** AjaxGroupEdit
**** _Variables.less
*** WebObjectListView
**** _Variables.less
**** WebObjectListView-contextMenu.less
**** WebObjectListView-pager.less
**** WebObjectListView-tableToolbar.less
**** WebObjectListView.less
* Forms
** Messages
*** AlertMessage
**** Images
**** _Variables.less
**** AlertMessage.less
** ModalWindows
*** ModalWindowContent
**** _Variables.less
**** ModalWindowContent.less
*** ModalWindowWrapper
**** _Variables.less
**** ModalWindowWrapper
** Pages
*** EditPage
**** _Variables.less
**** EditPage.less
*** ExceptionPage
**** _Variables.less
**** ExceptionPage.less
*** LoginPage
**** _Variables.less
**** LoginPage.less
*** MainPage
**** Images
**** _Variables.less
**** MainPage.less
* Libraries
** jQueryUI
*** Images
*** jQueryUI.Base.less
*** jQueryUI.DateTimePicker.less
*** jQueryUI.less
*** jQueryUI.Settings.less
* BaseStyles.css
* BaseStyles.less
* Theme.skin
* Web.config

`BaseTheme` - тема, в которой определены стили, общие для всех тем. Это может быть расположение элементов, картинки, отступы и так далее. Все взаимодействие между базовой темой и другими происходит за счет импорта файлов базовой темы в другие соответствующие файлы. Рассмотрим подробно, как это выглядит. Таким образом, это позволяет не повторять один и тот же код во всех темах. Основное отличие базовой темы от других заключается в том, что среди ее файлов отсутствует `_Settings.less`, поскольку выделять отдельные настройки для нее не нужно. Рассмотрим, как работает тема на примере формы главной страницы `MainPage`.

1. В `BaseTheme` содержится код, общий для `MainPage` во всех темах.

2. В соответствующем файле `_Variables.less` содержаться переменные, использующиеся в `MainPage.less` базовой темы.

3. `_Variables.less` импортируется в самом начале `MainPage.less`. При этом используется ключевое слово `reference`. Это означает, что содержимое будет использоваться, но при этом не будет импортировано физически.

4. `MainPage.less` базовой темы импортируется в самое начало `MainPage.less` других тем.

5. `_Variables.less` базовой темы импортируется в `_Settings.less` других тем. Туда же импортируется соответствующий `_Variables.less` темы. При этом указывается ключевое слово `reference`.

6. В `MainPage.less` тем импортируются соответствующие `_Variables.less` (с `reference`) и `_Settings.less`.


Теперь рассмотрим пример уже с кодом для полного понимания. 

`_Variables.less` базовой темы:

```
/**
 * Файл, содержащий настраиваемые параметры базовой темы.
 * Основная страница.
 */

/* Наименования классов. */
@pageHeaderClass: page-header; // Хедер.```
`MainPage.less` базовой:

```
/**
* Стили базовой темы.
* Основная страница.
*/

@import (reference) '_Variables.less';

.@{pageHeaderClass} {
    padding: 0 20px;

    &-image {
        display: inline-block;
        margin: 10px;
        width: 90px;
        height: 90px;
        background-image: url(Images/app-logo.png);
        background-position: 50% 50%;
        background-repeat: no-repeat;
        vertical-align: middle;
    }

    &-caption {
        display: inline-block;
    }
}```
