---
title: Сохранение ограничений на форме задания ограничения 
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Windows UI (формы), Ограничения, Черновик статьи
summary: Перечислены методы класса `ICSSoft.STORMNET.Windows.Forms.Utils` для сериализации-десериализации; указано какие из них применяются для функции ограничения, приведены примеры; описана проблема десериализации ограничений при использовании  подписанных и неподписанных сборок, указан подход к решению
toc: true
permalink: ru/fw_advlimit-function-serialization.html
folder: products/flexberry-winforms/
lang: ru
---

## Средства сериализации-десериализации в `ICSSoft.STORMNET.Windows.Forms.Utils` 
В сборке `ICSSoft.STORMNET.Windows.Forms` реализован класс `ICSSoft.STORMNET.Windows.Forms.Utils`, предоставляющий, в частности, методы для сериализации-десериализации, которые применимы в т.ч. для [функции ограничения](fo_limit-function.html). Данные методы представляют собой обертку над соответствующими методами сборки [`ICSSoft.STORMNET.Tools`](fo_ics-soft-stormnet-tools.html), и выполняют обращение к ней. 

### Методы сериализации класса `ICSSoft.STORMNET.Windows.Forms.Utils`:

#### `ObjectToString`

 __Назначение__:  Сериализация в строку при помощи `SoapFormatter`. 

__Параметры__:

`o` - Объект

__Возвращаемый результат__: Строка.

__Сигнатура__:

```csharp
public static string ObjectToString(object o)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectToString(o);
}
```
 
#### `ObjectFromString`
 
 __Назначение__: Десериализация из строки при помощи `SoapFormatter`.

__Параметры__:

`s` - Сериализованный объект

__Возвращаемый результат__: Востановленный объект.

__Сигнатура__:

```csharp
public static object ObjectFromString(string s)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectFromString(s);
}
```

#### `ObjectToBinaryString`
 __Назначение__: Сериализация в строку при помощи `BinaryFormatter`.

__Параметры__:

`o` - Объект

__Возвращаемый результат__: Строка.

__Сигнатура__:

```csharp
public static string ObjectToBinaryString(object o)
{
    return ICSSoft.STORMNET.Tools.ToolBinarySerializer.ObjectToString(o);
}
```

#### `ObjectFromBinaryString`
 __Назначение__: Десериализация из строки при помощи `BinaryFormatter `(если не получится, то попробуем `SoapFormatter`- для совместимости с унаследованными данными).

__Параметры__:

`s` - Сериализованный объект

__Возвращаемый результат__: Востановленный объект.

__Сигнатура__:

```csharp
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


На [форме задания ограничений] при сохранении (восстановлении) [LimitFunction](fw_limitation-editform.html) используются методы `Utils.ObjectToBinaryString` (`Utils.ObjectFromBinaryString`), т.е. сериализуются ограничения с помощью бинарной сериализации, а восстанавливаются как бинарные строки, так и SOAP, т.к. в ранних версиях технологии использовалась SOAP-сериализация.

### Пример SOAP-сериализации LimitFunction

```csharp            
            Function fn = FunctionBuilder.BuildAnd(
                FunctionBuilder.BuildEquals("ПарамПамПам", "кто ходит в гости по утрам"),
                FunctionBuilder.BuildOr(
                    FunctionBuilder.BuildEquals("ТотПоступаетМудро", Environment.UserName),
                    FunctionBuilder.BuildIsNull("НаТоОноИУтро")
                )
            );             

            string serializedFn = Utils.ObjectToString((new ExternalLangDef()).FunctionToSimpleStruct(fn));
            Assert.IsNotNull(serializedFn);
            Console.WriteLine(serializedFn);

            Function восставшийИзНебытия = (
                                new ExternalLangDef()).FunctionFromSimpleStruct(Utils.ObjectFromString(serializedFn));
            Assert.IsNotNull(восставшийИзНебытия);
```

### Пример бинарной сериализации LimitFunction
Этот вид сериализации более производительный и строки получаются короче.

```csharp
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

## Возможная проблема десериализации
В коде выше используется конструкция `advlimit.FromSimpleValue`, которая на основании объекта особого вида строит непосредственно [ограничение](fo_limit-function.html). В структуре передаваемого объекта особого вида, среди прочих, есть [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) типа, из-за чего могут возникнуть проблемы при десериализации: например, если ограничение было создано с неподписанной сборкой, а требуется открыть уже с версией, что была подписана (соответственно, [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) типа изменилось и его нельзя получить через `Type.GetType(...)`), и др. Для этого случая добавлен делегат, который позволяет определить собственный дополнительный метод для получения типа по его имени.

```csharp
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
```csharp
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