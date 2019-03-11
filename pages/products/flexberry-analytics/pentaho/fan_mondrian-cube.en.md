--- 
title: creating a Mondrian schema for OLAP cube 
keywords: OLAP, cube, dimension, diagram, connection, database 
sidebar: flexberry-analytics_sidebar 
toc: false 
permalink: en/fan_mondrian-cube.html 
lang: en 
autotranslated: true 
hash: 112905c1f73e78145483162cdbeb899395fe00b44141868d31c280b7f6d5f46e 
summary: 
--- 

`Mondrian` server OLAP (analytical processing in real time) with open source codes written in `Java`. Developed and maintained by the Corporation `Pentaho`. 

To create the schema you will need to use a desktop application `Pentaho schema workbench`. 

`OLAP-куб` — (_On-Line Analytical Processing_ — interactive data analysis) multidimensional array data are usually sparse and long-term stored, used in OLAP. Can be implemented on the basis of a universal relational DBMS or specialized software. For details, see [OLAP cube](https://ru.wikipedia.org/wiki/OLAP-куб). 

## the creation of the schema 

To generate the schema, you must run the action: `File -> new -> schema`. The attributes of the new scheme to fill `name` (name of scheme). 

To save the schema, you need to run the action: `File -> save`. 

## configure a connection to a database 

{% include important.html content="the database Name should be in English only." %} 

Sets the DB connection: `Options -> connection`. In the window that appears in section `General` to fill: 

* Connection name – Name for подключения; 
* Connection Type = Postgre SQL – Type storage данных; 
* Access = Native (JDBC) – Type подключения; 
* Settings: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-001.png) 


## cube Creation 

* Created schema, you must create a cube (PCM scheme and click Add cube» qmo) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-002.png) 

The attributes of the new cube to fill: 

* name – the name of the cube. 

![](/images/pages/products/flexberry-analytics/mondrian-cube-003.png) 

* Next, you need to add the fact table for the cube (RMB on the cube and click Add Table» qmo); 

![](/images/pages/products/flexberry-analytics/mondrian-cube-004.png) 

The attributes of the new table to fill in: 

* schema diagram БД; 
* name – fact table for the cube. 

![](/images/pages/products/flexberry-analytics/mondrian-cube-005.png) 

## Create A Measure 

Add measure to the cube (RMB on the cube and select the Measure» «Add) and fill: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-006.png) 

* name – the name of the action (Displayed in the web interface); 
* aggregator – a type of calculation that takes one of the values: 

* sum; 
* count; 
* min; 
* max; 
* avg; 
* distinct-count.
* column – the name attribute of the fact table, which will be considered as a measure. 

![](/images/pages/products/flexberry-analytics/mondrian-cube-007.png) 

## Create A Dimension 

### Measurement scheme 

The measurement scheme is used in cases when within the same schema creates several cubes. For multiple cubes, you can use a single measurement scheme. Thus we only need to add a reference to this dimension in the cube. 

1.To create a new dimension for the circuit (PCM scheme and click on the Dimension). 

![](/images/pages/products/flexberry-analytics/mondrian-cube-008.png) 

If a dimension is created for the schema, not the Cuba of the essential attributes only the name field is the name. 
Field type – the type of measure is populated by default value StandartDimension for time measurements. For time measurements is populated with the value of the TimeDimension. 

![](/images/pages/products/flexberry-analytics/mondrian-cube-009.png) 

2.The disclosure of the measurement appears in the hierarchy. Required attributes for hierarchy: 

* name – name of hierarchy 
* primarykey - column in the dimension table, the values of which coincide with any column of the tables (s) of facts (for the connection of cube and dimension) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-010.png) 

3.Add table to measure (PCM hierarchy and click on Add Table). Among the mandatory attributes, the level has the following: 

* schema diagram БД; 
* name – the name of the table 

4.After the table is added, we similarly need to add at least one level (level). Among the mandatory attributes, the level has the following: 

* name – the name уровня; 
* column (the column that will be displayed as a dimension in the cube). 

![](/images/pages/products/flexberry-analytics/mondrian-cube-011.png) 

5.To use the dimension diagrams in the cube, you need to click RMB on the desired cube and click add dimension usage 

![](/images/pages/products/flexberry-analytics/mondrian-cube-012.png) 

Required attributes: 

* name – the name of the dimension in the cube. 
* foreign key – a column in the fact table whose values must match the key specified in the dimension hierarchies. 
* source – select the dimension that will be used. 

Optional attribute: 

* caption – the name for the measurement that will be carried for a specific cube. 

![](/images/pages/products/flexberry-analytics/mondrian-cube-013.png) 

### Dimension for the cube 

1.To use a dimension for a cube, you need to click RMB on the desired cube and click add dimension 

![](/images/pages/products/flexberry-analytics/mondrian-cube-014.png) 

If a dimension is created for cube, then fill in the following fields: 

* name – имя; 
* type – the type of measure is populated by default value StandartDimension for time measurements. For time measurements is populated with the value of the TimeDimension. 
* foreignKey – foreign key to link to the fact table of the cube.

![](/images/pages/products/flexberry-analytics/mondrian-cube-015.png) 

2.The disclosure of the measurement appears in the hierarchy. Required attributes for hierarchy: 

* name – name of hierarchy 
* primarykey - column in the dimension table, the values of which coincide with any column of the tables (s) of facts (for the connection of cube and dimension) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-016.png) 

3.Add table to measure (PCM hierarchy and click on Add Table). Among the mandatory attributes, the level has the following: 

* schema diagram БД; 
* name – the name of the table 

4.After the table is added, we similarly need to add at least one level (level). Among the mandatory attributes, the level has the following: 

* name – the name уровня; 
* column (the column that will be displayed as a dimension in the cube). 

![](/images/pages/products/flexberry-analytics/mondrian-cube-017.png) 


### Dimension JOIN 

Used class diagram: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-018.png) 

1.Create A Dimension 
2.In an already established hierarchy RMB and create Join 
3.Fill in the Join table references 

![](/images/pages/products/flexberry-analytics/mondrian-cube-019.png) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-020.png) 

4.In the Join you need to specify the primarykey and foreignkey 

![](/images/pages/products/flexberry-analytics/mondrian-cube-021.png) 

5.Create A Level: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-022.png) 

6.Fill in the Hierarchy to associate with a fact table 

![](/images/pages/products/flexberry-analytics/mondrian-cube-023.png) 

7.In the cube you need to create a Dimension Usage 

![](/images/pages/products/flexberry-analytics/mondrian-cube-024.png) 

The cube schema: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-025.png) 

### Dimension hierarchy 

#### Use Levels 

1.Model: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-026.png) 

2.To create a new dimension for the scheme or for the cube 

![](/images/pages/products/flexberry-analytics/mondrian-cube-027.png) 

If you intend to work with dates, it should be noted attribute type = TimeDimension 
3.Next, fill in the Hierarchy 
4.Add a table for the hierarchy 
5.After creating the first level: 

* Fill In The Year: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-028.png) 

{% include important.html content="For first level is required to tick uniqueMembers = true." %} 

* Fill In The Month: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-029.png) 

#### Using Closure Table 

0.Model: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-030.png) 

1.To create a new dimension for the scheme or for the cube 

![](/images/pages/products/flexberry-analytics/mondrian-cube-031.png) 

2.Next, fill in the Hierarchy 

![](/images/pages/products/flexberry-analytics/mondrian-cube-032.png) 

3.Add a table for the hierarchy 

![](/images/pages/products/flexberry-analytics/mondrian-cube-033.png) 

4.Further, in the disclosed hierarchy need to add a level 

![](/images/pages/products/flexberry-analytics/mondrian-cube-034.png) 

5.In the generated level, you need to add Closure 

![](/images/pages/products/flexberry-analytics/mondrian-cube-035.png) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-036.png) 

6.Fill in Closure, select the Table in the desired table 

![](/images/pages/products/flexberry-analytics/mondrian-cube-037.png) 

7.To complete a level, it is necessary to fill fields: 

* name – the name уровня; 
* table – select from the drop-down list for table измерения; 
* column – attribute primary key таблицы; 
* nameColumn attribute to display the value измерения; 
* the parentColumn attribute is for a key родителя; 
* type – the type данных; 
* uniqueMembers – mark the checkbox to mark that this level is the primary счету; 
* levelType – Regular; 
* hideMemberlf – Never 
* caption – the display name 

![](/images/pages/products/lexberry-analytics/mondrian-cube-038.png) 

8.Fill in the attributes Closure parentColumn and childColumn values from drop down lists: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-039.png) 

9.For a cube to create a link to measurement: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-040.png) 

10.To Fill In The Dimension Usage: 

![](/images/pages/products/flexberry-analytics/mondrian-cube-041.png) 

## Create a virtual cube 

To display multiple cubes in one schema function create the virtual cubes. This requires: 

1.Create at least two of the cube dimensions and measures 

2.In an open circuit, you need to create a virtual cube (PCM scheme and click `Add virtual cube`) 

![](/images/pages/products/flexberry-analytics/mondrian-cube-042.png) 

3.Fill in the window that appears, name (name) for a virtual cube 

4.To add to the virtual cube is references to dimension normal cubes, for this: 
* RMB on the virtual cube and virtual cube choose `Add dimension` 

![](/images/pages/products/flexberry-analytics/mondrian-cube-043.png) 

* In the resulting window, a virtual measurement to fill in the attributes: name and cubeName exactly as they are called in the source cube 

![](/images/pages/products/flexberry-analytics/mondrian-cube-044.png) 

5.Add in a virtual cube reference measures a normal cubes for this: 
* RMB on the virtual cube and virtual cube choose `Add measure` 

![](/images/pages/products/flexberry-analytics/mondrian-cube-045.png) 

* In the resulting window, a virtual measurement to fill in the attributes: name and cubeName exactly as they are called in the source cube 

{% include important.html content=" the name Parameter to fill in the format: [Measures].[<Measure name>]." %} 

![](/images/pages/products/flexberry-analytics/mondrian-cube-046.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}