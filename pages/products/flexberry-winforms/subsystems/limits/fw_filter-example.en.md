--- 
title: Example usage AdvLimit and create FilterSettings 
sidebar: flexberry-winforms_sidebar 
keywords: Constraints 
summary: Description of the form specification of user constraints, and an indication of how to configure it to allow you to impose restrictions on detalam 
toc: true 
permalink: en/fw_filter-example.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: eb8d746482d90cb27d2439417da503b0bfcf367b9b42e32fa4f304a7e49c8152 
--- 

## Form edit user constraints (AdvLimit) 

In the generated application forms there is the possibility of imposing user restrictions on the lists. User restrictions allow you to configure rules according to which the filtered objects in the list. Consider the following example: 

"Note: it is assumed that the application already generated" 

### class Diagram 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filter-ex-diagram.png) 

### Form set custom restrictions 

Suppose we have a list of 5 clients that filled in the following way, and we want to select all customers, registered in Perm: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filter-ex-list1.png) 

To do this, you must open the edit form of custom constraints, and to introduce a corresponding limitation on the field `Прописка`. Check very primitive - check the string for the presence of the substring "Perm". 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filter-ex-list2.png) 

Once the limit is filled, you must click "`Сохранить and применить`" after returning the form you need to update the list so that it applied a restriction. 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filter-ex-list3.png) 

As you can see, the number of Customers was reduced to 3 people: only those who have in the field `Прописка` was a substring of Perm. 

To return the list to its original state, you must reset the current limit by clicking the "`Сбросить ограничения`" 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filter-ex-list4.png) 


## Example settings FilterSettings 

FilterSettings allow you to customize the available to overlay user restrictions field of the object. Consider this example: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex0.png) 

Here is the setup form list of Credits. Suppose we want to filter the list and bring those loans that had at least one repayment in the amount exceeding 100 000. 

Open the form to create custom constraints: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex1.png) 

In the tree fields of the object the Credit is all his masters, and a private field, but there was none of detailov. So it appeared to detaily, you must create a FilterSetting through AdmConsole. 

1. Download AdmConsole 
2. Rule `AdmConsole.exe.cofig`, need to specify the path to the database of our application in key CustomizationString 

```
 <add key="CustomizationStrings" value="SERVER=localhost;Trusted_connection=yes;DATABASE=credits;" /> 
``` 

3. When you first launch the app, select the folder Assembly cache, you can specify the path to the folder where the project is going, but it is preferable to create a separate folder. 
4. The folder cache must be copied: 
1. DLL with objects `<Projectname>(Objects).dll` 
2. DLL with Forms `<Projectname>(Forms).dll` 
3. IIS.WinUI.AdvancedFSCtrl.dll - located in the folder AdmConsole.exe 
5. Open `Специальные forms настройки` -> `Настройки фильтров` 
6. Create a new configuration by clicking on the button `Создать`, will open the creation form settings. 
7. On the form: ![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex2.png) 
1. We introduce `Наименование` settings 
2. Choose build from the drop down list 
3. Choose the type `Кредит` 
4. Choose view `КредитL` 
![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex3.png) 
6. Go to the tab `Детейлы` and click `Заполнить`. 
"__Note__: if you press the button `Заполнить` the message "No changes of datalow", you must save the configuration and try again" 
8. Save the setup and exit AdmConsole 

Now you need to configure it to do so in list form `Кредитов` needs to be applied this setting filters. Options: 
* [A setting in VS designer](fw_filter-settings.html) 
* [Setting code](fw_filter-settings.html). "__Note__: if you can not open the project in Visual Studio, but want to still use this setting, you must do the following: 
* Open the folder with the projects forms 
* Find the file КредитL.cs and open it with any text editor (e.g. Notepad) 
* Find a method GetDpdForm() 
* Record in [brackets programmer](fd_change-model.html) `КредитL.GetDpdForm() end` the following line of code: 

```csharp 
form.FilterSettingName = "Loans"; 
``` 

* Save the file" 

Now after assembling the apps come in the form of loans and open the form to create custom constraints: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex4.png) 

As you can see, there is a possibility of imposing restrictions on detail Loan. Create a condition that filters all loans with maturity of more than 100,000. Press `Сохранить and применить` and update the list on the form, you will receive: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings-ex5.png) 







{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}