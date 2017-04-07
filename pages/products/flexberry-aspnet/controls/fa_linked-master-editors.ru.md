---
title: Связывание контролов редактирования мастеров
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_linked-master-editors.html
lang: ru
---

Данная статья рассказывает, каким образом можно связывать такие web-контролы редактирования мастеров как [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) и [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html).

## Методы для [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html)

### Создание зависимых лукапов внутри AGE

Если лукапы находятся внутри [AGE](fa_ajax-group-edit.html), то для этого нужно воспользоваться [следующим способом](fa_controls-age.html).

### Работа с LookUp'ом из javascript

Подписка на изменение значения в LookUp'e

```javascript
$('#<%=ctrlМойЛукап.ClientID%>').on('change', function () {
  // alert('Значение изменилось');
});
```

Либо можно задать клиентский обработчик изменения в серверном коде:

```csharp
    lookup.ChangeClientHandler = "alert('Значение изменилось в лукапе с идентификатором {0}');";
```

## Связывание при помощи серверных методов, которые предусмотрены технологией

Для конкретного контрола редактирования мастера ([MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) или [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) имеется возможность указать его мастеровой контрол ([MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) или [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)), т.е. тот, от которого он зависит. При смене мастерового контрола вызовется серверный обработчик для изменения свойств зависимого контрола.

```csharp
// Это нужно писать только тогда, когда все свойства у контролов проставлены WebBinder (тип и пр.)
ctrlSlave.AddMasterLookup(ctrlMaster, MasterOfSlaveChanged);
```

`MasterOfSlaveChanged` - это делегат вида:

```csharp
/// <summary>
/// Делегат обработки смены значения в AJAX-лукапе
/// </summary>
/// <param name="masterSelectedPk">Текущее значение мастерового лукапа</param>
/// <param name="selectedPk">Текущее значение зависимого лукапа</param>
/// <param name="masterLookups">Список остальных мастеровых лукапов</param>
public delegate void AjaxLookUpChangeHandler(
    string masterSelectedPk,
    ref string selectedPk,
    List<MasterLookup> masterLookups);
```

Например, если нужно очистить зависимый контрол, если очистился мастеровой:

```csharp
private void MasterOfSlaveChanged(string masterselectedpk, ref string selectedpk, List<MasterLookup> masterLookups)
{
    if (string.IsNullOrEmpty(masterselectedpk))
    {
        selectedpk = string.Empty;
    }
}
```

Также, можно изменять LimitFunction и пр.

В такой обработчик приходит значение мастерового контрола - `masterselectedpk` и значение зависимого контрола - `selectedPk` (передается по ссылке). (Т.е. если будет изменён `selectedpk`, то в javascript методе проставится значение и пойдет еще один AJAX-запрос на зачитку объекта по `selectedpk`).

{% include note.html content="Один контрол редактирования мастера может иметь несколько мастеровых контролов. При связывании каждый раз задается новый обработчик." %}

{% include note.html content="В динамике нельзя менять мастеровой контрол." %}

{% include note.html content="Есть возможность узнавать в обработчике, какой контрол редактирования мастера его вызвал - `bool Triggered`." %}

{% include important.html content="Мастеровой [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html), может быть зависимым контролом своего зависимого контрола." %} 

Например:

```csharp
ctrl1.AddMasterLookup(ctrl2, ctrl1Ofctrl2Changed);
ctrl2.AddMasterLookup(ctrl1, ctrl2Ofctrl1Changed);
```

НО: Мастеровой [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html) не может быть зависимым контролом своего зависимого контрола типа [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html).
