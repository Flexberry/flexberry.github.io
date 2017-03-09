---
title: Порядок вызовов бизнес-серверов
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Бизнес-серверы
toc: true
permalink: ru/fo_business-server-order.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry ORM](fo_flexberry-orm.html)
* '''Компонент''': [Бизнес-сервер](business-logic.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Описаны основные правила, которым определяется порядок вызова [бизнес-серверов](fo_business-servers-wrapper-business-facade.html).
</td>
</tr></tbody></table></a>
</div>

# Порядок вызова бизнес-серверов
Следует учесть, что в [бизнес-сервере](fo_business-servers-wrapper-business-facade.html) может быть вызвано обновление объекта не через возвращаемый массив, а отдельным вызовом [сервиса данных](fo_data-service.html). В этом случае, надо учесть то, что [бизнес-серверы](fo_business-servers-wrapper-business-facade.html) будут вызываться для этого объекта отдельно.

## Агрегатор, детейлы
Если имеется следующая ситуация c [детейлами](fo_detail-associations-and-their-properties.html):

![](/images/pages/img/page/Business-Servers-Wrapper-Business-Facade/AgregatorDetailAtBS.png)

то бизнес-сервера при обновлении будут срабатывать в следующем порядке:
# КлассАгрегатор
# КлассДетейл

## Наследование
Если имеется следующая ситуация с [наследованием](inheritance.html):

![](/images/pages/img/page/Business-Servers-Wrapper-Business-Facade/BSHierarchy.JPG)

то бизнес-серверы при обновлении объекта класса `Человек` будут срабатывать в следующем порядке: 
# Существо
# Животное
# Млекопитающее
# Человек

## Интерфейсы
Можно прописать [атрибут для привязки бизнес-сервера](otrabotka-polzovatelskih-operacii-v-processe-raboty-servisa-dannyh-integraciya-s-biznes-serverom.html) не только к классу, но и к интерфейсу. В этом случае все объекты класса-наследника от этого интерфейса будут обрабатываться данным бизнес-сервером. Если указано несколько интерфейсов с бизнес-серверами: 
```cs
public class Class2:Class3, Interface3
{
//...
}

public class Class1:Class2, Interface1, Interface2
{
//...
}
```

то порядок срабатывания будет таким: 

# бизнес-сервер, привязанный к Class3
# бизнес-сервер, привязанный к Interface3
# бизнес-сервер, привязанный к Class2
# бизнес-сервер, привязанный к Interface2
# бизнес-сервер, привязанный к Interface1
# бизнес-сервер, привязанный к Class1

(((
<msg type=note>Если некоторый интерфейс встретится несколько раз, то [бизнес-сервер](fo_business-servers-wrapper-business-facade.html) для него будет выполнен только один раз в соответствии с уровнем самого дальнего предка.</msg>
)))

## Правила обновления объекта данных
Например, имеется следующая ситуация:

![](/images/pages/img/page/Business-Servers-Wrapper-Business-Facade/BusinesServerMasters.GIF)

Если в бизнес-сервере для класса Товар мы каким-то образом исправили Фирму-производителя и вернули её в методе бизнес-сервера, то для фирмы-производителя вызовется её бизнес-сервер. Важно понимать, что запрос для Товара был уже сгенеририван (это было выполнено сразу после отработки его собственного бизнес-сервера) и в бизнес-сервере Фирмы-производителя изменять Товар не следует - новые изменения не будут зафиксированы в БД.
