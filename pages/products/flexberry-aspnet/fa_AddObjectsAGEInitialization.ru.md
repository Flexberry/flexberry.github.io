---
title: Добавление объектов в AGE при инициализации формы (новый объект)
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_add-objects-a-g-e-initialization.html

---
<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 
* Платформа: [Flexberry ASP.Net](fa_flexberry-a-s-p-n-e-t.html)
* Компонент: [AjaxGroupEdit](https://flexberry.github.io/ru/fa_ajax-group-edit.html)
* Предназначение: Возможность добавления объектов в [AjaxGroupEdit](https://flexberry.github.io/ru/fa_ajax-group-edit.html) при инициализации формы.
</td>
</tr></tbody></table></a>
</div>

## Алгоритм добавления объектов в AGE при инициализации формы
AjaxGroupEdit редактирует уже зачитанные детейлы мастерового объекта (сам AGE в БД ничего не читает). Поэтому, если нужно при создании нового объекта добавить ему детейлы, то нужно редактируемому объекту просто добавить детейлы.

## Пример
Пример редактирования объекта "Кошка" с детейлами "Лапа":

```csharp
/// <summary>
/// Здесь лучше всего писать бизнес-логику, оперируя только объектом данных
/// </summary>
protected override void PreApplyToControls()
{
    if (!IsPostBack && (DataObject == null || DataObject.GetStatus(true) == ObjectStatus.Created))
    {
        var cat = new Кошка();
        var foots = new DetailArrayOfЛапа(cat)
            {
                new Лапа { Размер = 10 }, 
                new Лапа { Размер = 11 }
            };
        cat.Лапа = foots;

        DataObject = cat;
    }
}
```

Если вы хотите добавить объекты, но не сохранять их БД, если пользователь их не изменил, то нужно также добавлять, но со статусом Unaltered.

## Откуда ссылаются на эту страницу

* [AjaxGroupEdit (AGE)](https://flexberry.github.io/ru/fa_ajax-group-edit.html)


## Куда ссылается эта страница

* [AjaxGroupEdit (AGE)](https://flexberry.github.io/ru/fa_ajax-group-edit.html)
* [Flexberry ASP.NET](https://flexberry.github.io/ru/fa_flexberry-a-s-p-n-e-t.html)