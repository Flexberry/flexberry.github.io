---
title: ErrorBox
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_error-box.html
folder: products/flexberry-winforms/
lang: ru
---

## Работа с исключительными ситуациями в приложениях
Flexberry Platform в сборке `ICSSoft.STORMNET.UI.dll` содержит классы для обработки исключительных ситуаций. 

## Простое отображение ошибки
Стандартный сценарий отображения Exception в специальной форме выглядит так:

```csharp
try
{
  throw new Exception("Опаньки!");
  //...
}
catch(Exception ex)
{
  ErrorBox.Show(ex);
}
```

## Более сложный сценарий выдачи информации об исключениях
Иногда чтобы понять что случилось в системе недостаточно знать номер строки в исходном коде. Для таких случаев есть возможность сделать ряд снимков экрана, добавить дополнительную информацию об ошибках и передать это всё на форму отображения ошибки. Оговоримся, что вся эта информация не будет использована пока пользователь не выгрузит её с помощью какого-либо провайдера отчётов об ошибках.

Итак, чтобы получить снимки экрана:

```csharp
//Сделаем снимок только формы
Bitmap screen1 = ErrorBox.CaptureScreenShot(formToCapture);
//или снимем весь экран
Bitmap screen2 = ErrorBox.CaptureScreenShot();
```

Для того чтобы вывести эти снимки и сообщение:

```csharp
//...
catch(Exception ex)
{
  List<Bitmap> screens = new List<Bitmap>();
  screens.Add(screen1);
  screens.Add(screen2);
  ErrorBox.Show(ex, screens, "Дополнительная информация об ошибке: переменная Х имеет значение:" + X);
}
```

## Провайдеры отчётов об ошибках
Пользователь имеет возможность выгрузить информацию об ошибке, нажав на кнопку с синей дискеткой. По-умолчанию доступны 3 провайдера отчётов об ошибках: 
* Сохранить отчёт об ошибке на диск
* [Отправить отчет по электронной почте](fw_send-to-email-bug-report-provider.html)
* Скопировать информацию в буфер обмена

Первые 2 варианта поддерживают сохранение изображений экрана (вся информация помещается в один zip-файл). В буфер обмена попадает только текстовая информация.


Чтобы добавить свой провайдер отчёта об ошибке, нужно унаследоваться от интерфейса `IBugReportProvider` и в конфигурационном файле добавить настройку `BugReportProviders`, в которой указать полные имена типов провайдеров, разделённые символом "|". 

__Важно:__ нельзя подключить 2 провайдера с одинаковым MenuItemName (будет подключен только первый из них).

Для работы с данными ошибки можно использовать статические методы класса `ErrorBox`.


## Добавление информации о прикладной системе
Класс `ICSSoft.STORMNET.Windows.Forms.ErrorBox` содержит статический делегат, который позволяет собрать информацию о системе для того, чтобы она попала в информацию об ошибке.

```csharp
/// <summary>
/// Делегат для сбора информации о прикладной системе
/// </summary>
/// <returns></returns>
public delegate string GetCurrentAppInfoDelegate();

/// <summary>
/// Делегат, который должен собрать информацию о прикладном приложении. Его результат будет присобачен к общей информации о системе.
/// </summary>
public static GetCurrentAppInfoDelegate GetCurrentAppInfo = null;
```

## Обработка неотловленных исключений в прикладных системах
Чтобы пользователям не показывалась "страшная" форма с информацией об ошибке, если случилось неотловленное исключение, нужно подписаться на события `Application.ThreadException` и `System.AppDomain.CurrentDomain.UnhandledException` для того чтобы обработать их правильным образом.

Генератор приложений Flexberry добавляет строки

```csharp
System.Windows.Forms.Application.ThreadException += ICSSoft.STORMNET.Windows.Forms.ErrorBox.ApplicationThreadException;
System.AppDomain.CurrentDomain.UnhandledException += ICSSoft.STORMNET.Windows.Forms.ErrorBox.CurrentDomainUnhandledException;
```

в метод `Main` приложения (в скобках программиста). Обработчики `ICSSoft.STORMNET.Windows.Forms.ErrorBox.ApplicationThreadException` и `ICSSoft.STORMNET.Windows.Forms.ErrorBox.CurrentDomainUnhandledException` просто запускают стандартную `ErrorForm` с информацией об исключении. Если требуется особая логика, то можно использовать собственный обработчик события. 

