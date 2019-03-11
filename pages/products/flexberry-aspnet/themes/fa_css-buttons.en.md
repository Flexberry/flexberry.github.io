--- 
title: Buttons CSS 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_css-buttons.html 
lang: en 
autotranslated: true 
hash: bca9c1f11e5124ddcc2d9ffc3f12bd94777d36b6399cde9d8a63d049058ddf8a 
--- 

## create a CSS class for links 

```html
<a href="#" class="btn">Кнопка</a>
``` 
```css
<style>
.btn{
    /* specify CSS attributes */
    display: inline-block;
    padding: 4px 12px;
    font-size: 14px;
    line-height: 20px;
    color: #000;
    cursor: pointer;
    vertical-align: middle;
    background: #FFF;
    border: 1px solid #CCC;
}
</style>
``` 

## button State 

Pseudo-classes define the dynamic state of the elements that varies over time or through user actions, as well as the position in the document tree. 

* normal 
* highlighted (a :hover pseudo-class) 
* active (pseudo-class :active) 

```css
.btn:hover{
    background: #CCC;
    border: 1px solid #AAA;
}

.btn:active{
    background: #777;
    border: 1px solid #999;
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}