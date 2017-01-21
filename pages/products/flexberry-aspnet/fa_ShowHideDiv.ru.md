---
title: ShowHideDiv
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_show-hide-div.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''ShowHideDiv'''

* '''Платформа''': Web.
* '''Предназначение:''' контрол для показа/скрытия контента.
* '''JavaScript API:''' 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_show_hide_div.html)'''.
* '''[ShowHideDiv на тестовом стенде](http://ru:6158/forms/Controls/ShowHideDiv/)'''.

</td>
</tr></tbody></table></a>
</div>

`ICSSoft.STORMNET.Web.AjaxControls.ShowHideDiv` - контрол для показа/скрытия контента. Свойства:
{| border="1"
! Свойство !! Тип !! Описание
|-
| `Content` || `System.Web.UI.ControlCollection` || Контент, который нужно скрывать/показывать
|-
| `CookieName` || `string` || Возвращает имя для сохранения состояния контрола в cookies (формируется из имени формы и ID контрола)
|-
| `Title` || `string` || Заголовок
|}

Методы:
{| border="1"
! Метод !! Описание
|-
| `HideDiv()` || Скрыть контент
|-
| `ShowDiv()` || Показать контент
|}

# Пример использования:
```xml
...
<%@ Register TagPrefix="ac" Namespace="ICSSoft.STORMNET.Web.AjaxControls" Assembly="ICSSoft.STORMNET.Web.AjaxControls" %>
...
        <ac:ShowHideDiv ID="shd" runat="server">
            <img src="http://flexberry.ru/App_Themes/Blue/images/flex/Logo_h113px.png" />         
        </ac:ShowHideDiv>
...
```
# Вид:
## В закрытом состоянии:
![](/images/pages/img/CaseberryWeb/AjaxControls/ShowHideDivCollapsed.PNG)
## В открытом состоянии:
![](/images/pages/img/CaseberryWeb/AjaxControls/ShowHideDivExpanded.PNG)

# Клиентские методы
Скрытие и раскрытие: setExpand
```
$('#ИД').showHide('setExpand', false)
```
 
 