---
title: Psedodetails in LinqProvider
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Варианты задания ограничений, используемые методы, связь с DetailVariableDef
toc: true
permalink: en/fo_psedodetails-linq-provider.html
lang: en
---

## Постановка задачи задания ограничений на псевдодетейлы

Пусть сущности "Клиент" и "Кредит" связаны представленным на изображении образом.

![](/images/pages/products/flexberry-orm/query-language/pseudo-details.png)

Нужно ограничить клиентов, задав при этом ограничение на ссылающихся на них кредиты. Специфика данной задачи состоит в том, что согласно модели клиент не знает, какие кредиты на него ссылаются.

## Задание ограничений на псевдодетейлы

Пусть:

```csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
```

Задание ограничение на псевдодетейлы чуть менее интуитивно, чем использование [других возможностей LinqProvider](fo_linq-provider-faetures.html).

Для задания ограничения на псевдодетейл в [LinqProvider](fo_linq-provider.html) добавлена дополнительная сущность `PseudoDetail`. Для `PseudoDetail` доступно несколько вариантов конструктора.

### Варианты конструктора `PseudoDetail`

(Большое число конструкторов обусловлено тем, что в Linq-выражении сложности с использованием конструкторов со значением по умолчанию).

```csharp
// Вариант 1

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLinkName"> Имя связи от псевдодетейла к мастеру. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName)

// Вариант2

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLink"> Метод, определяющий имя связи от псевдодетейла к мастеру (определение идёт через "Information.ExtractPropertyPath(masterLink)"). </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink)

// Вариант 3

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении настоящий детейл (для псевдодетейлов данный метод будет некорректен).
/// </summary>
/// <param name="view"> Представление детейла. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view)

// Вариант 4

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLink"> Метод, определяющий имя связи от псевдодетейла к мастеру (определение идёт через "Information.ExtractPropertyPath(masterLink)"). </param>
/// <param name="masterToDetailPseudoProperty"> Имя связи от мастера к псевдодетейлу (псевдосвойство). </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink,
	string masterToDetailPseudoProperty)

// Вариант 5

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLink"> Метод, определяющий имя связи от псевдодетейла к мастеру (определение идёт через "Information.ExtractPropertyPath(masterLink)"). </param>
/// <param name="masterToDetailPseudoProperty"> Имя связи от мастера к псевдодетейлу (псевдосвойство). </param>
/// <param name="masterConnectProperties"> Свойства мастера, по которым можно произвести соединение. Аналог OwnerConnectProp для <see cref="DetailVariableDef"/> в lcs. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink,
	string masterToDetailPseudoProperty,
	string[] masterConnectProperties)

// Вариант 6

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLinkName"> Имя связи от псевдодетейла к мастеру. </param>
/// <param name="masterToDetailPseudoProperty"> Имя связи от мастера к псевдодетейлу (псевдосвойство). </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName,
	string masterToDetailPseudoProperty)

// Вариант 7

/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLinkName"> Имя связи от псевдодетейла к мастеру. </param>
/// <param name="masterToDetailPseudoProperty"> Имя связи от мастера к псевдодетейлу (псевдосвойство). </param>
/// <param name="masterConnectProperties"> Свойства мастера, по которым можно произвести соединение. Аналог OwnerConnectProp для <see cref="DetailVariableDef"/> в lcs. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName,
	string masterToDetailPseudoProperty,
	string[] masterConnectProperties)
```

### Методы PseudoDetail

На псевдодетейлы возможно накладывать ограничения существования и всеобщности.

```csharp
// Вариант 1

/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExist.
/// </summary>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool Any()

// Вариант 2

/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExist.
/// </summary>
/// <param name="predicate"> LimitFunction для псевдодетейла. </param>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool Any(Expression<Func<TP, bool>> predicate)

// Вариант 3

/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExistExact.
/// </summary>
/// <param name="predicate"> LimitFunction для псевдодетейла. </param>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool All(Expression<Func<TP, bool>> predicate)
```

#### Ограничение существования на псевдодетейлы

**Объект типа `PseudoDetail` определяется вне linq-выражения:**

```csharp
var pseudoDetail = new PseudoDetail<Порода, Кошка>(
	Information.GetView("КошкаE", typeof(Кошка)),
	Information.ExtractPropertyPath<Кошка>(x => x.Порода));

// Все породы, для которых определены кошки
ds.Query<Порода>(Порода.Views.ПородаE).Where(
	y => pseudoDetail.Any()).ToList();
```

**Объект типа `PseudoDetail` определяется внутри linq-выражения:**

```csharp
// Все породы, для которых определены кошки, у которых кличка не "Барсик"
ds.Query<Порода>(Порода.Views.ПородаE)
	.Where(
		y => 
			new PseudoDetail<Порода, Кошка>(
				Information.GetView("КошкаE", typeof(Кошка)),
				Information.ExtractPropertyPath<Кошка>(x => x.Порода))
			.Any(x.Кличка != "Барсик"))
	.ToList();
```

#### Ограничение всеобщности на псевдодетейлы

```csharp
// Все породы, где кошки не носят кличку "Барсик"
ds.Query<Порода>(Порода.Views.ПородаE)
	.Where(
		y => 
			new PseudoDetail<Порода, Кошка>(
				Information.GetView("КошкаE", typeof(Кошка)),
				Information.ExtractPropertyPath<Кошка>(x => x.Порода))
			.All(x.Кличка != "Барсик"))
	.ToList();
```

## PseudoDetail и DetailVariableDef

Ниже показан пример кода, демонстрирующий связь `PseudoDetail` и [DetailVariableDef](fo_variable-def.html).

`ComparePseudoDetailWithDetailVariableDef` - это метод, проводящий конвертацию из linq в lcs и сравнивающий результаты.

```csharp
const string masterToDetailPropertyName = "SomePropertyName";
var masterConnectProperties = new string[] { "Property1", "Property2" };

ComparePseudoDetailWithDetailVariableDef(
	new PseudoDetail<Порода, Кошка>(
		Information.GetView("КошкаE", typeof(Кошка)),
		Information.ExtractPropertyPath<Кошка>(x => x.Порода),
		masterToDetailPropertyName,
		masterConnectProperties),
	new DetailVariableDef(
		this.ldef.GetObjectType("Details"),
		masterToDetailPropertyName,
		Information.GetView("КошкаE", typeof(Кошка)),
		"Порода",
		masterConnectProperties));
```
