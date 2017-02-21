---
title: Скрытые свойства в представлении
sidebar: flexberry-designer_sidebar
keywords: View (представление)
toc: true
permalink: ru/fd_hidden-properties-in-view.html
folder: products/flexberry-designer/
lang: ru
---

# Скрытые свойства

Программист может объявить часть свойств, попадающих в [Определение-представления|представление] «скрытыми», тогда они будут в [Определение-представления|представлении], но не будут видны в пользовательском интерфейсе.


Для объявления состава скрытых свойств необходимо инициализировать свойство `Hidden` при указании атрибута `View`.


__Пример__:

```
[View("Простое2", new string[]{"Name as Имя",  "AnOtherAttrib"}, Hidden=new string[]{"AnOtherAttrib"})]
```

# Иллюстрация на примере сравнения разных представлений

Рассмотрим 3 разных представления: в первом будет 2 свойства и ни одно из них не будет скрытым, во втором те же 2 свойства, но уже 1 скрытое, а в третьем будет только 1 свойство (то самое, которое осталось "открытым" в представлении №2).

{|
!   !! 2 всего 0 скрытых !! 2 всего 1 скрытое !! 1 всего 0 скрытых
|-
| style="font-weight: bold; padding:10px;" bgcolor="LightGray" | Flexberry
| ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden1View.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden2View.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden3View.PNG)
|-
| style="font-weight: bold; padding:10px;" bgcolor="LightGray" | Код
| ```
 [View("КлиентHidden1", new string[] {
            "ФИО",
            "Прописка"})] ```  
| ```
 [View("КлиентHidden2", new string[] {
            "ФИО",
            "Прописка"}, Hidden=new string[] {
            "Прописка"})] ```
| ```
     [View("КлиентHidden3", new string[] {
            "ФИО"})] ```
|-
| style="font-weight: bold; padding:10px;" bgcolor="LightGray" | Форма редактирования 
| ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden1E.PNG) ||  ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden2E.PNG) ||  ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden3E.PNG)
|-
| style="font-weight: bold; padding:10px;" bgcolor="LightGray" | Форма списка 
| ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden1L.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden2L.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden3L.PNG)
|-
| style="font-weight: bold; padding:10px;" bgcolor="LightGray" | Загружаемые данные 
| ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden1Data.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden2Data.PNG) || ![](/images/pages/img/page/Hidden-Properties-In-View/ClientHidden3Data.PNG)
|}

На примере наглядно видно, что 1 и 2 вариант идентичны в плане выгружаемых данных (строка 5), а 2 и 3 идентичны в плане пользовательского интерфейса (строки 3 и 4)