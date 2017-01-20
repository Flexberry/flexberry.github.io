---
title: ControlForBindStruct
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/control-for-bind-struct.html
folder: product--folder
lang: ru
---

(((Данная статья ещё редактируется)))

# ControlForBindStruct 
ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct - структура, определяющая контрол для редактирования некоторого свойства (используется, например, при создании [провайдера контролов](control-provider-winforms.html): пример см. [здесь](processing-date-in--control-provider.html)). 

Для данной структуры определены три конструктора:
* `<nowiki>ControlForBindStruct(object control, string controlPropName)</nowiki>`
* `<nowiki>ControlForBindStruct(object control, string controlPropName, Type[] typeMapping</nowiki>)`
* `<nowiki>ControlForBindStruct(object control, string controlPropName, Type[] typeMapping, IComponent[] additionalControls)</nowiki>`

Общее описание аргументов конструктора приведено в статье [Настройка контролов, понятие провайдера контролов, стандартный провайдер контролов](control-provider-winforms.html). Остановимся более подробно на аргументах конструктора.

## control
`control` - экземпляр контрола, который будет редактировать значение (описание взято [отсюда](control-provider-winforms.html)).
```cs
var txtbox = new System.Windows.Forms.TextBox();
var dateTimePicker = new ICSSoft.STORMNET.Windows.Forms.DateTimePicker();
```
## controlPropName
`controlPropName` - имя значимого свойства контрола, т.е. то, в которое устанавливается и возвращается значение (описание взято [отсюда](control-provider-winforms.html)).

Например:
* для контрола типа `System.Windows.Forms.TextBox` : "Text".
* для контрола типа `System.Windows.Forms.CheckBox` : "Checked".
* для контрола типа `System.Windows.Forms.ComboBox` : "Text".
* для контрола типа `ICSSoft.STORMNET.Windows.Forms.DateTimePicker` : "ObjectValue".
* ...

## typeMapping
`typeMapping` - это массив, используемый для [маппирования](control-provider-winforms.html) типов значений, с которыми должен работать `control`.
Например, 

1. если значение типа `System.String` будет обрабатываться с помощью `System.Windows.Forms.TextBox`, то маппинг можно опустить:
```cs
new ControlForBindStruct(new System.Windows.Forms.TextBox(), "Text")
```
2. если значение типа `ICSSoft.STORMNET.UserDataTypes.NullableDateTime` будет обрабатываться с помощью `ICSSoft.STORMNET.Windows.Forms.DateTimePicker`, который работает с типом `System.DateTime`, то необходимо выполнить маппинг (полный пример [здесь](processing-date-in--control-provider.html)):
```cs
ControlForBindStruct(new ICSSoft.STORMNET.Windows.Forms.DateTimePicker(), "ObjectValue", 
							new System.Type[] {typeof(ICSSoft.STORMNET.UserDataTypes.NullableDateTime),
										typeof(System.DateTime)})
```
3. если значение типа `ICSSoft.STORMNET.UserDataTypes.NullableDecimal` будет обрабатываться с помощью `System.Windows.Forms.TextBox`, который работает с типом `System.String`, то необходима цепочка маппинга, поскольку системе известно, как перевести `ICSSoft.STORMNET.UserDataTypes.NullableDecimal` в `System.Decimal`, а из `System.Decimal` уже в `System.String`.
```cs
ControlForBindStruct(new System.Windows.Forms.TextBox(), "Text", 
						new Type[] { typeof(ICSSoft.STORMNET.UserDataTypes.NullableDecimal), 
										typeof(Decimal), 
										typeof(string) }
```
----