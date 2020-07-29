---
title: deployment Diagram (Deployment diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, diagram, figure deployment, the server infrastructure
summary: Basic information on the deployment diagram and its elements
toc: true
permalink: en/fd_deployment-diagram.html
lang: en
autotranslated: true
hash: 3e2703f2f24ff61bd24277a632c77a874b22b712036d8da39d1ced4a80676546
---

**Deployment diagram** - one of the available [kinds of graphs](fd_editing-diagram.html) supported [Flexberry Designer](fd_flexberry-designer.html).
Enterprise applications often require for its operation a certain it infrastructure, store information in databases located somewhere on the company servers, cause the web services, share resources, etc. In such cases, it is useful to have a graphical representation of the infrastructure on which the application will be deployed. What is needed, and deployment diagram, which is sometimes referred to charts placement.

![Example](/images/pages/products/flexberry-designer/diagram/deployment-diagram.png)

Such diagrams make sense to build only for hardware and software systems, whereas UML allows to build models of any system, not necessarily computer.

### Use a deployment diagram

1. A graphical representation of an it infrastructure can help to more efficiently distribute system components on the nodes of the network, which determines including the performance of the system.
2. Such a diagram could help to solve numerous tasks associated with, for example, security.

The deployment diagram shows the system topology and distribution of components of the system in its nodes and connections - routes of information transfer between the hardware nodes. This is the only chart that used a "three-dimensional" notation: nodes of the system are denoted by cubes. All the rest of the notation in the UML a plane figure.

## The main elements of deployment diagram

In the diagram the deployment you can display the following elements of UML notation that are available in the Toolbox:

Element/Notation | Purpose
:--------------------------------|:----------------------------------------------------------
![Example](/images/pages/products/flexberry-designer/diagram/component.jpg) | Component (Component)
![Example](/images/pages/products/flexberry-designer/diagram/componentinstance.jpg) | the component Instance (Component instance)
![Example](/images/pages/products/flexberry-designer/diagram/interface.jpg) | Interface (Interface)
![Example](/images/pages/products/flexberry-designer/diagram/node.jpg) | Node (Node)
![Example](/images/pages/products/flexberry-designer/diagram/nodeinstance.jpg) | host Instance (Node instance)
![Example](/images/pages/products/flexberry-designer/diagram/instance.jpg) | Object (Object)
![Example](/images/pages/products/flexberry-designer/diagram/activeobject1.jpg) | the Active object (Active object)
![Example](/images/pages/products/flexberry-designer/diagram/dependency1.jpg) | Addiction (Dependency)
![Example](/images/pages/products/flexberry-designer/diagram/connection.jpg) | Communication (Connection)
![Example](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Point of bending of the links (Point)
![Example](/images/pages/products/flexberry-designer/diagram/note.jpg) | Comment (Note)
![Example](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Connector review (Note the connector)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}