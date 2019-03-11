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
hash: a78412e7d78b825978aa86152747e663dc91a340f5cfe20693b0588d106696f7 
--- 

For the second [ObjectListView](fw_objectlistview.html), the constraint should be assigned before calling the base Edit method (in the overridden on the dependent list form). Otherwise there will be a race between the raising of the form and updating the list and assigning limitations.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}