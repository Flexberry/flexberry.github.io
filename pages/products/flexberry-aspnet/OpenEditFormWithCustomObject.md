---
title: Открытие формы редактирования с передачей объекта с частично заполненными полями
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Windows UI (формы)
toc: true
permalink: ru/open-edit-form-with-custom-object.html
folder: product--folder
lang: ru
---

# Форма редактирования

Форма редактирования позволяет не только создавать новый объект с нуля, но и создать объект, в котором некоторые поля уже заполнены. 

Для этого необходимо произвести POST-запрос по адресу формы редактирования с параметром `"DataObject"`. Значение параметра `"DataObject"` - объект данных, сериализованный в XML и закодированный в HTML.

Никаких ограничений на передаваемый объект данных не налагается. Статус передаваемого объекта не играет роли, т.к. после передачи и десериализации объекта он устанавливается в `ObjectStatus.Created`.

Сериализация объекта производится с помощью `ICSSoft.STORMNET.Tools.ToolXML` ([Агрегирующие-функции.ashx?HL=сериализация|подробнее]).

После сериализации XML необходимо закодировать в HTML для безопасной передачи данных (Если `ASP.NET Request Validation` увидит угловые скобочки, то может кинуть исключение `A potentially dangerous Request.Form value was detected from the client`; подробнее об этом [раз](http://www.asp.net/whitepapers/aspnet4/breaking-changes#0.1__Toc256770147) и [два](http://social.msdn.microsoft.com/forums/en-US/netfxbcl/thread/a056886b-a1ad-40f8-9f4a-f7e8db39950b/)). Сделать это можно с помощью [Server.HTMLEncode](http://msdn.microsoft.com/en-us/library/ms525347(v=vs.90).aspx) или [HttpUtility.HtmlEncode](http://msdn.microsoft.com/ru-ru/library/system.web.httputility.htmlencode.aspx).

### Пример

```
// Сериализация объекта данных для POST-запроса на сервере
var xmldoc = ICSSoft.STORMNET.Tools.ToolXML.DataObject2XMLDocument(ref dobj);
string serializedObj = System.Web.HttpUtility.HtmlEncode(xmldoc.InnerXml); 
```
```
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

# Примеры использования
### Windows

[Пример](prinuditelnyi-vyzov-formy-redaktirovaniya.html)

### Web

[Пример](open-edit-form-web-example.html)