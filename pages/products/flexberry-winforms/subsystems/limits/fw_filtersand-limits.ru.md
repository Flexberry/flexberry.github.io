---
title: Фильтры и ограничения
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Ограничения
summary: Объясняются понятия, связанные с понятиями фильтров и ограничений
toc: true
permalink: ru/fw_filtersand-limits.html
lang: ru
---

## Фильтры

__Фильтры__ - это инструмент наложения пользовательских ограничений на список объектов.

Все списки имеют функциональность по наложению на них фильтров. Для загрузки списка используется [представление](fd_view-types.html) L, а представления T и D используется для наложения на него ограничений. Поэтому представления L и T (D для детейлов объекта) должны содержать одинаковый набор атрибутов.

## Редактор ограничений

Редактор ограничений служит для создания фильтров.

Подробнее о редакторе можно посмотреть в [этой статье](fw_limit-editor-simple-view.html)

## Настройки фильтров (FilterSettings)

Для задания [FilterSettings](fw_filter-settings.html) необходимо воспользоваться Flexberry Administrative Console (AdmConsole).

Если используется генератор настроек фильтров, то необходимо обратить внимание на следующие моменты:

* Для работы его можно определить как [ярлык](ffw_desktop-operations.html) на [рабочий стол](fw_app-desktop.html):

```csharp
arr.Add(new IIS.WinUI.Runners.EditFormRunner(typeof(IIS.Core.App_FilterSettingsGeneratorE), "Администрирование", "Генератор настроек фильтров", "", new IIS.Core.App_FilterSettingsGenerator(), typeof(IIS.Core.App_FilterSettingsGenerator), false));
```

* В конфиг-файле приложения должны быть строчки вида:

```xml
<add key="DefaultLibFolder" value="lib"/>
<add key="FilterSettingStrategy" value="reflection"/>
```

Сборки объектов и форм при этом нужно перенести в папку, указанную в `DefaultLibFolder`. 

* У объектов обязательно должно быть [T-представление](fd_t-view.html). 

* После того, как настройки фильтров будут сгенерированы, их необходимо подключить как указано [здесь](fw_filter-settings.html).


[FilterSettings](fw_filter-settings.html) определяют настройки для [детейлов](fd_key-concepts.html) и лукапов (например, без таких настроек при задании ограничений невозможно использовать [детейлы](fd_key-concepts.html)). 


После задания FilterSettings в БД приложения, на которую была настроена AdmConsole, заполнятся следующие таблицы:
* STORMFILTERSETTING (содержит названия настроек фильтров и названия объектов, к которым будут применены данные настройки);
* STORMFILTERLOOKUP (содержит настройки для лукапов); 
* STORMFILTERDETAIL (содержит настройки для [детейлов](fd_key-concepts.html));

{% include note.html content="AdmConsole адекватно [создаёт настройки автоматически](fw_filter-settings.html), если приложение собрано для .Net Framework версии 3.0 или 3.5. Альтернативным решением проблемы версий фреймворков может быть [указание более высокой версии фреймворка](gbt_set-runtime-dotnet-version.html) для AdmConsole." %}

{% include note.html content="Для того, чтобы ограничение `сам объект` заработало - нужно создать STORMFILTERLOOKUP для этого объекта." %}

## Форма задания ограничений AdvLimit
[AdvLimit](fw_limitation-editform.html) позволяет задавать ограничения; для расширения функциональности необходимо настроить FilterSettings (см. выше, [здесь](fw_filter-settings.html) и [здесь](fw_filter-example.html)).

## LimitFunction
Через [свойство LimitFunction структуры LoadingCustomizationStruct](fo_loading-customization-struct.html) можно программно задавать ограничения (пример см. [здесь](fw_filtersettings-for-use-in-lists.html)).