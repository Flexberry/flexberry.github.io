---
title: Свойство MaxLength элемента управления ExtLookUpTextControl
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_ext-lookup-text-control-max-length.html
folder: products/flexberry-winforms/
lang: ru
---

У `ExtLookUpTextControl` появилось свойство `MaxLength`, отвечающее на максимальную длину вводимого пользователем текста. 
Следует заметить, что в коде свойству Text можно задать значение, длина которого больше, чем длина значения, указанного свойством `MaxLength`, т.е. свойство влияет только на текст, вводимый в элемент управления во время выполнения. Другими словами по LookUp’у (и через предлагаемый контролом вариант) может быть выбран текст, превышающий `MaxLength`.

