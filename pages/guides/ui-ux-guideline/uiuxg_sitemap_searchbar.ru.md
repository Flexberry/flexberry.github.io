---
title: Руководство к компоненту "Flexberry-sitemap-searchbar"
keywords: навигация, поиск по сайту, боковое меню, боковая страница
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: ru/uiuxg_sitemap_searchbar.html
lang: ru
summary: Руковдство по применению компонента flexberry-sitemap-searchbar.
---

## Sitemap Searchbar

Компонент {% raw %}{{#flexberry-sitemap-searchbar}}{% endraw %} позволяет совершать поиск по страницам в веб-приложении. Наилучшее место, где его можно внедрять, это боковое меню веб-приложения (sitemap.hbs).  

### Пример использования с развернутым боковым меню

![Картинка использования 1](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\1.png)

![Картинка использования 2](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\2.png)

![Картинка использования 3](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\3.png)

### Пример использования со свернутым боковым меню

![Картинка использования 4](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\4.png)

![Картинка использования 5](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\5.png)

![Картинка использования 6](../../../images\pages\guides\ui-ux-guideline\uiuxg_sitemap_searchbar\6.png)

### Структура компонента 📝

Компонент включает в себя следующие элементы:

* Строку поиска (id="sitemap-search")
* Блок с выводимыми результатами  (id="results-list")
* Блок с результатами формируется из подкомонентов {% raw %}{{#searchbar-node}}{% endraw %}, которые вызывают друг друга рекурсивно.

### Использование компонента 🔮

Единственное свойство, которое необходимо передать компоненту, это объект с деревом-сайтмапа.

```
{% raw %}{{flexberry-sitemap-searchbar
treeContent=model.nodes}}{% endraw %}
```

**treeContent** - массив объектов, по которым ведётся поиск. Данный массив имеет древовидный вид (N-Array tree), где неизвестно, какой глубины/ ширины этот объект.
![img](https://media.geeksforgeeks.org/wp-content/uploads/20190612120758/generic-tree_gfg.png)

### Структура массива treeContent 🧱

Данный компонент позволяет вести поиск по дереву любой глубины и ширины, однако, дерево должно придерживаться определенной структуры.
Тип дерева - массив. Объект в дереве является узлом, и может иметь безграничное количество "детей" (тип данных - массив). Главные свойства узла:

* "link" - **string** ИЛИ **Null** (если это вкладка), ссылка на страницу в приложении.
Пример использования:

```javascript
"link": "components-examples/flexberry-button/settings-example"
```

* "caption" - **object**, человекочитаемое название объекта, которое будет отображаться в поиске.
*Пример использования:

```javascript
"caption": {
    "string": "Пример работы с компонентом"
}*)
```

* "children" - **array** ИЛИ **Null** (если это ссылка), следующий узел в дереве, который включает в себя объекты с вышеупомянутыми свойствами.
Пример:

```javascript
"children": [
{ //Первый подузел-ссылка
        "link": "index",
        "caption": {
            "string": "Главная"
        },
        "title": {
            "string": ""
        },
        "children": null
    }, 
{ // Второй подузел-вкладка
        "link": null, // Так как это вкладка, у него нет ссылки
        "caption": {
            "string": "Приложение"
        },
        "title": {
            "string": ""
        },
        "children": [...] // Вложенный узел
    }]
```
