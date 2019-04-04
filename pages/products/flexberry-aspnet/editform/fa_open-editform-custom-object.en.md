---
title: Opening the edit form with the transfer object with some fields already filled
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP NET Windows UI (forms)
toc: true
permalink: en/fa_open-editform-custom-object.html
lang: en
autotranslated: true
hash: 83e137b46063fa69ce8168260a2995ced05f942e015d4c017fa21f23f82dd4a7
---

The edit form allows you to not only create a new object from scratch, but to create an object in which some fields already filled in.

To open a web edit form with the transfer object with some fields already filled, you must make a POST request to the address edit form with parameter `DataObject`. The value of the parameter `DataObject` object data serialized in XML and encoded in HTML.

No restrictions on the transmit object data are imposed. The status of the item is irrelevant, because after the transfer and deserialization of the object it is installed in `ObjectStatus.Created`.

The serialization of the object is performed using `ICSSoft.STORMNET.Tools.ToolXML` [more d cnfnmt ICSSoft.STORMNET.Tools.XmlTools](fo_ics-soft-stormnet-tools.html).

When serialized, the XML must be encoded in HTML for secure data transfer. If `ASP.NET Request Validation` will see the corner brackets, that can throw an exception `A potentially dangerous Request.Form value was detected from the client`; read more about it on the website www.asp.net](http://www.asp.net/whitepapers/aspnet4/breaking-changes#0.1__Toc256770147) and [social.msdn.microsoft.com](http://social.msdn.microsoft.com/forums/en-US/netfxbcl/thread/a056886b-a1ad-40f8-9f4a-f7e8db39950b/). You can encode it with [Server.HTMLEncode](http://msdn.microsoft.com/en-us/library/ms525347(v=vs.90).aspx) or [HttpUtility.HtmlEncode](http://msdn.microsoft.com/ru-ru/library/system.web.httputility.htmlencode.aspx).

### Example

```csharp
// Serialize data object to a POST request on the server 
var xmldoc = ICSSoft.STORMNET.Tools.ToolXML.DataObject2XMLDocument(ref dobj);
string serializedObj = System.Web.HttpUtility.HtmlEncode(xmldoc.InnerXml); 
```

```csharp
// Open the edit form on the client 
var data = { 'DataObject': serializedObj };
var editformUrl = 'MyObjectE.aspx';
openUrlWithPost(editformUrl, data);

function openUrlWithPost (url, params, target) {
	var form = document.createElement("form");
	form.setAttribute("method", post);
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
## For Win

[Similar functionality for Win-applications](fw_editform.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}