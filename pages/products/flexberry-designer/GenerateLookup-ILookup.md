---
title: Произвольный контрол в качестве лукапа
sidebar: product--sidebar
keywords: Flexberry Designer
toc: true
permalink: ru/generate-lookup--i-lookup.html
folder: product--folder
lang: ru
---

# Произвольный контрол в качестве лукапа

## Требования к контролу
Для того, чтобы возможно было генерировать произвольный контрол в качестве лукапа, необходимо, чтобы он реализовывал интерфейс "ICSSoft.STORMNET.Windows.Forms.ILookup". Если контрол не реализует интерфейс "ICSSoft.STORMNET.Windows.Forms.ICustomizableControl", то он будет просто сгенерирован без адекватной настройки собственных свойств.
<br />
В примере будет рассмотрена генерация ExtLookUpTextControl.


(((
<msg type=warning>
В настоящее время лучше использовать [ExtendedLookUp](extended-look-up.html) вместо ExtLookUpTextControl (оба контрола находятся в одной библиотеке). 
</msg>
)))


## Генерация
В настройках стадии нужно выбрать вкладку "Дополнительно" и нажать кнопку "Дополнительные настройки".
![](/images/pages/img/CaseberryTool/ILookUpControl/1.JPG)

Выбрать сборку, содержащую контрол.
<br />
Значение в колонке "Название" можно изменить.
![](/images/pages/img/CaseberryTool/ILookUpControl/2.JPG)

Заходим в представление объекта, где есть ссылка на мастера. И в настройках лукапа указываем:
# "Тип лукапа" - custom
# В ComboBox "Имя Лукапа" выбираем имя контрола, которое было указано в настройках стадии
# "Свойство мастера" - свойство мастера, к которому будет привязываться(binding) контрол. Например, для предиктивного ввода.
![](/images/pages/img/CaseberryTool/ILookUpControl/3.JPG)

Генерируем приложение как обычно, запускаем, получаем форму со сгенерированным контролом.
![](/images/pages/img/CaseberryTool/ILookUpControl/4.JPG)


## Полученный код
В сгенерированной форме создается контрол
```
  protected ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl ctrlПорода;
```

Добавляется информация о контроле
```

m_arrLookupInformations.Add(new ICSSoft.STORMNET.UI.LookupInformation(this.ctrlПорода, "Порода", "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/ctrlПорода(Ext" +
                        "LookUpTextControl)", null));
```

Прописываются биндинги для EditManager
```

            EditManager.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("КИЛ_КошкаE", typeof(IIS.КошкиСЛапами.Кошка), new ICSSoft.STORMNET.Windows.Forms.Design.OneBind[] {
                        new ICSSoft.STORMNET.Windows.Forms.Design.OneBind(ctrlПорода, typeof(ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl), "Value", null, "Порода"),
                        ...
                        );

```

Проставляются свойства контролу
```

            ctrlПорода.DataObjectTypes = null;
            ctrlПорода.EditManagerForBind = null;
            ctrlПорода.HierarchyField = "";
            ctrlПорода.InGridProperty = null;
            ctrlПорода.LimitFunction = null;
            ctrlПорода.Location = new System.Drawing.Point(56, 12);
            ctrlПорода.MaximumSize = new System.Drawing.Size(3000, 20);
            ctrlПорода.Name = "ctrlПорода";
            ctrlПорода.Property = null;
            ctrlПорода.PropForBind = "";
            ctrlПорода.PropForUpdate = "";
            ctrlПорода.ReadOnly = false;
            ctrlПорода.ReadOnlyText = false;
            ctrlПорода.Size = new System.Drawing.Size(150, 20);
            ctrlПорода.StringedValue = null;
            ctrlПорода.SymbolsToIntellisense = 1;
            ctrlПорода.TabIndex = 1;
            ctrlПорода.Value = null;
            ctrlПорода.ViewName = null;
            ctrlПорода.ИспользоватьОтображСвойствоПриВводе = false;
            ctrlПорода.ОтображаемоеСвойство = null;
            ctrlПорода.ОтображатьВсеПоляПриLookup = true;
            ctrlПорода.РежимВводаЗначений = false;
            ctrlПорода.РежимМастерСтрокой = false;
```
