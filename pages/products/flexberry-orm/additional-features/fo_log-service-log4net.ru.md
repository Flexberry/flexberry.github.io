---
title: Flexberry LogService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, log4net, ADO.NET аппендер, пример
summary: Использование log4net для Flexberry ORM
toc: true
permalink: ru/fo_log-service-log4net.html
lang: ru
---

`Flexberry LogService` является [продуктом платформы Flexberry](fp_landing_page.html) и предназначен для осуществления записи событий работы программного продукта.

Flexberry LogService базируется на [log4net](http://logging.apache.org/log4net/).

{% include note.html content="Flexberry LogService доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.LogService)." %}

### Пример использования LogService

``` csharp
try
{
  int i = (int)val;
}
catch (Exception ex)
{
   LogService.LogError("Произошло нечто ужасное", ex);
}
```

### Конфигурирование

Пример конфигурации (дополнительные аппендеры можно найти по ссылке: [config-examples](http://logging.apache.org/log4net/release/config-examples.html)):

``` xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <!-- регистрируем специальную секцию -->
  <configSections>
    <!--  <section name="log4net" type="System.Configuration.IgnoreSectionHandler" />  -->
	<section name="log4net" type="log4net.Config.Log4NetConfigurationSectionHandler, log4net" />
  </configSections>

  <!-- описание того как же мы будем логи писать. Примеры других аппендеров: http://logging.apache.org/log4net/release/config-examples.html -->
  <log4net>
    <appender name="LogFileAppender" type="log4net.Appender.RollingFileAppender">
      <param name="File" value="log/trace.log"/>
      <param name="AppendToFile" value="true"/>
      <param name="RollingStyle" value="Date"/>
      <layout type="log4net.Layout.PatternLayout">
        <param name="ConversionPattern" value="%-5p %d{yyyy-MM-dd hh:mm:ss} [%t) %m%n" />
      </layout>
    </appender>
	
	<appender name="EventLogAppender" type="log4net.Appender.EventLogAppender" >
    <layout type="log4net.Layout.PatternLayout">
        <conversionPattern value="%date [%thread) %-5level %logger [%property{NDC}) - %message%newline" />
    </layout>
	</appender>

    <appender name="SmtpAppender" type="log4net.Appender.SmtpAppender">
    <to value="to@flexberry.ru" />
    <from value="error@flexberry.ru" />
    <subject value="test logging message" />
    <smtpHost value="smtpHost" />
    <bufferSize value="512" />
    <lossy value="true" />
    <evaluator type="log4net.Core.LevelEvaluator">
        <threshold value="WARN"/>
    </evaluator>
    <layout type="log4net.Layout.PatternLayout">
        <conversionPattern value="%newline%date [%thread) %-5level %logger [%property{NDC}) - %message%newline%newline%newline" />
    </layout>
</appender>
	
	<root>
      <level value="DEBUG"/>
      <appender-ref ref="LogFileAppender"/>
      <appender-ref ref="SmtpAppender"/>
      <appender-ref ref="EventLogAppender"/>
    </root>
  </log4net>
</configuration>
```

### ADO.NET аппендер

Для записи сообщений лога в БД следует использовать `ADO.NET аппендер`.
Можно использовать стандартный `log4net.Appender.AdoNetAppender`, однако в пакете LogService реализован кастомный ADO.NET аппендер (ICSSoft.STORMNET.CustomAdoNetAppender), переопределяющий логику получения строки соединения из конфигурационного файла приложения (в т.ч. зашифрованной строки соединения).

У аппендера есть опциональный атрибут `ConnectionStringName`. Если его не задавать, то поиск строки соединения будет происходить аналогично тому, как это делает [DataServiceProvider](fo_ds-provider.html):

* Пытается получить имя строки соединения из настройки `DefaultConnectionStringName` в секции appSettings, и затем ищет такую строку в секции conectionStrings.
* Если имя `DefaultConnectionStringName` не задано, пытается получить строку из настройки CustomizationStrings в секции appSettings.

Если же атрибут будет задан, то аппендер будет искать строку с таким именем в секции conectionStrings.

При использовании зашифрованной строки соединения необходимо указывать настройку Encrypted со значением true (в секции appSettings), иначе аппендер не будет производить расшифровку строки соединения.

#### Пример конфигурации ADO.NET аппендера

```xml
<appender name="AdoNetAppender" type="ICSSoft.STORMNET.CustomAdoNetAppender">
      <bufferSize value="0" />
      <connectionType value="System.Data.SqlClient.SqlConnection, System.Data, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" />
      <ConnectionStringName value="DefConnStr" />
      <commandText value="INSERT INTO [ApplicationLog) ([primaryKey),[Timestamp),[ThreadName),[Category),[ProcessName),[Message),[FormattedMessage)) VALUES (NEWID(), @log_date, @thread, @log_level, @logger, @message, @exception)" />
      <parameter>
        <parameterName value="@log_date" />
        <dbType value="DateTime" />
        <layout type="log4net.Layout.RawTimeStampLayout" />
      </parameter>
      <parameter>
        <parameterName value="@thread" />
        <dbType value="String" />
        <size value="512" />
        <layout type="log4net.Layout.PatternLayout">
          <conversionPattern value="%thread" />
        </layout>
      </parameter>
      <parameter>
        <parameterName value="@log_level" />
        <dbType value="String" />
        <size value="64" />
        <layout type="log4net.Layout.PatternLayout">
          <conversionPattern value="%level" />
        </layout>
      </parameter>
      <parameter>
        <parameterName value="@logger" />
        <dbType value="String" />
        <size value="512" />
        <layout type="log4net.Layout.PatternLayout">
          <conversionPattern value="%logger" />
        </layout>
      </parameter>
      <parameter>
        <parameterName value="@message" />
        <dbType value="String" />
        <size value="2500" />
        <layout type="log4net.Layout.PatternLayout">
          <conversionPattern value="%message" />
        </layout>
      </parameter>
      <parameter>
        <parameterName value="@exception" />
        <dbType value="String" />
        <size value="4000" />
        <layout type="log4net.Layout.ExceptionLayout" />
      </parameter>
    </appender>
```

### Написание своего аппендера

Часто существующих решений недостаточно, для этого, есть возможность написать свой аппендер. Тут есть всего 2 важных момента:

* Нужно унаследоваться от `log4net.Appender.AppenderSkeleton` и переопределить метод `protected override void Append(log4net.Core.LoggingEvent loggingEvent)`
* Все публичные свойства могут быть означены через конфигурацию этого конкретного аппендера

#### Пример кода аппендера

``` csharp
using System;
using System.Collections;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Http;
using ICSSoft.STORMNET;
using RemoteLogger;

namespace Logging
{
    public class RemoteLoggerAppender:log4net.Appender.AppenderSkeleton
    {
        private string _srvUrl;

        /// <summary>
        /// Адрес сервиса
        /// </summary>
        public string SrvUrl
        {
            get { return _srvUrl; }
            set { _srvUrl = value; }
        }

        protected override void Append(log4net.Core.LoggingEvent loggingEvent)
        {
            IDictionary props = new Hashtable();
            props["name") = Guid.NewGuid().ToString();

            HttpClientChannel chan = new HttpClientChannel(props, new BinaryClientFormatterSinkProvider());

            try
            {
                ChannelServices.RegisterChannel(chan, false);

                Logger logger = (Logger)Activator.GetObject(typeof(Logger), SrvUrl);
                if (loggingEvent.ExceptionObject == null)
                {
                    logger.Info(loggingEvent.MessageObject.ToString());
                }
                else
                {
                    logger.Info(loggingEvent.MessageObject.ToString(), loggingEvent.ExceptionObject);
                }
                
            }
            catch (Exception ex)
            {
                LogService.Log.Error("RemoteLoggerAppender", ex);
            }
            finally
            {
                ChannelServices.UnregisterChannel(chan);
            }
        }
    }
}
```

В конфиге подключается этот аппендер таким образом:

``` xml
    <appender name="remoteLoggerAppender" type="Logging.RemoteLoggerAppender, RemoteLoggerAppender">
      <SrvUrl value="tcp://localhost:2121/RemoteLogSrv"/>
    </appender>
    <root>
      <level value="DEBUG"/>
      <appender-ref ref="remoteLoggerAppender"/>
    </root>
```

Как видно, `SrvUrl` передаётся как параметр в конфиге и может применяться при выводе сообщений в коде аппендера. Атрибут `type` задаёт полный тип аппендера с неймспейсом и указанием имени сборки. Разумеется сборка с аппендером должна находиться рядом со сборкой `log4net`.
