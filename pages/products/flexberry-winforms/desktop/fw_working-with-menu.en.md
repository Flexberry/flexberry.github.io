---
title: menu winforms applications
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, desktop, menu
summary: Use the ToolStrip for the organization of the menu on the main application form
toc: true
permalink: en/fw_working-with-menu.html
lang: en
autotranslated: true
hash: d61fc3e02988802f7e4c299c468a39adecde734a8864ba4c437faae6dd1bf0d5
---

## Use Of ToolStrip

To organize menu in main form of the application it is possible to use the ToolStrip.

__.__ The version of Visual Studio should be 2005 or above.

In the main application project, in parentheses, the programmer must specify three methods:

_AddToolStripMenu_
_ChangeToolStripMenuItem_
_CopyMenuToStrip_

```csharp
// *** Start programmer edit section *** (ThreatsModelDesktop CustomMembers) 
        static void AddToolStripMenu(ToolStrip ts, int menuindex, int submenuindex, 
            string Text, Image image, EventHandler e, System.Windows.Forms.Keys shortkeys)
        {
            ((ToolStripMenuItem)ts.Items[menuindex]).DropDownItems.Insert(submenuindex, new ToolStripMenuItem(Text, null, e));
            ChangeToolStripMenuItem(ts, menuindex, submenuindex, image, shortkeys);
        }
        static void ChangeToolStripMenuItem(ToolStrip ts, int menuindex, int submenuindex, Image image, System.Windows.Forms.Keys shortkeys)
        {
            ToolStripMenuItem tsmi = ((ToolStripMenuItem)((ToolStripMenuItem)ts.Items[menuindex]).DropDownItems[submenuindex]);
            tsmi.ShortcutKeys = shortkeys;
            tsmi.ShowShortcutKeys = true;
            tsmi.ImageTransparentColor = Color.Magenta;
            tsmi.Image = image; 
        }
        static void CopyMenuToStrip(MenuItem mi, ToolStrip ts, ToolStripMenuItem tsmi)
        {
            if (!mi.Visible) return;
            ToolStripItem mtddb = null;
            if (mi.Text == "-")
            {
                mtddb = new ToolStripSeparator();
                tsmi.DropDownItems.Add(mtddb);  
            }
            else
            {
                mtddb = new ToolStripMenuItem(mi.Text, null, mtsb_Click);
                if (tsmi == null) ts.Items.Add(mtddb);  
                else tsmi.DropDownItems.Add(mtddb);  
            }
            mtddb.Tag = mi;
            for (int i = 0; i < mi.MenuItems.Count; i++)
            {
                CopyMenuToStrip(mi.MenuItems[i], ts, (ToolStripMenuItem)mtddb);
            }
        }
```

In the method `main` in parentheses programmer to change the settings menu:

```csharp
//Remove Properties 
desktop.menuItem1.MenuItems[0].Visible = false;
//Insert handler on Help 
desktop.menuItem4.Click += new EventHandler(menuItem4_Click);
//Copy an existing menu in a ToolStrip 
ToolStrip ts = new ToolStrip();
ts.Parent = desktop;
for (int i = 0; i < desktop.mainMenu1.MenuItems.Count; i++)
{
CopyMenuToStrip(desktop.mainMenu1.MenuItems[i],ts,null);
desktop.mainMenu1.MenuItems[i].Visible = false; //hide the menu.. 
}
//Add new menu items with images and hot keys 
AddToolStripMenu(ts, 0, 0, "To create a model of PDIS", (System.Drawing.Image)global::IIS.Product.Properties.Resources.new16,new EventHandler(WorkMenu_Click),Keys.Alt | Keys.N);
AddToolStripMenu(ts, 0, 1, "Open model PDIS...",(System.Drawing.Image)global::IIS.Product.Properties.Resources.Open16, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.O);
ts.Items.Insert(1,new ToolStripMenuItem("Reference"));
AddToolStripMenu(ts, 1, 0, "Threats...", null, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.U);
AddToolStripMenu(ts, 1, 1, "Counter measures...", null, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.M);
AddToolStripMenu(ts, 2, 1, "License agreement...", (System.Drawing.Image)global::IIS.Product.Properties.Resources.Attrib16,  new EventHandler(menuItem4_Click), Keys.Alt | Keys.F10);
//For the menu "Output", plug-in icon and a hotkey 
ChangeToolStripMenuItem(ts, 0, 3,(System.Drawing.Image)global::IIS.Product.Properties.Resources.Exit16, Keys.Alt | Keys.X);
//For the menu "Help..." , insert the icon and a hotkey 
ChangeToolStripMenuItem(ts, 2, 0,(System.Drawing.Image)global::IIS.Product.Properties.Resources.Help16,  Keys.F1);
```

As a result of the compilation of the updated interface:

![Interface](/images/pages/products/flexberry-winforms/desktop/image001.jpg)

Menu» «File:

![Menu File](/images/pages/products/flexberry-winforms/desktop/image002.jpg)

Menu Reference» qmo:

![Menu Reference](/images/pages/products/flexberry-winforms/desktop/image003.jpg)

Menu «?»:

![Menu read More](/images/pages/products/flexberry-winforms/desktop/image004.jpg)

The result solved a few problems:

1. Menu looks more conservative.
2. Enhanced visual effect from the menu through icons.
3. Improved ability hot keys.

## Menu in the main form of the application

In the application in the Main method, it is necessary to find a variable desktop, which properties are all elements of an existing menu.

Example

1. To remove the visibility of the menu item "Properties".
2. Add menu item "switch user" with the handler.
3. Add a handler to the menu item "Help".

```csharp
[STAThread()]
static void Main()
{
//... 
      БорьбаDesktop desktop = new БорьбаDesktop();
      //... 
      desktop.menuItem1.MenuItems[0].Visible = false;                 //Свойства; 
      desktop.menuItem1.MenuItems.Add(0,new MenuItem("Change user...",new EventHandler(БорьбаDesktop_Click)));
      desktop.menuItem4.Click += new EventHandler(menuItem4_Click);   //Помощь; 
      //... 
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}