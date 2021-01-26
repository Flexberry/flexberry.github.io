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
Сервис логирования представляет собой [один из доступных во фреймворке Flexberry Ember сервисов](efd3_service.html) и обеспечивает запись сообщений различного уровня в базу данных приложения.

Сервис логирования Flexberry Ember расширяет возможности [стандартного логгера фреймворка Ember.js](https://api.emberjs.com/ember/3.1/classes/Ember.Logger).

При инициализации приложения данный сервис автоматически внедряется с именем `logService` на уровни: 

* [Роут](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/),
* [Компонент](https://guides.emberjs.com/v3.1.0/components/defining-a-component/),
* [Контроллер](https://guides.emberjs.com/v3.1.0/controllers/).

По умолчанию сервис логирования выключен, существует возможность включить его как программно, так и в файле конфигурации.

## Возможности сервиса log

### Уровни логирования

Сервис логирования поддерживает семь уровней логирования:

* `ERROR` - уровень ошибок;
* `WARN` - уровень предупреждений;
* `LOG`- уровень логов;
* `INFO`- уровень информационных сообщений;
* `DEBUG`- уровень отладочных сообщений;
* `DEPRECATION`- уровень сообщений о подозрительных и устаревших участках исходного кода;
* `PROMISE`- уровень ошибок промисов.

Разработчик может самостоятельно сгенерировать сообщения данных уровней, используя соответствующие методы класса `Ember.Logger`:

```javascript
Ember.Logger.error('Текст сообщения об ошибке'); // уровень ошибок
Ember.Logger.warn('Текст предупреждения'); // уровень предупреждений;
Ember.Logger.log('Текст лога'); // уровень логов;
Ember.Logger.info('Информационный текст'); // уровень информационных сообщений;
Ember.Logger.debug('Отладочный текст'); // уровень отладочных сообщений;
Ember.Logger.warn('DEPRECATION "Текст лога"'); // уровень сообщений о подозрительных и устаревших участках исходного кода.
```

{% include note.html content="В отличие от [стандартных методов класса Ember.Logger](https://api.emberjs.com/ember/3.1/classes/Ember.Logger) методы сервиса логирования возвращают `Promise`." %}

Пользователь в случае необходимости может дождаться выполнения асинхронной операции записи сообщения в базу, используя оператор `then`. Например:

```javascript
 Ember.Logger.error('Текст сообщения об ошибке').then(result => {«действия после записи сообщения в БД»});
```

Кроме того, по завершении промиса инициируется событие с именем уровня ошибки. Например, `ERROR`, `WARN` и т.п. Обработчики событий с этими именами можно создать с использованием методов класса [Evented](https://api.emberjs.com/ember/3.1/classes/Evented).

**ERROR - уровень ошибок**

На уровень `ERROR` выводятся следующие сообщения об ошибках: 
* все ошибки времени выполнения приложения, обрабатываемые хуком [Ember.onerror](https://guides.emberjs.com/v3.1.0/configuring-ember/debugging/#toc_implement-an-emberonerror-hook-to-log-all-errors-in-production);
* ошибки, формируемые методом [Ember.Logger.error](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/error?anchor=error);
* ошибки, возникающие при обработке промисов при помощи [обработки события error класса RSVP.Promise](https://guides.emberjs.com/v3.1.0/configuring-ember/debugging/#toc_errors-within-an-rsvppromise).

**WARN - уровень предупреждений**

На уровень `WARN` выводятся предупреждения, формируемые методом [Ember.Logger.warn](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/warn?anchor=warn).

**LOG - уровень логов** 

На уровень `LOG` выводятся сообщения, формируемые методом [Ember.Logger.log](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/log?anchor=log).

**INFO - уровень информационных сообщений** 

На уровень `INFO` выводятся информационные сообщения, формируемые методом [Ember.Logger.info](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/info?anchor=info).

**DEBUG - уровень отладочных сообщений** 
На уровень `DEBUG` выводятся отладочные сообщения, формируемые методом [Ember.Logger.debug](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/debug?anchor=debug).

**DEPRECATION - уровень сообщений о подозрительных и устаревших участках исходного кода** 
На уровень `DEPRECATION` выводятся предупреждения о подозрительных и устаревших участках исходного кода, формируемые методом [Ember.Logger.warn](https://api.emberjs.com/ember/3.1/classes/Ember.Logger/methods/warn?anchor=warn), и включающие подстроку 'DEPRECATION'.

### Просмотр сообщений

Фреймворк Flexberry Ember включает в себя [модель](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/addon/models/i-i-s-caseberry-logging-objects-application-log.js), а также роуты для отображени [списковой формы](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/addon/routes/i-i-s-caseberry-logging-objects-application-log-l.js) и [формы редактирования](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/addon/routes/i-i-s-caseberry-logging-objects-application-log-e.js) для логов.

{% include warning.html content="Данная функциональность доступна при использоваии бэкенда Flexberry Ember!" %}

Для подключения возможности просмотра записанных сообщений в приложение необходимо:

* добавить необходимый роут в [роутер](https://guides.emberjs.com/v3.1.0/getting-started/core-concepts/#toc_router-and-route-handlers), объявленный в файле `/app/router.js`:

```javascript
this.route('i-i-s-caseberry-logging-objects-application-log-l');
```

* добавить ссылку в свойство `sitemap`, объявленное в файле `app/controllers/application.js` для отображения соответствующего пункта меню:

```javascript
{
  link: 'i-i-s-caseberry-logging-objects-application-log-l',
  caption: ...,
  title: ...,
  children: null
} 
```

По умолчанию на списке отображаются следующие столбцы:
* Время - время создания сообщения;
* Категория - ERROR, WARN, INFO, LOG, DEBUG, DEPRECATION;
* Сервер - DNS бэкенда;
* Браузер - информация о браузере, в котором было запущено клиентское приложение;
* URL - адрес страницы, с которой было инициировано логирование сообщения;
* Сообщение - текст сообщения.

![Отображение списка сообщений из сервиса логирования](/images/pages/products/flexberry-ember/ember-flexberry/services/log1.png)

На списковой форме отображения логов имеются две предопределенные пользовательские настройки, их можно увидеть в меню соответствующей [формы](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/i-i-s-caseberry-logging-objects-application-log-l) (эти настройки определяют отображаемые по умолчанию столбцы и сортировку):

* `Message`
* `FormattedMessage`

![Доступные предопределенные настройки пользователя на форме просмотра сообщений лога](/images/pages/products/flexberry-ember/ember-flexberry/services/log2.png)

По умолчанию используется предопределённая настройка `Message`.

При необходимости можно использовать предопределённую настройку `FormattedMessage` для отображения столбца `Форматированное сообщение`, в котором для сообщений уровня `ERROR` может содержаться имя файла, строка, стек вызовов, место возникновения ошибки.

![Отображение формы просмотра сообщений лога в режиме FormattedMessage](/images/pages/products/flexberry-ember/ember-flexberry/services/log3.png)

{% include note.html content="Для того, чтобы донастроить требуемый режим отображения списка сообщений, можно воспользоваться возможномтями [пользовательских настроек спискового компонента]()." %}

## Настройки логирования в файле конфигурации

Конфигурация сервиса логирования устанавливается в файле `environment.js`.

Пример настроек сервиса логирования в приложении Flexberry Ember представлены ниже.

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

* `enabled` - флаг, определяющий, включён ли сервис логирования.
* `storeErrorMessages`, `storeWarnMessages`, `storeLogMessages`, `storeInfoMessages`, `storeDebugMessages`, `storeDeprecationMessages`, `storePromiseErrors` - флаги, определяющие, осуществлять ли запись сообщений соответствующего уровня в базу данных приложения,
* `showPromiseErrors` - флаг, определяющий, нужно ли вызывать исходный обработчик ошибок для промиса до записи соответствующего сообщения об ошибке в базу данных приложения,
* `errorMessageFilterActive` - флаг, определяющий, нужно ли применть фильтры логов, заданные в свойстве `errorMessageFilters`. 

## Настройки логирования в коде приложения

Разработчик может динамически включать и отключать сервис логирования с испоьзованием оператора:

```javascript
this.get('logService').set('enabled', true);
```
Для динамического включения или отключения логирования на требуемом уровне используются операторы:

```javascript
this.get('logService').set('storeErrorMessages', true) // уровень ошибок
this.get('logService').set('storeWarnMessages', true) // уровень предупреждений;
this.get('logService').set('storeLogMessages', true) // уровень логов;
this.get('logService').set('storeInfoMessages', true) // уровень информационных сообщений;
this.get('logService').set('storeDebugMessages', true) // уровень отладочных сообщений;
this.get('logService').set('storeDeprecationMessages', true) // уровень сообщений о подозрительных и устаревших участках исходного кода.
this.get('logService').set('storePromiseErrors', true) // уровень сообщений об ошибках промисов.
```
Для фильтрации сообщений, которые записываются в логи, можно использовать свойство `errorMessageFilters`, которое представляет собой массив объектов следующего вида:

```javascript
{ category: 'PROMISE', message: 'TransitionAborted' }
```
В свойстве `catrgory` объекта указывается требуемый уровень логов, а в свойстве `message` - фраза, которая может встречаться в логируемом сообщении. При этом если логируемое сообщение содержит указанную фразу, а также имеет указанный уровень лога, то запись в базу данных приложения соответствующего сообщения выполняться не будет.