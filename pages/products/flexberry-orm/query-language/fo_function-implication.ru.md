---
title: Функция импликации
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, ограничения, импликация? funcImplication
summary: Использование импликации в ограничениях
toc: true
permalink: ru/fo_function-implication.html
lang: ru
---

`funcImplication` - функция [ExternalLangDef](fo_external-lang-def.html) для задания логической импликации.

Импликация - функция двух логических операндов: предпосылки и следствия, может принимать следующие значения:

Предпосылка  | Следствие | Результат
:----------|:----------|:----------
 0 | 0 | 1
 0 | 1 | 1
 1 | 0 | 0
 1 | 1 | 1

Логически, импликация "Если а, то b " равна "(не a) или b".
Например, ограничение вида: "Если кличка = барсик, то пол = мужской" приведет к выводу всех барсиков с мужским полом и всех (не барсиков).


## Пример

**Все Ивановы Иваны и все не Ивановы**

``` csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcImplication,
                    langDef.GetFunction(langDef.funcEQ, new VariableDef(langDef.StringType, "Фамилия"),"Иванов"),
                    langDef.GetFunction(langDef.funcEQ, new VariableDef(langDef.StringType, "Имя"), "Иван"));
```
