---
title: FuncEQ
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-e-q.html

---
##FuncEQ
FuncEQ - функция, аналогичная сравнению на равенство в SQL, в построителе функций ограничения SQLWhereLanguageDef.

##Параметры GetFunction
Функция GetFunction принимает первым параметром тип функции funcEQ, а дальше принимает 2 объекта на сравнение их между собой. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым параметром - объект, с которым будет происходить сравнение.

**Исключение составляет тип bool:**

```
langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.BoolType, "SomeBoolFlag"))
```

сработает и без указания второго параметра (по умолчанию будет происходить сравнение с true).

Проверку на null надо проводить при помощи функции FuncIsNull, при попытке проверить что-либо на null с помощью FuncEQ возникнет исключительная ситуация Object reference not set to an instance of an object.

Рассмотрим пример. Требуется вычитать все Кредиты определенного Клиента.

![](/images/pages/products/flexberry-orm/FuncEQ.png)

SQL-выражение выглядело бы следующим образом:

```
SELECT * FROM Кредит WHERE Клиент = '{ID}'
```

Где {ID} - первичный ключ искомого Клиента

Через SQLWhereLanguageDef:

```
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)), клиент.__PrimaryKey);
```
