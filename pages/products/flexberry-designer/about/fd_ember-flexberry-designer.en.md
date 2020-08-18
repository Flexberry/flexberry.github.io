---
title: Ember Flexberry Designer
keywords: Ember Flexberry Desinger, UML, design basics, modules, configure
summary: General information about Ember Flexberry Desinger
sidebar: flexberry-designer_sidebar
toc: false
permalink: en/fd_ember-flexberry-designer.html
lang: en
autotranslated: true
hash: 1839f4e92a02301dac58405063c5117a1418a196f1ec62b46c859f29c4a7f41e
---

**Ember Flexberry Designer** - CASE-tool with a web interface [Flexberry Designer](fd_flexberry-designer.html) is used for analysis and design of object-oriented systems, as well as generating prototypes of web applications based on the constructed models. Implemented using the framework [Ember.js](https://ru.wikipedia.org/wiki/Ember.js).

Ember Flexberry Designer is [product platform Flexberry](fp_landing_page.html).

`Ember Flexberry Desinger` can be used in two modes:

1. Create a separate service that allows you to work with different projects. In this case, a list of organizations (similar to [Github](https://github.com)) and the list of projects.
2. Working with an integral part of the application. In this case, Desinger loaded with the selected project-specific solutions.

## Basic concepts

`Метаданные приложения` – data describing the structure of classes and their relationships, the structure of related forms and other artifacts used in the design and generate a prototype application.

`Класс данных` class that corresponds to some domain entities. In the diagrams the UML has a stereotype, or may have a stereotype `implementation`. Instances of data classes are data objects. However, the term «qmc data object is periodically used as a synonym for «data class».

`Собственный атрибут` – attribute data class that is defined directly in the class.

`Мастер` class data from the multiplicity 1 or 0..1 in the UML with respect to» «Association between classes of data.

`Детейл` class of data associated with the aggregator (which is part of the» qmo) in the UML with respect to» «composition between classes of data.

`Представление` – named set of its own or inherited attributes of the class, as well as attributes of other linked classes (including themselves). Representations are used when receiving data from backend and, accordingly, when reading data from a database. Views are used when reading data on the application forms, as well as for explicit» «read data from within the source code of the application. On forms, basically, there are two kinds of submission:

* _L-predstavlenie - used to list формах;
* _E-predstavlenie - used in the forms editing component for detaylari.

`Списковая форма` – shape, which provides a user interface for displaying a list of data objects in a view and perform support actions with this list (search, filtering, sorting, display, data export, etc.). One class of data may involve several list forms. From the point of view of application metadata, list form is a class with the stereotype `listform`.

`Форма редактирования` - shape, which provides a user interface for creating or editing a data object in a given view. With one data class can be associated to several forms of editing. From the point of view of application metadata editing form is a class with the stereotype `editform`.
The application class is a special class in the application metadata, which stores common to all of the applications settings – for example, the navigation structure (menu), application configuration, etc. This class has the stereotype `application` in the application metadata.

`Бизнес-сервер` class to which the submitted methods,» «triggered automatically after the insertion, modification and deletion of data in a data object of a certain type before sending the appropriate changes to the database (similar to triggers in databases, but at the level of the application source code), and related business logic associated with the corresponding data class. From the point of view of application metadata business server is a class with the stereotype `businessserver`.

`Сервис генерации` – additional software on the server side, providing the possibility of generating prototype applications based on metadata of the applications.

## Modes Flexberry Designer

### With» «rigidly specified design

In this case, the project ID specified in your configuration before you build the application. In this mode, when you run the application at once will open form with a structure specified in the project configuration.

Project ID is specified in the file `environment.js` application:

```javascript
var ENV = {
    …
    APP: {
      …
      fdCurrentProjectContext: {
        singleModeStageId: '...',
      },
      …
    },
    …
};
```

### Without» «hard reference to the project

In this case, the users can work with multiple projects, grouped by organizations.
With the mode of operation without a rigid connection to the project, before work on the project, you must first select the organization, then, you must create or select a project and after a project is selected will open the form for editing the application structure.

### Organization

A list of organizations for which the planned establishment of the service. Within the organization there is a list of projects (solutions).

### Projects

The project is actually the solution application system. Each project has its own metadata structure (classes and forms).

### The application structure

The application structure is the basic form, which is the key to create the application. It consists of:

* Levogo dereva (list of classes). This is a list of all data classes of the applications, as well as all of the list form and edit form bound to the appropriate data classes (nested nodes). Classes of different types have different icon. The left tree allows you to create/delete/edit data classes and forms to the list of classes in the form of a table (similar to the Manager class in the desktop version of the designer).
* Pravogo dereva (the structure of the target application) to edit the navigation structure (menu structure) of the design application. In this tree you can create menus of arbitrary nesting by grouping menu items into folders» «at various levels. The navigation items (not counting the» «folders, which are used to group elements) can be list form, form editing, or menu items that refer to an arbitrary URL. Available operations:

 * adding a new navigation item (menu item) with reference to an arbitrary address URL;
 * edit properties (title and description) of the selected item навигации;
 * delete item навигации;
 * add new folders» «in the structure навигации;
 * change the order of navigation controls (move up and down);
 * save the navigation.

To add list forms or edit forms (from a list of all the possible forms that appear in the left tree), you must select the appropriate form in the left tree, then select the corresponding» «folder in the right tree and click on add form in the navigation structure.

* tabs allowing you to configure and edit properties of a class `application`, stages and forms.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}