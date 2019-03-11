--- 
title: Creating BPM applications 
keywords: BPMN 
sidebar: guide-bpm app_sidebar 
toc: false 
permalink: en/gbpm_landing-page.html 
lang: en 
autotranslated: true 
hash: 0826b54451c7665efe835c8bc553b071fb0ea441c1ac94856af08f619e68651c 
summary: a Course on creating applications with the process component on the platform Flexberry. 
--- 

## Creating BPM applications 

Design method and application development, with the process component is shown in figure: 
![](/images/pages/guides/flexberry-bpm/method-designing-developing-application-process.png) 

According to the scheme presented on the figure above, the methodology of designing and developing business applications with a workflow component in the application designer includes the following steps: 
1. Create a new project. 
2. Create repositories for source code and check addresses in the application designer. 
3. The installation of a BPM solution in the environment of the developed application. According to the research, it is recommended to use jBPM KIE. 
4. Configure the project to use the process part, which includes specifying the appropriate flag and out of the deployed BPM solutions. 
5. The structural design of the app: customize forms and navigation structure business applications (figures 1, 2, 3). 
6. The transition in the process editor, the creation and revision processes in BPMN 2.0 notation. 
7. Code generation applications, installation of modules integration with the process (getting and posting the code in the repositories). 
8. Revision of the application code, which includes the development of maven packages for custom activities jBPM (will be used in the process and call the business logic of the application), the development of services with business logic for process steps to be called custom activities, developing the business logic of the application to launch processes and to handle requests from the BPM solution. Implemented logic is published to repositories of code. 
9. Registration process in the business application. 
10. If the application is ready, it forces the project team executes the implementation of the developed application or the application can be finalized, the design process returns to step 5. 

Figure 1 – the constructor of the edit form 
![](/images/pages/guides/flexberry-bpm/b1-editing-constructor.png) 

Figure 2 – the Constructor of list form 
![](/images/pages/guides/flexberry-bpm/b2-list-design-builder.png) 

Figure 3 – settings app structure 
![](/images/pages/guides/flexberry-bpm/b3-application-structure-configuration.png)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}