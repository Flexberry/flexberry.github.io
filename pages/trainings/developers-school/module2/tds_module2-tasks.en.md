---
title: Module 2. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module2-tasks.html
lang: en
autotranslated: true
hash: bc66e6ad12e5250b2ee547effbbf18963facc6d21728095e252f5ba0426608e5
---

## The expected results of tasks execution

According to the results of assignments the student must:
1. To be able to use the tools of team work on the platform GitHub:
* Create Issues, to customize the list of templates and their creation and use labels for Issues
* To create and use Kanban Board to add Issues on the Kanban Board
* Pomilovat group Issues in iteration use Milestones
* Create Pull Requests and link them with Issues
* Configure server builds, Continuous Integration and automatic deployment of applications using Travis CI
2. To be able to use tools of teamwork Azure DevOps (TFS):

## Assignments for module

### Task 1. The use of a command-work of the GitHub platform

1. The repository that was used while performing the task the first training module (with the code of the client application), to create templates for Issues create new challenges and new mistakes. Text templates can be arbitrary, but must match the type of the created Issue. Using the template for a task in the Issue should also be added to the label Feature, and using the template for error - label Bug.
2. To create three Issue-based templates two tasks and one error. Functionality for tasks and errors to invent your own (as errors, for example, you can describe the necessity of correction of colors or styles or other parts of the application).
3. All created Issues add Milestone, the expected period of performance Milestone set any within two weeks from the moment of creation Issues.
4. Inside the repository from the client application to create the Kanban Board where all the tasks can be in one of four States: "New", "Active", "In progress", "Completed" (the name of the corresponding "cards" on the Kanban Board can be both Russian and English).
5. All created Issues to add to the Kanban Board and initially ask them the status "New".
6. Create two additional accounts (for example, with temporary emails) on GitHub for work on behalf of other developers on your problems. One of the created accounts to give access for making changes to your repository from the client application, else the access is not granted.
7. Using additional accounts to perform the implementation of one task and the error described above:
* The implementation approach to use Git Flow: create a develop branch, task, and error to accomplish in the individual branches, which must be created from branch develop. The name of the branch to perform the task must begin with the prefix "feature -" the name of the branch to fix the bug with the prefix "fix-".
* The task must be implemented from the account that has write access to the repository from client application. Upon completion of the task should be created a pull request in the repository with branches for tasks in the develop branch. Pull request should be associated with an appropriate Issue for the task. After you create a pull request the status of a task on the Kanban Board should be changed to "Completed".
* Error needs to be fixed from the account that does not have write access to the repository from client application. To correct the error must be created a fork of the main repository, at the end of error correction needs to be made pull request to main repository, which was made with a fork. Pull request should be associated with an appropriate Issue for the task. After you create a pull request the status of a task on the Kanban Board should be changed to "Completed".
8. Pull request with the solution of the problem should be adopted, the relevant Issue when making pull requests should be automatically closed (in the Issue should be a link to the pull request or the commit that closed it).
9. Pull request with the bug fix should remain open, accept and close it is not required.
10. For a repository must be configured the build server and Continuous Integration using Travis CI:
* Server build must be run with every commit to the repository
* In the process, server build dollzhny to run a test collection that was exported from a Postman while performing the task 2 of the first training module, using the Newman.
* Builds that run from the branches master and develop should trigger an automatic deployment of client application on GitHub Pages associated with the repository (code client prilojeniya should go to the folder with the name of the source branch, i.e. "master" or "develop" inside the branch gh-pages). If your application uses a build system (e.g. Webpack), in the process of running a server build it should work, and GitHub Pages should be "collected" client application.
11. In the repository client application must contain a README file.md and CONTRIBUTING.md (you can use both English and Russian language when writing file content):
* In the README file.md must contain a description of your client application, as well as links to the deployed version of the application from the master and develop branches on GitHub Pages.
* In the file CONTRIBUTING.md must contain a description of how to add source code to your repository (rules can be formulated in arbitrary form, not necessarily attempting to formulate a "complete" rule set file must be created for example).

**Support resources:**
1. [Create templates for Issues and pull requests on GitHub](https://tproger.ru/news/multiple-issue-and-pull-request-templates-in-one-github-project/).
2. [Continuous Integration for newbies](https://habr.com/ru/post/352282/)
3. [Official Travis CI Tutorial](https://docs.travis-ci.com/user/tutorial/)
4. [Official GitHub guides](https://guides.github.com/)

## You can

* [Go to checklist of verification tasks](tds_module2-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
* <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with additional content module](tds_module2-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}