---
title: Открытие формы редактирования с передачей объекта с частично заполненными полями
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Windows UI (формы)
toc: true
permalink: ru/fa_open-editform-custom-object.html
lang: ru
---

Форма редактирования позволяет не только создавать новый объект с нуля, но и создать объект, в котором некоторые поля уже заполнены. 

Чтобы открыть web-форму редактирования с передачей объекта с частично заполненными полями, необходимо произвести POST-запрос по адресу формы редактирования с параметром `DataObject`. Значение параметра `DataObject` - объект данных, сериализованный в XML и закодированный в HTML.

Никаких ограничений на передаваемый объект данных не налагается. Статус передаваемого объекта не играет роли, т.к. после передачи и десериализации объекта он устанавливается в `ObjectStatus.Created`.

Сериализация объекта производится с помощью `ICSSoft.STORMNET.Tools.ToolXML` [подробнее d cnfnmt ICSSoft.STORMNET.Tools.XmlTools](fo_ics-soft-stormnet-tools.html).

После сериализации XML необходимо закодировать в HTML для безопасной передачи данных. Eсли `ASP.NET Request Validation` увидит угловые скобочки, то может кинуть исключение `A potentially dangerous Request.Form value was detected from the client`; подробнее об этом [на сайте www.asp.net](http://www.asp.net/whitepapers/aspnet4/breaking-changes#0.1__Toc256770147) и [social.msdn.microsoft.com](http://social.msdn.microsoft.com/forums/en-US/netfxbcl/thread/a056886b-a1ad-40f8-9f4a-f7e8db39950b/). Закодировать можно с помощью [Server.HTMLEncode](http://msdn.microsoft.com/en-us/library/ms525347(v=vs.90).aspx) или [HttpUtility.HtmlEncode](http://msdn.microsoft.com/ru-ru/library/system.web.httputility.htmlencode.aspx).

### Пример

```csharp
// Сериализация объекта данных для POST-запроса на сервере
var xmldoc = ICSSoft.STORMNET.Tools.ToolXML.DataObject2XMLDocument(ref dobj);
string serializedObj = System.Web.HttpUtility.HtmlEncode(xmldoc.InnerXml); 
```

```csharp
// Открытие формы редактирования на клиенте
var data = { 'DataObject': serializedObj };
var editformUrl = 'MyObjectE.aspx';
openUrlWithPost(editformUrl, data);

function openUrlWithPost (url, params, target) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", url);
	form.setAttribute("target", target || "_blank");

	for (var key in params) {
		if (params.hasOwnProperty(key)) {
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = key;
			input.value = params[key];
			form.appendChild(input);
		}
	}

	form.submit();
};
```
## Для Win

[Аналогичный функционал для Win-приложений](fw_editform.html)
