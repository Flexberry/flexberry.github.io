---
title: Поднятие из GroupEdit'a на лукап объекта, не имеющего ссылку на объект-инициатор
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: На примере показано как можно из GroupEdit вызвать на лукап список объектов, не имеющих связей c объектом-инициатором
toc: true
permalink: ru/fw_lookup-from-groupedit.html
folder: products/flexberry-winforms/
lang: ru
---

На _примере_ поднятия __Дежурной группы__ из GE "Дежурные группы смены" на форме Дежурной смены из поля "Номер группы":

Есть класс __ДежурнаяГруппаСмены__ и не связанный с ним (отношениями ассоциации или композиции) класс __ДежурнаяГруппа__. По лукапу номера группы нужно вызвать список ДежурныхГрупп для заполнения полей ДежурнойГруппыСмены из свойств выбранной ДежурнойГруппы.

Для этого:

* В методе __GetControl__ класса CustomControlProvide устанавливаем контрол для поля НомерГруппы:

```csharp
            if (view.Name #  "ДежурнаяГруппаСменыE" && propertyName  "НомерГруппы")
            {
                ICSSoft.STORMNET.Windows.Forms.LookUp lookUp = new ICSSoft.STORMNET.Windows.Forms.LookUp();

                ctrl = new ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct(lookUp, "Value");
            }
```

* Переопределяем метод __OnEdit__ в winformДежурнаяСменаE. Так как класс ДежурнаяГруппаСмены не имеет ссылки на ДежурнуюГруппу, то выбираем любой другой класс, имеющий такую ссылку, в нашем случае подойдет __ЧленДежурнойГруппы__.

То есть пишем :

```csharp
dataobject = new ЧленДежурнойГруппы(); //класс, имеющий ссылку на дежурную группу
propertyname = "ДежураяГруппа"; //имя ссылки

public override void OnEdit(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
        {
            if (dataobject is ДежурнаяГруппаСмены && propertyname == "ДежурныеГруппыСмены.НомерГруппы")
            {
                if (((ДежурнаяГруппаСмены)dataobject).ТипДежурнойГруппы != null)
                {
                    SQLWhereLanguageDef langdef = new SQLWhereLanguageDef();
                    tag = langdef.GetFunction(langdef.funcEQ,
                            new VariableDef(langdef.GuidType, "ТипДежурнойГруппы"), ((ДежурнаяГруппаСмены)dataobject).ТипДежурнойГруппы.__PrimaryKey);
                }
                dataobject = new ЧленДежурнойГруппы();
                propertyname = "ДежураяГруппа";
            }

            base.OnEdit(propertyname, dataobject, contpath, tag);
        }
```

* Переопределяем метод __Edited__ в winformДежурнаяСменаE. Записываем все необходимые свойства в ДежурнуюГруппуСмены из выбранной ДежурнойГруппы:

```csharp
public override void Edited(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
        {
            base.Edited(dataobject, contpath, propertyname);

            if (dataobject is ЧленДежурнойГруппы && propertyname == "ДежураяГруппа")
            {
                ДежурнаяГруппаСмены ДежГрСмены = (ДежурнаяГруппаСмены)ДежурныеГруппыСмены.EditManager.DataObject;
                ДежурнаяГруппа ДежГр = ((ЧленДежурнойГруппы)dataobject).ДежураяГруппа;
                ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject("ДежурнаяГруппаE", ДежГр);
                ДежГрСмены.НомерГруппы = ДежГр.НомерГруппы;
                ДежГрСмены.ТипДежурнойГруппы = ДежГр.ТипДежурнойГруппы;
                ДежГрСмены.Руководитель = ДежГр.Руководитель;
                ДежГрСмены.Комментарий = ДежГр.Комментарий;
                ДежГрСмены.Позывной = ДежГр.Позывной;
                ДежГрСмены.ЧленыДежГруппыСмены.Clear();

                for (int t = 0; t < ДежГр.ЧленыДежурнойГруппы.Count; t++)
                {
                    Объекты.ЧленДежурнойГруппы членДежГр = ДежГр.ЧленыДежурнойГруппы[t];
                    Объекты.ЧленДежГруппыСмены чдгс = new Объекты.ЧленДежГруппыСмены();
                    чдгс.ДежурнаяГруппаСмены = ДежГрСмены;
                    чдгс.НомерПП = членДежГр.НомерПП;
                    чдгс.Сотрудник = членДежГр.Сотрудник;
                    чдгс.Позывной = членДежГр.Позывной;
                    ДежГрСмены.ЧленыДежГруппыСмены.Add(чдгс);
                }
                ДежурныеГруппыСмены.EditManager.Change();
            }
        }
```