--- 
title: Incorporation services ASP.NET State Service 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_asp-net-state-service.html 
lang: en 
autotranslated: true 
hash: cd9d457a5eadd4d79d8e69d3edbc43ac1eaaf31f24b5aa15030ba7e83ea20ea6 
--- 

For the correct operation of the generated application is required to run the service `ASP.NET Service` State (Service state ASP.NET). If you run the generated web application displays the error message associated with this service, you must: 

1.Go to control panel, switch if necessary, to display small icons, then select `Администрирование`, then – `Службы`. A window will open: 

![](/images/pages/guides/flexberry-aspnet/services.png) 

2.In the list double-click on `ASP.NET State Service` (or `Служба States ASP.NET`). 

3.If the `Тип запуска` is set to `Вручную`, you need to change it to `Автоматически` and click `Запустить`. 

![](/images/pages/guides/flexberry-aspnet/settings-services.png) 

4.Click `Применить`, `ОК`. 

5.Close all Windows related to the control Panel. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Launch web apps](gpg_start-application.html) 
* [The definition of user roles and their functions](gpg_identifying-roles.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}