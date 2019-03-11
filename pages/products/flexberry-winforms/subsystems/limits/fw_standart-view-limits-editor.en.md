--- 
title: Standard of editor limitations. Format restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Constraints 
summary: Describes the capabilities set restrictions for attributes of type date and logical attributes and the use of SQL expressions restrictions 
toc: true 
permalink: en/fw_standart-view-limits-editor.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 83cd1d7d664c63a1cb411112cbfd05c85b263d62a4d88dcc6a65aefdcbf30b5c 
--- 

In this article, we describe a possible format of constraints, which correspond to the editable constraints in standard form. 

## Logical attributes and operations 
For Boolean attributes following operations are possible: 
* " "(Space) - Boolean attribute is inserted without an operation. For example, if you write "Recent And Relevant" 
* "=", "<>" - You can compare only attributes with parameters of type Boolean. For example, "True = @param". In the standard view editor to compare two fields or compare a field with a truth impossible. For the first case you need to use the advanced editor for the second you can just insert the attribute without the use of surgery. 
* "Not empty", "Not filled", "Not" Wrap the attribute in the appropriate option, only accept 1 parameter 
* "AND", "OR" 

If you create a restriction in the extended constraint editor with Boolean operations, it must meet the following criteria: 
* Should only be used the above-mentioned operations 
* If you are using the operation "=", "<>", then the first operand must be an attribute from the view, and the second parameter(@param). For example, "True = @param"; 

## Functions for working with dates 
Working with dates in standard form described in [Functions for working with dates in 'standard view'](fw_date-limits-standart-view.html) 

If you create a restriction in the extended constraint editor with functions for date, it must meet the following criteria: 
* If the comparison operation "=", "<>", ">", "<" and so as the operand of a function over an attribute that returns a DateTime value, the second operand must be the same function. For example, "Only date(Databasenya) = date(Today())". A function of type "date Only(Delaroderie) = Today()" to not display будет; 

## the Job SQL expression 
The job SQL expression is available only as a logical operand. The expression it is possible to write in the text box of the editor, or to open a form to specify SQL expressions. Click on the "..." button in the row set values for SQL functions. 

In SQL expressions, there may be line breaks. The result of the query must be a Boolean value. 

For information about software using SQL expressions in the constraints described in [funcSQL](fo_func-sql.html).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}