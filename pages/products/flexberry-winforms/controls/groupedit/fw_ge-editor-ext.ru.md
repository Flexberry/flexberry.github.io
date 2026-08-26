---
title: Редактирование детейлов на отдельной форме
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit, GEEditorExt
summary: Правила подключения компонента для редактирования детейлов на отдельной форме
toc: true
permalink: ru/fw_ge-editor-ext.html
lang: ru
---

`GEEditorExt` - расширение компонента [GroupEdit](fw_group-edit.html), позволяющее организовать следующее поведение:

1. На панель инструментов `GroupEdit` добавляются кнопки "Создать" и "Открыть"
2. При нажатии на кнопку "Открыть" поднимается форма редактирования, соответствующая типу объекта в `GroupEdit`. При поднятии формы данные, введённые в `GroupEdit`, отражаются на форме.
3. При нажатии на "Создать" поднимается форма редактирования детейла, соответствующая типу объекта в `GroupEdit`, при сохранении которого создаётся строчка в `GroupEdit`.
4. При сохранении детейла на форме редактирования не происходит запись в БД - детейл запишется в БД вместе со своим мастером при его сохранении.
5. В  случае, когда объект открыт на форме редактирования, запрещено его редактировать в `GroupEdit`(выдаётся предупреждение и выводится на передний план форма редактирования). Для согласованности отображения в `GroupEdit` изменённых на форме данных синхронизируется отображение данных при выходе с формы редактирования и при ее активации.

{% include important.html content="GEEditorExt не входит в стандартную поставку Flexberry Winforms." %}

## Как подключить GEEditorExt

Подключение `GEEditorExt` рассмотрено на примере: "Покупатель" - это [класс мастера](fd_key-concepts.html), "Покупка" - [класс детейла](fd_key-concepts.html).

![Пример диаграммы](/images/pages/products/flexberry-winforms/controls/groupedit/class-diagram_-customer-purchase2.jpg)

{% include note.html content="Для редактирования детейлов необходимо сгенерировать соответствующую форму редактирования (при быстрой прототипизации она не создаётся)." %}

1.В классе формы агрегатора объявляется переменную типа `GEEditorExt`:

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    IIS.AMS02.GEEditorExt extGEПокупки;
    //...
}
```

2.В метод `Edit` формы агрегатора добавляется код  по инициализации данной переменной. Конструктору класса `GEEditorExt` в качестве параметра передается [`GroupEdit`](fw_group-edit.html), функциональность которого планируется расширить.

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    IIS.AMS02.GEEditorExt extGEПокупки;
    public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
    {
        //...
        #region возможность создания и редактирования реагирований на отдельной формы из GE
                        if (this.extGEПокупки == null)
                                this.extGEПокупки = new IIS.AMS02.GEEditorExt(Покупки);
                #endregion возможность создания и редактирования реагирований на отдельной формы из GE
        //...
    }
    //...
}
```

{% include note.html content="Вместо пунктов 1 и 2, если не надо совершать дополнительных действий с кнопками (скрывать их, переименовывать и т.п.) можно использовать метод-расширение у GroupEdit." %}

3.В метод `GetEditor` формы агрегатора добавляется код, указывающий, какую форму поднимать для редактирования конкретного детейла:

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    public override Type GetEditor(ICSSoft.STORMNET.UI.EventType eventtype, ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
    {
        if ((propertyname == "Покупка"))
        {
            return System.Type.GetType("IIS.MasterField.C__ПокупкаE");
        }
        //...
    }
    //...
}
```

4.Переопределить метод `PromtUserForActionAtClose` формы детейла (это связанно с особенностью 1 класса `GEEditorExt`):

```csharp
public class WinformC__ПокупкаE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупкаE
{
    public override ICSSoft.STORMNET.UI.DialogResult PromtUserForActionAtClose()
    {
        if (EditManager.DataObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created &&
            EditManager.DataObject.DynamicProperties.ContainsKey("Псевдосохранен") ||
            EditManager.DataObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.UnAltered &&
            EditManager.DataObject.GetAlteredPropertyNames().Length == 0)
            return ICSSoft.STORMNET.UI.DialogResult.Yes;
        return base.PromtUserForActionAtClose();
    }
    //...
}
```

5.В независимой форме детейла переопределить метод вычитки из БД (для того, чтобы не вычитывался полностью объект при поднятии формы редактирования):

```csharp
public class C__ПокупкаE : ICSSoft.STORMNET.UI.BaseIndpdEdit
{
    protected override void PrepareDataObjectForEdit(ICSSoft.STORMNET.DataObject dobject)
    {
        // *** Start programmer edit section *** (PrepareDataObjectForEdit (DataObject) start)
        if (dobject.GetStatus(false) == ICSSoft.STORMNET.ObjectStatus.Altered)
        {
            m_objView = ICSSoft.STORMNET.Information.GetView("C__ПокупкаE", typeof(IIS.MasterField.Покупка));
            return;
        }
        // *** End programmer edit section *** (PrepareDataObjectForEdit (DataObject) start)
        m_objView = ICSSoft.STORMNET.Information.GetView("C__ПокупкаE", typeof(IIS.MasterField.Покупка));
        if ((dobject.GetStatus(false) != ICSSoft.STORMNET.ObjectStatus.Created)
                    || dobject.Prototyped)
        {
            ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(m_objView, dobject, false, false);
            dobject.InitDataCopy();
        }
        // *** Start programmer edit section *** (PrepareDataObjectForEdit (DataObject) end)

        // *** End programmer edit section *** (PrepareDataObjectForEdit (DataObject) end)
    }
    //...
}
```

Здесь предполагается, что представления для отображения детейла на форме редактирования и в [GroupEdit](fw_group-edit.html) совпадают (т.е. это одно и то же представление или представления с одинаковым набором атрибутов). Если представления разные, то код в скобках программиста в приведенном выше методе `PrepareDataObjectForEdit` надо заменить на такой:

```csharp
m_objView = ICSSoft.STORMNET.Information.GetView("C__ПокупкаE", typeof(IIS.MasterField.Покупка));
ICSSoft.STORMNET.View ge_View = ICSSoft.STORMNET.Information.GetView("C__ПокупкаD", typeof(IIS.MasterField.Покупка));
//дочитываем то, что есть на форме редактирования, но нет в GE
ICSSoft.STORMNET.View дочитать_View = m_objView - ge_View;
//из этого представления убираем свойства, которые могли быть изменены на форме редактирования,
//если она открывалась ранее
foreach (string altprop in dobject.GetAlteredPropertyNames())
    дочитать_View.RemoveProperty(altprop);
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(дочитать_View, dobject, false, false);
return;
```

Однако в данном случае (в случае разных представлений) проявится особенность: если открыть мастер, ничего на нем не менять, открыть детейл на редактирование и сразу закрыть форму, то она закроется без вопроса о сохранении, а форма мастера спросит о сохранении. Это связано с тем, что после вычитывания дополнительных свойств детейла его набор атрибутов в копии мастера будет отличаться от набора в самом мастере. Избавиться от этого можно ручным исправлением копии мастера перед открытием формы редактирования детейла.
