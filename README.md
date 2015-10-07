Flexberry Documentation generated using [YUIDoc](http://yui.github.io/yuidoc/).

## Prerequisites
* [Node.js](http://nodejs.org/) (with NPM)
* `npm install -g yuidocjs`

## Installation
* `git clone --recursive https://github.com/Flexberry/Documentation.git`
* `npm install`

## Generation docs
* `git submodule foreach git pull`
* `yuidoc` (pay attention to warnings in the process output)

## Running / Development
* `yuidoc --server`
* Visit [http://localhost:3000](http://localhost:3000).

## Adding a new project in Generation
* `git submodule add <project_repo>`
* Add path to generate docs in `options.paths` array in `yuidoc.json`.
