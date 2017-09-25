---
title: Фильтры и ограничения
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Ограничения
toc: true
permalink: ru/fw_filtersand-limits.html
folder: products/flexberry-winforms/
lang: ru
---

# Фильтры

'''Фильтры''' - это инструмент наложения пользовательских ограничений на список объектов.

Все списки имеют функциональность по наложению на них фильтров. Для загрузки списка используется [представление](fd_view-types.html) L, а представления T и D используется для наложения на него ограничений. Поэтому представления L и T (D для детейлов объекта) должны содержать одинаковый набор атрибутов.

# Редактор ограничений

Редактор ограничений служит для создания фильтров.

Подробнее о редакторе можно посмотреть в [этой статье](fw_limit-editor-simple-view.html)

# Настройки фильтров (FilterSettings)
Для задания [FilterSettings](filter-settings.html) необходимо воспользоваться [Flexberry Administrative Console (AdmConsole)]() или [Генератором настроек фильтров](прикладные-системы_Генератор-настроек-фильтров.html) (не все необходимые для генератора dll входят в стандартную поставку Flexberry).


(((
<msg type=note>Если используется [генератор настроек фильтров](прикладные-системы_Генератор-настроек-фильтров.html), то необходимо обратить внимание на следующие моменты: 

1. Для работы его можно определить как [ярлык](Ярлыки-рабочего-стола.html) на [рабочий стол](fw_app-desktop.html):
```cs
arr.Add(new IIS.WinUI.Runners.EditFormRunner(typeof(IIS.Core.App_FilterSettingsGeneratorE), "Администрирование", "Генератор настроек фильтров", "", new IIS.Core.App_FilterSettingsGenerator(), typeof(IIS.Core.App_FilterSettingsGenerator), false));
```
2. В конфиг-файле приложения должны быть строчки вида:
```xml
<add key="DefaultLibFolder" value="lib"/>
<add key="FilterSettingStrategy" value="reflection"/>
```
Сборки объектов и форм при этом нужно перенести в папку, указанную в `DefaultLibFolder`. 

3. У объектов обязательно должно быть [T-представление](fd_t-view.html). 

4. После того, как настройки фильтров будут сгенерированы, их необходимо подключить как указано [здесь](filter-settings.html).
</msg>
)))


[FilterSettings](filter-settings.html) определяют настройки для [детейлов](fd_key-concepts.html) и лукапов (например, без таких настроек при задании ограничений невозможно использовать [детейлы](fd_key-concepts.html)). 


После задания FilterSettings в БД приложения, на которую была настроена [AdmConsole](), заполнятся следующие таблицы:
* STORMFILTERSETTING (содержит названия настроек фильтров и названия объектов, к которым будут применены данные настройки);
* STORMFILTERLOOKUP (содержит настройки для лукапов); 
* STORMFILTERDETAIL (содержит настройки для [детейлов](fd_key-concepts.html));

(((
<msg type=note>[AdmConsole]() адекватно [создаёт настройки автоматически](filter-settings.html), если приложение собрано для .Net Framework версии 3.0 или 3.5. Альтернативным решением проблемы версий фреймворков может быть [указание более высокой версии фреймворка](set-runtime-dotnet-version.html) для [AdmConsole]().</msg>
)))

(((
<msg type=note>Для того, чтобы ограничение "сам объект" заработало - нужно создать STORMFILTERLOOKUP для этого объекта.</msg>
)))

# Форма задания ограничений AdvLimit
[AdvLimit](limitation-edit-form.html) позволяет задавать ограничения; для расширения функциональности необходимо настроить FilterSettings (см. выше, [здесь](filter-settings.html) и [здесь](fw_filter-example.html)).

# LimitFunction
Через [свойство LimitFunction структуры LoadingCustomizationStruct](fo_loading-customization-struct.html) можно программно задавать ограничения (пример см. [здесь](filter-settings-for-use-in-lists.html) и [Ограничение-на-детеилы-из-кода|здесь]).
----* [Генератор настроек фильтров](прикладные-системы_Генератор-настроек-фильтров.html)