---
title: Формат даты GroupEdit в режиме редактирования 
sidebar: product--sidebar
keywords: DateTime (работа с датами)
toc: true
permalink: ru/Формат-даты--group-edit-в-режиме-редактирования.html
folder: product--folder
lang: ru
---

Формат даты GroupEdit'а в режиме редактирования ячейки по умолчанию Short (зашито в код). Для изменения формата даты(например, на dd.MM.yyyy HH:mm)  в отдельно взятом GroupEdit'е следует в обработчике `SetupEditor` GroupEdit'а добавить код:

```cs
if (e.control is ICSSoft.STORMNET.Windows.Forms.DateTimePicker)
{
    ICSSoft.STORMNET.Windows.Forms.DateTimePicker dtp=(ICSSoft.STORMNET.Windows.Forms.DateTimePicker)e.control;
    dtp.OnlyDate = false;

    dtp.Format = DateTimePickerFormat.Custom;
    dtp.CustomFormat = "dd.MM.yyyy HH:mm";
}
```