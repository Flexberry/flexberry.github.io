---
title: activity Diagram (Activity diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, diagram activities, track
summary: Basic information about the chart and its elements
toc: true
permalink: en/fd_activity-diagram.html
lang: en
autotranslated: true
hash: 8f45abb9b4055108edfde2b48e0cb8d1fae73c318d48bff98c7fbc98dc23183f
---

**Chart of activities (types of activities)** - one of the available [kinds of graphs](fd_editing-diagram.html) supported [Flexberry Designer](fd_flexberry-designer.html).
She, like [state diagram](fd_statechart-diagram.html), **reflects the dynamic aspects of system behavior**. Essentially, this diagram is a flowchart that shows the flow of control passes from one activity to another.

![Example](/images/pages/products/flexberry-designer/diagram/activity diagram.png)

Activity on the chart are "scattered" on the treadmill, each of which corresponds to the behavior of one of the objects (e.g. customer, Manager, web server, database server, etc.). It makes it easy to identify which object performs each of the activities. Track - the area of the activity diagram, which shows only **the activities under the responsibility of a specific object**. The track is designed to split the chart in accordance **with the allocation of responsibility for actions**. The title of the track may indicate the role or object to which it corresponds.

## The main elements of the chart of activities

In the diagram of activities you can display the following elements of UML notation that are available in the Toolbox:

Element/Notation | Purpose
:------------------------------------------------------------------|:--------------------------------------------
![Example](/images/pages/products/flexberry-designer/diagram/decision.jpg) | decision (Decision)
![Example](/images/pages/products/flexberry-designer/diagram/activeobject1.jpg) | Active state (Active state)
![Example](/images/pages/products/flexberry-designer/diagram/startstate.jpg) | Initial state (Start state)
![Example](/images/pages/products/flexberry-designer/diagram/finalstate.jpg) | End state (Final state)
![Primer](/images/pages/products/flexberry-designer/diagram/complextransition.jpg)![Primer](/images/pages/products/flexberry-designer/diagram/complextransition_ver.jpg) | Synchronizer/splitter (Complex transition)
![Example](/images/pages/products/flexberry-designer/diagram/objinstate.jpg) | Object in the state (in Object state)
![Primer](/images/pages/products/flexberry-designer/diagram/signalreceipt-l.jpg)![Primer](/images/pages/products/flexberry-designer/diagram/signalreceipt-r.jpg) | Receive signal (Signal receipt)
![Primer](/images/pages/products/flexberry-designer/diagram/signalsend-l.jpg)![Primer](/images/pages/products/flexberry-designer/diagram/signalsend-r.jpg) | Sending signal (sending Signal)
![Example](/images/pages/products/flexberry-designer/diagram/transition.jpg) | Transition (Transition) (in Object state)
![Example](/images/pages/products/flexberry-designer/diagram/objectflow.jpg) | Modify (object flow)
![Example](/images/pages/products/flexberry-designer/diagram/partition.jpg) | Partition (Partition)
![Primer](/images/pages/products/flexberry-designer/diagram/swlane-h.jpg)![Primer](/images/pages/products/flexberry-designer/diagram/swlane-v.jpg) | Separator swimming lanes (Swimlane separator)
![Example](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Point of bending of the links (Point)
![Example](/images/pages/products/flexberry-designer/diagram/note.jpg) | Comment (Note)
![Example](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Connector review (Note the connector)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}