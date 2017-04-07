---
title: Использование множественного выбора в LookUp в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_multi-lookup-age.html
folder: products/flexberry-aspnet/
lang: ru
---

В AiaxGroupEdit существует возможность множественного выбора по LookUp.

Настроить можно так:

```csharp
ctrlКвартира.AddLookUpSettings(Information.ExtractPropertyName<Квартира>(x => x.ВидОтделки), new LookUpSetting { MultiSelect = true });
```

На пользовательском интерфейсе множественный выбор выглядит так:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age1.png)

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age2.png)

