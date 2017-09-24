---
title:  Определение контролов, доступных только на чтение, с помощью EditManager
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
toc: true
permalink: ru/fw_readonly-in-editmanager.html
folder: products/flexberry-winforms/
lang: ru
---

На форме редактирования иногда необходимы поля, которые блокируются через EditManager. Однако возникают ситуации, когда при использовании [EditManager.SetReadonlyFlagProperties](edit-manager-set-readonly-flag-properties.html) после сохранения объекта на какой-то время снимается блокировка и значение может быть изменено. Для предотвращения таких ситуаций существует метод '''AddControlsToForcedReadOnlyList''', блокирующий изменение флаг ReadOnly. Данный метод работает со списком контролов. Список в свою очередь можно редактировать: добавлять или удалять контролы.

# Метод AddControlsToForcedReadOnlyList

```cs
/// <summary>
/// Добавить элементы в список контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Новые элементы.</param>
public void AddControlsToForcedReadOnlyList(List<Control> controlList)
```
## Пример использования
```cs 
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
        {
            base.Edit(dataobject, contpath, propertyname, tag);
            if (DataObject != null)
            {
                EditManager.AddControlsToForcedReadOnlyList(new List<Control>() { ctrlФИО });
            }
        }
```

# Метод RemoveControlsFromForcedReadOnlyList
Данный метод позволяет удалить контролы, добавленные методом AddControlsToForcedReadOnlyList.
```cs 
/// <summary>
/// Удалить элементы из списка контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Удаляемые элементы.</param>
/// <param name="readOnlyFlag">Флаг, который у удаляемых из списка элементов нужно проставить в свойство ReadOnly.</param>
public void RemoveControlsFromForcedReadOnlyList(List<Control> controlList, bool readOnlyFlag = false)
```

----