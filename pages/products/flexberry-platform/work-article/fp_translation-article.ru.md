---
title: Правила перевода статей
sidebar: flexberry-platform_sidebar
keywords: Flexberry Platform
toc: true
permalink: ru/fp_translation-article.html
lang: ru
---

{% include note.html content="В случае возникновения вопросов обращаться [в чат](https://gitter.im/Flexberry/PlatformDevelopment)." %}

## Подключение к GitHub

_Если регистрация на GitHub осуществлена ранее, перейти к пункту "Подключение к редактору контента"._

### Регистрация на GitHub

* Открыть [github.com](https://github.com/).
* Далее ввести необходимые данные для регистрации и нажать `Sign up for GitHub`.

![](/images/pages/products/flexberry-platform/work-article/check-in-github.png)

* Далее будет отображена форма регистрации в несколько шагов.
  * На первом шаге надо проверить введенные данные а затем нажать `Create un account`.

![](/images/pages/products/flexberry-platform/work-article/check-in-step1.png)

  * Подтвердить регистрацию (на указанный e-mail придет письмо).
  * На втором шаге оставить все по умолчанию (отметка в верхнем кружочке).

![](/images/pages/products/flexberry-platform/work-article/check-in-step2.png)

  * На третьем - отметить подходящие пункты и нажать `Submit`.

![](/images/pages/products/flexberry-platform/work-article/check-in-step3.png)

* Регистрация окончена.

### Подключение к редактору контента

* Открыть [prose.io](http://prose.io/).
* Выбрать `AUTHORIZE ON GITHUB`.

![](/images/pages/products/flexberry-platform/work-article/open-prose-io.png)

* Далее подтвердить вход по `Authorize application`.

![](/images/pages/products/flexberry-platform/work-article/authorize-application.png)

## Перевод статьи

## Открыть репозиторий и каталог

Открыть репозиторий [flexberry.github.io](http://prose.io/#Flexberry/flexberry.github.io). Далее перейти к каталогу статей в левой части сайта:

![](/images/pages/products/flexberry-platform/work-article/open-folder.png)

Все необходимые статьи лежат в папке `pages` и разделены на подкаталоги в соответствии с наименованием продукта или категории. Открывать подкаталоги следует до уровня отдельных статей, так как работа осуществляется именно со статьей.

## Выбор статьи

__Статья для перевода на английский язык должна содержать постфикс__ `en`.
Если заданная статья имеет соответствующий постфикс, то следует перейти к разделу "Работа с содержанием статьи".
Если же постфикс отсутствует, следует создать __копию статьи__ с соответствующим постфиксом.

### Создание копии статьи

Для того чтобы создать копию статьи, следует сделать следующее:

* Открыть статью на редактирование, кнопка `Edit`.
* Скопировать все содержимое (рекомендуется наименование статьи скопировать заранее и вставить, например, в блокнот).
* Вернуться в каталог, в котором расположена статья. Нажать `New File`.

![](/images/pages/products/flexberry-platform/work-article/new-article.png)

* Создать новый файл с тем же именем, но постфиксом `en` вместо `ru` в наименовании (наименование выделено __жирным__ в верхней части страницы).
* Вставить в него все скопированное содержимое.

__Статью до того, как она переведена и все изменения внесены, не сохранять!__

## Работа с содержанием статьи

### Редактирование заголовка статьи

В тексте статьи нужно поменять `permalink` и `lang`, заменив постфикс `en` постфикс `ru`. Для этого нажать кнопку `Meta Data` в правой части страницы:

![](/images/pages/products/flexberry-platform/work-article/update-toc.png)

Далее нажать `Done`.

{% include note.html content="Для созданных статей (пункт `Создание копии статьи`) редактирование заголовка возможно только после сохранения статьи, то есть в режиме редактирования pull-request (пункт Отслеживание submit request)." %}

### Перевод статьи.

Перевести содержимое статьи на английский язык, не меняя её структуры и разметки.

### Сохранение статьи

__Статью до того, как она переведена и все изменения внесены, не сохранять!__

В правой части экрана нажать на кнопку `Changes to submit`

![](/images/pages/products/flexberry-platform/work-article/save-article.png)

Будут отображены все внесенные изменения. Следует в правом верхнем углу в блоке `Describe your Changes` ввести комментарий об изменениях (например, Translated article и наименование статьи).

Далее нажать `SUBMIT CHANGE REQUEST`.

### Отслеживание submit request

Далее нужно [отслеживать отправленный submit request](https://github.com/Flexberry/flexberry.github.io/pulls).

Отправленные изменения будут проверены. К ним могут быть написаны замечания, которые потребуется исправить.

Чтобы внести исправления в существующий pull-request статьи, следует сделать следующее:

 * В списке pull-request, ожидающих подтверждения щелкнуть по тому, который требуется отредактировать.
Далее нажать на комментарий к pull-request.

![](/images/pages/products/flexberry-platform/work-article/change-pull-request1.png)

* На открывшемся списке изменений в правом верхнем углу нажать на кнопку `Change this file...` (в виде карандаша).

![](/images/pages/products/flexberry-platform/work-article/change-pull-request2.png)

* Внести изменения. В нижней части страницы ввести комментарий (что исправлено), оставить отмеченным первый "кружок" (коммит в тот же pull-request) и нажать `Commit changes`.

![](/images/pages/products/flexberry-platform/work-article/change-pull-request3.png)

Изменения будут добавлены к тому же pull-request.

### Оценка принятых изменений

После принятия request-а нужно [зайти на вкладку с коммитами](https://github.com/Flexberry/flexberry.github.io/commits/master).

Следует удостовериться, что билд на коммит, соответствующий принятому request-у, прошел без ошибок (рядом с ним нет иконки красного крестика). Если билд не пройден (крестик есть), то сообщить тому, кто принимал pull-request.

Если билд успешно собран, зайти на [flexberry.github.io](https://flexberry.github.io), переключиться на английский язык. Найти переведеную статью, и удостовериться, что в итоговом приложении с документацией она выглядит корректно (если в статье что-то выглядит не так и нужно её подправить, то следует выполнить пункты раздела "Работа с содержанием статьи".
