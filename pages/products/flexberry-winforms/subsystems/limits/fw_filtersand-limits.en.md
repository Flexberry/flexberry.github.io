---
title: Filters and limits
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Restrictions
summary: Explains the concepts related to the concepts of filters and restrictions
toc: true
permalink: en/fw_filtersand-limits.html
lang: en
autotranslated: true
hash: 61a57fa2c2c8a8b2d96741b80c71ff7f136c425f58c76664069b69e3a5c330e1
---

## Filters

__Filters__ is a tool of imposing user restrictions on the object list.

All lists have the functionality for the imposition of filters. To download the list, use [view](fd_view-types.html) L and submission to T and D used for imposing on him restrictions. This is why L and T (D for detailov object) should contain the same set of attributes.

## The constraint editor

The constraint editor is used to create filters.

Read more about the editor can be viewed in [this article](fw_limit-editor-simple-view.html)

## The settings filter (FilterSettings)

To set [FilterSettings](fw_filter-settings.html) you must use Flexberry Administrative Console (AdmConsole).

If you use generator filters settings, you need to pay attention to the following points:

* It can be defined as [label](ffw_desktop-operations.html) [Desk](fw_app-desktop.html):

```csharp
arr.Add(new IIS.WinUI.Runners.EditFormRunner(typeof(IIS.Core.App_FilterSettingsGeneratorE), Administration, "Generator filters settings", "", new IIS.Core.App_FilterSettingsGenerator(), typeof(IIS.Core.App_FilterSettingsGenerator), false));
```

* In the config app should be lines like:

```xml
<add key="DefaultLibFolder" value="lib"/>
<add key="FilterSettingStrategy" value="reflection"/>
```

The Assembly of objects and forms in this case should be moved to the folder specified in `DefaultLibFolder`.

* The objects have to be [T view](fd_t-view.html).

* Once filter settings are generated, they must be connected as indicated [here](fw_filter-settings.html).


[FilterSettings](fw_filter-settings.html) specify settings for [datalow](fd_key-concepts.html) and lyapov (for example, without these settings when you set limits you cannot use [detaily](fd_key-concepts.html)).


After you set FilterSettings in the application database, which was set up AdmConsole will be filled with the following table:
* STORMFILTERSETTING (contains names of filters names of objects, which will apply these settings);
* STORMFILTERLOOKUP (contains settings for lyapov);
* STORMFILTERDETAIL (contains settings for [datalow](fd_key-concepts.html));

{% include note.html content="AdmConsole adequately [creates the settings automatically](fw_filter-settings.html) if the app is built for .Net Framework version 3.0 or 3.5. Alternative solution versions of frameworks can be an indication of a higher version of the framework](gbt_set-runtime-dotnet-version.html) for AdmConsole." %}

{% include note.html content="in order to limit `сам объект` work - you need to create STORMFILTERLOOKUP for this object." %}

## Form restrictions AdvLimit
[AdvLimit](fw_limitation-editform.html) allows you to set ограничения; to extend the functionality, you must configure FilterSettings (see [here](fw_filter-settings.html) and [here](fw_filter-example.html)).

## LimitFunction
[Property LimitFunction structure LoadingCustomizationStruct](fo_loading-customization-struct.html) you can programmatically define constraints (for example, see [here](fw_filtersettings-for-use-in-lists.html)).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}