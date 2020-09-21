---
title: Файлы в odata
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, odata, файлы
summary: Особенности работы с файлами через OData-сервис
toc: true
permalink: ru/efd2_work-files.html
lang: ru
---

## Описание

OData-сервис предоставляет возможность загружать какие-либо файлы на сервер, скачивать их, а также осуществлять их привязку к свойствам объектов данных.
Клиентская часть, в свою очередь, содержит специальную трансформацию для представления файловых свойств на клиенте, и компонент [flexberry-file](ef2_file.html) для работы с ними, далее подробнее.

## Быстрый старт

Если вам нужно по быстрому наладить работу с файлами через OData-сервис, не изучая всех подробностей того как в сервисе реализована работа с ними, то краткий алгоритм будет таким:
* Проставьте файловому свойству .NET-класса объекта данных тип `ICSSoft.STORMNET.FileType.File` (если хотите хранить файлы в БД) или `ICSSoft.STORMNET.UserDataTypes.WebFile` (если хотите хранить файлы в файловой системе)
* Проставьте файловому свойству ember-модели тип `DS.attr('file')`
* В БД проставьте этому свойству тип `NVARCHAR(MAX)` (если работаете с MS SQL Server) или тип `TEXT` (если работаете с PostrgreSQL)
* Зарегистрируйте файловый контроллер в OData-сервисе, прописав в классе `App_Start\ODataConfig.cs` в методе `Configure` следующую команду:
  ```csharp
  config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
  ```
* В `hbs`-шаблоне ember-формы "скормите" файловое свойство объекта данных свойству `value` компонента [flexberry-file](ef2_file.html), модель, с которой ассоциировано файловое свойство свойству `relatedModel` компонента, и сконфигурируйте компонент указав ему как минимум URL-для для загрузки файлов:
  * Либо прямо в `hbs`-шаблоне:

    Шаблон:

    ```hbs
    {% raw %}{{flexberry-file
      relatedModel=model
      value=model.myFileProperty
      uploadUrl="<Адрес узла, на котором развернут OData-сервис>/api/File"
    }}{% endraw %}
    ```

  * Либо `value` и `relatedModel` в шаблоне, а `uploadUrl` в конфигурационном файле приложения (чтобы не указывать его каждый раз, когда используем компонент):

    Шаблон:

    ```hbs
    {% raw %}{{flexberry-file
      relatedModel=model
      value=model.myFileProperty
    }}{% endraw %}
    ```

    Конфигурационный файл приложения (`config/environment.js`):
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
* Успех! Работа с файлами через OData-сервис налажена.

Если хотите знать подробности того как в OData-сервисе реализована работа с файлами, читайте продолжение статьи.

## Файловые свойства объектов данных в .NET и в СУБД

Для работы с файлами в привязке к свойствам объектов данных, первое что необходимо сделать, это определиться с типом данных, который будет использоваться файловыми свойствами объектов данных.
OData-сервис поддерживает два типа данных для таких свойств:
* `ICSSoft.STORMNET.FileType.File` - тип данных, который будет хранить файлы прямо в объекте данных base64-строкой, а значит и в базе данных, файлы будут храниться base64-строкой в таблице соответствующей типу объекта данных.
  * Плюсы использования этого типа данных - файл гарантированно является частью объекта данных, и не может существовать отдельно от него, если удаляется объект данных, то гарантированно будет удален и файл, не оставив никаких следов в системе.
  * Минусы использования этого типа данных - большие файлы не получится хранить таким образом.
  * `ICSSoft.STORMNET.UserDataTypes` - сборка, в которой находится .NET-тип данных, её нужно указывать в карте типов в свойствах стадии.
  * `NVARCHAR(MAX)` - cоответствующий тип данных для карты типов MS SQL Server-а.
  * `TEXT` - cоответствующий тип данных для карты типов PostgreSQL Server-а.
* `ICSSoft.STORMNET.UserDataTypes.WebFile` - тип данных, который будет хранить файлы в файловой системе сервера, в объекте данных только его метаописание (наименование с расширением, размер, и url, по которому файл можно скачать)
  * Плюсы использования этого типа данных  - таким образом можно хранить в привязке к объектам данных любые файлы каких угодно размеров.
  * Минусы использования этого типа данных - файлы в этом случае отделены от объектов данных и целостность связи файла с объектом данных не гарантируется на все 100%. Файл теоретически может быть изменен/перемещен/удален из файловой системы без изменений в метаописании, которое хранится в объекте данных. При удалении объекта данных, ORM не всегда может проверить есть ли где-то в файловой системе какой-нибудь файл ассоциированный с ним, если файловой свойство в объекте данных не загружено, или если объект данных является одним из детейлов удаляемого объекта данных/агрегатора, а детейлы опять же не загружены, тогда объект данных удалится, а ассоциированные с ним файлы так и останутся "жить" в файловой системе.
  * `ICSSoft.STORMNET.UserDataTypes` - сборка, в которой находится .NET-тип данных, её нужно указывать в карте типов в свойствах стадии.
  * `NVARCHAR(MAX)` - cоответствующий тип данных для карты типов MS SQL Server-а.
  * `TEXT` - cоответствующий тип данных для карты типов PostgreSQL Server-а.

Когда с типом данных определились, нужно указать соответствующий тип у свойства объекта данных, к которому будут привязаны какие-либо файлы, и прописать этот тип в карте типов в свойствах стадии (указать там полный путь к типу данных и сборку, в которой он находится), а также прописать нужный тип в карте типов используемой СУБД (см. рисунок ниже).

![](/images/pages/products/flexberry-ember/ember-flexberry-data/efd2_work-files/flexberry-designer-files-types.jpg)

## Файловые свойства объектов данных в ember

В клиентских моделях ember-а файлы, независимо от выбранного .NET-типа данных, всегда представляются сериализованным JSON-объектом, который содержит метаописание файла. Т.к. метаописание приходит сериализованным, т.е. строкой, то с точки зрения ember-а оно совершенно ничем не отличается от любого другого строкового свойства типа `string`, однако в аддоне [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/transforms/file.js) под него все-таки сделана специальная трансформация `file`, для того чтобы файловые свойства можно было отличать от остальных (таким образом, например, компонент `flexberyy-groupedit` понимает, что для работы со свойством такого типа нужно встраивать компонент `flexberry-file`, а не просто `flexberry-textbox`).

Клиентская модель объекта данных с файловым свойством (с изображения выше) будет выглядеть следующим образом:

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

## Провайдеры файловых свойств в OData-сервисе
Со стороны OData-сервиса работа с файлами ведется единообразно, благодаря провайдерам файловых типов, реализующим общий интерфейс `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.IDataObjectFileProvider` из сборки `NewPlatform.Flexberry.ORM.ODataService`:

```csharp
namespace NewPlatform.Flexberry.ORM.ODataService.Files.Providers
{
    using System;
    using System.Collections.Generic;
    using System.IO;

    using ICSSoft.STORMNET;

    /// <summary>
    /// Интерфейс для провайдеров файловых свойств объектов данных.
    /// </summary>
    public interface IDataObjectFileProvider
    {
        /// <summary>
        /// Получает тип файловых свойств объектов данных, обрабатываемых провайдером.
        /// </summary>
        Type FilePropertyType { get; }

        /// <summary>
        /// Получает или задает путь к каталогу, в котором должны храниться файлы, загруженные на сервер при помощи провайдера.
        /// </summary>
        string UploadsDirectoryPath { get; set; }

        /// <summary>
        /// Получат или задает базовую часть URL-а для ссылок на скачивание / удаление файлов.
        /// </summary>
        string FileBaseUrl { get; set; }

        /// <summary>
        /// Осуществляет получение метаданных с описанием файлового свойства объекта данных.
        /// </summary>
        /// <param name="fileProperty">
        /// Файловое свойство объекта данных, для которого требуется получить метаданные файла.
        /// </param>
        /// <returns>
        /// Метаданные с описанием файлового свойства объекта данных.
        /// </returns>
        FileDescription GetFileDescription(object fileProperty);

        /// <summary>
        /// Осуществляет получение метаданных с описанием файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// Метаданные с описанием файлового свойства объекта данных.
        /// </returns>
        FileDescription GetFileDescription(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение списка метаданных с описанием файловых свойств объекта данных, соответствующих типу <see cref="FilePropertyType"/>.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловые свойства.
        /// </param>
        /// <returns>
        /// Список метаданных с описанием файловых свойств объекта данных, соответствующих типу <see cref="FilePropertyType"/>.
        /// </returns>
        List<FileDescription> GetFileDescriptions(DataObject dataObject);

        /// <summary>
        /// Осуществляет получение файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// Значение файлового свойства объекта данных.
        /// </returns>
        object GetFileProperty(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение файлового свойства из файла, расположенного по заданному пути.
        /// </summary>
        /// <param name="filePath">
        /// Путь к файлу.
        /// </param>
        /// <returns>
        /// Значение файлового свойства объекта данных.
        /// </returns>
        object GetFileProperty(string filePath);

        /// <summary>
        /// Осуществляет получение файлового свойства объекта данных, по его метаданным.
        /// </summary>
        /// <remarks>
        /// При необходимости будет  вычитан объект данных.
        /// </remarks>
        /// <param name="fileDescription">
        /// Метаданные с описанием файлового свойства объекта данных.
        /// </param>
        /// <returns>
        /// Значение файлового свойства объекта данных.
        /// </returns>
        object GetFileProperty(FileDescription fileDescription);

        /// <summary>
        /// Осуществляет получение списка файловых свойств объекта данных, соответствующих типу <see cref="FilePropertyType"/>.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловые свойства.
        /// </param>
        /// <returns>
        /// Список файловых свойств объекта данных, соответствующих типу <see cref="FilePropertyType"/>.
        /// </returns>
        List<object> GetFileProperties(DataObject dataObject);

        /// <summary>
        /// Осуществляет получение имени файла для файлового свойства объекта данных.
        /// </summary>
        /// <param name="fileProperty">
        /// Файловое свойство объекта данных, для которого требуется получить имя файла.
        /// </param>
        /// <returns>
        /// Имя файла.
        /// </returns>
        string GetFileName(object fileProperty);

        /// <summary>
        /// Осуществляет получение имени файла для файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство, для которого требуется получить имя.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// Имя файла.
        /// </returns>
        string GetFileName(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение MIME-типа для файлового свойства объекта данных.
        /// </summary>
        /// <param name="fileProperty">
        /// Файловое свойство объекта данных, для которого требуется получить MIME-тип.
        /// </param>
        /// <returns>
        /// MIME-тип файла, соответствующего заданному файловому свойству.
        /// </returns>
        string GetFileMimeType(object fileProperty);

        /// <summary>
        /// Осуществляет получение MIME-типа для файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство, для которого требуется получить MIME-тип.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// MIME-тип файла, соответствующего заданному файловому свойству.
        /// </returns>
        string GetFileMimeType(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение размера файла, связанного с объектом данных, в байтах.
        /// </summary>
        /// <param name="fileProperty">
        /// Файловое свойство объекта данных, для которого требуется получить размер файла.
        /// </param>
        /// <returns>
        /// Размер файла в байтах.
        /// </returns>
        long GetFileSize(object fileProperty);

        /// <summary>
        /// Осуществляет получение MIME-типа для файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство, для которого требуется получить MIME-тип.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// MIME-тип файла, соответствующего заданному файловому свойству.
        /// </returns>
        long GetFileSize(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение потока данных для файлового свойства объекта данных.
        /// </summary>
        /// <param name="fileProperty">
        /// Значение файлового свойства объекта данных, для которого требуется получить поток данных.
        /// </param>
        /// <returns>
        /// Поток данных.
        /// </returns>
        Stream GetFileStream(object fileProperty);

        /// <summary>
        /// Осуществляет получение потока данных для файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство, для которого требуется получить поток данных.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        /// <returns>
        /// Поток данных.
        /// </returns>
        Stream GetFileStream(DataObject dataObject, string dataObjectFilePropertyName);

        /// <summary>
        /// Осуществляет получение потока данных для файлового свойства объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет  вычитан объект данных.
        /// </remarks>
        /// <param name="fileDescription">Метаданные с описанием файлового свойства объекта данных, для которого требуется получить поток данных.</param>
        /// <returns>Поток данных.</returns>
        Stream GetFileStream(FileDescription fileDescription);

        /// <summary>
        /// Осуществляет удаление из файловой системы файла, соответствующего файловому свойству объекта данных.
        /// </summary>
        /// <param name="fileDescription">
        /// Метаданные удаляемого файла.
        /// </param>
        void RemoveFile(FileDescription fileDescription);

        /// <summary>
        /// Осуществляет удаление из файловой системы файла, соответствующего файловому свойству объекта данных.
        /// </summary>
        /// <param name="fileProperty">
        /// Значение файлового свойства объекта данных, для которого требуется выполнить удаление.
        /// </param>
        void RemoveFile(object fileProperty);

        /// <summary>
        /// Осуществляет удаление из файловой системы файла, соответствующего файловому свойству объекта данных.
        /// </summary>
        /// <remarks>
        /// При необходимости будет произведена дочитка объекта данных.
        /// </remarks>
        /// <param name="dataObject">
        /// Объект данных, содержащий файловое свойство.
        /// </param>
        /// <param name="dataObjectFilePropertyName">
        /// Имя файлового свойства в объекте данных.
        /// </param>
        void RemoveFile(DataObject dataObject, string dataObjectFilePropertyName);
    }
}
```

Этот интерфейс реализуют два провайдера:
* `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.DataObjectFileProvider` из сборки `NewPlatform.Flexberry.ORM.ODataService`
* `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.DataObjectWebFileProvider` из сборки `NewPlatform.Flexberry.ORM.ODataService`

При желании можно реализовать собственный произвольный файловый тип данных, и реализовать для него подобный провайдер.

Каждый из этих провайдеров, по сути, просто stateless-набор утилит для работы с соответствующим файловым типом данных, поэтому OData-сервис инстанцирует их только по одному разу и регистрирует в специальном файловом контроллере.

## Файловый контроллер в OData-сервисе

Работа с файлами в OData-сервисе обеспечивается специальным файловым контроллером `NewPlatform.Flexberry.ORM.ODataService.Controllers.FileController` из сборки `NewPlatform.Flexberry.ORM.ODataService`.
Через него осуществляется доступ к упомянутым выше файловым провайдерам, и с их помощью он обеспечивает загрузку файлов на сервер, и их скачивание.

Для того чтобы через OData-сервис была возможность работать с файловым контроллером, его необходимо зарегистрировать в сервисе и определить маршрут, по которому он будет доступен, для этого в `HttpConfiguration` предусмотрен метод расширения:

```csharp
/// <summary>
/// Осуществляет регистрацию маршрута для загрузки/скачивания файлов.
/// </summary>
/// <param name="httpConfiguration">Используемая конфигурация.</param>
/// <param name="routeName">Имя регистрируемого маршрута.</param>
/// <param name="routeTemplate">Шаблон регистрируемого маршрута.</param>
/// <param name="uploadsDirectoryPath">Пути к каталогу, который предназначен для хранения файлов загружаемых на сервер.</param>
/// <param name="dataService">Сервис данных для операций с БД.</param>
/// <returns>Зарегистрированный маршрут.</returns>
public static IHttpRoute MapODataServiceFileRoute(
    this HttpConfiguration httpConfiguration,
    string routeName,
    string routeTemplate,
    string uploadsDirectoryPath,
    IDataService dataService)
```

Его вызов обычно производится в файле конфигурации OData-сервиса после регистрации основного маршрута для доступа к объектам данным, и выглядит следующим образом:

```csharp
config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
```

Этот вызов сопоставит файловый контроллер адресу `<Адрес узла, на котором развернут OData-сервис>/api/File`, и зарегистрирует в контроллере файловые провайдеры `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.DataObjectFileProvider` и `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.DataObjectWebFileProvider`.

Для регистрации файловых провайдеров контроллер содержит статический метод:

```csharp
/// <summary>
/// Осуществляет регистрацию провайдера файловых свойств для объекта данных.
/// </summary>
/// <param name="dataObjectFileProvider">
/// Провайдер файловых свойств для объекта данных.
/// </param>
public static void RegisterDataObjectFileProvider(IDataObjectFileProvider dataObjectFileProvider)
```

При необходимости его можно вызвать вручную, и зарегистрировать собственные файловые провайдеры.

Или при регистрации контроллера, сразу вручную указать желаемый набор файловых провайдеров, только для регистрации потребуется обращаться к той перегрузке метода `MapODataServiceFileRoute`, которая это позволяет:

```csharp
/// <summary>
/// Осуществляет регистрацию маршрута для загрузки/скачивания файлов.
/// </summary>
/// <param name="httpConfiguration">Используемая конфигурация.</param>
/// <param name="routeName">Имя регистрируемого маршрута.</param>
/// <param name="routeTemplate">Шаблон регистрируемого маршрута.</param>
/// <param name="uploadsDirectoryPath">Пути к каталогу, который предназначен для хранения файлов загружаемых на сервер.</param>
/// <param name="dataObjectFileProviders">
/// Провайдеры файловых свойств объектов данных, которые будут использоваться для связывания файлов с объектами данных.
/// </param>
/// <param name="dataService">Сервис данных для операций с БД.</param>
/// <returns>Зарегистрированный маршрут.</returns>
public static IHttpRoute MapODataServiceFileRoute(
    this HttpConfiguration httpConfiguration,
    string routeName,
    string routeTemplate,
    string uploadsDirectoryPath,
    IEnumerable<IDataObjectFileProvider> dataObjectFileProviders,
    IDataService dataService)
```

Помимо метода `RegisterDataObjectFileProvider` файловый контроллер содержит еще несколько вспомогательных статических методов, которые используются в основном для тестирования, и скорей всего, в ручную их использовать не потребуются.

### Загрузка файла на сервер

Загрузка файлов на сервер осуществляется обработчиком POST-запросов файлового контроллера:

```csharp
/// <summary>
/// Осуществляет загрузку файлов на сервер.
/// </summary>
/// <remarks>
/// Файлы загружаются в файловую систему, в каталог <see cref="UploadsDirectoryPath"/>/{UploadedFileKey},
/// где UploadedFileGuid - <see cref="Guid"/>, идентифицирующий загруженный файл.
/// </remarks>
/// <returns>
/// Описание загруженного файла.
/// </returns>
[HttpPost]
public Task<FileDescription> Post()
```

Обработчик действует следующим образом:
* Асинхронно вычитывает загружаемый файл из тела POST-запроса
* В случае, если файл успешно вычитан из тела POST-запроса, "идет" в тот каталог, который при регистрации контроллера был указан в качестве каталога для загружаемых файлов (для наглядности пусть это будет "~/Uploads"), создает там подкаталог, который именует только что сгенерированным GUID-ом (например так "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe") и сохраняет загруженный файл в этом каталоге (если загруженный файл называется image.png, то после того как обработчик закончит работу картина будет такой "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png"). GUID используется из-за того что имена различных файлов в принципе могут и совпадать, и если их складывать прямо в каталог "~/Uploads", то потенциально они будут друг-друга перетирать.
* Затем обработчик возвращает метаописание загруженного файла, по которому клиент сможет его скачать, либо связать с файловым свойством какого-нибудь объекта данных, для только что загруженного файла это метаописание будет выглядеть следующим образом:

```javascript
{
  // URL для скачивания файла.
  "fileUrl":"https://flexberry-ember-dummy.azurewebsites.net/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe&fileName=image.png",

  // URL для скачивания preview (если файл это изображение).
  "previewUrl":"https://flexberry-ember-dummy.azurewebsites.net/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe&fileName=image.png&getPreview=true",

  // Наименование файла.
  "fileName":"image.png",

  // Размер файла в байтах.
  "fileSize": 12345,

  // MIME-тип файла.
  "fileMimeType": "image/png"
}
```

Также в обработчике предусмотрена возможность, при загрузке очередного файла, удалить ранее загруженный файл, который не пригодился (например пользователь выбрал один файл, загрузил его на сервер, но еще не связывал с объектом данных, а потом передумал и решил загрузить какой-то другой файл) в этом случае можно отправить в теле запроса, в свойстве `formData.previousFileDescription` ранее загруженного файла, и он будет удален из файловой системы сервера, после успешной загрузки нового файла (ранее упомянутый компонент [flexberry-file](ef2_file.html) так и делает, указывает `formData.previousFileDescription` при необходимости).

### Привязка файла к свойству объекта данных

Файл просто загруженный в файловую систему сервера, сам по себе не представляет большой ценности, нужно еще связать его с файловым свойством объекта данных. Этим занимается `DataObjectController`, обеспечивающий всю работу с объектами данных в OData-сервисе, при обработке создания/обновления объектов данных.
Как это происходит разберем на примере сохранения объекта данных типа `Suggestion` с детейлами типа `SuggestionFile`, в которых имеется файловое свойство `File` типа `ICSSoft.STORMNET.FileType.File` (см. диаграмму классов в начале статьи).

Пусть у агрегатора `Suggestion` имеется один детейл `SuggestionFile`, и у этого детейла в качестве файла выбран описанный в предыдущем разделе файл "image.png", уже загруженный на сервер по пути "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png" и имеющий метаописание:

```javascript
{
  "fileUploadKey": "0d57629c-7d6e-4847-97cb-9e2fc25083fe", // GUID, который использован в качестве наименования для каталога, в котором хранится файл.
  "fileName": "image.png", // Наименование файла.
  "fileSize": 12345, // Размер файла в байтах.
  "fileMimeType": "image/png" // MIME-тип, соответствующий файлу.
}
```

Этот агрегатор отправляется на сохранение через OData-сервис, и попадает в обработчик POST-запрсов (в случае сохранения нового объекта) или в обработчик PATCH-запросов (в случае обновления существующего объекта), в `DataObjectController`-е.
Объект данных в этот обработчик приходит в виде JSON-объекта, у которого помимо прочих свойств, в свойстве `file` содержится приведенное выше метаописание файла "image.png".
Чтобы осуществить сохранение объекта через ORM, `DataObjectController` создает объект данных (и в случае, если обновляется уже существующий объект проставляет ему первичный ключ через вызов метода [`SetExistObjectPrimaryKey`](fo_data-object.html#SetExistObjectPrimaryKey)).
Затем контроллер начинает перебирать свойства полученного JSON-объекета, сопоставляет их свойствам объекта данных, извлекает из объекта данных информацию о типе этих свойств, и наконец когда известен тип, осуществляет приведение типов, и означивает свойства объекта данных значениями полученными из JSON-объекта.
Когда этот разбор свойств доходит до свойства `file`, `DataObjectController` проверяет зарегистрирован ли в файловом контроллере провайдер для типа `ICSSoft.STORMNET.FileType.File`, который имеет это свойство, и если такой провайдер зарегистрирован, `DataObjectController` делает вывод что это файловое свойство, извлекает из файлового контроллера ссылку на нужный провайдер, и обращается к нему, чтобы тот на основе имеющегося метаописания сформировал значение нужного типа (в данном случае `ICSSoft.STORMNET.FileType.File`), провайдер по метаописанию восстанавливает путь, по которому файл расположен в файловой системе, преобразует его в base64-строку, создает объект типа `ICSSoft.STORMNET.FileType.File`, и кладет base64-строку в него, затем полученный объект типа `ICSSoft.STORMNET.FileType.File` проставляет в свойство `file` объекта данных, а файл находящийся в файловой системе помечает на удаление, и в случае успешного сохранения объекта данных, он будет удален из файловой системы.
Часть логики `DataObjectController`-а отвечающая за работу с файловыми свойствами при создании/изменении объектов выглядит следующим образом:

```javascript
// Если тип свойства относится к одному из зарегистрированных провайдеров файловых свойств,
// значит свойство файловое, и его нужно обработать особым образом.
if (FileController.HasDataObjectFileProvider(dataObjectPropertyType))
{
    IDataObjectFileProvider dataObjectFileProvider = FileController.GetDataObjectFileProvider(dataObjectPropertyType);

    // Обработка файловых свойств объектов данных.
    string serializedFileDescription = value as string;
    if (serializedFileDescription == null)
    {
        // Файловое свойство было сброшено на клиенте.
        // Ассоциированный файл должен быть удален, после успешного сохранения изменений.
        // Для этого запоминаем метаданные ассоциированного файла, до того как свойство будет сброшено
        // (для получения метаданных свойство будет дочитано в объект данных).
        // Файловое свойство типа File хранит данные ассоциированного файла прямо в БД,
        // соответственно из файловой системы просто нечего удалять,
        // поэтому обходим его стороной, чтобы избежать лишных вычиток файлов из БД.
        if (dataObjectPropertyType != typeof(File))
        {
            _removingFileDescriptions.Add(dataObjectFileProvider.GetFileDescription(obj, dataObjectPropName));
        }

        // Сбрасываем файловое свойство в изменяемом объекте данных.
        Information.SetPropValueByName(obj, dataObjectPropName, null);
    }
    else
    {
        // Файловое свойство было изменено, но не сброшено.
        // Если в метаданных файла присутствует FileUploadKey значит файл был загружен на сервер,
        // но еще не был ассоциирован с объектом данных, и это нужно сделать.
        FileDescription fileDescription = FileDescription.FromJson(serializedFileDescription);
        if (!(string.IsNullOrEmpty(fileDescription.FileUploadKey) || string.IsNullOrEmpty(fileDescription.FileName)))
        {
            Information.SetPropValueByName(obj, dataObjectPropName, dataObjectFileProvider.GetFileProperty(fileDescription));

            // Файловое свойство типа File хранит данные ассоциированного файла прямо в БД,
            // поэтому после успешного сохранения объекта данных, оссоциированный с ним файл должен быть удален из файловой системы.
            // Для этого запоминаем описание загруженного файла.
            if (dataObjectPropertyType == typeof(File))
            {
                _removingFileDescriptions.Add(fileDescription);
            }
        }
    }
```

Как видно из приведенной выше части кода `DataObjectController`-а, для удаления файла достаточно проставить `null` в качестве значения файлового свойства объекта данных (тогда, в случае успешного сохранения изменений, файловое свойство будет сброшено, а ассоциированный файл будет удален).

Если бы свойство `file` имело тип `ICSSoft.STORMNET.FileType.WebFile` смысл был бы тот же самый, только файл бы не преобразовывался в base64-строку и не удалялся бы потом из файловой системы, а так бы и остался на "постоянном месте жительства" по пути "~/Uploads/0d57629c-7d6e-4847-97cb-9e2fc25083fe/image.png", а в файловом свойстве объекта данных (и соответственно в БД) сохранилось бы метаописание файла, содержащее URL-адрес файлового контроллера и fileUploadKey (`<Адрес узла, на котором развернут OData-сервис>/api/File?fileUploadKey=0d57629c-7d6e-4847-97cb-9e2fc25083fe`).

После успешного сохранения объекта данных, `DataObjectController` возвращает его на клиент в виде JSON-объекта, и после того как осуществлено связывание файла с файловым свойством в объекте данных, метаописание файла, которое вернется на клиент в свойстве `file` несколько изменится, в нем уже не будет ключа загрузки `fileUploadKey`, вместо него будут свойства указывающие на тип объекта данных, его первичный ключ, и имя свойства, в котором хранится файл:

```javascript
{
  // URL для скачивания файла.
  "fileUrl":"<Адрес узла, на котором развернут OData-сервис>/api/File?entityTypeName=MyNameSpace.SuggestionFile, MyAssembly, Version=1.0.0.0, Culture=neutral, PublicKeyToken=xxxxxxxxxxxxxxxx&entityPrimaryKey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx&entityPropertyName=File&fileName=image.png",

  // URL для скачивания preview (если файл это изображение).
  "previewUrl":"<Адрес узла, на котором развернут OData-сервис>/api/File?entityTypeName=MyNameSpace.SuggestionFile, MyAssembly, Version=1.0.0.0, Culture=neutral, PublicKeyToken=xxxxxxxxxxxxxxxx&entityPrimaryKey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx&entityPropertyName=File&fileName=image.png&getPreview=true",

  // Наименование файла.
  "fileName":"image.png",

  // Размер файла в байтах.
  "fileSize": 12345,

  // MIME-тип файла.
  "fileMimeType": "image/png"
}
```

### Скачивание файла

Скачивание файлов с сервера осуществляется обработчиком GET-запросов файлового контроллера:

```csharp
/// <summary>
/// Осуществляет скачивание файлов с сервера.
/// В зависимости от значения флага <paramref name="getPreview"/> возвращается либо содержимое файла, либо файл в виде приложения.
/// </summary>
/// <param name="fileDescription">Описание запрашиваемого файла.</param>
/// <param name="getPreview">Параметр, определяющий, требуется ли файл просто для предпросмотра (если значение <c>true</c>), либо требуется его скачать и сохранить.</param>
/// <returns>Описание загруженного файла.</returns>
[HttpGet]
public HttpResponseMessage Get([FromUri] FileDescription fileDescription = null, [FromUri] bool getPreview = false)
```

В качестве основного параметра обработчик принимает метаописание скачиваемого файла (`fileDescription`),
а в качестве опционального параметра принимает флаг определяющий, требуется ли файл просто для предпросмотра, или же его требуется скачать в виде вложения с последующим сохранением на клиентском устройстве (`getPreview`), по умолчанию флаг имеет значение `false`, а значит по умолчанию запрашиваемые файлы будет скачиваться в виде вложения, но если этот флаг имеет значение `true`, то файл будет возвращаться в виде base64-строки представленной через [Data URL](https://ru.wikipedia.org/wiki/Data:_URL), в случае изображений такие данные можно подставлять в качестве атрибута `src` тега `img` (`<img src=...></img>`), в ранее упомянутом компоненте [flexberry-file](ef2_file.html) так и реализован предпросмотр для файлов изображений.

Получив метаописание файла, обработчик смотрит на состав свойств в нем, и в зависимости от состава действует немного по разному:
* При наличии свойств `entityTypeName`, `entityPrimaryKey`, `entityPropertyName` в метаописании, обработчик понимает что файл уже был связан с объектом данных, вычитывает его, извлекает из него файловое свойство, и с помощью соответствующего свойству файлового провайдера извлекает поток данных файла (`FileStream`).
* При наличии свойства `fileUploadKey` в метаописании, обработчик понимает что файл еще не был был связан с объектом данных, а значит хранится в файловой системе, в каталоге именуемом так же как `fileUploadKey`, значит не нужно предварительно вычитывать никакой объект данных, а можно сразу получить поток данных файла (`FileStream`). А поскольку тип `ICSSoft.STORMNET.FileType.WebFile` как раз хранит файлы в файловой системе по ключу `fileUploadKey`, обработчик использует `NewPlatform.Flexberry.ORM.ODataService.Files.Providers.DataObjectWebFileProvider` для этих целей.

Часть логики обработчика отвечающая за получения потока данных файла, на основе метаописания выглядит следующим образом:

```csharp
/// <summary>
/// Осуществляет получение потока данных для запрашиваемого файла (а также имя файла, MIME-тип, и размер в байтах).
/// </summary>
/// <param name="fileDescription">Описание файла.</param>
/// <param name="fileName">Имя файла.</param>
/// <param name="fileMimeType">MIME-тип файла.</param>
/// <param name="fileSize">Размер файла в байтах.</param>
/// <returns>Поток данных для запрашиваемого файла.</returns>
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
        // Запрашиваемый файл уже был связан с объектом данных, и нужно вычитать из него файловое свойство.
        dataObjectType = Type.GetType(fileDescription.EntityTypeName, true);
        filePropertyType = Information.GetPropertyType(dataObjectType, fileDescription.EntityPropertyName);
    }
    else
    {
        // Запрашиваемый файл еще не был связан с объектом данных, а значит находится в каталоге загрузок,
        // в подкаталоге с именем fileDescription.FileUplodKey.
        // Получение файлов по ключу загрузки реализовано в DataObjectWebFileProvider.
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
