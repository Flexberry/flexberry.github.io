--- 
title: Modules to extend the functionality 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, connectivity module, registration module, the creation module 
summary: How to connect and configure modules to generate applications, databases and working with charts 
toc: true 
permalink: en/fd_flexberry-plugins.html 
lang: en 
autotranslated: true 
hash: 78aa4d97ae039f900b84515a1bdc8e9aa3312fedd00d6c9f7f0029495e3c641d 
--- 

Expansion modules add functionality in [Flexberry Designer](fd_landing_page.html), providing new opportunities. The module can be generator source code search utility, import / export, etc. 

When using the expansion modules have additional menu items when you select repository objects Flexberry Designer: [Configuration Stage or System](fd_recommended-structure-repository.html). 

The modules are stored in libraries (. DLL) and placed in the folder with the executable file `Flexberry Designer`. 

## Connection module 

Before using the modules, they should be connected to `Flexberry Designer`: 

1. To register the modules in the database that stores the repository моделей; 
2. For a specific repository (or Project, or Configurations, or Stages) to choose from registered modules are in the appropriate repository (Project, Configuration or Stage). 

### Check modules in database repositories 

1.Select the menu Configuration\Modules: 

![](/images/pages/products/flexberry-designer/about/pluginsreg.png) 

2.Click `Создать` in the Toolbox list of modules: 

![](/images/pages/products/flexberry-designer/about/addplugin.png) 

3.To select a module is dynamic - link library (*.dll), where is the module 
4.Repeat from step 2 if you need to register several modules. 

### Selection module for repository, project, configuration or stage 

1.To open the form for editing the properties of a repository, project, configuration or stage: 

![](/images/pages/products/flexberry-designer/about/editrepprop.png) 

2.To select the necessary modules: 

![](/images/pages/products/flexberry-designer/about/propeditselectmodules.png) 

3.Set in the toolbar Save button. 
4.The modules are connected. 

## Standard modules Flexberry Designer 

* [Expander Flexberry ORM](fo_orm-case-plugin.html) 
* [Expander Flexberry ASP.NET](fa_asp-net-generator.html) 
* [Expander Flexberry Ember](ef_generator.html) 
* [Expander WinForms Flexberry](fw_flexberry-winforms-case-plugin.html) 

## How to implement your extension module Flexberry Designer 

[How to create your own extension module for Flexberry Designer is described in a separate article.](fd_plugins-development.html) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/