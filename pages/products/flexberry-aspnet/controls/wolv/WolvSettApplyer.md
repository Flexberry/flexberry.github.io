---
title: WolvSettApplyer
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-sett-applyer.html
folder: products/flexberry-aspnet/
lang: ru
---
Эта статья описывает часть информации о [WebObjectListView](web-object-list-view.html).

## Введение

WolvSettApplyer нужен для того, чтобы единообразно обрабатывать применение настроек к [WOLV](web-object-list-view.html) на всем проекте. Он поставляется вместе
с веб-шаблоном, но его можно изменять под конкретные нужды проекта.

## Задание операций для WOLV на форме LookUp'а

Если вы хотите управлять [WOLV](web-object-list-view.html) на форме [LookUp'а](look-up--overview.html), то вам необходимо использовать следующий код в `WolvSettApplyer`:

```cs
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
