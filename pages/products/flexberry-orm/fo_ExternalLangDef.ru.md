---
title: ExternalLangDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_external-lang-def.html
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">

* **Продукт**: [Flexberry ORM](flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* **Программная библиотека**: ExternalLangDef.dll.
* **Предназначение**: Общее описание работы построителя [функций ограничения](limit-function.html) ExternalLangDef, являющегося расширением [SQLWhereLanguageDef](function-list.html).

</td>
</tr></tbody></table></a>
</div>
# ExternalLangDef
`ExternalLangDef` является расширением базового функционального языка `[SQLWhereLanguageDef](function-list.html)`.

Принципиальным отличием `ExternalLangDef` от `[SQLWhereLanguageDef](function-list.html)` является возможность наложения ограничения на детейлы.

## Основные возможности ExternalLangDef
Класс `ExternalLangDef` является  наследником `[SQLWhereLanguageDef](function-list.html)`, и задание функции ограничения также осуществляется вызовом метода `GetFucntion(string, params object&#0091;&#0093;)`. Однако описание переменной-детейла, передаваемое в данный метод параметром, вместо базового класса `[Описание переменных при построении функций ограничения](variable-def.html)` выполняется с помощью его наследника `[DetailVariableDef](variable-def.html)`.

### Функции ограничения на существование детейлов

* [funcExistExact](exist--exist-exact--exist-all--exist-all-exact.html)
* [funcExistAllExact](exist--exist-exact--exist-all--exist-all-exact.html)
* [funcExist](exist--exist-exact--exist-all--exist-all-exact.html)
* [funcExistDetails](exist-detals.html)
* [funcExistAll](exist--exist-exact--exist-all--exist-all-exact.html)

### Функции ограничения на значения детейлов

* funcMaxWithLimit: максимальное значение в детейле с ограничением.
* funcMinWithLimit: минимальное значение в детейле с ограничением.
* funcAvgWithLimit: среднее значение в детейле с ограничением.
* funcSumWithLimit: сумма значений в детейле с ограничением.
* string funcCountWithLimit: количество значений в детейле с ограничением.

### Функции ограничения на дату

* [funcYearPart](external-lang-def-restriction-on-the-date.html) - ограничение на год.
* [funcMonthPart](external-lang-def-restriction-on-the-date.html) - ограничение на месяц.
* [funcDayPart](external-lang-def-restriction-on-the-date.html) - ограничение на день.
* [funcHHPart](external-lang-def-restriction-on-the-date.html) - ограничение на час.
* [funcMIPart](external-lang-def-restriction-on-the-date.html) - ограничение на минуту.
* [funcDATEDIFF](external-lang-def-restriction-on-the-date.html) - ограничение на разницу дат.
* [funcOnlyDate](external-lang-def-restriction-on-the-date.html) - ограничение на дату.
* [funcDayOfWeek](external-lang-def-restriction-on-the-date.html) - ограничение на день недели.
* [funcDayOfWeekZeroBased](external-lang-def-restriction-on-the-date.html) - ограничение на день недели.
* [funcOnlyTime](external-lang-def-restriction-on-the-date.html) - ограничение на время.
* [funcDateAdd](external-lang-def-restriction-on-the-date.html) - полный аналог SQL функции dateadd.
* [funcDaysInMonth](external-lang-def-restriction-on-the-date.html) - ограничение на количество дней в месяце.

##### Параметры

* [paramTODAY](external-lang-def-restriction-on-the-date.html) - параметр для получения сегодняшней даты.
* [paramYearDIFF](external-lang-def-restriction-on-the-date.html) - параметр для получения значения года от даты.
* [paramMonthDIFF](external-lang-def-restriction-on-the-date.html) - параметр для получения значения месяца от даты.
* [paramWeekDIFF](external-lang-def-restriction-on-the-date.html) - параметр для получения значения недели от даты.
* [paramQuarterDIFF](external-lang-def-restriction-on-the-date.html) - параметр для получения значения четверти от даты.
* [paramDayDIFF](external-lang-def-restriction-on-the-date.html) - параметр для получения значения дня от даты.


### Разное

* [funcToChar](func-to-char.html) - функция преобразования выражения в строку.
* funcCurrentUser
* [funcImplication](implication-in-external-lang-def.html) - функция импликации.
* `ExternalLangDef.ExistViewName` - строковая константа, которая используется для обозначения имени представления, когда строятся ограничения на детейлы.
