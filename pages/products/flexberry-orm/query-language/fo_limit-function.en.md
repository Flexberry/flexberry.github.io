---
title: Functions limits
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM Limitations
summary: Definition and purpose of the builders restrictions
toc: true
permalink: en/fo_limit-function.html
lang: en
autotranslated: true
hash: 635984df750e9e6f0562c998fd426af05a75303caa93e74bac0427fe5852d2bb
---

The restriction feature allows to filter and limit the data selection and represent a special extensible language constraints developed for [Flexberry ORM](fo_flexberry-orm.html).

This mechanism lies in the basis of functioning [LINQProvider](fo_linq-provider.html) so, [these mechanisms of imposing restrictions closely linked](fo_limitation.html).

There is [the ability to serialize function limitations](fo_limit-function-serialization.html).

## "Builders" functions restrictions

[SQLWhereLanguageDef](fo_function-list.html) - class-Builder functions, the restrictions on the deducted objects.

[ExternalLangDef](fo_external-lang-def.html) - language extension restrictions to set restrictions on the dependent objects (detali).

[FunctionBuilder](fo_function-builder.html) is a wrapper class over ExternalLangDef with a less verbose syntax for building functions restrictions objects deducted.

[FunctionBuilder examples](fo_function-builder-examples.html) - more detailed examples for the refaktor `LangDef.GetFunction`.

There is an opportunity to expand the implemented languages of limitations](fo_using-languagedef.html).

## Application functions limit

Working with functions restrictions often associated with reading data. When reading data, the function restrictions specified in the structure [LoadingCustomizationStruct](fo_loading-customization-struct.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}