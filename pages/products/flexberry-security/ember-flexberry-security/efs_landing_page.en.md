--- 
title: ember-flexberry-security 
keywords: security, audit 
tags: [EmberJS] 
sidebar: ember-flexberry-security_sidebar 
toc: false 
permalink: en/efs_landing_page.html 
folder: ember-flexberry 
lang: en 
autotranslated: true 
hash: 91a76d5c811ee9599fa463654ce011ea3c06b502dc50d347955c6f5fd611e879 
summary: This section focuses on the client part of the subsystem powers and change audit data. 
--- 

# Authority and audit 
Ember addon ember-flexberry-security includes a set of components and forms for working with data audit and authority for the client. 

## Powers 
* [Subsystem powers](efs_security.html) 
* [Setup forms of authority](efs_security_forms.html) 
* [The data model authority](efs_security_schema.html) 

## Audit data changes 
* [Audit](efs_audit.html) 
* [Audit form](efs_audit_forms.html) 
* [Viewing audit data by object](efs_audit_for_object.html) 

# Setup ember addon 
Installing the addon is done with the command: 
```
ember install ember-flexberry-security
``` 
{% include note.html content=" 

The plugin is still in development, but the types of audits and some form of reference for you to use. The generator form of authority does not generate, because they are defined in the addon ember-flexberry-security. The programmer need only to specify their availability in router.js and to add to Sitemap (if necessary). To understand what forms are in the addon ember-flexberry-security can be viewed in its dummy-app [here](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/router.js) 
In fact, the form, route which starts with `i-c-s-soft-s-to-r-m-n-e-t-business-audit-objects-audit` - audit and `i-c-s-soft-s-to-r-m-n-e-t-security` to the authority. 
The application system must also add ranting and forms that are needed. 
Sitemap will look like [tak](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/controllers/application.js) 

You also need to localize admixed [locale of the addon polnomochii](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/locales/ru/translations.js) 
see `EmberFlexberrySecurityTranslations` 

To implement the logic of switching to the display form of audit is necessary to do so like this: 
[Hbs](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/templates/ember-flexberry-dummy-application-user-edit.hbs) 
see `{{action 'showAudit'}}` 

[controller](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/controllers/ember-flexberry-dummy-application-user-edit.js) 
see ShowAuditActionControllerMixin 

On bekende therefore need to publish using the ODataService Assembly audit and Assembly with authorization objects. You will also need to write a function for filtering of the provided values (default technological implementation yet). 

"%}


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}