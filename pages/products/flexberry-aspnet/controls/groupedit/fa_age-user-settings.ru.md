---
title: Пользовательские настройки AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_age-user-settings.html
lang: ru
---

## Настройка ширины столбцов

Делается [аналогично WebObjectListView](fa_web-object-list-view.html).

### Сохранение объектов

У [AGE](fa_ajax-group-edit.html) есть элемент под названием `Status`, который отражает редактировался объект или нет, т.е. начинал ли пользователь что-либо вводить.  
Когда пользователь начинает вводить что-либо в строчку AGE, то рядом с кнопками в строке появляется индикатор ☀ - звездочка:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/wge-modified.png)
 
Это нужно для того, чтобы при нажатии на кнопку сохранить на странице (не в AGE) можно было предупредить пользователя, что он не сохранил данные в AGE. Если разрабатывается собственный контрол и встраивается в AGE, то для поддержки данного функционала следует реализовать контролу интерфейс `IGroupEditCompatible`.

## Редактирование списка детейлов

Строки детейла можно редактировать с формы редактирования элемента: в текущем окне, в новом окне и в модальном окне.
Настройка данных операций производится аналогично подобному в [WebObjectListView](fa_web-object-list-view.html).

```csharp
protected override void PostApplyToControls()
{
    // В новом или модальном окне.
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInModalWindow = false(true);
    ctrlКвартира.Operations.OpenEditorInNewWindow = true(false);
            
    // В текущем окне.
    // Формирование URL формы редактирования детейла с ReturnUrl для возврата к этой (текущей) форме.
    ctrlКвартира.DetailEditForm = FormUrlHelper.UpdateParam(
        WebParamController.ReturnURL,
        FormUrlHelper.RedirectEditFormUrl(Page.ResolveUrl(FormPath), DataObject.__PrimaryKey.ToString()),

        // КвартираE - форма редактирования детейла.
        КвартираE.FormPath);
}
```

## Особенности открытия формы редактирования в отдельной вкладке в AGE

Если форма редактирования открывается в отдельной вкладке (OpenEditorInNewWindow=1, OpenEditorInModalWindow=0), детейл можно редактировать
и на вкладке с AGE, и на странице редактирования. Исключить параллельное редактирование, например, блокировкой строки в AGE пока открыта другая вкладка.

## Смена представления в зависимости от состояния агрегатора

Иногда необходимо отображать представления детейла с разными настройками для одного и того же агрегатора в разных состояниях. Например, при отмеченном/неотмеченном чекбоксе.

Например, есть такая модель данных:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/model-agregator.png)

У детейла есть два представления с разными настройками: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/two-view-detail.png)

На диаграмме для мастера возможно установить только одно представление детейла. Следовательно, смену представлений необходимо настраивать в коде приложения на форме редактирования. 
Выглядеть это будет следующим образом:

```csharp
/// <summary>
/// Здесь лучше всего изменять свойства контролов на странице, которые не обрабатываются WebBinder.
/// </summary>
protected override void PostApplyToControls()
{
      //Если не отмечено состояние "Активно" в чекбоксе,
      //то выводится первое представление детейла (свойства 1,3,5).
            
      if (ctrlАктивно.Checked == false)
      {
          ctrlSeveralViewsDetail.DetailViewName = SeveralViewsDetail.Views.SeveralViewsDetailD1.Name;
      }
      //Если чекбокс отмечен, то выводить второе представление (свойства 2,4,6).
      else
      {
          ctrlSeveralViewsDetail.DetailViewName = SeveralViewsDetail.Views.SeveralViewsDetailD2.Name;
      }

      Page.Validate();
}
```
 