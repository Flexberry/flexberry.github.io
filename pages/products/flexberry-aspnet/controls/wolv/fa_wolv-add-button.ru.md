---
title: Добавление кнопки в тулбар или в строки WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-add-button.html
lang: ru
---

## Добавление  кнопок в тулбар WOLV

Чтобы добавить кнопку в Toolbar WOLV необходимо воспользоваться методом `AddImageButton(...)` списка.
Однако, прежде чем добавлять кнопку в Toolbar, стоит определиться, какой обработчик будет у данной кнопки:

* Серверный
* Клиентский
* Серверный и клиентский.

### Серверный обработчик

Если обработчик должен быть серверным, то необходимо воспользоваться следующей перегрузкой метода `AddImageButton`:

```csharp
/// <summary>
/// Метод для добавления пользовательской кнопки в тулбар с возможностью установки
/// серверного обработчика.
/// </summary>
/// <param name="id">Серверный идентификатор кнопки.</param>
/// <param name="cssClass">CSS класс, который применится к кнопке.</param>
/// <param name="alternateText">Текст подсказки к кнопке.</param>
/// <param name="clickHandler">Серверный обработчик нажатия кнопки.</param>
public void AddImageButton(string id, string cssClass, string alternateText, ToolBarBtnEventHandler clickHandler)
```

Как можно заметить, в метод передаётся ID кнопки, CSS-класс, текст всплывающей подсказки, а также серверный обработчик нажатия следующего вида:

```csharp
public delegate void ToolBarBtnEventHandler(LinkButton sender, ToolBarBtnEventArgs eventArgs);
```

#### ToolBarBtnEventArgs

`ToolBarBtnEventArgs` - аргументы, передаваемые в серверный обработчик нажатия кнопки в Toolbar'e WOLV.

Информация, которую можно получить из `ToolBarBtnEventArgs`:

| Название | Описание |
| -------- | -------- |
| `List<string> PrimaryKeys` | Список ключей объектов, выделенных в списке. __Важно: если установлена опция "Выделить всё на всех страницах", то вернётся пустой список__. |
| `bool IsAllSelected` | Установлена ли опция "Выделить всё на всех страницах". |
| `WebObjectListView WOLV` | Экземпляр списка, на Toolbar'e которого была нажата кнопка. |
| `Function LimitFunction` | Ограничивающая функция, наложенная на список. |

#### Пример использования

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestToolbarServerButton", // ID кнопки
        "wolv-test-toolbar-server-button", // CSS-Класс
        "Тестовая серверная кнопка тулбара", // Текст всплывающей подсказки
        CustomToolbarButtonClickHandler); // Обработчик
    ...
}
...

private void CustomToolbarButtonClickHandler(object sender, ToolBarBtnEventArgs eventArgs)
{
    TestToolbarButtonLabel.Visible = true;

    // Проверка свойства Wolv аргументов события.
    if (eventArgs.Wolv != WebObjectListView1)
    {
        throw new Exception("Экземпляр WOLV в обработчике нажатия кнопки тулбара задан неверно.");
    }

    // Проверка свойства PrimaryKeys.
    if (eventArgs.PrimaryKeys.Count > 0)
    {
        var objects = eventArgs.PrimaryKeys.Select(x => new Город { __PrimaryKey = Guid.Parse(x) })
                            .Cast<DataObject>().ToArray();
        DataServiceProvider.DataService.LoadObjects(objects, Город.Views.ГородL, true);

        SelectedCitiesLabel.Visible = SelectedCitiesList.Visible = true;
        foreach (var obj in objects)
        {
            SelectedCitiesList.Controls.Add(new HtmlGenericControl("li") { InnerText = ((Город)obj).Наименование });
        }
    }

    // Проверка свойства IsAllSelected.
    AllIsSelectedLabel.Visible = eventArgs.IsAllSelected;
}
```

### Клиентский обработчик

Если обработчик должен быть клиентским, необходимо воспользоваться следующей перегрузкой метода `AddImageButton`:

```csharp
/// <summary>
/// Метод для добавления пользовательской кнопки в тулбар с возможностью установки
/// клиентского обработчика.
/// </summary>
/// <param name="id">Серверный идентификатор кнопки.</param>
/// <param name="cssClass">CSS класс, который применится к кнопке.</param>
/// <param name="alternateText">Текст подсказки к кнопке.</param>
/// <param name="clientClickHandler">Наименование JS функции в глобальном объекте - клиентского обработчика нажатия кнопки.</param>
/// <param name="clientClickAddParams">Дополнительные параметры для JS обработчика.</param>
public void AddImageButton(string id, string cssClass, string alternateText, string clientClickHandler, string clientClickAddParams)
```

Кроме ID, CSS-класса и текста всплывающей подсказки в метод также передается имя JS-функции, а также параметры, которые стоит передать в эту функцию.

{% include note.html content="Передаётся имя JS-функции, а не её код." %}

#### Пример использования

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestToolbarClientButton", // ID кнопки
        "wolv-test-toolbar-client-button", // CSS-класс
        "Тестовая клиентская кнопка тулбара",  // Текст подсказки
        "ToolbarBtnClickAlert", // Название JS-функции.
        // В принципе, вариант alert(\"Нажата пользовательская кнопка тулбара с клиентским обработчиком\");
        // тоже прокатывает, но при более сложных обработчиках может работать некорректно.
        // Рекомендуется передавать имя функции и описывать её отдельно!
        string.Empty); // Параметры, передаваемые в функцию ToolbarBtnClickAlert.
    ...
}
```

### Серверный и клиентский обработчик

Чтобы добавить кнопку с одновременно и серверным, и клиентским обработчиком, необходимо воспользоваться следующей перегрузкой метода `AddImageButton(...)`:

```csharp
/// <summary>
/// Добавление кнопки на тулбар.
/// </summary>
/// <param name="lb">Кнопка для добавления.</param>
public void AddImageButton(LinkButton lb) 
```

При использовании этого метода важно понимать следующее:

1. Использовать этот метод необходимо только тогда, когда другие методы не подходят.
2. Вся настройка кнопки (включая внешний вид и стили) ложится на прикладного разработчика.

#### Пример использования

```csharp
var twoHandlersButton = new LinkButton
{
    OnClientClick = "alert('Сработал серверный обработчик кнопки с 2 обработчиками.');",
    ID = "TwoHandlersButton",
    CssClass = "ics-wolv-toolbar-button wolv-test-toolbar-client-server-button",
};
twoHandlersButton.Click += CustomToolbarButtonClickHandler1;
twoHandlersButton.ToolTip = "Кнопка с двумя обработчиками.";
WebObjectListView1.AddImageButton(twoHandlersButton);
```

## Добавление кнопок в строки WOLV

Чтобы добавить кнопку в Toolbar WOLV необходимо воспользоваться методом `AddImageButtonToRow(...)` списка.

Однако, прежде чем добавлять кнопку в Toolbar, стоит определиться, какой обработчик будет у данной кнопки:

* Серверный
* Клиентский.

### Серверный обработчик

Если обработчик должен быть серверным, то необходимо воспользоваться следующей перегрузкой метода `AddImageButtonToRow`:

```csharp
/// <summary>
/// Добавить кнопку в каждую строку
/// </summary>
/// <param name="id">ID кнопки (прибавится индекс строки)</param>
/// <param name="cssClass">Класс контрола</param>
/// <param name="alternateText">Подпись</param>
/// <param name="clickHandler">Серверное событие, которое придёт при нажатии
/// PrimaryKey объекта в строке можно получить так:
/// primaryKey = (sender as ImageButton).Attributes["pk"];
/// </param>
public void AddImageButtonToRow(string id, string cssClass, string alternateText, ToolBarBtnEventHandler clickHandler)
```

Как и в случае с добавлением серверной кнопки на Toolbar, в метод передаётся ID кнопки, CSS-класс, текст всплывающей подсказки, а также серверный обработчик нажатия следующего вида:

```csharp
public delegate void ToolBarBtnEventHandler(LinkButton sender, ToolBarBtnEventArgs eventArgs);
```

#### ToolBarBtnEventArgs

Аналогично EventArgs'ам Toolbar'a.

#### Пример использования

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButtonToRow(
        "TestRowServerButton", // ID кнопки
        "ics-wolv-toolbar-button ics-wolv-toolbar-button-icon wolv-test-row-server-button", // CSS-класс
        "Тестовая серверная кнопка в строке", // Текст всплывающей подсказки
        CustomToolbarButtonClickHandler); // Серверный обработчик нажатия
    ...
}

...

private void CustomToolbarButtonClickHandler(object sender, ToolBarBtnEventArgs eventArgs)
{
    TestToolbarButtonLabel.Visible = true;

    // Проверка свойства Wolv аргументов события.
    if (eventArgs.Wolv != WebObjectListView1)
    {
        throw new Exception("Экземпляр WOLV в обработчике нажатия кнопки тулбара задан неверно.");
    }

    // Проверка свойства PrimaryKeys.
    if (eventArgs.PrimaryKeys.Count > 0)
    {
        var objects = eventArgs.PrimaryKeys.Select(x => new Город { __PrimaryKey = Guid.Parse(x) })
                            .Cast<DataObject>().ToArray();
        DataServiceProvider.DataService.LoadObjects(objects, Город.Views.ГородL, true);

        SelectedCitiesLabel.Visible = SelectedCitiesList.Visible = true;
        foreach (var obj in objects)
        {
            SelectedCitiesList.Controls.Add(new HtmlGenericControl("li") { InnerText = ((Город)obj).Наименование });
        }
    }

    // Проверка свойства IsAllSelected.
    AllIsSelectedLabel.Visible = eventArgs.IsAllSelected;
}
```

### Клиентский обработчик

Если обработчик должен быть клиентским, необходимо воспользоваться следующей перегрузкой метода `AddImageButton`:

```csharp
/// <summary>
/// Добавить кнопку в каждую строку
/// </summary>
/// <param name="id">ID кнопки (прибавится индекс строки)</param>
/// <param name="cssClass">Класс контрола</param>
/// <param name="alternateText">Подпись</param>
/// <param name="clientClickHandler">Имя клиентской функции, которая будет вызвана при клике</param>
/// <param name="clientClickAddParams">Дополнительные параметры, которые передадутся в клиентскую функцию</param>
public void AddImageButtonToRow(string id, string cssClass, string alternateText, string clientClickHandler, string clientClickAddParams)
```

Кроме ID, CSS-класса и текста всплывающей подсказки в метод также передается имя JS-функции, а также параметры, которые стоит передать в эту функцию.

{% include note.html content="Передаётся имя JS-функции, а не её код." %}

#### Пример использования

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestRowClientButton", // ID кнопки
        "ics-wolv-toolbar-button ics-wolv-toolbar-button-icon wolv-test-row-client-button", // CSS-класс
        "Тестовая клиентская кнопка в строке",  // Текст подсказки
        "RowBtnClickAlert", // Название JS-функции.
        // В принципе, вариант alert(\"Нажата пользовательская кнопка в строке с клиентским обработчиком\");
        // тоже прокатывает, но при более сложных обработчиках может работать некорректно.
        // Рекомендуется передавать имя функции и описывать её отдельно!
        string.Empty); // Параметры, передаваемые в функцию RowBtnClickAlert.
    ...
}
```
