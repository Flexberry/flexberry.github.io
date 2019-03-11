--- 
title: Interpretation of a Boolean value NULL 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Flexberry Reports, database, constraints 
summary: Features interpretation of a Boolean value 
toc: true 
permalink: en/fo_interpretation-boolean-null.html 
lang: en 
autotranslated: true 
hash: 5fc62451eba0a918787c5e75bfcfc7cf6d24e57556c1dd450748d067ee0aef96 
--- 

[Attribute class](fo_attributes-class-data.html), declared to be of type bool, in the DB is of type bit can have 3 values: 

* 1 if explicitly writing the attribute value `True`; 
* 0, if explicitly writing the attribute value `False`; 
* NULL if it did not record. 

NULL value has pitfalls: the user interface, such a value is displayed as `False`, i.e. the checkmark, the user will not be exposed. However, the NULL value is ignored when queries [service data](fo_data-service.html) if it imposed a [limit](fo_limitation.html). 

Boolean attribute called `Исполнено`. When filtering is set to sign `Исполнено`, it finds all who have "Full = 1". 

When you need to find who the sign is not set, is searched on the basis of "NOT (Filled = 1)", while NULL values are ignored (this is normal behavior of the DBMS). To explicitly find anyone with a sign is not installed, it is necessary to write "NOT (is non-empty(Executed))". 

In the end the contradiction: for the user interface and in his mind there are only 2 values, and when you filter the data the system works with 3. 

To avoid this, you can: 

* sew processing technology. It should be understood that there are systems that use all three States. Of life such situations are, as a rule, from imports, when the database gets a NULL deliberately. 
* at the application level, the tasks, and in DB [set default values](fo_features-dafault-value.html). Thereby eliminating the possibility of inadvertent entry of NULL values in the database System. And this is the most correct way, because you always have to know that the object which means. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}