---
title: Как редактировать объекты данных на формах, связывание полей ввода со свойствами объекта данных 
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
summary: Пошаговая инструкция по "ручному" созданию формы редактирования объекта и связыванию на ней контролов со свойствами объекта данных
toc: true
permalink: en/fw_edit-data-objects-on-forms.html
folder: products/flexberry-winforms/
lang: en
---

В статье описано, что необходимо сделать для того, чтобы «вручную» создать форму, где есть возможность редактирования объекта данных.

## Связывание через программный код

1) Разместить необходимые элементы управления на форме;

2) Создать экземпляр класса ICSSoft.STORMNET.Windows.Forms.Binders.EditManager (менеджер редактирования — специализированный класс для непрерывного связывания контролов со свойствами объекта данных).

В конструкторе обязательным параметром является класс данных, на который настраивается менеджер редактирования.

__Пример__: 

```csharp
em = new StormNetForms.Binders.EditManager(typeof(CDDD));
```

3) Связать контролы со свойствами объекта данных. 
Для этого необходимо вызвать у EditManager метод AddControl. Параметрами передаются: структура ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct Примеры связывания контрола со свойством объекта данных: 

```csharp
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtName, "Text"), "Наименование");
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtCapacity, "Text"), "Объем");
```

4) Установить в свойство EditManager.DataObject объект данных, который требуется отредактировать.

После выполнения этих действий контролы будут подключены к свойствам объекта данных посредством EditManager, соответственно, когда пользователь будет редактировать значения в контролах, одновременно будут изменяться значения свойств объекта данных.

Если значение объекта данных изменено программным образом, то для обновления значения на форме необходимо выполнить EditManager.Change.

EditManager имеет события, позволяющие определить изменение значений свойств объекта данных. Это событие BeforeChangePropertyValue, срабатывающее перед установкой значения и AfterChangeProperty, срабатывающее после установки значения.

Также, можно связывать вручную намазанные контролы с другими контролами, предоставляющими свой EditManager, например с GroupEditBase, тогда можно обеспечить редактирование значений объекта данных, находящегося в списке, через внешние контролы.

## Связывание через окно редактирования свойств

Вместо того, чтобы конструировать EditManager из кода, его также можно «набросить» на форму как контрол и связать контролы со свойствами через стандартное окно редактирования свойств в среде Visual Studio. 

Если в окне редактирования свойств EditManager в поле Bindings.<select view> по какой-то причине ничего нельзя выбрать, то в коде зависимой формы можно исправить строку: 

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("", null, null);
```

на следующую, где указано, с каким представлением работает EditManager: 
 ```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("C__КлиентE", typeof(IIS.TryFilter.Клиент), null);
```

Далее через поле Bindings.<Add> необходимо добавить нужные свойства объекта, после чего в появившихся ниже строчках определить для них контролы из списка. 
Через поле Bindings.<Remove> можно осуществить удаление свойства объекта из биндинга.

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