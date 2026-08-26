---
title: Включение режима Read-only для отдельных столбцов AGE
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_read-only-age.html
lang: ru
---

Так как технологией не предусматривается сценарий, когда какой-либо отдельный столбец [AGE](fa_ajax-group-edit.html) доступен только для чтения, то стандартного способа нет.

Однако, есть обходной путь: 

1. Добавить в детейл [вычислимое поле](fo_not-stored-attributes.html).
2. Задать функцию вычисления поля так, чтобы она дублировала столбец, который необходимо сделать только для чтения.
3. В [E-представление](fd_e-view.html) детейла созданное вычислимое поле, снять видимость с оригинального поля.

После перегенерации объектов все будет работать.

### Пример

Пусть дан следующий детейл:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age1.png)

Необходимо сделать поле `Field2` доступным только для чтения.

Для начала необходимо добавить вычислимое поле в класс:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age2.png)

Затем, настроить представление:

1. Добавить вычислимое поле
2. Снять видимость со "старого" поля
3. Настроить заголовок вычислимого поля

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age3.png)

Перегенерировать объекты. После перегенерации надо модифицировать `getter` вычислимого поля так, чтобы он возвращал значение поля, которое должно отображаться только для чтения:

```csharp
[ICSSoft.STORMNET.NotStored()]
[StrLen(255)]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "Field2")]
public virtual string Field2ReadOnly
{
    get
    {
        // *** Start programmer edit section *** (Side.Field2ReadOnly Get)
        return Field2; // Здесь указываем поле.
        // *** End programmer edit section *** (Side.Field2ReadOnly Get)
    }
    set
    {
        // *** Start programmer edit section *** (Side.Field2ReadOnly Set)

        // *** End programmer edit section *** (Side.Field2ReadOnly Set)
    }
}
```

{% include note.html content="`DataServiceExpression` в данном случае устанавливать не обязательно: при загрузке AGE берется реальное значение, а не `StringedView`. Однако, рекомендуется установить и его." %}

После этого AGE будет выглядеть следующим образом:

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age4.png)
 