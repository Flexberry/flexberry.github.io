---
title: Как запретить закрытие фомы Flexberry Winforms
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_forbid--closing--form.html
folder: products/flexberry-winforms/
lang: ru
---

## Как запретить закрытие фомы Flexberry Winforms

Для того, чтобы запретить закрытие формы, надо переопределить метод `Finalize()` независимой формы.

Пример:

```cs
[Scriptizer.RunTime.ScriptFinalizer]
public override bool Finalize()
{
return false;
}
```