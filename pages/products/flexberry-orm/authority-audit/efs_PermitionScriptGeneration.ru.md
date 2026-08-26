---
title: Генерация скрипта назначения полномочий
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security
toc: true
permalink: ru/efs_permition-script-generation.html
lang: ru
---

(((
В версии [консоли управления полномочиями](efs_security-console.html) после 21.03.2013 появился дополнительный пункт меню "Привести в соответствие SQL-сервер" (через меню: SQL-скрипты -> Привести в соответствие SQL-сервер). После выбора данного пункта меню происходит генерация скрипта назначения полномочий на базу данных приложения.

В версии консоли управления полномочиями после 15.10.2014 учтена возможность установки ограничений на столбцы таблиц.
)))

# Скрипт назначения полномочий

Существует база данных приложения и другая база данных, где определяются пользователи и их полномочия. Такая реализация взаимодействия не позволяет использовать средства SQL-сервера для проверки полномочий и реализации защиты данных. 

Генерируемый скрипт организует настройку прав в базе данных приложения в SQL-сервере в соответствии с заданными параметрами в базе данных подсистемы полномочий.

## Пример работы скрипта назначения полномочий

Например, у нас есть пользователь `ICS_HOME\VPupkin`. В [консоли управления полномочиями](efs_security-console.html) у нас заведён пользователь с логином `VPupkin`, состоящий в группе `ICS_HOME`. Пользователю `VPupkin` назначен доступ `Insert` на класс `Учебник` из приложения `АбвгдApp` с базой данных `АбвгдDB`. 

Таким образом, если всё упростить, генерируемый скрипт должен обратиться к SQL-серверу и:
* создать логин для windows-пользователя `ICS_HOME\VPupkin` на сервере, если он не был создан ранее;
* создать пользователя базы данных приложения `АбвгдDB`, соответствующего логину `ICS_HOME\VPupkin`, если он не был создан ранее;
* назначить этому пользователю право на `Insert` в таблицу `Учебник`, запретить `Delete`, `Update` и `Select` в таблицу `Учебник`;
 *определить права пользователя на остальные таблицы, если необходимо.

## Пользователи, роли и группы

Определимся с терминологией, используемой при генерации скрипта, чтобы не возникало путаницы: 
* Если в домене `ICS_HOME` есть пользователь `VPupkin`, то в [консоли управления полномочиями](efs_security-console.html) это будет задано так: пользователь `VPupkin` находится в группе `ICS_HOME` (и `ics.perm.ru`, соответственно).
* Если пользователь `ICS_HOME\VPupkin` находится в группе `ICS_HOME\Администраторы`, то в консоли управления полномочиями) это будет задано так: пользователь `VPupkin` находится в роли `Администраторы`.

Скрипт организует не только создание логинов в SQL-сервере для пользователей, которые в консоли определены в нужную группу и существуют в домене, но и для групп, которые в консоли заданы в виде ролей и существуют в домене.

## Особенности работы скрипта назначения полномочий

* '''Общее разрешение на таблицы.''' Считается, что если у класса [AccessType](fo_access-type.html) имеет значение none, то никакой проверки над данным классом не должно производиться. Соответственно, всем пользователям необходимо предоставить доступ к соответствующим классам таблицам. 
Другие [типы проверки полномочий для класса|AccessType] интерпретируются как `@this`.
* '''Назначение прав.''' Для классов, у которых [AccessType](fo_access-type.html) не `none` считается, что операции `Insert`, `Delete`, `Update` и `Select` запрещены, если у пользователя нет на это особого разрешения.
* '''Права с фильтром.''' На настоящий момент ограничение доступа, в которых присутствует фильтр, не учитывается.
* '''(Из MSDN)''' Запрет (DENY) на уровне таблицы имеет меньший приоритет, чем предоставление разрешения (GRANT) на уровне столбца. Такая несогласованность в иерархии разрешений сохранена в целях обратной совместимости.
* '''Владелец БД.''' Если среди пользователей в системе полномочий присутствует владелец соответствующей базы, то скрипт генерироваться не будет, поскольку у владельца БД права менять не стоит.

## Задание полномочий на столбцы таблицы

В версии после 15.10.2014 доступна возможность [задания полномочий на отдельные свойства объекта](efs_check-access-to-attribute.html). Для данной функциональности также доступна возможность генерации скрипта назначения полномочий в SQL-сервере.
Для назначения прав необходимо указать соответствующие атрибуты назначения операций у свойства объекта, после чего пользователю в консоли управления полномочиями дать права на соответствующие операции.

'''Особенности'''
* Если на свойстве есть операция и у пользователя нет какого-либо разрешения на данную операцию, то он получает запрет на SELECT из столбца, соответствующего свойству (ограничение формируется только на операцию чтения).
* Детейлы не учитываются при назначений полномочий на столбцы.
* Поскольку у мастеров объекта из-за наследования может быть несколько столбцов-хранилищ, то одинаковые полномочия будут накладываться на все столбцы.

## Общий алгоритм генерации скрипта для пользователя

Алгоритм генерации скрипта для одного пользователя на конкретном примере.

* Пользователь, если его ранее не было добавлено, добавлен на сервер и в соответствующую БД.
* Пусть есть класс `FirstClass` и `SecondClass` (имена соответствующих таблиц совпадают с именами классов).
* Пусть оба класса имеют одинаковые свойства с назначенными операциями:
** "Property1" – «operationName1, operationName2».
** "Property2" – операции не назначены.
** "Property3" – «operationName3».
** "Property4" – «operationName2».
* Пусть текущий пользователь имеет следующие заданные разрешения:
** На класс `FirstClass` задан доступ `Full` с фильтром, `Read`, `Insert`  без фильтров. 
** На класс `ThirdClass` задан доступ `Full` без фильтра.
** На операцию «operationName1» заданы разрешения без фильтров: `Full`, `Read`, `Insert`.
** На операцию «operationNameOther» заданы разрешения без фильтров: `Full`.
** На операцию «operationName2» заданы разрешения «Update» без фильтра и `Full `с фильтром.

Тогда скрипт будет генерироваться следующим образом: 

'''1.''' Ищутся классы, на которые у пользователя нет прав. Со всех столбцов таблицы снимаются разрешения, которые могли там находиться. 

В нашем случае класс `SecondClass` существует, но у пользователя разрешений на него нет: запрет на все операции над таблицей + снятие запретов со столбцов с операций чтения (другие операции мы не рассматриваем). 

```csharp
SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)
```
'''2.''' Определяется, какие права есть у пользователя на оставшиеся классы, после чего определяется набор операций, которые необходимо запретить пользователю.

В данном случае на класс «FirstClass» у нас есть право `Read `и `Insert`, но при этом нет `Delete `и `Update`.

```csharp
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```

'''3.''' У классов, на которые у пользователя есть права, определяются свойства, на которые не задано никаких операций. С таких свойств сбрасываются все ограничения по доступным операциям.

В данном случае это свойство «Property2».

```csharp
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)
```

'''4.''' У классов, на которые у пользователя есть права, определяются свойства, на которые наложены операции, но при этом пользователь не имеет прав ни на одну операцию. На такие свойства накладываются ограничения по доступным для столбца операциям.

В данном случае это свойство «Property3» (на «operationName3» у пользователя нет прав).

```csharp
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)
```

'''5.''' Для оставшихся свойств таблицы на основании имеющихся для операций разрешений вычисляются права доступа и они назначаются.

В данном случае «Property1» имеет Full-доступ, а «Property4» имеет доступ для `Update `(этого достаточно, чтобы получить право на чтение.)

```csharp
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```


'''Итоговый скрипт для пользователя:'''

```csharp
SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```

'''Весь скрипт целиком имеет примерно следующий вид:'''

<msg type=warning>В связи с особенностями вики-разметки в строках 31 и 68 примера кода, представленного ниже, намеренно допущена ошибка. Чтобы получить правильный вариант кода, нужно убрать лишний пробел между повторяющимися символами @.</msg> 

(((
```csharp
BEGIN TRY 
 BEGIN TRANSACTION ChangePermitions WITH MARK N'Changing permitions' 


  USE TestInhDB
 DECLARE  
    @user_name sysname, 
    @common_cmd nvarchar(MAX), 
    @specific_cmd nvarchar(MAX) 

 IF NOT EXISTS 
   (SELECT * FROM sys.server_principals where type='U' AND name = 'ICS_HOME\tosotova) 
 BEGIN 
 	CREATE LOGIN [ICS_HOME\ tosotova] FROM WINDOWS 
 END 

 IF NOT EXISTS 
   (SELECT *  FROM sys.database_principals WHERE type = 'U' AND SUSER_SNAME(sid) = 'ICS_HOME\ tosotova ')
 BEGIN 
 	CREATE USER [tosotova] FOR LOGIN [ICS_HOME\ tosotova] 
 END 

 DECLARE UserCursor CURSOR FOR  
 SELECT TOP 1 name  
 FROM sys.database_principals  
 WHERE type = 'U' AND SUSER_SNAME(sid) = 'ICS_HOME\ tosotova ' 
 OPEN UserCursor  
 
 FETCH NEXT FROM UserCursor INTO @user_name  
 
 IF @@fetch_status = 0 
 BEGIN  

SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  

 END  
 
 CLOSE UserCursor  
 DEALLOCATE UserCursor  
 

 COMMIT TRANSACTION ChangePermitions  
 END TRY 
 BEGIN CATCH 
   DECLARE @ErrorMessage NVARCHAR(4000); 
   DECLARE @ErrorSeverity INT; 
   DECLARE @ErrorState INT; 
  
   SELECT  
     @ErrorMessage = ERROR_MESSAGE(), 
     @ErrorSeverity = ERROR_SEVERITY(), 
     @ErrorState = ERROR_STATE(); 
     IF @@TRANCOUNT > 0 
         ROLLBACK TRANSACTION ChangePermitions  
     RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState) 
 END CATCH
```
)))

## Настройка генерации

При выборе пункта меню "Привести в соответствие SQL-сервер" появится окно с настройками генерации

![](/images/pages/img/page/PermitionScriptGeneration/GenerationSettings.png)


### Удаление "лишних" windows-пользователей

Данная настройка полезна в следующей ситуации: пусть в результате выполнения скрипта был создан какой-то пользователь и ему были назначены права. Далее пользователь был удалён в консоли управления полномочиями. При выборе данной настройки скрипт проверит наличие таких пользователей и попытается их удалить.

### Применение скрипта после генерации

Без данной настройки после генерации пользователю будет предоставлена возможность просмотреть и отредактировать полученный скрипт. Если настройка будет включена, то скрипт сразу же будет передан на исполнение без дополнительных подтверждений.

### Строка соединения

Данная настройка определяет строку подключения к базе данных приложения. 

Строку подключения к базе данных приложения можно задать в конфиге консоли управления полномочиями в настройке `ApplicationConnectionString` в секции `connectionStrings`:

```xml
<?xml version="1.0"?>
<configuration>
	<!-- ... -->
	<connectionStrings>
		<!-- ApplicationConnectionString - строка соединения с базой данных приложения-->
		<add name="ApplicationConnectionString" connectionString="SERVER=server;Trusted_connection=yes;DATABASE=Security_test2;" />
	</connectionStrings>
	<!-- ... -->
</configuration>
```

Если в конфиге не задана данная настройка, то по умолчанию используется строка подключения к базе данных консоли управления полномочиями.

### Сборки с объектами

Сборки с объектами необходимы для получения атрибутов классов, на которые накладываются полномочия.

Из атрибутов вычитываются:

* `ClassStorageAttribute`(то есть имя таблицы, в котором хранятся объекты соответствующего класса);
* `AccessTypeAttribute` (то есть [тип проверки полномочий для класса](fo_access-type.html)).

Выбрать сборки можно из списка, который появляется при нажатии на кнопку "Выбрать сборки...". Поиск сборок с объектами осуществляется по умолчанию в папке, откуда было запущено приложение. Изменить папку, где будет происходить поиск сборок с объектами, можно нажатием на кнопку "Путь к сборкам..." (просмотреть текущий путь можно во всплывающей на данную кнопку подсказке).

## Аудит применения скриптов

Система ведёт [аудит](efs_audit.html) исполнения скриптов назначения полномочий. 

Просмотреть данные аудита можно через [ярлык](fw_desktop-operations.html) на [рабочем столе](fw_app-desktop.html): Аудит -> Аудит операций.
