---
title: PostgreSQL
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_postgresql.html
folder: base-tech
lang: ru
---

## Краткое описание

PostgreSQL — СУБД с открытым исходным кодом, основой которого был код, написанный в Беркли. Она поддерживает большую часть стандарта SQL и предлагает множество современных функций:

* сложные запросы
* внешние ключи
* триггеры
* изменяемые представления
* транзакционная целостность
* многоверсионность

Кроме того, пользователи могут всячески расширять возможности PostgreSQL, например создавая свои

* типы данных
* функции
* операторы
* агрегатные функции
* методы индексирования
* процедурные языки

Благодаря свободной лицензии, PostgreSQL разрешается бесплатно использовать, изменять и распространять всем и для любых целей — личных, коммерческих или учебных.

Базовые ссылки приведены на странице [Вики](https://wiki.postgresql.org/wiki/Russian)

## Лекции, курсы, презентации, видео

### Видеокурсы

* DBA1. Администрирование PostgreSQL 9.4 - Тверь 16-18 декабря 2015 [Слайды](http://files.postgrespro.ru/departments/edu/www/dba1_student_guide.zip)
  * [О курсе](https://www.youtube.com/watch?v=h_GdEaF1Ymc&index=1&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&t=8s)
  * [Введение в PostgreSQL](https://www.youtube.com/watch?v=e2K_-uoqvaM&index=2&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Архитектура PostgreSQL](https://www.youtube.com/watch?v=uha_uTmXslY&index=3&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Установка PostgreSQL](https://www.youtube.com/watch?v=qjCNbv_xIDU&index=4&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Использование psql](https://www.youtube.com/watch?v=6tFz298jxak&index=5&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Базы данных](https://www.youtube.com/watch?v=mo-KgKNQ9fE&index=6&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Табличные пространства](https://www.youtube.com/watch?v=oRqWOdaBia8&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=7)
  * [Системный каталог](https://www.youtube.com/watch?v=igE4pm1uhhQ&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=8)
  * [Основные объекты БД](https://www.youtube.com/watch?v=j-qNqXwkuzo&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=9)
  * [Пользователи и роли](https://www.youtube.com/watch?v=Asicqi0ewO8&index=10&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Схемы](https://www.youtube.com/watch?v=F5A5F0T0xDs&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=11)
  * [Привилегии](https://www.youtube.com/watch?v=gNFLEXEj5HQ&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=12)
  * [Конфигурирование сервера](https://www.youtube.com/watch?v=Q_d8dJNshAg&index=13&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Подключение и аутентификация](https://www.youtube.com/watch?v=BQcU2EFZTVc&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=14)
  * [Мониторинг работы](https://www.youtube.com/watch?v=Uhq_LSiHVIw&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC&index=15)
  * [Сопровождение PostgreSQL](https://www.youtube.com/watch?v=A--R5Q3bNNM&index=16&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Логическое резервирование](https://www.youtube.com/watch?v=LXC15K4pV0o&index=17&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)
  * [Физическое резервирование](https://www.youtube.com/watch?v=xWtuIwfdSH0&index=18&list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC)  
* [DBA 2. «Администрирование PostgreSQL 9.5. Расширенный курс»](https://www.youtube.com/playlist?list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Архитектура PostgreSQL](https://www.youtube.com/watch?v=iODeKnTD1kA&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Изоляция](https://www.youtube.com/watch?v=QU2PVSnBGEY&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=2)
   * [Страницы и версии строк](https://www.youtube.com/watch?v=PTWk0SVKYOA&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=3)
   * [Снимки и блокировки](https://www.youtube.com/watch?v=4jQ2_pLt7E8&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=4)
   * [Очистка](https://www.youtube.com/watch?v=eoprQJW4fH0&index=5&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Автоочистка и заморозка](https://www.youtube.com/watch?v=y_xfhmvJesI&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=6)
   * [Буферный кэш](https://www.youtube.com/watch?v=LHvNUrrHGsQ&index=7&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Упреждающий журнал](https://www.youtube.com/watch?v=GghMySWRH48&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=8)
   * [Контрольная точка](https://www.youtube.com/watch?v=LGeva4__Jag&index=9&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Файловая репликация](https://www.youtube.com/watch?v=NLZrAWLZgmU&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=10)
   * [Потоковая репликация](https://www.youtube.com/watch?v=jBqjltLgoDY&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=11)
   * [Переключение на реплику](https://www.youtube.com/watch?v=ZZNX1OVmrhM&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=12)
   * [Репликация: варианты](https://www.youtube.com/watch?v=TH_MzSKqoUQ&index=13&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Обработка запроса](https://www.youtube.com/watch?v=7l0-VEDDbJo&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=14)
   * [Методы доступа](https://www.youtube.com/watch?v=lFeTYrqCEMw&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=15)
   * [Способы соединения](https://www.youtube.com/watch?v=6agSyZZmqpQ&index=16&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Статистика](https://www.youtube.com/watch?v=rOtXfPEknZw&index=17&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Использование памяти](https://www.youtube.com/watch?v=zMJLujgSu7s&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=18)
   * [Оптимизация запросов](https://www.youtube.com/watch?v=6qEQ0H3-olM&index=19&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Секционирование](https://www.youtube.com/watch?v=iYy2B-pvKLA&index=20&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Локализация](https://www.youtube.com/watch?v=LaD59hlRaMg&index=21&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Обновление сервера](https://www.youtube.com/watch?v=owh41kBr4-o&index=22&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Управление расширениями](https://www.youtube.com/watch?v=cRLLUr808Yk&index=23&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH)
   * [Внешние данные](https://www.youtube.com/watch?v=M-51f8YrRpQ&list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH&index=24)
* [Beginners PostgreSQL database Tutorial 1 - Installing and Setting up PostgreSQL (pgAdmin)](https://www.youtube.com/watch?v=ghTksCsFBcI)   

### Рекомендованные книги

* [Документация по PostgreSQL 9.6.1 от компании PostgresPRO](http://files.postgrespro.ru/departments/edu/www/dba1_student_guide.zip)
* [PostgreSQL для начинающих](https://postgrespro.ru/media/2016/07/14/PostgreSQL%20for%20Beginners%20v2i.pdf)
* [PostgreSQL Tutorial](http://www.tutorialspoint.com/postgresql/)
* []()
* []()
* []()
* []()

## Программное обеспечение

* [Исходные, бинарные коды, Live CD, виртуальные машины](https://www.postgresql.org/download/)
* [Официальный docker-образ](https://hub.docker.com/_/postgres/)


## Лабораторные работы и практические задания

## Возможности по сертификации

* [Администрирование PostgreSQL 9.4. Базовый курс](http://academy.ru/catalog/postgresql/DBA1.html)
* [Администрирование PostgreSQL 9.5. Расширенный курс](http://academy.ru/catalog/postgresql/DBA2.html)

## Перейти

* [Microsoft .NET Framework](gbt_dotnet.html)
* [Главная страница курса](gbt_landing-page.html)

