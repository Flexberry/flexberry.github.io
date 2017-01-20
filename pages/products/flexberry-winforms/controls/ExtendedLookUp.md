---
title: ExtendedLookUp
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/extended-look-up.html
folder: product--folder
lang: ru
---
# ExtendedLookUp
'''ExtendedLookUp''' представляет из себя лукап с предиктивным вводом. Находится в сборке `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`

# ExtendedLookUp в качестве лукапа
Как установить `ExtendedLookUp` в качестве лукапа описано в статье [Произвольный контрол в качестве лукапа](generate-lookup--i-lookup.html). 

Если необходимо установить `ExtendedLookUp` в качестве лукапа в [GroupEdit](group-edit.html), то у соответствующего [GroupEdit](group-edit.html) необходимо [установить свойство `EnableValueDisplayResponsibility` в `true`](displaying-master-in--group-edit.html).

# События
Событие `ValueSelected` срабатывает при нажатии Enter и закрытии списка.

# AutoComleteBox
Одним из составляющих компонентов `ExtendedLookUp` является `AutoComleteBox`, отвечающий за отображение подсказок по вводу во включённом режиме `AutoOpenListForSuggestions`.

## AutoCompleteBox.OrderColumns
Свойство `AutoCompleteBox.OrderColumns` определяет столбцы, по которым будет производиться сортировка, при определении порядка следования элементов в выводимой подсказке. 

```cs
ctrlКлиент.AutoCompleteBox.OrderColumns = new ColumnsSortDef[] {new ColumnsSortDef("Прописка", SortOrder.Asc) };
```


(((
<msg type=note>В настоящее время задание `AutoCompleteBox.OrderColumns` возможно только в программном коде.</msg>
)))

 

----