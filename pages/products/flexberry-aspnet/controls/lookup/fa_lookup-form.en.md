---
title: creating a LookUp page in your Web application
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_lookup-form.html
lang: en
autotranslated: true
hash: e7518742168f54d7c71f19599621e09a830b12b4d81367b74f269450f7ad5075
---

## The mechanism of interaction

You can see the article [Performanoe interaction in Web-applications](fa_form-interaction.html).

## Create page

To create a LookUp page should:

1. Create a new web page.
2. Adding [WebObjectListView](fa_web-object-list-view.html) with `ID = "LookUpFormWOLV"`.
3. When the form is loaded
* To accept passed parameters.
* Set up [WOLV](fa_web-object-list-view.html)
* Connect scripts
4. Configure the property `LookUpFormURL` from [LookUp'a](fa_lookup-overview.html).

### Passed parameters

On the LookUp page is transmitted a number of options for configuring WOLV'a and pages:

* `ViewName` - the name of the view to [WOLV](fa_web-object-list-view.html)
* `typeName` - the name of the data type displayed in [WOLV](fa_web-object-list-view.html)
* `connStrName` - the connection string to the database (optional)
* `nameValueControl` - parameters lucapa
* `PK` - the PrimaryKey of the selected master (if any)
* `FormCaption` - title (optional)
* `csdName` - name Column-Definition Sort - definition sort [WOLV](fa_web-object-list-view.html), which is stored in the session
* `editPage` - address edit page objects [WOLV](fa_web-object-list-view.html)
* `countOnPage` - number of objects on one page [WOLV](fa_web-object-list-view.html)
* `LFName` - name Limit Function to limit the discharge data [WOLV](fa_web-object-list-view.html). Limit Functon is stored in the session.

### Setting WOLV

You need to throw the values passed in as parameters, and to make some additional configuration:

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

You also need to check for optional parameters and apply them:

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

{% include note.html content="Before the call to [LookUp](fa_lookup-overview.html) ColumnSortDefinition placed into the session under the key passed as parameter, and gets out session for this key." %}

And of course `LimitFunction`:

```csharp
string lfName = HttpContext.Current.Request["LFName"];
if (!string.IsNullOrEmpty(lfName))
Function lf1 = LimitFunctionsHolder.LoadLimitFunction(lfName);
LookUpFormWOLV.LimitFunction = LookUpFormWOLV.LimitFunction != null
              ? FunctionBuilder.BuildAnd(LookUpFormWOLV.LimitFunction, lf1)
              : lf1;
```

{% include note.html content="it is Advisable to wrap the method call in the block `LoadLimitFunction` `try-catch`." %}

### Connection scripts

For the functioning of the WOLV, you need to connect the following scripts:

```csharp
ResourcesPaths.Add("Scripts.ListView.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-1.7.2.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery-ui-1.8.17.min.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.color.js");
ContextHelper.ПодключитьВнешнийФайл("/shared/script/jquery.tooltip.js");
```

## The complete code for the OnLoad method

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
                        "An error occurred while receiving data." + "\\n"
                        + "Maybe it's been a long time since the last action." + "\\n"
                        + "Refresh the page with which the operation was performed on selection of values.");

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

## Passing parameters on the LookUp form

Passing parameters on the LookUp form described in [article](fa_lookup-form-send-params.html).




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}