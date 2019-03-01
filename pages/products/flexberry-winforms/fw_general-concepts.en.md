--- 
title: General concepts 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: there are two basic concepts Flexberry Platform for the integration of parts of the user interface and business logic to a running system 
toc: true 
permalink: en/fw_general-concepts.html 
lang: en 
autotranslated: true 
hash: 69dc8d33ff19f7cb2d90c3c0eaf258dd59904a2fb8d564d04ff7cf9812fbae03 
--- 

In Flexberry Platform implementing a user interface, and integration of parts of the interface and business logic to a running system is based on two concepts: 

* Allocation is not dependent on the physical nature of the user interface. 

Provide a custom interface, you have to remember that it consists of two parts-depending on the type of input/output user interface, the so-called `UI`-dependent, e.g., common table on the basis of `WinForms` or client-server interface the browser — Web-based server `ASP.NET`. 

In this concept it is important to understand the logic implemented in `UI`-independent parts should not use a specific `UI`-dependent user interface. Thus, it should ensure the functioning of the custom logic under various types of user interfaces. 

* Flexible integration of parts of the interface (and business logic) by means of scenarios. 

The point of this is not to prescribe the forms of interaction, and calls business services "hard" code in the same forms, and have separately described the scenario of interaction, interpretiruya when the system is running (for details, see p. Writer in "the Axioms"). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/