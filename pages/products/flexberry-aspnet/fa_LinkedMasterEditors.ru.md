---
title: Связывание контролов редактирования мастеров
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_linked-master-editors.html
folder: products/flexberry-aspnet/
lang: ru
---

# Связывание web-контролов редактирования мастеров

Данная статья рассказывает, каким образом можно связывать такие web-контролы редактирования мастеров как [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html) и [MasterEditorAjaxDropDown](master-editor-ajax-drop-down.html).

## Методы для [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html)

### Создание зависимых лукапов внутри AGE

Если лукапы находятся внутри [AGE](fa_ajax-group-edit.html), то для этого нужно воспользоваться [AjaxGroupEdit.ashx| следующим способом].

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
Для конкретного контрола редактирования мастера ([MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html) или [MasterEditorAjaxDropDown](master-editor-ajax-drop-down.html)) имеется возможность указать его мастеровой контрол ([MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html) или [MasterEditorAjaxDropDown](master-editor-ajax-drop-down.html)), т.е. тот, от которого он зависит. При смене мастерового контрола вызовется серверный обработчик для изменения свойств зависимого контрола.

```

// Это нужно писать только тогда, когда все свойства у контролов проставлены WebBinder (тип и пр.)
ctrlSlave.AddMasterLookup(ctrlMaster, MasterOfSlaveChanged);
```

`MasterOfSlaveChanged` - это делегат вида:
```

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
```

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

(((
<msg type=note>Один контрол редактирования мастера может иметь несколько мастеровых контролов. При связывании каждый раз задается новый обработчик.</msg>
)))

(((
<msg type=note>В динамике нельзя менять мастеровой контрол.</msg>
)))

(((
<msg type=note>Есть возможность узнавать в обработчике, какой контрол редактирования мастера его вызвал - `bool Triggered`.</msg>
)))

(((
<msg type=important>Мастеровой [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html), может быть зависимым контролом своего зависимого контрола. Например:
```

ctrl1.AddMasterLookup(ctrl2, ctrl1Ofctrl2Changed);
ctrl2.AddMasterLookup(ctrl1, ctrl2Ofctrl1Changed);
```.
НО: Мастеровой [MasterEditorAjaxDropDown](master-editor-ajax-drop-down.html) не может быть зависимым контролом своего зависимого контрола типа [MasterEditorAjaxDropDown](master-editor-ajax-drop-down.html).
</msg>
)))
