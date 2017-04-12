---
title: Операции AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_age-operations.html
lang: ru
---

## Описание операций

Для того чтобы можно было задавать допустимые операции и, как следствие, изменять внешний вид [AjaxGroupEdit](fa_ajax-group-edit.html), следует обращаться к свойству `Operations`.

| Операция | Описание |
|:------------|:-------------------------------------------------------------| 
| PlusInRow | Показывать ли в строке кнопку "Плюс", которая отменяет редактирование, сохраняя в полях введенные значения|
| Edit | Редактирование|
| Add | Добавление|
| Delete | Удаление|
| EditInRow | Показывать кнопку редактирования детейла в строке. Редактирование возможно в [нескольких режимах](fa_open-windows-age.html): в текущем, модальном и новом окне|
| AddNewRowOnTop | Показывать только что добавленную запись вверху списка (для AjaxGroupEdit)|


Например, если необходимо скрыть кнопку редактирования, то код может выглядеть следующим образом:

```csharp
ctrlMyWebGroupEdit.Operations.Delete = false;
```

{% include warning.html content="Так как по умолчанию для детейлов не создаются формы редактирования, то для включения опции `EditInRow` сначала необходимо вручную сгенерировать формы редактирования, а затем установить свойство `DetailEditForm`, чтобы `указать` GroupEdit'у где редактировать детейлы. Данную настройку следует устанавливать в методе `PostApplyToControls()`." %}

Пример:

```csharp
protected override void PostApplyToControls()
{
	ctrlПодзадача.DetailEditForm = ПодзадачаE.FormPath;
	ctrlПодзадача.Operations.EditInRow = true;

	Page.Validate();
}
```

{% include warning.html content="При открытии на редактирование из AGE выполняется сохранение объекта." %}
 