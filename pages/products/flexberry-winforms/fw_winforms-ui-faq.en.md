--- 
title: WinForms UI FAQ 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms 
summary: Frequently asked questions Flexberry Winforms 
toc: true 
permalink: en/fw_winforms-ui-faq.html 
lang: en 
autotranslated: true 
hash: ef78e111fab5b8b8ad0c2d0d8212a7eab8e426a05f30b920e2ad01b17c37ac8c 
--- 

In the process of application development may raise questions, the answers to which can be found below. 

## Question 1 

For example, there are objects `Корпус` and `Аудитория`. `Корпус` is a master for `Аудитории`. You want to make a form to edit these objects is roughly of this format: at the top [ObjectListView](fw_objectlistview.html) `Корпусами` down [GroupEdit](fw_group-edit.html) with the audience of the case. In the selection of OLV building, the GE is loaded from the appropriate audience. The audience in GE I want to edit, instead of calling separately form `АудиторияE`. How best to do this? 

Created custom listform to represent `КорпусL`. To generate it appropriate .Net form where you want to add `GroupEdit`. But it is not clear how to specify object data in it. Opens a blank form with a choice of packages. 

How to solve this problem? 

### Response 

To solve this problem it is necessary to go to the other side: we need the edit form case with detaylari (audience must be detaylari so they can be edited in GroupEdit). Then you need to switch the wizard as described in [Switching of the edited object without closing the edit form](fw_switch-editing-object.html). 

In this case, the lock will adequately work. But if the audience separately, also can be edited (if there is a separate edit form for them), `GroupEdit` must be configured to work with the [block editable objects](fw_lock-rows-in-groupedit.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}