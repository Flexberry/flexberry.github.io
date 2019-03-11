--- 
title: Rules for translating articles 
sidebar: flexberry-platform_sidebar 
keywords: Flexberry Platform 
toc: true 
permalink: en/fp_translation-article.html 
lang: en 
autotranslated: true 
hash: 1f0d7249d67cf4229788592811e770d8da7c8cbbb2ca668b8aa1b0efe653be8b 
--- 

{% include note.html content="In case of any questions contact [chat](https://gitter.im/Flexberry/PlatformDevelopment)." %} 

## Connect to GitHub 

Esli check on GitHub, which you made earlier, go to "connecting to a content editor"._ 

### Check on GitHub 

* Open [github.com](https://github.com/). 
* Next enter the required details for registration and click `Sign up for GitHub`. 

![](/images/pages/products/flexberry-platform/work-article/check-in-github.png) 

* Next will be displayed the registration form in a few steps. 
* The first step is to check the entered data and then click `Create un account`. 

![](/images/pages/products/flexberry-platform/work-article/check-in step1.png) 

* Confirm the registration (a confirmation e-mail will be sent). 
* In the second step, leave all defaults (marked in the upper circle). 

![](/images/pages/products/flexberry-platform/work-article/check-in step2.png) 

* The third is to mark the appropriate items and click `Submit`. 

![](/images/pages/products/flexberry-platform/work-article/check-in step3.png) 

* Registration is complete. 

### connection to the content editor 

* Open [prose.io](http://prose.io/). 
* Choose `AUTHORIZE ON GITHUB`. 

![](/images/pages/products/flexberry-platform/work-article/open-prose-io.png) 

* Next, confirm the entry by `Authorize application`. 

![](/images/pages/products/flexberry-platform/work-article/authorize-application.png) 

## to Access the repository and directory 

Open repository [flexberry.github.io](http://prose.io/#Flexberry/flexberry.github.io). Next, go to the directory of articles on the left of the website: 

![](/images/pages/products/flexberry-platform/work-article/open-folder.png) 

All necessary articles are in the folder `pages` and is divided into subdirectories according to name of product or category. To open the subdirectories to the level of individual articles, as the work is carried out with the article. __The article for translation into English must contain a Postfix__ `en.md`. For example, `pages/products/flexberry-aspnet/controls/fa_show-hide-div.en.md`. 

## work with the content of the article 

For writing articles uses [Markdown](http://daringfireball.net/projects/markdown/). Articles in English must also comply with the syntax of Markdown. 

### translation of the article. 

Transfer the contents of the article into English without changing its structure and layout.

### Saving articles 

__The article before it is translated and all changes made are not saved!__ 

On the right side of the screen click on the button `Changes to submit` 

![](/images/pages/products/flexberry-platform/work-article/save-article.png) 

Will show all the changes. Should be in the upper right corner of the block `Describe your Changes` to enter a comment about the changes (for example, a Translated article and the name of the article). 

Next, click `SUBMIT CHANGE REQUEST`. 

### Tracking submit request 

Next you need [track sent submit request](https://github.com/Flexberry/flexberry.github.io/pulls). 

The submitted changes are verified. They can be written in the comments that you will need to correct. 

To make corrections to the existing pull-request the article, you must do the following: 

* In the list of pull requests pending verification, click on the one that you want to edit. 
Next, click on the comment to pull-request. 

![](/images/pages/products/flexberry-platform/work-article/change-pull-request1.png) 

* For the list of changes in the upper right corner click on `Change this file...` (in pencil). 

![](/images/pages/products/flexberry-platform/work-article/change-pull-request2.png) 

* To make changes. In the bottom of the page to enter a comment (which is fixed), to leave marked the first "circle" (a commit in the same pull-request) and click `Commit changes`. 

![](/images/pages/products/flexberry-platform/work-article/change-pull-request3.png) 

Changes will be added to the same pull-request. 

### Assessment of the changes made 

After making the request-and the need [to go to the tab with the commit](https://github.com/Flexberry/flexberry.github.io/commits/master). 

You should make sure that the spec for the commit corresponding to the accepted request, passed with no errors (there are no icons red x). If the build fails (x is), then let those who have accepted pull-request. 

If the build is successfully assembled, go to [flexberry.github.io](https://flexberry.github.io), to switch to English. Find perevedeno article, and make sure that the final application with documentation it looks correct (if the article is something does not look right and need to correct, you should repeat the steps in the section "Working with the contents of the article". 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}