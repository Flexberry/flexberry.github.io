---
title: GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Основные моменты использования компонента для редактирования детейлов GroupEdit и список статей для рассмотрения конкретых особенностей использования
toc: true
permalink: ru/fw_group-edit.html
lang: ru
---

<!-- Данная статья ещё редактируется -->

## Описание

`GroupEdit` - Windows-контрол для создания и редактирования [детейлов](fo_detail-associations-properties.html).

### Свойства GroupEdit'a

| Свойство  | Описание |
| ------------- | ------------- |
| `AdvansedMarks`  | Добавление дополнительных кнопок управления выделением строк и меток на панель управления GroupEdit'a.  |
| `AllowRowLocking`  | Поддержка режима редактирования с блокировками.  |
| `AlternativeColor`  | Альтернативный цвет для раскраски строк.  |
| `BackOnShiftEnter` | Переход на следующую ячейку по нажатию &lt;Shift&gt; + &lt;Enter&gt; при выключенном режиме `EditOnEnter`.
| `DoNotAutoLoadItems` | Не загружать данные из базы автоматически.
| `EnableValueDisplayResponsibility` | Поддержка режима [`ValueDisplayResponsibility`](fw_displaying-master-in-groupedit.html).
| `EditOnEnter` | Редактирование содержимого ячейки по нажатию на клавишу &lt;Enter&gt;
| `GenerateValueChangedEventOnRowOperations` | Генерировать события изменения при добавлении или удалении строки.
| `KeepFocus` | По умолчанию "false". Если установлено "true", то при сохранении будет установлен фокус на той же строке, где он был до сохранения. Запоминается не номер строки, а идентификатор выделенного объекта.
| `LeaveOnLastEnter` | Переходить к следующему контролу при нажатии на клавишу &lt;Enter&gt; в последней строке.
| `MoveNextOnEnter` | Переходить к следующей ячейке по клавише &lt;Enter&gt; при включенном режиме `EditOnEnter`.
| `NewRowOnInsert` | Переходить на первую ячейку новой строки при нажатии &lt;Enter&gt;.
| `NextOnEnter` | Перемещать активную ячейку по клавише &lt;Enter&gt; при выключенном режиме `EditOnEnter`.
| `ReadOnly` | Режим "только чтение".
| `SortOrder` | Настройка сортировки столбца: Asc (по возрастанию), Desc (по убыванию), None (без сортировки). 
| `SortPriority` | Настройка приоритета сортировки столбца.
| `ShowStatusBar` | Отображение полосы состояния, на которой показывается количество элементов.
| `UseAlernativeColoring` | Использовать чередующуюся окраску строк (базовый/альтернативный цвет). |

### Изменение значения в контроле, который вставлен в GroupEdit

Для того, чтобы выполнить настройку контрола, который встраивается в ячейку GroupEdit, нужно использовать событие `gr_SetupEditorEventHandler`, обработчик которого автоматически генерируется на формах редактирования. В аргументы данного события передаются и объект данных, контрол и имя редактируемого в данный момент свойства.
Пример:

```csharp
protected override void gr_SetupEditorEventHandler(object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e)
{
    // *** Start programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ))
            
    // *** End programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ))
    base.gr_SetupEditorEventHandler(sender, e);
    // *** Start programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ) End)
    DateTimePicker dateTimePicker = e.control as DateTimePicker;
    ICSSoft.STORMNET.DataObject dataObject = e.dataObject;
    string propertyName = e.propertyName;

    if (dateTimePicker != null && dataObject.GetStatus() == ObjectStatus.Created)
    {
        dateTimePicker.Value = DateTime.Now.AddMonths(1);
    }

    // *** End programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ) End)
}
```

### Сортировка

Как и [ObjectListView](fw_objectlistview.html) `GroupEdit` позволяет отсортировать содержимое по различным столбцам. Чтобы быстро настроить многоуровневую сортировку, можно щелкать по столбцам левой кнопокой мыши, зажав клавишу &lt;Ctrl&gt;

Чтобы многоуровневая сортировка не сбросилась при случайном нажатии на столбец добавлен уточняющий вопрос о смене сортировки.

### Именованые настройки отображения столбцов

Существует возможность сохранения именованых настроек отображения столбцов по аналогии с [ObjectListView](fw_objectlistview.html). Настройки сохраняются в базе данных отдельно для каждого пользователя.

{% include note.html content="Данная опция работает только при включенной настройке `UseSettings = true` в файле конфигурации." %}

### Прорисовка границ ячеек

Сделать рамки в `GroupEdit` можно, используя следующий код:

```csharp
C1FlexGrid ge = GetGridFromGE(Лапа);
ge.Styles.Normal.Border.Direction = BorderDirEnum.Both;
ge.Styles.Normal.Border.Style = BorderStyleEnum.Flat;
```

`GroupEdit` с прорисованными границами будет выглядеть следующим образом:

![](/images/pages/products/flexberry-winforms/controls/groupedit/groupedit-explain.png)

### EditManager

Обычно `GroupEdit` располагается на [форме редактирования](fd_editform.html), имеющей свой [`EditManager`](fw_editmanager.html). Однако, у `GroupEdit'а` есть __свой `EditManager`__, отвечающий за связывание и события.

К примеру, если необходимо отловить событие возврата значения при выборе мастера, то необходимо подписаться на событие `AfterChangeProperty` EditManager'a, относящегося к GroupEdit'у, а не к странице редактирования:

```csharp
GroupEdit1.EditManager.AfterChangeProperty += (o, s) => 
{
    // Обработчики
};
```

{% include note.html content="Стоит учесть, что событие `AfterChangeProperty` при выборе мастера сработает _дважды_: 1ый раз при нажатии на кнопку [лукапа](fa_lookup-overview.html), а 2ой раз при возврате значения." %}


В `GroupEdit` добавлена полоса состояния, отображающая количество элементов.

Чтобы включить отображение полосы состояния, необходимо установить свойство `ShowStatusBar = true;`


## Полезные ссылки по GroupEdit

* Некоторые часто задаваемые вопросы освящены в статье [FAQ по вводному обучению](gbt_initial-trainig-faq.html), а также в статье [WinForms UI FAQ](fw_winforms-ui-faq.html).
* [Обработка нажатий клавиш контролами в GE](fw_processing-keystrokes-of-ge.html).
* [Отображение мастера в `GroupEdit`](fw_displaying-master-in-groupedit.html).
* [Блокировка редактирования отдельных записей в `GroupEdit`](fw_lock-rows-in-groupedit.html).
* [Настройка `ToolBar` в `GroupEdit` (настройка вертикального размера)](fw_setting-toolbar-in-groupedit.html).
* Установка формата даты [в режиме редактирования](fw_groupedit-date-format-edit.html) и  [в режиме отображения](fw_groupedit-date-format-view.html).
* [Получение FlexGrid из GroupEdit](fw_flex-grid.html).
* Обработка событий:
    * [Обработка события отметки строк в `GroupEdit`](fw_processing-events-mark-the-rows-in-groupedit.html).
    * [События добавления и удаления в `GroupEdit`](fw_addition-and-removal-events-in-groupedit.html).
* [Ограничение-тип-лукапа-combo-в-GroupEdit](fw_restriction-type-lookup-combo-in-groupedit.html).
* [Функциональность при работе с массивами детеиловых объектов DetailArray](fo_functionality-work-detail-array.html).
* [Наложение ограничений на GroupEdit](fw_add-limit-to-groupedit.html).


## Расширения GroupEdit
Для `GroupEdit` существует ряд расширений, например:
* [GEEditorExt](fw_ge-editor-ext.html) (редактирование детейлов в отдельном окне).
* [GEEmptyDetailRemover](fw_ge-empty-detail-remover.html) (удаление пустых строк).