---
title: assemblies Location after code generation
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM
summary: the Concept of the package structure, the name and location of the directory of the generated application
toc: true
permalink: en/fo_location-assembly.html
lang: en
autotranslated: true
hash: 01ad075499517a19c228fd8892f37183abfd068796c07ec27b8d8c2956da3ee1
---

## The concept of the package

__Package__ is called a logical grouping of the generated source code builds. After generating the source files, which are packaged, are placed in a separate subdirectory.

### Directory structure after code generation as follows

* The directory specified in "Directory for the source code" stage settings
1
* Annex 2
* ...
* Annex N
* Package 1
* Pack of 2
* ...
* Pack N

If the package name is not specified inside any class, the package name uses [the property "product Name"](fd_project-customization.html) from the settings stage.

Inside each package can contain following source code builds(in separate directories with the specified names):

* `BusinessServers` - Assembly classes with stereotypes [businessserver](fd_business-servers.html);
* `BusinessServersComPlus` - Assembly [wrapper for accessing a business server](fo_business-server.html) via COM for business servers that have a check mark `GenerateComPlusServer`;
* `BusinessServersHttp` - Assembly wrapper to access бизнес-серверуfo_business-server.html using a web service for servers that have a check mark `GenerateHTTPRemoteServer`;
* `Catchers` - for classes with the stereotype [eventarg](fd_eventarg.html) that have a checkmark `GenerateCatcher` in this Assembly generated classes-interceptors событий;
* `Objects` classes with stereotypes: [implementation](fd_data-classes.html), `type`, [enumeration](fd_enumerations.html), `eventarg`.
* `Scripts` - scripts defined diagram technique `EBSD`.

In addition (in the presence of special generators) can be generated:

* `DesktopCustomizers` - assembling classes of Adjusters classes with the stereotype [application](fd_additional-stereotypes.html);
* `Forms` classes with stereotypes: [editform, listform, printform, userform](fd_additional-stereotypes.html);

If not under classes, falling in the respective category, the source codes are not generated and the directory is not created.

### Namespace

Inside the Assembly defines the namespace. The developer can manage namespaces, which has the following features:

* each class has a property `NamespacePostfix`;
* stage has the property [Namespace](fd_project-customization.html).

Namespaces are generated as follows (one word is attributed, the point between them is not placed):

``` csharp
<Namespace стадии> + <NamespacePostfix класса>
```
If NamespacePostfix class is not specified, then only the "<Namespace stage>".

If <Namespace stages> is not specified, it is formed as "<company Name>.<Product name>" from the settings stage. [Read more...](fd_project-customization.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}