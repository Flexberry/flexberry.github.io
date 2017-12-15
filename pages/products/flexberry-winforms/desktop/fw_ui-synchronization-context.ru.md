---
title: Запуск кода в основном потоке приложения из другого потока
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
summary: Указано что делать, если возникает необходимость запустить код в основном потоке приложения при отсутствии ссылки на контрол, созданный в этом потоке
toc: true
permalink: ru/fw_ui-synchronization-context.html
folder: products/flexberry-winforms/
lang: ru
---

Одно из основных правил многопоточной разработки для форм Windows гласит: ''«Обращение к элементу управления должно производится из того потока, в котором этот элемент управления был создан»''. Обычно эта задача решается вызовом методов `Control.Invoke` (синхронный запуск делегата) и `Control.BeginInvoke` (асинхронный запуск делегата).
Однако иногда возникает необходимость запустить код в основном потоке приложения при отсутствии ссылки на контрол, созданный в этом потоке. Для решения задачи запуска кода в основном потоке из другого потока в WinForms применяется класс `SynchronizationContext`. В Flexberry Platform обратиться к контексту синхронизации можно посредством вызова `UISynchronization.Context`, статическое поле `Context` инициализируется в конструкторе формы рабочего стола.

__Пример:__

```csharp
if (UISynchronization.Context!=null)
UISynchronization.Context.Send((delegate
       {
       	bugReportProvider.SaveError(_screenShots, sysInfo, exceptions[0] as Exception);
       }), null);
```


