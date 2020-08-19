--- 
title: Options to open the object read-only 
sidebar: flexberry-orm_sidebar 
keywords: DataObject ORM Flexberry 
summary: Variants of the block edit facility 
toc: true 
permalink: en/fo_read-only-object.html 
lang: en 
autotranslated: true 
hash: ce62df2406d2dc54cc15b5600bfa5072ec8ea189af6527f2d02161c1cab0b4b9 
--- 

### Option 1: proactive action 

If it becomes clear that the object will need to access read-only before it is opened, you can use the following example: 

```csharp
private void button1_Click(object sender, EventArgs e)
    { 
        ICSSoft.STORMNET.DataObject[] dObjs = objectListView1.GetObjectsByMarks();
        foreach (ICSSoft.STORMNET.DataObject d in dObjs)
        {
            //lock needed to unlock object (unlock the object with a different key will be impossible). 
            Guid key = new Guid();
            d.LockObject(key);
            OnEditEvent("", d, "");
        }
    }
``` 

In this case, the database lock will not be sent. 

### Option 2: the form is already open 

```csharp
 public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
        {
            if (dataobject == null)
                return;

            if (!((Квалификация)dataobject).Актуально)
            {
                this.SetEditManagerReadOnly(true);
                this.Caption += "(date)";
            }

            base.Edit(dataobject, contpath, propertyname, tag);
        }
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}