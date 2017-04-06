---
title: Работа с полномочиями на объекты
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security, Ключевые понятия
toc: true
permalink: ru/efs_security-rights-objects.html
lang: ru
---

## Задание полномочий на объекты

Рассмотрим задачу задания полномочий на объекты на примере следующей задачи. Есть класс Document. Необходимо иметь возможность на каждый отдельный Document задавать индивидуальные полномочия.

(((<msg type=important>
Данное решение не рекомендуется использовать в реальных проектах до выхода стабильных версий пакетов, возможно оно будет изменено.
" %}

Чтобы задать полномочия на чтение (то есть [DataService|сервис данных) не будет вычитывать объекты, на которые не заданы полномочия) на объекты требуется задать в полномочиях соответствующие фильтры. Например, если мы хотим, чтобы пользователю User1 был доступен на чтение документ Document1, то необходимо создать Permition, где агентом является User1, а субъектом - класс Document, создать Access с типом доступа Read и наложить фильтр, что первичный ключ равен первичному ключу объекта Document1. Если потребуется дополнительно дать права на Document2, то нужно внести изменения в фильтр, что первичный ключ может иметь значение первичного ключа Document1 или Document2.

Как это реализовать:

1. Обновить пакет Flexberry ORM до версии не ниже 2.1.0-alpha01. 

Обновить пакет Flexberry Security до версии не ниже 1.5.0-alpha01.

Прописать в секцию unity определение для ISecurityManager:
```xml
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration" />
    ...
  </configSections>
  <appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
  </appSettings>
  
  <connectionStrings>
    <add name="DefConnStr" connectionString="SERVER=rtc-storm.ics.perm.ru;Trusted_connection=yes;DATABASE=SecurityControlTest" />
    <add name="CaseberrySecurity" connectionString="SERVER=rtc-storm.ics.perm.ru;Trusted_connection=yes;DATABASE=SecurityControlTestS" providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66" />
  </connectionStrings>
  
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <alias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary" />
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
	...
    <container>
      <!-- Менеджер полномочий, который будет использоваться сервисом данных DataServiceProvider.DataService. -->
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
                mapTo="NewPlatform.Flexberry.Security.SecurityManager, NewPlatform.Flexberry.Security">
        <constructor>
		  <!-- SecurityManagerDataService - сервис данных, через который будет идти запрос к полномочиям. -->
          <param name="dataService" dependencyName="SecurityManagerDataService" />
		  <!-- Проверка полномочий включена. -->
          <param name="enabled" type="System.Boolean" value="true" />
		  <!-- Проверка полномочий на объекты включена. -->
          <param name="useRightsOnObjects" type="System.Boolean" value="true" />
		  <!-- Проверка полномочий на атрибуты включена. -->
          <param name="useRightsOnAttribute" type="System.Boolean" value="true" />
        </constructor>
      </register>
      
	  <!-- 
		SecurityManagerDataService - сервис данных, через который будет идти запрос к полномочиям. 
		Здесь дублируется тип сервиса данных и его строка соединения. Связано с совместным использованием SecurityManager и CheckingLibrary.
	  -->
      <register name="SecurityManagerDataService"
                type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
                mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <lifetime type="singleton" />
        <constructor>
		  <!-- securityManagerWithoutRightsCheck - менеджер полномочий с выключенной проверкой полномочий. -->
          <param name="securityManager" dependencyName="securityManagerWithoutRightsCheck" />
        </constructor>
		<!-- Задаём строку соединения к БД полномочий. -->
        <property name="CustomizationString" value="SERVER=rtc-storm.ics.perm.ru;Trusted_connection=yes;DATABASE=SecurityControlTestS;"/>
      </register>
	  
	  <!-- securityManagerWithoutRightsCheck - менеджер полномочий с выключенной проверкой полномочий. -->
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
        <lifetime type="singleton" />
        <constructor>
          <param name="enabled" type="System.Boolean" value="false" />
        </constructor>
      </register>
      
    </container>
  </unity>
  ...
</configuration>
```

2. Удостовериться, что у класса Document стоит [AccessType|тип доступа this).

3. Для удобного задания полномочий реализуем форму вида:

[imageauto||{UP(SecurityRightsOnObjects)}SetPermissionsOnObjectForm.png)

На такой форме можно выбрать:
* объект типа Document, на который мы задаём полномочия;
* пользователя, для которого задаются полномочия;
* тип доступа;
* добавляем или удаляем доступ (в зависимости от галочки).

4. Поскольку объекты полномочий расположены в БД полномочий, то определяем специальный [DataService|сервис данных).

``` csharp
/// <summary>
/// Закэшированное значение сервиса данных для работы с полномочиями.
/// </summary>
private static IDataService _securityDataService = null;

/// <summary>
/// Сервис данных для работы с полномочиями.
/// После первого получения кэшируется.
/// </summary>
private static IDataService SecurityDataService
{
	get
	{
		if (_securityDataService == null)
		{
			IUnityContainer container = UnityFactory.GetContainer();
			_securityDataService = container.Resolve<IDataService>("SecurityManagerDataService");
		}

		return _securityDataService;
	}
}
```

5. Чтобы выбранные полномочия менялись при нажатии на кнопку "Сохранить", пишем соответствующую логику.

``` csharp
/// <summary>
/// Нетривиальная логика сохранения объекта.
/// </summary>
/// <returns>Объект данных, который сохранился.</returns>
protected override DataObject SaveObject()
{
    // Вычитываем выбранное имя пользователя.
    string agentName = SelectUserControl.Items[SelectUserControl.SelectedIndex).Value;

    // Определяем выбранный документ.
    Document selectedDocument = DataObject.Document;

    CurrentUserService.IUser user = new User() { Login = agentName };

    // Определяем тип доступа.
    string selectedAccessTypeName = SelectAccessType.Items[SelectAccessType.SelectedIndex).Value;

    // Определяем, добавляем или удаляем права.
    bool addAccess = AddAccessCheckBox.Checked;
    tTypeAccess selectedTypeAccess;

    // Если ошибка в заполненных данных, то выводим ошибку.
    if (string.IsNullOrEmpty(agentName) || !Enum.TryParse(selectedAccessTypeName, out selectedTypeAccess))
    {
        WebErrorBoxRiser.Rise(new Exception("Указанные данные некорректны."), false);
        return null;
    }

    SecurityManager securityManager = new SecurityManager(SecurityDataService, true, true, false);

    if (addAccess)
    {
        securityManager.AddPermissionToObject(user, selectedTypeAccess, selectedDocument);
    }
    else
    {
        securityManager.RemovePermissionToObject(user, selectedTypeAccess, selectedDocument);
    }

    return null;
}
```

Теперь при задании на какого-то пользователя специфичных прав ему будет доступно просматривать только соответствующие строки.

## Проверка полномочий, заданных на объект

Чтобы проверить, удовлетворяет ли объект заданным фильтрами полномочий, можно воспользоваться следующей функциональностью:

``` csharp
ISecurityManager securityManager = DataServiceProvider.DataService.SecurityManager;
bool hasAccess = securityManager.AccessObjectCheck(objectTocheck, tTypeAccess.Insert, true);
```

Сначала в примере происходит получение текущей инстанции `ISecurityManager`, установленной в текущий [DataService|сервис данных) (требуется, чтобы при этом менеджер полномочий был настроен на проверку полномочий на объекты (например, с помощью конфига, как показано выше); если это не так, то необходимую инстанцию менеджера полномочий можно создать с помощью программного кода).

Далее в метод менеджера полномочий передаётс:
* объект, полномочия на который проверяются (может быть передан любой объект, в этом случае происходит проверка доступа на тип объекта, но если передан [DataObject|объект данных), то проверка происходит и на сам объект с учётом заданных ограничений), 
* тип операции, полномочия на которую требуется проверять,
* флаг, определяющий, требуется ли кидать исключение, если отсутствуют полномочия на объект.

(((<msg type=important>
На настоящий момент поддерживается проверка полномочий на минимальное количество функций ограничения (точно поддерживаются только ограничения, создаваемые выше).
" %}
