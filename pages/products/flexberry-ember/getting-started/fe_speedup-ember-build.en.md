---
title: accelerating the build EmberJS applications
keywords: ember-cli, build, speedup, optimization, optimization
tags: [EmberJS]
sidebar: flexberry-ember_sidebar
toc: true
permalink: en/fe_speedup-ember-build.html
lang: en
autotranslated: true
hash: ec3fdcc1404c6643559d7ab1a2c91e9ef09c4f0eca540674234e1265ff9720d0
summary: Recommendations for configuring the environment to optimize the speed of Assembly of EmberJS applications.
---

## Guidelines for choosing operating systems to ember-cli

If it is possible to choose the operating system on which to build ember applications, it is recommended to use one of the modern Linux distributions. In this operating system the build is running as quickly as possible due to the peculiarities of the file system.

## Recommendations for setting up the environment to build under Microsoft Windows

1. You should add the directory with the projects in EmberJS in the exceptions to Windows Defender or any other antivirus. During Assembly are handled tens of thousands of small js files.
2. For a directory with projects in EmberJS disable indexing. In the properties of the directory `Общие` -> `Атрибуты` -> `Другие` -> `Атрибуты indexing and архивации` -> clear the checkbox `Разрешить to index the contents of files in this folder in addition to the properties файла`.
3. It is recommended to use the console in administrator mode.
4. It is recommended to use SSD instead of HDD, including the cache folder with npm, yarn, bower. In the case of using `npm link` or `yarn link` make sure that all the files participating in the build will be on the SSD.
5. You can configure a RAM-Disk for devices without SSD, but with enough RAM.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}