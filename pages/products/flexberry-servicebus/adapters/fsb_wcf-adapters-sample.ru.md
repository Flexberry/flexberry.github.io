---
title: Пример WCF-адаптеров
sidebar: flexberry-servicebus_sidebar
keywords: DnsIdentity, Callback, Тип сообщения, Шина, Адаптер
toc: true
permalink: ru/fsb_wcf-adapters-sample.html
lang: ru
summary: Пошаговое руководство по созданию примера WCF-адаптера для приложения, подключающегося к корпоративной шине
---

Данная статья на примерах описывает создание [адептеров](fsb_adapters.html) (или клиентов) для [Flexberry Service Bus](fsb_landing_page.html) (сервиса шины), позволяющих как отправлять сообщения, так и принимать их. Итогом реализации таких адаптеров является успешно принятое соответствующим клиентом сообщение.

Для создания адаптеров по примеру потребуются следующие инструменты:

* [MS Visual Studio](https://www.visualstudio.com) - для создания собственно приложений адаптеров отправки/приема сообщений
* [Docker](fsb_installation.html) - программное обеспечение для автоматизации развёртывания и управления приложениями в среде виртуализации на уровне операционной системы. Необходим, если нет уже развернутых и готовых к использованию административного приложения шины и ее сервиса.
* [PowerShell](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6) - необходим для работы с Docker, если требуется [локальная настройка](fsb_installation.html) и запуск шины и ее сервиса

В процессе реализации примера будет создано три консольных приложения:

* Приложение, с помощью которого будут __отправлены сообщения__. В данном случае это будет консольное приложение, в командную строку которого вводится сообщение, в последствии передаваемое шиной принимающему приложению.
* Приложение, __принимающее переданные сообщения__. Для примера также используется консольное приложение, результатом работы которого будет отображение принятого им сообщение, переданное с помощью шины от приложения-отправителя.
* Приложение, __принимающее переданные сообщения с callback__. Для примера также используется консольное приложение, результатом работы которого будет отображение принятого им сообщение, переданное с помощью шины от приложения-отправителя.

Код описываемых приложений выложен в примерах для `Flexberry Service Bus` на [GitHub](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples). Принципы работы с GitHub описаны на сайте [https://help.github.com](https://help.github.com/articles/cloning-a-repository/) или на сайте [https://git-scm.com](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)

## Работа с административным приложением

Для работы с приложениями отправки-приема сообщений требуется иметь доступ к запущенной инстанции шины (административному приложению). Для этого действия нужен адрес административного приложения и адрес запущенного сервиса шины. Получить их можно следующими способами:
* административное приложение - локально по адресу [http://localhost:1818/](http://localhost:1818/). Данный вариант возможен, если используется [Docker](fsb_installation.html).
* сервис - локально по адресу [http://localhost:7075/WcfService](http://localhost:7075/WcfService). Данный вариант возможен, если используется `Docker`.
* с помощью адресов, предоставленных Администратором, в случае централизованно развернутой шины

Также для последующей работы адаптеров нужно:
* создать тип сообщений, которые будут участвовать в процессе передачи-приема
* клиентов шины
* оформить подписки для клиентов, принимающих сообщения
* разрешения на передачу сообщений.

### Создание типа сообщений

* Запустить административное приложение
* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Типы сообщений"
* Нажать кнопку "Добавить"
* На открывшейся форме указать:
    * имя типа сообщений- можно задать произвольное
    * идентификатор - потребуется при настройке адаптеров. Наименование можно задать произвольное, но если оно состоит из нескольких слов, то должно быть написано верюлюжбим стилем (например, MessageType).
    * также можно дополнить тип сообщения описанием
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/components/message-type-edit.png)

### Регистрация клиента в сервисной шине

* Открыть административное приложение
* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Клиенты"
* Нажать кнопку "Добавить"
* На открывшейся форме указать:
    * имя клиента - можно задать произвольное
    * идентификатор - потребуется при настройке адаптеров. Наименование можно задать произвольное, но если оно состоит из нескольких слов, то должно быть написано верюлюжбим стилем (например, MessageSender).
    Клиентов для примера потребуется три, соответственно, их ID должны позволять отличать клиентов друг от друга и использовать в настройке соответствующего адаптера.
    * адрес требуется для клиентов, принимающих сообщени и использующих callback. Его необходимо будет взять из конфига соответствующего созданного адаптера, принимающего сообщения.
    * остальные поля не нужны для создания описываемых примеров
* Нажать "Сохранить и закрыть" на форме.

![](/images/pages/products/flexberry-servicebus/adapters/add-client.png)

#### Разрешение на отправку сообщений

Для клиента, передающего сообщение, нужно добавить разрешение на отправку сообщений:
* развернуть пункт меню "Разрешения на отправку сообщений"
* нажать "Добавить"
* в строке выбрать созданный ранее тип сообщений
* нажать "Сохранить и закрыть" на форме.

![](/images/pages/products/flexberry-servicebus/components/clients-sender-edit.png)

#### Формирование подписки на сообщение

Для клиента, принимающего сообщение, нужно сформировать подписку на сообщение.

* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Подписки на сообщения"
* На открывшейся форме:
    * выбрать клиента, принимающего сообщение, созданного ранее
    * тип сообщения, созданный ранее
    * установить дату прекращения действия подписки
    * "Передавать через" - это сервис, используемый для передачи сообщений. Для примера нужен WCF.
    * если клиент использует `callback` установить флаг "Callback"
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/components/clients-receiver-edit.png)

## Пример создания клиента, отправляющего сообщения

#### Создание консольного приложения

Для создания консольного приложения можно использовать [MS Visual Studio](https://www.visualstudio.com).

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app.png)

В открывшемся окне ввести название приложения и место его расположения.

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app2.png)

#### Добавление ссылки на сервис шины в приложение

Для добавления ссылки средствами `MS Visual Studio` требуется иметь доступ к запущенной инстанции шины (см. пункт "Работа с административным приложением").

В `MS Visual Studio` в `Solution Explorer` правой клавишей мыши нажать на проект созданного адаптера, отправляющего сообщения, и в  контекстном меню выбрать пункт `Add Service Reference…`

![](/images/pages/products/flexberry-servicebus/adapters/add-reference.png)

* В появившемся окне указать адрес сервисной шины (открытой ранее) и название сервиса (Namespace - пространство имен, будет использовано в последующем в коде).
* Нажать "OK"

#### Написание кода адаптера

После того как приложение создано, будет открыт файл с возможностью редактирования кода:

![](/images/pages/products/flexberry-servicebus/adapters/method-main.png)

Код файла `Program.cs` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp1/ConsoleApp1/Program.cs):

```csharp
using ConsoleApp1.ServiceBus;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string adaptersCommand = "";

            while (adaptersCommand != "exit")
            {
                Console.WriteLine("Enter your name (for exit type \"exit\"):");

                adaptersCommand = Console.ReadLine();

                if (adaptersCommand != "exit")
                {
                    using (var ServiceBus = new ServiceBus.ServiceBusServiceClient())
                    {
                        // Ввести сообщение.
                        var message = new MessageForESB
                        {
                            ClientID = ConfigurationManager.AppSettings["ServiceID4SB"],
                            MessageTypeID = ConfigurationManager.AppSettings["MessageTypeID"],
                            Body = "Hello from " + adaptersCommand + "!"
                        };

                        // Отправить сообщение через шину.
                        ServiceBus.SendMessageToESB(message);

                        ServiceBus.Close();
                    }
                }
            }
        }
    }
}
```

{% include note.html content="_ConsoleApp1_ в _using ConsoleApp1.ServiceBus;_ - это название созданного приложения,  _ServiceBus_ - это пространство имен, указанное при подключении сервиса шины (в коде должно использоваться _заданное на предыдущем этапе_ пространство имен)." %}

#### Настройка файла конфигурации

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp1/ConsoleApp1/App.config):

```xml
<appSettings>
    <add key="ServiceID4SB" value="{e053821c-e44a-4547-b8d1-162f44e59f90}"/>
    <add key="MessageTypeID" value="{cc54b462-b76d-4b7c-981c-295645f3b5a1}"/>
</appSettings>
    <system.serviceModel>
        <bindings>
            <customBinding>
                <binding name="WSHttpBinding_IServiceBusService">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
                <binding name="WSHttpBinding_IServiceBusInterop">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
                <binding name="WSHttpBinding_ICallbackSubscriber">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
            </customBinding>
          <wsHttpBinding>
            <binding name="WSHttpBinding_IServiceBusService">
              <security mode="None"/>
            </binding>
          </wsHttpBinding>
        </bindings>
        <client>
            <endpoint address="http://localhost:7075/WcfService"
                binding="wsHttpBinding" bindingConfiguration="WSHttpBinding_IServiceBusService"
                contract="ServiceBus.IServiceBusService" name="WSHttpBinding_IServiceBusService" />
            <endpoint address="http://localhost:7075/WcfService"
                binding="customBinding" bindingConfiguration="WSHttpBinding_IServiceBusInterop"
                contract="ServiceBus.IServiceBusInterop" name="WSHttpBinding_IServiceBusInterop" />
            <endpoint address="http://localhost:7075/WcfService"
                binding="customBinding" bindingConfiguration="WSHttpBinding_ICallbackSubscriber"
                contract="ServiceBus.ICallbackSubscriber" name="WSHttpBinding_ICallbackSubscriber" />
        </client>
    </system.serviceModel>
```

Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из административного приложения.

Свойство "address" в секции `client` должно содержать адрес сервиса шины (см. пункт "Работа с административным приложением").

## Пример создания клиента, принимающего сообщения

#### Создать консольное приложение

Консольное приложение для клиента, принимающего сообщения, создается по тому же алгоритму, что и для адаптера, отправляющего сообщения.

#### Добавить ссылку на сервис шины в приложение

* Нажать правой клавишей мыши на проект в `Solution Explorer`
* В контекстном меню выберать пункт `Add Service Reference…`
* В появившемся окне указать адрес сервиса шины и пространство имен (Namespace).
* Нажать "OK"

#### Написание кода адаптера

Код файла `Program.cs` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp3/ConsoleApp3/Program.cs):

```csharp
using ConsoleApp3.ServiceBus;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp3
{
    class Program
    {
        static void Main(string[] args)
        {
            string adaptersCommand = "";

            while (adaptersCommand != "exit")
            {
                using (var ServiceBus = new ServiceBus.ServiceBusServiceClient())
                {
                    string serviceID = ConfigurationManager.AppSettings["ServiceID4SB"];
                    string messageTypeID = ConfigurationManager.AppSettings["MessageTypeID"];

                    //Получить все адресованные сообщения (при запуске).
                    MessageFromESB message = ServiceBus.GetMessageFromESB(serviceID, messageTypeID);
                    Console.WriteLine(message.Body);

                    // Получить новые сообщения/выйти из приложения.
                    Console.WriteLine("Enter \"get\" for get a message (for exit type \"exit\"):");
                    adaptersCommand = Console.ReadLine();

                    while (message != null)
                    {
                        // Получить новый список сообщений через команду "get".
                        message = ServiceBus.GetMessageFromESB(serviceID, messageTypeID);
                    }
                }
            }
        }
    }
}
```

{% include note.html content="`ConsoleApp3` в `using ConsoleApp3.ServiceBus;` - это название созданного приложения,  _ServiceBus_ - это пространство имен, указанное при подключении сервиса шины (в коде должно использоваться _заданное на предыдущем этапе_ пространство имен)." %}

#### Дополнить конфигурационный файл

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp3/ConsoleApp3/App.config):

```xml
  <appSettings>
    <add key="ServiceID4SB" value="7"/>
    <add key="MessageTypeID" value="4"/>
  </appSettings>
  <system.serviceModel>
    <bindings>
      <customBinding>
        <binding name="WSHttpBinding_IServiceBusService">
          <textMessageEncoding messageVersion="Soap11WSAddressing10" />
          <httpTransport />
        </binding>
        <binding name="WSHttpBinding_IServiceBusInterop">
          <textMessageEncoding messageVersion="Soap11WSAddressing10" />
          <httpTransport />
        </binding>
        <binding name="WSHttpBinding_ICallbackSubscriber">
          <textMessageEncoding messageVersion="Soap11WSAddressing10" />
          <httpTransport />
        </binding>
      </customBinding>
      <wsHttpBinding>
        <binding name="WSHttpBinding_IServiceBusService">
          <security mode="None"/>
        </binding>
      </wsHttpBinding>
    </bindings>
    <client>
      <endpoint address="http://localhost:7075/WcfService"
          binding="wsHttpBinding" bindingConfiguration="WSHttpBinding_IServiceBusService"
          contract="ServiceBus.IServiceBusService" name="WSHttpBinding_IServiceBusService" />
      <endpoint address="http://localhost:7075/WcfService"
          binding="customBinding" bindingConfiguration="WSHttpBinding_IServiceBusInterop"
          contract="ServiceBus.IServiceBusInterop" name="WSHttpBinding_IServiceBusInterop" />
      <endpoint address="http://localhost:7075/WcfService"
          binding="customBinding" bindingConfiguration="WSHttpBinding_ICallbackSubscriber"
          contract="ServiceBus.ICallbackSubscriber" name="WSHttpBinding_ICallbackSubscriber" />
    </client>
  </system.serviceModel>
```

Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из административного приложения.

Свойство "address" в секции `client` должно содержать адрес сервиса шины (см. пункт "Работа с административным приложением").

## Пример создания клиента, принимающего сообщения с использованием ICallbackSubscriber

Адаптер, использующий интерфейс `ICallbackSubscriber`, проверяет наличе отправленных на его адрес сообщений и передает принятое сообщение при его наличии в приложение-получатель. Инициатором передачи сообщения является сам сервис шины.

#### Создать консольное приложение

Консольное приложение для клиента, принимающего сообщения, создается по тому же алгоритму, что и для адаптера, отправляющего сообщения.

#### Добавить ссылку на сервис шины в приложение

* Нажать правой клавишей мыши на проект в `Solution Explorer`
* В контекстном меню выберать пункт `Add Service Reference…`
* В появившемся окне указать адрес сервиса шины и пространство имен (Namespace).
* Нажать "OK"

#### Написание кода адаптера

Код файла `Program.cs` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp2/ConsoleApp2/Program.cs):

```csharp
using ConsoleApp2.ServiceBus;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleApp2
{
    class Program
        static void Main(string[] args)
        {
            var messageFromESB = new MessageFromESB();
            MyServiceHost.StartService();

            Console.WriteLine("MsgListener started.");
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();

            MyServiceHost.StopService();
        }

        internal class MyServiceHost
        {
            private static Thread ScanThread;

            internal static ServiceHost myServiceHost = null;

            internal static void StartService()
            {
                myServiceHost = new ServiceHost(new MsgListenerClass());

                myServiceHost.Open();

                //ScanThread = MsgListenerClass.GetScanningThread();
                //ScanThread.Start();
            }

            internal static void StopService()
            {
                //ScanThread.Abort();

                if (myServiceHost.State != CommunicationState.Closed)
                    myServiceHost.Close();
            }
        }

    }

    [ServiceContract(ConfigurationName = "MsgListener.ICallbackSubscriber")]
    public interface ICallbackSubscriber
    {
        [OperationContract]
        void AcceptMessage(MessageFromESB msg);

        [OperationContract]
        void RiseEvent(string ИдТипаСобытия);

        string GetSourceId();
    }

    [ServiceBehavior(InstanceContextMode =InstanceContextMode.Single)]
    public class MsgListenerClass : ICallbackSubscriber
    {
        public string GetSourceId()
        {
            return ConfigurationManager.AppSettings["ExternalKey"];
        }

        public void AcceptMessage(MessageFromESB msg)
        {
            Console.WriteLine(msg.Body);
        }

        public void RiseEvent(string ИдТипаСобытия)
        {
            Console.WriteLine(ИдТипаСобытия);
        }

        internal static void SubscribeMe4Messages(string ИдТипаСообщения)
        {
            using (ServiceBus.ServiceBusServiceClient ServiceBus = new
            ServiceBus.ServiceBusServiceClient())
            {
                ServiceBus.SubscribeClientForMessageCallback(
                ConfigurationManager.AppSettings["ServiceID4SB"],
                ИдТипаСообщения);

                ServiceBus.Close();
            }
        }

        private static void NewSubscribeOrUpdate()
        {
            while (true)
            {
                SubscribeMe4Messages(
                ConfigurationManager.AppSettings["MessageTypeID"]);

                Thread.Sleep(Convert.ToInt32(
                ConfigurationManager.AppSettings["ScanPeriod"]));
            }
        }

        public static Thread GetScanningThread()
        {
            return new Thread(NewSubscribeOrUpdate);
        }
    }
```

{% include note.html content="`ConsoleApp2` в `using ConsoleApp2.ServiceBus;` - это название созданного приложения,  _ServiceBus_ - это пространство имен, указанное при подключении сервиса шины (в коде должно использоваться _заданное на предыдущем этапе_ пространство имен)." %}

#### Дополнить конфигурационный файл

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp2/ConsoleApp2/App.config):

```xml
<appSettings>
<add key="ExternalKey"  value="MsgListener" />
<add key="ServiceID4SB" value ="{6b1a2d64-d904-4262-9a4b-170d7cba14b5}"/>
<add key ="MessageTypeID" value ="{e053821c-e44a-4547-b8d1-162f44e59f90}"/>
<add key="ScanPeriod" value="3000"/>
</appSettings>
<startup>
    <supportedRuntime version="v4.0" sku=".NETFramework,Version=v4.5"/>
</startup>
    <system.serviceModel>
        <bindings>
            <customBinding>
                <binding name="WSHttpBinding_IServiceBusService">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
                <binding name="WSHttpBinding_IServiceBusInterop">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
                <binding name="WSHttpBinding_ICallbackSubscriber">
                    <textMessageEncoding messageVersion="Soap11WSAddressing10" />
                    <httpTransport />
                </binding>
            </customBinding>
        </bindings>
        <client>
            <endpoint address="http://localhost:7075/MonoPostgreSQLWcfService"
                binding="customBinding" bindingConfiguration="WSHttpBinding_IServiceBusService"
                contract="ServiceBus.IServiceBusService" name="WSHttpBinding_IServiceBusService" />
            <endpoint address="http://localhost:7075/MonoPostgreSQLWcfService"
                binding="customBinding" bindingConfiguration="WSHttpBinding_IServiceBusInterop"
                contract="ServiceBus.IServiceBusInterop" name="WSHttpBinding_IServiceBusInterop" />
            <endpoint address="http://localhost:7075/MonoPostgreSQLWcfService"
                binding="customBinding" bindingConfiguration="WSHttpBinding_ICallbackSubscriber"
                contract="ServiceBus.ICallbackSubscriber" name="WSHttpBinding_ICallbackSubscriber" />
        </client>
      <services>
        <service name="ConsoleApp2.MsgListenerClass" behaviorConfiguration="MsgListenerClientBehaviors">
          <host>
            <baseAddresses>
              <add baseAddress="http://localhost:8080/Listener"/>
            </baseAddresses>
          </host>
          <endpoint contract="MsgListener.ICallbackSubscriber" binding="basicHttpBinding"/>
          <endpoint contract="IMetadataExchange" binding="mexHttpBinding" address="mex" />
        </service>
      </services>

      <behaviors>
        <serviceBehaviors>
          <behavior name="MsgListenerClientBehaviors" >
            <serviceMetadata httpGetEnabled="true" />
            <serviceDebug includeExceptionDetailInFaults="True" />
          </behavior>
        </serviceBehaviors>
      </behaviors>
    </system.serviceModel>
```

Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из административного приложения. `ScanPeriod` – значение обновления подписки в миллисекундах. `ExternalKey` – внешний идентификатор для клиента.

Свойство "address" в секции `client` должно содержать адрес сервиса шины (см. пункт "Работа с административным приложением").

Свойство "baseAddress" в секции `host` должно содержать адрес, на котором адаптер будет прослушивать входящие сообщения. Данный адрес указывается в настройке клиента, принимающего сообщения, в приложении Администратора для шины (см. пункт "Работа с административным приложением")

## Результаты взаимодействия адаптеров отправки-приема сообщений

Для отправки-получения сообщений нужно запустить приложения.

{% include note.html content="Для запуска приложений `MS Visual Studio` должна быть запущена от имени Администратора" %}

Результатом выполнения будет сообщение с текстом отправленного сообщения в адаптере, принимающем сообщения.

![](/images/pages/products/flexberry-servicebus/adapters/result.png)

## Возможные проблемы

### Не приходят сообщения в приложение-получатель с callback

В консольное приложение для получения сообщений не приходит отправленное сообщение, а в административном приложении отображены ошибки отправки-получения.

_Решение_

Заменить адрес для адаптера и клиента-получателя из административного приложения, установив в `http://localhost:8080/Listener` вместо _localhost_ IP компьютера. Узнать IP компьютера можно, набрав в консоли (Win+r, далее cmd) `ipconfig`.

Пример:

```csharp
<baseAddresses>
    <add baseAddress="http://111.11.111.11:8080/Listener"/>
</baseAddresses>
```
