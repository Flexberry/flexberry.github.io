--- 
title: Device builds 
keywords: ember 
sidebar: flexberry-gis_sidebar 
toc: true 
permalink: en/fg_content-builds.html 
folder: products/flexberry-gis/ 
lang: en 
autotranslated: true 
hash: c9531990719dea2c3aeff2e94dbae3d65fa5803540b3123c29011426f1480779 
--- 

## General information 

Have GIS add-ons builds nastroeny on GitHub-e, so that the application bildade by Travis and diploidy to branch gh-pages. 

## the Algorithm setup builds 

1.It is necessary to add in the **config** your prilozheniya [next cod](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/config/environment.js#L165): 

```js
// Change paths to application assets if the build has been started with the following parameters: 
// ember build --gh-pages --brunch=<brunch-to-publish-on-gh-pages>. 
if (process.argv.indexOf('--gh-pages') >= 0) {
  var brunch;

  // Retrieve name from brunch process arguments. 
  process.argv.forEach(function(value, index) {
    if (value.indexOf('--brunch=') >=0) {
      brunch=value.split('=')[1];
      return;
    }
  });

  // Change base URL to force application assets paths to be relative. 
  ENV.baseURL = '/' + ENV.repositoryName + '/' + brunch + '/';
  ENV.locationType = 'none';
}
``` 

2.To add to the project file `.travis.yml` with [next content](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/.travis.yml). 

```yaml
---
language: node_js
node_js:
  - "6.10.3"

sudo: false

cache:
  directories:
    - node_modules

env:
  - EMBER_TRY_SCENARIO=default
  - EMBER_TRY_SCENARIO=ember-release

matrix:
  fast_finish: true
  allow_failures:
    - env: EMBER_TRY_SCENARIO=ember-release

before_install:
  - npm config set spin false
  - npm install -g npm@^2

install:
  - npm install -g phantomjs-prebuilt
  - npm install -g bower
  - npm install
  - npm rebuild node-sass
  - bower install

script:
  - ember try:one $EMBER_TRY_SCENARIO test

after_success:
  - test $EMBER_TRY_SCENARIO == "default" && test $TRAVIS_PULL_REQUEST == "false" && ember build --gh-pages --brunch=$TRAVIS_BRANCH && bash scripts/deploy-to-gh-pages.sh
``` 

3.To add script `deploy-to-gh-pages.sh` with [next soderjaniem](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh) 
In script-e specify my project in the lines [7](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh#L7) and [83](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh#L83) 

4.To create a branch on GitHub-e `-gh-pages`. 

5.From [ember-flexberry-gis-test-stand](https://github.com/Flexberry/ember-flexberry-gis-test-stand/tree/gh-pages) to copy to the new branch `-gh-pages` files: 

* `.gitignore` 
* `index.hbs` (you need to imanity 12th place) 
* folder `images` 
* folder `stylesheets` 

6.Enable build on [Travis](https://travis-ci.org/). 

7.Be added to the configuration of the build `gh-token`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}