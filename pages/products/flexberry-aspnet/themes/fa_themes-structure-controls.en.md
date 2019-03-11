--- 
title: Structure of the Flexberry ASP.NET for controls 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_themes-structure-controls.html 
lang: en 
autotranslated: true 
hash: 1973f3ac8a455783c947a669c4099c866d16d6e911380bd719e12c0298ee621f 
--- 

## file Structure 

To edit those, created a separate base theme `BaseTheme` that contain standard styles and settings common to all themes and can be overridden in each. In this paper, we focus on an example implementation of one control and will not be affected shape. 

The file structure in a General view looks so: 


* - BaseTheme 
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

In this structure example, there is only one theme `Theme`, which contains a single control `SomeControl`. That means each file will be discussed below. 

## Level-BaseTheme 

Inside the base theme folder is stored `Controls`, which contains any controls. In this case, a single control `SomeControl`. Inside this folder contain a folder `Images` that stores pictures for control and two .less file. 

`_Variables.less` - contains a set of variables for use in styles. Variables include: class names and IDs, colors, and indentation. 

`SomeControl.less` - contains standard styles for the control. These include the location of elements on the page, standard colors, and indentation. 

## Level Theme 

`Theme` corresponds to one of the existing themes. Inside the theme, as in the case of `BaseTheme`, a folder is stored `Controls`, which contains `SomeControl`. Inside this folder there is a folder `Images` that contains images specifically for the control and three .less file. 

The purpose `_Variables.less` and `SomeControl.less` remains unchanged. File `_Settings.less` is used to override existing styles and improvements in applied projects 

## Implementation on the example control Lookup 

As an example, will be considered simple lookup consisting of `input` type `text` and two buttons. 

The file structure will look as follows: 

* - BaseTheme 
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

For a start, will be discussed the implementation at the level of basic themes. All class names must be put into variables and add to `_Variables.less`. This file must be imported into `Lookup.less` at the beginning - to define styles - keyword (reference) allows you to import the contents of the file, not add it physically. This allows us not to import the same record several times. 

```css
@parentControlClass: control;
@textInputClass: txtInput;
@viewBtnClass: viewBtn;
@deleteBtnClass: deleteBtn;

@inputBorderColor: #B9B9B9;
@btnHoveredBackgroundColor: #f0f0f0;
``` 

Further, in `Lookup.less` need to register the default styles for a control, namely the base color, location of elements on the page. The end result is something like this: 

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

You also need to `_Variables.less` add variables containing the basic colors lucapa. In this example, one variable is considered responsible for the color of the border input`a. 

```css
@inputBorderColor: #B9B9B9;
``` 

As a result of all of the action happens standard control. Further, it is possible to change and extend depending on the topic, in which it is located. 

### Example at the level of themes `Theme`. 

In `Lookup.less` to the style definitions you want to import `Lookup.less` of `BaseTheme`. Next, you can add new styles. All new padding, colors, classes, IDs should be taken in `_Variables.less`, which is `Theme`. Also in this same file are submitted to the colors used in control. `_Variables.less` you need to connect at the very end - after styles in order to overload the existing variables. Additionally, `Lookup` - in the end - you need to connect `_Settings.less`. This file is used for applied projects. To work the variables used in the theme, you need to import `_Variables.less` as of `Theme` and `BaseTheme`. 

The result: 

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

Example `_Settings.less` as follows: 

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

As a result, the icon of the button will display differently. 

After full adjustment lucapa, file `Lookup.less` (`Theme`) must be imported into `Base.less`. After compilation will be obtained `Base.css`. Will be thus considered as rules in the basic theme and. In addition, we will podprugin all the settings on applied projects and General settings for the. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}