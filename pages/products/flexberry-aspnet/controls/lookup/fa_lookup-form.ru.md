---
title: Создание LookUp-страницы в Web-приложении
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_lookup-form.html
lang: ru
---

## Механизм взаимодействия

Можно посмотреть в статье [Межформенное взаимодействие в Web-приложениях](fa_form-interaction.html).

## Создание страницы

Для создания LookUp-страницы необходимо:

1. Создать новую web-страницу.
2. Добавить на неё [WebObjectListView](fa_web-object-list-view.html) с `ID = "LookUpFormWOLV"`.
3. При загрузке формы
    * Принять передаваемые параметры.
    * Настроить [WOLV](fa_web-object-list-view.html)
    * Подключить скрипты
4. Настроить свойство `LookUpFormURL` у [LookUp'a](fa_lookup-overview.html).

### Передаваемые параметры

На LookUp-страницу передается ряд параметров для настройки WOLV'a и страницы:

* `ViewName` - имя представления для [WOLV](fa_web-object-list-view.html)
* `typeName` - наименование типа данных, отображаемого в [WOLV](fa_web-object-list-view.html)
* `connStrName` - строка подключения к базе (опционально)
* `nameValueControl` - параметры лукапа
* `PK` - PrimaryKey выбранного мастера (если таковой имеется)
* `FormCaption` - заголовок страницы (опционально)
* `csdName` - наименование Column Sort Definition - определения сортировки [WOLV](fa_web-object-list-view.html), которая хранится в сессии
* `editPage` - адрес страницы редактирования объектов [WOLV](fa_web-object-list-view.html)
* `countOnPage` - количество объектов на одной странице [WOLV](fa_web-object-list-view.html)
* `LFName` - наименование Limit Function для ограничения выгрузки данных [WOLV](fa_web-object-list-view.html). Limit Functon хранится в сессии.

### Настройка WOLV

Необходимо перебросить значения, переданные в качестве параметров, а также провести некоторую дополнительную настройку:

```csharp
LookUpFormWOLV.View = Information.GetView(Request["viewName"], Type.GetType(Request["typeName"]));
LookUpFormWOLV.LookUp = true;
LookUpFormWOLV.LookUpParam = Request["nameValueControl"];
LookUpFormWOLV.ConnStrinName = Request["connStrName"];
LookUpFormWOLV.Operations.Edit = false;
LookUpFormWOLV.Operations.Delete = false;
LookUpFormWOLV.Operations.New = false;
LookUpFormWOLV.EditPage = Request["editPage"];
LookUpFormWOLV.Operations.ShowMarks = false; 

var applyer = new WolvSettApplyer();
applyer.SettingsApply(LookUpFormWOLV);
```

Также необходимо проверить наличие необязательных параметров и применить их:

`PrimaryKey`:

```csharp
if (!string.IsNullOrEmpty(Request["PK"]))
            {
                LookUpFormWOLV.SetInitialSearch("__PrimaryKey", Request["PK"]);
            }
```

`ColumnSortDef`:

```csharp
if (!string.IsNullOrEmpty(Request["csdName"]))
            {
                var columnSortDef = (ColumnsSortDef[])HttpContext.Current.Session[Request["csdName"]];
                LookUpFormWOLV.InitialColumnsSort = columnSortDef;
            }
```

{% include note.html content="Перед вызовом на [LookUp](fa_lookup-overview.html) ColumnSortDefinition помещается в сессию под ключом, передаваемым в качестве параметра, и достается из сессии по этому ключу." %}

Ну и конечно же `LimitFunction`:

```csharp
string lfName = HttpContext.Current.Request["LFName"];
if (!string.IsNullOrEmpty(lfName))
Function lf1 = LimitFunctionsHolder.LoadLimitFunction(lfName);
LookUpFormWOLV.LimitFunction = LookUpFormWOLV.LimitFunction != null
              ? FunctionBuilder.BuildAnd(LookUpFormWOLV.LimitFunction, lf1)
              : lf1;
```

{% include note.html content="Желательно обернуть вызов метода `LoadLimitFunction` в блок `try-catch`." %}

### Подключение скриптов

Для функционирования WOLV необходимо подключить следующие скрипты:

```csharp
ResourcesPaths.Add("Scripts.ListView.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-1.7.2.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-ui-1.8.17.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.color.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.tooltip.js");
```

## Полный код метода OnLoad

```csharp
protected override void OnLoad(EventArgs e)
        {
            ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-1.7.2.min.js");
            ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-ui-1.8.17.min.js");
            ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.color.js");
            ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.tooltip.js");

            string viewName = Request["viewName"];
            string typeName = Request["typeName"];
            string connStrName = Request["connStrName"];
            string nameValueControl = Request["nameValueControl"];
            string selectPK = Request["PK"];
            string formCaption = Request["FormCaption"];
            string columnSortDefName = Request["csdName"];
            string editPage = Request["editPage"];
            string countOnPage = Request["CountOnPage"];

            Title = formCaption;
            LookUpFormCaption.InnerText = formCaption;

            if (viewName #  null || typeName  null || Type.GetType(typeName) == null)
                return;

            string lfName = HttpContext.Current.Request["LFName"];
            if (!String.IsNullOrEmpty(lfName))
            {
                try
                {
                    Function lf1 = LimitFunctionsHolder.LoadLimitFunction(lfName);
                    LookUpFormWOLV.LimitFunction = LookUpFormWOLV.LimitFunction != null
                                                       ? FunctionBuilder.BuildAnd(LookUpFormWOLV.LimitFunction, lf1)
                                                       : lf1;
                }
                catch (LimitFunctionNotFoundException)
                {
                    WebMessageBox.Show(
                        "Произошла ошибка при получении данных." + "\\n"
                        + "Возможно, прошло много времени с момента последних действий." + "\\n"
                        + "Обновите страницу, с которой была выполнена операция выбора значений.");

                    LookUpFormWOLV.SkipDataLoad = true;
                }
                catch (Exception ex)
                {
                    LogService.Log.Error("LookUpForm_PageLoad", ex);
                }
            }

            if (!string.IsNullOrEmpty(columnSortDefName))
            {
                var columnSortDef = (ColumnsSortDef[])HttpContext.Current.Session[columnSortDefName];
                LookUpFormWOLV.InitialColumnsSort = columnSortDef;
            }

            if (!String.IsNullOrEmpty(selectPK))
            {
                LookUpFormWOLV.SetInitialSearch("__PrimaryKey", selectPK);
            }

            LookUpFormWOLV.View = Information.GetView(viewName, Type.GetType(typeName));
            LookUpFormWOLV.LookUp = true;
            LookUpFormWOLV.LookUpParam = nameValueControl;
            LookUpFormWOLV.ConnStrinName = connStrName;
            LookUpFormWOLV.Operations.Edit = false;
            LookUpFormWOLV.Operations.Delete = false;
            LookUpFormWOLV.Operations.New = false;
            LookUpFormWOLV.EditPage = editPage;
            LookUpFormWOLV.Operations.ShowMarks = false;

            if (!IsPostBack)
            {
                int intCountOnPage;
                if (int.TryParse(countOnPage, out intCountOnPage))
                {
                    LookUpFormWOLV.ResultsPerPageProperty = intCountOnPage;
                }
            }

            var applyer = new WolvSettApplyer();
            applyer.SettingsApply(LookUpFormWOLV);

            base.OnLoad(e);
        }
```

## Передача параметров на LookUp-форму

Передача параметров на LookUp-форму описана в [статье](fa_lookup-form-send-params.html).

