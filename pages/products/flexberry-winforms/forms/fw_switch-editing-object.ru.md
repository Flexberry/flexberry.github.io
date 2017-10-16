---
title: Переключение редактируемого объекта без закрытия формы редактирования
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_switch-editing-object.html
folder: products/flexberry-winforms/
lang: ru
---

Иногда сценарий работы системы подразумевает последовательное переключение просматриваемых или редактируемых объектов на форме редактирования без закрытия самой этой формы. Аналогом такой функциональности является просмотр событий в системном логе Windows (EventLog). Flexberry Platform для организации данного сценария поддерживает специальную функцию, доступную из независимых форм редактирования: `SwitchEditingObject(newObject)`.
Типичная и рекомендуемая реализация со стороны прикладного проекта может быть выполнена следующим образом:
1. Зависимая форма включает интерфейсную часть по переключению объектов (имеется в виду пользовательский интерфейс: кнопки и прочие контролы).
2. Зависимая форма содержит события, которые генерирует интерфейсная часть (переход вперёд, назад, на указанный объект и пр.). Срабатывание этих событий привязано к соответствующим контролам. Пример:
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
3. Независимая форма видит этисобытия, но через интерфейс (после кода класса независимой формы Flexberry генерирует интерфейс для определения событий методов и атрибутов зависимой формы)
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
4. Независимая форма подписывается на эти дополнительные события зависимой формы в специальном методе `prv_AttachEventsFromDpdForm(object form)`
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
    SQLWhereLanguageDef lDef = SQLWhereLanguageDef.LanguageDef;
    Function f = lDef.GetFunction(next ? lDef.funcG : lDef.funcL, new VariableDef(lDef.GuidType, "StormMainObjectKey"), pk);
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



