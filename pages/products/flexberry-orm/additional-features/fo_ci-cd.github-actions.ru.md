---
title: CI/CD с GitHub Actions
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, CI/CD, GitHub Actions
summary: Конфигурация GitHub Actions для сборки и тестирования Flexberry ORM
toc: true
permalink: ru/fo_ci-cd.github-actions.html
lang: ru
---

## GitHub Actions workflow

Flexberry ORM использует GitHub Actions для CI/CD. Конфигурация workflow находится в директории `.github/workflows/`.

### Версии GitHub Actions

Workflow использует последние версии GitHub Actions:

| Действие | Старая версия | Новая версия |
|----------|---------------|--------------|
| `actions/checkout` | v2 | v4 |
| `actions/setup-dotnet` | v1 | v4 |

### Среда исполнения

```yaml
runs-on: ubuntu-22.04
```

### Установка .NET SDK

Workflow устанавливает .NET SDK для всех поддерживаемых целевых фреймворков:

```yaml
- name: Install .NET 6.0
  uses: actions/setup-dotnet@v4
  with:
    dotnet-version: '6.0.x'

- name: Install .NET 7.0
  uses: actions/setup-dotnet@v4
  with:
    dotnet-version: '7.0.x'

- name: Install .NET 10.0
  uses: actions/setup-dotnet@v4
  with:
    dotnet-version: '10.0.x'
```

### Тестирование

Тесты запускаются для всех поддерживаемых фреймворков:

```yaml
- name: Test on dotnet 6.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.Tests/bin/Debug/net6.0/NewPlatform.Flexberry.ORM.Tests.dll

- name: Integration test on dotnet 6.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.IntegratedTests/bin/Debug/net6.0/NewPlatform.Flexberry.ORM.IntegratedTests.dll

- name: Test on dotnet 7.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.Tests/bin/Debug/net7.0/NewPlatform.Flexberry.ORM.Tests.dll

- name: Integration test on dotnet 7.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.IntegratedTests/bin/Debug/net7.0/NewPlatform.Flexberry.ORM.IntegratedTests.dll

- name: Test on dotnet 10.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.Tests/bin/Debug/net10.0/NewPlatform.Flexberry.ORM.Tests.dll

- name: Integration test on dotnet 10.0
  run: dotnet test ./NewPlatform.Flexberry.ORM.IntegratedTests/bin/Debug/net10.0/NewPlatform.Flexberry.ORM.IntegratedTests.dll
```

### Docker-контейнеры баз данных

```yaml
# PostgreSQL
--health-retries 10

# MSSQL
image: mcr.microsoft.com/mssql/server:2022-CU10-ubuntu-20.04
```

### SSH-настройки для Doxygen

```yaml
mkdir -p ~/.ssh
ssh-keyscan github.com >> ~/.ssh/known_hosts
eval "$(ssh-agent -a $SSH_AUTH_SOCK)"
echo "$PRIVATE_KEY" | tr -d '\r' | ssh-add -
```

## Тесты под Mono

Для тестов под Linux workflow устанавливает Mono и добавляет задержки для снижения риска SIGSEGV:

```yaml
- name: Wait before mono tests (to reduce SIGSEGV risk)
  run: sleep 15

- name: Wait before mono integration tests (to reduce SIGSEGV risk)
  run: sleep 15
```