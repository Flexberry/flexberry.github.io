--- 
title: LINQPad 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, database, Constraints 
summary: Installation, connection, use LINQPad and work with queries 
toc: true 
permalink: en/fo_linq-pad.html 
lang: en 
autotranslated: true 
hash: 41bdf6dd112e8f1635c52981e170f1c50e0f40cef6c89bc16efc605568d5b6e3 
--- 

[LINQPad](http://linqpad.net) allows you to write queries in LINQ and test execution results on real data without much hassle. As a bonus, LINQPad allows you to quickly see the SQL query that goes to database when using LINQ in a real world environment. 

For Flexberry DataService written a special provider, which greatly simplifies the work in LINQPad with our projects. 

## Install LINQPad and connect to working database 

To install LINQPad and the provider [the official website](http://linqpad.net). 
* File `LINQPad4.zip` or `LINQPad4 is AnyCPU.zip` (for 64-bit CPU). 
* The driver file that supports Flexberry DataService: `SQLDataServiceLinqPadDriver.lpx` 
* Unpack the archive to a convenient place and put next provider. 
* Run `LINQPad.exe` 
* In the opened window, click `Add connection` 

![](/images/pages/products/flexberry-orm/query-language/acc-connection.PNG) 

* In the next window click `View more drivers...` 
* Click `Browse...` and specify the path to the file with the provider (`SQLDataServiceLinqPadDriver.lpx`). 
* In the window `Choose Data Context` to choose `Flexberry SQLDataService` and click `Next >` 
* In the appeared window specify the path to the Assembly data objects, and the file of configurations of the application (because it uses the connection string to the database). After clicking `OK` the new connection will appear in the main window of the program in the list of connections. 

Primechanie: users of Windows XP at this point, the program may be issued a critical error. You should restart the program, the connection will be added. 

## Using LINQPad 

Added a connection you can expand to see a list of classes. Each class also can be expanded to see the list of fields and views (views are shown from the inner class object data Views). 

![](/images/pages/products/flexberry-orm/query language/expanded-tree.png) 

To write a query, you must call the context menu of the connection and select the menu item `New Query` 

In the right window to write the query 

```sql
from k in Кредит
select k 
``` 
`C# Expression` selecting from the drop-down list `Language`. 

Odnako, due to some problems when using dynamic views, it is recommended to use queries of the form 

```csharp
ds.Query<IIS.Кредиты.Кредит>(IIS.Кредиты.Кредит.Views.КредитE).Dump();
``` 

with explicit representation, which shall be deducted the data.

Primechanie 1_: you must select `C# Statement(s)` from the drop-down list `Language` 

Primechanie 2_: Dump() at the end of compulsory writing, without just konstruiruet the request, but database will not go away and the results do not provide. 

Here `ds` is a constant driver that points to the instance of the data service to simplify writing queries. 

To determine the namespace of the class it is enough to put him in the tree on the left, will come out a tooltip with the full name of the class. 

A request indicating the constraints will look like a regular query using [LINQProvider](fo_linq-provider.html): 

```sql
ds.Query<IIS.Кредиты.Кредит>(IIS.Кредиты.Кредит.Views.КредитE).Where(k => k.СуммаКредита >= 100000).Dump();
``` 

![](/images/pages/products/flexberry-orm/query language/query.png) 

### the Context of execution of requests 

The query text that is written in the editor is actually inserted in a dynamically compiled class - data context. The request is one of the methods to this context. In this context connected using-and for all namespaces, which have classes of data objects from the Assembly. Also to the compiled context connects a number of assemblies from Flexberry required to work LINQProvider. These assemblies are first searched in the directory with the Assembly of data objects, if they are not there, then take the Assembly out of the driver. In addition, automatically added the Assembly references depends on the Assembly of data objects, and which are located in the same directory. 

Another important detail: in the constructor of the context data, this is a method to disable the authorization checks because authorization is not available. 

```csharp
ICSSoft.STORMNET.RightManager.DisableAllRightChecks();
``` 

### Add references to additional assemblies with objects 

If there is a need to use multiple files with assemblies of objects, you can add them as follows: 

* Call `контекстное меню` in the input field of the query and choose Properties...`Query ` 
* Tab `Additional References` point the way to additional assemblies of objects 
* Tab `Additional Imports` Namespace specify the namespace (you can choose from attached assemblies). 

### Output 

By default, the results are interesting, but unreadable. To switch to tabular output method, just click on the button at the top of the plot area requests. 

Primechanie: automatic switching of output format has already been done request is not going to happen, you must run the query again. 

## SQL 

As a nice bonus given the option of viewing generated SQL query-expression. 

To view the SQL statement, it is sufficient to switch to the SQL tab of the results pane of the query. 
If the database is a few queries, for example, if the submission involves detaily, the tab will show all the queries, separated by an empty line. 

![](/images/pages/products/flexberry-orm/query language/sql.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}