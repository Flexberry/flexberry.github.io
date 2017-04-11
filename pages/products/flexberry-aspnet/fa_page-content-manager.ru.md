---
title: PageContentManager
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_page-content-manager.html
lang: ru
---

Класс `PageContentManager` предназначен для подключения скритов (js) и стилей (css) в C#-коде.

{% include note.html content="`PageContentManager` является заменой устаревшего [ContextHelper](fa_context-helper.html). Во всех новых проектах следует использовать именно его." %}

## Принцип работы

Основное предназначение `PageContentManager` - формирование списка ресурсов, которые нужно подключить к текущей странице. Подключение осуществляется через `IPageContentConnector` (который должен быть сконфигурирован через unity). 

Существует два способа подключения ресурсов:

* [на основе фильтрации HTML](fa_filter-page-content-connector.html) (по умолчанию, из соображений обратной совместимости);
* [на основе контролов-плейсхолдеров](fa_placeholder-page-content-connector.html) (рекомендуется);

{% include note.html content="Если не зарегистрировать тип в файле конфигурации unity, то будет работать старый метод добавления скриптов на страницу - с использованием  [FilterPageContentConnector](fa_filter-page-content-connector.html)." %}

{% include note.html content="Ресурсы будут подключены в том же самом порядке, в котором они были добавлены (FIFO)." %}

{% include note.html content="Скрипты и стили с одинаковыми путями подключаются только один раз." %}

### Настройка момента срабатывания скрипта

По аналогии с `ContextHelper`'ом и его методами `ДобавитьСкрипт` и `ДобавитьСкриптВDocumentReady`, у `PageContentManager`'а есть возможность настроить момент срабатывания скрипта. Для этого в вызове метода добавления скрипта есть флаг `onPageLoad`, по умолчанию равный `false`.

Таким образом, аналогом метода `ДобавитьСкриптВDocumentReady` является вызов `AttachJavaScriptCode(script, true);`

## Пример использования

```csharp
PageContentManager.AttachJavaScriptCode("alert('Hello');", true); // Отобразит окно с сообщением при загрузке страницы.
```

## Собственный способ подключения скриптов и стилей

Чтобы использовать собственный способ подключения ресурсов, необходимо создать новый класс и унаследовать интерфейса `IPageContentConnector`.
