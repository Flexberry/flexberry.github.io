--- 
title: IAudit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit database 
toc: true 
permalink: en/efs_i-audit.html 
lang: en 
autotranslated: true 
hash: 36599c334b748d572ab534cb5c56f2eef5f2dad0babdb234c44a2e05d7cfa260 
--- 

# IAudit 
`IAudit` is an interface for logic [audit](fa_audit-web.html) (that is, the class that implements this interface will be responsible for recording audit information and proofreading). 

# ICSSoft.STORMNET.Business.Audit 
`ICSSoft.STORMNET.Business.Audit` is an implementation of the interface `IAudit` and is located in the Assembly `ICSSoft.STORMNET.Business.Audit`. 

# ICSSoft.STORMNET.Business.Audit.EmptyAudit 
In version after 28.01.2015 added class `ICSSoft.STORMNET.Business.Audit.EmptyAudit` that implements the interface `IAudit` and is located in the Assembly `ICSSoft.STORMNET.Business`. This class is a class stub that you can use to change the object fields was not in the database. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}