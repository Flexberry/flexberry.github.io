--- 
title: Deployment of solutions in Linux 
keywords: Programming 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_deployment_linux.html 
folder: guides/base-tech/deployment/ 
lang: en 
autotranslated: true 
hash: a2e538f5fc7b177b0b118169f15a5bf6f409870cf7027477d49a7637999f7696 
--- 

## Brief description 

Currently, Russia uses the following Linux distributions: 

* Certified 
* [Alt Linux SPT 6.0](http://www.altlinux.ru/products/altlinux-spt-fstec/) 
* [Alt Linux SPT 7.0](https://www.basealt.ru/products/alt-spt/) 
* [Astra Linux Special Edition, release "Smolensk"](http://astralinux.com/smolensk.html) 
* [ROSA CHROME](https://www.rosalinux.ru/pocaxpom/) 
* Non-certified 
* [Alt Linux 7th platform](http://www.altlinux.ru/products/7th-platform/centaurus/) 
* [Alt Linux 8 platform](https://www.basealt.ru/products/alt-server/) 
* [ROSA Fresh](https://www.rosalinux.ru/rosa-fresh/) 
* [Astra Linux Common Edition](http://www.astra-linux.com/products/module-positions.html) 
* [Ubuntu](http://ubuntu.ru/) 
* [CentOS](https://www.centos.org/) 
* ... 

To reduce dependence on particular distribution and batch database when you expand solutions to the most widely used containers [Docker](https://www.docker.com/) to avoid large overhead costs when deploying server and client solutions on a wide class of distributions. 
As container images are used as [public containers](https://hub.docker.com/),and containers designed for use in specific development projects. Developed containers, as a rule, are based on the distributions ALTLinux certified 7th platform and the 8th platform for General use. Deployment of solutions in the docker container system is described in the [following section](gbt_deployment_docker.html). 

## Installation and configuration of Linux OS 

### Description 
To install and configure Linux, You need to choose a distro, download the image, burn it to physical media (flesh card or CD/DVD) and using interakcia to install it on a physical disk partition or in a virtual machine HyperV, VirtualBox, VMWare, ... the table provides a list of distributions, links to the download page of the image, instructions for recording an image on a medium, the distribution and the license text. 

Distribution | Download | Save | Install | License 
------------|---------|--------|-----------|--------- 
ALTLinux P7 | [Download](http://www.altlinux.ru/products/7th-platform/kdesktop/)| [REC](https://www.altlinux.org/Write) | [Setting](https://docs.altlinux.org/ru-RU/kdesktop/7.0.5/html-single/kdesktop/index.html) | [License](http://www.altlinux.ru/products/7th-platform/kdesktop/kdesktop-license) 
BaseALT P8 [Download](https://www.basealt.ru/products/alt-workstation/) | [REC](https://www.altlinux.org/Write) | [Setting](http://basealt.EN/static/Basealt_Desktop_Установка.pdf) | [License](https://www.basealt.ru/products/alt-workstation/license/) 
ROSA Fresh | [Download](http://mirror.yandex.ru/rosa/rosa2014.1/iso/ROSA.Fresh.R8/) | [REC](http://wiki.rosalab.ru/ru/index.php/Установка_ROSA_Desktop) | [Setting](http://smotrisoft.ru/ustanovka-rosa-desktop-fresh/) | [GPL License](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 
Astra Linux Common Edition [Download](http://astralinux.com/download-ce.html) | [REC](http://astralinux.com//images/doc/AstraLinuxCE_install.pdf) | [Setting](http://astralinux.com/doc.html) | [License](http://astralinux.com/litsenzionnoe-soglashenie.html) 
Ubuntu | [Download](http://ubuntu.ru/get) | [REC](http://help.ubuntu.ru/wiki/руководство_по_ubuntu_desktop_14_04/получение_ubuntu) | [Setting](http://help.ubuntu.ru/wiki/руководство_по_ubuntu_desktop_14_04/введение) | [GPL License](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 
CentOS | [Download](https://wiki.centos.org/Download) | [REC](http://serveradmin.ru/ustanovka-centos-7/) | [Setting](http://serveradmin.ru/ustanovka-centos-7/) | [the GPL and other](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) 

The application of all these distributions by individuals does not require the purchase of a license agreement from the copyright owner. 

Please note that for distributions with a license [GNU GPL](https://ru.wikipedia.org/wiki/GNU_General_Public_License#GPL_v2) (ROSA Fresh, Ubuntu, CentOS, ...) in their application in commercial and public institutions and enterprises of the Russian Federation, a license is required to support the distribution and their application in state agencies may be restricted, as they are not included in [the Unified register of the Russian software and database dannyh](https://reestr.minsvyaz.ru/reestr/?sort_by=date&sort=asc&class[]=54112&name=&owner_status=&owner_name=&set_filter=Y). 


### Links to materials for the study 

#### Lectures, courses, presentations, videos 

* [Training courses and seminars for IT specialists](https://www.basealt.ru/courses/training/) 
* [Review of ALT Linux 7](https://www.youtube.com/watch?v=uQrwD-OR-V4) 
* [Linux - Install Ubuntu next to Windows. (BIOS MBR) ](https://www.youtube.com/watch?annotation_id=annotation_3417824783&feature=iv&src_vid=nGfkdBR2VVo&v=OmMkAGuZxCE) 

#### Recommended books 

* [ALT Linux outside. ALT Linux from the inside](http://www.altlinux.ru/products/books/inside-outside/) 
* [Linux: a Complete guide / D. N. Kolisnichenko, Peter V. Allen (PDF)](http://www.softlabirint.ru/book/18371-linux-polnoe-rukovodstvo-dn-kolisnichenko-piter-v-allen-pdf.html) 
* [The Linux command «from A to Z», review with examples](http://edu-cisco.org/docs/lp/Linux-handbook-comands-A-Z-SEDICOMM-University.pdf) 
* [Daniel J. Barrett - basic Linux commands pocket guide](http://www.proklondike.com/books/linux/barret-linux-osnovnye-komandy.html) 
* [Best books about Linux](https://losst.ru/luchshie-knigi-o-linux) 

### Software 

### Laboratory work and practical tasks 

### Opportunities for certification 
* [Certification of specialists ALTLinux](http://www.altlinux.ru/partners/certified-specialist/) 
* [Certified Centre of competence for training Astra Linux](http://www.astralinux.com/uchebnyi/centr-obucheniya.html) 


## Install and configure WEB server apache2 

### description of the installation in the distributions of the family ALTLinux 

The user in as root, type the command: 

```sh
# apt-get update
# apt-get install apache2
``` 

Answer Y to the query about installing dependent packages. 
After installing apache2 and its dependent packages dial for automatic server startup Apache : 

* for server distros with SysV system Manager: 

```sh
# chkconfig --add httpd2
# chkconfig --level 2345 httpd2 on
# service apache2 start
``` 

* for server and desktop distributions, with a system Manager Systemd: 

```sh
# systemctl enable apache2
# systemctl start apache2
``` 

By default, the apache server when obrazenia to port 80 at http://localhost/ displays the content located in /var/www/html/. If You plan to support multiple virtual hosts, You can configure in the file /etc/httpd2/conf/sites-available/vhosts.conf. 

Apache2 server supports virtualizatio hosts 

* [ports](https://httpd.apache.org/docs/2.4/vhosts/examples.html#port) 
* [domains](https://httpd.apache.org/docs/2.4/vhosts/examples.html#purename) 
* [IP-addresses](https://httpd.apache.org/docs/2.4/vhosts/examples.html#twoips) 
* [a combination of the above options](https://httpd.apache.org/docs/2.4/vhosts/examples.html#ipport). 

Code virtual host, usually located in subdirectories of the directory /var/www/vhosts/. 

After the description of the virtual hosts you need to activate them and reboot the server: 

```sh
# a2ensite vhosts
# service apache2 restart
``` 

### Check rabotosposobnosti 

After starting the server, type the command: 

```sh
# netstat -nlpt | grep httpd2
``` 

the result string should be displayed with a description of the list of ports served by apache (default port 80): 

```
tcp    0      0 :::80   :::*   LISTEN   xxxxx/httpd2
``` 

xxx is the number of process httpd2. 

If the program netstat is missing, install it: 

```sh
# apt-get install net-tools
``` 

or you can use the command 

```sh
# fuser  -nv tcp 80
``` 

This command to display a list of root and child processes httpd2 serving port 80. For example: 

```
80/tcp: 3756  3768  3769  3770  3771  3772
``` 

If the netstat command and/or the fuser output an empty list, look for errors in the log file /var/log/httpd2/error_log. 

To check the operation of the apache2 server can be addressed in the browser to the address http://localhost/ or http://&lt;ip-адрес-внешнего_интерфейса&gt;/ when the server installation. 

Typing 

```sh
# tail -f /var/log/httpd2/*log
``` 

you can dynamically review the logs of the appeal to the pages of the apache2 server, the error and warnings in the server. 

### Links to materials for the study 

* Ubuntu [Apache HTTP Server](http://help.ubuntu.EN/wiki/apache2) 
* The distribution of ROSA: [Install Apache, PHP, MySQL ](http://wiki.rosalab.ru/ru/index.php/Установка_Apache,_PHP,_MySQL) 
* CentOS [Install and configure Apache, PHP, MySQL on CentOS](https://unixhost.pro/clientarea/knowledgebase/12/Ustanovka-i-nastrojka-Apache-PHP-MySQL-na-CentOS.html) 

#### Lectures, courses, presentations, videos 



#### Recommended books 

### Software 

### Laboratory work and practical tasks 



## Install and configure application server Mono/.NET 

### description of the installation in the distributions of the family ALTLinux 

The user in as root, type the command: 

```sh
# apt-get update
# apt-get install apache2-mod_mono mono4-full
``` 

Answer Y to the query about installing dependent packages. 

After you install all the packages type the following command: 

```sh
# cd /usr/bin
# ln -sf mod-mono-server4  mod-mono-server.sh
# cd /etc/httpd2/conf/mods-available/
# > mono.conf
# echo "LoadModule mono_module modules/mod_mono.so" > mono.load
# a2enmod  mono
``` 

### Check rabotosposobnosti 

Start your own virtual Mono/.NET site is similar to running a virtual site Apache2 server: 

* Place trees site in a separate directory (for example /var/www/vhosts/myMonoApp/) HOST system. 
* Add the configuration of Your virtual host configuration file /etc/docker/apache2/conf/vhosts.conf using the template: 

```
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

* Restart apache2 server: 

* for server distros with SysV system Manager: 

```
# service apache2 restart
    ``` 

* for server and desktop distributions, with a system Manager Systemd: 

```
# systemctl restart apache2
    ``` 

* Ask the browser to the root page of the virtual website URL: http://localhost:881/. 


### Links to materials for the study 

#### Lectures, courses, presentations, videos 

#### Recommended books 

### Software 

### Laboratory work and practical tasks 

### Links to materials for the study 
[Start ASP.NET applications on Linux](https://www.ibm.com/developerworks/ru/library/l-Mono_10/) 
[Series of articles: Working with Mono](https://www.ibm.com/developerworks/ru/library/l-Mono_13/) 


## Go 
* [Deploy solutions in the Docker container](gbt_deployment_docker.html) 
* [Course home page](gbt_landing-page.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}