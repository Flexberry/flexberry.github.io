---
title: Кнопка «Свернуть/развернуть папки» на рабочем столе
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/desktop-operations.html
folder: product--folder
lang: ru
---

В панели инструментов рабочего стола появилась кнопка '''«Свернуть/развернуть папки»'''. Для ее отображения необходимо пристроить свойству `Operations` контрола рабочего стола значение `DesktopOperations.TreeExpandCollapse`. Проще всего сделать это в методе Main формы рабочего стола.

```cs
static void Main()
{
    try
    {
		...

        КошкиИЛапыDesktop desktop = new КошкиИЛапыDesktop();
		
		...  

        desktop.desktopCtrl2.Operations = DesktopOperations.TreeExpandCollapse;
        Application.Run(desktop);
    }
    catch (System.Exception e)
    {
		...
    }
}
```