--- 
title: Path generation 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, way, generation, source code 
summary: Rules configure the path generation for an application 
toc: true 
permalink: en/fd_generation-path.html 
lang: en 
autotranslated: true 
hash: 2b4dc212f3a9b407a6f92d100ec0bf5df10058c452e0a004e0ffe1648f98b4fe 
--- 

## Description 

The path generation determines where to generate the app. In relation to the path generated the app using the "Directory for source code" in [setting the stage](fd_project-customization.html). By default, the application will be generated in the folder with Flexberry. The target path generation will look like this: 
The path generation/directory for the source code 

## setup path generation 

To configure the path generation is necessary in the top panel click "Settings">"Path generation..." and select the desired path. 

![](/images/pages/products/flexberry-designer/generate/generation-path-setup.png) 

## Storage settings path generation 

Setting the path generation is stored in the application configuration file. 

```xml
<add key="GenerationPath" value="C:\Work.NET\CASE\CodeGen"/>
``` 
Thus, if the application will run with a different configuration file, you will need to define this setting again. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/