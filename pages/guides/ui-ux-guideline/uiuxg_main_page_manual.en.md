---
title: Desk
keywords: Desk
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_main_page_manual.html
lang: en
autotranslated: true
hash: b3f40ef6c16ae774f2db6e7b9dab947488a7b9ae80853cc445ae31654829cbd2
summary: Rules for creating and filling the main page.
---

## Main features

In our system, the main page changes depending on the privileges and access level of the user. This page quite often customized to suit a particular customer's needs.

Basically a typical home page contains a set of [card](uiuxg_cards.EN.md), icons, and small [entry fields](uiuxg_input_fields.EN.md).

> Main components main page: [Home page](uiuxg_main_page.ru)

UIS MV:

![Main page of UIS MV](/images/pages/guides/ui-ux-guideline/uiuxg_main_page_manual/1.png)

Highway SB:

![Main page of UIS MV](/images/pages/guides/ui-ux-guideline/uiuxg_main_page_manual/2.png)

The prototype PROCEEDS:

![Main page in the prototype](/images/pages/guides/ui-ux-guideline/uiuxg_main_page_manual/3.png)

When designing the home page you need to work through the following steps:

1. Identify frequently used функции;
2. To distribute what functions you want to assign to a specific ролей;
3. To establish a hierarchy of these functions on the main page.
4. To define additional elements.

## The recommended location of the main elements

As an example, we divide into several groups the elements of the prototype:

![Group prototype](/images/pages/guides/ui-ux-guideline/uiuxg_main_page_manual/4.png)

### 1. The input field

Can perform several different functions:

* Search записям;
* Search selected разделу;
* Select the desired menu item (similar to Spotlight in macOS).

Correctly inserted the input field will greatly help the experienced user of the system and reduce the time for searching for the required section or entry.

### 2. Informer

Widgets need to display quick statistics on the system. Essentially a widget is a mini the dashboard with basic statistics. Possible any widget needs to redirect to the desired section. For example, click on the statistics new applications navigate to the appropriate section.

### 3. Card

Flashcards are a quick links to sections of the system. Cards can contain a title, an icon and links to subsections.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}