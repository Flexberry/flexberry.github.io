---
title: Форма задания ограничений
sidebar: flexberry-winforms_sidebar
keywords: Ограничения
summary: Описано внутреннее устройство формы AdvLimitComponent
toc: true
permalink: ru/fw_limitation-editform.html
folder: products/flexberry-winforms/
lang: ru
---

## Общее внутреннее устройство формы
### AdvLimitComponent

Программист видит AdvLimitComponent, который располагается на форме вместе со списковым контролом [ObjectListView](fw_objectlistview.html). Этот компонент добавляет на тулбар ObjectListView кнопку, которая позволяет управлять накладываемыми ограничениями. Для иерархических списков AdvLimitComponent автоматически не генерируется, но его можно добавить вручную. Главным параметром AdvLimitComponent является списковый контрол, к которому будет привязан этот компонент.

### EditAdvLimitDialog

Компонент, который позволяет вызвать форму задания ограничения из произвольного места (не только со списка).

Параметры диалога задаются при помощи свойства EditAdvLimitDialog.DialogParameters типа EditAdvLimitDialog.DialogParams.

Настроить при помощи этого свойства можно следующее:

| Свойство | Описание|
|--|--|
| `DoNotShowStandardTab` | Не показывать вкладку стандартного ограничения
| `DoNotShowSimpleTab` | Не показывать вкладку простого ограничения
| `DoNotShowParametersArea` | Не показывать область задания параметров
| `DoNotShowLimitArea` | Не показывать область задания ограничения
| `DoNotSaveEmptyLimit` | При попытке сохранить пустое ограничение выдавать сообщение о невозможности данной операции
| `DisableLimitName` | Сделать недоступным ввод имени ограничения
| `ApplyBtnText` | Текст на кнопке применения ограничения
| `ApplyLimitButtonImage` | Картинка на кнопке применения ограничения
|||


### EditAdvansedFilter1

Форма расширенного редактора ограничений. Включает обработку редактирования параметров ограничения и встроенные контролы для редактирования самого ограничения.

### AdvansedLimit

Объектная модель ограничения, редактируемого на форме задания ограничений. Включает в себя как ограничивающую функцию, так и определения параметров.

### STORMAdvLimit

Объектное представление сохраняемого в базе ограничения. Не содержит логики по десериализации. Сам класс знает только сериализованное Value. Десериализуется в своё нехранимое свойство AdvLimit при помощи AdvLimitComponent. Используется только для чтения-записи в БД (наследник от [DataObject](fo_data-object.html)).

### DataObjectTypeCreator

Служебный класс, который позволяет в динамике создать типы. Эти типы используются на кастом-форме задания параметров ограничений.

### ExpressionBox

Контрол для редактирования выражения в ограничении.

## Полезные ссылки

[Ограничение на себя](fw_self-limit.html), <BR>
[Упрощенный вид редактора ограничений](fw_limit-editor-simple-view.html), <BR>
[Стандартный вид редактора ограничений](fw_standart-view-limits-editor.html), <BR>
[Наложение ограничений по столбцам в списках](fw_nalozhenie-ogranichenij-po-stolbcam-v-spiskah.html), <BR>
[Копирование / вставка ветки ограничений (сохранение и загрузка из файла))](fw_copy-paste-limitation-branch.html), <BR>
[Мастера в списке выбора типа параметров в редакторе AdvLimit](fw_masters-in-list-selection-type-parameters-in-advlimit.html),<BR>
[Функция импликации при задании ограничений](fo_function-implication.html),<BR>
[Функции для работы с датой при задании ограничений](fw_date-time-funtions-in-limits.html) (пример использования [здесь](fw_date-limits-standart-view.html))<BR>
[Сохранение ограничений на форме задания ограничения](fo_limit-function-serialization.html)
