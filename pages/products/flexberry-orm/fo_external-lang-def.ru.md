---
title: ExternalLangDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_external-lang-def.html
---

`ExternalLangDef` является расширением базового функционального языка [SQLWhereLanguageDef](fo_function-list.html).

Принципиальным отличием `ExternalLangDef` от [SQLWhereLanguageDef](fo_function-list.html) является возможность наложения ограничения на детейлы.

## Основные возможности ExternalLangDef

Класс `ExternalLangDef` является  наследником [SQLWhereLanguageDef](fo_function-list.html), и задание функции ограничения также осуществляется вызовом метода GetFucntion(string, params object;). Однако описание переменной-детейла, передаваемое в данный метод параметром, вместо базового класса [Описание переменных при построении функций ограничения](fo_variable-def.html) выполняется с помощью его наследника [DetailVariableDef](fo_variable-def.html).

### Функции ограничения на существование детейлов

* [funcExistExact](fo_exist-exist-exact-exist-all-exist-all-exact.html)
* [funcExistAllExact](fo_exist-exist-exact-exist-all-exist-all-exact.html)
* [funcExist](fo_exist-exist-exact-exist-all-exist-all-exact.html)
* [funcExistDetails](fo_exist-detals.html)
* [funcExistAll](fo_exist-exist-exact-exist-all-exist-all-exact.html)

### Функции ограничения на значения детейлов

* funcMaxWithLimit: максимальное значение в детейле с ограничением.
* funcMinWithLimit: минимальное значение в детейле с ограничением.
* funcAvgWithLimit: среднее значение в детейле с ограничением.
* funcSumWithLimit: сумма значений в детейле с ограничением.
* string funcCountWithLimit: количество значений в детейле с ограничением.

### Функции ограничения на дату

* [funcYearPart](fo_external-lang-def-restriction-on-the-date.html) - ограничение на год.
* [funcMonthPart](fo_external-lang-def-restriction-on-the-date.html) - ограничение на месяц.
* [funcDayPart](fo_external-lang-def-restriction-on-the-date.html) - ограничение на день.
* [funcHHPart](fo_external-lang-def-restriction-on-the-date.html) - ограничение на час.
* [funcMIPart](fo_external-lang-def-restriction-on-the-date.html) - ограничение на минуту.
* [funcDATEDIFF](fo_external-lang-def-restriction-on-the-date.html) - ограничение на разницу дат.
* [funcOnlyDate](fo_external-lang-def-restriction-on-the-date.html) - ограничение на дату.
* [funcDayOfWeek](fo_external-lang-def-restriction-on-the-date.html) - ограничение на день недели.
* [funcDayOfWeekZeroBased](fo_external-lang-def-restriction-on-the-date.html) - ограничение на день недели.
* [funcOnlyTime](fo_external-lang-def-restriction-on-the-date.html) - ограничение на время.
* [funcDateAdd](fo_external-lang-def-restriction-on-the-date.html) - полный аналог SQL функции dateadd.
* [funcDaysInMonth](fo_external-lang-def-restriction-on-the-date.html) - ограничение на количество дней в месяце.

##### Параметры

* [paramTODAY](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения сегодняшней даты.
* [paramYearDIFF](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения значения года от даты.
* [paramMonthDIFF](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения значения месяца от даты.
* [paramWeekDIFF](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения значения недели от даты.
* [paramQuarterDIFF](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения значения четверти от даты.
* [paramDayDIFF](fo_external-lang-def-restriction-on-the-date.html) - параметр для получения значения дня от даты.


### Разное
* [funcToChar](fo_func-to-char.html) - функция преобразования выражения в строку.
* funcCurrentUser
* [funcImplication](fo_function-implication.html) - функция импликации.
* `ExternalLangDef.ExistViewName` - строковая константа, которая используется для обозначения имени представления, когда строятся ограничения на детейлы.
