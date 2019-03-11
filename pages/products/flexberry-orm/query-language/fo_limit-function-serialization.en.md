--- 
title: Serialization LimitFunction 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Approach and example of serialization functions limitations 
toc: true 
permalink: en/fo_limit-function-serialization.html 
lang: en 
autotranslated: true 
hash: 10c44078343eb52029784a9f3c430961e36878845be289ddf555de9f46c4e95e 
--- 

## Approach to serialization LimitFunction 

Class [`Function`](fo_limit-function.html) implements the interface `ISerializable`. Therefore, for functions of limited available as `SOAP` and binary serialization. 

It is recommended to use library tools [ICSSoft.STORMNET.Tools.XmlTools](fo_ics-soft-stormnet-tools.html) instead of a direct appeal to `System.Runtime.Serialization.Formatters.Soap.SoapFormatter` or `System.Runtime.Serialization.Formatters.Binary.BinaryFormatter`. 

In the specified library classes available: 

* `ToolXML` - allows to serialize means `SoapFormatter`, 
* `ToolBinarySerializer` - allows to serialize means `BinaryFormatter`. 

## Example comparing binary and SOAP serialization LimitFunction 

Binary form serialization is more productive and the rows get shorter. 

```csharp
[TestMethod]        
public void FunctionSerializeTst()        
{
    Serialize(true);            
    Serialize(false);        
}

private void Serialize(bool binary)
{
    DateTime start = DateTime.Now;
    string fnStr = "";

    ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
    SQLWhereLanguageDef ldef = SQLWhereLanguageDef.LanguageDef;
    Function fn = ldef.GetFunction(
                ldef.funcAND,
                ldef.GetFunction(
                ldef.funcEQ, new VariableDef(ldef.StringType,"Parampampam"), "who goes to visit in the morning"
                ),
                ldef.GetFunction(
                ldef.funcOR,
                ldef.GetFunction(ldef.funcEQ, new VariableDef(ldef.StringType, "Compositepicture"), Environment.UserName),
                ldef.GetFunction(ldef.funcIsNull, new VariableDef(ldef.StringType, "Nationair"))
                )
                );


    for (int i = 0; i < 1000; i++)
    {
        string serializedFn;
        if (binary)
        {
            serializedFn = ToolBinarySerializer.ObjectToString(externalLangDef.FunctionToSimpleStruct(fn));
        }
        else
        {
            serializedFn = ToolXML.ObjectToString(externalLangDef.FunctionToSimpleStruct(fn));
        }
        Assert.IsNotNull(serializedFn);
        Function восставшийИзНебытия;
        if (binary)
        {
            восставшийИзНебытия =
                externalLangDef.FunctionFromSimpleStruct(ToolBinarySerializer.ObjectFromString(serializedFn));
        }
        else
        {
            восставшийИзНебытия = externalLangDef.FunctionFromSimpleStruct(ToolXML.ObjectFromString(serializedFn));
        }
        Assert.IsNotNull(восставшийИзНебытия);
        fnStr = "The length of the serialized string: " + serializedFn.Length + Environment.NewLine
                + serializedFn.Substring(0, 50) + Environment.NewLine + "lf: " + восставшийИзНебытия;
    }            
    Console.WriteLine("Run time " + (DateTime.Now - start).TotalMilliseconds);
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}