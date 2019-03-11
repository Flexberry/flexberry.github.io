--- 
title: DateTimePicker 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, date and time 
summary: Description and example of creating ControlProvider for NullableDateTime 
toc: true 
permalink: en/fw_datetime-picker.html 
lang: en 
autotranslated: true 
hash: ad5c518c845683fc125f385f90c5524f7da318e4b098c129b3b32ec08289516a 
--- 

To set the correct date display, you must create your [ControlProvider](fw_control-provider-winforms.html) to date. 

```csharp
/// <summary> 
/// ControlProvider to NullableDateTime. 
/// </summary> 
public class ControlProviderNullable : ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider
{
   ///... 
   public override ControlForBindStruct GetControl(string ApplicationType, Type type, View view, string propertyName, object initControl)
   {
       if (type #  typeof(DateTime?) || type  typeof(DateTime))
          {
             ICSSoft.STORMNET.Windows.Forms.DateTimePicker picker = new ICSSoft.STORMNET.Windows.Forms.DateTimePicker();
             picker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
             picker.CustomFormat = "dd.MM.yyyy hh:mm";
             picker.OnlyDate = false;
             picker.UseNullableValues = true;
             return new ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct(picker, "ObjectValue", new System.Type[] { typeof(ICSSoft.STORMNET.UserDataTypes.NullableDateTime), typeof(System.DateTime) });
          }
            return new ControlForBindStruct();
    }
}
``` 

In the generated class, you can specify all properties required date. The data settings will apply to all independent forms that have date. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}