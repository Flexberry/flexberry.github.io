---
title: Пользовательские настройки AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_a-g-e-user-settings.html
folder: products/flexberry-aspnet/
lang: ru
---



# Настройка ширины столбцов
Делается [WebObjectListView.ashx#Возможность_изменения_ширины_столбцов_10|аналогично WebObjectListView].

# Сохранение объектов
У [AGE](ajax-group-edit.html) есть элемент под названием Status <input type="hidden" /> которое отражает редактировался объект или нет, т.е. начинал ли пользователь что-либо вводить. <br /> Когда пользователь начинает вводить что-либо в строчку AGE, то рядом с кнопками в строке появляется индикатор ☀ - звездочка: [|][|][|][|]![](/images/pages/img/CaseberryWeb/WGE-modified.png)Это нужно для того, чтобы при нажатии на кнопку сохранить на странице (не в AGE) можно было предупредить пользователя, что он не сохранил данные в AGE. Если разрабатывается собственный контрол и встраивается в AGE, то для поддержки данного функционала следует реализовать контролу интерфейс IGroupEditCompatible.

# Редактирование списка детейлов
Строки детейла можно редактировать с формы редактирования элемента: в текущем окне, в новом окне и в модальном окне.
Настройка данных операций производится аналогично подобному в WOLV.

```cs
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

# Особенности открытия формы редактирования в отдельной вкладке в AGE
Если форму редактирования открывается в отдельной вкладке (OpenEditorInNewWindow=1, OpenEditorInModalWindow=0), детейл можно редактировать
и на вкладке с AGE, и на странице редактирования. Это можно решить, например, блокировкой строки в AGE пока открыта другая вкладка.

# Смена представления в зависимости от состояния агрегатора
Иногда необходимо отображать представления детейла с разными настройками для одного и того же агрегатора в разных состояниях. Например, при отмеченном/неотмеченном чекбоксе.

Например, есть такая модель данных:

![](/images/pages/ABratchikova/Модель для демонстрации возможности смены представления для разных состояний агрегатора.png)

У детейла есть два представления с разными настройками: 

![](/images/pages/ABratchikova/Два представления детейла.png)

На диаграмме для мастера возможно установить только одно представление детейла. Следовательно, смену представлений необходимо настраивать в коде приложения на форме редактирования. Для вывода необходимого представления используется <http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_ajax_group_edit.html#a14b02ecc8f3af5ec619b0b85e178df8aDetailViewName>.

Выглядеть это будет следующим образом:

```cs
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
 


 