--- 
title: Flexberry BPM 
keywords: ember 
sidebar: flexberry-bpm_sidebar 
toc: false 
permalink: en/fbpm_architecture.html 
lang: en 
autotranslated: true 
hash: b36a0e10edbb81186000811a2a3c6da1fbbfd14345813db50b4d99af06fdd7a9 
--- 

## Implementation architecture of the business application workflow component 

Flexberry BPMS includes the following subsystems: 
* **An administrative interface** providing: 
* synchronization of models and versions in the business processes of a standard management server business processes database process models Flexberry BPMS; 
* on-screen interface for management and monitoring of standard сервера; 
* **Web API** provides: 
* receive requests from business приложения; 
* transfer of requests to perform simultaneous actions business приложениям; 
* transfer of requests to perform asynchronous actions service fftimer. 
* Service **fftimer** ensure the implementation of the asynchronous action or upon request from the service Flexberry BPMS WebAPI or on a specified schedule. 
* **Library services** technological actions (Activity Services) is used to run the business application standard operations (Activities). 

On the side of business applications: 
* interface methods Flexberry BPMS provide the transmission of requests from the business application on the WEB API; 
* application Activity Services (including technological Activity Services) provides reception and processing of synchronous and asynchronous requests to perform actions (Activities) from a standard server control business processes or Flexberry BPMS. 

In addition, the system uses the service bus for interaction between the business applications and Flexberry BPMS. 
Flexberry BPMS provides the following tasks: 
* the possibility of using the process approach in the information system developed using platform Flexberry; 
* ability to connect a variety of control systems of business processes, including several одновременно; 
* assign tasks to users and automated control исполнения; 
* automatic control of override users when assigning задач; 
* tracking of the performance of individual employees and units in performing tasks within the framework of existing business processes in the enterprise.

The main functions of BPMS Flexberry: 
* integration with one or more control systems business процессами; 
* storage of information about existing connected systems, management of business processes процессах; 
* storing information about the possibility of running processes in certain ситуациях; 
* storing information about executable in the moment процессах; 
* storage of information about users that can be assigned задачи; 
* storing information about the tasks assigned to users during the execution of the business процессов; 
* storage of information about substitutions, user and reassign tasks to deputies in accordance with this information. 

The overall architecture of the business application workflow component is shown in figure: 
![](/images/pages/products/flexberry-bpm/architecture.png) 

Detailed consideration of the components of BPMS Flexberry presented separately in the following sections. 

### administration Interface Flexberry BPMS 

The admin interface Flexberry BPMS is designed to view and edit information published in the system of processes that may run on the execution in the connected external systems of control of business processes and information regarding running at the moment, the business processes and the tasks that are assigned under them. 

The basic functions of the subsystem: 
* maintain a registry of published external processes management systems business процессами; 
* view and edit information about executable in the moment processes, as well as historical information about processes and задачах; 
* view and edit information about the currently assigned tasks пользователей; 
* view metrics, logs, crash reports system. 

### Database to store data about the processes and tasks 

This database contains information about published external processes management systems business processes available on starting, running currently processes, as well as historical data, information about the users assigned tasks. The object model data processes is shown in figure: 
![](/images/pages/products/flexberry-bpm/Model_Data_Process.png) 

The object model data about the tasks and performers presented on the figure below: 
![](/images/pages/products/flexberry-bpm/Model_Data_Task.png) 

### Services Flexberry BPMS Web API 

The Flexberry BPMS Web services API is designed to enable communication of external information systems with Flexberry BPMS, and also act as a backend for the admin interface Flexberry BPMS.

The basic functions of the subsystem: 
* provide a RESTful API for external systems to: 
* get information on available to start процессах; 
* to obtain information about the executable in the moment процессах; 
* to obtain information about the user's assigned задачах; 
* to start, continue, stop, restart specified процесс; 
* terminate, cancel, reassign задачу; 
* add, edit, delete information about the substitution of one system user other пользователем; 
* to provide an OData API for the admin interface Flexberry BPMS. 

### Service timer Service Timer Flexberry BPMS 

Service timer Service Timer Flexberry BPMS is designed to organize asynchronous interaction with the control systems of business processes, and to perform repetitive tasks on a specified schedule. 

The basic functions of the subsystem: 
* maintain task queues on исполнение; 
* execution of tasks on a given расписанию; 
* organization of asynchronous interactions with the control systems of business processes. 

### Database for service timer 

This database contains information about job queues, timer, as well as information about timer jobs that run on a specified schedule. 

### Modules integrate with external systems of control of business processes 

The modules integrate with external systems of control of business processes designed to implement a uniform interaction Flexberry with different BPMS BPMS according to the chosen course of studies architecture. System management of business processes from different manufacturers have different integration capabilities with external systems, which leads to the realization in the information system to which you want to add the ability to run business processes, modules for interaction with all such BPMS. Flexberry BPMS already contains modules for integration with various BPMS, providing a developed platform Flexberry information system a common API that does not depend on the BPMS used. The module is implemented for integration with jBPM (KIE), which was selected after a review of the existing BPM solutions. 

### Effective methods of expanding business application workflow component 

The business applications of process component is implemented as multiple services, which allows, on the one hand, to obtain flexibility in terms of scaling and failover, but on the other hand, these applications are difficult to deploy and update. With the aim of finding solutions to minimize the labor costs associated with deployment and maintenance of business applications created on the platform Flexberry, a review was undertaken of the sources on the Internet. 
To effectively deploy and support this infrastructure was the chosen method of containerization docker with support for cluster mode of deployment of microservices – docker-swarm [42, 43, 44].

This mode allows the expander: 
* To ensure the functioning of the system as a set of independent distributed across the cluster of microservices that are grouped in stacks services. Thereby to provide a uniform distribution of the load on the servers of a cluster [45]. 
* Provide fast deployment of services on different operating systems within the chosen operating environment [46]. 
* To deploy part or all of a set of microservices in the cloud: Microsoft Azure, Docker, Cloud, etc.. 
* Support running in a cluster several versions of the software (docker microservices) with scalability (running multiple identical copies) of necessary services to improve the performance and reliability of the solution [47]. 
* Group microservices in stacks of services that provides encapsulation of traffic between services stack within the allocated logical network with built-in DNS server. 
* Provide a failover solution by scaling the area of the services, and restart in the event of their refusal or failure of the server. 

Within the chosen architecture (see figure @the General architecture of a business application...") it is possible to allocate following stacks docker-services (see figure "a Set of stacks of project services"): 
* Stack services Flexberry BPMS as described above performs coordinating the work of business applications and support standard server management business processes. 
* Stacks of standard servers managing business processes. Each stack supports the operation of the services within a specific type. 
* Stacks of business applications. Each stack provides the services of a specific business application. 
* Stack services service bus. 

All stacks of the services integrated under the virtual network that provides communication between services running on the nodes of a docker swarm cluster. 
A set of stacks services PROJECT1, United virtual network PROJECT1_Net: 
![](/images/pages/products/flexberry-bpm/project-service-stacks-united-virtual network.png) 

Each stack services is a set of services, combined internal virtual network. 
* Stack services Flexberry BPMS includes services (see figure "Services stack Flexberry BPMS"): 
* Service administration interface FBPMS-admin; 
* Service WebAPI interface FBPMS-api; 
* Service asynchronous call action FBPMS-fftimer; 
* The database service, FBPMS-db. 

Services United virtual network, in which rises your own DNS server, ensuring conversion to the service name (fftimer, WebAPI, admin, db) to the IP address of the service within a virtual network. This mechanism greatly simplifies the configuration of the service, as in the configuration files specifies the names of services and not the IP addresses of the services within a virtual network, which can change. 
To improve the performance and resiliency services FBPMS-admin and FBPMS-api can scale.
Services stack Flexberry BPMS: 
![](/images/pages/products/flexberry-bpm/Flexberry-pbms-stack-services.png) 

Stack standard server management business process includes the docker image server (for example, a standard docker image, jBPM) and, if necessary, additional services such as databases. 
* Stack business applications include: 
* Ensure implementation of the actions (Activities) requests from Flexberry BPMS or BPMS server standard Protocol Web API; 
* A set of services, providing a WEB interface for participants процесса; 
* Service database and other necessary for the functioning of the services. 

All services of the business application of the joint virtual network and accessible within its framework by the name of the service. To improve the performance and reliability of functioning of system services WEB-interfaces of the participants of the business process and service actions (Activities) can be scaled. 
Stack services service bus includes database service and the service itself service bus. 
Using docker containerization provides the following benefits: 
* deployment of the system in the Multisystem (Linux, Windows) and cloud environment (Windows Azure, Docker, Cloud, etc.) среде; 
* the transition to a new software version without interrupting operation of complex ПО; 
* fast deployment of applications without the need to customize files конфигурации; 
* the possibility of rapid deployment of multiple systems within one кластера; 
* support failover solution by replicating part of the services and restarting them in case of failure of servers to other servers in the cluster. 

### Methods for modifying business applications with a workflow component 

An important aspect in the life cycle of the design process and the development of information systems, created on the platform Flexberry is the stage of modification and redesign in connection with a change in business rules or the operating requirements of the information system. The more complex and functional information system, the more impact that architecture and the process of application modification. Particularly acute this problem occurs in the presence of introduced end users of a business application that must remain operational to ensure business processes performed by the user. 
In the process of functioning of its component parts are constantly subjected to changes: 
* development of new versions of the interfaces of the participants of the business process or the administrative interface Flexberry BPMS without changing the database design данных; 
* the emergence of new versions of the models of the business process without changing the list of actions (Activities) and structures bases данных; 
* the emergence of new versions of the models of the business process without changing the list of actions (Activities) or database structures.

To accommodate this aspect has been further research on possible methods of modification of business applications, taking into account the need to update the functioning of information systems. 

#### a Method of modifying business applications based on versioning 

If the changes do not affect list of actions (Activities) and structure of the database, the transition to new software versions and models business processes without disruption to the functioning of the system. 
The system of containerization, docker-swarm supports versioning software (docker images) functionaudio within microservices docker. 
When a new version of the service manually or automatically stops the operation of the docker services with legacy software and runs similar services with the new version (see figure 24). In this case, there may be a small (tens of seconds) periods of suspension of operation of the service. 
Dynamic update of software versions and models of the business processes: 
![](/images/pages/products/flexberry-bpm/dynamic-update-versions.png) 

If the service does not operate in a single copy (scaled), docker sequentially updates the old version to the new in all instances. In this case the suspension of operation of the service is not in the system at the moment of transition services there are different versions. 

#### Method of parallel dynamic transition to a new model of the business process 

If in the process it is necessary to implement a new model of a business process with a new action (Activity), it is possible to use the previous updates, but with the agreed order of updates – up to the launch of the first business process, you must update the Web API services provide the implementation of new actions (Activities). 

#### Method of successive transition to a new model of the business process 

In the process of functioning of the system possible situations when you need to significantly upgrade the database structure and possible functioning versions of the software. In this situation it makes sense to expand on existing or new infrastructure docker servers, the new version of the system. 
The process of creating a new version (for example, PROJECT1-V2) system in docker-swarm is to create a new virtual network (for example, PROJECT1-V2_Net) and the launch of services of the new system of attaching them to the newly created network (see figure 25). 
Creating a new version of the OS in the new virtual network: 
![](/images/pages/products/flexberry-bpm/creation-new-version-system.png) 

After creating a new system initializes the necessary database schemas, migrating and synchronizing data from a previous version of the system and testing the new system (PROJECT1-V2). 
After the testing is completed the current business processes under the old system and launched the new business processes under the new version of the system.
After the trial operation of the services of the older system, and virtual network (PROJECT1_Net) is removed from the system. 

#### Comparative analysis of the methods modifications to the business application workflow component 

As a result of the review of the methods of modification of business applications with a workflow component, we can conclude that depending on what elements of a functioning information system has been modified so that you must choose one or the other method. If the modifications occur sequentially and in a separate component of the whole infrastructure, it is possible to use a method-based versioning a docker images or parallel dynamic updates. In the case of major changes the recommended method is a consistent transition with a full update a deployed version of the business application workflow component. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}