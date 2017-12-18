---
title: Файлы в odata
sidebar: ember-flexberry-data_sidebar
keywords: Flexberry Ember, odata, файлы
summary: Особенности работы с файлами через odata
toc: true
permalink: ru/efd_work-fiels.html
lang: ru
---

## Описание
OData-сервис предоставляет возможность загружать какие-либо файлы на сервер, скачивать их, а также осуществлять их привязку к свойствам объектов данных.
Клиентская часть, в свою очередь, содержит специальную трансформацию для представления файловых свойств на клиенте, и компонент [flexberry-file](ef_file.html) для работы с ними, далее подробнее.

## Файловые свойства объектов данных в .NET и в СУБД
Для работы с файлами в привязке к свойствам объектов данных, первое что необходимо сделать, это определиться с типом данных, который будет использоваться файловыми свойствами объектов данных.
OData-сервис поддерживает два типа данных для таких свойств:
* ICSSoft.STORMNET.FileType.File - тип данных, который будет хранить файлы прямо в объекте данных base64-строкой, а значит и в базе данных, файлы будут храниться base64-строкой в таблице соответствующей типу объекта данных.
  * Плюсы использования этого типа данных - файл гарантированно является частью объекта данных, и не может существовать отдельно от него, если удаляется объект данных, то гарантированно будет удален и файл, не оставив никаких следов в системе.
  * Минусы использования этого типа данных - большие файлы не получится хранить таким образом.
  * ICSSoft.STORMNET.UserDataTypes - сборка, в которой находится .NET-тип данных, её нужно указывать в карте типов в свойствах стадии.
  * NVARCHAR(MAX) - cоответствующий тип данных для карты типов MS SQL Server-а.
  * TEXT - cоответствующий тип данных для карты типов PostgreSQL Server-а.
* ICSSoft.STORMNET.UserDataTypes.WebFile - тип данных, который будет хранить файлы в файловой системе сервера, в объекте данных только его метаописание (наименование с расширением, размер, и url, по которому файл можно скачать)
  * Плюсы использования этого типа данных  - таким образом можно хранить в привязке к объектам данных любые файлы каких угодно размеров.
  * Минусы использования этого типа данных - файлы в этом случае отделены от объектов данных и целостность связи файла с объектом данных не гарантируется на все 100%. Файл теоретически может быть изменен/перемещен/удален из файловой системы без изменений в метаописании, которое хранится в объекте данных. При удалении объекта данных, ORM не всегда может проверить есть ли где-то в файловой системе какой-нибудь файл ассоциированный с ним, если файловой свойство в объекте данных не загружено, или если объект данных является одним из детейлов удаляемого объекта данных/агрегатора, а детейлы опять же не загружены, тогда объект данных удалится, а ассоциированные с ним файлы так и останутся "жить" в файловой системе.
  * ICSSoft.STORMNET.UserDataTypes - сборка, в которой находится .NET-тип данных, её нужно указывать в карте типов в свойствах стадии.
  * NVARCHAR(MAX) - cоответствующий тип данных для карты типов MS SQL Server-а.
  * TEXT - cоответствующий тип данных для карты типов PostgreSQL Server-а.

Когда с типом данных определились, нужно указать соответствующий тип у свойства объекта данных, к которому будут привязаны какие-либо файлы, и прописать этот тип в карте типов в свойствах стадии (указать там полный путь к типу данных и сборку, в которой он находится), а также прописать нужный тип в карте типов используемой СУБД (см. рисунок ниже).

![](/images/pages/products/flexberry-ember/ember-flexberry-data/efd_work-files/flexberry-designer-files-types.jpg)

## Файловые свойства объектов данных в ember
В клиентских моделях ember-а файлы, независимо от выбранного .NET-типа данных, всегда представляются сериализованным JSON-объектом, который содержит метаописание файла, и хоть с точки зрения ember-а это метаописание совершенно ничем не отличается от любой другой строки, в аддоне [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/transforms/file.js) под него все-таки сделана специальная трансформация `file`, для того чтобы файловые свойства можно было отличать от остальных (таким образом, например, компонент `flexberyy-groupedit` понимает, что для работы со свойством такого типа нужно встраивать компонент `flexberry-file`, а не просто `flexberry-textbox`).

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

### Загрузка файла на сервер

### Привязка файла с свойству объекта данных

### Скачивание файла
