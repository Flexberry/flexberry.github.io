--- 
title: Creating module extensions 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, create a module, example, debugging 
summary: an Example of creating a module and its debugging 
toc: true 
permalink: en/fd_plugins-development.html 
lang: en 
autotranslated: true 
hash: 40243aa2cd91c5f432ec40c7422e074036f62db44058eb362b70b58311333bfb 
--- 

## Example of creating a simple extension module for Flexberry Designer 

**You must connect the library:** 

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
            
            // Operations available for the stage. 
            _stageFeatures = new NamedDoMethod[] { new NamedDoMethod("Take the name of the stage", new DoMethodDelegate(ShowStageName)) };
            
            // Operations available for the configuration. 
            _configurationFeatures = new NamedDoMethod[] { new NamedDoMethod("Display name"configuration, new DoMethodDelegate(ShowConfigurationName)) };
        }
        #endregion
        #region Methods
        public void ShowStageName(object oStage)
        {
            // Stage, or its successor STORMCASE.STORMNET.Repository.Stage. 
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

## Debugging extension module 

To debug an extension module, in the configuration file Flexberry Designer to add the following setting: 

```xml
<add key="GenerationDebug" value="true"/>
``` 

If the setting is, in some cases, output in a separate process you will see a message when you can connect to the new created process. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}