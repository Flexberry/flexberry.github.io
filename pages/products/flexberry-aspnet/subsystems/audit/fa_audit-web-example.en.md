--- 
title: Connection Example of an audit to an existing Web application using regeneration project 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Audit, Flexberry Designer 
toc: true 
permalink: en/fa_audit-web-example.html 
lang: en 
autotranslated: true 
hash: b907650d661581d03cc5df3fbdce0188a83dc3c2705c5daf2f0a7cb466b2041a 
--- 

{% include note.html content="If you regenerate your project there is no desire\possibility 
you should read the article [connection Example of an audit to an existing Web application without using regeneration project](fa_audit-web-example-manual.html)" %} 

## Connection audit 

The audit connection to the existing system will be reviewed by example. 

### Task 

Add listening of created and deleted objects in the existing system. Also need to change auditing for a class `Кредит` 

## class Diagram 

![](/images/pages/products/flexberry-aspnet/audit/filter-ex-diagram.png) 

### configuration audit 

#### database setup 

First we need to configure the audit database. In the form settings [properties stage](fo_audit-setup.html). 

![](/images/pages/products/flexberry-aspnet/audit/audit_app-settings.png) 

Database auditing can be stored in a separate database and database application. This is determined by the setting `БД audit database приложения`. If you decide to keep the data together. Select this setting and configure `Не remove existing таблицы`. 

Save the settings and close the form. 

#### setting the stage 

Open the settings form the stage and go to the tab `Настройка аудита` 

![](/images/pages/products/flexberry-aspnet/audit/audit-settings-stady.png) 

* Click `Включить audit in all классах` ([more](fo_audit-setup.html)) 

* Save the settings and close the form 

#### set up the classes 

Audit creation and deletion is enabled by default. Therefore, to complete the task, you need to enable auditing of changes to class `Кредит`. 

Odrt class properties `Кредит` and install `Вести audit operations изменения`. 

![](/images/pages/products/flexberry-aspnet/audit/audit-settings-class.png) 

Save the settings and close the form. 

#### setup a class with the stereotype Application 

### Generation applications 

To generate the application, you must: 

* [Align database applications](fd_matching-db.html) 
* Generate classes 
* Compile classes 
* Generate ASP.NET app 

### Result 

The project has added [web pages to display audit](fa_audit-web-forms.html): 

![](/images/pages/products/flexberry-aspnet/audit/audit-files-in-project.png) 

In each data class (class with the stereotype [Implementation](fd_data-classes.html)) added class AuditSettings to store audit settings for this class. 

In addition, classes were added to the submission for audit `AuditView`, and 4 new fields: `Creator`, `CreateTime`, `Editor`, `EditTime` - the consequences of placing the settings to Add audit fields ([more](efs_flexberry-audit-object-fields.html)). 

#### Viewing operations audit 

As mentioned above, the project appeared [web page to view audit data](fa_audit-web-forms.html). By default, the configuration file responsible for these pages (the file is in the folder forms\audit shown in the figure above), as follows: 

```xml
<authorization>
  <deny users="?"/>
  <allow roles="admin" />
  <deny users="*" />
</authorization>
``` 

This means that access is closed to all users except users with the admin role ([read more on MSDN](https://msdn.microsoft.com/ru-ru/library/8aeskccd(v=vs.90).aspx)) 

To deal with system configuration authority, you must change the configuration file as follows: 

```xml
<authorization>
  <deny users="?"/>
</authorization>
``` 

Thus, access to these pages would only be prohibited to unauthorized users. 

Links to the page `AuditEntityL.aspx` can be put on the [Desk](fa_add-page-web-desktop.html) or simply type in the address bar the page address. 

On the form you can see the standard [WebObjectListView](fa_web-object-list-view.html) is empty, as audited operations have not yet been committed. 

Next, you can create a new `Клиента`, edit an existing `Кредит`, and then update the form display the audit data: 

![](/images/pages/products/flexberry-aspnet/audit/audit-wolv.png) 

As you can see, time is fixed and the type операции; the object on which the operation was performed (its primaryKey); by whom and where the operation was performed, and the result of the operation. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}