---
title: Операции AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_a-g-e-operations.html
folder: products/flexberry-aspnet/
lang: ru
---



# Описание операций

Для того чтобы можно было задавать допустимые операции и, как следствие, изменять внешний вид [AjaxGroupEdit](ajax-group-edit.html), нужно обращаться к свойству Operations.


{| border="1"
|- 
! Операция
! Описание
|- 
| PlusInRow 
| Показывать ли в строке кнопку "Плюс", которая отменяет редактирование, сохраняя в полях введенные значения
|- 
| Edit 
| Редактирование
|- 
| Add 
| Добавление
|- 
| Delete 
| Удаление
|- 
| EditInRow 
| Показывать кнопку редактирования детейла в строке. Редактирование возможно в [нескольких режимах](example-open-windows-in-a-g-e.html): в текущем, модальном и новом окне.
|- 
| AddNewRowOnTop 
| Показывать только что добавленную запись вверху списка (для AjaxGroupEdit)
|}


Например, если необходимо скрыть кнопку редактирования, то код может выглядеть следующим образом:
```cs
ctrlMyWebGroupEdit.Operations.Delete = false;
```
(((<msg type=note>Так как по умолчанию для детейлов не создаются формы редактирования, то для включения опции `EditInRow` сначала необходимо вручную сгенерировать формы редактирования, а затем установить свойство `DetailEditForm`, чтобы "указать" GroupEdit'у где редактировать детейлы. Данную настройку следует устанавливать в методе `PostApplyToControls()`.

Пример: 
```cs
protected override void PostApplyToControls()
{
	ctrlПодзадача.DetailEditForm = ПодзадачаE.FormPath;
	ctrlПодзадача.Operations.EditInRow = true;

	Page.Validate();
}
```
</msg>)))

(((<msg type=note>При открытии на редактирование из AGE выполняется сохранение объекта.</msg>)))
 


 