---
title: Расширенный редактор ограничений в WEB
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы), Ограничения
toc: true
permalink: ru/fa_advanced-limit-editor.html

---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;<b><b class="searchkeyword">Редактор</b> ограничений</b><br><br><ul><li><b>Платформа</b>: <a class="pagelink" href="https://flexberry.github.io/ru/fa_flexberry-a-s-p-n-e-t.html" title="Flexberry ASP.NET">FlexberryASP.NET</a>.</li><li><b>Компонент</b>: <a class="pagelink" href="https://flexberry.github.io/ru/fa_web-controls.html" title="Web-контролы и web-компоненты">Web-контролы и web-компоненты</a>.</li><li><b>Предназначение:</b> <b class="searchkeyword">редактор</b> ограничений дает возможность осуществлять сложную фильтрацию выводимых элементов.</li><li><b>JavaScript API:</b></li><li><b><a class="externallink" href="http://storm:20013/namespace_web_limit_editor.html" title="Автодокументация" target="_blank">Автодокументация</a></b>.</li><li><b><a class="externallink" href="http://ru:6158/LimitEditor.aspx?param=CUSTOM_LimitEditor_ctl00$ContentPlaceHolder1$ctrlLimitFunctionEdit_formsExamplesLimitEditorTestLimitEditorTestPage.aspx_ICS_HOMEAPopova&amp;module=~/forms/Examples/LimitEditorTest/LimitEditorTestPage.aspx/__Page" title="Редактор ограничений на тестовом стенде" target="_blank">Редактор ограничений на тестовом стенде</a></b>.<br></li></ul><br></td>
</tr></tbody></table>
</div>

## Список пользовательских функций

* Открыть редактор
* Закрыть редактор (+ситуации когда он закрывается сам)
* Создать ограничение
* Создать ограничение на основе текущего
* Составить ограничение
    + Добавить условие
        - объединение (ИЛИ)
        - пересечение (И)
        - отрицание (НЕ)
    + Изменить операцию в условии
        - логические операции:
            - объединение (ИЛИ)
            - пересечение (И)
        - строковые операции:
            - ПоМаске
            - Пусто
            - НеПусто
        - операции сравнения:
            - больше
            - больше или равно
            - меньше
            - меньше или равно
            - равно
            - неравно
    + Составить ограничение с детейлом
    + Удалить условие
    + Очистить ограничение
    + Использовать параметр
        - добавить параметр
        - указать тип праметра
        - удалить параметр
    + Указать значение для условия
        - вручную ввести
        - выбрать параметр
        - выбрать значение из базы
* Применить ограничение
* Ввести значение параметра
* Изменить значение параметра
* Сбросить ограничение
* Сохранить ограничение
* Редактировать ограничение
* Удалить ограничение

## Программный сброс наложенного ограничения
Для программного сброса наложенного на список ограничения на серверной стороне достаточно вызвать метод

```csharp
WebObjectListView1.DropLimitFromLimitEditor();
```

## Выбор значения из базы
*Обратите внимание, что при выборе значения из базы для оптимизации отображаются только первые 100 значений, удовлетворяющих условию.* 
Существует возможность фильтрации значений из базы данных. Для этого в специальное поле вводится значение, которое должно являться подстрокой в итоговых найденных элементах.

## Поиск по маске
Существует возможность задавать ограничение по маске. Соответствующий элемент добавлен на риббон редактора. 

![](/flexberry.github.io/flexberry.github.io/images/pages/products/flexberry-aspnet/Ограничения/LimitEditor.png)

На настоящий момент наложены некоторые ограничения на шаблон:
* "*" - это 0 и более любых символов.
* "_" - это ровно 1 символ.
* Чтобы осуществлять поиск подстроки, необходимо в начале и конце строки указать символ "*". Например, для поиска подстроки, содержащей последовательность 123, нужно задать шаблон вида "*123*".
* Экранирование символов и некоторые спецсимволы на настоящий момент не поддерживаются (это связано в том числе с [ограничениями, накладываемыми использованием Regex в запросах к LinqProvider](https://flexberry.github.io/ru/fo_l-i-n-q-provider.html)).

## Подсчитать количество строк
На вкладке "Главная" есть кнопка "Подсчитать количество строк", по нажатию на которую выдается количество объектов, удовлетворяющих текущему ограничению.

>!Important
>Подсчет количества строк не учитывает наложенные на [WebObjectListView](https://flexberry.github.io/ru/web-object-list-view.html) [фильтры](https://flexberry.github.io/ru/fa_w-o-l-v-filters.html).


## Задание параметров
[Задать ограничение с параметрами](https://flexberry.github.io/ru/fa_limit-with-parameters-for-user.html)

[Типы параметров, используемых в ограничениях.](https://flexberry.github.io/ru/fa_advanced-limit-editor-parameters.html)

## Возможности редактора ограничений

* [Глобальные настройки расширенного редактора ограничений](https://flexberry.github.io/ru/fa_global-web-limit-editor-settings.html)
* [Запуск редактора ограничений без WOLV](https://flexberry.github.io/ru/fa_limit-editor-without-w-o-l-v.html)
* [Задание заголовка класса в расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_web-limit-editor-class-caption.html)
* [Работа с детейлами в расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_details-at-adv-limit-editor.html)
* [Преобразование функции ограничения в linq-ограничение](https://flexberry.github.io/ru/fo_lcs-to-linq.html)
* [Задание порядка отображения свойств в расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_set-prop-order-at-web-adv-limit-editor.html)
* [Выражения Пусто/Непусто в расширенном редакторе ограничений]()

## External-классы в расширенном редакторе ограничений
Основная статья [External-классы в расширенном редакторе ограничений.](https://flexberry.github.io/ru/fa_web-limit-editor-and-external-class.html)

## Откуда ссылаются на эту страницу

* [Параметры в Расширенном редакторе ограничений в Web](https://flexberry.github.io/ru/fa_advanced-limit-editor-parameters.html)
* [Работа с детейлами в расширенном редакторе ограничений в web](https://flexberry.github.io/ru/fa_details-at-adv-limit-editor.html)
* [Flexberry ASP.NET](https://flexberry.github.io/ru/fa_flexberry-a-s-p-n-e-t.html)
* [Глобальные настройки редактора ограничений](https://flexberry.github.io/ru/fa_global-web-limit-editor-settings.html)
* [Глоссарий](https://flexberry.github.io/ru/glossary.html)
* [Преобразование функции ограничения в linq-ограничение](https://flexberry.github.io/ru/fo_lcs-to-linq.html)
* [Упрощенный вид редактора ограничений](https://flexberry.github.io/ru/fw_limit-editor-simple-view.html)
* [Запуск Расширенного редактора ограничений без WOLV](https://flexberry.github.io/ru/fa_limit-editor-without-w-o-l-v.html)
* [Ограничения с параметрами в WOLV (для программиста)](https://flexberry.github.io/ru/fa_limit-with-parameters-for-developer.html)
* [Ограничения с параметрами (для пользователя) в Web](https://flexberry.github.io/ru/fa_limit-with-parameters-for-user.html)
* [Как задать порядок отображения свойств в расширенном редакторе ограничений в web](https://flexberry.github.io/ru/fa_set-prop-order-at-web-adv-limit-editor.html)
* [Компоненты технологии CASEBERRY]()
* [Web-контролы и web-компоненты](https://flexberry.github.io/ru/fa_web-controls.html)
* [External-классы в расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_web-limit-editor-and-external-class.html)
* [Задание заголовка класса в Расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_web-limit-editor-class-caption.html)
* [Выражения Пусто/Непусто в расширенном редакторе ограничений в Web](https://flexberry.github.io/ru/fa_web-limit-editor-is-null.html)
* [WebObjectListView (WOLV)]()

## Куда ссылается эта страница

* [Параметры в Расширенном редакторе ограничений в Web](https://flexberry.github.io/ru/fa_advanced-limit-editor-parameters.html)
* [Работа с детейлами в расширенном редакторе ограничений в web](https://flexberry.github.io/ru/fa_details-at-adv-limit-editor.html)
* [Flexberry ASP.NET](https://flexberry.github.io/ru/fa_flexberry-a-s-p-n-e-t.html)
* [Глобальные настройки редактора ограничений](https://flexberry.github.io/ru/fa_global-web-limit-editor-settings.html)
* [Преобразование функции ограничения в linq-ограничение](https://flexberry.github.io/ru/fo_lcs-to-linq.html)
* [Запуск Расширенного редактора ограничений без WOLV](https://flexberry.github.io/ru/fa_limit-editor-without-w-o-l-v.html)
* [Ограничения с параметрами (для пользователя) в Web](https://flexberry.github.io/ru/fa_limit-with-parameters-for-user.html)
* [LINQProvider](https://flexberry.github.io/ru/fo_l-i-n-q-provider.html)
* [Как задать порядок отображения свойств в расширенном редакторе ограничений в web](https://flexberry.github.io/ru/fa_set-prop-order-at-web-adv-limit-editor.html)
* [Web-контролы и web-компоненты](https://flexberry.github.io/ru/fa_web-controls.html)
* [External-классы в расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_web-limit-editor-and-external-class.html)
* [Задание заголовка класса в Расширенном редакторе ограничений](https://flexberry.github.io/ru/fa_web-limit-editor-class-caption.html)
* [Выражения Пусто/Непусто в расширенном редакторе ограничений в Web](https://flexberry.github.io/ru/fa_web-limit-editor-is-null.html)
* [WebObjectListView (WOLV)]()
* [Фильтры WebObjectListView](https://flexberry.github.io/ru/fa_w-o-l-v-filters.html)