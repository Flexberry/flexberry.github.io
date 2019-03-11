--- 
title: Events AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: CASE Plugins, Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_age-events.html 
lang: en 
autotranslated: true 
hash: cb6f06b8359ddf82569096d9a8dcfe1092863eb897a1af8e7b690a09cff15fc9 
--- 

## description of the event 

All event handlers have the same type: 

```csharp
/// <summary> 
/// Delegate event handlers WGE 
/// <summary> 
/// <param name="sender">WGE, which owns event>/param> 
/// <param name="args">event Parameters</param> 
public delegate void WGEEventHandler(AjaxGroupEdit sender, WGEEventArgs args);

/// <summary> 
/// Argument type for events WGE 
/// </summary> 
public class WGEEventArgs : CancelEventArgs
    {
        /// <summary> 
        /// The DataObject 
        /// </summary> 
        public DataObject DataObj { get; set; }

        /// <summary> 
        /// The exception 
        /// </summary> 
        public Exception Exception { get; set; }
    }
``` 

Any event can be cancelled by selecting the arguments `Cancel = true`, because all the arguments are inherited from `CancelEventArgs`. 

## event Handling AGE 

### Event rowdeleting 

Occurs when a row is deleted in the [AGE](fa_ajax-group-edit.html). 
The trigger is called when a row is deleted in the [AGE](fa_ajax-group-edit.html): 

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    ...
    <div style="clear: left">
        <ac:AjaxGroupEdit ID="ctrlКвартира" runat="server" ReadOnly="false" />
    </div>
    ...
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="TestScriptsPlaceHolder" runat="server">
    <script type="text/javascript">
        $(function () {
            $('#<%= ctrlКвартира.ClientID %>').on('rowdeleting.ajaxgroupedit', function (e, d) {
                    alert('Удаляем строку.');
                });
            });
    </script>
</asp:Content>
``` 

### Event rowdeleted 

Occurs after a row is deleted in AGE. 
Call trigger after delete a row in the [AGE](fa_ajax-group-edit.html): 

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    ...
    <div style="clear: left">
        <ac:AjaxGroupEdit ID="ctrlКвартира" runat="server" ReadOnly="false" />
    </div>
    ...
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="TestScriptsPlaceHolder" runat="server">
    <script type="text/javascript">
        $(function () {
            $('#<%= ctrlКвартира.ClientID %>').on('rowdeleted.ajaxgroupedit', function (e, d) {
                    alert('Строка удалена.');
                });
            });
    </script>
</asp:Content>
``` 

### the rowadded Event 

Occurs when adding a new row to AGE. Can be used for operations with strings AGE at the time of adding them to the list [of datalow](fd_d-view.html). 

For example, for imposing restrictions on the line. 

```javascript
/** 
* If new row added in Agay, just assign the limit function. 
* @param {Element} row Element of the row added to the DOM. 
*/
$('#<%=ctrlCompanyEmployee.ClientID%>').on('rowadded.ajaxgroupedit', function(row) {
    $('[id$=ctrlCompany]', row).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
});
``` 

## Methods 

### Deleting all rows in AGE - `deleteAllRows`. 

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    ...
    <span id="delAllRows" style="cursor: pointer">Удалить все записи</span>
    <div style="clear: left">
        <ac:AjaxGroupEdit ID="ctrlКвартира" runat="server" ReadOnly="false" />
    </div>
    ...
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="TestScriptsPlaceHolder" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $('span#delAllRows').click(function () {
                $('#<%= ctrlКвартира.ClientID %>').ajaxgroupedit('deleteAllRows');
            });
        });
    </script>
</asp:Content>
``` 

### get number of visible rows in the list - getDataRows 

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    ...
    <div style="clear: left">
	<ac:AjaxGroupEdit ID="ctrlПодзадача" runat="server" ReadOnly="false" />
    </div>
        <button id="getDataRows" onclick="getRows(); return false;">getDataRows</button>
    ...
</asp:Content>

<asp:Content ContentPlaceHolderID="TestScriptsPlaceHolder" runat="server" >
    <script type="text/javascript">
        function getRows() {
            var data = $('#<%=ctrlПодзадача.ClientID%>').ajaxgroupedit('getDataRows');
            if (data.length != 0) {
                var result = '';
                $.each(data, function(index, value) {
                    result += value.innerHTML;
                });
                alert('Записей в списке: ' + data.length + '\n' + result);
            } else {
                alert('В списке нет записей.');
            }
        };
    </script>
</asp:Content>
``` 

### setting LinkedLookUp in AGE - addDependedLookups 

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    ...
    <div style="clear: left">
	<ac:AjaxGroupEdit ID="ctrlTestLookUpD" runat="server" ReadOnly="false" />
    </div>
    ...
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="TestScriptsPlaceHolder" runat="server">
    <script type="text/javascript">
        $(function () {
            $('#<%=ctrlTestLookUpD.ClientID%>').ajaxgroupedit('addDependedLookups', {
                master: '<%=ICSSoft.STORMNET.Information.ExtractPropertyName<WebFormsTestStand.TestLookUpD>(x=>x.TestLookUpA1)%>',
                depended: '<%=ICSSoft.STORMNET.Information.ExtractPropertyName<WebFormsTestStand.TestLookUpD>(x=>x.TestLookUpA2)%>',
                url: '~/Forms/Controls/AjaxGroupEdit/JavaScriptApiTests/TestLinkedLookUpInAGE.aspx',
                method: 'GetPageMethod'
            });
        });
    </script>
</asp:Content>
``` 

Read more about AjaxGroupEdit can be read in this [article AjaxGroupEdit](fa_ajax-group-edit.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}