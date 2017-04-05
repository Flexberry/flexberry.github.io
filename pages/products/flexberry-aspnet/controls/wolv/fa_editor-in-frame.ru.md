---
title: Отображение на одной странице списковой формы и формы редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_editor-in-frame.html
lang: ru
---

Когда пользователь выбирает элемент в списке, бывает удобно сразу увидеть информацию об элементе и отредактировать ее.

Ниже представлен пример настроки [WOLV](fa_web-object-list-view.html), позволяющий одновременно отображать список и
[форму редактирования](fa_editform.html) выбранного в списке элемента со всеми ее возможностями.

[Форма редактирования](fa_editform.html) отображается во фрейме (при необходимости ее можно свернуть или развернуть).

Есть возможность настроить, где отображать [форму редактирования](fa_editform.html) относительно списка: слева, справа, внизу, сверху.

![](/images/pages/products/flexberry-aspnet/controls/wolv/editor-in-frame1.png)

## Пример настройки

1. Добавить элемент iframe.
    
    ```html
    <iframe id="editor" style="width:100%; height:600px;"></iframe> 
    ```

2. Установить обработчик события открытия формы редактирования из WOLV.
    
    ```javascript
    $('#wolv').on('showeditingpage.wolv', function (e, d) {
        // Отключается базовая логика (переход на форму редактирования).
        e.preventDefault();
    
        // Установка адреса для фрейма.
        $('#editor').attr('src', d.data.editingUrl);
    });
    ```
