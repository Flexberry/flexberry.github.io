--- 
title: IcsTreeView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_ics-treeview.html 
lang: en 
autotranslated: true 
hash: 8546d1b1121ebb9bf51855da76131f8062baafbc9e8bfc8bcc5cb612cbfa94af 
--- 

Web control `ICSSoft.STORMNET.Web.AjaxControls.IcsTreeView` - heir `System.Web.UI.WebControls.TreeView`, differs in that it is able to remember the state in cookies and to expand/hide the branch by clicking on the inscription of the site instead of the arrow to the left. 

## Properties 

In addition to the properties inherited from `System.Web.UI.WebControls.TreeView`, present: 

|Signature | Description| 
|:-----------------------|:---------------------------------------------------| 
| `public int? CookieExpiration` | life Time state of control in these days. Example: `ctrlCtrl.CookieExpiration = 10;`| 
| `public bool RememberState` | does the control remember condition. Default `true`| 
| `public ToggleOnNodeClick` bool | whether to hide or reveal child nodes by clicking on the inscription. This action is performed if the node is not assigned to another link, otherwise, click on it. When `ToggleOnNodeClick == false` and the absence of a reference node is negligably inscription. Default `true`| 
| `public static InitControlSettingsDelegate<IcsTreeView> InitSettings` | a Static delegate to customize the control. More info below| 
| `public bool CollapsedState` | the Parameter that determines whether the first display to display a tree in a collapsed state (if tree stored cookies, then the corresponding vertex will still be displayed in the specified in the cookie)| 

## Setting 

* To globally customize the control of this type, in `InitSettings` you need to assign a method that takes as a parameter an instance of `IcsTreeView` and changing its properties as desired. [Global setting web controls](fa_init-control-settings-delegate.html) 

* [Customize the site map's roles](fa_sitemap-according-roles.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}