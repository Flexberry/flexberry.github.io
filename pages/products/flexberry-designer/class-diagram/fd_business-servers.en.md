---
title: Business servers (classes with the stereotype businessserver)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Businessserver, business server, stereotype, generation, properties, business facade
summary: Features geniali classes with the stereotype businessserver, properties, business servers
toc: true
permalink: en/fd_business-servers.html
lang: en
autotranslated: true
hash: 8c24e35c26ee214dce7331aa61cc8c81383c14379fc881f98e64fdd449adcf23
---

To describe [business server](fo_business-server.html) you must create the chart class with the [stereotype](fd_key-concepts.html) `businessserver`.

For example:

![](/images/pages/products/flexberry-designer/class-diagram/businessserver.png)

## Generated

* Code business servers: [class that are inherited from ICSSoft.STORMNET.Business.BusinessServer](fo_user-operations-dataservice.html), methods are generated as virtual methods with the appropriate parameters and modifiers. The method body empty with [bracket programmer](fo_programmer-brackets.html) to make the code in the method. UML attributes are not generated, since the business objects servers are stateless, what a generator is issued a warning.
* Specified in the properties of plugs for your business servers.

# Properties of a business server

![](/images/pages/products/flexberry-designer/class-diagram/bsprops1.jpg)

Property | Description | Generation .Net language
:----------------------|:----------------------------|:--------------------------------------------
`Name` | name UML class Name .Net-class-business-servers
`Description` | a class description | `DocComment` before the class definition
`Packet, NamespacePostfix` | allow to set the Assembly and namespace in which to generate the type of | see [the Location of assemblies after code generation](fo_location-assembly.html).
`PBMembers` | allows you to specify whether to brace the programmer within the class to "manual" introduction of the members of the class | If the option is given, it is generated [bracket programmer](fo_programmer-brackets.html a) for "manual" introduction of the members of the class.
`GenerateComPlusServer` | | If the option is enabled, - the class generates a stub (wrapper) in a separate Assembly to access the business server through COM .
`ComPlusServerOptions` | | Indicates to the COM stub management transactionally COM default, in terms of COM . Affects the generation of the attribute ``` [Transaction(TransactionOption.XXXXXXX)] ``` directly in front of the stub class
`GenerateHTTPRemoteServer` | | If the option is enabled, - the class generates a stub (wrapper) in a separate Assembly to access the business server via Remoting, organized via the HTTP Protocol.

{% include note.html content="attribute Properties business servers do not count as not count the attributes. Properties and generation methods for your business server, as described [in the article class Methods and method parameters](fd_methods-parameters.html)." %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}