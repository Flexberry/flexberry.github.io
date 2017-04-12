---
title: Добавление объектов в AjaxGroupEdit при инициализации формы (новый объект)
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_add-objects-age-initialization.html
lang: ru
---

## Алгоритм добавления объектов в AGE при инициализации формы

`AjaxGroupEdit` редактирует уже зачитанные детейлы мастерового объекта (сам AGE в БД ничего не читает). Поэтому, если нужно при создании нового объекта добавить ему детейлы, то нужно редактируемому объекту просто добавить детейлы.

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
