---
title: Настройка карты сайта по ролям
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/sitemap-according-roles.html
folder: product--folder
lang: ru
---

В данной статье описан механизм организации карты сайта, элементы которой должны быть доступны пользователям с разными ролями.<br>
Предположим, что у нас имеется веб-приложение Гостиница, доступ к которому имеют пользователи с двумя возможными ролями: `Приемщик` и `admin`.

Если текущий пользователь имеет роль `admin`, то его карта сайта должна выглядеть следующим образом: <br>
![Меню администратора](/images/RNesterov/AdminMenu.PNG)<br>
Если текущий пользователь имеет роль `Приемщик`, то его карта сайта должна выглядеть следующим образом:<br>
![Меню приемщика](/images/RNesterov/PriemMenu.PNG)<br>

Необходимо разграничить доступ к соответствующим формам приложения. Для этого указываем роль пользователя, который должен иметь доступ к элементцу карты сайта, в атрибуте `roles`. Имеем следующую карту сайта (содержимое файла `web.sitemap`):  
```xml <siteMapNode title="Гостиница" roles="Приемщик,admin" xmlns="">
      <siteMapNode title="Бронирования" description="" url="~/forms/Bronirovanie/G_BronirovanieL.aspx" roles="admin"/> 
      <siteMapNode title="Номерной фонд" description="" url="~/forms/KomnataGostinicy/G_KomnataGostinicyL.aspx" roles="Приемщик"/> 
      <siteMapNode title="Вид животного" description="" url="~/forms/ZHivotnoe/G_ZHivotnoeL.aspx" roles="Приемщик"/>
</sitemapNode> 
```
Необходимо обратить внимание на то, что у узла карты сайта верхнего уровня (`Гостиница`) указаны все роли пользователей, которые могут иметь доступ к этому меню (и `Приемщик`, и `admin`).

Для того чтобы обеспечить корректное отображение вложенных пунктов меню в зависимости от роли пользователя, необходимо в папки веб-приложения, которые соответствуют указанным в `url` формам, добавить файлы `web.config`.

В папку `forms/Bronirovanie` добавить `web.config` следующего содержания:
```xml <configuration>
    <system.web>
      <authorization>
        <allow roles="admin"/>
        <deny users="*"/>
      </authorization>  
    </system.web>
</configuration>
```

В папку `forms/KomnataGostinicy` и `forms/ZHivotnoe` добавить `web.config` следующего содержания:
```xml <configuration>
    <system.web>
      <authorization>
        <allow roles="Приемщик"/>
        <deny users="*"/>
      </authorization>
    </system.web>
</configuration>
```

В результате вложенные пункты меню будут отображаться в зависимости от того, какова роль текущего пользователя веб-приложения.

