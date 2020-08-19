--- 
title: IMasterPageService 
sidebar: flexberry-aspnet_sidebar 
keywords: 
toc: true 
permalink: en/fa_i-master-page-service.html 
lang: en 
autotranslated: true 
hash: d7a34aee897df5f18df8596c79256b6e6985a0a3f35b5c0cfeb99723e95363f6 
--- 

`IMasterPageService` - the service interface for working with Master pages. 
The service is used to configure Master pages in the process page that is stored in assemblies, due to the fact that at the stage of compilation is not known, the structure and configuration of a specific application project. The service allows you to specify how used Master page and contanier, which will display the contents of the page. 

## create a user in the Web through the group 

To do this: 

1.Add a class to the project. Property **MasterPageFile**. 

```csharp
public class StaticMasterPageService : IMasterPageService
{
    /// <summary> 
    /// The server path to the current Master page. 
    /// </summary> 
    private readonly string _masterPageFile;

    /// <summary> 
    /// The ID of the container for page content. 
    /// </summary> 
    private readonly string _placeHolderId;

    /// <summary> 
    /// The class constructor. 
    /// Explicitly sets the parameters for working with Master page. 
    /// </summary> 
    /// <param name="masterPageFile">the Server path to the current Master page.</param> 
    /// <param name="placeHolderId">the ID of the container for the page content.</param> 
    public StaticMasterPageService(string masterPageFile, string placeHolderId)
    {
        _masterPageFile = masterPageFile;
        _placeHolderId = placeHolderId;
    }

    /// <summary> 
    /// The server path to the current Master file in WebAppTemplate. 
    /// </summary> 
    public string MasterPageFile
    {
        get
        {
            switch (ThemeService.Current.Theme)
            {
                case "Boost":
                    return "~/Boost.Master";
                case "Smart":
                    return "~/Site1.Master";
            }

            return _masterPageFile; 
        }
    }

    /// <summary> 
    /// Method to retrieve the control container for the page content. 
    /// Returns the main ContentPlaceholder of the Master page WebAppTemplate. 
    /// </summary> 
    /// <param name="page">the Page that you need to determine the container for the main content.</param> 
    /// <returns>the Control container in which are placed all the controls on the page.</returns> 
    public Control GetContentPlaceholder(Page page)
    {
        return page.FindControlsByID(_placeHolderId).FirstOrDefault();
    }

}
``` 

2.To Change The Web.config. Parameter **mapTo**. 

```xml
<!-- Configuration service Master. -->
<register type="NewPlatform.Flexberry.Web.MasterPage.IMasterPageService, ICSSoft.STORMNET.Web.AjaxControls" 
	mapTo="IIS.Budget.StaticMasterPageService, Budget(ASP.NET Application)">
	<lifetime type="singleton" />
	<constructor>
		<param name="masterPageFile" type="System.String" value="~/Site1.Master" />
		<param name="placeHolderId" type="System.String" value="ContentPlaceholder1" />
	</constructor>
</register>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}