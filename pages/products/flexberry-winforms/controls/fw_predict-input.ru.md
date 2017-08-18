---
title: Предиктивный ввод в Windows-приложениях
sidebar: product--sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
toc: true
permalink: ru/fw_predict-input.html
lang: ru
---
`Предиктивный ввод` позволяет модифицировать контролы для выбора мастера ([LookUp'ы](fa_lookup-overview.html)) или ввода текста таким образом, чтобы система подсказывала пользователю возможные варианты по мере ввода данных.

### Предиктивный ввод для LookUp'ов

В качестве контрола-подсказчика может выступать 
* Контрол, содержащийся в сборке `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll` ([ExtendedLookUp](fw_extended-lookup.html) или `ExtLookUpTextControl`)
* Контрол, созданный программистом ([подробнее в статье Произвольный контрол в качестве лукапа](fo_custom-lookup.html)).

## Подключение контрола

Чтобы подключить контрол предиктивного ввода, необходимо

* Зарегистрировать контрол в Стадии Flexberry Desinger
* В [E-представлении](fd_e-view.html) объекта указать для мастера custom-LookUp
* Сгенерировать объекты
* Скомпилировать объекты
* Сгенерировать формы
* В проекте Visual Studio установить ссылку на библиотеку с контролом

### Регистрация контрола в Стадии Flexberry Desinger

Для регистрации контрола необходимо перейти на Стадию и вызвать контекстное меню, в меню `Winforms` -> `C#` -> `Свойства модели`.

В открывшейся форме перейти на вкладку `Дополнительно` и нажать на кнопку `Дополнительные настройки`.

![](/images/pages/products/flexberry-winforms/controls/edit-stage.png)

В открывшейся форме дополнительных настроек необходимо в колонке `Путь к сборке` нажать на кнопку `...` и указать путь к DLL-файлу с контролом (для контролов Winforms это файл `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`).

![](/images/pages/products/flexberry-winforms/controls/path2dll.png)

В диалоговой форме выбора типа необходимо отметить `ExtendedLookUp`, `ExtLookUpTextControl` или оба.
 __Примечание__: рекомендуется использовать только `ExtendedLookUp`.

![](/images/pages/products/flexberry-winforms/controls/type-select.png)

Сохранить настройки, нажав `OK` - `Сохранить и закрыть`

Теперь контрол зарегистрирован в стадии Flexberry Desinger.

### Указание LookUp'a мастера в E-представлении объекта

Чтобы превратить обычный LookUp в LookUp с предиктивным вводом, необходимо указать мастеру тип используемого LookUp'a.

* Открыть E-представление объекта и выделить добавленный мастер, для которого необходимо использовать предиктивный ввод.

![](/images/pages/products/flexberry-winforms/controls/select-master.png)

* В поле `Тип лукапа` указать `custom`

![](/images/pages/products/flexberry-winforms/controls/select-type.png)

* В поле `Свойство мастера` указать свойство __мастера__, по которому будет производиться поиск для предиктивного ввода.
__Примечание__: свойство мастера должно содержаться в представлении. Наименование свойства вводится с клавиатуры.

![](/images/pages/products/flexberry-winforms/controls/select-property.png)

* В поле `Имя лукапа` выбрать из выпадающего списка необходимый LookUp.
__Примечание__: если в выпадающем списке нет необходимого LookUp'а, или вообще нет ничего, следует к пункту `Регистрация контрола в Стадии Flexberry Desinger`.

![](/images/pages/products/flexberry-winforms/controls/select-lookup.png)

* Сохранить изменения, внесенные в представление.

### Результат

![](/images/pages/products/flexberry-winforms/controls/predict-lookup.gif)

## Предиктивный ввод для текстового поля

Для реализации предиктивного ввода текста используется контрол [ExtendedTextBox](fw_extended-textbox.html)

## Фильтрация записей для предиктивного ввода

Чтобы наложить дополнительную фильтрацию на выводимые предиктивным вводом подсказки, необходимо указать контролу [LimitFunction](fo_function-list.html), накладывающий ограничение.

```csharp
Фильтрация записей для предиктивного ввода по логину
			SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
			extTextControl1.LimitFunction = langdef.GetFunction(langdef.funcEQ, 
				new VariableDef(langdef.StringType, "Логин"), username);
```

## Предиктивный ввод в Web-приложениях

Предиктивный ввод в Web-приложениях описан в статье [Предиктивный ввод в Web-приложениях](fa_predict-input-web.html)
