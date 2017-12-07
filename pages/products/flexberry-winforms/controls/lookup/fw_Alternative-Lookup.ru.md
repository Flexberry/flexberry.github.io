---
title: Альтернативный лукап
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Рассматривается пример смены обработчика look_LookUpEvent
toc: true
permalink: en/fw_alternative-lookup.html
folder: products/flexberry-winforms/
lang: en
---

## Пример

На конкретном лукапе меняем обработчик look_LookUpEvent на свой

```csharp
private void look_LookUpEvent1(object sender, System.EventArgs e)
{
    Посетитель oПосетитель = (Посетитель)this.EditManager.DataObject; 
    
    
    SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
    Function lf = langdef.GetFunction(langdef.funcSQL,"1=1"); 
    
    //создадим контейнер-раннер
    ICSSoft.АдресноеБюро.ЛичностьL FormЛичностьL = (ICSSoft.АдресноеБюро.ЛичностьL)      ICSSoft.STORMNET.UI.ContRunner.RunForm(typeof(ICSSoft.АдресноеБюро.ЛичностьL));
    FormЛичностьL.Edit(oПосетитель, "Личность", "Личность",lf);

    //подпишемся на события
    FormЛичностьL.SaveEvent+=new ICSSoft.STORMNET.UI.SaveEventArgsHandler(FormЛичностьL_SaveEvent);FormЛичностьL.CancelEvent+=new ICSSoft.STORMNET.UI.CancelEventArgsHandler(FormЛичностьL_CancelEvent);
}

private void FormЛичностьL_SaveEvent(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)
{
    //наши действия
}

private void FormЛичностьL_CancelEvent(object sender, ICSSoft.STORMNET.UI.CancelEventArgs e)
{
    //наши действия
}
```