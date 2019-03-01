--- 
title: Signing assemblies in Flexberry 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Assembly, sign the Assembly, utility 
summary: the Rules of signing assemblies 
toc: true 
permalink: en/fd_sign-assembly.html 
lang: en 
autotranslated: true 
hash: 573339d16e3970ff9b181514d65f049cdba858b424771118f17ec26a72751265 
--- 

In [advanced settings models](fd_project-customization.html) there is a check mark `Подписывать сборки`. After installation when you generate will be optionally generated files with the file extension .snk (if the project was generated previously, it will also be overwritten file `AssemblyInfo.cs`). 

To sign the assemblies from [Flexberry Designer](fd_landing_page.html) necessary utility [sn.exe](http://msdn.microsoft.com/en-us/library/k5b5tt23(v=vs.71).aspx), which you should copy to a folder [Flexberry Designer](fd_landing_page.html) (this utility can be found in subfolders of the folder `C:\Program Files\Microsoft SDKs\Windows` (this folder in the system may not be), it is better to be taken from the subfolder with the name "7.0"). 

About signing assemblies, you can also read the [msdn](http://msdn.microsoft.com/ru-ru/library/xwb8f617(v=vs.90).aspx). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/