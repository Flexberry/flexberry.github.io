--- 
title: sequence Diagram (Sequence diagram) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, diagram, sequence diagram, object, script, lifetime, actor, procedure 
summary: Basic information about the sequence diagram and its elements 
toc: true 
permalink: en/fd_sequence-diagram.html 
lang: en 
autotranslated: true 
hash: c419fac51397764e2cb5fa9b2862b474262d810ce4ea336433fa333d8e7a670d 
--- 

**Sequence diagram** - one of the available [kinds of graphs](fd_editing-diagram.html) supported [Flexberry Designer](fd_landing_page.html). 

![](/images/pages/products/flexberry-designer/diagram/sequence-diagram.png) 

**Sequence diagrams are used to clarify the diagram precedents**, a more detailed description of the logic of usage scenarios. This is an excellent tool for documenting project terms of usage scenarios! 

Sequence diagrams commonly contain **objects** that **interact within the scenario**, **message** they exchange, and **return results** related messages. However, often the results returned indicate that only if it's not obvious from the context. 

**Objects** are denoted by rectangles with underlined names (to distinguish them from classes). 

**Messages (method calls)** lines with arrows. 

**Return results** - dotted lines with arrows. 

The rectangles on the vertical lines under each object show **"lifetime" (focus) objects**. However, quite often they are not portrayed in the diagram, it all depends on the individual style of designing. 

## the Basic elements of sequence diagrams 

On the sequence diagram, you can display the following elements of UML notation that are available in the Toolbox: 

Element/Notation | Purpose 
:-----------------------------------------|:------------------------------------------------------- 
![](/images/pages/products/flexberry-designer/diagram/actor.jpg) | Participant (Actor) 
![](/images/pages/products/flexberry-designer/diagram/objectseq.jpg) | Object (Object) 
![](/images/pages/products/flexberry-designer/diagram/activeobjectseq.jpg) | the Active object (Active object) 
![](/images/pages/products/flexberry-designer/diagram/terminator.jpg) | the Terminator (Terminator) 
![](/images/pages/products/flexberry-designer/diagram/fwdnestedmsg.jpg) | procedure Call (Procedure call) 
![](/images/pages/products/flexberry-designer/diagram/fwdmessage.jpg) | Message (Flat message) 
![](/images/pages/products/flexberry-designer/diagram/fwdasyncmsg.jpg) | Asynchronous message (Async message) 
![](/images/pages/products/flexberry-designer/diagram/dependency.jpg) | message with the result (Return message) 
![](/images/pages/products/flexberry-designer/diagram/inscope.jpg) | time interval (In scope) 
![](/images/pages/products/flexberry-designer/diagram/timeconstraint.jpg) | temporal limit (Time constraint) 
![](/images/pages/products/flexberry-designer/diagram/corner.jpg) | curvature relationships (Point) 
![](/images/pages/products/flexberry-designer/diagram/note.jpg) | Comment (Note) 
![](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Connector (Note the connector) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}