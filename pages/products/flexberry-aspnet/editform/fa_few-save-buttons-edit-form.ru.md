---
title: Несколько кнопок сохранения на web-форме редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_few-save-buttons-editform.html
lang: ru
---

Данная статья описывает алгоритм добавления нескольких кнопок сохранения на [web-форму редактирования](fa_editform.html).

## Алгоритм добавления нескольких кнопок

* Добавить кнопку в разметку страницы.
* Изменить селекторы в настройках плагина icsEditForm.js.
* Изменить ID всех кнопок на странице так, чтобы они "находились" данным селектором.

### Добавление кнопки в разметку страницы

Чтобы добавить кнопку, достаточно скопировать уже существующую кнопку сохранения и поместить её в нужное место. Она примет следующий вид:

```csharp
<asp:ImageButton ID="ImageButton1" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
```

Оставим её в таком виде и перейдем к следующему пункту.

### Изменение селекторов

Подробнее о настройках плагина формы редактирования можно посмотреть в [этой статье](fa_editform-configuration.html).

Добавим на страницу следующий код:

```csharp
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

{% include note.html content="Если необходимо добавить кнопку `Сохранить и закрыть`, необходимо переопределить `saveAndCloseBtnSelector`. " %}

### Изменение ID кнопок на форме

По умолчанию, saveBtnSelector "ищет" кнопки, ID которых оканчивается на **SaveBtn** (не забываем, что реально на странице он превратится в **ContentPlaceHolder1_SaveBtn**). 

На предыдущем шаге мы переопределили его так, чтобы он "искал" ID, оканчивающиеся на **SaveBtn**. 

Таким образом, можно не трогать ID уже существующей кнопки, но надо изменить ID добавленной нами на 1 шаге кнопки следующим образом:

```csharp
<asp:ImageButton ID="BottomSaveBtn" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
```

В качестве ID можно использовать любой ID, который удовлетворит селектору "input[id$=\"SaveBtn\"]".
