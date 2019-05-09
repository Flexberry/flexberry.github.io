---
title: Module 2. Check-list check for jobs
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module2-check-list.html
lang: en
autotranslated: true
hash: 90953cd2af3db0baf9f1c7168cd5f54403d70ae75a071c564725b52cdfa1026a
---

## Task 1. The use of a command-work of the GitHub platform

1. Created templates for Issues to create new tasks and new mistakes: **2 points** (one score for each template).
2. When creating Issues, using prepared templates, adds labels "Feautre" and "Bug" for the tasks and errors respectively: **1 point**.
3. Created two tasks and one error using the prepared templates Issues: **1 point**.
4. All Issues associated with a Milestone with a due date within two weeks from the moment of creation Issues: **1 point**.
5. The repository created a Kanban Board where all the tasks can be in one of four States: "New", "Active", "In progress", "Completed": **1 point**.
6. At the time of inspection of the task one task has status "New" (the one for which you have not created a pull request), and another problem and error is "Executed" on the Board: **1 point**.
7. Created two pull requests is the pull request for the task is created from the branch inside repository, a pull request for the bug created from "fornataro" repository. Pull requests created under different user different from the user account to which you published the repository. Pull request for the task is closed, the pull request is for error - open: **2 points**.
8. The repository contains a develop branch. With this thread created branches for solving the problem and fix the error. The name of the branch to solve the problem has the prefix "feature -" the name of the branch is to correct errors - prefix "fix-": **1 point**.
9. Pull requests refer to the relevant Issues. Closed Issue refers to the pull request or the commit that closed it: **1 point**.
10. Repository configured server builds using Travis CI. In the server build runs tests for collections that are created when you run the task from the previous module, using the Newman: **3 points**.
11. Builds that run from the branches master and develop, initiate automatic deployment of the client application on GitHub Pages associated with the repository (code client prilojeniya gets in a folder with the name of the source branch, i.e. "master" or "develop" inside the branch gh-pages): **2 points**.
12. The repository has a README file.md and CONTRIBUTING.md. In the README file.md there is a description of the client application, as well as links to the deployed version of the application from the master and develop branches on GitHub Pages. In the file CONTRIBUTING.md there is a description of how to add source code to the repository. The language of the content files can be both Russian and English: **2 points** (one point for presence of each file).

**Total job**: 18 points.

## Task 2. The use of Azure DevOps (TFS)
1. Created new organization and project. In the project as a process for work items selected [Agile](https://docs.microsoft.com/ru-ru/azure/devops/boards/work-items/guidance/choose-process?view=azure-devops#main-distinctions-among-the-default-processes). Added private Git and TFVC repository to Azure Repos: **1 point**.
2. The backlog Azure Boards established three requirements with names `Создание npm-package chance-js`, `Установка package chance-js in приложение` and `Настройка CI/CD for приложения`: **1 point**.
3. For requirements `Создание npm-package chance-js` added tasks with the following titles: `Добавление source file репозиторий` and `Создание build to publish пакета`. Tasks added a description. Tasks assigned to the same user: **2 points** (one score per task).
4. For requirements package `Установка chance-js in приложение` added challenge with the following title: `Добавление package depends on chance-js`. The task added a description. The task is assigned to a user other than the user to whom is assigned the task of requirements `Создание npm-package chance-js`: **1 point**.
5. PstrfНастройка requirements for CI/CD for приложения` added tasks with the following titles: `Добавление build приложения` and `Публикация app on GitHub Pages`. Tasks added a description. Tasks assigned to different users: **2 points** (one score per task).
6. All created tasks planned for the iteration. Iteration should have a two-week period. All the tasks at the time of delivery of the job must be able `Resolved` (provided that the job is done completely). If some part of this task is not fulfilled, the respective tasks can have a status `New` or `Active`: **1 point**.
7. In the TFVC repository project in Azure Repos added the files from the [GitHub repository of the library of Chance](https://github.com/chancejs/chancejs). Team `gulp lint`, `gulp test` and `gulp build` run without error on the local machine (if you perform the mapping in the repository to a local folder). Adding files produced one or more times. Each check is associated with a task `Добавление source file репозиторий` requirements `Создание npm-package chance-js`. Comments to check-ins have the following format: `<date>. <Task name>. <Review>.`. The ins made by the user to whom the task is assigned: **3 points**.
8. In the Git project repository in Azure Repos added the files from the GitHub repository [Issue Tracker Demo](https://github.com/ehaberev/issue-tracker-demo): **1 point**.
9. Created "ribbon" in Azure Artifacts. Team `npm install` installs in-app npm packages from the tape and from the public repository of npm packages (in the setting of "tape" is set to Upstream source to access npm packages from the repository https://registry.npmjs.org through this "tape"). In "tape" published by npm-package `chance-js`: **2 points**.
10. Created by Bild, which is published npm package `chance-js` from TFVC repository to Azure Repos, in "the tape" Azure Artifacts. During execution of the build before you publish the npm package, run gulp tasks `lint`, `test` and `build`. Continuous Integration for the build off, that is, the build should not run every time without refreshing the repository. Bild is working - if you change the version of the npm package and run the build, the new version of npm package to be published in the "tape" Azure Artifacts: **3 points**.
11. In the application from the Git repository to Azure Repos adds a dependency on the npm package `chance-js` published in "tape" Azure Artifacts of the project. All changes made in a separate branch and merged into the working branch using pull requests. Create Waki, all commits and pull request is tied to the task `Добавление package depends on chance-js` requirements `Установка package chance-js in приложение`. Pull request before making a confirmed reviewera, reviewera should be the user that is different from the performer of the task. File `chance.js` folder `src` removed. The app must be installed `chance-js` from the "feeds" Azure Artifacts of the project. When running locally the app Issues should not be created because the file `chance.js` will be missing (because we don't use "garbage" for the application): **3 points**.
12. Created a build, which builds the application from the Git repository to Azure Repos. During execution of the build command is executed `npm run lint` to run linting. The build in "result" folder (drop folder), files are copied `index.html` and `main.js` folder `src` and file `chance.min.js` folder `node_modules/chance-js/dist/`. So the copied file `chance.min.js` in the process of the build renamed to `chance.js`. Continuous Integration for the build are included, i.e. build is triggered when any without refreshing in any branch of this repository: **3 points**.
13. Created release Azure Pipelines, which publishes artifacts ("results") build applications from the private Git repository to Azure Repos on GitHub Pages. As the artifact to release the selected build of the application from the Git repository of the project in the Azure Repos. Created a stage where the publication artifacts ("results") build on GitHub Pages. The stage starts automatically. Publication is done using a bash-script, similar to that created by the execution of the Task 1 of this module. Publication takes place in any personal repository on GitHub to a folder `azure` branches `gh-pages`. A release is a release is started upon successful completion of the build of the application, and deploys the application with all the changes to GitHub Pages: **3 points**.

**Total job**: 26 points.

## You can

* [Go to next module](tds_module3-about.html) <i class="fa fa-arrow-down" aria-hidden="true"></i>
* <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with the jobs plugin](tds_module2-tasks.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}