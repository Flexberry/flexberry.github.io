--- 
title: CSS classes WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-css.html 
lang: en 
autotranslated: true 
hash: 446de0e146a616226926e3cc9b2112c661b96cf92e41352e0a0a154031ccef4e 
--- 

The ability to customize the display [WOLV](fa_web-object-list-view.html) via CSS displayed in the internal structure and the CSS classes: 

```html
<div class="ics-wolv-column-header">
    <span class="ics-wolv-column-sort-order">[индекс порядка сортировки]</span>
    <span class="ics-wolv-column-sort-direction (ics-wolv-column-sort-asc|ics-wolv-column-sort-desc)"></span>
    <span class="ics-wolv-column-caption (ics-wolv-column-sort-enabled|ics-wolv-column-sort-disabled)">[подпись столбца]</span>
</div>
``` 

| CSS class | Description | 
| --------- | -------- | 
| `ics-wolv-column-header` | Container column header | 
| `ics-wolv-column-sort-order` | Figure responsible for the sort order | 
| `ics-wolv-column-sort-direction` | design Element showing the sort direction (descending and ascending) | 
| `ics-wolv-column-sort-asc` | design Element showing the direction of sort ascending | 
| `ics-wolv-column-sort-desc` | design Element showing the direction of sorting in descending order | 
| `ics-wolv-column-caption` | Signature column (for the enabled and disabled sorting) | 
| `ics-wolv-column-sort-enabled` | Signature column with sorting enabled | 
| `ics-wolv-column-sort-disabled` | Signature columns with disabled sorting | 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}