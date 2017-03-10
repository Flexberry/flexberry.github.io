---
title: BaseMasterEditorLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_base-master-editor-look-up.html

---

* **Платформа**: [FlexberryASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент**: [Web-контролы и web-компоненты](fa_web-controls.html)
* **Предназначение**: абстрактный контрол, от которого наследуются [MasterEditorLookUp](fa_master-editor-look-up.html), [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html) 

## Введение
Это абстрактный класс, от которого наследуются MasterEditorLookUp, MasterEditorAjaxLookUp.

## Единообразное изменение свойств лукапов
Реализовано два статических делегата:
1. InitLookUpSettings - Делегат для инициализации настроек. Установленные настройки можно переопределять на aspx формах.
2. ChangeLookUpSettings - Делегат для смены настроек, при помощи которого можно привести все лукапы к единообразному виду.

Пример, нужно подписаться в Global.asax:

```cs
BaseMasterEditorLookUp.ChangeLookUpSettings = AllForms.ChangeLookUpSettings;
```

```cs
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

## Откуда ссылаются на эту страницу

* [Наложение ограничения на LookUp в Web](fa_look-up-limit-web.html)
* [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html)
* [MasterEditorLookUp](fa_master-editor-look-up.html)
* [Web-контролы и web-компоненты](fa_web-controls.html)


## Куда ссылается эта страница

* [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html)
* [MasterEditorLookUp](fa_master-editor-look-up.html)
* [Web-контролы и web-компоненты](fa_web-controls.html)