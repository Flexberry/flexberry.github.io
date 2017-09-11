---
title: ExtendedTextBox
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_extended-textbox.html
lang: ru
---

# ExtendedTextBox
Содержится в сборке `ICSSoft.STORMNET.Windows.Forms`.

Это контрол для организации [предиктивного ввода](fw_predict-input.html). Обычно используется для выбора мастерового объекта на основании некоторой строки. Самый распространённый случай - ввод ФИО: заводим справочник ФИО, настраиваем `ExtendedTextBox` на него и получаем быстрый выбор среди граждан, имеющихся в БД.

## Свойства
* Свойство `AutoOpenListForSuggestions` – при значении `true` автоматически открывается список для вариантов при вводе текста.

* Свойство `UseExtendedMask` – при значении `true` варианты предлагаются при вхождении введенного текста как подстроки. Должно использоваться совместно с `AutoOpenListForSuggestions = true;` и `MaskFormat=”%{0}%”`.


## Подробности
* [Поведение-ExtendedTextBox-GetCurrentObject|Поведение ExtendedTextBox.GetCurrentObject]