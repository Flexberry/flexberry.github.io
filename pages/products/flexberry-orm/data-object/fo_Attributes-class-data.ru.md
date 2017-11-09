---
title: Атрибуты классов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, классы данных
summary: Описание атрибутов классов данных и особенности их генерации
toc: true
permalink: ru/fo_attributes-class-data.html
lang: ru
---

## Определение атрибута

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
| "DefaultValue" - значение по-умолчанию (инициализатор). | Никак | Приватному члену прописывается инициализатор с [указанным значением по-умолчанию](fo_features-dafault-value.html). <br>Если указано значение перечислимого типа, то генерируется инициализация значением этого типа. <br>Если тип не перечислимый, то берётся соответствующий .Net-тип и проверяется, есть ли публичное статическое свойство с имением "DefaultValue". <br>Далее, если тип простой стандартный (из namespace System), генерируется простая инициализация константой (напр: int idx=0). <br>В противном случае генерация останавливается с ошибкой.<br>**Замечание:** Если вы создаете свой [собственный тип на диаграмме (класс со стериотипом "type")](fd_data-types-properties.html), то, прежде чем задавать значение по-умолчанию, необходимо произвести компиляцию объектов. Лишь после компиляции можно задавать значение по-умолчанию и производить генерацию кода. |

Существует возможность [указать объект данных в качестве типа атрибута](fo_data-object-as-attribute-type.html).

## Дополнительно редактируемые свойства

Для того чтобы отредактировать дополнительные свойства атрибута необходимо:

* Открыть дополнительные свойства класса; 
* Щёлкнуть на закладке "Атрибуты".

На закладке расположен список всех атрибутов класса:

![](/images/pages/products/flexberry-orm/data-object/attributeprops.jpg)

| Свойство-Описание | Генерация в SQL DDL |Генерация в .Net-язык |
| `AccessModifier` - квалификатор доступа к атрибуту (`public`, `private`, `protected`) |  | Генерируется соответствующий квалификатор при объявлении класса (`public`, `private`, `protected`) | 
| `Stored` - хранимый или нет атрибут | Для хранимых атрибутов генерируется DDL-описание, для нехранимых - нет | Если атрибут нехранимый, то генерируется NotSotedAttribute |   
| `Name` - название атрибута |  |  |  
| `Description` | Никак | DocComment перед определением свойства |
| `Type` - тип атрибута | Отображается в соответствии с картой типов | Отображается в соответствии с картой типов |   
| `DefaultValue` - дублирует определение атрибута |  |  |   
| `NotNull` - указывает, что данный атрибут не может иметь пустого (Null) значения. __Для данного типа обязательно должно быть указано `DefaultValue` (значение по умолчанию)__ |  Если отмечено, то определение поля в CREATE TABLE генерируется как NOT NULL |  Перед определением свойства генерируется указание атрибута NotNullAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) |
| `DataService expression` - некоторое выражение, обеспечивающее счёт вычислимого атрибута | Никак |  Перед определением свойства генерируется указание атрибута [DataServiceExpressionAttribute](fo_not-stored-attributes.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)).
| `Storage` - некоторое логическое имя, под которым хранятся свойства |  Если указано, то такое и будет имя поля |  Перед определением свойства генерируется указание атрибута [PropertyStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)).
| `Order` - использовать ли упорядочение в случаях, когда объекты являются детейлами. | Никак |  Перед определением свойства генерируется указание атрибута [OrderAttribute](fo_functionality-work-detail-array.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
| `Trim` - для атрибутов строкового типа, применять ли удаление лишних пробелов в значениях | Никак | Перед определением свойства генерируется указание атрибута [TrimmedStringStorageAttribute](fo_trimmed-string-storage.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)) <br>При установке значений в свойство [Flexberry ORM](fo_flexberry-orm.html) применяет функцию string.Trim(). |
| `PBCustomAttributes` - [скобка программиста](fo_programmer-brackets.html) | Никак |  Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения .Net атрибутов перед кодом свойства. |
| `PBGetEnd` - [скобка программиста](fo_programmer-brackets.html) | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора get. |
| `PBGetStart` - [скобка программиста](fo_programmer-brackets.html) | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора get. |
| `PBSetEnd` - [скобка программиста](fo_programmer-brackets.html) | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора set. |
| `PBSetStart` - [скобка программиста](fo_programmer-brackets.html) | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора set. |
| `Autoincrement` - поле является автоинкрементируемым (указание галочки корректно только для атрибутов, которые помечены `NotNull` и имеют тип, [маппируемый](fd_types-map.html) на тип `int`) | Если отмечено, то у нового поля в таблице будет генерироваться [IDENTITY(Seed,Increment)](http://msdn.microsoft.com/ru-ru/library/ms186775.aspx), где `Seed` - ядро инкремента, `Increment` - шаг инкремента. По умолчанию `Seed` и `Increment` равны единице. Если указано `DefaultValue`, то в качестве `Seed` берётся значение `DefaultValue`. Если галочка указана - у соответствующего элемента добавляется атрибут [`DisableInsertPropertyAttribute`](fo_disable-insert-property-attribute.html). | Свойство, для которого указан `autoincrement`, будет обновлять значения только при записи данных в базу, следовательно, выносить поле для редактирование этого свойства на форму редактирования бессмысленно и даже вредно. |
| `Hint` - подсказка для поля | Никак. |  Никак (свойство используется не во всех плагинах генерации, генерируется подсказка, отображающая рядом с контролом, где представлено значение поля). |

{% include important.html content="Изменение галочки `Autoincrement` у атрибута, который уже сгенерирован в базу данных, невозможно, поскольку MS SQL Server позволяет выполнить назначение необходимых свойств только путём удаления и повторного создания столбца. Если используется `Autoincrement`, то обновление этого поля происходит не сразу, а после записи в БД. Это связано с механизмом работы  IDENTITY(Seed,Increment). Как следствие, Вы __не увидите__ изменений, пока не перевычитаете данные из БД." %}

## Пример

Если определение свойства выглядит так:

```csharp
+Название:Строка100
```

То код будет следующий:

```csharp
//....

private string fНазвание;

//....

// *** Start programmer edit section *** (Ошибка.Название CustomAttributes)

// *** End programmer edit section *** (Ошибка.Название CustomAttributes)
public virtual string Название
{
	get
	{
		// *** Start programmer edit section *** (Ошибка.Название Get start)

		// *** End programmer edit section *** (Ошибка.Название Get start)
		string result = this.fНазвание;
		// *** Start programmer edit section *** (Ошибка.Название Get end)

		// *** End programmer edit section *** (Ошибка.Название Get end)
		return result;
	}
	set
	{
		// *** Start programmer edit section *** (Ошибка.Название Set start)

		// *** End programmer edit section *** (Ошибка.Название Set start)
		this.fНазвание = value;
		// *** Start programmer edit section *** (Ошибка.Название Set end)

		// *** End programmer edit section *** (Ошибка.Название Set end)
	}
}
```
