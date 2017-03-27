---
title: SQL-запросы в Flexberry
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, БД
toc: true
permalink: ru/fo_sql-query.html
folder: products/flexberry-orm/
lang: ru
---

## Доступ к базе

В технологии Flexberry существует 3 способа доступа к базе данных.

1. Сервис данных (LimitFunctions) - [генератор SQL-скриптов](fo_function-list.html).
2. [LINQProvider](fo_linq-provider.html) - доступ к данным с использованием [LINQ](http://msdn.microsoft.com/ru-ru/library/bb397926.aspx).
3. ADO.NET - старый добрый ADO.NET, используем SQL-запрос из кода. __Примечание__: ADO.NET '''НЕ ИСПОЛЬЗУЕТСЯ''' в прикладных проектах в случаях, когда без него можно обойтись.

## Получение результата запроса через сервис данных

[Cервис данных](fo_data-service.html) (как сконструировать или получить cервис данных смотрите [здесь](construction--data-service.html)) осуществляет генерацию SQL-скриптов; при этом каждой таблице и полю назначается свой алиас и при внедрении строк в текст запроса это нужно учитывать.

### Из чего формируется SQL-запрос 

Из чего складывается текст SQL-запроса, генерируемого [сервисом данных](fo_data-service.html):

* [`LoadingCustomizationStruct`](fo_loading-customization-struct.html) и `LimitFunction` ([примеры](fo_function-list.html))
* Атрибутивная разметка (например, атрибут `Storage` как классов, так и связей).
* Реализация [наследования](fo_inheritance.html) с иcпользованием [TypeUsage](fo_type-usage-problem.html).
* [Перехват запроса к БД](fo_intercept-formation-sql-query.html).

### StormMainObjectKey и STORMJoinedMasterKey

#### StormMainObjectKey

`StormMainObjectKey` - [первичный ключ](http://msdn.microsoft.com/ru-ru/library/ms191236%28v=sql.90%29.aspx) (primarykey)  объекта, по которому идёт вычитка из базы данных (о первичных ключах объектов в Flexberry можно почитать [здесь](fo_primary-keys-objects.html)).
Константа `StormMainObjectKey` определена как `ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef.StormMainObjectKey`.

#### STORMJoinedMasterKey

Также при организации вычитки из базы данных `Flexberry` использует внешние ключи с именами по типу `STORMJoinedMasterKey1`. Использовать `STORMJoinedMasterKey` при построении ограничений в коде не рекомендуется.

### Общие рекомендации

* При настройке [`LoadingCustomizationStruct`](fo_loading-customization-struct.html) обычно нужно специальное [представление](fo_view-def.html) (его нужно либо [определить в коде](fo_view-def.html), как это сделано [Ограничение-на-детеилы-из-кода|здесь]; либо [создать заранее в Flexberry](fo_data-classes.html)).

* Для определения количества возвращаемых записей рекомендуется использовать параметр [`LoadingCustomizationStruct`](fo_loading-customization-struct.html) ReturnTop, как это сделано [Ограничение-на-детеилы-из-кода|здесь].

* Работу по сортировке, наложению ограничений, подчёту агрегирующих значений и прочим аналогичным действиям необходимо перекладывать на сервер за счёт задания параметров [`LoadingCustomizationStruct`](fo_loading-customization-struct.html).

## LinqProvider

Вычитка данных при помощи построения LINQ-запросов описана в [этой статье](fo_linq-provider.html)

## ADO.NET

Ниже представлен пример исполнения SQL-запросов через технологию ADO.Net:

```csharp
public virtual decimal СуммаОплаченныхПокупок
{
	get
	{
		var connection = (SqlConnection)((SQLDataService)DataServiceProvider.DataService).GetConnection(); //получение подключения
		var command = new SqlCommand("SELECT SUM(purchase.\"Сумма\") "+
			" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\" "+
			" WHERE purchase.\"Покупатель\"=@Customer AND  purchase.\"Статус\" = \'Оплачено\' ", connection); //формирование запроса
		var parameter = new SqlParameter("@Customer", SqlDbType.UniqueIdentifier);
		parameter.Value = ((KeyGuid)this.__PrimaryKey).Guid; //определение значения параметра
		command.Parameters.Add(parameter);
		
		try
		{
			connection.Open();
			var value = (decimal)command.ExecuteScalar(); //исполнение запроса
		}
		finally
		{
			connection.Close();
		}
		
		return value;
	}
	//...
}
```

__Примечание__: Даже если возникла острая необходимость использовать ADO.NET, проверьте, нельзя ли обойтись без него. (Возможно, Вам поможет [funcSQL](func-s-q-l.html))

## Итого

Есть три варианта вычитки данных:

1. [LINQProvider](fo_linq-provider.html) - находится в стадии доработки, как следствие - реализует не все функции.
2. Функциональный язык построения ограничений - является наиболее полным и объемлющим инструментом для вычитки данных. В перспективе будет вытеснен [LINQProvider](fo_function-list.html)'ом.
3. ADO.NET - используется __только__ в случаях, когда первые 2 способа не работают.

[`LINQProvider`](fo_linq-provider.html) предпочтительнее для использования, но пока не может предоставить полного набора функций.
`ADO.NET` не используется.
