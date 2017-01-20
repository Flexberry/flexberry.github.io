---
title: Кнопки на CSS
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/css-buttons.html
folder: product--folder
lang: ru
---

# Создание CSS-класса для ссылки
```
<a href="#" class="btn">Кнопка</a>
```
```
<style>
.btn{
    /* здесь указываются атрибуты CSS */
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
</style>```

# Состояния кнопок
Псевдоклассы определяют динамическое состояние элементов, которое изменяется со временем или с помощью действий пользователя, а также положение в дереве документа.
* обычная
* выделенная (псевдокласс :hover)
* нажатая (псевдокласс :active)

```
.btn:hover{
    background: #CCC;
    border: 1px solid #AAA;
}

.btn:active{
    background: #777;
    border: 1px solid #999;
}
```