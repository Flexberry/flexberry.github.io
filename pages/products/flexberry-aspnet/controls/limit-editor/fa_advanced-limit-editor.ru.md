---
title: Расширенный редактор ограничений в WEB
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы), Ограничения
toc: true
permalink: ru/fa_advanced-limit-editor.html
folder: products/flexberry-aspnet/limit-editor/
lang: ru

---

## Список пользовательских функций

* Открыть редактор
* Закрыть редактор (*ситуации когда он закрывается сам)
* Создать ограничение
* Создать ограничение на основе текущего
* Составить ограничение
    * Добавить условие
        * объединение (ИЛИ)
        * пересечение (И)
        * отрицание (НЕ)
    * Изменить операцию в условии
        * логические операции:
            * объединение (ИЛИ)
            * пересечение (И)
        * строковые операции:
            * ПоМаске (По маске)
            * Пусто
            * НеПусто (Заполнено)
        * операции сравнения:
            * больше
            * больше или равно
            * меньше
            * меньше или равно
            * равно
            * неравно
    * Составить ограничение с детейлом
    * Удалить условие
    * Очистить ограничение
    * Использовать параметр
        * добавить параметр
        * указать тип праметра
        * удалить параметр
    * Указать значение для условия
        * вручную ввести
        * выбрать параметр
        * выбрать значение из базы
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

*При выборе значения из базы для оптимизации отображаются только первые 100 значений, удовлетворяющих условию.* 
Существует возможность фильтрации значений из базы данных. Для этого в специальное поле вводится значение, которое должно являться подстрокой в итоговых найденных элементах.

## Поиск по маске

Существует возможность задавать ограничение по маске. Соответствующий элемент добавлен на риббон редактора. 

![](/images/pages/products/flexberry-aspnet/limit-editor/limit-editor.png)

На настоящий момент наложены некоторые ограничения на шаблон:
* "*" - это 0 и более любых символов.
* "_" - это ровно 1 символ.
* Чтобы осуществлять поиск подстроки, необходимо в начале и конце строки указать символ "*". Например, для поиска подстроки, содержащей последовательность 123, нужно задать шаблон вида "*123*".
* Экранирование символов и некоторые спецсимволы на настоящий момент не поддерживаются (это связано в том числе с [ограничениями, накладываемыми использованием Regex в запросах к LinqProvider](fo_linq-provider.html)).

## Подсчитать количество строк

На вкладке `Главная` есть кнопка `Подсчитать количество строк`, по нажатию на которую выдается количество объектов, удовлетворяющих текущему ограничению.

{% include note.html content="Подсчет количества строк не учитывает наложенные на [WebObjectListView](web-object-list-view.html) [фильтры](fa_wolv-filters.html)." %}


## Задание параметров

[Задать ограничение с параметрами](fa_limit-with-parameters-for-user.html)

[Типы параметров, используемых в ограничениях.](fa_advanced-limit-editor-parameters.html)

## Возможности редактора ограничений

* [Глобальные настройки расширенного редактора ограничений](fa_global-web-limit-editor-settings.html)
* [Запуск редактора ограничений без WOLV](fa_limit-editor-without-wolv.html)
* [Задание заголовка класса в расширенном редакторе ограничений](fa_web-limit-editor-class-caption.html)
* [Работа с детейлами в расширенном редакторе ограничений](fa_details-at-adv-limit-editor.html)
* [Преобразование функции ограничения в linq-ограничение](fo_lcs-to-linq.html)
* [Задание порядка отображения свойств в расширенном редакторе ограничений](fa_set-prop-order-at-web-adv-limit-editor.html)
* [Выражения Пусто/Непусто в расширенном редакторе ограничений]()

## External-классы в расширенном редакторе ограничений

Основная статья [External-классы в расширенном редакторе ограничений.](fa_web-limit-editor-external-class.html)