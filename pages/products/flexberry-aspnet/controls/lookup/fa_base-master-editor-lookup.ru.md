---
title: BaseMasterEditorLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_base-master-editor-lookup.html
lang: ru
---

## Единообразное изменение свойств лукапов

Реализовано два статических делегата:
1. `InitLookUpSettings` - Делегат для инициализации настроек. Установленные настройки можно переопределять на aspx формах.
2. `ChangeLookUpSettings` - Делегат для смены настроек, при помощи которого можно привести все лукапы к единообразному виду.

Пример, нужно подписаться в Global.asax:

```csharp
BaseMasterEditorLookUp.ChangeLookUpSettings = AllForms.ChangeLookUpSettings;
```

```csharp
/// <summary>
/// Смена настроек лукапов
/// </summary>
/// <param name="lookup">Лукап, которому меняются настройки</param>
public static void ChangeLookUpSettings(BaseMasterEditorLookUp lookup)
{
    lookup.LookUpFormHeight = 640;
    lookup.LookUpFormWidth = 854;
    lookup.ShowInThickBox = true;
    lookup.LookUpFormCaption = "Выберете значение";
}
```
