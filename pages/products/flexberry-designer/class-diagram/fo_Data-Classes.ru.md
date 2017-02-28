---
title: Классы данных (классы со стереотипом implementation или без указания стереотипа) и их свойства 
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fo_data--classes.html
---

## Классы данных (классы со стереотипом implementation или без указания стереотипа)
[Стереотип](key-concepts-flexberry-designer.html) `implementation` может указываться, а может не указываться, суть одна: UML-класс, соответствующий некоторой предметной сущности.

![](/images/pages/img/Flexberry plugins/implementation.jpg)

(((
<msg type=important>Классы данных, связанные [иерархией наследования](inheritance.html), [ассоциациями](master--association.html) и [агрегациями](detail-associations-and-their-properties.html) друг с другом, образуют так называемую "[объектную структуру прикладной системы](key-concepts-flexberry-designer.html)", т.е. структуру предметных сущностей и их отношения.</msg>
)))

### Что генерируется с класса данных

{| border="1"
! Что генерируется
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| `UML`-класс
| Определение таблицы: `CREATE TABLE`
Если в БД есть представление,соответствующее имени хранения данного класса, генерируется комментарий с информацией о наличии представления и определением таблицы.
| `.Net`-класс, наследующийся от .Net-класса, соответствующего предку по модели. Если в модели у класса нет предка, он наследуется от класса `[ICSSoft.STORMNET.DataObject](dataobject.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`
|-
| Атрибут UML-класса
| Поле в таблице класса ([подробности здесь](attributes-class-data.html))
| Виртуальное свойство с тем же именем и соответствующим модификатором (# - `protected`, + - `public`, - - `private`) и приватный член класса для этого свойства.
Тип свойства и приватного члена - тип атрибута.
 ([подробности здесь](attributes-class-data.html))
|-
| Метод UML-класса
| Никак
| Виртуальный метод с соответствующими параметрами и модификатором. Тело метода пустое со скобкой программиста для внесения кода метода. ([подробности здесь](class-methods-and-method-parameters.html))
|}


### Дополнительно редактируемые свойства класса данных и что как генерируется
Окно редактирования свойств класса данных выглядит следующим образом:

1. Закладка редактирования свойств UML-класса:
![](/images/pages/img/Flexberry plugins/implprop1.jpg)

{| border="1"
! Что генерируется
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| `Name` - имя `UML`-класса
| Никак
| Имя `.Net`-класса
|-
| `Stored` - указывает, хранимый ли класс, т.е. сохраняются ли экземпляры класса в источнике данных
| Если класс не хранимый, - генерация скрипта для этого класса не происходит.
| Никак
|-
| `Description` - некоторое описание класса.
| Никак
| `DocComment` перед определением класса
|-
| `Caption` - некоторое пользовательское имя, заголовок (отображается в пользовательском интерфейсе вместо имени класса)
| Никак
| Указывается атрибут `CaptionAttribute (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))` непосредственно перед объявлением класса.

 По умолчанию, пользовательское имя совпадает с именем класса.

__Замечание__: Если требуется, чтобы у различных экземпляров класса данных был различный заголовок, используется атрибут `InstanceCaptionProperty`, указывающий имя свойства, возвращающего заголовок. Данный атрибут можно указать только программным путём, в Flexberry Designer он не вынесен.
|-
| `PrimaryKeyStorage` - имя хранения для поля - [первичного ключа](primary-keys-objects.html)
| Генерируется как имя поля [первичного ключа](primary-keys-objects.html) в таблице.

Если не указано, имя поля первичного ключа - `primaryKey`.
| Если указано, генерируется атрибут `[PrimaryKeyStorageAttribute](data-objects-and-database-structures.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`, куда указывается имя хранения.

У общего предка классов данных - `[ICSSoft.STORMNET.DataObject](dataobject.html)`, этот атрибут указан как `PrimaryKeyStorage("primaryKey")`.
|-
| `Storage` - имя хранения для экземпляров этого класса данных
| Генерируется как имя таблицы.

Если не указано, имя таблицы совпадает с именем класса.
| Если указан, генерируется атрибут `[ClassStorageAttribute](data-objects-and-database-structures.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`, в который пишется имя хранения.
|-
| `AutoAltered` - автоматическое вычисление статуса с проверкой изменённых свойств 
| Никак
| Если указан, генерируется атрибут `[AutoAlteredAttribute](object-status-and-loading-state.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`.
|-
| `LoadingOrderXML` - позволяет указать порядок загрузки свойств экземпляров этого класса сервисами данных
| Никак
| Если указан, генерируется атрибут `[LoadingOrderAttribute](order-of-loading-property--data-object.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`.
|-
| `Trim` - применяется ли удаление пробелов (функция Trim()) для строковых данных при работе [сервиса данных](data-service.html)
(**не реализовано**, см. [дополнительную информацию](trimmed-string-storage.html)).
|    
|   
|-
| `Packet, NamespacePostfix` - позволяют настроить сборку и пространство имен
| Никак
| см. [Расположение сборок после генерации кода](location-assembly-after-code-generation.html).
|-
| `PBCustomAttributes` - позволяет указать, необходима ли скобка программиста непосредственно перед описанием класса для "ручного" внесения атрибутов 
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения `.Net` атрибутов перед классом.
|-
| `PBMembers` - позволяет указать, необходима ли скобка программиста внутри класса для "ручного" внесения членов класса.
| Никак
| Если галочка указана - генерируется [скобка программиста](programmer-brackets.html) для "ручного" внесения членов класса.
|-
| `BSClass` - [бизнес-сервер](business-servers.html), обрабатывающий этот класс данных.
| Никак
| Если указан, генерируется атрибут `[BusinessServerAttribute](otrabotka-polzovatelskih-operacii-v-processe-raboty-servisa-dannyh-integraciya-s-biznes-serverom.html) (Namespace: ICSSoft.STORMNET.Business, Assembly: ICSSoft.STORMNET.Business (in ICSSoft.STORMNET.Business.dll))`.
|-
| `PublishToEBSD`
| Никак
| Если галочка указана - перед классом генерируется указание атрибута `PublishToEBSDAttribute`, который указывает доступность данного класса для использования в редакторе диаграмм сценариев.
|}

2. Закладка редактирования [представлений](view-definition.html) класса


![](/images/pages/img/Flexberry plugins/implprop2.jpg)

{| border="1"
! Что генерируется
! Генерация в SQL DDL
! Генерация в .Net-язык
|-
| `Name` - имя представления
| Никак
| Имя представления в атрибуте `[ViewAttribute](view-definition.html) (Namespace: ICSSoft.STORMNET, Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll))`
|-
| `Description` - некоторое описание для пояснительных целей
| Никак
| Комментарий к [статическому свойству для доступу к представлению](static--view--accessors.html).
|-
| `Properties` - определение представления
| Никак
| Список атрибутов класса в .Net-атрибуте `[ViewAttribute](view-definition.html)`, указание ассоциированных представлений детейлов атрибутами `[AssociatedDetailViewAttribute](view-definition.html)`, указание дополнительных настроек мастеров в представлении атрибутами `[MasterViewDefineAttribute](view-definition.html)`.
|}

Для удобного редактирования представления используется [специальная форма-редактор](view-edit-form.html).
