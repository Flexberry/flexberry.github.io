---
title: ExtendedLookUp
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_extended-lookup.html
folder: products/flexberry-winforms/
lang: ru
---
# ExtendedLookUp
'''ExtendedLookUp''' представляет из себя лукап с предиктивным вводом. Находится в сборке `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`

# ExtendedLookUp в качестве лукапа
Как установить `ExtendedLookUp` в качестве лукапа описано в статье [Произвольный контрол в качестве лукапа](fo_custom-lookup.html). 

Если необходимо установить `ExtendedLookUp` в качестве лукапа в [GroupEdit](fw_group-edit.html), то у соответствующего [GroupEdit](fw_group-edit.html) необходимо [установить свойство `EnableValueDisplayResponsibility` в `true`](fw_displaying-master-in-groupedit.html).

# События
Событие `ValueSelected` срабатывает при нажатии Enter и закрытии списка.

# AutoComleteBox
Одним из составляющих компонентов `ExtendedLookUp` является `AutoComleteBox`, отвечающий за отображение подсказок по вводу во включённом режиме `AutoOpenListForSuggestions`.

## AutoCompleteBox.OrderColumns
Свойство `AutoCompleteBox.OrderColumns` определяет столбцы, по которым будет производиться сортировка, при определении порядка следования элементов в выводимой подсказке. 

```csharp
ctrlКлиент.AutoCompleteBox.OrderColumns = new ColumnsSortDef[] {new ColumnsSortDef("Прописка", SortOrder.Asc) };
```


{% include note.html content="В настоящее время задание `AutoCompleteBox.OrderColumns` возможно только в программном коде." %}