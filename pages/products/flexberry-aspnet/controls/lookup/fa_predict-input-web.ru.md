---
title: Предиктивный ввод в Web-приложениях
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_predict-input-web.html
lang: ru
---

## Настройка предиктивного ввода LookUp'ов

Чтобы добавить в LookUp предиктивный ввод, необходимо:

1. В [E-представлении](fd_e-view.html) объекта указать мастеру тип LookUp'a `standart` и указать свойство мастера, по которому будет происходить поиск для предиктивного ввода
2. Сгенерировать объекты
3. Скомпилировать объекты
4. Сгенерировать ASP.NET приложение

__Примечание__: [способ](fw_predict-input.html), описанный для Windows-приложений сработает и для Web.

## Настройка E-представления класса

Чтобы превратить обычный LookUp в LookUp с предиктивным вводом, необходимо указать мастеру тип используемого LookUp'a.

1.Открыть E-представление объекта и выделить добавленный мастер, для которого будет использоваться предиктивный ввод

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-master-web.png)

2.В поле `Тип лукапа` указать `standart`

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-type-web.png)

3.В поле `Свойство мастера` указать наименование свойства, по которому будет происходить поиск для предикативного ввода.

__Примечание__: свойство мастера должно содержаться в представлении. Наименование свойства вводится с клавиатуры.

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-property-web.png)

4.Сохранить изменения

### Результат

![](/images/pages/products/flexberry-aspnet/controls/lookup/predict-lookup-web.gif)

## Принципы подбора значений

Значения подбираются из поля мастера, выбранного при настройке. Ищутся значения, содержащие в себе подстроку, введенную пользователем. Реализован расширенный поиск вхождений слов.

Пусть дан список:

```csharp
1) слово
2) пара слов
3) целых три слова
4) триангуляция слова
5) парад слов
```

Пользователь вводит `три слов`, находится 3), 4)

По сути, запрос из like `%три слов%` выглядит как like `%три%слов%`.

![](/images/pages/products/flexberry-aspnet/controls/lookup/predict.png)

Подробнее о поиске по подстроке можно посмотреть [в статье MasterEditorAjaxLookUp] (fa_master-editor-ajax-lookup.html).

## Предиктивный ввод в Windows-приложениях

[Предиктивный ввод в Windows-приложениях](fw_predict-input.html)
