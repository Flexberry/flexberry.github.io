---
title: ExternalLangDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения, ExternalLangDef
summary: Возможности, используемые функции и параметры ExternalLangDef
toc: true
permalink: ru/fo_external-lang-def.html
lang: ru
---

`ExternalLangDef` является расширением базового функционального языка [SQLWhereLanguageDef](fo_function-list.html).

Принципиальным отличием `ExternalLangDef` от [SQLWhereLanguageDef](fo_function-list.html) является возможность наложения ограничения на детейлы.

## Основные возможности ExternalLangDef

Класс `ExternalLangDef` является  наследником [SQLWhereLanguageDef](fo_function-list.html), и задание функции ограничения также осуществляется вызовом метода GetFucntion(string, params object;). Однако описание переменной-детейла, передаваемое в данный метод параметром, вместо базового класса [Описание переменных при построении функций ограничения](fo_variable-def.html) выполняется с помощью его наследника [DetailVariableDef](fo_variable-def.html).

### Функции ограничения на существование детейлов

* `funcExistExact`
* `funcExistAllExact`
* `funcExist`
* `funcExistDetails`
* `funcExistAll`

Подробнее в статье [Ограничения на детейлы](fo_exist-details.htm).

### Функции ограничения на значения детейлов

* `funcMaxWithLimit`: максимальное значение в детейле с ограничением.
* `funcMinWithLimit`: минимальное значение в детейле с ограничением.
* `funcAvgWithLimit`: среднее значение в детейле с ограничением.
* `funcSumWithLimit`: сумма значений в детейле с ограничением.
* `string funcCountWithLimit`: количество значений в детейле с ограничением.

### Функции ограничения на дату

* `funcYearPart` - ограничение на год.
* `funcMonthPart` - ограничение на месяц.
* `funcDayPart` - ограничение на день.
* `funcHHPart` - ограничение на час.
* `funcMIPart` - ограничение на минуту.
* `funcDATEDIFF` - ограничение на разницу дат.
* `funcOnlyDate` - ограничение на дату.
* `funcDayOfWeek` - ограничение на день недели.
* `funcDayOfWeekZeroBased` - ограничение на день недели.
* `funcOnlyTime` - ограничение на время.
* `funcDateAdd` - полный аналог SQL функции dateadd.
* `funcDaysInMonth` - ограничение на количество дней в месяце.

Подробнее в статье [Ограничение в операциях с датой и временем](fo_restriction-datetime.html).

##### Параметры

* `paramTODAY` - параметр для получения сегодняшней даты.
* `paramYearDIFF` - параметр для получения значения года от даты.
* `paramMonthDIFF` - параметр для получения значения месяца от даты.
* `paramWeekDIFF` - параметр для получения значения недели от даты.
* `paramQuarterDIFF` - параметр для получения значения четверти от даты.
* `paramDayDIFF` - параметр для получения значения дня от даты.

Подробнее в статье [Ограничение в операциях с датой и временем](fo_restriction-datetime.html).

### Разное

* [funcToChar](fo_func-to-char.html) - функция преобразования выражения в строку.
* `funcCurrentUser`
* [funcImplication](fo_function-implication.html) - функция импликации.
* `ExternalLangDef.ExistViewName` - строковая константа, которая используется для обозначения имени представления, когда строятся ограничения на детейлы.
