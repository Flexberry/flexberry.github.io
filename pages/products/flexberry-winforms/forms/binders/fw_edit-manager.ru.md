---
title: Менеджер редактирования
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, формы, контролы, биндинг
summary: Определение, методы, способы создание прикладных форм, примеры
toc: true
permalink: ru/fw_editmanager.html
lang: ru
---

`EditManager` (`ICSSoft.STORMNET.Windows.Forms.Binders.EditManager`, менеджер редактирования) — специализированный класс для непрерывного связывания контролов со свойствами объекта данных.

## Метод EditManager.Change

Если значение объекта данных изменено программным образом, то для обновления значения на форме необходимо сообщить `EditManager`, что значение (либо весь объект изменились). Это делается вызовом у `EditManager` метода `Change()`. Если метод вызван без параметров, то обновляются все контролы. Если с параметром (именем свойства), то только контролы, «подвязанные» к указанному свойству. Если параметром указано имя мастера, то обновляются все контролы всех мастеровых свойств этого мастера.

Пример использования данного метода представлен в статье [Особенности задания значения по умолчанию](fo_features-dafault-value.html).

_Особенность:_ метод без параметра, либо с одним параметром-строкой не вызовет `AfterChangeProperties()`.

## Метод EditManager.SetReadonlyFlagProperties

На форме редактирования иногда необходимы поля, которые блокируются через EditManager. Однако возникают ситуации, когда при использовании `EditManager.SetReadonlyFlagProperties` после сохранения объекта на какой-то время снимается блокировка и значение может быть изменено. Для предотвращения таких ситуаций существует метод `AddControlsToForcedReadOnlyList`, блокирующий изменение флаг `ReadOnly`. Данный метод работает со списком контролов. Список в свою очередь можно редактировать: добавлять или удалять контролы.

```csharp
void EditManager.SetReadonlyFlagProperties(bool readonlyflag, params string[] properties)
```

Параметр `readonlyflag` определяет значение, которое будет установлено свойству ReadOnly у контролов, редактирующих поля, указанные в `properties`.

Пример использования данного метода представлен в статье [Формы и классы на рабочем столе приложения](fw_readonly-win.html).

## Метод AddControlsToForcedReadOnlyList

```csharp
/// <summary>
/// Добавить элементы в список контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Новые элементы.</param>
public void AddControlsToForcedReadOnlyList(List<Control> controlList)
```

__Пример:__

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

Данный метод позволяет удалить контролы, добавленные методом AddControlsToForcedReadOnlyList.

```csharp
/// <summary>
/// Удалить элементы из списка контролов, у которых EditManager не будет менять флаг ReadOnly.
/// </summary>
/// <param name="controlList">Удаляемые элементы.</param>
/// <param name="readOnlyFlag">Флаг, который у удаляемых из списка элементов нужно проставить в свойство ReadOnly.</param>
public void RemoveControlsFromForcedReadOnlyList(List<Control> controlList, bool readOnlyFlag = false)
```

## Создание формы редактирования объекта

Существует несколько способов "ручного" создания формы редактирования объекта с возможностью связать ее со свойствами существующего объекта данных.

### Связывание через программный код

1.Разместить необходимые элементы управления на форме;

2.Создать экземпляр класса `ICSSoft.STORMNET.Windows.Forms.Binders.EditManager` (менеджер редактирования — специализированный класс для непрерывного связывания контролов со свойствами объекта данных).

В конструкторе обязательным параметром является класс данных, на который настраивается менеджер редактирования.

_Пример:_

```csharp
em = new StormNetForms.Binders.EditManager(typeof(CDDD));
```

3.Связать контролы со свойствами объекта данных.

Для этого необходимо вызвать у [EditManager](fw_editmanager.html) метод `AddControl`. Параметрами передаются: структура `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct`.

_Примеры связывания контрола со свойством объекта данных:_

```csharp
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtName, "Text"), "Наименование");
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtCapacity, "Text"), "Объем");
```

4.Установить в свойство `EditManager.DataObject` объект данных, который требуется отредактировать.

После выполнения этих действий контролы будут подключены к свойствам объекта данных посредством `EditManager`, соответственно, когда пользователь будет редактировать значения в контролах, одновременно будут изменяться значения свойств объекта данных.

Если значение объекта данных изменено программным образом, то для обновления значения на форме необходимо выполнить `EditManager.Change`.

`EditManager` имеет события, позволяющие определить изменение значений свойств объекта данных. Это событие `BeforeChangePropertyValue`, срабатывающее перед установкой значения и `AfterChangeProperty`, срабатывающее после установки значения.

Также, можно связывать вручную намазанные контролы с другими контролами, предоставляющими свой `EditManager`, например с `GroupEditBase`, тогда можно обеспечить редактирование значений объекта данных, находящегося в списке, через внешние контролы.

### Связывание через окно редактирования свойств

Вместо того, чтобы конструировать EditManager из кода, его также можно «набросить» на форму как контрол и связать контролы со свойствами через стандартное окно редактирования свойств в среде Visual Studio.

Если в окне редактирования свойств EditManager в поле Bindings.<select view> по какой-то причине ничего нельзя выбрать, то в коде зависимой формы можно исправить строку:

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("", null, null);
```

на следующую, где указано, с каким представлением работает EditManager:

 ```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("C__КлиентE", typeof(IIS.TryFilter.Клиент), null);
```

Далее через поле Bindings.<Add> необходимо добавить нужные свойства объекта, после чего в появившихся ниже строчках определить для них контролы из списка. Через поле `Bindings.<Remove>` можно осуществить удаление свойства объекта из биндинга.

Для связывания полей ввода со свойствами объекта генерируется код, аналогичный следующему:

```csharp
Binds(string viewname, Type dataobjectType, OneBind[] binds) (параметры для создания объектов класса OneBind аналогичны параметрам структуры ControlForBindStruct).
this.editManagerMain.Bindings = new Binds("C__КлиентE", typeof(IIS.TryFilter.Клиент),
    new ICSSoft.STORMNET.Windows.Forms.Design.OneBind[]
        {
            new OneBind(this.textBoxClientFIO, typeof(System.Windows.Forms.TextBox), "Text", null, "ФИО"),
            new OneBind(this.textBoxClientAdress, typeof(System.Windows.Forms.TextBox), "Text", null, "Прописка")
        });
```

## Полезные ссылки

* [Как редактировать объекты данных на формах и связывать поля ввода со свойствами объекта данных с помощью EditManager](fw_edit-objects-on-forms.html).
* [LookUp](fw_lookup.html).
* Информация о [EditManager.ApplyDataFromControl](fw_editform.html).
