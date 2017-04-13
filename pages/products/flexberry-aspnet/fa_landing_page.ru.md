---
title: Структура Flexberry ASP.NET
keywords: Flexberry ASP.NET
toc: false
sidebar: flexberry-aspnet_sidebar
permalink: ru/fa_landing_page.html
lang: ru
---

## ASP.NET

1. Жизненный цикл ASP.NET страницы
    * Объекты каждый раз поднимаются из базы, сервер никого не помнит 
    * ViewState, IPostBackDataHandler
    * Global.asax
    * aspx, ascx
2. Авторизация
    * MembershipProvider
3. Master.Page
4. Css
5. Skinning
6. Javascript
    * Inline, js
    * JQuery
    * AJAX
        * Страница (AjaxShared.aspx)
        * Сервис (WEB, WFC)
7. Sitemap

## Flexberry ASP.NET

1. Алгоритм работы с технологическими контролами (нельзя изменять на проекте)
2. AspNetGenerator и WebAppTemplate
3. ICSSoft.STORMNET.Controls
    * WOLV
        * WOLVSettApplyer
        * Operations
        * CustomToolbarButtons (как пример - экспорт в Excel)
        * Раскраска (Stylization)
        * WebControlProvider
        * ViewColumnProvider
    * LookUp'ы
        * BaseMasterEditorLookUp
    * Autocomplete
    * WGE
        * WGESettApplyer
    * AjaxGroupEdit
    * ObjectTileView
4. WebTools
    * BridgeToDS
    * CacheHelper
    * ContextHelper
    * WebErrorBoxRiser
    * ControlProvider
    * ResponseFilterModule
    