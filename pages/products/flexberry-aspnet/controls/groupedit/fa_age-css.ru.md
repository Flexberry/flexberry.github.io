---
title: CSS-классы AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_age-css.html
lang: ru
---

## HTML-структура

```html
<div>
    <span>
         <div class="ics-age-toolbar">
             <button class="ics-button ics-age-toolbar-button-create" title="Добавить"></button>
             <button class="ics-button ics-age-toolbar-button-delete" title="Удалить"></button>
             <button class="ics-button ics-age-toolbar-button-moveup" title="Переместить вверх"></button>
             <button class="ics-button ics-age-toolbar-button-movedown" title="Переместить вниз"></button>
         </div>
         <table class="wge-wrap">
         ...
           <div class="actions">
               <a title="Редактировать" class="ics-button ics-age-toolbar-button-edit"></a>
               <a title="Удалить" class="ics-button ics-age-toolbar-button-delete"></a>
           </div>
         ...
         </table>
    </span>    
</div>
```

# CSS-классы

Возможности кастомизации отображения `AjaxGroupEdit` при помощи CSS. Внутренняя структура и описание CSS-классов:

| CSS-класс | Описание|
|:------------------|:------------------------------------------|
| `ics-age-toolbar` | Контейнер тулбара
| `ics-age-toolbar-button-create` | Иконка кнопки "Добавить"
| `ics-age-toolbar-button-delete` | Иконка кнопки "Удалить"
| `ics-age-toolbar-button-moveup` | Иконка кнопки "Переместить вверх"
| `ics-age-toolbar-button-movedown` | Иконка кнопки "Переместить вниз"
| `wge-wrap` | Обертка таблицы с содержимым
| `actions` | Контейнер для кнопок в строке
| `ics-age-toolbar-button-edit` | Иконка кнопки "Редактировать"
