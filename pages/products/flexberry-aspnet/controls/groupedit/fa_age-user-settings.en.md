--- 
title: Customizing AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_age-user-settings.html 
lang: en 
autotranslated: true 
hash: becdc07285fccb8a02357c8a392d59e56cc62d1c4182f45186acba5182d5deea 
--- 

## adjusting column widths 

Is done [same WebObjectListView](fa_web-object-list-view.html). 

### Saving objects 

[AGE](fa_ajax-group-edit.html) there is an item called `Status` that reflects the edited object or not, i.e., whether the user started something to enter. 
When the user begins to enter anything in line of AGE, the next buttons in the row indicator appears ☀ - asterisk: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/wge-modified.png) 

This is necessary in order to clicking on the save button on the page (not in AGE) it was possible to warn the user that it is not retained in the data AGE. If you are developing your own control and is embedded in AGE, to support this functionality must implement the control interface `IGroupEditCompatible`. 

## edit the list of datalow 

Line detail can be edited with the edit form element: in this window in a new window in a modal window. 
Setting of these operations is done similarly in [WebObjectListView](fa_web-object-list-view.html). 

```csharp
protected override void PostApplyToControls()
{
    // In a new or modal window. 
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInModalWindow = false(true);
    ctrlКвартира.Operations.OpenEditorInNewWindow = true(false);
            
    // In the current window. 
    // Forming the URL of the edit form of detail with ReturnUrl to return to this (current) form. 
    ctrlКвартира.DetailEditForm = FormUrlHelper.UpdateParam(
        WebParamController.ReturnURL,
        FormUrlHelper.RedirectEditFormUrl(Page.ResolveUrl(FormPath), DataObject.__PrimaryKey.ToString()),

        // Kvartira - form editing of detail. 
        КвартираE.FormPath);
}
``` 

## Features open edit form in a separate tab in the AGE 

If the edit form opens in a separate tab (OpenEditorInNewWindow=1, OpenEditorInModalWindow=0), detail you can edit 
and on the tab with AGE, and on the edit page. To exclude parallel editing, for example, a row lock in the AGE while is open another tab. 

## Change view depending on the state of the aggregator 

Sometimes you want to display representations of detail with different settings for the same aggregator are in different States. For example, if you marked/unmarked checkbox. 

For example, there is a data model: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/model-agregator.png) 

Detail there are two views with different settings: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/two-view-detail.png) 

In the chart wizard there is only one representation of detail. Therefore, to change views, you must configure the application code to the edit form. 
It will look as follows: 

```csharp
/// <summary> 
/// It is best to change the properties of controls on the page that are not handled WebBinder. 
/// </summary> 
protected override void PostApplyToControls()
{
      //If not marked as "Active" checkbox, and 
      //displays the first view of detail (1,3,5 properties). 
            
      if (ctrlАктивно.Checked == false)
      {
          ctrlSeveralViewsDetail.DetailViewName = SeveralViewsDetail.Views.SeveralViewsDetailD1.Name;
      }
      //If the checkbox is checked, then display the second view (properties 2,4,6). 
      else
      {
          ctrlSeveralViewsDetail.DetailViewName = SeveralViewsDetail.Views.SeveralViewsDetailD2.Name;
      }

      Page.Validate();
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}