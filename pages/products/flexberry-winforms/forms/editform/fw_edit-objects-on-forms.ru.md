---
title: Редактирование объектов данных на формах
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, формы, контролы, объекты данных
summary: Переключение редактируемых объектов, ручное создание формы редактирования, обновление и межформенное взаимодействие при редактировании объекта
toc: true
permalink: ru/fw_edit-objects-on-forms.html
lang: ru
---

Редактирование объектов данных на формах имеет множество нюансов и возможностей. Наиболее распространенные изложены в данной статье.

## Переключение редактируемого объекта без закрытия формы редактирования

Иногда сценарий работы системы подразумевает последовательное переключение просматриваемых или редактируемых объектов на форме редактирования без закрытия самой этой формы. Аналогом такой функциональности является просмотр событий в системном логе Windows (EventLog). Flexberry Platform для организации данного сценария поддерживает специальную функцию, доступную из независимых форм редактирования: `SwitchEditingObject(newObject)`.
Типичная и рекомендуемая реализация со стороны прикладного проекта может быть выполнена следующим образом:

* Зависимая форма включает интерфейсную часть по переключению объектов (имеется в виду пользовательский интерфейс: кнопки и прочие контролы).

* Зависимая форма содержит события, которые генерирует интерфейсная часть (переход вперёд, назад, на указанный объект и пр.). Срабатывание этих событий привязано к соответствующим контролам. Пример:

```csharp
/// <summary>
/// Делегат для события перемотки объектов
/// </summary>
/// <param name="sender"></param>
/// <param name="e"></param>
public delegate void PrevNextEventArgsHandler(object sender, EventArgs e);

//...
// *** Start programmer edit section *** (WinformПланетаE CustomMembers)
///<summary>
/// Перемотка вперёд
///</summary>
public event PrevNextEventArgsHandler NextEvent;
/// <summary>
/// Перемотка назад
/// </summary>
public event PrevNextEventArgsHandler PrevEvent;

private void button1_Click(object sender, EventArgs e)
{
    if (NextEvent != null)
    {
        NextEvent(this, e);
    }
}

private void button2_Click(object sender, EventArgs e)
{
    if (PrevEvent != null)
    {
        PrevEvent(this, e);
    }
}

// *** End programmer edit section *** (WinformПланетаE CustomMembers)
```

* Независимая форма видит эти события, но через интерфейс (после кода класса независимой формы Flexberry генерирует интерфейс для определения событий методов и атрибутов зависимой формы)

```csharp
/// <summary>
/// это определение интерфейса, от которого наследуется зависимая форма, но оно находится в файле независимой формы
/// </summary>
public interface DPDIПланетаE
{

    // *** Start programmer edit section *** (DPDIПланетаE Members)
    ///<summary>
    /// Перемотка вперёд
    ///</summary>
    event PrevNextEventArgsHandler NextEvent;
    /// <summary>
    /// Перемотка назад
    /// </summary>
    event PrevNextEventArgsHandler PrevEvent;

    // *** End programmer edit section *** (DPDIПланетаE Members)

}
```

* Независимая форма подписывается на эти дополнительные события зависимой формы в специальном методе `prv_AttachEventsFromDpdForm(object form)`

```csharp
protected virtual void prv_AttachEventsFromDpdForm(object form)
{
    // *** Start programmer edit section *** (prv_AttachEventsFromDpdForm( object form ))
    IIS.AlteredHierarchy.DPDIПланетаE form1 = ((IIS.AlteredHierarchy.DPDIПланетаE)(form));
    form1.NextEvent -= new PrevNextEventArgsHandler(form1_NextEvent);
    form1.NextEvent += new PrevNextEventArgsHandler(form1_NextEvent);

    form1.PrevEvent -= new PrevNextEventArgsHandler(form1_PrevEvent);
    form1.PrevEvent += new PrevNextEventArgsHandler(form1_PrevEvent);

    // *** End programmer edit section *** (prv_AttachEventsFromDpdForm( object form ))
    IIS.AlteredHierarchy.DPDIПланетаE typedForm = ((IIS.AlteredHierarchy.DPDIПланетаE)(form));
}

//...
// *** Start programmer edit section *** (ПланетаE CustomMembers)
void form1_PrevEvent(object sender, EventArgs e)
{
    GetNextOrPrevObj(false);
}

void form1_NextEvent(object sender, EventArgs e)
{
    GetNextOrPrevObj(true);
}

/// <summary>
/// Пример метода, который получает предыдущий или последующий объект и переключает объект, который мы редактируем
/// </summary>
/// <param name="next"></param>
private void GetNextOrPrevObj(bool next)
{
    KeyGuid pk = (KeyGuid)((BaseWinEdit)Editor).DataObject.__PrimaryKey;
    IDataService ds = DataServiceProvider.DataService;

    LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Планета), "ПланетаE");
    lcs.ColumnsSort = new[] { new ColumnsSortDef("primaryKey", SortOrder.Asc) };
    lcs.ReturnTop = 1;    
    Function f = next ? FunctionBuilder.BuildGreater(pk) : FunctionBuilder.BuildLess(pk);     
    lcs.LimitFunction = f;
    DataObject[] dataObjects = ds.LoadObjects(lcs);
    if (dataObjects.Length > 0)
    {
        DataObject dataObject = dataObjects[0];
        //dataObject.LockObject("AnyKey"); Раскомментировать, если просмотр нужен в режиме только для чтения

        SwitchEditingObject(dataObject);
    }
}
// *** End programmer edit section *** (ПланетаE CustomMembers)
```

При переключении объекта срабатывает логика, аналогичная логике закрытия формы, касающейся запроса о сохранении изменённого объекта, очистка блокировки. Дальше всё работает аналогично открытию: проверяется блокировка, устанавливается либо предлагается открыть в режиме только для чтения. Если пользователь откажется открыть заблокированный объект только на чтение, то форма попытается открыть предыдущий объект полностью выполнив логику по открытию (в методе `PrepareDataObjectForEdit(ICSSoft.STORMNET.DataObject dobject)` происходит зачитка объекта, если эта логика не переопределена).

## Обновление локальной копии объекта формы

При открытии формы на редактирование у нее создается локальная копия объекта. Вопрос о сохранении задается при различии этой локальной копии и редактируемого объекта данных.

Возникают ситуации, когда объект в базе изменился во время редактирования формы, т.е. локальная копия формы устарела. В такой ситуации необходимо обновить локальную копию формы с помощью вызова метода `ResetDataObjectCopy` у независимой формы. В качестве параметра метода передается объект, который теперь будет являться локальной копией формы.

## Создание формы редактирования объекта

Существует несколько способов "ручного" создания формы редактирования объекта с возможностью связать ее со свойствами существующего объекта данных. Они подробно описаны в статье [Менеджер редактирования](fw_editmanager.html).

## Работа на форме редактирования с полями нескольких объектов

Иногда встаёт задача организовать работу с полями объектов разных классов на одной форме редактирования. Например, необходима форма для быстрого ввода, где часть полей принадлежит одному классу, часть - другому и т.д. В таком случае необходимо отслеживать сохранение этого "сложного представления".

Одним из вариантов решения поставленной задачи может быть использование нескольких [EditManager](fw_editmanager.html).

Для этого нужно создать объекты и привязать к ним `EditManager`.

Логика сохранения объектов из "сложного представления" зависит от конкретной задачи (например, по каким полям определять, что такой же объект уже есть в базе, как интерпретировать изменение сохранённого объекта и прочее). При сохранении "сложного представления" необходимо обратить внимание на моменты, отмеченные в статьях:

* [Обработка множества объектов (в т.ч. и разнотипных)](fo_processing-multiple-objects.html)
* [Обновление связанных объектов](fo_update-related-objects.html)

## Межформенное взаимодействие при редактировании объектов

В основном, взаимодействия происходят вызовом формы редактирования с формы списка (например, при открытии объекта на редактирование) и, наоборот, с формы редактирования на форму списка (например, для выбора связанного объекта). Так или иначе, суть состоит в том, что с одной формы на другую передаётся некоторый объект данных с целью его редактирования целиком, либо его части. Соответственно, в конкретном (относительном) межформенном взаимодействии одна форма выступает в качестве __инициатора__ редактирования, а вторая, — в качестве __редактора__.

* Если объект открывается из списка на редактирование, форма списка — инициатор, а форма редактирования — редактор. В этом случае, очевидно — объект передаётся на редактирование в форму редактирования.
* Если из формы редактирования для выбора связанного объекта (мастерового) открывается форма списка, то форма редактирования — инициатор, а форма списка — редактор.

Разница между случаями 1 и 2 лишь в том, что в случае 2 объект редактируется не целиком, а только его часть.

## Полезные ссылки

* [Чтение принадлежащих различным классам объектов в одном представлении](fo_reading-several-types-objects.html)
* [Универсальная форма редактирования](fw_editform.html)
