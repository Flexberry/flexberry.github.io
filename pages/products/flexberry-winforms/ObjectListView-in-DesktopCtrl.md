---
title: Контрол рабочего стола
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/object-list-view-in--desktop-ctrl.html
folder: product--folder
lang: ru
---

# [#Desktop pict|Картинка на рабочем столе]
# [#ContextMenu|Контекстное меню]
# [#ShowGroups|Группировка элементов рабочего стола]
# [#Icons|Замена иконок элементов списка]
# [#UpdateObject|Обновление объектов ListView]
# [#ColumnItem|Работа с Column и Item]
# [#Event|Событие TreeViewItemSelected]
# [#DesktopSettings|DesktopSettings]

## [anchor|#Desktop pict]Картинка на рабочем столе

Фоновая картинка рабочего стола задается свойством `listView1.OverlayImage.Image`. Можно выбрать любую картинку форматов ''gif, jpg, jpeg, bmp, wmf, png''. Можно установить анимированную картинку, но анимироваться она не будет. После смены изображения нужно его обновить вызовом метода `RefreshOverlay`:
```cs
listView1.RefreshOverlay(listView1.OverlayImage)
```
Выравнивание картинки производится путём задания необходимого значения свойству `OverlayImage.Alignment` (тип - `System.Drawing.ContentAlignment`), после чего рекомендуется обновить картинку вышеуказанным способом.

Чтобы изменить прозрачность картинки нужно менять значение поля `OverlayImage.Transparency`. Оно принимает значения от 0 (абсолютная прозрачность) до 255 (совершенно непрозрачная картинка), значение по умолчанию 128.

### Пример
```cs
listView1.OverlayImage.Image = Image.FromFile("D:\\images\\limeleaf.png");
listView1.OverlayImage.Alignment = ContentAlignment.BottomLeft;
listView1.OverlayImage.Transparency = 50;
```
![](/images/pages/img/page/ObjectListView-in-DesktopCtrl/Картинка на рабочем столе.png)

Отступы по горизонтали и вертикали соответственно задаются с помощью атрибутов `OverlayImage.InsetX` и `OverlayImage.InsetY` (типы - int)

## [anchor|#ContextMenu]Контекстное меню
Рассматриваемое контекстное меню вызывается кликом правой кнопки мыши в свободном пространстве.


![](/images/pages/img/page/ObjectListView-in-DesktopCtrl/Контекстное меню.png)

# '''Обновить '''– обновляет все элементы списка с сохранением текущей позиции - `listView1.BuildList(true)`.
# '''Отображать дерево '''– скрыть/показать treeView (свойство `treeView1.Visible`).
# '''Показывать папки '''– выводить/не выводить папки в listView.
# '''Группировать элементы '''– группировать/не группировать элементы listView (свойство `ShowGroups`).
# '''Столбцы '''– выбрать столбцы для отображения в listView. Столбец «Название» отображается всегда.
# '''Всегда группировать по '''– выбрать столбец, по которому всегда будет происходить группировка элементов (свойство `AlwaysGroupByColumn`).

## [anchor|#ShowGroups]Группировка элементов рабочего стола
Группировка задаётся свойством `ShowGroups` (по умолчанию `true`).
Столбец для группировки по умолчанию определяется тем столбцом, по которому происходит упорядочивание элементов. Название группы принимает значение этого столбца у соответствующего элемента.

Например, если все элементы имеют разные имена, то при упорядочивании по имени будет отображаться столько групп, сколько всего элементов в `listView`, при этом в каждой группе будет по одному элементу. При этом название группы примет имя содержащегося в ней элемента.

Существует возможность задания столбца, по которому группировка будет происходить всегда, даже если сортировка производится по другому столбцу. Для этого служит атрибут `AlwaysGroupByColumn` (по умолчанию `null`), ему присваивается соответствующий столбец.

### Пример
```cs
listView1.ShowGroups = true;
listView1.AlwaysGroupByColumn = this.ItemType;
```

![](/images/pages/img/page/ObjectListView-in-DesktopCtrl/Группировка элементов рабочего стола.png)

## [anchor|#Icons]Замена иконок элементов списка
Если не устраивают стандартные иконки, отображаемые для папок, пользовательских форм и форм редактирования, заменить их можно следующим образом:
# Замена всей группы иконок. В экземпляре класса `Runner` поле для картинки `imagecache` должно быть `null` (стоит по умолчанию). Тогда рисунок берется из `imageListRunners` и `imageListRunnersLarge` (для отображения мелких (16x16) и крупных (32x32) иконок соответственно). В качестве ключа используются следующие значения: ''"IconFolder"'' – папка, ''"IconForm"'' – пользовательская форма, ''"IconEdit"'' – форма редактирования, ''"IconFolderUp"'' – на уровень вверх. В пользовательском приложении иконка меняется через одноимённые поля компонента `DesktopCtrl`, за исключением ''"IconFolderUp"'' – здесь её заменить нельзя.
# Замена иконки для конкретного экземпляра класса `Runner` (пользовательская форма или форма редактирования). Замена происходит в коде пользовательского приложения.

### Пример (ЛюдиDesktopCustomizer.cs)
```cs
public override ICSSoft.STORMNET.UI.Runner[] GetRunners()
…
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Люди.Л_ЧеловекL), "Люди", "Человек", "Описание человека"));
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Люди.Л_МашинаL), "Люди", "Машина", "Описание машины"));
arr.Add(new ICSSoft.STORMNET.Windows.Forms.FormRunner(typeof(IIS.Люди.WinformForm), "Люди", "Форма", "Описание формы"));
…
// *** Start programmer edit section *** (Люди GetRunners() End)
((ICSSoft.STORMNET.UI.ContRunner)arr[0]).SetImage("D:\\images\\goldstar3.png"); // "Человек"
((ICSSoft.STORMNET.Windows.Forms.FormRunner)arr[2]).SetImage("D:\\images\\caseberrytmcut.jpg"); //"Форма"
// *** End programmer edit section *** (Люди GetRunners() End)
```

![](/images/pages/img/page/ObjectListView-in-DesktopCtrl/Замена иконок элементов списка.png)

## [anchor|#UpdateObject]Обновление объектов `ListView`
Возможны ситуации, когда при добавлении, изменении или удалении каких-то объектов в `ListView` некоторые его элементы отображаются неправильно. Тогда `ListView` нужно обновить принудительно. Для этого используются следующие методы:
* `BuildList` – перестроить все items в listView. True в качестве аргумента означает, что нужно сохранить фокус и выделение.
* `RefreshOverlay` – обновить наложение, в частности картинку (передаётся аргументом).
* `RefreshObject` – обновить строку с передаваемым в качестве аргумента объектом.
* `RebuildColumns` – обновить имеющиеся столбцы. Вызывается, например, после изменения свойства `IsVisible` столбца. 

## [anchor|#ColumnItem]Работа с Column и Item
У компонента `ObjectListView` существуют некоторые особенности при настройке и добавлении столбцов и записей. Прежде всего нужно сказать несколько слов о вспомогательном классе `ItemInListView`.

Класс `ItemInListView` по сути связывает Items с Columns. `ObjectListView` сам создаёт новый item и присваивает каждому полю нужное значение. Чтобы составить однозначное соответствие «поле в `ItemInListView` – столбец в `ObjectListView`» имена полей в `ItemInListView` совпадают со значением полей `AspectName` (тип `string`) у соответствующих столбцов.
Добавление нового item происходит по схеме: 
# Создали экземпляр класса `ItemInListView` и заполнили его поля.
# Добавили его в `ObjectListView` методом `AddObjects` для нескольких item или `AddObject` для одного item.

При таком подходе отпадает необходимость в работе с subItem и к каждому добавленному item можно обращаться как к `ItemInListView`:
```cs
((ItemInListView)listView1.SelectedObjects[0]).Description
```

При создании столбцов нужно указать `AspectName` для связи с данными и `ImageAspectName` для связи с картинкой. Добавлять столбцы нужно в два массива.

### Пример
```cs
listView1.AllColumns.Add(Caption);
listView1.AllColumns.Add(Description);
listView1.AllColumns.Add(ItemType);
listView1.AllColumns.Add(ParentFolder);
listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
    Caption,
    Description,
    ItemType,
    ParentFolder});
```

## [anchor|#Event]Событие TreeViewItemSelected
Событие `TreeViewItemSelected` происходит при перевыборе элемента в treeView. Позволяет при определённых условиях отобразить вместо listView панель с какими-то своими контролами. Например, если выбранная в treeView вершина является листом, т.е. в ней нет форм и таблиц, а только папки (можно узнать по свойству `IsListNodeSelected`), можно вместо listView показать текст со справочной информацией.

В пользовательском приложении нужно подписаться на событие `TreeViewItemSelected` у экземпляра `DesktopCtrl`. В унаследованном классе `TreeViewShowControlsEventArgs` нужно задать значение true полю `Handled` если нужно отобразить свои контролы  и false если нужно отобразить listView. Далее создаются контролы и помещаются в `PanelOnListView`.

### Пример
```cs
private void desktopCtrl2_TreeViewItemSelected(object sender, ICSSoft.STORMNET.Windows.Forms.DesktopCtrl.TreeViewShowControlsEventArgs e)
{
    if (this.desktopCtrl2.IsListNodeSelected)
    {
        e.Handled = true;
        Label label = new Label();
        label.Text = "Некий текст заместо ListView";
        label.AutoSize = true;
        label.ForeColor = Color.DarkBlue;
        label.Location = new Point(100, 50);
        e.PanelOnListView.Controls.Add(label);
        e.PanelOnListView.BackColor = Color.LightYellow;
    }
    else
    {
        e.Handled = false;
    }
}
```

![](/images/pages/img/page/ObjectListView-in-DesktopCtrl/Событие TreeViewItemSelected.png)

## [anchor|#DesktopSettings]DesktopSettings
Серилиазуемый класс `DesktopSettings` служит для хранения таких пользовательских настроек как размер окна, ширина столбцов, столбцы для отображения и т.д. Для того чтобы настройки сохранялись и загружались, нужно установить свойство `UseSettings` класса `SettingManager` равным true. В таком случае настройки сохраняются перед закрытием пользовательского приложения, а загружаются при запуске.

Хранятся следующие настройки:
# string[] `History` - история строки в `AddressBar`.
# string `curpath` - текущий путь в папках.
# int `Viewmode` - режим представления элементов. 0 – большие иконки, 1 – список, 2 – подробности, 3 – маленькие иконки.
# int `FoldersSplitterPos` - позиция сплиттера между treeView и listView.
# bool `TreeViewVisible` – отображать treeView.
# bool `ListViewShowFolders` – отображать папки в listView.
# bool `ListViewShowGroups` – группировать элементы в listView.
# string `ListViewAlwaysGroupByColumnName` – название столбца, по которому нужно всегда группировать элементы (''“Caption”'' - название, ''“Description”'' - описание, ''"ItemType"'' - тип, ''"ParentFolder"'' - папка).
# string `ListViewColumnSort` – название столбца, по которому производится сортировка элементов listView.
# SortOrder `ListViewSortOrder` – порядок сортировки элементов listView (убывание, возрастание).
# Отображать столбцы: `ColumnDescriptionVisible` – описание, `ColumnItemTypeVisible` – тип, `ColumnParentFolderVisible` – папка. Столбец «Название» отображается всегда.
# Ширина столбцов: `ColumnCaptionWidth` - название, `ColumnDescriptionWidth` - описание, `ColumnItemTypeWidth` - тип, `ColumnParentFolderWidth` – папка.
 

