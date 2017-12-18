---
title: Windows-сервис аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-win-service.html
lang: ru
---

(((Данная статья относится к новому аудиту)))

# Аудит
Что такое аудит можно узнать в [этой статье](fa_audit-web.html).


# Сервис Аудита
Отдельный сервис Аудита предоставляет те же возможности, что и встроенный в приложение аудит, но он вынесен в виде отдельного Windows-сервиса. (данный сервис доработан в версии после 02.10.2013)

# Подключение к сервису аудита
Для подключения к сервису необходимо: 

1. Добавить в файл конфигурации приложения ключ `AuditWinServiceUrl`, определяющий url, где располагается сервис.

```xml
<add key="AuditWinServiceUrl" value="http://localhost:8733/AuditWcfService/" />
```
Выше указан адрес для примера. В реальной ситуации адрес необходимо брать из конфигурационного файла сервиса:
```xml
<services>
  <service name="ICSSoft.STORMNET.Business.AuditWcfServiceLibrary.AuditWcfService" behaviorConfiguration="MyBehavior">
	<endpoint address="" binding="basicHttpBinding" contract="ICSSoft.STORMNET.Business.AuditWcfServiceLibrary.IAuditWcfService">
	  <identity>
		<dns value="localhost"/>
	  </identity>
	</endpoint>
	<endpoint address="mex" binding="mexHttpBinding" contract="IMetadataExchange"/>
	<host>
	  <baseAddresses>
		<add baseAddress="http://localhost:8733/AuditWcfService/"/>
	  </baseAddresses>
	</host>
  </service>
</services>
```

2. Подготовить сборку с объектами, аудит которых будет производиться. Сборка нужна только для работы метода [WriteCommonAuditOperation](efs_audit-web-api.html), остальные методы записи аудита (в том числе метод записи аудита для собственных объектов и операций) не требуют сборок с объектами.

(((
<msg type=important>Обратите внимание, что сборка с объектами должна быть собрана для версии .Net Framework версии 3.5.</msg>
)))

Подготовленную сборку необходимо скопировать в папку "ObjectAssemblies", расположенную рядом с сервисом аудита.

3. Сконфигурировать настройки по работе с базой данных (есть [отдельная статья, определяющая механизм определения настроек соединения с БД при аудите](efs_data-service-for-audit.html)).

Для этого нужно в конфиг-файле приложения указать следующие настройки (данные настройки приведены для примера и могут отличаться в конкретном приложении):
```xml
<add key="IsAuditDatabaseLocal" value="False" />
<add key="AppNameForAudit" value="TestFS" />
<add key="AuditConnectionStringName" value="AuditConnString" />
```

Если `IsAuditDatabaseLocal = false`, то в конфиге сервиса будет искаться строка подключения, задаваемая параметром `AuditConnectionStringName` (то есть в данном случае - "`AuditConnString`").
```xml
<connectionStrings>
	<add name="AuditConnString" connectionString="SERVER=.;Trusted_connection=yes;DATABASE=AuditEtaloneDB;" />
</connectionStrings>
```

Тип сервиса данных всегда ищется в конфиг-файле по ключу `<esc><</esc>Имя строки соединения<esc>></esc>_DSType` (если такой записи нет, то берётся сервис данных, указанных в настройке "`DefaultDSType`", а если и это не помогло, то используется сервис данных, указанный в `DataServiceProvider.DataService`).

```xml
<appSettings>
    <add key="AuditConnString_DSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
    <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="CustomizationStrings" value="SERVER=.;Trusted_connection=yes;DATABASE=qwerty;" />
</appSettings>
```

Если `IsAuditDatabaseLocal = true`, то получение имени имени строки соединения происходит по особой схеме: имя ищется среди [AuditDSSettings](fa_audit-web.html). Имя сервиса по умолчанию определено как `<esc><</esc>AppNameForAudit<esc>></esc>_<esc><</esc>AuditConnectionStringName<esc>></esc>` (в нашем случае - это "`TestFS_AuditConnString`").

```xml
<connectionStrings>
	<add name="TestFS_AuditConnString" connectionString="SERVER=.;Trusted_connection=yes;DATABASE=AuditEtaloneDB;" />
</connectionStrings>
```

