--- 
title: basic theme Flexberry ASP.NET and its relationship to other topics 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_base-theme-structure.html 
lang: en 
autotranslated: true 
hash: c0c1312bd04ce509d3787f72c5ff3bf8e6ad334fcbf3fed6122dec86965ca2a8 
--- 

In this article, we describe the basic topic Flexberry ASP.NET, its purpose, and its relationship with other topics. The structure is described [here](fa_theme-structure.html). 

## Structure-BaseTheme 

`BaseTheme` is the underlying theme Flexberry ASP.NET. The structure is exactly the same as regular threads except that it is not all files. This topic is dynamic and can be updated. But only on the side Flexberry ASP.NET. Its structure is presented below. 

* Controls 
* DateTimePicker 
* _Variables.less 
* DateTimePicker.less 
* Lookup 
* _Variables.less 
* Lookup 
* ShowHideDiv 
* _Variables.less 
* ShowHideDiv.less 
* TableControls 
* AjaxGroupEdit 
* _Variables.less 
* WebObjectListView 
* _Variables.less 
* WebObjectListView-contextMenu.less 
* WebObjectListView-pager.less 
* WebObjectListView-tableToolbar.less 
* WebObjectListView.less 
* Forms 
* Messages 
* AlertMessage 
* Images 
* _Variables.less 
* AlertMessage.less 
* ModalWindows 
* ModalWindowContent 
* _Variables.less 
* ModalWindowContent.less 
* ModalWindowWrapper 
* _Variables.less 
* ModalWindowWrapper 
* Pages 
* EditPage 
* _Variables.less 
* EditPage.less 
* ExceptionPage 
* _Variables.less 
* ExceptionPage.less 
* LoginPage 
* _Variables.less 
* LoginPage.less 
* MainPage 
* Images 
* _Variables.less 
* MainPage.less 
* Libraries 
* jQueryUI 
* Images 
* jQueryUI.Base.less 
* jQueryUI.DateTimePicker.less 
* jQueryUI.less 
* jQueryUI.Settings.less 
* BaseStyles.css 
* BaseStyles.less 
* Theme.skin 
* Web.config 

- BaseTheme - the theme, which defines styles common to all themes. This can be the location items, pictures, indentation, and so on. All interaction between the underlying subject and the other is due to the file import the base theme to other relevant files. Let us consider in detail how it looks. Thus, it allows not to repeat the same code in all subjects. The main difference between the base theme from the others is that the _Settings file is missing.less as to allocate individual settings it is not necessary. Consider how the theme of the sample form of the main page MainPage. 

1. In-BaseTheme contains code that is common to the MainPage in all subjects. 
2. In the corresponding file _Variables.less contain the variables used in the MainPage.less basic topics. 
3. _Variables.less is imported at the beginning of the MainPage.less. This uses a key word reference. This means that the content will be used, but will not be imported physically. 
4. MainPage.less base theme is imported in the beginning of the MainPage.less other topics. 
5. _Variables.less base theme is imported in _Settings.less other topics. There is imported the corresponding _Variables.less topics. This indicates the key word reference. 
6. In MainPage.less imported the relevant _Variables.less (reference) and _Settings.less. 


Now consider the example already with the code to fully understand the example all the same the main page is MainPage and theme of Spring. 

_Variables.less basic topics: 

```less
/** 
* The file that contains the configurable settings of the base theme. 
* The main page. 
*/

/* Names of classes. */
@pageHeaderClass: page-header; // Header. 
``` 

MainPage.less basic topics: 

```less
/** 
* The styles of base theme. 
* The main page. 
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
}
``` 

As can be seen _Variables.less is imported to the MainPage.less then using this variables you can use. 

_Variables.less themes Spring: 

```less
/** 
* File containing the custom settings. 
* The main page. 
*/

/* Names of classes. */
@pageNavigatorClass: page-navigation; // The Navigator. 
@pageBlockTreeId: pageBlockTreeview; // Menu block. 
``` 

Settings.less themes Spring: 

```less
/** 
* The file for the adjustments of existing styles. 
* The main page. 
*/

@import (reference) "../../../../- BaseTheme/Forms/Pages/MainPage/_Variables.less";
@import (reference) '_Variables.less';
``` 


As can be seen here connect all the variables of base themes and Spring. Since in this file there are no adjustments, it is empty. 

Finally the MainPage.less themes Spring: 

```less
@import '../../../../BaseTheme/Forms/Pages/MainPage/MainPage.less';

/* The file's visual design of the main page. */
body {
    font-size: 13px;
    font-family: "Lucida Sans Unicode", "Lucida Grande", Verdana, Arial, Helvetica, sans-serif;
}

/* #region Header. */
.@{pageHeaderClass} {
    &-caption {
        a {
            font-size: 24px;
        }
    }

    &-userbar {
        float: right;
        margin: 13px 0;
    }
}
/* #endregion */


@import (reference) "_Variables.less";
@import '_Settings.less';
``` 

This is where the main page of basic topics as well as the corresponding variables for the theme Spring. 

Then the MainPage.less themes Spring is imported in BaseStyles.less, then using the batch file RebuildLessTheme compiled in BaseStyles.css. More about the process of finalizing the topics and the sweater [here](fa_change-theme.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}