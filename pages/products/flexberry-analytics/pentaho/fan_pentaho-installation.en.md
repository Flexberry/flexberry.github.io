--- 
title: Install and launch» «Flexberry Analytics at Docker 
keywords: Pentaho, installation, Docker, manual 
sidebar: flexberry-analytics_sidebar 
toc: false 
permalink: en/fan_pentaho-installation.html 
lang: en 
autotranslated: true 
hash: 47aabd70f95dd66febacf9b0b113878cc98572b5bba635a1a92e0b4049ee6c9d 
summary: 
--- 

Service Flexberry» «Analytics provides server `Pentaho`, oformlennye [Docker image](https://hub.docker.com/r/flexberry/pentaho/). 

To run the image `Pentaho` required to be installed ON a [Docker](https://docs.docker.com). For Windows users there is a corresponding version of the [Docker for Windows](https://docs.docker.com/docker-for-windows/install/). 

### PowerShell script installing and running Pentaho 

```powershell
$documentsPath = [Environment]::GetFolderPath('MyDocuments')
$folderName = 'Pentaho'
cd $documentsPath
New-Item -ErrorAction Ignore -Path $folderName -ItemType 'directory'
cd $folderName
$client = new-object System.Net.WebClient
$downloadPath = 'https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.Analytics/master/pentaho' 
$client.DownloadFile("$downloadPath/.env", "$pwd\.env")
$client.DownloadFile("$downloadPath/pull.sh", "$pwd\pull.cmd")
$client.DownloadFile("$downloadPath/docker-compose.yml", "$pwd\docker-compose.yml")
$client.DownloadFile("$downloadPath/composeStart.sh", "$pwd\composeStart.cmd")
$client.DownloadFile("$downloadPath/composeStop.sh", "$pwd\composeStop.cmd")
$client.DownloadFile("$downloadPath/swarmStart.sh", "$pwd\swarmStart.cmd")
$client.DownloadFile("$downloadPath/swarmStop.sh", "$pwd\swarmStop.cmd")
``` 

The script can be download from [GitHub](https://raw.githubusercontent.com/Flexberry/NewPlatform.Flexberry.Analytics/master/pentaho/getPentaho.ps1). 
To run the script you need to open [PowerShell console](https://docs.microsoft.com/ru-ru/powershell/scripting/setup/starting-windows-powershell?view=powershell-6). Then you can just copy the script text to the console and execute. 

### Configuration TCP server ports 

Server configuration is described in the file `.env`. 

The server uses the following TCP ports: 
```
SERVER_HTTP_PORT=8080
SERVER_AJP_PORT=8009
SERVER_PROM_PORT=1234
``` 

If these ports are already occupied, you can override variables specified in the file `.env`, putting unused ports. 

### Obtaining the image 

To work you must first download the ISO image repository script `pull.cmd`. 

If the image is in the repository has been updated to use it, you must re-run the command `pull.cmd`. 


### Start 

To start the service you must run the script `composeStart.cmd`. 

Service initialization occurs within 30-60 seconds. 

### Verification of a successful run Pentaho 

In PowerShell console, you need to enter 
```powershell
docker ps
``` 

The result will be given a list of running `docker образов`. 
Among them should be `flexberry/pentaho-saiku:8.0 `. 

The administration application with the default configuration is available at <`http://<Redresseur>:<SERVER_HTTP_PORT>`>. 

If the access server is the computer running the service and the value of the variable SERVER_HTTP_PORT in the file `.env` not changed, then the URL will look like: 
`http://localhost:8080/`. 

Username and password: `admin`, `password` (during the transition to commercial operation is necessary to change). 

At initial startup of the image created named Tom: 
- pentaho_hidden - hidden server files pentaho; 
- pentaho_hsqldb - internal database type hsql to store the current настроек; 
- pentaho_repository - filesystem пользователей; 
- pentaho_logs logs сервера; 
- pentaho_tmp temporary files on the server.

When re-launches the image uses the data from the specified named volumes. 
Thus the user's files and the current settings are saved when restarting the container. 

For a list of named volumes, use the command 
```
docker volume ls
``` 

If You plan to use these settings on another server, you must migrate the specified named Tom. 
Odnoimennyi folders volumes are in the directory `/var/lib/docker/volumes`.``` 

### Stop service 

To stop service you must run the script `composeStop.cmd`. 

Additionally ### 

- [git repository build of the image Pentaho](https://github.com/Flexberry/dockerfiles/blob/master/pentaho/README_ru.md); 
- [git repository startup scripts image](https://github.com/Flexberry/NewPlatform.Flexberry.Analytics/tree/master/pentaho); 
- [docker repository image](https://hub.docker.com/r/flexberry/pentaho/). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}