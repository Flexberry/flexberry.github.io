---
title: JS API карты
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: en/efg_jsapi.html
lang: en
published: true
---

## Enable / disable user interaction with the map

Sometimes, it is necessary to completely turn off the possibility of user interaction with the card, and after some time to turn it back on. 
For example, when a map runs some scenario where the user should not be able to zoom the map, move it, 
and somehow still to change its state of.

To do this, you can use the methods:
* `leafletMap.flexberryMap.interaction.disable()`
* `leafletMap.flexberryMap.interaction.enable()`

And to check whether the user interaction with the map is now enabled you can use the method:
* `leafletMap.flexberryMap.interaction.isEnabled()`

## Show / hide the progress bar of a long operation on the map

There are situations when in the process of working with the map, the user starts some long operation, and you need to wait for its completion
before the user can continue to work with the map, for example when using the identification tool.
In this case, you need to show the user the ID of the running operation, and hide it at the end of the operation.

To do this, you can use the following methods:
* `leafletMap.flexberryMap.loader.show(options)`
* `leafletMap.flexberryMap.loader.hide(options)`

Both methods accept an optional `options` object containing a single option:
* `options.content` is a string with a caption that will be displayed under the download indicator. Ie if you want to show 
the user is shown a loading indicator with the caption `Loading...` , then you need to call the `show` method as follows: 
`leafletMap.flexberryMap.loader.show({ content: 'Loading...'})`, and to remove this signature immediately when hiding the indicator 
you need to call the `hide` method like this `leafletMap.flexberryMap.loader.hide({ content: ''})`.

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/jsapi/efg_jsapi/loader.png)

To change the text that is displayed under the download progress bar, there is provided a method:
* `leafletMap.flexberryMap.loader.setContent(content)`

And to check whether the indicator is currently displayed you can use the method:
* `leafletMap.flexberryMap.loader.isShown()`

## Turn map tools on/off

The map also has an API for enabling and disabling its tools.

To enable any tool, the method is implemented:
* `leafletMap.flexberryMap.tools.enable(mapToolName, mapToolProperties)`. It accepts the required parameter `mapToolName` - this is 
the name of the tool from `app\map-tools` that you want to enable and the optional `mapToolProperties` parameter is 
an object with tool properties that need to be initialized before enabling it (these are the properties of the tool's exeplier).
When the method is called, the tool exeplier will be created if it has not been created before, and if the instance has already been created once, 
then he will be taken.

To disable the current tool the method is implemented:
* `leafletMap.flexberryMap.tools.disable()`. It does not accept any parameters, turns off the tool that is enabled 
at the moment, and instead includes the default tool.

To get the current tool you can use the method:
* `leafletMap.flexberryMap.tools.getEnabled()`

And to get the default tool, you can use the method:
* `leafletMap.flexberryMap.tools.getDefault()`

When initializing a map, the default tool is `app\map-tools\drag` (map navigation tool). 
But if necessary, you can assign your own, using the method:
* `leafletMap.flexberryMap.tools.setDefault(mapToolName, mapToolProperties)`. It accepts the required parameter `mapToolName` - this is 
the name of the tool from `app\map-tools` that you want to assign as the default tool and the optional parameter 
`mapToolProperties` is an object with tool properties that need to be initialized before enabling it (these are the properties 
instrument). The method call will create the instance of the tool if it has not been created previously, and if the instance was already 
once created, it will be taken.

In order to set the properties of some tool, without turning it on or off, you can use the method:
* `leafletMap.flexberryMap.tools.setProperties(mapToolName, mapToolProperties)`

And if you suddenly need to get rid of a previously created instance of a tool, then there is a method for this:
* `leafletMap.flexberryMap.tools.destroy(mapToolName)`

## Execute map commands

In addition to the tools that remain active after switching on until they are turned off, 
the map has `app\map-commands` commands. They are also present on the map toolbar, but differ from the theme tools,
that do not remain active until it is turned off, they just perform some action on the card once, no PI this 
without affecting the instruments. Examples of such commands are: search for the current location, export or print the map, etc.
To work with commands, an API similar to the tools API is provided.

A method is implemented to execute any command:
* `leafletMap.flexberryMap.commands.execute(mapCommandName, mapCommandProperties, mapCommandExecutionOptions)`. It accepts the required parameter `mapCommandName` - this is 
the name of the command from `app\map-commands` that you want to fill in, and the optional parameters `mapCommandProperties` are 
the object properties commands that need to be initialized before executing it (this is the properties of the instance commands), 
and the `mapCommandExecutionOptions` parameter are already options that will be passed to the `execute` method of the command itself.
When the method is called, the command exeplier will be created if it has not been created before, and if the instance has already been created, 
then he will be taken.

In order to set the properties of some tool, without turning it on or off, you can use the method:
* `leafletMap.flexberryMap.commands.setProperties(mapCommandName, mapCommandProperties)`

And if you suddenly need to get rid of a previously created instance of a command, then there is a method for this:
* `leafletMap.flexberryMap.commands.destroy(mapCommandName)`
