---
title: Возможные места утечки памяти в web-приложениях
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_memory-leaks.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Предназначение''': Описание возможных мест утечки памяти в web-приложениях.
</td>
</tr></tbody></table></a>
</div>

# Возможные места утечки памяти в web-приложениях

В первую очередь стоит обратить внимание на:
* Статические классы и атрибуты.
* Подписка на события. Garbage collector не собирает объекты, на события которого кто-то ещё подписан.
* Любые внешние ресурсы, которые не закрываются должным образом: файлы, соединение с БД и пр.
* Параметры пользовательской сессии.
 

