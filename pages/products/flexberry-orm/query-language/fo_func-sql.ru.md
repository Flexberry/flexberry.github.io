---
title: FuncSQL
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncSQL
toc: true
permalink: ru/fo_func-sql.html
lang: ru
---

`FuncSQL` - функция в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html), позволяющая выполнять вставки SQL.

{% include note.html content="Использовать данную функцию не рекомендуется." %}

Для случая, когда [ExternalLanguageDef](fo_external-lang-def.html) не располагает необходимым набором функций, [ограничение](fo_limit-function.html) можно построить в виде SQL-выражения. Пользоваться данной возможностью рекомендуется крайне осторожно, поскольку переключение типов источников данных в этом случае реализуется сложнее, ошибки выявлять тоже непросто.

Важно понимать, что `funcSQL` может быть частью другой "нормальной" функции, в этом случае необходимо не забывать про скобки снаружи этого SQL-выражения. Скобки при интерпретации сами не ставятся.

## Пример

Следующее выражение

```csharp
lcs.LimitFunction = ldef.GetFunction(ldef.funcAND,
                ldef.GetFunction(ldef.funcSQL, "\"abc\" = 1"),
                ldef.GetFunction(ldef.funcSQL, "\"def\" = 2")
                );

```

будет интерпретировано следующим образом:

```sql
WHERE ( "abc" = 1 AND "def" = 2)
```

{% include note.html content="Названия атрибутов необходимо заключать в кавычки, это поможет [Flexberry ORM](fo_flexberry-orm.html) корректно обрабатывать [ограничение](fo_limit-function.html)." %}
