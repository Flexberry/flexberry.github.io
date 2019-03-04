--- 
title: Checking password complexity in the security console 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security 
toc: true 
permalink: en/efs_checking-password-complexity-in-security-console.html 
lang: en 
autotranslated: true 
hash: 64459c60568ffa907e92b93de4cdeffab78c61f03d1c5e22f6474b8c1f964ac3 
--- 

# Checking password complexity in the security console 
When you install a user password in the security console has the ability to organize and control its complexity. To specify constraints that must be met for the password, either via the configuration file or through a dialog box `Агенты\Complexity пароля` (settings are saved in a configuration file). 

![](/images/pages/img/page/Checking-password-complexity-in-security-console/1 Console security.png) 
![](/images/pages/img/page/Checking-password-complexity-in-security-console/2 the complexity of the password.png) 

If you change the password the user entered value will be automatically monitored, and in violation of the restrictions be given an appropriate message. 

![](/images/pages/img/page/Checking-password-complexity-in-security-console/3 Error.png) 


# configuration file Structure 

Configuring password authentication is stored in the "'configuration file"'. 
```xml
<?xml version="1.0"?>
<configuration>
  <configSections>
...
    <section name="passwordOptions" type="ICSSoft.STORMNET.Security.PasswordOptions.PasswordOptions, Security(Forms), Version=1.0.0.1, Culture=neutral, PublicKeyToken=110e1aa82d692161" />
...
  </configSections>

...
  <passwordOptions minimumCharacterCount="6" useLatinCharacters="true"
    useDigitCharacters="true" useSpecialCharacters="false" useCyrillicCharacters="false"
    useUpperCaseCharacters="true" />
...
</configuration>
``` 

# See also 

* [Console privilege management (Security Console)](efs_security-console.html) 
* [Usage scenarios subsystem powers](efs_rights-scenarios.html). 
* [Service authority Flexberry Rights (CheckingLibrary)](efs_security-legacy-services.html) 
* [Engine powers WEB](fa_right-manager.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/