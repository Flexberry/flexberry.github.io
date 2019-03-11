--- 
title: active Backlight control 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, HighLighter 
summary: the Ad, the setting for one or more forms, an example of using a HighLighter 
toc: true 
permalink: en/fw_high-lighter.html 
lang: en 
autotranslated: true 
hash: a5ec02ed4a5bf2cddaff5b4636dfeb083de136d6929451ac5ccfdb4af201d664 
--- 

`HighLighter` component that provides illumination of the active control on the form. 

## Ad 

To use `HighLighter` in the project must be a reference to the Assembly `ICSSoft.STORMNET.Windows.Forms.AdditionalControls` 

In [brackets programmer](fo_programmer-brackets.html) `CustomMembers` to place the code: 

```csharp
// Declare HighLighter'and HL 
ICSSoft.STORMNET.Windows.Forms.HighLighter HL;
``` 

In the constructor of the dependent form: 

```csharp  
// Create object 
HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(this);

// Set the illumination color 
HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
``` 

In the designer, independent shape: 

```csharp  
// Create object 
HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(form);

// Set the illumination color 
HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
``` 

You can also set the backlight to several forms at once. This requires the following: 

* At the beginning of the application to subscribe to the load event of the form by `Desktop.GlobalWinformEvents.Load`. 
* To define the list `changingFormTypes`, which records types of forms for which you want to organize the lighting. 
* In the handler, turn on the backlight. 

The setting is done in the application. 

Example 

```csharp
 [AccessType(ICSSoft.STORMNET.AccessType.none)]
 public class TestStandWinformsDesktop : ICSSoft.STORMNET.Windows.Forms.Desktop
    {
        
        ...
        
        // *** Start programmer edit section *** (TestStandWinformsDesktop CustomMembers) 
        /// <summary> 
        /// The list of forms that are configured for illumination and the transition to Enter. 
        /// </summary> 
        private static List<Type> changingFormTypes = new List<Type>()
            {
                typeof(IIS.TestStandWinforms.WinformДомHighLighter),
                
            };

        /// <summary> 
        /// Handler for the load event of the form. 
        /// Subscribes the necessary forms on the backlight. 
        /// </summary> 
        /// <param name="sender">Originator of the event form.</param> 
        /// <param name="e">event Parameters.</param> 
        static void GlobalWinformEvents_Load(object sender, EventArgs e)
        {
            if (changingFormTypes.Contains(sender.GetType()))
            {
                Form currentForm = (Form)sender;
                ICSSoft.STORMNET.Windows.Forms.HighLighter HL;
                // Create object 
                HL = new ICSSoft.STORMNET.Windows.Forms.HighLighter(currentForm);
                HL.SubscribeHighliterManually();

                // Set the illumination color 
                HL.HighlightColor = System.Drawing.Color.FromArgb(150, 255, 150);
            }
        }

        // *** End programmer edit section *** (TestStandWinformsDesktop CustomMembers) 

        ...

 [STAThread()]
 static void Main()
 {
    try
    {
         // *** Start programmer edit section *** (TestStandWinforms Before authorization) 
         // Subscribe to the download form. 
         Desktop.GlobalWinformEvents.Load -= GlobalWinformEvents_Load;
         Desktop.GlobalWinformEvents.Load += GlobalWinformEvents_Load;

         ...
         // *** End programmer edit section *** (TestStandWinforms Before authorization) 
     ...
    }
 }
``` 

`HighLighter` subscribes to the events `OnGotFocus` and `OnLostFocus` form objects and change their property `BackColor` on your back and when it is triggered. 

## Result 

Comparison of one and the same shape with the included `Highlighter` and without: 

| Highlighter'without a | With Highlighter'om | 
|--|--| 
| ![](/images/pages/products/flexberry-winforms/controls/highlighter/highlighter-off.gif)|![](/images/pages/products/flexberry-winforms/controls/highlighter/highlighter-on.gif)| 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}