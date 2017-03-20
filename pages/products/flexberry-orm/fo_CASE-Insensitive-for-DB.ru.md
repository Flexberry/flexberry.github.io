---
title: CASE Insensitive для БД
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, For code validate, Public, БД
toc: true
permalink: ru/fo_c-a-s-e--insensitive-for--d-b.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Сервис данных](fo_data-service.html()
* **Предназначение:** [Flexberry ORM](fo_flexberry-o-r-m.html) позволяет настроить приложение таким образом, чтобы в генерируемом [сервисом данных](fo_data-service.html) запросе применялась функция [UPPER](https://msdn.microsoft.com/ru-ru/library/ms180055.aspx) для обеих сторон выражения сравнения строк.
* **Программная библиотека:** ICSSoft.STORMNET.Business.dll

## Нечувствительность к регистру в сервисе данных

Flexberry ORM позволяет настроить приложение таким образом, чтобы в генерируемом сервисом данных запросе применялась функция `UPPER` для обеих сторон выражения сравнения строк (то есть при сравнении строк не будет учитываться, в каком регистре записаны символы).

Для того, чтобы это настроить, необходимо в конфигурационном файле указать следующий параметр:

```xml
<add key="CaseInsensitive" value="true">
```
