---
title: Альтернативный лукап
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_alternative--lookup.html
folder: products/flexberry-winforms/
lang: ru
---

<H1 class=section><strong>Пример </strong></H1>
<P><FONT color=#000000 size=2 face=Arial><SPAN class=601553111-12032007>На конкретном лукапе меняем обработчик <FONT size=2>look_LookUpEvent </FONT>на свой</SPAN></FONT></P>
<P><FONT color=#000000 size=2 face=Arial>private void look_LookUpEvent<SPAN class=601553111-12032007>1</SPAN>(object sender, System.EventArgs e)<BR>{<BR>&nbsp;Посетитель oПосетитель = (Посетитель)this.EditManager.DataObject; <BR>&nbsp;&nbsp;<BR>&nbsp;SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;<BR>&nbsp;Function lf = langdef.GetFunction(langdef.funcSQL,"1=1"); </FONT></P>
<P><FONT color=#000000><FONT face=Arial><FONT size=2><FONT color=#008000>//<SPAN class=601553111-12032007>создадим контейнер-раннер</SPAN></FONT><BR>&nbsp;ICSSoft.АдресноеБюро.ЛичностьL FormЛичностьL = (ICSSoft.АдресноеБюро.ЛичностьL)ICSSoft.STORMNET.UI.ContRunner.RunForm(typeof(ICSSoft.АдресноеБюро.ЛичностьL));<BR>&nbsp;FormЛичностьL.Edit(oПосетитель, "Личность", "Личность",lf);</FONT></FONT></FONT></P><FONT size=+0><SPAN class=601553111-12032007></SPAN><FONT color=#000000>
<P><FONT size=2><FONT face=Arial><FONT color=#008000>//<SPAN class=601553111-12032007>подпишемся на события</SPAN></FONT></FONT><BR></FONT><FONT size=2 face=Arial>&nbsp;FormЛичностьL.SaveEvent+=new ICSSoft.STORMNET.UI.SaveEventArgsHandler(FormЛичностьL_SaveEvent);<BR>&nbsp;FormЛичностьL.CancelEvent+=new ICSSoft.STORMNET.UI.CancelEventArgsHandler(FormЛичностьL_CancelEvent);<BR>}</FONT></FONT></FONT></P>
<P><FONT color=#000000 size=2 face=Arial>private void FormЛичностьL_SaveEvent(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)<BR>{<BR><FONT color=#008000><SPAN class=601553111-12032007>&nbsp;&nbsp;&nbsp; </SPAN>//<SPAN class=601553111-12032007>наши действия</SPAN></FONT><BR>}</FONT></P>
<P><FONT color=#000000 size=2 face=Arial>private void FormЛичностьL_CancelEvent(object sender, ICSSoft.STORMNET.UI.CancelEventArgs e)<BR>{<BR><FONT color=#008000><SPAN class=601553111-12032007>&nbsp;&nbsp;&nbsp; </SPAN>//<SPAN class=601553111-12032007>наши действия</SPAN></FONT><BR>}</FONT></P>
<P><FONT size=2 face=Arial></FONT>&nbsp;</P>