---
title: Go to Enter on the form
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (forms)
summary: Describes the possibility of implementing additional logic on forms, for example, the treatment of depression of certain keys by creating a custom FormTuner s
toc: true
permalink: en/fw_custom-form-tuner.html
lang: en
autotranslated: true
hash: a951efdc31e815adc201d74ec81b44f2c394899f0f0b90c8f699bf8b9be35558
---

<!-- This article is still being edited -->

The transition on the Enter button, similar to the behavior of the Tab key, there are special controls.

## CustomFormTuner
`CustomFormTuner` is one of the responsible for the implementation of the transition button Enter.

{% include important.html content="
`CustomFormTuner` not supplied as standard Flexberry." %}


### Connection CustomFormTuner
To work `CustomFormTuner` need the following dll:
* IIS.AMS02.SpecificControls.dll
* ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll (supplied as standard Flexberry)

Later in the code the form designer, you must create and configure:

```csharp
public class WinformC__СотрудникE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.TryDOEPOnEnter.DPDIC__СотрудникE
{
	//... 
	public WinformC__СотрудникE()
	{
		//... 
		IIS.AMS02.CustomFormTuner Tuner = new IIS.AMS02.CustomFormTuner();
		Tuner.TuneMoveNextOnEnterPress(this);
		Tuner.TuneSkipKeyPressEventOnEnterPress(this);
		//... 
	}
}
```

## NextControlByEnterTuner
`NextControlByEnterTuner` - improved `CustomFormTuner`.

### Connection NextControlByEnterTuner
To work `NextControlByEnterTuner` need the following dll: `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`.

To connect programmatically in the form constructor to perform the following steps:

1) Create an object of the required type.

```csharp
NextControlByEnterTuner Tuner = new NextControlByEnterTuner();
```

2) Sign the appropriate form controls on the transition button Enter, this generates a list of signed controls, which can be obtained through the property `SubscribedOnEnterControls`. Currently, automatically subscribe the following types of controls (these controls should not be controls container): `System.Windows.Forms.TextBox` , `System.Windows.Forms.DateTimePicker` , `System.Windows.Forms.CheckBox` , `System.Windows.Forms.ComboBox` , `ICSSoft.STORMNET.Windows.Forms.DateTimePicker` , `ICSSoft.STORMNET.Windows.Forms.ExtendedComboBox` , `ICSSoft.STORMNET.Windows.Forms.ExtendedTextBox` , `ICSSoft.STORMNET.Windows.Forms.ExtTextControl`.

```csharp
Tuner.SubscribeDefaultSetOfControlsOnEnter(this);
```

3) Also, if necessary, you can add controls that should be signed on the clicking the Enter button.

```csharp
Tuner.AddToSubscribedOnEnterControls(new System.Windows.Forms.Control[] {lsvРезультат, btnНайти};
```

{% include note.html content="
Please note that when you add controls via the method AddToSubscribedOnEnterControls transmitted controls should not be a container for other controls. It is also worth noting that if passed the control, for example, class `System.Windows.Forms.Button`, then first execute the event, hung on click, after which it will be a transition on Enter (the transition is organized in the event `Control.KeyUp`)." %}


4) To achieve the desired result, you must also perform accurate configuration properties `TabIndex` and `TabStop` at the controls of the form.

## Interface ICanChangeByEnter
PstrfICanChangeByEnter` interface designed for interaction `NextControlByEnterTuner` with controls where pressing Enter hanged some logic.

```csharp
public interface ICanChangeByEnter
{
	/// <summary> 
	/// Determines whether the control handles the traffic on the Enter independently 
	/// </summary> 
	/// <returns></returns> 
	bool CanChangeByEnter { get; }

	/// <summary> 
	/// Determines if ever available to move in from the Enter control 
	/// </summary> 
	bool CanReleaseEnter { get; }

	/// <summary> 
	/// Determines if now is the time to get out of control by Enter 
	/// </summary> 
	bool LetReleaseEnterNow { get; }
}
```

If `LetReleaseEnterNow == true`, the transition will Enter выполнен; otherwise not.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}