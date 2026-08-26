---
title: DatePicker
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, JavaScript API, Web UI (Контролы)
toc: true
permalink: ru/fa_date-picker.html
lang: ru
---

**DataPicker** - web-контрол для выбора даты и времени.

## Наиболее интересные свойства

* `public DataPickerDisplayMode DisplayMode { get; set; }`  - Показывать блок выбора даты при нажатии на кнопку рядом с полем, при фокусировке контрола или в обоих случаях. По умолчанию `OnButtonClicked`.

* `public bool OnlyDate { get; set; }`  - Использовать ли формат без времени (только дата). По умолчанию true

* `public string YearRange { get; set; }`  - Определяет диапазон лет, которые окажутся в выпадающем списке выбора года. Это свойство можно задать относительно текущего года в формате **-nn:+nn**, либо относительно отображаемого календарем года, в формате **c-nn:+c+nn**, либо конкретным диапазоном дат: **nnnn:nnnn**. Указанные варианты можно комбинировать: **nnnn:+nn**. Кроме указанных в этом свойстве ограничений, на диапазон дат в выпадающем списке даты будет влиять заданные максимальная и минимальная дата (свойства `minDate` и `maxDate`). Значение по умолчанию **c-10:c+10**.

* `public string BeforeShowFunctionName { get; set; }`  - Имя js-функции, выполняющейся перед показом контрола.

### Пример задания свойств

Свойства лучше задавать в методе `PostApplyToControls()`.

В следующем примере контролу задается диапазон лет, доступных для ввода:

```csharp
protected override void PostApplyToControls()
{
    ctrlOnlyDate.YearRange = "2001:2002";
}
```

А в этом примере контролу задается минутный шаг:

```csharp
protected override void PostApplyToControls()
{
    ctrlStepMinute.OnlyDate = false;
    ctrlStepMinute.StepMinute = 10; 
}
```

## Настройка формата даты

Чтобы настроить отображение выводимого формата даты, необходимо установить свойство `DateFormat` (и `TimeFormat`, если необходимо настроить формат времени). Установленный формат будет использоваться:

* При выводе информации на экран.
* При валидации данных.
* При сохранении информации в базу.

## Настройки отображения в AGE

Самый простой способ настроить отображение DatePicker для всех [AjaxGroupEdit'ах](fa_ajax-group-edit.html) приложения - создать метод, производящий необходимые действия над экземпляром web-контрола (который передан ему в параметрах), и присвоить его в статическое свойство `InitSettings` класса `DatePicker`. [Подробнее](fa_init-control-settings-delegate.html).

## Переопределение картинки появления календаря

Для переопределения картинки, которая служит кнопкой для появления/скрытия календаря необходимо переопределить CSS-класс `.ui-datepicker-trigger`.

## [JS API](fa_javascript-api.html)

Для манипуляций с DatePicker на стороне клиента следует использовать `DatePicker JS API`, который представляет собой jQuery плагин (`icsDatePicker`).

Пример использования: 

```javascript
$(document).ready(function () {
    $('#getValBtn').bind('click', function () {
        // получить значение
        var vl = $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val');
        alert(vl);
    });
    $('#setValBtn').bind('click', function () {
        // установить значение
        $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val', '01.01.2010');
    });
    $('#clearValBtn').bind('click', function () {
        // очистить значение
        $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val', '');
    });

});
```
