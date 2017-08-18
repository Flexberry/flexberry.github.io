---
title: Скобки программиста
sidebar: flexberry-orm_sidebar
keywords: CASE Plugins, Flexberry ORM, Public
toc: true
permalink: en/fo_programmer-brackets.html
folder: products/flexberry-orm/
lang: en
---

## Изменения проекта и перегенерация кода

Скобки программиста представляют собой структуру вида

``` csharp 
// *** Start programmer edit section *** ({0})

// *** End programmer edit section *** ({0})
```

где {0} указывает на позицию скобки в коде.

Если код добавлен между скобками программиста, то он будет сохранён при перегенерации приложения.

<div markdown="span" class="alert alert-info" role="important"><i class="fa fa-info-circle"></i>При работе с автогенерируемыми файлами будьте внимательны и добавляйте код в правильное место.</div>

Структура кода относительно скобок программиста выглядит следующим образом:

```csharp
Генерируемый код
Открывающая скобка программиста
Добавленный в проект код // Не потеряется при перегенерации.
Закрывающая скобка программиста
Генерируемый код
Добавленный в проект код // !! Потеряется при перегенерации, т.к. находится вне скобок программиста.
Открывающая скобка программиста
Добавленный в проект код // Не потеряется при перегенерации.
Закрывающая скобка программиста
Генерируемый код
...
```

## Настройка скобок программиста

При генерации кода добавляются следующие скобки программиста:

* Заданные по умолчанию для конкретной генерируемой сущности.
* Заданные настройками, определёнными на интерфейсе (например, [свойствами PBCustomAttributes и PBMembers классов данных](fd_data-classes.html)).

Добавление пользовательских скобок программиста не поддерживается.

## Возникающие ошибки

Если при генерации через [Flexberry Designer](fd_landing_page.html) в лог выводится информация следующего типа:

* `Ошибка: Не найдено завершение скобки в файле "D:\Проекты\КредитыTestBuild\Кредиты\Objects\Клиент.cs"`, значит, где-то в указанном файле была удалена или добавлена лишняя скобка.

* исключение типа DuplicateBraceFoundedException значит,что где-то добавлена лишняя открывающая скобка.
* исключение типа NotFoundEndOfBraceException значит, что где-то удалена закрывающая скобка.

## Пример

Рассмотрим на примере.

В классе ниже встречается 12 скобок программиста, позволяющих внести изменения в любую часть кода. У каждой скобки свое предназначение и свое место. К примеру, скобки 

``` csharp 
// *** Start programmer edit section *** (Клиент CustomMembers)

// *** End programmer edit section *** (Клиент CustomMembers)
``` 

отвечают за добавление собственных членов класса. К примеру, если мы захотим добавить метод, возвращающий строку вида "ФИО (Прописка)", то добавлять этот метод нужно будет именно в скобки. 

Предположим, что метод выглядит следующим образом:

``` csharp 
public string GetFullClientString()
{
    return string.Format("{0} ({1})", ФИО, Прописка);
}
```

Тогда, после добавления его в __правильное__ место, код класса (отрывок) будет выглядеть так:

``` csharp
        private string fФИО;
        
        private string fПрописка;
        
        // *** Start programmer edit section *** (Клиент CustomMembers)

        public string GetFullClientString()
        {
            return string.Format("{0} ({1})", ФИО, Прописка);
        }

        // *** End programmer edit section *** (Клиент CustomMembers)
```

Код сгенерированного класса:

``` csharp
namespace IIS.Product_20008
{
    using System;
    using System.Xml;
    using ICSSoft.STORMNET;
    
    /// <summary>
    /// Клиент
    /// </summary>
    // *** Start programmer edit section *** (Клиент CustomAttributes)

    // *** End programmer edit section *** (Клиент CustomAttributes)
    public class Клиент : ICSSoft.STORMNET.DataObject
    {
        
        private string fФИО;
        
        private string fПрописка;
        
        // *** Start programmer edit section *** (Клиент CustomMembers)

        // *** End programmer edit section *** (Клиент CustomMembers)

        
        /// <summary>
        /// Клиент
        /// </summary>
        // *** Start programmer edit section *** (Клиент.ФИО CustomAttributes)

        // *** End programmer edit section *** (Клиент.ФИО CustomAttributes)
        [StrLen(255))
        public virtual string ФИО
        {
            get
            {
                // *** Start programmer edit section *** (Клиент.ФИО Get start)

                // *** End programmer edit section *** (Клиент.ФИО Get start)
                string result = this.fФИО;
                // *** Start programmer edit section *** (Клиент.ФИО Get end)

                // *** End programmer edit section *** (Клиент.ФИО Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Клиент.ФИО Set start)

                // *** End programmer edit section *** (Клиент.ФИО Set start)
                this.fФИО = value;
                // *** Start programmer edit section *** (Клиент.ФИО Set end)

                // *** End programmer edit section *** (Клиент.ФИО Set end)
            }
        }
        
        /// <summary>
        /// Клиент
        /// </summary>
        // *** Start programmer edit section *** (Клиент.Прописка CustomAttributes)

        // *** End programmer edit section *** (Клиент.Прописка CustomAttributes)
        [StrLen(255))
        public virtual string Прописка
        {
            get
            {
                // *** Start programmer edit section *** (Клиент.Прописка Get start)

                // *** End programmer edit section *** (Клиент.Прописка Get start)
                string result = this.fПрописка;
                // *** Start programmer edit section *** (Клиент.Прописка Get end)

                // *** End programmer edit section *** (Клиент.Прописка Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Клиент.Прописка Set start)

                // *** End programmer edit section *** (Клиент.Прописка Set start)
                this.fПрописка = value;
                // *** Start programmer edit section *** (Клиент.Прописка Set end)

                // *** End programmer edit section *** (Клиент.Прописка Set end)
            }
        }
    }
}
```
