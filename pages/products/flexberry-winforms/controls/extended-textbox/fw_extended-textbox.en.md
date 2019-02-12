---
title: Предиктивный ввод значений
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, ExtendedTextBox
summary: Свойства контрола и настройка дополнения ввденных слов
toc: true
permalink: en/fw_extended-textbox.html
lang: en
---

Содержится в сборке `ICSSoft.STORMNET.Windows.Forms`.

Это контрол для организации [предиктивного ввода](fw_predict-input.html). Обычно используется для выбора мастерового объекта на основании некоторой строки.

Самый распространённый случай - ввод ФИО: завести справочник ФИО, настроить `ExtendedTextBox` на него и получить быстрый выбор среди людей, имеющихся в БД.

## Свойства

* Свойство `AutoOpenListForSuggestions` – при значении `true` автоматически открывается список для вариантов при вводе текста.

* Свойство `UseExtendedMask` – при значении `true` варианты предлагаются при вхождении введенного текста как подстроки. Должно использоваться совместно с `AutoOpenListForSuggestions = true;` и `MaskFormat=”%{0}%”`.

## Дополнение введенных слов

При вызове метода `GetCurrentDataObject` у `ExtendedTextBox` происходит дополнение введенного слова, и объект данных будет возвращен для дополненного слова. Иногда возникают ситуации, когда такое поведение нежелательно (например, при нажатии backspace).
У `ExtendedTextBox` существует перегрузка метода `GetCurrentDataObject`, позволяющая указать необходимость дополнение введенного слова.

```csharp
public DataObject GetCurrentDataObject(bool reloadItems)
```

При значении параметра `reloadItems` установленном в `false` догрузка не произойдет, при значении `true` вернется объект для дополненного слова.
