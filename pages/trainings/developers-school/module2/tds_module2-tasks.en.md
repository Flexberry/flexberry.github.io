---
title: Module 2. Job
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: en/tds_module2-tasks.html
lang: en
autotranslated: true
hash: 165260fcc045fabf98ba38b7ff555e0061f0310e145eb99757ec5d4d83e48dd6
---

## The expected results of tasks execution

According to the results of assignments the student must:
1. To be able to use the tools of team work on the platform GitHub:
* Create Issues, to customize the list of templates and their creation and use labels for Issues
* To create and use Kanban Board to add Issues on the Kanban Board
* To pomilovat group Issues in iteration use Milestones
* Create Pull Requests and link them with Issues
* Configure server builds, Continuous Integration and automatic deployment of applications using Travis CI
2. To be able to use tools of teamwork Azure DevOps (TFS):
* Create the organization and projects in the Azure DevOps
* Working with Kanban boards, and plan the iteration
* To work with Git and TFVC repository, to tie code changes to tasks
* Create server builds
* To automatisierte releases of the application on the basis of performance of the server builds
* Use gallery-based packet Azure Artifacts

## Assignments for module

### Task 1. The use of a command-work of the GitHub platform

1. The repository that was used while performing the task the first training module (with the code of the client application), to create templates for Issues create new challenges and new mistakes. Text templates can be arbitrary, but must match the type of the created Issue. Using the template for a task in the Issue should also be added to the label Feature, and using the template for error - label Bug.
2. To create three Issue-based templates two tasks and one error. Functionality for tasks and errors to invent your own (as errors, for example, you can describe the necessity of correction of colors or styles or other parts of the application).
3. All created Issues add Milestone, the expected period of performance Milestone set any within two weeks from the moment of creation Issues.
4. Inside the repository from the client application to create the Kanban Board where all the tasks can be in one of four States: "New", "Active", "In progress", "Completed" (the name of the corresponding "cards" on the Kanban Board can be both Russian and English).
5. All created Issues to add to the Kanban Board and initially ask them the status "New".
6. Create two additional accounts (for example, with temporary emails) on GitHub for work on behalf of other developers on Your problems. One of the created accounts to give access for making changes to your repository from the client application, else the access is not granted.
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

### Task 2. The use of Azure DevOps (TFS)
1. To create a new organization in [Azure DevOps Services](https://azure.microsoft.com/ru-ru/services/devops/).
2. In the organization of the Azure DevOps Services to create a private project. For a new project:
* As a version control system to choose Git
* The process for work items to choose Agile
3. With the project created, add a TFVC repository to Azure Repos
4. Create one additional account to Azure DevOps (for example, with temporary emails) - to work on behalf of another developer on Your problems. New user to add created organization with level `Basic`.
5. The backlog project in Azure Boards to establish three requirements (User Story) with the following titles:
* `Создание npm-package chance-js`
* `Установка package chance-js in приложение`
* `Настройка CI/CD for приложения`
6. For requirements `Создание npm-package chance-js` to add tasks with the following titles:
* `Добавление source file репозиторий`. For the tasks to add a description. Task assign to user "main" (Your) account.
* `Создание build to publish пакета`. For the tasks to add a description. Task assign to user "main" (Your) account.
7. For requirements package `Установка chance-js in приложение` to add tasks with the following titles:
* `Добавление package depends on chance-js`. For the tasks to add a description. Task assign to user "additional" account.
8. PstrfНастройка requirements for CI/CD for приложения` to add tasks with the following titles:
* `Добавление build приложения`. For the tasks to add a description. Task assign to user "additional" account.
* `Публикация app on GitHub Pages`. For the tasks to add a description. Task assign to user "main" (Your) account.
9. To schedule the created tasks per iteration. Iteration should have two weeks from the date of commencement of this assignment. The name of the iteration can be arbitrary.
10. In the TFVC repository project in Azure Repos to add files from the [GitHub repository of the library of Chance](https://github.com/chancejs/chancejs):
* Clone the GitHub repository of the library `Сhance` on the local machine.
* The file `package.json` to change the package name in `chance-js`
* The file `gulpfile.js` using the library `gulp-uglify` replace [`gulp-butternut`](https://github.com/Zettersten/gulp-butternut) for problem `build`.
* Check that the teams `gulp lint`, `gulp test` and `gulp build` run locally without errors.
* To add files from a modified version of the library `Chance` from the local machine in the TFVC repository (using Visual Studio or through the web interface of Azure DevOps Services). Adding files to produce one or more check-ins. Every check must be associated with the task `Добавление source file репозиторий` requirements `Создание npm-package chance-js`. Comments to check-ins must have the following format: `<date>. <Task name>. <Review>.`
* As you complete a task, its status should change on the Board. The check should be made by the user to whom task was assigned. Task after the work on it must be in the status `Resolved`.
11. In the Git project repository in Azure Repos to add the files from the GitHub repository [Issue Tracker Demo](https://github.com/ehaberev/issue-tracker-demo) (you can pre-clone the repository to your local machine).
12. To create a "ribbon" Azure Artifacts:
* In the setting of "tape" must be set to the Upstream source to access npm packages from the repository https://registry.npmjs.org through this "tape" (in the app to be able to install not only the packages in the "ribbon", but also from the public repository npm packages).
13. To create a build, which will publish npm package `chance-js` from TFVC repository to Azure Repos, in "the tape" Azure Artifacts:
* You can use any suitable pool of agents.
* The build process of the application before you publish the npm package, you need to execute gulp tasks `lint`, `test` and `build`.
* Continuous Integration for this build should not be included, that is, the build must be run manually.
* As you create the build task status `Создание build to publish пакета` requirements `Создание npm-package chance-js` needs to change on the Board. Task after the work on it must be in the status `Resolved`.
* Created by the build to publish the package `chance-js` in a "band" Azure Artifacts of the project.
14. In the application from the Git repository to Azure Repos add a dependency on the npm package `chance-js` published in "tape" Artifacts Azure project to the Azure DevOps Services:
* Changes to make in a separate branch according to the git flow principles. Create Waki, all commits and pull request should be attached to the task `Добавление package depends on chance-js` requirements `Установка package chance-js in приложение`.
* File `chance.js` folder `src` needs to be removed.
* The app must be installed `chance-js` from the "feeds" Azure Artifacts of the project.
* When running locally the app Issues should not be created because the file `chance.js` will be missing (because we don't use "garbage" for the application file `chance.js` will be "placed" in running a server build).
* As you complete a task, its status should change on the Board. Commits must be done by the user to whom task was assigned. Task after the work on it must be in the status `Resolved`.
* Upon completion of the task should be created a pull request as "reviewer" this pull request should be assigned to the user "principal" (Your) account. This user must confirm task execution results execution "will codereview". After confirming the completion of a task "removeroom" pull request can be accepted.
15. To create a build that will build the application from the Git repository to Azure Repos in the form suitable for publishing on GitHub Pages:
* You can use any suitable pool of agents.
* In the process of implementation build the command should run `npm run lint` to run linting.
* The build in "result" folder (drop folder) must be copied files `index.html` and `main.js` folder `src` and file `chance.min.js` folder `node_modules/chance-js/dist/`. The file `chance.min.js` needs to be renamed to `chance.js` in the copy process.
* Continuous Integration for the build should be included.
* As you create the build task status `Добавление build приложения` requirements `Настройка CI/CD for приложения` needs to change on the Board. Task after the work on it must be in the status `Resolved`.
16. To create a release to Azure Pipelines, which will publish the artifacts ("results") build applications from the private Git repository at GitHub Pages:
* Create an empty Azure release in the Pipelines of the project.
* As a artifact to release to select build application from the Git repository of the project in the Azure Repos.
* Create single step, which will be implemented by publication of artifacts ("results") build on GitHub Pages. Stage should start automatically. You must publish by using a bash-script, similar to that created by the execution of the Task 1 of this module. Publication should be carried out in any of Your personal repository on GitHub to a folder `azure` branches `gh-pages`.
* As you create the release task status `Публикация app on GitHub Pages` requirements `Настройка CI/CD for приложения` needs to change on the Board. Task after the work on it must be in the status `Resolved`.
* To check the efficiency of release, initiating execution of the build of the application.

**Support resources:**
1. [Official documentation for the Azure DevOps](https://docs.microsoft.com/en-us/azure/devops/?view=azure-devops).
2. [A review of the app the Issue Tracker Demo](https://www.youtube.com/watch?v=JT_vDwlQRHs)

## You can

* [Go to checklist of verification tasks](tds_module2-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
* <i class="fa fa-arrow-left" aria-hidden="true"></i> [go to the page with additional content module](tds_module2-appendix.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}