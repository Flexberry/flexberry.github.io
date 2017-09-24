---
title: EditManager.Change
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
toc: true
permalink: ru/fw_edit-manager-change.html
folder: products/flexberry-winforms/
lang: ru
---

## Метод EditManager.Change

Если значение объекта данных изменено программным образом, то для обновления значения на форме необходимо сообщить [EditManager](edit-manager.html), что значение (либо весь объект изменились). Это делается вызовом у [EditManager](edit-manager.html) метода `Change()`. Если метод вызван без параметров, то обновляются все контролы. Если с параметром (именем свойства), то только контролы, «подвязанные» к указанному свойству. Если параметром указано имя мастера, то обновляются все контролы всех мастеровых свойств этого мастера.


Пример использования данного метода представлен [здесь](fo_features-dafault-value.html).

Особенность данного метода см. [Метод-EditManager-Change|здесь].
