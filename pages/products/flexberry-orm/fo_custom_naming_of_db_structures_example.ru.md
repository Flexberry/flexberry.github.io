---
title: Пример пользовательских наименований для структур в БД
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/fo_custom_naming_of_db_structures_example.html
---

Существует 2 пути для [определения наименования](fo_data-objects-and-database-structures.html): во Flexberry и в C# коде с помощью атрибутов.
Если вы выберете первый путь, то Flexberry сгенерирует соответствующие имена автоматически.

Вы можете [соотнести объекты данных и атрибуты к любому наименованию БД-структуры](fo_data-objects-and-database-structures.html):

* Имя таблицы для класса (атрибут [ClassStorage](fd_data-classes.html) для класса);
* Имя столбца для атрибута (атрибут [PropertyStorage](fo_attributes-class-data.html) для свойства);
* Имя столбца [первичного ключа (Primary key)](fo_primary-keys-objects.html) для идентификатора объекта данных (атрибут [PrimaryKeyStorage](fd_data-classes.html) для класса);
* Имя столбца внешнего ключа (Foreign key) для зависимостей мастера (атрибут [PropertyStorage](fd_master-association.html) для свойства);

Например, давайте посмотрим на диаграмму "DB structures custom naming":

* CustomDBOwnClass соотносится к таблице CustomDBOwn;
* CustomDBOwnClass.CustomOwnAttribute соотносится к столбцу CustomOwn в таблице CustomDBOwn;
* CustomDBMasterClass соотносится к таблице CustomDBMaster;
* CustomDBMasterClass.CustomMasterAttribute соотносится к столбцу CustomMaster в таблице CustomDBOwnCustomDBMaster;
* Отношение CustomDBOwnClass к CustomDBMasterClass определяется в столбце CustomDBMaster в таблице CustomDBOwnClass;
* Идентификаторы обоих классов соотносятся к столбцу pkey в соответствующих таблицах tables;

Смотрите, просто создайте объекты и сохраните их - это реально работает!

```cs
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
