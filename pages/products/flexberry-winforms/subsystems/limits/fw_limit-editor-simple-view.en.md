--- 
title: a Simplified view of the editor limitations 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms), Limitations 
summary: the paper Describes a simplified view of the editor limitations 
toc: true 
permalink: en/fw_limit-editor-simple-view.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: f9c1953de3ff12fdb4ee0e935a946d6dd99f5baf90d17e2242e23e8ad9fc95ca 
--- 

A simplified view of the editor allows you to set limits limits of simple functions, combined using "And". You can set the "OR" operation, but only for the same attribute: for this purpose it is necessary to impose some simple constraints on a single attribute - they will be combined via "OR". 

For Boolean attributes available functions "And" and "NO" corresponding to the test of TRUE and FALSE, respectively. 

Example constraints in a simplified form: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/scr02.jpg) 


This same restriction in standard form: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/scr03.jpg) 


__To create a limit, you need to:__ 

* In the first column, double-click in the cell. A form will appear `Выбор поля`. 
* Select the fields for which you will need to create the constraint. If necessary, you can deselect the field by closing the window `Выбор поля`.<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/17.png) 
* In the window "field Selection" click `Вернуть значение`. In the window `Редактирование ограничения` automatically added the row with the specified fields. By default, the restriction will be installed `=`:><br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/18.png) <br> 
If necessary, you can remove the line with the limitation: 
highlight this line, click `Удалить строку`. The line will be removed from the list. 
* Select from the list the desired View restriction. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/19.png) 
Depending on the kind of restrictions can appear/become unavailable fields for entering values. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/20.png) 
Yellow highlighted fields are not available for editing by form of restriction. White – editable, mandatory. 
* Click on the Value field. In the right row button will appear `...`. 
* Click `...`. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/21.png) 
* Select the value against which the restriction is created. 
* Click `Вернуть значение`. 
* After the constraint editing for all fields is finished, press the button `Сохранить and применить`. The restriction will remain and will be applied. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/