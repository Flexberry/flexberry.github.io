---
title: Dialogues with the system
keywords: interaction
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_elements_location.html
lang: en
autotranslated: true
hash: dc6d5fd02ec015052b0d1cf5ba1da3f217ed2012d9ddb85ddc66da0517378f8f
summary: Various dialog box and their description.
---

## Dialogues with the user

The system assumes the following main methods of communication between the user and the system:

* Modal окна;
* Pop-up уведомления;
* A tooltip (hint).

## Description of modal window

In the graphical user interface is called a modal window that blocks the user from parent app until, until the user close the window. Primarily implemented a modal dialog box. Also modal Windows are often used to attract user's attention to an important event or critical situation.

Modal window should have some sort of structure approximately like the picture below:

![The structure of the modal](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/1.png)

It can have multiple outputs (at least 2):

* Button отмены;
* Cross in углу;
* Press Esc;
* Clicking on the free space outside the modal window.

In addition, it is important to consider that its size must not exceed 50% of the space just the user interface and the name of the target action button should match the title of the modal window.

## The use of modal Windows

The modal window should show up in *rare* cases, *often,* when the user performs a critical action, such as deleting something or making a significant change.

>A modal window cannot be used to display error messages or successful action.

A vivid example of the use of modal Windows is the removal of objects.
Deleting objects must be performed a number of rules:

* Automatic focus on the confirmation button should be убран;
* Information must appear not only that the objects are removed, but their properties and consequences (especially if the objects cannot be recovered);
* There should be the possibility of exit from the unpleasant to the user state.

As it is now:

![Dialog - remove](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/5.png)

An obvious drawback is that the user does not know exactly what it removes, and therefore confirm the action it is easier. You need to notify the user about which object is deleted and what effects it will cause (in this example it is impossible to restore the deleted object)
![Dialog - remove - as it should be](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/delete.png)

This dialog box contains information about a deleted object, the warning about the lack of retroactivity of this action, the focus is on the button **No**, and **cross**, which will allow painless exit from "unpleasant", if necessary.

## Description pop-up notifications

Pop up a small notification window that usually appears in the lower right corner of the screen, which doesn't stop the process of interaction with the active application.

Structure pop-up notifications

![Location notification on screen](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/notification.png)

Also the main feature pop-UPS is an opportunity not to shut it down, as after a certain time they disappear on their own. Despite this property, the popup window it is possible to close cross in the top right corner.

## The use of toast notifications

To use pop-UPS is recommended in cases when it is necessary to notify the user of something, for example, in deriving information on the number of deleted rows.

As is (modal window):

![Existing modal window UIS MV](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/2.png)

As it should be (a toast):

![Planned - a pop-up notification](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/nt.png)

So the user can continue continuous work, just show a popup window with the right information. It remains on the user's screen or until swipe» «or disappear on their own.

> The recommended timer to automatically close the notification window **5 seconds**.

## Description tooltip

In addition to the modal Windows and notifications must exist tooltips which appear when hovering on the object when interaction with it has limitations or the interaction is not obvious.

The structure of the tooltip:

![Hint during registration](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/hiint.png)

## The use of tooltips

When you register the field for entering password is a set of constraints that the user did not have to fill out a form multiple times, you need to display a tooltip when hovering:

![Hint during registration](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/registration.png)

Another example is when the button is non-obvious action, which should warn the user:

![Tip](/images/pages/guides/ui-ux-guideline/uiuxg_dialog_with_a_user/hint.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}