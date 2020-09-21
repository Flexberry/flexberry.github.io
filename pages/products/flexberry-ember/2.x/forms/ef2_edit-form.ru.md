---
title: Формы редактирования и создания Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_edit-form.html
lang: ru
summary: Предназначение, структура, функции и отображение ошибок на форме редактирования
---

## Описание

Форма редактирования предназначена для редактирования объекта. Форма создания - для создания нового объекта (хотя в общем случае это очень близкие по логике работы формы).

Чтобы создать форму редактирования [модели](efd2_model.html) по [представлению](efd2_model-projection.html), необходимо определить соответствующие [роуты](ef2_route.html), [контроллеры](ef2_controller.html) и [шаблоны](ef2_template.html).

Реализованные в технологии [формы](ef2_forms.html) базовые элементы представляют собой один [контроллер](ef2_controller.html), один [шаблон](ef2_template.html) и разные [роуты](ef2_route.html) (роут для формы создания унаследован от роута для формы редактирования).

## Формы создания

Формы создания можно использовать, чтобы задавать [значения по умолчанию для свойств модели](ef2_default-value.html).

## Функциональность форм создания и редактирования

В таблице ниже представлены сведения об особенностях работы различных режимов для разных вариантов работы форм создания и редактирования.

`Обычная форма` (edit-form) - основной вариант работы формы редатирования.

`Детейловая форма без сохранения или с сохранением` (detail-edit-form) - это особый вид форм, когда требуется [редактировать детейлы в отдельных роутах](ef2_groupedit-detail-in-route.html).

`ReadOnly`/`Not ReadOnly` - открывается или нет [форма в режиме только для чтения](ef2_read-only-form.html).

`IsNew`/`Saved` - является ли объект новым или уже сохранён.

Операции:

* Save (Сохранение)
* Save and close (Сохранение и закрытие)
* Delete (Удаление)
* Close (Закрытие)

| Обычная форма | Детейловая форма без сохранения | Детейловая форма c сохранением |
| (edit-form) |(detail-edit-form) | (detail-edit-form)
:------------| :---------------| :-------------------| :-----------------
`ReadOnly IsNew`| Только закрытие. Модель не меняется. | Только закрытие. При этом состояние недоступно. | Только закрытие. При этом состояние недоступно.
`ReadOnly Saved` |Только закрытие. Модель не меняется.|Только закрытие. Модель не меняется.|Только закрытие. Модель не меняется.
`Not ReadOnly IsNew` | Сохранение, сохранение и закрытие, закрытие (откат модели).| Удаление (полное) и закрытие (без отката модели).| Сохранение, сохранение и закрытие, закрытие (откат модели).
`Not ReadOnly Saved` |Сохранение, сохранение и закрытие, удаление (полное), закрытие (откат модели).|Удаление (пометка об удалении) и закрытие (без отката модели).| Сохранение, сохранение и закрытие, удаление (полное), закрытие (откат модели).

### Шаблон для обычной формы редактирования

Таким образом, при оформлении [шаблона](ef2_template.html) формы редактирования обычной формы с учётом режима только для чтения достаточно следующей конструкции:

```hbs
`#unless readonly`
  <button type="submit" class="ui primary button" `action 'save'`>Save</button>
  <button type="submit" class="ui primary button" `action 'saveAndClose'`>Save and close</button>
  `#unless model.isNew`
  <button type="submit" class="ui primary button" `action 'delete'`>Delete</button>
  `/unless`
`/unless`
<button type="submit" class="ui primary button" `action 'close'`>Close</button>
```

### Шаблон для формы редактирования детейла

Таким образом, при оформлении шаблона формы редактирования детейла с учётом режима только для чтения и возможности работы в обычном режиме достаточно следующей конструкции.

`hasParentRoute` - это вычислимое свойство контроллера, определяющее, откуда пользователь пришёл на текущую форму: с агрегатора или нет.

* Форма `обычная`, если `hasParentRoute = false`, `saveBeforeRouteLeave = false`.
* Форма `детейловая без сохранения`, если `hasParentRoute = true`, `saveBeforeRouteLeave = false`.
* Форма `детейловая с сохранением`, если `hasParentRoute = true`, `saveBeforeRouteLeave = true`.

```hbs
`#unless readonly`
  `#unless (and hasParentRoute (not saveBeforeRouteLeave))`
  <button type="submit" class="ui positive button" `action 'save'`>Save</button>
  `/unless`
    `#unless (and hasParentRoute (not saveBeforeRouteLeave))`
    <button type="submit" class="ui positive button" `action 'saveAndClose'`>Save and close</button>
  `/unless`
  `#unless (and model.isNew (and (not hasParentRoute) (not saveBeforeRouteLeave)))`
  <button type="submit" class="ui negative button" `action 'delete'`>Delete</button>
  `/unless`
`/unless`
<button type="submit" class="ui button" `action 'close'`>Close</button>
```

### Отображение ошибок на форме редактирования

Если ошибка появилась при загрузке объекта до открытия формы редактирования - будет отображен шаблон `error.hbs`. Для изменения этого поведения можно использовать стандартное поведение ember'а.

### QueryParams для формы редактирования

В контроллере edit-form есть [queryParams](https://github.com/Flexberry/ember-flexberry/blob/adb541c44d902152d8c43ff588aa55d376a98ec8/addon/controllers/edit-form.js#L171). Установка этого поля осуществляется штатными механизмами Ember. Сервис блокировок осуществляет `transition` на роут с `readonly=true`, если видит, что объект заблокирован.

Если необходимо открыть форму редактирования на чтение, можно осуществить это аналогичным образом, передав в параметрах запроса (queryParam) параметр readonly со значением true.
