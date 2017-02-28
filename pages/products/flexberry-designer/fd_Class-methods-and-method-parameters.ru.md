---
title: Методы классов и параметры методов
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fd_class-methods-and-method-parameters.html
folder: products/flexberry-designer/
lang: ru
---

## Описание метода и что и как с него генерируется
Метод описывается в классе в нотации UML в виде: 
```
[AccessModifier]Name([ParamName:ParamType] [,...])[:Type]
```
Например:
```
+МожетПерейтиВСостояние(состояние:СостояниеОшибки, причина:string):bool
```
{| border="1"
! Что генерируется
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| Name
| Никак
| Определение виртуального метода, это и есть имя метода.
|-
| AccessModifier - модификатор сгенерированного в .Net-язык метода
| Никак
| Соответствующий модификатор в определении метода (# - protected, + - public, - - private)
|-
| ParamName
| Никак
| Имя параметра в определении метода
|-
| ParamType
| Никак
| Имя типа для параметра в определении метода
|-
| Type
| Никак
| Тип возвращаемого методом значения, если не указан, то сгенерируется void.
|}

## Дополнительно редактируемые свойства и что как генерируется
Для того, чтобы отредактировать дополнительные свойства методов, необходимо:
* Открыть дополнительные свойства класса; 
* Щёлкнуть на закладке "Методы". 

На закладке, в верхней части расположен список всех методов класса, в нижней - список параметров выбранного в верхней части метода:
![](/images/pages/img/Flexberry plugins/methods.jpg)
{| border="1"
! Свойство-Описание
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| AccessModifier - дублирует определение атрибута
| Никак
|  
|-
| Name - дублирует определение атрибута
| Никак
|   
|-
| Description - описание
| Никак
| DocComment перед определением метода
|-
| Type - дублирует определение атрибута
| Никак
|  
|-
| IsEvent - указывает, что это не метод, а событие.
| Никак
| [О генерации событий см. здесь](classes-with-stereotype-eventarg.html).
|-
| PBCustomAttributes
| Никак
| Если флажок выставлен, - генерируется скобка программиста
|}
Параметры методов:
{| border="1"
! Свойство-Описание
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| Name
| Никак
| Имя параметра.
|-
| Modifier
| Никак
| Модификатор параметра, соответственно правилам .Net - in, out, byval, byref.
|-
| Type
| Никак
| Тип параметра.
|-
| Description
| Никак
| Описание параметра в DocComment перед определением метода.
|}
## Пример
Если описание метода выглядит как:
```

+МожетПерейтиВСостояние(состояние:СостояниеОшибки, причина:string):bool
```
То исходный код:
```

// *** Start programmer edit section *** (Ошибка.МожетПерейтиВСостояние ICSSoft.BugLeR.СостояниеОшибки System.String CustomAttributes)

// *** End programmer edit section *** (Ошибка.МожетПерейтиВСостояние ICSSoft.BugLeR.СостояниеОшибки System.String CustomAttributes)
public virtual bool МожетПерейтиВСостояние(ICSSoft.BugLeR.СостояниеОшибки состояние, string причина)
{
	// *** Start programmer edit section *** (Ошибка.МожетПерейтиВСостояние ICSSoft.BugLeR.СостояниеОшибки System.String method implementation)
	return true ;
	// *** End programmer edit section *** (Ошибка.МожетПерейтиВСостояние ICSSoft.BugLeR.СостояниеОшибки System.String method implementation)
}
```

