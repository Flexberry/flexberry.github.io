--- 
title: Editing of datalow in a separate roat 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_groupedit-detail-in-route.html 
lang: en 
autotranslated: true 
hash: 10dd3ffcde86a297fb6da0644b38625b1ff93e27d669a07b062e27b9b0734c83 
summary: setting up templates and models aggregator and detail, serializer aggregator and controller of detail for enabling editing in a separate the router 
--- 

## Description 

Often detaily on [edit form](ef_edit-form.html) are adjusted directly in the control [flexberry-groupedit](ef_groupedit.html), but in some cases (for example, when working on mobile devices) there is a need to edit detail on a separate router. 

* Changes in the aggregator and detalaj persisted in the database only when you click "Save" on the form of the aggregator. 
* On the page of detail there are only two buttons: "Save" and "Close" (on the form there is no button to roll back the state of detail at the time of entry on the form). 
* Clicking on the "Save" button on the page of detail return to the aggregator page without saving changes to the database. 

## customize template aggregator and router 

In the [template](ef_template.html) edit form of the aggregator, the control `flexberry-groupedit` to define the following properties: 

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
}}{% endraw %}
``` 

* `rowClickable` - a flag that determines whether to treat depression is on the line. The default value is "false" to edit detail in a separate the router need to be set to "true". 
* `rowClick` - the action to perform when clicking the line. The default is set to "rowClick", to edit detail in a separate router need to act (action) with the given property name was defined in [controller](ef_controller.html) or [roat](ef_route.html) ("rowClick" is defined in the base of the route edit form). 

{% include note.html content="Current handler method `rowClick` implemented in such a way that if detail saved, then you will be redirected to Routh as `modelName/:id`, and if not saved, then `modelName.new` (respectively, [router](ef_router.html) must be configured to divert could pass properly)." %} 

* `editOnSeparateRoute` - a flag that determines whether edit detail in a separate route. The default value is "false". When you set the value to true changes the appearance `flexberry-groupedit`: disables the ability to edit directly in the control, clicking on the "Add" button now creates a new record and immediately redirected to route editing. 

## setting template detail 

To be able to edit detail in separate routes, the appropriate form must be created.

If it is assumed that the edit form of detail can be used not only with the forms of the aggregator, but the list form of datalow, to customize the display buttons "Save", "Delete", "Close", you can use the conditions presented in article [edit Form and creation](ef_edit-form.html). 

## controller configuration of detail 

Controller edit form of detail must inherit from "detail-edit-form" instead of "edit-form". In "detail-edit-form" there is additional logic that organizes the correct interaction between the forms of the aggregator and detail. If it is determined that the user has come in the form of detail not with the forms of the aggregator, it will execute the logic base controller "edit-form". 

```javascript
import DetailEditFormController from 'ember-flexberry/controllers/detail-edit-form';

export default DetailEditFormController;
``` 

## setting models aggregator and detail 

In the description [models](efd_model.html) aggregator it is important to check that bearing dealova [inverse-link](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations). 

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
``` 

To describe the model of detail important to check that indicated [inverse-link](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations) on the aggregator. 

```javascript
var Model = BaseModel.extend({
  ...
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
``` 

## configuring the aggregator 

It is important that [serializer](efd_serializer.html) for the aggregator and detail have been configured correctly. 

## Additional features of the implementation 

* Base controller for the edit form has a property `returnToAgregatorRoute`, which determines whether to adjust for a potential return to the previous route aggregator. By default, the base controller is set to "false". In the base controller detail this value is overridden to "true" (if necessary, in the application controller detail to change it back to "false" to prevent the return). Proofreading this flag happens in the roat [setupController](http://devdocs.io/ember/classes/ember.route#method_setupController). 
* For organization to preserve information between ranting and aggregator and detail used special service `detail-interaction`. This service is not intended for use in applied projects. 
* The basic route edit form myxinidae property `newRoutePath` where you determine how the router model determines the route for the new entry. Now the method is implemented as "currentPath '.new'". Potentially this method can be overridden in the application route. 

## Editing detailov in a separate router in Flexberry Groupedit preserving 

There is a mode `flexberry-groupedit`, wherein the editing of detail occurs on a separate route, thus, in the transition from get the aggregator to route detail, saves the aggregator, and the transition back is the preservation of detail. 

Configuration of this option is similar to the described above method. In the template of the control you need to set one additional flag `saveBeforeRouteLeave`. 

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
  saveBeforeRouteLeave=true
}}{% endraw %}
``` 

`saveBeforeRouteLeave` - a flag that determines whether to save the current model, and the transitions between the aggregator and detaila. The default value is "false". 

About how to operate the buttons on this dyelovoi the form described in the article [edit Form and creation](ef_edit-form.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/