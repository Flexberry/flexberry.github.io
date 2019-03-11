--- 
title: CSS classes AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_age-css.html 
lang: en 
autotranslated: true 
hash: 396f2acd4a8c1c268f06aa6be5f17a22b808d43d7b76b59ec4db41c01b422cda 
--- 

## HTML structure 

```html
<div>
    <span>
         <div class="ics-age-toolbar">
             <button class="ics-button. ics-age-toolbar-button-create" title="Add"></button>
             <button class="ics-button. ics-age-toolbar-button-delete" title=Delete></button>
             <button class="ics-button. ics-age-toolbar-button-moveup" title=Move up></button>
             <button class="ics-button. ics-age-toolbar-button-movedown" title=Move down></button>
         </div>
         <table class="wge-wrap">
         ...
           <div class="actions">
               <a title=Edit class="ics-button. ics-age-toolbar-button-edit"></a>
               <a title=Delete class="ics-button. ics-age-toolbar-button-delete"></a>
           </div>
         ...
         </table>
    </span>    
</div>
``` 

# CSS classes 

Customization display `AjaxGroupEdit` using CSS. The internal structure and description CSS classes: 

| CSS class | Description| 
|:------------------|:------------------------------------------| 
| `ics-age-toolbar` | Container toolbar 
| `ics-age-toolbar-button-create` | Icon "Add" button 
| `ics-age-toolbar-button-delete` | Icon "Delete" button 
| `ics-age-toolbar-button-moveup` | Icon buttons "Move up" 
| `ics-age-toolbar-button-movedown` | Icon buttons Move down 
| `wge-wrap` | Wrapper table with the contents 
| `actions` | Container for buttons in a row 
| `ics-age-toolbar-button-edit` | Icon "Edit" button 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}