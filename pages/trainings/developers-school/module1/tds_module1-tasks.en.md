---
title: Module1. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module1-tasks.html
lang: en
autotranslated: true
hash: b84e193bc2e0db869ab55b27f9596780a21f6bf77d7598e0ea98fd894430f53e
---

## The expected results of tasks execution

According to the results of assignments the student must:
1. To be able to develop the client part of the web application to work with a given RESTful service:
* Create queries to retrieve data without query parameters.
* Create queries to retrieve data with query parameters.
* Create queries to add data.
* Create change requests data.
* Create queries to delete data.

2. To be able to use Chrome Developer Tools, Postman or Fiddler to debug the communication between web client and web server, and testing server-side API.

## Assignments for module

### Task 1. Creating a web application based on REST API.

**Requirements application functionality:**
1. On the main application page should display a list of accounts (invoices).
2. To create a form for creating and editing invoices.
3. Each row in the list on the main page to add buttons to Change ««and»» Delete to perform the corresponding actions on a specific invoice using a form.
4. For a list on the main page of the application must podderzhivaetsya filter the displayed rows by the value of the selected field (using the "equal" operation).
5. For a list on the main page of the application must be supported by a full text search on all fields.
6. For a list on the main page of the application must podderzhivaetsya sort one field in three modes: "sort", "ascending", "descending".
7. Filter, sort and search for the list on the main page of the application must be able to work together, ie in the request must include all that the user has selected.

**Approximate appearance of the application:**
1. Main page:
![](/images/pages/trainings/developers-school/module1/list.png)

2. The page to edit your invoices:
![](/images/pages/trainings/developers-school/module1/edit.png)

3. Search bar filtering and sorting:
![](/images/pages/trainings/developers-school/module1/panel.png)

**Specifications:**
1. The application must be implemented in JavaScript without using JS client-framwrok.
2. To get a list of invoices must be queried to the server REST-based API.
3. As a server for publishing a REST API require you to use the JSON Server](https://github.com/typicode/json-server).
4. Data that should be published by the server at the start are in the file [db.json](https://github.com/Flexberry/flexberry-developers-school/blob/master/Tasks/1. Introduction to web development/db.json).
5. The server must be deployed on [Heroku](https://www.heroku.com/) using [this](https://github.com/jesperorb/json-server-heroku) tool.
6. To make requests to the server from the application, you need to use AJAX (jQuery tools)
7. The application can be both single-page and multi-page (SPA or MPA).
8. Additional JavaScript libraries (like jQuery etc), CSS frameworks can be used.
9. Additional libraries should be installed in the application using package managers [npm](https://www.npmjs.com/), [bower](https://bower.io/) or [yarn](https://yarnpkg.com/ru/) (optional).
10. The language of the user interface of the application can be in both Russian and English
11. The app should be laid out on GitHub personal repository, and publish on [GitHub Pages](https://pages.github.com/).
12. As a result of the assignment to send links to the GitHub repository page on GitHub Pages.

**Support resources:**
1. [Official documentation for the JSON Server](https://github.com/typicode/json-server/blob/master/README.md).
2. [Create fake REST API using JSON Server](https://code.tutsplus.com/ru/tutorials/fake-rest-api-up-and-running-using-json-server--cms-27871)

## You can

* [Go to next module](tds_module2-about.html)
* [Go to the page with additional content module](tds_module1-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}