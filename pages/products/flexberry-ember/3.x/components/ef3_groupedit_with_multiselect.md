---
title: Groupedit с функцией multiselect
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, groupedit, multiselect
toc: true
permalink: ru/ef3_groupedit_with_multiselect.html
lang: ru
---

### Описание groupedit с функцией multiselect

С версии ember-flexberry 3.9.0 в лукапах groupedit доступна функция множественного выбора. Эта функция позволяет не создавать каждый раз новую строку в groupedit, достаточно в одном лукапе отметить все необходимые записи, и на каждую выбранную запись строки в GE создадутся автоматически.

Работает это следующим образом. В групэдите с лукапом добавляем новую строку и кликаем кнопку "Выбрать" у лукапа. Откроется окно выбора объекта лукапа. Можно, как обычно, кликнуть по строке, и тогда в групэдит добавится только этот объект, окно выбора лукапа закроется. Или можно выбрать несколько объектов, отметив соответсвующие чекбоксы, и нажать кнопку "Использовать все выбранные записи":

![multiselect-lookup-in-ge](/images/pages/products/flexberry-ember/3.x/components/multiselect-lookup-in-ge.png)

После чего окно выбора лукапа закроется, и в групэдите создадутся строки с уже заполненными лукапами.

Если в групэдите уже есть записи, то новую строку создавать необязательно. Можно нажать кнопку выбора у любого лукапа, и новые записи создадутся, не затрагивая уже заполненный лукап. Если хотите заменить уже имеющееся значение лукапа, то в окне выбора кликаем по строке как обычно, не используя кнопку мультиселекта.

Доступен [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-groupedit/groupedit-with-multiselect-list) на тестовом стенде. Нужно выбрать любую запись из списка и на открывшейся эдит форме смотреть на groupedit "Комментарии".

### Настройка groupedit с функцией multiselect

Чтобы включить функцию мультиселекста в лукапе для групэдита, нужно прописать в контроллере формы lookup-settings для нужного компонента:

```javascript
import BaseEditFormController from 'ember-flexberry/controllers/edit-form';
import EditFormControllerOperationsIndicationMixin from 'ember-flexberry/mixins/edit-form-controller-operations-indication';

export default BaseEditFormController.extend(EditFormControllerOperationsIndicationMixin, {
    ...
    getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);
    const i18n = this.get('i18n');
    const lookupMultiGESettings = this.get('lookupMultiGESettings');
    if (attr.kind === 'belongsTo') {
      switch (`${model.modelName}+${bindingPath}`) {
        case 'ember-flexberry-dummy-comment+author':
          cellComponent.componentProperties = {
            choose: 'showLookupDialog',
            modalDialogSettings: {
              lookupSettings: lookupMultiGESettings,
            }, 
            remove: 'removeLookupValue',
            displayAttributeName: 'name',
            required: true,
            relationName: 'author',
            projection: 'ApplicationUserL',
            autocomplete: true,
          };
        break;
      }
    }
    return cellComponent;
  }
});
```
