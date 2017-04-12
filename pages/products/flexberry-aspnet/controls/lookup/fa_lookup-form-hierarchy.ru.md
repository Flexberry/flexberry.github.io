---
title: Открытие лукап-формы с поддержкой иерархии 
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_lookup-form-hierarchy.html
lang: ru
---

Два способа, с помощью которых можно вывести данные на LookUp-форму в иерархическом виде:

* Использование свойства `LookUpFormURL`;
* Использование `WOLVSettApplyer`.

## Использование свойства LookUpFormURL

Контрол `MasterEditorAjaxLookUp` позволяет задавать URL той формы, которая будет отображаться для выбора элемента с путем указания свойства `LookUpFormURL`.

Прежде чем задавать значение данного свойства, необходимо убедиться, что списковая форма с поддержкой иерархии уже разработана, а также во избежание возможных ошибок указания адреса, для данной списковой формы, на которой данные отображаются в иерархическом виде, необходимо определить свойство `FormPath`, содержащее путь до списковой формы с иерархией.

К примеру, есть списковая форма `TestHierarchy.aspx`, у которой следующим образом задано свойство `FormPath`:

```csharp
/// <summary>
/// Путь до формы.
/// </summary>
public static string FormPath
{
    get
    {
        return "~/forms/Controls/WOLV/HierarchyTests/TestHierarchy.aspx";
    }
}
```

Тогда на той странице, на которой используется лукап, необходимо будет контролу-лукапу задать свойства `LookUpFormURL` следующим образом (помимо задания других основных необходимых настроек):

```csharp
lookupTest.LookUpFormURL = TestHierarchy.FormPath;
```

## Использование WOLVSettApplyer

Также можно воспользоваться специальным тюнером для WOLV, который позволяет задавать дополнительные настройки для WOLV на прикладном уровне, - `WOLVSettApplyer`, который в свою очередь находится в `корне` сборки с ASP.NET приложением.

Однако необходимо проверить, что в конфигурационном файле веб-приложения указан правильный обработчик для лукап-формы, а именно той, которая была сгенерирована. То есть, если имеется сборка `SomeAssambly(ASP.NET Application)`, то правильным указанием обработчика лукап-формы будут следующие:

__Для IIS 7:__

```xml
<handlers>
...
<add name="LookUpForm" path="LookUpForm.aspx" verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, SomeAssambly(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" resourceType="Unspecified" preCondition="integratedMode" />
...
</handlers>
```

__Для IIS 6:__

```csharp
<httpHandlers>
...
<add verb="*" path="LookUpForm.aspx" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, SomeAssambly(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" validate="false" />
...
</httpHandlers>
```
