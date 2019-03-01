--- 
title: Assignment LimitFunction for the second ObjectListView 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), Limitations 
summary: See how to avoid problems when assigning LimitFunction for the second ObjectListView 
toc: true 
permalink: en/fw_assigning-limit-function-second-objectlistview.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 341324a3fc12b702f08f677949d1b9f791df576abf10ba535c7935f48898a9ae 
--- 

For the second [ObjectListView](fw_objectlistview.html), the constraint should be assigned before calling the base Edit method (in the overridden on the dependent list form). Otherwise there will be a race between the raising of the form and updating the list and assigning limitations.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/