---
title: Как запретить закрытие фомы Flexberry Winforms
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/forbid--closing--form.html
folder: product--folder
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