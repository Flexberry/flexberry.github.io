---
title: Провайдер контролов для Flexberry Winforms
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, binders, ControlProvider
summary: Понятие, виды, соответствие типа данных и контрола, создание провайдера контролов
toc: true
permalink: en/fw_control-provider-winforms.html
lang: en
---

Провайдеры контролов - классы, наследующиеся от абстрактного `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider`, предназначенные для привеления в соответствие типа контрола и типа объекта данных. Например, на универсальной [форме редактирования](fw_editform.html).

У класса `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider` имеется метод `GetControl`, возвращающий структуру `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct`, в которой и указывается соответствие контрола типу значения.

Существует так называемый _стандартный провайдер контролов_ `ICSSoft.STORMNET.Windows.Forms.Binders.StandardControlProvider`, в котором метод `GetControl` перегружен таким образом, что возвращает контролы некоторым предопределённым образом.

## Контролы, возвращаемые StandardControlProvider

| Тип | Контрол
|--|--
| string| System.Windows.Forms.TextBox |
| System.Decimal<br>System.Double<br> System.Int16<br>System.Int32<br>System.Int64<br>System.SByte<br>System.Single<br>System.UInt16<br>System.UInt32<br>System.UInt64| System.Windows.Forms.TextBox
| System.DateTime| ICSSoft.STORMNET.Windows.Forms.DateTimePicker
| bool| System.Windows.Forms.CheckBox
| Enum| ICSSoft.STORMNET.Windows.Forms.ExtendedComboBox
| ICSSoft.STORMNET.DetailArray| ICSSoft.STORMNET.Windows.Forms.GroupEditBase
| ICSSoft.STORMNET.DataObject| ICSSoft.STORMNET.Windows.Forms.ComboLookup или (в зависимости от параметров) ICSSoft.STORMNET.Windows.Forms.LookUp.LookUp

Если требуется, чтобы редактировался другой тип, или другим контролом, необходимо определить собственный провайдер контрола и ассоциировать его с типом.

Существует несколько наиболее распространённых ситуаций:

* Настройка контрола для редактирования значения стандартного типа;
* Редактирование нестандартного типа стандартным контролом;
* Редактирование нестандартного типа нестандартным контролом.

## Описание собственного провайдера контролов

На самом деле, во всех случаях, создание контрола происходит через стандартный провайдер контролов, однако предварительно стандартный провайдер проверяет ассоциированный с типом провайдер контрола. Стандартный провайдер возвращает предопределённые контролы только тогда, когда нет другого ассоциированного провайдера, или метод ассоциированного провайдера вернул `null` либо `ControlForBindStruct.Empty`.

Для того чтобы создать собственный провайдер контролов, необходимо унаследоваться от `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider` и переопределить метод `GetControl`. Он имеет параметры:

* `string ApplicationType` — тип приложения (некоторая строка, идентифицирующая тип пользовательского интерфейса);
* `Type type` — тип, значения которого нужно редактировать контролом;
* `ICSSoft.STORMNET.View view` — представление, в котором находится объект данных;
* `string propertyName` — имя свойства объекта данных, которое нужно редактировать.

Пример можно посмотреть в статье [DateTimePicker](fw_datetime-picker.html).

Возвращать контрол можно в зависимости от комбинации значений этих параметров, т.е. гибко настраивать пользовательский интерфейс.

Метод возвращает структуру `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct` при конструировании которой указывают:

* `System.Object control` — экземпляр контрола, который будет редактировать значение;
* `System.String controlPropName` — имя значимого свойства контрола, т.е. то, в которое устанавливается и возвращается значение;
* `System.Type[] typeMapping` — мапирование (цепочка явных, либо неявных преобразований) типов, используется в случае, когда значимое свойство не поддерживает напрямую нужный тип, но поддерживает другой, к которому нужный тип может преобразовываться. Если это мапирование указано, тогда при установке значения в свойство контрола преобразование происходит последовательно, по указанным типам, начиная с начала массива, а если обратно (при установке из свойства контрола в свойство объекта данных), то с конца массива.

__Примечание__: связывание контрола со значением происходит через стандартный `.Net`-биндинг. Т.е. связываемый контрол должен «понимать» тип значений.

## Ассоциирование провайдера контролов с типом

После того как провайдер создан, необходимо ассоциировать его с типом. Для этого служит атрибут `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProviderAttribute`. Параметром указывается тип провайдера.

_Например:_

```csharp
[ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider(typeof(ДеньгиTextBoxControlProvider))]
    public struct Деньги //Кстати, пример пользовательского типа
    {
        //И т.д.
```

Очевидно, что данный способ удобен для нестандартных типов. Но как быть со стандартными типами, к ним ведь никак нельзя приписать атрибут?

Существует ещё один механизм, установка провайдера контролов, обрабатывающего все типы. Чтобы выполнить это, следует установить в статическое свойство `StandardControlProvider.ControlProviderForNotCustomizedTypes` провайдер контролов.

_Пример:_

```csharp
StormNetForms.Binders.StandardControlProvider.ControlProviderForNotCustomizedTypes=new РесурсControlProvider();
```

## Настройка контрола для редактирования значения стандартного типа

Необходимо реализовать провайдер контролов и установить его в статическое свойство `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## Редактирование нестандартного типа стандартным контролом

Необходимо реализовать провайдер контролов с указанием мапирования типов и ассоциировать его с типом, либо установить его в статическое свойство `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## Редактирование нестандартного типа нестандартным контролом

Необходимо создать контрол для редактирования значений нестандартного типа.

Для этого:

* Реализовать контрол как наследник от `ICSSoft.STORMNET.Windows.Forms.Binders.BindableUserControl`
* Контрол обязательно должен иметь значащее свойство и событие, сигнализирующее об изменении значения, с именем `ХХХХХChanged`, где ХХХХХ — имя значащего свойства. Событие обязательно должно взводиться при изменении значения значащего свойства.
* Контрол может имплементировать интерфейс `ICSSoft.STORMNET.Windows.Forms.ICustomizableControl` для более точной настройки в зависимости от класса данных, представления, имени свойства.
* Контрол также может имплементировать интерфейс `ICSSoft.STORMNET.Windows.Forms.IButtonizableControl` специально для более удобного ввода значений через `ICSSoft.STORMNET.Windows.Forms.GroupEditBase`.

Далее необходимо реализовать провайдер контролов и ассоциировать его с типом, либо установить его в статическое свойство `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## Общие замечания по провайдерам контролов

Поскольку тип и прочие параметры приходят в перегружаемый метод `GetControl` провайдера, разумеется, нет необходимости делать по одному провайдеру для каждого контрола. Можно использовать один на несколько типов или создать один провайдер контролов на всю систему и установить его в `StandardControlProvider.ControlProviderForNotCustomizedTypes`.
