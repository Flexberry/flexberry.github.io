---
title: Глобальная настройка веб-контролов
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_init-control-settings-delegate.html
lang: ru
---

Для расширения возможностей настройки контролов есть тип делегата - `ICSSoft.STORMNET.Web.Tools.InitControlSettingsDelegate`:

```csharp
public delegate void InitControlSettingsDelegate<TControl>(TControl instance) where TControl : Control;
```

У [web-контролов](fa_web-controls.html), поддерживающих такой путь настройки, есть статические делегаты этого типа, которым можно присваивать методы для настройки. Обычно имеется делегат, вызывающийся в конце конструктора web-контрола. В методе можно инициализировать свойства и т.д. (в качестве параметра ему передается текущий экземпляр объекта).

Присваивать статические делегаты можно в `global.asax.cs`.

Пример настройки контрола DatePicker:

```csharp
DatePicker.InitSettings = delegate(DatePicker instance)
    {
        instance.DisplayMode = DataPickerDisplayMode.OnFieldFocused;
    };
```

или через лямбда-выражение:

```csharp
DatePicker.InitSettings = instance =>
                {
                    instance.DisplayMode = DataPickerDisplayMode.OnFieldFocused;
                };
```
