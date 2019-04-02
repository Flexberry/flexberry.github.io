---
title: Валидация данных
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Designer, Flexberry Winforms
summary: Для windows-приложений перечислены уровни проверки данных (во время редактирования, при сохранении на форме, в бизнес-серверах), приведена их сравнительная характеристика
toc: true
permalink: ru/fw_edit-form-validation.html
lang: ru
---

Валидация данных происходит в несколько этапов.

* Проверки во время редактирования на форме.
* Проверки во время сохранения объекта.
* Проверки в [бизнес-сервере](fo_bs-wrapper.html).

{% include note.html content="Чтобы сгенерировать код формы редактирования (или списка), необходимо в свойствах класса со [стереотипом EditForm](fd_editform.html) установить флажок `GenerateDependedForm`." %}

| Приём | Преимущества | Недостатки|
|--|--|--|
| Во время редактирования | Позволяет оперативно сообщать пользователю о некорректности значения в каком-то поле | Не позволяет в полной мере проверять сложные взаимосвязи между полями объекта и другими объектами
| Во время сохранения | Позволяет проверять сложные взаимосвязи между полями объекта, поскольку проверка происходит единовременно | Проверка начинается только при попытке сохранения формы
| В бизнес-сервере | Позволяет проверять сложные взаимосвязи между полями объекта, а также связи с другими объектами |  Проверка начинается только при попытке сохранения формы

Общие замечания по реализации экранных форм редактирования можно посмотреть в статье [Рекомендации по доработке форм редактирования](fw_forms-recommendations.html).

## Проверка и подсветка полей на форме редактирования

`DataObjectErrorProvider` - компонент, позволяющий осуществлять проверку и подсветку полей на форме редактирования.

Основная последовательность действий для работы с `DataObjectErrorProvider` отображена в способах проверки обязательных полей на этапе редактирования и на этапе сохранения данных.

{% include important.html content="
`DataObjectErrorProvider` не входит в стандартную поставку Flexberry." %}

## Проверка данных на форме во время редактирования

Проверка данных на форме во время редактирования может осуществляться за счёт:

* [генерации исключения](fo_check-object-set.html) при неправильном вводе в методе `set` соответствующего поля объекта
* определения обязательных для заполнения полей на диаграмме классов через атрибут [NotNull](fo_attributes-class-data.html)
* проверки через `DataObjectErrorProvider`

| Приём | Преимущества | Недостатки|
|--|--|--|
| Генерация исключения при неправильном вводе в методе `set` соответствующего поля объекта | Позволяет организовать работу таким образом, что пользователь не выйдет из поля до тех пор, пока значение поля не будет введено корректно | Для начала проверки требуется, чтобы фокус попал на соответствующее поле|
| Определение обязательных для заполнения полей на диаграмме классов через атрибут `NotNull` | Позволяет в модели задать обязательные для заполнения поля <br> Ненавязчивое сигнализирование о незаполненности поля | Не позволяет определять поля, обязательные только в некоторых ситуациях|
| Проверка через `DataObjectErrorProvider` | Позволяет быстро прописать в коде перечень обязательных полей и пользователи приложения не смогут его менять, <br> Ненавязчивое сигнализирование о незаполненности поля  | Не позволяет пользователям менять условия проверки данных на форме|

## Проверка данных на форме во время сохранения

Проверка данных на форме во время сохранения осуществляется [через события OnSave/OnSaveEvent](fw_check-through-on-save-event-example.html) и может включать следующие элементы:

* Определение обязательных для заполнения полей на диаграмме классов через атрибут `NotNull`.
* Проверка через `DataObjectErrorProvider`.

| Приём | Преимущества | Недостатки |
|--|--|--|
| Определение обязательных для заполнения полей на диаграмме классов через атрибут `NotNull` | Позволяет в модели задать обязательные для заполнения поля | Не позволяет определять поля, обязательные только в некоторых ситуациях
| Проверка через `DataObjectErrorProvider` | + позволяет быстро прописать в коде перечень обязательных полей и пользователи приложения не смогут его менять | - не позволяет пользователям менять условия проверки данных на форме

_Пример использования всех методов:_

```csharp
protected override void OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) //метод OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) независимой формы
{
  System.Collections.ArrayList arl = new System.Collections.ArrayList();
  arl.AddRange(e.dataobject.CheckNotNullProperties(m_objView, true)); //проверяем заданные в модели NotNull-поля
  arl.AddRange((Editor as WinformВещьE).dataObjectErrorProvider1.GetNullProperties()); //проверяем заданные с помощью DataObjectErrorProvider обязательные поля
  if (arl.Count > 0)
  {
    System.Windows.Forms.MessageBox.Show("Остались незаполненными обязательные поля: " + Environment.NewLine + string.Join(", ", (string[])arl.ToArray(typeof(string))), "Внимание");
    (Editor as WinformВещьE).dataObjectErrorProvider1.FocusProperty(arl[0].ToString());
    m_bFailedSave = true;
    Editor.FailedSave(null);
    ((ICSSoft.STORMNET.UI.IDpdEditForm)Editor).SetStatusMessage(ICSSoft.STORMNET.UI.EditFormStatusMessage.ErrorInSave);
  }
  else
  {
    #region //проверка логических условий и подсветка через DataObjectErrorProvider
    SpecificControls.DataObjectErrorProvider errorProvider = null;
    if (Editor != null) errorProvider = (Editor as WinformВещьE).ValidationErrorProvider;
    if (!SQLValidationManagerIntegrator.CheckAllRulesDigitReport(e.dataobject, this, null, errorProvider))
    {
      ОтменитьСохранение();
      return;
    }
    #endregion
    base.OnSave(e); //сохранить, если все проверки пройдены
  }
}
```

## Проверка обязательных полей

Если поля одного класса на одной форме редактирования должны быть обязательно заполнены, а на другой нет, то для отображения и проверки обязательных для заполнения полей (не отмеченных в классе атрибутом `NotNull`) можно воспользоваться `DataObjectErrorProvider`.

### Порядок действий

* кинуть `DataObjectErrorProvider` на форму
* привязать `DataObjectErrorProvider` к `EditManager`, к которому привязаны проверяемые поля(DataObjectErrorProvider.EditManagerForBind)
* задать проверяемые поля в DataObjectErrorProvider.Properties

```csharp
//
// dataObjectErrorProvider1
//
this.dataObjectErrorProvider1.DpdEditContainer = this;
this.dataObjectErrorProvider1.EditManagerForBind = this.EditManager;
this.dataObjectErrorProvider1.Properties = new string[] {
  "НомерКУСП",
  "ТипКарточки",
  "ВремяЗаполнения"};
```

* связать DataObjectErrorProvider с объектом данных. Вызвать dataObjectErrorProvider1.BindToData(); в методе Edit
* для того чтобы отобразить ошибку для поля объекта (таракан), можно воспользоваться BindToData() или SetError() для определенного поля

```csharp
if (obj #  null || obj.ToString()  "")
    dataObjectErrorProvider1.SetError(property, EditManager.NotNullToolTip);
else
    dataObjectErrorProvider1.SetError(property, string.Empty);
```

* добавления полей в сообщение о не заполненности обязательных полей при сохранении. В независимой форме редактирования в методе OnSave() написать:

```csharp
System.Collections.ArrayList arl = new System.Collections.ArrayList();//массив, куда попадут заголовки, из указанного представления, незаполненные полей
arl.AddRange(obj.CheckNotNullProperties(m_objView, true));//обязательные поля объекта (отмеченных в классе атрибутом NotNull)
string[] s1 = form.dataObjectErrorProvider1.GetNullProperties();//обязательные поля, указанные в dataObjectErrorProvider1
for (int i = 0; i < s1.Length; i++)
   arl.Add(m_objView.GetProperty(s1[i]).Caption);
if (arl.Count > 0)
{
   form.dataObjectErrorProvider1.FocusFirstNullProperty();//устанавливаем фокус на первое в массиве не заполненное обязательное поле
   IIS.WinUI.Tools.ShowWarning("Остались незаполненными обязательные поля: " + Environment.NewLine + string.Join(", ", (string[])arl.ToArray(typeof(string))), "Внимание");
   ОтменитьСохранение();
   return;
}
```

## Итого

Самым предпочтительным вариантом является реализация проверок __на всех уровнях__.

## Сценарии доработки

* Настроить проверку обязательности полей.
* Добавить ограничение числа вводимых символов для строковых контролов и допустимый диапазон для числовых контролов.
* Проставить [значения по умолчанию](fo_features-dafault-value.html).
* Добавить проверку [уникальности номеров](fo_unique-data-check.html), [диапазонов дат](fo_func-between.html).
