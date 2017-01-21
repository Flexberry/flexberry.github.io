---
title: Отображение на одной странице списковой формы и формы редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_editor-in-frame.html
folder: products/flexberry-aspnet/
lang: ru
---
Эта статья описывает часть информации о [WebObjectListView](web-object-list-view.html).

Когда пользователь выбирает элемент в списке, бывает удобно сразу увидеть информацию об элементе и отредактировать ее.

Ниже представлен пример настроки [WOLV](web-object-list-view.html), позволяющий одновременно отображать список и
[форму редактирования](flexberry-asp-net-edit-form.html) выбранного в списке элемента со всеми ее возможностями.

[Форма редактирования](flexberry-asp-net-edit-form.html) отображается во фрейме (при необходимости ее можно свернуть или развернуть).

Есть возможность настроить, где отображать [форму редактирования](flexberry-asp-net-edit-form.html) относительно списка: слева, справа, внизу, сверху.

![](/images/pages/img/CaseberryWeb/wolv/editorinframe1.PNG)

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
