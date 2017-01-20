---
title: Проверка сложности пароля в консоли безопасности
sidebar: product--sidebar
keywords: Flexberry Security
toc: true
permalink: ru/checking-password-complexity-in-security-console.html
folder: product--folder
lang: ru
---
# Проверка сложности пароля в консоли безопасности
При установке пароля пользователя в консоли безопасности есть возможность организовать контроль его сложности. Задать ограничения, которым должен удовлетворять пароль, можно либо через конфигурационный файл приложения, либо через диалоговое окно «Агенты\Сложность пароля» (настройки сохраняются в конфигурационном файле).

![](/images/pages/img/page/Checking-password-complexity-in-security-console/1 Консоль безопасности.png)  
![](/images/pages/img/page/Checking-password-complexity-in-security-console/2 Сложность пароля.png)

При смене пароля введенное пользователем значение будет автоматически контролироваться, а при нарушении ограничений выдаваться соответствующее сообщение.

![](/images/pages/img/page/Checking-password-complexity-in-security-console/3 Ошибка.png)


# Структура конфигурационного файла

Настройки проверки пароля хранятся в '''конфигурационном файле'''.
```xml
<?xml version="1.0"?>
<configuration>
  <configSections>
...
    <section name="passwordOptions" type="ICSSoft.STORMNET.Security.PasswordOptions.PasswordOptions, Security(Forms), Version=1.0.0.1, Culture=neutral, PublicKeyToken=110e1aa82d692161" />
...
  </configSections>

...
  <passwordOptions minimumCharacterCount="6" useLatinCharacters="true"
    useDigitCharacters="true" useSpecialCharacters="false" useCyrillicCharacters="false"
    useUpperCaseCharacters="true" />
...
</configuration>
```


# Смотрите также
* [Консоль управления полномочиями (Security Console)](security-console.html)
* [Как начать работу с подсистемой полномочий](how-to-start-work-with-right-manager.html).
* [Сценарии использования подсистемы полномочий](rights-scenarios.html).
* [RightService-Flexberry-Rights|Сервис полномочий Flexberry Rights (CheckingLibrary)]
* [Подсистема полномочий в WEB](right-manager-in--w-e-b.html).
* [c:Полномочия|Все статьи категории "Полномочия"].
