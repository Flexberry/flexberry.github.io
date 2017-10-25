---
title: Мастеровые ассоциации и их свойства 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, ассоциация, мастер, свойства мастеровой ассоциации, генерация, пример
summary: Особенности мастеровых ассоциаций и их свойства, пример генерации
toc: true
permalink: ru/fd_master-association.html
lang: ru
---

Согласно [базовой статье](fd_key-concepts.html) мастеровыми являются композиции, подобные:

![](/images/pages/products/flexberry-designer/class-diagram/master.png)

{% include note.html content="Принято, чтобы конец ассоциации имел множественность `*`, а начало `1`, либо '0..1` (иначе: проводите ассоциацию от мастерового класса ко внутреннему). Мастеровой класс всегда должен находиться со стороны начала ассоциации, а внутренний - со стороны конца." %}

{% include note.html content="При редактировании ассоциации на [диаграмме классов](fd_class-diagram.html) нужно учитывать [существующие особенности](fd_class-diagram-editor-features-work.html).
" %}

При этом генерируется ассоциация.  
Генерация в SQL DDL. Определение структуры SQL DDL создаётся так:

* В таблице, соотв. внутреннему классу, - ссылка на таблицу, соотв. мастеровому классу; 
* Constraint, связывающий таблицы.

Генерация в .Net-язык. Во внутреннем классе, - приватный член типа мастера и публичное виртуальное свойство с соответствующим модификатором (# - protected, + - public, - - private), поименованное, как имя роли, находящейся со стороны мастера. См. также [Структура классов объектов данных](fo_data-object-structure.html).

### Дополнительно редактируемые свойства и что как генерируется

Cвойства мастеровой ассоциации:

![](/images/pages/products/flexberry-designer/class-diagram/properties.png)

Свойство-Описание | Генерация в SQL DDL | Генерация в .Net-язык
:---------------------|:----------------------------------|:-------------------------------------
`Description` - описание | Никак | Описание в DocComment к свойству внутреннего класса, если оставлено пустым, DocComment не генерируется.
`StartRole` - имя роли со стороны начала (со стороны мастера) | Имя поля в таблице внутреннего класса, являющегося внешним ключом на таблицу мастерового класса, если другое имя не указано свойством Storage | Имя свойства и приватного члена внутреннего класса
`StartMultiplicity` - мощность ассоциации со стороны начала (со стороны мастера) | Если мощность 1, - в DDL скрипт CREATE TABLE для таблицы, соотв. внутреннему классу, в определении поля указывается NOT NULL. Если мощность 0..1 - NULL | Если мощность 1, - перед свойством во внутреннем классе генерируется указание атрибута NotNullAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
`StartRoleAccessModifier` - модификатор со стороны начала (со стороны мастера) | Никак | Соответствующий модификатор свойства (# - protected, + - public, - - private)
`StartRoleStored` | Поле внешнего ключа в таблице, соотв. внутреннему классу, не генерируется. | Перед определением свойства указывается .Net-атрибут NotStoredAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
`EndRole` - имя роли со стороны конца, дублирует на диаграмме | Никак | Не используется (зарезервировано)
`EndMultiplicity` - мощность ассоциации со стороны начала, дублирует на  | Никак | Не используется (зарезервировано)
`AutoGenerateTypeUsage` - автоматическая генерация [используемых типов](fo_type-usage-problem.html). | В DDL-определении таблицы для внутреннего класса генерируются [внешние ключи на каждый найденный класс](fo_storing-data-objects.html). При этом поля именуются следующим образом: @@<Storage>_M<ПорядкНомерВTypeUsage>@@ . «ПорядкНомерВTypeUsage» — начинается с 0. | Мастеровому свойству внутреннего класса прописывается .NET-атрибут [TypeUsageAttribute](fo_type-usage-problem.html), с указанием всех найденных классов.
`Storage` - имя [хранения](fo_storing-data-objects.html) | Если указано, то такое и будет имя поля - внешнего ключа, а не StartRole. | Перед определением свойства генерируется указание атрибута [PropertyStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
`TypeUsage` - явное задание [используемых типов](fo_type-usage-problem.html). Не учитывается при установленном флаге AutoGenerateTypeUsage. [Можно указать имена используемых типов](fo_type-usage.html) (имена мастерового класса и любых его [наследников](fd_inheritance.html), для которых должна быть актуальна данная мастеровая ассоциация), через запятую. | В DDL-определении таблицы для внутреннего класса генерируются [внешние ключи на каждый из указанных классов](fo_storing-data-objects.html). При этом поля именуются следующим образом: @@<Storage>_M<ПорядкНомерВTypeUsage>@@ . «ПорядкНомерВTypeUsage» — начинается с 0. | Мастеровому свойству внутреннего класса прописывается .NET-атрибут [TypeUsageAttribute](fo_type-usage-problem.html), с указанием указанных классов.
`AutoStoreMasterDisabled` - отключение автоматического сохранения мастерового объекта при сохранении основного объекта. | Никак | Перед определением мастерового свойства генерируется указание атрибута AutoStoreMasterDisabled (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
`PBMasterCustomAttributes` | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения .Net атрибутов перед кодом свойства.
`PBMasterGetEnd` | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора get.
`PBMasterGetStart` | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора get.
`PBMasterSetEnd` | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода перед концом аксессора set.
`PBMasterSetStart` | Никак | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения кода после начала аксессора set.

Автоматическая генерация работает следующим образом:

1. Выполняется поиск всех [наследников](fd_inheritance.html) мастерового класса (в пределах стадии);
2. Найденные классы, вместе с мастеровым классом, сортируются по названию по возрастанию; 
3. Результат является определением [используемых типов](fo_type-usage-problem.html).

### Пример

Для вышепоказанной диаграммы код на C# выглядит так:

```csharp
private ICSSoft.BugLeR.Пользователь fРегистратор;
// ........
// *** Start programmer edit section *** (Ошибка.Регистратор CustomAttributes)

// *** End programmer edit section *** (Ошибка.Регистратор CustomAttributes)
[NotNull())
public virtual ICSSoft.BugLeR.Пользователь Регистратор
{
	get
	{
		// *** Start programmer edit section *** (Ошибка.Регистратор Get start)

		// *** End programmer edit section *** (Ошибка.Регистратор Get start)
		ICSSoft.BugLeR.Пользователь result = this.fРегистратор;
		// *** Start programmer edit section *** (Ошибка.Регистратор Get end)

		// *** End programmer edit section *** (Ошибка.Регистратор Get end)
		return result;
	}
	set
	{
		// *** Start programmer edit section *** (Ошибка.Регистратор Set start)

		// *** End programmer edit section *** (Ошибка.Регистратор Set start)
		this.fРегистратор = value;
		// *** Start programmer edit section *** (Ошибка.Регистратор Set end)

		// *** End programmer edit section *** (Ошибка.Регистратор Set end)
	}
}
```
