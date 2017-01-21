---
title: Подключение скриптов и стилей на основе FilterPageContentConnector
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_filter-page-content-connector.html
folder: products/flexberry-aspnet/
lang: ru
---

Класс FilterPageContentConnector используется для подключения технологических скриптов и стилей, которые необходимы для работы контролов и среды выполнения при помощи пост-обработки разметки страницы. Скрипты подключаются в конце тега &lt;body&gt;, стили - в конец тега &lt;head&gt;.

Является альтернативой методу подключения ресурсов через `[PlaceholderPageContentConnector](placeholder-page-content-connector.html)`.

# Принцип работы
Перед отправкой сформированной HTML-разметки пользователю происходит пост-обработка страницы.
# определяется позиция для вставки данныз (конец тэга &lt;head&gt; для стилей и конце тэга &lt;body&gt; для скриптов и других спец. компонентов;
# в найденные позиции вставляется дополнительная HTML-разметка для подключения ресурсов;
# исправленные данные отправляются пользователю.

(((<msg type=warning>
При таком способе подключения данных присутствуют потенциальные ошибки, связанные с алгоритмом поиска позиции для добавления разметки. Например, когда на странице по какой-то причине существуют два закрывающихся тэга &lt;head&gt; (например, внутри комментария), то позиция может быть определена неправильно.

Эти проблемы связаны с тем, что полный анализ HTML не проводится (в связи с сложностью и ресурсоёмкостью), ищется первая возможная позиция.

В большинстве случаев (особенно для новых проектов) стоит использовать `[PlaceholderPageContentConnector](placeholder-page-content-connector.html)`.
</msg>)))

# Настройка
Для подключения ресурсов на основе фильтров нужно настроить unity (web.config):
```
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http"
          mapTo="NewPlatform.Flexberry.Web.Http.FilterPageContentConnector, NewPlatform.Flexberry.Web.Http" />
```

