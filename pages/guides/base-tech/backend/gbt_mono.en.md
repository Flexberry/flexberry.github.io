--- 
title: Mono 
keywords: Programming 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_mono.html 
lang: en 
autotranslated: true 
hash: a5eeb8bf509e437248291b131a77296bb2dd302655b6ead46dcc95d866f5b709 
--- 

**Mono** — a project to create a full-fledged incarnation of the system .NET Framework on the basis of free software. The main developer of the Mono project — the Corporation Xamarin, formerly Novell. The project is headed by Miguel de Icaza, a well-known developer, founder of the GNOME project. The implementation of Mono are available for the following operating systems: Windows, Linux, BSD (FreeBSD, OpenBSD, NetBSD), Solaris, Mac OS X, Apple iOS, Wii. Supported platforms: s390, SPARC, PowerPC, x86/x86-64, IA-64, ARM, Alpha, MIPS, HP PA. 

## Links to materials for the study 

* [Documentation](http://www.mono-project.com/docs/) 
* [Microsoft .NET Framework](https://flexberry.github.io/ru/gbt_dotnet.html) 
* [ASP.NET](https://flexberry.github.io/ru/gbt_aspnet.html) 
* [Programming language С#](https://flexberry.github.io/ru/gbt_csharp.html) 

* [Language learning C# for beginners (Mono)](https://www.youtube.com/watch?v=3FWqP80fNJM&list=PL0lO_mIqDDFU66Cwwctcv1C6VNVpaqHfo) 

### Presentation 

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px"> 
<iframe width="854" height="480" src="https://www.youtube.com/embed/5oLgQc_gnJg?list=PLaKXsWB2aJ1utjsUDJDmZBUW2KylhYsx0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe> 
</div> 

## debug the project 

1.To run the command 

```
docker pull akosinsky/monodevelop-ember:latest
``` 

2.Set [XServer](http://www.netsarang.com/download/down_xmg.html) under windows. 

3.Start XServer. 

4.To run the command 

```
docker run -dti --network host -e "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/root/projects/scripts" -e "DISPLAY=<IP-custom>" -e "http_proxy=http://<IP-custom>" -v с:/catalog:/childrencatalog  developer/monodevelop-ember:latest /usr/bin/mate-terminal --disable-factory
``` 

5.In the opened terminal window execute the command: 

```
monodevelop&
``` 

6.Open the desired project and in the properties specify: 

![](/images/pages/products/base-tech/mono/Monodevelop01.png) 

7.In Visual Studio, specify: 

[MdbConverter.exe](https://github.com/Flexberry/mono/tree/MdbConverter). with:/catalog:/childrencatalog 

To collect `MdbConverter` should: 
1. To perform `git clone https://github.com/Flexberry/mono` 
2. `git checkout MdbConverter` 
3. Open in Visual Studio project 
mono\mcs\tools\pdb2mdb\MdbConverter.csproj 
4. To Perform The Build. 

![](/images/pages/products/base-tech/mono/Monodevelop02.png) 

8.In the end, you can debug: 

![](/images/pages/products/base-tech/mono/Monodevelop03.png) 

## Software 

* [Mono](http://www.mono-project.com/download/#download-win) 
* [Xamarin Studio](https://www.xamarin.com/studio) 

## Go 

* [ORM](gbt_orm.html) 
* [Course home page](gbt_landing-page.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}