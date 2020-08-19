--- 
title: Development Flexberry Service Bus 
keywords: ESB, Development 
sidebar: flexberry-servicebus_sidebar 
toc: true 
permalink: en/fsb_development.html 
lang: en 
autotranslated: true 
hash: 0eee0777da066dd25aa59484bdafcd67ac0e1cd693b0b958ddafac64ec3c9bd1 
summary: Description of the development process and open repositories code Flexberry Service Bus. 
--- 

## Introduction 

Development `Flexberry Service Bus` is on GitHub [open repositories](https://github.com/search?q=topic:servicebus org:Flexberry&type=Repositories) as the team of platform Flexberry and with the support of enthusiasts from the community. 

## Service bus 

The address of the repository on GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus>. 
The repository the main components of the service `Flexberry Service Bus`, the solution contains: 

* `NewPlatform.Flexberry.ServiceBus.sln` - a solution that includes the following projects: 
* `NewPlatform.Flexberry.ServiceBus` project implementation components. 
* `NewPlatform.Flexberry.ServiceBus.Objects` project objectone model. 
* `NewPlatform.Flexberry.ServiceBus.Components` project with the interfaces of the components. 
* `NewPlatform.Flexberry.ServiceBus.ClientTools` project with public interfaces. 
* `NewPlatform.Flexberry.ServiceBus.ConsoleHost` - project console application. 
* `NewPlatform.Flexberry.ServiceBus.WinServiceHost` project with the application as a service. 
* `NewPlatform.Flexberry.ServiceBus.Tests` project with unit tests. 
* `NewPlatform.Flexberry.ServiceBus.IntegratedTests` project with integration tests. 

In the same repository are nuspec files, which are used to create NuGet packages, tires, used in administrative application. 

SQL scripts to create the database service bus are also located in this repository: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry Service Bus/SQL>. 

Configuration files and scripts used to create Docker images `flexberry-service-bus` and `flexberry-service-bus-postgres-db` are in the file [Docker](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Docker) the same repository. 

## Administrative application 

### OData backend administrative application bus 

The address of the repository on GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Editor>. 

* `NewPlatform.Flexberry.ServiceBus.Editor` - ASP.NET application for access to objects `Flexberry Service Bus` standard `OData`. 

Configuration files and scripts used to create the Docker image `flexberry-service-bus-editor` are in the file [Docker](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Editor/tree/develop/Docker) the same repository.

### ember-addon administrative application bus 

The address of the repository on GitHub: <https://github.com/Flexberry/ember-flexberry-service-bus>. 
Ember-addon contains the implementation of the main control `Flexberry Service Bus`. Designed for integration interface management bus in a target application created on the platform Flexberry. 

### ember-application administrative application bus 

The address of the repository on GitHub: <https://github.com/Flexberry/flexberry-service-bus-editor>. 
Management application `Flexberry Service Bus` implemented with using the addon `ember-flexberry-service-bus`. The backend-part is used ASP.NET app `NewPlatform.Flexberry.ServiceBus.Editor`. Packaged in the Docker image `flexberry-service-bus-editor` with OData backend. 

## Examples of adapters 

The address of the repository on GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples>. 
The repository contains a sample implementation of different versions of adapters for the tires. 

## development Process 

The development process based on [gitflow](https://proglib.io/p/git-github-gitflow/). 

### Issues and Pull requests 

Discovered problems in the bus are recorded in `Issues` repository. Welcome to repair problems sent in `Pull request` (PR) in the branch `develop`. 
Requests for major changes in `Flexberry Service Bus` should be in [RFC-repository platform](https://github.com/Flexberry/rfcs). 

### Testing platform components 

Projects on the C# contain tests: 
* Autonomous unit-tests run on Travis-CI for each commit and PR. 
* Integration unit tests - require for execution access to multiple DBMS and executed by the developers, as well as on the server, preparing NuGet packages. 

Projects in EmberJS contain tests running on Travis-CI for each commit and PR. 

### Automatic build Docker images 

1. [The Docker image flexberry-service-bus-postgres-db](https://hub.docker.com/r/flexberry/flexberry-service-bus-postgres-db) 
To run an automated build, you must create a git tag in the project <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus>. 
Examples of tag names: `1.0.0-alpha01-postgresql-db` or `1.0.0-postgresql-db`. 
This will create 2 docker tag referencing the same image. First name `1.0.0-alpha01` or `1.0.0 `. Second name `latest`. 

2. [The Docker image flexberry-service-bus](https://hub.docker.com/r/flexberry/flexberry-service-bus) 
To run an automated build, you must create a git tag in the project <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus>. 
Examples of tag names: `1.0.0-alpha01-service` or `1.0.0-service`. 
This will create 2 docker tag referencing the same image. First name `1.0.0-alpha01` or `1.0.0 `. Second name `latest`. 

3. [The Docker image flexberry-service-bus-editor](https://hub.docker.com/r/flexberry/flexberry-service-bus-editor) 
To run an automated build, you need to perform 2 steps, the sequence of steps is important. 
3.1 In the ember project <https://github.com/Flexberry/flexberry-service-bus-editor> instituted a special branch `docker-release`, which builds front-end for docker image, so I need to smiriti changes in branch `docker-release` so they got into the docker image. 
3.2 Create a git tag in the project <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Editor>. Examples of tag names: `1.0.0-alpha01-bus-editor` or `1.0.0-bus-editor`. 
This will create 2 docker tag referencing the same image. First name `1.0.0-alpha01` or `1.0.0 `. Second name `latest`. 

### code Style 

When developing `Flexberry Service Bus` it is common for the platform Flexberry [approach to the design code](fp_code-style.html) to adhere to PR was successfully taken. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}