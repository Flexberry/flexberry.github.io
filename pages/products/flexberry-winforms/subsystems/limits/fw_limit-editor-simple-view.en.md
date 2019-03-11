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
hash: 6c613c56be849777de8f5cf162dc2f83fd1089ecb44fb719790c9dc4ff5065bb 
--- 

A simplified view of the editor allows you to set limits limits of simple functions, combined using "And". You can set the "OR" operation, but only for the same attribute: for this purpose it is necessary to impose some simple constraints on a single attribute - they will be combined via "OR". 

For Boolean attributes available functions "And" and "NO" corresponding to the test of TRUE and FALSE, respectively. 

Example constraints in a simplified form: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/scr02.jpg) 


This same restriction in standard form: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/scr03.jpg) 


__To create a limit, you need to:__ 

* In the first column, double-click in the cell. A form will appear «Selection field». 
* Select the fields for which you will need to create the constraint. If necessary, you can deselect the field by closing the window «Selection field».<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/17.png) 
* In the window "field Selection" click «to Return the value of the». In the Edit window «limitations of» is automatically added to the row with the specified fields. By default, the restriction will be installed «=»:><br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/18.png) <br> 
If necessary, you can remove the line with the limitation: 
highlight the line, press «Delete row». The line will be removed from the list. 
* Select from the list the desired View restriction. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/19.png) 
Depending on the kind of restrictions can appear/become unavailable fields for entering values. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/20.png) 
Yellow highlighted fields are not available for editing by form of restriction. White – editable, mandatory. 
* Click on the Value field. In the line appears on the right button ...» qmo. 
* Press the button ...» qmo. 
![](/images/pages/products/flexberry-winforms/subsystems/limits/21.png) 
* Select the value against which the restriction is created. 
* Click «to Return the value of the». 
* After the constraint editing for all fields is finished, press «Save and apply». The restriction will remain and will be applied. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}