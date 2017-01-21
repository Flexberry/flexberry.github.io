---
title: Встраивание прикладных контролов в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_a-g-e-applied-controls.html
folder: products/flexberry-aspnet/
lang: ru
---
# Биндинг
Для того, чтобы контрол мог нормально работать в [AjaxGroupEdit](ajax-group-edit.html), нужно чтобы контрол реализовывал интерфейс IAjaxGroupEditCompatible:

```cs
namespace ICSSoft.STORMNET.Web.AjaxControls
{
    /// <summary>
    /// Контролы, реализующие этот интерфейс могут быть встроены в AjaxGroupEdit
    /// </summary>
    public interface IAjaxGroupEditCompatible : IGroupEditCompatible
    {
        /// <summary>
        /// Свойство, на которое биндится значение в столбце AjaxGroupEdit. 
        /// Проставится самим AjaxGroupEdit, контрол только пользуется значением.
        /// </summary>
        string BindingPropertyName { get; set; }

        /// <summary>
        /// Индикатор, который устанавливается в true, если контрол находится внутри в AjaxGroupEdit.
        /// Проставится самим AjaxGroupEdit.
        /// </summary>
        bool IsInsideEditor { get; set; }

        /// <summary>
        /// Генерировать или нет стандартную структуру для клиентского биндинга.
        /// Упрощает разработку для контролов с простым биндингом.
        /// </summary>
        bool GenerateDefaultStructForBinding { get; }

        /// <summary>
        /// Список внешних скриптов для поключения.
        /// Все эти скрипты внутри AjaxGroupEdit будут подключаться через ContextHelper.
        /// Например, "AjaxDataService.js"
        /// </summary>
        List<string> ExternalScriptPaths { get; }

        /// <summary>
        /// Текст скриптов, которые будут выполнены на document ready AjaxGroupEdit
        /// </summary>
        List<string> DocumentReadyScripts { get; }
    }
}
```

Важным аспектом работы AGE является клиентский биндинг, который срабатывает в javascript.

Работает он следующим образом: 
1. Рядом с контролом должна быть json структура, содержащая свойства `Property` - свойство DataObject и `ID` - ID контрола на клиенте
2. При нажатии на кнопку `сохранить` значение из контрола на клиенте при помощи js сформирует новую json структуру, которая и пойдет при postback для обработки серверным `WebBinder`;

Если вы проставите GenerateDefaultStructForBinding=true, то сгенерируется структура подобного вида:

```
{"Property":"ДатаРождения", "ID" : "ctrl306940bb5b9e4bb98d7c93989c6ae9ed_ctrlДатаРождения_ctrl"}
```

Если вы пишете свой контрол со сложным клиентским биндингом, то генерировать данную структуру нужно будет самостоятельно, проставив значение `GenerateDefaultStructForBinding == false`.
Пример:

```cs
protected override void Render(HtmlTextWriter writer)
{
    if (IsInsideEditor)
    {
        string valJson = string.Format("`\"Property\":\"{0}\", \"ID\" : \"{1}\"`", BindingPropertyName, ID + "_ctrl");
        writer.AddAttribute("class", "binding");
        writer.AddAttribute("type", "hidden");
        writer.AddAttribute("value", valJson);
        writer.RenderBeginTag(HtmlTextWriterTag.Input);
        writer.RenderEndTag();
    }
}
```

Важно: при использовании стандартной структуры для биндинга в значение поля ID json структуры будет проставляться ControlToEditClientID.

# Настройки отображения DatePicker в AGE
Самый простой способ настроить отображение DatePicker для всех групэдитов приложения - создать метод, производящий необходимые действия над экземпляром контрола (который передан ему в параметрах), и присвоить его в статическое свойство InitSettings класса DatePicker. [Подробнее](init-control-settings-delegate.html).

# Встраивание MasterEditorAjaxLookUp
В `AjaxGroupEdit` имеется возможность использовать [MasterEditorAjaxLookUp](master-editor-ajax-look-up.html). Для этого необходимо настраивать [WebControlProvider](master-editor-ajax-look-up.html).

Пример куска файла `WebControlProvider.xml`:

```xml
  <propertytype name="ТипЛапы">
    <control typename="" property="" codefile=""/>
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxLookUp" property="SelectedMasterPK" codefile=""/>
  </propertytype>
```

Выглядеть это будет следующим образом:

![](/images/pages/img/CaseberryWeb/WGEAjaxLookup.JPG)

# Контролы для редактирования
Возникает потребность задать собственные контролы для просмотра и для редактирования свойств объектов в `AjaxGroupEdit`. Контролы для просмотра задаются привычным образом в [WebControlProvider](web-control-provider.html). Для того, чтобы задать контрол, который будет создаваться для редактирования свойства, то нужно в `WebControlProvider` добавить директиву `<editcontrol />` с аналогичными свойствами `<control />`.

Например, для просмотра даты используется `ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl`, а для редактирования `ICSSoft.STORMNET.Web.Controls.DatePicker`.

Выглядит это следующим образом:

![](/images/pages/img/CaseberryWeb/WGEDates.JPG)

```xml
  <propertytype name="NullableDateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="DatePicker.ascx"/>
  </propertytype>
```

Подробнее про [WebControlProvider и встраивание контролов](web-control-provider.html).

# Скрипты
Если прикладному контролу нужно подключение скриптов (при загрузке страницы), то все скрипты нужно подключать через [ContextHelper](context-helper.html) и скрипты автоматически добавятся. Если контролу нужно подключение внешних скриптов, то нужно использовать свойство ExternalScriptPaths интерфейса IAjaxGroupEditCompatible.