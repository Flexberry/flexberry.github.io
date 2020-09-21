---
title: Сервис логирования
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_log-service.html
folder: products/ember-flexberry/services/
lang: ru
summary: Представлено описание сервиса логирования
---

## Описание

Сервис логирования обеспечивает запись сообщений различного Ember-приложений уровня в базу данных.

При инициализации приложения данный сервис автоматически внедряется под именем `logService` на уровни: 

* component,
* controller,
* route,
* view.

## Включение и отключение сервиса

По умолчанию сервис выключен.

Для того, чтобы включить сервис, необходимо в конфигурационном файле ember-приложения задать настройку: `config.APP.log`.

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            ...
          }
          ...
        }
        ...
    };
```

По умолчанию сервис логирования выключен (enabled=false). 
Разработчик может динамически включать данный сервис оператором:

```javascript
logService.enabled = true;
```

## Уровни логирования

Сервис логирования поддерживает шесть уровней логирования:

* `storeErrorMessages` - уровень ошибок;
* `storeWarnMessages` - уровень предупреждений;
* `storeLogMessages`- уровень логов;
* `storeInfoMessages`- уровень информационных сообщений;
* `storeDebugMessages`- уровень отладочных сообщений;
* `storeDeprecationMessages`- уровень сообщений о подозрительных и устарелых участков исходного кода. 


### storeErrorMessages - уровень ошибок

На данный уровень выводятся сообщения об ошибках в исходном коде, сообщения о невыполнения условий оператора Ember.assert() сообщения формируемый при обработке исключительных ситуаций в методе new Error(...), ...

Для включения сервиса необходимо в конфигурационном файле ember-приложения задать настройку: `config.APP.log.storeErrorMessages`.

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            storeErrorMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeErrorMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию:

```javascript
Ember.Logger.error(«Текст сообщения об ошибке»);
```

В отличие от стандартного метода метод error() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
 Ember.Logger.error(«Текст сообщения об ошибке»).then(result =&gt; {«действия после записи сообщения»});
```

### storeWarnMessages - уровень предупреждений

На данный уровень выводятся предупреждения формируемые методом Ember.Logger.warn().


Для включения сервиса необходимо в конфигурационном файле Эмбер-приложения задать настройку: "config.APP.log.storeWarnMessages".

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {i18n.t('forms.application.sitemap.log-service-examples.application-log.title')
            enabled: true,
            storeWarnMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeWarnMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию:

```javascript
Ember.Logger.warn(«Текст предупреждения»);
```

В отличие от стандартного метода метод warn() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
Ember.Logger.warn(«Текст предупреждения»).then(result =&gt; {«действия после записи сообщения»});
```

### storeLogMessages - уровень логов 

На данный уровень выводятся предупреждения формируемые методом Ember.Logger.log().

Для включения сервиса необходимо в конфигурационном файле Эмбер-приложения задать настройку: "config.APP.log.storeLogMessages".

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            storeLogMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeLogMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию

```javascript
Ember.Logger.log(«Текст лога»);
```

В отличие от стандартного метода метод log() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
Ember.Logger.log(«Текст лога»).then(result =&gt; {«действия после записи сообщения»});
```

### storeInfoMessages - уровень информационных сообщений 

На данный уровень выводятся предупреждения формируемые методом Ember.Logger.info().

Для включения сервиса необходимо в конфигурационном файле Эмбер-приложения задать настройку: "config.APP.log.storeInfoMessages".

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            storeInfoMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeInfoMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию

```javascript
Ember.Logger.info(«Информационный текст»);
```

В отличие от стандартного метода метод log() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
Ember.Logger.info(«Информационный текст»).then(result =&gt; {«действия после записи сообщения»});
```

### storeDebugMessages- уровень отладочных сообщений 
На данный уровень выводятся предупреждения формируемые методом Ember.Logger.debug().


Для включения сервиса необходимо в конфигурационном файле Эмбер-приложения задать настройку: "config.APP.log.storeDebugMessages".

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            storeDebugMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeDebugMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию

```javascript
Ember.Logger.debug(«Отладочный текст»);
```

В отличие от стандартного метода метод log() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
Ember.Logger.debug(«Отладочный текст»).then(result =&gt; {«действия после записи сообщения»});
```

### storeDeprecationMessages - уровень сообщений о подозрительных и устарелых участков исходного кода 
На данный уровень выводятся предупреждения формируемые методом Ember.Logger.warn() начинающиеся с префикса 'DEPRECATION'.

Для включения сервиса необходимо в конфигурационном файле Эмбер-приложения задать настройку: "config.APP.log.storeDeprecationMessages".

```javascript
module.exports = function(environment) {
    module.exports = function(environment) {
      var ENV = {
        ...
        APP: {
          ...
          log: {
            enabled: true,
            storeDeprecationMessages: true,
            ...
          }
          ...
        }
        ...
    };
```

Для динамического включения логировния данного уровня используйте оператор:

```javascript
logService.storeDeprecationMessages = true;
```

Разработчик может самостоятельно сгенерировать сообщение данного уровня используя функцию

```javascript
Ember.Logger.warn('DEPRECATION«Текст лога»');
```

В отличие от стандартного метода метод log() сервиса логирования возвращает Promise. Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения об ошибке в базу используя оператор:

```javascript
Ember.Logger.warn('DEPRECATION«Текст лога»').then(result =&gt; {«действия после записи сообщения»});
```

## Просмотр сообщений

ember-flexberry включает в себя model, router и template для отображения таблицы логов.
Для включения данного сервиса в приложение необходимо:
# добавить строку роутинга в /app/router.js

```javascript
       this.route('i-i-s-caseberry-logging-objects-application-log-l');
```

# добавить ссылку в sitemap файла app/controllers/application.js

```javascript
link: 'i-i-s-caseberry-logging-objects-application-log-l',caption: ...,title: ... 
```

По умолчанию на страница отображаются столбцы:
* Время - время получения сообщения;
* Категория - ERROR, WARN, INFO, LOG, DEBUG, DEPRECATION;
* Сервер - DNS Ember-сервера;
* Браузер - информация по браузкру клиента;
* URL -адрес странцы при отображении которой пришло ссобщение;
* Сообщение - текст сообщения;
![](/images/pages/kaf/log1.png)


Роутер включает в себя две предопределенные настройки пользователя:
* Message
* FormattedMessage
![](/images/pages/kaf/log2.png)
По умолчанию отображается настройка Message.

При необходимости разработчик может настройку FormattedMessage при которой отображается столбец "Форматированное сообщение" в котором для сообщений уровня Error может содержаться имя файла, строка, стек вызовов места возникновения ошибки.
![](/images/pages/kaf/log3.png)

Воспользовавшись сервисом пользовательских настроей разработчик может донастроить необходимый ему режим отображения спсика сообщений.
