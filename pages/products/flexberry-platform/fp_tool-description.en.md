--- 
title: Description recommended tools 
sidebar: flexberry-platform_sidebar 
keywords: Tools 
toc: true 
permalink: en/fp_tool-description.html 
lang: en 
autotranslated: true 
hash: 188c891816fe4d1089b16b36547c86c07abdbab0e65f7015f8295d8c36dea779 
summary: a List of tools recommended for use by developers. 
--- 

## Tools to work with ASP.NET applications 

### Mandatory set 

* [Flexberry Designer](https://flexberry.net/ru/); 
* Microsoft Visual Studio 2017; 
* SQL Management Studio; 
* pgAdmin. 

### Additionally, it is recommended 

* Resharper; 
* DotPeek; 
* StyleCop; 
* SQL Toolbelt; 
* LinqPad; 
* .NET Developer Bundle™; 
* ANTS Performance Profiler; 
* ANTS Memory Profiler; 
* Exception Hunter; 
* .NET Reflector Pro; 
* DevArt Data Compare; 
* DevArt Schema Compare. 

## the Tools for Ember applications 

### the Tools needed in the first place 

* [Node.js](http://nodejs.org). Allows you to work with tools for front-end outside the browser. Also in the node.js includes npm (node package manager) to work with dependencies in frontend projects, similar to NuGet in Flexberry ASP.NET WebForms. 
* [Bower](http://bower.io). Is analogous to npm, but they are often used on the project together. 
* [Git](http://git-scm.com). Distributed version control system. 
* [Ember-CLI](http://www.ember-cli.com). 
* [yarn](https://yarnpkg.com/lang/en/docs/install/#windows-stable). 

### Other tools 

* **Browser** and the Ember inspector extension that helps you debug ember-app: 
* [chrome](https://chrome.google.com/webstore/detail/ember-inspector/bmdblncegkenkacieihfhpjfppoconhi) 
* [firefox](https://addons.mozilla.org/en-US/firefox/addon/ember-inspector) 
* **Console** for git, npm, bower, ember-cli, etc. 
* **linux**, might go to any terminal. 
* **windows** standard cmd.exe not the best choice. 
Recommended: 
* PowerShell. 
* ConEmu. 
* Cmder. 
* **IDE and text editor**. IDE for js is very resource costly, therefore it is recommended to use text editors such as: 
* Visual Studio Code 
* Atom (it is recommended to install the extensions, tight work) 
* [file-icons](https://atom.io/packages/file-icons) 
* [platformio-ide-terminal](https://atom.io/packages/platformio-ide-terminal) 
* [language-markdown](https://atom.io/packages/language-markdown) 
* [markdown-preview-plus](https://atom.io/packages/markdown-preview-plus) 
* and other. 

{% include note.html content="the ember-cli.com](http://www.ember-cli.com/user-guide/#editors) says some of the recommendations, with some editors." %} 

#### Algorithm setup and tuning Node.js 

{% include important.html content="All commands in the algorithm need to enter in the cmd in administrator mode" %} 

1. You need to download and install [Node.js](https://nodejs.org/en/) version Recommended For Most Users 
2. After installation open cmd from administrator and enter `npm -v`, if issued, the version number of the npm install was successful and you can move on to the next item. 
3. Next you need to add proxy it can be done in two ways: 

#### Algorithm setup and configure Bower 

{% include important.html content="All commands in the algorithm need to enter in the cmd in administrator mode" %} 

{% include important.html content="Before you install Bower, you must first install Node.js" %} 

1. In cmd enter the command `npm install-g bower` to install [Bower](https://bower.io/). 
2. That would be to check whether the installed Bower, enter the command `bower -v` in cmd, if issued, the version number is the installation was successful and you can proceed to the next item. 

#### Algorithm installation and configuration of Git 

1. You need to download and install [Git](https://git-scm.com/). 

{% include important.html content="After installation need to restart your computer" %} 

Before you start working with the repository, perform the initial setup of git (if not previously configured) as specified in [Introduction - Initial setup Git](https://git-scm.com/book/ru/v1/Введение-Первоначальная-настройка-Git). 
At least: 

* You must specify the name and email attached to Your account on GitHub: 
* Name: `git config --global user.name: "John Doe"` 
* Email: `git config --global user.email johndoe@example.com` 

The next step is to become a member of the team [Flexberry on GitHub](https://github.com/Flexberry). 
It is necessary to send commits to the remote repository on GitHub. 

{% include note.html content="a problem May arise that when checking through the console (git in Powershel, for example) an error will be returned. Then you need to restart the computer. If this does not help - to reinstall git (remove and reinstall). "%} 

#### Algorithm setup Ember CLI 

{% include important.html content="Before you install Ember CLI, you must first install Node.js and Bower" %} 

1. When npm and bower installed, you need to install [Ember CLI](https://ember-cli.com/), the command will be `npm install-g ember-cli@2.4.3`. 
2. After installing Ember CLI, enter the command `ember -v`, if issued, the version number is the installation was successful. 

## Tools for working with Docker images 

* [Docker](https://www.docker.com/get-started) 

[Deployment of solutions in the Docker container](gbt_deployment_docker.html) 

#### Algorithm setup 

1. Install Docker Desktop. 
2. Enable virtualization in the BIOS. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/