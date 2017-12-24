---
title: Как единообразно обработать все формы приложения
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Описано как единообразно обработать все формы приложения, подписавшись на специальное событие. Приведён пример, в котором все формы подписываются на нажатие определённых клавиш.
toc: true
permalink: en/fw_uniformly-handle-all-application-forms.html
folder: products/flexberry-winforms/
lang: en
---

Подписка на глобальные события

В методе Main можно подписаться на специальное событие:

```csharp
static void Main()
...
// *** Start programmer edit section *** (Детейломания Main())
ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian();
ICSSoft.STORMNET.Windows.Forms.Desktop.GlobalWinformEvents.Load += new EventHandler(GlobalWinformEvents_Load);
// *** End programmer edit section *** (Детейломания Main())
ICSSoft.STORMNET.Business.LockService.ClearAllUserLocks();
...
}
```

В обработчик этого события будет приходить форма в переменной sender. Далее,например, можно все формы подписать на нажатие определённых клавиш:

```csharp
// *** Start programmer edit section *** (ДетейломанияDesktop CustomMembers)
static void GlobalWinformEvents_Load(object sender, EventArgs e)
{
((Form)sender).KeyPreview = true;
((Form)sender).KeyPress += new KeyPressEventHandler(ДетейломанияDesktop_KeyPress);
}
static void ДетейломанияDesktop_KeyPress(object sender, KeyPressEventArgs e)
{
if (e.KeyChar == 13)
{
MessageBox.Show("Enter was pressed","Hello");
}
}
// *** End programmer edit section *** (ДетейломанияDesktop CustomMembers)
```