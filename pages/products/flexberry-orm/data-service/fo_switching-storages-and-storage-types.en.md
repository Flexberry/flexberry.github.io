---
title: Как сменить тип хранилища
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: en/fo_switching-storages-and-storage-types.html
folder: products/flexberry-orm/
lang: en
---

## Этот пример показывает, как использовать СУБД Postgres в качестве хранилища.

* Мы надеемся, что вы установили Postgres. Если нет, то посетите [http://www.postgresql.org/]();
* Создайте базу данных, выполнив скрипт "FlexberryORM\Database\POSTGRES\create.sql";
* Затем откройте app.config и найдите секцию appSettings;
* Закомментируйте опции DataServiceType and CustomizationStrings, затем переименуйте опции DataServiceType_POSTGRE и CustomizationStrings_POSTGRE в DataServiceType и CustomizationStrings;
* Измените опцию CustomizationStrings для правильного подключения к вашему серверу и базе данных;
* Запустите пример, чтобы убедиться, что он работает: попробуйте проверить какие-либо примеры с созданием и загрузкой объектов данных.
