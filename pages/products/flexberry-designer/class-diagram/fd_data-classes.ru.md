---
title: Классы данных и их свойства 
sidebar: flexberry-designer_sidebar
keywords: DataObject, Flexberry Designer, класс данных, стереотип, implementation, представление
summary: Генерация класса данных в СУБД и .Net-язык
toc: true
permalink: ru/fd_data-classes.html
lang: ru
---

Классы данных - это классы со стереотипом implementation или без указания стереотипа.

[Стереотип](fd_key-concepts.html) `implementation` может указываться, а может не указываться, суть одна: UML-класс, соответствующий некоторой предметной сущности.

![](/images/pages/products/flexberry-designer/class-diagram/implementation.png)

{% include note.html content="Классы данных, связанные [иерархией наследования](fd_inheritance.html), [ассоциациями](fd_master-association.html) и [агрегациями](fo_detail-associations-properties.html) друг с другом, образуют так называемую [объектную структуру прикладной системы](fd_key-concepts.html), т.е. структуру предметных сущностей и их отношения." %}


### Что генерируется с класса данных

Что генерируется | Генерация в SQL DDL | Генерация в .Net-язык
:--------------|:---------------------------|:----------------------------------------
`UML`-класс | Определение таблицы: `CREATE TABLE`. Если в БД есть представление,соответствующее имени хранения данного класса, генерируется комментарий с информацией о наличии представления и определением таблицы. | `.Net`-класс, наследующийся от .Net-класса, соответствующего предку по модели. Если в модели у класса нет предка, он наследуется от класса `[ICSSoft.STORMNET.DataObject](fo_data-object.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`
Атрибут UML-класса | Поле в таблице класса ([подробности в статье Атрибуты классов данных](fo_attributes-class-data.html)) | Виртуальное свойство с тем же именем и соответствующим модификатором (# - `protected`, + - `public`, - - `private`) и приватный член класса для этого свойства. Тип свойства и приватного члена - тип атрибута.  ([подробности в статье Атрибуты классов данных](fo_attributes-class-data.html))
Метод UML-класса | | Виртуальный метод с соответствующими параметрами и модификатором. Тело метода пустое со скобкой программиста для внесения кода метода. ([подробности в статье Методы классов и параметры методов](fd_methods-parameters.html))


## Дополнительно редактируемые свойства класса данных

Окно редактирования свойств класса данных выглядит следующим образом:

1.Закладка редактирования свойств UML-класса:

![](/images/pages/products/flexberry-designer/class-diagram/implprop1.png)

Для того чтобы отредактировать дополнительные свойства какого-либо элемента диаграммы, следует щелкнуть правой клавишей мыши по редактируемому элементу:

![](/images/pages/products/flexberry-designer/class-diagram/additionalprops.jpg)

Затем выбрать пункт `Редактировать свойства`.

Что генерируется | Генерация в SQL DDL | Генерация в .Net-язык
:-----------------------------|:-----------------------------------|:-----------------------------------
`Name` - имя `UML`-класса | | Имя `.Net`-класса
`Stored` - указывает, хранимый ли класс, т.е. сохраняются ли экземпляры класса в источнике данных | Если класс не хранимый, - генерация скрипта для этого класса не происходит |
`Description` - некоторое описание класса | | `DocComment` перед определением класса
`Caption` - некоторое пользовательское имя, заголовок (отображается в пользовательском интерфейсе вместо имени класса) | | Указывается атрибут `CaptionAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))` непосредственно перед объявлением класса.  По умолчанию, пользовательское имя совпадает с именем класса. __Замечание__: Если требуется, чтобы у различных экземпляров класса данных был различный заголовок, используется атрибут `InstanceCaptionProperty`, указывающий имя свойства, возвращающего заголовок. Данный атрибут можно указать только программным путём, в Flexberry Designer он не вынесен.
`PrimaryKeyStorage` - имя хранения для поля - [первичного ключа](fo_primary-keys-objects.html) | Генерируется как имя поля [первичного ключа](fo_primary-keys-objects.html) в таблице. Если не указано, имя поля первичного ключа - `primaryKey`. | Если указано, генерируется атрибут [PrimaryKeyStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)), куда указывается имя хранения. У общего предка классов данных - [ICSSoft.STORMNET.DataObject](fo_data-object.html), этот атрибут указан как `PrimaryKeyStorage("primaryKey")`.
`Storage` - имя хранения для экземпляров этого класса данных | Генерируется как имя таблицы. Если не указано, имя таблицы совпадает с именем класса. | Если указан, генерируется атрибут [ClassStorageAttribute](fo_storing-data-objects.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)), в который пишется имя хранения.
`AutoAltered` - автоматическое вычисление статуса с проверкой изменённых свойств | | Если указан, генерируется атрибут [AutoAlteredAttribute](fo_object-status.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)).
`LoadingOrderXML` - позволяет указать порядок загрузки свойств экземпляров этого класса сервисами данных | | Если указан, генерируется атрибут [LoadingOrderAttribute](fo_order-loading-property.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)).
`Trim` - применяется ли удаление пробелов (функция Trim()) для строковых данных при работе [сервиса данных](fo_data-service.html) ( см. [дополнительную информацию](fo_trimmed-string-storage.html)) | |   
`Packet, NamespacePostfix` - позволяют настроить сборку и пространство имен | | см. [Расположение сборок после генерации кода](fo_location-assembly.html).
`PBCustomAttributes` - позволяет указать, необходима ли скобка программиста непосредственно перед описанием класса для "ручного" внесения атрибутов | | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения `.Net` атрибутов перед классом.
`PBMembers` - позволяет указать, необходима ли скобка программиста внутри класса для "ручного" внесения членов класса | | Если галочка указана - генерируется [скобка программиста](fo_programmer-brackets.html) для "ручного" внесения членов класса.
`BSClass` - [бизнес-сервер](fd_business-servers.html), обрабатывающий этот класс данных | | Если указан, генерируется атрибут [BusinessServerAttribute](fo_user-operations-dataservice.html) (Namespace: ICSSoft.STORMNET.Business, Assembly: ICSSoft.STORMNET.Business (in ICSSoft.STORMNET.Business.dll))
`PublishToEBSD` | | Если галочка указана - перед классом генерируется указание атрибута `PublishToEBSDAttribute`, который указывает доступность данного класса для использования в редакторе диаграмм сценариев.

2.Закладка редактирования [представлений](fd_view-definition.html) класса

![](/images/pages/products/flexberry-designer/class-diagram/implprop2.png)

Что генерируется | Генерация в .Net-язык
:------------------------|:-------------------------------
`Name` - имя представления |  Имя представления в атрибуте [ViewAttribute](fd_view-definition.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))
`Description` - некоторое описание для пояснительных целей | Комментарий к [статическому свойству для доступу к представлению](fo_static-view-accessors.html).
`Properties` - определение представления | Список атрибутов класса в .Net-атрибуте [ViewAttribute](fd_view-definition.html), указание ассоциированных представлений детейлов атрибутами [AssociatedDetailViewAttribute](fd_view-definition.html), указание дополнительных настроек мастеров в представлении атрибутами [MasterViewDefineAttribute](fd_view-definition.html).

Для удобного редактирования представления используется [специальная форма-редактор](fd_view-edit-form.html).