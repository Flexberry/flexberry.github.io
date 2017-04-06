---
title: Менеджер полномочий
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security, Ключевые понятия
toc: true
permalink: ru/efs_right-manager.html
lang: ru
---

## RightManager

`RightManager` - класс-менеджер полномочий. Помогает прикладному программисту взаимодействовать с [подсистемой полномочий](efs_right-manager-module.html).

Наиболее интересные методы RightManager'а описаны ниже.

### Включение и отключение проверки полномочий

Иногда требуется выполнить полное отключение проверки полномочий для всего приложения (если это веб-приложение, то сразу для всего приложения и для всех пользователей), для этого нужно вызвать:

```csharp
RightManager.DisableAllRightChecks();
```

Чтобы обратно включить полномочия, достаточно вызвать:

```csharp
RightManager.EnableAllRightChecks();
```

Проверка текущего состояния подсистемы полномочий:

```csharp
bool dontCheckRights = RightManager.DontCheckRights;
```

### Динамическое изменение типа доступа для объектов данных

Для того, чтобы не отключать полномочия целиком для приложения, можно отключить их только для отдельного класса или группы классов. Также можно включить полномочия для классов, код которых недоступен для изменения. Эти функции доступны через статический метод 
`RightManager.SetAdditionalAccessTypes(addAccessTypes);`
Каждый вызов этого метода приводит к очищению кэшей сервиса полномочий.

``` csharp
var addAccessTypes = new Dictionary<Type, AccessType>
{
	{ lockDataType, AccessType.@this }
};
RightManager.SetAdditionalAccessTypes(addAccessTypes);
```

### Проверка классов по имени с неймспейсом

Если у вас в системе присутствуют объекты, на которые наложены полномочия, с одинаковыми именами, но разными неймспейсами, то вам нужно в конфигурации сервиса полномочий и консоли управления указать такую настройку:
``` xml
    <!--эта настройка позволяет управлять именем классов (true - имя с неймспейсом, false - без неймспейса)-->
    <add key="UsingNamespaceForRights" value="false"/>
```
Программно значение этой настройки можно получить, обратившись к свойству `RightManager.UsingNamespaceNotTypeName`.

### Проверка доступа к объекту

``` csharp
RightManager.AccessObjectCheck(EditManager.DataObject, "Update", false)
```

В качестве операции могут быть переданы следующие значения:
<ul>
          <li>"FullControl":</li>
          <li>"Full"</li>
          <li>"Start"</li>
          <li>"Execute"</li>
          <li>"Open"</li>
          <li>"Read"</li>
          <li>"Insert"</li>
          <li>"Delete"</li>
          <li>"Update"</li>
</ul>

### Получение даты последнего обновления полномочий

``` csharp
        ///<summary>
        /// Получить дату последнего изменения полномочий
        ///</summary>
        ///<param name="lastUpdated">Дата последнего обновления</param>
        ///<returns>Результат выполнения операции (OperationResult.ОшибочныеАргументы если задано имя приложения, для которого режим работы отличается от CheckingMode.SimpleCheck или CheckingMode.SessionCheck)</returns>
        ///<exception cref="ApplicationException">Если не сможет подключиться к сервису, то будет сгенерировано исключение</exception>
        public static OperationResult GetLastSecurityUpdateTime(out DateTime? lastUpdated)
```
Этот метод `RightManager` позволяет получить дату последнего изменения полномочий (фактически проверяется Дата создания и дата изменения пермиссий, т.е. простое добавление или удаление пользователей на эту дату никак не влияет). Данный метод нужен для организации кэширования полномочий на прикладном уровне.

### Получение списка полномочий, доступных текущему пользователю

``` csharp
        /// <summary>
        /// Получить все полномочия для указанного пользователя (SessionMode)
        /// </summary>
        /// <param name="subjects">Список доступных полномочий</param>
        /// <returns>Результат выполнения операции (OperationResult.ОшибочныеАргументы если задано имя приложения, для которого режим работы отличается от CheckingMode.SessionCheck)</returns>
        public static OperationResult GetAllPermitions(out List<string> subjects)
```
Возвращается список имён объектов, для которых есть пермиссия. Даже если не выбрано ни одного [AccessType|AccessType), но пермиссия есть, то этот объект тоже вернётся как доступный.

### Обновление информации о пользователе

``` csharp
        /// <summary>
        /// Метод обновления информации о пользователе. 
        /// Доступен только для режима локального сервиса для веб-приложений. 
        /// Если пользователь не будет найден, то в результате будет "Ошибка выполнения".
        /// </summary>
        /// <param name="login">Логин пользователя, для которого обновляем информацию.</param>
        /// <param name="name">Имя пользователя. Если передать null, то обновляться не будет.</param>
        /// <param name="enabled">Должна ли быть активной учётная запись пользователя. Если передать null, то обновляться не будет.</param>
        /// <returns>Результат выполнения операции.</returns>
        public static UpdateResult UpdateUserInfo(string login, string name, bool? enabled)
```
Данный метод работает только в режиме веб-приложения с интегрированным сервисом полномочий (без отдельной службы windows).
