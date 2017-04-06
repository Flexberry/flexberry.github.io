---
title: Кастомизация оформления форм
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_custom-form-design.html
lang: ru
---

HTML код в списковых web-формах и [web-формах редактирования](fa_editform.html) разделены на основные блоки.

Для [web-формы редактирования](fa_editform.html):

```html
    <div class="ics-form ics-form-edit">
        <h2 class="ics-form-header ics-form-header-edit">...</h2>
        <div class="ics-form-toolbar ics-form-toolbar-edit">
            ...
        </div>
        <div class="ics-form-controls ics-form-controls-edit">
            ...
        </div>
    </div>
```

Для списковой формы:

```html
    <div class="ics-form ics-form-list">
        <h2 class="ics-form-header ics-header-listform">...</h2>
        <div class="ics-form-controls ics-form-controls-list">
            ...
        </div>
    </div>
```

Для кастомизации элементов оформления необходимо создать или изменить CSS классы для инетесующих элементов формы:
* *ics-form* - общий класс для всего содержимого списковых форм, форм редактирования и пользовательских форм;
* *ics-form-edit* - специальный класс для содержимого только форм редактирования;
* *ics-form-list* - специальный класс для содержимого только списковых форм;
* *ics-form-header* - общий класс для заголовка списковых форм и пользовательских форм;
* *ics-form-header-edit* - специальный класс для заголовка только форм редактирования;
* *ics-form-header-list* - специальный класс для заголовка только списковых форм;
* *ics-form-controls* - общий класс для контейнера контролов списковых форм и форм редактирования;
* *ics-form-controls-edit* - специальный класс для контейнера контролов только форм редактирования;
* *ics-form-controls-list* - специальный класс для контейнера контролов только списковых форм;

### Основные рекомендации

* для изменения дизайна всех форм следует пользоваться общими классами (*ics-form*, *ics-form-header* и т.п.);
* для изменения дизайна конкретного типа форм следует пользоваться специальными классами (*ics-form-edit* и т.п.);
