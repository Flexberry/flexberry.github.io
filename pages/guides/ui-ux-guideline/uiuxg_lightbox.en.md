---
title: Lightbox
keywords: lightbox, window, notification
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_lightbox.html
lang: en
autotranslated: true
hash: 09a8b8f0c2d94d6d6d22936144090a4cccff9b788731248201ded554ac813beb
summary: design Rules pop-up modal Windows. Their combination and order of appearance.
---

Form lightbox centered content. The background overlay is clickable, on click the form is closed. A required element of the cross closure (can be disabled if the form cannot be closed).

For light boxes better to use a fixed width for consistency. For example, the shape of the size **S** = `480px`, **M** = `720px`, etc. to Choose the width you need depending on the content of the form. The height depends on content, on your tablet and mobile form adapted to the screen.

>Lightbox **must not have a scrolling**. For long forms you need to use aidpage.

### Desktop

![Lightbox - Desktop](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/1.png)

### Tablet

![Lightbox Tablet](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/2.png)

### Smartphone

![Lightbox - Smartphone](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/3.png)

## Scrolling lightbox

>If the lightbox is not included on the screen, then scrolling should not appear inside the shape. The entire area of the overlay should be scrolled, and scrolling the main page is blocked.

![Scrolling lightbox](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/4.png)

## Combination boxes

>Lightboxes **are not nested in one another**. If you avoid a few of the light boxes cannot, they should overlap each other with the overlay. The scroll (if any) works only at the top of the lightbox, the rest it is locked.

![Combination of lightboxes](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/5.png)

## Combination with aidpages

![Lightboxes and midpage](/images/pages/guides/ui-ux-guideline/uiuxg_lightbox/6.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}