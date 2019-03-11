--- 
title: Creating and connecting the monitor task 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, the monitor task example 
summary: Example of creating and connecting the monitor task 
toc: true 
permalink: en/fo_creating-connection-bt-monitor.html 
lang: en 
autotranslated: true 
hash: 94ecd2ba65da069d14e3333012e043a4c0f1d392ca48a59ff718fdbd260aea14 
--- 

## create a monitor task 

In connection with the [task monitor](fo_business-task-monitor.html) there are two issues that the developer must address: 

* What is the task monitor must be provided (standard monitor task or creating some private); 
* Monitor the status of tasks directly to the system. 

If you require some special functionality (e.g., tasks in `log` file or some other display), you may need to create a monitor task. To do this you must create a class which is implemented interface `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. 

**Monitor tasks, which displays tasks in the Windows Application Log** 

```csharp
using System;
using ICSSoft.STORMNET.Business;
namespace CustomTaskMon
{
	public class EventTaskMon:IBusinessTaskMonitor
	{
		public EventTaskMon()
		{
		}

		#region IBusinessTaskMonitor Members
		public object BeginSubTask(string SubTask, object TaskID)
		{
			string sTaskID = TaskID==null?"N/A":TaskID.ToString();
			System.Diagnostics.EventLog.WriteEntry(string.Format("Subtask task number {0} started.", sTaskID), SubTask);
			return null;
		}

		public void EndSubTask(object SubTaskID)
		{
			string sTaskID = SubTaskID==null?"N/A":SubTaskID.ToString();
			System.Diagnostics.EventLog.WriteEntry(string.Format("Sub-task {0} ended.", sTaskID), "");
		}

		public void EndTask(object ID)
		{
			string sTaskID = ID==null?"N/A":ID.ToString();
			System.Diagnostics.EventLog.WriteEntry(string.Format("The task {0} ended.", sTaskID), "");
		}

		public void BeginTask(string TaskName, object ID)
		{
			string sTaskID = ID==null?"N/A":ID.ToString();
			System.Diagnostics.EventLog.WriteEntry(string.Format("The task {0} started.", sTaskID), TaskName);
		}

		object ICSSoft.STORMNET.Business.IBusinessTaskMonitor.BeginTask(string TaskName)
		{
			Guid taskID=Guid.NewGuid();
			BeginTask(TaskName,taskID);
			return taskID;
		}
		#endregion
	}
}
``` 

Connection `EventTaskMon` from the above example the application via a configuration file would be as follows: 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
	<appSettings>
	<add key="BusinessTaskMonitorType" value="CustomTaskMon.EventTaskMon, CustomTaskMon, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
	</appSettings>
</configuration>
``` 

{% include important.html content="the Assembly with the task monitor must be located in the same directory as the application." %} 

Components [Flexberry ORM](fo_flexberry-orm.html) use [monitor](fo_business-task-monitor.html) (e.g., [service data](fo_data-service.html) writes back the query). Therefore, the implementation of this example in `Windows Application Log` will have entries corresponding to the read list: 

![](/images/pages/products/flexberry-orm/business-task-monitor/business-task-monitor.jpg) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}