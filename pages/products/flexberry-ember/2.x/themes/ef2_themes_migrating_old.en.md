---
title: Transition to new themes
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: en/ef2_themes_migrating_old.html
folder: products/ember-flexberry/themes/
lang: en
autotranslated: true
hash: f7facf24c84e82dd879894e48251f8e64bfbaa3ba9ee1eb36acc6cfb0ff850d5
summary: Description of the transition to the new themes
---

## Manual for the transition to new themes

In order to be able to add their own custom themes in ember-flexberry should:

1. Copy to the root folder `ember-flexberry` file [theme.config](https://github.com/Flexberry/ember-flexberry/blob/develop/theme.config)
2. Copy the [theme.less](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/styles/theme.less) and the folder themes in `/addon/styles`
3. rename `/test/dummy/app/styles/app.scss` in `app.less` and add the top line `@import "src/semantic";`

4. Remove the line in the package.json

`"ember-cli-sass": "^5.2.0",`

and install the less compiler team `npm install --save-dev ember-cli-less`

5. ../Ember-cli-build.js add lines

```
   lessOptions: {  
      paths: [  
        'bower_components/semantic-ui'  
      ]  
    },  
    SemanticUI: {  
      import: {  
        css: false,  
        javascript: false,  
        images: false,  
        fonts: true  
      }  
    },  
```

6. In `theme.config` to register for the required items name blueSky. See an example here:

<https://github.com/Flexberry/ember-flexberry/blob/develop/theme.config>

Basically, the layout has changed on the main page, so we need to fix the file `/test/dummy/app/templates/application.hbs`
it should look like this:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/templates/application.hbs>

Since Semantic-UI does not allow you to connect 2 option, font icons, the easiest way to connect them via the vendor
you can download it from here:

<https://github.com/Flexberry/ember-flexberry/tree/develop/vendor>

you must copy the folder `fonts`, `font-icon.css` and folder `serviseImages` (some icons theme, only for theme blueSky) to `ember-flexberry/vendor/`, and also need to add in `ember-cli-build.js` import these files

```
  app.import('vendor/font-icon.css');  
  app.import('vendor/fonts/icons.eot', { destDir: 'assets/fonts' });  
  app.import('vendor/fonts/icons.otf', { destDir: 'assets/fonts' });  
  app.import('vendor/fonts/icons.svg', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/icons.ttf', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/icons.woff', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/icons.woff2', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.eot', { destDir: 'assets/fonts' });     
  app.import('vendor/fonts/crim.otf', { destDir: 'assets/fonts' });     
  app.import('vendor/fonts/crim.svg', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.ttf', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/crim.woff', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.woff2', { destDir: 'assets/fonts' });  
  app.import('vendor/serviceImages/close.png', {   
    destDir: 'assets/serviceImages'   
  });  
    app.import('vendor/serviceImages/close-hover.png', {   
    destDir: 'assets/serviceImages'   
  });  
   app.import('vendor/serviceImages/Plus.png', {   
    destDir: 'assets/serviceImages'   
  });  
    app.import('vendor/serviceImages/Minus.png', {   
    destDir: 'assets/serviceImages'   
  });
```

Also, to change the lettering font of menu icons and color of the pressing need to `/test/dummy/app/controllers/application.js` method `actions.toggleSidebar` looked like this:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/application.js>

If the menu consists not only of the main points but there are sub-items, you need to add scripts to menu items disappeared, it is necessary to `/tests/dummy/app/controllers/sitemap-node.js` add method `actions.subMenuEl` to make it look like this:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/sitemap-node.js>

Add action icons and hide the submenu in `ember-flexberry/app/templates/sitemap-node-content.hbs` and `/ember-flexberry/app/templates/sitemap-node.hbs` the result should be like this:

<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/sitemap-node-content.hbs>
<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/sitemap-node.hbs>

Correct buttons to remove and add, and changes, for this we need to add buttons classes `ui-add-ui-delete ui-change`:

<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/components/groupedit-toolbar.hbs>
<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/components/flexberry-lookup.hbs>

Addition:

* In the templates list forms to replace `<div class="ui {{state}} row form">` on `<div class="row">`
* In all files to replace `hidden-menu` on `hidden`.
* Controllers/application.js replace `.sidebar.icon.text-menu-1` on `.sidebar.icon.text-menu-show`, `.sidebar.icon.text-menu-2` on .`sidebar.icon.text-menu-hide`.
* Templates/application.hbs make substitutions as in the previous paragraph. Then add in the block `<div id="example" class="pusher">` - `<div class="ui form {{objectlistviewEventsService.loadingState}}">` <https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/templates/application.hbs#L38>
* In the theme.config to do this <https://github.com/Flexberry/ember-flexberry/blob/03751c63e852ed2fde8c7519022a17cbc9d284d1/theme.config#L21> from 21 to 73 string
* Controllers/application.js – insert action toggleSidebarMobile and service objectlistview-events, also change the toggleSidebar. (changes for controllers/application.js - <https://github.com/Flexberry/ember-flexberry-security/commit/ebfd645c00834dba426b97c00bf0cd6eb25e5807#diff-b02194543e6fcf5c7caff7b1a2f3d0f4> ;
* Templates/application.hbs – render the ui-sidebar in the beginning of the page that he was out of the blocks with content (by default, you need to move above the block with the class `bgw-fix`), under a block with class `pusher`, to create a block with class `ui main container` and transfer `{% raw %}{{outlet "modal"}}{% endraw %}`. (changes to templates/application.hbs - <https://github.com/Flexberry/ember-flexberry-security/commit/ebfd645c00834dba426b97c00bf0cd6eb25e5807#diff-33efff51085b97215b246f6fcfd9e0fe> ;
* If you use a mobile template application.hbs, then it need to follow steps from the previous paragraph for the ui sidebar and replace action toggleSidebar on toggleSidebarMobile;



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}