---
title: Deployment of solutions in Docker containers
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: en/gbt_deployment_docker.html
lang: en
autotranslated: true
hash: a4c11f9dac18ef9aa110b8bf1e7bb18a7509ea194632771771baea783bf8ffcb
---

## Description

docker is a lightweight containerization system applications with open source.
Currently there are several hundreds of Linux distributions. Each type of Linux distribution has its own peculiarities in the installation and configuration of applications:

* Differences in the procedure of installing packages (apt-get, yum, dnf, ...).
* Differences batch database (desired package can be omitted).
* Package name, version and installation mechanism may vary.
* Packages are built with various options and add-ons.
* The configuration files are located in different places.
* ...

All this creates serious problems when transferring a running application from one Linux distribution to another.

Docker solves this problem by creating a file system image that includes in addition to the application itself all the required libraries and configuration files. Thus setting the image of docker-apps built for one type of distribution (e.g. Ubuntu), You can based on it to start the container running the application on a different distribution (e.g. ALTLinux).

Unlike other virtualization systems (e.g. Virtuozzo/OpenVZ) docker applications of the same type of distribution is through the use of cascade-combined (layered) mount use the same libraries, configuration files, underlying distribution. This makes optimum use of the file system to reduce the amount of resources consumed by kontejnerov and reduce the time of its launch.
This same mechanism allows you to design your own images by extending (inheritance) of existing images, thus creating a tree of images of the distributions.

The undoubted advantage of docker is the presence of hundreds of official and unofficial up to half a million public images on [hub-portal](https://hub.docker.com/). Finding the right way app You can install it on your computer and run the application containers does not care about the incompatibility of Linux distributions.

In addition, You can expanding the received image to create a custom image app adding your configuration files and extra packages.

For example, if You need to run Your MPI program, You can download image MPI compiler from CentOS:

```sh
# docker pull nersc/centos-mpi
```

To create your own podobrat, using the instructions given on the page [image](https://hub.docker.com/r/nersc/centos-mpi/) and run Your MPI application with different parameters on different Linux distributions.

The technology for creating docker images based on existing You can examine on the page [Build your own images](https://docs.docker.com/engine/tutorials/dockerimages/).

The following images based on the base docker image distribution [ALTLinux/BaseALT P8](https://hub.docker.com/r/fotengauer/altlinux-p8/). In addition, created similar images on the basis of the distribution [ALTLinux C7](https://hub.docker.com/r/fotengauer/altlinux-p7/):

* [kafnevod/altlinux.p7-apache2](https://hub.docker.com/r/kafnevod/altlinux.p7-apache2/) - image of the Apache2 server
* [flexberry/altlinux.p7-apache2-mono4.6.2.7](https://hub.docker.com/r/flexberry/altlinux.p7-apache2-mono4.6.2.7/) image Apache2/Mono4 servers.

These images use the certified distribution library ALTLinux C7 and, if necessary, can be used instead of the described in this document images [kafnevod/altlinux.p8-apache2](https://hub.docker.com/r/kafnevod/altlinux.p8-apache2/), [flexberry/altlinux.p8-apache2-mono4.6.2.7](https://hub.docker.com/r/flexberry/altlinux.p8-apache2-mono4.6.2.7/) collected on the distribution ALTLinux/BaseALT P8.

When you run container You can pass it various parameters to bind TCP ports application container to various ports on the HOST system, mount the directory HOST system in the subdirectories of the container.

All these mechanisms allow you to run within a single OS:

* multiple containers with a single application of one версии;
* several containers of versions of different versions of the same приложения;
* applications conflicting TCP ports or file системе;
* swarm (swarm) container that supports some сервис;
* etc.

### The image WEB server Apache2: kafnevod/altlinux.p8-apache2

#### Install the image and run the container

Let's say You need to run Ember-app under WEB-server Apache2.
You can simply download the image server for distribution ALTLinux P8:

```sh
# docker pull kafnevod/altlinux.p8-apache2
```

The launch container is performed with the command:

```sh
# docker run -d -p 80:80 --name=apache2 kafnevod/altlinux.p8-apache2
```
The container starts in daemon mode (-d) with name (--name) apache2 and bind a port (-p) 80 of the container to port 80 of the HOST system.

To verify that the container, run the command:

```sh
# docker ps -f name=apache2
```

The command will show a line like:

```
CONTAINER_ID IMAGE                          COMMAND CREATED     STATUS              PORTS                NAMES
688acc21b881 kafnevod/altlinux.p8-apache2   httpd2  5 seconds   Up 4 seconds        0.0.0.0:80->80/tcp   apache2
```

The IMAGE column contains the name of the image container.
Column NAMES - the container name.
Column CONTAINER ID - ID (UID) of the container.
Column list of the PORTS attached ports container ports on the HOST system.
The STATUS column - status of the container (start up, restart, ...).
Column CREATED - time since the start of the container.
The COMMAND column is the name and parameters of the root process of the container.

Using the docker exec command You can start the process in the working container, for example, the pstree command (process tree):

```sh
# docker exec apache2 pstree
```
Command shows the current process tree container:

```

httpd2---2*[httpd2---26*[{httpd2}]]

```

You can also run bash interpretator

```sh
# docker exec -it apache2 bash
```

and interactively work within the container.
For example, to dynamically view the log files apache2-server with the command:

```sh
# tail -f /var/log/httpd2/*log
```

#### Check the operation of the container

To test the operation of the container type in the browser URL: http://localhost/.
The browser will display the text: **It works!**

> Here and below we consider the case when the container is launched on the user's computer. If You launch a container on a remote server, replace the server name in the URL from localhost to the IP address of the server or the domain name under which the server works. For testing purposes You can specify one or more domains of the server in the /etc/hosts file.

> If Your browser runs through a proxy server, describe the test addresses and server domains in the field _"do not use proxy server"_ window proxy server settings of the browser.

If You are in a container started the above command logs view **tail**, it will print lines like:

```
==> /var/log/httpd2/access_log <==
172.18.0.1 - - [26/Dec/2016:10:25:17 +0000] "GET / HTTP/1.1" 200 45
```

#### Running your own virtual website within container

The description file of the virtual sites on apache2 server container is in the file /conf/vhosts.conf image.
To start their own virtual site (for example demo) You must:
* Place the tree of the website in a separate directory (e.g. /var/www/vhosts/demo/) HOST system.
Let this be a simple test page /var/www/vhosts/demo/index.html:

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
  <head>
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
  <title>Тестовая demo-страница для проверки работоспособности виртуального хоста в контейнере kafnevod/altlinux.p8-apache2</title>
  </head>
  <body>
    <b><i>Виртуальный хост demo.local контейнера kafnevod/altlinux.p8-apache2 работает!</i></b>
  </body>
</html>
```

* Describe the configuration of that virtual host in the configuration file vhosts.conf a separate directory. For example, /etc/docker/apache2/conf/vhosts.conf:

```text
NameVirtualHost *:80
<VirtualHost *:80>
        DocumentRoot "/var/www/vhosts/demo/"
        ServerName demo.local
        ErrorLog "/var/log/httpd2/demo_log"
        CustomLog "/var/log/httpd2/demo_log" common
</VirtualHost>
```

* To remove a running container, if You have it running:

```sh
# docker rm -f apache2
```

* Run a new container with the command:

```sh
# docker run -d \
  -p 80:80 \
  --name=apache2Virt \
  -v /etc/docker/apache2/conf/:/conf/ \
  -v /var/www/vhosts/:/var/www/vhosts/ \
  kafnevod/altlinux.p8-apache2
```

Options-v mount configuration directory /conf/ directory and virtual sites /var/www/vhosts/ container to the /etc/docker/apache2/conf/ /var/www/vhosts/ HOST-system.

* Describe virtual domain in /etc/hosts file:

```
127.0.0.1 demo.local ... localhost
```

>If You launch a container on a remote server, instead of the 127.0.0.1 address in /etc/hosts file of the host where You launch the browser you have to add the line:

```
ip-адрес_сервера demo.local
```

>If You run a virtual website for external access, instead of editing /etc/hosts file Zaregistriruytes domain (e.g. demo.ics.perm.ru) with your ISP domain and specify it in the description of the configuration file /etc/docker/apache2/conf/vhosts.conf.

#### Check the operation of the virtual site in the container

To check the operation of the virtual website type in your browser's URL: http://demo.local/.
The browser will display the text: **Wirtualny host demo.local container kafnevod/altlinux.p8-apache2 works!_**

### Server image Mono/.NET applications flexberry/altlinux.p8-apache2-mono4.6.2.7

#### Install the image and run the container

To run mono applications download flexberry image/altlinux.p8-apache2-mono4.6.2.7:

```sh
# docker pull flexberry/altlinux.p8-apache2-mono4.6.2.7
```

Please note - as the way flexberry/altlinux.p8-apache2-mono4.6.2.7 is an extension of the image kafnevod/altlinux.p8-apache2, then the layers of the file system that are already present in the parent image to the child image is not downloaded.

```
6ed0c8c1348d: Already exists 
3083620832f3: Already exists 
03daa402b3d6: Already exists 
b867bce38065: Pulling fs layer                                                                                                                          
```

When you start the container a child of the image he uses layers of the file system of the parent image.
The test image contains the virtual site accessible on TCP port 880.
To check the test website, run a container loaded image:

```sh
# docker run -d -p 880:880 --name=apache2Mono flexberry/altlinux.p8-apache2-mono4.6.2.7
```

#### Check the operation of the container

Check the status of the container:

```sh
docker ps -a -f name=apache2Mono
CONTAINER_ID IMAGE                                    COMMAND CREATED     STATUS      PORTS                          NAMES
38c7e2a7a8aa flexberry/altlinux.p8-apache2-mono4.6.2.7 httpd2 2minutes ago Up 2minutes 80/tcp, 0.0.0.0:880->880/tcp   apache2Mono
```

Type in the browser the URL of the virtual test site with Mono-app http://localhost:880/. In the browser to display the result of the test mono-app - key **Click me!**.

#### Start your own virtual Mono/.NET site within the container

Start your own virtual Mono/.NET site is equivalent to running [virtual site Apache2-server](#Launch-own-virtual-website-in-part of container-apache):

* Place trees site in a separate directory (for example /var/www/vhosts/myMonoApp/) HOST system.
* Copy the virtual host configuration file of Mono application from a running container:

```sh
docker cp apache2Mono:/conf/vhosts.conf /etc/docker/apache2/conf/vhosts.conf
```

* Add the configuration of Your virtual host in the copied configuration file: /etc/docker/apache2/conf/vhosts.conf using the template:

```text
Listen 881
NameVirtualHost *:881

<VirtualHost *:881>
  ServerName myMonoApp.local
  MonoServerPath test.local "/usr/bin/mod-mono-server4"
  MonoDebug myMonoApp.local true
  MonoSetEnv myMonoApp.local MONO_IOMAP=all
  MonoApplications myMonoApp.local "/:/var/www/vhosts/myMonoApp"
  AddDefaultCharset utf-8
  <Location "/">
    Allow from all
    Order allow,deny
    MonoSetServerAlias myMonoApp
    SetHandler mono
    #SetOutputFilter DEFLATE
  </Location>
  ErrorLog /var/log/httpd2/myMonoApp_error_log
  LogLevel debug
  CustomLog /var/log/httpd2/myMonoApp_access_log common
</VirtualHost>
```

* Replace, if necessary, the 881 port to the port number you are going to use Your website, putting in the port in the-p flag when running the container. Domain myMonoApp.local to Your application domain name myMonoApp the name of Your mono application.

* Restart the container :

```sh
# docker rm -f apache2Mono
# docker run -d \
  -p 880:880 \
  -p 881:881 \
  --name=apache2MonoVirt \
  -v /etc/docker/apache2/conf/:/conf/ \
  -v /var/www/vhosts/:/var/www/vhosts/ \
  flexberry/altlinux.p8-apache2-mono4.6.2.7
```

#### Check the operation of the virtual site in the container Mono/.NET applications

Refer the browser to the root page of the virtual website URL: http://localhost:881/.

### Comments

After the completion of the container it remains in the list and can be found in the list of containers the ps command with the-a flag:

```sh
# docker ps -a
```

If the container is named (at startup, use the flag --name=iracontainer), then re-run the container with the same name will cause an error.

To remove a completed container-use the rm command:

```sh
# docker rm ID_контейнера_или_его_имя
```

Intensive process of starting the containers (usually during testing) the list of stopped containers can be quite large and cleaning this list may create some temporary problems.
If You do not plan to restart the container after its completion, or plan to repeatedly run the named container, use the --rm flag after an argument run start command. In this case, the container after automatically deleted from the list of completed containers. For example

```sh
docker run -d -p 80:80 --rm --name=apache2 kafnevod/altlinux.p8-apache2
```


If You need to automatically restart a container after it (emergency) stop and automatically start the container after restarting the system, use the --restart flag=unless-stopped argument after the run command that starts the container. For example:

```sh
docker run -d -p 80:80 --restart=unless-stopped --name=apache2 kafnevod/altlinux.p8-apache2
```

### docker for Windows

Run Docker for Windews Windews 8 and Server 2012 in a virtual machine Virtualbox.

In Windeos 10 Professional, and Windows Server 2016 appeared natina support Docker.

### Run Linux containers in Windows Server 2016 and Windows 10 Professional

When you run Linux containers in HyperV create a virtual machine of Linux, boot Linux Moby and runs all Linux containers.

### Run Windows containers on Windows Server 2016 and Windows 10 Professional

Supported [two startup mode of Windows-containers](https://dev-ops-notes.ru/docker/windows-контейнеры-и-docker/):
* the Windows Server container - the container runs within the HOST OS and uses the kernel and shared resources of the HOST system.
* Hyper-V container container runs in a separate virtual machine on Hyper-V.

## Links to materials for the study

### Lectures, courses, presentations, videos

* [DOCKER: INTRODUCTION TO VIRTUALIZATION TECHNOLOGIES](https://dev-ops-notes.ru/docker/docker-введение-в-технологии-виртуализаци/)
* [DOCKER: the CONTAINER SECURITY](https://dev-ops-notes.ru/docker/docker-проверка-контейнеров-на-безопаснос/)


#### Docker for Linux

* [Docker Video Tutorials](https://www.docker.com/products/resources/video-tutorials)
* [Docker YouTube Channel](https://www.youtube.com/user/dockerrun)
* [5 must-watch videos Docker](https://opensource.com/business/15/5/must-watch-docker-videos)
* [Video reports meet up with Docker](https://habrahabr.ru/company/badoo/blog/304702/)
* [Docker in the Bank. Video lectures by Alexander Tarasov from Alfa Bank](https://habrahabr.ru/company/jugru/blog/264669/)
* [ 350 useful resources, books and tools for working with Docker](https://habrahabr.ru/company/1cloud/blog/275015/)
* [Get started with Docker](https://docs.docker.com/engine/getstarted/)
* [The Docker commands](https://docs.docker.com/engine/reference/commandline/)
* [Docker run reference](https://docs.docker.com/engine/reference/run/)
* Series Play-With:
- [ Play with Docker](http://play-with-docker.com/);
- [Classroom Play with Docker](https://training.play-with-docker.com/);
- [Play with Kubernetes](https://labs.play-with-k8s.com/);
- [Play with Kubernetes Classroom](https://training.play-with-kubernetes.com/);

#### Docker for Windows
* [WINDOWS CONTAINERS AND DOCKER](https://dev-ops-notes.EN/docker/windows-containers-and-docker/)
* [WORKING WITH CONTAINERS AND DOCKER IN WINDOWS](https://dev-ops-notes.ru/docker/работа-с-контейнерами-в-windows-и-docker/)
* [Docker: Windows and Linux containers on a single machine](https://blog.amartynov.ru/docker-windows-и-linux-в-контейнерах-на-одной-машине/)

### Recommended books

* [The Docker Book. "James Turnbull" November 26, 2016](https://www.dockerbook.com/TheDockerBook_sample.pdf)

## Software

* [Engine user guide](https://docs.docker.com/engine/installation/)
* The [Install from binaries Docker](https://docs.docker.com/engine/installation/binaries/)

## Laboratory work and practical tasks


## Go
* [Course home page](gbt_landing-page.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}