---
title: exception Handling in winforms applications
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, error-handling
summary: exception Handling in the application, methods of reset in the movie information about exceptions, set the provider to report the error handling of uncaught exception, your log messages
toc: true
permalink: en/fw_error-box.html
lang: en
autotranslated: true
hash: f2557e81c4741c26d794f185542323929b26eeac3ce72deba1d2c91ffad7fb06
---

Flexberry Platform in the Assembly `ICSSoft.STORMNET.UI.dll` contains classes to handle exceptional situations.

## A simple display error

The standard scenario display `Exception` in a special form looks like this:

```csharp
try
{
  throw new Exception("Aw, snap!");
  //... 
}
catch(Exception ex)
{
  ErrorBox.Show(ex);
}
```

### Ways of collecting information about exceptions

Sometimes, to understand what happened in the system, not enough to know the line number in the source code. For such cases, it is possible to make a number of screen shots, add additional information about the error and pass it to the form, display the error. However, all of this information will not be used until the user uploads it with the help of some provider error reports.

So, to get the screen shots:

```csharp
//Make the only form 
Bitmap screen1 = ErrorBox.CaptureScreenShot(formToCapture);
//or create the whole screen 
Bitmap screen2 = ErrorBox.CaptureScreenShot();
```

In order to bring these pictures and the message:

```csharp
//... 
catch(Exception ex)
{
  List<Bitmap> screens = new List<Bitmap>();
  screens.Add(screen1);
  screens.Add(screen2);
  ErrorBox.Show(ex, screens, "More information error: variable X has value:" + X);
}
```

## Providers error reporting

The user has the option to upload the error information by pressing the appropriate button on the form errors. Default available 3 provider error reporting:

* Save the error report to disk
* Send report by email
* To copy information to the clipboard

The first 2 options supports saving screen images (all the information is in one zip file). In the clipboard only the text information.

To add your provider to report the error, you need to unasledovala `IBugReportProvider` from the interface and in the configuration file add setting `BugReportProviders` in which to specify the full names of types of providers, separated by a vertical bar.

__Important:__ never connect 2 providers with the same `MenuItemName` (to be connected only the first one).

To work with the data errors you can use the static methods of the class `ErrorBox`.

### Sending error message email

In the standard window error message there is a possibility to send email with error message.

![Form exception](/images/pages/products/flexberry-winforms/development/error-form.png)

When you select the menu item "Send email..." starts the mail client by default. The email contains an attachment with an archive containing a screenshot, a description of the error and information about the current system configuration.
![Message](/images/pages/products/flexberry-winforms/development/letter.png)

To start the mail client uses MAPI interface. Shipping address, title and message text can be configured in the application's configuration file with keys `BugReportEmailAddress`, `BugReportEmailTitle`, `BugReportEmailBody`.

__Example:__

```xml
<add key="BugReportEmailAddress" value="user@perm.ru" />
<add key="BugReportEmailTitle" value="Error in Cats or Legs" />
<add key="BugReportEmailBody" value="Message text" />
```

If the specified key is not defined in the configuration file, use the default value.

Address: blank (an empty string MAPI does not accept).

Theme: Error {Maisonville}.

Message text: the user's operation process {ИмяWindowsПользователя} the program { Maisonville} an error occurred. Additional information is in the attachment.

__Remark:__

For temporary storage of files, use the folder `Environment.SpecialFolder.InternetCache`. However, the file is not automatically removed, because it cannot be deleted until the message is sent.

## Adding information about the application

Class `ICSSoft.STORMNET.Windows.Forms.ErrorBox` contains a static delegate that allows you to collect system information in order to get the information about the error.

```csharp
/// <summary> 
/// Delegate to collect information about the application 
/// </summary> 
/// <returns></returns> 
public delegate string GetCurrentAppInfoDelegate();

/// <summary> 
/// Delegate to collect information on the applied application. The result will be prisobachit to General information about the system. 
/// </summary> 
public static GetCurrentAppInfoDelegate GetCurrentAppInfo = null;
```

## Handling uncaught exceptions in application systems

So that users do not show "terrible" form with error information, if there was uncaught exception, you need to subscribe to events `Application.ThreadException` and `System.AppDomain.CurrentDomain.UnhandledException` to treat them properly.

The application generator adds the string Flexberry

```csharp
System.Windows.Forms.Application.ThreadException += ICSSoft.STORMNET.Windows.Forms.ErrorBox.ApplicationThreadException;
System.AppDomain.CurrentDomain.UnhandledException += ICSSoft.STORMNET.Windows.Forms.ErrorBox.CurrentDomainUnhandledException;
```

in the method `Main` applications (in parenthesis the programmer). Handlers `ICSSoft.STORMNET.Windows.Forms.ErrorBox.ApplicationThreadException` and `ICSSoft.STORMNET.Windows.Forms.ErrorBox.CurrentDomainUnhandledException` just run standard `ErrorForm` with information about the exception. If you need a custom logic, then you can use your own event handler.

## The connection error log

To enable the error log, it is sufficient to specify this attribute in the configuration file:

```xml
<add key="ErrorLog" value="true" />
```

All ErrorBox will record errors to a csv file in the folder with the application.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}