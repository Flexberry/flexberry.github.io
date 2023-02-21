---
title: Flexberry CLI
sidebar: guide-practical-guides_sidebar
keywords: guide, flexberry designer enterprise, enterprise, генерация, генератор, cli, flexberry-cli, установка flexberry cli
toc: true
permalink: ru/gpg_flexberry-cli.html
lang: ru
---

Flexberry CLI - консольная утилита, предназначенная для генерации кода проекта, созданного с помощью `Flexberry Designer Desktop/Online/Enterprise`.

## Установка Flexberry CLI

### Windows
На странице `Генерация` нажмите кнопку `Скачать flexberry.exe`. Запустите установщик `setup-flexberry-cli.exe`.

Откройте `Диспетчер учетных данных`. Добавьте учётные данные для подключения к порталу Flexberry Designer Enterprise.

### Linux
1. [Установить Mono](https://www.mono-project.com/download/stable/#download-lin) (пакет `mono-complete`).
2. Скачать Nuget-пакет [NewPlatform.Flexberry.Designer.CommandLine](https://www.nuget.org/packages/NewPlatform.Flexberry.Designer.CommandLine) (используйте кнопку `Download package`).
3. Распаковать содержимое папки `/tools` (из скачанного архива `.nupkg`). Для генерации используется файл `flexberry.exe`.
4. Создать alias для удобного запуска `flexberry.exe`:
    - добавить строчку `alias flexberry-cli='mono /ваш/путь/до/flexberry.exe'` в файл `~/.bashrc`.
5. alias позволит запускать генератор командой `flexberry-cli ./ваша/конфигурация.fdg` (читай  `генерация`).

## Генерация
Для генерации проекта необходимо иметь файл с настройками генерации проекта (формат `.fdg`).

Чтобы скачать этот файл, перейдите на вкладку `Генерировать` -> нажмите `Скачать fdg файл`. Содержимое файла `.fdg` имеет формат `json`, файл можно редактировать.

### Настройки генерации (.fdg файл)

| Имя настройки | Описание |
| :---: | --- |
| **ProjectId** | ID вашего проекта на портале Flexberry Designer Enterprise. |
| **CrpLocalPath** | Путь к файлу crp (создаётся при экспорте стадии) для оффлайн-генерации. |
| **OnlineCrpUrl** | Адрес портала, с которого выполняется скачивание crp файла проекта. |
| **GenPath** | Относительный путь генерации текущего проекта. |
| **BeforeGenScriptPath** | Скрипт, который выполнится перед генерацией. |
| **AfterGenScriptPath** | Скрипт, который выполнится после успешной генерации. |
| **GenerationItems** | Соответствует настройкам генерации, задаваемым на портале во вкладке `Генерация` -> `Настройки генерации`. |

### Запуск генерации
Для запуска генерации запустите утилиту `flexberry.exe`, передав ей файл конфигурации:
- `flexberry.exe ./GenConfig.fdg`

Перед генерацией Flexberry CLI автоматически скачивает стадию указанного проекта (используя ProjectId) с портала Flexberry Designer Enterprise. Если автоматическая загрузка стадии не работает (напр. в `Linux`):
- выполните экспорт проекта на вкладке `Настройка` -> `Действия с проектом` -> Я хочу выполнить `Экспорт проекта`;
- извлеките `.crp` файл из полученного архива;
- укажите путь к crp файлу (`CrpLocalPath`) в файле конфигурации.

Генерацию можно запускать через Flexberry Designer Enterprise на вкладке `Генерация` -> `Генерировать`. В этом случае Flexberry CLI будет использовать последний запущенный на локальном компьютере `.fdg` файл для текущего проекта (т.е. проект должен быть сгенерирован хотя бы раз). Запущенная таким образом генерация выполняется локально на диске с использованием установленной Flexberry CLI.

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Практическое руководство по созданию приложения с помощью Flexberry Designer Enterprise](gpg_practical-guide.md)
