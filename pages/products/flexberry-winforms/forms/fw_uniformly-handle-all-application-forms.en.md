--- 
title: How to uniformly handle all forms of application 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Described as a unified process all the application forms, signing up for a special event. An example, where all forms are signed at the pressing of certain keys. 
toc: true 
permalink: en/fw_uniformly-handle-all-application-forms.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: c17c7f3266a719cf7c74e5d1f9d5dfad85bb2a210dca3d9456c6364468152b77 
--- 

Subscribe to global events 

In the Main method, you can subscribe to special event: 

```csharp
static void Main()
...
// *** Start programmer edit section *** (Metalmania Main()) 
ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian();
ICSSoft.STORMNET.Windows.Forms.Desktop.GlobalWinformEvents.Load += new EventHandler(GlobalWinformEvents_Load);
// *** End programmer edit section *** (Metalmania Main()) 
ICSSoft.STORMNET.Business.LockService.ClearAllUserLocks();
...
}
``` 

In the handler for this event will be coming form in the variable sender. Further, for example, all the forms to sign at the pressing of certain keys: 

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


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/