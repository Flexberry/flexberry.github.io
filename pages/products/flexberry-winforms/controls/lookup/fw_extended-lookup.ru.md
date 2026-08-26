---
title: Лукап с предиктивным вводом
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, LookUp, ExtendedLookUp
summary: Описание, свойства и события ExtendedLookUp
toc: true
permalink: ru/fw_extended-lookup.html
lang: ru
---

`ExtendedLookUp` представляет из себя лукап с предиктивным вводом. Находится в сборке `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`

## ExtendedLookUp в качестве лукапа

Как установить `ExtendedLookUp` в качестве лукапа описано в статье [Произвольный контрол в качестве лукапа](fo_custom-lookup.html).

Если необходимо установить `ExtendedLookUp` в качестве лукапа в [GroupEdit](fw_group-edit.html), то у соответствующего `GroupEdit` необходимо установить свойство `EnableValueDisplayResponsibility` в `true`.

## События

Событие `ValueSelected` срабатывает при нажатии Enter и закрытии списка.

## Свойства

### AutoCompleteBox.OrderColumns

Одним из составляющих компонентов `ExtendedLookUp` является `AutoComleteBox`, отвечающий за отображение подсказок по вводу во включённом режиме `AutoOpenListForSuggestions`.

Свойство `AutoCompleteBox.OrderColumns` определяет столбцы, по которым будет производиться сортировка, при определении порядка следования элементов в выводимой подсказке.

```csharp
ctrlКлиент.AutoCompleteBox.OrderColumns = new ColumnsSortDef[] {new ColumnsSortDef("Прописка", SortOrder.Asc) };
```

{% include note.html content="Задание `AutoCompleteBox.OrderColumns` возможно только в программном коде." %}

### MaxLength

Свойство `MaxLength` контрола `ExtLookUpTextControl`  отвечает за максимальную длину вводимого пользователем текста.

Следует заметить, что в коде свойству `Text` можно задать значение, длина которого больше, чем длина значения, указанного свойством `MaxLength`, т.е. свойство влияет только на текст, вводимый в элемент управления во время выполнения. Другими словами по LookUp (и через предлагаемый контролом вариант) может быть выбран текст, превышающий `MaxLength`.
