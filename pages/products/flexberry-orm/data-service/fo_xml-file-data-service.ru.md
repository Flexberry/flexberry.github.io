---
title: XMLFileDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_xml-file-data-service.html
folder: products/flexberry-orm/
lang: ru
---

## XMLFileDataService

`XMLFileDataService` - сервис данных, предназначенный для работы с базой данных в формате XML. Для корректной работы сервиса данных необходима XSD-схема базы данных и XML-файл базы данных. Получить их можно воспользовавшись [XMLSchemaGenerator](fd_x-m-l-schema-generator.html) (модулем расширения [Flexberry Designer](fd_flexberry-designer.html) для генерации XSD-схемы базы данных).

Для работы с данными в формате XML используется `System.Data.DataSet` поддерживающий транзакции и SQL-запросы. Также `DataSet` поддерживает ограничения ссылочной целостности. Для работы `XMLFileDataService` необходимо включать ограничения ссылочной целостности. Включение ограничений ссылочной целостности осуществляется посредством включения свойства `EnforceConstraints`. [XMLSchemaGenerator](fd_x-m-l-schema-generator.html) по умолчанию это свойство проставляет в `true`.

В качестве строки подключения указывается путь до `XSD` и `XML`-файлов без указания расширения (оба файла должны иметь одинаковое имя).

К примеру, в папке `C:\DataBase\` лежат файлы `base.xsd` и `base.xml`, тогда путь будет выглядеть следующим образом: `C:\DataBase\base`