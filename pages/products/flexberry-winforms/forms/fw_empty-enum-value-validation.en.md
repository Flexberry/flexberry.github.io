--- 
title: `Тараканы` and an enum type 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: there are ways to indicate that one of the values of an enumerated type is a value corresponding to a blank "empty" 
toc: true 
permalink: en/fw_empty-enum-value-validation.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 30d95fe7245d5fe4b7383fdfe88b2e865c21d58839e8e458660a26dad0910134 
--- 

There are two ways to specify that one of the values of an enumerated type is a value corresponding `пустому`, i.e. the value for which the displayed `тараканы` and a message saying I need to fill when you save the form. 

1. The value of an enumerated type is marked `Caption("")` with an empty string as a parameter. This functionality is standard for Flexberry. It should be recalled, to specify the Caption attribute with an empty value in the editor Flexberry you must use the symbol `~` (tilde). 

2. The value of an enumerated type is marked `EmptyEnumValue`. 

__Observations:__ 

1. To display the cockroach and the control input value when saving a property of a class must be marked with NotNull attribute(). 

2. In the current version `тараканы` do not appear in `GroupEdit` that is associated with the mechanism of display of list of values (using a built-in opportunity `FlexGrid`), but the control when you save the form will be implemented. If the display is using the standard ComboBox `тараканы` will be displayed correctly. 




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/