---
title: Функции для работы с датой при задании ограничений
sidebar: flexberry-winforms_sidebar
keywords: Ограничения
summary: Список функций для работы с датой при задании ограничений, пример применения в коде
toc: true
permalink: ru/fw_date-time-funtions-in-limits.html
folder: products/flexberry-winforms/
lang: ru
---

| Название функции | Тип возвращаемого значения | Типы аргументов | Примечание
|--|--|--|--|
| СЕГОДНЯ | :Дата/Время | без параметров | Возвращает текущую дату вместе со временем
| ТолькоДата | :Дата/Время | :Дата/Время 
| ТолькоВремя | :Дата/Время | :Дата/Время 
| День недели | :Число | :Дата/Время  | 1 = Понедельник, 2 = Вторник и т.д.
| ГОД | :Число | :Дата/Время  
| МЕСЯЦ | :Число | :Дата/Время
| ДЕНЬ | :Число | :Дата/Время 
| ЧАС | :Число | :Дата/Время  
| Минута | :Число | :Дата/Время 
| РАЗНОСТЬ ДАТ | :Число | :ЧастьДаты, :Дата/Время, :Дата/Время | Например,  РАЗНОСТЬ ДАТ(ГОД(), ДатаРождения, ДатаСмерти)

''Примечание'': Чтобы получить, например, текущий день или текущее время нужно выполнить суперпозицию, соответственно, ТолькоДата(СЕГОДНЯ()) или ТолькоВремя(СЕГОДНЯ()).

## Пример: вывести тех, у кого сегодня день рождения

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            new VariableDef(langDef.DateTimeType, "ДатаРождения")),
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            langDef.GetFunction("TODAY")));
```

## Пример: вывести тех, кто умер в 01:00:00

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            new VariableDef(langDef.DateTimeType, "ДатаСмерти")),
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            DateTime.Parse("01:00:00")));
```
