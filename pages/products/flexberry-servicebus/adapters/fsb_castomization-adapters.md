---
title: Правила создания и настройки адаптеров
sidebar: flexberry-servicebus_sidebar
keywords: DnsIdentity, Callback, Тип сообщения, Шина, Адаптер
toc: true
permalink: ru/fsb_castomization-adapters.html
lang: ru
summary: 
---

Для создания адаптеров необходимо выполнить подготовительные этапы:

1. Установить Docker
3. Запустить [сервис шины](fsb_installation.html) с помощью командного интерпритатора
4. Убедиться, что шина запущена (команда `docker ps`, результатом выполнения которой являются 3 строки)
5. Запустить приложение Администратора [http://localhost:180/](http://localhost:180/), `логин/пароль admin/admin`
6. Запустить [интерфейсы шины](fsb_installation.html) по адресу [http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService](http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService)

---------------------------------------------
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

Далее вставить в метод Main код:

```csharp
string s = "";

    while (s != "exit")
    {
        Console.WriteLine("Enter your name (for exit type \"exit\"):");

        s = Console.ReadLine();

        if (s != "exit")
        {
            using (var ServiceBus = new ServiceBusServiceClient("SBService"))
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
                    Body = ToolZIP.Compress("Hello from " + s + "!")
                };

                // И отправим его через шину.
                ServiceBus.SendMessageToESB(message);

                ServiceBus.Close();
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
* В открывшемся окне введите наименование клиента и адрес, на который будут приходить сообщения от сервисной шины
* Сохраните клиента, выбрав пункт меню «Сохранить и закрыть»

