---
title: Передача параметров от ContRunner до формы редактирования
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/parameters-from-cont-runner-to-edit-form.html
folder: product--folder
lang: ru
---

# Передача параметров в DesktopCustomizer
Для того чтобы передать параметр контейнеру запуска используйте перегруженный метод создания `ContRunner`.
```cs
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.LimitsTesting_Blog.LTB_БлогL), "MyTag1FromContRunner", "LimitsTesting_Blog", "Блог", ""));
```
# Приём параметра в списковой форме и передача на форму редактирования
Параметр в списковую форму попадает в метод `Activate(object tag)`. 
Обратите внимание на количество параметров в перегруженном методе `OnNewEvent()`. Перегрузить следует метод с 2мя параметрами, а вызвать в нём метод с 3мя. По-умолчанию создание объектов происходит без передачи тега.
```cs
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

# Приём параметра на форме редактирования
В метод `Edit` с самым большим числом параметров придёт тот самый тег.
```cs
// *** Start programmer edit section *** (WinformLTB_БлогE CustomMembers)
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
    // MessageBox.Show("БлогE tag: " + (tag??"<пусто>"));
    base.Edit(dataobject, contpath, propertyname, tag);
}
// *** End programmer edit section *** (WinformLTB_БлогE CustomMembers)
```



