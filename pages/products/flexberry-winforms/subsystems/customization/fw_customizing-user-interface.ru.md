---
title: Настройка пользовательского интерфейса
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Формы, пользовательские формы
summary: Приведен перечень наиболее часто требуемых доработок пользовательского интерфейса, полученного в результате генерации кода
toc: true
permalink: ru/fw_customizing-user-interface.html
lang: ru
---

После автоматической генерации приложения его интерфейс нуждается в доработке. Элементы на формах нуждаются в установке на нужное место и определении позиции. Внешний вид элементов нужно привести к определенному стилю, которому придерживается все приложение, и прочее.

Для Windows-приложений на помощь приходят 2 полезных класса, поддерживаемых технологией Flexberry:

* FormTuner
* ControlProvider

### FormTuner

`FormTuner` - это класс, позволяющий применить какие-либо настройки ко всем формам в приложении.

Достаточно часто возникает задача применить в run-time ко всем формам приложения какие-либо настройки. Особенно задача актуальна для независимых форм. В данном случае поможет `UniversalFormTuner`, а точнее его наследник, определенный в прикладном приложении.

Последовательность действий такова:

1.Добавить в прикладное приложение класс, наследующий от `ICSSoft.STORMNET.UI .UniversalFormTuner`.

2.Переопределить метод.

```csharp
public virtual void TuneForm( BaseWin form )
```

Параметр `form` – ссылка на экземпляр формы перед ее отображением.

3.Присвоить свойству `UniversalFormTuner.StandardTuner` ссылку на экземпляр «тюнера».

Кроме того, для осуществления единообразной обработки форм приложения, можно подписаться на специальное глобальное событие (в методе `Main`):

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

В обработчик этого события будет приходить форма в переменной sender. Далее можно, например, все формы подписать на нажатие определённых клавиш:

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

[ControlProvider](fw_control-provider-winforms.html) позволяет определить собственные контролы для редактирования определенных типов данных или изменить существующие. Будучи определенными в ControlProvider'e, эти контролы будут применяться в универсальной форме редактирования и в некоторых других контролах (например, в [GroupEdit](fw_group-edit.html)).

Сгенерированные формы нуждаются в доработке по следующим пунктам:

* Заголовки форм
* Расположение элементов на формах
* Якоря элементов на формах
* Минимальный и максимальный размеры форм
* Общий стиль цветов и шрифтов контролов на формах (ControlProvider)
* Локализация
* Установка фонового изображения на каждую форму (FormTuner)

### Использование горячих клавиш

Возникает необходимость добавить в приложение горячие клавиши, а также настроить порядок обхода элементов по нажатию клавиши <[Enter](fw_custom-form-tuner.html)>

## См. также

[Визуальная логика](fw_visual-logic.html)
