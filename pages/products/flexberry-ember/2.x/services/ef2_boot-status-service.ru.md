---
title: Сервис состояния приложения
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: ru/ef2_boot-status-service.html
lang: ru
summary: Методы loading, success, error, warning, reset
---

[Сервис состояния загрузки](https://github.com/Flexberry/ember-flexberry/blob/master/addon/services/app-state.js) - сервис, через который можно управлять классом для самой верхней формы в приложении, этот класс используется [semantic](https://semantic-ui.com/collections/form.html) для отображения различных состояний формы.

Управление осуществляется путем задания соответствующего метода и класса.

Методы:

* `loading` - управляет загрузкой страницы
* `success` - отображает успешное выполнение операций
* `error` - управляет отображением ошибок
* `warning` -управляет отображением предупреждения
* `reset` - убирает установленный класс

### Пример использования

Представлен [пример](https://github.com/Flexberry/ember-flexberry/blob/master/addon/services/app-state.js#L8) для `ember 3.x`.

1.Добавить службу в контроллер приложения:

```javascript
// app/controllers/application.js
import Controller from '@ember/controller';
import { inject as service } from '@ember/service';
export default Controller.extend({
    appState: service(),
});
```

2.Прописать состояние приложения в шаблоне:

```hbs
{% raw %}
<div class="ui {{appState.state}} form">
{{outlet}}
</div>
{% endraw %}
```

3.Использовать службу в контроллере (компоненте):

```javascript
// app/controllers/my-controller.js
import Controller from '@ember/controller';
import { inject as service } from '@ember/service';

export default Controller.extend({
    appState: service(),
    actions: {
        load() {
        this.get('appState').loading();
        this.load().finally(() => {
            this.get('appState').reset();
            });
        },
    },
});
```

В даном случае устанавливается контроль загрузки страницы, при успешном завершении которой сервис удаляет установленные настройки.
