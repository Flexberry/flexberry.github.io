---
title: Атрибуты классов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fo_attributes-class-data.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Редактор UML-диаграмм](fd_editing-diagram.html)
* **Предназначение**: На [диаграмме классов](fd_class-diagram.html) [атрибут в классе](fd_class-diagram-constraction.html) определяется в соответствии с нотацией UML.

## Определение атрибута и что как с него генерируется

На [диаграмме классов](fd_class-diagram.html) [атрибут в классе](fd_class-diagram-constraction.html) определяется строкой вида:

```
[/)[AccessModifier)Name:Type[=DefaultValue)
```

, что соответствует нотации UML.

```
+ДатаРегистрации:DateTime=Now
+Статус:Статус=Рег
+idx:int=0
```

| Что генерируется | Генерация в SQL DDL |Генерация в .Net-язык |
|---|---|---|
| "/" - если указано, [атрибут является нехранимым](fo_not-stored-attributes.html) | Если [атрибут не является хранимым](fo_not-stored-attributes.html) определение поля в CREATE TABLE не генерируется | Перед определением свойства указывается .Net-атрибут NotStoredAttribute (Namespace: ICSSoft.STORMNET, Assembly:ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) |
| "AccessModifier" - модификатор сгенерированного в .Net-язык свойства | Никак | соответствующий модификатор свойства (# - protected, + - public, - - private) |
| "Name", "Type"- очевидно | в CREATE TABLE генерируется определение поля с именем атрибута (если другое имя не указано в дополнительном свойстве Storage), с типом, преобразованным от исходного согласно  [карте отображения типов](fd_types-map.html) | Виртуальное свойство с тем же именем и приватный член класса для этого свойства.Тип свойства и приватного члена - тип атрибута, преобразованный от исходного согласно [карте отображения типов](fd_types-map.html). |


