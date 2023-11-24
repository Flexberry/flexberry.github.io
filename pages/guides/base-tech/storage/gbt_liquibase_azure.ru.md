---
title: Использование Liquibase в Azure Pipelines
keywords: liquibase, azure, pipelines
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase_azure.html
lang: ru
---

### Использование Liquibase в Azure Pipelines

Чтобы запускать команды Liquibase в Azure Pipelines, необходимо иметь агента сборки с установленным Liquibase CLI. Если такого агента нет, есть альтернативное решение.

В момент сборки скрипты необходимо:
- копировать на удалённый сервер по ssh,
- через ssh запускать необходимые команды Liquibase на удалённом сервере

Для этого Liquibase должен быть предварительно установлен на удалённый сервер, либо можно использовать вариант с Docker. YAML конвейер для запуска Liquibase команд на удалённом сервере может выглядеть так:
```yaml
# Пайплайн позволяет запускать команды liquibase на удалённом сервере через ssh.

# Параметры, которые необходимо задать в Azure Pipelines:
# - liquibaseContext - контекст, для которого будут выполнены команды liquibase (указывать обязательно; можно удалить этот параметр, если в проекте не используются контексты).

pool:
  name: FastAgents
steps:

# Этап 1. Подготовка скриптов для отправки на удалённый сервер:
- task: CopyFiles@2
  displayName: 'Подготовка скриптов'
  inputs:
    # отобрать только файлы скриптов:
    Contents: |
      **/.gitkeep
      **/*.*sql
      **/*liquibase.json
      liquibase.env
      liquibase.properties
      liquibase.sh
    TargetFolder: '$(Build.BinariesDirectory)/$(Build.BuildId)/code'
    CleanTargetFolder: true

# Архивировать скрипты, т.к. архивы отправляются значительно быстрее:
- task: ArchiveFiles@2
  displayName: 'Архивирование скриптов'
  inputs:
    rootFolderOrFile: '$(Build.BinariesDirectory)/$(Build.BuildId)/code'
    includeRootFolder: false
    archiveType: 'tar'
    archiveFile: '$(Build.BinariesDirectory)/$(Build.BuildId)/archive.tar.gz'
    replaceExistingArchive: true

# Этап 2. Отправить архив со скриптами на удалённый сервер:
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

# Этап 3. Запустить liquibase на удалённом сервере:
- task: SSH@0
  displayName: 'Запуск команды liquibase'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: '$(liquibase) validate --contexts $(liquibaseContext)' # --contexts $(liquibaseContext) необходимо удалить, если не используются контексты
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
  value: 'cd $(WorkingDirectory) && liquibase' # команда для запуска liquibase
```

{% include warning.html content="В данном скрипте переменная `WorkingDirectory` обязательно должна иметь значение (необходимо скопировать variables!). В противном случае на шаге 'Очистка папок' будет запущена команда `rm -rf /`." %}

Для работы представленного пайплайна, необходимо:
- добавить `sshEndpoint` в настройках проекта (задать данные для подключения к удалённому серверу, на котором будет запускаться liquibase)
- задать переменную `liquibaseContext` для конвейера, если конвейер будет запущен на каком-либо контексте

Liquibase записывает часть действий в stderr (лог ошибок). Для того, чтобы успешный запуск Liquibase не возвращал ошибку в Azure Pipelines, необходимо добавить опцию `failOnStdErr: false` (см. шаг "Запуск команды liquibase" в примере выше). Если же при выполнении команды Liquibase возникает ошибка, Liquibase вернёт ненулевой код выхода, т.е. операция "упадёт" даже с опцией `failOnStdErr: false`.

В `variables` можно изменить команду запуска liquibase на следующие:
- для обычного режима: `cd $(WorkingDirectory) && liquibase` (liquibase должен быть предварительно установлен);
- для docker режима: `cd $(WorkingDirectory) && docker run --rm -v ${PWD}/:/liquibase/changelog/ liquibase/liquibase --defaultsFile=/liquibase/changelog/liquibase.properties --changelog-file=liquibase.json --search-path=/liquibase/changelog/`;
- для запуска через собственный скрипт: `cd $(WorkingDirectory) && ./liquibase.sh`.

Если для запуска Liquibase используется скрипт (см. пример в конце [этой статьи](./gbt_liquibase.ru.md)), то необходимо перед запуском отметить его запускаемым:

```yaml
- task: SSH@0
  displayName: 'Делаем скрипт liquibase.sh запускаемым'
  inputs:
    sshEndpoint: 'My server'
    runOptions: 'commands'
    commands: 'chmod +x $(WorkingDirectory)/liquibase.sh'
    readyTimeout: '20000'
```

## Ресурсы

* [Liquibase](gbt_liquibase.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
