--- 
title: Font icons 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_font-icons.html 
lang: en 
autotranslated: true 
hash: b93b625f77d9fbdcb27320f90c4b3e922c77620448fbc1bfccd614481ce3252d 
--- 

## Description 

Font icons (icon font) is a font which contains a set of icons on various subjects. 

Advantages: 

* variability of color, and other properties of the cascade стилей; 
* resize images without losing качества; 
* a sufficient number of icons, made in the same style. 

## Insert icons using the markup 

In the technological styles of embedded font icons from [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/). 

To add icons you must do the following: 

* go to the website [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/) and choose иконку; 
* go to the description of the icons and see the name класса; 
* insert the markup tag with the appropriate классами; 
* set styles for the icons (color, size, etc.). 

# Insert icons using only styles 

```css
.element-class {
   display: inline-block;
   font: normal normal normal 14px/1 FontAwesome;
   text-rendering: auto;
   text-decoration: none;
   vertical-align: middle;
   -webkit-font-smoothing: antialiased;
   -moz-osx-font-smoothing: grayscale;
   transform: translate(0, 0);
}

.element-class:before {
   content: "\f083";
}
``` 

Icon code can be found on the [Font Awesome](http://fortawesome.github.io/Font-Awesome/icons/). 

{% include note.html content="a Font icons are inserted inside the parent element before (:before) and after (:after) content, as a pseudo-element. Hence the obvious conclusion that pseudo-elements apply only to elements which HAVE CONTENT. Elements that have no closing tag (for example, input, img), and, accordingly, have no content, no pseudo-elements will not be created." %} 

## Error in the console browser 

To fix the error, you need to add MIME type in config with the following contents: 

```xml
<system.webServer>    
   <staticContent>
      <remove fileExtension=".woff" />
      <remove fileExtension=".woff2" />
      <mimeMap fileExtension=".woff" mimeType="application/x-font-woff" />
      <mimeMap fileExtension=".woff2" mimeType="application/x-font-woff2" />
    </staticContent>
</system.webServer>
``` 

## Hide font icons 

If Your project have their own icons that You don't want to lose for whatever reason, then You need to hide them in the following way. 

```css
// Icons WOLV'and 
.ics-wolv-content .actions .ics-wolv-row-button,
.ics-wolv-toolbar .ics-wolv-toolbar-button .ics-wolv-toolbar-button-icon,
.miniToolbar .actions a {
    display: inline-block;
    background-repeat: no-repeat;
    transform: none;
    //background: url(../images/icon.png); // path to file with icons should be specified in Your selectors 
}

.ics-wolv-row-button {
    margin: 0 3px;
    width: 24px;
    height: 24px;
}

.ics-wolv-toolbar-button-icon,
.miniToolbar a {
  margin: 0;
  width: 16px;   // width and height might exist in another selector 
  height: 16px;
  vertical-align: middle;
}

.ics-wolv-toolbar-button-limit-edit .ics-wolv-toolbar-button-icon {
    padding: 0;
    margin: 0;
    height: 16px;
}

.ics-wolv-content .actions .ics-wolv-row-button:before,
.ics-wolv-toolbar .ics-wolv-toolbar-button .ics-wolv-toolbar-button-icon:before,
.miniToolbar .actions a:before {
    content: none;
}

.ics-wolv td.tableCellFilter .clrFilters:before,
.ics-wolv td.tableCellFilter .clrFilters:after {
    content: none;
}

// Icons lookup'and 
.ics-form-edit .ics-lookup-btn {
    background: url(../images/icon.png);
    top: 6px;     // the top margin, width, height depend on Your icons 
    width: 13px;
    height: 18px;
}

.ics-lookup .ics-lookup-btn.ics-lookup-btn-clear:before, 
.ics-lookup .ics-lookup-btn.ics-lookup-btn-lookup:before {
    content: none;
}
``` 

## Examples of usage 

[Examples of usage.](http://fortawesome.github.io/Font-Awesome/examples/)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}