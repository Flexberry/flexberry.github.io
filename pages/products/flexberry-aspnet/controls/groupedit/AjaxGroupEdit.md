---
title: AjaxGroupEdit
sidebar: product--sidebar
keywords: Flexberry ASP-NET, JavaScript API, Web UI (Контролы)
toc: true
permalink: ru/ajax-group-edit.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
   '''AjaxGroupEdit'''


* '''Платформа''': Web.
* '''Предназначение''': контрол, дающий возможность редактировать множество объектов одновременно.
* '''JavaScript API:''' да
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_ajax_group_edit.html)'''.
* '''[AjaxGroupEdit на тестовом стенде](http://ru:6158/forms/Controls/AjaxGroupEdit/)'''.


</td>
</tr></tbody></table></a>
</div>

(((<msg type=note>Данная статья находится в доработке</msg>)))

# Описание
[`AjaxGroupEdit`](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_ajax_group_edit.html)- контрол, который позволяет редактировать множество объектов. Является аналогом [GroupEdit](group-edit.html), для Web-приложений.


# Настройка внешнего вида контрола
## Настройка контролов внутри AGE
Настройка контролов внутри AGE описана в [статье](controls-in-a-g-e.html).

## CSS-классы AGE
Описание CSS-классов AGE можно прочистить в [статье](a-g-e-c-s-s.html).


## Операции
Описание операций AGE можно прочесть в [статье](a-g-e-operations.html).


## События
Описание событий AGE можно прочитать в [статье](a-g-e-events.html).


## Добавление объектов в AGE при инициализации формы (новый объект)
Описание алгоритма добавление можно прочитать в [статье](add-objects-a-g-e-initialization.html).


## Показ HTML значений
Для того, чтобы корректно показывать HTML значения в ячейках, нужно для атрибута проставить атрибут ```
[IsHTML()|IsHTML()]
```Т.е. аналогично [MasterEditorAjaxLookUp.ashx#%D0%9F%D0%BE%D0%BA%D0%B0%D0%B7_HTML_%D1%81%D0%B2%D0%BE%D0%B9%D1%81%D1%82%D0%B2_%D0%BD%D0%B0%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80_%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8_10|HTML в AjaxLookup] и WOLV.


## Order-атрибуты
Если в представлении есть order атрибут, то AjaxGroupEdit сбросит сортировку и наложит сортировку по этому атрибуту. В тулбар добавятся 2 кнопки для перемещения строк "вверх" и "вниз", которые уменьшают\увеличивают значение order-атрибута.


(((<msg type=note>Атрибут `Order` выставляется через Flexberry или добавляется вручную в код: ```
[Order()]
```Атрибут Order применим только к полям типа `int`.</msg>)))

## Группировка
Описание алгоритма настройки группировки описано в [статье](grouping-in-a-g-e.html).


## Включение режима Read-only для отдельных столбцов
Описание алгоритма настройки режима Read-only для отдельных столбцов описано в [статье](read-only-in-a-g-e.html).


## Добавление нового объекта
Для создания объекта в WGE имеется последняя строчка для ввода данных. Для того, чтобы ее совсем скрыть нужно указать не только `Add = false`, но и `PlusInRow = false````
ctrlMyWebGroupEdit.Operations.Add = false;
ctrlMyWebGroupEdit.Operations.PlusInRow = false;
```

# JavaScript API
Для манипуляций с AGE на стороне клиента следует использовать AGE JS API, который представляет собой jQuery плагин (ajaxgroupedit).


## Методы
{| border="1" 
|-
! Наименование
! Параметры
! Описание
|- 
| [#gdr|getDataRows]
| `selector`
| Метод, возвращающий все &lt;tr&gt;, исключая удаленные. Если `selector` принимает значение "all", то удаленные строки исключены не будут.
|- 
| `[#addnewrow|addNewRow]` 
| `ИмяПараметра1` - описание назначения
| Создание новой строки редактирования.
|- 
| `updateVisualState`
| `$container`<br> `statusValue`
| Метод, проставляющий "звёздочку" для отредактированных значений и прячущий удалённые.
|- 
| `[#adddependedlookups|addDependedLookups]`
| `settings`
| Добавление зависимых лукапов (подробнее см. ниже).
|- 
| `deleteRows`
| `rows` - jQuery-объект или массив HtmlElement, соответствующий удаляемым строкам
| Удаление указанных строк.
|- 
| `[#deleteallrows|deleteAllRows]`
| 
| Удаление всех строк.
|}



## Примеры использования
[anchor|#addnewrow]
### Добавление новой строки
```
 $('#&lt;%=ctrlКомната.ClientID%&gt;').ajaxgroupedit('addNewRow'); 
```
[anchor|#deleteallrows]
### Удаление всех строк
```

    <script type="text/javascript">
        $(document).ready(function () {
            $('span#delAllRows').click(function () {
                $('#<%= ctrlКвартира.ClientID %>').ajaxgroupedit('deleteAllRows');
            });
        });
    </script>
```
[anchor|#gdr]
### Подсчет количества строк
Используем метод `getDataRows`:
```
          
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
```
[anchor|#adddependedlookups]
### Зависимые лукапы внутри AGE
Для реализации зависимых лукапов есть метод `addDependedLookups`:
```javascript
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
```

Здесь:<br>
`master` - название свойства, которое отвечает за мастеровой лукап

`depended` - название свойства, которое отвечает за зависимый лукап

`url` - url сервиса, к которому будут идти запросы при смене значения в мастеровом лукапе

`method` - метод сервиса, к которому будут идти запросы при смене значения в мастеровом лукапе

Пример [зависимых лукапов](change-l-c-s-for-look-up-in-a-g-e.html).

## Возможные ошибки
* Не сохраняются изменения (например, добавленные объекты):
*# Убедитесь, что на странице нет js ошибок;
*# Проверьте, вызывается ли метод сохранения в AGE, который хранится в document.WgeSaveHandlers. Например, по-умолчанию вызов происходит в masterpage.js:

```javascript
if (typeof document.WgeSaveHandlers !== 'undefined') {
  var resHandler;
  $.each(document.WgeSaveHandlers, function (i, handler) {
    resHandler = handler();
    if (resHandler == false) {
      good = false;
      return;
    }
  });
}
```

# Пользовательские настройки
Описание настроек содержится в [статье](a-g-e-user-settings.html).

# Настройки для LookUp
С описанием настроек можно ознакомиться в [статье](settings-for-look-up-in-a-g-e.html).

# Встраивание прикладных контролов
Информацию о встраивании прикладных контролов можно прочитать в [статье](a-g-e-applied-controls.html).

# Множественный LookUp в AGE
Информацию о множественных LookUp'ах можно прочитать в [статье](multi-look-up-in-a-g-e.html).
