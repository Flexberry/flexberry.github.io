---
title: LinkModalPopupWindow
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/link-modal-popup-window.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''LinkModalPopupWindow'''

* '''Платформа''': Web.
* '''Предназначение:''' 
* '''JavaScript API:''' 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_link_modal_popup_window.html)'''.
* '''[LinkModalPopupWindow на тестовом стенде](http://ru:6158/forms/Controls/LinkModalPopupWindow/)'''.

</td>
</tr></tbody></table></a>
</div>

# Описание
С помощью данного контрола Вы можете поднять модальное окно и открыть в нём страницу.

# Подключение
Контрол можно подключить двумя способами: расположить на странице и добавить динамически.

# Использование

Располагаем контрол на странице и настраиваем его:

```
<ac:LinkModalPopupWindow ID="myLink" EnableViewState="false" runat="server" URL="homework.aspx" Enabled="true" Text="Ссылка" WindowTitle="Домашнее задание" />
```

Можно этот контрол добавить динамически или даже отрендерить вручную.
Или получить, используя следующую функцию:
```

LinkModalPopupWindow.GetHtml(this.ClientID + "_lmpw" + j.ToString(), "Подробнее", "TemaPlanLessonE.aspx?LookUp=true&amp;pk={0}", "Тематический план урока", 640, 480, true)
```

## Настройки контрола
{|
! Параметры !! Описание
|-
| ClientId || ID-контрола.
|-
| Text || Текст ссылки.
|-
| Url || Адрес, который нужно открывать.
|-
| Enabled || Доступна ли ссылка.
|-
| WindowTitle || Заголовок окна.
|-
| ATitle || Tooltip для ссылки.
|}
## Настройки оформления
{|
! Параметры !! Описание
|-
| WindowWidth || Ширина окна.
|-
| WindowHeight || Высота окна.
|-
| CssStyle || Стиль ссылки.
|-
| CssClass || CSS-Класс ссылки.
|}

# JS API

# Возможные ошибки
 
 