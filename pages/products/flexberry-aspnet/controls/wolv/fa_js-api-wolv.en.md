--- 
title: JavaScript jQuery API for WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP NET, JavaScript API 
toc: true 
permalink: en/fa_js-api-wolv.html 
lang: en 
autotranslated: true 
hash: 380f49759c70812b19133f895debf3795353969655dc7d04c8bb44b9af417cbe 
--- 

To manipulate [WOLV](fa_web-object-list-view.html) on the client side, use JS `WOLV API`, which is a jQuery plugin (`icsWolv`). 

## Methods 

| Name | Parameters | Description | 
| ------------ | ------------------- | -------- | 
| **Total** ||| 
| chooseDataObject | PrimaryKey object | Generate a click on the string object passed to this method. | 
| refresh | No | refresh the contents of WOLV. | 
| scroll | scroll the screen to WOLV. | 
| scrollToObject | PrimaryKey object | Scroll to the selected object. | 
| **Sorting** ||| 
| resetSort | No | Method to reset a custom sort WOLV. | 
| sort | `append` - add sort or re-install <br> `colNum` is the column number to sort by column WOLV. The sequence of shift parameters: `None -> ASC -> DESC -> None`. | 
| **Flags WOLV** ||| 
| getSelectedItems | No | Get the IDs of all selected items on all pages. "__If you selected all items on all pages (using the appropriate button, not manual selection), then come "'an empty array"'__". | 
| getSelectedAllObjectsValue | No | Allows to know whether the allocated all objects on all pages. Returns `boolean`. | 
| selectAllObjects | No | Mark all objects on all pages. | 
| unselectAllObjects | No | deselect all objects on all pages. | 
| selectObjectsOnCurrentPage | No | Mark all objects on the current page. | 
| unselectObjectsOnCurrentPage | No | deselect all objects on the current page. | 

Example usage: 

```javascript
// Update the contents of WOLV. 
$('#wolvid').icsWolv('refresh');
``` 

Generation click on the row: 

```javascript
$('#LookUpFormWOLV').icsWolv('chooseDataObject', '{4c6bf554-41d5-4cc6-b526-21bed9867a5a}');
``` 

## Events 

| Name | Parameters | Description | 
| ------------ | --------- | -------- | 
| `deleting.wolv` | `selectAll` - flag "prominence" of all objects on all страницах; <br> `selectedItems` - list of primary keys of selected objects данных; | Delete item (s) list. | 
| `editformclose.wolv` | `objectKey` - the primary key of the object that is edited on this форме; | close the form editing. | 
| `refreshing.wolv` | - | Update list. | 
| `rowclick.wolv` | `pk` - the primary key of the object corresponding to the row in which the clicked пользователь; | Click on the row of the list. | 
| `showaddingpage.wolv` | `addingUrl` - the URL of the creation form объекта; | display the page create a new data object. | 
| `showeditingpage.wolv` | `pk` is the primary key editable объекта; <br> `editingUrl` - the URL of the page редактирования; | edit page Displays object data. | 
| `showprototypingpage.wolv` | `pk` - primary key prototyperaptor объекта; <br> `prototypingUrl` - the URL of the page прототипирования; | Display forms of prototyping. | 
| `showviewingpage.wolv` | `pk` is the primary key of the viewing объекта; <br> `viewingUrl` - the URL of the page просмотра; | Display the view page of the data object. | 

Example usage: 

```javascript
$('.ics-wolv').on('showeditingpage.wolv', function() {
    alert('Была отображена форма редактирования объекта.');
});
``` 

#### Click on the line (rowclick.wolv) 

When using events `rowclick.wolv` you can get `PrimaryKey` object, the line which was made `click`: 

```javascript
$('.ics-wolv').on('rowclick.wolv', function(e, d) { 
     e.preventDefault();
     alert('primaryKey: ' + d.data.pk);
});
``` 

#### Client processing the upgrade list 

```javascript
// Subscribe to event updates from WOLV. 
// Event work when the user clicks the Refresh button on the panel WOLV. 
// The event also works when calling JS API method $('#wolvid').icsWolv('refresh'); 
$('.ics-wolv').on('refreshing.wolv', function(e) { 
    // Cancel update WOLV. 
    e.preventDefault(); 
    
    // Here you can describe your logic updates. 
    alert('Список не был обновлен');
});
``` 

#### Client removal treatment 

There is a possibility to subscribe to events before deleting items and, if necessary, cancel it. 

The handler receives two arguments: the event object and data. The object data field contains `data` flag `selectAll`, if it is `false`, also 
is passed an array of primary keys of selected objects `selectedItems`. 

Example usage: 

```javascript
$('#wolvid').on('deleting.wolv', function(e, eventData) { 
    if(eventData.data.selectAll)
        alert('Выделены все объекты на всех страницах');
    else
        alert(eventData.data.selectedItems); // Output a dedicated PC. 
    
    alert('Удаление отменено');
    return false; 
});
``` 

#### Client processing close the popup edit form 

By default, when you close the edit form open in a popup window, updates a list form with which the form was opened 
edit. You have the option to cancel this action, similar to deletion. You need to sign `WOLV` of the editing form 
(or `WOLV` whose id is specified in the parameter `WolvId` to it), `editformclose.wolv`. Passed to the handler in the event object will contain the field 
`objectKey` that stores the primary key of the object that is opened in a closed form. To cancel the default action by using `return false;` 
or `e.preventDefault();`. 

Example: 

```javascript
$('#wolvid').on('editformclose.wolv', function (e) {
    alert('Закрывается форма редактирования объекта с ключом ' + e.objectKey);
    alert('Обновления списковой формы не будет');
    return false;
});
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}