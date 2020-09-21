--- 
title: Guidelines for writing documentation 
sidebar: flexberry-platform_sidebar 
keywords: Flexberry Platform, design articles, documentation, regulations 
summary: Structure, style, rules, registration of notes, images, links, and code examples 
toc: true 
permalink: en/fp_documentation-writing-guidelines.html 
lang: en 
autotranslated: true 
hash: c1277accf704be8ea95c004304e42cdb1d3aaa9011ca1c49c6bcdf5c25bbca39 
--- 

Documentation is an integral part of any successful product, so you need to approach the documentation of developed products with the utmost responsibility and to promptly update the information. 

The following are guidelines for writing documentation for platform products [Flexberry](https://flexberry.github.io/ru//fp_landing_page.html). 

Documentation is generated using the generator of static sites [Jekyll](https://jekyllrb.com/) and therefore needs to have an idea about [Markdown syntax](https://daringfireball.net/projects/markdown/syntax) to create articles. 

## design Rules 

Source code documentation stored in the repository <https://github.com/Flexberry/flexberry.github.io>. 

* All articles are linked to the overall structure of articles in accordance with the name of the product and its features. 
* Required to observe the consistency of terminology, for this there is a special [Glossary](fp_glossary.html). 
* All phrases the articles are constructed in __anonymously__ with the literature and professional vocabulary. 

### directories 

The file with the texts of articles are placed in subdirectories, sootvetstvuyuschim products and categories in the directory _pages/products_, and are named according to the pattern _\[product-prefix\]\_\[article-name\].\[lang\].md_ where: 

* `product-prefix` prefix, the abbreviation of the product 
* `article-name` the title of the article in English 
* `lang` - language articles, valid values are **ru** and **en** 

For example, a file with the text of the article has the following name: _pages/products/flexberry-platform/work-article/fp_how-create-article.EN.md_. 

The screenshots should be placed according to the placement of the product (_pages\products\product-name\product-catalog\product-catalog_). The pictures must be assigned unique names or numbers to the text of the article could easily refer to them. The preferred format `.png`. 

### table of contents (sidebars) 

When adding an article, you need to add an entry for it in the contents section of the relevant product. 

Table of contents sections are described in the language of [YAML](https://yaml.org/) in the file _\_data/sidebars/\[product name\]\_sidebar.yml_. 
The article should contain the following information: 

* `title` - the name links to the article in the table of contents in English 
* `title_ru` - the name links to the article in the table of contents in Russian 
* `url` - link to the article, without specifying the language, format, _/\[product-prefix\]\_\[article-name\].HTML 
* `output` formats generation of article, comma-separated, valid values **web** and **pdf** 

For example, in the file _\_data/sidebars/flexberry-platform\_sidebar.yml_ contains the following entry about this article: 

```yml
# ...
    - title: Documentation writing guidelines
      title_ru: Рекомендации по написанию документации
      url: /fp_documentation-writing-guidelines.html
      output: web, pdf
# ...
``` 

{% include note.html content="More information about the table of contents and navigation can be found on [idratherbewriting.com](https://idratherbewriting.com/documentation-theme-jekyll/mydoc_sidebar_navigation.html)." %} 

### Metadata article 

At the beginning of each file with the text of the article should be a block with metadata, it is surrounded by three characters dash (`---`), and include the following information: 

* `title` (name of article). Title of the article should be fairly concise and comprehensive, reflecting the main thrust. 
* `summary` (description). Brief description of the content of the article should give understanding to the reader about the content - will allow you to quickly assess whether he passed. Use the index to search for articles. 
* `sidebar` (linked to table of contents section, see as indicated in the adjoining articles) 
* `keywords` (keywords, fill in a comma - search) 
* `toc` (to add a table of contents with links in the headers (under the main menu on the left), usually `true`) 
* `permalink` is a unique link to the article in format _\[lang\]/\[product-prefix\]\_\[article-name\].HTML 
* `lang` - language articles, valid values are **ru** and **en** 

For example, a block with metadata for the article, will link as follows: 

```md
---
title: Рекомендации по написанию документации
sidebar: flexberry-platform_sidebar
summary: Рекомендации, советы, и требования, которые нужно учесть при создании статьи.
toc: true
permalink: en/fp_documentation-writing-guidelines.html
lang: en
---
``` 

{% include note.html content="More information about the unit metadata can be found at [idratherbewriting.com](https://idratherbewriting.com/documentation-theme-jekyll/mydoc_pages.html#frontmatter)." %} 

### Making the content of the article 

* Blocks articles begin with second level header (##). Before and after the title should leave a blank line. 
* References are made in the following way: `[text](link)`. If the phrase includes a link to a few articles in resultate what the offer looks like one big link, the terms are recommended to be put in a separate paragraph. Therefore, the terms can be given a brief explanation and a link to the definition without overloading a main text. 
* Screenshots are inserted in the following way _\!\[\]\(/images/pages/products/product-name/product-name]/image-name.png)_. Separated by a blank line before and after screenshot (with the exception of examples of the buttons in the text). 
* If the text part need to pay special attention, you can add [note](https://flexberry.github.io/mydoc_alerts.html): 

{% include note.html content="Text" %} 

* [Code examples](https://flexberry.github.io/mydoc_code_samples.html) are separated by a blank line and three apostrophes before and after the code. You can also specify [language Primera](http://idratherbewriting.com/documentation-theme-jekyll/mydoc_syntax_highlighting.html#available-lexers). The language for the examples on the c# referred to as `csharp`. 

{% include note.html content="Before you proceed with the article should do a pull (sync version). **Before the commit of the article should also make pull!** Otherwise merge will be implemented that can remove the changes." %} 

## Useful links 

* [Quick guide to Markdown](https://paulradzkov.com/2014/markdown_cheatsheet/). 
* [Linter](https://marketplace.visualstudio.com/items?itemName=DavidAnson.vscode-markdownlint) - indicates that a formatting error in style `markdown`. 
* [Doc Jekyll Theme](https://idratherbewriting.com/documentation-theme-jekyll/) which we use for documentation. 
* [GitHowTo](https://githowto.com/ru) - interactive tour of the capabilities of the _git_. 
* [GitHub Help](https://help.github.com/) - help with _git_. 
* [Open issues](https://github.com/Flexberry/flexberry.github.io/issues) in the repository with the documentation. 

## It's interesting 

* [7 of the rules of writing technical documentation world-class](https://habr.com/ru/post/303760/) - interesting article, but it is necessary to consider specificity of audience for which written or another article. That will be favorably perceived by beginners (section [Practical guide](https://flexberry.github.io/ru//gpg_landing-page.html)) with high probability will lead to confusion for the professional (article about platform products Flexberry). 
* [Writing technical documentation: a guide for the layman](https://habr.com/ru/post/421549/) - have described the process of creating the documentation, this valuable tips on creating an "enabling environment", not while slipping to journalism. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
