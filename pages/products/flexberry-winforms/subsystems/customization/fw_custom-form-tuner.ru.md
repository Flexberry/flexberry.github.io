---
title: Переход по Enter на форме
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Описана возможность реализации дополнительной логики на формах, например, обработки нажатия определенных клавиш, путём создания пользовательских FormTuner-ов  
toc: true
permalink: ru/fw_custom-form-tuner.html
lang: ru
---

<!-- Данная статья ещё редактируется -->

Для осуществления перехода по кнопке Enter, аналогично тому, как выполняются переходы по кнопке Tab, существуют специальные контролы.

## CustomFormTuner
`CustomFormTuner` - один из контролов для осуществления перехода по кнопке Enter.

{% include important.html content="
`CustomFormTuner` не входит в стандартную поставку Flexberry." %}


### Подключение CustomFormTuner
Для работы `CustomFormTuner` необходимы следующие dll:
* IIS.AMS02.SpecificControls.dll
* ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll (входит в стандартную поставку Flexberry)

Далее в коде конструктора формы необходимо выполнить создание и настройку:

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
`NextControlByEnterTuner` - усовершенствованный `CustomFormTuner`.

### Подключение NextControlByEnterTuner
Для работы `NextControlByEnterTuner` необходима следующая dll: `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`.

Для подключения в программном коде можно в конструкторе формы выполнить следующую последовательность действий: 

1) Создать объект необходимого типа.

```csharp
NextControlByEnterTuner Tuner = new NextControlByEnterTuner();
```

2) Подписать подходящие контролы формы на переход по кнопке Enter, при этом формируется список подписанных контролов, который можно узнать через свойство `SubscribedOnEnterControls`. На настоящий момент автоматически подписываются контролы следующих типов (эти контролы не должны быть контролами-контейнерами): `System.Windows.Forms.TextBox` , `System.Windows.Forms.DateTimePicker` , `System.Windows.Forms.CheckBox` , `System.Windows.Forms.ComboBox` , `ICSSoft.STORMNET.Windows.Forms.DateTimePicker` , `ICSSoft.STORMNET.Windows.Forms.ExtendedComboBox` , `ICSSoft.STORMNET.Windows.Forms.ExtendedTextBox` , `ICSSoft.STORMNET.Windows.Forms.ExtTextControl`.

```csharp
Tuner.SubscribeDefaultSetOfControlsOnEnter(this);
```

3) Также, если есть необходимость, можно добавить контролы, которые должны быть подписаны на переход по кнопке Enter.

```csharp
Tuner.AddToSubscribedOnEnterControls(new System.Windows.Forms.Control[] {lsvРезультат, btnНайти};
```

{% include note.html content="
Обратите внимание, что при добавлении контролов через метод AddToSubscribedOnEnterControls, передаваемые контролы не должны быть контейнерами для других контролов. Также стоит заметить, что если передаваемый контрол, например, класса `System.Windows.Forms.Button`, то сначала выполнится событие, навешенное на клик, после чего уже будет осуществлён переход по Enter (переход организован в событии `Control.KeyUp`)." %}


4) Для достижения желаемого результата также необходимо выполнить аккуратную настройку свойств `TabIndex` и `TabStop` у контролов формы.

## Интерфейс ICanChangeByEnter
Интерфейс `ICanChangeByEnter` разработан для организации взаимодействия `NextControlByEnterTuner` с контролами, в которых на нажатие Enter повешена какая-то логика.

```csharp
public interface ICanChangeByEnter
{
	/// <summary>
	/// Определяет, обрабатывает ли контрол движение по Enter самостоятельно
	/// </summary>
	/// <returns></returns>
	bool CanChangeByEnter { get; }

	/// <summary>
	/// Определяет, будет ли когда-нибудь доступен переход по Enter из контрола
	/// </summary>
	bool CanReleaseEnter { get; }

	/// <summary>
	/// Определяет, можно ли сейчас выйти из контрола по Enter
	/// </summary>
	bool LetReleaseEnterNow { get; }
}
```

Если `LetReleaseEnterNow == true`, то переход по Enter будет выполнен; в противном случае - нет.