--- 
title: WebLockHelper 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_web-lock-helper.html 
lang: en 
autotranslated: true 
hash: 10b81d7e164a9dec6cc6ed2d0c42c16a71166db1599278bb920f561fe2a50763 
--- 

`ICSSoft.STORMNET.Web.Tools.WebLockHelper` class to work with web-locks of data objects in web applications. Uses the date and lifetime of a lock to determine its relevance, the lock is stored in the database. 

## Interface 

A class implements an interface `ILockHelper`: 

```csharp
public interface ILockHelper
{
    bool LockDataObject(string dataObjectKey, string userName, out string lockUserName);
    bool ClearWebLock(string dataObjectKey, string userName);
    string GetLock(string dataObjectKey);
    TimeSpan LockTimeout { get; set; }
}
``` 

* `LockDataObject` is to try to establish a lock on the object by the given key and user name. In the event of a lock `true` returns if the object is already locked - `false`. In this case, the parameter `lockUserName` is written the name of the user that set the lock. 
* `ClearWebLock` - remove a lock if it was installed by the current user. In case a good cleaning will return `true`, otherwise `false`. 
* `GetLock` - returns the name of the user that has locked the object by key. If the object is not locked, an empty string is returned. 
* `LockTimeout` the lifetime of a lock. You need to remove the old lock. If the edit page is open, it periodically sends ajax requests to extend the lock. When you close the page requests are canceled, and the lock is aging. The frequency of requests corresponds to the lifetime of the lock. 

## Uses 

If the desired application service locks, you can implement an interface `ILockHelper` and use your own class where you have, for example, to ask for locks in [WebObjectListView](fa_web-object-list-view.html). 

An example of using `WebLockHelper` in WOLV: 

```csharp
        // A property that stores LockHelper 
        public static ILockHelper LockHelper
        {
            get
            {
                if (_lockHelper == null)
                {
                    _lockHelper = new WebLockHelper();
                }
                return _lockHelper;
            }
            set { _lockHelper = value; }
        }
``` 

Setting the lock: 

```csharp
if (lockHelper.LockDataObject(PK, Context.User.Identity.Name, out lockUserName))
{
    MessageBox.Show("The object was successfully locked");
} 
else
{
    MessageBox.Show("The object is already locked by user " + lockUserName);
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}