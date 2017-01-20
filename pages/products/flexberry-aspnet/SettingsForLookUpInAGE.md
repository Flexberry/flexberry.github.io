---
title: Настройки для LookUp в AGE
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/settings-for-look-up-in-a-g-e.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Платформа''': [FlexberryASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Встраивание прикладных контролов в AjaxGroupEdit](a-g-e-applied-controls.html)
* '''Предназначение''': Возможность настраивать [LookUp](look-up--overview.html) находящийся в  [AjaxGroupEdit](ajax-group-edit.html). 
</td>
</tr></tbody></table></a>
</div>



# Описание алгоритма
Для того чтобы задать настройки для [LookUp'ов](look-up--overview.html), используемых в [AjaxGroupEdit](ajax-group-edit.html) нужно создать экземпляр класса `LookUpSetting`, инициализировав ему необходимые поля и при помощи метода AddLookUpSettings добавить их в [AjaxGroupEdit](ajax-group-edit.html).

Например, необходимо задать [LookUp'у](look-up--overview.html), который в представлении имеет свойство "`ТипЛапы`" представление, по которому будут вычитываться мастеровые объекты.

```cs
ctrlЛапа.AddLookUpSettings(Information.ExtractPropertyPath<Лапа>(r => r.ТипЛапы), new LookUpSetting { MasterViewName = ТипЛапы.Views.ТипЛапыL });
```
Также можно настраивать `LimitFunction`, а для `MasterEditorDropDown` можно задать настройку, отвечающую за `PostBack`.

Полный список свойств:
```cs
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

## Пример включения автозаполнения в LookUp:
```cs
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

## Пример наложения ограничения на LookUp
Пример наложения ограничения на [LookUp](look-up--overview.html) в [AGE](ajax-group-edit.html) описан в этой [статье](limited-look-up-in-a-g-e.html).

# Множественный выбор по LookUp
Информацию о множественном выборе по LookUp в [AGE](ajax-group-edit.html) можно прочитать в этой [статье](multi-look-up-in-a-g-e.html).
 


 