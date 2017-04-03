---
title: LookUp'ы
sidebar: flexberry-aspnet_sidebar
keywords: JavaScript API, Windows UI (Контролы)
toc: true
permalink: ru/fa_lookup-overview.html
folder: products/flexberry-aspnet/
lang: ru
---



## Что такое LookUp?
`LookUp` (действие) - выбор мастерового объекта (проставление ссылки на мастеровой объект).

`LookUp` (контрол) - контрол для выбора мастерового объекта, возвращающий ссылку на него.

![](/images/pages/img/page/LookUp-Overview/LookUp.jpg)


## Свойства LookUp'ов (Win)



## Свойства LookUp'ов (Web)
* `ColumnsSort` - сортировка по колонкам в [WOLV](fa_web-object-list-view.html) на открываемой форме.
* `ConnStrName` - строка подключения к базе.
* `EditedProperty` - свойство редактируемого объекта.
* `EnablePostBack` - включить ли обновление страницы после выбора значения.
* `LimitFunction` - ограничивающая функция, накладывает ограничение на список на поднимаемой на лукап форме. Автоматически пропишется `LookUpFormLFName` при установке.
* `LookUpFormCaption` - заголовок формы, поднимаемой на лукап. По умолчанию `Выбор значения`.
* `LookUpFormCountOnPage` - Количество строк [WOLV](fa_web-object-list-view.html) на форме выбора значения лукапа.
По умолчанию количество строк [WOLV] на базовой лукап-форме будет определяться на основе сохраненных настроек пользователя или значения по умолчанию в [Pager](fa_web-object-list-view.html). Такое поведение может быть переопределено в прикладной лукап-форме.
Если установлено конкретное значение, то количество строк в списке при открытии формы будет всегда равно указанному. При установке значения следует учитывать, что [Универсальный пейджинговый контрол. Pager.](pager.html) использует белый список возможных значений количества отображаемых объектов.
* `LookUpFormHeight` - высота формы, поднимаемой на лукап. По умолчанию `600`.
* `LookUpFormLeft` - левая граница открываемой формы. По умолчанию `100`.
* `LookUpFormLocation` - отображать ли в браузере адресную строку при открыти на лукап. По умолчанию не отображается.
* `LookUpFormMenubar` - отображать ли меню в браузере при открытии на лукап. По умолчанию не отображается.
* `LookUpFormResizable` - давать ли возможность изменять размер открываемой на лукап формы. По умолчанию возможность есть.
* `LookUpFormScrollbars` - отображать ли скроллбары на открываемой форме. По умолчанию отображаются.
* `LookUpFormStatus` - отображать ли строку состояния на открываемой форме. По умолчанию не отображаются.
* `LookUpFormToolbar` - отображать ли панель браузера (кнопки "Вперед", "Назад" и пр.). По умолчанию не отображаются.
* `LookUpFormTop` - верхняя граница открываемой формы. По умолчанию `100`.
* `LookUpFormURL` - URL списковой формы, которая будет использоваться для выбора значения.
* `LookUpFormWidth` - ширина открываемой формы. По умолчанию `750`.
* `[#mastertypename|MasterTypeName]` - тип [мастерового](fo_master-association.html) объекта. Устанавливается при помощи `typeof(Тип).AssemblyQualifiedName`.
* `[#masterviewname|MasterViewName]` - представление мастерового объекта.
* `[#objecttypename|ObjectTypeName]` - тип редактируемого объекта. Устанавливается при помощи `typeof(Тип).AssemblyQualifiedName`.
* `[#objectviewname|ObjectViewName]` - [представление](fo_view-def.html) редактируемого объекта.
* `[#propertytoshow|PropertyToShow]` - свойство мастера, которое будет отображаться в лукапе при выбранном объекте.
* `SelectedMasterPK` - [PrimaryKey](fo_primary-keys-objects.html) выбранного объекта.
* `ShowInThickBox` - показывать форму лукапа в thickbox или нет.
* `ShowObjectUrl` - URL, по которому будет подниматься форма просмотра для выбранного объекта.
ApplicationUrl + ListFormURL. Если не указана, то используется стандартная форма.



## Наложение ограничений на LookUp'ы
Основная статья: [Наложение ограничений на LookUp](fa_lookup-limit-web.html)


## Стилизация иконок кнопок у LookUp'а
Основная статья: [Стилизация иконок кнопок LookUp](fa_lookup-stylization.html)


## Наиболее интересные свойства LookUp'ов (Web)

[#propertytoshow|PropertyToShow]
[#masterviewname|MasterViewName]
[#mastertypename|MasterTypeName]
[#objecttypename|ObjectTypeName]
[#objectviewname|ObjectViewName]

Ниже они описываются подробно.


### PropertyToShow

[anchor|#propertytoshow]

Отвечает за отображаемое свойство __мастера__ после выбора лукапа.

Не работает при включеном `[AutoComplete](predict-input-web.html)`.

Устанавливается в методе `PostApplyToControls` или `PostLoad` формы редактирования. Если установите в методах, срабатывающих раньше, то изменения перезапишутся WebBinder'ом.

Принимает составные свойства (свойства мастеров). К примеру, можно указать:
```
ctrlКлиент.PropertyToShow = "Прописка.Город";
```

Таким образом, при выборе мастера `Клиент` будет отображаться свойство `Клиент.Прописка.Город`.



### MasterViewName

[anchor|#masterviewname]

Устанавливает представление, которое будет использоваться на открываемой форме лукапа.

В отличии от `PropertyToShow`, может устанавливаться в любом месте и не зависит от `AutoComplete`.


### MasterTypeName

[anchor|#mastertypename]

Устанавливает тип объекта, который необходимо выбрать на LookUp. Актуально, когда мастеровой объект связан наследованием.

Вкратце: если есть два класса, связанных наследованием (класс A наследуется от класса B), и необходимо, чтобы на LookUp открывался класс А (а по умолчанию открывается класс B), то необходимо установить `MasterTypeName` следующим образом:
```
ctrlM1.MasterTypeName = typeof(M1).AssemblyQualifiedName;
```
не забыв при этом установить `MasterViewName`

(((<msg type=Important>Для означивания `MasterTypeName` всегда используйте `typeof(Тип).AssemblyQualifiedName`.

Подробнее можно посмотреть '''[здесь](fo_type-usage-problem.html)'''.</msg>)))


### ObjectTypeName

[anchor|#objecttypename]

Устанавливает тип объекта, для которого устанавливается мастер.

Аналогично `MasterTypeName`, актуально для случаев наследования, но на сей раз в наследовании участвует базовый объект, а не мастер. Устанавливается в паре с `ObjectViewName`.


### ObjectViewName

[anchor|#objectviewname]

Устанавливает представление базового объекта.

Аналогично `MasterViewName`, актуально для случаев наследования, но на сей раз в наследовании участвует базовый объект, а не мастер. Устанавливается в паре с `ObjectTypeName`.


## Полезные ссылки
* [Как вызвать на LookUp](look-up--how-to-use.html)
* [Поднятие из GroupEdit'a на лукап объекта, не имеющего ссылку на объект-инициатор](look-up-from--group-edit.html)
* ['Размазывание' по Control'ам информации с LookUp'а](look-up-fill-information.html)
* [Изменение внешнего вида контрола LookUp](look-up-appearance.html)
* [Создание третьей кнопки рядом с LookUp'ом](look-up-third-button.html)
* [Если не работает LookUp](look-up-does-not-work.html)
* [Произвольный контрол в качестве лукапа](generate-lookup--i-lookup.html)
* [Предиктивный ввод](прикладные-системы_predict-input.html)
* [Настройки для LookUp в AGE](fa_settings-lookup-age.html)
* [Ограничение для LookUp в AGE](fa_limited-lookup-age.html)
