---
title: Performance ember-application
sidebar: flexberry-ember-2_sidebar
keywords: performance, bottleneck, perforator, perf, hbs
toc: true
permalink: en/ef2_performance-tips.html
lang: en
autotranslated: true
hash: 71c792bed7f5872b9bf29611df91f4b9f7ea7de7119cd2b29a71a0d62e82cded
summary: Tips on designing and developing with the use of EmberJS relating to application performance
---

## The Golden rules of app development for EmberJS

1. When designing components or forms you need to use "deferred rendering" blocks that the user sees. If you have a settings panel, filters, etc. are initially hidden to the user units - implement [hbs-template](https://guides.emberjs.com/v2.12.0/templates/handlebars-basics/) so that their rendering was performed by event show this block to the user.
2. With prolonged (more than 0.3 sec.) download some block - use ["Semantic UI component Loader"](https://semantic-ui.com/elements/loader.html), meaning the download. The user will see that the system is not "stuck" that process is, it means the system works. In addition, the shift feel of the visual component on the Loader and back gives the effect of "time compression" to the user, for short intervals, the user feels that the system forces him to expect.
3. In the design and implementation of the hbs templates if possible, you should avoid nested in each other component and helpers. As a rule, it is possible to develop the hbs template, which will contain only html components and basic helpers, such as conditions, loops, binding. Deep nesting of components is guaranteed to degrade application performance.
4. For properties that participate in the hbs-template, it is important to design the logic of their changes: every change in this field may lead to prerendering subfolder of the template. The correct life-cycle, which works optimally like this: you first performed the initialization properties, which can change several times, then started the rendering (see 1 and 2). In the worst variant of change of properties will occur in the [hooks related to the rendering components and pages](https://guides.emberjs.com/v2.12.0/components/the-component-lifecycle/), which will lead to prerendering template (EmberJS for this situation also generates a browser console Warning message about the performance issues).
5. Use the profiling tools performance Ember applications.

## The profiling tools performance Ember-application

* [Perforator](https://chrome.google.com/webstore/detail/perforator-ember-performa/hfdilejiecmablifdkololalnbbmdcdb) - an extension to Google Chrome that shows the rendering time ember component.
* Service `perf` - Perforator similar functionality, but built into ember-flexberry (can be used in browsers other than Google Chrome).
* Console browsers - most modern browsers allow you to profile JavaScript code.

### Service perf

For ember apps, which installed [addon ember-flexberry](ef2_landing_page.html) inclusion of service `perf` is in the file `config/environment.js`.

``` js
module.exports = function(environment) {
  // ... 
  var ENV = {
    // ... 
    APP: {
      // ... 
      perf: {
        enabled: true,
      },
      // ... 
    }
  };
  // ... 
  return ENV;
};
```

After switching it on, you need to restart `ember serve` to configuration changes have been applied. If everything is correct, then in the browser console when rendering hbs-template will start to appear record of rendering time.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}