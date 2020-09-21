---
title: Представления в ember-flexberry-приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd2_model-projection.html
folder: products/ember-flexberry-data/models-and-projections/
lang: ru
summary: Представлено детализированное описание того, как задаются представления в ember-flexberry-приложении.
---

## Определение представлений

Представления определяются для [моделей](efd2_model.html).
Представления используются для определения, какие свойства будут запрошены с сервера или отправлены на него. Определение представлений для модели осуществляется следующим образом:

```javascript
Model.defineProjection('<Имя представления>', '<Имя класса>', '<Атрибуты представления>');
```

*Имя представления* может быть произвольным. Чаще всего для форм редактирования используют представления с именем "<Короткое имя класса>E", а для списковых форм - "<Короткое имя класса>L".

*Имя класса* - это имя текущего класса, для которого [определяется модель](efd2_model.html). Например, `new-platform-someproject-somemodel`.

*Атрибуты представления* - это атрибуты [модели и зависимых моделей](efd2_model.html), которые входят в представление.

Рассмотрим представленный ниже пример. Здесь для модели `flexberry-ember-demo-suggestion` определяется представление `SuggestionE`.

`address: Proj.attr('Address')` - в представление добавляется свойство `address` модели `flexberry-ember-demo-suggestion` с заголовком `Address`.

`type: Proj.belongsTo('flexberry-ember-demo-suggestion-type', 'Type', { name: Proj.attr('Name') })` - в представление добавляется ссылка на мастера `type` модели `flexberry-ember-demo-suggestion` на тип `flexberry-ember-demo-suggestion-type` с заголовком `Type`, а также свойство `name` с заголовком `Name` модели `flexberry-ember-demo-suggestion-type`.

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

- в представление добавляется ссылка на детейлы `comments` модели 'flexberry-ember-demo-suggestion' с заголовком 'Comments'. Детейлы имеют тип 'flexberry-ember-demo-comment'. Из детейлов в представление попадают собственные свойства детейла, а также ссылка на мастера детейлов `author`, у которого свойство `name` скрыто (такое скрытие свойств мастеров часто используется для работы [лукапов](ef2_lookup.html)).

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
