--- 
title: Create edit form with a hierarchical list 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms 
summary: See how to manually put on the form of a hierarchical list of objects 
toc: true 
permalink: en/fw_object-hierarchical-view-on-editform.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 08b550585b34acca51ae4d2147125748b90bb9170e30886b736a9edad823b98f 
--- 

There are times when on the edit form, you need to create a hierarchical list. 

For example, in the hierarchical model looks like this: 

![](/images/pages/products/flexberry-winforms/controls/olv/object-hierarchical-view.png) 

However, by default generates a separate form to display a hierarchy (in this case, "Territory)". 
To the hierarchical list displayed on the edit form, the form you need to add the control ObjectHierarchicalView. 
To do this you must perform several actions: 
* Generate the edit form. 
* Add to the form in Form Designer Fields: 

```csharp
// *** Start programmer edit section *** (Form Designer Fields) 

       //... 
        private ICSSoft.STORMNET.Windows.Forms.ObjectHierarchicalView objectHierarchicalView1;
        
// *** End programmer edit section *** (Form Designer Fields) 
``` 

* Next, you add in the InitializeComponent. 

* Additions InitializeComponent 

For correct display ObjectHierarchicalView need to set all the properties of the control. 

```csharp
 private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WinformTestHierarchicalForm));
            //... 
            this.objectHierarchicalView1 = new ICSSoft.STORMNET.Windows.Forms.ObjectHierarchicalView();
            
            // 
            // objectHierarchicalView1 
            // 
            this.objectHierarchicalView1.AdvansedColumns = null;
            this.objectHierarchicalView1.AdvansedMarks = false;
            this.objectHierarchicalView1.AllowSorting = true;
            this.objectHierarchicalView1.AutoRefreshSupport = false;
            this.objectHierarchicalView1.DataObjectTypes = new System.Type[] {
        ((System.Type)(typeof(IIS.TestStandWinforms.Территория)))};
            this.objectHierarchicalView1.DataService = null;
            this.objectHierarchicalView1.Dock = System.Windows.Forms.DockStyle.None;
            this.objectHierarchicalView1.DrawGrid = false;
            this.objectHierarchicalView1.ExtendLastCol = true;
            this.objectHierarchicalView1.FillDataOnLoad = true;
            this.objectHierarchicalView1.FillDataOnLoadUserAllowed = true;
            this.objectHierarchicalView1.GrayedOnLoad = true;
            this.objectHierarchicalView1.HideToolBar = false;
            this.objectHierarchicalView1.HierarchicalMasterName = Hierarchy; //The name of the workman of the object specified in the HierarchicalMaster list form*. 
            this.objectHierarchicalView1.Limit = null;
            this.objectHierarchicalView1.LoadingPackageSize = 500;
            this.objectHierarchicalView1.Location = new System.Drawing.Point(10, 100);
            this.objectHierarchicalView1.MemoryTimeLoadLimit = ((long)(200));
            this.objectHierarchicalView1.Name = "objectHierarchicalView1";
            this.objectHierarchicalView1.NodesStyle = ((ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle)((ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle.Lines | ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle.Symbols)));
            this.objectHierarchicalView1.RowCountQuery = 12345;
            this.objectHierarchicalView1.SaveExpandedRows = false;
            this.objectHierarchicalView1.ShowCyclicalReferencedObjects = false;
            this.objectHierarchicalView1.ShowStatusBar = true;
            this.objectHierarchicalView1.ShowToolTip = false;
            this.objectHierarchicalView1.ShowTreeColumnName = "";
            this.objectHierarchicalView1.Size = new System.Drawing.Size(600, 322);
            this.objectHierarchicalView1.TabIndex = 6;
            this.objectHierarchicalView1.TreeLeafImage = null;
            this.objectHierarchicalView1.TreeLineColor = System.Drawing.Color.DarkGray;
            this.objectHierarchicalView1.TreeLineStyle = System.Drawing.Drawing2D.DashStyle.Dot;
            this.objectHierarchicalView1.TreeNodeImage = null;
            this.objectHierarchicalView1.TreeNodeImageCollapsed = null;
            this.objectHierarchicalView1.TreeNodeImageExpanded = null;
            this.objectHierarchicalView1.UseAlternativeColoring = false;
            this.objectHierarchicalView1.UseColumnOptimization = false;
            this.objectHierarchicalView1.UseHotkeyForEdit = true;
            this.objectHierarchicalView1.UseLimitFunctionExtension = false;
            this.objectHierarchicalView1.UseToolBar = null;
            this.objectHierarchicalView1.ViewName = "ТерриторияL";//The view name that is used in a hierarchical list. 
``` 


Read more about list form, you can read [here](fd_key-concepts.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/