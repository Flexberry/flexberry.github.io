---
title: Особенности форм в Windows-приложениях
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Формы
summary: Единообразная обработка, контроль положения на экране, зависимые и независимые формы, запрет на закрытие формы, закрытие  всех форм
toc: true
permalink: ru/fw_form-features.html
lang: ru
---

Существует ряд особенностей, которые характерны как для форм редактирования, так и для списковых форм Windows-приложений.

## Единообразная обработка всех форм приложения

Чтобы единообразно обработать все формы приложения, нужно подписаться на глобальные события.

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

В обработчик этого события будет приходить форма в переменной sender. Далее, например, можно все формы подписать на нажатие определённых клавиш:

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

## Реализация интерфейсов независимыми и зависимыми формами и отношения между ними

Диаграмма классов, представленная ниже, показывает, как относятся между собой независимые и зависимые формы списков, универсальные, а также реализуемые на основе базовых конкретные формы списков (могут реализовываться как вручную, так и посредством генераторов кода для `Flexberry`):

![Пример связи зависимых и независимых форм списков](/images/pages/products/flexberry-winforms/forms/primer11.jpg)

Диаграмма, представленная ниже, показывает, как относятся между собой независимые и зависимые формы редактирования, универсальные, а также базовые и конкретные на основе базовых формы (могут реализовываться как вручную, так и посредством генераторов кода для `Flexberry`):

![Пример связи зависимых и независимых форм редактирования](/images/pages/products/flexberry-winforms/forms/primer12.jpg)

## Положение формы на экране

В некоторых случаях восстоновление положения формы приводило к тому, что форма не была видна на экране. Такая ситуация, например, возникает при смене разрешения монитора на более низкое: при новом разрешении форма могла оказаться за пределами видимой области.

Для управления положением формы в Flexberry Platform в класс `BaseWin`, являющийся базовым для всех форм, были добавлены два свойства `OutScreenControl` и `VisiblePercent`.

Свойство `OutScreenControl` позволяет включить контроль выхода окна за пределы экрана в процессе перемещения окна пользователем. Свойство `VisiblePercent` устанавливает процент размера формы (длины/ширины), который должен оставаться видимым (диапазон значений от 0 до 100).

Замечания:

1. При загрузке формы контроль выхода за пределы экрана происходит вне зависимости от значения свойства `OutScreenControl` (для корректного позиционирования формы при смене разрешения). При начальном отображении формы учитывается значение `VisiblePercent`, по умолчанию значение свойства `VisiblePercent` равно __40__.

2. Текущая реализация не учитывает наличие нескольких мониторов.

## Запрет закрытия формы Flexberry Winforms

Для того чтобы запретить закрытие формы, надо переопределить метод `Finalize()` независимой формы.

Пример:

```csharp
[Scriptizer.RunTime.ScriptFinalizer]
public override bool Finalize()
{
return false;
}
```

## Закрытие всех открытых форм

Чтобы закрыть все открытые формы приложения, как происходит при закрытии главного окна приложения Flexberry, можно использовать следующий код.

```csharp
var coll = desktopCtrl2.PathRunners;

foreach (ArrayList runList in coll.GetAllValues())
{
  foreach (Runner run in runList)
  {
    if (run.Alive)
    {
      run.Stop();
    }
  }
}
```