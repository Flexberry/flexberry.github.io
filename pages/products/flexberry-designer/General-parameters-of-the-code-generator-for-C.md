---
title: Общие параметры генератора кода для C#
sidebar: product--sidebar
keywords: Flexberry Designer
toc: true
permalink: ru/general-parameters-of-the-code-generator-for--c.html
folder: product--folder
lang: ru
---

Для того, чтобы отредактировать общие параметры генератора кода для C#, выберите в структуре репозитария репозитарий, проект или конфигурацию, непосредственно к которому (которой) подключен генератор кода и нажмите в панели инструментов кнопку "Редактировать":
![](/images/pages/img/Flexberry plugins/csharpcommprops.jpg)
Далее, выберите в свойствах модуль CSharp и нажмите кнопку редактирования свойств:
![](/images/pages/img/Flexberry plugins/csharpcommprops1.jpg)
В открывшемся диалоге Вы можете отредактировать общие параметры:
![](/images/pages/img/Flexberry plugins/csharpcommprops2.jpg)
Здесь:
* `DetailArrayConstructorDescription` - описание, `DocComment` конструктора массивов объектов, которое записывается при генерации в исходный код; 
* `DetailArrayDescription` - формат описания, `DocComment` класса массивов объектов; 
* `DetailArrayGetByIndexDescription` - формат описания (`DocComment`) метода, возвращающего объект данных из массива объектов; 
* `DetailArrayAddObjectDescription` - формат описания (DocComment) метода, добавляюшего объект данных в массив объектов; 
* `AttributeFieldFormat` - формат наименования приватного члена атрибутов; 
* `MasterFieldFormat` - формат наименования приватного члена, соответствующего мастеровому объекту; 
* `DetailFieldFormat` - формат наименования приватного члена, соответствующего массиву детейловых объектов; 
* `AggregatorFieldFormat` - формат наименования приватного члена, соответствующего агрегатору; 
* `DetailArrayTypeFormat` - формат наименования класса - массива детейловых объектов; 
* `TargetStormNetVersion` - целевая версия `Flexberry Platform`, для которой происходит генерация; 
* `CompileSubpath` - подкаталог, куда "складываются" скомпилированные сборки; 
* `StormNetBinPath` - в каком каталоге искать сборки `Flexberry Platform` для установки на них ссылок в генерируемых сборках, если пустое - каталог, где расположен `Flexberry.EXE`; 
* `ProgrammingBracesStartFormat` - формат строки комментария, - открывающей [скобки программиста](programmer-brackets.html); 
* `ProgrammingBracesEndFormat` - формат строки комментария, - закрывающей скобки программиста. 
