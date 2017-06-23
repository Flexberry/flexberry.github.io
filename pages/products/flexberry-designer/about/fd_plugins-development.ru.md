---
title: Создание модуля расширения
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, создание модуля, пример, отладка
summary: Пример создания модуля и его отладка
toc: true
permalink: ru/fd_plugins-development.html
lang: ru
---

## Пример создания простейшего модуля расширения для Flexberry Designer

**Необходимо подключить библиотеки:**

* ICSSoft.STORMNET.DataObject 
* Repository 
* STORM.NET Case Tool plugin

```csharp
using System;
using System.Collections.Generic;
using System.Text;
using STORMCASE.Plugin;
using STORMCASE.Repository;
using System.Windows.Forms;
namespace STORMCASE.Plugin
{
    public class OurPlugin : CasePlugin
    {
        #region private members
        private NamedDoMethod[] _stageFeatures;
        private NamedDoMethod[] _configurationFeatures;
        private Type[] _stageTypes = new Type[] { typeof(STORMCASE.STORMNET.Repository.Stage) };
        private string _name;
        private string _description;
        #endregion
        #region CasePlugin members
        public override string Description
        {
            get { return _description; }
        }
        public override void EditSettings()
        {
        }
        public override NamedDoMethod[] GetFeatures(object SelectedObject)
        {
            if (SelectedObject.GetType() == typeof(STORMCASE.Repository.Configuration))
                return _configurationFeatures;
            else if (SelectedObject.GetType() == typeof(STORMCASE.STORMNET.Repository.Stage))
                return _stageFeatures;
            else return null;
        }
        public override Type[] GetStageTypes()
        {
            return _stageTypes;
        }
        public override string Name
        {
            get { return _name; }
        }
        public override string Settings
        {
            get
            {
                return null;
            }
            set
            {
            }
        }
        protected override void prv_SetDefaultSettings()
        {
        }
        #endregion
        #region Constructors
        public OurPlugin()
        {
            _name = "Our plugin name";
            _description = "Example of plugin";
            
            // Операции, доступные для стадии.
            _stageFeatures = new NamedDoMethod[] { new NamedDoMethod("Вывести имя стадии", new DoMethodDelegate(ShowStageName)) };
            
            // Операции, доступные для конфигурации.
            _configurationFeatures = new NamedDoMethod[] { new NamedDoMethod("Вывести имя конфигурации", new DoMethodDelegate(ShowConfigurationName)) };
        }
        #endregion
        #region Methods
        public void ShowStageName(object oStage)
        {
            // Stage, либо его наследник STORMCASE.STORMNET.Repository.Stage.
            Stage stage = oStage as Stage;
            MessageBox.Show(stage.Name);
        }
        public void ShowConfigurationName(object oConf)
        {
            Configuration conf = oConf as Configuration;
            MessageBox.Show(conf.Name);
        }
        #endregion
    }
}
```

## Отладка модуля расширения

Для того чтобы отлаживать модуль расширения, можно в конфигурационном файле Flexberry Designer добавить следующую настройку:

```xml
<add key="GenerationDebug" value="true"/>
```

Если настройка стоит, то в некоторых случаях при выходе в отдельный процесс будет выведено сообщение, когда можно производить подключение к новому созданному процессу.
