--- 
title: user interface customization 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: the following is the list of most commonly required improvements of the user interface, the resulting code generation 
toc: true 
permalink: en/fw_customizing-the-user-interface.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: d0000ca1a7bc3b3dfdc4ec636eb3dbfb2db54be6026a64288b1d3008eb2e4588 
--- 

After automatic generation of the application, its interface needs improvement. 

Items on forms and web pages need to install on your desired location and determine position. The appearance of elements to lead to a particular style, which adheres to the whole application, and so on. 

## Scenario refinement 

This section provides a list of the most frequently occurring modifications of the application after generation for Windows and Web applications. 

## Windows application 

For Windows-based applications come to the aid of 2 helpful class technology supported Flexberry: 

### FormTuner 

[The forms for application](fw_form-tuner.html) is a class that allows you to apply any settings for all forms in the application. 

### ControlProvider 

[ControlProvider](fw_control-provider-winforms.html) allows you to define your own controls for editing certain data types or modify existing ones. As defined in ControlProvider'e, these controls will apply in the universal form of editing in some other controls (for example, in [GroupEdit](fw_group-edit.html)). 

The generated forms need improvement in the following points: 

* The titles of the forms 
* Location of items on forms 
* Anchor elements on forms 
* The minimum and maximum form dimensions 
* The overall style of colors and fonts of the controls on the forms ("ControlProvider") 
* Localization 
* Setting a background image on each form ("FormTuner") 

### and 

There is a need to add in-app hotkeys and also adjust the order of items by pressing the <[Enter](fw_custom-form-tuner.html)> 

## Web application 

The generated Web pages need improvement in the following points: 

* Page titles 
* Alignment of elements on the pages 
* The overall style, colors and fonts of page elements 
* Page zoom 
* Localization 

# Cm. also 

[Visual logic](fw_visual-logic.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}