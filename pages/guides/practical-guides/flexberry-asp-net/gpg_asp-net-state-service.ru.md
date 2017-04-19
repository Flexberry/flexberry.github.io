---
title: Включение службы ASP.NET State Service
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_asp-net-state-service.html
lang: ru
---

Для корректной работы сгенерированного приложения необходимо, чтобы была запущена служба `ASP.NET State Service` (Служба состояний ASP.NET). Если при запуске сгенерированного веб-приложения выводится сообщение об ошибке, связанное с данной службой, необходимо:

1.Зайти на панель управления, переключиться при необходимости в режим отображения мелких значков, далее выбрать пункт `Администрирование`, далее – `Службы`. Откроется окно:
 
![](/images/pages/guides/flexberry-aspnet/services.png)

2.В списке дважды кликнуть по `ASP.NET State Service` (или `Служба состояний ASP.NET`).

3.Если в поле `Тип запуска` стоит значение `Вручную`, нужно изменить его на `Автоматически` и нажать кнопку `Запустить`.

![](/images/pages/guides/flexberry-aspnet/settings-services.png)
 
4.Нажать `Применить`, `ОК`.

5.Закрыть все окна, относящиеся к Панели управления.

## Перейти

*  <i class="fa fa-arrow-left" aria-hidden="true"></i> [Запуск веб-приложения](gpg_start-application.html)
* [Определение ролей пользователей и их функций](gpg_identifying-roles.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
