---
title: IcsTreeView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ics-tree-view.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''IcsTreeView'''

* '''Платформа''': Web.
* '''Предназначение:''' 
* '''JavaScript API:''' 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_ics_tree_view.html)'''.
* '''[IcsTreeView на тестовом стенде](http://ru:6158/forms/Controls/IcsTreeView/)'''.

</td>
</tr></tbody></table></a>
</div>
# IcsTreeView
Веб-контрол `ICSSoft.STORMNET.Web.AjaxControls.IcsTreeView` - наследник `System.Web.UI.WebControls.TreeView`, отличается от него тем, что умеет запоминать состояние в cookies и раскрывать/скрывать ветки по нажатию на саму надпись узла, а не на стрелку слева.
## Свойства
Кроме свойств, унаследованных от `System.Web.UI.WebControls.TreeView`, присутствуют:
{| border="1"
! Сигнатура !! Описание
|-
| `public int? CookieExpiration` || Время жизни состояния контрола в cookies в днях. Пример:```cs
ctrlCtrl.CookieExpiration = 10;```
|-
| `public bool RememberState` || Нужно ли контролу запоминать состояние. По умолчанию `true`
|-
| `public bool ToggleOnNodeClick` || Требуется ли скрывать/раскрывать дочерние узлы по клику на надпись. Данное действие производится, если узлу не назначена другая ссылка, в противном случае происходит переход по ней. При `ToggleOnNodeClick == false` и отсутствии ссылки узел представляет собой некликабельную надпись. По умолчанию `true`
|-
| `public static InitControlSettingsDelegate&#60IcsTreeView&#62 InitSettings` || Статический делегат для настройки контрола. Подробнее ниже
|-
| `public bool CollapsedState` || Параметр, определяющий, следует ли при первом показе отображать дерево в свёрнутом состоянии (если для дерева сохранены куки, то соответствующая вершина всё равно будет отображаться в указанном в куках виде).
|}
## Настройка
*Чтобы глобально кастомизировать контрол данного типа, в `InitSettings` присвойте метод, принимающий в качестве параметра экземпляр `IcsTreeView` и изменяющий его свойства нужным образом. [Глобальная настройка веб-контролов](fa_init-control-settings-delegate.html)

*[Настроить карту сайта по ролям](sitemap-according-roles.html)

----
