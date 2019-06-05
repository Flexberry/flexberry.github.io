--- 
title: Run the editor without restrictions WOLV 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_limit-editor-without-wolv.html 
lang: en 
autotranslated: true 
hash: 2611d05d3a88b74a5bd56307d4671e098a90aa2ebf80bb641ede078d694d2d6d 
--- 

Edit restrictions without WOLVW can be useful in cases when you want to invoke the constraint editor, for example editing form. 

{% include note.html content="In the proposed example uses the work session. This method is not only possible, it all depends on the task." %} 

## Work example 

Page with example: 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/limit-editor-without-wolv1.png) 

The button is called the constraint editor 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/limit-editor-without-wolv2.png) 

After clicking the button to Apply» «text representation of the functions is updated in the text boxes of the form. 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/limit-editor-without-wolv3.png) 


## Technical details 

In the implemented example, the constraint editor is started as follows: 

* The button is hung up the handler to open the editor restrictions. 
* A session key. 
* Each time you restart the session key is read recorded there the limit (if nothing is recorded, then recorded the empty constraint). 
* Open the editor restrictions on session key gets the current limit. 
* When pressed `Применить`, the restriction is recorded in the session. In the implemented example also is configured to reload the parent page, allowing privacitat from the session object with a limit and somehow display it on the interface. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}