--- 
title: the Use of quick prototypization 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, prototype, prototypical, generation, stereotype, app, objects, forms, assemblies 
summary: code Generation of a prototype application 
toc: true 
permalink: en/fd_using-quick-prototyping.html 
lang: en 
autotranslated: true 
hash: 05b049fdbf81435a042bdd19dc305d0cff673855dccdc246782090bc50951bcf 
--- 

Fast prototypical handy when you need to quickly create and run the system. 

That is generated prototypical: 

1. Generates a class with the stereotype [Application](fd_application.html) 
2. Generates [L-](fd_l-view.html) and [E-](fd_e-view.html) representation for each data class 
3. Generates classes with the stereotype [ListForm](fd_listform.html) for each data class 
4. Generates classes with the stereotype [EditForm](fd_editform.html) for each data class 
"__Note__: data class are classes with the stereotype [implementation](fd_data-classes.html) or no stereotype." 

For playback the following example requires that you have installed SQL `MS Server`. 

### Example of creating an information system 

The application "Telephone directory" 
1.To create a new project, name it "View". 
2.To create consistently in the project configuration stage, with any names. 
3.To create under a system call "Home". 
4.To create a class diagram, name "Structure". 
5.To put on the chart and linked classes. 

![Chart Telephone directory](/images/pages/products/flexberry-designer/generate/phonebook1.png) 

to save the graph. 
6.Position the cursor on a system where a chart, then select the menu item `Winforms\Utilities\Create predstavleniyam forms and приложение` (for this example): 

![Beginning of prototypical](/images/pages/products/flexberry-designer/generate/startprototyping.png) 

*For ASP.NET applications should use the plugin ASP.NET.* 

7.Enter the application name in the window that appears and click "OK": 

![Prototypical](/images/pages/products/flexberry-designer/generate/startprototyping1.png) 

Next to the chart "Structure" (in the same system) will automatically create a graph "View" that contains the classes of the forms (edit and list) and the class app: 

![Chart Telephone directory](/images/pages/products/flexberry-designer/generate/phonebook2.png) 

For each object will be created 2 views: 

* list and edit form 
* add a description (Description, Caption). 
8.To access the class properties-applications and change configuration connection settings to the data source: server name (**SERVER=**) and database (**DATABASE=** database does not have to exist). 

__Note__: the server Address is can be a personal computer (**localhost**).

![Configuration properties](/images/pages/products/flexberry-designer/generate/configprops.png) 

9.To edit the properties of a stage [settings for MS SQL Server direct generator](fd_configure-ms-sql-generator.html) (connection string to the data source must match that specified in application configuration). 

![Database connection](/images/pages/products/flexberry-designer/generate/called configbase.png) 

10.To edit [property phase](fd_configure-ms-sql-generator.html) (provide company name and product, as well as set the directory generation) 
11.To select "MS SQL Server direct generator\align...". Flexberry will create the database and generates the scripts to create data structures, apply them. 

__Note__: if a generation error occurs, it is necessary 

* Verify p. 9 (not specified in the connection string SERVER=server;DATABASE=database - if specified, must change in accordance with reality) 
* If everything is configured correctly, but the error continues to occur, contact support. 

12.Build the application 

* Web application: article [generation Plugin Flexberry ASP.NET](fa_asp-net-generator.html) 
* Windows application: select the menu item "Winforms/C#/Generate and kapeliovich". Flexberry will generate a - compile system and then prompts you to launch the app: 

![Launch application](/images/pages/products/flexberry-designer/generate/startapp.png) 

Click "OK", the application will start: 

![Phone book app](/images/pages/products/flexberry-designer/generate/phonebookapp.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}