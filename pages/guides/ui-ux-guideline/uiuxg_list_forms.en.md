---
title: list of forms
keywords: list, list form, sheet, arrangement of elements
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_list_forms.html
lang: en
autotranslated: true
hash: ef17c719e117fdd72d67f6c73fbb984fd62b5966394a667989a802e07f86c065
summary: Description the list of forms with recommendations for use.
---

## Description of list form

The list form is the key in all of our information systems, so during the development, it is very important to adhere to certain standards and paradigms.

## The structure of list form

Any list form has a certain structure and arrangement of the elements. The list form must contain:

* «Bread crumbs» – path to form, which is now open.
* Header формы;
* The toolbar or the toolbar– create, delete, update etc.
* Controls for working with the list – search, filters and др;
* Navigation bar page формы;
* A table with the object list form.

![Template list form](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/1.png)

## The title of the list form

The header of a list form is at the top of the form over all the controls and shape objects, but slightly below «Bread crumbs». The title should clearly identify what form the user is. Headers all forms must be unique. The title font is always bold with the largest size on the page. The header should fit snugly with no.

Information on using fonts, see sections [Rules of font use Gost UI](uiuxg_fonts_usage.EN.md) [Fonts](uiuxg_fonts.EN.md).

An example of the correct implementation of the header in list form:

![Template list form](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/2.png)

## Toolbar (toolbar)

Toolbar – «gentleman set» for interacting with the list form. All the key buttons should be in it. The required buttons to any toolbar (provided by technology):

|Icon|Action|
|-|-|
|!Button [add](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/3.png)|Button create a new item (most often it is the target), click it to change the redirection to a blank editing form of the new элемента;|
|!Button [update](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/4.png)|Button to refresh the list, when clicked, updates the list, but not all страницы;|
|!Button [remove](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/5.png) Button delete item(s) after selecting items using checkboxes you can delete them pressing the delete button элементов;|
|![Button filter](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/6.png)|open Button panel фильтрации;|
|!Button [column settings](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/7.png)|Button of location settings column, when clicked, opens a sidebar, where you can configure the order of the displayed columns on list форме;|
|!Button [export to Excel](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/8.png)|eject Button in Excel. If the page has multiple items are selected, only they will be saved in an Excel sheet, if there are no items selected.|

## Controls for working with the list

Above each column there are two Sorting control ««and»» Filtering. Clicking on the icon ![Sort](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/9.png) in sorting, when you first click – descending ![Sort](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/10.png), and the second one in ascending order ![Sort](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/11.png), in the third pressing, the sorting is reset.

![Sort](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/12.png)

## Navigation bar list of the form (pagination)

This control needs to have the ability to go between the closest (neighboring) pages from the current and any existing page.

This panel can be placed in one of two places:

* The list form, but only if the navigation panel is fixed and does not depend on the length of the list. (the example implementation below the list)

![The navigation panel below the list](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/13.png)

* Above the list form in the field of controls for working with the list. (an example implementation of the above list)

![The navigation bar above the list](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/14.png)

Also in the navigation bar have the ability to navigate to any available page:

![Navigation](/images/pages/guides/ui-ux-guideline/uiuxg_list_forms/15.png)

## Table with list objects

Table elements is the key on the list form, it should take up most of your workspace and placed in a separate window, relative to which all other elements are fixed. The table can be scrolling both vertical and horizontal (only in the case that the table has a lot of attributes and you cannot display them all without it scrolling).

On the form in the header row there is a button mass selection. When clicked, a window appears with a choice to select all the items on the page select all items on all pages. When pressed again, the selection is reset.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}