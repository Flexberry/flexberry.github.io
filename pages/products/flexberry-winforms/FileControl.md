---
title: FileControl (Winforms)
sidebar: product--sidebar
keywords: Flexberry Winforms, Windows UI (Контролы)
toc: true
permalink: ru/file-control.html
folder: product--folder
lang: ru
---


# FileControl (Winforms)
FileControl - это контрол для работы с файлами, дающий следующую функциональность:
* выбор файла из каталога (при этом создаётся копия содержимого файла, с которым и работает приложение).
* сохранение файла в каталог.
* удаление файла.
* запуск файла (файл открывается в ассоциированном с ним приложении).

![](/images/pages/img/page/FileControl/FileControl.png)

# Как подключить к проекту FileControl
Для работы FileControl необходима ICSSoft.STORMNET.FileType.dll (есть в стандартной поставке Flexberry). 

Чтобы подключить FileControl к проекту, необходимо выполнить следующее: 

1. Определить на диаграмме классов класс File [со стереотипом "typedef"](classes-with-stereotype--typedef.html). 

2. Настроить карту типов (открыть карту типов можно, например, так: .NET CSharp -> Редактировать -> Карта типов), добавив строку:

(((<msg type=Important>В версиях Flexberry до 14.11.2013 маппинг должен выглядеть следующим образом:
{| border="1" 
| File || ICSSoft.STORMNET.FileType.File || ICSSoft.STORMNET.FileType.dll 
|}

В версиях начиная с 14.11.2013 маппинг выглядит вот так:
{| border="1" 
| File || ICSSoft.STORMNET.FileType.File || ICSSoft.STORMNET.UserDataTypes.dll 
|}

Сборка '''ICSSoft.STORMNET.FileType.dll''' исключена из билда Flexberry.
</msg>)))
3. Настроить карту отображения типов (открыть карту отображения типов можно, например, так: MSSQLServer Direct Generator -> Редактировать -> Карта типов), добавив строку:

{| border="1" 
| File || TEXT
|}


# Свойства и методы FileControl
## Как показать/скрыть кнопки на FileControl
У контрола есть свойства, позволяющие показывать/скрывать кнопки изменением их на false или true.
```cs
ctrlФайл.HideOpenButtons = false; //показать кнопку выбора файла из каталога
ctrlФайл.HideSaveButtons= false; //показать кнопку сохранения файла в каталог
ctrlФайл.HideDeleteButtons = false; //показать кнопку удаления файла
ctrlФайл.HideStartButtons = false; //показать кнопку запуска файла (открытия в ассоциированном приложении)
```
По умолчанию все кнопки на контроле на форме редактирования скрыты.

## Другие свойства и методы
{| border="1"
! Свойство !! Тип !! Описание
|-
| `ButtonChooseFileFromFolder` || `Button` || Кнопка выбора файла из каталога
|-
| `ButtonSaveFileToFolder` || `Button` || Кнопка сохранения файла в каталог
|-
| `ButtonDelete` || `Button` || Кнопка удаления файла
|-
| `ButtonOpenFile` || `Button` || Кнопка запуска файла (открытия в ассоциированном приложении)
|-
| `GetDisplayValue` || `string` || Получение отображаемого значения для поля [GroupEdit](group-edit.html), с которым связан контрол; предусмотрена возможность пустых значений
|-
| `InnerFile` || `MemoryStream` || Поле, где хранится файл без zip-архивации
|-
| `ToolTipControl` || `ToolTip` || Контрол, отвечающий за тултипы
|-
| `Value` || `object` || Поле, где хранится файл с zip-архивацией
|}

{| border="1"
! Метод !! Тип возвращаемого значения !! Описание
|-
| `GetDisplayValue` || `string` || Получение отображаемого значения для поля [GroupEdit](group-edit.html), с которым связан контрол; предусмотрена возможность пустых значений
|}

# FileControl и формы списка


(((
<msg type=important>Не рекомендуется включать поля, обрабатываемые с помощью FileControl, в [Key-Concepts-Flexberry-FRAMEWORK|представления], обрабатываемые на [форме списка](Формы-списка-классы-со-стереотипом-listform.html), поскольку в этом случае при просмотре списка в память грузятся все файлы (кроме того, не гарантируется, что отображаемое имя файла будет "осмысленным").</msg>
)))


# Изменение открытых через FileControl файлов
Если в открытые через FileControl файлы внести изменения во внешней программе (не в ту версию, что лежит в каталоге, откуда взят файл, а ту, что находится в FileControl), то файл в FileControl автоматически обновится.

----