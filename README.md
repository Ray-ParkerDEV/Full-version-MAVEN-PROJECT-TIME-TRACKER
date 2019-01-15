## Project name
### TIME TRACKER

## Description
Time tracking system. The administrator assigns activity to the user. A user can have one or several activities.
 The user notes the amount of time spent on each activity. The user can send a request to add / delete activity.

## Table of Contents
* [Project name](#project-name)
* [Description](#description)
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Installation and running](#installation-and-running)
* [Authors](#author)
* [Общие требования по проекту](#Общие требования по проекту:)

### Technologies
* Maven
* Tomcat
* MySql Data Base
* JDBC
* JSP + JSTL
* Servlets
* Log4J
* JUnit
* Mockito
* JavaScript
* HTML
* CSS
* Custom tag, UseBean
* Localisation
* Connection Pool
* Transaction 
* Sessions, Filters
* Pagination
* Authorisation, Authentication system

### Prerequisites
To run the project you need installed (according to the documentation): 
  * Java 8 (jre/jdk) or higher version 
  * Apache Tomcat 8.5.32 or higher version
  * Apache Maven 3.0.1 or higher version
  * MySQL 8.0.13 or higher version
  
### Installation and running
To install and run the project on localhost:
 * Clone/fork or download the project [time tracker project](https://github.com/Ray-ParkerDEV/Servlet_login_origin) from the GitHub 
 * Create database **timetracker** on MySQL server from folder resources timetracker.sql. By default sql queries creates admin user. You can log in into app as admin using pass: admin, login: admin. You can change parameters of admin by editing corresponding data in file timetracker.sql and to assigne administrator your own credentials.
Install JDK, JRE, set parameters for environment variables. Install Apache Tomcat, install Apache Maven. 
GO ON!!! Nothing gonna stop you from tracking the Activities:)))

## Общие требования по проекту:
Необходимо построить веб-приложение, поддерживающую следующую функциональность:
На основе сущностей предметной области создать классы их описывающие.
Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам
Информацию о предметной области хранить в БД, для доступа использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. В качестве СУБД рекомендуется MySQL. 
Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
Код должен быть документирован.
Приложение должно быть покрыто Юнит-тестами
При разработке бизнес логики использовать сессии и фильтры, и события в системе обрабатывать с помощью Log4j.
В приложении необходимо реализовать Pagination, Transaction в зависимости от Вашего проекта.
Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
    В страницах JSP применять библиотеку JSTL.
Приложение должно корректно реагировать на ошибки и исключения разного рода (Пользователь никогда не должен видеть stack-trace на стороне front-end).
В приложении должна быть реализована система Авторизации и Аутентификации.

## Author
Bodyak Iaroslav (e-mail: [4456602@gmail.com](mailto:4456602@gmail.com))


![alt text](https://github.com/Ray-ParkerDEV/Servlet_login_origin/blob/master/src/main/webapp/images/clock.jpg)



