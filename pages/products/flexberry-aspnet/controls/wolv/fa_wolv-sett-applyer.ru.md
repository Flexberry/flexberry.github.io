---
title: WolvSettApplyer
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-sett-applyer.html
lang: ru
---

WolvSettApplyer нужен для того, чтобы единообразно обрабатывать применение настроек к [WOLV](fa_web-object-list-view.html) на всем проекте. Он поставляется вместе с Flexberry ASP.NET, но его можно изменять под конкретные нужды проекта.

## Задание операций для WOLV на форме LookUp'а

Если требуется изменять [WOLV](fa_web-object-list-view.html) на форме [LookUp'а](fa_lookup-overview.html), то необходимо использовать следующий код в `WolvSettApplyer`:

```csharp
/// <summary>
/// Метод по умолчанию должен быть пустым (всё должно быть в самом WOLV-e инициализироваться)
/// Прикладные программисты сами дописывают логику сюда.
/// </summary>
/// <param name="wolv"></param>
public void SettingsApply(WebObjectListView wolv)
{
  ...
  if (wolv.ID.Equals(LookUpForm.WebObjectListViewID))
  {
    // Это WOLV LookUpForm
  }
  ...
}
```
