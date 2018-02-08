---
title: Сериализация LimitFunction
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Подход и пример сериализации функции ограничения
toc: true
permalink: ru/fo_limit-function-serialization.html
lang: ru
---

## Подход к сериализации LimitFunction

Класс [`Function`](fo_limit-function.html) реализует интерфейс `ISerializable`. Поэтому для функций ограничения доступна как `SOAP`, так и бинарная сериализация. 

Рекомендуется использовать инструменты библиотеки [ICSSoft.STORMNET.Tools.XmlTools](fo_ics-soft-stormnet-tools.html) вместо прямого обращения к `System.Runtime.Serialization.Formatters.Soap.SoapFormatter` или `System.Runtime.Serialization.Formatters.Binary.BinaryFormatter`.

В указанной библиотеке доступны классы:

* `ToolXML` - позволяет выполнить сериализацию средствами `SoapFormatter`,
* `ToolBinarySerializer` - позволяет выполнить сериализацию средствами `BinaryFormatter`.

## Пример сравнения бинарной и SOAP сериализации LimitFunction

Бинарный вид сериализации более производительный и строки получаются короче.

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
                ldef.funcEQ, new VariableDef(ldef.StringType,"ПарамПамПам"), "кто ходит в гости по утрам"
                ),
                ldef.GetFunction(
                ldef.funcOR,
                ldef.GetFunction(ldef.funcEQ, new VariableDef(ldef.StringType, "ТотПоступаетМудро"), Environment.UserName),
                ldef.GetFunction(ldef.funcIsNull, new VariableDef(ldef.StringType, "НаТоОноИУтро"))
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
        fnStr = "Длина сериализованной строки: " + serializedFn.Length + Environment.NewLine
                + serializedFn.Substring(0, 50) + Environment.NewLine + " lf: " + восставшийИзНебытия;
    }            
    Console.WriteLine("Время выполнения " + (DateTime.Now - start).TotalMilliseconds);
}
```
