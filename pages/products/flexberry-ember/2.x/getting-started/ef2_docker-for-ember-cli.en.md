--- 
title: Ember-CLI via Docker 
keywords: ember, ember-cli, docker build 
tags: [EmberJS] 
sidebar: flexberry-ember-2_sidebar 
toc: true 
permalink: en/ef2_docker-for-ember-cli.html 
lang: en 
autotranslated: true 
hash: 378f9cff7b3d10866018e57751adf740c6e1b236cedf749d6557ae0e8d691945 
summary: description of the development tools without the need to install and on the developer's computer (make sure to have Docker installed). 
--- 

## Requirements installed on the computer 

To use the Docker image you want to install [Docker](https://www.docker.com/). On the manufacturer's website has various versions, including for Windows (recommended `Windows 10`). In order to make productive use of `Docker` (not only for ember development) recommended [read the containerization tool read more](gbt_deployment_docker.html). 

### Linking file system your operating system and the Docker container 

The Docker container can use the file system of the host machine. This is done by design `-v /host/folder:/image/folder` when you start the container. In Docker for Windows pre-required to configure access to the file system service Docker settings (`Settings...` -> `Shared Drives`). 

### Linking ports in your operating system and the Docker container 

The Docker container can "pass" ports from container to host machine. This is done by `-p 4200:4200` when you start the container. 

## the Docker image for Ember-CLI 

To work with `Ember-CLI` you can use the most popular Docker-image - [danlynn/ember-cli](https://hub.docker.com/r/danlynn/ember-cli/), which is published on [hub.docker.com](https://hub.docker.com). This way is implemented for the various versions of Ember-CLI, for example for version `2.4.3 ` the image will be called: `danlynn/ember-cli:2.4.3 `. **Note that for different versions of images have a slight difference in the command to start the Docker container (see [the documentation for the image](https://hub.docker.com/r/danlynn/ember-cli/)).** 

## Run the Docker container with Ember-CLI 

To run the image, you should perform 2 steps in command line interface (`Windows` recommended `PowerShell`): 
1) Download the needed image - `danlynn/ember-cli:2.4.3 ` 

``` bash
docker pull danlynn/ember-cli:2.4.3
``` 

2) Go to the folder with ember-app 

``` bash
cd <путь до каталога>`
``` 

3) Run the Docker container 

Option for `Windows PowerShell` 
``` bash
docker run --rm -ti -v ${PWD}:/myapp --entrypoint=/bin/bash -p 4200:4200 -p 49153:49153 -p 7357:7357 danlynn/ember-cli:2.4.3
``` 

Option for `Linux bash` 

``` bash
docker run --rm -ti -v $(pwd):/myapp --entrypoint=/bin/bash -p 4200:4200 -p 49153:49153 -p 7357:7357 danlynn/ember-cli:2.4.3
``` 

4) started the terminal running all the right commands Ember-CLI: 
`npm i`, `bower i --allow-root`, `ember b`, `ember s` etc.. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}