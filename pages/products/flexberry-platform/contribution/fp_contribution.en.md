--- 
title: platform Development community 
sidebar: flexberry-platform_sidebar 
keywords: Pull request, Issue, revision 
toc: true 
permalink: en/fp_contribution.html 
lang: en 
autotranslated: true 
hash: 4ddc590d17425282d692a9a37affe9e030a94c970b88787c61b1c9e26906bdb4 
summary: instructions on how to contribute to the platform Flexberry. 
--- 

## Introduction 

Flexberry platform is open and extensible technology for design and development. Developers that are actively using the platform can contribute, expanding and enriching the platform. The source code of the component was published with the aim to involve the community to work together on a platform that allows more efficient and faster to develop it for the benefit of users as the platform and user end applications. 

## the Process of platform development 

### Recommended development tools 

To see a list of recommended tools for the development of the [article](fp_tool-description.html). 

### GitFlow 

The development of platform components based on the [gitflow](https://proglib.io/p/git-github-gitflow/). 

### Issues and Pull requests 

Discovered problems in copmonent platform Flexberry `Issues` are recorded in the repository. Welcome to repair problems sent in `Pull request` (PR) in the branch `develop`. 
Here's how: 
1. You need to perform [Fork](https://help.github.com/articles/fork-a-repo/) repository to itself. 
2. Run [cloning](https://help.github.com/articles/cloning-a-repository/) your repository. 
3. To make the required revision in the source code, perform `commit` and `push` to your repository (you have to understand how to work with [git](https://help.github.com/articles/git-cheatsheet/)). 
4. Via the web interface <https://github.com> create `Pull request` branch `develop` repository, which was made `Fork` according to [the instructions from GitHub](https://help.github.com/articles/creating-a-pull-request-from-a-fork/). 
5. If the result of the analysis will be created `Pull request` comments requiring revision of the code, it is enough to perform a revision in your repository in the same branch, which was created `Pull request` new commits will be automatically added to `Pull request` (every commit will be assembled on `Travis-CI`). 

Requests for major changes should be made in [RFC-repository platform Flexberry](https://github.com/Flexberry/rfcs). 

### code Style 

During development it is common for the platform Flexberry [approach to the design code](fp_code-style.html) to adhere to PR was successfully taken. 

### Checklist for code review 

In addition to the code style, there are [other criteria](fp_code-review-check-list.html), which is checked Pullrequest with modifications.

### Testing platform components 

Projects on the C# contain tests: 
* Autonomous unit-tests run on Travis-CI for each commit and PR. 
* Integration unit tests - require for execution access to multiple DBMS and executed by the developers, as well as on the server, preparing NuGet packages. 

Projects in EmberJS contain tests running on Travis-CI for each commit and PR. 

### Recommendations for debugging 

Guidelines for debugging are described in [article](gbt_debugging.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}