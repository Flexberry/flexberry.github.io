--- 
title: Settings for LookUp in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_settings-lookup-age.html 
lang: en 
autotranslated: true 
hash: 9fc1705aec1e57d7eb4ab4d930bdf107f687f925707d6b3d6dca154b9f7905ca 
--- 

# description of the algorithm 

To specify the settings for [LookUp's](fa_lookup-overview.html) used in [AjaxGroupEdit](fa_ajax-group-edit.html) you need to create an instance of the class `LookUpSetting`, inicializirati him the required fields and using the method AddLookUpSettings add them to [AjaxGroupEdit](fa_ajax-group-edit.html). 

For example, you must ask [LookUp'y](fa_lookup-overview.html), which in the view has a property "`ТипЛапы`" presentation, which will be deducted artisans objects. 

```csharp
ctrlЛапа.AddLookUpSettings(Information.ExtractPropertyPath<Лапа>(r => r.ТипЛапы), new LookUpSetting { MasterViewName = ТипЛапы.Views.ТипЛапыL });
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

### an Example of enabling auto-complete LookUp: 

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

### Example of imposing restrictions on LookUp 

An example of imposing restrictions on [LookUp](fa_lookup-overview.html) [AGE](fa_ajax-group-edit.html) is described in this [article](fa_limited-lookup-age.html). 

## Multiple choice LookUp 

Information about multiple choice LookUp [AGE](fa_ajax-group-edit.html) can be read in [the article Using multiple choice. in AjaxGroupEdit](fa_multi-lookup-age.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}