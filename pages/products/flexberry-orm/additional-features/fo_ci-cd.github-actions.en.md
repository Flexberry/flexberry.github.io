---
title: CI/CD with GitHub Actions
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, CI/CD, GitHub Actions
summary: GitHub Actions workflow configuration for Flexberry ORM build and tests
toc: true
permalink: en/fo_ci-cd.github-actions.html
lang: en
---

## GitHub Actions workflow

Flexberry ORM uses GitHub Actions for CI/CD. The workflow is configured in the `.github/workflows/` directory.

### GitHub Actions versions

The workflow uses the latest versions of GitHub Actions:

| Action | Old Version | New Version |
|--------|-------------|-------------|
| `actions/checkout` | v2 | v4 |
| `actions/setup-dotnet` | v1 | v4 |

### Runner environment

```yaml
runs-on: ubuntu-22.04
```

### .NET SDK installation

The workflow installs .NET SDK for all supported target frameworks:

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

### Testing

Tests run for all supported frameworks:

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

### Database Docker containers

```yaml
# PostgreSQL
--health-retries 10

# MSSQL
image: mcr.microsoft.com/mssql/server:2022-CU10-ubuntu-20.04
```

### SSH configuration for Doxygen

```yaml
mkdir -p ~/.ssh
ssh-keyscan github.com >> ~/.ssh/known_hosts
eval "$(ssh-agent -a $SSH_AUTH_SOCK)"
echo "$PRIVATE_KEY" | tr -d '\r' | ssh-add -
```

## Mono tests

For Linux tests, the workflow installs Mono and adds delays to reduce SIGSEGV risk:

```yaml
- name: Wait before mono tests (to reduce SIGSEGV risk)
  run: sleep 15

- name: Wait before mono integration tests (to reduce SIGSEGV risk)
  run: sleep 15
```