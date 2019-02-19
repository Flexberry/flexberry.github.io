---
title: Фокус ввода и «Ctrl+S»
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Указана особенность поведения формы редактирования при сохранении по комбинации клавиш __«Ctrl+S»__ 
toc: true
permalink: ru/fw_focus-and-ctrl-s.html
folder: products/flexberry-winforms/
lang: ru
---

При сохранении формы редактирования по комбинации __«Ctrl+S»__ фокус остается на активном контроле, текущее значение попадает в объект данных с помощью вызова `EditManager.ApplyDataFromControl(ActiveControl)`.<br>
