---
title: Работа с детейлами в расширенном редакторе ограничений в web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_details-at-adv-limit-editor.html
folder: products/flexberry-aspnet/
lang: ru
---

# Работа с детейлами в [расширенном редакторе ограничений в web](advanced-limit-editor.html)
На что стоит обратить внимание:
* При задании ограничения всеобщности на детейлы (в lcs это [ExistExact](exist--exist-exact--exist-all--exist-all-exact.html)) в используемое представление детейла должна быть включена ссылка на агрегатора.
* При задании ограничения на детейлы через расширенный редактор ограничений в используемое представление агрегатора может потребоваться включить ссылку на детейлы.

# Работа с псевдодетейлами в [расширенном редакторе ограничений в web](advanced-limit-editor.html)
Для работы с псевдодетейлами в [расширенном редакторе ограничений](advanced-limit-editor.html) необходимо сделать следующее:
* При [задании порядка отображения свойств в расширенном редакторе ограничений](set-prop-order-at-web-adv-limit-editor.html) добавить интересующие псевдодетейлы (постановка задачи задания ограничений на псевдодетейлы описана [здесь](l-i-n-q-provider.html)) 
* В редакторе ограничений можно открывать сохранённые в виде lcs ограничения, либо конструировать новые.

![](/images/pages/img/page/DetailsAtAdvLimitEditor/LEPseudoDetail.png)

# Динамические представления для детейлов
Для работы детейлов в [расширенном редакторе ограничений в web](advanced-limit-editor.html) при применении ограничения на [WOLV](web-object-list-view.html), может потребоваться:
* [Задать детейлы в представление](set-prop-order-at-web-adv-limit-editor.html), если их ещё там нет.
* Задать [свойство AutoAddUsedInLimitationProperties](set-prop-order-at-web-adv-limit-editor.html), чтобы представление формировалось динамически.
* Определить механизм формирования динамического представления для детейлов через интерфейс `ICSSoft.STORMNET.Windows.Forms.IViewGenerator` (расположен в сборке `ExternalLangDef`). Например, в версии после 14.01.2015 можно в конфиг добавить следующую строчку:

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
				...	
	<container>
				  ...
	<register type="ICSSoft.STORMNET.Windows.Forms.IViewGenerator, ExternalLangDef" mapTo="NewPlatform.Flexberry.Web.Page.LimitEditorViewGenerator, NewPlatform.Flexberry.Web.LimitEditor" />
	</container>
</unity>
```

