---
title: Работа с псевдодетейлами в расширенном редакторе ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Ограничения
toc: true
permalink: ru/fa_pseudo-detail-extended-view.html
lang: ru
---

`PseudoDetailInExtendedView` - структура, используемая для работы [псевдодетейлов в расширенном редакторе ограничений](fa_details-limit-editor.html).

```csharp
/// <summary>
/// Конструктор.
/// </summary>
/// <param name="pseudoDetailViewName">Имя представления, определяющего псевдодетейл.</param>
/// <param name="pseudoDetailType">Тип псевдодетейла.</param>
/// <param name="masterLinkName">Свойство, по которому идёт связь от псевдодетейла к детейлу.</param>
/// <param name="masterToDetailPseudoProperty">Имя псевдосвойства, по которому идёт связь от мастера к детейлу.</param>
public PseudoDetailInExtendedView(
	string pseudoDetailViewName, Type pseudoDetailType, string masterLinkName, string masterToDetailPseudoProperty)
```

```csharp
/// <summary>
/// Конструктор.
/// </summary>
/// <param name="pseudoDetailView">Представление, определяющее псевдодетейл.</param>
/// <param name="masterLinkName">Свойство, по которому идёт связь от псевдодетейла к детейлу.</param>
/// <param name="masterToDetailPseudoProperty">Имя псевдосвойства, по которому идёт связь от мастера к детейлу.</param>
public PseudoDetailInExtendedView(View pseudoDetailView, string masterLinkName, string masterToDetailPseudoProperty)
```
