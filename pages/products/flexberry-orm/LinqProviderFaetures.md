---
title: Возможности LinqProvider
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/linq-provider-faetures.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.LINQProvider.dll.
* '''Предназначение''': Описание основных возможностей, предоставляемых [LINQProvider](l-i-n-q-provider.html).
</td>
</tr></tbody></table></a>
</div>

Ниже описаны основные возможности, предоставляемые [LINQProvider](l-i-n-q-provider.html).

В примерах "`ds`" - это сервис данных, полученный следующим образом:
```cs
var ds = (SQLDataService)DataServiceProvider.DataService; ```
# Поддерживаемые типы
## Числа
Помимо численных атрибутов объекта в запросах допускается использовать константы, переменные, а так же функции от них. Все это будет вычислено.
Например, запрос выдающий все объекты с атрибутом Длина, равным атрибуту Ширина + 10:
```cs
int Number1 = 8;
int Number2 = 2;
IQueryable<Кошка> queryList = ds.Query<Кошка>();
IQueryable<Кошка> query = from pn in queryList where pn.Длина == Number1 + Number2 + pn.Ширина select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
```

Поддерживаются не только целые числа при построении ограничений.
```cs
float length = 10.0f;
IQueryable<Улица> queryList = ds.Query<Улица>();
IQueryable<Улица> query = from pn in queryList where pn.Протяженность > length select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
```

## Строки
### Contains, StartsWith, EndsWith
Для работы со строками [Linq-провайдер](l-i-n-q-provider.html) позволяет использовать методы Contains, а также  варианты StartsWith, EndsWith с одним оператором. Использование других вариантов StartsWith и EndsWith вызовет исключение `MethodSignatureException`.

'''Выбрать кошек, чьи клички содержат подстроку “ош”'''
```cs
List<Кошка> cat = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.Кличка.Contains("ош")).ToList();
```

### Regex
В [LINQProvider](l-i-n-q-provider.html) есть минимальная поддержка Regex (основные ограничения связаны с тем, что шаблон для регулярного выражения переводится в шаблон поиска для конструкции like в sql).
```cs
List<Порода> objss = ds.Query<Порода>(Порода.Views.ПородаE).Where(x => Regex.IsMatch(x.Название, "12.*3")).ToList();
```

Допустимые для использования в регулярных выражениях конструкции: ".", ".*", "^", "$".

## Дата/Время
Для наложения ограничений на даты можно использовать различные свойства DateTime. 

[Linq-провайдер](l-i-n-q-provider.html) не поддерживает метод  DateTime.AddDays. При попытке ее использования будет брошено исключение `NotImplementedException`. 

Можно использовать числовые компоненты даты, такие как год, месяц, день в месяце, час минута, день недели.  При этом дни недели преобразуются в числа как в C# в перечислении `DayOfWeek` (0 – воскресение, 1 – понедельник .. 6 – суббота). Для этого в [была добавлена функция DayOfWeekZeroBased в дополнение к старой DayOfWeek](external-lang-def.html).

При попытке использования свойств Ticks, Second, Millisecond, DayOfYear будет брошено исключение `MethodSignatureException`. 

'''Выбрать переломы которые были раньше, чем сегодня:'''
```cs
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.Date < DateTime.Now.Date).ToList();
```

'''Применение AddYears'''
```cs
DateTime now = DateTime.Now;
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.AddYears(1) < now.Date).ToList();
```

'''Выбрать переломы, произошедшие в воскресение.'''
```

List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.DayOfWeek == DayOfWeek.Sunday).ToList();
```

### NullableDateTime
Чтобы произвести сравнение `NullableDateTime` с `DateTime` нужно привести их к одному типу __вне__ Linq-запроса.
```

DateTime date = new DateTime(2012, 1, 1);
NullableDateTime nullableDate = new NullableDateTime { Value = date };
// ДатаВыдачи имеет тип NullableDateTime.
List<Кредит> objss = ds.Query<Кредит>(Кредит.Views.КредитE).Where(к => к.ДатаВыдачи < nullableDate).ToList();
```

## Логический тип
Возможно использование не только выражений, но и их комбинаций с помощью операций алгебры логики, а так же использование констант true и false.

## Перечисление (enum):
Ограничение на перечисление лучше накладывать в следующем виде (пока нет полной поддержки enum):
```

// Сначала кэшируем значение enum в переменной, только потом ограничиваем
var типПерелома = ТипПерелома.Открытый;
ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Тип == типПерелома).ToList();
```

# Общие возможности и особенности
## Сравнение с `null`
При сравнении с null доступна не только проверка на равенство и неравенство, но и работа с операциями ">", ">=", "<", "<=".

Как и при стандартной работе с Linq, выражение вида "`Property > null`", где `Property`, например, типа `int`, вернёт `false`.
```cs
ds.Query<Лапа>(Лапа.Views.ЛапаE).Where(x => x.РазмерNullableInt > null);
```

## Выполнение простейших арифметических операций
Внутри Linq-выражения доступно выполнение простейших арифметических операций:
```cs
string prefix = "prefix";
string postfix = "postfix";
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(x => x.Кличка == prefix + postfix);
```

# Что (пока) НЕ может LINQProvider
Возможности [LINQProvider](l-i-n-q-provider.html) на настоящий момент ограничены.

Среди крупных ограничений можно указать "проекции" (проецирование из текущего типа выборки, т.е. потомков DataObject, какого-то другого типа), а также задания ограничений с использованием группировки (Group By).

Для использования проекции или группировки необходимо:
* Получить коллекцию объектов данных, для которой требуется группировка или проекция, с помощью [LINQProvider](l-i-n-q-provider.html).
* Сделать дополнительный запрос к полученной коллекции с использованием [LINQ to Objects](https://msdn.microsoft.com/ru-ru/library/bb397919.aspx).

# Работа с мастерами/детейлами/псевдодетейлами
## Ключи (PrimaryKey):
Для сравнения объектов по [ключу](primary-keys-objects.html) необходимо использовать метод `Equals`:
```

ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.__PrimaryKey.Equals(кошка.__PrimaryKey));
```

## Мастера
Имеется возможность наложить ограничение на мастера, либо его атрибуты типа int, long, bool, string, DateTime. 
Например:
```

Порода порода = ds.Query<Порода>(Порода.Views.ПородаE).First(); // Какой-нибудь объект типа порода
Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода == порода); //Получить первую кошку данной породы
Кошка кошка2 = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.Название == "Дикая"); // Получить кошку по названию породы 
```

При этом допускаются ограничения только на мастеров первого уровня. При попытке выполнить следующий код будет брошено исключение `MasterLevelException`.
```

Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.ТипПороды == порода.ТипПороды); //Использование мастера мастера вызовет исключение
```

Но можно наложить ограничение на мастера n-го уровня по его PrimaryKey:
```

ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => ПородаPKs.Contains(o.Порода.ТипПороды.__PrimaryKey));
```

## Детейлы
С учётом [особенностей написания Linq-запросов к массивам детейлов](functionality-work-with-detail-array.html):

```

ds.Query<Порода>(Порода.Views.КотенокE).Where(
                x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "передняя")).ToList();
```

## Псевдодетейлы
Работа с псевдодетейлами описана в [отдельной статье](psedo-details-in-linq-provider.html).

# Примеры использования
*Простая вычитка всех записей:
```
var credits = ds.Query<Кредит>(Кредит.Views.КредитL.Name);
```
*Наложение ограничений на строковое поле:
```
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Фамилия == "Петров");
```
*Наложение ограничений на дату:
```
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.ДатаРождения > new DateTime(1980, 1, 1));
```
*Наложение ограничений на логический тип:
```
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен);
```
*Разумеется, условия можно комбинировать: 
```
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен && l.ДатаУвольнения > new DateTime(2012, 1, 1));
```
*Наложение ограничений на мастер (по ключу):
```
var кредиты = ds.Query<Кредит>(Кредит.Views.КредитL.Name).Where(k => k.Клиент == klient);
```
*Наложение ограничений на детейлы:
```
ds.Query<Порода>(Порода.Views.КотенокE).Where(x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "передняя")).ToList();
```
