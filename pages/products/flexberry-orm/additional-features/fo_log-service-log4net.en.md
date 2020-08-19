---
title: Flexberry LogService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, log4net, ADO.NET appender example
summary: Using log4net for Flexberry ORM
toc: true
permalink: en/fo_log-service-log4net.html
lang: en
autotranslated: true
hash: d5881a4241a1064878baedd5fa3fe009e80738d9b4c390c093f0e0d6c8851068
---

`Flexberry LogService` is [product platform Flexberry](fp_landing_page.html) and is intended for implementation event records the software.

Flexberry LogService is based on [log4net](http://logging.apache.org/log4net/).

{% include note.html content="Flexberry LogService is available for installation in the project via [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.LogService)." %}

### Example of use of the LogService

``` csharp
try
{
  int i = (int)val;
}
catch (Exception ex)
{
   LogService.LogError("Something terrible happened", ex);
}
```

### Configuration

Example configuration (additional appenders can be found at: [config examples](http://logging.apache.org/log4net/release/config-examples.html)):

``` xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <!-- recorded a special section -->
  <configSections>
    <!-- <section name="log4net" type="System.Configuration.IgnoreSectionHandler" /> -->
	<section name="log4net" type="log4net.Config.Log4NetConfigurationSectionHandler, log4net" />
  </configSections>

  <!-- a description of how we will write the logs to. Examples of other appendrow: http://logging.apache.org/log4net/release/config-examples.html -->
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
        <conversionPattern value="�te [%thread) %-5level %logger [%property{NDC}) - %message%newline" />
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
        <conversionPattern value="%newline�te [%thread) %-5level %logger [%property{NDC}) - %message%newline%newline%newline" />
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

### ADO.NET appender

To log messages log in the database you should use `ADO.NET аппендер`.
You can use the standard `log4net.Appender.AdoNetAppender`, but in the package LogService implemented custom ADO.NET appender (ICSSoft.STORMNET.CustomAdoNetAppender) override the logic of getting a connection string from the configuration file of the application (including the encrypted connection string).

Appender is an optional attribute `ConnectionStringName`. If it is not set, the search of the connection string will occur in the same way as it does [DataServiceProvider](fo_ds-provider.html):

* Tries to get the name of the connection string from settings `DefaultConnectionStringName` in the appSettings section, and then looking for this line in section conectionStrings.
* If the name `DefaultConnectionStringName` not set, trying to get the string out of tune CustomizationStrings in the section appSettings.

If the attribute is specified, the appender will search with this name in the section conectionStrings.

When using the encrypted connection string you must specify a configuration Encrypted with the value true (in the appSettings section), otherwise the appender will not perform decryption of the connection string.

#### Example configuration ADO.NET appender

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
          <conversionPattern value=the "%message" />
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

### Writing your appender

Often existing solutions are not enough for this, it is possible to write your own appender. There are only 2 important points:

* Need to unasledovala from `log4net.Appender.AppenderSkeleton` and override the method `protected override void Append(log4net.Core.LoggingEvent loggingEvent)`
* All public properties can be signified through the configuration of that specific appender

#### Example code appender

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
        /// The address of the service 
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

In the config this appender is connected thus:

``` xml
    <appender name="remoteLoggerAppender" type="Logging.RemoteLoggerAppender, RemoteLoggerAppender">
      <SrvUrl value="tcp://localhost:2121/RemoteLogSrv"/>
    </appender>
    <root>
      <level value="DEBUG"/>
      <appender-ref ref="remoteLoggerAppender"/>
    </root>
```

As you can see, `SrvUrl` is passed as a parameter in the config and can be used to output messages in the code appender. Pstrftype` attribute specifies the fully qualified type appender with the namespace and name of the Assembly. Of course the Assembly with the appender should be next with the Assembly `log4net`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}