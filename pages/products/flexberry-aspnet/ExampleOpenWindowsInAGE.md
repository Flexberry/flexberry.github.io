---
title:  Примеры открытия окна редактирования в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_example-open-windows-in-a-g-e.html
folder: products/flexberry-aspnet/
lang: ru
---

# Открытие в текущем окне
Для того, чтобы открыть форму редактирования в текущем окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.DetailEditForm в PostApplyToControls().

```cs
/// <summary>
/// Здесь лучше всего изменять свойства контролов на странице, которые не обрабатываются WebBinder.
/// </summary>
protected override void PostApplyToControls()
{
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    Page.Validate();
}
```
# Открытие в модальном окне
Для того, чтобы открыть форму редактирования в модальном окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.ModalWindowSettings в PostApplyToControls().

```cs
/// <summary>
/// Здесь лучше всего изменять свойства контролов на странице, которые не обрабатываются WebBinder.
/// </summary>
protected override void PostApplyToControls()
{
    ctrlКвартира.ModalWindowSettings = new ThickBoxSettings(450, 700, "450*700");
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInNewWindow = true;
}
```

# Открытие в новом окне
Для того, чтобы открыть форму редактирования в новом окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.DetailEditForm в PostApplyToControls().

```cs
/// <summary>
/// Здесь лучше всего изменять свойства контролов на странице, которые не обрабатываются WebBinder.
/// </summary>
protected override void PostApplyToControls()
{
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInNewWindow = true;
    Page.Validate();
}
```