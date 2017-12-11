---
title: Присвоение LimitFunction для второго ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы), Ограничения
summary: Указано как избежать проблем при присвоении LimitFunction для второго ObjectListView
toc: true
permalink: ru/fw_assigning-limit-function-second-objectlistview.html
folder: products/flexberry-winforms/
lang: ru
---

Для второго [ObjectListView](fw_objectlistview.html) ограничение надо присваивать до вызова базового метода Edit (в переопределённом на зависимой списковой форме). Иначе будут гонки между поднятием формы и обновлением списка и присвоением ограничения.