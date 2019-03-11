--- 
title: Web form auditing 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Audit 
toc: true 
permalink: en/fa_audit-web-forms.html 
lang: en 
autotranslated: true 
hash: 64ccdc59c8653e5d689bdc05f76055b91eaaae23d2c5a3ade73d422c1440e415 
--- 

[Web-form](fa_tech-forms-web.html) the [audit](fa_audit-web.html) was developed for displaying objects used [ICSSoft.STORMNET.Business.Audit.Audit](efs_i-audit.html). Located in the Assembly "ICSSoft.STORMNET.Web.AjaxControls.dll". 

There are also [win-form audit](efs_audit-win-forms.html). 

### Tapicerowane forms of audit 

Tapicerowane form audit display audit records in the same form in which they exist in the database. 

**List form** allows you to view as auditing throughout the system as a whole and for each specific property. 
To view data for the entire system, simply connect the form AuditEntityL to the project (~/Forms/Audit/AuditEntityL.aspx). 
To view data for a specific object, you must pass the form of an extension option AuPK, wherein [primary key object](fo_primary-keys-objects.html), whose audit you want to display. 

For example, the address may be as follows: http://some:1111/Forms/Audit/AuditEntityL.aspx?AuPK={1843fc3c-b4fc-4a37-bf6e-60a0ea84aead}. 

**[Edit form](fa_editform.html)** allows you to view detailed information on specific audit records. On this form there is a table that presents information about modifying fields of the object while conducting the audited operations. 
A separate line is the change [primary key object](fo_primary-keys-objects.html) (this string is generated when you change detail or wizard). There are two modes of display these lines: they do not appear or they are hiding in a tree structure (the second option was implemented in [older versions of audit](efs_audit.html)). By default, implements the first option, but you can change the following initialization: 

```csharp
AuditService.Current.ShowPrimaryKey = true;
``` 

## object-oriented forms of audit 

Object-oriented forms of audit is focused on the display of information 

**List form** AuditEntityByObjectsL (~/Forms/Audit/AuditEntityByObjectsL.aspx) allows you to view information on the objects on which was performed the audited action. 

The form contains [next field](fa_audit-web.html): 

* Edit - the last modification date of the object (create, modify, or delete). 
* Editor - the user name who last modified the object. 
* Object type - type of the audited object. 
* ID - the ID of the audited object (usually [primary key](fo_primary-keys-objects.html)). 
* Create - creation date of the object. 
* Creator - name of the user who created the object. 

The default list is sorted by date "Edit" (top new). 

**Edit** AuditEntityByObjectsE allows you to view detailed information for a particular audited entity.

The form contains [next field](fa_audit-web.html): 

* ID - the ID of the audited object (usually [primary key](fo_primary-keys-objects.html)). 
* Object type - type of the audited object. 
* Create - creation date of the object. 
* Creator - name of the user who created the object. 
* Edit - the last modification date of the object (create, modify, or delete). 
* Editor - the user name who last modified the object. 
* The list of fields in the audit: 
* Operation time - time when the object was changed. 
* Operation type - transaction type, which was modified by field. 
* Name - the name of the user who performed the audited operation. 
* Login - login of the user who performed the audited operation. 
* Field name - display name of the field. 
* Old value - old value of the field. 
* New value - the new value of the field. 
* Source - the source, the audited operations. 
* Result - the result of the operation (whether to lock the operation was a failure, etc.). 

The list of fields to audit is sorted by the time of the operation (top new). 

To disable the display of records containing [primary keys](fo_primary-keys-objects.html) [masters](fd_master-association.html) and [datalow](fo_detail-associations-properties.html), you can perform the following settings: 

```csharp
AuditService.Current.ShowPrimaryKey = false;
``` 

If AuditService.Current.ShowPrimaryKey is set to true, the list of fields displayed changes [in hierarchy](fa_wolv-hierarhy.html) same as in tapicerowane forms of audit. 

{% include warning.html content="When using web forms audit must be in the file WebControlProvider.xml add the following information:" %} 

```csharp
<customproperty class="AuditEntity" property="ObjectType.Name">
  <control typename="NewPlatform.Flexberry.Web.Controls.DataObjectTypeCaption, ICSSoft.STORMNET.Web.AjaxControls" property="Value" codefile="" />
</customproperty>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}