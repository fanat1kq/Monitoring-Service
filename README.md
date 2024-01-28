 Monitoring-Service
Разработайте веб-сервис для подачи показаний счетчиков отопления, горячей и холодной воды

# Описание
Показания можно подавать один раз в месяц.
Ранее поданые показания редактировать запрещено. 
Последние поданые показания считаются актуальными.
Пользователь может видеть только свои показания, администратор может видеть показания всех пользователей.
Создайте реализацию, которая соответствует описанным ниже требованиям и ограничениям.

# Требования
- предусмотреть расширение перечня подаваемых показаний
- данные хранятся в памяти приложения
- приложение должно быть консольным (никаих спрингов, взаимодействий с БД и тд, только java-core и collections)
- регистрация пользователя
- авторизация пользователя
- реализовать эндпоинт для получения актуальных показаний счетчиков
- реализовать эндпоинт подачи показаний
- реализовать эндпоинт просмотра показаний за конкретный месяц
- реализовать эндпоинт просмотра истории подачи показаний
- реализовать контроль прав пользователя
- Аудит действий пользователя (авторизация, завершение работы, подача показаний, получение истории подачи показаний и тд)

# Нефункциональные требования
Unit-тестирование

Использование

Склоинруйте репозиторий на свой ПК
Откройте проект в IntelliJ IDEA.
Запустите класс "Main", для запуска консольного меню.
Далее следуйте инструкциям консольного меню.
Технологии

Java core
JUnit
Maven
