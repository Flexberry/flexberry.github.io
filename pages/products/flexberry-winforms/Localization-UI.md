---
title: Локализация пользовательского интерфейса
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/localization--u-i.html
folder: product--folder
lang: ru
---

Локализация состоит из двух следующих задач:
# Локализация `WinForms` (непосредственно форм).
# Локализация строковых констант, записанных непосредственно в код (сообщения пользователю и т.п.)

Первое обеспечивается средствами `Microsoft Visual Studio .NET`.
Для второго в `Flexberry Platform` существуют следующие средства:
# Получение-установка текущей культуры `.Net Framework (ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian(), ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICulture(), ICSSoft.STORMNET.Windows.Forms.WinApplication.GetUICulture())`;
# Возможность создать класс-локализатор, куда приходят вызовы для получения соответствующих строк, а также подключить какой-либо стандартный локализатор;
# Получение обработанной локализатором строки.

Итак, для того, чтобы устроить в своём приложении локализацию, необходимо:
# Выбрать  стандартный `ICSSoft.STORMNET.Windows.Forms.XMLLocalizator`, либо создать свой собственный (имплементировать интерфейс `ICSSoft.STORMNET.Windows.Forms. ILocalizator`)
# Установить сборке локализатор;
# Если есть необходимость, установить явно культуру;
# Заменить(написать) все константы в коде на вызовы ICSSoft.STORMNET.Windows.Forms.WinApplication. GetLocalString.

