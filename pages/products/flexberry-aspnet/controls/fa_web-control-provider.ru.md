---
title: WebControlProvider
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-control-provider.html
lang: ru
---

`WebControlProvider` - это провайдер, который позволяет настраивать контролы для отображения свойств объекта на списковых контролах (например, [WebObjectListView](fa_web-object-list-view.html), [AjaxGroupEdit](fa_ajax-group-edit.html)). Все его настройки хранятся в файле /xml/WebControlProvider.xml

## Настройки

Есть 2 способа настройки:

1.Указание типа контрола для любого типа (XML-элемент `propertytype`), например

```xml
  <propertytype name="Boolean">
    <control typename="System.Web.UI.WebControls.CheckBox, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Checked" codefile="" />
    <editcontrol ... />
    <filtercontrol ... />
  </propertytype>
```

2.Указание типа контрола для свойства конкретного типа (XML-элемент `customproperty`), например

```xml
  <customproperty class="Адрес" property="ПервичныйКлюч">
    <control typename="IIS.ISOGD.Controls.Partials.ArcMapViewControl" property="PrimaryKey" codefile="~/Controls/Partials/ArcMapViewControl.ascx" /> 
    <editcontrol ... />
    <filtercontrol .. />
  </customproperty>
```

* control - контрол, который будет использоваться для отображения (например, в [WebObjectListView](fa_web-object-list-view.html)) или для мастеровых свойств в [AjaxGroupEdit](fa_ajax-group-edit.html);
* editcontrol - контрол, который будет использоваться для редактирования (например, в [AjaxGroupEdit](fa_ajax-group-edit.html));
* filtercontrol - контрол, который будет использоваться для фильтрации в [WebObjectListView](fa_web-object-list-view.html);

В тэге `<control /> (<editcontrol />,<filtercontrol />)` указываются:

* `typename` - тип контрола;
* `property` - свойство контрола, которое биндится со значением в списке (в конкретной ячейке, где должен располагаться контрол);
* `codefile` - путь к ASCX-файлу контрола (только для ASCX контролов);

{% include important.html content="Важно помнить, что `customproperty` имеет б*о*льший приоритет, чем `propertytype`.

1. Сначала ищется контрол для конкретного свойства.
2. Если настройка не найдена, то ищется контрол для типа этого свойства.
" %}

{% include note.html content="Мастеровые свойства в [AjaxGroupEdit](fa_ajax-group-edit.html) будут всегда браться только из control, никак не из editcontrol." %}

## Встраивание контролов

Имеется возможность указать контрол для просмотра и для редактирования (например, в [AjaxGroupEdit](fa_ajax-group-edit.html)).

Если разработан пользовательский контрол, который используется на формах редактирования и необходимо встроить его в [WebObjectListView](fa_web-object-list-view.html). Может возникнуть проблема с тем, что в [WebObjectListView](fa_web-object-list-view.html) он выглядит как контрол для ввода данных, а использовать его планируется только для отображения.В таких случаях можно реализовать свойство `Enabled` у контрола, и когда контрол будет встраивается в WOLV, то ему автоматически проставится `Enabled = false`.

## Пример

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <propertytype name="Boolean">
    <control typename="System.Web.UI.WebControls.CheckBox, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Checked" codefile=""/>
  </propertytype>
  <propertytype name="NullableDateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="DatePicker.ascx"/>
  </propertytype>
  <propertytype name="DateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="DatePicker.ascx"/>
  </propertytype>
  <customproperty class="Адрес" property="ПервичныйКлюч">
    <control typename="IIS.ISOGD.Controls.Partials.ArcMapViewControl" property="PrimaryKey" codefile="~/Controls/Partials/ArcMapViewControl.ascx" /> 
  </customproperty>
</root>
```
