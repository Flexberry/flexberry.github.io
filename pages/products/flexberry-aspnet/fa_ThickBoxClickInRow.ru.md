---
title: ThickBoxClickInRow
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Архив
toc: true
permalink: ru/fa_thick-box-click-in-row.html
folder: products/flexberry-aspnet/
lang: ru
---



(((<msg type=Warning>Данная настройка больше не используется ([подробнее](w-o-l-v-edit-form.html)).</msg>)))

# Поддержка открытия формы редактирования в ThickBox при клике на строку в WebObjectListView

Для того, чтобы можно было поднять форму редактирования в ThickBox по клику на строку, необходимо установить в WebObjectListView свойство  Operations.ShowThickboxOnClickInRow = true;

Пример для списковой формы,
```
        protected void Page_Load(object sender, EventArgs e)
        {
            WebObjectListView1.Operations.ShowThickboxOnClickInRow = true;



            WebObjectListView1.View = Information.GetView("ТипЛапыL", typeof(ТипЛапы));
            WebObjectListView1.EditPage = "~//forms//TipLapy//TipLapyE.aspx";
            WebObjectListView1.TableCellGenerated += WebObjectListView1_TableCellGenerated;

            WOLVSettApplyer wsa = new WOLVSettApplyer();
            wsa.SettingsApply(WebObjectListView1);
        }
```
Если нужно изменить параметры сгенерированной ячейки, например, изменить размеры ThickBox и пр., то можно подписаться на событие WebObjectListView1.TableCellGenerated и изменить параметры ячейки.
<br />
Т.к. ThickBox не имеет нативной поддержки td и tr элементов, то он был модифицирован и все параметры у ячейки таблицы хранятся в атрибуте "abbr".

Пример,
```

        protected void Page_Load(object sender, EventArgs e)
        {
            WebObjectListView1.View = Information.GetView("ТипЛапыL", typeof(ТипЛапы));
            WebObjectListView1.EditPage = "~//forms//TipLapy//TipLapyE.aspx";
            WebObjectListView1.Operations.ShowThickboxOnClickInRow = true;
            WebObjectListView1.TableCellGenerated += WebObjectListView1_TableCellGenerated;

            WOLVSettApplyer wsa = new WOLVSettApplyer();
            wsa.SettingsApply(WebObjectListView1);
        }

        private void WebObjectListView1_TableCellGenerated(object sender, ThickBoxControlArgs args)
        {
            args.Cell.Attributes["abbr"] = "Новый url";
        }
```

Также, возникает необходимость, при поднятии формы редактирования в ThickBox, скрыть MasterPage. Для этого можно использовать следующее решение:
# Добавить новый пустой MasterPage с именем, например, Blank.Master;
# Подписаться на событие TableCellGenerated и в его обработчике добавить новый параметр "BlankMaster=true"
# В обработчике формы редактирования в методе Pre_Init изменять свойство MasterPageFile = "~/Blank.master" в зависимости от переданного параметра

Пример,
<br />
Списковая форма:
```

        protected void Page_Load(object sender, EventArgs e)
        {
            WebObjectListView1.View = Information.GetView("ТипЛапыL", typeof(ТипЛапы));
            WebObjectListView1.EditPage = "~//forms//TipLapy//TipLapyE.aspx";
            WebObjectListView1.Operations.ShowThickboxOnClickInRow = true;
            WebObjectListView1.TableCellGenerated += WebObjectListView1_TableCellGenerated;

            WOLVSettApplyer wsa = new WOLVSettApplyer();
            wsa.SettingsApply(WebObjectListView1);
        }

        private void WebObjectListView1_TableCellGenerated(object sender, ThickBoxControlArgs args)
        {
            String abbr = args.Cell.Attributes["abbr"];
            args.Cell.Attributes["abbr"] = abbr.Insert(abbr.IndexOf("?") + 1, "BlankMaster=true&");
        }
```
Форма редактирования
```

        protected override void OnPreInit(EventArgs e)
        {
            base.OnPreInit(e);

            if (System.Web.HttpContext.Current.Request["BlankMaster"] == "true")
            {
                MasterPageFile = "~/Blank.master";
            }
        }
```
<br />
''Внимание:''Если вы добавляете свои параметры в запрос, то их нужно добавлять до параметров ThickBox, например, в самое начало, как это было показано выше.
<br />
''Внимание 2:''Для описанного всего выше "стандартный" thickbox.js(который есть на сайте <http://jquery.com/demo/thickbox/>), не подходит, нужно пользоваться тем, который идет вместе с веб шаблоном.
<br />

## Примечание
ThickBox привяжется для всех ячеек строки, кроме первой ячейки(где редактирование, удаление и пр.). Если необходимо не привязывать ThickBox к какому-то столбцу, например, расположенному посередине, то необходимо в файле настроек ViewColumnProvider.xml для соответствующего свойства добавить атрибут: noteditbyclick="true";
<br/>
Например,
```

<?xml version="1.0" encoding="utf-8" ?>
<root>
  <type name="IIS.ISOGD.Адрес">
    <property name="ПервичныйКлюч" width="10" cut="false" filter="false" sort="false" align="ПоЦентру" noteditbyclick="true"/>
    <property name="ТерриторияПроп" width="120" cut="false"/>
    <property name="УлицаПроп" width="100" cut="false" noteditbyclick="true"/>
    <property name="Дом" width="50" cut="false" />
  </type>
</root>
```
 

