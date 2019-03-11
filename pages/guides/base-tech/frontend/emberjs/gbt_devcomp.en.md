--- 
title: Development of ember-components 
keywords: Programming 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_devcomp.html 
folder: guides/base-tech/frontend/emberjs/ 
lang: en 
autotranslated: true 
hash: 471ecf5c17a08c7c5d9d82e32aabab425e9298521c3d62b27e8994c93770e0e6 
--- 

## Brief description 

The contents of this page: 

* Generate components from ember-cli, the directory structure (components, templates/components) and files, their characteristics (what are referred to as components sure at least two words with a hyphen, ...) 
* The main hooks (init, this.$() didInsertElement possible initialisatie plugins on DOM elements-a, willDestroyElement for deinitialization, willDestroy) 
* Features components with wrapper and without (tagName is not empty, tagName=’) and the related accessibility of the unavailability of the choice of the markup elements within the component via this.$() 
* The connection code and template component 
* Handling DOM events and internal to the component action s in its code 
* Send action-s out through this.sendAction, a call to internal action s via this.send 
* Description and justification of the approach DDAU (Data Down Actions Up): components themselves do not change the transferred data and settings, but only reflect and react to changes in them, if necessary, modify the data, only the components of send out action, which are processed by the controllers/ranting and, if necessary, make changes to the data (component = component code the component template mixin for the controller or routes containing handlers action-s component). 
* Isolated testing ember-components by means of the integration tests in test framework to render components, and any markup, and then interact with it. 
* Testing features with the ui asynchronously running animation, and other asynchronously (Ember.run, the assert.async) 

## Go 

* [Back](gbt_emberjs.html)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}