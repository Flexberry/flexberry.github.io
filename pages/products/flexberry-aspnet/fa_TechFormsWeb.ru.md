---
title: Технологические формы Flexberry ASP.Net
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_tech-forms-web.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.Net](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Web-контролы и web-компоненты](web-controls.html)
* '''Предназначение''': Технологические формы представляют собой готовые формы, используемые для работы с общими задачами.
</td>
</tr></tbody></table></a>
</div>
# Технологические формы
Технология Flexberry ASP.Net генерирует стандартные (универсальные) страницы для отображения и редактирования данных.

О настройке технологических форм рассказано в [этой](technological-forms-customization-example.html) статье.

# Список форм
## Страницы с настройкой через DynamicPageRoute
Часть технологических страниц доступна через [DynamicPageRoute](routing.html):
* [Web-формы аудита](audit-web-forms.html).
* Форма пользовательских блокировок (`routes.AddDynamicPageRoute("flexberry/LocksList", DynamicPageIdentifier.LocksList)`).
* Форма записей лога (`routes.AddDynamicPageRoute("flexberry/LogList", DynamicPageIdentifier.LogList)`).
* Форма версий используемых сборок (`routes.AddDynamicPageRoute("flexberry/Version", DynamicPageIdentifier.Version)`).
* [Формы подсистемы полномочий](flexberry-asp-net-security-forms.html).
* Другие.

### Ограничение доступа к формам
Поскольку доступ к формам должен быть ограничен, то возможен следующий вариант ограничения доступа к технологическим формам:

В файле web.config, чтобы дать доступ на технологические формы только пользователям, имеющим роль "admin", можно добавить:
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
* [Lookup](look-up--overview.html)-страница `ICSSoft.STORMNET.Web.AjaxControls.Forms.LookUpForm`
![](/images/pages/img/TechForms/LookUpForm.png)
* Страница настройки столбцов `ICSSoft.STORMNET.Web.AjaxControls.Forms.ChooseColumns`
![](/images/pages/img/TechForms/ColumnSetupPage.png)

(((<msg type=note>Страница настройки столбцов идентична странице, используемой для [экспорта в Excel](w-o-l-v-export2-excel.html).</msg>)))

* Страница печати (`ICSSoft.STORMNET.Web.AjaxControls.Forms.ListPrintForm`)
* Страница с информацией о версиях сборок

# Создание собственной страницы (на примере Lookup-страницы)
# Создать собственный класс и понаследовать его от `ICSSoft.STORMNET.Web.Controls.LookUpForm`.
# (опционально) Создать свой экземпляр фабрики страницы (класс, который будет создавать страницу). Вместо создания своей реализации можно использовать технологический класс `ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories&lt;T&gt;`.
# Установить в `web.config` HTTP-handler для страницы

для своего HTTP-обработчика:
```
<add verb="*" path="LookUpForm.aspx" type=" MyProjectNamespace.LookUpFormHandlerFactoryType" validate="false"/>
```

для технологического HTTP-обработчика:
```
<add path="LookUpForm.aspx" 
       verb="*" 
       type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[MyProjectNamespace.LookUpFormType, MyProjectAssembly]], ICSSoft.STORMNET.Web.AjaxControls" 
       validate="false" />```

(((<msg type=note>Генератор автоматически создает и регистрирует прикладную лукап-форму для новых проектов</msg>)))
