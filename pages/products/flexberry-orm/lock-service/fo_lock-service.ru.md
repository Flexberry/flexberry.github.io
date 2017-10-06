---
title: Сервис блокировок
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, LockService
summary: Назначение и использование сервиса блокировок
toc: true
permalink: ru/fo_lock-service.html
lang: ru
---

Сервис блокировок (`ICSSoft.STORMNET.Business.LockService`) предназначен для удобной реализации механизма блокировок. Например, требуется защитить некоторый объект данных от изменения другими пользователями в то время, как он редактируется каким-либо пользователем.

Для того чтобы выставить блокировку, требуется воспользоваться одним из методов `SetLock`.

Для проверки блокировки следует пользоваться методом `GetLock`, либо попытаться повторно выполнить `SetLock`. Возвращённое непустое значение будет именем пользователя, который заблокировал объект.

Для снятия блокировок необходимо пользоваться методом `ClearLock`.

```csharp
Автор автор = new Автор();
LockService ls = new LockService();
ls.SetLock(автор); //Блокирование
string sLockID = ls.SetLock(автор); //Попытка повторного блокирования того же объекта
if (sLockID!=string.Empty)
{
	Console.WriteLine(string.Format("Заблокировано пользователем: {0}", sLockID));
}
ls.ClearLock(автор);//Очистка блокировки
Console.ReadLine();
```

Сервис блокировок обращается к хранилищу данных через [сервис данных](fo_data-service.html), указанный в [провайдере сервиса данных](fo_ds-provider.html) (`ICSSoft.STORMNET.Business.DataServiceProvider.DataService`).

В хранилище для блокировок должен существовать соответствующий источник.

Для реляционного хранения, он определён так:

``` sql
SQL
CREATE TABLE STORMNETLOCKDATA (
	LockKey char (300) NOT NULL ,
	UserName char (300) NOT NULL 
)
```
