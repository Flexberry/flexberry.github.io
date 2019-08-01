---
title: Apache Cordova
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_cordova.html
folder: guides/base-tech/mobile/
lang: ru
---

## Краткое описание

**Apache Cordova** (старое название — PhoneGap) — мобильная среда разработки приложений, первоначально разработанная Nitobi. Компания Adobe Systems приобрела Nitobi в 2011 году и провела ребрендинг PhoneGap, после чего выпустила версию с открытым исходным кодом программного обеспечения под названием Apache Cordova. 

Apache Cordova позволяет создавать приложения для мобильных устройств с помощью CSS3, HTML5 и JavaScript, вместо того, чтобы использовать конкретные платформы API, такие как Android, IOS или Windows Phone. Это обеспечивается за счет преобразования из CSS, HTML и JavaScript в код, который любая платформа воспринимает как элемент web. Это расширяет возможности HTML и JavaScript для работы с различными устройствами. 

В результате приложения являются гибридными, это означает, что они не являются ни по-настоящему мобильными приложениями (потому что вся генерация макета осуществляются с помощью web-view вместо основной структуры пользовательского интерфейса платформы), ни web — потому как они не только web — приложение, но и упакованы в качестве приложения для распределения, а также имеет доступ к API базового функционала устройства, такого как файловая система, камера и пр.). 

Программное обеспечение ранее называлось просто «PhoneGap», а затем «Apache Cordova». Как ПО с открытым исходным кодом, Apache Cordova используется в других программах, таких как Appery.io или Intel XDK.

## Примеры использования

### Установка модуля `cordova`, создание приложения и добавление платформы `android`

```
npm install -g cordova
```
```
cd MyApp
```
```
cordova platform add android
```

### «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="https://www.youtube.com/embed/FiItIyVniJo?ecver=2" width="854" height="480" frameborder="0" style="position:absolute;width:100%;height:100%;left:0" allowfullscreen>
    </iframe>
</div>

## Программное обеспечение

* [Apache Cordova](https://cordova.apache.org/#getstarted)
* [Microsoft Visual Studio](https://www.visualstudio.com/ru/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Базовые ресурсы</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>                    
                    <li><a href="https://cordova.apache.org/docs/en/latest/">Apache Cordova Documentation</a><i> — cordova.apache.org</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>                    
                    <li><a href="https://www.youtube.com/playlist?list=PLReL099Y5nRd9BNsMZwXvTDeqnfRMiGJy">Getting Started with Apache Cordova Mobile App Development</a><i> — youtube-аккаунт «Microsoft Visual Studio»</i></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>                    
                    <li><a href="https://www.amazon.com/Apache-Cordova-Cookbook-Mobile-Programming/dp/0321994809">Apache Cordova API Cookbook (Mobile Programming)</a><i> — amazon.сom</i></li>
                    <li><a href="https://www.amazon.com/Apache-Cordova-Programming-Mobile/dp/0134048199/ref=pd_lpo_sbs_14_t_1/142-6513688-8635118?_encoding=UTF8&psc=1&refRID=YE8HJYP4YAHXNVY8ZYJA">Apache Cordova 4 Programming (Mobile Programming)</a><i> — amazon.сom</i></li>
                    <li><a href="https://www.amazon.com/Apache-Cordova-Action-Raymond-Camden/dp/1633430065/ref=pd_lpo_sbs_14_t_0/142-6513688-8635118?_encoding=UTF8&psc=1&refRID=YE8HJYP4YAHXNVY8ZYJA">Apache Cordova in Action</a><i> — amazon.сom</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Основы UI](http://localhost:4000/ru/gbt_design.ru.html)
* [Главная страница курса](gbt_landing-page.html)