---
title: NuGet-пакеты Flexberry
sidebar: flexberry-platform_sidebar
keywords: NuGet, библиотеки, продукты
summary: Перечень библиотек и их зависимостей для продуктов платформы Flexberry
toc: true
permalink: ru/flexberry-nuget-packages.html
lang: ru
---

Отдельные компоненты технологии проектирования и программирования Flexberry распространяются через пакетный менеджер [NuGet](https://nuget.org).

Часть пакетов располагается в открытом публичном доступе в галерее [NuGet.org](https://www.nuget.org/packages?q=Flexberry).

Для ведения версий пакетов применяется принцип [семантического версионирования](http://semver.org/lang/ru/).

## Flexberry ORM

Пакет содержит компоненты для объектно-реляционного отображения.

Сборки, входящие в пакет описаны в статье [Библиотеки Flexberry ORM](fo_flexberry-orm-libraries.html).

### Пакеты для ODataService

* Flexberry ORM ODataService - пакет содержит компоненты для создания OData-сервиса.

Сборки, входящие в пакет описаны в статье [Flexberry ORM ODataService](fo_orm-odata-service.html).

* Flexberry ORM MongoDbDataService

### Пакеты для настройки взаимодействия Flexberry ORM и Flexberry GIS

* Flexberry ORM GisMSSQLDataService
* Flexberry ORM GisPostgresDataService

## Flexberry ASP.NET

Сборки, входящие в пакет [Flexberry ASP.NET](fa_flexberry-asp-net.html):

* CacheBaseLibrary.dll
* ClientLibrary.dll
* CustomSiteMapProvider.dll
* ExpressionBuilder.dll
* HttpCompress.dll
* ICSSoft.STORMNET.Business.BFDataService.dll
* ICSSoft.STORMNET.Business.HttpDataService.dll
* ICSSoft.STORMNET.EBSI.dll
* ICSSoft.STORMNET.LockServices.dll
* ICSSoft.STORMNET.Web.AjaxControls.dll
* ICSSoft.STORMNET.Web.Tools.dll
* IIS.ExpressionSerialization.dll
* IIS.Flex.CaseberryInteraction.dll
* IIS.Validation.dll
* IIS.Аудит(Objects).dll
* ImageResizerKernel.dll
* NewPlatform.Flexberry.Web.Http.dll
* NewPlatform.Flexberry.Web.LimitEditor.dll
* OfficeWebUI.dll
* SmartScroller.dll

### Пакеты для настройки стилей приложений

* Flexberry ASP.NET BaseTheme
* Flexberry ASP.NET SpringTheme
* Flexberry ASP.NET SmartTheme
* Flexberry ASP.NET DefaultTheme
* Flexberry ASP.NET LightTheme
* Flexberry ASP.NET BlueSkyTheme

### Flexberry ASP.NET WebApi Cors

## Flexberry Winforms

Пакет содержит компоненты фреймворка Flexberry Winforms: WinForms-контролы и основные типы, необходимые для работы генерируемых WinForms-приложений.

Сборки, входящие в пакет:

* AdmConsole(Objects).dll
* ICSSoft.STORMNET.BusinessTaskMonitor.dll
* ICSSoft.STORMNET.Deployment.dll
* ICSSoft.STORMNET.EBSI.dll
* ICSSoft.STORMNET.FileType.dll
* ICSSoft.STORMNET.UI.dll
* ICSSoft.STORMNET.Windows.Forms.dll
* ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll
* ICSSoft.STORMNET.Windows.Forms.MaskEditControl.dll
* ICSSoft.STORMNET.Windows.Forms.XmlViewer.dll
* Repository.dll
* C1.Common.dll
* C1.Win.C1FlexGrid.dll
* ActiveReports.dll
* ActiveReports.Viewer.dll
* MagicLocalLibrary.dll

## Flexberry Security

Пакет содержит компоненты [подсистемы полномочий](efs_security.html).

Сборки, входящие в пакет:

* CheckingLibrary.dll
* ICSSoft.STORMNET.AzManBridge.dll
* ICSSoft.STORMNET.RightManager.dll
* Security(BusinessServers).dll
* Security(Objects).dll
* NewPlatform.Flexberry.Security.dll
* Interop.ActiveDs.dll
* Microsoft.Interop.Security.AzRoles.dll

## Flexberry Audit

Пакет содержит компоненты [подсистемы аудита](fa_audit-web.html).

Сборки, входящие в пакет:

* ICSSoft.STORMNET.Business.Audit.dll

## Flexberry LogService

Пакет содержит компоненты [сервиса логирования событий](fo_log-service-log4net.html) (этот пакет также определяет используемую версию сборки log4net).

Сборки, входящие в пакет:

* LogService.dll

### Flexberry LogService Objects

Сборки, входящие в пакет:

* IIS.Flexberry.Logging(BusinessServers).dll
* IIS.Flexberry.Logging(Objects).dll
* IIS.Flexberry.Logging.MsEntLib.dll

### Flexberry LogService Winforms

Сборки, входящие в пакет:

* IIS.Flexberry.Logging(Forms).dll

## Flexberry CurrentUserService

Пакет содержит компоненты [сервиса текущего пользователя](fo_current-user-service.html).

Сборки, входящие в пакет:

* ICSSoft.Services.CurrentUserService.dll

## Flexberry UserSettingsService

Сборки, входящие в пакет:

* UserSettingsService.dll

## Flexberry UnityFactor

Пакет содержит компоненты [UnityFactory](fo_unity-factory.html).

Сборки, входящие в пакет:

* ICSSoft.Services.UnityFactory.dll

## Flexberry ServiceBus

### Дополнительные пакеты для Flexberry ServiceBus

* Flexberry ServiceBus Objects
* Flexberry ServiceBus ClientTools
* Flexberry ServiceBus Components
* Flexberry ServiceBus RabbitMQ

## Дополнительно

* Flexberry GIS Objects
* Flexberry Caching
* Flexberry Reports ExportToExcel
* Flexberry EasyNetQ Management Client
