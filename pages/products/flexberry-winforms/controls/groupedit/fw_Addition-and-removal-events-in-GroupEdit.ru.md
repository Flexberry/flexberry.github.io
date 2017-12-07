---
title: События добавления и удаления в GroupEditBase 
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Указаны способы перехвата событий добавления и удаления строк в GroupEditBase 
toc: true
permalink: ru/fw_addition-and-removal-events-in-groupedit.html
folder: products/flexberry-winforms/
lang: ru
---

Как в __GroupEditBase__ перехватить события удаления и добавления объекта и при определенных условиях эти события отменить? тока получением FlexGrida?

__Ответ__:

1) Можно управлять кнопкой удаления на тулбаре (показать, скрыть)

2) У FlexGrid есть событие перед удалением, в котором можно вернуть отмену удаления
