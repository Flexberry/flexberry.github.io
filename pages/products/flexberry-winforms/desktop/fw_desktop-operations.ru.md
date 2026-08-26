---
title: Операции рабочего стола
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, операции рабочего стола, папки, ярлыки, дерево элементов
summary: Видимость кнопки состояния папок, изображение на рабочем столе, группировка элементов, настройка дерева элементов, ярлыки рабочего стола, особенности настройки приложения с несколькими классами application, поддержка схем Windows XP
toc: true
permalink: ru/fw_desktop-operations.html
lang: ru
---

## Контекстное меню

Рассматриваемое контекстное меню вызывается кликом правой кнопки мыши в свободном пространстве.

![Контекстное меню](/images/pages/products/flexberry-winforms/desktop/context-menu.png)

* `Обновить` – обновляет все элементы списка с сохранением текущей позиции - `listView1.BuildList(true)`.
* `Отображать дерево` – скрыть/показать treeView (свойство `treeView1.Visible`).
* `Показывать папки` – выводить/не выводить папки в listView.
* `Группировать элементы` – группировать/не группировать элементы listView (свойство `ShowGroups`).
* `Столбцы` – выбрать столбцы для отображения в listView. Столбец «Название» отображается всегда.
* `Всегда группировать по` – выбрать столбец, по которому всегда будет происходить группировка элементов (свойство `AlwaysGroupByColumn`).

## Группировка элементов рабочего стола

Группировка задаётся свойством `ShowGroups` (по умолчанию `true`).
Столбец для группировки по умолчанию определяется тем столбцом, по которому происходит упорядочивание элементов. Название группы принимает значение этого столбца у соответствующего элемента.

Например, если все элементы имеют разные имена, то при упорядочивании по имени будет отображаться столько групп, сколько всего элементов в `listView`, при этом в каждой группе будет по одному элементу. При этом название группы примет имя содержащегося в ней элемента.

Существует возможность задания столбца, по которому группировка будет происходить всегда, даже если сортировка производится по другому столбцу. Для этого служит атрибут `AlwaysGroupByColumn` (по умолчанию `null`), ему присваивается соответствующий столбец.

### Пример группировки элементов рабочего стола

```csharp
listView1.ShowGroups = true;
listView1.AlwaysGroupByColumn = this.ItemType;
```

![Группировки рабочего стола](/images/pages/products/flexberry-winforms/desktop/desctop-group.png)

## Замена иконок элементов списка

Если не устраивают стандартные иконки, отображаемые для папок, пользовательских форм и форм редактирования, заменить их можно следующим образом:

* Замена всей группы иконок. В экземпляре класса `Runner` поле для картинки `imagecache` должно быть `null` (стоит по умолчанию). Тогда рисунок берется из `imageListRunners` и `imageListRunnersLarge` (для отображения мелких (16x16) и крупных (32x32) иконок соответственно). В качестве ключа используются следующие значения:

  * `IconFolder` – папка,
  * `IconForm` – пользовательская форма,
  * `IconEdit` – форма редактирования,
  * `IconFolderUp` – на уровень вверх.

В пользовательском приложении иконка меняется через одноимённые поля компонента `DesktopCtrl`, за исключением `IconFolderUp` – здесь её заменить нельзя.

* Замена иконки для конкретного экземпляра класса `Runner` (пользовательская форма или форма редактирования). Замена происходит в коде пользовательского приложения.

### Пример (ЛюдиDesktopCustomizer.cs)

```csharp
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

![Иконки элементов списка](/images/pages/products/flexberry-winforms/desktop/list-icons.png)

## Обновление объектов ListView

Возможны ситуации, когда при добавлении, изменении или удалении каких-то объектов в `ListView` некоторые его элементы отображаются неправильно. Тогда `ListView` нужно обновить принудительно. Для этого используются следующие методы:

* `BuildList` – перестроить все items в listView. True в качестве аргумента означает, что нужно сохранить фокус и выделение.
* `RefreshOverlay` – обновить наложение, в частности картинку (передаётся аргументом).
* `RefreshObject` – обновить строку с передаваемым в качестве аргумента объектом.
* `RebuildColumns` – обновить имеющиеся столбцы. Вызывается, например, после изменения свойства `IsVisible` столбца.

## Работа с Column и Item

У компонента `ObjectListView` существуют некоторые особенности при настройке и добавлении столбцов и записей. Прежде всего нужно сказать несколько слов о вспомогательном классе `ItemInListView`.

Класс `ItemInListView` по сути связывает Items с Columns. `ObjectListView` сам создаёт новый item и присваивает каждому полю нужное значение. Чтобы составить однозначное соответствие «поле в `ItemInListView` – столбец в `ObjectListView`» имена полей в `ItemInListView` совпадают со значением полей `AspectName` (тип `string`) у соответствующих столбцов.
Добавление нового item происходит по схеме:

* Создать экземпляр класса `ItemInListView` и заполнили его поля.
* Добавить его в `ObjectListView` методом `AddObjects` для нескольких item или `AddObject` для одного item.

При таком подходе отпадает необходимость в работе с subItem и к каждому добавленному item можно обращаться как к `ItemInListView`:

```csharp
((ItemInListView)listView1.SelectedObjects[0]).Description
```

При создании столбцов нужно указать `AspectName` для связи с данными и `ImageAspectName` для связи с картинкой. Добавлять столбцы нужно в два массива.

### Пример настройки столбцов

```csharp
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

## Событие TreeViewItemSelected

Событие `TreeViewItemSelected` происходит при перевыборе элемента в treeView. Позволяет при определённых условиях отобразить вместо listView панель с какими-то своими контролами. Например, если выбранная в treeView вершина является листом, т.е. в ней нет форм и таблиц, а только папки (можно узнать по свойству `IsListNodeSelected`), можно вместо listView показать текст со справочной информацией.

В пользовательском приложении нужно подписаться на событие `TreeViewItemSelected` у экземпляра `DesktopCtrl`. В унаследованном классе `TreeViewShowControlsEventArgs` нужно задать значение true полю `Handled` если нужно отобразить свои контролы  и false если нужно отобразить listView. Далее создаются контролы и помещаются в `PanelOnListView`.

### Пример настройки выбора элементов

```csharp
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

![Дерево выбора элементов](/images/pages/products/flexberry-winforms/desktop/treeview-item-selected.png)

## DesktopSettings

Серилиазуемый класс `DesktopSettings` служит для хранения таких пользовательских настроек как размер окна, ширина столбцов, столбцы для отображения и т.д. Для того чтобы настройки сохранялись и загружались, нужно установить свойство `UseSettings` класса `SettingManager` равным true. В таком случае настройки сохраняются перед закрытием пользовательского приложения, а загружаются при запуске.

Хранятся следующие настройки:

* string[] `History` - история строки в `AddressBar`.
* string `curpath` - текущий путь в папках.
* int `Viewmode` - режим представления элементов. 0 – большие иконки, 1 – список, 2 – подробности, 3 – маленькие иконки.
* int `FoldersSplitterPos` - позиция сплиттера между treeView и listView.
* bool `TreeViewVisible` – отображать treeView.
* bool `ListViewShowFolders` – отображать папки в listView.
* bool `ListViewShowGroups` – группировать элементы в listView.
* string `ListViewAlwaysGroupByColumnName` – название столбца, по которому нужно всегда группировать элементы (`Caption` - название, `Description` - описание, `ItemType` - тип, `ParentFolder` - папка).
* string `ListViewColumnSort` – название столбца, по которому производится сортировка элементов listView.
* SortOrder `ListViewSortOrder` – порядок сортировки элементов listView (убывание, возрастание).
* Отображать столбцы: `ColumnDescriptionVisible` – описание, `ColumnItemTypeVisible` – тип, `ColumnParentFolderVisible` – папка. Столбец «Название» отображается всегда.
* Ширина столбцов: `ColumnCaptionWidth` - название, `ColumnDescriptionWidth` - описание, `ColumnItemTypeWidth` - тип, `ColumnParentFolderWidth` – папка.

## Ярлыки рабочего стола

Механизм ярлыков позволяет работать с экземплярами объектов данных с рабочего стола.

Ярлыки стоит применять, если с каким-либо экземпляром приходится работать очень часто. Это позволит не загружать списковую форму и не искать экземпляр на ней.

Ярлыки можно применять для экземпляра любого класса, который является наследником  ICSSoft.STORMNET.DataObject.

### Папки для ярлыков

Папки, в которых можно создавать ярлыки указываются с помощью переопределения метода GetShortCutFolders() класса DesktopCustomizer. Этот метод возвращает ArrayList, элементами которого являются строки, в которых указывается полный путь к папке. По умолчанию этот метод возвращает только папку «Избранное».  

#### Пример папки для ярлыков

```csharp
public class NewDesktopCustomizer : ICSSoft.STORMNET.UI.DesktopCustomizer
{
  …
  public override System.Collections.ArrayList GetShortCutFolders()
  {
      System.Collections.ArrayList arr = base.GetShortCutFolders();
      arr.Add("NewFolder");
      return arr;
  }
  …
}
```

### Имя ярлыка

При создании ярлыка по умолчанию берется имя списковой формы. Для присвоения ярлыку адекватного имени нужно сделать следующее:
Класс соответствующего объекта необходимо пометить атрибутом ShortCutCaption. Параметром этого атрибута служит имя, с которым будет отображаться ярлык. В имени ярлыка можно указать @name@, где в качестве name выступает имя свойства или поля данного класса, тогда при определении имени ярлыка вместо данной конструкции будет подставлено значение указанного свойства или поля соответствующего объекта данных.

#### Пример задания имени ярлыка

```csharp
[ShortCutCaption("Кошка по кличке @Кличка@")]
public class Кошка : ICSSoft.STORMNET.DataObject
{
        …
        public virtual string Кличка
        {
            get
            {
                return this.fКличка;
            }
            set
            {
                this.fКличка = value;
            }
        }
        …
}
```

В результате имя ярлыка для объекта данных «Кошка» будет, например, таким: «Кошка по кличке Мурка».

### Раннеры

Раннер – класс, реализующий методы для запуска ярлыка. В конструкторе раннер принимает ярлык. Чтобы добавить новый раннер, нужно создать новый класс, наследующийся от DesktopShortCutRunner. Методы раннера, помеченные атрибутом RunnerMethodAttribute можно запустить из контекстного меню ярлыка, с данным типом раннера. Параметром атрибута является строка, которая будет отображаться в соответствующем пункте контекстного меню ярлыка.
В базовом классе DesktopShortCutRunner реализованы два стандартных метода:

* `RunEditForm` – запуск формы редактирования. Параметром передается название типа формы редактирования.
* `RunObjectMethod` – Запуск метода объекта. Параметром передается название метода.

Вторым параметром обоих методов может быть передано имя представления для загрузки объекта (этот параметр не обязателен, но в некоторых случаях его отсутствие может привести к ошибкам).
В раннере можно задать дефолтную картинку для ярлыков с данным типом.

### Как применить раннер для ярлыков

Раннер, которым будет запускаться ярлык, определяется автоматически при создании ярлыка.
Для того, чтобы раннер применялся для ярлыков объектов определенного типа, нужно сделать следующее:

1. В раннере реализовать статический метод GetObjectTypes, возвращающий массив типов, для которых следует применять данных раннер.
2. Переопределить метод GetShortCutRunners() класса DesktopCustomizer. Этот метод возвращает словарь, у которого ключ – это имя класса раннера, а значение – это массив поддерживаемых типов объектов, которые возвращает метод GetObjectTypes().

По умолчанию этот метод возвращает только раннер для отчетов (ReportRunner).

#### Пример раннера для ярлыков

```csharp
public class NewRunner : DesktopShortCutRunner
{
        …
        public static Type[] GetObjectsTypes()
        {
            return new Type[] { Type.GetType("ICSSoft.STORMNET.MyType, MyTypeAssemblyName") };
        }
        …
}

public class NewDesktopCustomizer : ICSSoft.STORMNET.UI.DesktopCustomizer
{
        …
        public override Dictionary<string, Type[]> GetShortCutRunners()
        {
            Dictionary<string, Type[]> shortCutRunners = base.GetShortCutRunners();
            shortCutRunners.Add("ICSSoft.STORMNET.NewShortCutRunner, NewShortCutRunnerAssemblyName", NewShortCutRunner.GetObjectsTypes());
            return shortCutRunners;
        }
        …
}
```

### Создание ярлыка из кода

Ярлык создается вызовом статического метода CreateShortCut. Метод принимает имя ярлыка, путь к папке, в которой отображать ярлык, тип раннера, объект данных, которому соответствует ярлык, тип формы редактирования и картинку, для отображения ярлыка. Если в качестве этого параметра передать null, то ярлык будет отображаться с дефолтной картинкой. Еще один необязательный параметр указывает является ли создаваемый ярлык общим или нет. Если ярлык общий, то в базе данных в поле User будет значение null и в дальнейшем его будут видеть все пользователи. Если же ярлык не является общим, то в базе данных ярлык сохранится с текущим пользователем.

## Кнопка состояния папок на рабочем столе

В панели инструментов рабочего стола имеется кнопка `Свернуть/развернуть папки`. Для ее отображения необходимо пристроить свойству `Operations` контрола рабочего стола значение `DesktopOperations.TreeExpandCollapse`. Проще всего сделать это в методе Main формы рабочего стола.

```csharp
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

## Картинка на рабочем столе

Фоновая картинка рабочего стола задается свойством `listView1.OverlayImage.Image`. Можно выбрать любую картинку форматов `gif, jpg, jpeg, bmp, wmf, png`. Можно установить анимированную картинку, но анимироваться она не будет. После смены изображения нужно его обновить вызовом метода `RefreshOverlay`:

```csharp
listView1.RefreshOverlay(listView1.OverlayImage)
```

Выравнивание картинки производится путём задания необходимого значения свойству `OverlayImage.Alignment` (тип - `System.Drawing.ContentAlignment`), после чего рекомендуется обновить картинку вышеуказанным способом.

Чтобы изменить прозрачность картинки, нужно менять значение поля `OverlayImage.Transparency`. Оно принимает значения от 0 (абсолютная прозрачность) до 255 (совершенно непрозрачная картинка), значение по умолчанию 128.

### Пример настройки картинки для рабочего стола

```csharp
listView1.OverlayImage.Image = Image.FromFile("D:\\images\\limeleaf.png");
listView1.OverlayImage.Alignment = ContentAlignment.BottomLeft;
listView1.OverlayImage.Transparency = 50;
```

![Изображение на рабочем столе](/images/pages/products/flexberry-winforms/desktop/desctop-picture.png)

Отступы по горизонтали и вертикали соответственно задаются с помощью атрибутов `OverlayImage.InsetX` и `OverlayImage.InsetY` (типы - int)

## Несколько классов приложений в одной стадии

Если в одной стадии встречается несколько классов со стереотипом [application](fd_application.html),

![Диаграмма с несколькими типами application](/images/pages/products/flexberry-winforms/development/generation/2-app_-u-m-l.jpg)

то в сгенерированной сборке `DesktopCustomizer` будет несколько классов для настройки.

![Настройка классов в коде приложения](/images/pages/products/flexberry-winforms/development/generation/2-app_-s-l-n.jpg)

__Примечание:__ отдельная сборка DesktopCustomizers для каждого приложения не генерируется.

## Поддержка схем Windows XP

Элементы управления `Flexberry Platform` поддерживают схемы пользовательских интерфейсов `Windows XP`: «мягкие» кнопки, градиентные заливки на закладках и т.д., и т.п. Такая поддержка включается явно.

Для того, чтобы настольное приложение поддержало схему пользовательского интерфейса `Windows XP`, необходимо:

* В методе `Main()` приложения вызвать метод `ICSSoft.STORMNET.Windows.Forms.WinApplication.EnableVisualStyles()`;
* Создать `Manifest`-файл в том же каталоге, где расположен exe-файл, например:

Файл приложения — `BugLeR.exe`, файл манифеста — `BugLeR.exe.manifest`, содержимое:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<assembly xmlns="urn:schemas-microsoft-com:asm.v1" manifestVersion="1.0">
<assemblyIdentity
    version="1.0.0.0"
    processorArchitecture="X86"
    name="BugLeR"
    type="win32"
/>
<description>Your application description here.</description>
<dependency>
    <dependentAssembly>
        <assemblyIdentity
            type="win32"
            name="Microsoft.Windows.Common-Controls"
            version="6.0.0.0"
            processorArchitecture="X86"
            publicKeyToken="6595b64144ccf1df"
            language="*"
        />
    </dependentAssembly>
</dependency>
</assembly>
```
