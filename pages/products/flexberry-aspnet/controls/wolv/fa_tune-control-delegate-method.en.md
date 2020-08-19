--- 
title: Configuring a control filter in WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: 
toc: true 
permalink: en/fa_tune-control-delegate-method.html 
lang: en 
autotranslated: true 
hash: cfd12f2b46997c97780084ddf12bc385524efd2d163d18686d3decca5bd4474c 
--- 

## Delegate change control filtering 

In Flexberry ASP.NET there is a delegate change control [filter](fa_wolv-filters.html) (at the moment the modification is available for the [DatePicker](fa_date-picker.html)) [WebObjectListView](fa_web-object-list-view.html). 

How does the delegate: a filtering module creates the controls for filtering by [WebControlProvider](fa_web-control-provider.html), where the delegate is invoked. 

```csharp
namespace ICSSoft.STORMNET.Web.Tools
{
    public class WebControlProvider
    {
        /// <summary> 
        /// Delegate that allows you to adjust the control after it is created. 
        /// </summary> 
        public TuneControlDelegate TuneControlDelegateMethod;
		
		...
    }
}
``` 

## CreatedControlData 

`CreatedControlData` is a special structure, reporting to the delegate, how and what was created by the control: 

```csharp
namespace ICSSoft.STORMNET.Web.Tools
{
    public class CreatedControlData
    {
		...
        /// <summary> 
        /// List of options the reasons for the creation of controls. 
        /// </summary> 
        public enum CreateControlReason
        {
            /// <summary> 
            /// This control for filtering in WebObjectListView. 
            /// </summary> 
            Filter
        }

        /// <summary> 
        /// The reason for creating control. 
        /// </summary> 
        public CreateControlReason ControlCreationReason { get; private set; }

        /// <summary> 
        /// The property type for which you created the control. 
        /// </summary> 
        public Type PropertyType { get; private set; }

        /// <summary> 
        /// The name of the property for which control is created. 
        /// </summary> 
        public string PropertyName { get; private set; }

        /// <summary> 
        /// Type containing the property, for which the control is created. 
        /// </summary> 
        public Type DataObjectType { get; private set; }
    }
}
``` 

## Example 

A delegate can be defined as follows: 

```csharp
/// <summary> 
/// Event handler for the initialization is complete. 
/// </summary> 
/// <param name="e">event Parameters.</param> 
protected override void OnInitComplete(EventArgs e)
{
	base.OnInitComplete(e);
	WebObjectListView1.WebControlProvider.TuneControlDelegateMethod = ChangeControlDelegateMethod;
}

/// <summary> 
/// The delegate that will change tuning controls for display of dates in the filter string. 
/// </summary> 
/// <param name="control">the control.</param> 
/// <param name="changeControlDelegateData">parameters to create the control (which was created).</param> 
private void ChangeControlDelegateMethod(Control control, CreatedControlData changeControlDelegateData)  
{  
	if (control is DatePicker  
  	    && changeControlDelegateData.ControlCreationReason  
            == CreatedControlData.CreateControlReason.Filter  
            && changeControlDelegateData.DataObjectType == typeof(TestChangeControlDelegate)  
            && changeControlDelegateData.PropertyName  
            == Information.ExtractPropertyPath<TestChangeControlDelegate>(x => x.DateTimePropertyWithDelegate))  
        {  
        	var datePicker = (DatePicker)control;  
        	datePicker.OnlyDate = false;  
        	datePicker.DateFormat = "This field cannot be filtered.";  
        	datePicker.TimeFormat = string.Empty;  
        }  
}  
``` 

In the filter control, the corresponding property will be available `DateTimePropertyWithDelegate` specifies the date and in the filter will be displayed "this field cannot be filtered". 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}