---
title: Position form on the screen
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (forms)
summary: Features using properties of shapes and OutScreenControl VisiblePercent responsible for the control of the exit window outside the screen
toc: true
permalink: en/fw_base-win-position.html
lang: en
autotranslated: true
hash: 68b802d38886c0a2f5b6d44eb9bf19a749a5e03d8850e12c008bdba73dc94d9f
---

In some cases, restoring the position of a form led to the fact that the form was not visible on the screen. This situation for example occurs when you change the screen resolution to a lower one, with a new permission form could be outside the visible area.

To control the position of the form in Flexberry Platform class `BaseWin`, which is the basic of all forms, were added two properties `OutScreenControl` and `VisiblePercent`.

Property `OutScreenControl` allows you to enable the control of the exit window outside the screen in the process of moving Windows user. Property `VisiblePercent` sets the percentage of the form size (length/width), which must remain visible (the range of values from 0 to 100).

Comments:

1. When the form is loaded the controls outside of the screen is regardless of the value of the property `OutScreenControl` (for correct positioning of the forms when changing resolution). The initial display of the form takes into account the value `VisiblePercent`, the default value of the property `VisiblePercent` equal to __40__.

2. The current implementation does not consider multiple monitors.





{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}