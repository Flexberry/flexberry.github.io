--- 
title: Change width list column 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_olv-resize.html 
lang: en 
autotranslated: true 
hash: 74e665dad8ef2dec958598668c63ff6f5e7bd5097a1787e09f9cfeb4326ac01e 
summary: Principles of configuring and using properties change the width of the columns in the lists of items and their detailov 
--- 

Change the width of columns [list](ef_object-list-view.html) and [datalow](ef_groupedit.html) on the edit form is implemented according to General principles. 

To enable/disable changing of width HN of the columns, you must specify the control in the template property `allowColumnResize` (for normal applications this property has a default value `true` for mobile - `false`. 

{% include note.html content="despite the fact that mobile applications changing the width of columns available to include the flag for this type of application is not recommended." %} 

If the plugin was initialized correctly, after you download the control when you hover over the border between columns, the cursor changes its appearance and you can change the width of columns. 

It is possible to prevent resizing of individual columns using the [initial configuration of columns to display](ef_model-user-settings-service.html) in the property `columnWidths`. 

## Service user settings 

If [service user settings](ef_model-user-settings-service.html) is enabled, when loading the list control to read the saved setting width of columns, and changing column width - the setting is saved. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/