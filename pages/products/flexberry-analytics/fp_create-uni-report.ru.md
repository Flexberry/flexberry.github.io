---
title: Создание универсальных отчётов
sidebar: flexberry-platform_sidebar
keywords: 
toc: true
permalink: ru/fp_create-uni-report.html
lang: ru
summary: 
---

Руководство пользователя по составлению отчётов находится по адресу \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem (на настоящий момент оно неполное и несколько устарело).
Для работы универсальных отчётов необходимо использовать DLL и шаблоны, находящиеся в папке \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem\StableVersion.

## Подготовка БД приложения

Для создания нужной структуры БД приложения необходимо:

1. Выполнить скрипт CreateTables.sql, который находится по адресу \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem\

2. Выполнить команды обновления БД, указанные [StormReport_ParameterObjectTypeAndParameterObjectFormType|здесь).

3. [StatitorEnvironmentStart|Запустить среду Статитора через ярлыки на рабочем столе). Выполнить пункты 1,2 и 3 (в пункте 3 вместо StatitorReport необходимо добавить отчет UniReport из библиотеки ICSSoft.STORMNET.Reports.UniReports.dll).

## Создание универсальных отчётов

Для создания универсальных отчётов необходимо:

1. [FiltersandLimits|Проводим настройку фильтров) класса, для объектов которого будет составляться универсальный отчёт.

2. Из `ICSSoft.STORMNET.Reports.ListformIntegration.ToolbarMenu.dll` на форму добавляем компонент ListformReportsMenu, настраиваем его свойство `TargetObjectListView` (это [ObjectListView), в тулбаре которого появится ListformReportsMenu) и `FilterSettingName` (название настройки фильтра соответствующего класса).

3. Добавляем интересующие типы отчётов:

``` csharp
public class WinformC__КлиентL : ICSSoft.STORMNET.UI.BaseWinListStandard, IIS.TryFilter.DPDIC__КлиентL
{
//...
public WinformC__КлиентL()
  {
  //...
  #region Универсальные отчеты
    var uniCombiner = new ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.UniListReportCombiner { ReportsTitle = "Универсальные отчеты" };
    var uniStatCombiner = new ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.UniStatListReportCombiner { ReportsTitle = "Статистические отчеты" };
    reportsMenu.ReportCombiners = new ICSSoft.STORMNET.Reports.ListformIntegration.Base.IListReportCombiner[) { uniCombiner, uniStatCombiner };
  #endregion Универсальные отчеты
  //...
  }
}
```

Подключаем необходимые dll:

* ICSSoft.STORMNET.Reports.ListformIntegration.Base.dll
* ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.dll

В результате на соответствующей [Формы-списка-классы-со-стереотипом-listform|списковой форме)в тулбаре появится дополнительная кнопка:

[image|Изображение|{UP(CreateUniReport)}UniReport.png)

4.Для [Running-UniReports|запуска универсального отчёта) необходимо подключить dll:
ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll (есть в стандартной поставке Caseberry).
