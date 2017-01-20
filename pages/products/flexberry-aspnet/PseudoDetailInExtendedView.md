---
title: PseudoDetailInExtendedView
sidebar: product--sidebar
keywords: Ограничения
toc: true
permalink: ru/pseudo-detail-in-extended-view.html
folder: product--folder
lang: ru
---
# PseudoDetailInExtendedView
`PseudoDetailInExtendedView` - структура, используемая для работы [псевдодетейлов в расширенном редакторе ограничений](details-at-adv-limit-editor.html).

```cs
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
```cs
/// <summary>
/// Конструктор.
/// </summary>
/// <param name="pseudoDetailView">Представление, определяющее псевдодетейл.</param>
/// <param name="masterLinkName">Свойство, по которому идёт связь от псевдодетейла к детейлу.</param>
/// <param name="masterToDetailPseudoProperty">Имя псевдосвойства, по которому идёт связь от мастера к детейлу.</param>
public PseudoDetailInExtendedView(View pseudoDetailView, string masterLinkName, string masterToDetailPseudoProperty)
```

----