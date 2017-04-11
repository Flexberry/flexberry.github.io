---
title: Структура тем Flexberry ASP.NET для контролов
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_themes-structure-controls.html
lang: ru
---

## Структура файлов

Для удобства редактирования тем, была создана отдельная базовая тема `BaseTheme`, содержащая стандартные стили и настройки общие для всех тем и могут быть переопределены в каждой. В данной статье будет рассматриваться пример реализации одного контрола и не будут затронуты формы.

Структура файлов в общем виде выглядит так:


* BaseTheme
    * Controls
        * SomeControl
            * Images
            * _Variables.less
            * Lookup.less
* Theme
    * Controls
        * SomeControl
            * Images
            * _Settings.less
            * _Variables.less
            * Lookup.less
    * Base.less
    * Base.css

В данной структуре в качестве примера используется только одна тема `Theme`, которая содержит в себе один единственный контрол `SomeControl`. Что означает каждый файл будет рассмотрена ниже.

## Уровень BaseTheme

Внутри базовой темы хранится папка `Controls`, которая содержит в себе какие-либо контролы. В данном случае это один единственный контрол `SomeControl`. Внутри этой папки содержаться папка `Images`, в которой хранятся картинки для контрола и два .less файла.

`_Variables.less` - содержит в себе набор переменных для использования в стилях. К переменным относятся: названия классов и идентификаторов, цвета и отступы.

`SomeControl.less` - содержит в себе стандартные стили для контрола. К ним относятся расположение элементов на странице, стандартные цвета и отступы.

## Уровень Theme

`Theme` соответствует одной из существующих тем.  Внутри темы, как и в случае с `BaseTheme`, хранится папка `Controls`, в которой содержится `SomeControl`. Внутри данной папке есть папка `Images`, которая содержит картинки конкретно для этого контрола и три .less файла.

Назначение `_Variables.less` и `SomeControl.less` остается неизменным. Файл `_Settings.less` используется для переопределения существующих стилей и для доработок в прикладных проектах

## Реализация на примере контрола Lookup

В качестве примера будет рассмотрен простой лукап, состоящий из `input` типа `text` и двух кнопок.

Файловая структура будет выглядеть следующим образом:

* BaseTheme
    * Controls
        * Lookup
            * Images
            * _Variables.less
            * SomeControl.less
* Theme
    * Controls
        * Lookup
            * Images
            * _Settings.less
            * _Variables.less
            * SomeControl.less
    * Base.less
    * Base.css

`Разметка лукапа`

```html
<body>
    <div class="control">
        <div>Тестовы лукап</div>
        <div class=txtInput>
            <input type="text">
        </div>
        <div class="viewBtn">
            <span></span>
        </div>
        <div class="deleteBtn">
            <span></span>
        </div>
    </div>
</body>
```

Для начала будет рассмотрена реализация на уровне базовой темы. Все названия классов необходимо вынести в переменные и добавить в `_Variables.less`. Этот файл необходимо импортировать в `Lookup.less` в самом начале - до определения стилей - с ключевым словом (reference) - позволяет импортировать содержимое файла, при этом не добавлять его физически. Это позволяет не импортировать одни и те же записи по нескольку раз.

```css
@parentControlClass: control;
@textInputClass: txtInput;
@viewBtnClass: viewBtn;
@deleteBtnClass: deleteBtn;

@inputBorderColor: #B9B9B9;
@btnHoveredBackgroundColor: #f0f0f0;
```

Далее, в `Lookup.less` надо прописать стандартные стили для контрола, а именно: базовые цвета, расположение элементов на странице. В итоге получится нечто такое:

```css
.@import (reference) '_Variables.less';

@{parentControlClass} {
    text-align: center;
    margin-top: 10%;

    .@{viewBtnClass},
    .@{deleteBtnClass} {
        display: inline-block;
        margin-right: -6px;
        margin-left: -7px;
        padding: 4px;
        width: 24px;
        height: 24px;
        border: 1px solid #9B9B9B;
        -moz-border-radius: 0;
        -webkit-border-radius: 0;
        background-image: -moz-linear-gradient(top, #ffffff, #cecece);
        background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#cecece));
        background-image: -webkit-linear-gradient(top, #ffffff, #cecece);
        background-image: -o-linear-gradient(top, #ffffff, #cecece);
        background-image: linear-gradient(to bottom, #ffffff, #cecece);
        -moz-box-shadow: inset 0 0 1px 1px #fff;
        -webkit-box-shadow: inset 0 0 1px 1px #fff;
        box-shadow: inset 0 0 1px 1px #fff;
        vertical-align: top;
        cursor: pointer;

        span {
            display: inline-block;
            margin-top: -1px;
            padding: 0;
            width: 16px;
            height: 16px;
            vertical-align: middle;
            line-height: 0;
            filter: none;
        }
    }

    .@{deleteBtnClass} {
        margin: 0;
    }

    .@{textInputClass} {
        display: inline-block;

        input {
            padding-left: 8px;
            height: 28px;

            &:focus {
                outline: none;
            }
        }
    }
}
```

Также необходимо в `_Variables.less` добавить переменные, содержащие в себе основные цвета лукапа. В данном примере рассмотрена одна переменная, отвечающая за цвет границы input`a.

```css
@inputBorderColor: #B9B9B9;
```

В результате всех действий получится стандартный контрол. Далее его можно изменять и расширять в зависимости от темы, в которой он находится.

### Пример на уровне темы `Theme`.

В `Lookup.less` до определения стилей необходимо импортировать `Lookup.less` из `BaseTheme`. Далее можно добавлять новые стили. Все новые отступы, цвета, классы, идентификаторы необходимо вынести в `_Variables.less`, который находится в `Theme`. Также в этот же файл выносятся цвета, использующиеся в контроле. `_Variables.less` необходимо подключить в самом конце - после стилей - для того, чтобы перегружать существующие переменные. Кроме того в `Lookup` - также в самом конце - нужно подключить `_Settings.less`. Этот файл используется для прикладных проектов. Чтобы в нем срабатывали переменные, использующиеся в теме, необходимо импортировать `_Variables.less` как из `Theme` так и из `BaseTheme`.

В результате:

`_Variables.less`

```css
@textColor: red;
@inputFocusedBackgroundColor: yellow;
@inputBorderColor: #000;
@inputTextColor: blue;
```

`Lookup.less`

```css
@import '../../../BaseTheme/Controls/Lookup/Lookup.less';

.@{parentControlClass} {
    div:first-child {
        color: @textColor;
        font-weight: bold;
        font-style: italic;
        font-size: 25px;
    }

    .@{viewBtnClass},
    .@{deleteBtnClass} {
        span {
            background-image: url("lookup-icons.png");
            background-position: 0 0;
            background-repeat: no-repeat;
        }
    }

    .@{deleteBtnClass} {
        border-top-right-radius: 6px;
        border-bottom-right-radius: 6px;

        span {
            background-position: 20% 0;
        }
    }

    .@{textInputClass} {
        input {
            height: 30px;
            border: 1px solid @inputBorderColor;
            border-top-left-radius: 6px;
            border-bottom-left-radius: 6px;
            color: @inputTextColor;

            &:focus {
                background: @inputFocusedBackgroundColor;
            }
        }
    }
}

@import (reference) '_Variables.less';
@import '_Settings.less';
```

Пример `_Settings.less` выглядит следующим образом:

```css
@import (reference) '../../../BaseTheme/Controls/Lookup/_Variables.less';
@import (reference) '_Variables.less';
.@{parentControlClass} {
    .@{deleteBtnClass} {
        span {
            background-position: -65px 100%;
        }
    }
}
```

В результате иконка кнопки удаления будет отображаться иначе.

После полной настройки лукапа, файл `Lookup.less` (в `Theme`) необходимо импортировать в `Base.less`. После компиляции будет получен `Base.css`. При этом будут учтены как правила в базовой теме, так и в отдельной. Кроме того, будут подгруженs все настройки для прикладных проектов и общие настройки для тем.
