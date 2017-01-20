---
title: Сохранение ограничений на форме задания ограничения 
sidebar: product--sidebar
keywords: Flexberry Winforms, Windows UI (формы), Ограничения, Черновик статьи
toc: true
permalink: ru/fw_adv-limit-function-serialization.html
folder: products/flexberry-winforms/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Winforms](flexberry-winforms.html)
* '''Компонент''': [AdvLimit](limitation-edit-form.html)
* '''Программная библиотека''': ICSSoft.STORMNET.UI.dll, ICSSoft.STORMNET.Windows.Forms.dll
* '''Предназначение''': <!--Краткое описание фичи.-->
</td>
</tr></tbody></table></a>
</div>


# Средства сериализации-десериализации в `ICSSoft.STORMNET.Windows.Forms.Utils` 
В сборке `ICSSoft.STORMNET.Windows.Forms` реализован класс `ICSSoft.STORMNET.Windows.Forms.Utils`, предоставляющий, в частности, методы для сериализации-десериализации, которые применимы в т.ч. для [функции ограничения](limit-function.html). Данные методы представляют собой обертку над соответствующими методами сборки `[ICSSoft.STORMNET.Tools](i-c-s-soft-s-t-o-r-m-n-e-t-tools.html)`, и выполняют обращение к ней. 

Методы сериализации класса `ICSSoft.STORMNET.Windows.Forms.Utils`:

{| border="1"
! Метод
! Описание
|-
| `ObjectToString`
| __Назначение__:  Сериализация в строку при помощи `SoapFormatter`.

__Параметры__:

`o` - Объект

__Возвращаемый результат__: Строка.

__Сигнатура__:

```cs
public static string ObjectToString(object o)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectToString(o);
}
 ```
|-
| `ObjectFromString`
| __Назначение__: Десериализация из строки при помощи `SoapFormatter`.

__Параметры__:

`s` - Сериализованный объект

__Возвращаемый результат__: Востановленный объект.

__Сигнатура__:

```cs
public static object ObjectFromString(string s)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectFromString(s);
}
```
|-
| `ObjectToBinaryString`
| __Назначение__: Сериализация в строку при помощи `BinaryFormatter`.

__Параметры__:

`o` - Объект

__Возвращаемый результат__: Строка.

__Сигнатура__:

```cs
public static string ObjectToBinaryString(object o)
{
    return ICSSoft.STORMNET.Tools.ToolBinarySerializer.ObjectToString(o);
}
```
|-
| `ObjectFromBinaryString`
| __Назначение__: Десериализация из строки при помощи `BinaryFormatter `(если не получится, то попробуем `SoapFormatter`- для совместимости с унаследованными данными).

__Параметры__:

`s` - Сериализованный объект

__Возвращаемый результат__: Востановленный объект.

__Сигнатура__:

```cs
public static object ObjectFromBinaryString(string s)
{
    object retObj;
    try
    {
        retObj = ICSSoft.STORMNET.Tools.ToolBinarySerializer.ObjectFromString(s);
    }
    catch
    {
        retObj = ICSSoft.STORMNET.Tools.ToolXML.ObjectFromString(s);
    }
    return retObj;
}
```
|}

На [форме задания ограничений] при сохранении (восстановлении) [LimitFunction](limitation-edit-form.html) используются методы `Utils.ObjectToBinaryString` (`Utils.ObjectFromBinaryString`), т.е. сериализуются ограничения с помощью бинарной сериализации, а восстанавливаются как бинарные строки, так и SOAP, т.к. в ранних версиях технологии использовалась SOAP-сериализация.

## Пример SOAP-сериализации LimitFunction
```cs
            SQLWhereLanguageDef ldef = SQLWhereLanguageDef.LanguageDef;
            Function fn = ldef.GetFunction(
                ldef.funcAND,
                ldef.GetFunction(
                ldef.funcEQ, new VariableDef(ldef.StringType,"ПарамПамПам"), "кто ходит в гости по утрам"
                ),
                ldef.GetFunction(
                ldef.funcOR,
                ldef.GetFunction(ldef.funcEQ, new VariableDef(ldef.StringType, "ТотПоступаетМудро"), Environment.UserName),
                ldef.GetFunction(ldef.funcIsNull, new VariableDef(ldef.StringType, "НаТоОноИУтро"))
                )
                );

            string serializedFn = Utils.ObjectToString((new ExternalLangDef()).FunctionToSimpleStruct(fn));
            Assert.IsNotNull(serializedFn);
            Console.WriteLine(serializedFn);

            Function восставшийИзНебытия = (
                                new ExternalLangDef()).FunctionFromSimpleStruct(Utils.ObjectFromString(serializedFn));
            Assert.IsNotNull(восставшийИзНебытия);
```
## Пример бинарной сериализации LimitFunction
Этот вид сериализации более производительный и строки получаются короче.
```cs
    string fnStr = "";
    string serializedFn;
    AdvansedLimit advlimit = new AdvansedLimit();
    ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
    advlimit.FromSimpleValue(Utils.ObjectFromString(_serializedFunc), externalLangDef);
    Function fn = advlimit.Function;

    serializedFn = Utils.ObjectToBinaryString(externalLangDef.FunctionToSimpleStruct(fn));
    Assert.IsNotNull(serializedFn);
    
    восставшийИзНебытия =
                externalLangDef.FunctionFromSimpleStruct(Utils.ObjectFromBinaryString(serializedFn));        
    Assert.IsNotNull(восставшийИзНебытия);
```

# Возможная проблема десериализации
В коде выше используется конструкция `advlimit.FromSimpleValue`, которая на основании объекта особого вида строит непосредственно [ограничение](limit-function.html). В структуре передаваемого объекта особого вида, среди прочих, есть [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) типа, из-за чего могут возникнуть проблемы при десериализации: например, если ограничение было создано с неподписанной сборкой, а требуется открыть уже с версией, что была подписана (соответственно, [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) типа изменилось и его нельзя получить через `Type.GetType(...)`), и др. Для этого случая добавлен делегат, который позволяет определить собственный дополнительный метод для получения типа по его имени.

```cs
namespace ICSSoft.STORMNET.Windows.Forms
{
    public class ExternalLangDef
    {
        /// <summary> Делегат для получения типа по его имени в методе SimpleValueToDataObject </summary>
        public static TypeResolveDelegate ExtraTypeResolver = null;
		
		//...
	}
	
	/// <summary>
	/// Делегат для получения типа по его имени (используется в особых случаях, когда стандартные методы почему-то не помогают)
	/// </summary>
	/// <param name="typeName">Имя типа.</param>
	/// <returns> Сформированный по имени тип </returns>
	public delegate Type TypeResolveDelegate(string typeName);
}
```

Ниже представлен пример использования данного делегата:
```cs
	// ...
	var obj = ICSSoft.STORMNET.Windows.Forms.Utils.ObjectFromBinaryString(data);
	var advansedLimit = new AdvansedLimit();
	var externalLangDef = new ExternalLangDef();
	ExternalLangDef.ExtraTypeResolver = ExtraTypeResolver;
	advansedLimit.FromSimpleValue(obj, externalLangDef);
	ExternalLangDef.ExtraTypeResolver = null;
	var result = advansedLimit;

	//...
}

private Type ExtraTypeResolver(string typeName)
{
	if (typeName == "IIS.University.ВидПодразделения, University(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null")
	{
		return typeof(IIS.University.ВидПодразделения);
	}

	return null;
}
```



