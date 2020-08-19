--- 
title: Development of themes in the application projects 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_change-theme.html 
lang: en 
autotranslated: true 
hash: 24338d65f11cc9f26a026617df854f9bbaffd5c9caf29792b950861b202d0b8e 
--- 

In order to properly modify the styles you need to do the following: 

1. To determine which group and sub-group styles include revision 
2. Add missing variables in the corresponding file _Variables.less 
3. Add the appropriate styles in _Settings.less 
4. To get the latest package themes 
5. To modify the batch file RebuildLessThemes.bat 
6. To run the batch file RebuildLessThemes.bat 

Consider in detail each item. 

1.As stated in [this article](fa_theme-structure.html) each topic is divided into three groups. 

* Controls (Controls), 
* Forms (Forms), 
* Library (Libraries). 

In each group there are subgroups. Before adding styles you must determine what unit they belong to. If you want to add padding to the main menu page - it forms. If you want to screw [DateTimePicker](fw_datetime-picker.html) is the controls. If you change the appearance of the calendar is library. 

2.Here you need to pay attention to the appointment of _Variables.less. This file contains all the variable used in this set of styles. These variables are: classes, IDs, colors, spacing. An example entry is shown below. 

```less
/* Names of classes. */
@pageHeaderClass: page-header; // Header. 
@pageBlockTreeId: pageBlockTreeview; // Menu block. 

/* Color. */
@linksColor: #000;
@linksHoveredColor: #12a1e5;
@linksFocusedColor: #3a88d5;

/* Otsuchi. */
@menuPadding: 25;
``` 

{% include note.html content="the name of the variable should bear a certain meaning. If the variable is a class, then at the end of the name you need to add the **Class**. If identifier - **Id**. If it is the color, you must specify the condition when it is applied (Hovered, Focused, Seleced, Disabled, etc), its type (Background, Border, TextShadow, BoxShadow) and add at the end **Color**" %} 

3.After you have added all necessary variables you'll need to add styles in _Settings.less. This file is imported in such a way that overrides existing styles. Thus it is possible not to use !important. 

Let's say we want to increase the height of the items of the main menu. After reading [the structure](fa_theme-structure.html), we determine that we need change will affect the main page (MainPage), which refers to the forms (Forms). We find the right folder, see that there are three _Settings file.less, _Variables.less MainPage.less. Determine what the selector will affect change, looking for the appropriate variable in _Variables.less. If it is not there - added. Then in _Settings.less add the required rule. 

{% include note.html content="If the rule already exists, and some of the properties you want to override, then in _Settings.less need to write the full path to the desired item. Otherwise, the new rule does not override the existing one." %} 

4.In order to make the changes effective, you need from .less-file to .css file. The structure is made such that from the set .less'n OK it turns out the big one .css-fayd (BaseStyles.css). There are a number of programs that allow you to compile .css, but some, roughly speaking, blunt, the other problem with the compilation of ways pictures. Therefore, it was written your own batch file, which easily compiles everything you need. It is located in the Tools folder, and it is possible to pick out \\EN\Flexberry Distr\CASEBERRY Subsystems\WebSystem. You need to download the whole folder and add to any project site (cecinit it is not necessary!). In the Tools folder there are three three files: own less.js text file readme and the batch file RebuildLessThemes. To the batch file to work properly you must follow the steps described in the readme. After that, open the batch file with a text editor and configurable to the following: 

1. Topics that need to be compiled. 
2. The path to BaseStyles.less. 

By default, the as many as five pieces (recommended theme-BaseTheme immediately remove. We need to leave one or more, for which everything is done. After that customizable way to BaseStyles.less. Here all should be clear 

{% include note.html content="the path must not contain Russian letters." %} 

6.If everything is configured correctly, after running the batch file and refresh the page, we will see added styles. Compiled .css fast enough so that after each application, you can batch file to run. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}