---
title: Язык программирования С#
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_csharp.html
lang: ru
---

## Краткое описание

**C# (произносится “Си-шарп”)** — это язык программирования, предназначенный для разработки самых разнообразных приложений, предназначенных для выполнения в среде .NET Framework. Язык C# прост, типобезопасен и объектно-ориентирован. Благодаря множеству нововведений C# обеспечивает возможность быстрой разработки приложений, но при этом сохраняет выразительность и элегантность, присущую языкам C.

**Visual C#** — это реализация языка C# корпорацией Майкрософт. Поддержка Visual C# в Visual Studio обеспечивается с помощью полнофункционального редактора кода, компилятора, шаблонов проектов, конструкторов, мастеров кода, мощного и удобного отладчика и многих других средств. Библиотека классов .NET Framework предоставляет доступ ко многим службам операционной системы и другим полезным, правильным классам, что существенно ускоряет цикл разработки.

## Пример использования

```csharp
// A Hello World! program in C#.
using System;
namespace HelloWorld
{
    class Hello 
    {
        static void Main() 
        {
            Console.WriteLine("Hello World!");

            // Keep the console window open in debug mode.
            Console.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }
    }
}
```

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
<iframe style="position: absolute; width: 100%; height: 100%; left: 0px; top: 0px; z-index: 2;" src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21112&authkey=ALgReuLtSJ-zwVM&em=2" frameborder="0" scrolling="no"></iframe>
</div>

## Программное обеспечение

* [Microsoft Visual Studio](https://www.visualstudio.com/)
* [MonoDevelop (Cross platform IDE for C#, F# and more)](http://www.monodevelop.com/)
* [LINQPad (The .NET Programmer’s Playground)](https://www.linqpad.net/)
* [dotPeek (Free .NET Decompiler and Assembly Browser)](https://www.jetbrains.com/decompiler/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Базовый курс</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>                    
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/67ef8sbd.aspx"> Руководство по программированию на C#</a><i> - Microsoft Docs</i></li> 
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/bb383962(v=vs.90).aspx"> Интерактивный учебник по Visual C#</a><i> - Microsoft Docs</i></li>
                    <li><a href="http://www.ecma-international.org/publications/standards/Ecma-334.htm"> Standard ECMA-334. C# Language Specification</a><i> - Ecma International</i></li>
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
                Самоучители</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">                
                <div>
                    <li><a href="https://professorweb.ru/my/csharp/charp_theory/level1/index.php"> Руководство по С#</a><i> - professorweb.ru</i></li>
                    <li><a href="https://msdn.microsoft.com/ru-ru/magazine/dn802602.aspx"> Новый и более совершенный C# 6.0</a><i> - Microsoft Developer Network</i></li> 
                    <li><a href="http://metanit.com/sharp/tutorial/"> Полное руководство по языку программирования С# 7.0 и платформе .NET 4.7</a><i> - metanit.сom</i></li>
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
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">                
                <div>                   
                    <li><a href="https://www.youtube.com/playlist?list=PLtjuvkyFrt5WjvySK8HinYjyTObam4ROY"> Базовый курс C#</a><i> - youtube-аккаунт «Georgiy Mogelashvili»</i></li>
                    <li><a href="https://mva.microsoft.com/en-US/training-courses/programming-in-c-jump-start-14254?l=MqbQvzSfB_1500115888"> Programming in C# Jump Start</a><i> - msdn Channel 9</i></li>                    
                    <li><a href="https://www.youtube.com/playlist?list=PLWCoo5SF-qAN-mySVH6p7X0YPvMr8U1OU"> Видеокурс примеров C#</a><i> - youtube-аккаунт «Программирование - это просто»</i></li>                    
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">                
                <div>
                    <li><a href="http://www.ozon.ru/context/detail/id/135794222/"> C# 6.0. Справочник. Полное описание языка</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/34820810/"> C# 6.0. Карманный справочник</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/21236101/"> CLR via C#. Программирование на платформе Microsoft.NET Framework 4.5 на языке C#</a><i> - ozon.ru</i></li>        
                </div>
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default" >
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse5">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse5">
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse" aria-expanded="false">
            <div class="panel-body">                
                <div>
                    <li><a href="https://www.microsoft.com/ru-ru/learning/exam-70-483.aspx"> Экзамен 70-483 (Программирование на C #)</a><i> - Microsoft</i></li>
                    <li><a href="https://geekbrains.ru/professions/microsoft_developer"> Разработчик C#</a><i> - GeekBrains</i></li>
                    <li><a href="https://www.microsoft.com/en-us/learning/visual-studio-certification.aspx"> Microsoft Visual Studio certifications</a><i> - Microsoft</i></li>       
                </div>
            </div>
        </div>
    </div>
</div>

## Перейти

* [Mono](gbt_mono.html)
* [Главная страница курса](gbt_landing-page.html)
