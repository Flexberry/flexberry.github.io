---
title: Модальный диалог
sidebar: flexberry-ember-3_sidebar
keywords: component, компонента, flexberry-dialog, модальные окна, модальный диалог
toc: true
permalink: ru/efd3_flexberry-dialog.html
lang: ru
summary: Обзор возможностей, настроек и особенностей устройства компонента модального диалога в технологии Flexberry Ember.
---

## Назначение компонента
Компонент модального диалога в технологии [Flexberry Ember](https://flexberry.github.io/ru/ef3_landing_page.html) предназначен для предоставления пользователю выбора между двумя вариантами во всплывающем окне.

## Обзор возможностей и API компонента

[Компонент модального диалога](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryDialogComponent.html) реализован на базе компонента [Semantic UI Modal](https://semantic-ui.com/modules/modal.html). Список настроек:
* `caption` - текст заголовка окна
* `content` - текст внутри модального окошка
* `contentClass` - классы, применяемые к контенту модального окна
* `approveButtonCaption` - подпись кнопки подтверждения (по умолчанию "Ок")
* `denyButtonCaption` - подпись кнопки несогласия (по умолчанию "Отмена")
* `allowMultiple` - разрешить показывать несколько модальных окон
* `visible` - атрибут, позволяющий показать модальное окно на странице

Настройки передаются:
- `offset` - величина вертикального отступа (по умолчанию `0`)
- `allowMultiple` - разрешить открывать несколько модальных окон (по умолчанию `false`)
- `closable` - разрешить закрывать окно по клику на область вне окна
- `transition` - название перехода для показа модального окна (по умолчанию `scale`) - _[см. список переходов](https://semantic-ui.com/modules/transition.html)_
- `duration` - длительность анимации показа модального окна

Список доступных callbacks:
* `beforeShow` - перед появлением модального окна
* `beforeHide` - перед скрытием модального окна
* `show` - в момент появления модального окна
* `hide` - в момент скрытия модального окна
* `approve` - в момент нажатия на кнопку согласия
* `deny` - в момент нажатия на кнопку отмены

Во все callback единственным параметром передаётся событие (тип `object`). Событие имеет атрибут `target` - объект, возвращаемый методом `modal` из Semantic UI. События `beforeHide`, `approve` и `deny` имеют атрибут `closeDialog`, позволяющий отменить скрытие модального окна. Если значение перевести в `false` внутри callback, окно не будет закрыто.

## Пример использования
```hbs
{% raw %}
{{#flexberry-dialog
  caption="Подтвердите выбор"
  content="Картинка будет удалена! Вы уверены?"
  visible=visible
  beforeHide=(action "onBeforeHide")
  approve=(action "deleteRecord")
}}
  <img src="{{img_src}}" />
{{/flexberry-dialog}}
{% endraw %}
```

Для показа модального диалога, необходимо перевести атрибут visible в `true`.
```javascript
this.set('visible', true);
```

Пример использования callback с отменой показа модального окна:
```javascript
actions: {
  onBeforeHide(e) {
    if (2 + 2 == 4) {
      e.closeDialog = false; // отменяет скрытие модального окна (окно остаётся на месте)
    }
  }
}
```
