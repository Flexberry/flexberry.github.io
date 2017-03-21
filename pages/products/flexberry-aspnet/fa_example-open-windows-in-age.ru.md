---
title:  Примеры открытия окна редактирования в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_example-open-windows-in-a-g-e.html
folder: products/flexberry-aspnet/
lang: ru

---

* **Платформа:** [FlexberryASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент:** [Операции AjaxGroupEdit](fa_a-g-e-operations.html)
* **Предназначение:** Возможность открытия web-формы редактирования в AjaxGroupEdit в разных режимах. 

## Варианты открытия web-формы редактирования в AGE

Редактирование записей [AjaxGroupEdit](fa_ajax-group-edit.html) возможно в разных режимах:

1. В текущем окне;
2. В модальном окне;
3. В новом окне.

### Открытие в текущем окне

Для того, чтобы открыть [web-форму редактирования](fa_flexberry-asp-net-edit-form.html  ) в текущем окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.DetailEditForm в PostApplyToControls().

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

### Открытие в модальном окне

Для того, чтобы открыть [web-форму редактирования](fa_flexberry-asp-net-edit-form.html) в модальном окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.ModalWindowSettings в PostApplyToControls().

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

### Открытие в новом окне

Для того, чтобы открыть [web-форму редактирования](fa_flexberry-asp-net-edit-form.html) в новом окне, следует на форме редактирования мастера указать ctrl<Имя детейла>.DetailEditForm в PostApplyToControls().

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

{% include note.html content="Если [web-форма редактирования](fa_flexberry-asp-net-edit-form.html) открывается в отдельной вкладке (OpenEditorInNewWindow=1, OpenEditorInModalWindow=0), детейл можно редактировать и на вкладке с [AGE](fa_ajax-group-edit.html), и на странице редактирования.
Это можно решить, например, блокировкой строки в [AGE](fa_ajax-group-edit.html) пока открыта другая вкладка." %}
