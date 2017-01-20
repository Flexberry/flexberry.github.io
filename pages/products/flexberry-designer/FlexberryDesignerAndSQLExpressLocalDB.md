---
title: Запуск Flexberry Tool под SQL Server Express LocalDB
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, How to (page), Public, БД
toc: true
permalink: ru/fd_Flexberry-tool-and-s-q-l-express-local-d-b.html
folder: products/flexberry-designer/
lang: ru
---

# Работа Flexberry Tool c SQL Server 2014 Express LocalDB
Об SQL Server 2014 Express LocalDB можно почитать в MSDN (лучше ознакомиться с двумя версиями, поскольку 28.10.2014 там присутствуют важные содержательные различия):
* <http://msdn.microsoft.com/ru-ru/library/hh510202.aspx>
* <http://msdn.microsoft.com/en-us/library/hh510202.aspx>

1. Скачать версию SQL Server 2014 Express LocalDB можно на странице <http://msdn.microsoft.com/ru-ru/evalcenter/dn434042.aspx> (скачать удалось из-под Chrome после указания логина и пароля от windows-аккаунта, при этом браузер долго не отображал диалог скачивания файла).

2. Подключиться к установленной инстанции SQL Server 2014 Express LocalDB можно через Management Studio. Имя сервера на локальном компьютере вероятно будет следующее: `(localdb)\MSSQLLocalDB` (данная информация содержится только в русской версии статьи в MSDN).

3. В `Flexberry.exe.config` изменить строку соединения примерно следующим образом (имя сервера и базы данных может отличаться):

```xml
<add key="CustomizationStrings" value="SERVER=(localdb)\MSSQLLocalDB;Trusted_connection=yes;DATABASE=CaseLocalDB;"/>
```
Либо можно раскомментировать настройки для SQL Server 2014 Express LocalDB из конфиг-файла (соответствующие настройки по умолчанию нужно закомментировать). При этом mdf-файл по умолчанию будет создаваться в папку C:\Users\ИмяПользователя. 

4. Если вместо русских букв начнут отображаться знаки вопроса, то у соответствующих баз данных необходимо в качестве `Collation` поставить `Cyrillic_General_CI_AS`. Как это сделать, указано в следующих статьях: 

* <http://technet.microsoft.com/en-us/library/ms175835(v=sql.120).aspx>, 
* <http://technet.microsoft.com/en-us/library/ms179254.aspx>. 
В версии Flexberry Tool после 28.10.2014 правильная кодировка выставляется при создании баз данных.


# Работа Flexberry Tool c SQL Server 2012 Express LocalDB
Если по какой-то причине не удалось скачать SQL Server 2014 Express LocalDB, можно воспользоваться версией 2012, скачать которую можно по ссылке <http://www.microsoft.com/ru-ru/download/details.aspx?id=35579>.

Пример строки соединения для LоcalDB:
```xml
<add key="CustomizationStrings" value="Server=(LocalDB)\v11.0; Integrated Security=true;  AttachDbFileName=D:\Flexberry\a1.mdf;"/>
```

