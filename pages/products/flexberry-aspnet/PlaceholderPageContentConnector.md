---
title: Подключение скриптов и стилей на основе PlaceholderPageContentConnector
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/placeholder-page-content-connector.html
folder: product--folder
lang: ru
---

Класс PlaceholderPageContentConnector используется для подключения технологических скриптов и стилей, которые необходимы для работы контролов и среды выполнения при помощи контролов-плейсхолдеров. Такой способ позволяет настраивать приложение более гибко, добавляя прикладной код до/после технологического и пользовательского.

Является альтернативой методу подключения ресурсов через `[FilterPageContentConnector](filter-page-content-connector.html)`.

# Принцип работы
Подключение ресурсов происходит при помощи обработки события начала реднеринга страницы (`PreRenderComplete`):
# в коллекции контролов страницы (включая Master-страницу) ищутся контролы с следующими идентификаторами:
#* `FlexberryScripts` - плейсхолдер для подключения скриптов;
#* `FlexberryStyles` - плейсхолдер для подключения стилей;
#* `FlexberryRawHtml` - плейсхолдер для подключения спец. технологических элементов;
# в коллекцию контролов плейсхолдеров добавляются контролы, подключающие ресурсы.

В случае, если контролы не найдены, то будет сгенерировано исключение.

(((<msg type=note>В связи с тем, что ресурсы подключаются в `PreRenderComplete`, то элементы, подключенные после уже не будут добавлены в разметку.</msg>)))

# Настройка
1. Для подключения ресурсов на основе плейсхолдеров нужно настроить `unity` (`web.config`):
```
XML
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http"
          mapTo="NewPlatform.Flexberry.Web.Http.PlaceholderPageContentConnector, NewPlatform.Flexberry.Web.Http" />
```
2. В разметку страницы / Master-страницы нужно добавить контролы-плейсхолдеры:
```
XML
<asp:Placeholder ID="FlexberryStyles" runat="server" />
<asp:Placeholder ID="FlexberryScripts" runat="server" />
<asp:Placeholder ID="FlexberryRawHtml" runat="server" />
```

(((<msg type=note>Плейсхолдер `FlexberryStyles` обычно добавляется в &lt;head&gt; страницы.</msg>)))

(((<msg type=warning>Положение плейсхолдера `FlexberryScripts` влияет на корректность работы пользовательских скриптов, добавляемых на страницу в `ContentPlaceHolder0`. `FlexberryScripts` должен находиться выше в разметке.</msg>)))

(((<msg type=note>В случае, когда вручную не добавлен FlexberryScripts, данные всё равно могут быть подключены в конец тега &lt;body&gt; (для этого он должен иметь атрибут `runat=server`)!</msg>)))




