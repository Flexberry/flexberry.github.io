---
title: CSS-классы AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_a-g-e-c-s-s.html
folder: products/flexberry-aspnet/
lang: ru
---

# AGE
Эта статья описывает часть информации о `[AjaxGroupEdit](fa_ajax-group-edit.html)`.

## html-структура
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
Для возможности кастомизации отображения `AjaxGroupEdit` при помощи CSS раскрываем информацию о внутренней структуре и CSS-классах:



{|
! CSS-класс !! Описание
|-
| `ics-age-toolbar` || Контейнер тулбара
|-
| `ics-age-toolbar-button-create` || Иконка кнопки "Добавить"
|-
| `ics-age-toolbar-button-delete` || Иконка кнопки "Удалить"
|-
| `ics-age-toolbar-button-moveup` || Иконка кнопки "Переместить вверх"
|-
| `ics-age-toolbar-button-movedown` || Иконка кнопки "Переместить вниз"
|-
| `wge-wrap` || Обертка таблицы с содержимым
|-
| `actions` || Контейнер для кнопок в строке
|-
| `ics-age-toolbar-button-edit` || Иконка кнопки "Редактировать"
|-
|}


