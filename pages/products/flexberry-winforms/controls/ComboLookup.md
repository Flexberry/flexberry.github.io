---
title: ComboLookup
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/combo-lookup.html
folder: product--folder
lang: ru
---

# [ComboLookup](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_windows_1_1_forms_1_1_combo_lookup.html)
Элемент управления для выбора мастера из комбинированного списка ComboBox.

Основные свойства:
* '''DataObjectType''' – тип мастерового объекта.
* '''MasterPropertyName''' – свойство объекта данных, которое редактируется ComboLookup.
* '''ComboPropertyName''' – мастерового объекта, которое будет отображаться в списке.
* '''CachedData''' – данные читаются из базы при каждом открытии списка или кэшируются.
* '''Limit''' – ограничение на список мастеровых объектов. Следует заметить, что свойство имеет тип FunctionForControls (см. пример).

```
  CSharp
comboLookup1.CachedData = true;
comboLookup1.ComboPropertyName = "Название";
comboLookup1.DataObjectType = typeof(IIS.КошкиСЛапами.Кошка);
comboLookup1.MasterPropertyName = "Порода";

SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;  
Function lf = langdef.GetFunction(langdef.funcLike,   
new VariableDef(langdef.StringType, "Название"), "Перс%");
comboLookup1.Limit = new FunctionForControls("ПородаL", lf);
```

# Изменение типа лукапа с Default на Combo
Если форма уже существует, то при попытке изменения типа у существующего лукапа изменения '''не применятся'''. 

Чтобы изменения вступили в силу необходимо вручную удалить весь код, связанный с контролом, из кода страницы и запустить перегенерацию.
