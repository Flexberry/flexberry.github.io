--- 
title: Filtering by detalam master. ConnectMasterProp, OwnerConnectProp 
sidebar: flexberry-winforms_sidebar 
keywords: Constraints 
summary: the examples show how to use OwnerConnectProp, ConnectMasterProp when building restrictions 
toc: true 
permalink: en/fw_master-details-filters.html 
lang: en 
autotranslated: true 
hash: b2b26d9ce52c54fb13f2c095abe7f247ebbfa4184c62b0a0a36c6cc054704743 
--- 

Filter by detalam wizard can be proillyustrirovana a few examples. 

## Diagram-example for 3 classes 

![](/images/pages/products/flexberry-winforms/subsystems/limits/diagramm.jpg) 


## Setting 
To filter out all Odottamattomasti for Salenatural, you must create a filter setup in the following way: 

* Create the filter configuration for Salenatural 
* To create a setting of detail for the filter settings 
* Specify the view Udostoveritsya 
* Setting detail specify OwnerConnectProp = Personality 
* Setting detail specify ConnectMasterProp = Personality 

Thus, Zaveniaghina and Odomdocument will be contacted in Person. 

## Diagram-example for 4 classes 

![](/images/pages/products/flexberry-winforms/subsystems/limits/diagramm2.png) 

"Example is taken from is "University"," 

## Setting 

To filter out all Obrazovatelnogo for Stroyrekonstruksiya, you must: 

* Create the filter configuration for Stroyrekonstruksiya 
* To create a setting of detail for the filter settings 
* Specify the view Obrazovatelnogo 
* Setting detail specify OwnerConnectProp = Zaveniaghina.Personality 
* Setting detail specify ConnectMasterProp = Personality 

Thus, Stroyrekonstruksiya and Obrazovatelnykh will be contacted through Zaveniaghina and Personality. 

## OwnerConnectProp, ConnectMasterProp 

Thus, in ConnectMasterProp specifies the path from detail and OwnerConnectProp - detalu from the object. The bundle is as follows: __Detail.ConnectMasterProp = Object.OwnerConnectProp__. 

It is also worth noting that the property OwnerConnectProp determines what objects are detaily. If the property is not specified (i.e. set to null), the ligament occurs __primary key__ (StormMainObjectKey). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}