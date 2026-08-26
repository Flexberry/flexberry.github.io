---
title: Организация меню winforms-приложений
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, desktop, menu
summary: Использование ToolStrip для организации меню на главной форме приложения
toc: true
permalink: ru/fw_working-with-menu.html
lang: ru
---

## Использование ToolStrip

Для организации меню на главной форме приложения возможно использование ToolStrip.

__Внимание!__ Версия Visual Studio должна быть 2005 или выше.

В основном проекте приложения в скобках программиста необходимо указать три метода:

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

В методе `main` в скобках программиста изменяются настройки меню:

```csharp
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
//Добавить новые элементы меню с картинками и горячими клавишами
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

В результате компиляции обновляется интерфейс:

![Интерфейс](/images/pages/products/flexberry-winforms/desktop/image001.jpg)

Меню «Файл»:

![Меню Файл](/images/pages/products/flexberry-winforms/desktop/image002.jpg)

Меню «Справочники»:

![Меню Справочники](/images/pages/products/flexberry-winforms/desktop/image003.jpg)

Меню «?»:

![Меню Подробнее](/images/pages/products/flexberry-winforms/desktop/image004.jpg)

В результате решено несколько проблем:

1. Меню выглядит более консервативно.
2. Усилен визуальный эффект от меню за счет иконок.
3. Расширена возможность горячих клавиш.

## Меню в главной форме приложения

В приложении в методе Main необходимо найти переменную desktop, у которой в свойствах находятся все элементы существующего меню.

Пример

1. Убрать видимость элемента меню "Свойства".
2. Добавить элемент меню "Сменить пользователя" с обработчиком.
3. Добавить обработчик на элемент меню "Помощь".

```csharp
[STAThread()]
static void Main()
{
//...
      БорьбаDesktop desktop = new БорьбаDesktop();
      //...
      desktop.menuItem1.MenuItems[0].Visible = false;                 //Свойства;
      desktop.menuItem1.MenuItems.Add(0,new MenuItem("Сменить пользователя...",new EventHandler(БорьбаDesktop_Click)));
      desktop.menuItem4.Click += new EventHandler(menuItem4_Click);   //Помощь;
      //...
}
```
