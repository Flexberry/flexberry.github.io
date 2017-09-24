---
title: Фокус ввода и «Ctrl+S»
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_focus-and-ctrl-s.html
folder: products/flexberry-winforms/
lang: ru
---

При сохранении формы редактирования по '''«Ctrl+S»''' фокус ввода переходил на кнопку сохранения. Скорее всего, это было сделано для сохранения значение активного контрола в объект данных, т.е. срабатывания биндинга.<br>
В соответствии с заданием № 2129 в код базовой формы редактирования внесены изменения: при сохранении по комбинации '''«Ctrl+S»''' фокус остается на активном контроле, текущее значение попадает в объект данных с помощью вызова `EditManager.ApplyDataFromControl(ActiveControl)`.<br>
