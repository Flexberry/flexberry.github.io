---
title: И опять работа с меню
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
toc: true
permalink: ru/fw_working-with-menu.html
folder: products/flexberry-winforms/
lang: ru
---

На пороге окончательного перехода в Web, не стоит забывать о том, что еще есть потребность в приложениях аля WinForms, а некоторые Заказчики еще работают на системах с DOS-историческим интерфейсом. J

 

Всем известна ситуация, когда Заказчик воспринимает в штыки интерфейс в создаваемых нами информационных системах т.к. привык к организации меню в своих старых программах. В свою очередь, формирование меню в наших системах никак не встроено в технологию. А ручками программисты работать не хотят…

 

Но если вдруг, меню кому-то понадобится, то могу предложить вариант решения.

Использовать системное меню на Desktop не совсем удобно, да и есть определенные ограничения и неудобства по вставке иконок и горячих клавиш. Поэтому используется ToolStrip.

Единственное ограничение – версия Visual Studio должна быть 2005 или выше.

 

В основном проекте приложения в скобках программиста добавляете три метода:

 

''AddToolStripMenu''

''ChangeToolStripMenuItem''

''CopyMenuToStrip''

 

Код приведен ниже:
```
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
 

В методе main в скобках программиста работаете с меню:
```

//Уберем Properties
desktop.menuItem1.MenuItems[0].Visible = false;                 
//Вставим обработчик на Help
desktop.menuItem4.Click += new EventHandler(menuItem4_Click);   
//Скопируем существующее меню в ToolStrip
ToolStrip ts = new ToolStrip();
ts.Parent = desktop;
for (int i = 0; i < desktop.mainMenu1.MenuItems.Count; i++)
{
CopyMenuToStrip(desktop.mainMenu1.MenuItems[i],ts,null);
desktop.mainMenu1.MenuItems[i].Visible = false; //скрываем меню..
}
//добавляем новые элементы меню с картинками и горячими клавишами
AddToolStripMenu(ts, 0, 0, "Создать модель ИСПДн", (System.Drawing.Image)global::IIS.Product.Properties.Resources.new16,new EventHandler(WorkMenu_Click),Keys.Alt | Keys.N);
AddToolStripMenu(ts, 0, 1, "Открыть модель ИСПДн...",(System.Drawing.Image)global::IIS.Product.Properties.Resources.Open16, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.O);
ts.Items.Insert(1,new ToolStripMenuItem("Справочники"));
AddToolStripMenu(ts, 1, 0, "Угрозы...", null, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.U);
AddToolStripMenu(ts, 1, 1, "Меры противодействия...", null, new EventHandler(WorkMenu_Click), Keys.Alt | Keys.M);
AddToolStripMenu(ts, 2, 1, "Лицензионное соглашение...", (System.Drawing.Image)global::IIS.Product.Properties.Resources.Attrib16,  new EventHandler(menuItem4_Click), Keys.Alt | Keys.F10);
//Для меню "Выход", вставляем иконку и горячую клавишу
ChangeToolStripMenuItem(ts, 0, 3,(System.Drawing.Image)global::IIS.Product.Properties.Resources.Exit16, Keys.Alt | Keys.X);
//Для меню "Помощь..." , вставляем иконку и горячую клавишу
ChangeToolStripMenuItem(ts, 2, 0,(System.Drawing.Image)global::IIS.Product.Properties.Resources.Help16,  Keys.F1);
```

Что касается меню, вроде бы все!

 

Запускаем компиляцию и видим немного обновленный интерфейс:
![](/images/pages/products/flexberry-winforms/desktop/image001.jpg)
Меню «Файл»:
![](/images/pages/products/flexberry-winforms/desktop/image002.jpg)
Меню «Справочники»:
![](/images/pages/products/flexberry-winforms/desktop/image003.jpg)
Меню «?»:
![](/images/pages/products/flexberry-winforms/desktop/image004.jpg)

На данном примере мы решили несколько проблем:

 

1.      Создали похожее на «старое» меню.

2.      Усилили визуальный эффект от меню за счет иконок.

3.      Расширили возможность горячих клавиш.
