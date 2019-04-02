--- 
title: the edit form with fields for multiple objects 
sidebar: flexberry-winforms_sidebar 
keywords: View (view) 
summary: provides links to articles that will help in the implementation of the work with fields of objects of different classes on one form of editing by ispolzovaniya several EditManager 
toc: true 
permalink: en/fw_work-with-several-objects-on-editform.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: e5f3921d565c7c1d4cedd19442e002329cfd2fa348fd8d7fd7e1723d0ea20254 
--- 
Sometimes faced with the task to organize the work with fields of objects of different classes on one form of editing. For example, the necessary form for a fast input where some fields belongs to one class, part of another, etc. In this case, it is necessary to monitor the preservation of this "complex presentation". 

## solution via EditManager 
One of the variants of solving this task can be usage of several [EditManager](fw_editmanager.html). 

### Binding EditManager 
The article [How to edit data objects on forms, associating input fields with object properties data](fw_edit-objects-on-forms.html) describes how to create and bind [EditManager](fw_editmanager.html)'s objects. 

### Saving objects 
The logic of preservation of objects from the "complex representation" depends on the specific task (e.g., what fields to determine that the same object is already in the database how to interpret the change of the stored object, etc.). 
While maintaining the "complex representation" is necessary to pay attention to the points made in the article: 
* [Processing of a set of objects (including different types)](fo_processing-multiple-objects.html) 
* [Update related objects](fo_update-related-objects.html) 

## See also 
* [Reads belonging to different object classes in a single view](fo_reading-several-types-objects.html) 
* [Uniform field](fw_uni-win-edit.html) 
* [Toggle the object being edited without closing the edit form ](fw_switch-editing-object.html)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}