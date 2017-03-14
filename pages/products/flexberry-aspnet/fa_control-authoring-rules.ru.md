---
title: Правила создания контролов
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_control-authoring-rules.html

---

## Подключение внешних файлов

1. Все скрипты и стили должны подключаться в `OnLoad`.
2. Контрол должен самостоятельно подключать **все** используемые JS библиотеки (jQuery, ...).