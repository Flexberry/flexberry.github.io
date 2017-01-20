---
title: События AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: CASE Plugins, Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_a-g-e-events.html
folder: products/flexberry-aspnet/
lang: ru
---



# Описание событий

Все обработчики событий имеют одинаковый тип: 
```
      
        /// <summary>
        /// Делегат обработчиков событий WGE
        /// <summary>
        /// <param name="sender">WGE, которому принадлежат события>/param>
        /// <param name="args">Параметры события</param>
        public delegate void WGEEventHandler(AjaxGroupEdit sender, WGEEventArgs args);

        /// <summary>
        /// Тип аргумента для событий WGE
        /// </summary>
        public class WGEEventArgs : CancelEventArgs
         {
        /// <summary>
        /// DataObject
        /// </summary>
        public DataObject DataObj { get; set; }

        /// <summary>
        /// Исключение
        /// </summary>
        public Exception Exception { get; set; }
    }
``` 

Любое событие можно отменить, установив свойство у аргументов Cancel = true, т.к. все аргументы наследуются от `[CancelEventArgs](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_u_i_1_1_cancel_event_args.html)`.

# Обработка событий AGE
## Событие '''`rowdeleting`''' 
Возникает при удалении строки в [AGE](ajax-group-edit.html).
Вызов триггера при удалении строки в [AGE](ajax-group-edit.html):
```
   
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
# Событие '''`rowdeleted`'''. Возникает после удалении строки в AGE.
Вызов триггера после удаления строки в [AGE](ajax-group-edit.html):
```
   
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

Методы

Удаление всех строк в AGE - '''`deleteAllRows`'''.
```

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

Получение количества видимых строк в списке - '''`getDataRows`'''
```

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

Настройка LinkedLookUp в AGE - '''`addDependedLookups`'''
```

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
Подробнее об AjaxGroupEdit можно прочитать в данной [статье](ajax-group-edit.html).

 


 