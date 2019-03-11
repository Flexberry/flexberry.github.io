--- 
title: Copy data objects 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM methods 
summary: Methods of copying the data and their characteristics 
toc: true 
permalink: en/fo_copying-data-objects.html 
lang: en 
autotranslated: true 
hash: 3928fc86c30cc78e898e3f26d887ff3498605c1fa7a21fd4d21ba7b5b993e8c2 
--- 

## Method CopyTo() 

To get a separate instance of the data object, you can use the method `CopyTo` declared in [DataObject](fo_data-object.html). 

### CreateDataObjectsCopy = true 

This is a flag that enables copying related objects (datalow and masters). 

*And the master are copied as objects, and not just put a link to them.* 

* If you copy an object that has detaily, then they too will make a copy. 
* If the copied object detail will create a full backup and its aggregator (with all its detaylari). 

### PrimaryKeyCopy 

Manages copy [primary key](fo_primary-keys-objects.html) (*valid on your key, keys, master keys metalowych objects*). 

* `true`: primary keys will be taken from the source object 
* `false`: primary keys are re-generated 

### copy of the data objects 

Another important feature of this method is that is copied and __link__ [your data](fo_data-object-copy.html) (is a single object copies the data to 2 instances of the data object). And the flag `PrimaryKeyCopy` is not affected - in copies of the data will remain the primary key, which was to be copied. 

**We do not guarantee adequate condition copy of the data after the method call `CopyTo`. Use the methods `InitDataCopy` or `clearDataCopy` after calling `CopyTo` .** 

### Context-dependent cache data objects (DataObjectCache) 

Reproduction occurs with the use of the object cache data. This means that with repeated reference to the object, verify its availability in the cache (the key is "the type of the primary key of the object") and only after this, is either create or use already created is found in the cache. 

**If the task is to copy the data object is one to one without using the cache (for example, if 2 masters of the same type with the same key has been downloaded with different data sets and the need to prevent the widespread use of the first of them), it is recommended to apply the method `CopyToObjectWithoutCache`.** 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}