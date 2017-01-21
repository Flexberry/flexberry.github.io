---
title: Сериализация LimitFunction
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_limit-function--serialization.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Tools.dll,ICSSoft.STORMNET.FunctionalLanguage.dll, ExternalLangDef.dll
* '''Предназначение''': Сериализация [функции ограничения](limit-function.html) средствами библиотеки `[ICSSoft.STORMNET.Tools](i-c-s-soft-s-t-o-r-m-n-e-t-tools.html)`.
</td>
</tr></tbody></table></a>
</div>

# Подход к сериализации LimitFunction

Класс `[Function](limit-function.html)` реализует интерфейс `ISerializable`. Поэтому для функций ограничения доступна как `SOAP`, так и бинарная сериализация. 

Рекомендуется использовать инструменты библиотеки `[ICSSoft.STORMNET.Tools.XmlTools](i-c-s-soft-s-t-o-r-m-n-e-t-tools.html)` вместо прямого обращения к `System.Runtime.Serialization.Formatters.Soap.SoapFormatter` или `System.Runtime.Serialization.Formatters.Binary.BinaryFormatter`.
 

В указанной библиотеке доступны классы:
* `ToolXML` - позволяет выполнить сериализацию средствами `SoapFormatter`,
* `ToolBinarySerializer` - позволяет выполнить сериализацию средствами `BinaryFormatter`.

В технологическом коде для сохранения `[Function](limit-function.html)` используется `binary`-сериализация:
* в Windows-приложениях при [сохранении ограничений на форме задания ограничения](adv-limit-function-serialization.html), 
* в Web-приложениях для сохранения `[Function](limit-function.html)` в `Session`(веб-сессия ASP.NET), когда сессия хранится в `StateServer`, т.е. в отдельно запущенной службе.

# Пример сравнения бинарной и SOAP сериализации LimitFunction

Бинарный вид сериализации более производительный и строки получаются короче.
```cs
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
