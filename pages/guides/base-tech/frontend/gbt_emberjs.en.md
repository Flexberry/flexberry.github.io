--- 
title: Ember JS 
keywords: Programming, ember-cli, ember weekly, ember times, frontend 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_emberjs.html 
lang: en 
autotranslated: true 
hash: 57275222bdb57df7ed4db609fa616bfae8e5b2067890b2e277585ab299144897 
--- 

## Brief description 

**Ember.js** — free JavaScript framework web applications that implements the MVC pattern, designed to simplify the creation of highly scalable single-page web applications. The framework is used by companies like TED, Yahoo! Twitch.tv and Groupon. 

In December 2011 frame web apps, SproutCore 2.0 was renamed to Ember.js in order not to be confused with version 1.0. The authors of the project are Tom Dale and Yehuda Katz, Ember and only a Core Team of over 10 developers. 

**The basic principles** 
* **Routes** are one of the fundamental principles Ember.js and underline the importance of the URL in managing application state. The route object matches the URL that defines the current state of the application. Routes are defined in a single object router. 
* **Models**, each route corresponds to a model that contains data corresponding to a current state of the application. And despite the fact that it is possible to use jQuery to retrieve JSON objects, most applications still uses the library with the data model, for example, Ember Data. 
* **Controllers** used to add models some rendering logic. Previously standard practice was to inherit the controller from ObjectController if the model was a single object from the ArrayController - if the model was an array of records. Now these base classes are deprecated and the normal practice is to access a model property from Ember.Controller. 
* **Templates** written in the language of HTMLBars (HTML [handlebars](http://handlebarsjs.com/) = HTMLbars) and describe the user interface. Templates used to build HTML code of the app and allow you to embed a dynamically updated expression. 

## Links to materials for the study 

### Examples 

* [The application for a site lease of real property (eng)](https://guides.emberjs.com/v2.16.0/tutorial/ember-cli/) 

### Basic information 

* [Official documentation Ember.js](https://guides.emberjs.com/v2.16.0/) 
* [Official documentation on Ember-CLI](https://ember-cli.com/user-guide/) 
* [Ember.js — the ideal framework for web prilojenii](https://medium.com/devschacht/graham-cox-ember-the-perfect-framework-for-web-applications-970e817ded98) 
* [Basic structure](https://guides.emberjs.com/release/getting-started/core-concepts/) 
* Makeup application 
* [Routing](https://guides.emberjs.com/release/routing/) 
* [Controllers](https://guides.emberjs.com/release/controllers/) 
* [Templates](https://guides.emberjs.com/release/templates/handlebars-basics/) 
* [Components](https://guides.emberjs.com/release/components/defining-a-component/) 
* [Testing](https://guides.emberjs.com/release/testing/) 
* [Acceptance tests](https://guides.emberjs.com/release/testing/acceptance/) 
* [Basics of unit testing](https://guides.emberjs.com/release/testing/unit-testing-basics/) 
* [Testing controllers](https://guides.emberjs.com/release/testing/testing-controllers/) 
* [Testing routes](https://guides.emberjs.com/release/testing/testing-routes/) 
* [Testing models](https://guides.emberjs.com/release/testing/testing-models/) 
* [Testing](https://guides.emberjs.com/release/testing/testing-components/) 

### Detailed review 

* [Application dependencies](https://guides.emberjs.com/release/addons-and-dependencies/managing-dependencies/) 
* [Install ember-addon-s](gbt_embaddon.html) 
* [Install npm packages](gbt_embnpm.html) 
* [Installing bower packages](gbt_embbower.html) 
* [Vendor](gbt_embvendor.html) 
* [Catalog assets](gbt_embassets.html) 
* [Ember-cli-build](gbt_embclibuild.html) 
* [Configuring Ember.js](https://guides.emberjs.com/release/configuring-ember/configuring-your-app/) 
* [Basic structure of configuration file](gbt_embbaseconf.html) 
* [Settings depending on the environment](gbt_embsetting.html) 
* [How to import it to your classes and to read out settings](gbt_embiosetting.html) 
* [Ember.getOwner and properties of the instance of application](gbt_embgetowner.html) 
* [Routing](gbt_embrout.html) 
* [Controllers](gbt_embcontr.html) 
* [Templates](gbt_embtemp.html) 
* [Development Ember components](gbt_devcomp.html) 
* [Ember development services](gbt_devservic.html) 
* [Ember Data](gbt_emddata.html) 
* [Profiling Ember.js Apps](https://speakerdeck.com/selvagsz/profiling-emberjs-apps) 

## Subscription to news from the team and EmberJS community 

* [The Ember.js Times](https://the-emberjs-times.ongoodbits.com/) 
* [Ember Weekly](http://www.emberweekly.com/) 

## Software 

* [Ember-CLI](https://guides.emberjs.com/v2.16.0/getting-started/quick-start/) 

## Go 

* [Server development](gbt_backend.html) 
* [Course home page](gbt_landing-page.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}