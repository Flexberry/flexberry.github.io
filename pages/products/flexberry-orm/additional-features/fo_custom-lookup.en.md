--- 
title: Arbitrary control as lucapa 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, control, generation, stage, example 
summary: Guidelines for creating a custom control as lucapa 
toc: true 
permalink: en/fo_custom-lookup.html 
lang: en 
autotranslated: true 
hash: 37a9c1b39a3f5622470add8b404ff64f6ef489406d75d3567d13914e73b29533 
--- 

## Requirements for control 

In order to be able to generate arbitrary control as lucapa, it is necessary to implement interface `ICSSoft.STORMNET.Windows.Forms.ILookup`. If the control does not implement the interface `ICSSoft.STORMNET.Windows.Forms.ICustomizableControl`, it is simply generated without adequate settings of their own properties. 

In the example we will generate `ExtLookUpTextControl`. 

{% include important.html content="currently it is better to use [ExtendedLookUp](fw_extended-lookup.html) instead `ExtLookUpTextControl` (both control are in the same library)." %} 

## Generation 

In the settings stage you need to choose the "Advanced" tab and click "advanced settings". 
![](/images/pages/products/flexberry-orm/additional-features/i-lookup1.JPG) 

Select the Assembly that contains the control. 
The value in column "Name" can be changed. 

![](/images/pages/products/flexberry-orm/additional-features/i-lookup2.JPG) 

To open the object representation where there is a link to the wizard. And in the settings lucapa specify: 

* `Тип лукапа` - custom 
* In the ComboBox `Имя Лукапа` is the name of the control that was specified in the settings of the stage 
* `Свойство мастера` - property master, to which will be attached (binding) control. For example, for predictive. 

![](/images/pages/products/flexberry-orm/additional-features/i-lookup3.JPG) 

Generate the application as usual. Start. In the end, you will receive a form with the generated control. 

![](/images/pages/products/flexberry-orm/additional-features/i-lookup4.JPG) 

## the resulting code 

In the generated form is created control 

```csharp
protected ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl ctrlПорода;
``` 

Added information about control 

```csharp
m_arrLookupInformations.Add(new ICSSoft.STORMNET.UI.LookupInformation(this.ctrlПорода, "Breed", "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/ctrlПорода(Ext" + "LookUpTextControl)", null));
``` 

Prescribed binding for `EditManager` 

```csharp
EditManager.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("Kilkuskie", typeof(IIS.КошкиСЛапами.Кошка), new ICSSoft.STORMNET.Windows.Forms.Design.OneBind[] {
            new ICSSoft.STORMNET.Windows.Forms.Design.OneBind(ctrlПорода, typeof(ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl), "Value", null, "Breed"),
            ...
            );
``` 

Tabulated properties of the control 

```csharp
ctrlПорода.DataObjectTypes = null;
ctrlПорода.EditManagerForBind = null;
ctrlПорода.HierarchyField = "";
ctrlПорода.InGridProperty = null;
ctrlПорода.LimitFunction = null;
ctrlПорода.Location = new System.Drawing.Point(56, 12);
ctrlПорода.MaximumSize = new System.Drawing.Size(3000, 20);
ctrlПорода.Name = "ctrlПорода";
ctrlПорода.Property = null;
ctrlПорода.PropForBind = "";
ctrlПорода.PropForUpdate = "";
ctrlПорода.ReadOnly = false;
ctrlПорода.ReadOnlyText = false;
ctrlПорода.Size = new System.Drawing.Size(150, 20);
ctrlПорода.StringedValue = null;
ctrlПорода.SymbolsToIntellisense = 1;
ctrlПорода.TabIndex = 1;
ctrlПорода.Value = null;
ctrlПорода.ViewName = null;
ctrlПорода.ИспользоватьОтображСвойствоПриВводе = false;
ctrlПорода.ОтображаемоеСвойство = null;
ctrlПорода.ОтображатьВсеПоляПриLookup = true;
ctrlПорода.РежимВводаЗначений = false;
ctrlПорода.РежимМастерСтрокой = false;
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}