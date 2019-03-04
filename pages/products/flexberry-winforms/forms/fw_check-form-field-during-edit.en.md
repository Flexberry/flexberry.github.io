--- 
title: data Validation on the form during editing 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Comparative analysis of different variants of data validation on the form during editing 
toc: true 
permalink: en/fw_check-form-field-during-edit.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 78d32698155ae3ca4918e624088c7d67f84467ec2af938a858ceff400ab52b1b 
--- 
Data validation on the form during editing can be carried out by: 
* [an exception is thrown if an invalid entry in the method `set` the corresponding field of the object](fo_check-object-set.html); 
* define required fields on the class diagram using the attribute [`NotNull`](fo_attributes-class-data.html); 
* checks through the [`DataObjectErrorProvider`](fw_data-object-error-provider.html). 

| Admission | Advantages | Disadvantages| 
|--|--|--| 
| [An exception if an invalid entry in the method `set` the corresponding field of the object](fo_check-object-set.html) | allows to organize work in such a way that the user will not leave the field until, until the field value is not entered correctly | - to start the verification process requires that the focus may fall on the corresponding field 
| Define required fields on the class diagram using the attribute [`NotNull`](fo_attributes-class-data.html) | allows the model to define required fields <br> discreet flag of nezaposlenosti field | does not allow to define fields that are mandatory only in certain situations 
| Check [`DataObjectErrorProvider`](fw_data-object-error-provider.html) | allows you to quickly prescribe in the code the list of mandatory fields and users of the application will not be able to change it, <br> discreet flag of nezaposlenosti field | does not allow users to change the validation criteria on the form 
||| 

Other methods of data validation on the form is described [here](fw_edit-form-validation.html).


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/