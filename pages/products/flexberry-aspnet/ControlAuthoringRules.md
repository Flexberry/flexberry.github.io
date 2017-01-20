---
title: Правила создания контролов
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/control-authoring-rules.html
folder: product--folder
lang: ru
---

## Подключение внешних файлов
# Все скрипты и стили должны подключаться в `OnLoad`.
# Контрол должен самостоятельно подключать __все__ используемые JS библиотеки (jQuery, ...).