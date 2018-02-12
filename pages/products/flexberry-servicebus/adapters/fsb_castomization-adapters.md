---
title: Правила создания и настройки адаптеров
sidebar: flexberry-servicebus_sidebar
keywords: DnsIdentity, Callback, Тип сообщения, Шина, Адаптер
toc: true
permalink: ru/fsb_castomization-adapters.html
lang: ru
summary: 
---

Создание адептеров (или клиентов) шины продемонстрировано на двух примерах:

* `MsgSender` – это консольное приложение, не имеющее собственного сервиса для подписок. После запуска приложения, оно просит пользователя ввести строку и затем отправляет введенное сообщение в сервисную шину.
* `MsgListener` – консольное приложение с собственным сервисом для приема сообщений. После запуска приложения, оно выводит на консоль текст приходящих сообщений.

## Разработка MsgSender

#### Создать консольное приложение

Для создания консольного приложения можно использовать [MS Visual Studio]()

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app.png)

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app2.png)

#### Добавить код адаптера

После того как приложение создано, будет открыт файл с возможностью редактирования кода:

![](/images/pages/products/flexberry-servicebus/adapters/method-main.png)

Далее нужно добавить nuget-пакет [NewPlatform.Flexberry.ServiceBus.ClientTools](https://www.nuget.org/packages/NewPlatform.Flexberry.ServiceBus.ClientTools) с актуальной версией (самое большое число без слов).

Для этого следует щелкнуть правой кнопкой "мыши" на Solution и выбрать пункт Manage NuGet Packages for Solution...:

![](/images/pages/products/flexberry-servicebus/adapters/nuget-manager.png)

Выбрать пункт меню Browse и вставить название пакета в окне поиска. Когда пакет будет найден, в окне справа отметить "галочкой" название созданного приложения и нажать Install:

![](/images/pages/products/flexberry-servicebus/adapters/install-packege.png)

Если будет предложена установка ряда дополнительных пакетов в появившемся окне, нажать ОК. В следующем появившемся окне нажать I Accept.

Далее код файла Program.cs необходимо заменить следующим:

```csharp
namespace MsgSender
{
    using MsgSender.ServiceBusServiceClient;
    using System;
    using System.Net;
    using System.Configuration;

    class Program
    {
        static void Main(string[] args)
        {
            string s = "";

            while (s != "exit")
            {
                Console.WriteLine("Enter your name (for exit type \"exit\"):");

                s = Console.ReadLine();

                if (s != "exit")
                {
                    using (var ServiceBus = new ServiceBusServiceClient.ServiceBusServiceClient("SBService"))
                    {
                        // Установим прокси, если нужно.
                        var useProxy = ConfigurationManager.AppSettings["UseProxy"];
                        if (!string.IsNullOrEmpty(useProxy) && useProxy.ToLower() == "true")
                        {
                            var proxy = new WebProxy(ConfigurationManager.AppSettings["ProxyServer"], true)
                            {
                                Credentials = new NetworkCredential(ConfigurationManager.AppSettings["ProxyLogin"], ConfigurationManager.AppSettings["ProxyPass"])
                            };
                            WebRequest.DefaultWebProxy = proxy;
                        }

                        // Создадим сообщение.
                        var message = new MessageForESB
                        {
                            ClientID = ConfigurationManager.AppSettings["ServiceID4SB"],
                            MessageTypeID = ConfigurationManager.AppSettings["MessageTypeID"],
                            Body = "Hello from " + s + "!" 
                        };

                        // И отправим его через шину.
                        ServiceBus.SendMessageToESB(message);

                        ServiceBus.Close();
                    }
                }
            }
        }
    }
}
```

#### Создать новый тип сообщений

* Запустить приложение администратора:
    * локально по адресу [http://localhost:180/](http://localhost:180/). Данный вариант возможен, если используется [Docker](fsb_installation.html).
    * с помощью адреса предоставленного Администратором, в случае централизованно развернутой [шины](fsb_landing_page.html)
* Выбрать пункт меню "Типы сообщений"
* Нажать кнопку "Добавить"
* В открывшемся форме указать наименование клиента (при необходимости и комментарий)
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/adapters/hello-type.png)

#### Добавить ссылку на сервисную шину в приложение

* Запустить сервисную шину:
    * локально по адресу [http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService](http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService). Данный вариант возможен, если используется [Docker](fsb_installation.html).
    * с помощью адреса предоставленного Администратором, в случае централизованно развернутой шины
* Нажать в MS Visual Studio правой клавишей мыши на проект в `Solution Explorer`
* В контекстном меню выберать пункт `Add Service Reference…`

![](/images/pages/products/flexberry-servicebus/adapters/add-reference.png)

* В появившемся окне указать адрес сервисной шины (открытой ранее) и название сервиса

![](/images/pages/products/flexberry-servicebus/adapters/window-reference.png)

* Нажать "OK"

#### Добавить ссылки на следующие библиотеки

* ICSSoft.STORMNET.Tools 
* System.Configuration

Сначала следует убедиться, что ссылки не добавлены по умолчанию. Для этого в Solution нужно распахнуть папку Reference.

Если ссылки не установлены, то щелкнуть правой кнопкой "мыши" по Reference, далее Add reference... . В появившемся окне выбрать Assemblies/Framework. Далее отметить "галочкой" необходимые библиотеки (существующие отметки не снимать).

![](/images/pages/products/flexberry-servicebus/adapters/add-lib.png)

* Нажать "OK"

## Зарегистрировать клиента в сервисной шине

* Открыть приложение администратора:
    * локально по адресу [http://localhost:180/](http://localhost:180/). Данный вариант возможен, если используется [Docker](fsb_installation.html).
    * с помощью адреса предоставленного Администратором, в случае централизованно развернутой [шины](fsb_landing_page.html)
* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Клиенты"
* Нажать кнопку "Добавить"
* В открывшемся форме указать наименование клиента
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/adapters/add-client.png)

#### Настроить файл конфигурации

* Открыть в MS Visual Studio в проекте файл App.config
* Добавить следующую секцию:

```csharp
<appSettings>
    <add key="ServiceID4SB" value="{e053821c-e44a-4547-b8d1-162f44e59f90}"/>
    <add key="MessageTypeID" value="{cc54b462-b76d-4b7c-981c-295645f3b5a1}"/>
</appSettings>
```

![](/images/pages/products/flexberry-servicebus/adapters/castomization-config.png)

* Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из приложения администратора.

## Разработка MsgListener

#### Создать консольное приложение

Консольное приложение для MsgListener создается также, как и для MsgSender.

#### Добавить ссылки на следующие библиотеки

Также, как и для MsgSender необходимо добавить ссылки на некоторые библиотеки:

* System.ServiceModel
* ICSSoft.STORMNET.Tools
* System.Configuration

#### Добавить код адаптера

Также нужно добавить nuget-пакет [NewPlatform.Flexberry.ServiceBus.ClientTools](https://www.nuget.org/packages/NewPlatform.Flexberry.ServiceBus.ClientTools) с актуальной версией (самое большое число без слов).

Далее код файла Program.cs необходимо заменить следующим:

```csharp
namespace MsgListener
{
    using System;
    using System.Configuration;
    using System.ServiceModel;
    using System.Threading;
    using ICSSoft.STORMNET.Tools;
    using MsgListener.ESB;

    class Program
    {
        static void Main()
        {
            MyServiceHost.StartService();

            Console.WriteLine("MsgListener started.");
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();

            MyServiceHost.StopService();
        }
    }

    internal class MyServiceHost
    {
        private static Thread ScanThread;

        internal static ServiceHost myServiceHost = null;

        internal static void StartService()
        {
            myServiceHost = new ServiceHost(typeof(MsgListenerClass));

            myServiceHost.Open();

            ScanThread = MsgListenerClass.GetScanningThread();
            ScanThread.Start();
        }

        internal static void StopService()
        {
            ScanThread.Abort();

            if (myServiceHost.State != CommunicationState.Closed)
                myServiceHost.Close();
        }
    }

    [ServiceContract]
    public interface ICallbackSubscriber
    {
        [OperationContract]
        void AcceptMessage(MessageFromESB msg);

        [OperationContract]
        void RiseEvent(string ИдТипаСобытия);

        string GetSourceId();
    }

    public class MsgListenerClass : ICallbackSubscriber
    {
        public string GetSourceId()
        {
            return ConfigurationManager.AppSettings["ExternalKey"];
        }

        public void AcceptMessage(MessageFromESB msg)
        {
            Console.WriteLine(ToolZIP.Decompress(msg.Body));
        }

        public void RiseEvent(string ИдТипаСобытия)
        {
            Console.WriteLine(ИдТипаСобытия);
        }

        internal static void SubscribeMe4Messages(string ИдТипаСообщения)
        {
            using (ServiceBusServiceClient ServiceBus = new
            ServiceBusServiceClient())
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
}
```

#### Зарегистрировать клиента в сервисной шине

* Запустите приложение администратора также, как и для MsgSender (если не было запущено ранее)
* Зайти в контейнер "Клиенты"
* Выберите пункт меню "Создать"
* В открывшемся окне ввести наименование клиента и адрес, на который будут приходить сообщения от сервисной шины
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/adapters/add-client-msglistener.png)

#### Дополнить конфигурационный файл

* Открыть файл с названием app.config
* Дополнить его код следующим:

```xml
  <appSettings>
    <add key="ExternalKey"  value="MsgListener" />
    <add key="ServiceID4SB" value ="{45ef0dc6-645c-4e8d-bd28-7abc8459c5cb}"/>
    <add key ="MessageTypeID" value ="{1c5207ae-6e48-4798-9a2a-188d3b096d8e}"/>
    <add key="ScanPeriod" value="3000"/>
  </appSettings>

  <system.serviceModel>
    <services>
      <service name="MsgListener.MsgListenerClass"
	behaviorConfiguration="MsgListenerClientBehaviors">
        <host>
          <baseAddresses>
            <add baseAddress="http://localhost:8080/MsgListener"/>
          </baseAddresses>
        </host>
        <endpoint contract="MsgListener.ICallbackSubscriber" binding="wsHttpBinding"/>
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

* Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из приложения администратора. `ScanPeriod` – значение обновления подписки в миллисекундах. `ExternalKey` – внешний идентификатор для клиента.

#### Добавить ссылку на сервисную шину в приложение

* Запустить сервисную шину также, как и для MsgSender (если не было запущено ранее)
* Нажать правой клавишей мыши на проект в Solution Explorer
* В контекстном меню выберать пункт Add Service Reference…
* В появившемся окне указать адрес сервисной шины и название сервиса (также, как и для MsgSender)
* Нажать "OK"

Результатом выполнения будет сообщение с текстом из TestSender должно прийти в TestListener.
