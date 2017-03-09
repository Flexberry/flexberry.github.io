---
title: LogService (log4net)
sidebar: flexberry-orm_sidebar
keywords: Flexberry, Public
toc: true
permalink: ru/fo_log-service-log4net.html
---

## Что это такое

Иногда требуется вывести информацию о том, что в системе не всё хорошо. Выдавать эту информацию простым пользователям иногда можно и даже нужно, но самое главное, это предупредить администраторов или разработчиков. В .NET-комопнентах платформы Flexberry используется компонент LogService, который базируется на `[log4net](http://logging.apache.org/log4net/)`. 

Все внутренние ошибки, которые генерирует технология Flexberry выводятся с уровнем `WARN`.

В том числе, пишутся и те `Exceptions`, которые показываются пользователю в UI-компонентах (`ExceptionBox`).

## Как это использовать
Чтобы использовать `LogService` нужно подключить 2 сборки:
* **`log4net.dll`**
* **`LogService.dll`**

Данные сборки доступны в виде [NuGet-пакета](https://www.nuget.org/packages/NewPlatform.Flexberry.LogService/).

Пример использования:

```csharp
try
{
  int i = (int)val;
}
catch (Exception ex)
{
  LogService.LogError("Произошло нечто ужасное", ex);
}
```

## Конфигурирование

Пример конфигурации: 

```xml
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
        <param name="ConversionPattern" value="%-5p %d{yyyy-MM-dd hh:mm:ss} [%t] %m%n" />
      </layout>
    </appender>
	
	<appender name="EventLogAppender" type="log4net.Appender.EventLogAppender" >
    <layout type="log4net.Layout.PatternLayout">
        <conversionPattern value="%date [%thread] %-5level %logger [%property{NDC}] - %message%newline" />
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
        <conversionPattern value="%newline%date [%thread] %-5level %logger [%property{NDC}] - %message%newline%newline%newline" />
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

Дополнительные аппендеры можно найти тут: [config-examples](http://logging.apache.org/log4net/release/config-examples.html)

## Написание своего аппендера

Часто существующих решений недостаточно, для этого, есть возможность написать свой аппендер. Тут есть всего 2 важных момента:

* Нужно унаследоваться от `log4net.Appender.AppenderSkeleton` и переопределить метод `protected override void Append(log4net.Core.LoggingEvent loggingEvent)`
* Все публичные свойства могут быть означены через конфигурацию этого конкретного аппендера

Пример кода аппендера:

```csharp
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
            props["name"] = Guid.NewGuid().ToString();

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

```xml
    <appender name="remoteLoggerAppender" type="Logging.RemoteLoggerAppender, RemoteLoggerAppender">
      <SrvUrl value="tcp://localhost:2121/RemoteLogSrv"/>
    </appender>
    <root>
      <level value="DEBUG"/>
      <appender-ref ref="remoteLoggerAppender"/>
    </root>
```

Как видно, `SrvUrl` передаётся как параметр в конфиге и может применяться при выводе сообщений в коде аппендера. Атрибут `type` задаёт полный тип аппендера с неймспейсом и указанием имени сборки. Разумеется сборка с аппендером должна находиться рядом со сборкой `log4net`.
