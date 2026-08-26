---
title: Настройки для LookUp в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_settings-lookup-age.html
lang: ru
---

Для того чтобы задать настройки для [LookUp](fa_lookup-overview.html), используемых в [AjaxGroupEdit](fa_ajax-group-edit.html) нужно создать экземпляр класса `LookUpSetting`, инициализировав ему необходимые поля и при помощи метода `AddLookUpSettings` добавить их в `AjaxGroupEdit`.

Например, необходимо задать `LookUp`, имеющему свойство `ТипЛапы` представление, по которому будут вычитываться мастеровые объекты.

```csharp
ctrlЛапа.AddLookUpSettings(Information.ExtractPropertyPath<Лапа>(r => r.ТипЛапы), new LookUpSetting { MasterViewName = ТипЛапы.Views.ТипЛапыL.Name });
```

Также можно настраивать `LimitFunction`, а для `MasterEditorDropDown` можно задать настройку, отвечающую за `PostBack`.

Полный список свойств:

```csharp
        /// <summary>
        /// Настройки лукапа
        /// </summary>
        public class LookUpSetting
        {
            /// <summary>
            /// Делать ли PostBack при смене значения
            /// </summary>
            public bool EnablePostBack { get; set; }

            /// <summary>
            /// Ограничение на выборку мастеров
            /// </summary>
            public Function LimitFunction { get; set; }

            /// <summary>
            /// Имя представления для загрузки мастеров
            /// </summary>
            public string MasterViewName { get; set; }

            /// <summary>
            /// Имя типа для загрузки мастеров, вручную обычно не должно задаваться
            /// </summary>
            public string MasterTypeName { get; set; }

            /// <summary>
            /// Autocomplete для MasterEditorAjaxLookup
            /// </summary>
            public bool Autocomplete { get; set; }

            /// <summary>
            /// Поиск по подстроке - для автокомплита
            /// </summary>
            public bool IsSubstring { get; set; }

            /// <summary>
            /// URL формы для просмотра выбранного объекта
            /// </summary>
            public string ShowObjectUrl { get; set; }
        }
```

### Пример включения автозаполнения в LookUp

```csharp
        /// <summary>
        /// Вызывается самым первым в Page_Load.
        /// </summary>
        protected override void Preload()
        {
            ctrlReviewers.AddLookUpSettings(Information.ExtractPropertyPath<Reviewer>(r => r.Programmer), new LookUpSetting() { Autocomplete = true });
            ctrlChangesetAuthors.AddLookUpSettings(Information.ExtractPropertyPath<ChangesetAuthor>(ca => ca.Programmer), new LookUpSetting() { Autocomplete = true });
            ctrlProjectForReview.AddLookUpSettings(Information.ExtractPropertyPath<ProjectForReview>(pfr => pfr.Project), new LookUpSetting() { Autocomplete = true });
        }
```

### Пример наложения ограничения на LookUp

Пример наложения ограничения на LookUp в AGE описан в статье [Ограничение для LookUp в AjaxGroupEdit](fa_limited-lookup-age.html).

## Множественный выбор по LookUp

Информацию о множественном выборе по LookUp в AGE можно прочитать в статье [Использование множественного выбора в LookUp в AjaxGroupEdit](fa_multi-lookup-age.html).
