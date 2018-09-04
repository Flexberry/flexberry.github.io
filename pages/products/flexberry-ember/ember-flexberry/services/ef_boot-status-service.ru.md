---
title: Сервис состояния загрузки
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: ru/ef_boot-status-service.html
lang: ru
summary: Методы и пример использования
---

__Сервис состояния загрузки__ - сервис, через который можно управлять классом для самой верхней формы в приложении, этот класс используется [semantic](https://semantic-ui.com/collections/form.html) для отображения различных состояний формы.

Управление осуществляется путем задания соответствующего метода и класса.

Методы:

* `loading` - устанавливает класс устанавливает класс (загрузка страницы)
* `success` - устанавливает класс ()
* `error` - устанавливает класс (отображение ошибки)
* `warning` - устанавливает класс (отображение предупреждения)
* `reset` - убирает установленный класс

### Пример использования

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

3.Использовать службу в другом контроллере (или компоненте):

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