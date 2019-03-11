--- 
title: updating the local copy of the object form 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Listed as using the method ResetDataObjectCopy to control local to the form a copy of the object 
toc: true 
permalink: en/fw_update-the-local-copy-of-the-form-object.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 3d579046cada0188a97fa02857e795ce103786f43186f293e19967f7074f3d96 
--- 

When the form is opened for editing, it creates a local copy of the object. The issue of conservation is set when the difference between this local copy and the edited data object. 

There are situations when the object in the database has changed while editing a shape, i.e. a local copy of the form is outdated. In this situation, you should update your local copy of the form by calling the method `ResetDataObjectCopy` independent form. As a parameter of the method is passed the object that will now be a local copy of the form. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}