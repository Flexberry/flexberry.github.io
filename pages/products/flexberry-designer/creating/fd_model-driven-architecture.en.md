--- 
title: a description of the methodology MDD 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, model, modeling 
summary: the Advantages of the modeling approach and especially its implementation on Flexberry 
toc: true 
permalink: en/fd_model-driven-architecture.html 
lang: en 
autotranslated: true 
hash: 8eb9a4c27fff73d0ef9d9577a033d85b973e63bd29448d60440afe614236bb48 
--- 

## Development-controlled models 

Development-driven models, (eng. model-driven development [MDD](http://ru.wikipedia.org/wiki/Model_Driven_Architecture)) is a style of software development in which models become the main artifacts of the development that generate executable code, and other artifacts. 

For construction applications it is necessary to create a formally precise model, which can then be automatically generated executable code. 

By the standards of the Object Management Group ([OMG](http://ru.wikipedia.org/wiki/Object_Management_Group)) create application consists of the following steps: 
1. First developed the domain model of the applications that are fully independent of the implementing technology. 
2. Then she transformered special tool in the platform-specific model. 
3. Finally, it is translated to source code in the appropriate programming language. 

## advantages of the approach 

This approach allows you to: 
1. To speed up the withdrawal the minimum viable product ([Minimum Viable Product](http://en.wikipedia.org/wiki/Minimum_viable_product)) on the market. 
2. To undertake routine programming: generation application framework, model classes, database, and so on. 
3. To resolve all disputes with the customer at the modeling stage. 

## features of the development at Flexberry 

Flexberry uses based on the above approach, however, implementation has its own unique features: 

1. Once the model is developed for a specific platform, there is no need to build a platform-independent model. 
2. The model lays the framework of the business logic, implement it by adapting the executable application code. 
3. There is a possibility of the connection of ready functional modules. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}