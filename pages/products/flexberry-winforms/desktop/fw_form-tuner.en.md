--- 
title: customizing the forms for your application 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms), the Specific implementation, PNRPU, Development 
summary: two variants of the uniform processing of application forms. The use of class `UniversalFormTuner` and handling global Load event 
toc: true 
permalink: en/fw_form-tuner.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: fc1985358b67cfcc564345231b84b649449f06b0839b87e36e44b835636232e4 
--- 

Quite often the problem arises to apply at run-time to all application forms any settings. In particular, the task relevant to the independent forms. In this case, You will `UniversalFormTuner`, or rather its successor, as defined in the application application. 


The sequence of actions is: 

1. Add to application class that inherits from `ICSSoft.STORMNET.UI .UniversalFormTuner`. 

0. Override method 
```csharp
public virtual void TuneForm( BaseWin form )
``` 
the <br>Option `form` – a reference to the instance of the form before it is displayed. 

0. To set the property `UniversalFormTuner.StandardTuner` a link to a copy of Your `тюнера`. 


In addition, for the implementation of a uniform forms processing application, you can subscribe to a special global event (in method `Main`): 

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

In the handler for this event will be coming form in the variable sender. Actually what to do - know yourself. For example, all the forms to sign at the pressing of certain keys: 

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