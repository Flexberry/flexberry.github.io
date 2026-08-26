---
title: Подписывание сборок в Flexberry
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, сборка, подписать сборку, утилита
summary: Правила подписывания сборок
toc: true
permalink: ru/fd_sign-assembly.html
lang: ru
---

В [дополнительных настройках модели](fd_project-customization.html) есть галочка `Подписывать сборки`. После её установки при генерации будут дополнительно сгенерированы файлы с расширением .snk (если проект был сгенерирован ранее, то также будет перезаписан файл `AssemblyInfo.cs`).

Для подписывания сборок из [Flexberry Designer](fd_flexberry-designer.html) необходима утилита [sn.exe](http://msdn.microsoft.com/en-us/library/k5b5tt23%28v=vs.71%29.aspx), которую следует скопировать в папку с [Flexberry Designer](fd_flexberry-designer.html) (данную утилиту можно найти в подпапках папки `C:\Program Files\Microsoft SDKs\Windows` (данной папки в системе может и не быть), лучше брать из подпапок, имеющих в имени "7.0").

О подписывании сборок можно также почитать в [msdn](http://msdn.microsoft.com/ru-ru/library/xwb8f617%28v=vs.90%29.aspx).
