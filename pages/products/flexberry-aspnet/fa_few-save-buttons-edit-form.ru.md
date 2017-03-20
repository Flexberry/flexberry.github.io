---
title: Несколько кнопок сохранения на web-форме редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_few-save-buttons-edit-form.html
folder: products/flexberry-aspnet/
lang: ru

---

* **Продукт**: [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент**: [Web-форма редактирования](fa_web-edit-form.html)
* **Предназначение**: добавлениe нескольких кнопок сохранения на [web-форму редактирования](fa_web-edit-form.html).

## Web-форма редактирования

Данная статья описывает алгоритм добавления нескольких кнопок сохранения на [web-форму редактирования](fa_web-edit-form.html).

## Алгоритм добавления нескольких кнопок

* Добавить кнопку в разметку страницы.
* Изменить селекторы в настройках плагина icsEditForm.js.
* Изменить ID всех кнопок на странице так, чтобы они "находились" данным селектором.

### Добавление кнопки в разметку страницы

Чтобы добавить кнопку, достаточно скопировать уже существующую кнопку сохранения и поместить её в нужное место. Она примет следующий вид:

```cs
<asp:ImageButton ID="ImageButton1" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
```

Оставим её в таком виде и перейдем к следующему пункту.