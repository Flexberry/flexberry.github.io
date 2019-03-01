--- 
title: Performance interaction when editing features 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms), Textbooks, 
summary: Explanation of the terms "initiator" and "editor" in the context of metforminno interaction 
toc: true 
permalink: en/fw_interaction-between-forms-when-editing-objects.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 78e99751a0227a6ae7e84b62038fc23447b3abef4db4af42565aade04e83d8a2 
--- 

Basically, the interactions are by call the edit form from the form list (for example, when you open the object for editing), and Vice versa, with the edit form for the list form (for example, to select the associated object). Anyway," the essence is that from one form to another is sent to object data and edit it as a whole, or parts of it." Accordingly, in a specific (relative) metforminom the interaction of the one form acts as a __initiator__ editing, and the second, — as _the_editor__. 

* If an object is opened from the list on the edit form of the list is the initiator, and the edit form editor. In this case, obviously the object is passed to the edit in the edit form. 
* If editor to select a related object (craftsman) opens the list form, the edit form is the initiator, and the form list editor. 

The difference between cases 1 and 2 only in the fact that in case 2, the object is not edited as a whole, but only part of it.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/