---
title: reporting Forms
keywords: report, reporting form, print
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_report.html
lang: en
autotranslated: true
hash: 0e8738c18bb57c289b0a4cb006c66cf8cb1f8e7d74b211b09f18a6b367ba5024
summary: Description of reporting forms, with recommendations for use.
---

## To generate the report. General information

Often, the user need to create the report with certain forms. This involves unloading all the fields and their values in a specific pattern. On forms where there is a possibility, there is a special button, which is on the toolbar. It looks the same for all forms:

!Button [generate report](/images/pages/guides/ui-ux-guideline/uiuxg_report/report_button.png)

When pressed, POPs up a special menu: "configuration report".

## Generating a report with edit form

When you click setup upload report in the edit form opens side menu with the options:

![Edit form](/images/pages/guides/ui-ux-guideline/uiuxg_report/edit_form.png)

In the settings menu you can select the display order of fields, their availability, and special pre-formed document template. In this template field is a certain way to reflect the necessary additional information. Similarly, the report templates in the System 1C» qmo.

## Generating a report with a list of the form

When you click setup upload report to list the form have a side menu with options similar to the edit form:

![List form](/images/pages/guides/ui-ux-guideline/uiuxg_report/list_form.png)

It also has the ability to customize the availability and order of display of the information, and the ability to select the number of paged records.

## Generating a report from a special form with parameters

This type of reporting requires a separate page with a set of parameters for reporting a certain type, for example, the report form on the state registration of death:

![Form of report of deaths](/images/pages/guides/ui-ux-guideline/uiuxg_report/death_form.png)

The radio button is responsible for choosing the format and method of formation (the date of registration or receipt) of the report. With the help of two calendar forms chosen period for which you want to generate the report. Below there is a selection of the Information System and the selection checkboxes required by the OCS. After clicking Print» «unloads the generated report to the user's computer based on the selected parameters.

![Template print report deaths](/images/pages/guides/ui-ux-guideline/uiuxg_report/death_report.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}