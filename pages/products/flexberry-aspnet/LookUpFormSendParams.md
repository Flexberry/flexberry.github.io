---
title: Передача параметров на LookUp-форму в Web-приложениях
sidebar: product--sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/look-up-form-send-params.html
folder: product--folder
lang: ru
---

# Статья
Данная статья относится к технологической [LookUp-форме](tech-forms-web.html) и описывает способ передачи собственных параметров в LookUp-форму при помощи [LookUp%20Overview.ashx#_JS_API__15|JavaSript API].

# LookUp-форма
При реализации собственной LookUp-формы может появиться необходимость передать несколько собственных параметров и разобрать их при загрузке формы. В данной статье будет разобран пример передачи параметров при использовании технологической LookUp-формы.

# Постановка задачи
Пусть дана форма редактирования с лукапом `ctrlLookUp1`, лукап "смотрит" на форму `LookUpForm.cs`, генерируемую вместе с приложением.

Необходимо добавить на форму редактирования кнопку, при нажатии на которую на LookUp-форму добавляется переданное сообщение.


# Алгоритм решения
# Создать кнопку и прописать для неё метод `click`, в котором будет происходить передача дополнительных параметров.
# "Отловить" на LookUp-форме приход дополнительных параметров и добавить требуемое сообщение (при необходимости).

## Создание кнопки и передача параметров
* Создадим кнопку:

```
 <button id="addMessageButton">Добавить сообщение на LookUp-форму</button> 
```

* Создадим для неё обработчик нажатия:

```

        $('#addMessageButton').click(function () {
            $('#<%=ctrlLookUp1.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', { formParams: 'ShowMessage=true&TheMessage=Сообщение' });
            return false;
        });
```

(((<msg type=note>Как и в других случаях работы с JS API, не забываем `return false;`, чтобы не происходил PostBack.</msg>)))

(((<msg type=warning>В коде прикладных приложений не стоит явно использовать константы "LookUp" и др., так как их значение может измениться в будущем. Для получения специальных технологических параметров из запроса следует использовать класс [Параметры-GET-запроса-для-форм|WebParamController] . Рекомендуем проверить текущие проекты.</msg>)))


## Обработка параметров на LookUp-форме
На технологической LookUp-форме по умолчанию добавлен метод 
```
 protected override void ApplyWolvSettings() 
```

Добавим в него следующий код:
```

    bool showMessage = Request["ShowMessage"] != null && Request["ShowMessage"] == "true";

    if (showMessage)
    {
        string message = Request["TheMessage"];
        // Добавить сообщение.
    }
```

