--- 
title: layer Type group 
keywords: ember 
sidebar: ember-flexberry-gis_sidebar 
toc: true 
permalink: en/efg_group.html 
lang: en 
autotranslated: true 
hash: b6ae62c51ceef54f09ff14eb16c0f7fcf6e3e231eef529ee8217739f1061f44b 
published: true 
--- 

## Specification 

Group layer alone does not lead to any rabotu data, visual and behavioral it is simply a container/directory for other layers. 
The role of the container is expressed mainly in three things: 

* In the layer tree, group the layers together with embedded in them the subsidiaries are displayed in the Villa иерархии; 
* When enabling/disabling visibility of the group layer on the map appear/disappear and all its nested child слои; 
* Group layers button add new sublayers, and to the root of the entire hierarchy of map layers and other layer types, this opportunity is not обладают; 

## Ember-layer component and its properties 

Ember-the group layer is on the way [ember-flexberry-gis/addon/components/layers/group-layer](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/components/layers/group-layer.js) 

## Examples of usage 

Example of adding a small multi-level hierarchy using group layers: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_group/group-layer-example.png) 

Please note, the system of coordinates and other attributes characteristic of other types of layers are not specified when adding a group layer 
you specify only the layer type `group`, the layer name, if required, else optional description and keywords. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}