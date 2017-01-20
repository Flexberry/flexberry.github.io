---
title: Несколько кнопок сохранения на web-форме редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_few-save-buttons-edit-form.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Web-форма редактирования](web-edit-form.html)
* '''Предназначение''': добавлениe нескольких кнопок сохранения на [web-форму редактирования](web-edit-form.html).
</td>
</tr></tbody></table></a>
</div>



# Web-форма редактирования
Данная статья описывает алгоритм добавления нескольких кнопок сохранения на [web-форму редактирования](web-edit-form.html).

# Алгоритм добавления нескольких кнопок
* Добавить кнопку в разметку страницы.
* Изменить селекторы в настройках плагина icsEditForm.js .
* Изменить ID всех кнопок на странице так, чтобы они "находились" данным селектором.

## Добавление кнопки в разметку страницы
Чтобы добавить кнопку, достаточно скопировать уже существующую кнопку сохранения и поместить её в нужное место. Она примет следующий вид:
```
<asp:ImageButton ID="ImageButton1" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
```

Оставим её в таком виде и перейдем к следующему пункту.

## Изменение селекторов
Подробнее о настройках плагина формы редактирования можно посмотреть в [этой статье](ics-edit-form-configuration.html).

Добавим на страницу следующий код:

```
        /// <summary>
        /// Переопределение настроек инициализации плагина
        /// </summary>
        public override object PluginInitSettings
        {
            get 
            { 
                return new
                    {
                        saveBtnSelector = "input[id$=\"SaveBtn\"]"
                    };
            }
        }
```

(((<msg type=note>Если необходимо добавить кнопку "Сохранить и закрыть", необходимо переопределить `saveAndCloseBtnSelector`.</msg>)))

## Изменение ID кнопок на форме
По умолчанию, saveBtnSelector "ищет" кнопки, ID которых оканчивается на '''_SaveBtn''' (не забываем, что реально на странице он превратится в '''ContentPlaceHolder1_SaveBtn'''). 

На предыдущем шаге мы переопределили его так, чтобы он "искал" ID, оканчивающиеся на '''SaveBtn'''. 

Таким образом, можно не трогать ID уже существующей кнопки, но надо изменить ID добавленной нами на 1 шаге кнопки следующим образом:

```

<asp:ImageButton ID="BottomSaveBtn" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
```

(((<msg type=note>В качестве ID можно использовать любой ID, который удовлетворит селектору "input&#0091;id$=\"SaveBtn\"&#0093;".</msg>)))

