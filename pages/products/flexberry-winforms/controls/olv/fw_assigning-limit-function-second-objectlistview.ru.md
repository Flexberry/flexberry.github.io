---
title: Присвоение LimitFunction для второго ObjectListView
sidebar: product--sidebar
keywords: Windows UI (Контролы), Ограничения
toc: true
permalink: ru/fw_assigning--limit-function-second--object-list-view.html
folder: products/flexberry-winforms/
lang: ru
---

Для второго [ObjectListView](object-list-view.html) ограничение надо присваивать до вызова базового метода Edit (в переопределённом на зависимой списковой форме). Иначе будут гонки между поднятием формы и обновлением списка и присвоением ограничения.