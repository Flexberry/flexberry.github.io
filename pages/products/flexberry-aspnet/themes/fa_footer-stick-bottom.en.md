--- 
title: Position footer at the bottom of the page 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_footer-stick-bottom.html 
lang: en 
autotranslated: true 
hash: 4f29232ed2a9dc5b27a551925239a4e09e8cc5efe78cdcb542228e2e1c3e9ba5 
--- 

In the body tag (when using ASP.NET in the form tag) are blocks with classes `wrapper` and `footer`. At the end of the tag content with the class `wrapper` inserted an empty block with the class `push` — it is used to create empty space between these blocks when the content `wrapper` completely fit into the screen area. 

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

For all parent blocks tag with the class `wrapper` must have height of 100% and removed the padding (margin): 

```css
{ // this selector can be replaced by specific tags (html, body, etc.), if its properties of breaking the display of other elements 
    margin: 0; 
}
html, body {
    height: 100%;
}
.wrapper {
    min-height: 100%;
    height: auto !important;
    height: 100%;
    margin: 0 auto -4em; // the value of the inverse of the height of the footer 
}
.footer, .push {
    height: 4em; // height of footer 
}
``` 

## the Use of multiple columns in the content 

When you use the columns inside the block with class `wrapper` you need to add `clear` class `push`: 

```css
.footer, .push {
    clear: both;
}
``` 

## Problems when used with ASP.NET 

If you use ASP.NET should add the following CSS code: 

```css
form {
    height: 100%;
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}