---
title: MasterEditorLinkedAjaxLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_master-editor-linked-ajax-lookup.html
lang: ru
---

{% include warning.html content="Данная статья описывает создание `MasterEditorLinkedLinkedLookup`, расположенных в AGE, а не непосредственно на форме редактирования!  
На формах редактирования необходимо использовать логику [Связывание AjaxAutocomplete и AjaxLookup](fa_link-ajax-autocomplete-ajax-lookup.html)." %}

## Постановка задачи

Есть диаграмма классов:

![Пример](/images/pages/products/flexberry-aspnet/controls/lookup/linked-lookup-diagram.png)

Необходимо при добавлении `СтрокиПланаПогашения` дать возможность

* либо выбрать `БанковскуюКарту` из списка,
* либо вбить `НомерКарты` вручную, и
  * при совпадении с существующим номером проставить ссылку на `БанковскуюКарту` автоматически,
  * а при несовпадении просто сохранить введенную пользователем информацию в поле `НомерКарты`.

## Подключение и настройка

Алгоритм подключения и настройки `LinkedLookup`:

* Настроить представление детейла.
* Добавить настройку в [WebControlProvider](fa_web-control-provider.html).
* Добавить настройку в [WebBinder](fa_web-binder.html).
* Настроить LookUp при помощи `LookUpSettings`

### Настройка представления детейла

В [E-представлении](fd_e-view.html) детейла необходимо добавить все необходимые поля (обязательно добавить `БанковскуюКарту`, `БанковскаяКарта.Номер` и `НомерКарты`), но при этом снять видимость с собственного поля, в котором будет храниться информация (в нашем случае это `НомерКарты`). Это делается в [Flexberry Desinger](fd_flexberry-designer.html) при помощи [формы редактирования представлений](fd_view-edit-form.html).

### Настройка WebControlProvider

В WebControlProvider необходимо добавить информацию о том, что для редактирования ссылки `СтрокаПланаПогашения` - `БанковскаяКарта` должен использоваться контрол  `MasterEditorLinkedAjaxLookUp`. Делается это в файле `~xml/WebControlProvider.xml` следующим образом:

![Пример](/images/pages/products/flexberry-aspnet/controls/lookup/web-control-provider.jpg)

```xml
<customproperty class="СтрокаПланаПогашения" property="Карта">
  <control typename="System.Web.UI.WebControls.Label, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Text" codefile="" />
  <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorLinkedAjaxLookUp" codefile="" />
</customproperty>
<customproperty class="СтрокаПланаПогашения" property="НомерКарты">
  <control typename="System.Web.UI.WebControls.Label, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Text" codefile="" />
  <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorLinkedAjaxLookUp" codefile="" />
</customproperty>
```

### Настройка биндинга

Для того, чтобы контрол корректно работал для него нужно настроить нестандартный биндинг (при помощи [WebBinder](fa_web-binder.html)).

Для этого необходимо:

* в папке `~/XML/Bindings/` создать XML-файл с названием, совпадающим с названием формы редактирования, на которой будет располагаться детейл (то есть на форме редактирования агрегатора). В примере называться `KreditE.xml`.
* В созданный xml-файл необходимо добавить нестандартный биндинг, который будет сообщать системе, что один web-контрол должен редактировать сразу два свойства. В примере это будет выглядеть следующим образом:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root partial="true">
  <detail name="СтрокаПланаПогашения">
    <property name="Карта">
      <control id="ctrlКарта" prop="SelectedMasterPK">
      </control>
    </property>
    <property name="НомерКарты">
      <control id="ctrlКарта" prop="Text">
      </control>
    </property>
  </detail>
</root>
```

Очень важно следить за правильностью составления этого биндинга:

* Указать, что биндинг составной: добавить блок `partial="true"` в тэг `root`.
* Указать имя детейла, для которого составляется этот биндинг (в примере `СтрокаПланаПогашения`).
* Указать наименования двух свойств, которые должны редактироваться (в примере `Карта` и `НомерКарты`).
* Указать ID контрола. Так как контрол находится в [AjaxGroupEdit'e](fa_ajax-group-edit.html), то в явном виде его наименования на странице найти невозможно. Имя формируется как `ctrl` + имя ссылки на мастера (в нашем случае `ctrl` + `Карта`), __обязательно следить за регистром, `ctrlКарта` это не то же самое, что `ctrlкарта`__!
* Наименование редактируемых свойств (`SelectedMasterPK` и `Text` всегда остаются неизменными, но важно понимать, что `Text` относится к свойству детейла, а `SelectedMasterPK` к мастеру детейла.

### Настройка лукапа

Чтобы при вводе текста система подсказывала пользователю существующие варианты, необходимо настроить лукап, указав ему `LookUpSetting`:

```csharp
protected override void Preload()
{
    ctrlСтрокаПланаПогашения.AddLookUpSettings(
        Information.ExtractPropertyPath<СтрокаПланаПогашения>(x => x.Карта), new LookUpSetting
        {
            LookUpBtnVisible = true,
            LookUpClearBtnVisible = true,
            Autocomplete = true,
            IsSubstring = true
        });
}
```

{% include note.html content="Хочется еще раз напомнить, что все настройки осуществляются на форме редактирования класса-агрегатора, то есть на форме `KreditE`." %}

## Возможные проблемы

Если в базе уже есть данные, то при подключении `MasterEditorLinkedAjaxLookUp` могут возникать проблемы при отображении "старых" данных.
