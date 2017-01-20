---
title: Открытие лукап-формы с поддержкой иерархии 
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/look-up-form-hierarchy-w-o-l-v.html
folder: product--folder
lang: ru
---
В данной статье будут описаны два способа, с помощью которых можно вывести данные на лукап-форму в иерархическом виде.


# Использование свойства LookUpFormURL
Контрол `MasterEditorAjaxLookUp` позволяет задавать URL той формы, которая будет отображаться для выбора элемента с путем указания свойства '''`LookUpFormURL`'''.

Прежде чем задавать значение данного свойства, необходимо убедиться, что списковая форма с поддержкой иерархии уже разработана, а также во избежание возможных ошибок указания адреса, для данной списковой формы, на которой данные отображаются в иерархическом виде, необходимо определить свойство '''`FormPath`''', содержащее путь до списковой формы с иерархией.

К примеру, есть списковая форма '''`TestHierarchy.aspx`''', у которой следующим образом задано свойство '''`FormPath`''':
```
      /// <summary>
        /// Путь до формы.
        /// </summary>
        public static string FormPath
        {
            get
            {
                return "~/forms/Controls/WOLV/HierarchyTests/TestHierarchy.aspx";
            }
        }
```
Тогда на той странице, на которой используется лукап, необходимо будет контролу-лукапу задать свойства '''`LookUpFormURL`''' следующим образом (помимо задания других основных необходимых настроек):
```
lookupTest.LookUpFormURL = TestHierarchy.FormPath;
```
# Использование WOLVSettApplyer
Также можно воспользоваться специальным тюнером для WOLV, который позволяет задавать дополнительные настройки для WOLV на прикладном уровне, - '''`WOLVSettApplyer`''', который в свою очередь находится в '''корне''' сборки с ASP.NET приложением.

Однако необходимо проверить, что в конфигурационном файле веб-приложения указан правильный обработчик для лукап-формы, а именно той, которая была сгенерирована. То есть, если имеется сборка TestStand(ASP.NET Application), то правильным указанием обработчика лукап-формы будут следующие:

Для IIS 7:
```
<handlers>
...
<add name="LookUpForm" path="LookUpForm.aspx" verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, TestStand(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" resourceType="Unspecified" preCondition="integratedMode" />
...
</handlers>```
Для IIS 6:
```
<httpHandlers>
...
<add verb="*" path="LookUpForm.aspx" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, TestStand(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" validate="false" />
...
</httpHandlers>```




 


