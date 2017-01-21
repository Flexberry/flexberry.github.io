---
title: Поведение ExtendedTextBox.GetCurrentObject
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_actions--extended-text-box--get-current-object.html
folder: products/flexberry-winforms/
lang: ru
---

При вызове метода `GetCurrentDataObject` у `[ExtendedTextBox](extended-text-box.html)` происходит дополнение введенного слова, и объект данных будет возвращен для дополненного слова. Иногда возникают ситуации, когда такое поведение нежелательно (например, при нажатии backspace).
У `[ExtendedTextBox](extended-text-box.html)` появилась перегрузка метода `GetCurrentDataObject`, позволяющая указать необходимость дополнение введенного слова.
```cs
public DataObject GetCurrentDataObject(bool reloadItems)
```
При значении параметра `reloadItems` установленном в `false` догрузка не произойдет, при значении `true` вернется объект для дополненного слова.
