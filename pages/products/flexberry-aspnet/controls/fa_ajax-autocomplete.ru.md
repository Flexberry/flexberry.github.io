---
title: Автодополнение в веб-системах (AjaxAutocomplete)
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ajax-autocomplete.html
lang: ru
---

При помощи `AjaxAutocomplete` возможно, например, при редактировании имени объекта получить автодополнение для данного поля, в котором будут предлагаться имена существующих объектов, начинающихся на введенные символы. `AjaxAutocomplete` используется из библиотеки jquery ui, поэтому библиотека jquery ui должна быть подключена.

Также, имеется возможность связать [автодополнение с лукапом](fa_link-ajax-autocomplete-ajax-lookup.html). Либо включить автодополнение у [Ajax-лукапа](fa_master-editor-ajax-lookup.html).

# Подключение

Если необходимые файлы не подключены, то необходимо выполнить следущее:
1. Обновить версию `ICSSoft.STORMNET.Web.AjaxControls.dll`
2. Добавить стили `jquery-ui.css` и картинку с индикатором загрузки `indicator.gif`;
3. Добавить библиотеку `jquery ui`;
4. Обновить версию `ICSSoft.STORMNET.Web.Tools.dll`

# Использование

Следует убедиться, что настройки для [ServiceSecurityProvider](fa_service-security-provider.html) указаны верно.  
Для того чтобы указать контрол, к которому будет применятся авдотополнение, необходимо добавить метод 

```csharp
AjaxAutocomplete.AddControlAutocomplete(контрол, тип_объектов, свойство_объекта)
```

например, в `Page_Load()`;

Имеется возможность указать, использовать ли поиск по подстроке и ограничение.

```csharp
//AjaxAutocomplete.AddControlAutocomplete(контрол, тип_объектов, свойство_объекта, использовать_ли_поиск_по_подстроке, ограничение);
AjaxAutocomplete.AddControlAutocomplete(ctrlЦена, typeof(Кошка), "Цена", false, func2);
```

## Пример

```csharp
AjaxAutocomplete.AddControlAutocomplete(ctrlКличка, typeof(Кошка), "Кличка");
AjaxAutocomplete.AddControlAutocomplete(ctrlЦена, typeof(Кошка), "Цена");
```

## Сортировка и Distinct

Если для ввода указано представление, то все поля попадут под Distinct. Накладывать сортировку можно на любое свойство в представлении.

## Раскраска совпадающих символов в выпадающем списке

Весь javascript, связанный с автодополнением находится в файле `AjaxDataService.js`.

Для раскраски символов был переопределен `renderItem` у прототипа автокомплита.

```javascript
$(function () {
    $.ui.autocomplete.prototype._renderItem = function (ul, item) {
        var re = new RegExp(this.term, 'i');
        var t = item.label.replace(re, "<span style='font-weight:bold;color:Blue;'>" +
            this.term +
                "</span>");
        return $("<li></li>")
            .data("item.autocomplete", item)
            .append("<a>" + t + "</a>")
            .appendTo(ul);
    };
});
```
