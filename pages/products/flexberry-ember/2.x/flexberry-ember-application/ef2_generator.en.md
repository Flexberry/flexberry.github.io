---
title: Generator Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: CASE Plugins, Flexberry Designer, Flexberry Ember
toc: true
permalink: en/ef2_generator.html
lang: en
autotranslated: true
hash: 2bbbb8cf0f99c9aacab1fa93451474cc886fdcc6d618cb87072b6da32039a4b9
summary: Generate Ember-app from the menu Flexberry Designer.
---

## Install the plugin

You want to install Flexberry Ember CasePlugin folder Flexberry Designer.

In the configuration file Flexberry Designer, you can add the setting, which is responsible for version being installed ember-addon (if you wish, you can specify a link to a git repository):

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <!-- ... -->
  <appSettings>
  <!-- ... -->
    <add key="EmberPluginAddonName" value="ember-flexberry@0.8.2"/>
  <!-- ... -->
  </appSettings>
  <!-- ... -->
</configuration>
```

## Call generator

The challenge comes from the stage in the [Flexberry Designer](fd_flexberry-designer.html).

Causes click PCM under the shortcut menu, select it in the Ember
![Example](/images/pages/products/flexberry-ember/ember-flexberry/generation/Ember-plugin-menu.jpg)

## Menu generator

* `Свойства модели` necessary to configure the card types similarly as it's done for [card types](fd_types-map.html) for the code.

In the process of generation types .NET of card types will be automatically replaced by the corresponding Javascript types according to this table:

| Type .NET | Javascript Types
|-----------|----------------|
| bool of bool? | boolean |
| a decimal decimal? | number |
| double, double? | number |
| short, short? | number |
| int, int? | number |
| long, long? | number |
| float, float? | number |
| ushort, ushort? | number |
| uint, uint? | number |
| ulong, ulong? | number |
| DateTime, DateTime? | date |
| NullableDateTime | date |
| NullableInt | number |
| NullableDecimal | number |
| All other types | number |

* `Frontend`

 * `EmberJS` - generation Front a on EmberJS
 * `Генерировать` - Performs generation of Ember-based application.
 * `Открыть каталог` - Opens the catalog with Ember-app.
 * `Apache Cordova`

* `Backend`
 * ASP.NET
 * JAVA
 * PHP

* `Storage`
 * Microsoft SQL server
 * Postgre SQL
 * Oracle
 * Microsoft Access

* `Утилиты`
* `Информация about лицензии`

## The generation process

The first stage is the creation of metadata (catalogue `vendor\flexberry`) to be used in teams `ember generate ...`.

Next generation can be accomplished in two ways:

1)In an empty directory ember-app - comes complete to install the application [addon ember-flexberry](https://github.com/Flexberry/ember-flexberry) and generating entities of the application Ember teams `ember generate ...`.

At initial start of the generator for the selected model Flexberry need to run option 1).

2)When a non-empty directory ember-app - Runs only the generate models command `ember generate ...`.

This option is suitable for subsequent runs of the generator.

## The mistake

`Перегенерация` is to generate applications on top of existing and modified generated code, i.e. without overwriting certain entities of that application that could be modified by an application programmer.

Models are in the folders:

`app\models` and `app\mixins\regenerated\models`.

Serializer are located in the folders:

`app\serializers` and `app\mixins\regenerated\serializers`.

At the moment, will not be overwritten when you start the generation of files in folders `app\models` and `app\serializers`.

To change chart Flexberry got to the Ember app will use mixins folder `app\mixins\regenerated\models` and `app\mixins\regenerated\serializers`. The files in these folders will be overwritten each time you start the generation process.

### The mistake of the localization files

If there are localization files, at regeneration, they are stored and supplemented (if necessary). Accordingly, remove no longer used localization values for `ru` and `en` must be cleaned manually.

## Error generation process

If in the generation process, an error occurs in which occurs the line:

`... You have to be inside an ember-cli project...`

you need to perform in the project directory Ember(directory 'ember-app') the following command:

```bash
ember init
ember install ember-flexberry
```

then run the generator again.

As the generator is still under development, the team `ember install ember-flexberry` will continue to change.

### The algorithm speed with teams ember init and ember install

* Create in advance the archive node_modules.7z using the following steps:

 * To create a new folder and run command
 * ember init
 * install ember ember-flexberry
 * npm install
 * bower install
 * npm uninstall ember-flexberry
 * 7z a-r node_modules.7z node_modules

* Before starting the generator always clean the folder where it should be generated ember-app

 * Copy the file folder from node_modules.7z, obtained in step 1 and run the commands:

 * 7z x node_modules.7z
 * ember init
 * install ember ember-flexberry
 * npm install
 * bower install

In addition to the possible speed commands `ember...` will be achieved you have the latest version `ember-flexberry`.
For a quick cleanup of the folder can not delete folder `node_modules`, and move it to another location and then purge the folder ember-app.

## Generation in ember addon

* Addon needs to be created manually, with the command ember addon called addon, then switch to this directory is created with the addon
* In the addon you need to install ember-flexberry team `ember install ember-flexberry`
* Need metadata to generate. While the plugin itself from Flexberry not understand what he should do in the addon, not a regular application. But the metadata that are generated in the vendor folder qmo\flexberry» versatile and suitable for both options. You need to generate app using a conventional plug-in generate ember-application - it will be a separate app, it will not be used, only metadata. Then there are 2 options:
 * Copy metadata from a folder vendor\flexberry in the same folder you created the addon as well.
 * Specify the path to the folder with the metadata when you call blueprints generation, article [Blueprints the means in ember-flexberry](ef2_blueprints.html).
* Trigger generation of metadata: `ember g flexberry-application`
* If you need to specify the path to the metadata, you can use this format: `ember g flexberry-addon addon --metadata-dir vendor\flexberry`
* You can generate shapes and models for Dummy application of this same generator, for this you need to run the command genraly flag `--dummy`: `ember g flexberry-application app --metadata-dir vendor\flexberry --dummy`



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}