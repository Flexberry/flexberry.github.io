---
title: Подсистема полномочий и подсистема аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit, Flexberry Security
toc: true
permalink: ru/efs_rights-and-audit-subsystems.html
lang: ru
---

# Подсистема полномочий и подсистема аудита
В версии Flexberry после 28.01.2014 объекты [Подсистема-полномочии|подсистемы полномочий], располагаемые в сборке `Security(Objects)`, перестали зависеть от [старого аудита](efs_audit.html) и теперь зависят от [нового аудита](fa_audit-web.html), что позволит:
* производить более тонкую настройку аудита объектов полномочий;
* вести "полный" аудит с сохранением, какие поля какие значения принимали в результате аудируемой операции (при работе старого аудита данная возможность была ограничена).

# Настройка [аудита](fa_audit-web.html) для объектов полномочий
Настройки [аудита](fa_audit-web.html) для объектов полномочий должна осуществляться особенным образом, поскольку:
* может возникнуть потребность вести для объектов полномочий "неполный" аудит (то есть отображать информацию по времени и авторству создания и последнего изменения объекта, но не записывать, когда именно и как были изменены поля объектов полномочий), при этом вся остальная система должна аудироваться по "полной" схеме.
* может возникнуть потребность писать аудит объектов полномочий в отдельную от остального аудита базу.

Для решения данных вопросов были расширены [настройки класса](fa_audit-web.html) и добавлена поддержка `AuditClassService` и `AuditClassConnectionStringName`.

## Настройка аудита полномочий и `AuditClassService`
Задание `AuditClassService` позволит определить способ, каким именно образом необходимо писать аудит полномочий.

Определение используемой реализации интерфейса [IAudit](efs_i-audit.html) для объектов полномочий осуществляется через настройки Unity в файле конфигурации (вычитка производится единожды, после чего значение кэшируется). Если зависимость не удаётся разрешить, то используются настройки для всего аудита в целом.

В примере ниже в качестве реализации `IAudit` для объектов полномочий используется [EmptyAudit](efs_i-audit.html), что позволит не писать "полный" аудит по объектам полномочий.

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
	<configSections>
		<section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
	</configSections>

	<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
	  <!--Определяем именованный экземпляр IAudit, который будет использоваться для записи аудита объектов полномочий.-->
      <register name="securityAuditClassService"
				type="ICSSoft.STORMNET.Business.Audit.IAudit, ICSSoft.STORMNET.Business"
				mapTo="ICSSoft.STORMNET.Business.Audit.EmptyAudit, ICSSoft.STORMNET.Business">
      </register>
	</unity>
	
</configuration>
```

## Настройка аудита полномочий и `AuditClassConnectionStringName`
Задание `AuditClassConnectionStringName` позволит определить имя строки подключения к БД, куда будут писаться данные аудита.

Для объектов полномочий `AuditClassConnectionStringName` берёт значение из файла конфигурации из настройки "`SecurityAuditClassConnectionStringName`" (вычитка производится единожды, после чего значение кэшируется). Если данная настройка не задана в файле конфигурации, то получение имени идёт по [стандартному механизму](efs_data-service-for-audit.html).

# Миграция существующих проектов
