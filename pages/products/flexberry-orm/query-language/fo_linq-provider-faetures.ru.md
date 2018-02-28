---
title: Возможности LinqProvider
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Поддерживаемые типы, особенности, ограничения и использование в объектах с агрегацией, примеры использования
toc: true
permalink: ru/fo_linq-provider-faetures.html
lang: ru
---

Ниже описаны основные возможности, предоставляемые [LINQProvider](fo_linq-provider.html).

В примерах `ds` - это сервис данных, полученный следующим образом:

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService; 
```

## Поддерживаемые типы

### Числа

Помимо численных атрибутов объекта в запросах допускается использовать константы, переменные, а так же функции от них. Все это будет вычислено.
Например, запрос выдающий все объекты с атрибутом Длина, равным атрибуту Ширина + 10:

``` csharp
int Number1 = 8;
int Number2 = 2;
IQueryable<Кошка> queryList = ds.Query<Кошка>();
IQueryable<Кошка> query = from pn in queryList where pn.Длина == Number1 + Number2 + pn.Ширина select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
```

Поддерживаются не только целые числа при построении ограничений.

``` csharp
float length = 10.0f;
IQueryable<Улица> queryList = ds.Query<Улица>();
IQueryable<Улица> query = from pn in queryList where pn.Протяженность > length select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
```

### Строки

#### Contains, StartsWith, EndsWith

Для работы со строками [Linq-провайдер](fo_linq-provider.html) позволяет использовать методы Contains, а также  варианты StartsWith, EndsWith с одним оператором. Использование других вариантов StartsWith и EndsWith вызовет исключение `MethodSignatureException`.

**Выбрать кошек, чьи клички содержат подстроку “ош”**

``` csharp
List<Кошка> cat = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.Кличка.Contains("ош")).ToList();
```

#### Regex

В [LINQProvider](fo_linq-provider.html) есть минимальная поддержка Regex (основные ограничения связаны с тем, что шаблон для регулярного выражения переводится в шаблон поиска для конструкции like в sql).

``` csharp
List<Порода> objss = ds.Query<Порода>(Порода.Views.ПородаE).Where(x => Regex.IsMatch(x.Название, "12.*3")).ToList();
```

Допустимые для использования в регулярных выражениях конструкции: ".", ".*", "^", "$".

### Дата/Время

Для наложения ограничений на даты можно использовать различные свойства `DateTime`. 

[Linq-провайдер](fo_linq-provider.html) не поддерживает метод  `DateTime.AddDays`. При попытке ее использования будет брошено исключение `NotImplementedException`. 

Можно использовать числовые компоненты даты, такие как год, месяц, день в месяце, час минута, день недели.  При этом дни недели преобразуются в числа как в C# в перечислении `DayOfWeek` (0 – воскресение, 1 – понедельник .. 6 – суббота). Для этого в [была добавлена функция DayOfWeekZeroBased в дополнение к старой DayOfWeek](fo_external-lang-def.html).

При попытке использования свойств Ticks, Second, Millisecond, DayOfYear будет брошено исключение `MethodSignatureException`. 

**Выбрать переломы которые были раньше, чем сегодня:**

``` csharp
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.Date < DateTime.Now.Date).ToList();
```

**Применение AddYears**

``` csharp
DateTime now = DateTime.Now;
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.AddYears(1) < now.Date).ToList();
```

**Выбрать переломы, произошедшие в воскресение.**

``` csharp
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.DayOfWeek == DayOfWeek.Sunday).ToList();
```

#### NullableDateTime

Чтобы произвести сравнение `NullableDateTime` с `DateTime` нужно привести их к одному типу __вне__ Linq-запроса.

``` csharp
DateTime date = new DateTime(2012, 1, 1);
NullableDateTime nullableDate = new NullableDateTime { Value = date };
// ДатаВыдачи имеет тип NullableDateTime.
List<Кредит> objss = ds.Query<Кредит>(Кредит.Views.КредитE).Where(к => к.ДатаВыдачи < nullableDate).ToList();
```

### Логический тип

Возможно использование не только выражений, но и их комбинаций с помощью операций алгебры логики, а так же использование констант true и false.

### Перечисление (enum)

Ограничение на перечисление ркомендовано накладывать следующим образом:

``` csharp
// Сначала кэшируем значение enum в переменной, только потом ограничиваем
var типПерелома = ТипПерелома.Открытый;
ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Тип == типПерелома).ToList();
```

## Общие возможности и особенности

### Сравнение с null

При сравнении с null доступна не только проверка на равенство и неравенство, но и работа с операциями ">", ">=", "<", "<=".

Как и при стандартной работе с Linq, выражение вида "`Property > null`", где `Property`, например, типа `int`, вернёт `false`.

``` csharp
ds.Query<Лапа>(Лапа.Views.ЛапаE).Where(x => x.РазмерNullableInt > null);
```

### Выполнение простейших арифметических операций

Внутри Linq-выражения доступно выполнение простейших арифметических операций:

``` csharp
string prefix = "prefix";
string postfix = "postfix";
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(x => x.Кличка == prefix + postfix);
```

## Ограничения LINQProvider

Среди крупных ограничений можно указать "проекции" (проецирование из текущего типа выборки, т.е. потомков DataObject, какого-то другого типа), а также задания ограничений с использованием группировки (Group By).

Для использования проекции или группировки необходимо:

* Получить коллекцию объектов данных, для которой требуется группировка или проекция, с помощью [LINQProvider](fo_linq-provider.html).
* Сделать дополнительный запрос к полученной коллекции с использованием [LINQ to Objects](https://msdn.microsoft.com/ru-ru/library/bb397919.aspx).

## Работа с мастерами/детейлами/псевдодетейлами

### Ключи (PrimaryKey):

Для сравнения объектов по [ключу](fo_primary-keys-objects.html) необходимо использовать метод `Equals`:

``` csharp
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.__PrimaryKey.Equals(кошка.__PrimaryKey));
```

### Мастера

Имеется возможность наложить ограничение на мастера, либо его атрибуты типа int, long, bool, string, DateTime. 
Например:

``` csharp
Порода порода = ds.Query<Порода>(Порода.Views.ПородаE).First(); // Какой-нибудь объект типа порода
Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода == порода); //Получить первую кошку данной породы
Кошка кошка2 = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.Название == "Дикая"); // Получить кошку по названию породы 
```

При этом допускаются ограничения только на мастеров первого уровня. При попытке выполнить следующий код будет брошено исключение `MasterLevelException`.

``` csharp
Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.ТипПороды == порода.ТипПороды); //Использование мастера мастера вызовет исключение
```

Но можно наложить ограничение на мастера n-го уровня по его PrimaryKey:

``` csharp
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => ПородаPKs.Contains(o.Порода.ТипПороды.__PrimaryKey));
```

### Детейлы

С учётом [особенностей написания Linq-запросов к массивам детейлов](fo_functionality-work-detail-array.html):

``` csharp
ds.Query<Порода>(Порода.Views.КотенокE).Where(
                x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "передняя")).ToList();
```

### Псевдодетейлы

Работа с псевдодетейлами описана в статье [Псевдодетейлы в LinqProvider](fo_psedodetails-linq-provider.html).

## Примеры использования

* Простая вычитка всех записей:

``` csharp
var credits = ds.Query<Кредит>(Кредит.Views.КредитL.Name);
```
* Наложение ограничений на строковое поле:

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Фамилия == "Петров");
```
* Наложение ограничений на дату:

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.ДатаРождения > new DateTime(1980, 1, 1));
```

* Наложение ограничений на логический тип:

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен);
```

* Разумеется, условия можно комбинировать: 

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен && l.ДатаУвольнения > new DateTime(2012, 1, 1));
```

* Наложение ограничений на мастер (по ключу):

``` csharp
var кредиты = ds.Query<Кредит>(Кредит.Views.КредитL.Name).Where(k => k.Клиент == klient);
```
* Наложение ограничений на детейлы:

``` csharp
ds.Query<Порода>(Порода.Views.КотенокE).Where(x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "передняя")).ToList();
```
