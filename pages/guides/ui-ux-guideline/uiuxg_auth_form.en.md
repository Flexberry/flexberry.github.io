---
title: authorization Form and registration
keywords: form, login, registration
sidebar: ui-ux-guideline_sidebar
toc: true
permalink: en/uiuxg_auth_form.html
lang: en
autotranslated: true
hash: 95f48064010536191a49844d5019fafc1648d2b382b640a6db91cf8547813e32
summary: Form for the authorization and registration of a new user.
---

## Authorization form

Consists of two required fields – **username** and **Password**. If one of the fields is not available, then the button **Enter** will be inactive. Authorization is also available through RSA and ESIA.

![The fields are blank - Login](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/6.png)

If the user is not in the database or attempts to login with invalid data, an error occurs validation.

![Error in the field - Log](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/7.png)

This form must have a reference to the new user registration.

![To log in](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/8.png)

The authorization form needs to be interactive – if the correctness of sending the data should receive the notice **the user Authorization is successful**. If a technical error should appear notice **user authentication is not performed**.

![Error sending the form to Login to your account](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/9.png)

## Registration form

The link for new user registration should be in the authorization form.

![Registration form](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/10.png)

The form consists of two required fields – **username** and **Password**.

![Log in to personal Cabinet - the Second example](/images/pages/guides/ui-ux-guideline/uiuxg_auth_form/8.png)

The registration form needs to be interactive – if the correctness of sending the data to show a notification **user Registration is successful**, then automatic transition to the login form. If a technical error should appear notice **user authentication is not performed, and re-registration of the user: **User with such data already registered**.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}