--- 
title: Copy / paste branch restrictions (saving and loading from file)) 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Restrictions 
summary: Describes the ability of the editor restrictions on saving and loading of branches restrictions from a file 
toc: true 
permalink: en/fw_copy-paste-limitation-branch.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 2fe31118e9f2cc29b4617ca9a1e64731cde0a7399768b0b8d407dda509ab8576 
--- 

## Description 

There is a possibility of saving and loading branches of constraints from a file 

To save: you need to get up to the required thread limit (the top of the branch must be a function) in the extended constraint editor in the main menu along with floppy to select from the drop down list _"to Maintain branch restrictions in the file.."_. 

To download the branch constraints: Stand up to the required empty space in the advanced editor restrictions. In the main menu next to the icon at the download restrictions to choose from the drop down list _"Load branch restrictions from a file.."_. In this case, if the type of the function in the selected branch restrictions at odds with the current, it will be wydano a message and the insert will not be performed. If the variables (object fields in a function) do not exist for this object (view), the branch will not be added. 

### Current limitations 

1. The branch must be a function. It is impossible to save separately to a specific value (constant) 

2. Branch should not contain settings because the settings are stored separately. 

3. Operation saving and loading restrictions are only available from the advanced editor limitations


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/