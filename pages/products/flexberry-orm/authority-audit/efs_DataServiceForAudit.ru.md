---
title: Механизм определения настроек подключения к БД при работе аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit, БД
toc: true
permalink: ru/efs_data-service-for-audit.html
lang: ru
---

Данная статья относится к [новому аудиту](fa_audit-web.html).

# Механизм определения строки подключения при работе аудита
'''Механизм работает в версиях сборок после 27.01.2015.'''

При ведении аудита важным является определить, как и в какую БД будут записываться данные аудита. Для этого определяется имя строки подключения к аудиту и тип [сервиса данных](fo_data-service.html).

## Определение имени строки подключения
Определение имени строки подключения происходит по следующему алгоритму:

1. Из настроек аудита из класса ([AuditClassSetting](efs_keep-and-use-audit-settings.html)) берётся настройка `AuditClassConnectionStringName`.

```cs
public class FirstClass : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
{
	...	
	
	/// <summary>
	/// Audit class settings.
	/// </summary>
	public class AuditSettings
	{
		/// <summary>
		/// Имя строки соединения с БД, куда необходимо писать аудит.
		/// </summary>
		public static string AuditClassConnectionStringName = "FirstClassConnectionStringName";
	}
}
```

Если `AuditClassConnectionStringName` определено в настройках аудита класса и значение является непустой последовательностью символов, то оно берётся в качестве имени строки подключения.

2. Если предыдущим способом имя строки подключения не было определено, то берётся настройка [IsDatabaseLocal](efs_keep-and-use-audit-settings.html) (см. также [следующую статью](efs_audit-win-service.html)).

2.1. Если `IsDatabaseLocal = false`, то в качестве имени строки соединения берётся значение [AuditConnectionStringName](efs_keep-and-use-audit-settings.html).

2.2. Если `IsDatabaseLocal = true`, то происходит поиск по структурам [AuditDSSetting](efs_keep-and-use-audit-settings.html).

Считается, что если `IsDatabaseLocal = true`, то запись аудита будет производиться в "текущую" БД посредством "текущего" [сервиса данных](fo_data-service.html) (понятие "текущий" условно, поскольку в метод определения имени строки подключения тип "текущего" [сервиса данных](fo_data-service.html) может попасть различными способами).

Если "текущий" [сервис данных](fo_data-service.html) некорректен, то в качестве имени строки соединения используется значение [ConnStringName](efs_keep-and-use-audit-settings.html) из первой `AuditDSSetting` из массива `AuditDSSettings` ([имя строки соединения по умолчанию определено как &lt;AppNameForAudit&gt;_&lt;AuditConnectionStringName&gt;](efs_audit-setter.html)).

Иначе среди массива `AuditDSSettings` ищется `AuditDSSetting`, где [ConnString](efs_keep-and-use-audit-settings.html) равно строке соединения "текущего" [сервиса данных](fo_data-service.html), а [DataServiceType](efs_keep-and-use-audit-settings.html) - типу "текущего" [сервиса данных](fo_data-service.html), и значение первой найденной `ConnStringName` будет искомым именем строки соединения для аудита.

Реализация интерфейса [IAudit](efs_i-audit.html) `ICSSoft.STORMNET.Business.Audit.Audit` обрабатывает полученное имя строки соединения следующим образом:
# Производится поиск строки соединения по имени среди доступных в файле конфигурации.
# Если строка соединения не была найдена, то используется строка соединения из [DataServiceProvider.DataService](fo_ds-provider.html).

## Определение типа сервиса данных
Тип [сервиса данных](fo_data-service.html), посредством которого будет записываться аудит, не определяется на этапе подготовки данных для записи в аудит. Тип определяется уже в реализации интерфейса `IAudit`.

Реализация интерфейса `IAudit` `ICSSoft.STORMNET.Business.Audit.Audit` определяет тип сервиса данных следующим образом (см. также [следующую статью](efs_audit-win-service.html)):
# Тип сервиса данных всегда ищется в конфиг-файле по ключу <Имя строки соединения>_DSType.
# Если такой записи нет, то берётся сервис данных, указанных в настройке "DefaultDSType".
# Иначе используется тип сервиса данных [DataServiceProvider.DataService](fo_ds-provider.html).
