---
title: Правила создания контролов
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_control-authoring-rules.html
folder: products/flexberry-aspnet/
lang: ru
---

## Подключение внешних файлов
# Все скрипты и стили должны подключаться в `OnLoad`.
# Контрол должен самостоятельно подключать __все__ используемые JS библиотеки (jQuery, ...).