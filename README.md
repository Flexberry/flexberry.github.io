Flexberry Documentation generated using [YUIDoc](http://yui.github.io/yuidoc/).

## Prerequisites
* [Node.js](http://nodejs.org/) (with NPM)
* `npm install -g yuidocjs`

## Installation
* `git clone --recursive https://github.com/Flexberry/Documentation.git`
* `npm install`

## Generation docs
* `git submodule update --remote --merge` (upgrade submodules and pull latest changes)
* `cd <branch_name>` (master or develop)
* `yuidoc` (pay attention to warnings in the process output)

## Running / Development
* `cd <branch_name>` (master or develop)
* `yuidoc --server`
* Visit [http://localhost:3000](http://localhost:3000).

## Adding a new project in Generation
* `git submodule add -b <branch_name> <project_repo_url> <branch_name>/<project_repo_name>`
* Add path to generate docs in `options.paths` array in `<branch_name>/yuidoc.json`.
