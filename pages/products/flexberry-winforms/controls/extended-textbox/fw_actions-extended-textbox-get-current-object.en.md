---
title: Поведение ExtendedTextBox.GetCurrentObject
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Рассматривается перегрузка метода GetCurrentDataObject элемента управления ExtendedTextBox
toc: true
permalink: en/fw_actions-extended-textbox-get-current-object.html
lang: en
---

При вызове метода `GetCurrentDataObject` у [ExtendedTextBox](fw_extended-textbox.html) происходит дополнение введенного слова, и объект данных будет возвращен для дополненного слова. Иногда возникают ситуации, когда такое поведение нежелательно (например, при нажатии backspace).
У [ExtendedTextBox](fw_extended-textbox.html) появилась перегрузка метода `GetCurrentDataObject`, позволяющая указать необходимость дополнение введенного слова.

```csharp
public DataObject GetCurrentDataObject(bool reloadItems)
```

При значении параметра `reloadItems` установленном в `false` догрузка не произойдет, при значении `true` вернется объект для дополненного слова.
