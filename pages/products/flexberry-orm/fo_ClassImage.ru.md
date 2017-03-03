---
title: Ассоциирование картинок с классами данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_class-image.html
---
<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* **Продукт:** [FlexberryORM|Flexberry ORM)
* **Компонент:** [DataObject|Объект данных)
* **Программная библиотека:** ICSSoft.STORMNET.DataObject.dll
* **Предназначение:** Описан механизм, позволяющий ассоциировать с [dataobject|классом данных) изображение.
</td>
</tr></tbody></table></a>
</div>

## Ассоциирование картинок с классами данных

С [классом данных](fo_data--classes) может быть ассоциировано изображение.

* `ClassImageFile`. Если требуется всегда указывать одно и то же изображение для различных экземпляров класса данных, следует поместить это изображение в сборку как встроенный (`embedded`) ресурс и указать атрибут `ClassImageFile`.
* `ClassImageProperty`. Если изображение у различных экземпляров класса данных может быть различным, используется атрибут `ClassImageProperty`, указывающий свойство класса данных, возвращающее изображение `System.Drawing.Image`.
* `ExternalImageProvider`. Картинки могут быть предоставлены и некоторым внешним провайдером (атрибут `ExternalImageProvider`).

Кроме того, что картинка отображается в пользовательском интерфейсе (например, контролы для работы с группой объектов), разработчик может получить:

* картинку для конкретного объекта данных методом `[Information](InformationClassAsMetadataSupervisor).[GetImageForInstance](InformationClassAsMetadataSupervisor#GetImageForInstance)`, 
* картинку для класса методом `[InformationClassAsMetadataSupervisor|Information).[InformationClassAsMetadataSupervisor#GetClassImage|GetClassImage)`, 
* имя свойства, предоставляющее картинку, методом `[InformationClassAsMetadataSupervisor|Information).[InformationClassAsMetadataSupervisor#GetClassImageProperty|GetClassImageProperty)`.

{% include note.html content="В свойствах сборки с картинкой должен быть указан правильный `Default Namespace` (соответствующий пространству имён того класса, к которому привязывается картинка). %}








