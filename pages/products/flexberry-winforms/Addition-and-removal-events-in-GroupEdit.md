---
title: События добавления и удаления в GroupEditBase 
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/addition-and-removal-events-in--group-edit.html
folder: product--folder
lang: ru
---

Как в '''GroupEditBase''' перехватить события удаления и добавления объекта и при определенных условиях эти события отменить? тока получением FlexGrida?

'''Ответ''':

1) Можно управлять кнопкой удаления на тулбаре (показать, скрыть)

2) У FlexGrid есть событие перед удалением, в котором можно вернуть отмену удаления
