---
title: Включение режима Read-only для отдельных столбцов AGE
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_read-only-in-a-g-e.html
folder: products/flexberry-aspnet/
lang: ru
---



# Описание алгоритма включения режима Read-only для отдельных столбцов
Так как технологией не предусматривается сценарий, когда какой-либо отдельный столбец AGE доступен только для чтения, то стандартного способа нет.


Однако, есть обходной путь: 

# Добавить в детейл [вычислимое поле](create-with-data-service-expression.html).
# Задать функцию вычисления поля так, чтобы она дублировала столбец, который необходимо сделать только для чтения.
# В [E-представление](e-view.html) детейла созданное вычислимое поле, снять видимость с оригинального поля.


После перегенерации объектов все будет работать.


### Пример
Пусть дан следующий детейл:
[imageauto||http://wiki.ics.perm.ru/GetFile.aspx?File=ReadOnlyAGE1.png&AsStreamAttachment=1&Provider=ScrewTurn.Wiki.Plugins.SqlServer.SqlServerFilesStorageProvider&IsPageAttachment=1&Page=AjaxGroupEdit&NoHit=1]

Необходимо сделать поле `Field2` доступным только для чтения.

Для начала, необходимо добавить вычислимое поле в класс:
[imageauto||http://wiki.ics.perm.ru/GetFile.aspx?File=ReadOnlyAGE2.png&AsStreamAttachment=1&Provider=ScrewTurn.Wiki.Plugins.SqlServer.SqlServerFilesStorageProvider&IsPageAttachment=1&Page=AjaxGroupEdit&NoHit=1]

Затем, настроить представление:
# Добавить вычислимое поле
# Снять видимость со "старого" поля
# Настроить заголовок вычислимого поля

[imageauto||http://wiki.ics.perm.ru/GetFile.aspx?File=ReadOnlyAGE3.png&AsStreamAttachment=1&Provider=ScrewTurn.Wiki.Plugins.SqlServer.SqlServerFilesStorageProvider&IsPageAttachment=1&Page=AjaxGroupEdit&NoHit=1]

Перегенерировать объекты. После перегенерации надо модифицировать `getter` вычислимого поля так, чтобы он возвращал значение поля, которое мы хотим отображать только для чтения:
```
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
        }```

(((<msg type=note>`DataServiceExpression` в данном случае устанавливать не обязательно: при загрузке AGE берется реальное значение, а не `StringedView`. Однако, рекомендуется установить и его.</msg>)))

После этого AGE будет выглядеть следующим образом:
[imageauto||http://wiki.ics.perm.ru/GetFile.aspx?File=ReadOnlyAGE4.png&AsStreamAttachment=1&Provider=ScrewTurn.Wiki.Plugins.SqlServer.SqlServerFilesStorageProvider&IsPageAttachment=1&Page=AjaxGroupEdit&NoHit=1]
 


 