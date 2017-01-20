---
title: Как открыть объект только на чтение
sidebar: product--sidebar
keywords: DataObject (объекты данных), Windows UI (формы)
toc: true
permalink: ru/read-only-object.html
folder: product--folder
lang: ru
---
# Как открыть объект только на чтение 
## Вариант 1: упреждающее действие
Если становится понятно что объект нужно будет открыть только на чтение ещё до того как он будет открыт, то можно воспользоваться следующим примером:

```cs
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

## Вариант 2: форма уже открыта
```cs
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