--- 
title: Example of a WCF-adapters 
sidebar: flexberry-servicebus_sidebar 
keywords: DnsIdentity, Callback, Type, message, Bus, Adapter 
toc: true 
permalink: en/fsb_wcf-adapters-sample.html 
lang: en 
autotranslated: true 
hash: 1daf658c242203fbc80a9efcb11c90a1bcbcad02d60a256a5e07e9669068396e 
summary: step-by-Step guide for building a sample WCF adapter for applications that connect to enterprise bus 
--- 

This article on examples describes a creation of [adapterom](fsb_adapters.html) (or customers) for [Flexberry Service Bus](fsb_landing_page.html) (service bus) that allows user to send messages and to receive them. The result of the implementation of such adapters is successfully adopted by the respective client a message. 

For the creation of adapters for example, you will need the following tools: 

* [MS Visual Studio](https://www.visualstudio.com) - to create their own application adapters sending/receiving messages 
* [Docker](fsb_installation.html) is a software to automate the deployment and management of applications in a virtualization environment on the operating system level. Required, if not already deployed and ready-to-use administrative application bus and its service. 
* [PowerShell](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6) is required to work with Docker, if you want [local settings](fsb_installation.html) and run the tire and its service 

In the process of implementing three example will create a console application: 

* The app, which will be __sent message__. In this case it will be a console application on the command line you enter the message, later to be transferred by bus to the receiving application. 
* App, __host sent message__. For example also use a console application, the output of which will display the received im message, transmitted via bus from the sender application. 
* App, __host sent message with callback__. For example also use a console application, the output of which will display the received im message, transmitted via bus from the sender application. 

Code applications posted in examples for `Flexberry Service Bus` on [GitHub](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples). How to work with GitHub is described on the website [https://help.github.com](https://help.github.com/articles/cloning-a-repository/) or at [https://git-scm.com](https://git-scm.com/book/ru/v1/Введение) 

## Work with the administrative application 

For applications sending / receiving messages, you must have access to a running instance of the bus (administrative application). This action requires the administrative application and the address of a running service bus. You can get them in the following ways: 
* administrative application - locally at [http://localhost:1818/](http://localhost:1818/). This option is possible, if you use [Docker](fsb_installation.html). 
* service - locally at [http://localhost:7075/WcfService](http://localhost:7075/WcfService). This option is available if you use `Docker`. 
* using the addresses provided by the Administrator, in the case of centrally flattened tires 

Also for further work adapters you need to: 
* create a message type that will participate in the process of transmitting / receiving 
* customers ' bus 
* to make the subscription for customers receiving the messages 
* permits transmission of messages. 

### Create message type 

* Run an administrative application 
* In the tree on the left in "Routing" choose the menu item "message Types" 
* Click "Add" 
* On the form to indicate: 
* the name of the message type: you can define arbitrary 
* ID will be required when configuring adapters. The name can be set arbitrary, but if it consists of multiple words, it must be written beryllium style (for example, MessageType). 
* also possible to add message type description 
* Save click "Save and close" 

![](/images/pages/products/flexberry-servicebus/components/message-type-edit.png) 

### Registration of the client in service bus 

* Open the administration application 
* In the tree on the left in "Routing" choose the menu item "Clients" 
* Click "Add" 
* On the form to indicate: 
* customer name - you can specify a 
* ID will be required when configuring adapters. The name can be set arbitrary, but if it consists of multiple words, it must be written beryllium style (for example, MessageSender). 
Customers for example will require three, respectively, their ID should allow to distinguish the clients from each other and used to configure the adapter. 
* address is required for clients receiving the message and use the callback. It must be taken from config matches the generated adapter receiving the messages. 
* the remaining fields do not need to create the described examples 
* Click "Save and close" on the form. 

![](/images/pages/products/flexberry-servicebus/adapters/add-client.png) 

#### Permission to send messages 

For the client sending the message, you need to add the permission to send messages: 
* expand the menu item "Permission to send" message 
* click "Add" 
* you choose your previously created message type 
* click "Save and close" on the form. 

![](/images/pages/products/flexberry-servicebus/components/clients-sender-edit.png) 

#### the Formation of a subscription to a message 

For the customer receiving the message, you need to create a subscription for the message.

* In the tree on the left in "Routing" choose the menu item "Subscribe to posts" 
* On the opened form: 
* choose the client that receives the message created earlier 
* the message type that you created earlier 
* to set the date of termination of the subscription 
* "Pass through" is a service used to send messages. For example, the correct WCF. 
* if the client uses `callback` to set the flag "Callback" 
* Save click "Save and close" 

![](/images/pages/products/flexberry-servicebus/components/clients-receiver-edit.png) 

## Example of creating a client that sends messages 

#### create a console application 

To create a console application, you can use MS Visual Studio](https://www.visualstudio.com). 

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app.png) 

In the opened window, enter the application name and its location. 

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app2.png) 

#### Add reference to the service bus to the application 

To add a reference means `MS Visual Studio` you want to have access to a running instance of the bus (see "using the administrative application"). 

In `MS Visual Studio` in `Solution Explorer` right mouse button click on the project created by the adapter sending the messages and from the context menu select `Add Service Reference...` 

![](/images/pages/products/flexberry-servicebus/adapters/add-reference.png) 

* In the window that appears, specify the address of the service bus (open earlier) and service name (Namespace - the namespace that will be used later in the code). 
* Click "OK" 

#### coding adapter 

Once the application is created, the file will be opened with the possibility of editing the code: 

![](/images/pages/products/flexberry-servicebus/adapters/method-main.png) 

Code file `Program.cs` need to be replaced [sleduyushim](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp1/ConsoleApp1/Program.cs): 

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
                        // Enter the message. 
                        var message = new MessageForESB
                        {
                            ClientID = ConfigurationManager.AppSettings["ServiceID4SB"],
                            MessageTypeID = ConfigurationManager.AppSettings["MessageTypeID"],
                            Body = "Hello from " + adaptersCommand + "!"
                        };

                        // Send the message via the bus. 
                        ServiceBus.SendMessageToESB(message);

                        ServiceBus.Close();
                    }
                }
            }
        }
    }
}
``` 

{% include note.html content="_ConsoleApp1_ in _using ConsoleApp1.ServiceBus;_ is the name of the application _ServiceBus_ is the namespace specified when connecting to the service bus (in the code should be used zadannoi in the previous etape namespace)." %} 

#### setup configuration file 

* Open in `MS Visual Studio` in the project created by the adapter sending the messages file `App.config` 
* Add [next cod](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp1/ConsoleApp1/App.config): 

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

Key `ServiceID4SB` (client) and `MessageTypeID` (message) to specify the administrative key of the application.

The property "address" in the section `client` must contain the address of the service bus (see "using the administrative application"). 

## Example of creating a client that receives messages 

#### Create a console application 

Console application for a client that accepts messages generated by the same algorithm as for the adapter that is sending messages. 

#### to Add a reference to the service bus to the application 

* Right-click on the project in `Solution Explorer` 
* In the context menu select `Add Service Reference...` 
* In the window that appears, specify the address of the service bus namespace (Namespace). 
* Click "OK" 

#### coding adapter 

Code file `Program.cs` need to be replaced [sleduyushim](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp3/ConsoleApp3/Program.cs): 

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

                    //Get all the message was addressed to (when you start). 
                    MessageFromESB message = ServiceBus.GetMessageFromESB(serviceID, messageTypeID);
                    Console.WriteLine(message.Body);

                    // Get new messages/log out from the app. 
                    Console.WriteLine("Enter \"get\" for get a message (for exit type \"exit\"):");
                    adaptersCommand = Console.ReadLine();

                    while (message != null)
                    {
                        // Get new list of messages via the command "get". 
                        message = ServiceBus.GetMessageFromESB(serviceID, messageTypeID);
                    }
                }
            }
        }
    }
}
``` 

{% include note.html content="`ConsoleApp3` in `using ConsoleApp3.ServiceBus;` is the name of the application _ServiceBus_ is the namespace specified when connecting to the service bus (in the code should be used zadannoi in the previous etape namespace)." %} 

#### to Supplement the configuration file 

* Open in `MS Visual Studio` in the project created by the adapter sending the messages file `App.config` 
* Add [next cod](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp3/ConsoleApp3/App.config): 

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

Key `ServiceID4SB` (client) and `MessageTypeID` (message) to specify the administrative key of the application. 

The property "address" in the section `client` must contain the address of the service bus (see "using the administrative application"). 

## Example of creating a client that receives messages using ICallbackSubscriber 

Adapter that uses the interface `ICallbackSubscriber`, the presence of checks sent to the address of the message and transmits the message with his presence in the receiving application. The initiator of the transmission of the message is the service bus. 

#### Create a console application 

Console application for a client that accepts messages generated by the same algorithm as for the adapter that is sending messages. 

#### to Add a reference to the service bus to the application 

* Right-click on the project in `Solution Explorer` 
* In the context menu select `Add Service Reference...` 
* In the window that appears, specify the address of the service bus namespace (Namespace). 
* Click "OK" 

#### coding adapter 

Code file `Program.cs` need to be replaced [sleduyushim](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp2/ConsoleApp2/Program.cs): 

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

{% include note.html content="`ConsoleApp2` in `using ConsoleApp2.ServiceBus;` is the name of the application _ServiceBus_ is the namespace specified when connecting to the service bus (in the code should be used zadannoi in the previous etape namespace)." %} 

#### to Supplement the configuration file 

* Open in `MS Visual Studio` in the project created by the adapter sending the messages file `App.config` 
* Add [next cod](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/WCFAdapter/ConsoleApp2/ConsoleApp2/App.config): 

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

Key `ServiceID4SB` (client) and `MessageTypeID` (message) to specify the administrative key of the application. `ScanPeriod` – the update value of the subscription in milliseconds. `ExternalKey` – the external ID for the client. 

The property "address" in the section `client` must contain the address of the service bus (see "using the administrative application"). 

The property of "baseAddress" in the section `host` must contain the address on which the adapter will listen for incoming messages. This address is specified in the client configuration that receives messages, the application Administrator for tires (see "Working with administrative application") 

## the results of the interaction of the adapters sending / receiving messages 

To send-receive messages, you need to run the application. 

{% include note.html content="To run applications `MS Visual Studio` must be running as Administrator" %} 

The output is a text message sent to the message adapter receives the message. 

![](/images/pages/products/flexberry-servicebus/adapters/result.png) 

## Possible problems 

### Not come to the message in the receiving application with callback 

In a console application to receive messages does not come sent the message and in the administrative application displayed error sending-receiving. 

Reshenie 

Replace the address for the adapter and a client-recipient of an administrative application, setting `http://localhost:8080/Listener` is _localhost_ IP of the computer. To know the computer's IP by typing in console (Win + r, then cmd) `ipconfig`. 

Example: 

```csharp
<baseAddresses>
    <add baseAddress="http://111.11.111.11:8080/Listener"/>
</baseAddresses>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}