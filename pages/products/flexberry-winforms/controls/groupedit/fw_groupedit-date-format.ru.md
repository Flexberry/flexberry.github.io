---
title: Формат даты в GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit, DateTime
summary: Настройка режима редактирования и отображения даты и времени
toc: true
permalink: ru/fw_groupedit-date-format.html
lang: ru
---

### Режим редактирования

Формат даты [GroupEdit](fw_group-edit.html) в режиме редактирования ячейки по умолчанию `Short`. Для изменения формата даты (например, на dd.MM.yyyy HH:mm)  в отдельно взятом `GroupEdit` следует в обработчике `SetupEditor` добавить код:

```csharp
if (e.control is ICSSoft.STORMNET.Windows.Forms.DateTimePicker)
{
    ICSSoft.STORMNET.Windows.Forms.DateTimePicker dtp=(ICSSoft.STORMNET.Windows.Forms.DateTimePicker)e.control;
    dtp.OnlyDate = false;

    dtp.Format = DateTimePickerFormat.Custom;
    dtp.CustomFormat = "dd.MM.yyyy HH:mm";
}
```

### В режиме отображения

Формат даты в режиме отображения для отдельного `GroupEdit` задается в конструкторе формы:

```csharp
			C1.Win.C1FlexGrid.C1FlexGrid flex = Tools.GetFlexGrid(this.ДвижениеОтказа);
			string attributeName = "Дата";			
			try
			{
				flex.Cols[attributeName].Style.Format = "dd.MM.yyyy"; 
			}
			catch
			{
				Tools.ShowWarning("Не удалось установить формат даты для атрибута " + attributeName + 
							      " - для него будет использоваться формат даты по умолчанию");
			}
```

В данном примере `this.ДвижениеОтказа` имеет тип `GroupEdit`.

{% include note.html content="Порядковые номера столбцов НЕ РАВНЫ порядку отображения. Использовать имена столбцов (не названия). Имена можно узнать в Properties для GroupEdit, Атрибут Columns." %}
