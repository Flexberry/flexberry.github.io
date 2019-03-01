--- 
title: ErrorBox 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Discusses the ways of handling error situations in the application, shows you how to change the default behavior in the application code 
toc: true 
permalink: en/fw_error-box.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: d74ec938f6fee1a8029484cc97c3650b09a0d310182a40173426228e2843afe1 
--- 

## Work with exceptions in applications 
Flexberry Platform in the Assembly `ICSSoft.STORMNET.UI.dll` contains classes to handle exceptional situations. 

## a Simple mapping error 
The standard scenario display the Exception in a special form looks like this: 

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

## More complicated scenario of release of information about exceptions 
Sometimes to understand what happened in the system not enough to know the line number in the source code. For such cases, it is possible to make a number of screen shots, add additional information about the error and pass it to the form, display the error. First of all, all of this information will not be used until the user uploads it with the help of some provider error reports. 

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
The user has the option to upload the error information by clicking on the button with a blue floppy disk. Default available 3 provider error reporting: 
* Save the error report to disk 
* [Send report by email](fw_send-to-email-bug-report-provider.html) 
* To copy information to the clipboard 

The first 2 options supports saving screen images (all the information is in one zip file). In the clipboard only the text information. 


To add your provider to report the error, you need to unasledovala `IBugReportProvider` from the interface and in the configuration file add setting `BugReportProviders` in which to specify the full names of types of providers, separated by a vertical bar. 

__Important:__ never connect 2 providers with the same are defined (to be connected only the first one). 

To work with the data errors you can use the static methods of the class `ErrorBox`. 


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




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/