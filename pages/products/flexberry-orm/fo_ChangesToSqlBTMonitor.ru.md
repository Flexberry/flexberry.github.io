---
title: Сервис записи SQL-скриптов изменения данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_changes-to-sql-b-t-monitor.html
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">

* **Продукт:** [Flexberry ORM](fo_flexberry-orm.html)
* **Компонент:** [Монитор задач](business-task-monitor.html)
* **Предназначение:** краткое описание назначения контрола.
* **Программная библиотека:** ChangesToSqlBTMonitor.dll
* **[Автодокументация](http://www.google.ru).**
* **[Демонстрационное приложение](https://github.com/Flexberry/FlexberryORM-DemoApp).**
* **[0|Учебный курс].**

</td>
</tr></tbody></table></a>
</div>

## Описание
ICSSoft.Services.ChangesToSqlBTMonitor представляет собой разновидность монитора задач, который позволяет осуществлять запись SQL-скриптов изменения данных.

## Подключение сервиса
Чтобы подключить данный сервис, нужно в конфигурационном файле написать:

```xml
    <add key="BusinessTaskMonitorType" value="ICSSoft.Services.ChangesToSqlBTMonitor, ChangesToSqlBTMonitor, Version=1.0.0.1, Culture=neutral, PublicKeyToken=e5ad39f116a43e0b"/>
```

## Использование сервиса
Пример обработчиков пунктов меню:

```cs
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