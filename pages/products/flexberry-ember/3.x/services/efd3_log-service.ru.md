---
title: Сервис логирования
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_log-service.html
lang: ru
summary: Описание сервиса логирования.
---

## Устройство системы логирования Flexberry Ember
Сервис логирования представляет собой [один из доступных в ember-аддоне `ember-flexberry` сервисов](efd3_service.html) и обеспечивает запись сообщений различного уровня в базу данных.

При инициализации приложения данный сервис автоматически внедряется под именем `logService` на уровни: 

* [Компонент](efd3_component.html),
* [Контроллер](efd3_controller.html),
* [Роут](efd3_route.html).

По умолчанию сервис логирования выключен, существует возможность включить его как программно, так и в файле конфигурации.

## Возможности сервиса log

Разработчик может динамически включать/отключать данный сервис оператором:

```javascript
logService.enabled = true;
```

### Уровни логирования

Сервис логирования поддерживает шесть уровней логирования:

* `storeErrorMessages` - уровень ошибок;
* `storeWarnMessages` - уровень предупреждений;
* `storeLogMessages`- уровень логов;
* `storeInfoMessages`- уровень информационных сообщений;
* `storeDebugMessages`- уровень отладочных сообщений;
* `storeDeprecationMessages`- уровень сообщений о подозрительных и устаревших участках исходного кода.

Разработчик может самостоятельно сгенерировать сообщения данных уровней, используя функции `Ember.Logger`:

```javascript
Ember.Logger.error(«Текст сообщения об ошибке»); // `storeErrorMessages` - уровень ошибок
Ember.Logger.warn(«Текст предупреждения»); // `storeWarnMessages` - уровень предупреждений;
Ember.Logger.log(«Текст лога»); // `storeLogMessages`- уровень логов;
Ember.Logger.info(«Информационный текст»); // `storeInfoMessages`- уровень информационных сообщений;
Ember.Logger.debug(«Отладочный текст»); // `storeDebugMessages`- уровень отладочных сообщений;
Ember.Logger.warn('DEPRECATION«Текст лога»'); // `storeDeprecationMessages`- уровень сообщений о подозрительных и устаревших участках исходного кода.
```

{% include note.html content="В отличие от [стандартных методов Ember.Logger](https://api.emberjs.com/ember/3.24/classes/Ember.Logger) методы сервиса логирования возвращают `Promise`." %}

Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения в базу, используя оператор `then`. Например:

```javascript
 Ember.Logger.error(«Текст сообщения об ошибке»).then(result => {«действия после записи сообщения»});
```

Для динамического включения/отключения логирования требуемого уровня используются операторы:

```javascript
logService.storeErrorMessages = true; // `storeErrorMessages` - уровень ошибок
logService.storeWarnMessages = true; // `storeWarnMessages` - уровень предупреждений;
logService.storeLogMessages = true; // `storeLogMessages`- уровень логов;
logService.storeInfoMessages = true; // `storeInfoMessages`- уровень информационных сообщений;
logService.storeDebugMessages = true; // `storeDebugMessages`- уровень отладочных сообщений;
logService.storeDeprecationMessages = true; // `storeDeprecationMessages`- уровень сообщений о подозрительных и устаревших участках исходного кода.
```

**storeErrorMessages - уровень ошибок**

На уровень `storeErrorMessages` выводятся сообщения об ошибках в исходном коде, сообщения о невыполнении условий оператора [`Ember.assert()`](https://api.emberjs.com/ember/3.4/functions/@ember%2Fdebug/assert), формируемые методом `Ember.Logger.error()`сообщения или при обработке исключительных ситуаций в методе `new Error(...)`, ...

**storeWarnMessages - уровень предупреждений**

На уровень `storeWarnMessages` выводятся предупреждения, формируемые методом `Ember.Logger.warn()`.

**storeLogMessages - уровень логов** 

На уровень `storeLogMessages` выводятся предупреждения, формируемые методом `Ember.Logger.log()`.

**storeInfoMessages - уровень информационных сообщений** 

На уровень `storeInfoMessages` выводятся информационные сообщения, формируемые методом `Ember.Logger.info()`.

**storeDebugMessages- уровень отладочных сообщений** 
На уровень `storeDebugMessages` выводятся отладочные сообщения, формируемые методом `Ember.Logger.debug()`.

**storeDeprecationMessages - уровень сообщений о подозрительных и устаревших участках исходного кода** 
На уровень `storeDeprecationMessages` выводятся предупреждения, формируемые методом `Ember.Logger.warn()`, начинающиеся с префикса 'DEPRECATION', о подозрительных и устаревших участках исходного кода.

### Просмотр сообщений

`ember-flexberry` включает в себя [модель](efd3_model.html), [роут](efd3_route.html) и [шаблон](efd3_template.html) для отображения таблицы логов.
Для подключения возможности просмотра записанных сообщений в приложение необходимо:

* добавить строку роутинга в [роутер](efd3_router.html) `/app/router.js`.

```javascript
this.route('i-i-s-caseberry-logging-objects-application-log-l');
```

* добавить ссылку в `sitemap` файла `app/controllers/application.js`

```javascript
link: 'i-i-s-caseberry-logging-objects-application-log-l',caption: ...,title: ... 
```

По умолчанию на странице отображаются столбцы:
* Время - время получения сообщения;
* Категория - ERROR, WARN, INFO, LOG, DEBUG, DEPRECATION;
* Сервер - DNS Ember-сервера;
* Браузер - информация по браузеру клиента;
* URL - адрес страницы, при отображении которой пришло сообщение;
* Сообщение - текст сообщения;

![Отображение списка сообщений из сервиса логирования](/images/pages/products/flexberry-ember/ember-flexberry/services/log1.png)

В [роуте](efd3_route.html) формы просмотра сообщений лога заданы две предопределенные [настройки пользователя](efd3_user-settings-service.html), их [можно увидеть в меню соответствующей формы](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/i-i-s-caseberry-logging-objects-application-log-l) (фактически эти настройки определяют отображаемые по умолчанию столбцы и сортировку):

* `Message`
* `FormattedMessage`

![Доступные предопределенные настройки пользователя на форме просмотра сообщений лога](/images/pages/products/flexberry-ember/ember-flexberry/services/log2.png)

По умолчанию используется предопределённая настройка `Message`.

При необходимости можно использовать предопределённую настройку `FormattedMessage`, при которой отображается столбец "Форматированное сообщение", в котором для сообщений уровня `Error` может содержаться имя файла, строка, стек вызовов, место возникновения ошибки.

![Отображение формы просмотра сообщений лога в режиме FormattedMessage](/images/pages/products/flexberry-ember/ember-flexberry/services/log3.png)

{% include note.html content="Для того, чтобы донастроить требуемый режим отображения списка сообщений, можно воспользоваться [сервисом пользовательских настроек](efd3_user-settings-service.html)." %}

## Настройки логирования в файле конфигурации

Для того, чтобы включить/отключить сервис, необходимо в конфигурационном файле ember-приложения задать настройку: `config.APP.log.enabled`.
Настройки сервиса логирования в сгенерированном приложении в файле конфигурации представлены ниже.

```javascript
'use strict';

module.exports = function(environment) {
  ...
  let ENV = {
    ...
    APP: {
      ...
      // Log service settings.
      log: {
        // Flag: indicates whether log service is enabled or not.
        enabled: true,

        storeErrorMessages: true,
        storeWarnMessages: false,
        storeLogMessages: true,
        storeInfoMessages: false,
        storeDebugMessages: false,
        storeDeprecationMessages: false,
        storePromiseErrors: true,
        showPromiseErrors: true,
        errorMessageFilterActive: false,
      }
    }
  };
  ...
  return ENV;
};
```

* `enabled` - флаг, определяющий, включён ли сервис логирования по умолчанию.
* `storeErrorMessages`, `storeWarnMessages`, `storeLogMessages`, `storeInfoMessages`, `storeDebugMessages`, `storeDeprecationMessages`- флаги, определяющие, осуществлять ли запись сообщений соответствующего уровня в лог. 