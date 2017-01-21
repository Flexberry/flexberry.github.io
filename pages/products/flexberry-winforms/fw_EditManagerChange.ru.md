---
title: EditManager.Change
sidebar: product--sidebar
keywords: Flexberry Winforms
toc: true
permalink: ru/fw_edit-manager-change.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))

# Метод EditManager.Change
Если значение объекта данных изменено программным образом, то для обновления значения на форме необходимо сообщить `[EditManager](edit-manager.html)`, что значение (либо весь объект изменились). Это делается вызовом у `[EditManager](edit-manager.html)` метода `Change()`. Если метод вызван без параметров, то обновляются все контролы. Если с параметром (именем свойства), то только контролы, «подвязанные» к указанному свойству. Если параметром указано имя мастера, то обновляются все контролы всех мастеровых свойств этого мастера.


Пример использования данного метода представлен [здесь](features-of-dafault-value-assignment.html).

Особенность данного метода см. [Метод-EditManager-Change|здесь].
----