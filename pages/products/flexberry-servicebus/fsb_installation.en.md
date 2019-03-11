--- 
title: Install and launch the tire in Docker 
sidebar: flexberry-servicebus_sidebar 
keywords: ESB, docker swarms 
toc: true 
permalink: en/fsb_installation.html 
lang: en 
autotranslated: true 
hash: 87502bafb3e0cfe6d813afcae94dec33bd7ac8088a2e7e52448ed14ecd3b446c 
summary: Start and stop the tyres to docker swarms. 
--- 

## Install and run the tyres to Docker 

### Conditions for running 

To start `Flexberry Service Bus` required to be installed ON a [Docker](https://docs.docker.com). For global users management Windows has the appropriate version of the [Docker for Windows](https://docs.docker.com/docker-for-windows/install/). 

### PowerShell install script and run the bus 

```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Flexberry Service Bus'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker' 
$client.DownloadFile("$downloadPath/flexberry-service-bus-swarm-configuration.yml", "$pwd\flexberry-service-bus-swarm-configuration.yml")
$client.DownloadFile("$downloadPath/flexberry-service-bus-rabbitmq.yml", "$pwd\flexberry-service-bus-rabbitmq.yml")
$client.DownloadFile("$downloadPath/start-flexberry-service-bus.cmd", "$pwd\start-flexberry-service-bus.cmd")
$client.DownloadFile("$downloadPath/stop flexberry-service-bus.cmd", "$pwd\stop-flexberry-service-bus.cmd")
``` 

The script can be download from [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.ServiceBus/develop/Docker/getServiceBus.ps1). 
To run the script you need to open [PowerShell console](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Then you can either write in the console the path to the script (for example D:\userName\NewPlatform.Flexberry.ServiceBus\Docker\getServiceBus.ps1) and run it or just copy the script text to the console and execute. 
After running the script will download all necessary files to run the tires in the command prompt, run `start-flexberry-service-bus.cmd` to start and `stop-flexberry-service-bus.cmd` to stop containers of the tire. 

{% include note.html content="From version `1.2.0 ` bus runs in a configuration with `RabbitMQ`, use the command `start-flexberry-service-bus.cmd no-rabbit` to run in the default configuration." %} 

### Verify successful startup bus 

* In the PowerShell console, enter "docker ps", will be given a list of running docker images (among them should be flexberry-service-bus-postgres-db flexberry-service-bus-editor and flexberry-service-bus). 
* The administration application with the default configuration is available at <http://localhost:1818>. Username and password: `admin`, `admin` (during the transition to commercial operation is necessary to change). 
* WCF-bus interface with default configuration: <http://localhost:7075/WcfService>. 
* REST-bus interface with default configuration: <http://localhost:7085/RestService>. 

### Further action bus 

* [Development of adapter for bus](fsb_adapters.html) 

Additionally ### 

* The downloading of containers: all containers will be downloaded when you first start. 
* Set up containers: 
[replace Web.config for the administrative application bus](fsb_editor.html), [replacement App.Config for the service bus](fsb_service.html). 
* Running containers: 
to start you want to run the command file [start-flexberry-service-bus.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/start-flexberry-service-bus.cmd).
* Stop containers: 
to stop you must run the command file [stop-flexberry-service-bus.cmd](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/blob/develop/Docker/stop-flexberry-service-bus.cmd). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}