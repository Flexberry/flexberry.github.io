---
title: Files in odata
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, odata, files
summary: Features working with files using OData service
toc: true
permalink: en/efd2_work-files.html
lang: en
autotranslated: true
hash: 76b8fc1984107867452159136c2e3beb2492d6fa9190708d7c05823f4351b350
---

## Description

The OData service provides the ability to upload any files to the server, download them, and perform them to bind to properties of data objects.
The client, in turn, contains a special transformation to represent the file properties on the client, and the component [flexberry-file](ef2_file.html) to work with them, then read more.

## Quick start

If you need to work with the files via OData service without learning all the details of how the service is implemented to work with them, then brief the algorithm is as follows:
* Select the file properties .NET object class data type `ICSSoft.STORMNET.FileType.File` (if you want to store files in the database) or `ICSSoft.STORMNET.UserDataTypes.WebFile` (if you want to store files in the file system)
* Select the file property of the ember model type `DS.attr('file')`
* In the database select this property type `NVARCHAR(MAX)` (if you work with MS SQL Server) or type `TEXT` (if you work with PostrgreSQL)
* Register file controller in OData service, writing in class `App_Start\ODataConfig.cs` in the method `Configure` the following command:
```csharp
  config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
  ```
* Pstrfhbs`-template ember-forms "feed" file object property data property `value` component [flexberry-file](ef2_file.html), the model with which the associated property file property `relatedModel` component and configure the component with the at least URL for file uploads:
* Either directly in `hbs`-template:

Template:

```hbs
    {% raw %}{{flexberry-file
      relatedModel=model
      value=model.myFileProperty
      uploadUrl="<The address of the node on which the deployed OData service>/api/File"
    }}{% endraw %}
    ```

* Either `value` and `relatedModel` in the template, and `uploadUrl` in the application's configuration file (not to specify it every time we use the component):

Template:

```hbs
    {% raw %}{{flexberry-file
      relatedModel=model
      value=model.myFileProperty
    }}{% endraw %}
    ```

The application configuration file (`config/environment.js`):
```javascript
    {
      ...
      APP: {
        ...
        components: {
          flexberryFile: {
            uploadUrl: '<Адрес узла, на котором развернут OData-сервис>/api/File'
          }
        }
        ...
      }
      ...
    }
    ```
* Success! Working with files using OData service established.

If you want to know the details of how OData service is implemented to work with files, read the rest of the article.

## File object properties data .NET and DBMS

To work with files in relation to the properties of data objects, the first thing you need to do is determine the type of data that will be used by the file object properties data.
OData service supports two data types for such properties:
* `ICSSoft.STORMNET.FileType.File` - the data type that will store files directly in the object data base64-string, and hence in the database, the files will be stored base64 string in the table corresponding to the type of the data object.
* The benefits of using this type of data file is guaranteed to be part of the data object, and cannot exist separately from him, if you delete a data object, it is guaranteed to be deleted and file, leaving no traces in the system.
* Cons of using this type of data - large files will not work to keep that way.
* `ICSSoft.STORMNET.UserDataTypes` - the Assembly in which it is situated .NET-data type, it is necessary to specify the map types in the properties for the stage.
* `NVARCHAR(MAX)` - corresponding data type for the card types MS SQL Server.
* `TEXT` - corresponding data type for the card types PostgreSQL Server.
* `ICSSoft.STORMNET.UserDataTypes.WebFile` - the data type that will store the files in a file system server a data object only the meta-description (name with extension, size and url where the file can be downloaded)
* The pros of using this type of data thus can be stored in the bind to data objects of any files of any size.
* Cons of using this type of data files in this case is separated from the data objects and the integrity of the connection file to a data object cannot be guaranteed at 100%. The file can theoretically be changed/moved/deleted from the file system without any changes to the meta description, which is stored in the data object. When deleting a data object, ORM cannot always check whether there is somewhere in the file system with a file associated with it, if the file property in the data object is not loaded, or if the data object is one of datalow deleted object data aggregator, and detaily again is not loaded, then the data object will be deleted, and its associated files will remain "live" in the file system.
* `ICSSoft.STORMNET.UserDataTypes` - the Assembly in which it is situated .NET-data type, it is necessary to specify the map types in the properties for the stage.
* `NVARCHAR(MAX)` - corresponding data type for the card types MS SQL Server.
* `TEXT` - corresponding data type for the card types PostgreSQL Server.

When the data type determined, you need to specify the appropriate type from the properties of the data object, to which are attached any files, and to register this type in the map types in the properties of the stage (specify there the full path to the data type and the Assembly where it is) and prescribe the right type in the card types used by the DBMS (see figure below).

![](/images/pages/products/flexberry-ember/ember-flexberry-data/efd2_work-files/flexberry-designer-files-types.jpg)

## File object properties data in ember

In client models ember-files regardless .NET-data type are always serialized JSON object that contains the meta-description file. Because the meta description comes serialized, i.e. line, from the point of view ember-and it is absolutely no different from any other string type properties `string`, but in the addon [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/transforms/file.js) it has made a special transformation `file` to file properties can be distinguished from the others (thus, for example, a component `flexberyy-groupedit` understands that to work with a property of this type need to embed the component `flexberry-file`, not just `flexberry-textbox`).

Client model data object with a file property (image above) will look like the following:

```javascript
import DS from 'ember-data';
import BaseModel from 'ember-flexberry/models/base';
import Proj from 'ember-flexberry-projections';

var Model = BaseModel.extend({
  order: DS.attr('number'),
  file: DS.attr('file'),
  suggestion: DS.belongsTo('flexberry-ember-demo-suggestion', { inverse: 'files', async: false })
});

Model.defineProjection('SuggestionFileE', 'flexberry-ember-demo-suggestion-file', {
  order: Proj.attr('Order'),
  file: Proj.attr('File')
});

export default Model;
```

## Providers a file of the properties in the OData service
From the OData service file is maintained uniformly, thanks to the providers of file types that implement a common interface `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.IDataObjectFileProvider` from the Assembly `NewPlatform.Flexberry.ORM.ODataService`:

```csharp
namespace NewPlatform.Flexberry.ORM.ODataService.Files.Providers
{
    using System;
    using System.Collections.Generic;
    using System.IO;

    using ICSSoft.STORMNET;

    /// <summary> 
    /// Interface for service providers to file object properties data. 
    /// </summary> 
    public interface IDataObjectFileProvider
    {
        /// <summary> 
        /// Gets the type of file object properties data processed by the provider. 
        /// </summary> 
        Type FilePropertyType { get; }

        /// <summary> 
        /// Gets or sets the path to the directory in which to store the files uploaded to the server using the provider. 
        /// </summary> 
        string UploadsDirectoryPath { get; set; }

        /// <summary> 
        /// Get or sets the base part of the URL for links to the download / delete files. 
        /// </summary> 
        string FileBaseUrl { get; set; }

        /// <summary> 
        /// Performs the retrieve metadata describing the file properties of the data object. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// The file property of the data object for which you want to obtain the file metadata. 
        /// </param> 
        /// <returns> 
        /// Metadata describing the file properties of the data object. 
        /// </returns> 
        FileDescription GetFileDescription(object fileProperty);

        /// <summary> 
        /// Performs the retrieve metadata describing the file properties of the data object. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing file properties. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// Metadata describing the file properties of the data object. 
        /// </returns> 
        FileDescription GetFileDescription(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Performs getting a list of metadata describing the file properties of the object data corresponding to the type <see cref="FilePropertyType"/>. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing file properties. 
        /// </param> 
        /// <returns> 
        /// The list of metadata describing the file properties of the object data corresponding to the type <see cref="FilePropertyType"/>. 
        /// </returns> 
        List<FileDescription> GetFileDescriptions(DataObject dataObject);

        /// <summary> 
        /// Performs the receiving file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing file properties. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// Value of the file object properties data. 
        /// </returns> 
        object GetFileProperty(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Provides getting file properties from a file located at the specified path. 
        /// </summary> 
        /// <param name="filePath"> 
        /// The path to the file. 
        /// </param> 
        /// <returns> 
        /// Value of the file object properties data. 
        /// </returns> 
        object GetFileProperty(string filePath);

        /// <summary> 
        /// Performs retrieving file properties of a data object, its metadata. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will proofread the data object. 
        /// </remarks> 
        /// <param name="fileDescription"> 
        /// Metadata describing the file properties of the data object. 
        /// </param> 
        /// <returns> 
        /// Value of the file object properties data. 
        /// </returns> 
        object GetFileProperty(FileDescription fileDescription);

        /// <summary> 
        /// Performs receive list of file properties of a data object corresponding to the type <see cref="FilePropertyType"/>. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing file properties. 
        /// </param> 
        /// <returns> 
        /// The list of file properties of a data object corresponding to the type <see cref="FilePropertyType"/>. 
        /// </returns> 
        List<object> GetFileProperties(DataObject dataObject);

        /// <summary> 
        /// Performs the receiving file name for the file object properties data. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// The file property of the data object for which you want to get the file name. 
        /// </param> 
        /// <returns> 
        /// The file name. 
        /// </returns> 
        string GetFileName(object fileProperty);

        /// <summary> 
        /// Performs the receiving file name for the file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing a file property for which you want to name. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// The file name. 
        /// </returns> 
        string GetFileName(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Performs the obtaining MIME type for the file object properties data. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// The file property of the data object for which you want to obtain the MIME type. 
        /// </param> 
        /// <returns> 
        /// The MIME type of the file corresponding to the target file. 
        /// </returns> 
        string GetFileMimeType(object fileProperty);

        /// <summary> 
        /// Performs the obtaining MIME type for the file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing a file property for which to get the MIME-type. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// The MIME type of the file corresponding to the target file. 
        /// </returns> 
        string GetFileMimeType(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Provides getting size of the file associated with the data object in bytes. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// The file property of the data object for which you want to get the file size. 
        /// </param> 
        /// <returns> 
        /// Size of file in bytes. 
        /// </returns> 
        long GetFileSize(object fileProperty);

        /// <summary> 
        /// Performs the obtaining MIME type for the file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing a file property for which to get the MIME-type. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// The MIME type of the file corresponding to the target file. 
        /// </returns> 
        long GetFileSize(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Performs the receiving of the data stream for the file object properties data. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// Value of the file object properties data, for which you want to receive a stream of data. 
        /// </param> 
        /// <returns> 
        /// Stream data. 
        /// </returns> 
        Stream GetFileStream(object fileProperty);

        /// <summary> 
        /// Performs the receiving of the data stream for the file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing a file property for which you want to receive a stream of data. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        /// <returns> 
        /// Stream data. 
        /// </returns> 
        Stream GetFileStream(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary> 
        /// Performs the receiving of the data stream for the file object properties data. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will proofread the data object. 
        /// </remarks> 
        /// <param name="fileDescription">metadata describing the file properties of a data object for which you want to receive a stream of data.</param> 
        /// <returns>the data Stream.</returns> 
        Stream GetFileStream(FileDescription fileDescription);

        /// <summary> 
        /// Performs the removal from the file system of the file corresponding to the file property of the data object. 
        /// </summary> 
        /// <param name="fileDescription"> 
        /// The metadata of the file to be deleted. 
        /// </param> 
        void RemoveFile(FileDescription fileDescription);

        /// <summary> 
        /// Performs the removal from the file system of the file corresponding to the file property of the data object. 
        /// </summary> 
        /// <param name="fileProperty"> 
        /// Value of the file object properties data, for which you want to delete. 
        /// </param> 
        void RemoveFile(object fileProperty);

        /// <summary> 
        /// Performs the removal from the file system of the file corresponding to the file property of the data object. 
        /// </summary> 
        /// <remarks> 
        /// If necessary, will be produced dochitcu data object. 
        /// </remarks> 
        /// <param name="dataObject"> 
        /// A data object containing file properties. 
        /// </param> 
        /// <param name="dataObjectFilePropertyName"> 
        /// The name of the file property in the data object. 
        /// </param> 
        void RemoveFile(DataObject dataObject, string dataObjectFilePropertyName);
    }
}
```

This interface implements two providers:
* `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.DataObjectFileProvider` from the Assembly `NewPlatform.Flexberry.ORM.ODataService`
* `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.DataObjectWebFileProvider` from the Assembly `NewPlatform.Flexberry.ORM.ODataService`

If desired, you can implement your own arbitrary file data type, and implement it for such provider.

Each of these providers, in fact, a stateless set of utilities to work with the appropriate file data type, so OData service instantiates them only once and registers them in a special file controller.

## File controller in OData service

Working with files in the OData service is provided by a special file controller `NewPlatform.Flexberry.ORM.The ODataService.Controllers.FileController` from the Assembly `NewPlatform.Flexberry.ORM.ODataService`.
Through it you can access the above mentioned file providers, and with their help, he provides the download of files to the server and download them.

Order via OData service had the opportunity to work with the file controller, it is necessary to register in the service and to determine the route by which he will be available for this `HttpConfiguration` provides an extension method:

```csharp
/// <summary> 
/// Registers the route to upload/download files. 
/// </summary> 
/// <param name="httpConfiguration">the configuration.</param> 
/// <param name="routeName">the Name of a registered route.</param> 
/// <param name="routeTemplate">the route Template is logged.</param> 
/// <param name="uploadsDirectoryPath">the Path to the directory which is designed to store files uploaded to the server.</param> 
/// <param name="dataService">data Service for transactions with the database.</param> 
/// <returns>Was the route.</returns> 
public static IHttpRoute MapODataServiceFileRoute(
    this HttpConfiguration httpConfiguration,
    string routeName,
    string routeTemplate,
    string uploadsDirectoryPath,
    IDataService dataService)
```

His challenge is usually done in the configuration file for the OData service after registering the primary route to access the objects data and is as follows:

```csharp
config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
```

This challenge will compare the file controller address `<address of the host where you deployed the OData-service>/api/File`, and registers in the controller file providers `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.DataObjectFileProvider` and `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.DataObjectWebFileProvider`.

To register file providers the controller contains a static method:

```csharp
/// <summary> 
/// Performs the registration of a provider file properties for the data object. 
/// </summary> 
/// <param name="dataObjectFileProvider"> 
/// Provider file properties for the data object. 
/// </param> 
public static void RegisterDataObjectFileProvider(IDataObjectFileProvider dataObjectFileProvider)
```

If necessary it can be invoked manually, and register own file providers.

Or when you check the controller at once manually specify the desired set of file providers to register need to contact the `MapODataServiceFileRoute` method overload that allows you to:

```csharp
/// <summary> 
/// Registers the route to upload/download files. 
/// </summary> 
/// <param name="httpConfiguration">the configuration.</param> 
/// <param name="routeName">the Name of a registered route.</param> 
/// <param name="routeTemplate">the route Template is logged.</param> 
/// <param name="uploadsDirectoryPath">the Path to the directory which is designed to store files uploaded to the server.</param> 
/// <param name="dataObjectFileProviders"> 
/// Providers file object properties data that will be used to associate files with the data objects. 
/// </param> 
/// <param name="dataService">data Service for transactions with the database.</param> 
/// <returns>Was the route.</returns> 
public static IHttpRoute MapODataServiceFileRoute(
    this HttpConfiguration httpConfiguration,
    string routeName,
    string routeTemplate,
    string uploadsDirectoryPath,
    IEnumerable<IDataObjectFileProvider> dataObjectFileProviders,
    IDataService dataService)
```

In addition to the method `RegisterDataObjectFileProvider` file controller contains several auxiliary static methods that are mainly used for testing, and probably in the manual to use them is not required.

### File download to the server

Upload files to the server is performed by the handler POST requests file controller:

```csharp
/// <summary> 
/// Performs the uploading of files to the server. 
/// </summary> 
/// <remarks> 
/// The files are downloaded to the file system in the directory <see cref="UploadsDirectoryPath"/>/{UploadedFileKey}, 
/// where UploadedFileGuid - <see cref="Guid"/> that identifies the downloaded file. 
/// </remarks> 
/// <returns> 
/// Description of uploaded file. 
/// </returns> 
[HttpPost]
public Task<FileDescription> Post()
```

The handler functions are as follows:
* Asynchronously reads the uploaded file from the body of a POST request
* If the file is successfully deducted from the body of a POST request, the "is" in the directory when you register a controller was specified as the directory for the downloaded files (for clarity let it be "~/Uploads"), it creates a subdirectory that refers to the newly generated GUID-Ohm (e.g. "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe") and stores the downloaded file in this directory (if the downloaded file is called image.png, after the handler is finished, the picture will be like this "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png"). This GUID is used due to the fact that the names of the various files can basically be the same, and if they are put directly into the directory "~/Uploads", then potentially they will have each other to talk.
* Then the handler returns the meta description of the uploaded file, which the client will be able to download it, or associate with the file a property of some object data for the newly uploaded file is the meta description will look as follows:

```javascript
{
  // URL for downloading the file. 
  "fileUrl":"https://flexberry-ember-dummy.azurewebsites.net/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe&fileName=image.png",

  // URL for download of a preview (if the file is an image). 
  "previewUrl":"https://flexberry-ember-dummy.azurewebsites.net/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe&fileName=image.png&getPreview=true",

  // The file name. 
  "fileName":"image.png",

  // File size in bytes. 
  "fileSize": 12345,

  // The MIME type of the file. 
  "fileMimeType": "image/png"
}
```

Also, the processor provides the possibility, when you download another file, delete the previously uploaded file that is not useful (for example, the user chose one file, uploaded it to the server but not yet linked to a data object, and then changed his mind and decided to upload some other file) in this case, you can send in the request body, in the property `formData.previousFileDescription` previously downloaded file and it will be removed from the file system of the server, after successful upload the new file (the previously mentioned component [flexberry-file](ef2_file.html) does so, indicates `formData.previousFileDescription` if necessary).

### Binding a file to a property of the data object

The file is simply uploaded to the file system of the server itself is of little value, you should still associate it with the file property of the data object. This deals `DataObjectController` for all with the data objects in the OData service, the processing of create/update data objects.
As it happens we consider an example of a save data object type `Suggestion` with detaylari type `SuggestionFile` that have the file property `File` type `ICSSoft.STORMNET.FileType.File` (see class diagram at the beginning of the article).

Let the aggregator `Suggestion` there is one detail `SuggestionFile`, and this detail as the file is selected described in the previous section the file "image.png" has already been uploaded to the server path "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png" and having a meta description:

```javascript
{
  "fileUploadKey": "0d57629c-7d6e-4847-97cb-9e2fc25083fe", // GUID that is used as a name for the directory in which the file is stored. 
  "fileName": "image.png", // The file name. 
  "fileSize": 12345, // File size in bytes. 
  "fileMimeType": "image/png" // The MIME type corresponding to the file. 
}
```

This aggregator is sent to the preserve via the OData service, and into the processor POST-taprov (in the case of saving a new object) or to the handler PATCH requests (in the case of updating an existing object), in `DataObjectController`.
A data object in this handler comes in the form of a JSON object, which in addition to other properties, in the property `file` contains the above meta-description file "image.png".
To carry out the preservation of the object through the ORM, `DataObjectController` creates a data object (and in the case that updated an existing object shall he the primary key by calling a method [`SetExistObjectPrimaryKey`](fo_data-object.html#SetExistObjectPrimaryKey)).
The controller then begins to iterate through the properties of the obtained JSON object, maps them to the properties of the data object, retrieves from the data object information about the type of these properties, and finally when you know the type, provides type conversion and oznachaet properties of the data object with values obtained from the JSON object.
When this analysis comes to properties properties `file`, `DataObjectController` checks if file controller provider for the type `ICSSoft.STORMNET.FileType.File` that has this property, and if such provider is registered, `DataObjectController` concludes that it is a file property, retrieves from the file controller link for the desired provider, and refers to him, so that on the basis of existing meta descriptions formed the value of the appropriate type (in this case `ICSSoft.STORMNET.FileType.File`), the provider's meta description restores the path where the file is located in the file system, converts it to base64 string, creates an object of type `ICSSoft.STORMNET.FileType.File`, and puts base64 string, then the returned object type `ICSSoft.STORMNET.FileType.File` affix to the property `file` data object and the file on the file system marks for deletion, and, if successful, save the data object, it will be deleted from the file system.
Part of the logic `DataObjectController`-responsible for working with the file properties when creating/altering objects as follows:

```javascript
// If the property type is one of the registered providers in the file properties 
// means the property file, and it should be processed in a special way. 
if (FileController.HasDataObjectFileProvider(dataObjectPropertyType))
{
    IDataObjectFileProvider dataObjectFileProvider = FileController.GetDataObjectFileProvider(dataObjectPropertyType);

    // Processing the file properties of data objects. 
    string serializedFileDescription = value as string;
    if (serializedFileDescription == null)
    {
        // File has been flushed to the client. 
        // The associated file must be deleted after saving the changes. 
        // Remember to do this, the metadata of the associated file before the property will be reset 
        // (for the metadata property is read into the data object). 
        // File property File stores the data in the associated file in the database 
        // from the file system there is simply nothing to delete 
        // so we bypass it and avoid superfluous vizitok of files from the database. 
        if (dataObjectPropertyType != typeof(File))
        {
            _removingFileDescriptions.Add(dataObjectFileProvider.GetFileDescription(obj, dataObjectPropName));
        }

        // Reset file property in the mutable data object. 
        Information.SetPropValueByName(obj, dataObjectPropName, null);
    }
    else
    {
        // The file property was changed, but not reset. 
        // If metadata file exists FileUploadKey means the file has been uploaded to the server 
        // but has not yet been associated with the data object, and it should be done. 
        FileDescription fileDescription = FileDescription.FromJson(serializedFileDescription);
        if (!(string.IsNullOrEmpty(fileDescription.FileUploadKey) || string.IsNullOrEmpty(fileDescription.FileName)))
        {
            Information.SetPropValueByName(obj, dataObjectPropName, dataObjectFileProvider.GetFileProperty(fileDescription));

            // File property File stores the data in the associated file in the database 
            // therefore, after successfully saving the data object, assotsiirovannye with it the file should be deleted from the file system. 
            // For this memorable description of the uploaded file. 
            if (dataObjectPropertyType == typeof(File))
            {
                _removingFileDescriptions.Add(fileDescription);
            }
        }
    }
```

As can be seen from the above part of the code `DataObjectController`-and for deleting the file is enough to put `null` as the value of the file object properties data (then, if successful, save the changes, the file property will be reset and the associated file will be deleted).

If the property `file` had type `ICSSoft.STORMNET.FileType.WebFile` the meaning would be the same, only the file would not be converted to base64 string and not removed then from the file system, and would remain on "permanent residence" on the path "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png" and in the file property of the data object (and the database) would remain the meta-description file that contains the URL of the file controller and fileUploadKey (`<Address of the host where the deployed OData service>/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe`).

After the successful saving of the data object, `DataObjectController` returns it to the client as a JSON object, and carried out the binding file with the file property in the data object, the meta-description of the file, which will return to client property `file` will change, it will not be download key `fileUploadKey`, instead it will have properties specifying the type of the data object, its primary key, and the name of the property that holds the file:

```javascript
{
  // URL for downloading the file. 
  "fileUrl":"<The address of the node on which the deployed OData service>/api/File?entityTypeName=MyNameSpace.SuggestionFile, MyAssembly, Version=1.0.0.0, Culture=neutral, PublicKeyToken=xxxxxxxxxxxxxxxx&entityPrimarykey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx&entityPropertyname=File&fileName=image.png",

  // URL for download of a preview (if the file is an image). 
  "previewUrl":"<The address of the node on which the deployed OData service>/api/File?entityTypeName=MyNameSpace.SuggestionFile, MyAssembly, Version=1.0.0.0, Culture=neutral, PublicKeyToken=xxxxxxxxxxxxxxxx&entityPrimarykey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx&entityPropertyname=File&fileName=image.png&getPreview=true",

  // The file name. 
  "fileName":"image.png",

  // File size in bytes. 
  "fileSize": 12345,

  // The MIME type of the file. 
  "fileMimeType": "image/png"
}
```

### File download

Downloading files from the server is carried out by the handler for GET requests file controller:

```csharp
/// <summary> 
/// Performs the downloading of files from the server. 
/// Depending on flag values in <paramref name="getPreview"/> returns either the file content or the file as attachment. 
/// </summary> 
/// <param name="fileDescription">Description of the requested file.</param> 
/// <param name="getPreview">Parameter that determines whether the file is just for preview (if the value is <c>true</c>), or you want to download and save.</param> 
/// <returns>the Description of the uploaded file.</returns> 
[HttpGet]
public HttpResponseMessage Get([FromUri] FileDescription fileDescription = null, [FromUri] bool getPreview = false)
```

As the main parameter handler takes the meta description of the downloaded file (`fileDescription`),
and the optional parameter is a flag which determines whether the file just to preview, or you need to download the attachment and then save on the client device (`getPreview` default) flag value is set to `false`, and therefore, by default, the requested file will be downloaded as attachments, but if this flag is set to `true`, the file will be returned as a base64-string is represented using the [Data URL](https://ru.wikipedia.org/wiki/Data:_URL), in the case of such image data can be expose as attribute `src` tag `img` (`<img src=...></img>`), in the previously mentioned component [flexberry-file](ef2_file.html) and implement preview for image files.

Having a meta description of the file, the handler looks at the composition properties in it, and depending on the composition acts a bit differently:
* If you have properties `entityTypeName`, `entityPrimaryKey`, `entityPropertyName` in the meta description, the handler understands that the file was already associated with the data object, reads it, retrieves the file, and using the appropriate property file the provider extracts the data stream of the file (`FileStream`).
* If you have properties `fileUploadKey` in the meta description, the handler understands that the file has not yet been associated with the data object, and then stored in the file system directory referred to as `fileUploadKey`, so no need to read any data object, we can immediately obtain a data stream of the file (`FileStream`). And because the type `ICSSoft.STORMNET.FileType.WebFile` just stores files in the file system on the key `fileUploadKey`, the handler uses `NewPlatform.Flexberry.ORM.The ODataService.Files.Providers.DataObjectWebFileProvider` for these purposes.

Part of the logic of the handler responsible for receiving a data stream file based on the meta descriptions as follows:

```csharp
/// <summary> 
/// Performs receive data stream for the requested file (and the file name, MIME type, and size in bytes). 
/// </summary> 
/// <param name="fileDescription">file Description.</param> 
/// <param name="fileName">file Name.</param> 
/// <param name="fileMimeType">the MIME type of the file.</param> 
/// <param name="fileSize">file Size in bytes.</param> 
/// <returns>a data Stream for the requested file.</returns> 
private Stream GetFileStream(
    FileDescription fileDescription,
    out string fileName,
    out string fileMimeType,
    out long fileSize)
{
    if (fileDescription == null)
    {
        throw new ArgumentNullException(nameof(fileDescription));
    }

    Stream fileStream = null;
    Type dataObjectType = null;
    Type filePropertyType = null;

    if (!string.IsNullOrEmpty(fileDescription.EntityPrimaryKey))
    {
        // The requested file has already been associated with the data object, and subtract from it the file property. 
        dataObjectType = Type.GetType(fileDescription.EntityTypeName, true);
        filePropertyType = Information.GetPropertyType(dataObjectType, fileDescription.EntityPropertyName);
    }
    else
    {
        // The requested file has not yet been associated with the data object, and so is in the directory downloads 
        // in a subdirectory with the name of fileDescription.FileUplodKey. 
        // Get a file download key is implemented in DataObjectWebFileProvider. 
        filePropertyType = typeof(WebFile);
    }

    if (!HasDataObjectFileProvider(filePropertyType))
    {
        throw new Exception(string.Format("DataObjectFileProvider for \"{0}\" property type not found.", filePropertyType.AssemblyQualifiedName));
    }

    IDataObjectFileProvider dataObjectFileProvider = GetDataObjectFileProvider(filePropertyType);
    object fileProperty = dataObjectFileProvider.GetFileProperty(fileDescription);

    fileStream = dataObjectFileProvider.GetFileStream(fileProperty);
    fileName = dataObjectFileProvider.GetFileName(fileProperty);
    fileMimeType = dataObjectFileProvider.GetFileMimeType(fileProperty);
    fileSize = fileStream.Length;

    return fileStream;
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}