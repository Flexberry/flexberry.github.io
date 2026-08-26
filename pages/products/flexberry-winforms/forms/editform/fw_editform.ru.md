---
title: Формы редактирования (классы со стереотипом editform) 
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, формы, форма редактирования
summary: Особенности генерации, свойства, атрибуты, методы, интерфейсы формы редактирования, универсальная форма редактирования, использование иерархического списка на форме редактирования, рекомендации
toc: true
permalink: ru/fw_editform.html
lang: ru
---

Описание среды `Flexberry` относительно пользовательского интерфейса изложено в [Учебник программиста Flexberry Platform](gbt_flexberry-platform-guide.html) раздел "Пользовательский интерфейс "(пункт 45-65).

Форма редактирования обеспечивает пользовательский интерфейс по вводу/редактированию экземпляра объекта данных в одном, либо нескольких представлениях.

Для описания формы редактирования необходимо создать в CASE UML-класс со стереотипом "editform". Например:

![Пример формы редактирования на диаграмме](/images/pages/products/flexberry-winforms/forms/editform.png)

## Что генерируется

* Класс UI-независимой формы редактирования, наследующийся от ICSSoft.STORMNET.UI.BaseIndpdEdit.
* .Net-интерфейс для зависимой формы редактирования.
* UI-зависимая форма редактирования (если необходимо).
* Атрибуты формы генерируются следующим образом:

  * Если включена генерация UI-зависимой формы, то генерируется .Net-интерфейс UI-зависимой формы редактирования с определением свойства, но только, если этот атрибут публичный.
  * В код UI-зависимой формы генерируется реализующее вышеуказанное интерфейсное определение виртуального свойства, а также приватный член и в обоих аксессорах код по установке/получению значения приватного члена, сопровождаемый скобками программиста.
  * В код UI-независимой формы генерируется определение виртуального свойства с указанным модификатором. Если генерация  UI-зависимой формы включена, то в код свойства генерируется вызов того же свойства UI-зависимой формы через интерфейс.
  * Методы формы генерируются следующим образом:

    * Если включена генерация UI-зависимой формы, то генерируется .Net-интерфейс UI-зависимой формы редактирования с определением метода, но только, если этот метод публичный.
    * В код UI-зависимой формы генерируется реализующий вышеуказанное интерфейсное определение виртуальный метод и в нём скобка программиста (подобно тому, как описано в статье [Методы классов и параметры методов](fd_methods-parameters.html)).
    * В код UI-независимой формы генерируется определение виртуального метода с указанным модификатором. Если генерация UI-зависимой формы включена, то в код метода генерируется вызов того же метода UI-зависимой формы через интерфейс.

## Дополнительно редактируемые свойства и что как генерируется

Самое главное в дополнительно редактируемых свойствах - выбор класса и представления, в котором должен редактироваться объект этого класса. Указание этого производится на закладке "Составные представления":

![Представления для формы редактирования](/images/pages/products/flexberry-winforms/forms/editformviews.jpg)

В `Flexberry Plugins` можно указать только один класс и одно представление, в котором должен редактироваться объект на этой форме. Если указать несколько классов и/или представлений, все, кроме первого, будут проигнорированы.

Итак, для того, чтобы выбрать класс и представление, нужно нажать на кнопку "...":

![Выбор представления](/images/pages/products/flexberry-winforms/forms/view-sel.jpg)

### Закладка "Форма"

![Свойства формы редактирования](/images/pages/products/flexberry-winforms/forms/editformprops.jpg)

| Свойство-Описание | Генерация в .Net-язык |
|--|--|
| `Name` - имя UML-класса | Имя .Net-класса независимой формы и производное от него для зависимой (например, ```winform[Name]```).
| `Description` - некоторое описание класса.| `DocComment` перед определением класса независимой формы
| `Caption` - некоторое пользовательское имя (отображается в пользовательском интерфейсе)| Заголовок на форме
| `GenerateDependedForm`| Если галочка установлена, - генерируется UI-зависимая (WinForm) форма пользовательского интерфейса в исходном коде, что позволяет "вручную" разместить на форме элементы управления. Если галочка не установлена, используется универсальная UI-независимая форма редактирования, на которой элементы управления, соответствующие атрибутам в представлении размещаются автоматически.
| `FixDependedForm`| Если галочка установлена, - UI-зависимая (WinForm) форма не перегенерируется. Это сделано для уверенности в том, что внесённые в форму изменения в части состава и расположения элементов управления не будут потеряны, что иногда происходит из-за наличия (к сожалению) ошибок в среде `Visual Studio .Net`. <br> Если галочка не установлена генерация происходит следующим образом: <br> 1. Если представление (его имя и атрибутный состав) не изменилось, тогда перегенерируется невизуальная часть формы (методы, свойства и т.д.); <br> 2. Если полностью изменилось представление (его имя, т.е. после последней генерации было указано другое используемое представление), то визуальная часть перегенерируется полностью, все элементы управления удаляются и размещаются новые в соответствии с новым представлением. <br>3. Если изменился атрибутный состав представления, то на форму добавляются элементы управления, соответствующие добавленным в представление атрибутам. Эл-ты управления, соотв. удалённым атрибутам не уничтожаются на форме.
| `Packet, NamespacePostfix` - позволяют настроить сборку и пространство имен | см. [Расположение сборок после генерации кода](fo_location-assembly.html).
| `PBCustomAttributes` - позволяет указать, необходима ли скобка программиста непосредственно перед описанием класса для "ручного" внесения атрибутов | Если галочка указана - генерируется скобка программиста для "ручного" внесения .Net атрибутов перед классом независимой формы.
| `PBMembers`| Если галочка указана - генерируется скобка программиста для "ручного" внесения членов класса независимой формы.
| `PropertyLookup`| Позволяет на отдельной форме настроить, какие списковые формы должны открываться с этой формы для выбора соответствующих связанных объектов (мастеров).
| `EditFormOperations`| Настройка доступных на форме операций.
| `PrintContainer` - имя класса со стереотипом `printform`, являющегося печатной формой, которая используется при печати с формы редактирования.| В коде независимой формы генерируется перегрузка метода GetPrinter, в котором:<br>* Если `PrintContainer` не указан, возвращается ошибка `NoSuchContainerException`; <br> * Если `PrintContainer` указан, возвращается тип контейнера; <br>В этом методе перед возвратом значения располагается неотключаемая скобка программиста, в которой можно закодировать "вручную" возвращаемый тип в случае наличия логики, которая, например, возвращает печатную форму в зависимости от свойств объекта.
| `ScriptName` - имя сценария, который должен использоваться этой формой. Соответствует имени EBSD-диаграммы, использующейся для описания сценария.| Метод `GetScript` в классе независимой формы перегружается таким образом, что возвращает из провайдера сценариев сценарий с указанным именем.
| `PublishToEBSD`| Если галочка указана - перед классом независимой формы генерируется указание атрибута `PublishToEBSDAttribute`, который указывает доступность данного класса для использования в редакторе диаграмм сценариев.|

#### PropertyLookup

`PropertyLookup` генерируется в код метода `GetEditor` UI-независимой формы редактирования, который и возвращает тип списковой формы в зависимости от имени мастерового свойства.

![PropertyLookup](/images/pages/products/flexberry-winforms/forms/propertylookup.jpg)

В верхнем списке расположены мастеровые свойства, в нижнем выпадающем списке следует выбрать форму, которая должна открываться для выбора пользователем соотв. связанного объекта.

#### EditFormOperations

Позволяет указать, какие операции доступны на этой форме (соответственно, какие кнопки появляются на панели управления):

* `Save` - сохранение;
* `Save and Close` - сохранение с последующим закрытием формы;
* `Print` - печать;
* `Print preview` - предварительный просмотр.

![EditFormOperations](/images/pages/products/flexberry-winforms/forms/editformoperations.jpg)

Генерируется:

* Если используется универсальная форма (галочка `GenerateDependedForm` не установлена), генерируется параметром при вызове конструктора зависимой формы в методе `GetDpdForm()` независимой формы;
* Если используется генерируемая форма, генерируется напрямую в зависимой форме установкой видимости кнопок панели инструментов.

### Свойства атрибутов

Свойства атрибутов аналогичны указанным в статье [Атрибуты классов данных](fo_attributes-class-data.html), с учётом вышеуказанных (в п 4 "Что генерируется") замечаний.

### Свойства методов

Свойства методов аналогичны указанным в статье [Методы классов и параметры методов](fd_methods-parameters.html), с учётом вышеуказанных (в п 5"Что генерируется") замечаний.

## Универсальная форма редактирования

Конечно, можно «вручную» размещать контролы и связывать их через `EditManager` с объектами данных, однако, для облегчения этого труда, существует универсальная форма редактирования, которая автоматически, по указанию типа объекта данных, представлений, размещает элементы управления и обеспечивает пользователя интерфейсом по редактированию объекта данных (панель инструментов, строка статуса).

Для того чтобы использовать универсальную форму редактирования, необходимо:

* Конструировать универсальную форму редактирования, передав в конструктор параметром представления, в которых должно осуществляться редактирование:

```csharp
StormNetUI.IDpdEditForm editcont = new StormNetUI.UniWinEdit(new StormNet.View[]{viewforedit});
```

* Установить обработчики на события (как минимум, на `CloseEvent`, чтобы можно было закрыть форму и `SaveEvent`, чтобы «отловить» вызов пользователем сохранения).
* Вызвать метод Edit, передавая объект данных.

Следует понимать, что форма реализует абсолютно «голый» пользовательский интерфейс, вся реакция от пользователя транслируется «наружу» формы через события, соответственно, эта управление формой производится через набор методов. Так, когда пользователь просто закрывает форму редактирования, посылается сообщение `CloseEvent`, но сама форма не закрывается. Чтобы закрыть форму, необходимо вызвать метод `Close`.

_Пример обработчика события `CloseEvent`:_

```csharp
private void ContainerCloseHandler (object sender, StormNetUI.CloseEventArgs args)
  {
    ((StormNetUI.IDpdForm)sender).CloseForm();
  }
```

{% include note.html content="Если необходимо выполнить переход на генерируемую форму с универсальной с контролами ICustomizable, метод Customize необходимо вызвать вручную в перегруженном методе Edit." %}

## Фокус ввода и «Ctrl+S»

При сохранении формы редактирования по комбинации __«Ctrl+S»__ фокус остается на активном контроле, текущее значение попадает в объект данных с помощью вызова `EditManager.ApplyDataFromControl(ActiveControl)`.

## Интерфейсы форм редактирования

Нижеприведённая диаграмма показывает интерфейсы для инициатора, для редактора:
![Интерфейсы формы редактирования](/images/pages/products/flexberry-winforms/forms/primer10.jpg)

## Создание формы редактирования с иерархическим списком

Бывают случаи, когда на форме редактирования необходимо создать иерархический список.

Например, в модели иерархический список выглядит так:

![Пример иерархического списка](/images/pages/products/flexberry-winforms/controls/olv/object-hierarchical-view.png)

Однако по-умолчанию генерируется отдельная форма для отображения иерархии (в данном случае, "Территория)". Для того чтобы иерархический список был отображен на форме редактирования, на форму необходимо добавить контрол `ObjectHierarchicalView`. Чтобы это сделать следует выполнить ряд действий:

* Сгенерировать форму редактирования.
* Добавить на форму в `Form Designer Fields`:

```csharp
// *** Start programmer edit section *** (Form Designer Fields)

  //...
  private ICSSoft.STORMNET.Windows.Forms.ObjectHierarchicalView objectHierarchicalView1;

// *** End programmer edit section *** (Form Designer Fields)
```

* Далее следует внести дополнения в `InitializeComponent`.

  * Дополнения InitializeComponent

Для корректного отображения `ObjectHierarchicalView` необходимо правильно прописать все свойства контрола.

```csharp
 private void InitializeComponent()
{
  System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WinformTestHierarchicalForm));
  //...
  this.objectHierarchicalView1 = new ICSSoft.STORMNET.Windows.Forms.ObjectHierarchicalView();

  //
  // objectHierarchicalView1
  //
  this.objectHierarchicalView1.AdvansedColumns = null;
  this.objectHierarchicalView1.AdvansedMarks = false;
  this.objectHierarchicalView1.AllowSorting = true;
  this.objectHierarchicalView1.AutoRefreshSupport = false;
  this.objectHierarchicalView1.DataObjectTypes = new System.Type[] {
  ((System.Type)(typeof(IIS.TestStandWinforms.Территория)))};
  this.objectHierarchicalView1.DataService = null;
  this.objectHierarchicalView1.Dock = System.Windows.Forms.DockStyle.None;
  this.objectHierarchicalView1.DrawGrid = false;
  this.objectHierarchicalView1.ExtendLastCol = true;
  this.objectHierarchicalView1.FillDataOnLoad = true;
  this.objectHierarchicalView1.FillDataOnLoadUserAllowed = true;
  this.objectHierarchicalView1.GrayedOnLoad = true;
  this.objectHierarchicalView1.HideToolBar = false;
  this.objectHierarchicalView1.HierarchicalMasterName = "Иерархия"; //Имя мастерового объекта, указывается в поле HierarchicalMaster списковой формы*.
  this.objectHierarchicalView1.Limit = null;
  this.objectHierarchicalView1.LoadingPackageSize = 500;
  this.objectHierarchicalView1.Location = new System.Drawing.Point(10, 100);
  this.objectHierarchicalView1.MemoryTimeLoadLimit = ((long)(200));
  this.objectHierarchicalView1.Name = "objectHierarchicalView1";
  this.objectHierarchicalView1.NodesStyle = ((ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle)((ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle.Lines | ICSSoft.STORMNET.Windows.Forms.TreeNodesStyle.Symbols)));
  this.objectHierarchicalView1.RowCountQuery = 12345;
  this.objectHierarchicalView1.SaveExpandedRows = false;
  this.objectHierarchicalView1.ShowCyclicalReferencedObjects = false;
  this.objectHierarchicalView1.ShowStatusBar = true;
  this.objectHierarchicalView1.ShowToolTip = false;
  this.objectHierarchicalView1.ShowTreeColumnName = "";
  this.objectHierarchicalView1.Size = new System.Drawing.Size(600, 322);
  this.objectHierarchicalView1.TabIndex = 6;
  this.objectHierarchicalView1.TreeLeafImage = null;
  this.objectHierarchicalView1.TreeLineColor = System.Drawing.Color.DarkGray;
  this.objectHierarchicalView1.TreeLineStyle = System.Drawing.Drawing2D.DashStyle.Dot;
  this.objectHierarchicalView1.TreeNodeImage = null;
  this.objectHierarchicalView1.TreeNodeImageCollapsed = null;
  this.objectHierarchicalView1.TreeNodeImageExpanded = null;
  this.objectHierarchicalView1.UseAlternativeColoring = false;
  this.objectHierarchicalView1.UseColumnOptimization = false;
  this.objectHierarchicalView1.UseHotkeyForEdit = true;
  this.objectHierarchicalView1.UseLimitFunctionExtension = false;
  this.objectHierarchicalView1.UseToolBar = null;
  this.objectHierarchicalView1.ViewName = "ТерриторияL";//Имя представления, которое используется на иерархическом списке.
```

## Рекомендации по доработке форм редактирования

Важно понимать, что форма служит не только для редактирования данных, но и для просмотра данных, а также для управления данными - запуск операции экспорта, печати, работы с данными аудита и прочее.

Поэтому проработку внешнего вида лучше начинать с того, что следует определить какие задачи пользователи будут выполнять с помощью формы. Основные - уже перечислены, это: редактирование, просмотр, выполенение операций над объектом, редактируемым на форме (экспорт, печать и прочее).

Для того чтобы сделать форму удобной для ввода, следует обратить внимание на следующие моменты:

* порядок обхода (переход по Tab),
* визуальное выделение фокуса - неопытные пользователи могут даже и не знать, что такое фокус ввода и что он отображется мигающей вертикальной чертой в поле ввода,
* [предиктивный ввод](fw_predict-input.html),
* отображение полей, обязательных для ввода. <br> Вариант реализации: через [DataObjectErrorProvider](fw_edit-form-validation.html). DataObjectErrorProvider позволит быстро и со вкусом прописать в коде перечень обязательных полей и пользователи приложения не смогут его менять.
* значения по умолчанию,
* автоматизация ввода пользовательских данных, например, ввод адреса или возможность ввести ключевые реквизиты другого объекта данных с возможностью автоматического поиска.

Для облегчения просмотра и поиска глазами на форме нужных данных применяются следующие механизмы:

* объединение элементов управления в логические группы (GroupBox) с указанием названия группы - общего признака всех атрибутов в этой группе,
* расположение контролов по сетке в несколько столбцов и строк. ширина полей ввода в одном столбце должна быть одинаковой, заголовки полей в столбцах начинаются с одной позиции в строке,
* увеличения шрифта. Бывают случаи, когда шрифт должен быть увеличен (например, когда большинство пользователей этой формы - пожилые люди с плохим зрением),
* реализация работы формы редактирования в режиме "[Только на чтение](fw_editmanager.html)" . Здесь работа заключается в проверке, что ненужные функции заблокированы, а нужные работают корректно.

Для выполнения операций над данными зачастую используется панель инструментов, на которой по умолчанию есть кнопки "Сохранить" и "Сохранить и закрыть". Для каждой операции лучше создавать отдельную кнопку на этой панели с чёткой иконкой, лаконичной надписью и понятной всплывающей подсказкой (tooltip). Между кнопками используйте вертикальный разделитель.

Расположение контролов на форме и группировка их в группы - комплексная задача, которая должна решаться совместно с аналитиком.

Также формы редактирования могут предоставлять следующие универсальные сервисы:

* аудирование изменений данных и предоставление доступа к данным аудита,
* проверка данных на соблюдение пользовательских условий,
* разграничение доступа к данным с помощью системы полномочий,
* хранение пользовательских настроек.

## Принудительный вызов формы редактирования

Вызов формы редактирования

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.Edit(oОбъект, "", "");
```

Если требуется функциональность по сохранению объекта при нажатию на кнопку "Сохранить" или "Сохранить и закрыть" на зависимой форме, то код будет выглядеть таким образом:

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.SaveEvent += (sr, ea) => { new BusinessService().UpdateObject(ea.dataobject); };
cont.Edit(oОбъект, "", "");
```

## Передача параметров от ContRunner до формы редактирования

### Передача параметров в DesktopCustomizer

Для того чтобы передать параметр контейнеру запуска, необходимо использовать перегруженный метод создания `ContRunner`.

```csharp
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.LimitsTesting_Blog.LTB_БлогL), "MyTag1FromContRunner", "LimitsTesting_Blog", "Блог", ""));
```

### Приём параметра в списковой форме и передача на форму редактирования

Параметр в списковую форму попадает в метод `Activate(object tag)`. Следует обращать внимание на количество параметров в перегруженном методе `OnNewEvent()`. Перегрузить следует метод с двумя параметрами, а вызвать в нём метод с тремя. По-умолчанию создание объектов происходит без передачи тега.

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

### Приём параметра на форме редактирования

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

## Связанные статьи

[E-представление](fd_e-view.html)
