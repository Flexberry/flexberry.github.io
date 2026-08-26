---
title: DecimalTextBox
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_decimal-textbox.html
lang: ru
---

ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox - это [web-контрол](fa_web-controls.html), представляющий собой TextBox, на который наложен фильтр ввода для валидации положительных вещественных чисел в форме десятичной дроби. Работа с ним аналогична работе с обычным TextBox'ом, так как это его наследник. Для разделения целой и дробной части используется точка или запятая.

`DecimalTextBox` генерируется на [web-форме редактирования](fa_editform.html), если у свойства объекта выставлен тип Double, Decimal или Single, а так же Nullable-аналоги этих типов. Для этих типов на списковой форме и web-форме редактирования удаляются незначащие нули.

При вставке из буфера значений, которые не являются положительными вещественными числами, в контроле отображается "0".

## Свойства

| Наименование | Тип | Описание|
|---|---|---|
| MaxDecimalPlaces | Int | Максимальное число знаков после десятичного разделителя. Если значение отрицательное, то ограничение на количество знаков после запятой не накладывается.|

## Особенности использования

Когда компонент `DecimalTextBox` используется для отображения вещественных чисел, для которых в базе данных используется тип с фиксированным количеством знаков после запятой (в том числе с 0 знаками после запятой), необходимо устанавливать свойство MaxDecimalPlaces для корректного отображения числа в компоненте.

На данный момент [DataObject](fo_data-object.html) не хранит метаданные о количестве знаков после запятой для вещественных типов C#, поэтому при генерации приложений нет возможности автоматизировать инициализацию свойства MaxDecimalPlaces.

Если не инициализировать свойство `MaxDecimalPlaces` в указанном выше случае, то на форме редактирования после изменения свойств существующего объекта данных в компоненте `DecimalTextBox` и последующего сохранения данных без закрытия формы, вещественное число может отображаться не корректно - отображаемое количество знаков после запятой может не соответствовать тому количеству знаков, которое указано для соответствующего типа в базе данных (отображаться будет то значение, которое ввел пользователь перед сохранением). При добавлении нового объекта данных такого поведения не наблюдается, поскольку выполняется дополнительный запрос к серверу для получения значения первичного ключа у нового объекта данных, в результате чего происходит повторное считывание объекта данных перед отображением на форме (при этом значения полей отображаются так, как они хранятся в базе данных).

Возможно, в будущих версиях [Flexberry Designer](fd_flexberry-designer.html) появится возможность указывать и хранить количество знаков после запятой для вещественных типов C# на уровне метаданных, тогда необходимость в ручной установке свойства MaxDecimalPlaces отпадет.

## DecimalTextBox в AjaxGroupEdit

По умолчанию для редактирования вещественных чисел в [AjaxGroupEdit](fa_ajax-group-edit.html) используется обычный TextBox.
Чтобы редактирование проходило с помощью DecimalTextBox, требуется произвести соответствующие настройки через [WebControlProvider](fa_web-control-provider.html).

Например, если требуется редактировать свойство Свойство5 класса SeveralViewsDetail с помощью контрола DecimalTextBox, то в [WebControlProvider.xml](fa_web-control-provider.html) нужна следующая запись:

 ```csharp
<customproperty class="SeveralViewsDetail" property="Свойство5">
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox, ICSSoft.STORMNET.Web.AjaxControls" codefile="" property="Text"/>
</customproperty>
 ```

Чтобы в `AjaxGroupEdit` изменить значения свойств `DecimalTextBox`, можно воспользоваться делегатом.

Например, если свойство `Свойство5` класса `SeveralViewsDetail` редактируется в рамках представления SeveralViewsDetailD1 и требуется ограничить количество цифр после запятой тремя, то требуется определить соответствующий делегат:

```csharp
ICSSoft.STORMNET.Web.AjaxControls.AjaxGroupEdit.SetControlTuner(SeveralViewsDetail.Views.SeveralViewsDetailD1.Name, TuneControlDelegate);
```

```csharp
/// <summary>
/// Делегат для донастройки контрола в AGE.
/// В данном случае для свойства <see cref="SeveralViewsDetail.Свойство5"/>
/// для контрола <see cref="ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox"/>
/// выставляем максимальное количество символов после запятой "3".
/// </summary>
/// <param name="control">Текущий подобранный контрол.</param>
/// <param name="createdControlData">Параметры для контрола.</param>
private void TuneControlDelegate(Control control, CreatedControlData createdControlData)
{
  string searchedProperty = ICSSoft.STORMNET.Information.ExtractPropertyPath<SeveralViewsDetail>(x => x.Свойство5);
  if (createdControlData.ControlCreationReason == CreatedControlData.CreateControlReason.Edit
    && createdControlData.DataObjectType == typeof(SeveralViewsDetail)
    && createdControlData.PropertyName == searchedProperty)
  {
    if (!(control is ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox))
    {
      throw new System.Exception(
        string.Format(
          "Для корректного функционирования теста {0} требуется, чтобы для редактирования свойства {1} использовался контрол {2}.",
          typeof(TestDecimalAge).Name,
          searchedProperty,
          typeof(ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox).Name));
    }

    var decimalTextBox = (ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox)control;
    decimalTextBox.MaxDecimalPlaces = 3;
  }
}
```
