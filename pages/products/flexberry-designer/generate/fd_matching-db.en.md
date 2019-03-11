--- 
title: alignment BD 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, DB, plugin, table structure, model 
summary: Rules creating and modifying a database with plugins Flexberyy Desinger 
toc: true 
permalink: en/fd_matching-db.html 
lang: en 
autotranslated: true 
hash: 07762867436270c6f4c7b323753ddc4544cd4311cf934dd084587dbdb522120a 
--- 

This function enables you to align existing database to the model: 
* Creates the database (if necessary). 
* Calculated the differences between [the database table structure and the structure corresponding to the object model](fo_storing-data-objects.html). 
* The script is generated, which causes the existing database to the model. 
* Enquire the user about the use of the generated script. 

In order to perform this function, should be in the plugin menu of generate database structure to select the "align database". 

{% include note.html content="Module align the joins in the database in accordance with a connection string specified in the stage settings for this module. If the server has no such database, the module `создаст ее`. This will be announced in the message window. Upon completion the user gets a window with the generated script, causes the database structure to the current model state. The user can apply this script may pre-modifying or abandon its use." %} 

## setting "don't delete existing tables" 

If the "[don't delete existing tables](fd_configure-ms-sql-generator.html)", the system will not attempt to remove the "extra" from her point of view on the table. This setting is suitable when the application database contains data optional bundled subsystems. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}