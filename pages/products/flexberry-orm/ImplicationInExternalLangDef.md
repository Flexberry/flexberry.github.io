---
title: Функция импликации (funcImplication)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_implication-in-external-lang-def.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ExternalLangDef.dll.
* '''Предназначение''': Общее описание работы функции импликации (funcImplication) построителя [функций ограничения] [ExternalLangDef](limit-function.html).
</td>
</tr></tbody></table></a>
</div>

# Функция импликации (funcImplication)
funcImplication - функция [ExternalLangDef](external-lang-def.html) для задания логической импликации.

Импликация - функция двух логических операндов: предпосылки и следствия, может принимать следующие значения:



{| align="center" cellspacing="0" cellpadding="2" border="1"
! Предпосылка  !! Следствие !! Результат
|- align="center" class="tablerow"
| 0 || 0 || 1
|- align="center" class="tablerow"
| 0 || 1 || 1
|- align="center" class="tablerow"
| 1 || 0 || 0
|- align="center" class="tablerow"
| 1 || 1 || 1
|}

Логически, импликация "Если а, то b " равна "(не a) или b".

Например, ограничение вида: "Если кличка = барсик, то пол = мужской" приведет к выводу всех барсиков с мужским полом и всех (не барсиков).


# Пример
'''Все Ивановы Иваны и все не Ивановы'''
```
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcImplication,
                                                    langDef.GetFunction(langDef.funcEQ,
                                                                        new VariableDef(langDef.StringType, "Фамилия"),
                                                                        "Иванов"),
                                                    langDef.GetFunction(langDef.funcEQ,
                                                                        new VariableDef(langDef.StringType, "Имя"),
                                                                        "Иван"));
```
