---
title: Сортировка в списковой форме, поднятой по лукапу
sidebar: flexberry-aspnet_sidebar
keywords: Web UI (Контролы)
toc: true
permalink: ru/fa_lookup-form-sort.html
lang: ru
---

Для того чтобы была возможность осуществлять сортировку на списковой форме, [поднятой по лукапу в web](fa_lookup-overview.html), необходимо сделать следующее:

1.Нужно удостовериться, что LookUp-форма правильная. Например, использовать стандартную пользовательскую форму, расположенную на тестовом стенде, тогда настройки в `web.config` должны быть следующие:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
	...
  <httpHandlers>
	...
      <add verb="*" path="LookUpForm.aspx" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, TestStand(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" validate="false" />
  </system.web>
  <system.webServer>
    <handlers>
		...
      <add name="LookUpForm" path="LookUpForm.aspx" verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, TestStand(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" resourceType="Unspecified" preCondition="integratedMode" />
    </handlers>
  </system.webServer>
</configuration>
```

2.Проверить, что на форме в методе ApplyWolvSettings используется [WOLVSettApplyer](fa_wolv-sett-applyer.html) прикладной доступности (а не расположенный в сборке AjaxControls) и что нет вызовов базового метода ApplyWolvSettings (данный вызов может перетереть настройки).

```cs
namespace ICSSoft.STORMNET.Web.Controls
{
    /// <summary>
    /// Класс прикладной формы лукапов.
    /// </summary>
    public class LookUpForm : AjaxControls.Forms.LookUpForm
    {
        /// <summary>
        /// Применение настроек к wolv лежащему на форме лукапа.
        /// Переопределенный метод реализует прикладную логику донастройки WOLV на форме.
        /// </summary>
        protected override void ApplyWolvSettings()
        {
            new WOLVSettApplyer().SettingsApply(LookUpFormWOLV);
        }
    }
}
```

3.В [WOLVSettApplyer](fa_wolv-sett-applyer.html) переопределить метод `ApplyLookUpSettings`. И назначить необходимую [операцию](fa_wolv-operations.html).

```csharp
namespace ICSSoft.STORMNET.Web
{
    /// <summary>
    /// Тюнер для WOLV'а доступный для прикладных программистов.
    /// </summary>
    public class WOLVSettApplyer : AjaxControls.WolvSettApplyer
    {
        /// <summary>
        /// Применить настройки для WOLV на форме лукапа. Вызывается, если в HTTP-запросе
        ///             присутствует параметр <see cref="P:ICSSoft.STORMNET.Web.Tools.WebParamController.OpenedFromLookupParamName"/>.
        /// </summary>
        /// <param name="wolv">WOLV, к которому нужно применить настройки.</param>
        public override void ApplyLookUpSettings(WebObjectListView wolv)
        {
            base.ApplyLookUpSettings(wolv);
            Type[] dataObjectTypes = wolv.DataObjectTypes;
            if (wolv.ID.Equals(LookUpForm.WebObjectListViewID)) // Накладываем требуемое условие.
            {
                wolv.Operations.UserColumnsSort = true;
            }
        }
    }
}
```
