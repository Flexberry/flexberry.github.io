---
title: Работа с детейлами в расширенном редакторе ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_details-limit-editor.html
lang: ru
---

## Работа с детейлами в расширенном редакторе ограничений в web

На что стоит обратить внимание (особенности связаны с [преобразованием функции ограничения в linq-выражение](fo_lcs-to-linq.html)):

* При задании ограничения всеобщности на детейлы (в lcs это [ExistExact](fo_exist-details.html)) в используемое представление детейла должна быть включена ссылка на агрегатора.
* При задании ограничения на детейлы через расширенный редактор ограничений в используемое представление агрегатора может потребоваться включить ссылку на детейлы.

##  Работа с псевдодетейлами в расширенном редакторе ограничений в web

Для работы с псевдодетейлами в [расширенном редакторе ограничений](fa_advanced-limit-editor.html) необходимо сделать следующее:
* При [задании порядка отображения свойств в расширенном редакторе ограничений](fa_prop-order-limit-editor.html) добавить интересующие псевдодетейлы (постановка задачи задания ограничений на псевдодетейлы описана [здесь](fo_linq-provider.html)) 
* В редакторе ограничений можно открывать сохранённые в виде lcs ограничения, либо конструировать новые.

![](/images/pages/products/flexberry-aspnet/ogranicheniye/le-pseudo-detail.png)

## Динамические представления для детейлов

Для работы детейлов в [расширенном редакторе ограничений в web](fa_advanced-limit-editor.html) при применении ограничения на [WOLV](fa_web-object-list-view.html), может потребоваться:
* [Задать детейлы в представление](fa_prop-order-limit-editor.html), если их ещё там нет.
* Задать [свойство AutoAddUsedInLimitationProperties](fa_prop-order-limit-editor.html), чтобы представление формировалось динамически.
* Определить механизм формирования динамического представления для детейлов через интерфейс ICSSoft.STORMNET.Windows.Forms.IViewGenerator (расположен в сборке ExternalLangDef). Например, в версии после 14.01.2015 можно в конфиг добавить следующую строчку:

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
				...	
	<container>
				  ...
	<register type="ICSSoft.STORMNET.Windows.Forms.IViewGenerator, ExternalLangDef" mapTo="NewPlatform.Flexberry.Web.Page.LimitEditorViewGenerator, NewPlatform.Flexberry.Web.LimitEditor" />
	</container>
</unity>
```
