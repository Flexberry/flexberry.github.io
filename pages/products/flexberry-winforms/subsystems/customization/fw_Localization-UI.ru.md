---
title: Локализация пользовательского интерфейса
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Указано как в приложении установить локализацию
toc: true
permalink: ru/fw_localization-ui.html
folder: products/flexberry-winforms/
lang: ru
---

Локализация состоит из двух следующих задач:
* Локализация `WinForms` (непосредственно форм).
* Локализация строковых констант, записанных непосредственно в код (сообщения пользователю и т.п.)

Первое обеспечивается средствами `Microsoft Visual Studio .NET`.
Для второго в `Flexberry Platform` существуют следующие средства:
* Получение-установка текущей культуры `.Net Framework (ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian(), ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICulture(), ICSSoft.STORMNET.Windows.Forms.WinApplication.GetUICulture())`;
* Возможность создать класс-локализатор, куда приходят вызовы для получения соответствующих строк, а также подключить какой-либо стандартный локализатор;
* Получение обработанной локализатором строки.

Итак, для того, чтобы устроить в своём приложении локализацию, необходимо:
1. Выбрать  стандартный `ICSSoft.STORMNET.Windows.Forms.XMLLocalizator`, либо создать свой собственный (имплементировать интерфейс `ICSSoft.STORMNET.Windows.Forms. ILocalizator`)
0. Установить сборке локализатор;
0. Если есть необходимость, установить явно культуру;
0. Заменить(написать) все константы в коде на вызовы ICSSoft.STORMNET.Windows.Forms.WinApplication. GetLocalString.
