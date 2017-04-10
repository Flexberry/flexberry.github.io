---
title: Настройка карты сайта по ролям
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_sitemap-according-roles.html
lang: ru
---

Пример: имеется веб-приложение `Гостиница`, доступ к которому имеют пользователи с двумя возможными ролями `Приемщик` и `admin`.

Если текущий пользователь имеет роль `admin`, то его карта сайта должна выглядеть следующим образом: 

![](/images/pages/products/flexberry-aspnet/admin-menu.png)

Если текущий пользователь имеет роль `Приемщик`, то его карта сайта должна выглядеть следующим образом:

![](/images/pages/products/flexberry-aspnet/priem-menu.png)

Необходимо разграничить доступ к соответствующим формам приложения. Для этого указываем роль пользователя, который должен иметь доступ к элементцу карты сайта, в атрибуте `roles`. Имеем следующую карту сайта (содержимое файла `web.sitemap`):

```xml
<siteMapNode title="Гостиница" roles="Приемщик,admin" xmlns="">
      <siteMapNode title="Бронирования" description="" url="~/forms/Bronirovanie/G_BronirovanieL.aspx" roles="admin"/> 
      <siteMapNode title="Номерной фонд" description="" url="~/forms/KomnataGostinicy/G_KomnataGostinicyL.aspx" roles="Приемщик"/> 
      <siteMapNode title="Вид животного" description="" url="~/forms/ZHivotnoe/G_ZHivotnoeL.aspx" roles="Приемщик"/>
</sitemapNode> 
```

Необходимо обратить внимание на то, что у узла карты сайта верхнего уровня (`Гостиница`) указаны все роли пользователей, которые могут иметь доступ к этому меню (и `Приемщик`, и `admin`).

Для того чтобы обеспечить корректное отображение вложенных пунктов меню в зависимости от роли пользователя, необходимо в папки веб-приложения, которые соответствуют указанным в `url` формам, добавить файлы `web.config`.

В папку `forms/Bronirovanie` добавить `web.config` следующего содержания:

```xml
<configuration>
    <system.web>
      <authorization>
        <allow roles="admin"/>
        <deny users="*"/>
      </authorization>  
    </system.web>
</configuration>
```

В папку `forms/KomnataGostinicy` и `forms/ZHivotnoe` добавить `web.config` следующего содержания:

```xml
<configuration>
    <system.web>
      <authorization>
        <allow roles="Приемщик"/>
        <deny users="*"/>
      </authorization>
    </system.web>
</configuration>
```

В результате вложенные пункты меню будут отображаться в зависимости от того, какова роль текущего пользователя веб-приложения.

При __windows-аутентификации__ пользователи обрабатываются вместе с доменом. Поэтому необходимо использовать провайдеры, которые учитывают домен. Для этого в конфигурационном файле приложения нужно указать следующее:

```xml
<membership defaultProvider="CaseberryMembershipProvider">
  <providers>
    <clear />
    <add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="SLAuthSample" />
  </providers>
</membership>
<roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
  <providers>
    <clear />
    <add name="CaseberryRoleProvider" type="CheckingLibrary.CaseberryDomainRoleProvider" />
  </providers>
</roleManager>
```

Кроме того стоит __обратить внимание на особенность обработки sitemap-файла__ при windows-аутентификации. А именно обработку `sitemapNode`, у которой указан `url` и `roles`.

```xml
<siteMapNode title="Роли" url="~/flexberry/DvorecList" roles="tsar" />
```

При использовании __стандартного sitemap-провайдера__ данная вершина будет всегда видимой, так как при наличии атрибута `url` выполняется проверка доступности url-узла для текущего пользователя.

Для решения данной проблемы существуют следующие способы:

1. Использовать собственный sitemap-провайдер.
2. Добавить промежуточную вершину без атрибута url.
3. Настроить параметры доступа к файлам.
