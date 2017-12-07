---
title: Формат даты GroupEdit в режиме редактирования 
sidebar: flexberry-winforms_sidebar
keywords: DateTime (работа с датами)
summary: Рассмотрен способ задания формата даты в ячейке GroupEdit в режиме редактирования
toc: true
permalink: en/fw_groupedit-date-format-edit.html
folder: products/flexberry-winforms/
lang: en
---

Формат даты GroupEdit'а в режиме редактирования ячейки по умолчанию Short (зашито в код). Для изменения формата даты(например, на dd.MM.yyyy HH:mm)  в отдельно взятом GroupEdit'е следует в обработчике `SetupEditor` GroupEdit'а добавить код:

```csharp
if (e.control is ICSSoft.STORMNET.Windows.Forms.DateTimePicker)
{
    ICSSoft.STORMNET.Windows.Forms.DateTimePicker dtp=(ICSSoft.STORMNET.Windows.Forms.DateTimePicker)e.control;
    dtp.OnlyDate = false;

    dtp.Format = DateTimePickerFormat.Custom;
    dtp.CustomFormat = "dd.MM.yyyy HH:mm";
}
```