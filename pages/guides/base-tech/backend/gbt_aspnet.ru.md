---
title: ASP.NET
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_aspnet.html
lang: ru
---

## Краткое описание

**ASP.NET (Active Server Pages для .NET)** — технология создания веб-приложений и веб-сервисов от компании Майкрософт. Она является составной частью платформы Microsoft .NET и развитием более старой технологии Microsoft ASP. На данный момент последней версией этой технологии является ASP.NET 5.

**ASP.NET** внешне во многом сохраняет схожесть с более старой технологией ASP, что позволяет разработчикам относительно легко перейти на ASP.NET. В то же время внутреннее устройство ASP.NET существенно отличается от ASP, поскольку она основана на платформе .NET и, следовательно, использует все новые возможности, предоставляемые этой платформой.

**Технология ASP.NET WebForms** появилась несколько раньше и на нее делалась ставка разработчиками Microsoft. Данная технология позволяет приблизить процесс разработки web-приложений к процессу разработки настольных приложений. В распоряжении разработчика имеется набор элементов управления, в которых входят как стандартные элементы, такие как текстовое поле, кнопка,  раскрывающийся список, так и специфические элементы управления, такие как элементы управления валидацией, календарь и т.п. Каждый элемент может быть декларативно размещен на форме. Элементы управления настраиваются при помощи свойств, доступных разработчику для изменения. Для написания кода логики используется событийная модель, близкая разработчикам настольных приложений.

**Технологию ASP.NET WebForms** можно рассматривать как некоторую надстройку над классическим принципом web-программирования «запрос-ответ».  Данная технология является примером концепции RAD (rapid application development — быстрая разработка приложений), позволяя разработчикам максимально быстро и комфортно создавать рабочие приложения. 

**Платформа ASP.NET MVC** представляет собой фреймворк для создания сайтов и веб-приложений с помощью реализации паттерна MVC.
Концепция паттерна (шаблона) MVC (model - view - controller) предполагает разделение приложения на три компонента:
- **Контроллер (controller)** представляет класс, обеспечивающий связь между пользователем и системой, представлением и хранилищем данных. Он получает вводимые пользователем данные и обрабатывает их. И в зависимости от результатов обработки отправляет пользователю определенный вывод, например, в виде представления.
- **Представление (view)** - это собственно визуальная часть или пользовательский интерфейс приложения. Как правило, html-страница, которую пользователь видит, зайдя на сайт.
- **Модель (model)** представляет класс, описывающий логику используемых данных.

**Платформа ASP.NET Core** представляет технологию от компании Microsoft, предназначенную для создания различного рода веб-приложений: от небольших веб-сайтов до крупных веб-порталов и веб-сервисов.

**Платформа ASP.NET Core** построен на основе кросс-платформенной среды .NET Core, которая может быть развернута на основных популярных операционных системах: Windows, Mac OS X, Linux. И хотя Windows в качестве среды для разработки и развертывания приложения до сих пор превалирует, но теперь уже мы не ограничены только этой операционной системой. То есть мы можем запускать веб-приложения не только на ОС Windows, но и на Linux и Mac OS. А для развертывания веб-приложения можно использовать традиционный IIS, либо кросс-платформенный веб-сервер Kestrel.

##  Пример использования

### Принцип работы шаблона MVC
![Принцип работы шаблона MVC](/images/pages/guides/base-technologies/backend/mvc.png)

##  «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/-XzDcHh5Y5s?list=PLlhqsC7hBaSezv_J4znt-NbFq4MCzcYzk" frameborder="0" allowfullscreen></iframe>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Больше информации по теме</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><b>Платформа Web API</b> представляет иной способ построения приложения ASP.NET несколько отличный от ASP.NET MVC. Web API представляет собой веб-службу, которая может взаимодействовать с различными приложениями. При этом приложение может быть веб-приложением ASP.NET, либо может быть мобильным или обычным десктопным приложением.</li>
                    <li><b>Платформа Web API</b> не является частью фреймворка ASP.NET MVC и может быть задействована как в связке с MVC, так и в соединении с Web Forms. Поэтому в Web API имеется своя система версий. Так, первая версия появилась с .net 4.5. А вместе с .NET 4.5.1 и MVC 5 вышла Web API 2.0.</li> 
                </div>   
            </div>
        </div>
    </div>
</div>

## Программное обеспечение

* [Microsoft Visual Studio](https://www.visualstudio.com/downloads/)
* [Visual Studio Code](https://code.visualstudio.com/download)
* [MonoDevelop (Cross platform IDE for C#, F# and more)](http://www.monodevelop.com/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Базовый курс</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/dd566231.aspx"> ASP.NET и Visual Studio для веб-разработки</a><i> - Microsoft Docs</i></li>
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/c67263se(v=vs.90).aspx"> Пошаговые руководства по приложению Web Forms</a><i> - Microsoft Docs</i></li>
                    <li><a href="https://www.asp.net/learn"> Learn ASP.NET</a><i> - Microsoft .NET</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse3">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse3">
                Самоучители</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://professorweb.ru/my/ASP_NET/sites/level1/"> Веб-сайты ASP.NET</a><i> - professorweb.ru</i></li>
                    <li><a href="https://professorweb.ru/my/ASP_NET/security/level1/"> Безопасность в ASP.NET</a><i> - professorweb.ru</i></li>
                    <li><a href="http://metanit.com/sharp/mvc5/">Руководство по ASP.NET MVC 5</a><i> - metanit.сom</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>
    
<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse4">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse4">
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw8qA87QMQbx5RpPO_dfCGOy"> Видеокурс ASP.NET Базовый курс</a><i> - youtube-аккаунт «ITVDN»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw_TRwlGr0BVFtS-sP3YaDMU">Видеокурс по ASP.NET Advanced</a><i> - youtube-аккаунт «ITVDN»</i></li>
                    <li><a href="https://channel9.msdn.com/Events/dotnetConf/2014/Web-Forms">ASP.NET What's New in Web Forms</a><i> - msdn Channel 9</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>
    
<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse5">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse5">
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.ozon.ru/context/detail/id/136359541/"> Microsoft ASP .NET. Обеспечение безопасности</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/28266738/">Самоучитель ASP.NET</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/29482313/">ASP.NET MVC 5 с примерами на C# 5.0 для профессионалов</a><i> - ozon.ru</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse6">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse6">
                Примеры</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://professorweb.ru/my/ASP_NET/gamestore/level1/1_1.php"> Интернет-магазин на ASP.NET Web Forms 4.5</a><i> - professorweb.ru</i></li>
                    <li><a href="https://professorweb.ru/my/ASP_NET/gamestore/level2/2_1.php">Интернет-магазин на ASP.NET MVC 5</a><i> - professorweb.ru</i></li>
                    <li><a href="http://metanit.com/sharp/helpdeskmvc/">Практическое руководство по ASP.NET MVC 4</a><i> - metanit.сom</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse7">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse7">
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse7" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.microsoft.com/ru-ru/learning/exam-70-486.aspx"> Экзамен 70-486. Developing ASP.NET MVC Web Applications</a><i> - Microsoft</i></li>
                    <li><a href="https://geekbrains.ru/professions/microsoft_developer">Разработчик C#</a><i> - GeekBrains</i></li>
                    <li><a href="https://www.microsoft.com/ru-ru/learning/mcsd-web-apps-certification.aspx">MCSD: Web Applications</a><i> - Microsoft</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Язык программирования С#](gbt_csharp.html)
* [Главная страница курса](gbt_landing-page.html)
