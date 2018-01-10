---
title: BaseEditForm
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_base-edit-form.html
lang: ru
---

От **BaseEditForm** наследуются все [web-формы редактирования](fa_editform.html). Данная форма не является технологической, ее можно изменять в зависимости от требований проекта.
Она была создана для переиспользования, структурирования кода и реализации единообразной логики форм редактирования.

## Свойства

|Свойство|Описание|
|---|---|
| DataObject | Редактируемый объект данных.|
| DataServiceType| Тип сервиса данных.|
| IsObjectCreated| Является ли объект новым.  Возвращает true в случае, когда объект создан нажатием кнопки "Добавить" или "Создать на основе".  В ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.Preload() всегда возвращает false! | 
| IsObjectPrototyped| Является ли объект [прототипом](http://wiki.ics.perm.ru/DataObjectPrototype.ashx).  Возвращает true в случае, когда объект создан нажатием кнопки "Создать на основе".  В ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.Preload() всегда возвращает false!|
| LockKey| Ключ для [блокировки](fw_readonly-win.html) объекта.  По умолчанию блокировка происходит по имени текущего авторизованного пользователя.|
| LockObject | Нужно ли блокировать объект данных.  По умолчанию блокировка включена при указанном первичном ключе ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.PK, при выключенном режиме "только чтение" ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.ReadOnly и только для авторизованных пользователей.  Требование авторизованности пользователей связано с тем, что ключ блокировки ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.LockKey по умолчанию формируется на основе имени текущего авторизованного пользователя.|
| OpenInModalWindow | Открыта ли форма во всплывающем окне.  По умолчанию определяется на основе параметра запроса ICSSoft.STORMNET.Web.Tools.WebParamController.OpenedInNewWindowParamName, добавляемого в ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView при формировании адресов страниц редактирования (ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView.GetRealEditPage(System.String)) и создания нового объекта данных (ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView.GetRealAddPage()).  Проверяется только наличие параметра. | 
| PK| [Первичный ключ](fo_primary-keys-objects.html) редактируемого объекта.|  
| PluginInitSettings | Настройки инициализации плагина формы редактирования.|
| ReadOnly | Флаг открытия формы только для чтения.|
| ReturnUrl | Адрес возврата.  Адрес всегда проверяется на "локальность" (соответствие текущему хосту) для предотвращения Open Redirection Attack.  Если адрес возврата отсутствует или не соответствует хосту, то возвращается адрес до корня сервера.|
| ShowCancelButton | Определяет, нужно ли показывать кнопку "Закрыть". В базовой реализации возвращает true, если ReturnUrl не пустой или форма открыта во всплывающем окне.|
| ShowSaveAndCloseButton | Определяет, нужно ли показывать кнопку "Сохранить и закрыть". В базовой реализации возвращает true, если форма не ReadOnly и ReturnUrl не пустой или форма открыта во всплывающем окне.|
| ShowSaveButton | Определяет, нужно ли показывать кнопку "Сохранить". В базовой реализации возвращает !ReadOnly.|
| View | Представление для загрузки редактируемых данных.|

## Примеры

* DataObject

```csharp
<br>var cat = new Дом();
<br>var foots = new DetailArrayOfКвартира(cat)
<br>{
<br>new Квартира { Номер = 10, КоличествоПроживающих = 3, КоличествоКомнат = 2 },
<br>new Квартира { Номер = 23, КоличествоПроживающих = 5, КоличествоКомнат = 4 },
<br>};
<br>cat.Квартира = foots;
<br>DataObject = cat;
```

* DataServiceType

```csharp
var dataServiceTypeName = dataService.GetType().Name;
<br>this.ctrlТипСервиса.Text = this.ctrlТипСервиса.Text + dataServiceTypeName;
```

* IsObjectCreated 

```csharp
protected void Button1_OnClick(object sender, EventArgs e)
<br>{
<br>TextBox1.Text = IsObjectCreated.ToString();
<br>}
```

* IsObjectProroryped

```csharp
protected void Button1_OnClick(object sender, EventArgs e)
<br>{
<br>TextBox1.Text = IsObjectPrototyped.ToString();
<br>}
```
