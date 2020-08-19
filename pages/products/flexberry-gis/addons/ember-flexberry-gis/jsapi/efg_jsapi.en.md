---
title: JS API map
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: en/efg_jsapi.html
lang: en
autotranslated: true
hash: 5ed13a71a881ad3967711fd78e0f68f64b0036a4e506c8f08360c03e5da2363d
published: true
---

## Enable/disable the possibility of user interaction with the map

Sometimes, I have to completely shut down the possibility of user interaction with the map, and after some time turn it back on.
For example, when the map runs a script in which the user should be able zoom the map, move it,
and how else to change her condition.

You can use the methods:
* `leafletMap.flexberryMap.interaction.disable()`
* `leafletMap.flexberryMap.interaction.enable()`

And to check if you're on a user's interaction with a map you can use the method:
* `leafletMap.flexberryMap.interaction.isEnabled()`

## Shows/hides the progress bar long operations on the map

There are times when in the process of working with the map user starts some long operation, and you need to wait until it is completed
before the user can continue working with the map, for example when using the identify tool.
In this case, you need to show the user an indicator of a running operation, and hide it by closing the loop operation.

You can use the following methods:
* `leafletMap.flexberryMap.loader.show(options)`
* `leafletMap.flexberryMap.loader.hide(options)`

Both methods take the input `options` optional object that contains a single option:
* `options.content` is a string with the caption that will be displayed under the loading indicator. Ie if you want to show
the user the loading indicator with the signature `Загрузка...`, then you need to call the method `show` as follows:
`leafletMap.flexberryMap.loader.show({ content: 'Loading...' })` but to remove the signature immediately when you hide the indicator
need wysiwtf method `hide` so `leafletMap.flexberryMap.loader.hide({ content: "})`.

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/jsapi/efg_jsapi/loader.png)

To change the text that is displayed under the download progress bar, there is provided a method:
* `leafletMap.flexberryMap.loader.setContent(content)`

And to check to see whether the light in the moment, you can use the method:
* `leafletMap.flexberryMap.loader.isShown()`

## Enable/disable map tools

The card also provides API on and off her tools.

To activate any tool implemented method:
* `leafletMap.flexberryMap.tools.enable(mapToolName, mapToolProperties)`. It accepts a required parameter `mapToolName` is
the name of the tool from `app\map-tools` that you want to enable, and an optional parameter `mapToolProperties` is
an object with properties of the tool that need to be initialized before it is enabled (this is the properties of the instance of the tool).
The method call will create the instance of the tool if it has not been created previously, and if the instance has already been created
he will be taken.

To turn off the current instrument method is implemented:
* `leafletMap.flexberryMap.tools.disable()`. It takes no parameters, disables the tool, which is included
at the moment, but instead includes a toolbar by default.

To get the current tool, you can use the method:
* `leafletMap.flexberryMap.tools.getEnabled()`

And to get the tool by default you can use the method:
* `leafletMap.flexberryMap.tools.getDefault()`

When initializing the map tool by default is assigned `app\map tools\drag` (the move tool on the map).
But if necessary, you can assign its using method:
* `leafletMap.flexberryMap.tools.setDefault(mapToolName, mapToolProperties)`. It accepts a required parameter `mapToolName` is
the name of the tool from `app\map-tools` to assign to a tool by default, and an optional parameter
`mapToolProperties` is an object with the properties of the tool that need to be initialized before it is enabled (this is the properties
PCs tool). The method call will create the instance of the tool if it has not been created previously, and if the instance was already
once created, it will be taken.

In order to set properties to some instrument, while not including and not turning it off, you can use the method:
* `leafletMap.flexberryMap.tools.setProperties(mapToolName, mapToolProperties)`

And if you need to get rid of the previously created instance of any tool, then this is the method:
* `leafletMap.flexberryMap.tools.destroy(mapToolName)`

## Vypolnenie command cards

In addition to the tools that remain active after switching to infantry then I will be off
the card has commands `app\map-commands`. They are also present on the toolbar of the map, but differ from those tools,
that does not remain active until turned off, they just perform some action on a map once, does this wee
without affecting the instruments. Examples of such commands: search your current location, export or print cards, etc.
To work with teams API same API predusmotreno tools.

To execute any command implemented method:
* `leafletMap.flexberryMap.commands.execute(mapCommandName, mapCommandProperties, mapCommandExecutionOptions)`. It accepts a required parameter `mapCommandName` is
the name of the team from `app\map-commands` you want italnet, and optional parameters `mapCommandProperties` is
the object properties commands that need to be initialized before executing it (this is the properties of the instance team),
and the parameter `mapCommandExecutionOptions` - it's the options that will be passed to the method `execute` team.
The method call will create the instance of the command, if it has not been created previously, and if the instance has already been created
he will be taken.

In order to set properties to some instrument, while not including and not turning it off, you can use the method:
* `leafletMap.flexberryMap.commands.setProperties(mapCommandName, mapCommandProperties)`

And if you need to get rid of the previously created instance of any team, then this is the method:
* `leafletMap.flexberryMap.commands.destroy(mapCommandName)`



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}