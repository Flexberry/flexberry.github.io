---
title: Generic methods in Flexberry Designer
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM
summary: The format of the description and examples of using generic methods
toc: true
permalink: en/fo_generic-methods.html
lang: en
---

## Генерик-методы и UML

В связи с тем что UML не описывает создание генерик-методов, но в это же время хотелось бы иметь возможность их автоматической генерации было добавлено расширение, позволяющее описывать такие методы при описании классов на [диаграммах](fd_class-diagram.html).

## Формат описания

Для описания генерик-методов на [диаграммах классов](fd_class-diagram.html) предлагается следующий формат:

`ИмяФункции<Шаблон1:Класс1,Интерфейс1;Шаблон2:Класс2;Шаблон3>(Параметр1:ТипПараметра1, Параметр2:ТипПараметра2):ТипФункции`

При этом часть в треугольных скобках, включая их самих, является необязательной и при ее отсутствии будет сгенерирован обычный метод.

Шаблон1 и Шаблон2 будут сгенерированы в имена универсальных типов в генерик методе. После каждого имени шаблона, через двоеточие могут быть описаны ограничения на этот шаблон, которые сгенерируются в часть `Where` прототипа метода. Шаблоны разделяются точкой с запятой.

## Примеры

### Пример 1

_Описание:_

`Добавить <T:DataObject>(dataObject:T)`

_Реализация:_

```csharp
public virtual void Добавить<T>(T dataObject)
where T : DataObject
{
    return;
}
```

### Пример 2

_Описание:+

`Добавить <U;T:DataObect;I:IComparable,ICloneable>(dataObject:T)`

_Реализация:_

```csharp
public virtual void Карамба<U,T,I>(T dataObject)
where T : DataObject
where I : IComparable, ICloneable
{
    return;
}
```

{% include note.html content="В качестве ограничения могут использоваться только один класс и интерфейсы. Flexberry Desinger не проверяет такую ошибку, так что код будет сгенерирован, но не будет компилироваться." %}