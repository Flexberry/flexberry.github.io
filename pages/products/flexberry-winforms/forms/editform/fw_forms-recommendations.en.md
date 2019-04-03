--- 
title: Recommendations for revision of the forms edit 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls) 
summary: The recommendations for improvement of the appearance of the forms are listed the most frequently used mechanisms and links to articles describing their implementation 
toc: true 
permalink: en/fw_forms-recommendations.html 
lang: en 
autotranslated: true 
hash: 88a980a2189a2816345b5f5fb2e3fdbe70ed6317c9e8ce0395d41c1ed18f7dad 
--- 

It is important to understand that the form is not only for editing but also for viewing data as well as data management - to run the export operation, printing, working with data audit and other. 

Therefore, the study of the appearance it is better to start with, you should determine what tasks users will perform using the form. Main is already listed, it's: edit, view, executing operations on an object edited in form (export, print, etc.). 

To make the form easy to enter, you should pay attention to the following points: 

* the order of traversal (transition Tab) 
* visual selection of focus - inexperienced users may not even know what is the input focus and that it shows a flashing vertical bar in the input field 
* [predictive](fw_predict-input.html), 
* display fields required for entry. the <br> embodiment: [DataObjectErrorProvider](fw_data-object-error-provider.html). DataObjectErrorProvider will allow you to quickly and tastefully to prescribe in the code the list of mandatory fields and users of the application will not be able to change it. 
* default values, 
* automation of user input, such as entering addresses or the ability to enter key details of the other object data with the ability to automatically search. 

To facilitate browsing and search eyes on the form of the desired data uses the following mechanisms: 

* the consolidation of controls into logical groups (GroupBox) specify the name of the group - a common feature of all attributes in this group 
* the location of the controls on the grid in columns and rows. width of input fields in a single column must be the same, the field headers in the columns start with the same position in the string, 
* increase the font. There are cases where the font needs to be increased (e.g., when most users of this form of elderly people with poor eyesight), 
* implementation of the edit form in the "[read-Only](fw_editmanager.html)" . Here the work is to verify that unnecessary functions are blocked, and need to work correctly. 

To perform operations on the data frequently used toolbar, which by default, has buttons "Save" and "Save and close". For each operation it is better to create a separate button on this panel with a clear icon, the laconic inscription and clear the tooltip (tooltip). Between the buttons, the vertical separator.

The location of the controls on the form and grouping them in groups - a complex task that should be solved jointly with the analyst. 

Also the edit form can provide the following universal services: 

* listening for data changes and provision of access to data of audit 
* checking for compliance with user conditions 
* separation of data access through a system of authority, 
* storage of user preferences.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}