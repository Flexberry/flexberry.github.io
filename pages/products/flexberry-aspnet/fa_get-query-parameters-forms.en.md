--- 
title: GET-request for forms 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_get-query-parameters-forms.html 
lang: en 
autotranslated: true 
hash: 7df82cf344f4b98ecde0e422d225dc5eca4752154b62dddd5356898a00e96140 
--- 

The GET-request for a LookUp form and the edit form in a modal window. 

Before in that and in other case we used a parameter called "LookUp": 

1. The basic editing form to determine which form opened in a separate window. 
2. In the script MasterEditorAjaxLookup for those occasions when, instead of technological LookUp form uses arbitrary list form: 
* based on this flag is set WOLV.LookUp (in technological form, the flag is not processed, it is considered that WOLV have a LookUp editor); 
* based on this flag is configured (for example ReadOnly) if the preview object. 

It is recommended to use a **different flags** for these purposes. 

{% include note.html content="In the code of applications is not necessary to explicitly use constants `LookUp` etc., as their value may change in the future. For special technological parameters from the request, use class WebParamController." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}