--- 
title: Service authority Flexberry Rights (CheckingLibrary) 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security, Key concepts 
toc: true 
permalink: en/efs_security-legacy-services.html 
lang: en 
autotranslated: true 
hash: 9561d7b8543328b1fd1a06fe44628d8ca2a50ebb2593902c88792e6537f5e0e4 
--- 

`Сервис powers Flexberry Rights` is an alternative service [authority](efs_right-manager-module.html), designed to meet the increasing demands. The main purpose of the service is to provide mediated access to databases (protecting the database from unwanted changes). 

### compatibility Issues 

To ensure the compatibility of applications with the new system of authority, the existing client-server architecture of the authorization check is left. 

Ispolzuyutsya and [RightManager](efs_right-manager.html), and `AzManBridge` (redirects calls to AzMan and `CheckingLibrary`). 

AzManBridgeService supports multiple applications at the same time, it is possible to use one server to check permissions for a set of different applications. 

### Modes Flexberry Rights 

There are several options `RightManager`: 

* `AzMan` - Autorization Manager (older technology, not recommended for new projects) 
* `SimpleCheck` - Check the similarity Autorization Manager, only used CheckingLibrary. 
* `SessionCheck` - Check with use of the session mechanism. 

#### SessionCheck 

The principle of operation in this case differs from the standard fact that in addition to information about the user for which you are requesting verification of credentials, `[RightManager)` transmits another session key that is checked on the server side. Thus, it is mandatory the verification of login and password prior to obtaining rights information of a user. 

The session key is issued to the client only in case of successful verification of login and password of the user. 

On the server side sets the time of inactivity of the session, after which the session key is removed from the list of active keys. If `[RightManager)` requesting authority with the key of the expired session, the server he will say no and ask again to operation of logging into the system. `[RightManager)` throw event `NeedLogIn`. The programmer must be sure to subscribe to this event and perform the operation: 

``` csharp
        /// <summary> 
        /// To login and get the session key (the output happens automatically when you call this function) 
        /// </summary> 
        /// <param name="login">login</param> 
        /// <param name="domain">domain</param> 
        /// <param name="pwd">password</param> 
        /// <param name="name">the name of the user (for synchronization)</param> 
        /// <returns>the success of the operation</returns> 
        public static OperationResult LogInAndGetNewSessionKey(string login, string domain, string pwd, out string name)
``` 

For example, you can use [UsingSimpleLoginForm#SimpleLoginformexample|standard form login) (Windows applications). 

#### an Example of a configuration file `AzManBridgeService` 

``` xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
	<appSettings>
		<add key="AzManSecurity" value="false"/>
                <!-- the port on which a web service -->
		<add key="PortNumber" value="8085"/>

                <!-- a flag indicating that this service handles multiple applications -->
		<add key="MultipleBases" value="true"/>

                <!-- output debug information is written to the log virtually all tests -->
		<add key="debugInformation" value="true"/>


		<add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
		<add key="CustomizationStrings" value="SERVER=Tornado;Trusted_connection=yes;DATABASE=MOB_Security;" />

                <!-- the name of the application described in this configuration file, in a database which will be merged session when you restart the service -->
		<add key="SessionStorageAppName" value="SessionModeTestingApp"/>
                <!-- The period in minutes for expire user sessions -->
		<add key="SessionExpireMin" value="5"/>

		<add key="DataServiceType_SessionModeTestingapp" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
		<add key="CustomizationStrings_SessionModetestingapp" value="SERVER=storm;Trusted_connection=no;DATABASE=SecurityInSessionModeDB;uid=webuser1;pwd=1;" />
		<add key="CheckingMode_SessionModeTestingApp" value="SessionCheck"/>


		<add key="DataServiceType_PGSS" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
		<add key="CustomizationStrings_PGSS" value="SERVER=tornado;Trusted_connection=no;DATABASE=PGSS_Security;uid=pgss_sec_user;pwd=1" />
		<add key="CheckingMode_PGSS" value="SessionCheck"/>


		<!--this setting allows you to manage your classes name (true - name with a namespace, false for no namespace)-->
		<add key="UsingNamespaceForRights" value="false"/>


<!-- ["CheckingMode_", appName) Possible values: AzMan, SimpleCheck, SessionCheck (Restarting the service is needed)-->
<!-- ["SessionStorageAppName") the name of the application to save sessions across reboots of the service -->
<!-- ["SessionExpireMin") Time after which the session is considered as expired (in minutes) -->
	</appSettings>

</configuration>
``` 

### the Job name of the application for authorization check 

In order for a single service authority could serve several applications, it is necessary to obtain information about what the app sends it a request. By default, the application name is taken the name of the executable without extension. Programmers have the ability through configuration file to replace this value by another. 

#### web 

The configuration file needs to specify the application name in block `unity`: 

``` xml
<!-- Authority system -->
  <unity>
    <typeAliases>
      <typeAlias alias="ISecurityService" type="ICSSoft.STORMNET.Security.ISecurityService, ICSSoft.STORMNET.DataObject"/>
      <typeAlias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary"/>
      <typeAlias alias="singleton"
                 type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager,Microsoft &#xD;&#xA;.Practices.Unity"
                                                />
    </typeAliases>
    <containers>
      <container>
        <types>
          <type type="ISecurityService" mapTo="CheckingInSessionMode">
            <lifetime type="singleton" />
            <typeConfig extensionType="Microsoft.Practices.Unity.Configuration.TypeInjectionElement, Microsoft.Practices.Unity.Configuration">
              <constructor>
                <param name="imprisone" parameterType="System.String">
                  <!-- Here it is necessary to register the name of the application -->
                  <value value="WebApp" type="System.String" />
                </param>
              </constructor>
            </typeConfig>
          </type>
        </types>
      </container>
    </containers>
  </unity>
  <!-- Authority system -->

<authentication mode=Forms >
      <forms name=".ASPXFORMSAUTH" loginUrl="LoginForm.aspx" timeout="30" slidingExpiration="true" />
    </authentication>

    <authorization>
      <deny users="?" />
    </authorization>

    <membership defaultProvider="CaseberryMembershipProvider">
      <providers>
        <clear/>
        <add name="CaseberryMembershipProvider" type="ICSSoft.STORMNET.Security.CaseberryMembershipProvider" applicationName="SLAuthSample"/>
      </providers>
    </membership>

    <roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
      <providers>
        <clear/>
        <add name="CaseberryRoleProvider" type="ICSSoft.STORMNET.Security.CaseberryRoleProvider" />
      </providers>
    </roleManager>
``` 

#### winforms 


In the configuration file set: `AppNameForAzMan` or taken 

``` csharp
_securityApplicationName = System.IO.Path.GetFileNameWithoutExtension(Environment.GetCommandLineArgs()[0));
``` 

Also have the ability to programmatically set the name of the application using `RightManager.SecurityApplicationName`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}