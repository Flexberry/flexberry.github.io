---
title: Пример: нехранимые и вычислимые свойства
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/nonstored_calculated_properties.html
folder: product--folder
lang: ru
---

В этом примере показывается, как использовать вычислимые свойства.

Давайте посмотрим на пример определения вычислимого свойства для объекта Person:
```
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

```

            IDataService dataService = DataServiceProvider.DataService;
            LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Person), Person.Views.Person_E);

            // Загрузить все объекты данных. Нехранимое свойство будет вычислено с помощью выражения в геттере.
            ICSSoft.STORMNET.DataObject[] persons = dataService.LoadObjects(lcs);

            // Загрузка в виде строкового представления, свойства отделены друг от друга точкой с запятой. Нехранимое свойство будет вычислено с помощью выражения в атрибуте DataServiceExpression.
            ObjectStringDataView[] osdvpersons = dataService.LoadStringedObjectView(';', lcs);

            Console.WriteLine("OK.");
```