---
title: Пример пользовательских наименований для структур в БД
sidebar: flexberry-orm_sidebar
keywords: Public, пример, базы данных, Flexberry Desinger, диаграммы
summary: Задание наименований для базы данных
toc: true
permalink: ru/fo_custom-naming-db-structures.html
lang: ru
---

Существует 2 пути для [определения наименования](fo_storing-data-objects.html):

* во Flexberry Desinger
* в C# коде с помощью атрибутов.

Если выбран первый путь, то Flexberry Desinger сгенерирует соответствующие имена автоматически.

Можно [соотнести объекты данных и атрибуты с любым наименованием БД-структуры](fo_storing-data-objects.html):

* Имя таблицы для класса (атрибут [ClassStorage](fd_data-classes.html) для класса);
* Имя столбца для атрибута (атрибут [PropertyStorage](fo_attributes-class-data.html) для свойства);
* Имя столбца [первичного ключа (Primary key)](fo_primary-keys-objects.html) для идентификатора объекта данных (атрибут [PrimaryKeyStorage](fd_data-classes.html) для класса);
* Имя столбца внешнего ключа (Foreign key) для зависимостей мастера (атрибут [PropertyStorage](fd_master-association.html) для свойства);

Например, диаграмма `DB structures custom naming`:

* `CustomDBOwnClass` относится к таблице `CustomDBOwn`;
* `CustomDBOwnClass.CustomOwnAttribute` относится к столбцу `CustomOwn` в таблице `CustomDBOwn`;
* `CustomDBMasterClass` относится к таблице `CustomDBMaster`;
* `CustomDBMasterClass.CustomMasterAttribute` относится к столбцу CustomMaster в таблице `CustomDBOwnCustomDBMaster`;
* Отношение `CustomDBOwnClass` к `CustomDBMasterClass` определяется в столбце `CustomDBMaster` в таблице `CustomDBOwnClass`;
* Идентификаторы обоих классов относятся к столбцу `pkey` в соответствующих таблицах `tables`

Достаточно создать объекты и сохранить их.

```csharp
CustomDBOwnClass cdbo = new CustomDBOwnClass();
CustomDBMasterClass cdbm = new CustomDBMasterClass();
cdbm.CustomMasterAttribute = new RandomStringGenerator().Generate(200);
cdbo.CustomDBMasterClass = cdbm;
cdbo.CustomOwnAttribute = new RandomStringGenerator().Generate(200);

IDataService dataService = DataServiceProvider.DataService;
ICSSoft.STORMNET.DataObject[] objstoupd = new ICSSoft.STORMNET.DataObject[] { cdbo, cdbm };
dataService.UpdateObjects(ref objstoupd);

Console.WriteLine("OK!");
```

Полный список примеров кода Flexberry ORM находится в статье ["Примеры кода"](fo_code-samples.html).
