---
title: Использование Liquibase в Azure Pipelines
keywords: liquibase, azure, pipelines
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase_azure.html
lang: ru
---

### Использование Liquibase в Azure Pipelines
Чтобы запускать команды Liquibase в Azure Pipelines, необходимо иметь агента сборки с установленным Liquibase CLI. Если такого агента у вас нет, есть альтернативное решение. В момент сборки скрипты необходимо копировать на удалённый сервер по ssh, затем через ssh запускать необходимые команды Liquibase (Liquibase должен быть предварительно установлен на удалённом сервере, либо используйте вариант с Docker). YAML конвеер для запуска Liquibase команд может выглядеть так:
```yaml
# Пайплайн позволяет запускать команды liquibase на удалённом сервере через ssh.

# Параметры, которые необходимо задать в Azure Pipelines:
# - liquibaseContext - контекст, для которого будут выполнены команды liquibase (если не указывать, команды выполнятся на всех контекстах).

pool:
  name: FastAgents
steps:

# Этап 1. Подготовка архива со скриптами для отправки на удалённый сервер:
- task: CopyFiles@2
  displayName: 'Подготовка скриптов'
  inputs:
    # отбираем только файлы скриптов:
    Contents: |
      **/.gitkeep
      **/*.*sql
      **/*liquibase.json
      liquibase.env
      liquibase.properties
      liquibase.sh
    TargetFolder: '$(Build.BinariesDirectory)/$(Build.BuildId)/code'
    CleanTargetFolder: true
# Архивируем скрипты, т.к. архивы отправляются значительно быстрее:
- task: ArchiveFiles@2
  displayName: 'Архивирование скриптов'
  inputs:
    rootFolderOrFile: '$(Build.BinariesDirectory)/$(Build.BuildId)/code'
    includeRootFolder: false
    archiveType: 'tar'
    archiveFile: '$(Build.BinariesDirectory)/$(Build.BuildId)/archive.tar.gz'
    replaceExistingArchive: true

# Этап 2. Отправляем архив со скриптами на удалённый сервер:
- task: CopyFilesOverSSH@0
  displayName: 'Отправка архива на сервер'
  inputs:
    sshEndpoint: 'My server'
    sourceFolder: '$(Build.BinariesDirectory)/$(Build.BuildId)'
    contents: 'archive.tar.gz'
    targetFolder: '$(WorkingDirectory)'
    cleanTargetFolder: false
    readyTimeout: '20000'
- task: SSH@0
  displayName: 'Распаковка архива на сервере'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: 'tar -xf $(WorkingDirectory)/archive.tar.gz -C ~/liquibase/$(Build.BuildId)'
    readyTimeout: '20000'
- task: SSH@0
  displayName: 'Удаление архива на сервере'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: 'rm $(WorkingDirectory)/archive.tar.gz'
    readyTimeout: '20000'

# Этап 3. Запускаем liquibase на удалённом сервере:
- task: SSH@0
  displayName: 'Делаем скрипт liquibase.sh запускаемым'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: 'chmod +x $(WorkingDirectory)/liquibase.sh'
    readyTimeout: '20000'
- task: SSH@0
  displayName: 'Запуск команды liquibase'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: '$(liquibase) validate --contexts $(liquibaseContext)' # --contexts $(liquibaseContext) необходимо удалить, если вы не используете контексты
    readyTimeout: '20000'
    failOnStdErr: false

# Этап 4: Чистим удалённый сервер от скриптов после выполнения нужных команд.
- task: SSH@0
  displayName: 'Очистка папок'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: 'rm -rf $(WorkingDirectory)'
    readyTimeout: '20000'

variables:
- name: WorkingDirectory
  value: '~/liquibase/$(Build.BuildId)' # каждая сборка (Build) взаимодействует со своей папкой
- name: liquibase
  value: 'cd $(WorkingDirectory) && ./liquibase.sh' # команда для запуска liquibase
```

> Будьте осторожны! В данном скрипте переменная `WorkingDirectory` обязательно должна иметь значение (не забудьте скопировать variables!). В противном случае на шаге "Очистка папок" будет запущена команда `rm -rf /`.

Для работы представленного пайплайна, не забудьте добавить `sshEndpoint` в настройках проекта (задать данные для подключения к удалённому серверу, на котором будет запускаться liquibase), а также задать переменную `liquibaseContext` если вы хотите запустить конвеер на каком-либо контексте.

В `variables` вы можете изменить команду запуска liquibase на следующие:
- для обычного режима: `liquibase` (liquibase должен быть предварительно установлен);
- для docker режима: `docker run --rm -v ${PWD}/:/liquibase/changelog/ liquibase/liquibase --defaultsFile=/liquibase/changelog/liquibase.properties --changelog-file=liquibase.json --search-path=/liquibase/changelog/`;
- для запуска через собственный скрипт: `cd $(WorkingDirectory) && ./liquibase.sh`.

## Ресурсы
* [Liquibase](./gbt_liquibase.ru.md) <i class="fa fa-arrow-left" aria-hidden="true"></i>