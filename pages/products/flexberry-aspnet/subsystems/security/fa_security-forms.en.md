--- 
title: Web forms subsystem powers 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Security 
toc: true 
permalink: en/fa_security-forms.html 
lang: en 
autotranslated: true 
hash: 97c4de542ad110f72923113af7f31dd1d8d9e55e48f397e47e6c43b048dc9f11 
--- 

Among technological forms, there are forms to work with objects of the subsystem powers. 

Among the web forms at the moment available forms for viewing and editing: 
* user 
* roles 
* groups 
* classes 
* operations 

### shape Features for working with operations 

For operations distinguish between "Access in General and with a specific kind of access." 

The first column of checkboxes defines the access to the operation as a whole. If the user specifies a particular kind of access, then the user has access to in General (if you add some kind of access to the surgery and save the form, you will automatically see a checkmark in the first column). 

![](/images/pages/products/flexberry-aspnet/security/operation-form.png) 

## the Way to form 

Configuring access to data forms is made through [DynamicPageRoute](fa_routing.html). 

For example, to make the forms available at the following addresses (see below for a fragment of the map of the site): 

```xml
<siteMapNode title=Users url="~/flexberry/SecurityUsersList" />
<siteMapNode title=Roles url="~/flexberry/SecurityRolesList" />
<siteMapNode title=Classes url="~/flexberry/SecurityClassesList" />
<siteMapNode title="Group" url="~/flexberry/SecurityGroupsList" />
<siteMapNode title=Operations url="~/flexberry/SecurityOperationsList" />
``` 

in RouteConfig, add the following: 

```csharp
namespace ICSSoft.STORMNET.Web
{
    /// <summary> 
    /// Class configuration of routing applications. 
    /// </summary> 
    public static class RouteConfig
    {
        /// <summary> 
        /// Method to register the routes in the collection. 
        /// If you change the url's don't forget to make relevant changes in SiteMap. 
        /// </summary> 
        /// <param name="routes">the Collection of routes in which you want to add new elements.</param> 
        public static void RegisterRoutes(RouteCollection routes)
        {
			// ... 
			
            routes.AddDynamicPageRoute("flexberry/SecurityClassEdit/{PK}", DynamicPageIdentifier.SecurityClassEdit);
            routes.AddDynamicPageRoute("flexberry/SecurityClassEdit", DynamicPageIdentifier.SecurityClassNew);
            routes.AddDynamicPageRoute("flexberry/SecurityClassesList", DynamicPageIdentifier.SecurityClassesList);
            routes.AddDynamicPageRoute("flexberry/SecurityRoleEdit/{PK}", DynamicPageIdentifier.SecurityRoleEdit);
            routes.AddDynamicPageRoute("flexberry/SecurityRoleEdit", DynamicPageIdentifier.SecurityRoleNew);
            routes.AddDynamicPageRoute("flexberry/SecurityRolesList", DynamicPageIdentifier.SecurityRolesList);
            routes.AddDynamicPageRoute("flexberry/SecurityUserEdit/{PK}", DynamicPageIdentifier.SecurityUserEdit);
            routes.AddDynamicPageRoute("flexberry/SecurityUserEdit", DynamicPageIdentifier.SecurityUserNew);
            routes.AddDynamicPageRoute("flexberry/SecurityUsersList", DynamicPageIdentifier.SecurityUsersList);
            routes.AddDynamicPageRoute("flexberry/SecurityGroupEdit/{PK}", DynamicPageIdentifier.SecurityGroupEdit);
            routes.AddDynamicPageRoute("flexberry/SecurityGroupEdit", DynamicPageIdentifier.SecurityGroupNew);
            routes.AddDynamicPageRoute("flexberry/SecurityGroupsList", DynamicPageIdentifier.SecurityGroupsList);
            routes.AddDynamicPageRoute("flexberry/SecurityOperationEdit/{PK}", DynamicPageIdentifier.SecurityOperationEdit);
            routes.AddDynamicPageRoute("flexberry/SecurityOperationEdit", DynamicPageIdentifier.SecurityOperationNew);
            routes.AddDynamicPageRoute("flexberry/SecurityOperationsList", DynamicPageIdentifier.SecurityOperationsList);
        }
    }
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}