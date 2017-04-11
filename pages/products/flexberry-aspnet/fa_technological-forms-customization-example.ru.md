---
title: Примеры настройки технологических форм
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_technological-forms-customization-example.html
lang: ru
---

Настройки технологических форм [web-приложений](fa_flexberry-asp-net.html) осуществляются с помощью сервиса `DynamicPageTuner`. Класс зарегистрирован в секции `unity`.

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
      <...>
    <container>
      <...>         
      <!-- Конфигурация сервиса для кастомизации технологических форм в проекте. -->
      <register type="NewPlatform.Flexberry.Web.Page.IDynamicPageTuner, ICSSoft.STORMNET.Web.AjaxControls" mapTo="WebFormsTestStand.DynamicPageTuner, TestStand(ASP.NET Application)">
        <lifetime type="singleton" />
        <constructor />
      </register> 
      <...>         
    </container>
  </unity>
```

С его помощью можно:

* изменить внешний вид технологических форм,
* настроить заголовки форм,
* отложить загрузку списка,
* настроить порядок и отображение столбцов и многое другое. 

## Пример отложенной загрузки данных

```csharp
public class DynamicPageTuner : IDynamicPageTuner, IDynamicPageWolvTuner
{
   /// <summary>
   /// Пример реализации метода настройки <see cref="WebObjectListView" /> на технологических страницах.
   /// </summary>
   /// <param name="pageId">Идентификатор страницы.</param>
   /// <param name="wolv">Экземпляр <see cref="WebObjectListView" /> для настройки.</param>
   public void Tune(DynamicPageIdentifier pageId, WebObjectListView wolv)
   {
       // Для списка классов включена отложенная загрузка данных,
       // для остальных страниц ничего не меняется.
       if (pageId == DynamicPageIdentifier.SecurityClassesList)
       {
           if (!wolv.Page.IsPostBack)
               wolv.SkipDataLoad = true;
       }
    }
}
```

## Пример настройки отображения столбцов

```csharp
public class DynamicPageTuner : IDynamicPageTuner, IDynamicPageWolvTuner
{
   // Настройка порядка и количества столбцов на списке пользователей.
   if (pageId == DynamicPageIdentifier.SecurityUsersList)
   {
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Login), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Name), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Creator), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.CreateTime), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Enabled), true));
   }
}
```

Выглядит следующим образом:

![](/images/pages/products/flexberry-aspnet/example-teh-settings.png)
