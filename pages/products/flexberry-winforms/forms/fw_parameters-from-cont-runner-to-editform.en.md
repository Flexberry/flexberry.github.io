---
title: Передача параметров от ContRunner до формы редактирования
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Указано какие методы и как следует переопределить, чтобы принять параметр от DesktopCustomizer в списковой форме и передать на форму редактирования
toc: true
permalink: en/fw_parameters-from-cont-runner-to-editform.html
folder: products/flexberry-winforms/
lang: en
---

## Передача параметров в DesktopCustomizer
Для того чтобы передать параметр контейнеру запуска используйте перегруженный метод создания `ContRunner`.

```csharp
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.LimitsTesting_Blog.LTB_БлогL), "MyTag1FromContRunner", "LimitsTesting_Blog", "Блог", ""));
```

## Приём параметра в списковой форме и передача на форму редактирования
Параметр в списковую форму попадает в метод `Activate(object tag)`. 
Обратите внимание на количество параметров в перегруженном методе `OnNewEvent()`. Перегрузить следует метод с 2мя параметрами, а вызвать в нём метод с 3мя. По-умолчанию создание объектов происходит без передачи тега.

```csharp
// *** Start programmer edit section *** (LTB_БлогL CustomMembers)
private object _tagFromRunner;

public override void Activate(object tag)
{
    _tagFromRunner = tag;
    // MessageBox.Show("Activate " + (tag??"<пусто>"));
    base.Activate(tag);
}

protected override void OnNewEvent(Type dataobjecttype, string contpath)
{
    // MessageBox.Show("OnNewEvent");
    base.OnNewEvent(dataobjecttype, contpath, _tagFromRunner);
}

protected override void OnEditEvent(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
{
    // MessageBox.Show("OnEditEvent " + (tag ?? "<пусто>"));
    base.OnEditEvent(propertyname, dataobject, contpath, _tagFromRunner);
}
// *** End programmer edit section *** (LTB_БлогL CustomMembers)
```

## Приём параметра на форме редактирования
В метод `Edit` с самым большим числом параметров придёт тот самый тег.

```csharp
// *** Start programmer edit section *** (WinformLTB_БлогE CustomMembers)
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
    // MessageBox.Show("БлогE tag: " + (tag??"<пусто>"));
    base.Edit(dataobject, contpath, propertyname, tag);
}
// *** End programmer edit section *** (WinformLTB_БлогE CustomMembers)
```