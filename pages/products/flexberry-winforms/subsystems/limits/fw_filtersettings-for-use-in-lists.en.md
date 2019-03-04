--- 
title: Where are filtersetting for use in lists 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), Limitations 
summary: Explains how to determine the user to display the list settings for the current user only 
toc: true 
permalink: en/fw_filtersettings-for-use-in-lists.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: ccf52a7ac0625daf5b34a5e315fe7b3c8d95b3642a5eb7be1f8084e107e44447 
--- 

And they are taken after applying around this limitation: ___AND ( = ( Module IIS.AMS02.Of the incident.WinformЗадержанныйL/objectListView1(ObjectListView) ) OR ( = ( User Sergey KOSHEL ) ISNULL ( User) ) )___ 

Code used to build restrictions: 

```csharp
var ldef = SQLWhereLanguageDef.LanguageDef;
lcs.LimitFunction = ldef.GetFunction(
    ldef.funcAND,
    ldef.GetFunction(
    ldef.funcEQ, vdModule, prv_GenModuleName(ObjectListView)
    ),
    ldef.GetFunction(
    ldef.funcOR,
    ldef.GetFunction(ldef.funcEQ, vdUser, Settings.SettingManager.GetUserName()),
    ldef.GetFunction(ldef.funcIsNull, vdUser)
    )
    );
``` 

That is, the user is taken from the Settings.SettingManager.GetUserName(). And there, in turn, either from the specified value, either from AD or from the Environment: 

```csharp
public static string GetUserName()
{
      if (username==string.Empty)
      {
           try
           {
                System.DirectoryServices.DirectorySearcher ds = new System.DirectoryServices.DirectorySearcher("(&(objectClass=user)(sAMAccountName= "+Environment.UserName+"))",
                     new string[]{cn});
                ds.CacheResults = true;
                System.DirectoryServices.SearchResult sr =  ds.FindOne();
                username = sr.Properties[cn][0].ToString();
           }
           catch
           {
                username =Environment.UserName;
           }
      }
      return username;
}
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/