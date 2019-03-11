--- 
title: selection of a theme Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_choose-theme.html 
lang: en 
autotranslated: true 
hash: d027014a2f622c3a12f6672767858a5358e29a032d29ff32da42e1eb7ebb30d1 
--- 

Application themes are responsible for its appearance. When you change themes changes: 

* Color range 
* Fonts 
* Icons 
* Indentation 
* and so on. 

Appearance themes, you can appreciate in the following pictures: 

### Default 

[WOLV](fa_web-object-list-view.html): 

![](/images/pages/products/flexberry-aspnet/themes/default.png) 

[Web edit form](fa_editform.html): 

![](/images/pages/products/flexberry-aspnet/themes/edit-page-default.png) 

### Light 

[WOLV](fa_web-object-list-view.html): 

![](/images/pages/products/flexberry-aspnet/themes/light.png) 

[Web edit form](fa_editform.html): 

![](/images/pages/products/flexberry-aspnet/themes/edit-page-light.png) 

### Smart 

[WOLV](fa_web-object-list-view.html): 

![](/images/pages/products/flexberry-aspnet/themes/smart.png) 

[Web edit form](fa_editform.html): 

![](/images/pages/products/flexberry-aspnet/themes/edit-page-smart.png) 

### Spring 

[WOLV](fa_web-object-list-view.html): 

![](/images/pages/products/flexberry-aspnet/themes/spring.png) 

[Web edit form](fa_editform.html): 

![](/images/pages/products/flexberry-aspnet/themes/edit-page-spring.png) 

## the Choice of theme in your Web application 

To set the default theme in the Web-application is sufficient in the configuration file `web.config` to enter the name of the theme 

```xml
<pages validateRequest="false" enableSessionState="true" theme="Spring" maintainScrollPositionOnPostBack="true">
``` 

in the theme property=. 

The default theme of Spring. 

The list of topics supplied at the generation of the application: 

* Default (despite the name it is not selected by default) 
* Light 
* Smart 
* Spring (selected by default) 

It is possible to switch threads in user mode. On any page in the lower left corner there is a line where you can specify what topic you need 
to use. The selected theme is set for this particular user. The theme, the default can only be changed in the configuration file. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}