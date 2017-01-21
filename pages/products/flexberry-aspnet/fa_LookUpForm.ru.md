---
title: Создание своей LookUp-страницы в Web-приложении.
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_look-up-form.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Web-контролы и web-компоненты](web-controls.html)
* '''Предназначение''': создание собственной LookUp-страницы.
</td>
</tr></tbody></table></a>
</div>


# Механизм взаимодействия
Можно посмотреть [здесь](form-interaction-web.html).

# Создание собственной страницы
Для создания собственной LookUp-страницы, необходимо:

# Создать новую web-страницу.
# Добавить на неё [WebObjectListView](web-object-list-view.html) с `ID = "LookUpFormWOLV"`.
# При загрузке формы
#* Принять передаваемые параметры.
#* Настроить [WOLV](web-object-list-view.html)
#* Подключить скрипты
# Настроить свойство `LookUpFormURL` у [LookUp'a](look-up--overview.html).

## Передаваемые параметры
На LookUp-страницу передается ряд параметров для настройки WOLV'a и страницы:

* `ViewName` - имя представления для [WOLV](web-object-list-view.html)
* `typeName` - наименование типа данных, отображаемого в [WOLV](web-object-list-view.html)
* `connStrName` - строка подключения к базе (опционально)
* `nameValueControl` - параметры лукапа
* `PK` - PrimaryKey выбранного мастера (если таковой имеется)
* `FormCaption` - заголовок страницы (опционально)
* `csdName` - наименование Column Sort Definition - определения сортировки [WOLV](web-object-list-view.html), которая хранится в сессии
* `editPage` - адрес страницы редактирования объектов [WOLV](web-object-list-view.html)
* `countOnPage` - количество объектов на одной странице [WOLV](web-object-list-view.html)
* `LFName` - наименование Limit Function для ограничения выгрузки данных [WOLV](web-object-list-view.html). Limit Functon хранится в сессии.

## Настройка WOLV
Необходимо перебросить значения, переданные в качестве параметров, а также провести некоторую дополнительную настройку:

```
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
applyer.SettingsApply(LookUpFormWOLV);```
Также необходимо проверить наличие необязательных параметров и применить их:

PrimaryKey:
```
if (!string.IsNullOrEmpty(Request["PK"]))
            {
                LookUpFormWOLV.SetInitialSearch("__PrimaryKey", Request["PK"]);
            }```

ColumnSortDef:
```
if (!string.IsNullOrEmpty(Request["csdName"]))
            {
                var columnSortDef = (ColumnsSortDef[])HttpContext.Current.Session[Request["csdName"]];
                LookUpFormWOLV.InitialColumnsSort = columnSortDef;
            }```

(((<msg type=note>Перед вызовом на [LookUp](look-up--overview.html) ColumnSortDefinition помещается в сессию под ключом, передаваемым в качестве параметра, и достается из сессии по этому ключу.</msg>)))

Ну и конечно же LimitFunction:
```
string lfName = HttpContext.Current.Request["LFName"];
if (!string.IsNullOrEmpty(lfName))
SQLWhereLanguageDef lng = SQLWhereLanguageDef.LanguageDef;
Function lf1 = LimitFunctionsHolder.LoadLimitFunction(lfName);
LookUpFormWOLV.LimitFunction = LookUpFormWOLV.LimitFunction != null
              ? lng.GetFunction(lng.funcAND, LookUpFormWOLV.LimitFunction, lf1)
              : lf1; ```

(((<msg type=note>Желательно обернуть вызов метода `LoadLimitFunction` в блок `try-catch`.</msg>)))

## Подключение скриптов
Для функционирования WOLV необходимо подключить следующие скрипты:

```
ResourcesPaths.Add("Scripts.ListView.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-1.7.2.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-ui-1.8.17.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.color.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.tooltip.js");
```

# Полный код метода OnLoad
```
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
                    SQLWhereLanguageDef lng = SQLWhereLanguageDef.LanguageDef;
                    Function lf1 = LimitFunctionsHolder.LoadLimitFunction(lfName);
                    LookUpFormWOLV.LimitFunction = LookUpFormWOLV.LimitFunction != null
                                                       ? lng.GetFunction(lng.funcAND, LookUpFormWOLV.LimitFunction, lf1)
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

# Передача параметров на LookUp-форму
Передача параметров на LookUp-форму описана в '''[этой статье](look-up-form-send-params.html)'''.

