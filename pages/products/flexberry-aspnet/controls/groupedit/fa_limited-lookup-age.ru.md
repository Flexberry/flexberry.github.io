---
title: Ограничение для LookUp в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_limited-lookup-age.html
lang: ru
---

Бывают ситуации, когда в [LookUp](fa_master-editor-ajax-lookup.html) для строк [детейла](fo_detail-associations-properties.html) нужно вставлять только определенные значения. Для этого [список](fa_web-object-list-view.html), открываемый на LookUp-форме необходимо ограничить. Однако для [AjaxGroupEdit](fa_ajax-group-edit.html) имеются свои особенности, которые необходимо учитывать. В результате чего [код выглядит чуть сложнее](fa_settings-lookup-age.html), чем при [ограничении на "обычном" LookUp](fa_lookup-limit-web.html).

Предположим, есть следующая [модель данных](fd_design.html):

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-diagram-age.png)

При добавлении строки-детейла у "МастерАгрегатор" для свойства "СвойствоДетейлаДляЛукапа" должно быть доступно только свойство "СвойствоЛукапМастера2" у "ЛукапМастер".

В коде приложения это будет выглядеть следующим образом:

```csharp
/// <summary>
/// Здесь лучше всего писать бизнес-логику, оперируя только объектом данных.
/// </summary>
protected override void PreApplyToControls()
{
	ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<МастерАгрегатор>(r => r.ЛукапМастер), new LookUpSetting
	{
		LimitFunction = FunctionBuilder.BuildEquals<ЛукапМастер>(x => x.[СвойствоЛукапМастера2], [ЗначениеСвойстваЛукапМастер])
	});
}      
```

## Пример

На реальном примере выглядит следующим образом:

### Диаграмма

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age-example.png)

### Код

```csharp
ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<Комната>(r => r.НазначениеКомнаты), new LookUpSetting
	{
		LimitFunction = FunctionBuilder.BuildEquals<НазначениеКомнаты>(x => x.Актуально, true)
	});
```

### Вид в приложении

![](/images/pages/products/flexberry-aspnet/controls/groupedit/lookup-age-application.png)

**Примечание:** Если ограничение на LookUp-форме не применено, следует проверить, правильно ли указаны названия мастеровых классов, представлений и свойств.
