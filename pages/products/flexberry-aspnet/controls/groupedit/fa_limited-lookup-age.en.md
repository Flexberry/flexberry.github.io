--- 
title: the Limit for LookUp in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_limited-lookup-age.html 
lang: en 
autotranslated: true 
hash: 4693b433ac29d7f459a4b5a71619d15a594531445be7323f56d17bf446925a59 
--- 

There are situations where [LookUp](fa_master-editor-ajax-lookup.html) for strings [detail](fo_detail-associations-properties.html) you want to insert only certain values. For this [list](fa_web-object-list-view.html) opened on the LookUp form you want to restrict. However, [AjaxGroupEdit](fa_ajax-group-edit.html) has its own characteristics that must be considered. Resulting in [the code looks a little more complicated](fa_settings-lookup-age.html) than at the [restriction to "normal" LookUp](fa_lookup-limit-web.html). 

Suppose we have the following [model data](fd_design.html): 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-diagram-age.png) 

When you add a row of detail "Mastergrader" properties "Svistoplyaska" should be available only property "СвойствоЛукапМастера2" "Loopmaster". 

In application code this will look like the following: 

```csharp
/// <summary> 
/// It is best to write the business logic, operating only with the data object. 
/// </summary> 
protected override void PreApplyToControls()
{
	ExternalLangDef langdef = ExternalLangDef.LanguageDef;
	
	ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<МастерАгрегатор>(r => r.ЛукапМастер), new LookUpSetting
	{
		LimitFunction = langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.[ТипСвойстваЛукапМастер], "СвойствоЛукапМастера2"),     [ЗначениеСвойстваЛукапМастер])
	});
}      
``` 

## Example 

On a real example as follows: 

### Chart 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age-example.png) 

### Code 

```csharp
ExternalLangDef langdef = ExternalLangDef.LanguageDef;

ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<Комната>(r => r.НазначениеКомнаты), new LookUpSetting
	{
		LimitFunction = langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.BoolType, "True"), true)
	});
``` 

### view in the app 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age application.png) 

**Note:** If a limit on the LookUp form is not applied, you must check whether the specified name artisan classes, performances and properties. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}