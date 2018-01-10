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

**Платформа Web API** представляет иной способ построения приложения ASP.NET несколько отличный от ASP.NET MVC. Web API представляет собой веб-службу, которая может взаимодействовать с различными приложениями. При этом приложение может быть веб-приложением ASP.NET, либо может быть мобильным или обычным десктопным приложением.

**Платформа Web API** не является частью фреймворка ASP.NET MVC и может быть задействована как в связке с MVC, так и в соединении с Web Forms. Поэтому в Web API имеется своя система версий. Так, первая версия появилась с .net 4.5. А вместе с .NET 4.5.1 и MVC 5 вышла Web API 2.0.

**Платформа ASP.NET Core** представляет технологию от компании Microsoft, предназначенную для создания различного рода веб-приложений: от небольших веб-сайтов до крупных веб-порталов и веб-сервисов.

**Платформа ASP.NET Core** построен на основе кросс-платформенной среды .NET Core, которая может быть развернута на основных популярных операционных системах: Windows, Mac OS X, Linux. И хотя Windows в качестве среды для разработки и развертывания приложения до сих пор превалирует, но теперь уже мы не ограничены только этой операционной системой. То есть мы можем запускать веб-приложения не только на ОС Windows, но и на Linux и Mac OS. А для развертывания веб-приложения можно использовать традиционный IIS, либо кросс-платформенный веб-сервер Kestrel.

##  Ссылки на материалы для изучения

### Базовый курс

* [The ASP.NET Site](https://www.asp.net/)
* [ASP.NET и Visual Studio для веб-разработки](https://msdn.microsoft.com/ru-ru/library/dd566231.aspx)
* [Пошаговые руководства по приложению Web Forms](https://msdn.microsoft.com/ru-ru/library/c67263se(v=vs.90).aspx)
* [Learn ASP.NET](https://www.asp.net/learn)
* [Getting Started with ASP.NET 4.5 Web Forms and Visual Studio 2013](https://code.msdn.microsoft.com/Getting-Started-with-221c01f5/view/SourceCode#)
* [Практическое руководство. Создание приложения ASP.NET Web Forms](https://msdn.microsoft.com/ru-ru/library/hh987037(v=vs.110).aspx)
* [Общие сведения о синтаксисе веб-страниц ASP.NET](https://msdn.microsoft.com/ru-ru/library/k33801s3(v=vs.100).aspx)
* [Веб-платформы ASP.NET для Visual Studio 2013](https://msdn.microsoft.com/ru-ru/library/dn467680(v=vs.108).aspx)
* [Документация по веб-API ASP.NET](https://msdn.microsoft.com/ru-ru/library/dn448365(v=vs.118).aspx)
* [Справочник по ASP.NET MVC 4](https://msdn.microsoft.com/ru-ru/library/gg416515(v=vs.108).aspx)
    
### Самоучители

* [Основы ASP.NET](https://professorweb.ru/my/ASP_NET/base/level1/base_aspnet_index.php)
* [Веб-сайты ASP.NET](https://professorweb.ru/my/ASP_NET/sites/level1/)
* [Безопасность в ASP.NET](https://professorweb.ru/my/ASP_NET/security/level1/)
* [ASP.NET Web Forms 4.5](https://professorweb.ru/my/ASP_NET/webforms_4_5/level1/)

* ASP.NET MVC
    * [Руководство по ASP.NET MVC 5](http://metanit.com/sharp/mvc5/)
    * [Изучаем ASP.NET MVC 5](https://professorweb.ru/my/ASP_NET/mvc/level1/)
    * [Руководство по ASP.NET Core](http://metanit.com/sharp/aspnet5/)
    * [Руководство по ASP.NET Web API 2](http://metanit.com/sharp/aspnet_webapi/)

### Видеокурсы

* [Видео курс ASP.NET Базовый курс](https://www.youtube.com/watch?v=VU-NObnc2jA&list=PLvItDmb0sZw8qA87QMQbx5RpPO_dfCGOy)
* [ASP.NET Advanced](https://www.youtube.com/watch?v=qiujcPFFinA&list=PLvItDmb0sZw_TRwlGr0BVFtS-sP3YaDMU)
* [Разработка приложений на основе ASP Net Web Forms](https://www.youtube.com/watch?v=VSsXD1JVRYQ&index=20&list=PLMv23B5LnJ9NJ_s4iuIaNen-DbvdR1dMq)
* [Создание простой страницы Asp.Net WebForms](https://www.youtube.com/watch?v=Ww6CVU4oS3k&index=1&list=PLIZicc8_ARKsGxB446wkS6vC-4OynaYIy)
* [ASP.NET Web Forms](https://channel9.msdn.com/Events/dotnetConf/2014/Web-Forms)
* [Введение в ASP.NET MVC](https://mva.microsoft.com/ru/training-courses/-aspnet-mvc-8322?l=eTXjmit7_304984382)

### Презентация

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/-XzDcHh5Y5s?list=PLlhqsC7hBaSezv_J4znt-NbFq4MCzcYzk" frameborder="0" allowfullscreen></iframe>
</div>

### Рекомендованные книги

* [Программирование с использованием Microsoft ASP.NET 3.5](http://www.ozon.ru/context/detail/id/4148051/)
* [ASP.NET. Сборник рецептов](http://www.ozon.ru/context/detail/id/28277279/)
* [Microsoft ASP .NET. Обеспечение безопасности](http://www.ozon.ru/context/detail/id/136359541/)
* [ASP.NET 4.5 с примерами на C# 5.0 для профессионалов](http://www.ozon.ru/context/detail/id/26199321/)
* [Самоучитель ASP.NET](http://www.ozon.ru/context/detail/id/28266738/)
* [ASP.NET MVC 4. Разработка реальных веб-приложений с помощью ASP.NET MVC](http://www.ozon.ru/context/detail/id/20343905/)
* [ASP.NET MVC 5 с примерами на C# 5.0 для профессионалов](http://www.ozon.ru/context/detail/id/29482313/)

## Программное обеспечение

* [Microsoft Visual Studio](https://www.visualstudio.com/downloads/)
* [Visual Studio Code](https://code.visualstudio.com/download)
* [MonoDevelop (Cross platform IDE for C#, F# and more)](http://www.monodevelop.com/)

## Примеры

* [Интернет магазин на ASP.NET Web Forms 4.5](https://professorweb.ru/my/ASP_NET/gamestore/level1/1_1.php)
* [Практическое руководство по ASP.NET MVC 4](http://metanit.com/sharp/helpdeskmvc/)
* [Интернет магазин на ASP.NET MVC 5](https://professorweb.ru/my/ASP_NET/gamestore/level2/2_1.php)

## Возможности по сертификации

* [Экзамен 70-486. Developing ASP.NET MVC Web Applications](https://www.microsoft.com/ru-ru/learning/exam-70-486.aspx)
* [Разработчик C#](https://geekbrains.ru/professions/microsoft_developer)
* [MCSD: Web Applications](https://www.microsoft.com/ru-ru/learning/mcsd-web-apps-certification.aspx)

## Перейти

* [Язык программирования С#](gbt_csharp.html)
* [Главная страница курса](gbt_landing-page.html)
