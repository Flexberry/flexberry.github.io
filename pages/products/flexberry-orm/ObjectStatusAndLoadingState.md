---
title: Статус и состояние загрузки объекта данных
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/object-status-and-loading-state.html
folder: product--folder
lang: ru
---

Статусы объектов данных могут быть:


#`ObjectStatus.UnAltered` — объект данных не изменён.

#`ObjectStatus.Created` — объект данных создан.

#`ObjectStatus.Altered` — объект данных изменён.

#`ObjectStatus.Deleted` — объект данных удалён.

Статус объекта можно выяснить методом объекта данных `GetStatus`, а установить — `SetStatus`. Статус позволяет выяснить, какие именно изменения были внесены в объект данных.

Статус `Altered` может вычисляться автоматически в момент вызова метода `GetStatus`. Для этого необходимо классу объекта данных приписать атрибут `AutoAltered`. Если этот атрибут не приписан, то установку статуса `Altered` программист должен выполнять «вручную». Следует внимательно пользоваться данной возможностью, помнить, что установка `AutoAltered` замедляет работу, поскольку ''при каждом получении статуса происходит реальная проверка свойств объекта данных на изменение''. По-умолчанию `AutoAltered=True;`

Существует возможность не перевычислять статус при установленном `AutoAltered`, для этого необходимо вызывать метод `GetStatus(false)`.

Проверить, установлен ли атрибут `AutoAltered`, можно методом `[Information-obtainingmetadata|Information].AutoAlteredClass`.

Состояние загрузки объекта данных:


#`LoadingState.NotLoaded` — объект данных не загружен.

#`LoadingState.Loaded` — вычитаны все собственные атрибуты объекта данных, все мастеровые первого уровня, все детейловые первого уровня.

#`LoadingState.LightLoaded` — объект данных частично загружен, подробности могут быть выяснены вызовом у объекта данных методов `[GetLoadedProperties](get-loaded-properties-and--check-loaded-property.html)` и `[CheckLoadedProperty](get-loaded-properties-and--check-loaded-property.html)`.

Состояние загрузки можно выяснить через метод `GetLoadingState`, а установить — `SetLoadingState`. 
Следует понимать, что если объект `NotLoaded`, то он может быть только `Created` и наоборот, если `Loaded/LightLoaded`, то он ни при каких условиях не может быть `Created`. Также, разумеется, объект не бывает `NotLoaded UnAltered`.

Когда объект данных создан, он всегда `NotLoaded Created`, то же самое и при вызове метода Clear.

## DataObject.SetStatus()
Данный метод имеет специальную логику, которая срабатывает при переключении статуса. 

{| border="1" 
|+ '''Результат установки статуса объекту данных'''
|- style="background-color: Red; color: White;" 
| '''Устанавливаемое \ Старое''' || UnAltered || Created || Altered || Deleted
|-
| bgcolor="Red" style="color: White;" | UnAltered 
| UnAltered || '''Created''' || UnAltered || '''Created если NotLoaded, иначе UnAltered'''
|-
| bgcolor="Red" style="color: White;" | Created   
| Created '''+ NotLoaded''' || Created '''+ NotLoaded''' || Created '''+ NotLoaded''' || Created '''+ NotLoaded'''
|-
| bgcolor="Red" style="color: White;" | Altered
| Altered || '''Created''' || Altered || '''Created если NotLoaded, иначе Altered'''
|-
| bgcolor="Red" style="color: White;" | Deleted 
| Deleted || Deleted || Deleted || Deleted
|}

* Примечание: если вам нужно перевести объект из состояния `Created` в состояние `Altered`, то достаточно вызвать метод `SetLoadingState(LoadingState.LightLoaded)` тем самым указав, что объект в базе присутствует. Более правильный способ - это вызвать `SetExistObjectPrimaryKey(object primaryKey)` - это не только указывает на то, что объект в БД присутсвует, но и сразу задаёт правильный [ключ](primary-keys-objects.html) этого самого объекта в базе.


## Изменение состояния после UpdateObject
[Обработка статуса и состояния загрузки объекта сервисами данных](processing-status-and-condition-of-load-object-data-services.html)

## Проверка на изменение свойств пользовательского типа
Если объект данных содержит свойства пользовательского типа (например, тип Деньги), то по-умолчанию сравнение значений такого атрибута самого объекта и его копии данных будет производиться через приведение этого атрибута к строке методом `ToString()`, а затем сравнением строк. Не для всех типов данный подход верен и работает быстро, поэтому существует интерфейс `IComparableType`, который содержит метод для сравнения. Если пользовательский тип унаследован от этого типа, то сравнение будет осуществляться не через строки, а вызовом специального метода, содержащегося в интерфейсе. Рекомендуется применять этот интерфейс для всех пользовательских типов атрибутов.


