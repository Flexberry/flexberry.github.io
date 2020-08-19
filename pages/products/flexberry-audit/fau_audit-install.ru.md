---
title: Установка и настройка аудита
sidebar: flexberry-audit_sidebar
keywords: flexberry, audit, designer, install
summary: Установка и настройка аудита в дизайнере и после генерации
toc: true
permalink: ru/fau_audit-install.html
lang: ru
---

Для работы аудита требуются полномочия в бд и имя пользователя.
При отсутствии полномочий на проекте возможно установить имя пользователя при инициализации бэкенда через CurentUserService.

1. Настройка БД в дизайнере
В настройках генерации базы данных (стадия/SQL/<Выбранная бд>/Настройка БД) требуется установить "БД аудита в БД приложения" и "БД полномочий в БД приложения"

![](/images/pages/products/flexberry-audit/designer-storage-audit.png)

так же присутствует возможность развернуть отдельные бд для аудита.

2. Настройка модели в дизайнере
В настройках модели приложения (стадия/Свойства модели/Настройка аудита) требуется установить "Вести аудит в приложении" и "Включить аудит во всех классах", добавляющий поля аудита

```
+CreateTime:AuditNullableDateTime
+Creator:string
+EditTime:AuditNullableDateTime
+Editor:string
```

ко всем классам приложения.

![](/images/pages/products/flexberry-audit/designer-model-audit.png)

3. Настройка аудита в сгенерированных объектах
При генерации объектов с настроенным аудитом в модели в каждый класс добавляются настройки аудита.

```сs
   /// <summary>
   /// Audit class settings.
   /// </summary>
   public class AuditSettings
   {
      
      /// <summary>
      /// Включён ли аудит для класса.
      /// </summary>
      public static bool AuditEnabled = true;
      
      /// <summary>
      /// Использовать имя представления для аудита по умолчанию.
      /// </summary>
      public static bool UseDefaultView = false;
      
      /// <summary>
      /// Включён ли аудит операции чтения.
      /// </summary>
      public static bool SelectAudit = false;
      
      /// <summary>
      /// Имя представления для аудирования операции чтения.
      /// </summary>
      public static string SelectAuditViewName = "AuditView";
      
      /// <summary>
      /// Включён ли аудит операции создания.
      /// </summary>
      public static bool InsertAudit = true;
      
      /// <summary>
      /// Имя представления для аудирования операции создания.
      /// </summary>
      public static string InsertAuditViewName = "AuditView";
      
      /// <summary>
      /// Включён ли аудит операции изменения.
      /// </summary>
      public static bool UpdateAudit = false;
      
      /// <summary>
      /// Имя представления для аудирования операции изменения.
      /// </summary>
      public static string UpdateAuditViewName = "AuditView";
      
      /// <summary>
      /// Включён ли аудит операции удаления.
      /// </summary>
      public static bool DeleteAudit = true;
      
      /// <summary>
      /// Имя представления для аудирования операции удаления.
      /// </summary>
      public static string DeleteAuditViewName = "AuditView";
      
      /// <summary>
      /// Путь к форме просмотра результатов аудита.
      /// </summary>
      public static string FormUrl = "";
      
      /// <summary>
      /// Режим записи данных аудита (синхронный или асинхронный).
      /// </summary>
      public static ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode WriteMode = ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode.Synchronous;
      
      /// <summary>
      /// Максимальная длина сохраняемого значения поля (если 0, то строка обрезаться не будет).
      /// </summary>
      public static int PrunningLength = 0;
      
      /// <summary>
      /// Показывать ли пользователям в изменениях первичные ключи.
      /// </summary>
      public static bool ShowPrimaryKey = false;
      
      /// <summary>
      /// Сохранять ли старое значение.
      /// </summary>
      public static bool KeepOldValue = true;
      
      /// <summary>
      /// Сжимать ли сохраняемые значения.
      /// </summary>
      public static bool Compress = false;
      
      /// <summary>
      /// Сохранять ли все значения атрибутов, а не только изменяемые.
      /// </summary>
      public static bool KeepAllValues = false;
   }
```
Здесь есть возможность настройки операции и представления, по которым будет производиться аудит (По умолчанию аудит операции изменения объекта выключен).

4. В приложении или бэкенде (для проекта на Ember) должны присутствовать пакеты Audit и Security.

5. В конфиге приложения настройки и дата сервис для аудита. 

```config
<appSettings>
   <add key="AuditEnabled" value="True" />
   <add key="AppNameForAudit" value="То самое приложение" />
   <add key="AuditConnectionStringName" value="AuditConnString_56254" />
   <add key="WriteSessions" value="False" />
   <add key="AuditWinServiceUrl" value="" />
   <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
   <add key="DefaultWriteMode" value="Synchronous" />
   <add key="IsAuditDatabaseLocal" value="True" />
</appSettings>
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
   <container>
      <register name="dataServiceForAuditAgentManagerAdapter" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr" />
      </register>
   </container>
</unity>
```