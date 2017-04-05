---
title: События WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-events.html
lang: ru
---

Все аргументы событий [WebObjectListView](fa_web-object-list-view.html) наследуются от базового:

```csharp
/// <summary>
/// Тип аргумента для событий WOLV.
/// </summary>
public class WolvEventArgs : CancelEventArgs
{
}
```

## Список событий

| Событие | Описание |
| ------- | -------- |
| `ObjectAdding` | Переход на форму создания объекта. |
| `ObjectEditing` | Переход на форму редактирования объекта. |
| `ObjectPrototyping` | Переход на форму прототипирования объекта. |
| `ObjectDeleting` | Удаление объекта началось. Событие будет вызываться для каждого удаляемого объекта. |
| `ObjectsDeleted` | Объекты удалились. Нельзя генерировать событие удаление для каждого объекта. |

* Есть возможность настроить своё сообщение для пользователей после удаления объекта. Для формирования сообщения можно использовать список удаленных объектов и
  список объектов, которые не удалось удалить.
* Также есть возможность передавать статус удаления меду событиями начала удаления и окончания удаления. 

## Аргументы событий

У событий `ObjectEditing` и `ObjectPrototyping` в аргументах содержится первичный ключ объекта данных - `DataObjectPrimaryKey`.

Через аргументы события `ObjectDeleting` можно получить к удаляемому объекту данных - `DataObj`, а так же изменять его.

Аргументы события `ObjectsDeleted` позволяют редактировать или очищать сообщение пользователю - `UserMessage`.

Для хранения прикладных метаданных между событием `ObjectDeleting` и событием `ObjectsDeleted` используются `DeletingState` и `DeletedState`, доступные через аргументы этих событий.

## Отмена событий

Любое событие можно отменить, установив свойство у аргументов `Cancel = true`, т.к. все аргументы наследуются от `CancelEventArgs`.

Если произошло исключение при удалении объекта, то его можно обработать подписавшись на событие `ExceptionThrown`.

## Собственный обработчик события `ObjectDeleting`

Если реализуется собственный обработчик `ObjectDeleting` и устанавливается свойство у аргументов `Cancel = true`, то нужно в обработчике события `ObjectsDeleted` очистить или изменить на своё сообщение пользователю, которое хранится `args.UserMessage`. Иначе появится сообщение "Не удалено объектов: 1, т.к. некоторые объекты были заблокированы.", даже если объекты успешно удалены.

## Пример ручной обработки события `ObjectDeleting` для обработки [каскадного удаления объектов](fo_cascade-delete.html)

Концепция обработки событий:

1. Подписаться на событие `ObjectDeleting`.
2. Написать обработчик, чтобы обработать каскадное удаление объектов, связанных с удаляемым.
3. Написать код бизнес-сервера, который будет сообщать обработчику об объектах, связанных с удаляемым.
4. Если таковые имеются, то запросить у пользователя разрешение на удаление связанных объектов (показав ему какие именно объекты будут удалены).
5. Если пользователь, разрешит каскадное удаление, то выполнить его.

### Первый этап. Подписка на событие

__Первый вариант.__ Подписаться статично (в разметке страницы, содержащей [WebObjectListView](fa_web-object-list-view.html). Пусть она называется `wolv_page.aspx`).

```xml
<%-- Разметка из wolv_page.aspx --%>;
<ac:WebObjectListView ID="WebObjectListView1" runat="server" OnObjectDeleting="WebObjectListView1_ObjectDeleting" />;
```

__Второй вариант.__ Подписаться из кода (в code-behind страницы, содержащей WOLV. Пусть файл с кодом называется `wolv_page.aspx.cs`).

```csharp
// Код из wolv_page.aspx.cs
protected override void Preload()
{
    WebObjectListView1.ObjectDeleting += WebObjectListView1_ObjectDeleting;
}
```

{% include note.html content="При подписке на событие из кода, студия предложит автоматическую генерацию обработчика события. Если нажать клавишу `Tab` после того как
напечатано +=, то сгенерируется имя обработчика `WebObjectListView1_ObjectDeleting.`

Если еще раз нажать клавишу `Tab`, будет сгенерирован шаблон обработчика:

```csharp
protected void WebObjectListView1_ObjectDeleting(WebObjectListView sender, WolvEventArgs args)
```
" %}

### Второй этап. Обработка события

```csharp
// Код из wolv_page.aspx.cs

// Колекция объектов в формате [{objectPK: Строковое представление первичного ключа удаляемого адреса, confirmMessage: Текст сообщения о ссылающихся, на адрес, объектах}, ...]
private List<object> _deletingObjectsCollection = new List<object>();

// Данный обработчик будет последовательно вызван для каждого объекта, выбранного на удаление.
protected void WebObjectListView1_ObjectDeleting(WebObjectListView sender, WolvObjectDeletingEventArgs args)
{   
    // Помечаем пришедший объект на удаление (в данном примере это будет адрес медицинского учреждения).
    args.DataObj.SetStatus(ICSSoft.STORMNET.ObjectStatus.Deleted);
    try
    {   // Пытаемся удалить (дальнейшая обработка происходит в Бизнес-сервере АдресБС.cs).
        // Если во время удаления произойдет ошибка, мы сможем ее обработать в блоке catch.
        // Если АдресБС.cs найдет учреждения, ссылающиеся на удаляемый адрес, он кинет ConfirmAdresDeletingException.
        ICSSoft.STORMNET.Business.DataServiceProvider.DataService.UpdateObject(args.DataObj);
    }
    // Обрабатываем исключение бизнес-сервера
    catch (ConfirmAdresDeletingException ex)
    {   
        // Если мы попали в этот участок кода, значит были обнаружены объекты ссылающиеся на удаляемый адрес.
        // Требуется подтверждение каскадного удаления.
        // Запоминаем информацию о ссылках на удаляемый адрес. После перезагрузки страницы, её заберёт клиентский код.
        // objectPK - первичный ключ ссылающегося объекта, confirmMessage - сообщение об этом объекте, сформированное в бизнес-сервере.
        _deletingObjectsCollection.Add(new { objectPK = args.DataObj.__PrimaryKey.ToString(), confirmMessage = ex.Message });
     }
     //Отменяем дальнейшее выполнение удаления WOLV-ом, т.к. мы уже выполнили его руками.
     //Если этого не сделать, то код на бизнес-сервере выполнится повторно.
     args.Cancel = true;
}

// Возвращает сериализованную строку JSON формата вида "[{"objectPK": "Строковое представление первичного ключа удаляемого адреса", "confirmMessage": "Текст сообщения о ссылающихся, на адрес, объектах"}, ...]".
public string DeletingObjects
{
    get
    {
        //JavaScriptSerializer находится в пространстве имен System.Web.Script.Serialization;
        JavaScriptSerializer serializer = new JavaScriptSerializer();
        //Экранируем кавычки
        return serializer.Serialize(_deletingObjectsCollection).Replace("\"", "\\\"");
    }
}
```

### Третий этап. Получение в бизнес-сервере информации о связанных объектах

```csharp
//Код из АдресБС.cs
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateАдрес(IIS.MedicalInstitutionsGuide.Адрес UpdatedObject)
{
    // *** Start programmer edit section *** (OnUpdateАдрес)
    //На случай, если обработка прервётся, возвращаем пустой массив DataObject[]
    DataObject[] result = new DataObject[0];

    if (UpdatedObject.GetStatus() == ObjectStatus.Deleted)
    {
        //Получим список учреждений ссылающихся на удаляемый адрес
        var ds = (SQLDataService)DataServiceProvider.DataService;
        МедицинскоеУчреждение[] mil = ds.Query<МедицинскоеУчреждение>(МедицинскоеУчреждение.Views.MIG_МедицинскоеУчреждениеE)
            .Where(o => o.Адрес.__PrimaryKey == UpdatedObject.__PrimaryKey)
            .ToArray();
        //Если список не пуст, значит потребуется каскадное удаление
        //Нужно проверить дал ли пользователь подтверждение, а если еще - нет, то запросить его
        if (mil.Length > 0)
        {
            //Если пользователь подтвердил каскадное удаление, то в DynamicProperties объекта должен находится флаг, по ключу "DeletingAllowed"
            if (UpdatedObject.DynamicProperties.ContainsKey("DeletingAllowed"))
            {
                //Помечаем учреждения на удаление
                foreach (МедицинскоеУчреждение mi in mil)
                {
                    mi.SetStatus(ObjectStatus.Deleted);
                }
                //Возвращаем удаляемые учреждения, чтобы они были удалены в этой же транзакции
                result = mil;
            }
            else
            {
                //Необходимо запросить у пользователя подтверждение на каскадное удаление учреждений, вслед за их адресом
                //Формируем строку с названиями учреждений
                string referencedObjects = String.Join(", ", mil.Select(o => String.Format("'{0}'", o.Название)));
                //Прерываем удаление, отправив в обработчик удаления, сообщения о ссылающихся объектах
                //Метод UpdatedObject.ToString(true) просто возвращает строку с адресом, например "Россия, Пермский край, Пермь, Братьев Игнатовых, 2"
                throw new MIG_ConfirmAdresDeletingException(String.Format("Вы уверены, что хотите удалить адрес: '{0}'? На него ссылаются следующие медицинские учреждения: {1}. 
                    Они так же будут удалены вместе с информацией об их телефонах и расходах.",
                    UpdatedObject.ToString(true),
                    referencedObjects));
            }
        }
    }
    return result;
    // *** End programmer edit section *** (OnUpdateАдрес)
}
```

### Четвертый этап. Запрос подтверждения на каскадное удаление объектов

Запрос подтверждения при помощи [JavaScript API](fa_js-api-core.html):

```javascript
//Код из wolv_page.aspx
//wiki запрещает тэг SCRIPT-а, поэтому перед ним стоит подчеркивание, в реальном коде его быть не должно.
<asp:Content ID="scriptContent" ContentPlaceHolderID="ContentPlaceHolder0" runat="server">
    <_script type="text/javascript">
        //Клиенская обработка каскадного удаления адресов и ссылающихся, на них, объектов
        $(document).ready(function () {
            //Забираем с сервера (через публичное свойство DeletingObjects)сериализованную строку JSON формата вида 
            //"[{"objectPK": "Строковое представление первичного ключа удаляемого адреса", 
            // "confirmMessage": "Текст сообщения о ссылающихся, на адрес, объектах"}, ...]" и десериализуем её
            var deletingObjects = $.parseJSON("<%=DeletingObjects%>");
            //Выполняем обработку, только если хотя бы для одного адреса требуется каскадное удаление
            if (deletingObjects.length > 0) {
                //Строка для сообщения пользователю
                var confirmMessage = "";
                //Строка JSON формата для первичных ключей удаляемых адресов (для их передачи на сервер, в случае подтверждения удаления)
                var deletingObjectsPK = "";
                var lastIndex = deletingObjects.length - 1;
                //Формируем строки
                for (var i = 0; i < deletingObjects.length; i++) {
                    confirmMessage = confirmMessage + (i + 1) + "). " + deletingObjects[i].confirmMessage + "\n";
                    deletingObjectsPK = deletingObjectsPK + "\"" + deletingObjects[i].objectPK + "\"";
                    if (i < lastIndex) {
                        deletingObjectsPK = deletingObjectsPK + ", ";
                    }
                }
                deletingObjectsPK = "[" + deletingObjectsPK + "]";
                //Отображаем окно подтверждения
                $.ics.dialog.confirm({
                    message: confirmMessage,
                    title: 'Подтверждение каскадного удаления связанных объектов',
                    okButtonText: "Всё равно удалить",
                    cancelButtonText: "Отменить удаление",
                    callback: function (res) {
                        if (res) {
                            //При нажатии OK.
                            //Инициируем PostBack, передав, в качестве аргумента, 
                            //строку JSON формата с первичными ключами удалямых адресов.
                            //'confirmDeletingOkBtn' - имя кнопки на окне подтверждения, которая инициирует PostBack.
                            __doPostBack('confirmDeletingOkBtn', deletingObjectsPK);
                        } else {
                            //При нажати Cancel.
                            alert("Каскадное удаление отменено.");
                        }
                    }
                });
            }
        });
    </_script>
</asp:Content>
```

Пользователю будет выдано следующее окно подтверждения:

![](/images/pages/products/flexberry-aspnet/controls/wolv/confirm-cascade-deleting.png)

### Пятый этап. Выполнить каскадное удаление объектов, если пользователь дал согласие

```csharp
// Код из wolv_page.aspx.cs
protected override void Preload()
{
    // *** Start programmer edit section *** (PostBack handling)
    if (Page.IsPostBack)
    {
        //Обрабатываем случай, когда PostBack произошел при подтверждении, пользователем, 
        //каскадного удаления выбранных адресов и ссылающихся, на них, объектов.

        //Получаем имя объекта вызвавшего PostBack
        string targetID = Request.Form["__EVENTTARGET"];
        if (!String.IsNullOrEmpty(targetID))
        {
            //Если объект - это кнопка "OK" окна подтверждения
            if (targetID.Equals("confirmDeletingOkBtn"))
            {
                //Получаем строку JSON формата с первичными ключами удаляемых адресов
                string argument = Request.Form["__EVENTARGUMENT"];
                //Выполняем десериализацию строки
                JavaScriptSerializer serializer = new JavaScriptSerializer();
                List<string> deletingObjectsPK = serializer.Deserialize<List<string>>(argument);
                //Формируем массив удаляемых адресов
                DataObject[] deletingAdreses = deletingObjectsPK.Select(pk =>
                {
                    //Создаем объект класса "Адрес" с нужным первичным ключом
                    Адрес deletingAdres = new Адрес();
                    deletingAdres.SetExistObjectPrimaryKey(new Guid(pk));
                    //Подтверждаем каскадное удаление объектов, ссылающихся на этот адрес
                    //по наличию, в DynamicProperties, ключа "DeletingAllowed", бизнес-сервер поймёт, что каскадное удаление разрешено
                    deletingAdres.DynamicProperties.Add("DeletingAllowed", true);
                    //Помечаем адрес на удаление
                    deletingAdres.SetStatus(ObjectStatus.Deleted);
                    //Добавляем в массив
                    return deletingAdres;
                }).ToArray<Адрес>();
                //Удаляем. (Дальнейшая обработка происходит в АдресБС)
                DataServiceProvider.DataService.UpdateObjects(ref deletingAdreses);
            }
        }
    }
    // *** End programmer edit section *** (PostBack handling)
}
```
