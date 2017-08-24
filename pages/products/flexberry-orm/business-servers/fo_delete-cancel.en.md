---
title: Cancellation of deletion of the used value from the directory
sidebar: flexberry-orm_sidebar
keywords: DataObject, business servers, restrictions
summary: Solving the problems of non-informative messages when an object is deleted
toc: true
permalink: en/fo_delete-cancel.html
lang: en
---

## Используемые ссылки на мастера

Данная статья затрагивает вопрос **отмены удаления** значений из таблицы, хранящей мастера, используемые в других объектах.

Вопрос о том, как **удалить** значения, рассматривается в статье [Каскадное удаление объектов](fo_cascade-delete.html).

## Попытка удаления мастера

Пусть дана следующая диаграмма:

![](/images/pages/products/flexberry-orm/business-servers/kredit-diagramm.png)

Если в базе данных есть объекты типа `Клиент`, ссылающиеся на него, то при попытке удаления объекта типа `Адрес` будет выведена ошибка:

![](/images/pages/products/flexberry-orm/business-servers/delete-error.png)

База данных не даст удалить такой объект, а пользователю выдастся неинформативное сообщение.

## Решение проблемы

Для решения данной проблемы стоит воспользоваться [бизнес-сервером](fo_bs-wrapper.html) удаляемого объекта:

* Узнать количество объектов, ссылающихся на удаляемый.
* Если оно не равно 0, выбросить исключение с понятным пользователю описанием проблемы.
* При необходимости, перехватить исключение и обработать особым образом.

## Пример

Для описаных выше условий: добавить проверку в бизнес-сервер класса `Адрес`:

```csharp
if (UpdatedObject.GetStatus() == ObjectStatus.Deleted)
{
    // Найдем количество клиентов, ссылающихся на удаляемый адрес.
    var ds = (MSSQLDataService)DataServiceProvider.DataService;
    var clientsCount = ds.Query<Клиент>(Клиент.Views.КлиентE).Where(k => k.Прописка.__PrimaryKey == UpdatedObject.__PrimaryKey).Count();

    // Если клиентов не 0, выкинем исключение.
    if (clientsCount != 0)
    {
        throw new Exception(string.Format("Невозможно удалить Адрес. По данному адресу проживает {0} клиент(а)(ов)", clientsCount));
    }
}
```

В результате, при попытке удалить адрес, по которому прописаны клиенты, пользователю выдастся следующее сообщение:

![](/images/pages/products/flexberry-orm/business-servers/delete-error-plus.png)

Сообщение изменилось на более информативное.
