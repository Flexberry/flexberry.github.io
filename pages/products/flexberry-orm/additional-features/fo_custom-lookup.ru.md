---
title: Произвольный контрол в качестве лукапа
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, контрол, генерация, стадия, пример
summary: Рекомендации по созданию пользовательского контрола в качестве лукапа
toc: true
permalink: ru/fo_custom-lookup.html
lang: ru
---

## Требования к контролу

Для того чтобы возможно было генерировать произвольный контрол в качестве лукапа, необходимо, чтобы он реализовывал интерфейс `ICSSoft.STORMNET.Windows.Forms.ILookup`. Если контрол не реализует интерфейс `ICSSoft.STORMNET.Windows.Forms.ICustomizableControl`, то он будет просто сгенерирован без адекватной настройки собственных свойств.

В примере будет рассмотрена генерация `ExtLookUpTextControl`.

{% include important.html content="В настоящее время лучше использовать [ExtendedLookUp](fw_extended-lookup.html) вместо `ExtLookUpTextControl` (оба контрола находятся в одной библиотеке)." %} 

## Генерация

В настройках стадии нужно выбрать вкладку "Дополнительно" и нажать кнопку "Дополнительные настройки".
![](/images/pages/products/flexberry-orm/additional-features/i-lookup1.JPG)

Выбрать сборку, содержащую контрол.
Значение в колонке "Название" можно изменить.

![](/images/pages/products/flexberry-orm/additional-features/i-lookup2.JPG)

Открыть представление объекта, где есть ссылка на мастера. И в настройках лукапа указать:

* `Тип лукапа` - custom
* В ComboBox `Имя Лукапа` - имя контрола, которое было указано в настройках стадии
* `Свойство мастера` - свойство мастера, к которому будет привязываться (binding) контрол. Например, для предиктивного ввода.

![](/images/pages/products/flexberry-orm/additional-features/i-lookup3.JPG)

Сгенерировать приложение как обычно. Запустить. В итоге будет получена форма со сгенерированным контролом.

![](/images/pages/products/flexberry-orm/additional-features/i-lookup4.JPG)

## Полученный код

В сгенерированной форме создается контрол

```csharp
protected ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl ctrlПорода;
```

Добавляется информация о контроле

```csharp
m_arrLookupInformations.Add(new ICSSoft.STORMNET.UI.LookupInformation(this.ctrlПорода, "Порода", "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/ctrlПорода(Ext" + "LookUpTextControl)", null));
```

Прописываются биндинги для `EditManager`

```csharp
EditManager.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("КИЛ_КошкаE", typeof(IIS.КошкиСЛапами.Кошка), new ICSSoft.STORMNET.Windows.Forms.Design.OneBind[] {
            new ICSSoft.STORMNET.Windows.Forms.Design.OneBind(ctrlПорода, typeof(ICSSoft.STORMNET.Windows.Forms.ExtLookUpTextControl), "Value", null, "Порода"),
            ...
            );
```

Проставляются свойства контролу

```csharp
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
