---
title: the Limit for LookUp in AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_limited-lookup-age.html
lang: en
autotranslated: true
hash: 0b7cb9e93642a2414017518b0f32522ff0703a3986eb4b989e99b31dd5d16e3d
---

There are times when in the [LookUp](fa_master-editor-ajax-lookup.html) for strings [detail](fo_detail-associations-properties.html) you want to insert only certain values. For this [list](fa_web-object-list-view.html) opened on the LookUp form you want to restrict. However, [AjaxGroupEdit](fa_ajax-group-edit.html) has its own characteristics that must be considered. Resulting in [the code looks a little more complicated](fa_settings-lookup-age.html) than at the [restriction to "normal" LookUp](fa_lookup-limit-web.html).

Suppose we have the following [model data](fd_design.html):

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-diagram-age.png)

When you add a row of detail the "Mastergrader" for property "Svistoplyaska" should be available only property "СвойствоЛукапМастера2 from Loopmaster".

In application code this will look like the following:

```csharp
/// <summary> 
/// It is best to write the business logic, operating only with the data object. 
/// </summary> 
protected override void PreApplyToControls()
{
	ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<МастерАгрегатор>(r => r.ЛукапМастер), new LookUpSetting
	{
		LimitFunction = FunctionBuilder.BuildEquals<ЛукапМастер>(x => x.[СвойствоЛукапМастера2], [ЗначениеСвойстваЛукапМастер])
	});
}      
```

## Example

On a real example as follows:

### Chart

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age-example.png)

### Code

```csharp
ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<Комната>(r => r.НазначениеКомнаты), new LookUpSetting
	{
		LimitFunction = FunctionBuilder.BuildEquals<НазначениеКомнаты>(x => x.Актуально, true)
	});
```

### View in the app

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age application.png)

**Note:** If a limit on the LookUp form is not applied, you must check whether the specified name artisan classes, performances and properties.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}