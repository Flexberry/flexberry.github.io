---
title: Module 3. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module3-tasks.html
lang: en
autotranslated: true
hash: 88de0ca0ecc8fccbe2cde85bd53aa7c766342cf7a7ad6631d924fdb9201cd712
---

## The expected results of the job

The results of the assignment the student should be able to create an SPA application in JavaScript without using JS-frameworks:

* To configure the environment for a new project
* Understand the MVC architecture (MVP/MVVM) modern JavaScript frameworks
* Be able to use the language features to implement ES6 JavaSctipt projects
* Be able to work with asynchronous calls in JavaScript
* Be able to organize interaction with bakanda for obtaining application data
* To be able with loose coupling between application components using IoC/DI
* To be able to use design patterns
* Be able to write unit, integration and e2e tests
* Be able to generate autodocumentation to the source code of the project
* To be able nastaviti of server builds and deployment for the application

## The task module

You want to create odnostranichnik application (SPA) based on [open test tasks of the company Aviasales](https://github.com/KosyanMedia/test-tasks/tree/master/aviasales_frontend), JavaScript-frameworks:

* [Description of a client part prilozheniya](https://github.com/KosyanMedia/test-tasks/tree/master/aviasales_frontend#тестовое-задание-aviasales-frontend)
* [Description of server API](https://github.com/KosyanMedia/test-tasks/blob/master/aviasales_frontend/server.md)
* [Page layout](https://www.figma.com/file/4fQe1lEbo4DARjvNtaU0uJ/Aviasales-test-task)
* Images are for airlines is available at http://pics.avs.io/99/36/{IATA_CODE_HERE}.png (for example, http://pics.avs.io/99/36/SU.png)

### Requirements to the functionality of the application

* The application should be two routes: route for the cheapest flights and route for the fastest flights (with two "tabs" on the page layout). When you first load the app offers a route for the cheapest tickets. The current route must be saved in the LocalStorage of the browser, restarting the browser should open route, stored in LocalStorage (if the URL is not specifying one of the ranting).

* When you first load the app (or reload the current page) should be rendered all the tickets that came in the response from the server, the filters in the block "Number of transfers" needs to be reset. Uploading next set of tickets should be done automatically when scrolling to the bottom of the page, or by clicking on "Show more tickets" (it is enough to choose one of вариантиов; in the layout of the buttons no, if you want to add your own). After loading the next set of tickets to display only those tickets that match the current filter in the "Number of transfers".

* When you select filters in the "Number of transfers" list loaded at the moment with server tickets must be filtered in accordance with the selected filters. The selected filter needs to change the parameters of the current URL (i.e. filters for the current routes must be stored in the URL parameters for the route). Selected filters should be saved in the LocalStorage of the browser.

* The block "Number of transfers" to add a button "Load filters" (in the layout of the toolbar, add your own). Clicking on this button from the LocalStorage needs to be loaded last saved filters for the current route. If LocalStorage is not saved filters for the router, the button should be grayed out.

* When changing the route filter in the block "Number of transfers" shall be dropped. Thus, if for the roat was previously selected filter in the box, the button "Load filters" must be active.

* At the request of the student can be implemented more ranting - for example, route to view information about the ticket, route "About", etc. In this case, the corresponding additions to the page layout students are implemented independently at its discretion.

### Technical requirements for the application

1. Create a new Git repository for a project to configure the environment:

* To initialize the project using the package Manager `npm` (generate the file `package.json`), fill the title and description of the project. As a license type to specify `MIT`. Add file .gitignore (can be generated using [this](https://www.gitignore.io/) tool).
* You can adjust the formatting code using the library `Prettier`:
* Use single quotes in JS-code
* Limit string length to 100 characters
* Use trailing commas in objects, arrays, etc.
* Use 2 spaces for tabs
* Set linting JavaScript with libraries [`ESLint`](https://eslint.org/). [Configuration for `ESLint` should be based](https://github.com/airbnb/javascript/tree/master/packages/eslint-config-airbnb-base) on [the rules of writing JavaScript code from Airbnb](https://github.com/leonidlebedev/javascript-airbnb), all conflicting with `Prettier` rules should be [disabled](https://github.com/prettier/eslint-config-prettier). To run linting JS files should be created npm script with the name `eslint:js`.
* [Before-commit](https://pre-commit.com/) you must run [linting js code for indexed files](https://github.com/okonet/lint-staged). If linting fails, commit should not be executed.
* Source files of the project should be located in a folder named `src`.
* The project must be configured the build system of the project using the library [`Broccoli.js`](https://broccoli.build/):
* Assembled project must be located in the folder `dist`. Folder `dist` should have the following structure:
* File `index.html` should be in the root folder `dist` (copied from the folder `src`)
* The folder should contain `assets` collected CSS and JS files
* In the folder `images` have copied all of the images required for the application
* Needs to be configured [compile LESS code to CSS](https://github.com/gabrielgrant/broccoli-less-single)
* Must be configured [using ES6 modules](https://github.com/chadhietala/broccoli-rollup), as well as the [trancelaciya ES6 and ES7 code](https://github.com/rollup/rollup-plugin-babel)
* Must be configured in the [import code from npm packages](https://github.com/rollup/rollup-plugin-node-resolve) with [using ES6-design `import`](https://github.com/rollup/rollup-plugin-commonjs)
* Must be configured on the [linting JS-code](https://github.com/ember-cli/broccoli-lint-eslint), and [linting LESS files](https://github.com/billybonks/broccoli-stylelint)
* Must be configured on the [precompile](https://handlebarsjs.com/precompilation.html) templates [Handlebars](https://handlebarsjs.com/) (see below) using [shell-script in the build process](https://github.com/liftM/broccoli-shell). The precompile should be run in the `templates.js`, which should be placed in a folder `dist/assets` in the Assembly process. The precompiling of the scripts must be run using the command <br>`npx handlebars ./src/templates --extension=hbs-f ${outputPath}/templates.js`<br> where `outputPath` is the option parameter `command` of the plugin that should be used as a function.
* Needs to be configured [build in our production and development environments](https://github.com/joliss/broccoli-env)
* The development environment should be the map file to the resulting. JS file
* Must be configured to [automatic update of the browser](https://github.com/stfsy/broccoli-livereload) when you change the source files of the project in the development environment
* Must be set [compression and minification of JS-code](https://github.com/TrySound/rollup-plugin-terser) and [CSS files](https://github.com/shinnn/broccoli-clean-css) in a production environment
* Must be configured in the [generation of unique file names](https://github.com/rickharrison/broccoli-asset-rev) in a production environment
* Code the resulting Assembly should work in all browsers latest versions
* For development and production of Assembly in the file `package.json` should be added npm scripts with names `build` and `build:prod` respectively

2. In the repository of your project when working with branches should use Git Flow approach.
3. To create odnostranichnik application (SPA), JavaScript frameworks based on MVC design pattern (MVP/MVVM):
* Folder `src` source code of the application must have the following structure:
* Folder `controllers` - controller classes for the ranting
* Folder `models` classes of data models for the application
* Folder `styles` - LESS-styles for the application
* Folder `templates` - templates ranting with the use of a template engine [Hanlebars](https://handlebarsjs.com/)
* Folder `utils` - utility (auxiliary function) for the application
* Folder `views` - view classes for your application
* File `store.js` class local storage (Stora) downloaded from the server models (i.e., cache models)
* File `app-container.js` container classes ("factories") of the application
* File `router.js` class router application
* File `app.js` - entry point in the application Composition Root
* File `index.html` page containing "budovou" markup for all pages of the application and links to the collected files with styles and JavaScript code (from your `dist/assets`).
* Main application page (file `index.html`) should include the marking block, which will include dynamic content every get.
* Visit `index.html` in addition to references to the collected scripts and styles should also be a link to a Runtime version [Handlebars libraries from a CDN](https://cdnjs.com/libraries/handlebars.js).
* Must use a layout with a fixed width of the page content (you may want to make adaptive layout, including the option layout for mobile devices). To describe the style you need to use the preprocessor LESS (in the simplest variant is allowed in LESS-files to use the descriptions of styles in standard CSS).
* The app must be used in a modular system ES6.
* All the controllers, models, utilities, views, stor, container application and the router must be described in the form of ES6 classes and exported using the syntax for describing ES6 modules.
* In the container application needs to be registered classes for controllers, models, views, Stora, router and any other parts of the application (the so-called "factory"). The container must be a store of type "key-value", whose key is a string consisting of two parts: `тип_фабрики:имя_фабрики` (e.g. `controller:about` or `store:main`). The value associated with the key must be a corresponding class. The container application should be globally accessible. Register factories in the container application had to be done in the Composition Root of the application in the file `app.js`. The app container should allow:
* To register a new factory. Registration can be implemented using an explicit transfer of the class in the registration mode, and using [dynamic imports](https://v8.dev/features/dynamic-import#dynamic). In case of using dynamic imports must be adhered to the Convention of naming files in the project - corresponding class file should lie in a folder with the name of the type of factory and named the same as the registered factory (e.g., factory `controller:about` file must have the following to let in the project: `src/controller/about.js`). Also, when using dynamic imports the build process of the application must be configured so that all dynamic modules are copied when you build your app in a folder `dist/assets` with preserving the folder structure in which they are located. In addition, when you register a new factory has to be optional specified whether this factory is registered as a singleton (unless explicitly specified, the factory should be registered as singleton).
* Get instance of the factory. Method to get instance of the factory must take a parameter list to be passed to the constructor of the corresponding class factory. If appropriate, the factory was registered as a singleton, the container application needs to give the same instance of the factory (list of references to instances of factories, singletons also need to be stored in the container), otherwise it should be given class associated with this factory.
* Remove the registration of the factory. If you remove the registration of the factory that was previously registered as a singleton, have also removed the reference to the instance of the appropriate factory in the container if the factory was informed instanziierung.
* The router applications should be implemented based on the concepts outlined in [this post](https://dev.to/rishavs/making-a-single-page-app-in-ye-good-olde-js-es6-3eng) (also see the corresponding source code [demo](https://src-brsetrrnrp.now.sh/)). In the constructor of the router must be passed the block ID on the page ("content"), which is rendered dynamic content of the ranting. The router must be registered as singleton in the container application. Initialization of the router and registration of ranting should be done in the file `app.js`. The router should allow:
* To register a new ranting. Was ranting should be kept as a pair "key-value" where key is a path segment in the URL for the respective routes, and the value is the name of the route as a string (for example, `'/':'index'`, `'/about':'about'`, `'/post/:id':'post-edit'`).
* To provide a method for processing changes get. This method should manage the lifecycle of the page load for the corresponding routes. For ranting, which is not registered in the router, to "block content" should render a template named `error404` (rendering of templates is described in the task below). For the ranting must run the following steps in sequence:
* From the container application must be received by the authority of the controller by the name of the current route (the controllers should be registered as singletons in the container application). In the absence of registration of the corresponding controller in the container may be used in the controller "default", which can also be registered in the container (for example, in a factory with a special name).
* Method should be called `beforeModel` controller for performing actions before loading the data required for the route (for example, additional initialization of the controller when you change routes, or divert to another route).
* Must vasyutina method `model` controller, which loaded the necessary data to display in the template get. The result of the load data should be available through the property `model` controller.
* Method should be called `render` controller that controls the rendering of the template for the router.
* Get the name of the current get.
* Controllers should be a context to render the template specific routes (i.e., pages) supporting a one-sided binding data, and also contain methods for managing the lifecycle of the page. Each controller shall provide the following list of properties and methods (methods `beforeModel` and `model` should be declared as asynchronous, and accordingly to return the object `Promise`):
* Method `beforeModel` must contain actions that will be executed before each render routes, which is linked to a controller. In the simplest case, this method may return an empty promis.
* Method `model` should contain the action to create or download is needed to display the template get the data. The result of forming or loading data must be stored in the property `model` controller. Property `model` controller must be shaped in the form of the object. When uploading the data from external sources should be used to stor the application.
* Method `render` needs to control the rendering of the corresponding template for the current route parameters in the "block content" on the page. This method must call methods `render` and `afterRender` by the corresponding instance of the view, the buyout must be obtained from the container for the current route (the submission should be registered as singletons in the container application). As context to render the template into a method `render` presentation must be passed the current instance of the controller (i.e. all the properties of the controller will be "variables" that can be used in the template rendering).
* Methods for subscription and publication of events to be used in the processing of DOM events in the associated predstaleny. These methods must be implemented based on [template Publisher-Subscriber](https://monsterlessons.com/project/lessons/publishsubscribe-v-javascript). Each controller must be registered with subscribe to change event properties of the controller, which calls a method `render` controller to re-render the template of the route if the appropriate route is active (so that when data changes, the relevant information is automatically displayed in the page template). Event handlers for the page's DOM must also be registered in the form of subscriptions to the relevant event (the names of such events it is better to give uniform in the different controllers, i.e., to adopt cacote sort of agreement in their naming).
* Each controller can contain additional features which including can be calculated based on other properties of the controller or its properties `model` (for example, properties in the form of getters and setters). For those of computable properties that are implemented using getters and setters, and for which we need to support one-way data binding, you need the setters to publish a change event properties of the controller (for prerendering page).
* Pstrfset` method should allow to modify controller properties, which are not computable and not implemented respectively in the form of getters and setters to support a one-sided binding data. This method after the change is passed as the property parameter should publish a change event properties of the controller (for prerendering page). The property name passed to this method may contain the names of the nested properties (for example `model.address`).
* The submission must answer directly for the rendering of the template roat in "block content" and register event handlers for DOM elements on the page get. Each submission should provide the following list of methods:
* Method `render` should render template roat in "block content" based on the [pre-compiled](https://handlebarsjs.com/precompilation.html) Handlebars-template (the appropriate methods of obtaining markup available through the property `Handlebars.templates`). The name of the template must be obtained in the name of the current get. Method to obtain the markup should provide the context that is passed as a parameter to the method `render` and is an instance of the associated controller.
* PstrfafterRender` method must register event handlers for DOM elements on the page (clicks on buttons, etc.). The DOM event handlers should publish relevant events to the controller. The associated Controller must be passed to this method via a parameter.
* Templates of ranting (pages) should be a file with the extension `.hbs` (Handlebars templates). The names of the corresponding templates must match the names of the ranting that will be registered through a router. Duplicate the markup in the ranting can either be duplicated in the template or to be rendered by [reusable templates](https://handlebarsjs.com/partials.html) at the discretion of the student (reusable templates are required to make a separate folder inside the folder `src/templates` for configuration to precompile the templates when you build your app).
* Application model to be used as business objects in the application. Minimum, each model should contain a list of the required properties that are initialized through the constructor of the model. Optionally, the data model may contain a method `save`, which can be defined logic to save the model in the "external source" of data (not necessarily in the framework of this задания; the implementation of this method will probably also require the implementation of storage of various States in the model), as well as additional techniques depending on the needs of the business logic of the application. Instantiate the models and must be made through the store application. Each model should contain obyazatlno property `id` that should yavlyatsya unique identifier for the model of the appropriate type. The relationship between the models must be maintained on the basis of references to other models using their IDs. Models must be registered in the application container, but not to be singletons.
* The store must be a class to hold a cache of the models used in the application. The model can be obtained from "external sources" of data and generated at the application level (in order to avoid having to constantly load the same data from "external sources"). Stor should provide the following list of methods:
* PstrfcreateRecord` method should allow to instantiate the model and save it in the cache. As parameters it needs to make the type instantiable model (model name), and object list of properties to initialize the instance of the model. Getting model class must be done through the app container. Cache models must be a associative array or hash table as a set of pairs "key-value" where key is the name of the model, and the value is an array of models of the appropriate type.
* Method `deleteRecord` should allow you to remove the model from the cache. As parameters it needs to make the type instantiable model (model name) and the instance ID of the model (the value of the property `id`).
* PstrfpeekRecord` method must allow to obtain a model instance from the cache without downloading them from the "external source" data. As parameters it needs to accept the model type (the model name) and the instance ID of the model (the value of the property `id`).
* PstrfpeekAll` method must allow to obtain all the instances of the models from the cache without downloading them from the "external source" data. As parameters it needs to accept the model type (the model name).
* Pstrffilter` method must allow to obtain a filtered list of models from the cache without downloading them from the "external source" data. As parameters it needs to accept the model type (the model name) and a callback that will be used to filter the elements of the corresponding array of instances of models.
* Pstrfquery` method that will be used to retrieve data from "external source" data. As parameters it needs to accept the model type (the model name), options as an object to run a query on external data source (for example, URL values of request headers, etc.). This method should return promise, the successful completion of which is loaded from an "external source" data should be automatically installerats and stored in the cache store. If the model of the appropriate type with a specific `id` already existed in the cache, it must be replaced by one loaded from an "external source data" option. Optional at the discretion of the student function can also accept a third parameter that will be passed to the method implementation to load data from the "external source" (below for different modela or depending on different model specification could zagruzhatsya different from "external sources").
* Utilities must be a "pure function", which are used for auxiliary purposes in the application (for example, the function for parsing the current URL, etc.).
* Optional at the discretion of the student application can also be implemented using the [helpers for the Handlebars templating engine](https://handlebarsjs.com/#helpers) and application components. Helpers must be located in the folder `src/helpers`. Components of the application must be a separate visual application blocks with their execution context and state. For each component should be created a corresponding class in the folder `src/components`, as well as a block helper in the folder `src/helpers/components`. At the template level roat component must be embedded in an external in relation to it, the template (e.g., template, routes) using [block helpers Handlebars](https://handlebarsjs.com/block_helpers.html). The component class must be implemented by analogy with the controller (except for the possibility of self-rendering) and the instance of the component class must be present in the form of properties in the ambient context (e.g., the controller or the ambient component). The component should also allow you to subscribe to events on the DOM elements corresponding to the template, and publish the relevant events in the external in relation to it the context (e.g., the controller or the embedding component) if this component has not been registered subscriptions to event-name. Each component must also be registered in the application container in the form of a singleton.
4. The source code of the project should be written using ES6 features (classes, arrow functions, the destructurization, etc.). Each class must be placed in a separate ES6-module that is physically must be placed in the appropriate folder (`src/models`, `src/controllers`, etc.). If necessary, the application may be provided roditelskie classes for different parts of the application (controllers, views, etc.).
5. Connection of third-party libraries in the form of npm modules is allowed.
6. The project should include unit, integration and e2e tests are written using the test framework [QUnit](https://qunitjs.com/). Tests should be placed in a folder `tests` app and should contain subfolders `unit`, `integration` and `e2e` for the respective types of tests. Folder `unit`, `integration` must inside to duplicate the folder structure `src`, and the file names of the tests must duplicate the names of tested application modules with the Postfix `-test` (for example, a unit test for a controller `about` should have the following path: `tests/unit/controllers/about-test.js`). For objects stub and Moka ajax requests can be used by the library [Sinon.js](https://sinonjs.org/), as well as globally to create a container application to register the stub in the form of necessary factories. For e2e tests, you can use tools like [Puppeteer](https://github.com/GoogleChrome/puppeteer), [Selenium](https://selenium.dev/), [Cypress](https://www.cypress.io/), or any other at the discretion of the student. In addition, at the discretion of the student tools can be used to analyze code coverage by tests, for example, [Istanbul](https://istanbul.js.org/). The list of tests for an application does not necessarily have to cover all modules of the application, it will be enough to run a few sample tests for each type of tests.
7. Project source code should be documented using [JSDoc](https://jsdoc.app/).
8. The project should be posted on GitHub in a personal repository. For the project on GitHub must be configured with continuous integration and continuous deployment (CI/CD) using the [Travis CI](https://travis-ci.com/). The server build should include the following steps:
* Run tests for a project
* Start the project build, including linting JS files and LESS files. For commits to the master branch should be run in a production Assembly.
* Deploy the collected version of the app to GitHub Pages for branches master and develop. For the master branch deployment should take place only upon successful completion of all tests.
* Assembly of autodocumentary and its subsequent deployment to GitHub Pages for branches master and develop. For the master branch deployment should take place only upon successful completion of all tests.

**Support resources:**

1. [How to write the code under test](https://habr.com/ru/company/mailru/blog/267277/).
2. [Guidelines for writing clean code](https://habr.com/ru/post/424051/)
3. [Creating a SPA app in vanilla JavaScript](https://dev.to/rishavs/making-a-single-page-app-in-ye-good-olde-js-es6-3eng)
4. [Simple router in JavaScript](https://myrusakov.ru/js-simple-router.html)

## You can

- [Go to checklist of verification tasks](tds_module3-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
- <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with additional content module](tds_module3-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}