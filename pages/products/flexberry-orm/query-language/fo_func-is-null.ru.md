---
title: FuncIsNull
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncIsNull
toc: true
permalink: ru/fo_func-is-null.html
lang: ru
---

`FuncIsNull` - функция, аналогичная проверке на null в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Для `FuncIsNull` необходим один параметр - описание переменной (Variable Definition), которую надо проверить на `null`.

Рассмотрим пример. Требуется вычитать все `Кредиты`, у которых `ДатаВыдачи` равняется `null`.

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредит WHERE ДатаВыдачи is null
```

Через [SQLWhereLanguageDef](fo_function-list.html):

``` csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcIsNull, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Клиент>(x => x.ДатаВыдачи)));
```
