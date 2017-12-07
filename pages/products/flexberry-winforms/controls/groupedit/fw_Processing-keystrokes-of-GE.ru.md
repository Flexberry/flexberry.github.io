---
title: Обработка нажатий клавиш контролами в GE
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Описан интерфейс `ISpecialKeysEditable`, реализация которого контролом для редактирования позволяет передать из GroupEdit этому контролу нажатые клавиши, не являющиеся алфавитно-цифровыми.
toc: true
permalink: ru/fw_processing-keystrokes-of-ge.html
folder: products/flexberry-winforms/
lang: ru
---

Интерфейс `ISpecialKeysEditable` предназначен для передачи контролам редактирования нажатых клавиш, не являющихся алфавитно-цифровыми. Например, контрол при нажатии `F3` поднимает список для выбора значения. Обработчик, определённый в контроле, срабатывает только, когда контрол находится в состоянии редактирования. Реализация контролом `ISpecialKeysEditable` позволяет предать нажатые клавиши в контрол из GE. При нахождении фокуса на ячейке GE и нажатии указанных клавиш контрол перейдет в режим редактирования, и затем ему будут переданы нажатые клавиши.
Интерфейс содержит единственный метод `List<Keys> GetSpecialEditKeys()`, который должен вернуть список обрабатываемых контролом сочетаний. 

Пример реализации интерфейса представлен ниже. Обрабатываются сочетания `F2+Shift+Ctrl` и `F3`.

```csharp
        #region ISpecialKeysEditable Members

        public List<Keys> GetSpecialEditKeys()
        {
            return new List<Keys> { Keys.F2 | Keys.Shift| Keys.Control , Keys.F3 };
        }

        #endregion
```
