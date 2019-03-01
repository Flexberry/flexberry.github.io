--- 
title: Business servers and wrappers 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, business servers, wrappers, stubs 
summary: the Basic principles of the business server and its wrapper 
toc: true 
permalink: en/fo_bs-wrapper.html 
lang: en 
autotranslated: true 
hash: 1d2190558f23fdb898cc1b2d6d7c15163acaec98da9497f33e51caea8e9f5206 
--- 

The business server has a set of methods. In `CASE` business server rendered [UML class with the installed attribute "businessserver"](fd_business-servers.html). The system can contain an arbitrary number of business servers. In General, the number of business servers and their methods is defined by an application developer. 

`Бизнес-сервер` class that contains the code of the business operations. 

`Заглушки, wrappers business сервера` — allow to perform business services in the following environments: 

* `COM ` (`COM ` wrapper) allows you to use `COM ` to ensure transaction for multiple calls to data services from within a single method, distributed transaction, the physical implementation architecture with application server (executing business server on a separate machine). 
* `IIS Hosted Remoting` (`IISHosted` wrapper) — allows access to the business server via `.NET Remoting` by `HTTP`, transport is `SOAP`. The use of this wrapper allows you to work in a mode where the application is a desktop user interface accesses a business server, located anywhere, via `WEB`. 

## features of use 

Additional information about the features of using a business server is available in the following articles: 

* [Call order business servers](fo_order-calls-bs.html) 
* [Testing custom operations in the data service (integration with the business server)](fo_user-operations-dataservice.html) 




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/