--- 
title: Save to audit computable fields 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit, View (view) 
toc: true 
permalink: en/efs_not-stored-properties-and-audit.html 
lang: en 
autotranslated: true 
hash: e2e9a22b29b63b09ffe0ca711eb1fa343fb06b6d3d83b87719882639b94de847 
--- 

(((This article applies to [a new version of audit](fa_audit-web.html)))) 

When you define the fields that are recorded in the [audit](fa_audit-web.html), take into account visibility [Definition-submission|submission]. 

For example, there are fields `Фамилия`, `Имя`, `Отчество` and [calculated field](fo_not-stored-attributes.html) `ФИО`. [Calculated field](fo_not-stored-attributes.html) has signified, required all fields in the view, however, that the audit was only `ФИО`, enough to make the field `Фамилия`, `Имя`, `Отчество` invisible at the level of ideas. 


(((<msg type=note>For information about changing the master did not get into auditing, you should make invisible all the fields in the wizard. The invisibility of datalow in performance is also considered.</msg>))) 

(((<msg type=important> Because when writing audit data, only the values of fields from a formed object, for the correct recording of data on a calculated field not [definition DataServiceExpression](fo_not-stored-attributes.html), but also required [duplicate calculations in get'Tere properties](fo_not-stored-attributes.html).</msg>))) 





 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/