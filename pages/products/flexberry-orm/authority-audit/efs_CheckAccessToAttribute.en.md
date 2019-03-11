--- 
title: Limitation of rights to attributes 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security 
toc: true 
permalink: en/efs_check-access-to-attribute.html 
lang: en 
autotranslated: true 
hash: 1018e40e20836295016b5538417f8a1947e94bf8d0956a07990d8a919063f53d 
--- 

# Rights attributes 
Flexberry Platform supports setting the properties of the data objects (attribute). Setting this right is made through specifying a custom expression in the attribute DataServiceExpression. The expression looks like the following: 

```cs
/*Operation:ИмяОперации;DeniedAccessValue:ЗначениеАтрибутаПриОтсутствииПрав*/
``` 


The presence of the user to the attribute determined by the rights specified in the expression of the operation (Operation section). In the absence of rights to property operation will have the meaning specified in section DeniedAccessValue. If expression is not specified, the value of the object is processed in the usual way. Setting of the rights to workmen properties is specific (see relevant section). 


# Order of installation of control rights for attributes 
1. The console powers it is necessary to create operations and to configure user access to them. 

2. In Flexberry Tool to add or edit DataServiceExpression, adding expression rights settings for the attributes. 
The value DeniedAccessValue should be recorded as it bolino to be recorded in the SQL query. In the example string means in single quotes. If DeniedAccessValue is already written next to it, for SQL server this review. 

3. When you run the application ustanoviti property RightManager.UseRightsOnAttribute = true. In a web application this can be done in the Global.Application_Start. 

4. If the custom property is computable and has a code to get'EP it is necessary that this code also took into account the rights to the attribute. If DataServiceExpression with restricted rights was added via Flexberry Tool, but the necessary code in get'EP will be added automatically. 

```cs
object deniedAccessValue;
if (!Information.CheckAccessToAttribute(this.GetType(), "Name", out deniedAccessValue))
return (string)deniedAccessValue;
``` 


# artisans svoistvami Rights to configure rights for the attributes which are references to the master, as the value DeniedAccessValue need to specify the key master who will be assigned to the property in the absence of rights. For such purposes it is recommended to have a special dummy master. 
```cs
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.MSSQLDataService), "/*Operation:ПросмотрАтрибутов;DeniedAccessvalue:'00000000-0000-0000-0000-000000000000'*/")]
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}