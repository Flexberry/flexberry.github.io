---
title: Подписывание сборок в Flexberry
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_sign-assembly-at-Flexberry.html
folder: products/flexberry-designer/
lang: ru
---
# Подписывание сборок
Как подписать чужую сборку, описано в статье [Как подписать чужую сборку](прикладные-системы_assembly--signing--without--code.html) (о подписывании сборок можно также почитать в [msdn](http://msdn.microsoft.com/ru-ru/library/xwb8f617%28v=vs.90%29.aspx)). 

Генерируемые Flexberry сборки можно подписывать. Для этого необходимо зайти в свойства стадии и во вкладке "Дополнительно" поставить галочку в пункте "Подписывать сборки".



[image||{UP(SignAssemblyAtCaseberry)}SignAssemblies.png]



В результате при генерации будут дополнительно сгенерированы файлы с расширением .snk (если проект был сгенерирован ранее, то также будет перезаписан файл `AssemblyInfo.cs`).

# sn.exe
Для подписывания сборок из Flexberry необходима утилита [sn.exe](http://msdn.microsoft.com/en-us/library/k5b5tt23%28v=vs.71%29.aspx), которую необходимо скопировать в папку с Flexberry (данную утилиту можно найти в подпапках папки `C:\Program Files\Microsoft SDKs\Windows` (данной папки в системе может и не быть), лучше брать из подпапок, имеющих в имени "7.0").

----