---
title: MasterEditorLinkedAjaxLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_master-editor-linked-ajax-lookup.html
lang: en
autotranslated: true
hash: f814c5a45ac2f2eff8796ffc33c4ad6878faacc76a5cf5939691d16567e072ba
---

{% include warning.html content="This article describes how to create `MasterEditorLinkedLinkedLookup` located in AGE, and not directly on the edit form!
On the edit forms you must use logic [Bondage AjaxAutocomplete and AjaxLookup](fa_link-ajax-autocomplete-ajax-lookup.html)." %}

## Statement of the problem

There's the class diagram:

![Example](/images/pages/products/flexberry-aspnet/controls/lookup/linked-lookup-diagram.png)

Necessary when adding `СтрокиПланаПогашения` to give the opportunity

* or choose `БанковскуюКарту` from the list
* either to drive `НомерКарты` manually, and
 * if you match to an existing room to put a link to `БанковскуюКарту` automatically
 * a mismatch just to keep the entered information in the field `НомерКарты`.

## Connection and setup

Algorithm setup and tuning `LinkedLookup`:

* Customize presentation of detail.
* Add setting to [WebControlProvider](fa_web-control-provider.html).
* Add setting to [WebBinder](fa_web-binder.html).
* Configure LookUp with `LookUpSettings`

### Customize the appearance of detail

In [E-view](fd_e-view.html) of detail you must add all required fields (must add `БанковскуюКарту`, `БанковскаяКарта.Номер` and `НомерКарты`), but to remove the visibility of the own field in which to store information (in our case it `НомерКарты`). This is done in [Flexberry Desinger](fd_flexberry-designer.html) using [date view](fd_view-edit-form.html).

### Setting WebControlProvider

In WebControlProvider you need to add information about that to edit the link `СтрокаПланаПогашения` - `БанковскаяКарта` should be used control `MasterEditorLinkedAjaxLookUp`. This is done in the file `~xml/WebControlProvider.xml` as follows:

![Example](/images/pages/products/flexberry-aspnet/controls/lookup/web-control-provider.jpg)

```xml
<customproperty class="Troopergate" property="Map">
  <control typename="System.Web.UI.WebControls.Label, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Text" codefile="" />
  <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorLinkedAjaxLookUp" codefile="" />
</customproperty>
<customproperty class="Troopergate" property="Nomercury">
  <control typename="System.Web.UI.WebControls.Label, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Text" codefile="" />
  <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorLinkedAjaxLookUp" codefile="" />
</customproperty>
```

### Configuration binding

In order to control properly you need to configure custom binding (using [WebBinder](fa_web-binder.html)).

To do this:

* ` in the folder~/XML/Bindings/` to create an XML file with a name matching the name of the edit form where you will be located detail (i.e. the edit form of the aggregator). In the example to be called `KreditE.xml`.
* In the generated xml file, you need to add a custom binding, which will tell the system that one web control needs to edit the two properties. In the example it will look like the following:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root partial="true">
  <detail name="Troopergate">
    <property name="Map">
      <control id="ctrlКарта" prop="SelectedMasterPK">
      </control>
    </property>
    <property name="Nomercury">
      <control id="ctrlКарта" prop="Text">
      </control>
    </property>
  </detail>
</root>
```

It is very important to monitor the accuracy of this binding:

* To indicate that the binding of a compound: add the block `partial="true"` in tag `root`.
* Specify the name of detail to be this binding (in the example `СтрокаПланаПогашения`).
* Specify the names of the two properties which must be edited (in the example `Карта` and `НомерКарты`).
* Specify the ID of the control. Since the control is in [AjaxGroupEdit'e](fa_ajax-group-edit.html), the explicit form of its name on the page can not be found. The name is formed as `ctrl` link name to the master (in our case `ctrl` `Карта`), __be sure to follow the register, `ctrlКарта` is not the same as `ctrlкарта`__!
* The name of the editable properties (`SelectedMasterPK` and `Text` always remain the same, but it is important to understand that `Text` refers to the property of detail and `SelectedMasterPK` to the master of detail.

### Setting lucapa

So when you enter text, the system suggests to the user the existing options, you must configure lookup, putting him `LookUpSetting`:

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

{% include note.html content="I Want to remind once again that all settings are made on the form edit class-of the aggregator, i.e. on the form `KreditE`." %}

## Possible problems

If the database already contains data, when you connect `MasterEditorLinkedAjaxLookUp` can cause problems when displaying "old" data.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}