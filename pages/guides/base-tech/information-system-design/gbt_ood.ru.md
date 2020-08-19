---
title: Объектно-ориентированное программирование
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_ood.html
folder: guides/base-tech/information-system-design/
lang: ru
---

## Краткое описание

**Объектно-ориентированное программирование (ООП)** — методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определенного класса, а классы образуют иерархию наследования.

**Основные понятия**
* **Абстракция данных** - Абстрагирование означает выделение значимой информации и исключение из рассмотрения незначимой. В ООП рассматривают лишь абстракцию данных (нередко называя её просто «абстракцией»), подразумевая набор значимых характеристик объекта, доступный остальной программе.
* **Инкапсуляция** — свойство системы, позволяющее объединить данные и методы, работающие с ними, в классе. Одни языки (например, С++, Java или Ruby) отождествляют инкапсуляцию с сокрытием, но другие (Smalltalk, Eiffel, OCaml) различают эти понятия.
* **Наследование** — свойство системы, позволяющее описать новый класс на основе уже существующего с частично или полностью заимствующейся функциональностью. Класс, от которого производится наследование, называется базовым, родительским или суперклассом. Новый класс — потомком, наследником, дочерним или производным классом.
* **Полиморфизм подтипов (полиморфизм)** — свойство системы, позволяющее использовать объекты с одинаковым интерфейсом без информации о типе и внутренней структуре объекта. Другой вид полиморфизма — параметрический — в ООП называют обобщённым программированием.
* **Класс** - универсальный, комплексный тип данных, состоящий из тематически единого набора «полей» (переменных более элементарных типов) и «методов» (функций для работы с этими полями), то есть он является моделью информационной сущности с внутренним и внешним интерфейсами для оперирования своим содержимым (значениями полей). В частности, в классах широко используются специальные блоки из одного или чаще двух спаренных методов, отвечающих за элементарные операции с определенным полем (интерфейс присваивания и считывания значения), которые имитируют непосредственный доступ к полю. 
* **Объект** - сущность в адресном пространстве вычислительной системы, появляющаяся при создании экземпляра класса (например, после запуска результатов компиляции и связывания исходного кода на выполнение).

##  Пример использования

### Схема принципов ООП
![Схема принципов ООП](/images/pages/guides/base-technologies/information-system-design/ood.png)


##  Ресурсы

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
                    <li><a href="https://professorweb.ru/my/csharp/charp_theory/level3/3_1.php"> Основы объектно-ориентированного программирования</a><i> - professorweb.ru</i></li>
                    <li><a href="https://metanit.com/sharp/tutorial/3.1.php"> Объектно-ориентированное программирование</a><i> - metanit.сom</i></li>
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
                    <li><a href="https://www.youtube.com/playlist?list=PLmRNNqEA7JoPhVQCUisflWWhjdoKucDuf"> Курс «Объектно-ориентированное программирование»</a><i> - youtube-аккаунт «Образовательный портал GeekBrains»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PL6LDsbZOeyrx462VmH18qS0a9Dw9LwpSu"> Основы объектно-ориентированного програмирования</a><i> - youtube-аккаунт «Yellow-Duck.net»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLY7PmJJFH5nRcWGKbgl9N7txM5YLtela6"> Объектно-Ориентированное Программирование</a><i> - youtube-аккаунт «Volodya Mozhenkov»</i></li>
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
                    <li><a href="http://www.ozon.ru/context/detail/id/2336754/"> Объектно-ориентированное конструирование программных систем</a><i> - ozon.ru</i></li>
                    <li><a href="https://www.ozon.ru/context/detail/id/26036833/"> Объектно-ориентированное мышление</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/3905587/"> Объектно-ориентированный анализ и проектирование с примерами приложений</a><i> - ozon.ru</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [UML](gbt_uml.html)
* [Главная страница курса](gbt_landing-page.html)
