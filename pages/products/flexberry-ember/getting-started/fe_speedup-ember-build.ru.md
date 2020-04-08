---
title: Ускорение сборки EmberJS-приложений
keywords: ember-cli, build, speedup, optimization, оптимизация
tags: [EmberJS]
sidebar: flexberry-ember_sidebar
toc: true
permalink: ru/fe_speedup-ember-build.html
lang: ru
summary: Рекомендации по настройке окружения для оптимизации скорости сборки EmberJS-приложений.
---

## Рекомендации по выбору операционной системы для ember-cli

Если есть возможность выбрать операционную систему на которой будут разрабатываться ember-приложения, то рекомендуется использовать один из современных Linux-дистрибутивов. В этой операционной системе сборка выполняется максимально быстро благодаря особенностям файловой системы ([источник](https://levelup.gitconnected.com/working-with-front-end-tools-on-linux-and-windows-the-grand-performance-test-b51a77a71636)).

## Рекомендации по настройке окружения для сборки под ОС Microsoft Windows

Если сборка проекта занимает больше 1-3 минут, то попробуйте воспользоваться следующими рекомендациями.

1. Следует добавить каталог с проектами на EmberJS в исключения для Windows Defender или любых других антивирусов. Во время сборки обрабатываются десятки тысяч небольших js-файлов.
2. Для каталога с проектами на EmberJS отключить индексацию. В свойствах каталога `Общие` -> `Атрибуты` -> `Другие` -> `Атрибуты индексирования и архивации` -> снять галку `Разрешить индексировать содержимое файлов в этой папке в дополнение к свойствам файла`.
3. Рекомендуется использовать консоль с правами администратора. В консоли с правами администратора поддерживаются символьные ссылки (symlink), что даёт ощутимый прирост скорости сборки.
4. Рекомендуется использовать SSD-диск вместо HDD, в т.ч. для папки с кешами npm, yarn, bower. В случае использования `npm link` или `yarn link` убедитесь, что все файлы, участвующие в сборке будут находиться на SSD.
5. Можно настроить RAM-Disk для устройств без SSD, но с достаточным объёмом оперативной памяти.
6. Отключить тестирование (tests: false) и проверку стиля написания кода (code style) (hinting: false) при сборке приложения. Сделать это в файле ember-cli-build. В тестировании при сборке особой необходимости нет, а ввиду большой кодовой базы тестирование занимает существенный объём времени. Запускать тестирование явно командой `ember test`.
7. В качестве меры, которая почему-то работает - удалить папки `node_modules` и `tmp`, а также файл `yarn.lock`, после чего переустановить пакеты и собрать приложение ([источник](https://github.com/ember-cli/ember-cli/issues/6921)). Удаление папки `tmp` актуально для версий < 2.13 (не точно), в более старших она сама очищается.
8. Проанализировать что в вашем конкретном проекте занимает максимальное время при сборке и попробовать побороть эту конкретную проблему. Долгое время сборки - это не ещё приговор, а интересная техническая задача!

## Дополнительные материалы

* [Windows support - Appendix - Ember CLI Guides](https://cli.emberjs.com/release/appendix/windows/)
* [Tips for improving build time of large apps](https://discuss.emberjs.com/t/tips-for-improving-build-time-of-large-apps)
* [How we speed up testing and building process of our Ember app at Brandnew? ~2.5x faster!](https://medium.com/@tommaqs/how-we-speed-up-testing-and-building-process-of-our-ember-app-at-brandnew-2-5x-faster-299dd4995a97)
* [Build performance](https://github.com/ember-cli/ember-cli/blob/master/docs/perf-guide.md)
* [How we cut down our ember build time?](https://www.gokatz.me/blog/how-we-cut-down-our-ember-build-time)
