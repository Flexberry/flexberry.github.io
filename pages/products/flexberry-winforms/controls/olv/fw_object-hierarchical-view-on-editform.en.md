---
title: Создание формы редактирования с иерархическим списком
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
toc: true
permalink: en/fw_object-hierarchical-view-on-editform.html
folder: products/flexberry-winforms/
lang: en
---

Бывают случаи, когда на форме редактирования необходимо создать иерархический список. 

Например, в модели иерархический список выглядит так:

![](/images/pages/products/flexberry-winforms/controls/olv/object-hierarchical-view.png)

Однако по-умолчанию генерируется отдельная форма для отображения иерархии (в данном случае, "Территория)".
Для того чтобы иерархический список был отображен на форме редактирования, на форму необходимо добавить контрол ObjectHierarchicalView.
Чтобы это сделать следует выполнить ряд действий:
* Сгенерировать форму редактирования.
* Добавить на форму в Form Designer Fields:

```csharp
// *** Start programmer edit section *** (Form Designer Fields)

       //...
        private ICSSoft.STORMNET.Windows.Forms.ObjectHierarchicalView objectHierarchicalView1;
        
// *** End programmer edit section *** (Form Designer Fields)
```

* Далее следует внести дополнения в InitializeComponent.

    * Дополнения InitializeComponent

Для корректного отображения ObjectHierarchicalView необходимо правильно прописать все свойства контрола.

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
            this.objectHierarchicalView1.HierarchicalMasterName = "Иерархия"; //Имя мастерового объекта, указывается в поле HierarchicalMaster списковой формы*. 
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
            this.objectHierarchicalView1.ViewName = "ТерриторияL";//Имя представления, которое используется на иерархическом списке.
```


Подробнее о списковой форме можно прочитать [здесь](fd_key-concepts.html).
