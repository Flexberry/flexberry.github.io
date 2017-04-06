---
title: Шрифтовые иконки
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_font-icons.html
lang: ru
---

## Описание

Шрифтовые иконки (иконочный шрифт) - это шрифт, который содержит в себе набор иконок различной тематики.

Преимущества:

* вариативность цвета, и прочих свойств каскадных стилей;
* изменение размера картинки без потери качества;
* достаточное количество иконок, выполненных в одном стиле.

## Вставка иконки, используя разметку

В технологических стилях встроены шрифтовые иконки с сайта [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/). 

Для добавления иконки необходимо выполнить следующие действия:

* перейти на сайт [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/) и выбрать понравившуюся иконку;
* перейти в описание иконки и посмотреть название класса;
* вставить в разметку тег с соответствующими классами;
* задать стили для иконки (цвет, размер и т.д.).

# Вставка иконки, используя только стили

```css
.element-class {
   display: inline-block;
   font: normal normal normal 14px/1 FontAwesome;
   text-rendering: auto;
   text-decoration: none;
   vertical-align: middle;
   -webkit-font-smoothing: antialiased;
   -moz-osx-font-smoothing: grayscale;
   transform: translate(0, 0);
}

.element-class:before {
   content: "\f083";
}
```

Код иконки можно посмотреть на сайте [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/).

{% include note.html content="Шрифтовые иконки вставляются внутрь родительского элемента, до (:before) и после (:after) содержимого, как псевдоэлемент. Отсюда следует неочевидный вывод, что псевдоэлементы применимы только к элементам, к которых ЕСТЬ СОДЕРЖИМОЕ. Элементы, которые не имеют закрывающего тега (например, input, img), и, соответственно, не имеющие содержимого, никаких псевдоэлементов создавать не будут." %}

## Ошибка в консоле браузера

Для устранения ошибки необходимо добавить MIME type в config, со следующим содержанием:

```xml
<system.webServer>    
   <staticContent>
      <remove fileExtension=".woff" />
      <remove fileExtension=".woff2" />
      <mimeMap fileExtension=".woff" mimeType="application/x-font-woff" />
      <mimeMap fileExtension=".woff2" mimeType="application/x-font-woff2" />
    </staticContent>
</system.webServer>
```

## Скрытие шрифтовых иконок

Если в Вашем проекте имеются свои иконки, которые Вы не хотели бы терять по каким-либо причинам, тогда Вам необходимо их скрыть нижеследующим образом.

```css
// Иконки WOLV'а
.ics-wolv-content .actions .ics-wolv-row-button,
.ics-wolv-toolbar .ics-wolv-toolbar-button .ics-wolv-toolbar-button-icon,
.miniToolbar .actions a {
    display: inline-block;
    background-repeat: no-repeat;
    transform: none;
    //background: url(../images/icon.png);  // путь до файла с иконками, должен быть указан в Ваших селекторах
}

.ics-wolv-row-button {
    margin: 0 3px;
    width: 24px;
    height: 24px;
}

.ics-wolv-toolbar-button-icon,
.miniToolbar a {
  margin: 0;
  width: 16px;   // ширина и высота возможно уже указаны в другом селекторе
  height: 16px;
  vertical-align: middle;
}

.ics-wolv-toolbar-button-limit-edit .ics-wolv-toolbar-button-icon {
    padding: 0;
    margin: 0;
    height: 16px;
}

.ics-wolv-content .actions .ics-wolv-row-button:before,
.ics-wolv-toolbar .ics-wolv-toolbar-button .ics-wolv-toolbar-button-icon:before,
.miniToolbar .actions a:before {
    content: none;
}

.ics-wolv td.tableCellFilter .clrFilters:before,
.ics-wolv td.tableCellFilter .clrFilters:after {
    content: none;
}

// Иконки lookup'а
.ics-form-edit .ics-lookup-btn {
    background: url(../images/icon.png);
    top: 6px;     // отступ сверху, ширина, высота зависят от Ваших иконок
    width: 13px;
    height: 18px;
}

.ics-lookup .ics-lookup-btn.ics-lookup-btn-clear:before, 
.ics-lookup .ics-lookup-btn.ics-lookup-btn-lookup:before {
    content: none;
}
```

## Примеры использования

[Примеры использования.](http://fortawesome.github.io/Font-Awesome/examples/)