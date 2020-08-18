---
title: basic system and technology architecture Flexberry Winforms
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Key concepts
summary: Logical and physical levels of the system, key terms, metadata, types of forms, business server, scripts
toc: true
permalink: en/fw_flexberry-winforms-architecture.html
lang: en
autotranslated: true
hash: 349ea9fefcb4bfdf95bd3d78d70e3e529104eb5b881cb2b29accc979c9b03efc
---

Basic system and technology architecture includes architectural, logical and physical levels.

## Logical architectural levels of the system on the basis of Flexberry Platform

Any application system that is created on the basis of `Flexberry Platform`, contains three logical levels:

* User interface.
* Business logic.
* Data, metadata, and data access.

![Logical architectural levels of the system on the basis of Flexberry Platform](/images/pages/products/flexberry-winforms/primer4.jpg)

Level III is represented by the data objects assigned to them with declarative metadata and data services that provide the basic functionality for working with repositories.

Level II submitted by the [business server](fo_business-server.html) that contains the code of system-wide operations on the data objects and operations performed when the data services (handlers). A business server can be executed both independently and through some wrapper (e.g., wrapper `COM ` allows you to perform a business service under `COM `).

Level I provides the actual user interface, depending on the environment of implementing (this can be a desktop client, browser (UI dependent form), the user interface, not depending on the environment of implementing (UI-independent form) and screenwriter. Fundamentally, the custom logic is separated from the dependent on the client implementation. For example, the logic of restriction should be implemented in the UI-independent form that will allow you to use the same logic independently from the UI-dependent implementation of user interface. Thus, the internal implementation of a system as independent as possible from the user interface, while possessing re applicability even custom logic.

## Physical and architectural levels of the system on the basis of Flexberry Platform

Multi-tier distributed client-server architecture with a separated application server and its implementation.

The logical architecture of the system can be physically implemented as:

* Monolithic.
* Two-tier client-server.
* Distributed multi-level.
* Distributed multi-level in `WEB`.

Data services can serve as local sources (files) and the DBMS, so the physical architecture can be implemented like this:

* Stand-alone server, the data service is a local source (eg. XMLDataService).
* Independent business server, the service data DBMS source (eg. ODBCDataService).
* Business server that COM , the service data DBMS source.
* Business server for a Web Service, the data service DBMS source.

It can be exotic «and», the type of the local file, but `Web` service.

Obviously, the use of different media gives different possibilities. Everything here is at the discretion of the developer.

## Systems integration

The physical location code (both the source and binary builds) does not matter. The connection of the parts of any system and a foreign system as a subsystem are implemented by connecting respective assemblies.

The division of the Assembly within the system is the responsibility of the developer.

## The metadata object structure

Separately existing metadata does not exist, instead, defining additional class properties ([view](fd_view-definition.html), for example), [attributes](fo_attributes-class-data.html) (e.g. [`NotNull`](fo_attributes-class-data.html)), methods, etc. identify the attributes of `.Net`. In essence, all the additional metadata are defined declaratively `.Net`-attributes directly to the data classes, their properties etc.

There is no universal [of information class (`ICSSoft.STORMNET.DataObject.Information`) with static methods to retrieve metadata](fo_methods-class-information.html). Access application developer to declaratively defined metadata (`.Net`-attributes) is not to be pursued directly (through `.NET Reflection`), but only through appropriate methods of this [class information](fo_methods-class-information.html). The only exceptions are those attributes which are entered directly by the application developer.

## Data storage and independence from storage

Servicom dannyh refers to any implementation of the abstract class `ICSSoft.STORMNET.Business.DataService`, providing the save/load any data objects in any repository.
The details in the article [Service data](fo_data-service.html).

### Form

Forms are designed for interaction with the user.

Formay is the method of providing a user interface to access one or more data objects. Forms are _UI-zavisimye (depend on the physical nature of the interface – `WinForms, WebForms`) and _UI-nezavisimye. Custom logic must be implemented in a UI-independent forms that will allow you to make fewer changes, and to avoid code duplication when implemented in a system other types of user interface.

_UI-dependent forma can be uniwersalnymi, i.e. to implement some standard functionality without the possibility of any modification. As a rule, it is necessary for very simple objects for which it makes sense to save on the creation of individual forms. Universalnye UI-dependent forma can be generated (from the source code) and then zaprogramirovan.

Formy redaktirovaniya allow the user to edit the custom attributes of the data objects in connection with the mechanics of the data objects, the aggregated data objects in accordance with one or more views. The edit form provides a "polymorphic" edit: as data objects of its own class and all inherited classes, in the representation(s) of their own class.

Forma spiska allows the user to "polymorphic" to work with a list of data objects as its own class and any inherited classes. In this case, the form of the list identifies the data classes of the descendants and the name of the compatible view.

Forma pechati allows to obtain a printed copy of the data object in accordance with the representation(s).

## Business server

[Business server](fo_business-server.html) is designed for logical allocation operations, which apply to the system. In essence, the business server has a set of methods. Accordingly, the business server does not have a condition (common — stateless). In CASE a business server draw a UML class with the executable attribute [businessserver](fd_business-servers.html). The system can contain an arbitrary number of business servers. In General, the number of business servers and their methods is defined by an application developer.

Business service is also used in cases when the service data repository (adding, deleting, etc. of the object), you want to perform any action.

When code is generated for each business server is created:

* Code business server definitions методов;
* The code of the stubs (qmc «needs — determined by the developer in CASE the properties of the class);

## Writer

There is the following problem when creating multi-level systems: there are separate data access, business logic, user interface. However, as (than) it is possible to combine these parts in a working system? In other words, how (where) should be recorded that under certain manipulation of the user interface forms displayed in sequence and when there is a call to the business server?

Of course, it is possible, as is commonly done,» «hard to register calls directly in code, for example, in the user interface, however, it is a bad idea for the following reasons:

* Parts of the system cease to be parts of the» qmo, because» «start to know about each other, which can make it difficult to reuse.
* Modification of this unifying logic is time-consuming as it is in many places (qmc «scattered in parts, assemblies, classes).
* Modification of the unifying logic requires intervention in the source code (requires programmer). If the system can be modified from the outside, it is possible to give the flexibility to customize the logic of the finished pieces directly to the user.
* Generally difficult to build the logic, which includes the operation execution process, which is highly branched, depending on any conditions. Or, run different logic depending on user permissions.

At the same time, this is the uniting logic is nothing but a scenario for the user and can be described as an independent entity.

To solve this problem, in `Flexberry Platform` provides for the establishment of event-oriented scenarios (`EBS — Event-Based Script`) and their interpretation.

There is a special graphical language allowing to describe the scenarios (`EBSD — Event-Based Script Diagram` corresponding diagrammatic method implemented in `Flexberry`).

There is a special component Scenarist (`EBSI — Event-Based Script Interpreter`) providing the execution (interpretation) of the script in `RunTime`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}