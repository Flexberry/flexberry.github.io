---
title: Реализация серверной логики
sidebar: guide-practical-guides_sidebar
keywords: guide, C#, ASP.NET, клиент, сервер, бизнес-сервер, FunctionBuilder, БД, flexberry designer, стадия, диаграмма классов
toc: true
permalink: ru/gpg_server-logic-implementation.html
lang: ru
---

Рассмотрим пример типовой задачи, которую можно решить на стороне сервера. Пусть существует определенный заказ в статусе "Новый". Он содержит ряд строк с указанными товарами и их количеством на складе. Покупатель оплачивает заказ, следовательно, товары, входящие в его покупку, должны стать недоступны для повторной покупки. То есть оплаченный товар перестает быть "товаром в магазине" и становится собственностью покупателя. В такой ситуации при переводе заказа в статус "Оплаченный" товар должен списаться со складов.

Решение данной задачи не требует динамического отклика на стороне клиента и, соответственно, может быть реализовано в процессе обработки запроса об изменении статуса заказа на стороне сервера. Следовательно, нам нужно отследить, когда **заказ** будет находиться **в состоянии Update** с изменением статуса на **Paid**. В этом случае нам нужно выполнить поиск товара на складах и списать товар. При этом если определенного товара на складе более не остается, нужно удалить запись об этом товаре:

![Логика бизнес-сервера на смену статуса заказа](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-1.png)

Разобьем данную логику на конкретные шаги:

1. уточнить состояние (заказ может быть новым или измененным) и статус заказа (проверяем, чтобы заказ имел статус "Оплаченный");
2. вычитать и отсортировать все записи о товарах на складах в соответствии со списком товаров в заказе;
3. перебрать все товары определенного типа на всех складах и:
- если товара на складах недостаточно - вывести сообщение об ошибке;
- если конкретного товара на очередном складе достаточно, то списать нужное количество товара и обнулить счетчик списания;
- если конкретного товара на очередном складе недостаточно, то удалить запись о текущем товаре на данном складе и уменьшить счетчик списания на то количество товара, которое имеется на данном складе.

**Реализуем** эту **логику** в ранее созданном бизнес-сервере **OrderBS**. В процессе работы используйте брейкпоинты, чтобы контролировать значения переменных.

## Условия применения логики

Прежде всего, необходимо реализовать проверку необходимых условий для применения описанной логики (шаг 1). Для этого в секции программиста (между комментариями "Start" и "End") **в теле** метода **OnUpdateOrder** проверим ряд условий:

{% highlight csharp%}
{% raw %}
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateOrder(IIS.Shop.Order UpdatedObject)
{
    // *** Start programmer edit section *** (OnUpdateOrder)
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
{% endraw %}
{% endhighlight %}

В этом коде мы проверяем через операцию "или" два возможных состояния заказа (1 и 2 строки), а после с использованием операции "и" проверяем, что нам нужна запись, у которой поле "Статус" изменилось (3 строка) и равно на данный момент статусу "Оплачено" (4 строка).

{% include important.html content="Мы сравниваем статус не просто со строкой &#34;Paid&#34;, мы берем соответствующее значение из перечисления OrderStatus." %}

При добавлении данного кода обращение к объекту **ObjectStatus** будет подчеркнуто **красным цветом**. Для того, чтобы исправить эту ошибку, нам нужно указать использование соответствующего пространства имен в данном классе. Для исправления подобных ошибок можно **навести курсор** на "проблемный" объект, раскрыть **список предлагаемых исправлений** и выбрать **подключение** требуемого **пространства имен**:

![Подключение пространства имен ICSSoft.STORMNET](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-3.png)

{% include important.html content="Каждый раз, когда вы добавляете новый using в коде, необходимо перезапускать приложение. Это нельзя сделать в режиме паузы (Break Mode)." %}

Если все выполнено верно, то ошибка исчезнет.

Дальнейший код, который мы добавим, может выглядеть достаточно громоздко. Поэтому **создадим** для действий с Заказом и Товарами на складах отдельный **метод changeOrderStatusPaid()**, который будет возвращать список измененных объектов в основной метод бизнес-сервера:

{% highlight csharp%}
{% raw %}
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
{% endraw %}
{% endhighlight %}

{% include note.html content="Для добавленного метода changeOrderStatusPaid следует учитывать два момента:
1. Данный метод у нас помечен модификатором доступа private: он будет недоступен из других классов. Такое объявление является предпочтительным, так как метод реализует внутреннюю логику и нигде больше не будет использоваться.
2. Данный метод мы добавили перед основным методом бизнес-сервера, в специально отведенном скобками разработчика месте. Это нужно для того, чтобы наш метод при перегенерации сохранился." %}

Прежде всего, для любого метода рекомендуется добавлять **комментарии для автодокументации** ([Documentation comments](https://docs.microsoft.com/ru-ru/dotnet/csharp/language-reference/language-specification/documentation-comments)), в которой минимально указываются:
1. {% raw %}<summary>{% endraw %} - описание метода;
2. {% raw %}<param>{% endraw %} - параметр метода с указанием имени и описания;
3. {% raw %}<returns>{% endraw %} - описание возвращаемых значений (если метод возвращает не void).

Кроме того, так как мы будем в дальнейшем часто использовать тип **DataObject**, мы **упростили обращение** к нему, добавив требуемое пространство имен с использованием **конструкции using**, и тем самым избавились от необходимости писать полное имя типа - ICSSoft.STORMNET.DataObject.

**Вызовем добавленный метод** (changeOrderStatusPaid) из основного (OnUpdateOrder) и вернуть его результат в качестве списка измененных объектов:

{% highlight csharp%}
{% raw %}
public virtual DataObject[] OnUpdateOrder(IIS.Shop.Order UpdatedObject)
{
    // *** Start programmer edit section *** (OnUpdateOrder)
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
{% endraw %}
{% endhighlight %}

{% include note.html content="При наличии нескольких методов, которые могут изменять объекты данных, необходимо все измененные объекты добавлять к одному общему списку, а не заменять целиком данный список." %}

**Проверим**, все ли верно мы выполнили. Для этого запустим сервер в **режиме отладки** и поставим **брейкпоинт** в строку с оператором **return** в методе **changeOrderStatusPaid**:

![Брейкопинт в методе changeOrderStatusPaid](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-6.png)

В клиентском приложении **переведем** Заказ 2 в статус "Оплаченный" и сохраним его: на сервере сработает созданный брейкпоинт в новом методе. Продолжим выполнение кода на сервере (кнопка Continue) и обновим страницу в браузере: убедимся, что все изменения сохранились. Далее переведем текущий заказ обратно в статус "Новый" и сохраним его: брейкпоинт в данном случае не сработает. Это означает, что метод работает корректно - метод changeOrderStatusPaid не будет вызываться для новых заказов.

## Реализация основной части логики

Теперь, когда для логики перевода Заказа в статус "Оплаченный" создан отдельный метод, можно реализовывать основной функционал. Аналогичная логика реализована в разделе "[Работа с бизнес-сервером](gpg_business-server.html)" (Практическое руководство по веб-разработке на Flexberry ASP.NET). 

{% include important.html content="Всю дальнейшую логику мы будем проверять на примере Заказа 2." %}

В числе вносимых в код **изменений** (по сравнению с кодом, который представлен по ссылке выше) мы добавим возможность сортировки вычитываемых товарных позиций со складов по номеру склада. Для этого нужно обновить класс StoreProduct во **Flexberry Designer**: необходимо добавить невидимый атрибут **Storehouse.Number** в представление **StoreProductE**. 

![Добавление свойства Storehouse.Number в представление StoreProductE](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-7.png)

После этого следует **обновить проект с объектами данных на бэкенде** (выполнить перегенерацию соответствующего проекта). Теперь мы легко получим доступ из объекта конкретной позиции товара на складе к характеристике самого склада, в частности к его номеру. Условимся, что товары будут списываться всегда со складов в порядке увеличения порядкового номера склада. 

Реализуем теперь основную логику. Прежде всего, нужно **вычитать список всех требуемых товарных позиций со всех складов**:

{% highlight csharp%}
{% raw %}
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    // Определим массив, который будем возвращать для обновления.
    DataObject[] ret = new DataObject[0];

    // Построим ограничение и вычитаем все объекты StoreProduct, которые нам подходят.
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

    DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

    return ret; // БРЕЙКПОИНТ
}
{% endraw %}
{% endhighlight %}

{% include note.html content="При вычитке данных здесь используется функционал класса FunctionBuilder. Существует другой метод вычитки из БД - Linq-запросы. Выбор конкретного зависит от проекта." %}

**Результатом** выполнения данного участка кода является вычитанный из БД **список товарных позиций на складах в переменной objs**, соответствующих списку товаров в заказе. Проверим список объектов данных в этой переменной, установив **брейкпоинт** на **return ret** (см. предыдущий скриншот): мы ожидаем увидеть две записи (1 и 2 ед. товара на 1-м и 2-м складах соответственно).

![Содержимое переменной objs](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-9.png)

Далее для удобства работы сформируем **отсортированные** по первичному ключу товаров пары "ключ-значение", в которые в качестве значений добавим коллекцию вычитанных **товарных позиций** с соответствующим первичным ключом товара:

{% highlight csharp%}
{% raw %}
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    ...

    DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

    // Разместим вычитанные объекты в отсортированном списке для удобного доступа в дальнейшем.
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
{% endraw %}
{% endhighlight %}

**Проверим** результат (брейкпоинт прежний).

![Сортированный список товара на разных складах](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-11.png)

Теперь мы имеем единую структуру данных, которая хранит информацию о том, что товар с конкретным id найден на двух складах.

{% include note.html content="Объект в текущем на момент останова программы состоянии доступен для просмотра в любом месте, где к нему есть обращение в коде." %}

**Реализуем** непосредственно **логику списания** товаров из оплаченного заказа со складов:

{% highlight csharp%}
{% raw %}
private static DataObject[] changeOrderStatusPaid(IIS.Shop.Order UpdatedObject)
{
    // Определим массив, который будем возвращать для обновления.
    DataObject[] ret = new DataObject[0];

    // Построим ограничение и вычитаем все объекты StoreProduct, которые нам подходят.
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

    DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

    // Разместим вычитанные объекты в отсортированном списке для удобного доступа в дальнейшем.
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

    // Определим строчку для сообщения об ошибке. 
    string errStr = string.Empty;
    ArrayList retObjs = new ArrayList();

    // Проверим наличие товара на складах, если не хватает, то выдадим сообщение об ошибке, если хватает, то вычитаем количество.
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
            errStr += "Товар	\"" +
            order.OrderItem[i].Product.Name + "\" в наличии отсутствует." + Environment.NewLine;
        }

        // В случае, если чего-то не хватило, сообщаем об этом пользователю.
        if (errStr != string.Empty)
        {
            throw new Exception(errStr);
        }

        // Если всё нормально, то возвращаем массив объектов, которые надо обновить.
        ret = new DataObject[retObjs.Count]; retObjs.CopyTo(ret, 0);
    }

    return ret;
}
{% endraw %}
{% endhighlight %}

{% include note.html content="Для того, чтобы удалить запись из БД с использованием возможностей Flexberry ORM, достаточно присвоить ей статус Deleted и добавить к списку изменяемых объектов." %}

**Проверим**, как работает написанный нами код. Для этого зафиксируем для себя состояние складов до изменения статуса заказа:

![Состояние складов до смены статуса](/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-13.png)

Ожидаем, что на первом складе совсем не останется товара "Монитор игровой MSI Optix MAG241CP", а на втором останется только 1 ед. указанного товара. Для проверки выполним два шага:

<ol>
<li>
    <b>Выставим Дату оплаты Заказу 2 перед изменением статуса</b><br>
    {% include important.html content="Если вы не проставите Дату оплаты, то в дальнейшем без нарушения логики или дополнительных действий (например, манипуляцией этим полем в БД) у вас не получится её выставить." %}
</li>

<li>
    Переведем Заказ 2 в статус "Оплаченный", сохраним его и посмотрим, какие товары остались на складах:

    <img src="/images/pages/guides/flexberry-ember/7-2-server-logic-implementation/7-2-14.png" alt="Состояние складов после смены статуса">
</li>

</ol>

Код работает корректно: со складов списалось 2 единицы товара "Монитор игровой MSI Optix MAG241CP" в указанной нами последовательности.

## Итог
В результате действий, описанных в данной главе, мы реализовали часть серверной логики, которая отрабатывает в момент сохранения. Действия, произведенные на сервере, отличаются высоким уровнем безопасности, так как полностью изолированы от потенциального пользователя. Их скорость также выше, чем у аналогичных манипуляций на стороне клиента.
Иногда в бизнес-серверах ember-приложений, сгенерированных при помощи Flexberry Designer, дублируется клиентская логика изменения в моделях. Это касается переопределения операций создания, обновления и удаления записей.

## Самостоятельная работа
Вы можете выполнить следующие доработки в приложении самостоятельно для более полной реализации функционала приложения:
1. при изменении статуса Заказа на "Оплаченный" создавать на стороне сервера Накладную, к которой был бы уже прикреплен заказ, установлены статус "Новый" и выставлена текущая дата создания. 

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Бизнес-серверы и режим отладки](gpg_business-servers-and-debug-mode.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Реализация презентационной логики](gpg_implementation-of-presentation-logic.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
