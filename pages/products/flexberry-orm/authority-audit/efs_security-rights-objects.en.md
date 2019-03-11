--- 
title: authorization to objects 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security, Key concepts 
toc: true 
permalink: en/efs_security-rights-objects.html 
lang: en 
autotranslated: true 
hash: 5e852346d70d13cdd73ece9a451b5c0010fd5c1c55cc81256d3602e387f21aed 
--- 

## to set permissions on objects 

Consider the task assignments of powers to objects on the example of the following tasks. There is a Document class. It must be possible for each individual Document to specify individual credentials. 

(((<msg type=important> 
This solution is not recommended for use in real projects to release a stable version, maybe it will be changed. 
"%} 

To set read permission (that is, [DataService|data service) will not read the objects which are not set permissions) on the objects you want to set the relevant filters. For example, if we want that User1 was available to read the document Document1, you must create Permition, where the agent is User1, and the subject of the Document class, create Access access type Read and to impose a filter that the primary key equals the primary key of the object Document1. If you will need to give the right to Document2, then you need to make changes to the filter that a primary key can have a primary key value Document1 or Document2. 

How to implement it: 

1. Upgrade your package to Flexberry ORM to version 2.1.0-alpha01. 

Upgrade your package to Flexberry Security to version 1.5.0-alpha01. 

To register in the partition of unity definition for ISecurityManager: 
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
      <!-- Manager of office, which will be used by the data service DataServiceProvider.DataService. -->
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
                mapTo="NewPlatform.Flexberry.Security.SecurityManager NewPlatform.Flexberry.Security">
        <constructor>
		  <!-- SecurityManagerDataService data service, through which there will be a request to the authority. -->
          <param name="dataService" dependencyName="SecurityManagerDataService" />
		  <!-- The credentials included. -->
          <param name="enabled" type="System.Boolean" value="true" />
		  <!-- The authorization check for the objects included. -->
          <param name="useRightsOnObjects" type="System.Boolean" value="true" />
		  <!-- Authorization for attributes is enabled. -->
          <param name="useRightsOnAttribute" type="System.Boolean" value="true" />
        </constructor>
      </register>
      
	  <!-- 
SecurityManagerDataService data service, through which there will be a request to the authority. 
Here is duplicated by the service type data and the connection string. Associated with the joint use of SecurityManager and CheckingLibrary. 
-->
      <register name="SecurityManagerDataService"
                type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
                mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <lifetime type="singleton" />
        <constructor>
		  <!-- securityManagerWithoutRightsCheck Manager powers off the credentials. -->
          <param name="securityManager" dependencyName="securityManagerWithoutRightsCheck" />
        </constructor>
		<!-- Set the connection string to the database credentials. -->
        <property name="CustomizationString" value="SERVER=rtc-storm.ics.perm.ru;Trusted_connection=yes;DATABASE=SecurityControlTestS;"/>
      </register>
	  
	  <!-- securityManagerWithoutRightsCheck Manager powers off the credentials. -->
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

2. Make sure the Document class is [AccessType|access type this). 

3. For easy tasks authority to implement the form view: 

[imageauto||{UP(SecurityRightsOnObjects)}SetPermissionsOnObjectForm.png) 

On this form you can select: 
* an object of type Document, which we ask полномочия; 
* user for which to set полномочия; 
* type доступа; 
* add or remove access (depending on the show). 

4. Since authorization objects located in the database of the authority, designated special [DataService|service data). 

``` csharp
/// <summary> 
/// Cached value of the service data for authority. 
/// </summary> 
private static IDataService _securityDataService = null;

/// <summary> 
/// The service data for authority. 
/// After first getting cached. 
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

5. Below are selected credentials changed when you click on the "Save" button, write the appropriate logic. 

``` csharp
/// <summary> 
/// Trivial logic for saving an object. 
/// </summary> 
/// <returns>the data Object that is preserved.</returns> 
protected override DataObject SaveObject()
{
    // Deducted the selected user name. 
    string agentName = SelectUserControl.Items[SelectUserControl.SelectedIndex).Value;

    // Define the selected document. 
    Document selectedDocument = DataObject.Document;

    CurrentUserService.IUser user = new User() { Login = agentName };

    // Define the type of access. 
    string selectedAccessTypeName = SelectAccessType.Items[SelectAccessType.SelectedIndex).Value;

    // Define, add or remove rights. 
    bool addAccess = AddAccessCheckBox.Checked;
    tTypeAccess selectedTypeAccess;

    // If error-filled data, we deduce the error. 
    if (string.IsNullOrEmpty(agentName) || !Enum.TryParse(selectedAccessTypeName, out selectedTypeAccess))
    {
        WebErrorBoxRiser.Rise(new Exception("The specified data is incorrect."), false);
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

Now when you set some user specific rights you will be able to view only the relevant lines.

## the credentials specified on the object 

To check whether the object satisfies specified filters powers, you can use the following functionality: 

``` csharp
ISecurityManager securityManager = DataServiceProvider.DataService.SecurityManager;
bool hasAccess = securityManager.AccessObjectCheck(objectTocheck, tTypeAccess.Insert, true);
``` 

First, the example retrieves the current instance `ISecurityManager` installed in the current [DataService|data service) (requires to the Manager of office was set to credentials to the objects (for example, using the config as shown above); if not, then the necessary authority Manager authority can be created by using program code). 

Further, in the method of the Manager powers of Paredes: 
* object permissions which are checked (may be transferred to any object, in this case, the access check on the object type, but if you pass in a [DataObject|data object), the validation happens on the object with the given restrictions), 
* the type of operation, authority for which is required to check 
* the flag that determines whether to throw an exception if no authorization for the object. 

(((<msg type=important> 
Currently supported authorization check on the minimum number of functions limitations (just only supported constraints created above). 
"%} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}