--- 
title: the Wizard in the view (View) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View master, view, primary key 
summary: Rules for the use of craftsmen and their properties 
toc: true 
permalink: en/fd_masters-view.html 
lang: en 
autotranslated: true 
hash: 36830e70bba84db8c039058a281a2a2a319a2cc29cf309a034ad262f8b4ba2b4 
--- 

When to choose a representation of themselves [masters](fd_master-association.html), and when simply their properties? 

General recommendations are: 
* If you are creating a [view](fd_view-definition.html) that will be used on the list and no logic is not planned, it is enough to pull in a [view](fd_view-definition.html) attributes [wizard](fd_master-association.html). 
* If [view](fd_view-definition.html) will be used for some logic to have a [subject-master](fd_master-association.html), you need to get in [view](fd_view-definition.html). 

The fact is that when sucide [primary key](fo_primary-keys-objects.html) [master](fd_master-association.html) put down correctly anyway, but it is initialized in the first case under the simplified scheme. In addition, after the change [wizard](fd_master-association.html) and try to save it, an error may occur `NotLoadedProperties` because the wizard is missing in [list of uploaded masters](fo_definition-loaded-properties.html). 




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/