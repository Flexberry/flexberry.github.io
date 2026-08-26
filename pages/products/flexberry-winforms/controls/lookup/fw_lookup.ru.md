---
title: LookUp
sidebar: flexberry-winforms_sidebar
keywords:  Flexberry Winforms, Controls, LookUp
summary: Внешний вид, множественный выбор, переопределение методов, возможные проблемы использования и пути их решения
toc: true
permalink: ru/fw_lookup.html
lang: ru
---

`LookUp` (действие) - выбор мастерового объекта (проставление ссылки на мастеровой объект).

`LookUp` (контрол) - контрол для выбора мастерового объекта, возвращающий ссылку на него.

## Возврат нескольких объектов

Существует возможность получать все объекты, выбранные на списке по Lookup.

Это используется в [редакторе ограничений](fw_limit-editor-advanced-view.html), где для вида ограничения `Среди значений` возвращаются все выбранные на списке по лукапу объекты.

Все выбранные объекты можно получить, обратившись к `DynamicProperties` стандартно возвращаемого объекта. А именно:

```csharp
o.DynamicProperties["retFromObjectListView"] as List<DataObject>
```

Полученный список не будет содержать первого выбранного объекта `o`.

__Замечание:__ на формах, имеющих несколько [ObjectListView](fw_objectlistview.html) или `ObjectHierarchicalView`, нужно убрать перегрузку `prv_EditData` и подписку на `OLV.ReturnData`.

## Переопределить метода Edited

На примере показано как можно переопределить метод __Edited__, чтобы использовать полученные  от LookUp данные.

```csharp
public override void Edited(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
    {
        base.Edited (dataobject, contpath, propertyname);

        if (propertyname == "Сотрудник")
        {
            Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
            Объекты.Сотрудник р = (Объекты.Сотрудник)Э.Сотрудник;

            Э.ФИОСотрудника = Э.Сотрудник.ОпрПолноеФИО();
            if (р.Должность != null)
            {
                DataServiceProvider.DataService.LoadObject(р.Должность);
                Э.Должность = р.Должность.Наименование;
            }
            else Э.Должность = "";

            EditManager.Change("ФИОСотрудника");
            EditManager.Change("Должность");
        }

        if (propertyname == "Сотрудник")
        {
            Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
            Объекты.ОтвЗаРешение р = (Объекты.ОтвЗаРешение)Э.УКогоНаходится;

            if (р != null)
            {
                extTextControl1.Text = р.Наименование;
            }
            else extTextControl1.Text = "";
        }
    }
```

## Кнопка отображения списка

Для того чтобы добавить кнопку отображения мастерового списка к Lookup, нужно:

* Создать плоский контрол __button__
* Добавить иконку из __ImageList__.
* На нажатие кнопки добавить __вызов__ следующей функции:

```csharp
public void ПоказатьСписок()
    {
        SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
        Function lf = null;

        // формируем lf
        LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Лицо), "ЛицоL");
        lcs.LimitFunction = lf;

        ICSSoft.STORMNET.DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

        if (objs.Length == 0)
        {
            Tools.ShowInformation("Не найдено ни одного лица с заданными параметрами");
            return;
        }
        else // хоть кого-то нашли
        {
            string contpath = "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/TabControl(Tab" + "Control)/panel(TabPage)/panel(GroupBox)/ctrlЛицо(LookUp)";
            base.OnEdit("Лицо", EditManager.DataObject, contpath, lf);
        }
    }
```

## Изменение внешнего вида

Существует два способа изменить изображения на кнопках LookUp.

1.В случае если требуется изменить изображения в рамках всего приложения, необходимо инициализировать _статический ImageList_ при запуске приложения. В этом списке должны присутствовать изображения с именами "LookUp", "Clear" и "Edit".

__Пример:__

```csharp
LookUp.UserImageList.Images.Add("LookUp", Properties.Resources.LookUpImage);
LookUp.UserImageList.Images.Add("Clear", Properties.Resources.ClearImage);
LookUp.UserImageList.Images.Add("Edit", Properties.Resources.EditImage);
```

2.Для конкретного экземпляра `LookUp` с помощью свойства `UserImageList` можно назначить _пользовательский ImageList_, в котором также должны быть изображения с именами "LookUp", "Clear" и "Edit".

### Размер и границы кнопок

Размер кнопок задается с помощью свойства `ButtonSize`, при изменении данного свойства должно выполнится перевычисление размера всего контрола.

За отображение границ кнопок отвечает свойство `ButtonBorder`.

## Запрет изменения в BeforeChangeProperty

Уставновка признака Cancel в методе BeforeChangeProperty при изменении мастерового поля не блокирует Lookup и последующее изменение свойства.

## Смена обработчика look_LookUpEvent

Для того чтобы заменить обработчик `look_LookUpEvent` на свой, нужно:

* создать контейнер-runner,
* подписаться на события сохранения (отказа от сохранения),
* задать свои действия при наступлении событий.

```csharp
private void look_LookUpEvent1(object sender, System.EventArgs e)
{
    Посетитель oПосетитель = (Посетитель)this.EditManager.DataObject;
    
    Function lf = FunctionBuilder.BuildTrue();

    //создадим контейнер-раннер
    ICSSoft.АдресноеБюро.ЛичностьL FormЛичностьL = (ICSSoft.АдресноеБюро.ЛичностьL)      ICSSoft.STORMNET.UI.ContRunner.RunForm(typeof(ICSSoft.АдресноеБюро.ЛичностьL));
    FormЛичностьL.Edit(oПосетитель, "Личность", "Личность",lf);

    //подпишемся на события
    FormЛичностьL.SaveEvent+=new ICSSoft.STORMNET.UI.SaveEventArgsHandler(FormЛичностьL_SaveEvent);FormЛичностьL.CancelEvent+=new ICSSoft.STORMNET.UI.CancelEventArgsHandler(FormЛичностьL_CancelEvent);
}

private void FormЛичностьL_SaveEvent(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)
{
    //наши действия
}

private void FormЛичностьL_CancelEvent(object sender, ICSSoft.STORMNET.UI.CancelEventArgs e)
{
    //наши действия
}
```

## Возможные проблемы использования

### Стандартно сгенерированный lookup

#### На форме

Если не срабатывает обработчик события `look_LookUpEvent` (в зависимой форме).

* Правильно указать путь на форме до `Lookup` в методе `prv_TuneLookupInformations` (в зависимой форме).
* Указать форму вызова в методе `GetEditor` независимой формы.
* На кнопку `LookUp` (например, для Гражданства это будет ctrlГражданство) навесить обработчик __события__ LookUp.

```csharp
this.ctrlГражданство.LookUpEvent += look_LookUpEvent
```

#### В GroupEdit

* Привязка [GropEdit](fw_group-edit.html) к EditManager формы
* Наличие обработчика `this.gr_SetupEditorEventHandler` типа `OnSetupEditorEventHandler` для события `SetupEditor`
* Упоминание `GroupEdit` в методе `prv_TuneLookupInformations` (в зависимой форме), _например_:

```csharp
m_arrGroupEditInformations.Add(
               new ICSSoft.STORMNET.UI.GroupEditInformation(this.ПредметыИзъятия,
               "ИтогиИзъятия.ПредметыИзъятия",
               "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/TabControl(TabControl)/panel(TabPage)/ПредметыИзъятия(GroupEditBase)"));
```

### Нестандартные случаи использования Lookup

#### Не поднимается форма по Lookup

Нужно учесть особенности контрола:

* Если не существует свойство, имя которого передаётся в качестве `PropertyName`, то лучше указать пустую строку, иначе метод `Save` не будет вызываться.
* Нехранимые объекты, поднимаемые по Lookup, scriptizer обрабатываются нестандартно и событие необходимо прописывать вручную.

Также следует проверить:

* Хранимый ли объект, с которого поднимается LookUp.
* Какие формы поднимаются и с какой формы.
* Поднимается ли с формы по кнопке LookUp. Если да, то поднимается ли с формы LookUp. Если да, то _неправильно указываются параметры_. Если не поднимается ни по кнопке, ни из метода, то есть ошибка, скорее всего, носит системный характер.

#### Не выдаётся сообщение о сохранении объекта при закрытии формы

Необходимо проверить:

* Объект имеет статус _Altered_
* Объект изменил свои свойства с момента поднятия формы по Lookup. При поднятии по Lookup форма-инициатор в методе `PrepareDataObjectForEdit` создаёт копию данных, и при закрытии сравнивается эта копия с текущим на момент закрытия объектом. Сохранение произойдёт только в случае наличия разницы между объектом и его копией.
* Форма корректно поднята по Lookup:
  * передаётся объект формы-инициатора LookUp, а не тот, который надо редактировать
  * указано существующее свойство или пустая строка

## Изменение названия списка для Lookup

Изменить название списка для Lookup, находящегося на разных формах редактирования можно задать условия на форме редактирования, а на списковой форме добавить обработчик данного события. Например, на  форме редактирования при открытии Lookup в объект данных добавить необходимое название в `DynamicProperties`, а на списковой форме его вычитывать.

## Дополнительно

* [Лукап с предиктивным вводом](fw_extended-lookup.html)
* [Вызов независимого мастера](fw_lookup-another-object.html)
