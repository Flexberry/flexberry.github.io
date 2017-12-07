---
title: Свойство MaxLength элемента управления ExtLookUpTextControl
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Указаны особенности свойства MaxLength компонента ExtLookUpTextControl
toc: true
permalink: en/fw_ext-lookup-text-control-max-length.html
folder: products/flexberry-winforms/
lang: en
---

Свойство `MaxLength` контрола `ExtLookUpTextControl`  отвечает на максимальную длину вводимого пользователем текста. 
Следует заметить, что в коде свойству Text можно задать значение, длина которого больше, чем длина значения, указанного свойством `MaxLength`, т.е. свойство влияет только на текст, вводимый в элемент управления во время выполнения. Другими словами по LookUp’у (и через предлагаемый контролом вариант) может быть выбран текст, превышающий `MaxLength`.

