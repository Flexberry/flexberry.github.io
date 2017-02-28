---
title: Создание и подключение монитора задач [Монитор задач](business-task-monitor.html)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_creating-and-connection--business-task-monitor.html
---
##Создание монитора задач
В связи с задачей мониторинга существует два вопроса, которые разработчик должен решить:

    1. Какой монитор задач должен предоставляться (выбор стандартного монитора задач или создание какого-то собственного);
    2. Подключение монитора задач непосредственно к системе.

Если разработчику требуется какая-то особая функциональность (например, запись задач в log-файл или какое-то иное отображение), может потребоваться создать свой монитор задач. Для этого, необходимо создать класс с реализованным интерфейсом ICSSoft.STORMNET.Business.IBusinessTaskMonitor.

**Монитор задач, который отображает задачи в Windows Application Log**

```
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
            System.Diagnostics.EventLog.WriteEntry(string.Format("Подзадача задачи № {0} началась.", sTaskID), SubTask);
            return null;
        }

        public void EndSubTask(object SubTaskID)
        {
            string sTaskID = SubTaskID==null?"N/A":SubTaskID.ToString();
            System.Diagnostics.EventLog.WriteEntry(string.Format("Подзадача {0} закончилась.", sTaskID), "");
        }

        public void EndTask(object ID)
        {
            string sTaskID = ID==null?"N/A":ID.ToString();
            System.Diagnostics.EventLog.WriteEntry(string.Format("Задача № {0} закончилась.", sTaskID), "");
        }
 
        public void BeginTask(string TaskName, object ID)
        {
            string sTaskID = ID==null?"N/A":ID.ToString();
            System.Diagnostics.EventLog.WriteEntry(string.Format("Задача № {0} началась.", sTaskID), TaskName);
        }

        object ICSSoft.STORMNET.Business.IBusinessTaskMonitor.BeginTask(string TaskName)
        {
            Guid taskID=Guid.NewGuid();
            BeginTask(TaskName,taskID);
            return taskID;
        }
        #endregion
    }
```

Подключение EventTaskMon из вышеприведённого примера к приложению через настройку конфигурационного файла будет следующим:

```
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
    <appSettings>
    <add key="BusinessTaskMonitorType" value="CustomTaskMon.EventTaskMon, CustomTaskMon, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
    </appSettings>
</configuration>
```

**Внимание!**
Сборка с монитором задач должна обязательно находиться в том же каталоге, что и приложение.

Компоненты Flexberry ORM используют монитор (например, сервис данных пишет туда запросы). Так что, при реализации этого примера, в Windows Application Log появятся записи, соответствующие чтению списка:
![](/images/pages/DPriss/BusinessTaskMonitor.jpg)
