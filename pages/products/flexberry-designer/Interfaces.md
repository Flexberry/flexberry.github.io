---
title: Интерфейсы (классы со стереотипом interface) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fd_interfaces.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': Классы со [стереотипом](key-concepts-flexberry-designer.html) interface на [диаграмме классов](class-diagram.html) представляют собой интерфейсы.
</td>
</tr></tbody></table></a>
</div>
# Интерфейсы (классы со стереотипом interface)
[Flexberry Designer](flexberry-designer.html) позволяет пользователю описывать интерфейсы, для последующей генерации в исходный код на `CLR`-совместимом языке, в частности, `C#`.

Для описания интерфейса, необходимо создать на диаграмме UML-класс со стереотипом `interface`.

![](/images/pages/img/Flexberry plugins/interface.gif)

```
    public interface Интерфейс
    {
        int Свойство
        {
			get;
			set;
        }
        void Метод();
    }
```

# Что генерируется
Генерируется определение интерфейса.

Атрибуты UML-класса генерируются определением в интерфейсе публичных свойств.

Методы UML-класса генерируются определением в интерфейсе публичных методов.

# Свойства интерфейса
## Основные
![](/images/pages/img/Flexberry plugins/interfaceprop1.jpg)
{| border="2"
! Свойство-Описание
! Генерация в .Net-язык
|-
| `Name` - имя UML-класса
| Имя интерфейса
|-
| `Description` - некоторое описание класса.
| `DocComment` перед определением интерфейса
|-
| `Packet, NamespacePostfix` - позволяют настроить сборку и пространство имен
| см. [Расположение сборок после генерации кода](location-assembly-after-code-generation.html).
|-
| `PBMembers` - позволяет указать, необходима ли [скобка программиста](programmer-brackets.html) внутри класса для "ручного" внесения членов класса.
| Не используется
|-
| `PublishToEBSD`
| Если галочка указана -- перед классом генерируется указание атрибута `PublishToEBSDAttribute`, который указывает доступность данного интерфейса для использования в редакторе диаграмм сценариев.
|}

## Свойства атрибутов
![](/images/pages/img/Flexberry plugins/interfaceprop2.jpg)
{| border="2"
! Свойство-Описание
! Генерация в .Net-язык
|-
| "Name", "Type"- очевидно 
| Виртуальное свойство с тем же именем.
Тип свойства - тип атрибута, преобразованный от исходного согласно [карте отображения типов](types-map.html).
|-
| `Description`
| `DocComment` перед определением свойства
|}

## Свойства методов аргументов события
Свойства и генерация методов для интерфейса происходит, как описано [здесь](class-methods-and-method-parameters.html), за тем исключением, что не генерируется тело метода и [скобки программиста](programmer-brackets.html).


