---
title: SQL-запросы в Flexberry
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных, база данных, запросы, ограничения, ADO.NET 
summary: Описание способов работы с БД и рекомендуемые способы доступа к БД
toc: true
permalink: ru/fo_sql-query.html
lang: ru
---

## Доступ к базе

В технологии Flexberry существуют следующие способы доступа к базе данных:

* [Сервис данных](fo_data-service.html). Для чтения данных возможно использование как
	* [Функции ограничения](fo_limit-function.html),
	* так и [LINQProvider](fo_linq-provider.html)).
* ADO.NET. Позволяет выполнять SQL-запросы из кода.

 __Примечание__: ADO.NET `НЕ ИСПОЛЬЗУЕТСЯ` в проектах в случаях, когда без него можно обойтись. Даже если возникла острая необходимость использовать ADO.NET, следует проверить, нельзя ли обойтись без него. Возможно, подойдет использование [funcSQL](fo_func-sql.html).

Если говорить о применимости возможных способов вычитки данных, то: ADO.NET - используется __только__ в случаях, когда не достаточно возможностей сервиса данных.

### Формирование запроса в сервисе данных

Из чего складывается текст SQL-запроса, генерируемого [сервисом данных](fo_data-service.html):

* Настройки [LoadingCustomizationStruct](fo_loading-customization-struct.html) (работу по сортировке, наложению [limitFunction](fo_limit-function.html), подчёту агрегирующих значений и прочим аналогичным действиям необходимо перекладывать на сервер за счёт задания параметров `LoadingCustomizationStruct`.
* Атрибутивная разметка (например, атрибут `Storage` как классов, так и связей).
* Реализация [Inheritance](fd_inheritance.html) с иcпользованием [TypeUsage](fo_type-usage-problem.html).
* [Перехват запроса к БД](fo_intercept-formation-sql-query.html).

Если посмотреть, какие запросы отправляет сервис данных на сервер, то в нём встречаются константы типа `StormMainObjectKey` и `STORMJoinedMasterKey`.

* ``StormMainObjectKey`` - [первичный ключ](fo_primary-keys-objects.html) (primarykey)  объекта, по которому идёт вычитка из базы данных. Константа `StormMainObjectKey` определена как `ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef.StormMainObjectKey`.
* ``STORMJoinedMasterKey``. При организации вычитки из базы данных внешним ключам даются имена по типу `STORMJoinedMasterKey1`. Использовать `STORMJoinedMasterKey` при построении ограничений в коде __не рекомендуется__, поскольку их порядок может меняться.

### ADO.NET

Ниже представлен пример исполнения SQL-запросов через технологию ADO.Net:

``` csharp
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
