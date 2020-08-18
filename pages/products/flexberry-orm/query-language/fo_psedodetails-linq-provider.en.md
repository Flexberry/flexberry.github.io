---
title: Pseudometal in LinqProvider
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM Limitations
summary: Options for specifying constraints, methods used, communication with DetailVariableDef
toc: true
permalink: en/fo_psedodetails-linq-provider.html
lang: en
autotranslated: true
hash: 151491f614d94caa60a6858b5ddd0f001b45a0c6afc819ced665196df8c2dee7
---

## Statement of the problem of specifying limits on pseudometal

Let the entities "Customer" and "Credit" are connected shown in the image way.

![](/images/pages/products/flexberry-orm/query language/pseudo-details.png)

You need to restrict clients by setting the limit to reference them loans. The specificity of this problem is that according to the model, the client does not know what the credits refer to it.

## Setting limits on pseudometal

Let:

```csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
```

Job restriction pseudometal a little less intuitive than the use of [other features LinqProvider](fo_linq-provider-faetures.html).

To set restrictions on pseudometal in [LinqProvider](fo_linq-provider.html) added entity `PseudoDetail`. For `PseudoDetail` you have a few options to the constructor.

### The designer's options `PseudoDetail`

(A large number of designers due to the fact that a Linq expression complexity using constructors with default value).

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLinkName"> the relationship Name from pseudometal to the master. </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLink"> Method that defines the relationship name from pseudometal to the master (the definition goes through "Information.ExtractPropertyPath(masterLink)"). </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression real detail (for pseudometal this method will be incorrect). 
/// </summary> 
/// <param name="view"> the View of detail. </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLink"> Method that defines the relationship name from pseudometal to the master (the definition goes through "Information.ExtractPropertyPath(masterLink)"). </param> 
/// <param name="masterToDetailPseudoProperty"> the name of the relationship from master to pseudometal (pseudovoigt). </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink,
	string masterToDetailPseudoProperty)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLink"> Method that defines the relationship name from pseudometal to the master (the definition goes through "Information.ExtractPropertyPath(masterLink)"). </param> 
/// <param name="masterToDetailPseudoProperty"> the name of the relationship from master to pseudometal (pseudovoigt). </param> 
/// <param name="masterConnectProperties"> Properties of the master, by which you can make the connection. Analogue OwnerConnectProp for <see cref="DetailVariableDef"/> in the lcs. </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	Expression<Func<TP, object>> masterLink,
	string masterToDetailPseudoProperty,
	string[] masterConnectProperties)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLinkName"> the relationship Name from pseudometal to the master. </param> 
/// <param name="masterToDetailPseudoProperty"> the name of the relationship from master to pseudometal (pseudovoigt). </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName,
	string masterToDetailPseudoProperty)
```

```csharp
/// <summary> 
/// Constructor entity that represents a Linq expression pseudometal. 
/// </summary> 
/// <param name="view"> the View of pseudometal. </param> 
/// <param name="masterLinkName"> the relationship Name from pseudometal to the master. </param> 
/// <param name="masterToDetailPseudoProperty"> the name of the relationship from master to pseudometal (pseudovoigt). </param> 
/// <param name="masterConnectProperties"> Properties of the master, by which you can make the connection. Analogue OwnerConnectProp for <see cref="DetailVariableDef"/> in the lcs. </param> 
public PseudoDetail(
	ICSSoft.STORMNET.View view,
	string masterLinkName,
	string masterToDetailPseudoProperty,
	string[] masterConnectProperties)
```

### Methods `PseudoDetail`

Pseudometal possible to impose constraints of existence and universality.

```csharp
/// <summary> 
/// Helper method that can be converted at compile Linq expressions in funcExist. 
/// </summary> 
/// <returns> When compiling returns true when interpreted in Linq is formed DetailVariableDef. </returns> 
public bool Any()
```

```csharp
/// <summary> 
/// Helper method that can be converted at compile Linq expressions in funcExist. 
/// </summary> 
/// <param name="predicate"> LimitFunction for pseudometal. </param> 
/// <returns> When compiling returns true when interpreted in Linq is formed DetailVariableDef. </returns> 
public bool Any(Expression<Func<TP, bool>> predicate)
```

```csharp
/// <summary> 
/// Helper method that can be converted at compile Linq expressions in funcExistExact. 
/// </summary> 
/// <param name="predicate"> LimitFunction for pseudometal. </param> 
/// <returns> When compiling returns true when interpreted in Linq is formed DetailVariableDef. </returns> 
public bool All(Expression<Func<TP, bool>> predicate)
```

#### The limitation of existence to pseudometal

**An object of type `PseudoDetail` is defined outside of the linq expression:**

```csharp
var pseudoDetail = new PseudoDetail<Порода, Кошка>(
	Information.GetView("Koskee", typeof(Кошка)),
	Information.ExtractPropertyPath<Кошка>(x => x.Порода));

// All breeds who have cats 
ds.Query<Порода>(Порода.Views.ПородаE).Where(
	y => pseudoDetail.Any()).ToList();
```

**An object of type `PseudoDetail` is defined within linq expressions:**

```csharp
// All breeds who have a cat whose nickname is not "snow leopard" 
ds.Query<Порода>(Порода.Views.ПородаE)
	.Where(
		y => 
			new PseudoDetail<Порода, Кошка>(
				Information.GetView("Koskee", typeof(Кошка)),
				Information.ExtractPropertyPath<Кошка>(x => x.Порода))
			.Any(x.Кличка != "Barsik"))
	.ToList();
```

#### The constraint of universality on pseudometal

```csharp
// All breeds where the cats don't wear the nickname "snow leopard" 
ds.Query<Порода>(Порода.Views.ПородаE)
	.Where(
		y => 
			new PseudoDetail<Порода, Кошка>(
				Information.GetView("Koskee", typeof(Кошка)),
				Information.ExtractPropertyPath<Кошка>(x => x.Порода))
			.All(x.Кличка != "Barsik"))
	.ToList();
```

## PseudoDetail and DetailVariableDef

Below shows a code sample that demonstrates the relationship `PseudoDetail` and [DetailVariableDef](fo_variable-def.html).

`ComparePseudoDetailWithDetailvariabledef` is a method of conducting a conversion from linq to lcs and compare the results.

```csharp
const string masterToDetailPropertyName = "SomePropertyName";
var masterConnectProperties = new string[] { "Property1", "Property2" };

ComparePseudoDetailWithDetailVariableDef(
	new PseudoDetail<Порода, Кошка>(
		Information.GetView("Koskee", typeof(Кошка)),
		Information.ExtractPropertyPath<Кошка>(x => x.Порода),
		masterToDetailPropertyName,
		masterConnectProperties),
	new DetailVariableDef(
		this.ldef.GetObjectType("Details"),
		masterToDetailPropertyName,
		Information.GetView("Koskee", typeof(Кошка)),
		"Breed",
		masterConnectProperties));
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}