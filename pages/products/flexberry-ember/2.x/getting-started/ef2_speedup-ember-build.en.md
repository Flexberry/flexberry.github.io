---
title: accelerating the build EmberJS applications
keywords: ember-cli, build, speedup, optimization, optimization
tags: [EmberJS]
sidebar: flexberry-ember-2_sidebar
toc: true
permalink: en/ef2_speedup-ember-build.html
lang: en
autotranslated: true
hash: d3070907df9b5c63a80c050b68f32e49a82909a7424b325eb5567f3e0e773857
summary: Recommendations for configuring the environment to optimize the speed of Assembly of EmberJS applications.
---

## Guidelines for choosing operating systems to ember-cli

If it is possible to choose the operating system on which to build ember applications, it is recommended to use one of the modern Linux distributions. In this operating system the build is running as quickly as possible due to the peculiarities of the file system ([Istochnik](https://levelup.gitconnected.com/working-with-front-end-tools-on-linux-and-windows-the-grand-performance-test-b51a77a71636)).

## Recommendations for setting up the environment to build under Microsoft Windows

If a project build takes more than 1-3 minutes, then try to use the following recommendations.

1. You should add the directory with the projects in EmberJS in the exceptions to Windows Defender or any other antivirus. During Assembly are handled tens of thousands of small js files.
2. For a directory with projects in EmberJS disable indexing. In the properties of the directory `Общие` -> `Атрибуты` -> `Другие` -> `Атрибуты indexing and архивации` -> clear the checkbox `Разрешить to index the contents of files in this folder in addition to the properties файла`.
3. It is recommended to use the console with administrator privileges. In the console with administrator rights supports symbolic links (symlink), which gives a significant speed boost in the Assembly.
4. It is recommended to use SSD instead of HDD, including the cache folder with npm, yarn, bower. In the case of using `npm link` or `yarn link` make sure that all the files participating in the build will be on the SSD.
5. You can configure a RAM-Disk for devices without SSD, but with enough RAM.
6. To disable testing (tests: false) and checking coding style (code style) (hinting: false) when you build your app. To do this in ember-cli-build. In testing during Assembly there is no particular need, and because of the large code base takes significant amount of time. To run a test is clearly the team `ember test`.
7. As a measure, which somehow works - delete folders `node_modules` and `tmp` and file `yarn.lock`, and then reinstall the packages and build the application ([source](https://github.com/ember-cli/ember-cli/issues/6921)). Deletion of folder `tmp` true for versions < 2.13 (not sure), in older she is cleared.
8. To perform that your particular project will take the maximum time during Assembly and to try to overcome this particular problem. The long build time is not even a sentence, but an interesting technical challenge!

## Additional materials

* [Windows support - Appendix - the Ember CLI Guides](https://cli.emberjs.com/release/appendix/windows/)
* [Tips for improving build time of large apps](https://discuss.emberjs.com/t/tips-for-improving-build-time-of-large-apps)
* [How we speed up testing and building process of our brand new Ember app at? ~2.5 x faster!](https://medium.com/@tommaqs/how-we-speed-up-testing-and-building-process-of-our-ember-app-at-brandnew-2-5x-faster-299dd4995a97)
* [Build performance](https://github.com/ember-cli/ember-cli/blob/master/docs/perf-guide.md)
* [How we cut down our build time ember?](https://www.gokatz.me/blog/how-we-cut-down-our-ember-build-time)
* [Ember performance tweaks: Optimising build timelines & bundle size](https://abhilashlr.in/ember-performance-tweaks-part-1)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}