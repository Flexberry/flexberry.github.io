---
title: Подсветка активного контрола
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, HighLighter
summary: Объявление, настройка для одной или нескольких форм, пример использования HighLighter
toc: true
permalink: ru/fw_high-lighter.html
lang: ru
---

`HighLighter` - компонент, обеспечивающий подсветку активного контрола на форме.

## Объявление

Для его использования `HighLighter` в проекте должна быть ссылка на сборку `ICSSoft.STORMNET.Windows.Forms.AdditionalControls`

В [скобки программиста](fo_programmer-brackets.html) `CustomMembers` поместить код:

```csharp
// Объявление HighLighter'а HL
ICSSoft.STORMNET.Windows.Forms.HighLighter HL;
```

В конструкторе зависимой формы:

```csharp  
// Создание объекта                            
HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(this);

// Задание цвета подсветки
HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
```

В конструкторе независимой формы:

```csharp  
// Создание объекта                            
HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(form);

// Задание цвета подсветки
HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
```

Также можно настроить подсветку для нескольких форм сразу. Для этого необходимо следующее:

* В самом начале приложения подписаться на событие загрузки формы посредством `Desktop.GlobalWinformEvents.Load`.
* Определить список `changingFormTypes`, куда записываются типы форм, для которых необходимо организовать подсветку.
* В обработчике включить подсветку.

Настройка производится в приложении.

Пример

```csharp
 [AccessType(ICSSoft.STORMNET.AccessType.none)]
 public class TestStandWinformsDesktop : ICSSoft.STORMNET.Windows.Forms.Desktop
    {
        
        ...
        
        // *** Start programmer edit section *** (TestStandWinformsDesktop CustomMembers)
        /// <summary>
        /// Список форм, для которых настроена подсветка и переход по Enter.
        /// </summary>
        private static List<Type> changingFormTypes = new List<Type>()
            {
                typeof(IIS.TestStandWinforms.WinformДомHighLighter),
                
            };

        /// <summary>
        /// Обработчик события загрузки формы.
        /// Используется для подписки необходимых форм на подсветку.
        /// </summary>
        /// <param name="sender">Инициатор события, форма.</param>
        /// <param name="e">Параметры события.</param>
        static void GlobalWinformEvents_Load(object sender, EventArgs e)
        {
            if (changingFormTypes.Contains(sender.GetType()))
            {
                Form currentForm = (Form)sender;
                ICSSoft.STORMNET.Windows.Forms.HighLighter HL;
                // Создание объекта                            
                HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(currentForm);
                HL.SubscribeHighliterManually();

                // Задание цвета подсветки
                HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
            }
        }

        // *** End programmer edit section *** (TestStandWinformsDesktop CustomMembers)

        ...

 [STAThread()]
 static void Main()
 {
    try
    {
         // *** Start programmer edit section *** (TestStandWinforms Before authorization)
         // Подписываемся на загрузку формы.
         Desktop.GlobalWinformEvents.Load -= GlobalWinformEvents_Load;
         Desktop.GlobalWinformEvents.Load += GlobalWinformEvents_Load;

         ...
         // *** End programmer edit section *** (TestStandWinforms Before authorization)
     ...
    }
 }
```

`HighLighter` подписывается на события `OnGotFocus` и `OnLostFocus` объектов формы и меняет их свойство `BackColor` на свой и обратно при срабатывании.

## Результат

Сравнение одной и той же формы с включенным `Highlighter` и без:

| Без Highlighter'a | С Highlighter'ом |
|--|--|
| ![](/images/pages/products/flexberry-winforms/controls/highlighter/highlighter-off.gif)|![](/images/pages/products/flexberry-winforms/controls/highlighter/highlighter-on.gif)|
 