---
title: Добавить собственную форму на рабочий стол Windows-приложения
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_add-form-to-win-desktop.html
folder: products/flexberry-winforms/
lang: ru
---
Чтобы добавить собственную форму на рабочий стол windows-приложения, необходимо:
1. Открыть файл `<ИмяПроекта>DesktopCustomizer.cs`.
2. В файле найти метод `GetRunner()`.
3. В [скобки программиста](fo_programmer-brackets.html) надо добавить необходимые [Стартеры](fw_app-desktop.html#стартеры).

В результате должно получиться примерно следующее:

```csharp
public override ICSSoft.STORMNET.UI.Runner[] GetRunners()
{
    System.Collections.ArrayList arr = new System.Collections.ArrayList();
    arr.AddRange(base.GetRunners());
    // *** Start programmer edit section *** (Кредиты GetRunners())

    // *** End programmer edit section *** (Кредиты GetRunners())
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитныйИнспекторL), "Система учета кредитов", "Кредитный инспектор", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитL), "Система учета кредитов", "Кредит", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "Система учета кредитов", "Клиент", ""));
    // *** Start programmer edit section *** (Кредиты GetRunners() End)
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "Система учета кредитов\\Клиенты", "Клиенты", ""));
    arr.Add(new ICSSoft.STORMNET.Windows.Forms.FormRunner(typeof(winformОтчеты), "Система учета кредитов", "Отчеты", ""));
    // *** End programmer edit section *** (Кредиты GetRunners() End)
    ICSSoft.STORMNET.UI.Runner[] retArray = new ICSSoft.STORMNET.UI.Runner[arr.Count];
    arr.CopyTo(retArray);
    return retArray;
}
```

Примечание1: Можно указывать путь в дереве пунктов меню.

Примечание2: Формы принято именовать c префиксом `winform`.

После запуска приложения можно наблюдать следующую картину:
![](/images/pages/products/flexberry-winforms/desktop/win-desktop-plus.png)
