---
title: Service user settings
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, Flexberry ORM ODataService
toc: true
permalink: en/ef2_model-user-settings-service.html
lang: en
autotranslated: true
hash: 1462879fcca8669848c58279436906d44ee093bf24ab823aa45b8d75c3a27dac
summary: the description of the service user settings
---

## Description

Service user settings (`user-settings-service`) is designed for configuring, editing, saving and applying custom settings.
At the moment the service allows you to konfigurirovanii the display mode of the component `flexberry-objectlistview`.
In the future, a possible extension of the list of the supported component.

The introduction of this service is standard for the Ember way:

```javascript
userSettingsService: Ember.inject.service('user-settings-service'),
```

## Enabling and disabling service save user settings

By default, the service save user settings off (the interaction with the database occurs).
In this mode, all installed in the user interface settings are only saved within the current session or until you reboot one of the pages.

In order to enable the service, you need in the config Ember-application to set: `config.APP.useUserSettingsService`.

```javascript
module.exports = function(environment) {
  ...
  var ENV = {
    ...
    APP: {
      // Here you can pass flags/options to your application instance 
      // when it is created 

      useUserSettingsService: true
    }
  };
  ...
  return ENV;
};
```

## Override the method of obtaining the current user

The important point is which user sets (or looked up) the current setting.

The name of the current user is returned by the method `getCurrentUser`, which by default returns an empty string (in case system users are not included).

If you set up some kind of system user, you must override this method to return the correct user.

On the server, the user name is defined as: `System.Web.HttpContext.Current.User.Identity.Name`.

## Configuration for flexberry-objectlistview

For using the service user settings component `flexberry-objectlistview` must contain the following attributes:

```hbs
{% raw %}{{flexberry-objectlistview
  ...
  componentName="..."
  colsConfigButton=true
  ...
}}{% endraw %}
```

* `componentName` - a mandatory attribute used to anchor user settings. It must be unique within a single displayed page.
* `colConfigButton` - flag opredeli or not to display buttons Online configuration settings by the user.

Saves all of the configuration, including setting the sort column.

## Configuration for flexberry-toggler

In order for [flexberry-toggler](ef2_flexberry-toggler.html) applicable custom settings, it should be in the template routes, the heir `list-form` and `edit-form` `ember-flexberry`.

In cases where these conditions cannot be met, need custom settings to upload service. For example:

```javascript
this.get('userSettingsService').setDeveloperUserSettings(this.get('developerUserSettings') || {}).then(...)
```

## Work with the service

For Online service designed two buttons. ![](/images/pages/kaf/menu1.png)

* First click to edit the current (DEFAULT) settings of the user.
* The second provides the work (creating, updating, applying, removing) named user settings.

These buttons are displayed when the attribute is set `colsConfigButton=true`.

When you click on the first button displays the interface for adjusting the parameters of column display components `flexberry-objectlistview`.

![](/images/pages/kaf/list0.png)

Each line interface identifies the configuration of the column with the name specified in the third column "column Name".

* The first column of the table is used to configure to show/hide the specified column.
* The second column allows using the keys Up/Down to change the location of the column.
* The third column stores the name of the column being edited.
* The fourth column specifies the sort direction of a column when it is displayed (without sorting, in ascending order, in descending order).
* The fifth column defines the priority in which columns are sorted.
* The sixth column allows you to specify the column width. A Checkbox located in the last row of the column allows you to enable/disable define the width of the columns when displayed.

*Note*: to make the column width of the column display the column width is sufficient to invoke the configuration window using the mouse to shift one of the column borders.

After the configuration mode display the columns the user can save the settings as

* the default setting by pressing Apply (Use). When this is an update of the current page on the specified configuration parameters.

![](/images/pages/kaf/list1.png)

* Named setting by entering setting name and press Save (Save). The system displays a message about saving the specified settings, reloading the page is not performed.

![](/images/pages/kaf/list2.png)

When you click on the second button provides the user with the interface for working with named settings.

![](/images/pages/kaf/menu2.png)

The functionality of the item "New setting" is the same as the above-described functionality for editing the current setting.


* When you select settings from the item "Apply" this setting becomes the current (DEFAULT) setting.
* When you select settings from the Edit item displays the interface for editing settings.
* If you select from "Delete" the specified setting is deleted.
* Select "default Setting" leads to the establishment of the settings specified by the developer and reloading the current page.


The "Display setup" is displayed only in development mode (variable `config.ENV.envirinment` in the file `/config/envoironment` matters develop) and installs as software developer initial values of display parameters (see below).

![](/images/pages/kaf/info.png)

## Set the initial values of the developer

The developer is able to set the initial configuration of columns to display in the description file routing `/app/routes/...js` in the property `developerUserSettings`:

```javascript
import ListFormRoute from 'ember-flexberry/routes/list-form';
export default ListFormRoute.extend({
modelProjection: '...',
modelName: '...',
developerUserSettings: ...
});
```

In standard customizing, format properties `developerUserSettings`

```javascript
developerUserSettings: { «componentName»: { } }
```

Where» «componentName - component name specified in `flexberry-objectlistview`.
For the router belonging to the subclass of router `routes/list-form`, a valid description display only one component.
For other classes of routers in the later description of initial configuration of several components.

If a developer has to predetermine the order and visibility of columns, direction and sort order, their width, it can describe properties of» «componentName:

* `colsOrder` - the order and visibility of columns. Format:

```javascript
[{ propName: «columnName», hide: false}, { propName: «columnName», hide: true}, ...]
```

* `sorting` - direction and sort order. Format:

```javascript
[{ propName: «columnName», direction: "asc",  propName: «columnName», direction: "desc", ...]
```

* `columnWidths` - width columns. Using the properties `fixed` you can prevent changing the width of individual columns. If you want to forbid to resize the column or column tools menu, the» «columnName need to specify `OlvRowToolbar` or `OlvRowMenu` respectively. Formats:

```javascript
[{ propName: «columnName», width: ... }, { propName: «columnName», width: ... }, ...]

[{ propName: «columnName», fixed: true }, { propName: «columnName», fixed: false }, ...]

[{ propName: «columnName», width: ..., fixed: true }, { propName: «columnName», width: ..., fixed: false }, ...]
```

componentName» «may include any set of specified properties.

To simplify initialization properties `developerUserSettings` the developer can obtain the current settings by selecting the "Show settings" from the drop-down menu (see above).
There are two format description properties componentName» qmo:


* As an object:

```javascript
«componentName»: { colsOrder: [ ... ],  sorting: [ ... ] ,  columnWidths: ... }
```

* As a string:

```javascript
«componentName»: `{  colsOrder: [ ... ],  sorting: [ ... ],  columnWidths: [ ... ]}`
```

The first option is preferable if you manually set the properties `colsOrder`, `sorting`, `columnWidth`.

When you copy settings from "Show setup" string constants are enclosed in double quotation marks ("). Because the current standard requires JSCS with the description of object of framing of the string constants in single quotation marks ('), Copy/Paste more convenient the second option descriptions properties» «componentName as a string.

## Sort and specify the column widths displayed in the interface table

The user has the option to set the sort order and column widths directly in the interface displayed in the table.

When you click, hold the left mouse button on the column boundary and drag the column sizes will change dynamically. Change sizes when the service save the user's preferences are recorded in the database.

When you click the left mouse button on the column header the current collation reset the current sort order of the selected column will change cyclically in the order: increase, decrease, no sorting.

When you click the left mouse button on the column header with the Ctrl withheld the current collation is not sbrasivaya. Adds the specified sorting for the selected column.

If you change the column sort order is reloading the page with the specified collation. The sort order ukazyvaetsya parameter sort page address.

## Levels of user settings

The current mode is based on the following levels:

* mode settings specified разработчиком;
* mode settings set by the user in интерфейсе;
* sort mode specified in parameters the URL of the page.

The parameters of each subsequent level block the previous settings.

## Configure ODataService

If the server part is connected [Flexberry ORM ODataService](fo_orm-odata-service.html) for correct operation of the service requires that, in addition to the application Assembly facilities, additionally passed down the names of the two technological assemblies: "UserSettingsService", "NewPlatform.Flexberry.ORM.ODataService".

```csharp
config.MapODataServiceDataObjectRoute(new[] { "SomeApp.Objects", ..., "UserSettingsService", "NewPlatform.Flexberry.ORM.ODataService" });
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}