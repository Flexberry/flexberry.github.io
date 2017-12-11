---
title: Возврат нескольких объектов по лукапу
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы), Ограничения
summary: Указаны особенности получения всех объектов, выбранных на списке по лукапу
toc: true
permalink: ru/fw_return-multiple-objects-lookup.html
folder: products/flexberry-winforms/
lang: ru
---

Существует возможность получать все объекты, выбранные на списке по лукапу.
Это используется в редакторе ограничений, где для вида ограничения "`Среди значений`" теперь возвращаются все выбранные на списке по лукапу объекты.


Все выбранные объекты можно получить, обратившись к DynamicProperties стандартно возвращаемого объекта. А именно:

```csharp
o.DynamicProperties["retFromObjectListView"] as List<DataObject>
```

Полученный список не будет содержать первого выбранного объекта `o`.


__Замечание__ относительно форм, имеющих несколько ObjectListView или ObjectHierarchicalView. Дабы возврат нескольких объектов работал на таких формах, нужно убрать перегрузку `prv_EditData` и подписку на `OLV.ReturnData`.