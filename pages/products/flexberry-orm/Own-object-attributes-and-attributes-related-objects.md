---
title: Доступ к собственным атрибутам объекта и атрибутам связанных объектов
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_own-object-attributes-and-attributes-related-objects.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Объект данных](dataobject.html)
* '''Программная библиотека''': ICSSoft.STORMNET.DataObject.dll
* '''Предназначение''': Описаны основные способы доступа к собственным атрибутам объекта и атрибутам связанных объектов.
</td>
</tr></tbody></table></a>
</div>

# Получение значения свойства
Доступ к атрибутам [объекта данных](dataobject.html) и атрибутам связанных объектов стандартен.
```
SimpleDataObject sdo = new SimpleDataObject();
sdo.Name="Something";//Доступ к собственному атрибуту
sdo.Master.DblAttr=3.14;//Доступ к мастеровому атрибуту
sdo.Details[0].StringAttr="DetailAttribute";//Доступ к детейловому атрибуту
```

# Cтроготипизированное получение свойств в виде строк
Вместо того, чтобы использовать названия атрибутов в виде строк-констант, например:
```

var propertyName = ((Пользователь)dataObject).Наименование;
```

Можно использовать строготипизированный доступ с использованием лямбды методами [Information](information-class-as-metadata-supervisor.html):
```

var propertyName = Information.ExtractPropertyName<Пользователь>(x => x.Наименование);
```

Все доступные методы:
```

        /// <summary>
        /// Извлечение свойства внутри текущего класса
        /// <code>
        /// // Пример использования:
        /// ExtractPropertyName[T](() => objectInstance.Name); // вернет "Name", T - тип объекта objectInstance
        /// ExtractPropertyName[T](() => objectInstance.Master.Name); // вернет "Name", T - тип объекта objectInstance
        /// </code>
        /// </summary>
        /// <typeparam name="TSource"> Тип класса - источника </typeparam>
        /// <param name="propertyExpression"> Лямбда - выражение для доступа к свойству </param>
        /// <returns> Имя свойства (одиночное!) </returns>
        public static string ExtractPropertyName<TSource>(Expression<Func<TSource>> propertyExpression)

        /// <summary>
        /// Explicit извлечение свойства по типу
        /// <code>
        /// // Пример использования:
        /// ExtractPropertyName(a =&gt; a.Name); // вернет "Name"
        /// ExtractPropertyName(a =&gt; a.b.c.Name); // вернет "Name"
        /// </code>
        /// </summary>
        /// <typeparam name="TSource">
        /// Тип класса - источника 
        /// </typeparam>
        /// <param name="propertyExpression">
        /// Лямбда - выражение для доступа к свойству 
        /// </param>
        /// <returns>
        /// Имя свойства (одиночное!) 
        /// </returns>
        public static string ExtractPropertyName<TSource>(Expression<Func<TSource, object>> propertyExpression)

        /// <summary>
        /// Рекурсивный метод получения пути для свойства, заданного через вложенную лямбду.
        /// Лямбда-выражение может содержать вложенные обращения к мастерам.
        /// <code>
        /// // Пример использования:
        /// ExtractPropertyPath[T](() => objectInstance.Name); // вернет "Name", T - тип объекта objectInstance
        /// ExtractPropertyPath[T](() => objectInstance.Master.Name); // вернет "Master.Name", T - тип объекта objectInstance
        /// </code>
        /// </summary>
        /// <typeparam name="TProperty"> Тип свойства </typeparam>
        /// <param name="propertyExpression"> Лямбда - выражение для доступа к свойству </param>
        /// <returns> Полный путь к свойству (разделение через точку) </returns>
        public static string ExtractPropertyPath<TProperty>(Expression<Func<TProperty>> propertyExpression)

        /// <summary>
        /// Рекурсивный метод получения пути для свойства, заданного через вложенную лямбду.
        /// Лямбда-выражение может содержать вложенные обращения к мастерам.
        /// <code>
        /// // Пример использования:
        /// ExtractPropertyPath(a => a.Name); // вернет "Name"
        /// ExtractPropertyPath(a => a.b.c.Name); // вернет "b.c.Name"
        /// </code>
        /// </summary>
        /// <typeparam name="TSource"> Тип класса - источника </typeparam>
        /// <param name="propertyExpression"> Лямбда - выражение для доступа к свойству </param>
        /// <returns>
        /// Полный путь к свойству (разделение через точку) 
        /// </returns>
        public static string ExtractPropertyPath<TSource>(Expression<Func<TSource, object>> propertyExpression)

        /// <summary>
        /// Explicit извлечение свойства по типу
        /// </summary>
        /// <typeparam name="TSource"> Тип класса - источника </typeparam>
        /// <param name="propertyExpression"> Лямбда - выражение для доступа к свойству </param>
        /// <returns> <see cref="PropertyInfo"/> свойства (самого последнего) </returns>
        public static PropertyInfo ExtractPropertyInfo<TSource>(Expression<Func<TSource, object>> propertyExpression)
```
