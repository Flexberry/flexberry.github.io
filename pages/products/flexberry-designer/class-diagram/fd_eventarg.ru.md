---
title: События и параметры событий (классы со стереотипом eventarg)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, eventarg, стереотип, события, генерация, атрибуты, методы, пример
summary: Особенности генерации событий и их параметров, пример генерации
toc: true
permalink: ru/fd_eventarg.html
lang: ru
---

## Описание события

[Flexberry Designer](fd_flexberry-designer.html) позволяет пользователю описывать события и их аргументы, для возможной последующей генерации в исходный код на CLR-совместимом языке (в частности, `C#`).

События описываются [внутри UML-классов, в секции методов](fd_class-diagram-constraction.html), в следующем виде:

``` csharp
/[AccessModifier)Name([ParamName:TypeEventArgs)
```

{% include note.html content="Параметр должен быть один и его тип был UML-классом со [стереотипом](fd_key-concepts.html) eventarg, если это не так, в процессе генерации произойдёт ошибка." %}

Пример описания аргументов события и самого события:

Пример

### Генерация класса аргументов события

Класс, наследующийся от `System.EventArgs.` Также генерируется определение делегата.

```csharp
   // *** Start programmer edit section *** (Аргументы CustomAttributes)
    // *** End programmer edit section *** (Аргументы CustomAttributes)
    public class Аргументы : System.EventArgs
    {
        private string fарг1;
        // *** Start programmer edit section *** (Аргументы CustomMembers)
        // *** End programmer edit section *** (Аргументы CustomMembers)
        // *** Start programmer edit section *** (Аргументы.арг1 CustomAttributes)
        // *** End programmer edit section *** (Аргументы.арг1 CustomAttributes)
        public virtual string арг1
        {
            get
            {
                // *** Start programmer edit section *** (Аргументы.арг1 Get start)
                // *** End programmer edit section *** (Аргументы.арг1 Get start)
                string result = this.fарг1;
                // *** Start programmer edit section *** (Аргументы.арг1 Get end)
                // *** End programmer edit section *** (Аргументы.арг1 Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Аргументы.арг1 Set start)
                // *** End programmer edit section *** (Аргументы.арг1 Set start)
                this.fарг1 = value;
                // *** Start programmer edit section *** (Аргументы.арг1 Set end)
                // *** End programmer edit section *** (Аргументы.арг1 Set end)
            }
        }
    }
    public delegate void АргументыHandler(object sender, ICSSoft.Product.Аргументы e);
```

## Что генерируется в классе, где определено событие

В исходный код класса генерируется событие, объявленное типом делегата, а также метод, взводящий это событие.

```csharp
 public event ICSSoft.Product.АргументыHandler Событие;

        // *** Start programmer edit section *** (OnСобытие CustomAttributes)
        // *** End programmer edit section *** (OnСобытие CustomAttributes)
        private void OnСобытие(ICSSoft.Product.Аргументы ea)
        {
            if ((this.Событие != null))
            {
                this.Событие(this, ea);
            }
        }
```

## Основные свойства аргументов события

![Пример](/images/pages/products/flexberry-designer/class-diagram/argpropsclass.jpg)

Свойство | Описание | Генерация в .Net-язык
:--------------------|:--------------------|:---------------------------------------------------------
`Name` | Имя UML-класса | Имя `.Net`-класса
`Description` | Некоторое описание класса | `DocComment` перед определением класса
`GenerateCatcher` | | Если установлено, генерируется класс-перехватчик события для использования событийно-ориентированным интерпретатором сценариев (EBSI). Если не планируется EBSI, галочку устанавливать не нужно.
`Packet, NamespacePostfix` | Позволяют настроить сборку и пространство имен, в которое должен генерироваться тип | см. [Расположение сборок после генерации кода](fo_location-assembly.html).
`PBMembers` | Позволяет указать, необходима ли скобка программиста внутри класса для "ручного" внесения членов класса | Если галочка указана - генерируется скобка программиста для "ручного" внесения членов класса.

## Свойства атрибутов аргументов события

![Пример](/images/pages/products/flexberry-designer/class-diagram/argpropsattrs.jpg)

Свойство | Описание | Генерация в .Net-язык
:-------------------|:---------------------------|:----------------------------------------------
`AccessModifier` | Модификатор сгенерированного в .Net-язык свойства | Соответствующий модификатор свойства (# - protected, + - public, - - private)
`Name`, `Type`| |Виртуальное свойство с тем же именем и приватный член класса для этого свойства. Тип свойства и приватного члена - тип атрибута, преобразованный от исходного согласно [карте отображения типов](fd_typedef.html).
`DefaultValue` | Значение по-умолчанию | Приватному члену прописывается инициализатор с указанным значением по-умолчанию. Если указано значение перечислимого типа, то генерируется инициализация значением этого типа. Если тип не перечислимый, то берётся соответствующий .Net-тип и проверяется, есть ли публичное статическое свойство с имением `DefaultValue`. Далее, если тип простой стандартный (из namespace System), генерируется простая инициализация константой (напр: int idx=0). В противном случае генерация останавливается с ошибкой
`Description` | | `DocComment` перед определением свойства
`PBCustomAttributes` | Скобка программиста | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения .Net атрибутов перед кодом свойства.
`PBGetEnd` | Скобка программиста | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора `get`.
`PBGetStart` | Скобка программиста | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора `get`.
`PBSetEnd` | Скобка программиста | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора `set`.
`PBSetStart` | Скобка программиста | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора set.

## Свойства методов аргументов события

Свойства и генерация методов, см. в [статье Методы классов и параметры методов](fd_methods-parameters.html).

![Пример](/images/pages/products/flexberry-designer/class-diagram/eventprops.jpg)

Свойство | Описание | Генерация в .Net-язык
:-------------------|:----------------------------|:-------------------------------------------
`AccessModifier` | Модификатор события | Соответствующий модификатор в определении события (# - protected, + - public, - - private)
`Name` | Имя события | Имя события
`Description` | Описание | `DocComment` перед определением метода OnСобытие
`Type` | | Не учитывается
`IsEvent` | Указывает, что это - событие, а не метод, дублирует "/" в определении события. | Генерируется событие, а не метод.
`PBCustomAttributes` | | Если флажок выставлен, - генерируется скобка программиста перед определением метода OnСобытие
