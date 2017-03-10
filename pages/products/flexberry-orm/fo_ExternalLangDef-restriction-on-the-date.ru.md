---
title: ExternalLangDef - ограничение на дату/время
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_external-lang-def-restriction-on-the-date.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](fo_flexberry-orm.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* '''Программная библиотека''': ExternalLangDef.dll.
* '''Предназначение''': Общее описание доступных в [ExternalLangDef](fo_external-lang-def.html) методов задания [ограничений](fo_limit-function.html) на дату и время.
</td>
</tr></tbody></table></a>
</div>

# Ограничения на части даты
Ограничения на части даты используются, когда необходимо получить данные по части даты.

Функции для получения частей даты доступны в [ExternalLangDef](fo_external-lang-def.html).
В примерах ниже будем использовать следующий код:
```cs
var langdef = ExternalLangDef.LanguageDef;
var order = LoadingCustomizationStruct.GetSimpleStruct(typeof (Заказ), Заказ.Views.ЗаказL);
```
## Ограничение на год (funcYearPart)
'''Найти заказы, оформленные в 2014 году.'''

SQL-выражение выглядит следующим образом:
```

SELECT * FROM [dbo].[Заказ] WHERE YEAR([ДатаВыдачи]) = 2014
```

Через [ExternalLangDef](fo_external-lang-def.html)
```

order.LimitFunction = langdef.GetFunction(langdef.funcEQ, 
                                          langdef.GetFunction(langdef.funcYearPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "2014");
```

## Ограничение на месяц (funcMonthPart)
'''Найти заказы, оформленные в мае.'''

SQL-выражение выглядит следующим образом:
```

SELECT * FROM [dbo].[Заказ] WHERE Month([ДатаВыдачи]) = 05
```

Через [ExternalLangDef](fo_external-lang-def.html)
```

order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcMonthPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "05");
```

## Ограничение на день (funcDayPart)
'''Найти заказы, оформленные 13 числа.'''

SQL-выражение выглядит следующим образом:
```

SELECT * FROM [dbo].[Заказ] WHERE Day([ДатаВыдачи]) = 13
```

Через [ExternalLangDef](fo_external-lang-def.html)
```

order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcDayPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "13");
```

## Ограничение на час (funcHHPart)
'''Найти заказы, оформленные в 10 утра.'''

SQL-выражение выглядит следующим образом:
```

SELECT * FROM [dbo].[Заказ] WHERE DatePart(hh, [ДатаВыдачи]) = 10
```

Через [ExternalLangDef](fo_external-lang-def.html)
```

order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcHHPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "10");
```

## Ограничение на минуты (funcMIPart)
'''Найти заказы, оформленные в 20 минут.'''

SQL-выражение выглядит следующим образом:
```

SELECT * FROM [dbo].[Заказ] WHERE DatePart(MINUTE, [ДатаВыдачи]) = 20
```

Через [ExternalLangDef](fo_external-lang-def.html)
```

order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcMIPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "20");
```

Если необходимо точное время, то ограничение будет выглядеть так:
```

order.LimitFunction = langdef.GetFunction(
						  langdef.funcAND,
						  langdef.GetFunction(
								langdef.funcEQ, 
								langdef.GetFunction(
									langdef.funcHHPart, 
									new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))), 
								10),
						  langdef.GetFunction(
								langdef.funcEQ, 
								langdef.GetFunction(
									langdef.funcMIPart, 
									new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))), 
								20));
```

## Ограничение на дату (funcOnlyDate)
'''Вывести тех, у кого сегодня день рождения.'''

```

var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Человек>(x => x.ДатаРождения))),
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            langDef.GetFunction("TODAY")));
```

## Ограничение на время (funcOnlyDate)
'''Вывести тех, кто родился в 01:00:00.'''

```

var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Человек>(x => x.ДатаРождения))),
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            DateTime.Parse("01:00:00")));
```

## Ограничение на день недели (funcDayOfWeek)
Функция, возвращающая день недели числом. (1 = Понедельник, ..., 7 = Воскресенье)

'''Вывести только те числа которые попали на понедельник.'''

```cs
// Создаем ограничение на объект.
 lcs.LimitFunction = 
           // Ограничение, что функция возвращает объекты.  
           ldef.GetFunction(
                    // В которых значения параметра 1 (=) значению параметра 2.
                    ldef.funcEQ,
                    // Параметр 1: получаем даты и преобразовываем её в число.
                    ldef.GetFunction(
                        ldef.funcDayOfWeek,
                        new VariableDef(ldef.DateTimeType,
                            Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Параметр 2.
                    1);
```


## Ограничение на день недели (funcDayOfWeekZeroBased)
Функция, возвращающая день недели числом. (0 = Воскресенье, 1 = Понедельник, ...)

'''Найти все заказы которые были сделаны в Воскресенье.'''

```cs
// Создаем ограничение на объект.
LimitFunction =
        // Ограничение, что функция возвращает объекты.  
        ldef.GetFunction(
                   // В которых значения параметра 1 (=) значению параметра 2.
                   ldef.funcEQ,
                   // Параметр 1: Ограничение, что из поля "Дата", возвращаются день недели числом.
                   ldef.GetFunction(ldef.funcDayOfWeekZeroBased, new VariableDef(ldef.DateTimeType, "Дата")),
                   // Параметр 2: Возвращающийся день недели числом должен быть = 0 (Воскресенье).
                   0)
```


## Ограничение на разницу дат (funcDATEDIFF)
'''Пример в разработке.'''

## Ограничение на количество дней в месяце (funcDaysInMonth)
'''Пример в разработке.'''

## Полный аналог SQL функции dateadd (funcDateAdd)
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последний год.'''
```cs
// Создаем ограничение на объект.
LimitFunction  =
         // Ограничение, что функция возвращает объекты.  
         ldef.GetFunction(
                // В которых значения параметра 1 (<) значению параметра 2.
                ldef.funcL,
                // Параметр 1: Ограничение, что из поля "Дата", возвращаются дата значение года, которой увеличено на 1.
                ldef.GetFunction(
                    ldef.funcDateAdd, // Полный аналог SQL функции dateadd.
                    ldef.GetFunction(ldef.paramYearDIFF),
                    1,
                    new VariableDef(ldef.DateTimeType, "Дата")),
                // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
```

## Параметры для работы с датой.
#### paramTODAY - параметр для получения сегодняшней даты.
'''Найти все заказы которые были сделаны за прошедшие месяцы (+ текущий).'''
```cs
// Создаем ограничение на объект ("Дата"<=paramTODAY).
LimitFunction =
      // Ограничение, что функция возвращает объекты. 
      ldef.GetFunction(   
                   // В которых значения параметра 1 (<=) значению параметра 2.
                   ldef.funcLEQ, 
                   // Параметр 1: Ограничение, что из поля "Дата", возвращаются только значения месяца.
                   ldef.GetFunction(ldef.funcMonthPart, new VariableDef(ldef.DateTimeType, "Дата")),
                   // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только значения месяца.
                   ldef.GetFunction(ldef.funcMonthPart, ldef.GetFunction(ldef.paramTODAY)));
```
#### paramYearDIFF - параметр для получения значения года от даты.
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последний год.'''
```cs
// Создаем ограничение на объект.
lcs.LimitFunction = 
              // Ограничение, что функция возвращает объекты.  
              ldef.GetFunction(
                    // В которых значения параметра 1 (<) значению параметра 2.
                    ldef.funcL,
                    // Параметр 1: Ограничение, что из поля "Дата", возвращается значение года даты, которое увеличено на 1.
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramYearDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
```
#### paramMonthDIFF - параметр для получения значения месяца от даты.
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последний месяц.'''
```cs
// Создаем ограничение на объект.
lcs.LimitFunction = 
           // Ограничение, что функция возвращает объекты.  
           ldef.GetFunction(
                    // В которых значения параметра 1 (<) значению параметра 2.
                    ldef.funcL,
                    // Параметр 1: Ограничение, что из поля PoleDateTime, возвращается значение месяца даты, которое увеличено на 1.
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramMonthDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
```
#### paramWeekDIFF - параметр для получения значения недели от даты.
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последнюю неделю.'''
```cs
// Создаем ограничение на объект.
lcs.LimitFunction = 
             // Ограничение, что функция возвращает объекты.  
             ldef.GetFunction(
                    // В которых значения параметра 1 (<) значению параметра 2.
                    ldef.funcL,
                    // Параметр 1: Ограничение, что из поля PoleDateTime, возвращается значение недели даты, которое увеличено на 1.
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramWeekDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
```
#### paramQuarterDIFF - параметр для получения значения четверти от даты.
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последнюю четверть.'''
```cs
// Создаем ограничение на объект.
lcs.LimitFunction = 
             // Ограничение, что функция возвращает объекты.   
             ldef.GetFunction(
                    // В которых значения параметра 1 (<) значению параметра 2.
                    ldef.funcL,
                    // Параметр 1: Ограничение, что из поля PoleDateTime, возвращается значение четверти даты, которое увеличено на 1.
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramQuarterDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
```
#### paramDayDIFF - параметр для получения значения дня от даты
'''Найти все заказы которые были сделаны за все время, кроме тех которые были сделаны за последние три дня.'''
```cs
// Создаем ограничение на объект.
LimitFunction  =
         // Ограничение, что функция возвращает объекты.  
         ldef.GetFunction(
                // В которых значения параметра 1 (<) значению параметра 2.
                ldef.funcL,
                // Параметр 1: Ограничение, что из Даты берется только дни и их количество увеличивается на 3.
                ldef.GetFunction(
                    ldef.funcDateAdd, // Полный аналог SQL функции dateadd.
                    ldef.GetFunction(ldef.paramDayDIFF),
                    3,
                    new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                // Параметр 2: Ограничение, что из сегодняшней даты, возвращаются только дату(без времени).
                ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));             
```

