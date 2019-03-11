--- 
title: Nahranie (computable) properties of the data object 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, DataServiceExpression, DSE, data types, data objects 
summary: use calculated properties in Flexberry ORM 
toc: true 
permalink: en/fo_not-stored-attributes.html 
lang: en 
autotranslated: true 
hash: aaf5fd86302589269a0224ac4af80f30ab9ca85bf116e52851be328926fd11dc 
--- 

## Nechranice property 

If the property Nechranice (marked [attribute `NotStored`](fo_attributes-class-data.html)), it is _not_ processed [data services](fo_data-service.html). 
Inside the accessor `get` specify any code (expression) that calculates the value of the property. 

### Nechranice property and views 

The developer should follow closely that in [view](fd_view-definition.html), which is loading objects [data services](fo_data-service.html), got all the properties which depend on the calculated attribute. 

**Important: Failure to heed this will cause invalid account errors.** 

### service Behavior data using a calculated field 

Usually [data services](fo_data-service.html) with the fix lists not using data objects when the return data as strings. This is done to improve performance, as the savings of computational resources for creating data objects is very high. However, the expression for the computable account of the attribute is specified directly in a property of the data object, so when the data object contains calculated attributes, data services, and in doing so creates a data object, properties oznacavanja under [view](fd_view-definition.html), then the conversion to a string (because the data service will request the value of a calculated property, runs his account). To avoid account through the data object and, accordingly, to expedite the work of the data services when you are uploading a list, the attribute is applied `DataServiceExpression` which the property is assigned to the service data expression» «clear this data service. Thus, if the attribute `DataServiceExpression` specified, the data object is not created, and the account of computable attribute» «is shifted to the service data according to an expression. 

{% include warning.html content="create expression in DSE does not negate that it should be duplicated to GET." %} 

### Rules for the use of computable fields 

**Accepted** to the IDs in the expression is clearly distinguished by the symbol **@** (dog). 

{% include important.html content="Computable attributes, which imposed authority __definitely__ need to have `DataServiceExpression`" %} 

#### an Example of a calculated property 

```csharp
[NotStored]
public Деньги Итого 
{ 
    get 
         { 
              return ИтогоДисконт + ИтогоВекселя - СуммаНДС;
         }
}
``` 

#### an Example of a computable property attribute DataServiceExpression 

```csharp
[DataServiceExpression(typeof(SQLDataService),"'\"' @NAME@ '\"'")]
[NotStored] 
public string НАИМЕНОВАНИЕВКАВЫЧКАХ 
{
    get
       {
           return "\""+ НАИМЕНОВАНИЕ+"\"";
       }
}
``` 

#### Example detailov 

Example detailov when determining the value of a calculated field is available in [Nahranie (computable) properties of the data object](fo_not-stored-attributes.html). 

## Primary keys in computable fields 

Use [primary keys](fo_primary-keys-objects.html) in computable fields by specifying [STORMMainObjectKey](fo_sql-query.html) without the " @ " symbol. 

For example, in list form, the message "Street <Imali> has a primary key <Particleillusion>". 

``` csharp
[ICSSoft.STORMNET.NotStored())
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "\'Street \' @Name@ \' has the primary key \' and CAST(STORMMainObjectKey as varchar(max))"))
public virtual string NotStoredName
``` 

When using this property as a craftsman in the sql query `STORMMainObjectKey` will be correctly replaced by `STORMJoinedMasterKey`. 

```sql
('Улица ' + "IIS.TestStandWinforms.Home"."Street.The name" + ' имеет первичный ключ ' +CAST("STORMJoinedMasterKey0" as varchar(max))) as "Street.NotStoredName"
``` 

## the Use of computable fields in calculated fields 

In relation to architectural features [generator sql queries](fo_sql-query.html) in Flexberry ORM, use a calculated field in the calculated fields at the moment __not supported__. 

For example, there is a class `Дом` [master](fd_master-association.html) `Улица`. 

The class `Улица` there is a computable field `NotStoredName` calculated as follows: 

```sql
'Улица ' + @Название@ + ' имеет первичный ключ ' +CAST(STORMMainObjectKey as varchar(max))
``` 

Suppose that there is a need to use this field when calculating the value of a calculated field class `Дом`: 

```sql
'Корпус ' + @Корпус@ + ';' + @Улица.NotStoredName@
``` 

However, this option value will not be counted. To implement the required logic, you can paint an expression that calculates the value of the field as follows: 

```sql
'Корпус ' + @Корпус@ + ';' + 'Улица ' + @Улица.Название@ + ' имеет первичный ключ ' +CAST(@Улица@ as varchar(max))
``` 

## Example of creating nehrenovo data properties 

The process of creating a calculated property using `DataServiceExpression` is well illustrated by the following task: There is a system of accounting customers, which stores information about their purchases. The purchases can be two statuses: "submitted to the Bank" and "Paid". You need to determine the buyer computable field "Amount paid". 

First and foremost you must create in Flexberry Designer [class diagram](fd_class-diagram.html). 

![](/images/pages/products/flexberry-orm/data-types/class-diagram-customer-purchase.jpg) 

Field "Somaprescription" class "Buyer" to do all of nagraniem](fo_attributes-class-data.html), and then in [DataService attribute Expression](fo_attributes-class-data.html) this field to add the line: 

`DataService : ICSSoft.STORMNET.Business.SQLDataService`; 

_DataService Expression:_ 

```sql
"SELECT SUM(purchase."Сумма")
FROM "Buyer" customer join "Buy" purchase on customer."primaryKey" = purchase."Buyer"
WHERE purchase."Buyer" = StormMainObjectKey AND purchase.Status = 'Оплачено' "
``` 

To generate the code. 

### Work with program code 

Was generated with the following code: 

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Amount\")"+
"FROM \"Customer\" customer join \"Purchase\" purchase on customer.\"primaryKey\" = purchase.\"Buyer\""+
"WHERE purchase.\"Buyer\" = StormMainObjectKey AND purchase.\"Status\" = \'Paid\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get {	return null;	}
	set {}
}
``` 

As a result, when viewing lists of buyers Nechranice field "Somaprescription" is calculated [no object creation data](fo_not-stored-attributes.html).

{% include note.html content="After editing the object `Покупатель` in the list of the appropriate buyer in the field `СуммаОплаченныхПокупок` displays an empty string. One solution to this problem might be for the cached values, which you need to create a private variable that will store the cached value." %} 

```csharp
public class Покупатель : ICSSoft.STORMNET.DataObject
{
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedPurchaseSum = null; //variable to cache fields Somaprescription 
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedAvailableSum = null; //variable to store the cache field Dostupnymi 
	//... 
}
``` 

You also need to edit the code for `СуммаОплаченныхПокупок`. 

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Amount\")"+
"FROM \"Customer\" customer join \"Purchase\" purchase on customer.\"primaryKey\" = purchase.\"Buyer\""+
"WHERE purchase.\"Buyer\" = StormMainObjectKey AND purchase.\"Status\" = \'Paid\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get
	{
		return this.cashedPurchaseSum;
	}
	set
	{
		if (value != null)
		{
			this.cashedPurchaseSum= value;
		}
	}}
``` 

If we extend the problem statement that the "Available amount" of the class "Customer", you must record the available amount on the account (i.e. the amount in the account minus the amount of payments that has the status "submitted to the Bank"), the code for the field object may have the following form (why `this.СуммаНаСчете` in the expression written as `@Communitcate@` explained in the article [Nahranie (computable) properties of the data object](fo_not-stored-attributes.html)): 

```csharp
// *** Start programmer edit section *** (Buyer.Dostupnymi CustomAttributes) 
[DataServiceExpression(typeof(SQLDataService), "SELECT @Communitcate@ - SUM(purchase.\"Amount\") "+
	"FROM \"Customer\" customer join \"Purchase\" purchase on customer.\"primaryKey\" = purchase.\"Buyer\" "+
	"WHERE purchase.\"Buyer\" = StormMainObjectKey AND purchase.\"Status\" = \'submitted to the Bank\' ")]
// *** End programmer edit section *** (Buyer.Dostupnymi CustomAttributes) 
[ICSSoft.STORMNET.NotStored()]
public virtual decimal ДоступнаяСумма
{
	get
	{
		return this.cashedAvailableSum;
	}
	set
	{
		if (value != null)
		{
			this.cashedAvailableSum = value;
		}
	}
}
``` 

{% include warning.html content="In the above example is omitted, if the value of datalow changes, then the cached value should be recalculated (for example, using [SQL query](fo_sql-query.html))" %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}