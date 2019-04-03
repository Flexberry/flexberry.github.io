---
title: Rules for working with git
sidebar: flexberry-platform_sidebar
keywords: Code
summary: Definitions, basic cloning, create a new branch, make changes, remove the repository
toc: true
permalink: en/fp_git-rules.html
lang: en
autotranslated: true
hash: ea844793996eba5ccbd07d8d3587f8f440d8881c86c95df0bf5015c18a66037a
---

**Git** (not to be confused with github) is a set of console tools that track and record changes to files. With its help, you can revert to an older version of the project, to compare, to analyze, merge changes and much more. This process is called version control.

Git is distributed, i.e. does not depend on one Central server that stores the files. Instead, it works completely locally, storing the data in the folders on the hard disk, called a repository. However, you can store a copy of the online repository, it is much easier working on one project for several people.

[GitHub](https://github.com/) is one of the popular hosting git repositories.

## Basic commands for git

For git you can use a GUI app (including the interfaces for working with git in Visual Studio and WebStorm), but in most cases, working with git from the console (terminal emulator).

We recommend that you initially used to working with git from the console. Moreover, graphical interfaces do not cover all the functionality of git, providing only the most commonly used features.

### Cloning an existing repository

To obtain a copy of an existing Git repository-for example, the project you need to make changes, you must use `git clone`.

```git
# Клонирование репозитория осуществляется командой git clone [URL].
git clone https://github.com/Flexberry/ember-flexberry.git 
```

### Branching

**Branch** is used for the simultaneous and independent development. The main branch is `master` or `develop`. Other branches are fixes and changes which are not yet added to the main branch.

To switch between branches, you must use `git checkout <branchname>`.

Make a new branch and switch to it at once, execute `git checkout-b <new branch>`.

{% include note.html content="When you create branches follow the rules of their naming. If the branch to dobavleny new functionality, its name should be: `feature-<number of tasks if there is a>-<task title>`. If a branch for patches, `fix-<number of tasks if there is a>-<task title>`." %}

```git
# Переключиться на ветку от которой надо наследоватся (обычно это основная ветка master или develop)
git checkout develop

# Получить последнии изменения для текущей ветки.
git pull

# Создать новую ветку.
git checkout -b <имя новой ветки>
```

Merge branch into the current, with the command: `git merge <branch>`.

### The record of changes in repository

**Staging area changes (staging area)** - the area where changes(files) to be included in the commit.

Add changes to staging area - `git add <file name>` or `git add *` if it is necessary to include all changes.

Remove changes from staging area - `git checkout -- <file name>` or `git checkout -- *` if you need to remove all modifications.

To view the contents of the working directory and staging area - `git status`

To fix(save) the prepared changes to the local repository - `git commit-m "your commit message"`

{% include note.html content="Creating a commit is necessary to use a standard Conventional team Commits." %}

### Working with remote repository

**Remote** repository which is considered to be General (located on github), receives commits from the local repository, what would other programmers can see them. Remote repositories can be multiple, but usually it is one.

To latest changes made in the branch from the remote repository `git pull`

Send zafiksirovany changes from your local repository to the remote - `git push origin <branch>`

## Useful links

* [Simple cheat sheet for git](http://rogerdudler.github.io/git-guide/index.ru.html)
* [A very good tutorial (practice)](https://try.github.io)
* [How to work with git on a real project](http://habrahabr.ru/post/106912/)
* [Optional: the book ProGIT](https://git-scm.com/book/ru/v2)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}