---
title: PageContentManager
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/page-content-manager.html
folder: product--folder
lang: ru
---

# PageContentManager
Класс `PageContentManager` предназначен для подключения скритов (js) и стилей (css) в C#-коде.

Является заменой устаревшего [ContextHelper](context-helper.html). Во всех новых проектах следует использовать именно его.

# Принцип работы
Основное предназначение `PageContentManager` - формирование списка ресурсов, которые нужно подключить к текущей странице. Подключение осуществляется через `IPageContentConnector` (который должен быть сконфигурирован через unity). 

Существует два способа подключения ресурсов:
# [на основе фильтрации HTML](filter-page-content-connector.html) (по умолчанию, из соображений обратной совместимости);
# [на основе контролов-плейсхолдеров](placeholder-page-content-connector.html) (рекомендуется);

(((<msg type=note>Ресурсы будут подключены в том же самом порядке, в котором они были добавлены (FIFO).</msg>)))

(((<msg type=note>Скрипты и стили с одинаковыми путями подключаются только один раз.</msg>)))

## Настройка момента срабатывания скрипта
По аналогии с `ContextHelper`'ом и его методами `ДобавитьСкрипт` и `ДобавитьСкриптВDocumentReady`, у `PageContentManager`'а есть возможность настроить момент срабатывания скрипта. Для этого в вызове метода добавления скрипта есть флаг `onPageLoad`, по умолчанию равный `false`.

Таким образом, аналогом метода `ДобавитьСкриптВDocumentReady` является вызов `AttachJavaScriptCode(script, true);`

# Пример использования
```
PageContentManager.AttachJavaScriptCode("alert('Hello');", true); // Отобразит окно с сообщением при загрузке страницы.
```

# Собственный способ подключения скриптов и стилей
Чтобы использовать собственный способ подключения ресурсов, необходимо создать новый класс и унаследовать интерфейса `IPageContentConnector`.

