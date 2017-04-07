---
title: Расположение footer внизу страницы
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_footer-stick-bottom.html
lang: ru
---

В тег body (при использовании ASP.NET в тег form) помещаются блоки с классами `wrapper` и `footer`. В самом конце содержимого тега с классом `wrapper` вставляется пустой блок с классом `push` — он служит для создания пустого пространства между этими блоками, когда содержимое `wrapper` полностью помещается в область экрана.

```html
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="layout.css" />
    </head>
    <body>
        <div class="wrapper">
            <p>Your website content here.</p>
            <div class="push"></div>
        </div>
        <div class="footer">
            <p>Copyright (c) 2008</p>
        </div>
    </body>
</html>
```

## CSS

Для всех родительских блоков тега с классом `wrapper` должна быть установлена высота 100% и убраны отступы (margin):

```css
{ // данный селектор можно заменить на конкретные теги (html, body и т.д.), если его свойства нарушают отображение других элементов
    margin: 0; 
}
html, body {
    height: 100%;
}
.wrapper {
    min-height: 100%;
    height: auto !important;
    height: 100%;
    margin: 0 auto -4em; // значение обратное высоте footer
}
.footer, .push {
    height: 4em; // высота footer
}
```

## Использование нескольких колонок в содержимом

При использовании колонок внутри блока с классом `wrapper` необходимо добавить `clear` для класса `push`:

```css
.footer, .push {
    clear: both;
}
```

## Проблемы при использовании с ASP.NET

Если используется ASP.NET, следует добавите следующий CSS-код:

```css
form {
    height: 100%;
}
```
