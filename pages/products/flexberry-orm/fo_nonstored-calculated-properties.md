---
title: Пример нехранимых и вычислимых свойств
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/fo_nonstored-calculated-properties.html
---

В этом примере показывается, как использовать вычислимые свойства.

Давайте посмотрим на пример определения вычислимого свойства для объекта Person:

```csharp
        [ICSSoft.STORMNET.NotStored()]
        [StrLen(255)]
        [DataServiceExpression(typeof(SQLDataService), "isnull(@FirstName@,\'\') + \' \' + isnull(@LastName@,\'\')")]
        public virtual string FullName
        {
            get
            {
                return string.Format("{0} {1}", fFirstName, fLastName);
            }
            set
            {
            }
        }
```

В атрибуте DataServiceExpression определено выражение, которое будет использоваться сервисом данных при выполнении запроса из таблицы.
Эквивалентный этому выражению код на C# написан в геттере свойства.

```csharp
            IDataService dataService = DataServiceProvider.DataService;
            LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Person), Person.Views.Person_E);

            // Загрузить все объекты данных. Нехранимое свойство будет вычислено с помощью выражения в геттере.
            ICSSoft.STORMNET.DataObject[] persons = dataService.LoadObjects(lcs);

            // Загрузка в виде строкового представления, свойства отделены друг от друга точкой с запятой. Нехранимое свойство будет вычислено с помощью выражения в атрибуте DataServiceExpression.
            ObjectStringDataView[] osdvpersons = dataService.LoadStringedObjectView(';', lcs);

            Console.WriteLine("OK.");
```