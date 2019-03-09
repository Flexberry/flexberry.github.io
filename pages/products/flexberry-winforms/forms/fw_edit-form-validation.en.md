--- 
title: data Validation 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Designer, Flexberry Winforms 
summary: For windows-applications lists the level of data validation (while editing, when you save the form, the business servers), given their comparative characteristics, and links to articles describing how to implement these urovi check 
toc: true 
permalink: en/fw_edit-form-validation.html 
lang: en 
autotranslated: true 
hash: a4ef6a6f27b1ea6d63b827d6e43c3f77338cd3169b133db8c9973f51d1ff893d 
--- 

Data validation occurs in several stages. 

`Web`: 
* First, you need to the maximum client-side validators (jQuery). These validators should block the form submission to the server if something is not filled or filled incorrectly (so we reduce server load and increase responsiveness and interactivity of the application). If it is a simple `required`, you can use [ASP.NET-validator](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.basevalidator(v=vs.100).aspx) enabled, the client handler then jQuery-validators is not necessary. The main thing to specify [`ValidationGroup`](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.basevalidator.validationgroup(v=vs.100).aspx). 

* After client-side validators need to use the server and place it on the form [`ValidationSummary`](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.validationsummary(v=vs.100).aspx)you need to display a beautiful error messages. 

* The third line – [business server](fo_bs-wrapper.html). It is also necessary, as the logic may be not only on the form and test it, as a rule, should always. Form, unlike the rest potential of» «visualmill, the response from the business server has to transform in clear view and show to the user. It is recommended to handle different from the standard way event save the object in the method `Save` and `SaveAndClose` through block `try-catch`. In `catch` to catch the message from the business server (preferably typed) and inform the user about the problem. This can be done either through a message to [WebErrorBox](fa_exception-handling.html) or mark on the page or something. 

`Windows`: 
By analogy with the scenario for the Web: 
* Check [during editing](fw_check-form-field-during-edit.html) on the form. 
* Check the [save](fw_check-form-field-during-save.html) of the object. 
* Check in [business server](fo_bs-wrapper.html). 

{% include note.html content="to generate code edit form (or list), it is necessary in properties of a class with the [stereotype EditForm](fd_editform.html)) select `GenerateDependedForm`." %} 

| Admission | Advantages | Disadvantages| 
|--|--|--| 
| [during editing](fw_check-form-field-during-edit.html) | allows you to quickly inform the user about incorrect values in some field | does not allow to fully test the complex relationships between the fields of the object and other objects 
| [while saving](fw_check-form-field-during-save.html) | allows you to test complex relationships between fields of an object since the check happens at a time - test starts only when you try to save the form 
| [business server](fo_bs-wrapper.html) | allows you to test complex relationships between fields of an object, as well as links with other objects - test starts only when you try to save the form 
||| 

General comments on the implementation of the on-screen edit forms can be viewed [here](fw_forms-recommendations.html). 



## Total 

The preferred option is the implementation of checks __at all levels__. 

## Scenario refinement 

* Configure [checking mandatory fields](fw_check-not-null-fields.html). 
* Add the limit number of characters for string and controls a valid range for numeric controls. 
* To put [default](fo_features-dafault-value.html). 
* Add a check [unique offer](fo_unique-data-check.html), [date ranges](fo_func-between.html). 












 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/