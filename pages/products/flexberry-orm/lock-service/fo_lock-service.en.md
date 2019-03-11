--- 
title: Service locks 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, LockService 
summary: the Purpose and use of the service locks 
toc: true 
permalink: en/fo_lock-service.html 
lang: en 
autotranslated: true 
hash: 911c7d878409fcc062560bd545f223d69529400f6535adab401a17f493ebbc2a 
--- 

Service locks (`ICSSoft.STORMNET.Business.LockService`) is designed for convenient implement locking mechanism. For example, you want to protect certain data object from changes by other users at the time how it is edited by any user. 

To put the lock you want to use one of the methods `SetLock`. 

To check the lock, one should use the method `GetLock`, or try to rerun `SetLock`. Returned a nonblank value is the name of the user who has locked the object. 

To open blockages, you must use the method `ClearLock`. 

```csharp
Автор автор = new Автор();
LockService ls = new LockService();
ls.SetLock(автор); //Blocking 
string sLockID = ls.SetLock(автор); //Attempt to lock the same object 
if (sLockID!=string.Empty)
{
	Console.WriteLine(string.Format("Locked by: {0}", sLockID));
}
ls.ClearLock(автор);//Cleanup the lock 
Console.ReadLine();
``` 

Service lock accesses the data store via [service data](fo_data-service.html) specified in the [service provider data](fo_ds-provider.html) (`ICSSoft.STORMNET.Business.DataServiceProvider.DataService`). 

Vault locks must be a corresponding source. 

For relational storage, it is defined as: 

``` sql
SQL
CREATE TABLE STORMNETLOCKDATA (
	LockKey char (300) NOT NULL ,
	UserName char (300) NOT NULL 
)
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}