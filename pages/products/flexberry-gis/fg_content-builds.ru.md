---
title: Устройство билдов
keywords: ember
sidebar: flexberry-gis_sidebar
toc: true
permalink: ru/fg_content-builds.html
folder: products/flexberry-gis/
lang: ru
---

## Общая информация

У ГИС аддонах билды настроины на GitHub-е, так что  приложение билдятся трависом и деплоится на ветку gh-pages.

## Алгоритм настройки билдов

1.Надо добавить в **config** своего приложиния [следующий код](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/config/environment.js#L165):

```js
// Change paths to application assets if build has been started with the following parameters:
// ember build --gh-pages --brunch=<brunch-to-publish-on-gh-pages>.
if (process.argv.indexOf('--gh-pages') >= 0) {
  var brunch;

  // Retrieve brunch name from process arguments.
  process.argv.forEach(function(value, index) {
    if (value.indexOf('--brunch=') >=0) {
      brunch=value.split('=')[1];
      return;
    }
  });

  // Change base URL to force paths to application assets be relative.
  ENV.baseURL = '/' + ENV.repositoryName + '/' + brunch + '/';
  ENV.locationType = 'none';
}
```

2.Добавить в проект файл `.travis.yml` со [следующим содержанием](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/.travis.yml).

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

3.Добавить в проект script `deploy-to-gh-pages.sh` cо [следующим содержанием](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh)
В script-е укажите свой проект в строчках [7](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh#L7) и [83](https://github.com/Flexberry/ember-flexberry-gis-test-stand/blob/develop/scripts/deploy-to-gh-pages.sh#L83)

4.Создать ветку на GitHub-e `-gh-pages`.

5.Из [ember-flexberry-gis-test-stand](https://github.com/Flexberry/ember-flexberry-gis-test-stand/tree/gh-pages) скопировать в созданную ветку `-gh-pages` файлы:

* `.gitignore`
* `index.hbs` (в файле нужно именить 12 строчку)
* папку `images`
* папку `stylesheets`

6.Включить билд на [Travis](https://travis-ci.org/).

7.Добавить в настройку билда `gh-token`.
