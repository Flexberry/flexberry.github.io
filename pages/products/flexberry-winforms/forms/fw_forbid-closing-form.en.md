---
title: Как запретить закрытие фомы Flexberry Winforms
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: Пример как запретить закрытие фомы Flexberry Winforms
toc: true
permalink: en/fw_forbid-closing-form.html
folder: products/flexberry-winforms/
lang: en
---

Для того, чтобы запретить закрытие формы, надо переопределить метод `Finalize()` независимой формы.

Пример:

```csharp
[Scriptizer.RunTime.ScriptFinalizer]
public override bool Finalize()
{
return false;
}
```