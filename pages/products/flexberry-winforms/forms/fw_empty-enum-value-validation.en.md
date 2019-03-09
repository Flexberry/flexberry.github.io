--- 
title: «and» Cockroaches enum type 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: there are ways to indicate that one of the values of an enumerated type is a value corresponding to a blank "empty" 
toc: true 
permalink: en/fw_empty-enum-value-validation.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: ba6c90145990d0f82e044fd3e396518fae80a9eac44457294535ed916292be49 
--- 

There are two ways to specify that one of the values of an enumerated type is a value corresponding» «empty, i.e. the value for which the cockroaches appear «and» a message saying I need to fill when you save the form. 

1. The value of an enumerated type is marked `Caption("")` with an empty string as a parameter. This functionality is standard for Flexberry. It should be recalled, to specify the Caption attribute with an empty value in the editor Flexberry you must use the symbol «~» (tilde). 

2. The value of an enumerated type is marked `EmptyEnumValue`. 

__Observations:__ 

1. To display the cockroach and the control input value when saving a property of a class must be marked with NotNull attribute(). 

2. In the current version of» «cockroaches do not appear in `GroupEdit` that is associated with the mechanism of display of list of values (using a built-in opportunity `FlexGrid`), but the control when you save the form will be implemented. If the display is using the standard ComboBox, cockroaches» «will be displayed correctly. 




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/