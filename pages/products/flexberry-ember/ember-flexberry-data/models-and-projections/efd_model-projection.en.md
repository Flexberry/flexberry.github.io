--- 
title: Views in ember-flexberry application 
sidebar: ember-flexberry-data_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/efd_model-projection.html 
folder: products/ember-flexberry-data/models-and-projections/ 
lang: en 
autotranslated: true 
hash: 0b524de44859f4e5f2c52075a4e27a46434e5599e5a41950bc611b61b05b71a3 
summary: Presents a detailed description of how to set view in ember-flexberry application. 
--- 

## Define views 

Views are defined for [models](efd_model.html). 
Views are used to determine which properties are requested from the server or sent to it. Define views for the model is as follows: 

```javascript
Model.defineProjection('<Имя представления>', '<Имя класса>', '<Атрибуты представления>');
``` 

*The name of the view* can be arbitrary. Most edit forms use a view with name "<Short name of the class>E", and list forms - "<Short name of the class>L". 

*The class name* is the name of the current class, for which [the defined model](efd_model.html). For example, `new-platform-someproject-somemodel`. 

*View attributes* - attributes [models and dependent models](efd_model.html), which are included in the presentation. 

Consider the example below. Here for models `flexberry-ember-demo-suggestion` the view is defined `SuggestionE`. 

`address: Proj.attr('Address')` - view adds a property `address` model `flexberry-ember-demo-suggestion` with the title `Address`. 

`type: Proj.'s belongsto('flexberry-ember-demo-suggestion-type', 'Type', { name: Proj.attr('Name') })` - in the view you add a reference to the wizard `type` model `flexberry-ember-demo-suggestion` type `flexberry-ember-demo-suggestion-type` with the title `Type` and property `name` with the title `Name` model `flexberry-ember-demo-suggestion-type`. 

```javascript
comments: Proj.hasMany('flexberry-ember-demo-comment', 'Comments', { 
		text: Proj.attr('Text'),
		votes: Proj.attr('Votes'),
		moderated: Proj.attr('Moderated'),
		author: Proj.belongsTo('flexberry-ember-demo-application-user', 'Author', { 
			name: Proj.attr('Name', { hidden: true }) 
		}) 
	})
``` 

in the view you add a reference to detaily `comments` model 'flexberry-ember-demo-suggestion' with the heading 'Comments'. Detaili have a type of 'flexberry-ember-demo-comment'. Of datalow into view get own properties of detail and also the link to the master detailov `author`, whose property `name` hidden (this hides the properties of the masters is frequently used for [lyapov](ef_lookup.html)). 

```javascript
Model.defineProjection('SuggestionE', 'flexberry-ember-demo-suggestion', { 
	address: Proj.attr('Address'),
	text: Proj.attr('Text'),
	date: Proj.attr('Date'),
	votes: Proj.attr('Votes'),
	moderated: Proj.attr('Moderated'),
	type: Proj.belongsTo('flexberry-ember-demo-suggestion-type', 'Type', { 
		name: Proj.attr('Name') 
	}),
	author: Proj.belongsTo('flexberry-ember-demo-application-user', 'Author', { 
		name: Proj.attr('Name') 
	}),
	comments: Proj.hasMany('flexberry-ember-demo-comment', 'Comments', { 
		text: Proj.attr('Text'),
		votes: Proj.attr('Votes'),
		moderated: Proj.attr('Moderated'),
		author: Proj.belongsTo('flexberry-ember-demo-application-user', 'Author', { 
			name: Proj.attr('Name', { hidden: true }) 
		}) 
	}),
	files: Proj.hasMany('flexberry-ember-demo-suggestion-file', 'Files', { 
		order: Proj.attr('Order'),
		file: Proj.attr('File') 
	}),
	userVotes: Proj.hasMany('flexberry-ember-demo-vote', 'User votes', { 
		voteType: Proj.attr('Vote type'),
		applicationUser: Proj.belongsTo('flexberry-ember-demo-application-user', 'Application user', { 
			name: Proj.attr('Name', { hidden: true }) 
		}) 
	})
})
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}