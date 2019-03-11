--- 
title: Service record SQL-scripts change the data 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, monitor tasks, changing data 
summary: a Sample implementation of write SQL scripts using the task monitor 
toc: true 
permalink: en/fo_changes-sql-bt-monitor.html 
lang: en 
autotranslated: true 
hash: a710c7d0df6a85458dfbe188f74fd7754cede2a831fa96019fc033c3a7b54e19 
--- 

## Description 

`ICSSoft.Services.ChangesToSqlBTMonitor` is a kind [task monitor](fo_business-task-monitor.html), which allows you to record SQL-scripts change the data. 

## the service Connection 

To connect this service, you need in the configuration file to write: 

```xml
    <add key="BusinessTaskMonitorType" value="ICSSoft.Services.ChangesToSqlBTMonitor, ChangesToSqlBTMonitor, Version=1.0.0.1, Culture=neutral, PublicKeyToken=e5ad39f116a43e0b"/>
``` 

## use of the service 

Example handlers for menu items: 

```csharp
private void RecorderClearClick(object sender, EventArgs e)
{
	ICSSoft.Services.ChangesToSqlBTMonitor.Clear();
}

private void RecorderSaveClick(object sender, EventArgs e)
{
	if (saveFileDialog.ShowDialog(this) == DialogResult.OK)
	{
		string fileName = saveFileDialog.FileName;

		StreamWriter writer = new StreamWriter(fileName, false, Encoding.UTF8);

		// Have the writer write strings to the store. 
		writer.Write(ICSSoft.Services.ChangesToSqlBTMonitor.Record);

		writer.Close();
	}
}
``` 

So in the output script to avoid unnecessary queries, you can configure a filter on the table names in the configuration file 

```xml
    <add key="ChangesToSqlExcept" value="AuditSystem,STORMSETTINGS,Audiopiracy,Auditionee"/>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}