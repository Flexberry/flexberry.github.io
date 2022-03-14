---
title: Creating a new project
keywords: Ember Flexberry Designer, UML, design basics, modules, configure
summary: Flexberry Designer Online registration and creating a new project
sidebar: flexberry-designer-online_sidebar
toc: false
permalink: en/fdo_landing_page.html
lang: en
---

## Creating a new project

### Registration and logging in Flexberry Designer Online

To register in Flexberry Designer and log into the portal, you must perform the following steps:

1. First need to open the portal [flexberry.net](https://flexberry.net/).
2. Next button `Sign up`. Open login page and register. If the check is already there, you can log in.
3. Note the checkbox `I am not Robot`. To specify a real email address, you will receive an email with activation account. To specify the name and press the register button.
4. The system displays a notification that an email is sent. Open the mail to find a letter from `no-reply@flexberry.net` where there is a link to confirm your entry, also comes an email with a password for authorization.
5. Click on the link. Display a notice `Verfication successfully completed`. Click `Ок`.

Open start page Flexberry Designer Online, where you can see parts of it.

* `All Projects`. In this section there is a list of all created projects.
* `My license and Request`. This section is a list of licenses and appeals in support. The License is for Flexberry Designer Desktop. The list of complaints is all the questions and answers from the support team of the designer.
* `Flexberry Designer Desktop`. In this section you can download Flexberry Designer Desktop. This is the professional version of Flexberry Designer Online. Necessary for the development of offline and multi-structured or complex applications. Requires a special license. To create an application online a special license is not required.
* `User Profile`
* `flexberry.net Portal`. Translates into the portal Flexberry Platform where you can find variety of products developed using the platform.
* `Platform source code`. Opening this button opens the GitHab page of the Flexberry Platform. It is possible to participate in the finalization of platform products through a system of pull-requests.
* `Documentation`. The Documentation button opens the website with product documentation of flexberry platform. At the top is a link to the documentation for working with the designer.
* `Chat`. Here is a room where you can ask questions about working with the designer, its development and documentation. The questions are answered by competent specialists who are always ready to help and support.

### Create a new project

To create your first project click `All projects` on the main page. After pressing menu `All projects` page will be displayed with the proposal to create the first draft. Click `New project`, it will open the creation form of the project.

`Project name`, it is advisable to call the project so that it is clear which application you want to create. It should be brief and succinct.
`Identifier name` is how the project will be called in the team. Perhaps the abbreviation or the purpose of the project. If left blank, the field will be created automatically by the project name. Identifier name of the project should be in English or written in Latin.
`Access` – who will be able to view and edit the project.
`Description` – brief description of the project.

The project not only create, but also to import already existing one. For this field to Select a project based on saved file, select project button to Select a file. After all fields are filled in, the project can be sustained. Later this data can be edited. This will open the project page where will be displayed a list of all generated classes. Creating classes is one of the main functions Flexberry Designer Online.

### A brief overview of what Flexberry Designer Online

When you create a project, open the `Application model` page, which is a list of the classes (essentially, a set of elements) that describe the domain of the application and its functionality. Flexberry Online Designer allows you to create different types of classes from simple entities (application objects) and forms to private types and business servers.
Classes can also be created during creation of the diagram. `Diagrams` allow you to not only create classes, but also to establish relationship between them, which in the future will allow to associate different forms in the application. Also, some types of diagrams help to detail the subject area, to describe the processes that must be implemented by the application.
`Naviagtion` allows you to create a menu of the application to distribute forms groups and/or roles.
`Generation` allows you to create a working prototype application, which is suitable for the initial demonstration to the customer. On this basis the application is easy to modify in accordance with the wishes of the customer as the main points of the subject area had been introduced.
The app can be `configure` in accordance with the needs of your application, specify the path generation for client and server parts of the application, to Supplement the description. Also the settings menu option allows to delete the project.

### Project settings

`Project Name`, Project identifier name and description are explained above.

`Access`:

* Open – access project of yours.
* Open to entire Internet – the project will have open access.

#### Settings generation

They can be for the client and server parts, if you need to split them. The server part is usually used for "complex" algorithms, so as not to overload the user interface. The client part is needed for »on the fly« processing.
Login, password for the client part repository and branch for the client part, as well as similar fields for the backend part are used if you need to generate an application in an existing repository.
If you do not have a repository yet, the designer will create a new repository on [Githab](https://github.com/) by the project name (it will generate and bind [ssh keys](https://ru.wikipedia.org/wiki/SSH)), and generates code in the created repository.
In addition, the project may be published in `gh-pages`.
It is also possible to change localization (the language which the designer displays) and the theme designer.
To export project need to open the `Settings` page and under `Project Action` with the project to choose `I want to export project`.
You can also make backup project, click the button `I want to do Project backup`.
