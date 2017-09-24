---
title: Принудительный вызов формы редактирования
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_force-call-editing-form.html
lang: ru
---

Вызов формы редактирования

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);      
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.Edit(oОбъект, "", "");
```

Если требуется функциональность по сохранению объекта при нажатию на кнопку "Сохранить" или "Сохранить и закрыть" на зависимой форме, то код будет выглядеть таким образом:

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);      
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.SaveEvent += (sr, ea) => { new BusinessService().UpdateObject(ea.dataobject); };
cont.Edit(oОбъект, "", "");
```
