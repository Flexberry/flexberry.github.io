---
title: Настройка кастомной валидации
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, валдация, validation, ember-cp-validations
toc: true
permalink: ru/gpg_custom-validation.html
lang: ru
---

**Валидация** на формах редактирования в базовом варианте автоматически реализуется при генерации приложения на основании данных диаграммы классов. Так, например, выглядит валидация поля "Цена за ед." на форме редактирования "Товара":

![Валидация на число в поле "Цена за ед." модели "Товар"](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-1.png)

Настройка валидации в аддоне ember-flexberry версии **3.x.x** осуществляется при помощи аддона **[ember-cp-validations](http://offirgolan.github.io/ember-cp-validations/docs/modules/Usage.html)** (для версий 2.x.x используют аддон [ember-validations](https://github.com/DavyJonesLocker/ember-validations)). Он не только предоставляет определенное количество встроенных валидаторов, но также позволяет писать кастомные.

В прошлом разделе "[Заполнение первичных данных приложения](gpg_filling-application-primary-data.html)" мы говорили о том, что на форме "Заказ" существует поле, которое при заполнении должно валидироваться особым образом:

![Логика валидации](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-2.png)

Так, при выборе товара и указании количества этого товара необходимо проверять, есть ли в наличии на всех складах, вместе взятых, необходимое количество указанного товара. Для этого напишем свой **собственный валидатор**.

Прежде всего, откроем новый терминал (`Терминал` → `Новый терминал`) и выполним команду 

`ember generate validator check-product-amount`

![Логи создания валидатора](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-3.png)

Откроем сгенерированный валидатор:

*`app → validators → check-product-amount.js`*

![Стартовый функционал нового валидатора](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-4.png)

Мы видим "заготовку" для кастомного валидатора. Наш код для валидации мы будем размещать в методе **validate** (выделенная зеленым строка 5). По умолчанию данный метод возвращает значение true: это значит, что если мы прямо сейчас подключим этот валидатор, то он будет для любого проверяемого значения сообщать, что валидация прошла успешно (не будет "выкидывать" ошибок).

Для того, чтобы реализовать требуемый вариант валидации нам, прежде всего, необходимо вычитать все склады. Для этого прежде всего в функции validate получим **сервис store** ([специальный сервис Ember Data](https://guides.emberjs.com/v3.1.0/models/#toc_the-store-and-a-single-source-of-truth)):

{% highlight javascript%}
{% raw %}
import { inject } from '@ember/service';
{% endraw %}
{% endhighlight %}

{% highlight javascript%}
{% raw %}
const CheckProductAmount = BaseValidator.extend({
  store: inject(),

  validate(/*value, options, model, attribute*/) {
    let store = this.get('store');
  }
});
{% endraw %}
{% endhighlight %}

Подробнее о функции inject можно прочитать [тут](https://guides.emberjs.com/v3.1.0/applications/services/).

Далее нам требуется вычитать актуальные сведения о складах и хранящихся на них товарах из БД. Для этого воспользуемся [клиентским языком запросов](https://flexberry.github.io/ru/efd_query-language.html) из **аддона ember-flexberry-data** (устанавливается вместе с аддоном ember-flexberry):

{% highlight javascript%}
{% raw %}
validate(/*value, options, model, attribute*/) {
    let store = this.get('store');

    let builder = new Builder(store, 'i-i-s-shop-storehouse');
    builder.selectByProjection('StorehouseE');

    return store.query('i-i-s-shop-storehouse', builder.build())
        .then(function(storehouses) {

    });
}
{% endraw %}
{% endhighlight %}

{% include important.html content="Запрос из store методом query является асинхронным: если не указать оператор return, то по умолчанию из валидатора будет возвращаться значение false. Чтобы вернуть некое значение из асинхронного запроса внутри валидатора (объяснить ему, что необходимо дождаться результата запроса), нужно:<br>
1. поставить return перед самим запросом;<br>
2. что-то возвращать из функции внутри самого запроса." %}

В переменной storehouses у нас теперь хранятся данные обо всех складах. Нам нужно просмотреть товары у каждого склада сохранить их в общей переменной, в которой хранились бы данные о наличии товаров и их общем количестве. Создадим **объект products** и **переберем все склады** и **все товары** на каждом складе, сохраняя суммарное количество товаров в созданном объекте:

{% highlight javascript%}
{% raw %}
return store.query('i-i-s-shop-storehouse', builder.build())
    .then(function(storehouses) {
        let products = {};
        storehouses.forEach(function (storehouse) { // перебираем склады
          let storeProducts = storehouse.get('storeProduct');
          storeProducts.forEach(function (storeProduct) { // перебиаем товары
            let productId = storeProduct.get('product.id');
            let amount = storeProduct.get('amount');

            if (products.hasOwnProperty(productId)) { // проверяем, есть ли уже данные о товаре
              products[productId] += amount;
            } else {
              products[productId] = amount;
            }
          })
        });
    });
{% endraw %}
{% endhighlight %}

{% include important.html content="Значения всех свойств моделей, как и других Ember-объектов, мы получаем при помощи конструкции object.get(‘property’). Если вам нужно получить какое-то свойство по цепочке (например, id у property), то следует перечислять последовательность вызовов через &#34;.&#34; (например, object.get(‘property.id’))." %}

Теперь в объекте products находятся данные о количестве товаров в формате 
"&#60;id товара>: &#60;количество>". 

Далее нам нужно получить само **значение валидируемого поля** (валидатор срабатывает при каждом изменении значения поля), то есть id товара из **строки заказа** и **сравнить** их с данными, хранящимися в объекте products. Значение валидируемого поля передается в параметре **value** метода validate, но id товара нужно будет получить из связанной с валидируемым полем **модели**. Для этого **раскомментируем** часть параметров у функции validate и **получим id** товара у модели:

![Раскомментирование параметров валидатора](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-8.png)

{% include important.html content="Хотя мы не используем параметр options, исключить или изменить порядок параметров мы не можем: для параметров любой JavaScript-функции важен порядок их объявления." %}

{% highlight javascript%}
{% raw %}
return store.query('i-i-s-shop-storehouse', builder.build())
    .then(function(storehouses) {
        let products = {};
        storehouses.forEach(function (storehouse) { // перебираем склады
          let storeProducts = storehouse.get('storeProduct');
          storeProducts.forEach(function (storeProduct) { // перебиаем товары
            let productId = storeProduct.get('product.id');
            let amount = storeProduct.get('amount');

            if (products.hasOwnProperty(productId)) { // проверяем, есть ли уже данные о товаре
              products[productId] += amount;
            } else {
              products[productId] = amount;
            }
          })
        });

        let product = model.get('product');
        if (product) {
          let productId = product.get('id');
        } else {
          return true;
        }
    });
{% endraw %}
{% endhighlight %}

Если товар в строке заказа не выбран,то валидацию мы выполнять не будем. По умолчанию тогда валидатор будет сообщать, что все в порядке (ошибка не будет выводиться).

Если же товар в строке заказа выбран, то **получим** соответствующее **суммарное количество** из объекта products и **сравним** его с валидируемым значением:

{% highlight javascript%}
{% raw %}
let product = model.get('product');
if (product) {
    let productId = product.get('id');

    let maxAmount = products[productId] ? products[productId] : 0;
    if (maxAmount >= Number(value)) {
    return true;
    } else {
    }
} else {
    return true;
}
{% endraw %}
{% endhighlight %}

{% include note.html content="Значение value требуется приводить к числовому типу, так как оно с формы приходит в виде строки." %}

Остается сформировать **сообщение**, которое валидатор вернет в случае, если невозможно на складах нет требуемого количества товара для формирования заказа:

{% highlight javascript%}
{% raw %}
if (maxAmount >= Number(value)) {
return true;
} else {
let measure = product.get('measure');
let message = maxAmount > 0 ?
                `В наличии ${maxAmount} ${measure}`
                : `Товар отсутствует на складе`;
return message;
}
{% endraw %}
{% endhighlight %}

Валидатор создан. Подключим его к нужной модели и проверим работоспособность валидатора:

*`app → mixins/regenerated → models → i-i-s-shop-order-item.js`*

{% highlight javascript%}
{% raw %}
export let ValidationRules = {
  amount: {
    descriptionKey: 'models.i-i-s-shop-order-item.validations.amount.__caption__',
    validators: [
      validator('ds-error'),
      validator('number', { allowString: true, allowBlank: true, integer: true }),
      validator('check-product-amount', {
        showSuggestions: false,
       }),
    ],
  },

  ...

}
{% endraw %}
{% endhighlight %}

Из раздела ("[Заполнение первичных данных приложения](gpg_filling-application-primary-data.html)") мы помним, что на складах присутствуют следующие товары:

1. Монитор Samsung C24F390FHI - 4 + 1 = 5 (шт.)
2. Системный блок Acer Aspire XC-330 DT.BD2ER.001 - 2 + 0 = 2 (шт.)
3. Монитор игровой MSI Optix MAG241CP - 1 + 2 = 3 (шт.)

Так как нам необходимо проверить и ситуацию, когда товара на складах нет совсем, уберем позицию "Системный блок игровой Lenovo Legion T530-28ICB MT (90JL00M4RS)" со 2 склада. После этого перейдем к списку "Заказов" и создадим новый заказ. Сохранять мы его не будем, но валидацию протестируем:

1. Системный блок игровой Lenovo Legion T530-28ICB MT (90JL00M4RS) - 1 (шт.)
2. Монитор Samsung C24F390FHI - 5 (шт.)
3. Монитор Samsung C24F390FHI - 6 (шт.)

![Проверка работы валидатора](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-13.png)

Как мы видим, валидация работает корректно.

{% include note.html content="По умолчанию настройки линтинга кода запрещают использование конструкции console.log(), которая может использоваться для отладки работы валидатора." %}

Для того, чтобы пропала эта ошибка, нужно прописать в **`.eslintrc.js`** правило:

![Настройка eslint](/images/pages/guides/flexberry-ember/5-4-custom-validation/5-4-14.png)

Для применения этого правила следует перезапустить приложение (**Ctrl+C**, и затем выполнить команду **ember s** в терминале). Чтобы можно было отлаживать валидатор через логи, его следует подключить к нужной модели.

## Итог

Кастомная валидация используется в тех случаях, когда контекста проекции той или иной формы не хватает для валидирования полей. В остальных случаях рекомендуется обходиться встроенными средствами [ember-cp-validations](https://rawgit.com/offirgolan/ember-cp-validations/c4123c983e54f24dd790ffa1bad66cfdf2f47ec6/docs/modules/Common%20Options.html).

Например, в нашем конкретном случае нам было необходимо просмотреть содержимое всех складов через builder: сам заказ не располагает данными о складах. Добавлять их к модели только ради валдиации будет избыточно и потребует отдельных связей, поэтому в данном случае кастомная валидация уместна.

В дальнейшем мы будем работать также и с настройкой встроенных валидаторов.

## Самостоятельная работа

Вы можете выполнить следующие доработки в приложении самостоятельно для более полной реализации функционала приложения:
1. доработать валидацию числовых полей: проверять, что для определенных полей значения могут быть положительные (строго больше 0) или неотрицательные (больше или равно 0).


## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Вычислимые свойства и проекции моделей](gpg_computable-properties-and-projections-of-models.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Автозаполнение элементов формы](gpg_autofill-form-elements.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>