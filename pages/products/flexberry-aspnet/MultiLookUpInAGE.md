---
title: Использование множественного выбора в LookUp в AjaxGroupEdit
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/multi-look-up-in-a-g-e.html
folder: product--folder
lang: ru
---

(((<msg type=note>Данная статья находится в доработке</msg>)))

В AiaxGroupEdit существует возможность множественного выбора по LookUp.

Настроить можно так:
```cs
ctrlКвартира.AddLookUpSettings(Information.ExtractPropertyName<Квартира>(x => x.ВидОтделки), new LookUpSetting { MultiSelect = true });
```

На пользовательском интерфейсе множественный выбор выглядит так:

![](/images/pages/ABratchikova/Множественный выбор в AGE1.png)

![](/images/pages/ABratchikova/Множественный выбор в AGE2.png)

