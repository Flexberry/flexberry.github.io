--- 
title: Prototypical for Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_web-data-object-prototyping.html 
lang: en 
autotranslated: true 
hash: b183e04a8b7667bb6e3f72b60b1e834ac92f2bb12bc12f69e4c62ae22ec62fcc 
--- 

## How prototypical in Flexberry ASP.NET 

If the GET-request for the form transmitted, in addition to the primary key of editable object that `Prototyping=true`, the system understands that you will need to create a new object based on the object with the specified primary key. 

## features of the implementation 

* The very prototypical is executed when the object is saved using the method of [Prototyping](fo_data-object-prototype.html). 
* In the original, there is a lock that is removed when you first save the object's prototype. 

## WOLV and "basing" 

[WOLV](fa_web-object-list-view.html) provides the ability to perform prototypical object. To do this, the appropriate [WOLV](fa_web-object-list-view.html) must include [NewByExampleInRow](fa_wolv-operations.html): 

```csharp
public partial class КредитL : BaseListForm<Кредит>
{
	//... 
	protected override void Preload()
	{
		WebObjectListView1.Operations.NewByExampleInRow = true;
	}
}
``` 

Each line of the web control will have an additional icon, clicking on which will be done no editing of the selected object, and its prototypical. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/