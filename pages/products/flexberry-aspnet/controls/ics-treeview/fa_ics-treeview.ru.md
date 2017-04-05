---
title: IcsTreeView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ics-treeview.html
lang: ru
---

Веб-контрол `ICSSoft.STORMNET.Web.AjaxControls.IcsTreeView` - наследник `System.Web.UI.WebControls.TreeView`, отличается от него тем, что умеет запоминать состояние в cookies и раскрывать/скрывать ветки по нажатию на саму надпись узла, а не на стрелку слева.

## Свойства

Кроме свойств, унаследованных от `System.Web.UI.WebControls.TreeView`, присутствуют:

|Сигнатура | Описание|
|:-----------------------|:---------------------------------------------------|
| `public int? CookieExpiration` | Время жизни состояния контрола в cookies в днях. Пример: `ctrlCtrl.CookieExpiration = 10;`|
| `public bool RememberState` | Нужно ли контролу запоминать состояние. По умолчанию `true`|
| `public bool ToggleOnNodeClick` | Требуется ли скрывать/раскрывать дочерние узлы по клику на надпись. Данное действие производится, если узлу не назначена другая ссылка, в противном случае происходит переход по ней. При `ToggleOnNodeClick == false` и отсутствии ссылки узел представляет собой некликабельную надпись. По умолчанию `true`|
| `public static InitControlSettingsDelegate<IcsTreeView> InitSettings` | Статический делегат для настройки контрола. Подробнее ниже|
| `public bool CollapsedState` | Параметр, определяющий, следует ли при первом показе отображать дерево в свёрнутом состоянии (если для дерева сохранены куки, то соответствующая вершина всё равно будет отображаться в указанном в куках виде)|

## Настройка

* Чтобы глобально кастомизировать контрол данного типа, в `InitSettings` необходимо присвоить метод, принимающий в качестве параметра экземпляр `IcsTreeView` и изменяющий его свойства нужным образом. [Глобальная настройка веб-контролов](fa_init-control-settings-delegate.html)

* [Настроить карту сайта по ролям](fa_sitemap-according-roles.html)
