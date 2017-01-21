---
title: Изменение внешнего вида контрола LookUp
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_look-up-appearance.html
folder: products/flexberry-winforms/
lang: ru
---

# Настройка изображений


Существует два способа изменить изображения на кнопках LookUp’а.

1. В случае если требуется изменить изображения в рамках всего приложения, необходимо инициализировать статический ImageList при запуске приложения. В этом списке должны присутствовать изображения с именами "LookUp", "Clear" и "Edit".

Пример:
```cs
LookUp.UserImageList.Images.Add("LookUp", Properties.Resources.LookUpImage);
LookUp.UserImageList.Images.Add("Clear", Properties.Resources.ClearImage);
LookUp.UserImageList.Images.Add("Edit", Properties.Resources.EditImage);
```


2. Для конкретного экземпляра LookUp’а с помощью свойства `UserImageList` можно назначить пользовательский ImageList, в котором также должны быть изображения с именами "LookUp", "Clear" и "Edit".


# Размер кнопок


Размер кнопок задается с помощью свойства `ButtonSize`, при изменении данного свойства должно выполнится перевычисление размера всего контрола.

# Границы кнопок


За отображение границ кнопок отвечает свойство `ButtonBorder`.