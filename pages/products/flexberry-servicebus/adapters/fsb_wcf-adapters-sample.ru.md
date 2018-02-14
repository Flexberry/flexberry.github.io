---
title: Пример создания и настройки адаптеров
sidebar: flexberry-servicebus_sidebar
keywords: DnsIdentity, Callback, Тип сообщения, Шина, Адаптер
toc: true
permalink: ru/fsb_wcf-adapters-sample.html
lang: ru
summary: Поэтапное создание приложений для отправки и приема сообщений с помощью Flexberry Service Bus
---

Данная статья на примерах описывает создание [адептеров](fsb_adapters.html) (или клиентов) для [Flexberry Service Bus](fsb_landing_page.html) (сервиса шины), позволяющих как отправлять сообщения, так и принимать их. Успешным итогом реализации таких адаптеров является успешно принятое соответствующим клиентом сообщение.

Для создания адаптеров по примеру потребуются следующие инструменты:

* [MS Visual Studio](https://www.visualstudio.com) - для создания собственно приложений адаптеров отправки/приема сообщений
* [Docker](fsb_installation.html) - программное обеспечение для автоматизации развёртывания и управления приложениями в среде виртуализации на уровне операционной системы. Необходим, если нет уже развернутых и готовых к использованию приложения Администратора шины и ее сервиса.
* [PowerShell](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6) - необходим для работы с Docker, если требуется [локальная настройка](fsb_installation.html) и запуск шины и ее сервиса

В процессе реализации примера будет создано три консольных приложения:

* Приложение, с помощью которого будут __отправлены сообщения__. В данном случае это будет консольное приложение, в командную строку которого вводится сообщение, в последствии передаваемое шиной принимающему приложению.
* Приложение, __принимающее переданные сообщения__. Для примера также используется консольное приложение, результатом работы которого будет отображение принятого им сообщение, переданное с помощью шины от приложения-отправителя.

Код описываемых приложений выложен в примерах для `Flexberry Service Bus` на [GitHub](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples). Принципы работы с GitHub описаны на сайте [https://help.github.com](https://help.github.com/articles/cloning-a-repository/) или на сайте [https://git-scm.com](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)

## Работа с приложением Администратора

Для работы с приложениями отправки-приема сообщений требуется иметь доступ к запущенной инстанции шины (приложению Администратора). Для этого действия нужен адрес приложения Администратора и адрес запущенного сервиса шины. Получить их можно следующими способами:
* приложение Администратора - локально по адресу [http://localhost:180/](http://localhost:180/). Данный вариант возможен, если используется [Docker](fsb_installation.html).
* сервис - локально по адресу [http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService](http://127.0.0.1:7075/HighwaySBMonoPostgreSQLWcfService). Данный вариант возможен, если используется `Docker`.
* с помощью адресов, предоставленных Администратором, в случае централизованно развернутой шины

Также для последующей работы адаптеров нужно:
* создать тип сообщений, которые будут участвовать в процессе-передачи-приема
* клиентов шины
* оформить подписки для клиентов, принимающих сообщения
* разрешения на передачу сообщений.

### Создание типа сообщений

* Запустить приложение Администратора
* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Типы сообщений"
* Нажать кнопку "Добавить"
* На открывшейся форме указать:
    * наименование типа сообщений- можно задать произвольное
    * ID (или PK) - генерируется автоматически, потребуется при настройке адаптеров. Наименование можно изменить на строку, написанную верюлюжбим стилем (например, MessageType).
    * также можно указать комментарий.
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/adapters/hello-type.png)

### Регистрация клиента в сервисной шине

* Открыть приложение Администратора
* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Клиенты"
* Нажать кнопку "Добавить"
* На открывшейся форме указать:
    * наименование клиента - можно задать произвольное
    * ID (или PK) - генерируется автоматически, потребуется при настройке адаптеров. Наименование можно изменить на строку, написанную верюлюжбим стилем (например, MessageSender).
    Клиентов для примера потребуется три, соответственно, их ID должны позволять отличать клиентов друг от друга и использовать в настройке соответствующего адаптера.
    * адрес требуется для клиентов, принимающих сообщения. Его необходимо будет взять из конфига соответствующего созданного адаптера, принимающего сообщения.
* Нажать "Сохранить и закрыть" на форме.

![](/images/pages/products/flexberry-servicebus/adapters/add-client.png)

#### Разрешение на отправку сооьщений

Для клиента, передающего сообщение, нужно добавить разрешение на отправку сообщений:
* развернуть пункт меню "Разрешения на отправку сообщений"
* нажать "Добавить"
* в строке выбрать созданный ранее тип сообщений
* нажать "Сохранить и закрыть" на форме.

![](/images/pages/products/flexberry-servicebus/adapters/permission-message.png)

#### Формирование подписки на сообщение

Для клиента, принимающего сообщение, нужно сформировать подписку на сообщение. Для описываемого примера подписки понадобится две: для ...

* В дереве слева в пункте "Маршрутизация" выбрать пункт меню "Подписки на сообщения"
* На открывшейся форме:
    * выбрать клиента, принимающего сообщение, созданного ранее
    * тип сообщения, созданный ранее
    * установить дату прекращения действия подписки
    * "Передавать через" - это сервис, используемый для передачи сообщений. Для примера нужен WCF.
    * если клиент использует... установить флаг "Callback"
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/adapters/subscription-message.png)

## Пример создания клиента, отправляющего сообщения

#### Создание консольного приложения

Для создания консольного приложения можно использовать [MS Visual Studio](https://www.visualstudio.com).

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app.png)

В открывшемся окне ввести название приложения (в примере MsgSender) и место его расположения.

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app2.png)

#### Написание кода адаптера

После того как приложение создано, будет открыт файл с возможностью редактирования кода:

![](/images/pages/products/flexberry-servicebus/adapters/method-main.png)

Далее нужно добавить nuget-пакет [NewPlatform.Flexberry.ServiceBus.ClientTools](https://www.nuget.org/packages/NewPlatform.Flexberry.ServiceBus.ClientTools) с актуальной версией.

{% include important.html content="Флаг `Include prerelease` должен быть выключен." %}

Для этого следует щелкнуть правой кнопкой "мыши" на `Solution` и выбрать пункт `Manage NuGet Packages for Solution...`:

![](/images/pages/products/flexberry-servicebus/adapters/nuget-manager.png)

Выбрать пункт меню `Browse` и вставить название пакета в окне поиска. Когда пакет будет найден, в окне справа отметить "галочкой" название созданного приложения и нажать `Install`:

![](/images/pages/products/flexberry-servicebus/adapters/install-packege.png)

Если будет предложена установка ряда дополнительных пакетов в появившемся окне, нажать "ОК". В следующем появившемся окне нажать `I Accept`.

Далее код файла `Program.cs` в `namespace` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/MsgSender/MsgSender/Program.cs):

```csharp
using ServiceBusServiceClient;
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
                using (var ServiceBus = new ServiceBusServiceClient.ServiceBusServiceClient())
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
```

#### Добавление ссылки на сервис шины в приложение

Для добавления ссылки средствами `MS Visual Studio` требуется иметь доступ к запущенной инстанции шины (см. пункт "Работа с приложением администратора").

В `MS Visual Studio` в `Solution Explorer` правой клавишей мыши нажать на проект созданного адаптера, отправляющего сообщения, и в  контекстном меню выбрать пункт `Add Service Reference…`

![](/images/pages/products/flexberry-servicebus/adapters/add-reference.png)

* В появившемся окне указать адрес сервисной шины (открытой ранее) и название сервиса

![](/images/pages/products/flexberry-servicebus/adapters/window-reference.png)

* Нажать "OK"

#### Настройка файла конфигурации

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/MsgSender/MsgSender/App.config):

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
          <security mode="None" />
        </binding>
      </wsHttpBinding>
    </bindings>
    <client>
      <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService"
          binding="wsHttpBinding" bindingConfiguration="WSHttpBinding_IServiceBusService"
          contract="ServiceBusServiceClient.IServiceBusService" name="WSHttpBinding_IServiceBusService" />
      <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService"
          binding="customBinding" bindingConfiguration="WSHttpBinding_IServiceBusInterop"
          contract="ServiceBusServiceClient.IServiceBusInterop" name="WSHttpBinding_IServiceBusInterop" />
      <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService"
          binding="customBinding" bindingConfiguration="WSHttpBinding_ICallbackSubscriber"
          contract="ServiceBusServiceClient.ICallbackSubscriber" name="WSHttpBinding_ICallbackSubscriber" />
    </client>
  </system.serviceModel>
```

Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из приложения Администратора.

Свойство "address" в секции `client` должно содержать адрес сервиса шины (см. пункт "Работа с приложением администратора").

##### Возможные проблемы

Ошибки при компиляции приложения:
* не установлена ссылка на сборку `System.Configuration`. Для добавления ссылки щелкнуть правой кнопкой "мыши" по `Reference`, далее `Add reference...`. В появившемся окне выбрать `Assemblies/Framework`. Далее отметить "галочкой" необходимые библиотеки (существующие отметки не снимать).

![](/images/pages/products/flexberry-servicebus/adapters/add-lib.png)

Нажать "OK".

## Пример создания клиента, принимающего сообщения

#### Создать консольное приложение

Консольное приложение для клиента, принимающего сообщения, создается по тому же алгоритму, что и для адаптера, отправляющего сообщения.

#### Написание кода адаптера

Для клиента, принимающего сообщения, также нужно добавить nuget-пакет [NewPlatform.Flexberry.ServiceBus.ClientTools](https://www.nuget.org/packages/NewPlatform.Flexberry.ServiceBus.ClientTools) с актуальной версией.

{% include important.html content="Флаг `Include prerelease` должен быть выключен." %}

Далее код файла `Program.cs` в `namespace` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/MsgListener/MsgListener/Program.cs):

```csharp
using System;
using System.Configuration;
using System.ServiceModel;
using System.Threading;
using ICSSoft.STORMNET.Tools;
using ServiceBusServiceClient;

class Program
{
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
            var service = new MsgListenerClass();
            myServiceHost = new ServiceHost(service);

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

[ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Multiple, InstanceContextMode = InstanceContextMode.Single)]
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
        using (ServiceBusServiceClient.ServiceBusServiceClient ServiceBus = new
        ServiceBusServiceClient.ServiceBusServiceClient())
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

#### Дополнить конфигурационный файл

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/MsgListener/MsgListener/App.config):

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
        <textMessageEncoding messageVersion="Soap11WSAddressing10"/>
        <httpTransport/>
    </binding>
    <binding name="WSHttpBinding_IServiceBusInterop">
        <textMessageEncoding messageVersion="Soap11WSAddressing10"/>
        <httpTransport/>
    </binding>
    <binding name="WSHttpBinding_ICallbackSubscriber">
        <textMessageEncoding messageVersion="Soap11WSAddressing10"/>
        <httpTransport/>
    </binding>
    </customBinding>
    <wsHttpBinding>
    <binding name="BINDDD">
        <security mode="None"/>
    </binding>
    </wsHttpBinding>
</bindings>
<client>
    <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService" binding="customBinding"
        bindingConfiguration="WSHttpBinding_IServiceBusService" contract="ServiceBusServiceClient.IServiceBusService"
        name="WSHttpBinding_IServiceBusService"/>
    <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService" binding="customBinding"
        bindingConfiguration="WSHttpBinding_IServiceBusInterop" contract="ServiceBusServiceClient.IServiceBusInterop"
        name="WSHttpBinding_IServiceBusInterop"/>
    <endpoint address="http://localhost:7075/HighwaySBMonoPostgreSQLWcfService" binding="customBinding"
        bindingConfiguration="WSHttpBinding_ICallbackSubscriber" contract="ServiceBusServiceClient.ICallbackSubscriber"
        name="WSHttpBinding_ICallbackSubscriber"/>
</client>
<services>
    <service name="MsgListener.MsgListenerClass" behaviorConfiguration="MsgListenerClientBehaviors">
    <endpoint address="" binding="wsHttpBinding" bindingConfiguration="BINDDD" name="MsgListenerClass" contract="MsgListener.ICallbackSubscriber"/>
    <endpoint contract="IMetadataExchange" binding="mexHttpBinding" address="mex" />
    <host>
        <baseAddresses>
        <add baseAddress="http://localhost:8080/MsgListener"/>
        </baseAddresses>
    </host>
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

Для ключей `ServiceID4SB` (клиент) и `MessageTypeID` (сообщение) указать ключи из приложения Администратора. `ScanPeriod` – значение обновления подписки в миллисекундах. `ExternalKey` – внешний идентификатор для клиента.

Свойство "address" в секции `client` должно содержать адрес сервиса шины (см. пункт "Работа с приложением администратора").

Свойство "baseAddress" в секции `host` должно содержать адрес, на котором адаптер будет прослушивать входящие сообщения. Данный адрес указывается в настройке клиента, принимающего сообщения, в приложении Администратора для шины (см. пункт "Работа с приложением Администратора")

#### Добавить ссылку на сервис шины в приложение

* Нажать правой клавишей мыши на проект в `Solution Explorer`
* В контекстном меню выберать пункт `Add Service Reference…`
* В появившемся окне указать адрес сервиса шины и название сервиса
* Нажать "OK"

##### Возможные проблемы

Ошибки при компиляции приложения:
* не установлена ссылка на сборку `System.ServiceModel` - добавить в `Reference` проекта адаптера 
* не установлена ссылка на сборку `System.Configuration` - добавить в `Reference` проекта адаптера
* не установлена ссылка на сборку `ICSSoft.STORMNET.Tools` - добавить nuget-пакет [NewPlatform.Flexberry.ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM)

## Результаты взаимодействия адаптеров отправки-приема сообщений

Результатом выполнения будет сообщение с текстом отправленного сообщения в адаптере, принимающем сообщения.

![](/images/pages/products/flexberry-servicebus/adapters/result.png)
