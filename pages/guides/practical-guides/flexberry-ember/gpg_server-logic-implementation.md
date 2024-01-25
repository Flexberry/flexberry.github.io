---
title: Реализация серверной логики
sidebar: guide-practical-guides_sidebar
keywords: guide, C#, ASP.NET, клиент, сервер, бизнес-сервер, FunctionBuilder, БД, flexberry designer, стадия, диаграмма классов
toc: true
permalink: ru/gpg_server-logic-implementation.html
lang: ru
---

Реализация серверной логики будет продемонстрирована на примере типовой задачи, которую можно решить на стороне сервера. Пусть существует определенный заказ в статусе "Новый". Он содержит ряд строк с указанными товарами и их количеством на складе. Покупатель оплачивает заказ, следовательно, товары, входящие в его покупку, должны стать недоступны для повторной покупки. То есть оплаченный товар перестает быть "товаром в магазине" и становится собственностью покупателя. В такой ситуации при переводе заказа в статус "Оплаченный" товар должен списаться со складов.

Решение данной задачи не требует динамического отклика на стороне клиента и, соответственно, может быть реализовано в процессе обработки запроса об изменении статуса заказа на стороне сервера. Следовательно, нужно отследить, когда **заказ** будет находиться **в состоянии Update** с изменением статуса на **Paid**. В этом случае нужно выполнить поиск товара на складах и списать товар. При этом если определенного товара на складе более не остается, нужно удалить запись об этом товаре:

![Логика бизнес-сервера на смену статуса заказа](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-1.png)

Для решения задачи можно выделить следующие шаги:

1. уточнить состояние (заказ может быть новым или измененным) и статус заказа (проверяем, чтобы заказ имел статус "Оплаченный");
2. вычитать и отсортировать все записи о товарах на складах в соответствии со списком товаров в заказе;
3. перебрать все товары определенного типа на всех складах и:

- если товара на складах недостаточно - вывести сообщение об ошибке;
- если конкретного товара на очередном складе достаточно, то списать нужное количество товара и обнулить счетчик списания;
- если конкретного товара на очередном складе недостаточно, то удалить запись о текущем товаре на данном складе и уменьшить счетчик списания на то количество товара, которое имеется на данном складе.

Реализовать эту логику можно в ранее созданном бизнес-сервере **OrderBS**. В процессе работы рекомендуется использовать брейкпоинты, чтобы контролировать значения переменных.

## Условия применения логики

Прежде всего, следует реализовать проверку ключевых условий для применения описанной логики (шаг 1). Для этого в секции программиста (между комментариями "Start" и "End") **в теле** метода **OnUpdateOrder** проверяется ряд условий:

'''csharp
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateOrder(IIS.Shop.Order UpdatedObject)
{
    // \*** Start programmer edit section *** (OnUpdateOrder)
    if ((UpdatedObject.GetStatus() == ObjectStatus.Created
        || UpdatedObject.GetStatus() == ObjectStatus.Altered)
        && Array.IndexOf(UpdatedObject.GetAlteredPropertyNames(), "Status") >= 0
        && UpdatedObject.Status == OrderStatus.Paid)
    {
        result = changeOrderStatusPaid(UpdatedObject);
    }

    return new ICSSoft.STORMNET.DataObject[0];
    // *** End programmer edit section *** (OnUpdateOrder)
}
'''

В этом коде через операцию "или" проверяются два возможных состояния заказа (1 и 2 строки), а после с использованием операции "и" - что нужна запись, у которой поле "Статус" изменилось (3 строка) и равно на данный момент статусу "Оплачено" (4 строка).

> Сравнение статуса происходит не просто со строкой *Paid*,берется соответствующее значение из перечисления OrderStatus.

При добавлении данного кода обращение к объекту **ObjectStatus** будет подчеркнуто **красным цветом**. Для того чтобы исправить эту ошибку, необходимо указать использование соответствующего пространства имен в данном классе. Для исправления подобных ошибок можно **навести курсор** на "проблемный" объект, раскрыть **список предлагаемых исправлений** и выбрать **подключение** требуемого **пространства имен**:

![Подключение пространства имен ICSSoft.STORMNET](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-3.png)

> Каждый раз, когда добавлен новый using в коде, необходимо перезапускать приложение. Это нельзя сделать в режиме паузы (Break Mode).

Если все выполнено верно, то ошибка исчезнет.

Дальнейший код, может выглядеть достаточно громоздко. Поэтому для действий с Заказом и Товарами на складах создается отдельный **метод changeOrderStatusPaid()**, который будет возвращать список измененных объектов в основной метод бизнес-сервера:

'''csharp
public class OrderBS : ICSSoft.STORMNET.Business.BusinessServer
{

    // *** Start programmer edit section *** (OrderBS CustomMembers)

    /// <summary>
    /// Перевод заказа в статус "Оплачено"
    /// </summary>
    /// <param name="UpdatedObject">Заказ</param>
    /// <returns>Список измененных объектов (заказ + товары на складе)</returns>
    private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
    {
        // Определим массив, который будем возвращать для обновления.
        DataObject[] ret = new DataObject[0];

        return ret;
    }

    // *** End programmer edit section *** (OrderBS CustomMembers)

    // *** Start programmer edit section *** (OnUpdateOrder CustomAttributes)


    // *** End programmer edit section *** (OnUpdateOrder CustomAttributes)
    public virtual DataObject[] OnUpdateOrder(IIS.Shop.Order UpdatedObject)
    {
        // *** Start programmer edit section *** (OnUpdateOrder)
        if ((UpdatedObject.GetStatus() == ObjectStatus.Created
            || UpdatedObject.GetStatus() == ObjectStatus.Altered)
            && Array.IndexOf(UpdatedObject.GetAlteredPropertyNames(), "Status") >= 0
            && UpdatedObject.Status == OrderStatus.Paid)
        {
            result = changeOrderStatusPaid(UpdatedObject);
        }

        return new DataObject[0];
        // *** End programmer edit section *** (OnUpdateOrder)
    }
}
'''

> include note.html content="Для добавленного метода changeOrderStatusPaid следует учитывать два момента:

  1. Данный метод у помечен модификатором доступа private: он будет недоступен из других классов. Такое объявление является предпочтительным, так как метод реализует внутреннюю логику и нигде больше не будет использоваться.
  2. Данный метод добавлен перед основным методом бизнес-сервера, в специально отведенном скобками разработчика месте. Это нужно для того, чтобы метод при перегенерации сохранился.

Прежде всего, для любого метода рекомендуется добавлять **комментарии для автодокументации** ([Documentation comments](https://docs.microsoft.com/ru-ru/dotnet/csharp/language-reference/language-specification/documentation-comments)), в которой минимально указываются:

1. <summary> - описание метода;
2. <param> - параметр метода с указанием имени и описания;
3. <returns> - описание возвращаемых значений (если метод возвращает не void).

Кроме того, так как в дальнейшем будет часто использоваться тип **DataObject**, упрощено обращение к нему через добавление требуемого пространства имен с использованием **конструкции using**, таким образом, нет необходимости писать полное имя типа - ICSSoft.STORMNET.DataObject.

Вызов добавленного метода (changeOrderStatusPaid) из основного (OnUpdateOrder) и возвращение его результата в качестве списка измененных объектов:

'''csharp
public virtual DataObject[] OnUpdateOrder(IIS.Shop.Order UpdatedObject)
{
    // \*** Start programmer edit section *** (OnUpdateOrder)
    DataObject[] result = new DataObject[0];

    if ((UpdatedObject.GetStatus() == ObjectStatus.Created
        || UpdatedObject.GetStatus() == ObjectStatus.Altered)
        && Array.IndexOf(UpdatedObject.GetAlteredPropertyNames(), "Status") >= 0
        && UpdatedObject.Status == OrderStatus.Paid)
    {
        result = changeOrderStatusPaid(UpdatedObject);
    }

    return result;
    // *** End programmer edit section *** (OnUpdateOrder)
}
'''

> При наличии нескольких методов, которые могут изменять объекты данных, необходимо все измененные объекты добавлять к одному общему списку, а не заменять целиком данный список.

Для проверки правильности выполнения следует запустить сервер в режиме отладки с установленным брейкпоинтом в строке с оператором **return** в методе **changeOrderStatusPaid**:

![Брейкопинт в методе changeOrderStatusPaid](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-6.png)

В клиентском приложении Заказ 2 необходимо перевести в статус "Оплаченный" и сохранить его: на сервере сработает созданный брейкпоинт в новом методе. Продолжить выполнения кода на сервере (кнопка Continue) и обновить страницу в браузере: все изменения должны быть сохранены. Далее текущий заказ следует перевести обратно в статус "Новый" и сохранить его: брейкпоинт в данном случае не сработает. Это означает, что метод работает корректно - метод changeOrderStatusPaid не будет вызываться для новых заказов.

## Реализация основной части логики

После реализации логики перевода Заказа в статус "Оплаченный" создан отдельный метод. Следовательно, можно реализовывать основной функционал. Аналогичная логика реализована в разделе "[Работа с бизнес-сервером](gpg_business-server.html)" (Практическое руководство по веб-разработке на Flexberry ASP.NET).

> Всю дальнейшую логику мы будем проверять на примере Заказа 2.

В числе вносимых в код изменений (по сравнению с кодом, который представлен по ссылке выше) будет добавлена возможность сортировки вычитываемых товарных позиций со складов по номеру склада. Для этого нужно обновить класс StoreProduct во Flexberry Designer: добавить невидимый атрибут **Storehouse.Number** в представление **StoreProductE**.

![Добавление свойства Storehouse.Number в представление StoreProductE](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-7.png)

После этого следует обновить проект с объектами данных на бэкенде (выполнить перегенерацию соответствующего проекта). Теперь существует доступ из объекта конкретной позиции товара на складе к характеристике самого склада, в частности к его номеру. Предположительно, товары будут списываться всегда со складов в порядке увеличения порядкового номера склада.

Реализация основной логики: прежде всего, нужно **вычитать список всех требуемых товарных позиций со всех складов**:

'''charp
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    // Массив, который будет возвращен для обновления.
    DataObject[] ret = new DataObject[0];

    // Ограничение и вычитка всех объектов StoreProduct, которые подходят.
    Order order = UpdatedObject;
    Function lf = null;

    for (int i = 0; i < order.OrderItem.Count; i++)
    {
        Function storeProduct = FunctionBuilder.BuildEquals<StoreProduct>(x => x.Product, order.OrderItem[i].Product);
        if (order.OrderItem[i].Product != null)
        {
            lf = lf == null ? storeProduct : FunctionBuilder.BuildOr(lf, storeProduct);
        }
    }

    LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(StoreProduct), "StoreProductE");
    lcs.LimitFunction = lf;

    // Сортировка по номеру склада
    var sortColumns = new List<ColumnsSortDef>();
    sortColumns.Add(new ColumnsSortDef("Storehouse.Number", SortOrder.Asc));
    lcs.ColumnsSort = sortColumns.ToArray();
    
    IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    IDataService ds = mainUnityContainer.Resolve<IDataService>();
    DataObject[] objs = ds.LoadObjects(lcs);

    return ret; // БРЕЙКПОИНТ
}
'''

> При вычитке данных здесь используется функционал класса FunctionBuilder. Существует другой метод вычитки из БД - Linq-запросы. Выбор конкретного зависит от проекта.

Результатом выполнения данного участка кода является вычитанный из БД **список товарных позиций на складах в переменной objs**, соответствующих списку товаров в заказе. Необходимо проверить список объектов данных в этой переменной, установив **брейкпоинт** на **return ret** (см. предыдущий скриншот): отображены две записи (1 и 2 ед. товара на 1-м и 2-м складах соответственно).

![Содержимое переменной objs](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-9.png)

Далее для удобства работы следует сформировать **отсортированные** по первичному ключу товаров пары "ключ-значение", в которые в качестве значений будет добавлена коллекция вычитанных **товарных позиций** с соответствующим первичным ключом товара:

'''
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    ...
    IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    IDataService ds = mainUnityContainer.Resolve<IDataService>();
    DataObject[] objs = ds.LoadObjects(lcs);

    // Размещение вычитанных объектов в отсортированном списке для удобного доступа в дальнейшем.
    SortedList sl = new SortedList();
    for (int i = 0; i < objs.Length; i++)
    {
        var primaryKey = ((StoreProduct)objs[i]).Product.__PrimaryKey;
        ArrayList productList;
        if (sl.ContainsKey(primaryKey))
        {
            productList = (ArrayList)sl[primaryKey];
            productList.Add(objs[i]);
        }
        else
        {
            productList = new ArrayList();
            productList.Add(objs[i]);
            sl.Add(primaryKey, productList);
        }
    }

    return ret; // БРЕЙКПОИНТ
}
'''

**Проверка** результата (брейкпоинт прежний).

![Сортированный список товара на разных складах](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-11.png)

Теперь создана структура данных, которая хранит информацию о том, что товар с конкретным id найден на двух складах.

> Объект в текущем на момент останова программы состоянии доступен для просмотра в любом месте, где к нему есть обращение в коде.

Реализация непосредственно **логики списания** товаров из оплаченного заказа со складов:

'''
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    // Массив, который будет возвращаться для обновления.
    DataObject[] ret = new DataObject[0];

    // Ограничение и вычитка всех объектов StoreProduct, которые подходят.
    Order order = UpdatedObject;
    Function lf = null;

    for (int i = 0; i < order.OrderItem.Count; i++)
    {
        Function storeProduct = FunctionBuilder.BuildEquals<StoreProduct>(x => x.Product, order.OrderItem[i].Product);
        if (order.OrderItem[i].Product != null)
        {
            lf = lf == null ? storeProduct : FunctionBuilder.BuildOr(lf, storeProduct);
        }
    }

    LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(StoreProduct), "StoreProductE");
    lcs.LimitFunction = lf;

    // Сортировка по номеру склада
    var sortColumns = new List<ColumnsSortDef>();
    sortColumns.Add(new ColumnsSortDef("Storehouse.Number", SortOrder.Asc));
    lcs.ColumnsSort = sortColumns.ToArray();

    IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    IDataService ds = mainUnityContainer.Resolve<IDataService>();
    DataObject[] objs = ds.LoadObjects(lcs);

    // Размещение вычитанных объектов в отсортированном списке для удобного доступа в дальнейшем.
    SortedList sl = new SortedList();
    for (int i = 0; i < objs.Length; i++)
    {
        var primaryKey = ((StoreProduct)objs[i]).Product.__PrimaryKey;
        ArrayList productList;
        if (sl.ContainsKey(primaryKey))
        {
            productList = (ArrayList)sl[primaryKey];
            productList.Add(objs[i]);
        }
        else
        {
            productList = new ArrayList();
            productList.Add(objs[i]);
            sl.Add(primaryKey, productList);
        }
    }

    // Определение строки сообщения об ошибке. 
    string errStr = string.Empty;
    ArrayList retObjs = new ArrayList();

    // Проверка наличия товара на складах, если не хватает, то выводится сообщение об ошибке, если хватает - вычитывается количество.
    for (int i = 0; i < order.OrderItem.Count; i++)
    {
        if (sl.ContainsKey(order.OrderItem[i].Product.__PrimaryKey))
        {
            ArrayList arl = ((ArrayList)sl[order.OrderItem[i].Product.__PrimaryKey]);

            int productTotalCount = 0;
            for (int j = 0; j < arl.Count; j++)
            {
                productTotalCount += ((StoreProduct)arl[j]).Amount;
            }

            if (productTotalCount < order.OrderItem[i].Amount)
            {
                errStr += " Не хватает товара \"" + order.OrderItem[i].Product.Name
                    + "\" в наличии: " + productTotalCount
                    + ", требуется " + order.OrderItem[i].Amount
                    + Environment.NewLine;
            }
            else
            {
                int count = order.OrderItem[i].Amount;
                for (int j = 0; j < arl.Count; j++)
                {
                    if (count > 0 && ((StoreProduct)arl[j]).Amount > count)
                    {
                        ((StoreProduct)arl[j]).Amount -= count;
                        count = 0;
                        retObjs.Add(arl[j]);
                    }
                    else if (count > 0)
                    {
                        count -= ((StoreProduct)arl[j]).Amount;
                        ((StoreProduct)arl[j]).SetStatus(ObjectStatus.Deleted);
                        retObjs.Add(arl[j]);
                    }
                }
            }
        }
        else
        {
            errStr += "Товар  \"" +
            order.OrderItem[i].Product.Name + "\" в наличии отсутствует." + Environment.NewLine;
        }

        // В случае, если чего-то не хватило, вывести сообщение.
        if (errStr != string.Empty)
        {
            throw new Exception(errStr);
        }

        // При отсутствии проблем вернуть массив объектов, которые надо обновить.
        ret = new DataObject[retObjs.Count]; retObjs.CopyTo(ret, 0);
    }

    return ret;
}
'''

> Для того чтобы удалить запись из БД с использованием возможностей Flexberry ORM, достаточно присвоить ей статус Deleted и добавить к списку изменяемых объектов.

Для проверки написанного кода рекомендуется зафиксировать состояние складов до изменения статуса заказа:

![Состояние складов до смены статуса](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-13.png)

Предположительно, на первом складе совсем не останется товара "Монитор игровой MSI Optix MAG241CP", а на втором останется только 1 ед. указанного товара. Для проверки можно выполнить шага:

1.Выставить Дату оплаты Заказу 2 перед изменением статуса.

> Если не проставитm Дату оплаты, то в дальнейшем без нарушения логики или дополнительных действий (например, манипуляцией этим полем в БД) её не получится выставить.

2.Перевести Заказ 2 в статус "Оплаченный", сохранить его и проверить остатки товаров на складах:

![Состояние складов после смены статуса](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-14.png)

Код работает корректно: со складов списалось 2 единицы товара "Монитор игровой MSI Optix MAG241CP" в указанной нами последовательности.

## Итог

В результате действий, описанных в данной главе, реализована часть серверной логики, которая отрабатывает в момент сохранения. Действия, произведенные на сервере, отличаются высоким уровнем безопасности, так как полностью изолированы от потенциального пользователя. Их скорость также выше, чем у аналогичных манипуляций на стороне клиента.
Иногда в бизнес-серверах ember-приложений, сгенерированных при помощи Flexberry Designer, дублируется клиентская логика изменения в моделях. Это касается переопределения операций создания, обновления и удаления записей.

## Самостоятельная работа

Выполнить следующие доработки в приложении для более полной реализации функционала приложения: при изменении статуса Заказа на "Оплаченный" создавать на стороне сервера Накладную, к которой был бы уже прикреплен заказ, установлены статус "Новый" и выставлена текущая дата создания.

## Перейти

- [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
- [Бизнес-серверы и режим отладки](gpg_business-servers-and-debug-mode.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
- [Реализация презентационной логики](gpg_implementation-of-presentation-logic.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
