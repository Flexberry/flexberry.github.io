---
title: DatePicker
sidebar: product--sidebar
keywords: Flexberry ASP-NET, JavaScript API, Web UI (Контролы)
toc: true
permalink: ru/date-picker.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''DatePicker'''

* '''Платформа''': [FlexberryASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Web-контролы и web-компоненты](web-controls.html)
* '''Предназначение:''' Web-контрол для выбора даты и времени
* '''JavaScript API:''' да 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_date_picker.html)'''.
* '''[DatePicker на тестовом стенде](http://ru:6158/forms/Controls/DatePicker/)'''.

</td>
</tr></tbody></table></a>
</div>



# Описание
`DataPicker` - web-контрол для выбора даты и времени.

## Наиболее интересные свойства
    `public [DataPickerDisplayMode](http://storm:20013/namespace_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls.html#a38a08ac4e13eda35df0027d0ea553e7b) DisplayMode { get; set; } ` - Показывать блок выбора даты при нажатии на кнопку рядом с полем, при фокусировке контрола или в обоих случаях. По умолчанию `OnButtonClicked`.

    `public bool OnlyDate { get; set; } ` - Использовать ли формат без времени (только дата). По умолчанию `true`

    `public string YearRange { get; set; } ` - Определяет диапазон лет, которые окажутся в выпадающем списке выбора года. Это свойство можно задать относительно текущего года в формате '''`-nn:+nn`''', либо относительно отображаемого календарем года, в формате '''`c-nn:+c+nn`''', либо конкретным диапазоном дат: '''`nnnn:nnnn`'''. Указанные варианты можно комбинировать: '''`nnnn:+nn`'''. Кроме указанных в этом свойстве ограничений, на диапазон дат в выпадающем списке даты будет влиять заданные максимальная и минимальная дата (свойства `minDate` и `maxDate`). Значение по умолчанию '''`c-10:c+10`'''.

    `public string BeforeShowFunctionName { get; set; } ` - Имя js-функции, выполняющейся перед показом контрола.

Остальные свойства доступны [здесь](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_date_picker.html).

### Пример задания свойств
Свойства лучше задавать в методе `PostApplyToControls()`.

В следующем примере контролу задается диапазон лет, доступных для ввода:
```
        protected override void PostApplyToControls()
        {
            ctrlOnlyDate.YearRange = "2001:2002";
        }
```
А в этом примере контролу задается минутный шаг:
```
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
Самый простой способ настроить отображение DatePicker для всех [AjaxGroupEdit'ах](ajax-group-edit.html) приложения - создать метод, производящий необходимые действия над экземпляром web-контрола (который передан ему в параметрах), и присвоить его в статическое свойство InitSettings класса DatePicker. [Подробнее](init-control-settings-delegate.html).


## Переопределение картинки появления календаря
Для переопределения картинки, которая служит кнопкой для появления/скрытия календаря необходимо переопределить CSS-класс `.ui-datepicker-trigger`.

# [JS API](java-script-a-p-i.html)
Для манипуляций с `DatePicker` на стороне клиента следует использовать DatePicker JS API, который представляет собой jQuery плагин (icsDatePicker).

Пример использования: 
```cs
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

