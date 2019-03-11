--- 
title: Constraints with parameters for the programmer 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Restrictions 
toc: true 
permalink: en/fa_limit-parameters-developer.html 
lang: en 
autotranslated: true 
hash: 480f4d3bce8aca238c1795f651fa1b62cf3111063e83a6241cec72120514b02f 
--- 

The problem of applying constraints to parameters is necessary to substitute specific values of parameters in the restriction to generate a list of what is happening in the Page_Load [WOLV](fa_web-object-list-view.html). 
If you need parameter values WOLV displays a dynamically generated [input parameters](fa_limit-parameters-user.html), after entering the values that the list can be displayed. An additional complication is the fact that before the Page_Load WOLV uncertain, some of its properties (e.g., View). 

The flow limitation parameters: 

1. In the Page_Init WOLV to establish whether the current restrictions settings. If so, instead of a list display control `WolvParametersInputControl` that retrieves a list of parameters, each of which is dynamically generated control to enter the value. 
2. When clicking on the control button `WolvParametersInputControl` `Применить ограничение`, parameter values are substituted into the constraint. The list is displayed in a regular way. Entered values are saved in session. 

Ecosea implementation does not imply the existence of parameters in the restriction imposed by the programmer.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}