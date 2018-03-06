---
title: Пример REST-адаптера шины
sidebar: flexberry-servicebus_sidebar
keywords: Шина, Адаптер
toc: true
permalink: ru/fsb_rest-adapters-sample.html
lang: ru
summary: Пошаговое руководство по созданию примера REST-адаптера для приложения, подключающегося к корпоративной шине
---

Данная статья на примерах описывает создание [адептеров](fsb_adapters.html) (или клиентов) для [Flexberry Service Bus](fsb_landing_page.html) (сервиса шины), позволяющих как отправлять сообщения, так и принимать их. Итогом реализации таких адаптеров является успешно принятое соответствующим клиентом сообщение.

Для создания адаптеров по примеру потребуются следующие инструменты:

* [MS Visual Studio](https://www.visualstudio.com) - для создания собственно приложений адаптеров отправки/приема сообщений
* [Docker](fsb_installation.html) - программное обеспечение для автоматизации развёртывания и управления приложениями в среде виртуализации на уровне операционной системы. Необходим, если нет уже развернутых и готовых к использованию административного приложения шины и ее сервиса.
* [PowerShell](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6) - необходим для работы с Docker, если требуется [локальная настройка](fsb_installation.html) и запуск шины и ее сервиса

В процессе реализации примера будет создано два консольных приложения:

* Приложение, с помощью которого будут __отправлены сообщения__. В данном случае это будет консольное приложение, в командную строку которого вводится сообщение, в последствии передаваемое шиной принимающему приложению.
* Приложение, __вычитывающие сообщения__, результатом работы которого будет отображение сообщениний отправленых пользователю.

Код описываемых приложений выложен в примерах для `Flexberry Service Bus` на [GitHub](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples). Принципы работы с GitHub описаны на сайте [https://help.github.com](https://help.github.com/articles/cloning-a-repository/) или на сайте [https://git-scm.com](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)

## Описание HTTP-запросов для взаимодействия с Flexberry Service Bus

### Отправка сообщения в шину

Отправить сообщение можно, задав значения его полей в JSON-структуре и отправив ее в теле POST-запроса к
шине.

* Метод: POST
* Путь: /Message
* Тело запроса:

```js
 // Тело сообщения.
 "Body": "string",
 // Идентификатор типа сообщения.
 "MessageTypeID": "string",
 // Идентификатор клиента-отправителя.
 "ClientID": "string",
 // Теги сообщения.
 "Tags": {},
 // Вложение.
 "Attachment": "string",
 // Приоритет (чем больше число, тем выше приоритет).
 "Priority": 0
 ```

* Пример:

```js
POST http://localhost:7085/RestService/Message
{
 "Body": "Message body",
 "MessageTypeID": "MsgType1",
 "ClientID": "Client1",
 "Tags": { "tag1": "tag1value", "tag2": "tag2value" },
 "Attachment": "MessageAttachment",
 "Priority": 0
}
 ```

### Получение списка сообщений, имеющихся для указанного клиента

В запросе указывается идентификатор клиента, возвращается массив объектов, каждый из которых хранит
информацию об одном сообщении: идентификатор сообщения, идентификатор типа сообщения, приоритет,
время формирования сообщения.

* Путь: /Messages
* Параметры:

```js
  clientId (обязательный) – идентификатор клиента
 ```

* Тело ответа:

```js
{
 // Уникальный идентификатор сообщения.
 "Id": "string",
 // Идентификатор типа сообщения.
 "MessageTypeID": "string",
 // Приоритет сообщения.
 "Priority": 0,
 // Время формирования сообщения.
 "MessageFormingTime": "2015-04-07T04:35:30.198Z"
}
 ```
* Пример:

```js
http://localhost:7085/RestService/Messages?clientId=client1
```

### Получение сообщения по идентификатору клиента, индексу и типу сообщения

Запрос обрабатывается следующим образом: список сообщений заданного типа для заданного клиента
сортируется, в первую очередь, по приоритету (по убыванию) и затем по дате формирования (по
возрастанию). После этого из полученного списка выбирается сообщение с заданным индексом.
Ответ сервера содержит JSON-структуру с полями сообщения.

* Метод: GET
* Путь: /Message
* Параметры:

```js
clientId (обязательный) – идентификатор клиента
messageTypeId (обязательный) – идентификатор типа сообщения
index (обязательный) – индекс сообщения в упорядоченном списке
```

* Тело ответа:

```js
{
 // Идентификатор сообщения.
 "Id": "string",
 // Тело сообщения.
 "Body": "string",
 // Время формирования сообщения.
 "MessageFormingTime": "2015-04-07T04:35:30.212Z",

 // Идентификатор типа сообщения.
 "MessageTypeID": "string",
 // Имя отправителя сообщения.
 "SenderName": "string",
 // Имя группы сообщения.
 "GroupID": "string",
 // Теги сообщения.
 "Tags": {},
 // Вложение.
 "Attachment": "string"
}
 ```

* Пример:
Для получения сообщения, наиболее приоритетного к отправке, следует запросить сообщение с индексом 0:

```js
http://localhost:7085/RestService/Message?clientId=client1&messageTypeId=msgType1&index=0
 ```

### Получение сообщения по уникальному идентификатору

При запросе списка сообщений с сервера или запросе одного сообщения информация о сообщении включает
идентификатор сообщения. По этому идентификатору затем можно повторно получать сообщение, если это
необходимо.

* Метод: GET
* Путь: /Message/{id}
* Параметры: id (обязательный, часть URL) – идентификатор сообщения
* Тело ответа:

```js
{
 "Id": "string",
 "Body": "string",
 "MessageFormingTime": "2015-04-07T04:35:30.212Z",
 "MessageTypeID": "string",
 "SenderName": "string",
 "GroupID": "string",
 "Tags": {},
 "Attachment": "string"
}
 ```

* Пример:

```js
http://localhost:7085/RestService/Message/{330732E9-7DB6-42C6-883D-E1596F4FBF78}
```

### Удаление сообщения из шины

Удалить сообщение также можно по идентификатору.

* Метод: DELETE
* Путь: /Message/{id}
* Параметры:

```js
id (обязательный, часть URL) – идентификатор сообщения
```

* Пример:

```js
DELETE http://localhost:7085/RestService/Message/{330732E9-7DB6-42C6-883D-E1596F4FBF78}
```

### Запрос отправки сообщения клиенту по callback

Для приема сообщений по инициативе шины (callback) посредством HTTP необходимо в клиентском адаптере
реализовать обработку HTTP-запроса следующего вида:

* Метод: POST
* Путь: /Message
* Тело запроса:

```js
{
 // Уникальный идентификатор сообщения.
 "Id": "string",
 // Тело сообщения.
 "Body": "string",
 // Время формирования сообщения.
 "MessageFormingTime": "string",
 // Идентификатор типа сообщения.
 "MessageTypeID": "string",
 // Имя отправителя сообщения.
 "SenderName": "string",
 // Имя группы сообщения.
 "GroupID": "string",
 // Теги сообщения.
 "Tags": { "tag1": "tag1value" },
 // Вложение.
 "Attachment": []
}
```

## Работа с административным приложением

Для работы с приложениями отправки-приема сообщений требуется иметь доступ к запущенной инстанции шины (административному приложению). Для этого действия нужен адрес административного приложения и адрес запущенного сервиса шины. Получить их можно следующими способами:
* административное приложение - локально по адресу [http://localhost:1818/](http://localhost:1818/). Данный вариант возможен, если используется [Docker](fsb_installation.html).
* сервис - локально по адресу [http://localhost:7085/RestService](http://localhost:7085/RestService). Данный вариант возможен, если используется `Docker`.
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
    * адрес требуется для клиентов, принимающих сообщения. Его необходимо будет взять из конфига соответствующего созданного адаптера, принимающего сообщения.
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
    * "Передавать через" - это сервис, используемый для передачи сообщений. Для примера нужен REST.
* Сохранить по кнопке "Сохранить и закрыть"

![](/images/pages/products/flexberry-servicebus/components/clients-receiver-edit-rest.png)

## Пример создания клиента, отправляющего сообщения

#### Создание консольного приложения

Для создания консольного приложения можно использовать [MS Visual Studio](https://www.visualstudio.com).

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app.png)

В открывшемся окне ввести название приложения и место его расположения.

![](/images/pages/products/flexberry-servicebus/adapters/creating-console-app2.png)

#### Написание кода адаптера

После того как приложение создано, будет открыт файл с возможностью редактирования кода:

![](/images/pages/products/flexberry-servicebus/adapters/method-main.png)

Код файла `Program.cs` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/RESTAdapter/MsgSender/MsgSender/Program.cs):

```csharp
using System;
using System.Net;
using System.Text;
using System.Configuration;
using Newtonsoft.Json;

namespace MsgSender
{
    class Program
    {
        static void Main(string[] args)
        {

            string msgBody = "";

            while (msgBody != "exit")
            {
                Console.Write("Enter your msg (for exit type \"exit\"): ");

                msgBody = Console.ReadLine();

                // Создадим сообщение.
                string jsonData = JsonConvert.SerializeObject(new
                {
                    Body = msgBody,
                    ClientID = ConfigurationManager.AppSettings["SenderID"],
                    MessageTypeID = ConfigurationManager.AppSettings["MessageTypeID"]
                });

                string url = ConfigurationManager.AppSettings["Address"] + "/Message";

                using (var webClient = new WebClient())
                {
                    webClient.Encoding = Encoding.UTF8;
                    webClient.Headers[HttpRequestHeader.ContentType] = "application/json";
                    webClient.UploadString(url, "POST", jsonData);
                    Console.WriteLine("Message sent");    
                }
            }
        }
    }
}

```

#### Настройка файла конфигурации

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/RESTAdapter/MsgSender/MsgSender/App.config):

```xml
<appSettings>
    <add key="SenderID" value=""/>
    <add key="MessageTypeID" value=""/>
    <add key="Address" value="http://localhost:7085/RestService"/>
</appSettings>
```

Для ключей `SenderID` (клиент отправляющий сообщение) и `MessageTypeID` (тип сообщения) указать ключи из административного приложения.

`Address` должен содержать адрес развёрнутой шины.

## Пример создания клиента, принимающего сообщения

#### Создать консольное приложение

Консольное приложение для клиента, принимающего сообщения, создается по тому же алгоритму, что и для адаптера, отправляющего сообщения.

#### Написание кода адаптера

Код файла `Program.cs` необходимо заменить [следующим](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/RESTAdapter/MsgRecipient/MsgRecipient/Program.cs):

```csharp
using System;
using System.Net;
using System.Text;
using System.Threading;
using System.Configuration;

namespace MsgRecipient
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                string url = ConfigurationManager.AppSettings["Address"] + "/Messages?clientId=" + ConfigurationManager.AppSettings["RecipientID"];

                using (var webClient = new WebClient())
                {
                    webClient.Encoding = Encoding.UTF8;
                    var response = webClient.DownloadString(url);
                    Console.WriteLine(response);
                }

                Thread.Sleep(5000);
            }
        }
    }
}
```

#### Дополнить конфигурационный файл

* Открыть в `MS Visual Studio` в проекте созданного адаптера, отправляющего сообщения, файл `App.config`
* Добавить [следующий код](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples/blob/master/RESTAdapter/MsgRecipient/MsgRecipient/App.config):

```xml
<appSettings>
  <add key="RecipientID" value=""/>
  <add key="Address" value="http://localhost:7085/RestService"/>
</appSettings>
```

Для ключа `RecipientID` (принимающий клиент) указать ключ из административного приложения.

`Address` должен содержать адрес развёрнутой шины.

## Результаты

Для отправки-получения сообщений нужно запустить приложения.

{% include note.html content="Для запуска приложений `MS Visual Studio` должна быть запущена от имени Администратора" %}

Результатом выполнения будет ответ включающий все отправленыые клиенту сообщения.
