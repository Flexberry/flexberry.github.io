---
title: Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_flexberry-asp-net.html
lang: en
autotranslated: true
hash: 9fa6bc417cf5f9566744f2b224ca3f28e7054e260425fbdb9bc2aad9c3a4f91d
---

Flexberry ASP.NET is [product platform Flexberry](fp_landing_page.html). The product website Flexberry ASP.NET: [flexberry.ru](http://flexberry.ru/Flexberry/ForDevelopers/FlexberryASPNet).

## Description of product

By [Flexberry Designer](fd_flexberry-designer.html) it is possible to create [class diagram](fd_class-diagram.html). This [chart](fd_class-diagram.html) you can generate the required database structure (DB), the object model and ready web application.

## The product

Flexberry ASP.NET consists of the following modules:

* [Expansion module Flexberry Designer to generate web applications in the uml models](fa_asp-net-generator.html).
* [Web controls and web components](fa_web-controls.html).
* [Subsystem powers (web)](fa_right-manager.html)
* [Themes](fa_themes.html)

## Limitations Flexberry ASP.Net

* [List of supported browsers](fa_browsers.html)
* [Possible locations of a memory leak in web applications](fa_memory-leaks.html)

### Related articles

* ASP.NET web controls, components
 * [WebObjectListView](fa_web-object-list-view.html)
 * [Sorting in list form, raised on locapo](fa_lookup-form-sort.html)
 * [Opening lookup-enabled form hierarchy](fa_lookup-form-hierarchy.html)
 * [Advanced constraint editor in the WEB](fa_advanced-limit-editor.html)
 * [External-classes in the advanced editor, limitations](fa_web-limit-editor-external-class.html)
 * [Empty/is non-empty in the advanced editor, limitations in the WEB](fa_web-limit-editor-null.html)
 * [Display on the interface shape and datalow](fo_masters-details.html)
 * [ViewColumnProvider](fa_view-column-provider.html)
* Lock
 * [Working with locks in the web](fa_working-locks-web.html)
 * [WebLockHelper](fa_web-lock-helper.html)
* Design and development of web applications
 * [Set of columns for placing the controls on the level of representation Flexberry Designer](fd_specify-column-controls.html)
 * [Routing in a Web app](fa_routing.html)
 * [Scripting web development](fa_scenario-web.html)

## Features settings for Unity Lifetime Managers

Uses Unity Lifetime Managers to manage the lifetime of created objects. The application can use both existing managers and to create their own, depending on application requirements.

| Name | Description | application Features |
|--------------|----------|----------------------------|
| `UnityContainer.Resolve<IAbstraction>()` | each time is created a new instance is created abstraction | Cannot be used for caching, so caching the data will not occur |
| `ContainerControlledLifetimemanager (singleton)` | He ensures the creation of container instance based on the first Resolve, and on all subsequent returns an already created | In such a scenario, it is necessary that the class either did not keep any state, or been thread-safe |
| `TransientLifetimeManager` | Unity creates and returns a new instance of the requested type for each call to the Resolve method. The default for all types that are registered using the RegisterType method, which was not specified by the Manager. When registering transitional types there is no need to pass an instance TransientLifetimeManager for registration | is thread safe because every user has its own instance of the dependency. Can lead to is created and garbage-collected innumerable instances, while one instance would be enough|
| `ContainerControlledTransientmanager` | TransientManager Similar, but contains a reference to each created disposable instance and deletes them when you delete the container | Useful when used in session-based projects with the child container, associated with the session |
| `ContainerControlledLifetimemanager` | Registers an existing or permitted the object as a singleton instance in the container that the registered object instance. Returns the same instance of the registered type or object each time called Resolve or when the dependency mechanism injects instances into other classes. [Podrobnee...](https://github.com/unitycontainer/unity/wiki/Unity-Lifetime-Managers#containercontrolledlifetimemanager) ||
| `ExternallyControlledLifetimemanager` | Provides generic support for externally managed deadlines, allows you to register and mapping existing objects in conteineres reference to the instance that it detects or creates when you call the Resolve method and also when the mechanism of the dependency injects instances into other classes based on attributes or constructor parameters of this class | Use this Manager allows other code to save the object in memory or dispose of them, and also allows you to control the lifetime of existing objects or allow any other mechanism to control the lifetime of |
| `HierarchicalLifetimeManager` | Similar ContainerControlledLifetimeManager. The difference is that if there are child containers, each child resolves its own instance of the object and does not share it with the parent. When you Resolve the parent's behavior seems like a life time контейнера; when you resolve the parent and child instances remain different instances, each of which acts as a container-managed Lifetime. Each of the child elements will resolve to its own instance of ||
| `PerResolveLifetimeManager` | Similar TransientLifetimeManager, but also provides a signal to the plan Assembly's default, marking the type so that instances are reused in the object graph Assembly. In the case of recursion, a single behavior is applied when the object was registered in the PerResolveLifetimeManager |In comparison with the Transient, when using Per Graph, there are no additional costs, so it can be used as a replacement for Transient|
| `PerThreadLifetimeManager` | Returns for each thread the same instance of the registered type or object each time called Resolve or when the dependency mechanism injects instances into other classes. Effectively implements a singleton behavior for objects for each thread. PerThreadLifetimeManager returns different objects from the container for each thread, | compared with the Transient, when using Per Graph, there are no additional costs, so it can be used as a replacement for Transient |

## Examples

### Example of setting technological forms

Examples of how technological forms presented in [article, Examples of how technological forms](fa_technological-forms-customization-example.html).

## Refinement user interface

The generated Web pages need improvement in the following points:

* Page titles
* Alignment of elements on the pages
* The overall style, colors and fonts of page elements
* Page zoom
* Localization



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}