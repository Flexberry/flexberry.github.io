--- 
title: Batch reading 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: Rules for the use of batch reading 
toc: true 
permalink: en/fo_reading-portion.html 
lang: en 
autotranslated: true 
hash: a63da461154bd0cee34b02f90fe8cd8d0e1b3012b62712ca7632057c3eacd74d 
--- 

A very convenient possibility is to read the data objects in portions, piece by piece. There is a possibility to call the read operation so that in addition to portions of data objects [data service](fo_data-service.html) returned a read state. Transmitting the subsequent read operations is the state, you can get another portion. 

To perform batch reading should: 

* Set [LoadingCustomizationStruct](fo_loading-customization-struct.html) additionally the serving size through property `LoadingCustomizationStruct`. 
* Call the read method of the objects with the parameters `LoadingCustomizationStruct` and state to state. 
* Perform follow-up pochityvaya as. 

``` csharp
IDataService ds = DataServiceProvider.DataService;
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Автор), Автор.Views.Главное);					
lcs.LoadingBufferSize = 1; // The batch size. 
object state = null; // Return state of data service. 
DataObject[) dataobjects = ds.LoadObjects(lcs, ref state); // Call the service data state is remembered. 
prvPrintPortion(dataobjects); // Print the first portion. 
while (dataobjects.Length &gt; 0) // Until something else is returned. 
{
	Console.WriteLine("Press Enter to read the next portion of the authors.");
	Console.ReadLine();
	dataobjects = ds.LoadObjects(ref state); // Read the next portion. Lcs already do not share. 
	prvPrintPortion(dataobjects); // Print another batch. 
} 

Console.WriteLine("More authors no. The end.");
Console.Read();
``` 

{% include note.html content="the Signature of the method call `LoadObjects` __different__. For pochityvaya data is a method call with 1 parameter `ref state`." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}