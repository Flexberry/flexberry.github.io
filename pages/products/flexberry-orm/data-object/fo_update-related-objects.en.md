--- 
title: Update related objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, DataObject, master, detail 
summary: Features update tatalovich and artisan objects 
toc: true 
permalink: en/fo_update-related-objects.html 
lang: en 
autotranslated: true 
hash: 3dc358a191c4b9746205d75cf393a57426ba5fbd009921ebfa3ce45f1acb8110 
--- 

It is possible that the preservation of some [data object](fo_data-object.html) may result in the preservation of related objects that clearly were not transferred for preservation. 

## Update metalowych sites together with the [cap](fd_key-concepts.html) 

If you are updating an object that has [Decalogue objects](fo_detail-associations-properties.html), those are also updated, if it meant their [status](fo_processing-status-condition-load.html). 

## Update the mechanics of objects with internal 

If changed [artisan object](fd_master-association.html), the update of the inner class object is updated also artisan. 



The above rules apply to all objects in the chain changes. So for example, if you have the inner class object, which has a artisan with detalyami, updates all. It is important to remember that `попадают only changed объекты`, i.e., [status](fo_processing-status-condition-load.html) which is designed to update.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}