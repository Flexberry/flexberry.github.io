---
title: Module 6. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module6-tasks.html
lang: en
autotranslated: true
hash: 897097e1d8152f4c1fabd41941d07d4f4db9b25f56ceb5b29859c36bc9e22711
---

## The expected results of tasks execution

According to the results of assignments the student must:
1. To understand the principles of ORM, the advantages and disadvantages of using.
2. To meet `Flexberry ORM`:
* Install and configure `Flexberry ORM` to work in your application.
* Be able to read objects in different ways
* To understand the concept of masters and datalow and work with them
* Be able to manage the transaction
* Be able to work in mnogoletnei
* Understand what the business server and be able to work with them
* Be able to perform cascade delete

## Assignments for module

### Task 1.

1. Create a console application. Subject area – order drugs in online shop with delivery to a physical pharmacy.
The buyer drives the name of the medication, then the system first searches for the specified name in the entire network of pharmacies and gives addresses where the drug is available. If the addresses of these pharmacies is inconvenient for the buyer, or the drug is nowhere in stock, the buyer can order the delivery of medicines to the selected pharmacy.
Class `Лекарства` has the attributes: name.
Class `Аптека` has the attributes: address, list of medicines available with prices at this pharmacy, a list with the work schedule.
Classes associated with connection `ассоциация` as `многие-to-многим`.
2. To install and configure `Flexberry ORM`, database (`PostgreSQL`).
3. To create instances of objects. To create instances of objects should be in the code using `Flexberry ORM`.
4. Add the medications to pharmacies (also using `Flexberry ORM`).
5. To make the following queries in different ways:
1. To obtain a list of all medications available for ordering – using `LINQ-провайдера`;
2. A list of the addresses of all pharmacies with `LoadingCustomizationStruct`;
3. A list of all medications in stock at a particular pharmacy conversion `LINQ in LCS`;
4. The address of the pharmacy where a given drug is in stock and is cheaper than just getting `строкового представления`;
5. The list of drugs more than the specified amount in a specific pharmacy, using `SQL-запросов`;
6. A list of pharmacies that operate on the specified day and at specified time – using `LINQ-провайдера`.

### Task 2.
1. Programmatically delete all instances of medication from the database using `Flexberry ORM`.
2. To add a new database `PostgreSQL`.
3. To add instances of drugs in the new database using `Flexberry ORM`.
4. Make requests from `Задания 1 p.5` specified ways to ensure that queries work correctly. The application should work in the mode of mnogoletnei.

### Task 3.
1. To create a web application with the same subject area.
2. To run queries from `Задания 1 p. 5` specified ways. Each request to create a separate form.
3. Create a business server. Use it to perform actions:
1. Create a form create a new аптеки;
2. In the list of medicines available to specify a non-existent лекарство;
3. When saving in a single transaction must be created not only the instance of the pharmacy, the specified лекарство;
4. Go to the form with all the medications and verify the medication created.


**Support resources:**
1. [Course via PostgreSQL](https://postgrespro.ru/education/courses/DEV1).
2. [Course of lectures on the foundations of databases](https://www.youtube.com/playlist?list=PLrCZzMib1e9oOFQbuOgjKYbRUoA8zGKnj)

## You can

* [Go to checklist of verification tasks](tds_module6-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
* <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with additional content module](tds_module2-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}