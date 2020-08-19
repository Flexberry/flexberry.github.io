---
title: Settings for LookUp in AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Controls)
toc: true
permalink: en/fa_settings-lookup-age.html
lang: en
autotranslated: true
hash: c652a2797eec6056c5aace4b1cb7baf3d22e72324ef38d1cd036e0c574fe2376
---

To specify the settings for [LookUp](fa_lookup-overview.html) used in [AjaxGroupEdit](fa_ajax-group-edit.html) you need to create an instance of the class `LookUpSetting`, inicializirati him the required fields and using the method `AddLookUpSettings` add them to `AjaxGroupEdit`.

For example, you must ask `LookUp` having a property `ТипЛапы` performance, which will be deducted artisans objects.

```csharp
ctrlЛапа.AddLookUpSettings(Information.ExtractPropertyPath<Лапа>(r => r.ТипЛапы), new LookUpSetting { MasterViewName = ТипЛапы.Views.ТипЛапыL.Name });
```

You can also configure `LimitFunction`, and for `MasterEditorDropDown` you can set a preference responsible for `PostBack`.

Full list of properties:

```csharp
        /// <summary> 
        /// Settings lucapa 
        /// </summary> 
        public class LookUpSetting
        {
            /// <summary> 
            /// Do PostBack when changing value 
            /// </summary> 
            public bool EnablePostBack { get; set; }

            /// <summary> 
            /// Restriction for sample masters 
            /// </summary> 
            public Function LimitFunction { get; set; }

            /// <summary> 
            /// The name of the view to load masters 
            /// </summary> 
            public string MasterViewName { get; set; }

            /// <summary> 
            /// The type name to download the masters manually normally should not be set 
            /// </summary> 
            public string MasterTypeName { get; set; }

            /// <summary> 
            /// Autocomplete for MasterEditorAjaxLookup 
            /// </summary> 
            public bool Autocomplete { get; set; }

            /// <summary> 
            / / Search substring for autocomplete 
            /// </summary> 
            public bool IsSubstring { get; set; }

            /// <summary> 
            /// URL form to display the selected object 
            /// </summary> 
            public string ShowObjectUrl { get; set; }
        }
```

### An example of enabling auto-complete LookUp

```csharp
        /// <summary> 
        /// Called the first in the Page_Load. 
        /// </summary> 
        protected override void Preload()
        {
            ctrlReviewers.AddLookUpSettings(Information.ExtractPropertyPath<Reviewer>(r => r.Programmer), new LookUpSetting() { Autocomplete = true });
            ctrlChangesetAuthors.AddLookUpSettings(Information.ExtractPropertyPath<ChangesetAuthor>(ca => ca.Programmer), new LookUpSetting() { Autocomplete = true });
            ctrlProjectForReview.AddLookUpSettings(Information.ExtractPropertyPath<ProjectForReview>(pfr => pfr.Project), new LookUpSetting() { Autocomplete = true });
        }
```

### An example of imposing restrictions on LookUp

An example of imposing restrictions on the LookUp in the AGE described in article [Restrictions to LookUp in AjaxGroupEdit](fa_limited-lookup-age.html).

## Multiple choice LookUp

Information about multiple choice LookUp in AGE can be found in the article [Using multiple choice. in AjaxGroupEdit](fa_multi-lookup-age.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}