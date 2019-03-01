--- 
title: Defining editable fields for one form, but different applications 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Presents solutions to the problem of accessibility of the different fields for the same forms in different applications, detailed example with step by step explanation 
toc: true 
permalink: en/fw_different-applications-and-fields.html 
lang: en 
autotranslated: true 
hash: 7d543b9d01f55f81d605b06b270f3738f6a8737125dce53e95d583ae5a6bcf45 
--- 

We set the following goal: "to Make different applications available to different fields on the same form." 
Perhaps another solution to this problem: 

* Depending on what apps you have running, you can lock a field for editing with [EditManager.SetReadonlyFlagProperties](fw_editmanager.html). 
* Identify a running application at startup by writing a specific value in a [static field](http://msdn.microsoft.com/library/98f28cdx.aspx) public class. 

To demonstrate the solution to this problem below is the solution of the following problem: "There is a list of employees. HR staff can edit the fields "name", "date of birth" and "Address registration", and the heads of enterprises to evaluate the "Performance" employees." 

## Work in Flexberry Tool 

In Flexberry was created the class diagram. 

![Class diagram](/images/pages/products/flexberry-winforms/desktop/class-diagram_-workers.jpg) 

And then defines user applications and the generated code. 

## Work with program code 

In public the applications you develop the class defined by the static field. 

```csharp
    public enum tWorkerShowType //an enum type to specify the running application 
    {
        Unknown, //it is not installed 
        ToHead, //head 
        ToPersonnelOffice //the personnel Department 
    }
    public class Сотрудник : ICSSoft.STORMNET.DataObject
    {
        public static WorkerShowType CurShowType = tWorkerShowType.Unknown; //static box to specify the running application 
        //... 
    }
``` 

In the application code determine the value of this static field. 

```csharp
static void Main()
{
    try
    {
        Сотрудник.CurShowType = tWorkerShowType.ToPersonnelOffice; //define the value of a static field 
        //... 
    }
    //... 
}
``` 

An overridable method [`Edit`](fw_form-interaction.html) on the edit form. 

```csharp
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
  base.Edit(dataobject, contpath, propertyname, tag); //call the base method 
  if (dataobject != null)
    {
      switch (Сотрудник.CurShowType)
        {
          case tWorkerShowType.ToHead: //if running the Manager application 
            EditManager.SetReadonlyFlagProperties(
            true, new string[] { "Name", "Dataromance", "Adresboek" });
            break;
          case tWorkerShowType.ToPersonnelOffice: //if the application is running the personnel Department. 
            EditManager.SetReadonlyFlagProperties(true, new string[] { "Performance" });
            break;
          case tWorkerShowType.Unknown: //if not specified the type of application 
            MessageBox.Show("You have not set the parameter, whose name was running form.");
            break;
        }
    }
}
``` 

{% include note.html content="Call `base.Edit(...)` must precede the definition of the fields available for editing." %} 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/