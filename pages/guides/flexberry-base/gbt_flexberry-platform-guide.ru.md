---
title: Устаревший учебник программиста Flexberry Platform
sidebar: guide-base-tech_sidebar
keywords: Учебники
toc: true
permalink: ru/gbt_flexberry-platform-guide.html
lang: ru
---

__Учебник программиста__ - это азы программирования на Flexberry Platform. Тут собраны основные моменты, касающиеся данной технологией. Рекомендуется к прочтению новыми сотрудниками при поступлении на работу.

## Оглавление:

# [Общие концепции](general-concepts.html)

# Объекты данных

* [Структура классов объектов данных]()

## Оглавление:
* [Общие концепции](general-concepts.html)

## Объекты данных

* [Конструирование объектов данных](construction--data-objects.html)
* [Создание объекта данных на основе другого (прототипизация)](data-object-prototype.html)
* [Статус и состояние загрузки объекта данных](fo_object-status.html)
* [Блокирование объекта данных](blocking-object-data.html)
* [Преобразование типа свойства объекта данных к типу хранилища](fo_convert-type-property.html)
* [Нехранимые (вычислимые) свойства объекта данных](fo_not-stored-attributes.html)
* [Контекстно-зависимый кэш объектов данных](context--sensitive--cache--data--objects.html)
* [Первичные ключи объектов](fo_primary-keys-objects.html)
* [Хранение объектных данных в реляционной базе данных](fo_storing-data-objects.html)
* [Перехват событий ObjectListView (создание, удаление, изменение объекта), выполнение действий](interception-events--object-list-view.html)
* [Получение метаданных через Information](fo_methods-class-information.html)
* Обновление связанных объектов
* Функциональность при работе с массивами детейловых объектов (DetailArray)
* [Межформенное взаимодействие при редактировании объектов](interaction-between-forms-when-editing-objects.html)
* [Как без использования генераторов кода сделать на форме список объектов данных](make-a-list-of-data-objects-without-generators.html)
* [Как редактировать объекты данных на формах, связывание полей ввода со свойствами объекта данных ](edit--data-objects-on--forms.html)
* [Как «закрутить» объект данных в строку XML и восстановить обратно](aggregating-function.html)

# Атрибуты объектов

* [Доступ к собственным атрибутам объекта и атрибутам связанных объектов](fo_own-object-attributes.html)
* [Динамические атрибуты объектов](dynamic-properties.html)

# Сервисы данных

* [Обработка статуса и состояния загрузки объекта сервисами данных](fo_processing-status-condition-load.html)
* [Стандартные сервисы данных Flexberry Platform](standard-data-services.html)
* [Конструирование/получение сервиса данных](fo_construction-ds.html)
* [Отработка пользовательских операций в процессе работы сервиса данных (интеграция с бизнес-сервером)](fo_user-operations-dataservice.html)
* [Как реализовать собственный сервис данных](implement-a-custom--data-service.html)
* Дополнительные возможности, предоставляемые SQLDataService
* [Обертки и специализированный бизнес-фасад для сервисов данных](wraps-and-specialized-business-facade-for-data-services.html)

# Представление

* [Определение представления](fd_view-definition.html)
* [Скрытые свойства в представлении](hidden--properties--in--view.html)
* [Операции с представлениями](view--operations.html)
* [Адаптивные представления для детейлов](adaptive-views-for-details.html)
* [Как проверить, удовлетворяет ли некоторый объект представлению](test-object-for-viewing.html)

# Обработка объектов

* [Обработка одного объекта](processing-one-object.html)
* [Обработка множества объектов (в т.ч. и разнотипных)](fo_processing-multiple-objects.html)

# Приложение и рабочий стол

* [Приложение и рабочий стол](app-desktop.html)
* [Запуск независимых форм с рабочего стола](running-independent-forms-from-the-desktop.html)
* [Запуск независимых форм без рабочего стола (из кода)](running-independent-forms-without-desktop.html)
* [Запуск произвольной зависимой формы с рабочего стола](running-any-dependent-forms-from-the-desktop.html)

# Чтение объектов

* [Чтение принадлежащих различным классам объектов в одном представлении](reading-several-types-objects.html)
* [Чтение объектов с наложенным ограничением](Чтение-объектов-с-наложенным-ограничением.html)
* [Порционное чтение](fo_reading-portion.html)
* [Настройка параметров чтения ](fo_loading-customization-struct.html)

# Интерфейсы

* [Интерфейсы форм редактирования](interfaces--edit-forms.html)
* [Реализация интерфейсов независимыми и зависимыми формами и отношения между ними](implementation-interfaces-independent-and-dependent-forms-and-relationship-between-them.html)
* [UI-независимый пользовательский интерфейс](u-i-independent-user-interface.html)
* [Локализация пользовательского интерфейса](localization--u-i.html)

# Настройки приложения

* [Установка и конфигурирование заглушки COM+](Установка-и-конфигурирование-заглушки--c-o-m.html)
* [Установка и конфигурирование заглушки IISHosted](Установка-и-конфигурирование-заглушки--i-i-s-hosted.html)
* [Конфигурирование бизнес-фасадов из настроек приложения ](configuring-business-facades-of-application-settings.html)
* [Бизнес-серверы, обёртки, бизнес-фасад](fo_bs-wrapper.html)
* [Настройка контролов, понятие провайдера контролов, стандартный провайдер контролов](control-provider-winforms.html)
* [Настройка отладочных пакетов и выбор активного отладочного пакета](visual-studio-design-packages.html)
* [Подсистема настроек](setting-manager.html)

# Прочие возможности

* Определение атрибутов хранения
* [Проблема используемых типов](fo_type-usage-problem.html)
* [Отображение TypeUsage в структуру данных](fo_type-usage.html)
* [ObjectListView, основная функциональность в RunTime](object-list-view-basic-functionality-in--run-time.html)
* [LookUp'ы](fa_lookup-overview.html)
* [Универсальная форма редактирования ](Универсальная-форма-редактирования.html)
* [Поддержка схем Windows XP](support-schemes--windows--x-p.html)
* [Сервис блокировок](lock-service.html)
* [Создание и подключение монитора задач](fo_creating-connection-bt-monitor.html)
* [Подсистема полномочий](efs_right-manager-module.html)
* [Прочие возможности](class-image.html)
* [Перечислимые типы данных](fd_enumerations.html)
* [Если что-либо «глючит» в Design-Time](design--time--errors.html)
