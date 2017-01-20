---
title: Пример: использование стереотипа "typedef"
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/using_stereotypes_example.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Предназначение''': ознакомление с функциональными возможностями [Flexberry ORM](flexberry-orm-case-plugin.html) и демонстрация правильного использования стереотипа "typedef".
</td>
</tr></tbody></table></a>
</div>

# Использование стереотипа "typedef" в моделировании

Существует возможность определить собственный тип на диаграмме Flexberry. Для этого следует разместить новый класс на диаграмме и поменять его [стереотип на "typedef"](classes-with-stereotype--typedef.html).
Как результат, данный тип можно будет использовать как атрибут для свойств классов во всей стадии Flexberry.

Однако, этот тип является лишь псевдонимом, и используется только на уровне абстракции при моделировании.
Необходимо разрешить тип данного "typedef", указав соответствующие типы в C# и SQL на специальной [карте типов Flexberry](types-map.html).
К примеру, если посмотреть на класс String4000 (Flexberry sample repository, Entities diagram), то видно, что он маппится на классы System.String и VARCHAR(4000).

Как пример сгенерированного свойства, следует обратить внимание на Description класса CD:
```
        [StrLen(4000)]
        public virtual string Description
        {
           get {...}
           set {...}
        }
```

Полный список примеров кода Flexberry ORM находится в статье ["Примеры кода"](code-samples.html).
