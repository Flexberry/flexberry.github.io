--- 
title: Functions for creating backup and change history 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry Designer, Flexberry ORM, plugins 
summary: the purpose of the History plugin, create bekap, compare the stages, the restoration stage 
toc: true 
permalink: en/fo_history-plugin.html 
lang: en 
autotranslated: true 
hash: 7a10d669559de8fa2bebab044d407b89124e06c89c10a78616bede08bac21311 
--- 

Function `создания бэкапа` and `истории изменений` designed to store the history of changes of Stages with the ability to recover a stage at the moment of saving. 

## Capabilities 

These functions allow you to perform following set of operations that change records stage: 

* Creating a backup stage 
* View information about the generated bekapai 
* View history of changes stages Конфигурации; 
* View the history of changes to a particular stage. 
* Restoration stage 
* Recovery in the current stage 
* Restore to a new stage 
* The establishment in any existing stage 
* Compare two records of history 
* Comparing the history record with any existing стадией; 
* Comparing recorded history with the current state of the stage. 
* Connectivity expansion module 

## Connection of extension module 

The connection is made according to the following algorithm: 

* Register the plugin in Flexberry Designer; 
* Open menu `Настройки - Модули`; 
* Click `Создать`; 
* Specify the library path `History Case Tool plugin.dll`. 
* Add a plugin to the repository: 
* Open the properties of the repository by selecting the menu item `Репозиторий - Edit свойства`; 
* Under Modules, click `Создать`, adding line in column `Модуль` to choose `HistoryPlugin`; 
* To save the changes. 

{% include note.html content="More on modules and their connection can be viewed in this article [Modules extend the functionality](fd_flexberry-plugins.html)." %} 

This will bring up a menu Stage `HistoryPlugin`: 

* `История changes стадии`; 
* `Создать бекап`. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/add-item-history.png) 

Also, along with the buttons `Сохранить` and `Сохранить and закрыть` button will appear `Сохранить creating бекапа`, which saves stage and automatically creates a backup for it. 

## Creating backups 

When you create a backup entry is created on the state stage at the moment. 

{% include note.html content="as the process of creating a backup for big stages takes considerable time, it is not recommended to create backups every time you make changes to the Stage." %} 

To create a backup in two ways: 

* Select the menu item Phase `Утилиты to Create бекап` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/create-backup.png) 

* Click `Сохранить creating бекапа` in any form, linked to stages (any chart, class, etc.). 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/save-backup.jpg) 

{% include note.html content="a Backup is created for the whole stage at once, regardless of the form on which the button was pressed `Сохранить creating бекапа`. For example, if you press this button on the edit form class, it will create backup for the whole stage, which is tied to this class and not for class and not for the chart." %} 

## View information about bekapai 

Created backups available in the display form of backup that can be opened in two ways: 

* Choose the menu item Configuration - `Утилиты - History stages конфигурации`. 
Opens the display form of backups at all stages of the selected Configuration. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/history-configuration.png) 

* Select the menu item Phase `Утилиты - History стадии`. 
Opens the display form of backup concrete Stage. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/add-item-history.png) 

On the form `История changes стадии` presented with a list showing: 

* The creation date of the backup 
* User that created the backup 
* Transacted with the stage (Created, Changed, Deleted) 
* PrimaryKey stage 
* The name of the stage 
* Journey to the stage 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/history-change-stage.jpg) 

## the state Restore phase 

During the recovery stage of history is uncompressing and deserializing data stored in the Content field. This restores all of the classes, associations, views, and charts the stage. The recovery stage can be made in any of the existing stages, or a new stage. When restoring to an existing stage, it is first removed, and then in the database with the same name and the key is restored from the history stage. 

To restore from backup stage, it is necessary for the display form of backups click `Восстановить in текущую`. You can also restore a backup to any other, including new stage. 

{% include note.html content="If you restore the Stage current stage is deleted and a new one is created, so the list of backups, you receive another entry. A distinctive feature of these records is the lack of information in column `Имя стадии`."%} 

### recovery Algorithm in the current stage 

* Go to the form `История changes стадии` 
* Select the menu item Phase `Утилиты - History стадии` 
* Open a form `История changes стадии` 
* Select the line of the backup from which to restore the state of the stage 
* In the drop-down list on the toolbar, select `Восстановить in текущую` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/restore-current.jpg) 

* In splashes the warning, click `ОК` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/warning-window.jpg) 

### Algorithm state restore stage, which has been removed 

* Choose the menu item Configuration `Утилиты - History stages конфигурации` 
* Open a form `История changes стадии` 
* Select the line of the backup from which to restore the state of the remote stage 
* In the drop-down list on the toolbar, select `Восстановить as...` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/restore-as.jpg) 

* In the opened window specify the name with which the stage will be restored. Further, there are several options: 
* Leave the name as the stage before deletion. The stage will be restored with the old name 
* Enter a new name. Stage will be created with the new name 
* Select an existing stage from the list (the data in the selected stage will be overwritten!) 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/choose-stage-name.jpg) 

* Click OK. 

## Comparison stages 

### Comparison of changes between the backup stage and the current stage status 

*Only available from safe backups of a specific stage.* 

The comparison algorithm backup stage and the current stage status: 

* Go to the form `История changes стадии` 
* Provide backup for comparison by clicking on the line right button мыши; 
* Choose from the drop-down list item `Сравнить with текущей` 
* Will open a window with the log comparison and the window with the result of the comparison 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/compare-current.jpg) 

### Comparing two backups one stage/configuration 

For comparison, two stages are required on the form viewer backup 2 backup to select and click `Сравнить`. 
This way you can compare two backup one stage, or backups of two different stages in the same configuration. 
Available from safe backups of a particular stage and view the backup configuration stages.

Algorithm to compare two backups: 

* Go to the form `История changes стадии` 
* Select the first backup by clicking on the line with the right mouse button 
* To allocate a second backup, holding `Ctrl` and clicking the right button of the mouse on the other line 
* Choose from the drop-down list item `Сравнить` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/compare-two-backups.jpg) 

* Will open a window with the log comparison and the window with the result of the comparison. 

### Comparison backup stage with the current state of any other stage 

Allows you to compare the saved state of the stage, with the current state of another stage in this Repository, even if it is in a different Configuration or a different Project. 
Available from safe backups of a particular stage and view the backup configuration stages. 

The comparison algorithm backup stage with the current state of any other stage: 

* Go to the form `История changes стадии` 
* Choose the backup that you want to compare (only selected 1 row) 
* Choose from the drop-down list item `Сравнить` 
* In the opened window, from the list of all available in this repository stages to choose the one with which we will compare. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/compare-stage.jpg) 

* Click OK 
* Udut open a window with the log comparison and the window with the result of the comparison. 

### Displaying results of the comparison 

The results of the comparison are displayed on the form: 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/showing-comparison-results.jpg) 

On the form displayed between which stages are compared. A signed path for each stage format `Репозиторий/Project/Configuration/Стадия`. 

Below are available for viewing 5 tab: 

* The result of the comparison (displayed when you open it) 
* Objects are 
* Objects only stage 1 
* Facilities vary 
* Objects only stage 2 

#### Tab is the result of the comparison 

* A pie chart displays information about the percentage matching, differing and unique for each stage class (`Classes`) and views (`Views`). 
* Blue color on the diagram shows classes and the submission are presented only in the first compare phase (`Стадия 1`). Detailed information on these is available on the tab `Объекты only stage 1` 
* Yellow color on the diagram shows classes and the submission are presented only in the second compare phase (`Стадия 2`). Detailed information on these is available on the tab `Объекты only stage 2` 
* Red color on the diagram shows classes and views that are in both compare the stages are the same. Detailed information on these is available on the tab `Совпадают` 
* Blue color on the diagram shows classes and views that are in both compare the stages, but have some differences. To obtain detailed information about them and see what exactly are the differences on the tab `Совпадают` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/comparison-chart.jpg) 

#### the Objects Tab vary 

Information about classes and performances, which are in both compare the stages, but have some differences. 
Tab is a table in which the column `Тип объекта` see 2 drop-down list with classes (`Classes`) and views (`Views`). In column `Наименование объекта` opposite folded written a number of classes and ideas which differ. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/number-different-objects.jpg) 

To expand the list `Classes` and select one of the found classes, such as `Дом`. 

In the lower window there are 2 tabs: `Поля` and `Ассоциации`. 

##### Tab `Поля` 

Allows you to see which fields have class in stage 1 and stage 2. Shown a stereotype, a parent attribute values. Items that are not in one of the compared phases are highlighted in red. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/field-compare.jpg) 

##### Tab `Ассоциации` 

Allows you to see what this class is wizards, detaily, aggregators, roles, cardinality of relationships. Items that are not in one of the compared phases are highlighted in red (in this example, the class `Дом` no differences in associations). 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/association-compare.jpg) 

To expand the list `Views` and select one of the found concepts, such as `Заказ.ЗаказЕ`. 

PstrfСвойства` tab allows you to look at what properties has an idea in stage 1 and stage 2. Items that are not in one of the compared phases are highlighted in red. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/views-compare-attribute.jpg) 

PstrfДетейлы` tab allows you to see what Detaily has an idea in stage 1 and stage 2. Items that are not in one of the compared phases are highlighted in red. In this example, the performance `Заказ.ЗаказЕ` has no datalow under `TestStandWin`. 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/views-compare-detail.jpg) 

#### Tab Objects only stage 1 

Shows objects that are only in the first of the compared stages. You can view detailed information about them, similar to that described tabs «Objects vary».

#### Tab «Objects only stage 2 

Shows objects that exist only in the second of the compared stages. You can view detailed information about them, in the same way as described for the tab «Objects vary». 

#### Tab «Objects match» 

Shows the objects that are in both compare the stages and are identical in all respects. You can view detailed information about them, similar to that described tabs «Objects vary». 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}