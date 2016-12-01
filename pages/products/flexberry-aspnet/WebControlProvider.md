---
title: WebControlProvider
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/web-control-provider.html
folder: product--folder
lang: ru
---
`WebControlProvider` - это провайдер, который позволяет настраивать контролы для отображения свойств объекта на списковых контролах (например,
[WebObjectListView](web-object-list-view.html), [AjaxGroupEdit](ajax-group-edit.html)). Все его настройки хранятся в файле `/xml/WebControlProvider.xml`

## Настройки

Есть 2 способа настройки:

1. Указание типа контрола для любого типа (XML-элемент `propertytype`), например

    ```xml
    <propertytype name="Boolean">
        <control typename="System.Web.UI.WebControls.CheckBox, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Checked" codefile="" />
        <editcontrol ... />
        <filtercontrol ... />
    </propertytype>
    ```

2. Указание типа контрола для свойства конкретного типа (XML-элемент `customproperty`), например

    ```xml
    <customproperty class="Адрес" property="ПервичныйКлюч">
        <control typename="IIS.ISOGD.Controls.Partials.ArcMapViewControl" property="PrimaryKey" codefile="~/Controls/Partials/ArcMapViewControl.ascx" />
        <editcontrol ... />
        <filtercontrol .. />
    </customproperty>
    ```

* control - контрол, который будет использоваться для отображения (например, в [WebObjectListView](web-object-list-view.html)) или для мастеровых свойств в
  [AjaxGroupEdit](ajax-group-edit.html);
* editcontrol - контрол, который будет использоваться для редактирования (например, в [AjaxGroupEdit](ajax-group-edit.html));
* filtercontrol - контрол, который будет использоваться для фильтрации в [WebObjectListView](web-object-list-view.html);

В тэге &lt;control /&gt; (&lt;editcontrol /&gt;,&lt;filtercontrol /&gt;) указываются:

1. `typename` - тип контрола;
2. `property` - свойство контрола, которое биндится со значением в списке (в конкретной ячейке, где должен располагаться контрол);
3. `codefile` - путь к ASCX-файлу контрола (только для ASCX контролов);

{% capture warning %}
Важно помнить, что `customproperty` имеет больший приоритет, чем `propertytype`.

1. Сначала ищется контрол для конкретного свойства.
2. Если настройка не найдена, то ищется контрол для типа этого свойства.
{% endcapture %}
{% include warning.html content=warning %}

{% capture note %}Мастеровые свойства в [AjaxGroupEdit](ajax-group-edit.html) будут всегда браться только из `control`, никак не из `editcontrol`.{% endcapture %}
{% include note.html content=note %}

## Встраивание контролов

Имеется возможность указать контрол для просмотра и для редактирования (например, в [WebGroupEdit]) [WebGroupEdit.ashx#%D0%9A%D0%BE%D0%BD%D1%82%D1%80%D0%BE%D0%BB%D1%8B_%D0%B4%D0%BB%D1%8F_%D1%80%D0%B5%D0%B4%D0%B0%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F_3|Контрол для редактирования в WebGroupEdit].

Если вы разработали свой контрол, который используется на формах редактирования и хотите встроить его в [WebObjectListView](web-object-list-view.html).
У вас возникает проблема с тем, что в [WebObjectListView](web-object-list-view.html) он выглядит как контрол для ввода данных, а вы хотели бы использовать
его только для отображения. Вам нужно реализовать свойство `Enabled` у контрола, и когда контрол будет встраивается в `WOLV`, то ему автоматически проставится
`Enabled = false`.

## Пример

```cs
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


