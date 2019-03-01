--- 
title: Sending error message email 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Describes the ability to send email with error message in the application and specified the desired settings in the application's configuration file 
toc: true 
permalink: en/fw_send-to-email-bug-report-provider.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 1b621b8d778c6c137d117f8f5e3a8219c6e6b56f94a16bb8f7c55c48b72324bf 
--- 

In the standard window error message has the opportunity to send email with error message. 

![](/images/pages/products/flexberry-winforms/development/error-form.png) 

When you select the menu item "Send email..." starts the mail client by default. The email contains an attachment with an archive containing a screenshot, a description of the error and information about the current system configuration. 
![](/images/pages/products/flexberry-winforms/development/letter.png) 

To start the mail client uses MAPI interface. 
Shipping address, title and message text can be configured in the application's configuration file with keys BugReportEmailAddress, BugReportEmailTitle, BugReportEmailBody. 

__Example:__ 

```
<add key="BugReportEmailAddress" value="user@perm.ru" />
<add key="BugReportEmailTitle" value="Error in Cats or Legs" />
<add key="BugReportEmailBody" value="Message text" />
``` 
If the specified key is not defined in the configuration file, use the default value. 

"Address": blank (an empty string MAPI does not accept). 

"Subject": Error {Maisonville}. 

"Message text" In the user's operation process {ИмяWindowsПользователя} the program { Maisonville} an error occurred. Additional information is in the attachment. 

__Remark:__ 

For temporary storage of files, use the folder Environment.SpecialFolder.InternetCache. However, the file is not automatically removed, because it cannot be deleted until the message is sent.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/