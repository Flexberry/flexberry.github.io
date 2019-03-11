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
hash: e955ef2f93dda7b020f1b888bb78441ef0c00f5079ad43849a6b65d151978737 
--- 

When you upgrade in the store data object, any data service account statuses the following way: 

| **ObjectStatus**| **Storage**| **data Object**| 
|:----------------|:----------------|:----------------| 
| UnAltered| Not modify the data| does Not change (remains Loaded/LightLoaded and UnAltered)| 
| Created| Creates data in the store| Change status to Loaded and UnAltered| 
| Altered| Modifies data in the storage| Changing status to Loaded/LightLoaded and UnAltered| 
| Deleted| Removes data from the repository| the Object is deleted from the array of objects to process (null is returned in case of one object)|


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}