---
title: creating a generic report
sidebar: flexberry-platform_sidebar
keywords:
toc: true
permalink: en/fp_create-uni-report.html
lang: en
autotranslated: true
hash: a917356d5f75bd55dd97a4ee5857f93effe68dbd197548c89b4a70b02a441b0a
summary:
---

User guide for the drafting of the report is located at \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem (currently it is incomplete and somewhat out of date).
To work flexible reporting, you must use DLL and templates located in the \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem\StableVersion.

## Preparation for the application database

To create the desired database structure of the application should:

1. To execute the script CreateTables.sql, which is located at \\storm\CASEBERRY Distr\CASEBERRY Subsystems\ReportsSystem\

2. To execute the commands update the database specified [StormReport_ParameterObjectTypeAndparameterobjectformtype|here).

3. [StatitorEnvironmentStart|Start environment of Statitory through shortcuts on the desktop). To implement paragraphs 1, 2 and 3 (paragraph 3 is StatitorReport you want to add a report UniReport from the library ICSSoft.STORMNET.Reports.UniReports.dll).

## Creating generic report

To create a generic report should:

1. [FiltersandLimits|Hold configure filters) - class for objects which will be drawn up the report.

2. From `ICSSoft.STORMNET.Reports.ListformIntegration.ToolbarMenu.dll` in the form of added component ListformReportsMenu, custom property `TargetObjectListView` (this [ObjectListView) in the toolbar which will appear ListformReportsMenu) and `FilterSettingName` (the name of the filter settings of the corresponding class).

3. Add interesting types of reports:

``` csharp
public class WinformC__КлиентL : ICSSoft.STORMNET.UI.BaseWinListStandard, IIS.TryFilter.DPDIC__КлиентL
{
//... 
public WinformC__КлиентL()
  {
  //... 
  #region Универсальные отчеты
    var uniCombiner = new ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.UniListReportCombiner { ReportsTitle = A "universal records" };
    var uniStatCombiner = new ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.UniStatListReportCombiner { ReportsTitle = "Statistical reports" };
    reportsMenu.ReportCombiners = new ICSSoft.STORMNET.Reports.ListformIntegration.Base.IListReportCombiner[) { uniCombiner, uniStatCombiner };
  #endregion Универсальные отчеты
  //... 
  }
}
```

A required dll plug-in:

* ICSSoft.STORMNET.Reports.ListformIntegration.Base.dll
* ICSSoft.STORMNET.Reports.ListformIntegration.UniReports.dll

As a result, the corresponding [Form-classes-with-stereotype-listform|list form)will appear in the toolbar button:

[image|Image|{UP(CreateUniReport)}UniReport.png)

4.[Running-UniReports|start media report) it is necessary to connect dll:
ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll (there is in the standard distribution of Caseberry).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}