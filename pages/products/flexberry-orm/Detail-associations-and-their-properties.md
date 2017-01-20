---
title: Детейловые ассоциации и их свойства 
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/detail-associations-and-their-properties.html
folder: product--folder
lang: ru
---

Согласно "Аксиом Flexberry Platform" детейловыми являются композиции, подобные:
![](/images/pages/img/Flexberry plugins/detail.gif)
'''Важное замечание''': Принято, чтобы конец ассоциации имел множественность *, а начало (где ромбик) - 1. [Класс-шапка](key-concepts-flexberry-designer.html) всегда должен находиться со стороны начала композиции, а детейловый класс - со стороны конца.
{| border="1"
! Что генерируется
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| композиция 
| Определение структуры SQL DDL создаётся так:

 * В таблице, соотв. детейловому классу, - внешний ключ на таблицу, соотв. [шапке](key-concepts-flexberry-designer.html); 
 * Constraint, связывающий таблицы; 
| См. [Учебник программиста Flexberry Platform](Учебник-программиста--flexberry-platform.html) раздел [Структура классов объектов данных](data-object-generated-structure.html).

Вкратце:

1. В классе-детейле генерируется свойство с приписанным .Net-атрибутом AgregatorAttribute и приватный член, оба с типом [класса-шапки](key-concepts-flexberry-designer.html); 

2. Для хранения наборов детейловых объектов генерируется специальный класс - наследник от DetailArray;

 3. В [классе-шапке](key-concepts-flexberry-designer.html) генерируется свойство и приватный член, объявленные типом спец.класса, - наследника от DetailArray. 
|}
## Дополнительно редактируемые свойства и что как генерируется
Открыв свойства композиции, мы увидим:
![](/images/pages/img/Flexberry plugins/composition.jpg)
{| border="1"
! Свойство-Описание
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| Description - описание детейлового свойства.
| Никак
| Описание (DocComment) свойства доступа к массиву детейловых объектов в [классе-шапке](key-concepts-flexberry-designer.html)
|-
| StartRole - имя роли со стороны начала композиции, дублирует имя роли на диаграмме
| в CREATE TABLE таблицы детейла генерируется определение поля для внешнего ключа с этим именем.
| Имя свойства-агрегатора в классе-детейле.
|-
| StartMultiplicity - множественность со стороны начала ассоциации, дублирует на диаграмме. Может принимать любые значения, однако при генерации может быть только 1. Если другое значение - генерация остановится с ошибкой.
| Никак
| Никак
|-
| EndRole - имя роли со стороны конца композиции
| Никак
| Имя свойства и приватного члена в [классе-шапке](key-concepts-flexberry-designer.html)
|-
| EndRoleStored
| Никак
| Приватный член в [классе-шапке](key-concepts-flexberry-designer.html) не генерируется и код аксессоров get и set в свойстве остаётся пустым (просто по две [скобки программиста](programmer-brackets.html) в каждом аксессоре)
|-
| Aggregator AutogenerateTypeUsage - автоматическая генерация используемых типов для [агрегатора (шапки)](key-concepts-flexberry-designer.html) в классе-детейле.

Автоматическая генерация работает следующим образом:

1. Выполняется поиск всех наследников агрегатора (в пределах стадии);

 2. Найденные классы, вместе с агрегатором, сортируются по названию по возрастанию;

 3. Результат является определением используемых типов. 
О используемых типах см. документ [Учебник программиста Flexberry Platform](Учебник-программиста--flexberry-platform.html) раздел "Работа с объектами данных\Наследование" (пункт 14-15).
| В DDL-определении таблицы для класса - детейла генерируются внешние ключи на каждый найденный класс. При этом поля именуются следующим образом: ```
<AggregatorStorage>_M<ПорядкНомерВTypeUsage>
```. «ПорядкНомерВTypeUsage» — начинается с 0. 
| Свойству, указывающему на агрегатора, детейлового класса прописывается .NET-атрибут TypeUsageAttribute, с указанием всех найденных классов.
|-
| Aggregator TypeUsage - используемые типы для [агрегатора (шапки)](key-concepts-flexberry-designer.html) в классе-детейле.

Не учитывается при установленном флаге Aggregator AutogenerateTypeUsage

Можно указать имена используемых типов (имена агрегатора и любых его наследников, для которых должна быть актуальна данная детейловая ассоциация), через запятую.

О используемых типах см. документ [Учебник программиста Flexberry Platform](Учебник-программиста--flexberry-platform.html) раздел "Работа с объектами данных\Наследование" (пункт 14-15).
| В DDL-определении таблицы для класса - детейла генерируются внешние ключи на каждый из указанных классов. При этом поля именуются следующим образом: ```
<AggregatorStorage>_M<ПорядкНомерВTypeUsage>
```. «ПорядкНомерВTypeUsage» — начинается с 0. 
| Свойству, указывающему на агрегатора, детейлового класса прописывается .NET-атрибут TypeUsageAttribute, с указанием указанных классов.
|-
| AggregatorStorage - имя хранения свойства-агрегатора детейлового класса.
| Если указано, то такое и будет имя поля - внешнего ключа в таблице, соотв. классу-детейлу, а не StartRole.
| Перед определением свойства, указывающего на агрегатор в детейловом классе, генерируется указание атрибута PropertyStorageAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))

Как работает этот атрибут, см. документ [Учебник программиста Flexberry Platform](Учебник-программиста--flexberry-platform.html) раздел [DataObjectsAndDatabaseStructures#AttributeDefinitionStorage|Именование при хранении объектных данных].
|-
| Detail AutogenerateTypeUsage - автоматическая генерация используемых типов для детейла в [классе-шапке](key-concepts-flexberry-designer.html).

Автоматическая генерация работает следующим образом:

1. Выполняется поиск всех наследников детейла (в пределах стадии);

 2. Найденные классы, вместе с детейлом, сортируются по названию по возрастанию;

 3. Результат является определением используемых типов. 

О используемых типах см. документ [Учебник программиста Flexberry Platform](Учебник-программиста--flexberry-platform.html) раздел "Работа с объектами данных\Наследование"(пункт 14-15).
| Никак
| Свойству, указывающему на массив детейловых объектов, [класса-шапки](key-concepts-flexberry-designer.html), прописывается .NET-атрибут TypeUsageAttribute, с указанием указанных классов.
|-
| Detail TypeUsage - используемые типы для детейла в [классе-шапке](key-concepts-flexberry-designer.html).
| Никак
| Свойству, указывающему на массив детейловых объектов, [класса-шапки](key-concepts-flexberry-designer.html), прописывается .NET-атрибут TypeUsageAttribute, с указанием всех найденных классов.
|-
| PBAggregatorCustomAttributes
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения .Net атрибутов перед кодом свойства-агрегатора в детейле.
|-
| PBAggregatorGetEnd
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода перед концом аксессора get свойства-агрегатора в детейле.
|-
| PBAggregatorGetStart
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода после начала аксессора get свойства-агрегатора в детейле.
|-
| PBAggregatorSetEnd
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода перед концом аксессора set свойства-агрегатора в детейле.
|-
| PBAggregatorSetStart
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода после начала аксессора set свойства-агрегатора в детейле.
|-
| PBDetailCustomAttributes
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения .Net атрибутов перед кодом свойства, указывающего на массив детейловых объектов.
|-
| PBDetailGetEnd
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода перед концом аксессора get свойства, указывающего на массив детейловых объектов..
|-
| PBDetailGetStart
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода после начала аксессора get свойства, указывающего на массив детейловых объектов..
|-
| PBDetailSetEnd
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода перед концом аксессора set свойства, указывающего на массив детейловых объектов..
|-
| PBDetailSetStart
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения кода после начала аксессора set свойства, указывающего на массив детейловых объектов..
|}

## Пример
Для вышепоказанной диаграммы код на C# выглядит так:


Код для класса-детейла (класс-детейл - Шаг, свойство агрегатор с приватным членом - Ошибка, класс для хранения массива детейловых объектов - DetailArrayOfШаг):
```
 public class Шаг : ICSSoft.STORMNET.DataObject
    {
        private int fНомер;
        private string fОписание;
        private ICSSoft.BugLeR.Ошибка fОшибка;       
        // *** Start programmer edit section *** (Шаг CustomMembers)
        
        // *** End programmer edit section *** (Шаг CustomMembers)
        
        // *** Start programmer edit section *** (Шаг.Номер CustomAttributes)

        // *** End programmer edit section *** (Шаг.Номер CustomAttributes)
        public virtual int Номер
        {
            get
            {
                // *** Start programmer edit section *** (Шаг.Номер Get start)

                // *** End programmer edit section *** (Шаг.Номер Get start)
                int result = this.fНомер;
                // *** Start programmer edit section *** (Шаг.Номер Get end)

                // *** End programmer edit section *** (Шаг.Номер Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Шаг.Номер Set start)

                // *** End programmer edit section *** (Шаг.Номер Set start)
                this.fНомер = value;
                // *** Start programmer edit section *** (Шаг.Номер Set end)

                // *** End programmer edit section *** (Шаг.Номер Set end)
            }
        }
        // *** Start programmer edit section *** (Шаг.Описание CustomAttributes)

        // *** End programmer edit section *** (Шаг.Описание CustomAttributes)
        public virtual string Описание
        {
            get
            {
                // *** Start programmer edit section *** (Шаг.Описание Get start)

                // *** End programmer edit section *** (Шаг.Описание Get start)
                string result = this.fОписание;
                // *** Start programmer edit section *** (Шаг.Описание Get end)

                // *** End programmer edit section *** (Шаг.Описание Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Шаг.Описание Set start)

                // *** End programmer edit section *** (Шаг.Описание Set start)
                this.fОписание = value;
                // *** Start programmer edit section *** (Шаг.Описание Set end)

                // *** End programmer edit section *** (Шаг.Описание Set end)
            }
        }
        
        /// <summary>
        /// мастеровая ссылка на шапку ICSSoft.BugLeR.Ошибка
        /// </summary>
        // *** Start programmer edit section *** (Шаг.Ошибка CustomAttributes)

        // *** End programmer edit section *** (Шаг.Ошибка CustomAttributes)
        [Agregator()]
        public virtual ICSSoft.BugLeR.Ошибка Ошибка
        {
            get
            {
                // *** Start programmer edit section *** (Шаг.Ошибка Get start)

                // *** End programmer edit section *** (Шаг.Ошибка Get start)
                ICSSoft.BugLeR.Ошибка result = this.fОшибка;
                // *** Start programmer edit section *** (Шаг.Ошибка Get end)

                // *** End programmer edit section *** (Шаг.Ошибка Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Шаг.Ошибка Set start)

                // *** End programmer edit section *** (Шаг.Ошибка Set start)
                this.fОшибка = value;
                // *** Start programmer edit section *** (Шаг.Ошибка Set end)

                // *** End programmer edit section *** (Шаг.Ошибка Set end)
            }
        }
    }
    
    /// <summary>
    /// Detail array of Шаг
    /// </summary>
    // *** Start programmer edit section *** (DetailArrayDetailArrayOfШаг CustomAttributes)

    // *** End programmer edit section *** (DetailArrayDetailArrayOfШаг CustomAttributes)
    public class DetailArrayOfШаг : ICSSoft.STORMNET.DetailArray
    {
        
        // *** Start programmer edit section *** (ICSSoft.BugLeR.DetailArrayOfШаг members)
        
        // *** End programmer edit section *** (ICSSoft.BugLeR.DetailArrayOfШаг members)
        
        /// <summary>
        /// Construct detail array
        /// </summary>
        public DetailArrayOfШаг(ICSSoft.BugLeR.Ошибка fОшибка) : 
                base(typeof(Шаг), ((ICSSoft.STORMNET.DataObject)(fОшибка)))
        {
        }
        
        /// <summary>
        /// Returns object with type Шаг by index
        /// </summary>
        public ICSSoft.BugLeR.Шаг this[int index]
        {
            get
            {
                return ((ICSSoft.BugLeR.Шаг)(this.ItemByIndex(index)));
            }
        }
        
        /// <summary>
        /// Adds object with type Шаг
        /// </summary>
        public virtual void Add(ICSSoft.BugLeR.Шаг dataobject)
        {
            this.AddObject(((ICSSoft.STORMNET.DataObject)(dataobject)));
        }
    }
```
Код для [класса-шапки](key-concepts-flexberry-designer.html) (фрагмент класса Ошибка: приватный член и свойство типа массива детейловых объектов - DetailArrayOfШаг):
```

public class Ошибка : ICSSoft.STORMNET.DataObject
    {

        // ...

        private ICSSoft.BugLeR.DetailArrayOfШаг fШагиДляВоспроизведения;

        // ...

        // *** Start programmer edit section *** (Ошибка.ШагиДляВоспроизведения CustomAttributes)

        // *** End programmer edit section *** (Ошибка.ШагиДляВоспроизведения CustomAttributes)
        public virtual ICSSoft.BugLeR.DetailArrayOfШаг ШагиДляВоспроизведения
        {
            get
            {
                // *** Start programmer edit section *** (Ошибка.ШагиДляВоспроизведения Get start)

                // *** End programmer edit section *** (Ошибка.ШагиДляВоспроизведения Get start)
                if ((this.fШагиДляВоспроизведения == null))
                {
                    this.fШагиДляВоспроизведения = new ICSSoft.BugLeR.DetailArrayOfШаг(this);
                }
                ICSSoft.BugLeR.DetailArrayOfШаг result = this.fШагиДляВоспроизведения;
                // *** Start programmer edit section *** (Ошибка.ШагиДляВоспроизведения Get end)

                // *** End programmer edit section *** (Ошибка.ШагиДляВоспроизведения Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Ошибка.ШагиДляВоспроизведения Set start)

                // *** End programmer edit section *** (Ошибка.ШагиДляВоспроизведения Set start)
                this.fШагиДляВоспроизведения = value;
                // *** Start programmer edit section *** (Ошибка.ШагиДляВоспроизведения Set end)

                // *** End programmer edit section *** (Ошибка.ШагиДляВоспроизведения Set end)
            }
        }

        // ...

    }
```
