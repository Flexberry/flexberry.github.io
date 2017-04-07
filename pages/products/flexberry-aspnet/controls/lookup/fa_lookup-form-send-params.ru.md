---
title: Передача параметров на LookUp-форму в Web-приложениях
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/fa_lookup-form-send-params.html
lang: ru
---

Данная статья относится к технологической [LookUp-форме](fa_tech-forms-web.html) и описывает способ передачи собственных параметров в LookUp-форму при помощи [JavaSript API](fa_lookup-overview.html).

## LookUp-форма

При реализации собственной LookUp-формы может появиться необходимость передать несколько собственных параметров и разобрать их при загрузке формы. В данной статье будет разобран пример передачи параметров при использовании технологической LookUp-формы.

## Постановка задачи

Пусть дана форма редактирования с лукапом `ctrlLookUp1`, лукап "смотрит" на форму `LookUpForm.cs`, генерируемую вместе с приложением.  
Необходимо добавить на форму редактирования кнопку, при нажатии на которую на LookUp-форму добавляется переданное сообщение.

## Алгоритм решения

1. Создать кнопку и прописать для неё метод `click`, в котором будет происходить передача дополнительных параметров.
2. "Отловить" на LookUp-форме приход дополнительных параметров и добавить требуемое сообщение (при необходимости).

### Создание кнопки и передача параметров

* Создать кнопку:

```xml
 <button id="addMessageButton">Добавить сообщение на LookUp-форму</button> 
```

* Создать для неё обработчик нажатия:

```javascript
$('#addMessageButton').click(function () {
    $('#<%=ctrlLookUp1.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', { formParams: 'ShowMessage=true&TheMessage=Сообщение' });
    return false;
});
```

{% include note.html content=">Как и в других случаях работы с JS API, не забываем `return false;`, чтобы не происходил PostBack." %}


{% include warning.html content="В коде прикладных приложений не стоит явно использовать константы `LookUp` и др., так как их значение может измениться в будущем. Для получения специальных технологических параметров из запроса следует использовать класс [WebParamController](fa_get-query-parameters-forms.html) . Рекомендуем проверить текущие проекты." %}

## Обработка параметров на LookUp-форме

На технологической LookUp-форме по умолчанию добавлен метод 

```csharp
 protected override void ApplyWolvSettings() 
```

Добавить в него следующий код:

```csharp
bool showMessage = Request["ShowMessage"] != null && Request["ShowMessage"] == "true";

if (showMessage)
{
    string message = Request["TheMessage"];
    // Добавить сообщение.
}
```
