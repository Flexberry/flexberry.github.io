--- 
title: Processing of status and the download status object 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: Processing status and download status of the object data services 
toc: true 
permalink: en/fo_processing-status-condition-load.html 
folder: products/flexberry-orm/ 
lang: en 
autotranslated: true 
hash: 03e09262c527546e48e5b3068fad45d26d3c00521cfcecea0579c77435713e58 
--- 

When you upgrade in the store data object, any data service account statuses the following way: 

| **ObjectStatus**| **Storage**| **data Object**| 
|:----------------|:----------------|:----------------| 
| UnAltered| Not modify the data| does Not change (remains Loaded/LightLoaded and UnAltered)| 
| Created| Creates data in the store| Change status to Loaded and UnAltered| 
| Altered| Modifies data in the storage| Changing status to Loaded/LightLoaded and UnAltered| 
| Deleted| Removes data from the repository| the Object is deleted from the array of objects to process (null is returned in case of one object)|


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/