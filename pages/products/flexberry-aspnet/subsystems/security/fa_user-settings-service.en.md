--- 
title: UserSettingsService 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry UserSettingsService 
toc: true 
permalink: en/fa_user-settings-service.html 
lang: en 
autotranslated: true 
hash: d305c80bb0025b057ab72b335fc7ea5cd4bc2007fc148cd694c0ff52ecfcc218 
--- 

`UserSettingsService` is a service by storing user preferences. 

1. Allows you to set, get and delete user settings. Implemented multiple fields with different standard types are easier to use, does not require the cost of type conversion. 
2. There are many overloads of methods for ease of use. 
3. There are General settings (Common), which are defined for all application at once. 
4. Implemented support for specifying settings like names (user, module, and settings), and unique identifiers. This implementation allows application parts to have the appropriate reference, which can be visually edited and used to control the settings. 
5. The service may be used as an Assembly within the app or as a WCF service (in development). 
6. There is a set of methods to work with collections of settings (in development). It allows to implement functionality of the form: "give me all user settings N to I put them in your кэш; removed from the cache when the user logs off". 
7. Writing service, is using Unit testing (NUnit). 

To work `UserSettingsService` requires a separate table in the database. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}