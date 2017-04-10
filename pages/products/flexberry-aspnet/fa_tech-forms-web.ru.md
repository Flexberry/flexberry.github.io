---
title: Технологические формы Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_tech-forms-web.html
lang: ru
---

Технология Flexberry ASP.Net генерирует стандартные (универсальные) страницы для отображения и редактирования данных.

## Список технологических форм

### Страницы с настройкой через DynamicPageRoute

Часть технологических страниц доступна через [DynamicPageRoute](fa_routing.html):
* [Web-формы аудита](fa_audit-web-forms.html)
* Форма пользовательских блокировок (`routes.AddDynamicPageRoute("flexberry/LocksList", DynamicPageIdentifier.LocksList)`)
* Форма записей лога (`routes.AddDynamicPageRoute("flexberry/LogList", DynamicPageIdentifier.LogList)`)
* Форма версий используемых сборок (`routes.AddDynamicPageRoute("flexberry/Version", DynamicPageIdentifier.Version)`)
* LookUp-форма
* Другие

### Ограничение доступа к формам

Поскольку доступ к формам должен быть ограничен, то в файле web.config, чтобы дать доступ на технологические формы только пользователям, имеющим роль `admin`, можно добавить:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>

	...

  <!--Определяем доступ на технологические формы.-->
  <location path="flexberry">
    <system.web>
      <authorization>
        <!--Кодом ниже даётся доступ только админам. Чтобы закрыть доступ неавторизованным пользователям, можно воспользоваться конструкцией 'deny users="?"'.-->
        <allow roles="admin"/>
        <deny users="*"/>
      </authorization>
    </system.web>
  </location>
</configuration>
```

## Другие технологические формы

* [Lookup](fa_lookup-overview.html)-страница `ICSSoft.STORMNET.Web.AjaxControls.Forms.LookUpForm`

![](/images/pages/products/flexberry-aspnet/lookup-form.png)

* Страница настройки столбцов `ICSSoft.STORMNET.Web.AjaxControls.Forms.ChooseColumns`

![](/images/pages/products/flexberry-aspnet/column-setup-page.png)

{% include note.html content="Страница настройки столбцов идентична странице, используемой для [экспорта в Excel](fa_wolv-export-excel.html)." %}

* Страница печати (`ICSSoft.STORMNET.Web.AjaxControls.Forms.ListPrintForm`)
* Страница с информацией о версиях сборок

## Настройка технологических форм

Основная статья [Настройка технологиченских страниц Flexberry ASP.NET](fa_technological-forms-customization-example.html)

## Создание собственной страницы (на примере Lookup-страницы)

1. Создать собственный класс и понаследовать его от `ICSSoft.STORMNET.Web.Controls.LookUpForm`.
2. (опционально) Создать свой экземпляр фабрики страницы (класс, который будет создавать страницу). Вместо создания своей реализации можно использовать технологический класс `ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories<T>`.
3. Установить в `web.config` HTTP-handler для страницы

для HTTP-обработчика:

```xml
<add verb="*" path="LookUpForm.aspx" type=" MyProjectNamespace.LookUpFormHandlerFactoryType" validate="false"/>
```

для технологического HTTP-обработчика:

```xml
<add path="LookUpForm.aspx" 
       verb="*" 
       type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[MyProjectNamespace.LookUpFormType, MyProjectAssembly]], ICSSoft.STORMNET.Web.AjaxControls" 
       validate="false" />
```

{% include note.html content="Генератор автоматически создает и регистрирует прикладную лукап-форму для новых проектов." %}
