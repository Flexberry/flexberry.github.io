--- 
title: Set of columns for placing the controls at the level of ideas 
sidebar: flexberry-designer_sidebar 
keywords: CASE Plugins, Flexberry ASPNET, Winforms Flexberry, plugin, performance, speaker, controls, example 
summary: Placing controls in columns on the model level 
toc: true 
permalink: en/fd_specify-column-controls.html 
lang: en 
autotranslated: true 
hash: 292d8802487d37ec7060858c0dc4b07c514386ccb67cf4a9fa6caf1d1250261a 
--- 

The opportunity came at the view level to set speakers to embed controls (#); 
for this in field "Path" in editovat view we need to specify the value in one of the following formats: 
1. #X 
2. #X(*) 
3. #X(Y*) 
4. #X(Z) 

X - integer number колонки; 
Y - column width in percent (relative), followed by the character '*'; 
Z - column width in pixels (absolute); 

Details: 
* columns with relative width are stretched automatically when you resize the form in proportion to their ширине; 
* width in percent for the relative column is calculated as follows: 
W<sub>abs_i</sub> = (W<sub>abs_global</sub> - SUM(W<sub>abs_j</sub>)) / SUM(W<sub>*_k</sub>) * W<sub>*_i</sub> 
* a description of the same columns for different attributes must be the same (i.e. it is impossible for one field to write #1(100) and for another #1); 
* record #X #X is equivalent to writing(*) and is equivalent to writing #X(1*); 
* if you specify one column with relative width ( % % , *) and the other with absolute, relative is taken with 100% of the remaining width 

**Example** 
Nickname path: #1 
Delaroderie path: #2(2*) 
Floors path: #2(2*) 
Host path: #1 
Pravica path: #3(100) 

Build a form with 3 columns, the last width of 100px, the rest of the space is divided by the width in proportion 1:2 






{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}