---
title: Mono
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_mono.html
lang: ru
---

## Краткое описание

**Mono** — проект по созданию полноценного воплощения системы .NET Framework на базе свободного программного обеспечения. 

Mono включает в себя компилятор языка C# — dmcs, среду исполнения .NET — mono (с поддержкой JIT) и mint (без поддержки JIT), отладчик, а также ряд библиотек, включая реализацию WinForms, ADO.NET и ASP.NET, а также компиляторы smcs (для создания приложений для Moonlight) и vbc (для приложений, написанных на VB.NET).

В рамках проекта также разрабатываются привязки для графической библиотеки GTK+ на платформу .NET.

**Mono** содержит альтернативу структуре WPF-приложений (XAML + C# или любой другой язык, который поддерживается в данной среде исполнения). Данный язык называется Glade, при помощи него можно собирать GTK-приложения.

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/5oLgQc_gnJg?list=PLaKXsWB2aJ1utjsUDJDmZBUW2KylhYsx0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>
</div>

## Программное обеспечение

* [Mono](http://www.mono-project.com/download/#download-win)
* [Xamarin Studio](https://www.xamarin.com/studio)

## Больше информации по теме

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Отладка проекта</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <ol>
                        <li>
                            Выполнить команду
                            <pre>docker pull akosinsky/monodevelop-ember:latest</pre>
                        </li>
                        <li>Установить <a href="http://www.netsarang.com/download/down_xmg.html"> XServer</a> под windows.</li>
                        <li>Запустить XServer.</li>
                        <li>
                            Выполнить команду
                            <pre>docker run -dti --network host -e "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/root/projects/scripts" -e "DISPLAY=&lt;IP-custom&gt;" -e "http_proxy=http://&lt;IP-custom&gt;" -v с:/catalog:/childrencatalog  developer/monodevelop-ember:latest /usr/bin/mate-terminal --disable-factory</pre>
                        </li>
                        <li>
                            В открывшемся окне терминала выполнить команду:
                            <pre>monodevelop&</pre>
                        </li>
                        <li>
                            Открыть нужный проект и в свойствах указать:
                            <img src="/images/pages/products/base-tech/mono/Monodevelop01.png">
                        </li>
                        <li>
                            В Visual Studio указать:
                            <p><a href="https://github.com/Flexberry/mono/tree/MdbConverter"> MdbConverter.exe</a>. с:/catalog:/childrencatalog</p>
                            <p>Чтобы собрать <code class="highlighter-rouge">MdbConverter</code> необходимо:</p>
                            <ol>
                                <li> 
                                    Выполнить <code class="highlighter-rouge">git clone https://github.com/Flexberry/mono</code>
                                </li>
                                <li> 
                                    <code class="highlighter-rouge">git checkout MdbConverter</code>
                                </li>
                                <li> 
                                    Открыть в Visual Studio проект 
                                    mono\mcs\tools\pdb2mdb\MdbConverter.csproj
                                </li>
                                <li> 
                                    Выполнить Build.
                                    <img src="/images/pages/products/base-tech/mono/Monodevelop02.png">
                                </li>
                            </ol>
                        </li>
                        <li>
                            В итоге можно выполнять отладку:
                            <img src="/images/pages/products/base-tech/mono/Monodevelop03.png">
                        </li>
                    </ol>
                </div>   
            </div>
        </div>
    </div>
</div>

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Документация</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.mono-project.com/docs/"> Mono Documentation</a><i> - Mono</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [ORM](gbt_orm.html)
* [Главная страница курса](gbt_landing-page.html)
