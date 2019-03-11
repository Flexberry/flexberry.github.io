--- 
title: Customization of registration forms 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_custom-form-design.html 
lang: en 
autotranslated: true 
hash: b07bf25197dc34b1d9ee188cac91b0800cd4d586ac48dcf1d0c2556221bf2d6d 
--- 

The HTML code in the list of web-forms and [web edit forms](fa_editform.html) is divided into basic blocks. 

To [web editor](fa_editform.html): 

```html
    <div class="ics-ics form-form-edit">
        <h2 class="ics-form-header ics-form-header-edit">...</h2>
        <div class="ics-form-toolbar ics-form-toolbar-edit">
            ...
        </div>
        <div class="ics-form-controls ics-form-controls-edit">
            ...
        </div>
    </div>
``` 

For a list of the form: 

```html
    <div class="ics-ics form-form-list">
        <h2 class="ics-form-header ics-header-listform">...</h2>
        <div class="ics-form-controls ics-form-controls-list">
            ...
        </div>
    </div>
``` 

For customization of design elements you need to create or change CSS classes for initiauy form elements: 
* *ics form* shared class for all of the contents list forms, edit forms and custom форм; 
* *ics-form-edit* is a special class for content only forms редактирования; 
* *ics-form-list* is a special class for content list форм; 
* *ics-form-header* - General class for the header list forms and custom форм; 
* *ics-form-header-edit* is a special class for the header only forms редактирования; 
* *ics-form-header-list* is a special class for the header only the list форм; 
* *ics-form-controls* - General class for container controls list and forms редактирования; 
* *ics-form-controls-edit* is a special class for container controls only forms редактирования; 
* *ics-form-controls-list* is a special class for container controls only list форм; 

### Key recommendations 

* to change the design of all forms should be shared with classes (*ics form*, *ics-form-header*, etc.) ; 
* to change the design of a particular type of mold, you should use special classes (*ics-form-edit*, etc.) ; 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}