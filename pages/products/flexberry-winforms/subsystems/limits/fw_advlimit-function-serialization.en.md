---
title: restrictions on the form of task constraints
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Windows UI (form) Constraints, a draft of the article
summary: Lists the methods of the class `ICSSoft.STORMNET.Windows.Forms.Utils` for serialization-десериализации; specified which ones are used for the limit function, given примеры; described problem deserializing limitations when using signed and unsigned assemblies, the approach to the solution
toc: true
permalink: en/fw_advlimit-function-serialization.html
folder: products/flexberry-winforms/
lang: en
autotranslated: true
hash: 73af8f8400c3bc2b5290d65c44c34732dd4e09f9e16416ab4eef8f50178bd166
---

## Means serialization-deserialization in `ICSSoft.STORMNET.Windows.Forms.Utils`
In the Assembly `ICSSoft.STORMNET.Windows.Forms` class is implemented `ICSSoft.STORMNET.Windows.Forms.Utils`, providing, inter alia, methods for serialization-deserialization, which are applicable including for [parental control](fo_limit-function.html). These methods are a wrapper over the respective methods of Assembly [`ICSSoft.STORMNET.Tools`](fo_ics-soft-stormnet-tools.html), and perform it.

### The serialization methods of the class `ICSSoft.STORMNET.Windows.Forms.Utils`:

#### `ObjectToString`

__Assign__: Serialize to a string using `SoapFormatter`.

__Settings__:

`o` Object

__The result is__: String.

__Signature__:

```csharp
public static string ObjectToString(object o)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectToString(o);
}
```

#### `ObjectFromString`

__Assign__: Deserialize from a string with `SoapFormatter`.

__Settings__:

`s` - Serialized object

__The result is__: the Restored object.

__Signature__:

```csharp
public static object ObjectFromString(string s)
{
	return ICSSoft.STORMNET.Tools.ToolXML.ObjectFromString(s);
}
```

#### `ObjectToBinaryString`
__Assign__: Serialize to a string using `BinaryFormatter`.

__Settings__:

`o` Object

__The result is__: String.

__Signature__:

```csharp
public static string ObjectToBinaryString(object o)
{
    return ICSSoft.STORMNET.Tools.ToolBinarySerializer.ObjectToString(o);
}
```

#### `ObjectFromBinaryString`
__Assign__: Deserialize from a string with `BinaryFormatter `(if it does not, then try `SoapFormatter` - for compatibility with legacy data).

__Settings__:

`s` - Serialized object

__The result is__: the Restored object.

__Signature__:

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


On [form constraint] while maintaining (recovery) [LimitFunction](fw_limitation-editform.html) are used `Utils.ObjectToBinaryString` (`Utils.ObjectFromBinaryString`), i.e. restrictions are serialized using binary serialization, and restored as a binary string, and SOAP, as in earlier versions of the technology have used the SOAP serialization.

### An example SOAP serialization LimitFunction

```csharp            
            Function fn = FunctionBuilder.BuildAnd(
                FunctionBuilder.BuildEquals("Parampampam", "who goes to visit in the morning"),
                FunctionBuilder.BuildOr(
                    FunctionBuilder.BuildEquals("Compositepicture", Environment.UserName),
                    FunctionBuilder.BuildIsNull("Nationair")
                )
            );             

            string serializedFn = Utils.ObjectToString((new ExternalLangDef()).FunctionToSimpleStruct(fn));
            Assert.IsNotNull(serializedFn);
            Console.WriteLine(serializedFn);

            Function восставшийИзНебытия = (
                                new ExternalLangDef()).FunctionFromSimpleStruct(Utils.ObjectFromString(serializedFn));
            Assert.IsNotNull(восставшийИзНебытия);
```

### An example of binary serialization LimitFunction
This type of serialization is more productive and the rows get shorter.

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

## Possible problem deserializing
In the above code is used to design `advlimit.FromSimpleValue`, which is based on the object of a special type builds directly [limit](fo_limit-function.html). In the structure of the transmitted object is a special type, among others, have [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) type, which can cause problems during deserialization: for example, if the constraint was created with an unsigned Assembly, you want to open already with the version that was signed (respectively, [`AssemblyQualifiedName`](http://msdn.microsoft.com/ru-ru/library/system.type.assemblyqualifiedname.aspx) the type of change and it is impossible to get through `Type.GetType(...)`), etc. For this case, added a delegate that allows you to define your own additional method to get type by its name.

```csharp
namespace ICSSoft.STORMNET.Windows.Forms
{
    public class ExternalLangDef
    {
        /// <summary> Delegate for getting the type by its name in the method SimpleValueToDataObject </summary> 
        public static TypeResolveDelegate ExtraTypeResolver = null;
		
		//... 
	}
	
	/// <summary> 
	/// Delegate to retrieve a type by its name (used in special cases when the standard methods somehow) 
	/// </summary> 
	/// <param name="typeName">type Name.</param> 
	/// <returns> Generated name of the type </returns> 
	public delegate Type TypeResolveDelegate(string typeName);
}
```

Below is an example of using this delegate:
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
	if (typeName == "IIS.University.Videokasete University(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null")
	{
		return typeof(IIS.University.ВидПодразделения);
	}

	return null;
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}