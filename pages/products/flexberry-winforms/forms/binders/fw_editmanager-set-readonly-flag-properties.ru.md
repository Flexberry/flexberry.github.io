---
title: Метод EditManager.SetReadonlyFlagProperties
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
summary: Указаны методы EditManager для работы со свойством ReadOnly контролов
toc: true
permalink: ru/fw_editmanager-set-readonly-flag-properties.html
folder: products/flexberry-winforms/
lang: ru
---
## Назначение

Метод [`EditManager`](fw_editmanager.html).`SetReadonlyFlagProperties` позволяет установить значение свойства `ReadOnly` у контролов, редактирующих поля, указанные в параметрах вызова данного метода.

```csharp
void EditManager.SetReadonlyFlagProperties(bool readonlyflag, params string[] properties)
```

Параметр `readonlyflag` определяет значение, которое будет установлено свойству ReadOnly у контролов, редактирующих поля, указанные в `properties`.

Пример использования данного метода представлен [здесь](fw_different-applications-and-fields.html).

## Метод AddControlsToForcedReadOnlyList

```csharp
/// <summary>
/// Добавить элементы в список контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Новые элементы.</param>
public void AddControlsToForcedReadOnlyList(List<Control> controlList)
```

### Пример использования

```csharp 
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
        {
            base.Edit(dataobject, contpath, propertyname, tag);
            if (DataObject != null)
            {
                EditManager.AddControlsToForcedReadOnlyList(new List<Control>() { ctrlФИО });
            }
        }
```


## Метод RemoveControlsFromForcedReadOnlyList

```csharp 
/// <summary>
/// Удалить элементы из списка контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Удаляемые элементы.</param>
/// <param name="readOnlyFlag">Флаг, который у удаляемых из списка элементов нужно проставить в свойство ReadOnly.</param>
public void RemoveControlsFromForcedReadOnlyList(List<Control> controlList, bool readOnlyFlag = false)
```