---
title: ViewColumnProvider
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_view-column-provider.html
lang: ru
---

`ViewColumnProvider` - это провайдер, который позволяет настраивать отображение колонок на контролах ([WebObjectListView](fa_web-object-list-view.html),
[AjaxGroupEdit](fa_ajax-group-edit.html)). Все его настройки хранятся в файле `/xml/ViewColumnProvider.xml`.  
Провайдер позволяет настраивать отображение колонок как для свойств и панели инструментов пользовательских типов (через тег `type`, `property` и `toolbar`), так и для типов отображаемого значения (через тег `basetype`).  
Приоритетными являются настройки свойств. Если они не найдены в файле конфигурации, то ищутся настройки для типа значения.

Параметр `name` применяется в составе тегов (`type`, `property` и `basetype`). Остальные параметры (`width`, `fixedwidth`, `cut` и другие) применяются в составе тегов `property` и `basetype`.

__После смены значения в xml-файле, проект нужно перекомпилировать.__

## Параметры

* `name` - Атрибут объекта из представления, с которым работает контрол. **Является обязательным!** 
  
    ```xml 
    <basetype name="System.Int32"/>
    ```
    
    ```xml
    <type name="Здание">
        <property name="Наименование"/>
    </type>
    ```

* `width` - Ширина колонки. Этот параметр применится не к самой колонке, а к ее содержимому. Обычно, всем колонкам, которые должны иметь определенную ширину, задается этот параметр, а остальным колонкам в css задается 100% ширина.

    ```xml
    <property name="Здание.Наименование" width="150" />
    ```

* `fixedwidth` - Задает ширину колонки. Параметр применится к самой колонке. Можно задавать как просто числом(100 - по умолчанию, пиксели), так и с указанием пикселей(100px) и процентов(100%)
    
    ```xml
    <property name="Здание.Наименование" fixedwidth="100" />
    ```

* `minwidth` - Задает минимальную ширину колонки. Задавать параметр можно `только в пикселях`, иначе не сработает. Минимальная ширина применяется, когда текущая ширина колонки становится меньше минимальной.

    ```xml
    <property name="Здание.Наименование" minwidth="100px" />
    ```

* `filter` - Нужна ли возможность фильтрования по данному столбцу

    ```xml
    <property name="Здание.Наименование" filter="false" />
    ```

* `filteroperations` - Нужна ли возможность использовать операции фильтрования по данному столбцу (`>=`, `<=`)

    ```xml
    <property name="Здание.Наименование" filteroperations="false" />
    ```

* `noteditbyclick` - Запрет на редактирование объекта по клику на ячейку в этом столбце (редактирование и так отменяется, если указать `filter=false`).
  
    ```xml
    <property name="Здание.Наименование" noteditbyclick="true" />
    ```

* `sort` - Включить\выключить сортировку для столбца

    ```xml
    <property name="Здание.Наименование" sort="true" />
    ```

* `cut` - Включить\выключить обрезание содержимого для столбца. Если текст превышает 30 символов, то текст обрезается и в конце добавляется многоточие
    
    ```xml
    <property name="Здание.Наименование" cut="true" />
    ```

* `cutwidth` - Количество символов, которое нужно оставлять в столбце не обрезая. Если задан этот параметр, то можно не задавать cut="true"

    ```xml
    <property name="Здание.Наименование" cutwidth="80" />
    ```

* `align` | Выравнивание содержимого столбца. Значения: "ПоЛевомуКраю", "ПоЦентру", "ПоПравомуКраю"

    ```xml
    <property name="Здание.Наименование" align="ПоЦентру" />
    ```

Можно добавлять свои свойства и обрабатывать их, например, в `WolvSettApplyer`. Получать произвольные настройки отображения можно при помощи метода `GetColumnSettingsBySettingsName(тип, имя_настройки, имя_свойства);` 

## Настройки колонки с панелью инструментов

Настройки для колонки с панелью инструментов осуществляется через тэг `toolbar`. Данный тэг допускает следующие параметры: `width`, `fixedwidth`, `minwidth`, `align`.

## Пример

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <basetype name="System.DateTime" width="100" />
  <basetype name="System.Guid?" cut="false" />
  <type name="IIS.ISOGD.Адрес">
    <toolbar fixedwidth="300px" width="200" minwidth="100px" />
    <property name="ПервичныйКлюч" width="10" cut="false" filter="false" sort="false" align="ПоЦентру" noteditbyclick="true"/>
    <property name="ТерриторияПроп" width="180" cut="false" />
    <property name="УлицаПроп" width="100" cut="false" />
    <property name="Дом" width="50" cut="false"/>
    <property name="Корпус" width="50" cut="false"/>
    <property name="Строение" width="50" cut="false" />
    <property name="ЖилойРайон.НаименованиеВидТеррЕд" width="100" cut="false" />
    <property name="Микрорайон.НаименованиеВидТеррЕд" width="100" cut="false" />
    <property name="Квартал.НаименованиеВидТеррЕд" width="100" cut="false" />
    <property name="КодКЛАДР" width="60" cut="false" />
    <property name="СостояниеАдреса" width="80" cut="false" />
    <property name="ДатаИзмененияСостояния" width="80" cut="false" />
    <property name="СрокРезервированияАдреса" width="80" cut="false" />
  </type>
</root>
```
