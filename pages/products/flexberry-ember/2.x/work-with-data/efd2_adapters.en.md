---
title: Adapters in ember-app flexberry
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_adapters.html
folder: products/ember-flexberry-data/models-and-projections/
lang: en
autotranslated: true
hash: bc6d323ce6d1a0e198d63407f7f7867f1ff8afddac9abafc6c9778a5b48ecb28
summary: the Adapters that determine how a call to the server to retrieve data in ember-flexberry application.
---

## Description

A great description is in the [documentation on the official website](https://guides.emberjs.com/v2.4.0/models/customizing-adapters/).

## Configuring the adapters in the app

When generating the application `ember-flexberry` file is created `app/adapters/application.js` with the following contents:
```javascript
import { Projection, Adapter } from 'ember-flexberry-data';
import config from '../config/environment';

export default Adapter.Odata.extend(Projection.AdapterMixin, {
  host: config.APP.backendUrls.api,
});
```

This is the adapter for the application, which specifies the address `OData` service.

If the model has not defined your own adapter, use the adapter application.
To determine the adapter for a specific model, you must create an adapter with the same name as the model, it can be done with the command:

```cmd
ember g adapter my-model
```

The result of this command will create a file `app/adapters/my-model.js` with the following contents:

```javascript
import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
});
```

In order to change the address `OData` service for this model, it is necessary to override the property `host`, specifying the new address `OData` service. For example, if the configuration of the second address `OData` service specified in the property `api2`, you get this:

```javascript
import ApplicationAdapter from './application';
import config from '../config/environment';

export default ApplicationAdapter.extend({
  host: config.APP.backendUrls.api2,
});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}