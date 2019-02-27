--- 
title: pseudocatalase in the advanced editor limitations 
sidebar: flexberry-aspnet_sidebar 
keywords: Constraints 
toc: true 
permalink: en/fa_pseudo-detail-extended-view.html 
lang: en 
autotranslated: true 
hash: efe37b72423006b809fb2d0efa87790ac7613585e985f12dae47b0ee4b8acb9d 
--- 

`PseudoDetailInExtendedView` structure used for work [pseudometal in the advanced editor, limitations](fa_details-limit-editor.html). 

```csharp
/// <summary> 
/// Constructor. 
/// </summary> 
/// <param name="pseudoDetailViewName">the name of the view defining pseudometal.</param> 
/// <param name="pseudoDetailType">the Type of pseudometal.</param> 
/// <param name="masterLinkName">the Property on which the connection from pseudometal to detailu.</param> 
/// <param name="masterToDetailPseudoProperty">the Name of pseudovoigt, which is the connection from master to detaile.</param> 
public PseudoDetailInExtendedView(
	string pseudoDetailViewName, Type pseudoDetailType, string masterLinkName, string masterToDetailPseudoProperty)
``` 

```csharp
/// <summary> 
/// Constructor. 
/// </summary> 
/// <param name="pseudoDetailView">a View that defines pseudometal.</param> 
/// <param name="masterLinkName">the Property on which the connection from pseudometal to detailu.</param> 
/// <param name="masterToDetailPseudoProperty">the Name of pseudovoigt, which is the connection from master to detaile.</param> 
public PseudoDetailInExtendedView(View pseudoDetailView, string masterLinkName, string masterToDetailPseudoProperty)
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/