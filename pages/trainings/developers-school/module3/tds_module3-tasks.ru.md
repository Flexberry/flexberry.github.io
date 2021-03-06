---
title: Модуль 3. Задания
keywords: Programming, Training, Developers school
sidebar: training-developers-school_sidebar
toc: false
permalink: ru/tds_module3-tasks.html
lang: ru
---

## Ожидаемые результаты выполнения задания

По результатам выполнения задания обучающийся должен уметь создавать SPA-приложения на языке JavaScript без использования JS-фреймворков:

* Настраивать окружение для нового проекта
* Понимать архитектуру MVC (MVP/MVVM) современных JavaScript-фреймворков
* Уметь использовать возможности языка ES6 для реализации JavaSctipt-проектов
* Уметь работать с асинхронными вызовами в JavaScript
* Уметь организовывать взаимодействие с бакендом для получения данных приложения
* Уметь ораганизовать слабую связанность между компонентами приложения с использованием IoC/DI 
* Уметь использовать паттерны проектирования
* Уметь писать модульные, интеграционные и e2e-тесты
* Уметь генерировать автодокументацию к исходному коду проекта
* Уметь настроваить серверные билды и развертывание для приложения

## Задание по модулю

Требуется создать одностраниченое приложение (SPA) на основе [открытого тестового задания компании Aviasales](https://github.com/KosyanMedia/test-tasks/tree/master/aviasales_frontend) без использования JavaScript-фреймворков:

* [Описание клиентской части приложения](https://github.com/KosyanMedia/test-tasks/tree/master/aviasales_frontend#%D1%82%D0%B5%D1%81%D1%82%D0%BE%D0%B2%D0%BE%D0%B5-%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-aviasales-frontend)
* [Описание серверного API](https://github.com/KosyanMedia/test-tasks/blob/master/aviasales_frontend/server.md)
* [Макет страницы](https://www.figma.com/file/4fQe1lEbo4DARjvNtaU0uJ/Aviasales-test-task)
* Картинки авиакомпаний доступны по адресу http://pics.avs.io/99/36/{IATA_CODE_HERE}.png (например, http://pics.avs.io/99/36/SU.png)

### Требования к функционалу приложения

* В приложении должно быть два роута: роут для самых дешевых рейсов и роут для самых быстрых рейсов (соответствуют двум "вкладкам" на макете страницы). При первой загрузке приложения открывается роут для самых дешевых билетов. Текущий роут должен сохраняться в LocalStorage браузера, при перезапуске браузера должен открываться роут, сохраненный в LocalStorage (если в URL явно не указан один из роутов).

* При первой загрузке приложения (или перезагрузке текущей страницы) должны быть отрендерены все билеты, которые пришли в ответе с сервера, фильтры в блоке "Количество пересадок" должны быть сброшены. Подгрузка следующего набора билетов должна осуществляться автоматически при скроллинге до конца страницы, либо по нажатию на кнопку "Показать ещё билеты" (достаточно выбрать один из вариантиов; в макете данной кнопки нет, при необходимости добавить самостоятельно). После подгрузки следующего набора билетов должны отображаться только те билеты, которые соответствуют текущему фильтру в блоке "Количество пересадок".

* При выборе фильтров в блоке "Количество пересадок" список загруженных на текущий момент с сервера билетов должен быть отфильтрован в соответствии с выбранными фильтрами. Выбранный фильтр должен изменять параметры текущего URL (т.е. фильтры для текущего роута должны храниться в параметрах URL для данного роута). Выбранные фильтры должны сохраняться в LocalStorage браузера.

* В блок "Количество пересадок" добавить кнопку "Загрузить фильтры" (в макете данной кнопки нет, добавить самостоятельно). При нажатии на эту кнопку из LocalStorage должны подгружаться последние сохраненные там фильтры для текущего роута. Если в LocalStorage нет сохраненных фильтров для данного роута, кнопка должна быть неактивна.

* При смене роута фильтр в блоке "Количество пересадок" должен сбрасываться. При этом, если для соответствующего роута ранее был выбран фильтр в данном блоке, кнопка "Загрузить фильтры" должна быть активна.

* По желанию обучающегося могут быть реализованы дополнительные роуты - например, роут для просмотра подробной информации о билете, роут "О приложении" и т.п. В этом случае соответствующие дополнения к макету страницы обучающимся реализуются самостоятельно на свое усмотрение.

### Технические требования к приложению

1. Создать новый Git-репозиторий для проекта, настроить окружение:

    * Инициализировать проект при помощи менеджера пакетов `npm` (сгенерировать файл `package.json`), заполнить название и описание проекта. В качестве типа лицензии указать `MIT`. Добавить файл .gitignore (можно сгенерировать с помощью [этого](https://www.gitignore.io/) инструмента).
    * Настроить форматирование кода с использованием библиотеки `Prettier`:
        * Использовать одинарные кавычки в JS-коде
        * Ограничение длины строки - 100 символов
        * Использовать завершающие запятые в объектах, массивах и т.п.
        * Использовать 2 пробела для табуляции
    * Настроить линтинг JavaScript-кода при помощи библиотеки [`ESLint`](https://eslint.org/). [Конфигурация для `ESLint` должна быть основана](https://github.com/airbnb/javascript/tree/master/packages/eslint-config-airbnb-base) на [правилах написания JavaScript-кода от Airbnb](https://github.com/leonidlebedev/javascript-airbnb), все конфликтующие с `Prettier` правила должны быть [отключены](https://github.com/prettier/eslint-config-prettier). Для выполнения линтинга JS-файлов должен быть создан npm-скрипт с именем `eslint:js`.
    * [Перед выполнением коммита](https://pre-commit.com/) должен запускаться [линтинг js-кода для проиндексированных файлов](https://github.com/okonet/lint-staged). Если линтинг завершается с ошибками, коммит не должен быть выполнен.
    * Исходные файлы проекта должны быть расположены в папке с именем `src`.
    * В проекте должна быть настроена система сборки проекта с использованием библиотеки [`Broccoli.js`](https://broccoli.build/):
        * Собранный проект должен быть расположен в папке `dist`. Папка `dist` должна иметь следующую структуру:
            * Файл `index.html` должен находиться в корне папки `dist` (копируется из папки `src`)
            * Папка `assets` должна содержать собранные CSS- и JS-файлы
            * В папку `images` должны копироваться все изображения, необходимые для работы приложения
        * Должна быть настроена [компиляция LESS-кода в CSS](https://github.com/gabrielgrant/broccoli-less-single)
        * Должно быть настроено [использование ES6-модулей](https://github.com/chadhietala/broccoli-rollup), а также [транспиляция ES6- и ES7-кода](https://github.com/rollup/rollup-plugin-babel)
        * Должен быть настроен [импорт кода из npm-пакетов](https://github.com/rollup/rollup-plugin-node-resolve) с [использованием ES6-конструкции `import`](https://github.com/rollup/rollup-plugin-commonjs)
        * Должен быть настроен [линтинг JS-кода](https://github.com/ember-cli/broccoli-lint-eslint), а также [линтинг LESS-файлов](https://github.com/billybonks/broccoli-stylelint)
        * Должна быть настроена [прекомпиляция](https://handlebarsjs.com/precompilation.html) шаблонов [Handlebars](https://handlebarsjs.com/) (см. далее) с использованием [shell-скрипта в процессе сборки](https://github.com/liftM/broccoli-shell). Прекомпиляция должна выполняться в файл `templates.js`, который должен помещаться в папку `dist/assets` в процессе сборки. Прекомпиляция скриптов должна выполняться с использованием команды <br>`npx handlebars ./src/templates --extension=hbs -f ${outputPath}/templates.js`,<br> где `outputPath` - это параметр опции `command` плагина, которая должна использоваться в виде функции.
        * Должна быть настроена [сборка в production- и development-окружениях](https://github.com/joliss/broccoli-env)
        * В development-окружении должен генерироваться map-файл для результирующего JS-файла
        * Должно быть настроено [автоматическое обновление страниц в браузере](https://github.com/stfsy/broccoli-livereload) при изменении исходных файлов проекта в development-окружении
        * Должно быть настроено [сжатие и минификация JS-кода](https://github.com/TrySound/rollup-plugin-terser), а также [CSS-файлов](https://github.com/shinnn/broccoli-clean-css) в production-окружении
        * Должна быть настроена [генерация уникальных имен файлов](https://github.com/rickharrison/broccoli-asset-rev) в production-окружении
        * Код, полученный в результате сборки, должен работать во всех браузерах последних версий
        * Для development- и production-варианта сборки в файл `package.json` должны быть добавлены npm-скрипты с именами `build` и `build:prod` соответственно

2. В репозитории проекта при работе с ветками должен использоваться подход Git Flow.
3. Создать одностраниченое приложение (SPA) без использования JavaScript-фреймворков на основе паттерна MVC (MVP/MVVM):
    * Папка `src` с исходным кодом приложения должна иметь слудующую структуру:
        * Папка `controllers` - классы контроллеров для роутов
        * Папка `models` - классы моделей данных для приложения
        * Папка `styles` - LESS-стили для приложения
        * Папка `templates` - шаблоны роутов с использованием шаблонизатора [Hanlebars](https://handlebarsjs.com/)
        * Папка `utils` - утилиты (вспомогательные функции) для приложения
        * Папка `views` - классы представлений для приложения
        * Файл `store.js` - класс локального хранилища (стора) загруженных с сервера моделей (т.е. кеша моделей)
        * Файл `app-container.js` - контейнер классов ("фабрик") приложения
        * Файл `router.js` - класс роутера приложения
        * Файл `app.js` - точка входа в приложение, Composition Root
        * Файл `index.html` - главная страница, содержащая "бащовую" разметку для всех страниц приложения и ссылки на собранные файлы со стилями и JavaScript-кодом (из папки `dist/assets`).
    * Главная страница приложения (файл `index.html`) должна включать разметку с блоком, в который будет включаться динамическое содержимое каждого роута.
    * На странице `index.html` помимо ссылок на собранные скрипты и стили должна также присутствовать ссылка на Runtime-версию [библиотеки Handlebars из CDN](https://cdnjs.com/libraries/handlebars.js).
    * Должна использоваться верстка с фиксированной шириной содержимого страницы (при желании можно сделать адаптивную верстку, включающую вариант верстки для мобильных устройств). Для описания стилей необходимо использовать препроцессор LESS (в самом простом варианте допускается в LESS-файлах использовать описания стилей на обычном CSS).
    * В приложении должна быть использована модульная система ES6.
    * Все контроллеры, модели, утилиты, представления, стор, контейнер приложения и роутер должны быть описаны в виде ES6-классов и экспортированы с использованием синтаксиса для описания ES6-модулей.
    * В контейнере приложения должны быть зарегистрированы классы для контроллеров, моделей, представлений, стора, роутера и любых других частей приложения (так называемые "фабрики"). Контейнер должен представлять собой хранилище типа "ключ-значение", ключ которого представляет собой строку, состоящую из двух частей: `тип_фабрики:имя_фабрики` (например, `controller:about` или `store:main`). Значение, связанное с ключом, должно представлять собой соответствующий класс. Контейнер приложения должен быть доступен глобально. Регистрация фабрик в контейнер приложения должна выполняться в Composition Root приложения в файле `app.js`. Контейнер приложения должен позволять:
        * Регистрировать новую фабрику. Регистрация может быть реализована как с использованием явной передачи класса в метод регистрации, так и с использованием [динамических импортов](https://v8.dev/features/dynamic-import#dynamic). В случае использвания динамических импортов должна соблюдаться конвенция именования файлов в проекте - соответствующий файл с классом должен лежать в папке с именем типа фабрики и называться так же, как регистрируемая фабрика (например, для фабрики `controller:about` файл должен иметь следующий пусть в проекте: `src/controller/about.js`). Также при использовании динамических импортов процесс сборки приложения должен быть настроен таким образом, чтобы все динамические модули копировались при сборке приложения в папку `dist/assets` с сохранением структуры папок, в которых они находятся. Кроме того, при регистрации новой фабрики должно быть опционально указано, будет ли эта фабрика регистрироваться как синглтон (если явно не указано, фабрика должна регистрироваться как синглтон).
        * Получать инстанцию фабрики. Метод для получения инстанции фабрики должен принимать перечень параметров, который будет передаваться в конструктор соответствующего класса-фабрики. Если соответствующая фабрика была зарегистрирована как синглтон, контейнер приложения должен отдавать одну и ту же инстанцию фабрики (перечень ссылок на инстанции фабрик-синглтонов тажке должен храниться в контейнере), в противном случае должен отдаваться класс, связанный с данной фабрикой.
        * Удалять регистрацию фабрики. При удалении регистрации фабрики, которая была ранее зарегистрирована как синглтон, должна также удаляться ссылка на инстанцию соответствующей фабрики в контейнере, если соответствующая фабрика была ранее инстанциирована.
    * Роутер приложения должен быть реализован на основе концепций, изложенных в [данном посте](https://dev.to/rishavs/making-a-single-page-app-in-ye-good-olde-js-es6-3eng) (можно также посмотреть исходный код соответствующего [демо](https://src-brsetrrnrp.now.sh/)). В конструктор роута должен передаваться идентификатор блока на странице ("блок контента"), в который будет рендериться динамическое содержимое роутов. Роутер должен быть зарегистрирован как синглтон в контейнере приложения. Инициализация роутера и регистрация роутов должны выполняться в файле `app.js`. Роутер должен позволять:
        * Регистрировать новые роуты. Зарегистрированные роуты должны храниться как пара "ключ-значение", в которой ключ представляет собой сегмент пути в URL для соответствующего роута, а значение - имя роута в виде строки (например, `'/':'index'`, `'/about':'about'`, `'/post/:id':'post-edit'`).
        * Предоставлять метод для обработки изменения роута. Данный метод должен управлять жизненным циклом загрузки страницы для соответствующего роута. Для роутов, которые не зарегистрированы в роуте, в "блок контента" должен рендериться шаблон с именем `error404` (рендеринг шаблонов описан в задании далее). Для зарегистрированных роутов должны выполняться следующие действия в указанной последовательности:
            * Из контейнера приложения должна быть получена инстанция контроллера по имени текущего роута (котроллеры должны быть зарегистрированы как синглтоны в контейнере приложения). При отсутствии регистрации соответствующего контроллера в контейнере, может использоваться контроллер "по умолчанию", который также может быть зарегистрирован в контейнере (например, в виде фабрики со специальным именем).
            * Должен вызываться метод `beforeModel` контроллера для возможности выполнения действий перед загрузкой данных, необходимых для данного роута (например, дополнительная инициализация контроллера при изменении роута или переадресация на другой роут).
            * Должен вызывться метод `model` контроллера, в котором подгружаются необходимые данные для отображения в шаблоне роута. Результат загрузки данных должен быть доступен через свойство `model` контроллера.
            * Должен вызываться метод `render` контроллера, который управляет рендерингом шаблона для данного роута.
        * Получать имя текущего роута.
    * Контроллеры должны представлять собой контекст для рендеринга шаблона конкретного роута (т.е. страницы) с поддержкой одностороннего биндинга данных, а также содержать методы для управления жизненным циклом страницы. Каждый контроллер должен предоставлять следующий перечень свойств и методов (методы `beforeModel` и `model` должны быть объявлены как асинхронные, и соответственно возвращать объект `Promise`):
        * Метод `beforeModel` должен содержать действия, которые будт выполняться перед каждым рендерингом роута, с которым связан контроллер. В самом простом случае данный метод может возвращать пустой промис.
        * Метод `model` должен содержать действия для формирования или загрузки необходимых для отображения шаблона роута данных. Результат формирования или загрузки данных должен сохраняться в свойстве `model` контроллера. Свойство `model` контроллера должно обязательно быть сформировано в виде объекта. При подгрузке данных из внешних источников должен использоваться стор приложения.
        * Метод `render` должен управлять рендерингом соответсвующего шаблона для текущего роута в "блок контента" на странице. Данный метод должен последовательно вызывать методы `render` и `afterRender` у соответствующей инстанции представления, котрая должна быть получена из контейнера приложения по имени текущего роута (представления должны быть зарегистрированы как синглтоны в контейнере приложения). В качестве контекста для рендеринга шаблона в метод `render` представления должна передаваться текущая инстанция контроллера (т.е. все свойства контроллера, будут являться "переменными", которые можно использовать в шаблоне при рендеринге).
        * Методы для подписки и публикации событий, которые должны использоваться при обработке событий DOM в связанном предствалении. Данные методы должны быть реализованы на основе [шаблона Publisher-Subscriber](https://monsterlessons.com/project/lessons/publishsubscribe-v-javascript). В каждом контроллере по умолчанию должно быть зарегистрирована подписка на событие изменения свойств котроллера, которая вызывает метод `render` контроллера для повторного рендеринга шаблона роута в случае, если соответствующий роут является активным (чтобы при изменении данных соответствующая информация автоматически отобразилась в шаблоне страницы). Обработчики для событий DOM страницы также должны быть зарегистрированы в виде подписок на соответствующие события (имена таким событиям лучше давать единообразные в разных контроллерах, т.е. принять какоте-то соглашение по их именованию).
        * Каждый контроллер может содержать дополнительные свойства, которые в том числе могут вычисляться на основе других свойств контроллера или его свойства `model` (например, свойства в виде геттеров и сеттеров). Для тех вычислимых свойств, которые реализованы через геттеры и сеттеры, и для которых нужно поддерживать односторонний биндинг данных, необходимо в сеттерах опубликовать событие изменения свойств контроллера (для перерендеринга страницы).
        * Метод `set` должен позволять изменять свойства контроллера, которые не являются вычислимыми и не реализованы соответственно в виде геттеров и сеттеров, для поддержки одностороннего биндинга данных. Данный метод после изменения переданного в качестве параметра свойства должен опубликовать событие изменения свойств контроллера (для перерендеринга страницы). Имя свойства, передаваемого в данный метод, может содержать имена вложенных свойств (например `model.address`).
    * Представления должны отвечать непосредственно за рендеринг шаблона роута в "блок контента" и регистрацию обработчиков событий DOM для элементов на странице роута. Каждое представление дожно предоставлять следующий перечень методов:
        * Метод `render` должен выполнять рендеринг шаблона роута в "блок контента" на основе [предварительно скомпилированных](https://handlebarsjs.com/precompilation.html) Handlebars-шаблонов (соответствующие методы получения разметки доступны через свойство `Handlebars.templates`). Имя необходимого шаблона должно быть получено по имени текущего роута. Методу для получения разметки должен передаваться контекст, который передается в виде параметра в метод `render` и является инстанцией связанного контроллера.
        * Метод `afterRender` должен регистрировать обработчики событий DOM для элементов на странице (клики по кнопкам и пр.). Обработчики событий DOM должны публиковать соответствующие события в контроллер. Связанный Контроллер должен передаваться в данный метод через параметр.
    * Шаблоны роутов (страниц) должны представлять собой файлы с расширением `.hbs` (шаблоны Handlebars). Имена соответствующих шаблонов должны совпадать с именами роутов, которые будут регистрироваться через роутер. Повторяющаяся разметка в роутах может либо дублироваться в шаблонах, либо быть вынесена в виде [переиспользуемых шаблонов](https://handlebarsjs.com/partials.html) на усмотрение обучающегося (переиспользуемые шаблоны необходимо вынести в отдельную папку внутри папки `src/templates` для возможности конфигурирования прекомпиляции шаблонов при сборке приложения).
    * Модели приложения должны использоваться как бизнес-объекты в приложении. Минимально каждая модель должна содержать перечень необходимых свойств, которые инициализируются через конструктор модели. Опционально данные модели могут содержать метод `save`, в котором может быть определена логика сохранения модели во "внешнем источнике" данных (не обязательно в рамках реализации данного задания; реализация данного метода, скорее всего, потребует также реализацию хранения различных состояний модели), а также дополнительные методы в зависимости от потребностей бизнес-логики приложения. Инстанциирование моделей и их получение должно осуществляться через стор приложения. Каждая модель должна обязатльно содержать свойство `id`, которое должно являтсья уникальным идентификатором для модели соответствующего типа. Связь между моделями должна поддерживаться на основании ссылок на другие модели через их идентификаторы. Модели должн быть зарегистрированы в контейнере приложения, но не являться при этом синглтонами.
    * Стор должен представлять собой класс для хранения кеша используемых моделей в приложении. Модели при этом могут быть получены как из "внешних источников" данных, так и сгенерированы на уровне приложения (для того, чтобы не было необходимости постоянно подгружать одни и те же данные из "внешних источников"). Стор должен предоставлять следующий перечень методов:
        * Метод `createRecord` должен позволять инстанциировать модель и сохранять её в кеше. В качестве параметров он должен принимать тип инстанциируемой модели (имя модели), а также объект с перечнем свойств для инициализации инстанции модели. Получение класса модели должно выполняться через контейнер приложения. Кеш моделей должен представлять собой ассоциативный массив или хеш-таблицу в виде набора пар "ключ-значение", где ключ - это имя модели, а значение - это массив моделей соответствующего типа.
        * Метод `deleteRecord` должен позволять удалять модель из кеша. В качестве параметров он должен принимать тип инстанциируемой модели (имя модели), а также идентификатор инстанции модели (значение свойства `id`).
        * Метод `peekRecord` должен позволять получить инстанцию модели из кеша без предварительной загрузки из "внешнего источника" данных. В качестве параметров он должен принимать тип модели (имя модели), а также идентификатор инстанции модели (значение свойства `id`).
        * Метод `peekAll` должен позволять получить все инстанции моделей из кеша без предварительной загрузки из "внешнего источника" данных. В качестве параметров он должен принимать тип модели (имя модели).
        * Метод `filter` должен позволять получить отфильтрованный перечень моделей из кеша без предварительной загрузки из "внешнего источника" данных. В качестве параметров он должен принимать тип модели (имя модели), а также callback, который будет использоваться для фильтрации элементов соответствующего массива инстанций моделей.
        * Метод `query`, который будет использоваться для получения данных из "внешнего источника" данных. В качестве параметров он должен принимать тип модели (имя модели), опции в виде объекта для выполнения запроса во "внешний" источник данных (например, URL, значения заголовков запроса и т.п.). Данный метод должен возвращать промис, по успешному завершению которого загруженные из "внешнего источника" данных должны автоматически инстанциироваться и сохраниться в кеше стора. Если модель соответствующего типа с определенным `id` уже существовала в кеше, она должна быть заменена на загруженный из "внешнего источника" данных вариант. Опционально на усмотрение обучающегося функция также может принимать третий параметр, в который будет передаваться реализация метода для загрузки данных из "внешнего источника" (чтобы для разных можделей или в зависимости от разных условий модели могли загружатсья из разных "внешних источников").
    * Утилиты должны представлять собой "чистые функции", которые используются для вспомогательных целей в приложении (например, функция для парсинга текущего URL и пр.).
    * Опционально на усмотрение обучающегося в приложении также может быть реализовано использование [хелперов для шаблонизатора Handlebars](https://handlebarsjs.com/#helpers) и компонентов приложения. Хелперы должны размещаться в папке `src/helpers`. Компоненты приложения должны представлять собой отдельные визуальные блоки приложения со своим контекстом выполнения и состоянием. Для каждого компонента должен создаваться соответствующий класс в папке `src/components`, а также блочный хелпер в папке `src/helpers/components`. На уровне шаблона роута компонент должен встраиваться во внешний по отношению к нему шаблон (например, шаблон роута) при помощи [блочных хелперов Handlebars](https://handlebarsjs.com/block_helpers.html). Класс компонента должен быть реализован по аналогии с контроллером (за исключением возможности самостоятельного рендеринга), при этом инстанция класса компонента должна присутствовать в виде свойства в объемлющем контексте (например, в контроллере или объемлющем компоненте). Компонент также должен позволять подписываться на события DOM элементов, соответствующих его шаблону, а также публиковать соответствующие события во внешний по отношению к нему контекст (например, в контроллер или объемлющий компонент) в случае, если в данном компоненте не было зарегистрировано подписок на событие с нужным именем. Каждый компонент также должен быть зарегистрирован в контейнере приложения в виде синглтона.
4. Исходный код проекта должен быть написан с использованием возможностей ES6 (классы, стрелочные функции, деструктуризация и пр.). Каждый класс должен быть вынесен в отдельный ES6-модуль, который физически должен располагаться в соответствующей папке (`src/models`, `src/controllers` и т.п.). При необходимости в приложении могут быть предусмотрены родительнские классы для разных частей приложения (контроллеров, представлений и т.п.).
5. Подключение сторонних библиотек в виде npm-модулей допускается.
6. Проект должен содержать модульные, интеграционные и e2e-тесты, которые написаны с использованием тестового фреймворка [QUnit](https://qunitjs.com/). Тесты должны размещаться в папке `tests` приложения и должна содержать вложенные папки `unit`, `integration` и `e2e` для соответствующих видов тестов. Папки `unit`, `integration` при этом должны внутри дублировать структуру папки `src`, а имена файлов тестов должны дублировать имена тестируемых модулей приложения с постфиксом `-test` (например, модульный тест для контроллера `about` должен иметь следующий путь: `tests/unit/controllers/about-test.js`). Для объектов-заглушек, а также мока ajax-запросов может быть использована библиотека [Sinon.js](https://sinonjs.org/), а также глобально создаваться контейнер приложения для регистрации заглушек в виде необходимых фабрик. Для e2e-тестов можно использовать такие инструменты как [Puppeteer](https://github.com/GoogleChrome/puppeteer), [Selenium](https://selenium.dev/), [Cypress](https://www.cypress.io/) или любые другие на усмотрение обучающегося. Кроме того, на усмотрение обучающегося могут использоваться инструменты для анализа покрытия кода тестами, например [Istanbul](https://istanbul.js.org/). Перечень тестов для приложения не обязательно должен полностью покрывать все модули приложения, достаточно будет выполнить по несколько примеров тестов для каждого типа тестов.
7. Исходный код проекта должен быть задокументирован с использованием [JSDoc](https://jsdoc.app/).
8. Проект должен быть выложен на GitHub в личный репозиторий. Для проекта на GitHub должны быть настроены непрерывная интеграция и непрерывное развертывание (CI/CD) с использованием [Travis CI](https://travis-ci.com/). Серверный билд должен включать в себя следующие шаги:
    * Запуск тестов проекта
    * Запуск сборки проекта, включающей в себя линтинг JS-файлов и LESS-файлов. Для коммитов в ветку master должен запускаться production-вариант сборки.
    * Развертывание собранной версии приложения на GitHub Pages для веток master и develop. Для ветки master развертывание должно осуществляться только при успешном прохождении всех тестов.
    * Сборка автодокументации и последующее её развертывание на GitHub Pages для веток master и develop. Для ветки master развертывание должно осуществляться только при успешном прохождении всех тестов.

**Вспомогательные ресурсы:**

1. [Как писать тестируемый код](https://habr.com/ru/company/mailru/blog/267277/).
2. [Рекомендации по написанию чистого кода](https://habr.com/ru/post/424051/)
3. [Создание SPA приложения на ванильном JavaScript](https://dev.to/rishavs/making-a-single-page-app-in-ye-good-olde-js-es6-3eng)
4. [Простой роутер на JavaScript](https://myrusakov.ru/js-simple-router.html)

## Вы можете

- [Перейти к чек-листу проверки заданий](tds_module3-check-list.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
- <i class="fa fa-arrow-left" aria-hidden="true"></i> [Перейти на страницу с дополнительными материалами модуля](tds_module3-appendix.html)
