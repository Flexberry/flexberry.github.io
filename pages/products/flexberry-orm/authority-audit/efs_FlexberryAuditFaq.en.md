--- 
title: FAQ on audit 
sidebar: ember-flexberry-security_sidebar 
keywords: FAQ, Flexberry Audit 
toc: true 
permalink: en/efs_flexberry-audit-faq.html 
lang: en 
autotranslated: true 
hash: 5dbceb579426d185eef624391cdca26b025fab46298dbc659da1c543115c7f82 
--- 

# Audit really says nothing 
`Аудит really says nothing – I don't know where смотреть`. What you can do: 
# to Check that there is a code [initial audit application](efs_audit-setter.html). 
# to Check that there is no method call to disable auditing (`[AuditService.DisableAudit()](efs_audit-web-api.html)`). 
# to Check that the required operation is enabled for a class (for example, default change auditing disabled). 
# to Verify the correctness [of the settings in the config and classes](efs_keep-and-use-audit-settings.html). 
# to Verify that the audit [configured on your writing option](efs_i-audit.html). 
# ... 

# Audit does not write user 
`Аудит writes of the user who made the changes – don't know where смотреть`. What you can do: 
# to Check the settings [the current service user](fo_current-user-service.html) (the General algorithm of determination by audit of the current user is described [here](efs_not-stored-properties-and-audit.html)). 
# ... 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/