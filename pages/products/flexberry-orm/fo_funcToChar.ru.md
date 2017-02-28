---
title: funcToChar
sidebar: flexberry-orm_sidebar
keywords: DateTime (работа с датами), Flexberry ORM, Public
toc: true
permalink: ru/fo_func-to-char.html
---
##funcToChar
funcToChar - функция из ExternalLangDef, служащая для того, чтобы задать преобразование выражения в строку. Это бывает необходимо для корректного сравнения значений выражений со строковыми константами. На данный момент реализована только для MSSQLDataService и OracleDataService.

##Использование

Функция может использоваться в двух вариантах:
* С двумя аргументами, где первый - какое-либо выражение, возвращающее значение, а второй - число, означающее длину результирующей строки.
* С тремя аргументами, где первый - выражение, имеющее тип DATETIME, второй - длина строки, а третий - номер формата для отображения даты. Для задания последнего параметра можно использовать перечисление ExternalLangDef.DateFormats. В нем определены следующие значения:
    1. German (DD.MM.YY)
    2. GermanWithCentury (DD.MM.YYYY)
    3. Month (DD Mon YY - сокращенное название месяца)
    4. MonthWithCentury (DD Mon YYYY)
    5. Time (hh:mi:ss - время)
Кроме этого, можно использовать, другие форматы, задавая их числом. Полный список находится здесь (это возможно только для MSSQLDataService).

###Пример использования

Получение выражения, приводящего дату к строке и сравнение с помощью функции LIKE

```
var stringDate = _langdef.GetFunction(
                    _langdef.funcToChar,
                    new VariableDef(_langdef.DateTimeType, propertyName),
                    10, // Длина строки, содержащей дату в формате 'DD.MM.YYYY'
                    ExternalLangDef.DateFormats.GermanWithCentury);
return _langdef.GetFunction(
            _langdef.funcLike,
            stringDate,
            string.Format("%{0}%", searchValue.Trim().ToLower()));
```

