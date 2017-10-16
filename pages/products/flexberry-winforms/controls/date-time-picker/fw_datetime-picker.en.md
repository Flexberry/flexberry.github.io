---
title: DateTimePicker
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: en/fw_datetime-picker.html
folder: products/flexberry-winforms/
lang: en
---

<!-- Данная статья ещё редактируется -->

Для того чтобы настроить корректное отображение даты необходимо создать свой [ControlProvider](fw_control-provider-winforms.html) для даты.

```csharp
/// <summary>
/// ControlProvider для NullableDateTime.
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

В созданном классе можно задать все необходимые свойства даты. При этом данные настройки будут применимы ко всем независимым формам, имеющим дату.
