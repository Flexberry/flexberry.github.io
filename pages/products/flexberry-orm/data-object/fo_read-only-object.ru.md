---
title: Варианты открытия объекта только на чтение
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM
summary: Варианты блокирования редактирования объекта
toc: true
permalink: ru/fo_read-only-object.html
lang: ru
---

### Вариант 1: упреждающее действие

Если становится понятно что объект нужно будет открыть только на чтение ещё до того как он будет открыт, то можно воспользоваться следующим примером:

```csharp
private void button1_Click(object sender, EventArgs e)
    { 
        ICSSoft.STORMNET.DataObject[] dObjs = objectListView1.GetObjectsByMarks();
        foreach (ICSSoft.STORMNET.DataObject d in dObjs)
        {
            //ключ блокировки нужен для разблокировки объекта (разблокировать объект другим ключом будет невозможно).
            Guid key = new Guid();
            d.LockObject(key);
            OnEditEvent("", d, "");
        }
    }
```

В этом случае блокировка в базу отправляться не будет.

### Вариант 2: форма уже открыта

```csharp
 public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
        {
            if (dataobject == null)
                return;

            if (!((Квалификация)dataobject).Актуально)
            {
                this.SetEditManagerReadOnly(true);
                this.Caption += " (неактуально)";
            }

            base.Edit(dataobject, contpath, propertyname, tag);
        }
```