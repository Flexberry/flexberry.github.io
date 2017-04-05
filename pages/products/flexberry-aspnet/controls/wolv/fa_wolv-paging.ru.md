---
title: Пейджеры WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-paging.html
lang: ru
---

Для отображения постраничного перехода используются два [пейджинговых контрола](fa_pager.html) _верхний_ и _нижний_. Для разных `Pager'ов` используются разные CSS-классы и настройки.

## Настройки Pager'ов

| Параметр верхнего пейджера | Параметр нижнего пейджера | Описание                |
| --------------- | -------------- | ------------------ |
| `TopPagerVisible` | `BottomPagerPagesVisible` | Показывать или нет пейджер. |
| `TopPagerNavigationVisible` | `BottomPagerNavigationVisible` | Показывать или нет панель навигации (вперёд-назад) в пейджере. |
| `TopPagerGoToPageVisible` | `BottomPagerGoToPageVisible` | Показывать или нет панель перехода к произвольной странице в пейджере. |
| `TopPagerPagesVisible` | `BottomPagerPagesVisible` | Показывать или нет список страниц в пейджере. |
| `TopPagerInformationVisible` | `BottomPagerInformationVisible` | Показывать или нет информацию о количестве загруженных данных в пейджере. |
| `TopPagerInformationFormatter` | `BottomPagerInformationFormatter` | Делегат для формирования строки с информацией о загруженных данных в пейджере. |
| `TopPagerItemsInPageVisible` | `BottomPagerItemsInPageVisible` | Показывать или нет панель с списком выбора числа отображаемых элементов на странице в пейджере. |
| `TopPagerInlineGoToPage` | `BottomPagerInlineGoToPage` | Показывать поле ввода для перехода к произвольной странице в блоке с текущей страницей в пейджере. |
| `TopPagerNavigationBackText` | `BottomPagerNavigationBackText` | Текст кнопки навигации для перехода на предыдущую страницу в пейджере. |
| `TopPagerNavigationForwardText` | `BottomPagerNavigationForwardText` | Текст кнопки навигации для перехода на следующую страницу в пейджере. |
| `TopPagerNavigationSeparatorText` | `BottomPagerNavigationSeparatorText` | Разделительный текст между кнопками навигации в пейджере. |
| `TopPagerGoToPageLabelText` | `BottomPagerGoToPageLabelText` | Текст подписи к полю ввода для перехода к произвольной странице в пейджере. |
| `TopPagerPagesSkipText` | `BottomPagerPagesSkipText` | Текст пропуска в длинных списках страниц в пейджере. |
| `TopPagerPagesWithoutSkippingCount` | `BottomPagerPagesWithoutSkippingCount` | Максимальное число страниц, отображаемое без пропусков в пейджере. |
| `TopPagerFirstPagesCount` | `BottomPagerFirstPagesCount` | Число первых страниц, отображаемое в пейджере. |
| `TopPagerFirstPagesWithoutSkippingCount` | `BottomPagerFirstPagesWithoutSkippingCount` | Число первых страниц, отображаемое без пропусков в пейджере. |
| `TopPagerLastPagesCount` | `BottomPagerLastPagesCount` | Число последних страниц, отображаемое в пейджере. |
| `TopPagerLastPagesWithoutSkippingCount` | `BottomPagerLastPagesWithoutSkippingCount` | Число последних страниц, отображаемое без пропусков в пейджере. |
| `TopPagerItemsOnPageCaptionText` | `BottomPagerItemsOnCaptionText` | Текст подписи к списку выбора числа отображаемых элементов  в пейджере. |

## CSS-классы Pager'ов

| CSS-класс верхнего пейджера | CSS-класс нижнего пейджера | Описание |
| --------------------------- | -------------------------- | -------- |
| `ics-wolv-pager ics-wolv-pager-top` | `ics-wolv-pager ics-wolv-pager-bottom` | CSS-класс контейнера пейджера |
| `ics-wolv-pager-navigation ics-wolv-pager-navigation-top` | `ics-wolv-pager-navigation ics-wolv-pager-navigation-bottom` | CSS класс контейнера блока навигации пейджера. |
| `ics-wolv-pager-navigation-button` | `ics-wolv-pager-navigation-button` | Общий CSS класс кнопок навигации пейджера. |
| `ics-wolv-pager-navigation-button-back` | `ics-wolv-pager-navigation-button-back` | CSS класс кнопки навигации для перехода к предыдущей странице пейджера. |
| `ics-wolv-pager-navigation-button-forward` | `ics-wolv-pager-navigation-button-forward` | CSS класс кнопки навигации для перехода к следующей странице пейджера. |
| `ics-wolv-pager-navigation-disabled` | `ics-wolv-pager-navigation-disabled` | CSS класс отключённой кнопки навигации пейджера. |
| `ics-wolv-pager-navigation-enabled` | `ics-wolv-pager-navigation-enabled` | CSS класс включённой кнопки навигации пейджера. |
| `ics-wolv-pager-navigation-separator` | `ics-wolv-pager-navigation-separator` | CSS класс разделителя между кнопками навигации пейджера. |
| `ics-wolv-pager-go-to-page ics-wolv-pager-go-to-page-top` | `ics-wolv-pager-go-to-page ics-wolv-pager-go-to-page-bottom` | CSS класс контейнера блока перехода к произвольной странице пейджера. |
| `ics-wolv-pager-go-to-page-label` | `ics-wolv-pager-go-to-page-label` | CSS класс подписи к полю ввода номера страницы для перехода пейджера. |
| `ics-wolv-pager-go-to-page-input` | `ics-wolv-pager-go-to-page-input` | CSS класс поля ввода номера страницы для перехода пейджера. |
| `ics-wolv-pager-inline-go-to-page` | `ics-wolv-pager-inline-go-to-page` | CSS класс текущей страницы с полем ввода номера страницы для перехода пейджера. | 
| `ics-wolv-pager-inline-go-to-page-input` | `ics-wolv-pager-inline-go-to-page-input` | CSS класс поля ввода номера страницы для перехода в блоке текущей страницы пейджера. | 
| `ics-wolv-pager-pages ics-wolv-pager-pages-top` | `ics-wolv-pager-pages ics-wolv-pager-pages-bottom` | CSS класс контейнера блока с списком страниц пейджера. |
| `ics-wolv-pager-page ics-wolv-pager-page-top` | `ics-wolv-pager-page ics-wolv-pager-page-bottom` | Общий CSS класс блоков страниц пейджера. |
| `ics-wolv-pager-current-page ics-wolv-pager-current-page-top` | `ics-wolv-pager-current-page ics-wolv-pager-current-page-bottom` | CSS класс блока текущей страницы пейджера. |
| `ics-wolv-pager-skip ics-wolv-pager-skip-top` | `ics-wolv-pager-skip ics-wolv-pager-skip-bottom` | CSS класс блока пропуска страниц пейджера. |
| `ics-wolv-pager-items-on-page ics-wolv-pager-items-on-page-top` | `ics-wolv-pager-items-on-page ics-wolv-pager-items-on-page-bottom` | CSS класс контейнера блока выбора числа отображаемых элементов пейджера. |
| `ics-wolv-pager-items-on-page-caption ics-wolv-pager-items-on-page-caption-top` | `ics-wolv-pager-items-on-page-caption ics-wolv-pager-items-on-page-caption-bottom` | CSS класс подписи к списку выбора числа отображаемых элементов пейджера. |
| `ics-wolv-pager-items-on-page-list ics-wolv-pager-items-on-page-list-top` | `ics-wolv-pager-items-on-page-list ics-wolv-pager-items-on-page-list-bottom` | CSS класс списка выбора числа отображаемых элементов пейджера.

## Сохранение пользовательских установок

Сохранение установленных пользователем значений `Pager`'ов производится по ключу, составленному из пути к форме, `UniqueID` `WOLV`'а и имени представления. Таким образом, на одной и той же форме при смене представления настройка тоже будет меняться.

## Отключение пейджинга

У WebObjectListView предусмотрена возможность отключения пейджинга через настройку `AllowPaging`.
По умолчнию она имеет значение `true`, если выставить `false`, то пейджинг быдет отключен, все доступные записи будут отображаться на единственной странице, на месте пейджеров будет отображаться только информация о том, что показаны 1 - N из N записей.

![](/images/pages/products/flexberry-aspnet/controls/wolv/disable-paging1.png)

Пейджинг можно отключить в разметке страницы, проставив у контрола настройку AllowPaging="False".

```xml
<ac:WebObjectListView ID="WebObjectListView1" runat="server" Visible="true" AllowPaging="False" />
```

Пейджинг можно отключить и из behind-кода страницы, так же проставив у контрола настройку AllowPaging=false.

```csharp
/// <summary>
/// Вызывается самым первым в Page_Load.
/// </summary>
protected override void Preload()
{
    WebObjectListView1.AllowPaging = false;
}
```
