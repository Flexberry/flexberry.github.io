---
title: Настройка контролов внутри AGE
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_controls-in-a-g-e.html

---

* **Продукт:** [Flexberry ASP.Net](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент:** [AjaxGroupEdit](fa_ajax-group-edit.html)
* **Предназначение:** Представлен механизм, с помощью которого возможно донастроить контролы, которые используются в [AjaxGroupEdit.](fa_ajax-group-edit.html)

## Описание настройки

Иногда возникает задача дополнительной настройки свойств контролов, генерируемых для редактирования и отображения значений свойств детейлов в AGE. Для этого можно использовать делегаты, задаваемые статически для класса [AjaxGroupEdit](fa_ajax-group-edit.html). Делегат задается для конкретного имени представления и имеет тип TuneControlDelegate, подробнее про который можно почитать [здесь](fa_tune-control-delegate-method.html). Добавить делегат можно добавить с помощью метода SetControlTuner:

```cs
/// <summary>
/// Установить метод для настройки контролов, отображающихся в AGE.
/// </summary>
/// <param name="viewName">Имя представления (детейла, отображаемого в AGE), для которого будет вызываться передаваемый метод.</param>
/// <param name="method">Метод, выполняющий настройку контролов.</param>
public static void SetControlTuner(string viewName, TuneControlDelegate method)
```

Задавать делегаты лучше в Global.asax.cs, в методе Application_Start. Пример использования:

```cs
AjaxGroupEdit.SetControlTuner(
    Подзадача.Views.ПодзадачаE.Name,
        (control, data) =>
           { 
              if (control.GetType() == typeof(TextBox) && data.PropertyName == Information.ExtractPropertyName<Подзадача>(x => x.Описание)) 
                 ((TextBox)control).TextMode = TextBoxMode.MultiLine; 
           });
```

Подробно об AjaxGroupEdit написано в этой [статье](fa_ajax-group-edit.html).
 
## Другие варианты настройки контролов

* [Настройки для LookUp в AGE](fa_settings-for-look-up-in-a-g-e.html)

## Откуда ссылаются на эту страницу

* [AjaxGroupEdit (AGE)](fa_ajax-group-edit.html)
* [DecimalTextBox](fa_decimal-text-box.html)

## Куда ссылается эта страница

* [AjaxGroupEdit (AGE)](fa_ajax-group-edit.html)
* [DecimalTextBox](fa_decimal-text-box.html)
* [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* [Настройки для LookUp в AGE](fa_settings-for-look-up-in-a-g-e.html)
* [Настройка контрола фильтрации в WebObjectListView](fa_tune-control-delegate-method.html)


 