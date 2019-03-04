--- 
title: Run the code in the main application thread from another thread 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), the Windows UI (forms) 
summary: Specified what to do if you need to run the code in the main thread of the application in the absence of a reference to the control created in this thread 
toc: true 
permalink: en/fw_ui-synchronization-context.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 8401fafb771552ac1f6c0a8ba5adf531577e7d5b452de1dc4e22aeb1b0a9bccf 
--- 

One of the basic rules of concurrent programming for Windows forms reads as follows: "`Обращение to the control should be done from the thread that the control was создан`". This is normally done by calling methods `Control.Invoke` (simultaneous launch delegate) and `Control.BeginInvoke` (asynchronous start delegate). 
However, it is sometimes necessary to run code in the main thread of the application in the absence of a reference to the control created in this thread. To solve the problem of running code in main thread from another thread in WinForms used class `SynchronizationContext`. In Flexberry Platform to refer to the context synchronization is available by calling `UISynchronization.Context`, `Context` static field is initialized in the form constructor your desktop. 

__Example:__ 

```csharp
if (UISynchronization.Context!=null)
UISynchronization.Context.Send((delegate
       {
       	bugReportProvider.SaveError(_screenShots, sysInfo, exceptions[0] as Exception);
       }), null);
``` 





 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/