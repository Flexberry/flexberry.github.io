--- 
title: Using ToolStrip for the organization of the menu 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), the Windows UI (forms) 
summary: For example, consider using the ToolStrip to the organization menu in the main form of the application 
toc: true 
permalink: en/fw_working-with-menu.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: dad376c80b52eed42670bc03558b2fc0f18fe1ca7a74d4a83383e8f560f22db1 
--- 

On the threshold of the final transition to the Web, do not forget that there is still a need for Ala WinForms applications, and some Customers are still working on systems with DOS-historic interface. 

Everyone knows the situation, when the Customer perceives a hostile reception interface in the created by us information systems as used to the organization menu in its old programs. In turn, the formation menu in our system is not built into the technology. 

A possible solution. 

Use the system menu on the Desktop is not very convenient, and there are some restrictions and inconvenience for the insertion of icons and shortcuts. Therefore, use a ToolStrip. 

The only limitation is the version of Visual Studio should be 2005 or above. 

In the main application project, in parentheses, the programmer adds three methods: 

_AddToolStripMenu_ 
_ChangeToolStripMenuItem_ 
_CopyMenuToStrip_ 

Code given below: 

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


In the main method, in parentheses, the programmer working with the menu: 

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
//add new menu items with images and hot keys 
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

As for the menu, everything seems to be! 

Run a compilation and see a slightly updated interface: 
![](/images/pages/products/flexberry-winforms/desktop/image001.jpg) 
Menu `Файл`: 
![](/images/pages/products/flexberry-winforms/desktop/image002.jpg) 
Menu `Справочники`: 
![](/images/pages/products/flexberry-winforms/desktop/image003.jpg) 
Menu `?`: 
![](/images/pages/products/flexberry-winforms/desktop/image004.jpg) 

In this example, we solved several problems: 

1. Created like `старое` menu. 
2. Increased visual effect of the menu through icons. 
3. Has increased the hot keys. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/