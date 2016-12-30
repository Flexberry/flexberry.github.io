---
title: Формы редактирования и создания Flexberry ASP.NET Ember
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_edit-form.html
folder: products/ember-flexberry/forms/
lang: ru
summary: 
---

## Описание

Форма редактирования предназначена для редактирования объекта. Форма создания - для создания нового объекта (хотя в общем случае это очень близкие по логике работы формы).

Чтобы создать форму редактирования [модели](efd_model.html) по [представлению](efd_model-projection.html), необходимо определить соответствующие [роуты](ef_route.html), [контроллеры](ef_controller.html) и [шаблоны](ef_template.html).

Реализованные в технологии [формы](ef_forms.html) базовые элементы представляют собой один [контроллер](ef_controller.html), один [шаблон](ef_template.html) и разные [роуты](ef_route.html) (роут для формы создания унаследован от роута для формы редактирования).

## Формы создания
Формы создания можно использовать, чтобы задавать [значения по умолчанию для свойств модели](ef_default-value.html).

## Функциональность форм создания и редактирования

{| border="1"
|  
| colspan="2" | Обычная форма
(edit-form) 
| colspan="2" | Детейловая форма без сохранения
(detail-edit-form) 
| colspan="2" | Детейловая форма c сохранением
(detail-edit-form)
|-
| rowspan="4" style="background-color: #0F0;" | ReadOnly
IsNew
| S
| - 
| S
| - 
| S
| -
|-
| SC 
| - 
| SC
| - 
| SC 
| -
|-
| D 
| - 
| D
| - 
| D 
| -
|-
| C 
| модель не меняется 
| C
| состояние не должно быть доступно 
| C
| состояние не должно быть доступно
|-
| rowspan="4" style="background-color: #0F0;" | ReadOnly
Saved
| S
| -
| S
| - 
| S
| -
|-
| SC 
| - 
| SC
| -
| SC 
| -
|-
| D 
| - 
| D
| - 
| D 
| -
|-
| C 
| модель не меняется 
| C
| модель не меняется  
| C
| модель не меняется 
|-
| rowspan="4" style="background-color: #F0F;" | Not ReadOnly
IsNew
| S
| + 
| S
| -
| S
| -
|-
| SC 
| + 
| SC
| -
| SC 
| +
|-
| D 
| - 
| D
| полное удаление 
| D 
| полное удаление
|-
| C 
| откат модели
| C
| без отката модели
| C
| без отката модели,
используется с SC
|-
| rowspan="4" style="background-color: #F0F;" | Not ReadOnly
Saved
| S
| + 
| S
| - 
| S
| -
|-
| SC 
| + 
| SC
| - 
| SC 
| +
|-
| D 
| полное удаление  
| D
| пометка об удалении
| D 
| пометка об удалении
|-
| C 
| откат модели 
| C
| без отката модели 
| C
| без отката модели,
используется с SC
|}

