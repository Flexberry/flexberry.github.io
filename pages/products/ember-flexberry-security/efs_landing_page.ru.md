---
title: ember-flexberry-security
keywords: security, audit
tags: [EmberJS]
sidebar: ember-flexberry-security_sidebar
toc: false
permalink: ru/efs_landing_page.html
folder: ember-flexberry
lang: ru
summary: Данный раздел посвящён клиентской части подсистемы полномочий и аудита изменения данных.
---

# Полномочия и аудит
Ember addon ember-flexberry-security включает в себя набор компонентов и форм для работы с данными аудита и полномочий на клиенте.

## Полномочия
* [Подсистема полномочий](efs_security.html)
* [Формы настройки полномочий](efs_security_forms.html)
* [Модель данных полномочий](efs_security_schema.html)

## Аудит изменения данных
* [Подсистема аудита](efs_audit.html)
* [Формы аудита](efs_audit_forms.html)
* [Просмотр данных аудита по объекту](efs_audit_for_object.html)

# Установка ember addon
Установка аддона выполняется командой:
```
ember install ember-flexberry-security
```
{% include note.html content=" 

Плагин ещё в разработке, но формы аудита и некоторые формы полномочий уже можно использовать.  Генератор формы полномочий не генерирует, потому что они определены в аддоне ember-flexberry-security. Программисту надо лишь задать их доступность в router.js и добавить в сайтмап (если нужно). Чтобы понять какие формы есть в аддоне ember-flexberry-security можно смотреть в его dummy-приложение [сюда](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/router.js)
Собственно, формы, роут которых начинается с `i-c-s-soft-s-t-o-r-m-n-e-t-business-audit-objects-audit` - относятся к аудиту, а `i-c-s-soft-s-t-o-r-m-n-e-t-security` - к полномочиям.
В прикладной системе надо тоже добавлять роуты форм, которые нужны.
Сайтмап будет выглядеть примерно [так](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/controllers/application.js)

Также нужно в локализации примешать [локали из аддона полномочий](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/locales/ru/translations.js)
см. `EmberFlexberrySecurityTranslations`

Чтобы реализовать логику перехода на форму просмотра аудита надо сделать так как тут:
[Hbs](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/templates/ember-flexberry-dummy-application-user-edit.hbs) 
см. `{{action 'showAudit'}}`

[controller](https://github.com/Flexberry/ember-flexberry-security/blob/develop/tests/dummy/app/controllers/ember-flexberry-dummy-application-user-edit.js)
см. ShowAuditActionControllerMixin

На бакенде, соответственно, надо опубликовать через ODataService сборку с объектами аудита и сборку с объектами полномочий. Дополнительно потребуется написать функцию для фильтрации выдаваемых значений (дефолтной технологической реализации пока нет).

" %}