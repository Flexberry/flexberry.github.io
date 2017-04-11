---
title: Web-формы подсистемы полномочий
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Flexberry Security
toc: true
permalink: ru/fa_security-forms.html
lang: ru
---

Среди технологических форм присутствуют формы для работы с объектами подсистемы полномочий.

Среди веб-форм на настоящий момент доступны формы для просмотра и редактирования:
* пользователей
* ролей
* групп
* классов
* операций

### Особенности формы для работы с операциями

Для операций различают "Доступ в целом" и "Доступ с конкретным видом доступа".

Первый столбик галочек определяет доступ к операции в целом. Если у пользователя указан конкретный вид доступа, то у пользователя есть доступ в целом (если добавить какой-то вид доступа на операцию и сохранить форму, то автоматически появится галочка в первом столбце).

![](/images/pages/products/flexberry-aspnet/security/operation-form.png)

## Путь до формы

Настройка доступа к данным формам производится через [DynamicPageRoute](fa_routing.html).

Например, чтобы сделать формы доступными по следующим адресам (ниже представлен фрагмент карты сайта):

```xml
<siteMapNode title="Пользователи" url="~/flexberry/SecurityUsersList" />
<siteMapNode title="Роли" url="~/flexberry/SecurityRolesList" />
<siteMapNode title="Классы" url="~/flexberry/SecurityClassesList" />
<siteMapNode title="Группы" url="~/flexberry/SecurityGroupsList" />
<siteMapNode title="Операции" url="~/flexberry/SecurityOperationsList" />
```

можно в RouteConfig прописать следующее:

```csharp
namespace ICSSoft.STORMNET.Web
{
    /// <summary>
    /// Класс конфигурации маршрутов приложения.
    /// </summary>
    public static class RouteConfig
    {
        /// <summary>
        /// Метод для регистрации маршрутов в коллекции.
        /// При изменении адреса страниц не забудьте произвести соответствующие изменения в SiteMap.
        /// </summary>
        /// <param name="routes">Коллекция маршрутов, в которую необходимо добавить новые элементы.</param>
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
