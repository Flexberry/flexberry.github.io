---
title: BaseMasterEditorLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_base-master-editor-look-up.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''BaseMasterEditorLookUp'''

* '''Платформа''': Web.
* '''Предназначение''': абстрактный контрол, от которого наследуются MasterEditorLookUp, MasterEditorAjaxLookUp 
* '''JavaScript API:''' 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_base_master_editor_look_up.html)'''.
* '''BaseMasterEditorLookUp на тестовом стенде:''' нет.

</td>
</tr></tbody></table></a>
</div>

# Введение
Это абстрактный класс, от которого наследуются MasterEditorLookUp, MasterEditorAjaxLookUp.

# Единообразное изменение свойств лукапов
Реализовано два статических делегата:
# InitLookUpSettings - Делегат для инициализации настроек. Установленные настройки можно переопределять на aspx формах.
# ChangeLookUpSettings - Делегат для смены настроек, при помощи которого можно привести все лукапы к единообразному виду.

Пример,
Нужно подписаться в Global.asax:
```
            BaseMasterEditorLookUp.ChangeLookUpSettings = AllForms.ChangeLookUpSettings;
```
```

        /// <summary>
        /// Смена настроек лукапов
        /// </summary>
        /// <param name="lookup">Лукап, которому меняются настройки</param>
        public static void ChangeLookUpSettings(BaseMasterEditorLookUp lookup)
        {
            lookup.LookUpFormHeight = 640;
            lookup.LookUpFormWidth = 854;
            lookup.ShowInThickBox = true;
            lookup.LookUpFormCaption = "Выберете значение";
        }
```
 


 