---
title: Псевдодетейлы в LinqProvider
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_psedo-details-in-linq-provider.html
---

# Постановка задачи задания ограничений на псевдодетейлы
Пусть сущности "Клиент" и "Кредит" связаны представленным на изображении образом.



![](/images/pages/img/page/LINQProvider/PseudoDetails.png)



Нужно ограничить клиентов, задав при этом ограничение на ссылающихся на них кредиты. Специфика данной задачи состоит в том, что согласно модели клиент не знает, какие кредиты на него ссылаются.

# Задание ограничений на псевдодетейлы
Пусть:
```cs
var ds = (SQLDataService)DataServiceProvider.DataService;
```
Задание ограничение на псевдодетейлы чуть менее интуитивно, чем использование [других возможностей LinqProvider](linq-provider-faetures.html).

Для задания ограничения на псевдодетейл в [LinqProvider](fo_linq-provider.html) добавлена дополнительная сущность `PseudoDetail`. Для `PseudoDetail` доступно несколько вариантов конструктора.

## Варианты конструктора `PseudoDetail`
(Большое число конструкторов обусловлено тем, что в Linq-выражении сложности с использованием конструкторов со значением по умолчанию).

```cs
/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLinkName"> Имя связи от псевдодетейла к мастеру. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName)
```

```cs
/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении псевдодетейл.
/// </summary>
/// <param name="view"> Представление псевдодетейла. </param>
/// <param name="masterLink"> Метод, определяющий имя связи от псевдодетейла к мастеру (определение идёт через "Information.ExtractPropertyPath(masterLink)"). </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink)
```

```cs
/// <summary>
/// Конструктор сущности, представляющей в Linq-выражении настоящий детейл (для псевдодетейлов данный метод будет некорректен).
/// </summary>
/// <param name="view"> Представление детейла. </param>
public PseudoDetail(
	ICSSoft.STORMNET.View view)
```

```cs
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
```

```cs
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
```

```cs
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
```

```cs
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


## Методы `PseudoDetail`
На псевдодетейлы возможно накладывать ограничения существования и всеобщности.

```cs
/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExist.
/// </summary>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool Any()
```

```cs
/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExist.
/// </summary>
/// <param name="predicate"> LimitFunction для псевдодетейла. </param>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool Any(Expression<Func<TP, bool>> predicate)
```

```cs
/// <summary>
/// Вспомогательный метод, преобразуемый на этапе компиляции Linq-выражения в funcExistExact.
/// </summary>
/// <param name="predicate"> LimitFunction для псевдодетейла. </param>
/// <returns> При компиляции вернёт true, при интерпретации в Linq формируется DetailVariableDef. </returns>
public bool All(Expression<Func<TP, bool>> predicate)
```

### Ограничение существования на псевдодетейлы
'''Объект типа `PseudoDetail` определяется вне linq-выражения:'''
```cs
var pseudoDetail = new PseudoDetail<Порода, Кошка>(
	Information.GetView("КошкаE", typeof(Кошка)),
	Information.ExtractPropertyPath<Кошка>(x => x.Порода));

// Все породы, для которых определены кошки
ds.Query<Порода>(Порода.Views.ПородаE).Where(
	y => pseudoDetail.Any()).ToList();
```

'''Объект типа `PseudoDetail` определяется внутри linq-выражения:'''
```cs
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

### Ограничение всеобщности на псевдодетейлы
```cs
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

# PseudoDetail и DetailVariableDef
Ниже показан пример кода, демонстрирующий связь `PseudoDetail` и `[VariableDef#PseudoDetail|DetailVariableDef]`.

(`ComparePseudoDetailWithDetailVariableDef` - это метод, проводящий конвертацию из linq в lcs и сравнивающий результаты.)
```cs
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
