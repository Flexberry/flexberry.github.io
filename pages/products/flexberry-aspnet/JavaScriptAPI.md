---
title: JavaScript API
sidebar: product--sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/java-script-a-p-i.html
folder: product--folder
lang: ru
---

# JS API
JavaScript API основано на библиотеке jQuery и представляет собой набор плагинов для неё.
 	
Всю систему клиентского JS API можно разделить на две основные группы.
 	
# '''ядро API''' (общий набор методов и данных для работы с Flexberry web).
#* `jquery.ics.js`


# '''API контролов''' (набор операций для работы с основными технологическими web-контролами).
#* `[jquery.icsWolv.js](web-object-list-view.html)`
#* `[jquery.ajaxgroupedit.js](ajax-group-edit.html)`
#* `[jquery.icsDatePicker.js](date-picker.html)`
#* `[LookUp Overview|jquery.icsMasterEditorAjaxLookup.js]`.


# '''API форм''' (набор операций для работы с основными технологическими web-формами).
#* `[jQuery.icsEditForm](web-edit-form.html)`


# Конвенция именования
Имена всех файлов JS API должны соответствовать формату `jquery.icsName.js`, где `Name` - имя плагина JS API.

Если у плагина имеются стилевые CSS файлы, то они должны называться аналогично: `jquery.icsName.css`.

# Ядро API

## Диалоговые окна
Все методы для работы с диалоговыми окнами находятся в объекте
```javascript
$.ics.dialog
```
#### confirm
Диалоговое окно для подтверждения действия пользователя:
```javascript
$.ics.dialog.confirm(options);
```
'''Внимание!''' Метод является асинхронным. Для проверки результата действия пользователя нужно устаналивать `callback`.

Для поддержки всех функцинальных возможностей должен быть подключен файл `jquery.alerts.js`. Если плагин не подключен то будут использованы встроенные возможности браузера с урезанной функциональностью.

Опции:
{|
! Наименование !! Описание !! Значение по умолчанию
|-
| `message` || Текст запроса. || пустая строка
|-
| `title` || Заголовок окна с сообщением. || пустая строка
|-
| `callback` || Функция для обработки результата выбора пользователя. Первый аргумент функции - результат выбора
пользователя: `true` для кнопки `OK` и `false` для кнопки `Cancel`. || `undefined`
|-
| `okButtonText` || Текст для кнопки `OK`. || `$.ics.configuration.dialog.okButtonText`
|-
| `cancelButtonText` || Текст для кнопки `Cancel`. || `$.ics.configuration.dialog.cancelButtonText`
|-
|}

Пример использования:
```javascript
$.ics.dialog.confirm({
    message: 'Удалить файл?',
    title: 'Подтверждение удаления файла',
    callback: function (res) {
        if (res) {
            // При нажатии OK.
            deleteFile();
        } else {
            // При нажати Cancel.
            cancelFileDeleting();
        }
    }
});
```

Результат (с подключенным плагином `jquery.alerts`):
![](/images/pages/img/page/JavaScriptAPI/jsapi_dialog_confirm.PNG)

Результат (без плагина `jquery.alerts`):
![](/images/pages/img/page/JavaScriptAPI/jsapi_dialog_confirm_without_plugin.png)

#### alert
Диалоговое окно для отображения информации для пользователя:
```javascript
$.ics.dialog.alert(options);
```
'''Внимание!''' Метод является асинхронным. Для обработки события закрытия окна нужно устаналивать `callback`.

Для поддержки всех функцинальных возможностей должен быть подключен файл `jquery.alerts.js`. Если плагин не подключен то будут использованы встроенные возможности браузера с урезанной функциональностью.

Опции:
{|
! Наименование !! Описание !! Значение по умолчанию
|-
| `message` || Текст сообщения. || `undefined` (пустая строка)
|-
| `title` || Заголовок окна с сообщением. || `undefined` (пустая строка)
|-
| `callback` || Функция обратного вызова после закрытия окна пользователем. Не принимает аргументов. || `undefined`
|-
|}

Если вместо опций будет передан текст, то будут использованы опции по умолчанию с указанным сообщением.

Пример использования:
```javascript
$.ics.dialog.alert({
    message: 'Файл успешно удалён!',
    title: 'Внимание',
    callback: function () {
        // После закрытия окна.
        // ...
    }
});
```

Результат (с подключенным плагином `jquery.alerts`):
![](/images/pages/img/page/JavaScriptAPI/jsapi_dialog_alert.png)

Результат (без плагина `jquery.alerts`):
![](/images/pages/img/page/JavaScriptAPI/jsapi_dialog_alert_without_plugin.png)

#### modal
Диалоговое модальное окно:
```javascript
$.ics.dialog.modal(options);
```
'''Внимание!''' Метод является асинхронным.  Для обработки события закрытия окна нужно устаналивать `callback`.

Для работы метода обязательно подключение плагина `jQuery Thickbox`.

Опции:
{|
! Наименование !! Описание !! Значение по умолчанию
|-
| `content` || Содержимое, отображаемое в окне. Может быть HTML. || `undefined` (пустая строка)
|-
| `href` || Ссылка на страницу, которую нужно отобразить. Этот параметр имеет больший приоритет по сравнению с `content`. || `undefined` (пустая строка)
|-
| `title` || Заголовок окна. || `undefined` (пустая строка)
|-
| `callback` || Функция обратного вызова перед закрытием окна пользователем. Передаваемый аргумент позволяет отменить закрытие окна. || `undefined`
|-
| `width` || Ширина окна в пикселях. || `$.ics.configuration.dialog.width`
|-
| `height` || Ширина окна в пикселях. || `$.ics.configuration.dialog.height`
|-
| `modal` || Флаг модального окна. В случае `true` отсутствует возможность закрыть окно. || `true`
|-
|}

Возвращаемый объект:
{|
! Наименование !! Описание
|-
| `close` || Метод для закрытия окна.
|-
| `closed` || Флаг закрытого окна, позволяющий определить, открыто ли ещё окно.
|-
| `context` || Контекст для отображаемого окна. Если отображаются данные по ссылке, то `context` ссылается на объект `window` открытого окна, иначе это объект `window` текущего окна.
|-
|}

Пример использования:
```javascript
var modal = $.ics.dialog.modal({
    width: 500,
    content: '<strong>Выполняется сохранение...</strong>',
    title: 'Ждите!',
    modal: false,
    callback: function() {
        // После закрытия окна.
        // ...
    }
});

setTimeout(function() {
    modal.close();
}, 4000);
```

Результат:
![](/images/pages/img/page/JavaScriptAPI/jsapi_dialog_modal.png)


## Специальные функции
#### generateUniqueId
Генерация уникального идентификатора DOM-элемента.
```javascript
$.ics.generateUniqueId(prefix);
```

Параметры:
{|
! Наименование !! Описание !! Значение по умолчанию
|-
| `prefix` || Префикс для генерируемого идентификатора. || `undefined` (пустая строка)
|-
|}

Пример использования:
```javascript
var uniqueId = $.ics.generateUniqueId('element_'); // element_032452
```
