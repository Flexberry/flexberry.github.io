--- 
title: global setting web controls 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_init-control-settings-delegate.html 
lang: en 
autotranslated: true 
hash: ba38b2b40c9f1794f0f618fccde40dde1f24ac41301d3962f1618a5f1b129e5c 
--- 

To empower the settings of the controls have a delegate type - `ICSSoft.STORMNET.Web.Tools.InitControlSettingsDelegate`: 

```csharp
public delegate void InitControlSettingsDelegate<TControl>(TControl instance) where TControl : Control;
``` 

At the [web control](fa_web-controls.html) that support this way of configuration is static, the delegates of this type, to which you can assign methods to customize. Usually there is a delegate called at the end of the designer web control. In the method, you can initialize properties, etc. (as a parameter it is passed the current instance of the object). 

To assign a static delegates in `global.asax.cs`. 

An example of setting the DatePicker control: 

```csharp
DatePicker.InitSettings = delegate(DatePicker instance)
    {
        instance.DisplayMode = DataPickerDisplayMode.OnFieldFocused;
    };
``` 

or using a lambda expression: 

```csharp
DatePicker.InitSettings = instance =>
                {
                    instance.DisplayMode = DataPickerDisplayMode.OnFieldFocused;
                };
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}