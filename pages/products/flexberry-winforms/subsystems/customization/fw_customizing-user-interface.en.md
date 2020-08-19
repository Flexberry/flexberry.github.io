---
title: user interface customization
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Forms, custom forms
summary: the following is the list of most commonly required improvements of the user interface, the resulting code generation
toc: true
permalink: en/fw_customizing-user-interface.html
lang: en
autotranslated: true
hash: 424165f2d55aefe4a47fb7ff80a54b23e279b8c92bbcf051d314472e132bc0b2
---

After automatic generation of the application, its interface needs improvement. Items on the forms need to install on your desired location and determine position. The appearance of elements to lead to a particular style, which adheres to the whole application, and so on.

For Windows-based applications come to the aid of 2 helpful class technology supported Flexberry:

* FormTuner
* ControlProvider

### FormTuner

`FormTuner` is a class that allows you to apply any settings for all forms in the application.

Quite often the problem arises to apply at run-time to all application forms any settings. In particular, the task relevant to the independent forms. In this case, will help `UniversalFormTuner`, or rather its successor, as defined in the application application.

The sequence of actions is:

1.Add to application class that inherits from `ICSSoft.STORMNET.UI .UniversalFormTuner`.

2.Override the method.

```csharp
public virtual void TuneForm( BaseWin form )
```

Option `form` – a reference to the instance of the form before it is displayed.

3.To set the property `UniversalFormTuner.StandardTuner` a reference to an instance of» «tuner.

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

### ControlProvider

[ControlProvider](fw_control-provider-winforms.html) allows you to define your own controls for editing certain data types or modify existing ones. As defined in ControlProvider'e, these controls will apply in the universal form of editing in some other controls (for example, in [GroupEdit](fw_group-edit.html)).

The generated forms need improvement in the following points:

* The titles of the forms
* Location of items on forms
* Anchor elements on forms
* The minimum and maximum form dimensions
* The overall style of colors and fonts of controls on forms (ControlProvider)
* Localization
* Setting a background image on each form (FormTuner)

### The use of keyboard shortcuts

There is a need to add in-app hotkeys and also adjust the order of items by pressing the <[Enter](fw_custom-form-tuner.html)>

## Cm. also

[Visual logic](fw_visual-logic.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}