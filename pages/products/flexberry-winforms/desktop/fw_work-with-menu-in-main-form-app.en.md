---
title: Работа с меню в главной форме приложения
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
summary: На примере рассмотрено как изменять элементы меню на главной форме приложения
toc: true
permalink: en/fw_work-with-menu-in-main-form-app.html
folder: products/flexberry-winforms/
lang: en
---

Находим в приложении метод Main.
Далее ищем переменную desktop,
у которой в свойствах находятся 
все элементы существующего меню!
 
Пример:
Убираем видимость элемента меню "Свойства".
Добавляем элемент меню "Сменить пользователя" с обработчиком.
Добавляем обработчик на элемент меню "Помощь".

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