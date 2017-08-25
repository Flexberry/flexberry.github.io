---
title: Сервис записи SQL-скриптов изменения данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, монитор задач, изменение данных
summary: Пример реализации записи SQL-скриптов с помощью монитора задач
toc: true
permalink: ru/fo_changes-sql-bt-monitor.html
lang: ru
---

## Описание

`ICSSoft.Services.ChangesToSqlBTMonitor` представляет собой разновидность [монитора задач](fo_business-task-monitor.html), который позволяет осуществлять запись SQL-скриптов изменения данных.

## Подключение сервиса

Чтобы подключить данный сервис, нужно в конфигурационном файле написать:

```xml
    <add key="BusinessTaskMonitorType" value="ICSSoft.Services.ChangesToSqlBTMonitor, ChangesToSqlBTMonitor, Version=1.0.0.1, Culture=neutral, PublicKeyToken=e5ad39f116a43e0b"/>
```

## Использование сервиса

Пример обработчиков пунктов меню:

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

Чтобы в выходном скрипте не было лишних запросов, можно настроить фильтр по именам таблиц в конфигурационном файле

```xml
    <add key="ChangesToSqlExcept" value="Аудит_Сессия,STORMSETTINGS,АудитОперации,АудитИзменения"/>
```
