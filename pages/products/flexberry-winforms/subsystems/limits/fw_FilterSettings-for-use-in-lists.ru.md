---
title: Откуда берутся фильтерсеттинги для применения на списках
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Ограничения
summary: Объясняется как определяется пользователь для отображения настроек списка только для текущего пользователя
toc: true
permalink: ru/fw_filtersettings-for-use-in-lists.html
folder: products/flexberry-winforms/
lang: ru
---

 А берутся они после применения примерно такого ограничения: ___AND ( = ( Module IIS.AMS02.Происшествия.WinformЗадержанныйL/objectListView1(ObjectListView) ) OR ( = ( User KOSHEL Sergey ) ISNULL ( User) ) )___

Код, используемый для построения ограничения: 

```csharp
lcs.LimitFunction = FunctionBuilder.BuildAnd(
    FunctionBuilder.BuildEqials(vdModule, prv_GenModuleName(ObjectListView)),
    FunctionBuilder.BuildOr(
        FunctionBuilder.BuildEqials(vdUser, Settings.SettingManager.GetUserName()),
        FunctionBuilde.BuildIsNull(vdUser)
    ));
```

То есть, юзер берется из Settings.SettingManager.GetUserName(). А там, в свою очередь, либо из установленного значения, либо из AD, либо из Environment: 

```csharp
public static string GetUserName()
{
      if (username==string.Empty)
      {
           try
           {
                System.DirectoryServices.DirectorySearcher ds = new System.DirectoryServices.DirectorySearcher("(&(objectClass=user)(sAMAccountName= "+Environment.UserName+"))",
                     new string[]{"cn"});
                ds.CacheResults = true;
                System.DirectoryServices.SearchResult sr =  ds.FindOne();
                username = sr.Properties["cn"][0].ToString();
           }
           catch
           {
                username =Environment.UserName;
           }
      }
      return username;
}
```