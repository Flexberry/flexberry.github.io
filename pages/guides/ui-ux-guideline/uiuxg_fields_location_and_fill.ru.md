---
title: Расположение и заполнение полей
keywords: UI, UX
sidebar: ui-ux-guideline_sidebar
toc: false
permalink: ru/uiuxg_fields_location_and_fill.html
lang: ru
summary: Основные gравила заполнения и расположения полей.
---

## Компоненты форм ввода данных

Типичная форма содержит в себе следующие пять компонентов:

* **Структура.** Включает в себя порядок полей, внешний вид формы на странице и логические связи между несколькими полями.
* **Поле ввода.** Включает в себя текстовые поля, поля пароля, флажки, радиокнопки и любые другие поля, предназначенные для ввода.
* **Строки меток.** Обычно поясняют пользователю что означает поле ввода.
* **Кнопки действий.** Производят определенное действие (например, отправка формы) (элемент [Кнопки и тулбар -404-](link_here)).
* **Обратная связь.** Необходимо дать понять, что действие находится в работе или завершилось. Результат действия может быть положительным (успешная отправка) или отрицательным (ошибка) (раздел [Диалоги с системой](uiuxg_dialog_with_a_system.ru.md)).

Формы также могут содержать следующие компоненты:

* **Подсказки.** Объяснение того, как заполнять форму (раздел [Диалоги с системой](uiuxg_dialog_with_a_system.ru.md)).
* **Валидация.** Проверка данных гарантирует, что введенные данные верны.

## Структура формы

Форма – это способ общения с пользователем. Как и любая другая беседа, она должна состоять из логической связи между двумя сторонами: пользователь и приложение.

## Логическая последовательность формы

Выстраивайте логику формы так, чтобы она была понятна с точки зрения пользователя.

## Группировка связанной информации

Группируйте связанную информацию в отдельные блоки и наборы. Группировка связанных полей поможет пользователям разобраться в информации, которую они должны предоставить. Посмотрите как это работает на примере формы контактной информации ниже *(тут должен быть [пример -404-](link_here) из макета по группировке в форме)*:

![Группировка в форме](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/1.png)

(Image: Nielsen Norman Group)

## Вкладки, Сворачиваемые разделы. *(когда будет макет)*

## Один столбец vs. Несколько столбцов

Одной из проблем с организацией полей формы в нескольких столбцах является непоследовательная интерпретация пользователями этих полей. Если форма имеет горизонтально расположенные поля, то пользователь должен следовать по Z-образной траектории вдоль формы, что замедляет скорость восприятия и превращает путь к завершению в мешанину. Но если форма составлена в один столбец, то путь к завершению один — вниз страницы.

![Несколько столбцов vs. один столбец](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/2.png)

Слева вы видите один из возможных способов интерпретации форм, составленных в несколько столбцов. И противопоставленный ему способ построения формы в один столбец.

![Один столбец](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/3.png)

### Поля ввода

Поля ввода — это элемент формы, который позволяет пользователям заполнить ее. Существуют различные типы полей для нужной вам информации: текстовые поля, поля пароля, раскрывающиеся списки, флажки, радиокнопки, сборщики информации и др.

### Количество полей

Ключевое правило в дизайне формы гласит, что чем она короче, тем лучше. Таким образом, ваша задача состоит в том, чтобы свести к минимуму количество полей настолько, насколько это возможно. Это сделает ваши формы менее загруженными, особенно когда вы просите ввести много информации. Однако не переусердствуйте, потому что никто не любит формы из трех полей, которые дополнены 30-ю вопросами. В данный момент нормой считается отображение 5–7 полей.

### Обязательное vs. Необязательное

Старайтесь избегать необязательных полей в формах. Но если вы их используете, то, по крайней мере, четко различайте те поля, которые могут быть пустыми.

![Обязательное](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/4.png)

![Необязательное](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/5.png)

### Маски ввода информации

Маски ввода информации — это визуальное улучшение, которое помогает отформатировать введенную информацию автоматически. Они появляются тогда, когда пользователь фокусируется на определенном поле ввода, и это форматирование помогает пользователю сосредоточиться на необходимых сведениях и легче подметить возможные ошибки. В приведенном ниже примере круглые скобки, пробелы и тире автоматически применяются при вводе телефона, номера кредитных карт и других данных с числами. Этот способ экономит время и помогает избежать ошибок при вводе числовых значений.

![Маска ввода](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/6.gif)

### Desktop-Only: дружим форму с клавиатурой

Пользователи должны иметь возможность сосредоточиться на форме и редактировать каждое поле используя только клавиатуру. Опытные пользователи, которые интенсивно используют клавиатуру, должны иметь возможность легко переходить между полями и редактировать их, не отрывая пальцы от клавиатуры. Вы можете найти детальные требования для взаимодействия с клавиатурой в [рекомендации консорциума W3C](https://www.w3.org/TR/WCAG20-TECHS/G202.html).

![Работа с клавиатурой](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/7.png)

### Desktop-Only: Автофокус на поле ввода

Автовыбор поля дает пользователю представление о том, с чего следует начать чтобы быстро и правильно заполнить поля формы. Данная техника дает визуальный сигнал о каком-либо изменении, будь то изменение цвета, выцветание в окне, мигающие стрелки или другой визуальный отклик. При создании формы аналитик должен обязательно продумать первое поле для автофокуса и указать его в постановке задачи.

![Автофокус в форме](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/8.png)

### Mobile-Only: Адаптивная клавиатура по запросу

При проектировании интерфейсов для смартфонов нужно всегда активировать подходящую клавиатуру для всех полей ввода. Если нужно ввести дату, то вызывается date-picker, если это номер, то числовую клавиатуру и т. д.

Код ниже добавляет к полю ввода нумерическую клавиатуру как на Android, так и на iOS. Подробную информацию можно узнать здесь: [Android](https://developer.android.com/training/keyboard-input/style "Android Developers - Specify the input method type"), [iOS](https://developer.apple.com/library/archive/documentation/StringsTextFonts/Conceptual/TextAndWebiPhoneOS/KeyboardManagement/KeyboardManagement.html "Text Programming Guide for iOS"), [StackOverflow](https://stackoverflow.com/questions/6178556/phone-numeric-keyboard-for-text-input "Phone: numeric keyboard for text input").

~~~~html
<input type="number" pattern="[0-9]*" inputmode="numeric">
~~~~

![Адаптивная клавиатура](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/9.png)

(Image: Google)

### Автозаполнение

Автозаполнение поможет предотвратить опечатки и позволит улучшить опыт пользователей. Это также относится к вводу с клавиатуры. Например, заполнение полей с адресом, является наиболее проблемной частью любой регистрационной формы. Такой инструмент как автозаполнение адреса (который использует геолокацию, чтобы обеспечить точное определение местоположения пользователя) позволяет указать свой адрес с меньшим числом нажатий клавиш чем обычные поля ввода.

![Автозаполнение](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/11.gif)

### Метки

Четко прописанные метки являются одним из основных способов сделать интерфейс более доступным. Правильные метки сообщают пользователю о цели поля ввода, сохраняют свою полезность, когда акцент делается на самом поле и остаются видимыми даже после того, как все поля были заполнены. Метки в нашей ДС располагаются прямо внутри поля ввода или строго над ним.

![Состояния полей](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/12.png)

![Поля](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/13.png)

### Количество слов в метках

> Метки не могут заменить текст. Необходимо употреблять емкие, короткие и понятные надписи (слово или два), чтобы пользователи могли быстро прочитать форму.

![Поля не заполнены](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/14.png)

### Заглавные буквы в начале предложения vs. Заглавные Буквы в Начале Слова

В большинстве цифровых продуктов сегодня существует два способа использовать заглавные буквы:

* В начале слова: капитализация каждого слова, кроме предлогов и частиц — «Это Заглавные Буквы».
* В начале предложения: капитализация первого слова в предложении — «Это заглавные буквы».

Второй вариант используется для меток и имеет одно преимущество над первым: его немного проще читать. Если разница для коротких надписей незначительна, то для длинных она уже существенна. Теперь Вы Знаете Насколько Трудно Читать Длинный Текст в Заглавных Буквах.

![Поля Имя - Почта](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/15.png)

### Встроенные подсказки (иногда заменяют метку)

Набор подсказок в качестве заполнителя в поле ввода исчезает, как только пользователь фокусируется на этом поле. Хорошее решение для проблемы встроенных подсказок – это плавающий ярлык. Встроенная подсказка будет отображаться по умолчанию, но как только поле ввода будет активировано, встроенная подсказка исчезает.

![Подсказка в поле](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/16.png)

![Подсказка в поле №2](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/17.png)

![Подсказка в поле №3](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/17_2.png)

### Кнопки действий

Нажатие на такую кнопку вызывает какие-то действия, например, отправка формы.

![Call to action](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/18.png)

### Первичные действия vs. Вторичные действия

Отсутствие визуального различия между первичными и вторичными действиями могут легко привести к проблемам. Увеличение визуальной выразительности вторичных действий минимизирует риск ошибки и усиливает ощущение движения к успешному результату.

![Адаптивная клавиатура](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/3.png)

### Расположение кнопки

Сложные формы, как правило, имеют кнопку «Назад». Если такая кнопка расположена прямо над полем ввода (как на первом скриншоте ниже), то пользователь может случайно нажать на нее. Все дело в том, что кнопка «Назад» — это вторичное действие, которое должно быть менее доступным (второй скриншот показывает правильное расположение вторичных кнопок).

![Расположение кнопок](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/19.png)

### Правило наименования

Избегайте общих слов, таких как **Отправить** для действия, потому что они создают впечатление, что форма является шаблонной. Вместо этого укажите то действие, которое будет выполняться при нажатии на кнопку, например, **Создать мой аккаунт** или **Подписаться на еженедельные предложения**.

![Наименование кнопок](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/20.png)

### Кнопки с несколькими действиями

Избегайте кнопок с несколькими действиями, так как они могут отвлекать пользователей от цели заполнения формы.

[Добавить]: # (Добавить пример из Figma)

### Внешний вид

Убедитесь, что кнопки выглядят как кнопки: сделайте индикацию нажатия, если можно.

![Кнопки](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/21.gif)

(Image: Vadim Gromov)
Тень под кнопкой указывает на то, что ее можно нажать.

### Микровзаимодействия

Спроектируйте кнопку **Отправить** таким образом, чтобы она ясно показывала, что форма обрабатывается после действий пользователя. Это обеспечивает обратную связь с пользователем и предотвращает двойное нажатие.

![Пример микровзаимодействия](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/22.gif)

(Image: Michaël Villar)

### Проверка

Ошибки при проверке формы неизбежны и являются естественной частью ввода данных (потому что пользователи склонны делать ошибки). Да, ошибки должны быть сведены к минимуму, но они никогда не будут устранены окончательно. Итак, самым важным вопросом является то, как сделать проверку легкой для пользователя, чтобы они смогли исправить ошибки?

### Встроенная проверка

Пользователям не нравится, что приходится заполнять всю форму и в самом конце узнавать об ошибках. Особенно неприятно завершает длинную форму тот факт, что после нажатия кнопки «Отправить», вас награждают несколькими сообщениями об ошибках. Это еще больше раздражает, когда вообще непонятно где были допущены эти ошибки.

![Проверка заполнения](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/23.png)

(Image: Stack Exchange)

Проверка должна сообщать пользователям о правильности введенного текста, как только они его ввели. Встроенная проверка в режиме реального времени сразу информирует пользователя о правильности его данных. Такой подход позволяет им исправлять ошибки быстрее, не дожидаясь, пока они нажмут на кнопку «Отправить».

Однако, избегайте проверки на каждое нажатие клавиши, потому что в большинстве случаев, вы просто не можете быть уверены, что пользователь закончил набирать ответ. Иногда форма должна проверять данные в то время, когда пользователь переходит к новому полю.

![Проверка взаимодействия](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/24.gif)

(Image: Medium)
Формы Google указывают, что адрес электронной почты, который вы вводите, недействителен, прежде чем вы закончите печатать.

С другой стороны, формы проверки после ввода данных не сообщают пользователю, что он совершил ошибку с достаточной оперативностью.

![Пример взаимодействия](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/25.gif)

(Image: Medium)
Проверка в магазине Apple производится после ввода данных.

Михаэль Коньевич, в своей статье «Проверка в форме: Проектирование взаимодействия», рассматривает различные стратегии проверки и предлагает гибридную стратегию, чтобы удовлетворить обе стороны:

* Награждать рано, наказывать поздно;
* Если пользователь вводит данные в поле, которое было в допустимом состоянии (введенные данные были действительны), то проверка производится после ввода данных;
* Если пользователь вводит данные в поле, которое было в недопустимом состоянии (то есть, ранее введенные данные были недействительны), то проверка производится во время ввода данных.

![Проверка поля](../../../images/pages/guides/ui-ux-guideline/uiuxg_fields_location_and_fill/26.gif)

(Image: Medium)