---
title: Загрузка данных и EmptyControl в WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-empty-control.html
lang: ru
---

## EmptyControl

Если в WOLV нет данных, то можно отобразить свой контрол, который говорит пользователю об отсутствии данных. По-умолчанию покажется `ICSSoft.STORMNET.Web.AjaxControls.EmptyWOLVControl`.

Для того чтобы сменить контрол необходимо в свойстве `WOLV.EmptyControlType` указать тип его тип следующим образом:

1.Создать свой контрол с указанием, что должно быть отображено вместо содержимого по умолчанию.

    ```csharp
    /// <summary>
    /// ...
    /// </summary>
    public partial class TestEmptyControl : BaseListWebControl
    {
        /// <summary>
        /// ...
        /// </summary>
        protected override void OnLoad(EventArgs e)
        {
            var control = new LiteralControl("Для данного списка не предусмотрено данных");
            Controls.Add(control);
            base.OnLoad(e);
        }
    }
    ```

2.Создать списковую страницу, на которой будет указан тип контрола.

    ```csharp
    /// <summary>
    /// ...
    /// </summary>
    public partial class BaseListFrormEmptyControl : BaseListForm<...>
    {
        ...

        /// <summary>
        /// Вызывается самым первым в Page_Load.
        /// </summary>
        protected void Page_Load(object sender, EventArgs e)
        {
            WebObjectListView1.EmptyControlType = typeof(TestEmptyControl);
        }

        ...
     }
    ```

В итоге страница списка будет выглядеть так:

![](/images/pages/products/flexberry-aspnet/controls/wolv/empty-wolv-control.png)

## Загрузка данных

Для того чтобы отключить загрузку данных, необходимо установить свойство `SkipDataLoad`.

Например, если данные в WOLV грузятся долго и при первой загрузке сраницы их предпочтительнее скрыть, но сразу дать возможность пользователю ввести значение фильтра, то можно в `Page_Load` прописать:

```csharp
wolv.SkipDataLoad = !IsPostBack;
```
