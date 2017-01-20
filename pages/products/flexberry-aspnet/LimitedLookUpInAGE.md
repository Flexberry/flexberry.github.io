---
title: Ограничение для LookUp в AGE
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/limited-look-up-in-a-g-e.html
folder: product--folder
lang: ru
---

# Общая информация
Бывают ситуации, когда в [LookUp](master-editor-ajax-look-up.html) для строк [детейла](detail-associations-and-their-properties.html) нужно вставлять только определенные значения. Для этого [список](web-object-list-view.html), открываемый на LookUp-форме необходимо ограничить. Однако для [AjaxGroupEdit](ajax-group-edit.html) имеются свои особенности, которые необходимо учитывать. В результате чего [код выглядит чуть сложнее](settings-for-look-up-in-a-g-e.html), чем при [ограничении на "обычном" LookUp](look-up-limit-web.html).

Предположим, есть следующая [модель данных](design.html):

![](/images/pages/ABratchikova/Диаграмма для LookUp в  AGE.png)

При добавлении строки-детейла у "МастерАгрегатор" для свойства "СвойствоДетейлаДляЛукапа" должно быть доступно только свойство "СвойствоЛукапМастера2" у "ЛукапМастер".

В коде приложения это будет выглядеть следующим образом:

```cs
/// <summary>
/// Здесь лучше всего писать бизнес-логику, оперируя только объектом данных.
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
# Пример
На реальном примере выглядит следующим образом:

## Диаграмма
![](/images/pages/ABratchikova/LookUp AGE пример.png)

## Код
```cs
ExternalLangDef langdef = ExternalLangDef.LanguageDef;

ctrlКомната.AddLookUpSettings(Information.ExtractPropertyPath<Комната>(r => r.НазначениеКомнаты), new LookUpSetting
	{
		LimitFunction = langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.BoolType, "Актуально"), true)
	});
```

## Вид в приложении
![](/images/pages/ABratchikova/LookUp в AGE вид в приложении.png)

# Примечания
Если ограничение на LookUp-форме не применено, следует проверить, правильно ли указаны названия мастеровых классов, представлений и свойств.
