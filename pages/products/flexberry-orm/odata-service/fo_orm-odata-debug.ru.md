---
title: Отладка OData-контроллеров
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM ODataService, OData
summary: Что необходимо сделать для отладки OData-контроллеров
toc: true
permalink: ru/fo_orm-odata-debug.html
lang: ru
---

## Порядок действий для отладки кода OData от Microsoft

Для того, чтобы отладить методы контроллеров, реализующих протокол OData, необходимо 
1. Склонировать технологический форк [исходников OData](https://github.com/Flexberry/WebApi) нужной версии
2. Выполнить билд в конфигурации Debug в Visual Studio
3. Подложить эти сборки и отладочные символы для них в папку `\packages\Microsoft.AspNet.OData.x.x.x\`
4. Собрать и запустить под отладкой прикладной проект
5. При запуске может возникнуть ошибка `Could not load file or assembly 'AssemblingX, Version=1.0.5000.0, Culture=neutral, PublicKeyToken=xxxxxxxx' or one of its dependencies. Strong name validation failed`. Исправить эту ошибку можно, выполнив команду как рекомендовано [тут](https://stackoverflow.com/a/33784222). В случае со сборкой `System.Web.OData` команда может выглядеть так: `sn -Vr System.Web.OData,31bf3856ad364e35`

После выполнения описанных действий должна быть возможность остановки на брейкпоинтах внутри кода, содержащегося в библиотеке Microsoft.