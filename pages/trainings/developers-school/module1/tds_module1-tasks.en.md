---
title: Module 1. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module1-tasks.html
lang: en
autotranslated: true
hash: 598277786d3981fac1b8efe0c6f086890bfb5d981909a7be833dac211ace5010
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
6. For a list on the main page of the application must podderzhivaetsya sort one field in three modes: "unsorted", "ascending", "descending".
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
4. Data that should be published by the server at the start are in the file [db.json](https://github.com/Flexberry/flexberry-developers-school/blob/master/Задания/Модуль 1. Introduction to web development/db.json).
5. The server must be deployed on [Heroku](https://www.heroku.com/) using [this](https://github.com/jesperorb/json-server-heroku) tool.
6. To make requests to the server from the application, you need to use AJAX (it is possible by means of jQuery)
7. The application can be both single-page and multi-page (SPA or MPA).
8. Additional JavaScript libraries (like jQuery etc), CSS frameworks can be used.
9. Additional libraries should be installed in the application using package managers like [npm](https://www.npmjs.com/), [bower](https://bower.io/) or [yarn](https://yarnpkg.com/ru/) (optional).
10. The language of the user interface of the application can be in both Russian and English
11. The app should be laid out on GitHub personal repository, and publish on [GitHub Pages](https://pages.github.com/).
12. As a result of the assignment to send links to the GitHub repository page on GitHub Pages.

**Support resources:**
1. [Official documentation for the JSON Server](https://github.com/typicode/json-server/blob/master/README.md).
2. [Create fake REST API using JSON Server](https://code.tutsplus.com/ru/tutorials/fake-rest-api-up-and-running-using-json-server--cms-27871)

### Task 2. Testing networking and server-side API.

1. Using Chrome Chrome Developer Tools [Postman](https://www.getpostman.com/downloads/) and [Fiddler](https://www.telerik.com/download/fiddler) run the following queries against a server that is deployed when performing the task 1:
* Receive all invoices, any field containing the number "7"
* Receive all invoices, the value of the field number less than "100000"

Both queries should be done using all three tools (Chrome Chrome Developer Tools and Fiddler, Postman) to do screenshots of the responses received and keep the screenshots in the document. The document should be laid out on the GitHub repository for task 1 (create document separate folder). You can use [Google Docs](https://www.google.ru/intl/ru/docs/about/) to publish documents (in this case, the repository must be created .md file with a link to the document in Google Docs).

**Sample screenshot of the body of the HTTP response in Chrome Chrome Developer Tools:**
![](/images/pages/trainings/developers-school/module1/dt-example.png)

**Sample screenshot of the body of the HTTP response in Postman:**
![](/images/pages/trainings/developers-school/module1/postman-example.png)

**Sample screenshot of the body of the HTTP response in Fiddler:**
![](/images/pages/trainings/developers-school/module1/fiddler-example.png)

2. Using Postman or Fiddler, run the following queries:
* Create invoice
* Change invoice
* Delete nakladani

By analogy with the assignment from the previous paragraph it is necessary to create a document with screenshots of the responses received from both instruments for each query. The document also needs to put on GitHub in a repository for task 1 in the same folder along with the previously created document.

3. Using Postman to create two collections with automated tests for the API provided by the server that are deployed when performing the task 1.

A minimal set of tests in the first collection:
* Test for receipt of all invoices
* Test adding overhead
* Test for change of invoices
* Test for removing false

A minimum set of tests in the second collection:
* Test for receipt of invoices filtered by the field value. The field name and its value should be set in the environment variables. For this test, prepare a file with a JSON array of values of the variables to each field in the invoice must correspond to a single array element (i.e., must be provisioned for the data filter on each field).

The collection can be expanded by an additional set of tests for more test coverage at the discretion of the student.

When you start a second collection in the Collection Runner, you need to specify a file with podgotovlennye data to run tests.

Both the resulting collection to be exported are appropriate .json files exported collections to post on the GitHub repository for task 1. For the second collection to the repository on GitHub, you also need to put the data files and environment variables for the test.

After you export your collections to start a prepared collection of tests on the local machine from the console using the [Newman](https://github.com/postmanlabs/newman). To take a screenshot of the terminal with the results of running, save it in a document similar to the tasks from the preceding paragraphs and to put the resulting document on GitHub in the repository for task 1 in the same directory along with previously created documents.

**Support resources:**
1. [Official documentation for the JSON Server](https://github.com/typicode/json-server/blob/master/README.md).
2. [Introduction to Postman](https://habr.com/ru/company/kolesa/blog/351250/)
3. [Test collection Postman Echo](https://docs.postman-echo.com/) - to add a test collection in Postman, click on "Run in Postman" in the upper right corner. In a test collection you can see examples of tests for various test cases.
4. [How to test requests using Fiddler](https://help.mindbox.EN/operations-and-integration/surgery/how-test-queries-with-using-fiddler)
5. [Basic use Fiddler](https://learn.javascript.ru/fiddler)
6. [Instructions to install and run Fiddler on a Mac OS](https://blogs.msdn.microsoft.com/jpsanders/2018/02/08/usinginstalling-fiddler-on-mac-os/)

## You can

* [Go to checklist of verification tasks](tds_module1-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
* <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with additional content module](tds_module1-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}